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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorldReader;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class Robot1 extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Robot1.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private float moveSpeed = 0.2f;

    public Robot1(EntityType<? extends Robot1> type, World par1World) {
        super(type, par1World);
        this.xpReward = 5;
        this.TargetSorter = new GenericTargetSorter((Entity) this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 10, 1.0));
        this.goalSelector.addGoal(2, new MoveThroughVillageGoal((CreatureEntity)(Object)this, 0.8999999761581421, false, 32, () -> true));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        if (this.renderdata == null) {
            this.renderdata = new RenderInfo();
        }
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
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

    public int getArmorValue() {
        return 2;
    }

    @Override
    public boolean fireImmune() {
        return true;
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
        if (this.level.random.nextInt(8) == 0 && e != null) {
            if (this.distanceToSqr(e) < 5.0 && !this.level.isClientSide && this.level.random.nextInt(18) == 1) {
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2.5f, this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
                this.remove();
            }
            for (int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 1.0, this.getZ(), 0.0, 0.0, 0.0);
                this.level.addParticle(ParticleTypes.LAVA, this.getX(), this.getY() + 1.0, this.getZ(), 0.0, 0.0, 0.0);
            }
            this.getNavigation().moveTo(e, 1.2);
        }
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return ChaosSounds.KYUUBI_LIVING;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.SCORPION_HIT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return ChaosSounds.ROBOT1_DEATH;
    }

    protected float getSoundVolume() {
        return 1.0f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.GUNPOWDER;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        return ActionResultType.PASS;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        return super.doHurtTarget(par1Entity);
    }

    @Override
    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            ret = super.hurt(par1DamageSource, par2);
        }
        return ret;
    }

    private boolean isSuitableTarget(LivingEntity par1LivingEntity, boolean par2) {
        if (par1LivingEntity == null) {
            return false;
        }
        if (par1LivingEntity == this) {
            return false;
        }
        if (!par1LivingEntity.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1LivingEntity)) {
            return false;
        }
        if (!this.getSensing().canSee(par1LivingEntity)) {
            return false;
        }
        if (par1LivingEntity instanceof MonsterEntity) {
            return false;
        }
        if (par1LivingEntity instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity) par1LivingEntity;
            if (p.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8.0D, 3.0D, 8.0D));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();

        while (var2.hasNext()) {
            Entity var3 = (Entity) var2.next();
            LivingEntity var4 = (LivingEntity) var3;

            if (isSuitableTarget(var4, false)) {
                return var4;
            }
        }
        return null;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        return true;
    }
}
