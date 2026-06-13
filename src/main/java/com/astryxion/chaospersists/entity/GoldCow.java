package com.astryxion.chaospersists.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GoldCow extends RedCow {
    public GoldCow(EntityType<? extends GoldCow> type, World world) {
        super(type, world);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = this.random.nextInt(3) + this.random.nextInt(1 + looting);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(new ItemStack(Items.APPLE));
        }
        this.spawnAtLocation(new ItemStack(Items.GOLDEN_APPLE));
        super.dropCustomDeathLoot(source, looting, recentlyHit);
    }

    @Override
    public CowEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return (GoldCow) this.getType().create(level);
    }
}
