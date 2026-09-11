package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.compat.eeeabsmobs.EeeabsMobsBeamCompat;
import com.astryxion.chaospersists.compat.illageandspillage.IllageAndSpillageExecuteCompat;
import com.astryxion.chaospersists.compat.legendarymonsters.LegendaryMonstersBeamCompat;
import com.astryxion.chaospersists.compat.mutantmonsters.MutantMonstersExecuteCompat;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class PurplePower extends LivingEntity {
    private static final EntityDataAccessor<Integer> PURPLE_TYPE =
            SynchedEntityData.defineId(PurplePower.class, EntityDataSerializers.INT);

    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;
    private int purple_type = 0;

    public PurplePower(EntityType<? extends PurplePower> type, Level level) {
        super(type, level);
        this.fireImmune();
        this.targetSorter = new GenericTargetSorter(this);
        this.noPhysics = true;
        this.refreshDimensions();
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList();
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {}

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 500.0)
                .add(Attributes.ARMOR, 25.0);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.75f, 0.75f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PURPLE_TYPE, 0);
    }

    public void setPurpleType(int par1) {
        if (this.level() == null) {
            return;
        }
        if (this.level().isClientSide()) {
            return;
        }
        this.purple_type = par1;
        this.entityData.set(PURPLE_TYPE, par1);
    }

    public int getPurpleType() {
        return this.entityData.get(PURPLE_TYPE);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void push(Entity par1Entity) {
    }

    public int mygetMaxHealth() {
        return 1000;
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    public int mygetExperienceValue() {
        return 35;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
        if (this.getPurpleType() == 0) {
            if (this.level().isClientSide() && this.level().getRandom().nextInt(4) == 1) {
                this.level()
                        .addParticle(
                                ParticleTypes.FIREWORK,
                                this.getX(),
                                this.getY() + 1.25,
                                this.getZ(),
                                (this.random.nextFloat() - this.random.nextFloat()) / 2.0f,
                                (this.random.nextFloat() - this.random.nextFloat()) / 2.0f,
                                (this.random.nextFloat() - this.random.nextFloat()) / 2.0f);
            }
        } else if (this.level().isClientSide() && this.level().getRandom().nextInt(6) == 1) {
            this.level()
                    .addParticle(
                            ParticleTypes.FIREWORK,
                            this.getX(),
                            this.getY() + 0.6499999761581421,
                            this.getZ(),
                            (this.random.nextFloat() - this.random.nextFloat()) / 5.0f,
                            (this.random.nextFloat() - this.random.nextFloat()) / 5.0f,
                            (this.random.nextFloat() - this.random.nextFloat()) / 5.0f);
        }
        if (this.level().isClientSide()) {
            this.purple_type = this.getPurpleType();
        } else {
            this.setPurpleType(this.purple_type);
        }
        if (!this.level().isClientSide() && this.level().getRandom().nextInt(2500) == 1) {
            if (this.getPurpleType() == 10) {
                this.level()
                        .explode(
                                null,
                                this.getX(),
                                this.getY() + 0.25,
                                this.getZ(),
                                9.1f,
                                this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                                        ? Level.ExplosionInteraction.MOB
                                        : Level.ExplosionInteraction.NONE);
            }
            this.discard();
        }
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        HitResult hit =
                this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.55, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        return hit.getType() == HitResult.Type.MISS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide()) {
            this.customServerAiStep();
        }
    }

    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        LivingEntity e = null;
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.getRandom().nextInt(300) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (!bid.isAir() && keep_trying != 0) {
                zdir = this.getRandom().nextInt(10) + 8;
                xdir = this.getRandom().nextInt(10) + 8;
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                (int) this.getX() + xdir,
                                (int) this.getY() + this.getRandom().nextInt(20) - 10,
                                (int) this.getZ() + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget);
                if (bid.isAir()
                        && !this.canSeeTarget(
                                (double) this.currentFlightTarget.getX(),
                                (double) this.currentFlightTarget.getY(),
                                (double) this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE.defaultBlockState();
                }
                --keep_trying;
            }
        } else if (this.getRandom().nextInt(7) == 2
                && this.level().getDifficulty() != Difficulty.PEACEFUL
                && (e = this.findSomethingToAttack()) != null) {
            this.currentFlightTarget =
                    new BlockPos((int) e.getX(), (int) (e.getY() + (double) (e.getBbHeight() / 2.0f)), (int) e.getZ());
            if (this.distanceToSqr(e)
                    < (double) ((4.0f + e.getBbWidth() / 2.0f) * (4.0f + e.getBbWidth() / 2.0f))) {
                this.doHurtTarget(e);
                this.discard();
            }
        }
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            this.discard();
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.4 - motion.x) * 0.2,
                        (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.20000000149011612,
                        (Math.signum(var5) * 0.4 - motion.z) * 0.2));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 4.0f);
    }

    @Override
    public boolean causeFallDamage(float par1, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double par1, boolean par3, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        return false;
    }

    public boolean checkSpawnRules(net.minecraft.world.level.LevelAccessor level, net.minecraft.world.entity.MobSpawnType spawnReason) {
        return true;
    }

    @Override
    public int getArmorValue() {
        return 25;
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
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player p) {
            if (p.isCreative()) {
                return false;
            }
            if (this.getPurpleType() > 0 && this.getPurpleType() != 10) {
                return false;
            }
            return true;
        }
        if (this.getPurpleType() != 0
                && this.getPurpleType() != 10
                && par1EntityLiving instanceof TamableAnimal tameable
                && tameable.isTame()) {
            return false;
        }
        if (MyUtils.isRoyalty(par1EntityLiving)) {
            return false;
        }
        // Princess beams (types 1-3): do not hunt villagers, golems, livestock, or other
        // non-hostiles (covers Guard Villagers mods). Queen (0) / King (10) stay lethal.
        if (this.getPurpleType() != 0 && this.getPurpleType() != 10) {
            if (par1EntityLiving instanceof SnowGolem
                    || MyUtils.isProtectedCompanion(null, par1EntityLiving)) {
                return false;
            }
            if (!(par1EntityLiving instanceof Enemy)
                    && !(par1EntityLiving instanceof Monster)) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        AABB box = this.getBoundingBox().inflate(32.0, 24.0, 32.0);
        List<LivingEntity> var5 = this.level().getEntitiesOfClass(LivingEntity.class, box);
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        boolean var4 = false;
        if (par1Entity instanceof LivingEntity e) {
            float intended;
            if (this.getPurpleType() == 0 || this.getPurpleType() == 10) {
                intended = e.getHealth() / 4.0f - 1.0f;
                e.setHealth(intended);
                var4 = e.hurt(this.damageSources().mobAttack(this), e.getMaxHealth() / 8.0f);
                if (this.getPurpleType() == 10) {
                    this.level()
                            .explode(
                                    null,
                                    e.getX(),
                                    e.getY() - 0.25,
                                    e.getZ(),
                                    9.1f,
                                    this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                                            ? Level.ExplosionInteraction.MOB
                                            : Level.ExplosionInteraction.NONE);
                }
            } else {
                intended = e.getHealth() * 15.0f / 16.0f;
                e.setHealth(intended);
                var4 = e.hurt(this.damageSources().mobAttack(this), 5.0f);
                if (this.getPurpleType() == 1) {
                    e.setSecondsOnFire(10);
                }
                if (this.getPurpleType() == 2) {
                    e.addEffect(new MobEffectInstance(MobEffects.POISON, 50, 0));
                }
                if (this.getPurpleType() == 3) {
                    e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 50, 0));
                }
            }
            EeeabsMobsBeamCompat.enforceBeamHealth(e, intended);
            LegendaryMonstersBeamCompat.enforceBeamHealth(e, intended);
            MutantMonstersExecuteCompat.forceExecuteIfDowned(e);
            IllageAndSpillageExecuteCompat.forceExecuteIfDowned(e);
        }
        return var4;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        par1NBTTagCompound.putInt("PurpleType", this.purple_type);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.purple_type = par1NBTTagCompound.getInt("PurpleType");
    }
}
