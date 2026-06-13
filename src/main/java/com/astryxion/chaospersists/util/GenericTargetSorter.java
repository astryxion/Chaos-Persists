package com.astryxion.chaospersists.util;

import java.util.Comparator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.CreeperEntity;

public class GenericTargetSorter implements Comparator<Entity> {
    private final Entity theEntity;

    public GenericTargetSorter(Entity par2Entity) {
        this.theEntity = par2Entity;
    }

    public int compareDistanceSq(Entity par1Entity, Entity par2Entity) {
        double weight;
        double var3 = this.theEntity.distanceToSqr(par1Entity);
        if (par1Entity instanceof CreeperEntity) {
            var3 /= 2.0;
        }
        if ((weight = par1Entity.getBbHeight() * par1Entity.getBbWidth()) > 1.0) {
            var3 /= weight;
        }
        double var5 = this.theEntity.distanceToSqr(par2Entity);
        if (par2Entity instanceof CreeperEntity) {
            var5 /= 2.0;
        }
        if ((weight = par2Entity.getBbHeight() * par2Entity.getBbWidth()) > 1.0) {
            var5 /= weight;
        }
        return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
    }

    @Override
    public int compare(Entity par1Obj, Entity par2Obj) {
        return this.compareDistanceSq(par1Obj, par2Obj);
    }
}
