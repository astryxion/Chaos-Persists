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
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.MoverType;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
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
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class Spyro extends TamableAnimal {
    private static final EntityDataAccessor<Integer> SPYRO_FIRE =
            SynchedEntityData.defineId(Spyro.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ACTIVITY =
            SynchedEntityData.defineId(Spyro.class, EntityDataSerializers.INT);

    private BlockPos currentFlightTarget;
    private final GenericTargetSorter targetSorter;
    public int activity = 1;
    private int owner_flying = 0;
    private boolean target_in_sight = false;
    private float moveSpeed = 0.3f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Spyro(EntityType<? extends Spyro> type, Level level) {
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
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.5f, 0.5f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.activity = 1;
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
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.activity = tag.getInt("SpyroActivity");
        this.entityData.set(ACTIVITY, this.activity);
        this.entityData.set(SPYRO_FIRE, tag.getInt("SpyroFire"));
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid =
                        this.level()
                                .getBlockState(new BlockPos(x + dx, y + i, z + j))
                                .getBlock();
                if (bid == Blocks.LAVA) {
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
                if (bid == Blocks.LAVA) {
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
                if (bid == Blocks.LAVA) {
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
                if (bid == Blocks.LAVA) {
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
                if (bid == Blocks.LAVA) {
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
                if (bid == Blocks.LAVA) {
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

    public int getActivity() {
        return this.entityData.get(ACTIVITY);
    }

    public void setActivity(int par1) {
        this.activity = par1;
        this.entityData.set(ACTIVITY, this.activity);
        if (par1 != 2) {
            this.setNoGravity(false);
            this.noPhysics = false;
            MyUtils.clearChaosFlight(this);
        }
    }

    public int getSpyroFire() {
        return this.entityData.get(SPYRO_FIRE);
    }

    public void setSpyroFire(int par1) {
        this.entityData.set(SPYRO_FIRE, par1);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public int mygetMaxHealth() {
        return 200;
    }

    public int getSpyroHealth() {
        return (int) this.getHealth();
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
            PetCombatHelper.onPetSit(this);
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

    /** True when no solid block is under the hitbox. Do not trust onGround() after chaos flight. */
    private boolean lacksGroundSupport() {
        if (this.level() == null) {
            return !this.onGround();
        }
        double minY = this.getBoundingBox().minY - 0.05;
        double half = this.getBbWidth() * 0.35;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (double xOff : new double[] {0.0, half, -half}) {
            for (double zOff : new double[] {0.0, half, -half}) {
                pos.set(this.getX() + xOff, minY, this.getZ() + zOff);
                if (!this.level().getBlockState(pos).getCollisionShape(this.level(), pos).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Sit / land must actually drop. Chaos flight leaves noGravity on if we only stop the fly AI. */
    private void applyLandFallIfNeeded() {
        if (this.level().isClientSide) {
            return;
        }
        if (!this.isSittingNow() && this.getActivity() == 2) {
            return;
        }
        this.setNoGravity(false);
        this.noPhysics = false;
        MyUtils.clearChaosFlight(this);
        if (!this.lacksGroundSupport()) {
            return;
        }
        this.setOnGround(false);
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x * 0.98, Math.min(motion.y - 0.08, -0.22), motion.z * 0.98);
        this.move(MoverType.SELF, this.getDeltaMovement());
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
                && !var2.isEmpty()
                && var2.is(Blocks.ICE.asItem())
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            if (!this.level().isClientSide) {
                spawnTamingParticles(true);
                this.level().broadcastEntityEvent(this, (byte) 6);
                this.setSpyroFire(0);
                par1EntityPlayer.displayClientMessage(
                        Component.literal("Baby Dragon fireballs extinguished."), true);
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
                && var2.is(Items.DIAMOND)
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)
                && !this.level().isClientSide) {
            Entity ent =
                    spawnCreature(
                            this.level(),
                            "dragon",
                            this.getX(),
                            this.getY(),
                            this.getZ());
            if (ent != null) {
                transferTameToSpawnedEntity(ent, par1EntityPlayer.getUUID());
                this.discard();
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
                && var2.is(Items.FLINT_AND_STEEL)
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            if (!this.level().isClientSide) {
                spawnTamingParticles(true);
                this.level().broadcastEntityEvent(this, (byte) 6);
                this.setSpyroFire(1);
                par1EntityPlayer.displayClientMessage(
                        Component.literal("Baby Dragon fireballs lit!"), true);
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
                && var2.is(Items.NAME_TAG)
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            this.setCustomName(var2.getHoverName());
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
    public int getArmorValue() {
        return 5;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isInSittingPose()) {
            return null;
        }
        if (this.getActivity() != 2) {
            return null;
        }
        return ChaosSounds.ROAR;
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
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        if (this.isTame()) {
            int var3 = this.getRandom().nextInt(4);
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

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    public static boolean checkSpyroSpawnRules(
            EntityType<Spyro> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        return true;
    }

    public float getAttackStrength(Entity par1Entity) {
        return 4.0f;
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
        return super.hurt(par1DamageSource, par2);
    }

    @Override
    public void die(DamageSource source) {
        this.noPhysics = false;
        this.setNoGravity(false);
        MyUtils.clearChaosFlight(this);
        if (this.getNavigation() != null) {
            this.getNavigation().stop();
        }
        super.die(source);
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
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        if (this.isSittingNow()) {
            if (this.getActivity() != 1) {
                this.setActivity(1);
            }
            this.owner_flying = 0;
            this.setNoGravity(false);
            this.noPhysics = false;
            MyUtils.clearChaosFlight(this);
        } else if (this.getActivity() != 2) {
            this.setNoGravity(false);
            this.noPhysics = false;
            MyUtils.clearChaosFlight(this);
        }
        super.tick();
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.07, 0.0));
        }
        if (this.level().isClientSide) {
            return;
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (ChaosPersists.PrinceAutoGrow != 0
                && this.getRandom().nextInt(100000) == 1
                && !this.isPersistenceRequired()) {
            Entity ent =
                    spawnCreature(
                            this.level(),
                            "dragon",
                            this.getX(),
                            this.getY(),
                            this.getZ());
            if (ent != null) {
                transferTameToSpawnedEntity(ent, this.getOwnerUUID());
                this.discard();
                return;
            }
        }
        if (!this.isSittingNow() && this.activity == 2) {
            Vec3 motion = this.getDeltaMovement();
            if (this.getY() < (double) this.currentFlightTarget.getY() + 2.0) {
                this.setDeltaMovement(motion.multiply(1.0, 0.7, 1.0));
            } else if (this.getY() > (double) this.currentFlightTarget.getY() - 2.0) {
                this.setDeltaMovement(motion.multiply(1.0, 0.5, 1.0));
            } else {
                this.setDeltaMovement(motion.multiply(1.0, 0.61, 1.0));
            }
        }
        if (!this.isSittingNow()
                && this.activity == 1
                && this.isTame()
                && this.getOwner() != null
                && this.distanceToSqr(this.getOwner()) > 256.0) {
            this.setActivity(2);
        }
        this.doMovement();
        this.applyLandFallIfNeeded();
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isSittingNow() || this.getActivity() != 2) {
            this.setNoGravity(false);
            super.travel(travelVector);
            return;
        }
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
        if (this.activity == 1 && !this.isSittingNow()) {
            super.customServerAiStep();
        }
        if (this.getRandom().nextInt(100) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(1.0f);
        }
        if (this.level().isClientSide) {
            return;
        }
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.isSittingNow()) {
            return;
        }
        if (this.activity == 0) {
            this.setActivity(1);
        }
        if (this.getRandom().nextInt(20) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 11; ++i) {
                int j = i;
                if (j > 4) {
                    j = 4;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() - 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 6) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.setActivity(1);
                this.getNavigation().moveTo((double) this.tx, (double) (this.ty - 1), (double) this.tz, 1.0);
                if (this.isInLava()) {
                    this.heal(1.0f);
                    this.playSound(SoundEvents.GENERIC_SPLASH, 1.0f, this.getRandom().nextFloat() * 0.2f + 0.9f);
                }
            }
        }
        if (this.getRandom().nextInt(100) == 1 && !this.target_in_sight) {
            int next = 1;
            if (this.getRandom().nextInt(8) == 1) {
                next = 2;
            }
            this.setActivity(next);
        }
        this.owner_flying = 0;
        if (this.isTame() && this.getOwner() instanceof Player owner && owner.getAbilities().flying) {
            this.owner_flying = 1;
            this.setActivity(2);
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
        if (this.isSittingNow()) {
            return;
        }
        if (this.activity == 1) {
            return;
        }
        if (this.getActivity() == 2 && this.getRandom().nextInt(300) == 0) {
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
        if (this.getRandom().nextInt(6) == 1 && this.level().getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity prior = this.getTarget();
            e = PetCombatHelper.resolveCombatTarget(this, prior, this::findSomethingToAttack);
            if (e != prior) {
                this.setTarget(e);
            }
            if (e != null) {
                if (this.isTame() && this.getHealth() / (float) this.mygetMaxHealth() < 0.25f) {
                    this.setActivity(2);
                    this.target_in_sight = false;
                    do_new = false;
                    this.currentFlightTarget =
                            new BlockPos(
                                    (int) (this.getX() + (this.getX() - e.getX())),
                                    (int) (this.getY() + 1.0),
                                    (int) (this.getZ() + (this.getZ() - e.getZ())));
                } else {
                    this.setActivity(2);
                    this.target_in_sight = true;
                    this.currentFlightTarget =
                            new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                    this.getNavigation().moveTo(e, 1.25);
                    do_new = false;
                    float reach = 3.0f + e.getBbWidth() / 2.0f;
                    if (this.distanceToSqr(e) < (double) (reach * reach)) {
                        this.doHurtTarget(e);
                    } else if (this.distanceToSqr(e) < 64.0
                            && !this.isInWater()
                            && (this.getSpyroFire() == 1 && this.getRandom().nextInt(10) == 0
                                    || this.getRandom().nextInt(15) == 1)) {
                        SmallFireball var2 =
                                new SmallFireball(
                                        this.level(),
                                        this,
                                        e.getX() - this.getX(),
                                        e.getY() + 0.25 - (this.getY() + 1.25),
                                        e.getZ() - this.getZ());
                        var2.moveTo(
                                this.getX(),
                                this.getY() + 1.25,
                                this.getZ(),
                                this.getYRot(),
                                this.getXRot());
                        this.level()
                                .playSound(
                                        null,
                                        this.getX(),
                                        this.getY(),
                                        this.getZ(),
                                        SoundEvents.ARROW_SHOOT,
                                        this.getSoundSource(),
                                        0.75f,
                                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                        this.level().addFreshEntity(var2);
                    }
                }
            } else {
                this.target_in_sight = false;
            }
        }
        if (this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1
                && this.getActivity() != 3) {
            do_new = true;
        }
        if (do_new && !this.target_in_sight) {
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
                        zdir = this.getRandom().nextInt(6);
                        xdir = this.getRandom().nextInt(6);
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
                                goy + this.getRandom().nextInt(9 + this.owner_flying * 2) - 4,
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
        double speed_factor = 0.5;
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
        if (MyUtils.shouldSkipCombatTarget(this, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Spyro) {
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

    private static void transferTameToSpawnedEntity(Entity ent, UUID ownerUuid) {
        if (ent == null) {
            return;
        }
        if (ent instanceof TamableAnimal tame) {
            tame.setTame(true);
            if (ownerUuid != null) {
                tame.setOwnerUUID(ownerUuid);
            }
            return;
        }
        if ("Dragon".equals(ent.getClass().getSimpleName())) {
            try {
                ent.getClass().getMethod("setTamed", boolean.class).invoke(ent, Boolean.TRUE);
                if (ownerUuid != null) {
                    ent.getClass().getMethod("setOwnerId", UUID.class).invoke(ent, ownerUuid);
                }
            } catch (ReflectiveOperationException ignored) {
            }
        }
    }

    public static Entity spawnCreature(Level level, String par1, double x, double y, double z) {
        ResourceLocation res =
                par1.contains(":")
                        ? new ResourceLocation(par1)
                        : new ResourceLocation("chaospersists", par1);
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }
}
