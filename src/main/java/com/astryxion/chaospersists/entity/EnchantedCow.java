package com.astryxion.chaospersists.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EnchantedCow extends RedCow {
    public EnchantedCow(EntityType<? extends EnchantedCow> type, World world) {
        super(type, world);
    }

    private void dropEnchantedGoldenApple() {
        ItemEntity var3 = new ItemEntity(this.level, this.getX(), this.getY() + 1.0, this.getZ(), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
        this.level.addFreshEntity(var3);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = this.random.nextInt(4) + this.random.nextInt(1 + looting);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(new ItemStack(Items.APPLE));
        }
        this.spawnAtLocation(new ItemStack(Items.GOLDEN_APPLE, 2));
        this.dropEnchantedGoldenApple();
        super.dropCustomDeathLoot(source, looting, recentlyHit);
    }

    @Override
    public CowEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return (EnchantedCow) this.getType().create(level);
    }
}
