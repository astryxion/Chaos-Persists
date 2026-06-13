/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.SpiderDriver
 *  com.astryxion.chaospersists.SpiderRobot
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.monster.CaveSpiderEntity
 *  net.minecraft.entity.monster.Spider
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.EffectInstance
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.SpiderRobot;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class SpiderDriver
extends SpiderEntity {
    private GenericTargetSorter TargetSorter = null;
    private int attackTime = 0;

    public SpiderDriver(EntityType<? extends SpiderDriver> type, World par1World) {
        super(type, par1World);
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(3, new MyEntityAIWander(this, 0.65f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.getVehicle() != null) {
            return false;
        }
        return true;
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected Entity findPlayerEntityToAttack() {
        double d0 = 16.0;
        return this.level.getNearestPlayer(this, d0);
    }

    protected void customServerAiStep() {
        LivingEntity e;
        if (!this.isAlive()) {
            return;
        }
        if (this.attackTime > 0) {
            this.attackTime--;
        }
        super.customServerAiStep();
        if (this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(5) == 0 && this.getVehicle() == null && (e = this.findSpiderRobot()) != null) {
            this.lookAt((Entity)e, 10.0f, 10.0f);
            if (this.distanceToSqr((Entity)e) < (double)((4.0f + e.getBbWidth() / 2.0f) * (4.0f + e.getBbWidth() / 2.0f))) {
                this.startRiding((Entity)e);
            } else {
                this.getNavigation().moveTo((Entity)e, 0.55);
            }
        }
        if (this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(4) == 0 && this.getVehicle() != null && (e = this.findSomethingToAttack()) != null) {
            this.lookAt((Entity)e, 10.0f, 10.0f);
            if (this.distanceToSqr((Entity)e) >= (double)((11.0f + e.getBbWidth() / 2.0f) * (11.0f + e.getBbWidth() / 2.0f)) && this.getVehicle() instanceof SpiderRobot) {
                SpiderRobot sp = (SpiderRobot)this.getVehicle();
                double d1 = e.getZ() - this.getZ();
                double d2 = e.getX() - this.getX();
                double dd = Math.atan2(d1, d2);
                sp.goThisWay(0.35 * Math.cos(dd), 0.35 * Math.sin(dd));
            }
        }
    }

    protected void attackEntity(Entity par1Entity, float par2) {
        if (this.attackTime <= 0 && par2 < 2.0f && par1Entity.getBoundingBox().maxY > this.getBoundingBox().minY && par1Entity.getBoundingBox().minY < this.getBoundingBox().maxY) {
            this.attackTime = 16;
            this.doHurtTarget(par1Entity);
            if (this.level.random.nextInt(2) == 0) {
                ((LivingEntity)par1Entity).addEffect(new EffectInstance(Effects.POISON, 60, 0));
            }
        }
    }

    public int getArmorValue() {
        if (this.getVehicle() != null) {
            return 8;
        }
        return 20;
    }

    private LivingEntity findSpiderRobot() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(SpiderRobot.class, this.getBoundingBox().inflate(25.0, 15.0, 25.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!var4.getPassengers().isEmpty()) continue;
            return var4;
        }
        return null;
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == this) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof SpiderRobot) {
            return false;
        }
        if (par1Mob instanceof SpiderDriver) {
            return false;
        }
        if (par1Mob instanceof SpiderEntity) {
            return false;
        }
        if (par1Mob instanceof CaveSpiderEntity) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (this.distanceToSqr((Entity)par1Mob) < 36.0) {
            return false;
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(35.0, 15.0, 35.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false)) continue;
            return var4;
        }
        return null;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        List<SpiderRobot> nearby = this.level.getEntitiesOfClass(SpiderRobot.class, this.getBoundingBox().inflate(24.0, 12.0, 24.0));
        if (!nearby.isEmpty()) {
            return true;
        }
        if (!(this.level instanceof net.minecraft.world.IServerWorld)) {
            return false;
        }
        BlockPos blockpos = this.blockPosition();
        @SuppressWarnings("unchecked")
        EntityType<? extends MonsterEntity> monsterType = (EntityType<? extends MonsterEntity>) this.getType();
        return MonsterEntity.checkMonsterSpawnRules(monsterType, (net.minecraft.world.IServerWorld) this.level, reason, blockpos, this.random);
    }
}

