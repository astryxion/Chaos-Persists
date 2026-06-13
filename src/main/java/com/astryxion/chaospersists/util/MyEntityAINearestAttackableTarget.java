package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Girlfriend;
import java.util.function.Predicate;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;

public class MyEntityAINearestAttackableTarget extends MyEntityAITarget {
    LivingEntity targetEntity;
    Class<? extends LivingEntity> targetClass;
    int targetChance;
    private final Predicate<LivingEntity> targetEntitySelector;
    private final MyEntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;

    public MyEntityAINearestAttackableTarget(MobEntity par1LivingEntity, Class<?> par2Class, float par3, int par4, boolean par5) {
        this(par1LivingEntity, par2Class, par3, par4, par5, false);
    }

    public MyEntityAINearestAttackableTarget(MobEntity par1LivingEntity, Class<?> par2Class, float par3, int par4, boolean par5, boolean par6) {
        this(par1LivingEntity, par2Class, par3, par4, par5, par6, null);
    }

    @SuppressWarnings("unchecked")
    public MyEntityAINearestAttackableTarget(MobEntity par1, Class<?> par2, float par3, int par4, boolean par5, boolean par6,
            Predicate<LivingEntity> par7IEntitySelector) {
        super(par1, par3, par5, par6);
        this.targetClass = (Class<? extends LivingEntity>) par2;
        this.targetDistance = par3;
        this.targetChance = par4;
        this.theNearestAttackableTargetSorter = new MyEntityAINearestAttackableTargetSorter(this, par1);
        this.targetEntitySelector = par7IEntitySelector != null ? par7IEntitySelector : (e -> true);
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        if (this.taskOwner instanceof TameableEntity && !((TameableEntity) this.taskOwner).isTame()) {
            return false;
        }
        if (this.taskOwner instanceof Girlfriend && !((Girlfriend) this.taskOwner).isTame()) {
            return false;
        }
        if (this.taskOwner instanceof Girlfriend && ((Girlfriend) this.taskOwner).isOrderedToSit()) {
            return false;
        }
        if (this.targetChance > 0 && this.taskOwner.getRandom().nextInt(100) > this.targetChance) {
            return false;
        }
        List<? extends LivingEntity> var5 = this.taskOwner.level.getEntitiesOfClass(
                this.targetClass,
                this.taskOwner.getBoundingBox().inflate(this.targetDistance, 4.0D, this.targetDistance),
                this.targetEntitySelector);
        Collections.sort(var5, this.theNearestAttackableTargetSorter);
        Iterator<? extends LivingEntity> var2 = var5.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (isSuitableTarget(var4, false)) {
                this.targetEntity = var4;
                return true;
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
