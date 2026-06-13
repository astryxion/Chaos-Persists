package com.astryxion.chaospersists.util;

import java.util.Comparator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.CreeperEntity;

public class MyEntityAINearestAttackableTargetSorter implements Comparator<Entity> {
    private final Entity theEntity;
    final MyEntityAINearestAttackableTarget parent;

    public MyEntityAINearestAttackableTargetSorter(MyEntityAINearestAttackableTarget par1EntityAINearestAttackableTarget, MobEntity par2Entity) {
        this.parent = par1EntityAINearestAttackableTarget;
        this.theEntity = par2Entity;
    }

    public int compareDistanceSq(Entity par1Entity, Entity par2Entity) {
        double var3 = this.theEntity.distanceToSqr(par1Entity);
        if (par1Entity instanceof CreeperEntity) {
            var3 /= 2.0;
        }
        double var5 = this.theEntity.distanceToSqr(par2Entity);
        if (par2Entity instanceof CreeperEntity) {
            var5 /= 2.0;
        }
        return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
    }

    @Override
    public int compare(Entity par1Obj, Entity par2Obj) {
        return this.compareDistanceSq(par1Obj, par2Obj);
    }
}
