package com.astryxion.chaospersists.entity;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityLavaLovingItem extends ItemEntity {
    private boolean forceFireImmune = true;

    public EntityLavaLovingItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
        super(par1World, par2, par4, par6, par8ItemStack);
        this.noFire();
    }

    public void noFire() {
        this.forceFireImmune = true;
        this.invulnerableTime = 300;
    }

    public void yesFire() {
        this.forceFireImmune = false;
        this.invulnerableTime = 0;
    }

    @Override
    public boolean fireImmune() {
        return this.forceFireImmune;
    }

    @Override
    public void lavaHurt() {
        if (!this.forceFireImmune) {
            this.hurt(DamageSource.LAVA, 4.0F);
        }
    }

    @Override
    public void setSecondsOnFire(int seconds) {
        if (!this.forceFireImmune) {
            super.setSecondsOnFire(seconds);
        }
    }
}
