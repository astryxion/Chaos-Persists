/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.MyEntityAIJealousy
 *  com.astryxion.chaospersists.MyEntityAINearestAttackableTarget
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.passive.TameableEntity
 */
package com.astryxion.chaospersists.util;
import net.minecraft.entity.ai.goal.Goal;
import java.util.EnumSet;
import net.minecraft.entity.ai.goal.Goal.Flag;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.vector.Vector3d;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.util.MyEntityAINearestAttackableTarget;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;

public class MyEntityAIJealousy
extends MyEntityAINearestAttackableTarget {
    private TameableEntity theTameable;

    public MyEntityAIJealousy(TameableEntity par1TameableEntity, Class par2Class, float par3, int par4, boolean par5) {
        super((MobEntity)par1TameableEntity, par2Class, par3, par4, par5);
        this.theTameable = par1TameableEntity;
    }

    public boolean canUse() {
        TameableEntity te = (TameableEntity)this.taskOwner;
        Girlfriend gf = null;
        Boyfriend bf = null;
        LivingEntity ep = null;
        if (te == null) {
            return false;
        }
        if (!te.isTame()) {
            return false;
        }
        if (te.isOrderedToSit()) {
            return false;
        }
        if (!super.canUse()) {
            return false;
        }
        MobEntity victim = (MobEntity) this.targetEntity;
        if (victim == null) {
            return false;
        }
        if (te instanceof Girlfriend ? victim instanceof Girlfriend && (gf = (Girlfriend)victim).isTame() : victim instanceof Boyfriend && (bf = (Boyfriend)victim).isTame()) {
            return false;
        }
        ep = te.getOwner();
        if (ep == null) {
            return false;
        }
        return true;
    }
}

