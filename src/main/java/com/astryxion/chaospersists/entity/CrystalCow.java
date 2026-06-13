package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CrystalCow extends RedCow {
    public CrystalCow(EntityType<? extends CrystalCow> type, World world) {
        super(type, world);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.spawnAtLocation(new ItemStack(ChaosPersists.MyCrystalApple));
    }

    @Override
    public CowEntity getBreedOffspring(ServerWorld level, AgeableEntity entityageable) {
        return (CrystalCow) this.getType().create(level);
    }
}
