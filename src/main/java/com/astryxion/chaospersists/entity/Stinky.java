package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

public class Stinky extends TamableAnimal {
    private static final EntityDataAccessor<Integer> SPYRO_FIRE =
            SynchedEntityData.defineId(Stinky.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ACTIVITY =
            SynchedEntityData.defineId(Stinky.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SKIN_COLOR =
            SynchedEntityData.defineId(Stinky.class, EntityDataSerializers.INT);

    private BlockPos currentFlightTarget;
    private final GenericTargetSorter targetSorter;
    public int activity = 1;
    private int owner_flying = 0;
    private float moveSpeed = 0.3f;
    private int skin_color = -1;
    private int syncit = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Stinky(EntityType<? extends Stinky> type, Level level) {
        super(type, level);
        this.fireImmune();
        this.xpReward = 35;
        this.moveSpeed = 0.3f;
        this.setOrderedToSit(false);
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(
                2, new AvoidEntityGoal<>(this, Monster.class, 8.0f, 0.30000001192092896, 0.4000000059604645));
        this.goalSelector.addGoal(3, new MyEntityAIFollowOwner(this, 1.15f, 12.0f, 2.0f));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25, Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(7, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new OpenDoorGoal(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 10.0);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.75f, 0.75f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.activity = 1;
        this.entityData.define(SKIN_COLOR, 0);
        this.entityData.define(ACTIVITY, this.activity);
        this.entityData.define(SPYRO_FIRE, 1);
        this.setOrderedToSit(false);
        this.setTame(false);
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("SpyroActivity", this.entityData.get(ACTIVITY));
        tag.putInt("SpyroFire", this.entityData.get(SPYRO_FIRE));
        tag.putInt("StinkySkin", this.entityData.get(SKIN_COLOR));
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.activity = tag.getInt("SpyroActivity");
        this.entityData.set(ACTIVITY, this.activity);
        this.entityData.set(SPYRO_FIRE, tag.getInt("SpyroFire"));
        this.skin_color = tag.getInt("StinkySkin");
        this.entityData.set(SKIN_COLOR, this.skin_color);
    }

    public int getActivity() {
        this.activity = this.entityData.get(ACTIVITY);
        return this.activity;
    }

    public void setActivity(int par1) {
        this.activity = par1;
        this.entityData.set(ACTIVITY, par1);
    }

    public int getSpyroFire() {
        return this.entityData.get(SPYRO_FIRE);
    }

    public void setSpyroFire(int par1) {
        this.entityData.set(SPYRO_FIRE, par1);
    }

    public int getSkin() {
        this.skin_color = this.entityData.get(SKIN_COLOR);
        return this.skin_color;
    }

    public void setSkin(int par1) {
        this.skin_color = par1;
        this.entityData.set(SKIN_COLOR, 0);
        this.entityData.set(SKIN_COLOR, par1);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public int mygetMaxHealth() {
        return 100;
    }

    /** Keep sitting pose in sync with stay order (1.7.10 EntityTameable.setSitting parity). */
    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);
        if (orderedToSit) {
            if (this.getNavigation() != null) {
                this.getNavigation().stop();
            }
            if (!this.level().isClientSide) {
                PetCombatHelper.onPetSit(this);
            }
            this.owner_flying = 0;
            this.setActivity(1);
            this.setNoGravity(false);
            this.noPhysics = false;
            MyUtils.clearChaosFlight(this);
            Vec3 dm = this.getDeltaMovement();
            this.setDeltaMovement(dm.x, Math.min(dm.y, 0.0), dm.z);
        }
    }

    private boolean isSittingNow() {
        return this.isOrderedToSit() || this.isInSittingPose();
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        ItemStack var2 = par1EntityPlayer.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty() && var2.is(Items.BEEF) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(2) == 1) {
                        this.setTame(true);
                        this.setOwnerUUID(par1EntityPlayer.getUUID());
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            } else if (this.isOwnedBy(par1EntityPlayer)) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Blocks.DEAD_BUSH.asItem())
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            if (!this.level().isClientSide) {
                this.setTame(false);
                this.setHealth((float) this.mygetMaxHealth());
                this.setOwnerUUID(null);
                spawnTamingParticles(false);
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            // OreSpawn 1.7.10 setSitting toggled one flag; on 1.20 ordered-sit and pose are separate.
            this.setOrderedToSit(!this.isOrderedToSit());
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(par1EntityPlayer, hand);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.BEEF);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.CRYO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.6f;
    }

    @Override
    public int getArmorValue() {
        return 6;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        if (this.isTame()) {
            // Was `var4 < ++var3` (OreSpawn copy) — increments every check and never ends.
            int var3 = this.getRandom().nextInt(4) + 1;
            for (int var4 = 0; var4 < var3; ++var4) {
                this.spawnAtLocation(Items.BEEF);
            }
        }
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby()
                ? (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.5f
                : (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.0f;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    public static boolean checkStinkySpawnRules(
            EntityType<Stinky> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (!MyUtils.isDay(level)) {
            return false;
        }
        List<Stinky> buddies =
                level.getLevel()
                        .getEntitiesOfClass(Stinky.class, new AABB(pos).inflate(20.0, 10.0, 20.0));
        return buddies.size() <= 2;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (this.findBuddies() > 2) {
            return false;
        }
        return true;
    }

    public float getAttackStrength(Entity par1Entity) {
        return 10.0f;
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        float var2 = this.getAttackStrength(par1Entity);
        return par1Entity.hurt(this.damageSources().mobAttack(this), var2);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        if (ret && !this.isDeadOrDying()) {
            this.setOrderedToSit(false);
            this.setActivity(2);
        }
        return ret;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        HitResult hit =
                this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.75, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        return hit.getType() == HitResult.Type.MISS;
    }

    private void dropItemFront(Item index, int par1) {
        float f = 0.75f + Math.abs(this.getRandom().nextFloat() * 0.75f);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX() - (double) f * Math.sin(Math.toRadians(this.getYHeadRot())),
                        this.getY() + 0.9,
                        this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYHeadRot())),
                        new ItemStack(index, par1));
        this.level().addFreshEntity(var3);
    }

    private void dropItemRear(Item index, int par1) {
        float f = 0.55f + Math.abs(this.getRandom().nextFloat() * 0.55f);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX() + (double) f * Math.sin(Math.toRadians(this.getYHeadRot())),
                        this.getY() + 0.25,
                        this.getZ() - (double) f * Math.cos(Math.toRadians(this.getYHeadRot())),
                        new ItemStack(index, par1));
        this.level().addFreshEntity(var3);
    }

    @Override
    public void die(DamageSource source) {
        this.noPhysics = false;
        this.setNoGravity(false);
        MyUtils.clearChaosFlight(this);
        this.setActivity(1);
        if (this.getNavigation() != null) {
            this.getNavigation().stop();
        }
        super.die(source);
    }

    @Override
    public void tick() {
        if (this.isDeadOrDying()) {
            this.noPhysics = false;
            if (!this.level().isClientSide) {
                this.setNoGravity(false);
            }
            MyUtils.clearChaosFlight(this);
            super.tick();
            return;
        }
        super.tick();
        if (this.level().isClientSide) {
            return;
        }
        if (this.getRandom().nextInt(1750) == 1) {
            this.playSound(SoundEvents.PLAYER_BURP, 1.0f, 1.0f);
            this.dropItemFront(Items.COAL, 1);
        }
        if (this.getRandom().nextInt(2000) == 2) {
            this.playSound(ChaosSounds.FART, 1.0f, 1.5f);
            if (this.skin_color == 0) {
                this.dropItemRear(Items.BLAZE_POWDER, 1);
            }
            if (this.skin_color == 1) {
                this.dropItemRear(Items.ROTTEN_FLESH, 1);
            }
            if (this.skin_color == 2) {
                this.dropItemRear(Items.MELON_SEEDS, 1);
            }
            if (this.skin_color == 3) {
                this.dropItemRear(ChaosPersists.UraniumNugget, 1);
            }
            if (this.skin_color == 4) {
                this.dropItemRear(Items.WHEAT, 1);
            }
            if (this.skin_color == 5) {
                this.dropItemRear(Items.BRICK, 1);
            }
            if (this.skin_color == 6) {
                this.dropItemRear(Blocks.TORCH.asItem(), 1);
            }
            if (this.skin_color == 7) {
                this.dropItemRear(Items.EMERALD, 1);
            }
            if (this.skin_color == 8) {
                this.dropItemRear(Items.GOLD_INGOT, 1);
            }
            if (this.skin_color == 9) {
                this.dropItemRear(Blocks.OAK_LEAVES.asItem(), 1);
            }
            if (this.skin_color == 10) {
                this.dropItemRear(ChaosPersists.TitaniumNugget, 1);
            }
            if (this.skin_color == 11) {
                this.dropItemRear(ChaosPersists.MyAppleSeed, 1);
            }
            if (this.skin_color == 12) {
                this.dropItemRear(Items.DIAMOND, 1);
            }
            if (this.skin_color == 13) {
                this.dropItemRear(Blocks.SAND.asItem(), 1);
            }
            if (this.skin_color == 14) {
                this.dropItemRear(Blocks.COBBLESTONE.asItem(), 1);
            }
            if (this.skin_color == 15) {
                this.dropItemRear(Items.BONE, 1);
            }
            if (this.skin_color == 16) {
                this.dropItemRear(Items.STRING, 1);
            }
            if (this.skin_color == 17) {
                this.dropItemRear(ChaosPersists.MyCherrySeed, 1);
            }
            if (this.skin_color == 18) {
                this.dropItemRear(ChaosPersists.MyPeachSeed, 1);
            }
        }
    }

    @Override
    public void aiStep() {
        if (this.isDeadOrDying()) {
            this.noPhysics = false;
            if (!this.level().isClientSide) {
                this.setNoGravity(false);
            }
            MyUtils.clearChaosFlight(this);
            super.aiStep();
            return;
        }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.aiStep();
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.07, 0.0));
        }
        if (!this.level().isClientSide && this.getRandom().nextInt(2000) == 1) {
            int i = this.getRandom().nextInt(19);
            this.setSkin(i);
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.skin_color < 0) {
            this.skin_color = this.getRandom().nextInt(19);
        }
        ++this.syncit;
        if (this.syncit > 20) {
            this.syncit = 0;
            if (this.level().isClientSide) {
                this.getActivity();
                this.getSkin();
            } else {
                int j = this.activity;
                this.setActivity(j);
                j = this.skin_color;
                this.setSkin(j);
            }
        }
        if (this.activity == 2) {
            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(motion.x, motion.y * 0.6, motion.z);
        }
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid =
                        this.level()
                                .getBlockState(new BlockPos(x + dx, y + i, z + j))
                                .getBlock();
                if (bid == Blocks.COAL_ORE) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
                bid = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j)).getBlock();
                if (bid == Blocks.COAL_ORE) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x - dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid =
                        this.level()
                                .getBlockState(new BlockPos(x + i, y + dy, z + j))
                                .getBlock();
                if (bid == Blocks.COAL_ORE) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
                bid = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j)).getBlock();
                if (bid == Blocks.COAL_ORE) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y - dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                Block bid =
                        this.level()
                                .getBlockState(new BlockPos(x + i, y + j, z + dz))
                                .getBlock();
                if (bid == Blocks.COAL_ORE) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z + dz;
                        ++found;
                    }
                }
                bid = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz)).getBlock();
                if (bid == Blocks.COAL_ORE) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z - dz;
                        ++found;
                    }
                }
            }
        }
        return found != 0;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }
    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        PetCombatHelper.tickPetCombat(this);

        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }

        if (this.activity != 2) {
            super.customServerAiStep();
        }

        if (this.getRandom().nextInt(100) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(1.0f);
        }

        if (!this.isSittingNow()) {
            if (this.activity == 0) {
                this.setActivity(1);
            }

            if (this.getRandom().nextInt(100) == 1) {
                if (this.getRandom().nextInt(20) == 1) {
                    this.setActivity(2);
                } else {
                    this.setActivity(1);
                }
            }

            this.owner_flying = 0;
            if (this.isTame() && this.getOwner() != null) {
                Player e = (Player) this.getOwner();
                if (e.getAbilities().flying) {
                    this.owner_flying = 1;
                    this.setActivity(2);
                }
            }

            if (this.activity == 1 && this.isTame() && this.getOwner() != null) {
                LivingEntity e = this.getOwner();
                if (this.distanceToSqr(e) > 256.0) {
                    this.setActivity(2);
                }
            }

            this.doMovement();
        } else {
            // Sitting: drop chaos flight so mid-air sit falls (OreSpawn gravity).
            MyUtils.clearChaosFlight(this);
            this.setNoGravity(false);
            if (this.getNavigation() != null) {
                this.getNavigation().stop();
            }
        }
    }

    private void doMovement() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        boolean do_new = false;
        double ox = 0.0;
        double oy = 0.0;
        double oz = 0.0;
        boolean has_owner = false;
        LivingEntity e = null;
        if (this.currentFlightTarget == null) {
            do_new = true;
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.activity == 2 && this.getRandom().nextInt(300) == 0) {
            do_new = true;
        }
        if (this.isTame() && this.getOwner() != null) {
            e = this.getOwner();
            has_owner = true;
            ox = e.getX();
            oy = e.getY();
            oz = e.getZ();
            if (this.distanceToSqr(e) > 100.0) {
                do_new = true;
            }
            if (this.owner_flying != 0 && this.distanceToSqr(e) > 36.0) {
                do_new = true;
            }
        }
        LivingEntity prior = this.getTarget();
        e = PetCombatHelper.resolveCombatTarget(this, prior, this::findSomethingToAttack);
        if (e != prior) {
            this.setTarget(e);
        }
        if (this.getRandom().nextInt(7) == 1
                && this.level().getDifficulty() != Difficulty.PEACEFUL
                && e != null) {
            if (this.isTame() && this.getHealth() / (float) this.mygetMaxHealth() < 0.25f) {
                this.setActivity(2);
                do_new = false;
                this.currentFlightTarget =
                        new BlockPos(
                                (int) (this.getX() + (this.getX() - e.getX())),
                                (int) (this.getY() + 1.0),
                                (int) (this.getZ() + (this.getZ() - e.getZ())));
            } else {
                this.setActivity(2);
                this.currentFlightTarget =
                        new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                do_new = false;
                float reach = 3.0f + e.getBbWidth() / 2.0f;
                if (this.distanceToSqr(e) < (double) (reach * reach)) {
                    this.doHurtTarget(e);
                }
            }
        }
        if (this.activity == 1) {
            // Leaving flight (activity 2): restore gravity or Stinky freezes mid-air.
            MyUtils.clearChaosFlight(this);
            this.setNoGravity(false);
            if (this.getRandom().nextInt(50) == 0 && ChaosPersists.PlayNicely == 0) {
                this.closest = 99999;
                this.tz = 0;
                this.ty = 0;
                this.tx = 0;
                for (int i = 1; i < 9; ++i) {
                    int j = i;
                    if (j > 2) {
                        j = 2;
                    }
                    if (this.scan_it((int) this.getX(), (int) this.getY() + 1, (int) this.getZ(), i, j, i)) {
                        break;
                    }
                    if (i < 4) {
                        continue;
                    }
                    ++i;
                }
                if (this.closest < 99999) {
                    this.getNavigation().moveTo((double) this.tx, (double) this.ty, (double) this.tz, 1.25);
                    if (this.closest < 12) {
                        this.level()
                                .setBlock(
                                        new BlockPos(this.tx, this.ty, this.tz),
                                        Blocks.AIR.defaultBlockState(),
                                        2);
                        this.heal(1.0f);
                        this.playSound(
                                SoundEvents.PLAYER_BURP,
                                0.5f,
                                this.getRandom().nextFloat() * 0.2f + 1.5f);
                    }
                }
            }
            return;
        }
        if (this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1) {
            do_new = true;
        }
        if (do_new) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                int gox = (int) this.getX();
                int goy = (int) this.getY();
                int goz = (int) this.getZ();
                if (has_owner) {
                    gox = (int) ox;
                    goy = (int) oy;
                    goz = (int) oz;
                    if (this.owner_flying == 0) {
                        zdir = this.getRandom().nextInt(4) + 6;
                        xdir = this.getRandom().nextInt(4) + 6;
                    } else {
                        zdir = this.getRandom().nextInt(8);
                        xdir = this.getRandom().nextInt(8);
                    }
                } else {
                    zdir = this.getRandom().nextInt(5) + 6;
                    xdir = this.getRandom().nextInt(5) + 6;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                gox + xdir,
                                goy + this.getRandom().nextInt(6 + this.owner_flying * 2) - 2,
                                goz + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR
                        && !this.canSeeTarget(
                                (double) this.currentFlightTarget.getX(),
                                (double) this.currentFlightTarget.getY(),
                                (double) this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        }
        double speed_factor = 1.0;
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        if (this.owner_flying != 0) {
            speed_factor = 1.75;
            if (this.isTame() && this.getOwner() != null && this.distanceToSqr(this.getOwner()) > 49.0) {
                speed_factor = 3.5;
            }
        }
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.5 - motion.x) * 0.15 * speed_factor,
                        (Math.signum(var3) * 0.7 - motion.y) * 0.21 * speed_factor,
                        (Math.signum(var5) * 0.5 - motion.z) * 0.15 * speed_factor));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setZza((float) (0.75 * speed_factor));
        this.setYRot(this.getYRot() + var8 / 3.0f);
        MyUtils.applyChaosFlightMovement(this);
}

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Stinky) {
            return false;
        }
        if (par1EntityLiving instanceof Mothra) {
            return true;
        }
        if (this.isTame() && !PetCombatHelper.wantsPetToAttack(this, par1EntityLiving)) {
            return false;
        }
        if (PetCombatHelper.isAutoHostileTarget(par1EntityLiving)) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(12.0, 6.0, 12.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)
                    || !this.canSeeTarget(var4.getX(), var4.getY(), var4.getZ())) {
                continue;
            }
            return var4;
        }
        return null;
    }

    private int findBuddies() {
        return this.level()
                .getEntitiesOfClass(Stinky.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0))
                .size();
    }
}
