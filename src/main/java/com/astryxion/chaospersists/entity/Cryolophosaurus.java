package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;

public class Cryolophosaurus extends MonsterEntity {
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.25f;

    public Cryolophosaurus(EntityType<? extends Cryolophosaurus> type, World par1World) {
        super(type, par1World);
        this.xpReward = 10;
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.350000023841858));
        this.goalSelector.addGoal(2, new MoveThroughVillageGoal(this, 1.0, false, 512, () -> true));
        this.goalSelector.addGoal(3, new MyEntityAIWanderALot(this, 10, 1.0));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity) this);
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Cryolophosaurus_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Cryolophosaurus_stats.attack)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Cryolophosaurus_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.Cryolophosaurus_stats.defense;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.random.nextInt(6) == 0) {
            return ChaosSounds.CRYO_LIVING;
        }
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.CRYO_HURT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return ChaosSounds.CRYO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(10);
        if (i == 0) {
            return Items.CHICKEN;
        }
        if (i == 1) {
            return ChaosPersists.UraniumNugget;
        }
        if (i == 2) {
            return ChaosPersists.TitaniumNugget;
        }
        return null;
    }

    public void initCreature() {
    }

    @Override
    public net.minecraft.util.ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, net.minecraft.util.Hand hand) {
        return net.minecraft.util.ActionResultType.PASS;
    }

    @Override
    protected void customServerAiStep() {
        LivingEntity e;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.level.random.nextInt(5) == 1 && (e = this.findSomethingToAttack()) != null) {
            this.getNavigation().moveTo(e, 1.25);
            if (this.distanceToSqr(e) < 5.0 && (this.random.nextInt(12) == 0 || this.random.nextInt(14) == 1)) {
                this.doHurtTarget(e);
            }
        }
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
        if (!this.getSensing().canSee(par1LivingEntity)) {
            return false;
        }
        if (par1LivingEntity instanceof Alosaurus) {
            return false;
        }
        if (par1LivingEntity instanceof TRex) {
            return false;
        }
        if (par1LivingEntity instanceof Cryolophosaurus) {
            return false;
        }
        if (par1LivingEntity instanceof Ghost) {
            return false;
        }
        if (par1LivingEntity instanceof GhostSkelly) {
            return false;
        }
        if (par1LivingEntity instanceof CaveFisher) {
            return false;
        }
        if (par1LivingEntity instanceof GammaMetroid) {
            return false;
        }
        if (par1LivingEntity instanceof EntityButterfly) {
            return false;
        }
        if (par1LivingEntity instanceof Firefly) {
            return false;
        }
        if (par1LivingEntity instanceof EntityMosquito) {
            return false;
        }
        if (par1LivingEntity instanceof RockBase) {
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(9.0, 2.0, 9.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity) var2.next();
            var4 = (LivingEntity) var3;
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        if (this.level.isDay() && this.getY() > 50.0) {
            return false;
        }
        return true;
    }
}
