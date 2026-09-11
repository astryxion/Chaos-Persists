package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class SpiderDriver extends Spider {
    private final GenericTargetSorter targetSorter;
    private int attackTime = 0;

    public SpiderDriver(EntityType<? extends SpiderDriver> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(3, new MyEntityAIWander(this, 0.65f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.getVehicle() != null) {
            return false;
        }
        return true;
    }

    @Override
    public void aiStep() {
        if (this.isRemoved()) {
            return;
        }
        if (this.attackTime > 0) {
            --this.attackTime;
        }
        super.aiStep();
        if (this.level().getDifficulty() != Difficulty.PEACEFUL
                && this.getRandom().nextInt(5) == 0
                && this.getVehicle() == null) {
            LivingEntity e = this.findSpiderRobot();
            if (e != null) {
                this.setTarget(e);
                if (this.distanceToSqr(e)
                        < (double) ((4.0f + e.getBbWidth() / 2.0f) * (4.0f + e.getBbWidth() / 2.0f))) {
                    this.startRiding(e);
                } else {
                    this.getNavigation().moveTo(e, 0.55);
                }
            }
        }
        if (this.level().getDifficulty() != Difficulty.PEACEFUL
                && this.getRandom().nextInt(4) == 0
                && this.getVehicle() != null) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                if (this.distanceToSqr(e)
                                >= (double)
                                        ((11.0f + e.getBbWidth() / 2.0f) * (11.0f + e.getBbWidth() / 2.0f))
                        && this.getVehicle() instanceof SpiderRobot sp) {
                    double d1 = e.getZ() - this.getZ();
                    double d2 = e.getX() - this.getX();
                    double dd = Math.atan2(d1, d2);
                    sp.goThisWay(0.35 * Math.cos(dd), 0.35 * Math.sin(dd));
                }
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (this.attackTime <= 0
                && target.getBoundingBox().maxY > this.getBoundingBox().minY
                && target.getBoundingBox().minY < this.getBoundingBox().maxY) {
            this.attackTime = 16;
            boolean ret = super.doHurtTarget(target);
            if (ret && target instanceof LivingEntity living && this.getRandom().nextInt(2) == 0) {
                living.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
            }
            return ret;
        }
        return false;
    }

    @Override
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
        List<SpiderRobot> candidates =
                this.level()
                        .getEntitiesOfClass(
                                SpiderRobot.class,
                                this.getBoundingBox().inflate(25.0, 15.0, 25.0));
        Collections.sort(candidates, this.targetSorter);
        Iterator<SpiderRobot> var2 = candidates.iterator();
        while (var2.hasNext()) {
            SpiderRobot var4 = var2.next();
            if (var4.getPassengers().isEmpty()) {
                return var4;
            }
        }
        return null;
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
        if (par1EntityLiving instanceof SpiderRobot) {
            return false;
        }
        if (par1EntityLiving instanceof SpiderDriver) {
            return false;
        }
        if (par1EntityLiving instanceof Spider) {
            return false;
        }
        if (par1EntityLiving instanceof CaveSpider) {
            return false;
        }
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player p) {
            if (p.getAbilities().instabuild) {
                return false;
            }
            return true;
        }
        if (this.distanceToSqr(par1EntityLiving) < 36.0) {
            return false;
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class,
                                this.getBoundingBox().inflate(35.0, 15.0, 35.0));
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

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (!level.getEntitiesOfClass(SpiderRobot.class, this.getBoundingBox().inflate(24.0, 12.0, 24.0)).isEmpty()) {
            return true;
        }
        return super.checkSpawnRules(level, spawnReason);
    }
}
