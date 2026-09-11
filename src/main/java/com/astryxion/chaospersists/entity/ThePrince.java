package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.item.ThunderBolt;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;
import com.astryxion.chaospersists.util.RoyalPetFollowHelper;

public class ThePrince extends TamableAnimal {
    private static final EntityDataAccessor<Integer> SPYRO_FIRE =
            SynchedEntityData.defineId(ThePrince.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ACTIVITY =
            SynchedEntityData.defineId(ThePrince.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ATTACKING =
            SynchedEntityData.defineId(ThePrince.class, EntityDataSerializers.INT);

    private BlockPos currentFlightTarget;
    private final GenericTargetSorter targetSorter;
    public int activity = 1;
    private int owner_flying = 0;
    private float moveSpeed = 0.3f;
    private int syncit = 0;
    private int head1ext = 0;
    private int head2ext = 0;
    private int head3ext = 0;
    private int head1dir = 1;
    private int head2dir = 1;
    private int head3dir = 1;
    private int ok_to_grow = 0;
    private int kill_count = 0;
    private int fed_count = 0;
    private int day_count = 0;
    private int is_day = 0;

    public ThePrince(EntityType<? extends ThePrince> type, Level level) {
        super(type, level);
        this.fireImmune();
        this.xpReward = 50;
        this.moveSpeed = 0.32f;
        this.setOrderedToSit(false);
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner(this, 1.15f, 12.0f, 2.0f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, LivingEntity.class, 6.0f));
        this.goalSelector.addGoal(5, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new OpenDoorGoal(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 500.0)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ARMOR, 16.0);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.75f, 1.25f);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.activity = 1;
        this.entityData.define(ATTACKING, 0);
        this.entityData.define(ACTIVITY, this.activity);
        this.entityData.define(SPYRO_FIRE, 1);
        this.setOrderedToSit(false);
        this.setTame(false);
        this.noPhysics = false;
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("SpyroActivity", this.entityData.get(ACTIVITY));
        tag.putInt("SpyroFire", this.entityData.get(SPYRO_FIRE));
        tag.putInt("SpyroGrow", this.ok_to_grow);
        tag.putInt("SpyroKill", this.kill_count);
        tag.putInt("SpyroFed", this.fed_count);
        tag.putInt("SpyroDay", this.day_count);
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.activity = tag.getInt("SpyroActivity");
        this.entityData.set(ACTIVITY, this.activity);
        this.entityData.set(SPYRO_FIRE, tag.getInt("SpyroFire"));
        this.ok_to_grow = tag.getInt("SpyroGrow");
        this.kill_count = tag.getInt("SpyroKill");
        this.fed_count = tag.getInt("SpyroFed");
        this.day_count = tag.getInt("SpyroDay");
        this.refreshDimensions();
    }

    public int getActivity() {
        int i;
        this.activity = i = this.entityData.get(ACTIVITY);
        return i;
    }

    public void setActivity(int par1) {
        this.activity = par1;
        this.entityData.set(ACTIVITY, 0);
        this.entityData.set(ACTIVITY, par1);
    }

    public int getSpyroFire() {
        return this.entityData.get(SPYRO_FIRE);
    }

    public void setSpyroFire(int par1) {
        this.entityData.set(SPYRO_FIRE, par1);
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, par1);
    }

    public int getHead1Ext() {
        return this.head1ext;
    }

    public int getHead2Ext() {
        return this.head2ext;
    }

    public int getHead3Ext() {
        return this.head3ext;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public int mygetMaxHealth() {
        return 500;
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);
        if (!this.level().isClientSide) {
            if (orderedToSit) {
                this.setActivity(1);
                this.setAttacking(0);
                PetCombatHelper.onPetSit(this);
                MyUtils.clearChaosFlight(this);
                this.setNoGravity(false);
                this.noPhysics = false;
                this.getNavigation().stop();
                // Clear flight XZ; keep downward motion so midair stay falls (OreSpawn 1.7.10).
                Vec3 dm = this.getDeltaMovement();
                this.setDeltaMovement(0.0, Math.min(dm.y, 0.0), 0.0);
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        ItemStack var2 = par1EntityPlayer.getItemInHand(hand);
        if (var2.isEmpty()) {
            var2 = ItemStack.EMPTY;
        } else if (var2.getCount() <= 0) {
            par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()
                && var2.is(Blocks.DIAMOND_BLOCK.asItem())
                && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            if (!this.level().isClientSide) {
                this.tame(par1EntityPlayer);
                spawnTamingParticles(true);
                this.level().broadcastEntityEvent(this, (byte) 7);
                this.heal((float) this.mygetMaxHealth() - this.getHealth());
                this.ok_to_grow = 1;
                this.kill_count = 1000;
                this.fed_count = 1000;
                this.day_count = 1000;
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
                && this.isOwnedBy(par1EntityPlayer)
                && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            FoodProperties var3 = var2.getFoodProperties(this);
            if (var3 != null) {
                if (!this.level().isClientSide) {
                    if ((float) this.mygetMaxHealth() > this.getHealth()) {
                        this.heal((float) (var3.getNutrition() * 10));
                    }
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                    ++this.fed_count;
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
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
                        Component.literal("Prince fireballs extinguished."), true);
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
                        Component.literal("Prince fireballs lit!"), true);
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (!var2.isEmpty()
                && var2.is(Items.DIAMOND)
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)
                && !this.level().isClientSide
                && this.ok_to_grow != 0) {
            Entity ent =
                    spawnCreature(
                            this.level(),
                            "The Young Prince",
                            this.getX(),
                            this.getY(),
                            this.getZ());
            if (ent != null) {
                if (this.isTame()) {
                    transferTameToYoungPrince(ent, this.getOwnerUUID());
                }
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
            if (!this.level().isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(par1EntityPlayer, hand);
    }

    public void set_ok_to_grow() {
        this.ok_to_grow = 1;
        this.kill_count = 0;
        this.fed_count = 0;
        this.day_count = 0;
    }

    public int getPrinceKillCount() {
        return this.kill_count;
    }

    public int getPrinceFedCount() {
        return this.fed_count;
    }

    public int getPrinceDayCount() {
        return this.day_count;
    }

    public boolean isPrinceGrowthItemFed() {
        return this.ok_to_grow != 0;
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.BEEF);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isInSittingPose()) {
            return null;
        }
        if (this.getAttacking() == 0) {
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
        return 0.6f;
    }

    @Override
    public int getArmorValue() {
        return 16;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        int var3 = this.getRandom().nextInt(4) + 1;
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(Items.BEEF);
        }
    }

    @Override
    public float getVoicePitch() {
        return (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2f + 1.3f;
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
        return true;
    }

    public float getAttackStrength(Entity par1Entity) {
        return 10.0f;
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        float var2 = this.getAttackStrength(par1Entity);
        boolean var4 = par1Entity.hurt(this.damageSources().mobAttack(this), var2);
        if (par1Entity instanceof LivingEntity el && el.getHealth() <= 0.0f) {
            ++this.kill_count;
        }
        return var4;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            ret = super.hurt(par1DamageSource, par2);
            if (ret && !this.isDeadOrDying()) {
                this.setOrderedToSit(false);
                this.setActivity(2);
            }
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

    @Override
    public void tick() {
        int i;
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
        this.noPhysics = this.getActivity() == 2;
        if (!this.level().isClientSide) {
            this.setNoGravity(this.getActivity() == 2);
        }
        if (this.getRandom().nextInt(10) == 1) {
            i = this.getRandom().nextInt(3);
            if (i == 0) {
                this.head1dir = 2;
            }
            if (i == 1) {
                this.head1dir = -2;
            }
            if (i == 2) {
                this.head1dir = 0;
            }
        }
        if (this.getRandom().nextInt(10) == 1) {
            i = this.getRandom().nextInt(3);
            if (i == 0) {
                this.head2dir = 2;
            }
            if (i == 1) {
                this.head2dir = -2;
            }
            if (i == 2) {
                this.head2dir = 0;
            }
        }
        if (this.getRandom().nextInt(10) == 1) {
            i = this.getRandom().nextInt(3);
            if (i == 0) {
                this.head3dir = 2;
            }
            if (i == 1) {
                this.head3dir = -2;
            }
            if (i == 2) {
                this.head3dir = 0;
            }
        }
        this.head1ext += this.head1dir;
        if (this.head1ext < 0) {
            this.head1ext = 0;
        }
        if (this.head1ext > 60) {
            this.head1ext = 60;
        }
        this.head2ext += this.head2dir;
        if (this.head2ext < 0) {
            this.head2ext = 0;
        }
        if (this.head2ext > 60) {
            this.head2ext = 60;
        }
        this.head3ext += this.head3dir;
        if (this.head3ext < 0) {
            this.head3ext = 0;
        }
        if (this.head3ext > 60) {
            this.head3ext = 60;
        }
    }

    @Override
    public void aiStep() {
        if (this.isDeadOrDying()) {
            super.aiStep();
            return;
        }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.aiStep();
        if (this.isOrderedToSit() || this.isInSittingPose()) {
            this.getNavigation().stop();
            // OreSpawn 1.7.10 does not zero motion while sitting — only stop AI so midair stay can fall.
            if (!this.level().isClientSide && this.onGround()) {
                this.setDeltaMovement(Vec3.ZERO);
            }
        }
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.07, 0.0));
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        ++this.syncit;
        if (this.syncit > 20) {
            this.syncit = 0;
            if (this.level().isClientSide) {
                this.getActivity();
            } else {
                int j = this.activity;
                this.setActivity(j);
            }
        }
        if (this.activity == 2) {
            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(motion.x, motion.y * 0.6, motion.z);
        }
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
        if (this.isTame() && !RoyalPetFollowHelper.isStayingPut(this)) {
            RoyalPetFollowHelper.syncRoyalFollow(this);
        }

        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }

        if (this.activity != 2) {
            super.customServerAiStep();
        }

        if (this.getRandom().nextInt(200) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(1.0f);
        }

        if (!this.isTame()) {
            Player p = this.level().getNearestPlayer(this, 10.0);
            if (p != null) {
                this.tame(p);
                spawnTamingParticles(true);
                this.level().broadcastEntityEvent(this, (byte) 7);
                this.heal((float) this.mygetMaxHealth() - this.getHealth());
            }
        }

        if (!this.isOrderedToSit() && !this.isInSittingPose()) {
            if (this.activity == 0) {
                this.setActivity(1);
            }

            if (this.getRandom().nextInt(100) == 1) {
                // 1.7.10: randomly land (activity 1) even while airborne. A prior 1.20 guard
                // required onGround while flying, so after attacking birds they never landed.
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
        }

        if ((ChaosPersists.PrinceAutoGrow != 0 || this.ok_to_grow != 0)
                && this.kill_count > 25
                && this.fed_count > 10
                && this.day_count > 10) {
            Entity ent =
                    spawnCreature(
                            this.level(),
                            "The Young Prince",
                            this.getX(),
                            this.getY(),
                            this.getZ());
            if (ent != null) {
                if (this.isTame()) {
                    transferTameToYoungPrince(ent, this.getOwnerUUID());
                }
                this.discard();
            }
        }

        if (this.is_day == 0) {
            this.is_day = 1;
            if (!this.level().isDay()) {
                this.is_day = -1;
            }
        } else {
            if (this.is_day == -1 && this.level().isDay()) {
                this.day_count += 1;
            }
            this.is_day = 1;
            if (!this.level().isDay()) {
                this.is_day = -1;
            }
        }
    }

    private void doMovement() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 10;
        boolean do_new = false;
        double ox = 0.0;
        double oy = 0.0;
        double oz = 0.0;
        boolean has_owner = false;
        double rr = 0.0;
        double rhdir = 0.0;
        double rdd = 0.0;
        double pi = 3.1415926545;
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
            oy = e.getY() + 1.0;
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
        if (e != null) {
            if (this.isTame() && this.getHealth() / (float) this.mygetMaxHealth() < 0.25f) {
                this.setActivity(2);
                this.setAttacking(0);
                // 1.7.10: orbit the owner when hurt. Fly-away-from-enemy every tick with
                // noPhysics sent them through walls in a straight line.
                do_new = has_owner;
                if (!has_owner) {
                    this.currentFlightTarget =
                            new BlockPos(
                                    (int) (this.getX() + (this.getX() - e.getX())),
                                    (int) (this.getY() + 1.0),
                                    (int) (this.getZ() + (this.getZ() - e.getZ())));
                }
            } else {
                this.setActivity(2);
                this.setAttacking(1);
                this.currentFlightTarget =
                        new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                do_new = false;
                float reach = 3.0f + e.getBbWidth() / 2.0f;
                if (this.distanceToSqr(e) < (double) (reach * reach)) {
                    this.doHurtTarget(e);
                } else if (!(this.distanceToSqr(e) <= 25.0
                        || this.distanceToSqr(e) >= 144.0
                        || this.isInWater()
                        || this.getSpyroFire() == 0
                        || this.getRandom().nextInt(3) != 0 && this.getRandom().nextInt(4) != 1)) {
                    int which = this.getRandom().nextInt(3);
                    if (which == 0) {
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rdd =
                                Math.abs(
                                                rr
                                                        - (rhdir =
                                                                Math.toRadians(
                                                                        (this.getYRot() + 90.0f)
                                                                                % 360.0f)))
                                        % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            this.firecanon(e);
                        }
                    } else if (which == 1) {
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rdd =
                                Math.abs(
                                                rr
                                                        - (rhdir =
                                                                Math.toRadians(
                                                                        (this.getYRot() + 90.0f)
                                                                                % 360.0f)))
                                        % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            this.firecanonl(e);
                        }
                    } else {
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rdd =
                                Math.abs(
                                                rr
                                                        - (rhdir =
                                                                Math.toRadians(
                                                                        (this.getYRot() + 90.0f)
                                                                                % 360.0f)))
                                        % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            this.firecanoni(e);
                        }
                    }
                }
            }
        } else {
            this.setAttacking(0);
            if (this.isTame()
                    && has_owner
                    && this.getHealth() / (float) this.mygetMaxHealth() < 0.25f) {
                this.setActivity(2);
                do_new = true;
            }
        }
        if (this.activity == 1) {
            MyUtils.clearChaosFlight(this);
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

    /** Same targets as {@link com.astryxion.chaospersists.util.MyUtils#isRoyalty}. */
    private static boolean isRoyaltyTarget(LivingEntity e) {
        if (e instanceof ThePrince) {
            return true;
        }
        String n = e.getClass().getSimpleName();
        return "ThePrinceTeen".equals(n)
                || "ThePrinceAdult".equals(n)
                || "ThePrincess".equals(n)
                || "TheKing".equals(n)
                || "KingHead".equals(n)
                || "TheQueen".equals(n)
                || "QueenHead".equals(n)
                || "PurplePower".equals(n);
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
        if (isRoyaltyTarget(par1EntityLiving)) {
            return false;
        }
        if (this.isTame() && !PetCombatHelper.wantsPetToAttack(this, par1EntityLiving)) {
            return false;
        }
        if (PetCombatHelper.isAutoHostileTarget(par1EntityLiving)) {
            return true;
        }
        if (par1EntityLiving instanceof EntityButterfly) {
            return true;
        }
        if (par1EntityLiving instanceof Cockateil) {
            return true;
        }
        if (par1EntityLiving instanceof Dragonfly) {
            return true;
        }
        if (par1EntityLiving instanceof EntityMosquito) {
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
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            boolean canSee =
                    this.canSeeTarget(var4.getX(), var4.getY(), var4.getZ())
                            || this.canSeeTarget(
                                    var4.getX(),
                                    var4.getY() + (double) (var4.getBbHeight() * 0.5f),
                                    var4.getZ());
            if (!canSee && this.distanceToSqr(var4) > 64.0) {
                continue;
            }
            return var4;
        }
        return null;
    }

    private void firecanon(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 3.0;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        float r1 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
        float r2 = 3.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
        float r3 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
        BetterFireball bf =
                new BetterFireball(
                        this.level(),
                        this,
                        e.getX() - cx + (double) r1,
                        e.getY() + (double) (e.getBbHeight() / 2.0f) - (this.getY() + yoff) + (double) r2,
                        e.getZ() - cz + (double) r3);
        bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
        bf.setBig();
        if (this.getRandom().nextInt(2) == 1) {
            bf.setSmall();
        }
        this.level()
                .playSound(
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        this.getSoundSource(),
                        1.0f,
                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
        this.level().addFreshEntity(bf);
    }

    private void firecanonl(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 3.0;
        double var3;
        double var5;
        double var7;
        float var9;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        this.level()
                .playSound(
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        this.getSoundSource(),
                        1.0f,
                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
        ThunderBolt lb =
                new ThunderBolt(
                        ChaosPersists.ENTITY_TYPE_THUNDER_BOLT.get(), cx, this.getY() + yoff, cz, this.level());
        lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
        var3 = e.getX() - lb.getX();
        var5 = e.getY() + 0.25 - lb.getY();
        var7 = e.getZ() - lb.getZ();
        var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
        lb.shoot(var3, var5 + (double) var9, var7, 1.4f, 4.0f);
        Vec3 lbdm = lb.getDeltaMovement();
        lb.setDeltaMovement(lbdm.x * 3.0, lbdm.y * 3.0, lbdm.z * 3.0);
        this.level().addFreshEntity(lb);
    }

    private void firecanoni(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 3.0;
        double var3;
        double var5;
        double var7;
        float var9;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        this.level()
                .playSound(
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        this.getSoundSource(),
                        1.0f,
                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
        IceBall lb =
                new IceBall(
                        ChaosPersists.ENTITY_TYPE_ICE_BALL.get(), cx, this.getY() + yoff, cz, this.level());
        lb.setIceMaker(1);
        lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
        var3 = e.getX() - lb.getX();
        var5 = e.getY() + 0.25 - lb.getY();
        var7 = e.getZ() - lb.getZ();
        var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
        lb.shoot(var3, var5 + (double) var9, var7, 1.4f, 4.0f);
        Vec3 lbdm = lb.getDeltaMovement();
        lb.setDeltaMovement(lbdm.x * 3.0, lbdm.y * 3.0, lbdm.z * 3.0);
        this.level().addFreshEntity(lb);
    }

    private static void transferTameToYoungPrince(Entity ent, UUID ownerUuid) {
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
        if (ent instanceof ThePrinceTeen teen) {
            teen.setTame(true);
            if (ownerUuid != null) {
                teen.setOwnerUUID(ownerUuid);
            }
        }
    }

    public static Entity spawnCreature(Level level, String par1, double par2, double par4, double par6) {
        ResourceLocation res =
                par1.contains(":")
                        ? new ResourceLocation(par1)
                        : new ResourceLocation(
                                "chaospersists", par1.toLowerCase(Locale.ROOT).replace(' ', '_'));
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(par2, par4, par6, level.getRandom().nextFloat() * 360.0f, 0.0f);
        if (!serverLevel.addFreshEntity(entity)) {
            return null;
        }
        return entity;
    }
}
