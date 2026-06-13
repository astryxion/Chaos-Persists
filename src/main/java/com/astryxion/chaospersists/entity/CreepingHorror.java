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

public class CreepingHorror extends MonsterEntity {
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.25f;

    public CreepingHorror(EntityType<? extends CreepingHorror> type, World par1World) {
        super(type, par1World);
        this.xpReward = 5;
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
                .add(Attributes.MAX_HEALTH, ChaosPersists.CreepingHorror_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.CreepingHorror_stats.attack)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.CreepingHorror_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.CreepingHorror_stats.defense;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (this.isPersistenceRequired()) {
            return;
        }
        long t = this.level.getDayTime();
        if ((t %= 24000L) > 11000L) {
            return;
        }
        if (this.level.random.nextInt(500) == 1) {
            this.remove();
        }
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return ChaosSounds.CREEPINGHORROR_LIVING;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return ChaosSounds.CREEPINGHORROR_HIT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return ChaosSounds.CREEPINGHORROR_DEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 0.65f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(3);
        if (i == 0) {
            return Items.ROTTEN_FLESH;
        }
        if (i == 1) {
            return Items.BONE;
        }
        return Items.STRING;
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
        if (par1LivingEntity instanceof CreepingHorror) {
            return false;
        }
        if (par1LivingEntity instanceof RockBase) {
            return false;
        }
        if (par1LivingEntity instanceof EnderReaper) {
            return false;
        }
        if (par1LivingEntity instanceof LeafMonster) {
            return false;
        }
        if (par1LivingEntity instanceof Dragon) {
            return false;
        }
        if (par1LivingEntity instanceof TerribleTerror) {
            return false;
        }
        if (par1LivingEntity instanceof LurkingTerror) {
            return false;
        }
        if (par1LivingEntity instanceof PitchBlack) {
            return false;
        }
        if (par1LivingEntity instanceof Firefly) {
            return false;
        }
        if (par1LivingEntity instanceof Island) {
            return false;
        }
        if (par1LivingEntity instanceof IslandToo) {
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 4.0, 16.0));
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
        if (this.level.isDay()) {
            return false;
        }
        if (ChaosPersists.getDimensionId(this.level) != ChaosPersists.getDimension(6) && this.getY() > 15.0) {
            return false;
        }
        return true;
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        return true;
    }
}
