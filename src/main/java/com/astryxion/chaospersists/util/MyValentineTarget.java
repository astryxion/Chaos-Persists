package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.function.Predicate;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class MyValentineTarget extends MyEntityAITarget {
    LivingEntity targetEntity;
    LivingEntity Me;
    Class<? extends Entity> targetClass;
    int targetChance;
    private final Predicate<Entity> targetEntitySelector;
    private final MyValentineTargetSorter theNearestAttackableTargetSorter;

    public MyValentineTarget(MobEntity par1LivingEntity, Class<?> par2Class, float par3, int par4, boolean par5) {
        this(par1LivingEntity, par2Class, par3, par4, par5, false);
    }

    public MyValentineTarget(MobEntity par1LivingEntity, Class<?> par2Class, float par3, int par4, boolean par5, boolean par6) {
        this(par1LivingEntity, par2Class, par3, par4, par5, par6, null);
    }

    @SuppressWarnings("unchecked")
    public MyValentineTarget(MobEntity par1, Class<?> par2, float par3, int par4, boolean par5, boolean par6, Predicate<Entity> par7IEntitySelector) {
        super(par1, par3, par5, par6);
        this.targetClass = (Class<? extends Entity>) par2;
        this.targetDistance = par3;
        this.targetChance = par4;
        this.theNearestAttackableTargetSorter = new MyValentineTargetSorter(this, par1);
        this.targetEntitySelector = par7IEntitySelector != null ? par7IEntitySelector : (e -> true);
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        this.Me = par1;
    }

    @Override
    public boolean canUse() {
        if (ChaosPersists.valentines_day == 0) {
            return false;
        }
        if (this.Me instanceof Girlfriend) {
            Girlfriend gf = (Girlfriend) this.Me;
            if (gf.feelingBetter != 0) {
                return false;
            }
        }
        if (this.targetChance > 0 && this.taskOwner.getRandom().nextInt(100) > this.targetChance) {
            return false;
        }
        List<? extends Entity> var5 = this.taskOwner.level.getEntitiesOfClass(
                this.targetClass,
                this.taskOwner.getBoundingBox().inflate(this.targetDistance, 4.0D, this.targetDistance),
                (java.util.function.Predicate<? super Entity>) this.targetEntitySelector::test);
        Collections.sort(var5, this.theNearestAttackableTargetSorter);
        Iterator<? extends Entity> var2 = var5.iterator();
        while (var2.hasNext()) {
            Entity var3 = var2.next();
            if (var3 instanceof LivingEntity) {
                LivingEntity var4 = (LivingEntity) var3;
                if (isSuitableTarget(var4, false)) {
                    this.targetEntity = var4;
                    return true;
                }
            }
        }
        this.targetEntity = null;
        return false;
    }

    @Override
    public void start() {
        this.taskOwner.setTarget(this.targetEntity);
        super.start();
    }
}
