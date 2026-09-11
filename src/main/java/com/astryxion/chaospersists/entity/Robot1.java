package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

public class Robot1 extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Robot1.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private float moveSpeed = 0.2f;

    public Robot1(EntityType<? extends Robot1> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 10, 1.0));
        this.goalSelector.addGoal(
                2, new MoveThroughVillageGoal(this, 0.8999999761581421, false, 4, () -> false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.isPersistenceRequired();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return 5;
    }

    public RenderInfo getRenderInfo() {
        return this.renderdata;
    }

    public void setRenderInfo(RenderInfo r) {
        this.renderdata.rf1 = r.rf1;
        this.renderdata.rf2 = r.rf2;
        this.renderdata.rf3 = r.rf3;
        this.renderdata.rf4 = r.rf4;
        this.renderdata.ri1 = r.ri1;
        this.renderdata.ri2 = r.ri2;
        this.renderdata.ri3 = r.ri3;
        this.renderdata.ri4 = r.ri4;
    }

    @Override
    public int getArmorValue() {
        return 2;
    }

    @Override
    public void aiStep() {
        LivingEntity e;
        super.aiStep();
        e = this.getTarget();
        if (e != null && !e.isAlive()) {
            this.setTarget(null);
            e = null;
        }
        if (e == null) {
            e = this.findSomethingToAttack();
            if (e != null) {
                this.setTarget(e);
            }
        }
        if (this.getRandom().nextInt(8) == 0 && e != null) {
            if (this.distanceToSqr(e) < 5.0
                    && !this.level().isClientSide
                    && this.getRandom().nextInt(18) == 1) {
                this.level()
                        .explode(
                                this,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                2.5f,
                                this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                                        ? ExplosionInteraction.MOB
                                        : ExplosionInteraction.NONE);
                this.discard();
            }
            if (this.level().isClientSide) {
                for (int i = 0; i < 2; ++i) {
                    this.level()
                            .addParticle(
                                    ParticleTypes.SMOKE,
                                    this.getX(),
                                    this.getY() + 1.0,
                                    this.getZ(),
                                    0.0,
                                    0.0,
                                    0.0);
                    this.level()
                            .addParticle(
                                    ParticleTypes.LAVA,
                                    this.getX(),
                                    this.getY() + 1.0,
                                    this.getZ(),
                                    0.0,
                                    0.0,
                                    0.0);
                }
            }
            this.getNavigation().moveTo(e, 1.2);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.KYUUBI_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.SCORPION_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ROBOT1_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.spawnAtLocation(Items.GUNPOWDER);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if ("cactus".equals(par1DamageSource.getMsgId())) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8.0, 3.0, 8.0));
        Collections.sort(candidates, this.targetSorter);
        Iterator<LivingEntity> var2 = candidates.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (this.isSuitableTarget(var4, false)) {
                return var4;
            }
        }
        return null;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof ServerLevelAccessor serverLevel) {
            if (!Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_ROBOT1.get(),
                    serverLevel,
                    spawnReason,
                    this.blockPosition(),
                    serverLevel.getRandom())) {
                return false;
            }
        } else if (level.getMaxLocalRawBrightness(this.blockPosition()) >= 8) {
            return false;
        }
        if (level instanceof Level world && world.isDay()) {
            return false;
        }
        return true;
    }
}
