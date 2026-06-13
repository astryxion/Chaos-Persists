package com.astryxion.chaospersists.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RedCow extends CowEntity {
    public RedCow(EntityType<? extends RedCow> type, World world) {
        super(type, world);
        this.setPersistenceRequired();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int appleCount = 1 + this.random.nextInt(2 + looting);
        for (int i = 0; i < appleCount; i++) {
            this.spawnAtLocation(new ItemStack(Items.APPLE));
        }
        this.spawnAtLocation(new ItemStack(Items.LEATHER, 1 + this.random.nextInt(1 + looting)));
        this.spawnAtLocation(new ItemStack(Items.BEEF, 1 + this.random.nextInt(2 + looting)));
    }

    @Override
    public CowEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return (RedCow) this.getType().create(level);
    }

    @Override
    protected void customServerAiStep() {
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        super.customServerAiStep();
    }
}
