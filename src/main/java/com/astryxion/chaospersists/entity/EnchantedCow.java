package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EnchantedCow extends RedCow {
    public EnchantedCow(EntityType<? extends EnchantedCow> type, Level level) {
        super(type, level);
    }

    private void dropEnchantedGoldenApple() {
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX(),
                        this.getY() + 1.0,
                        this.getZ(),
                        new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
        this.level().addFreshEntity(entityItem);
    }

    @Override
    public RedCow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return new EnchantedCow(ChaosPersists.ENTITY_TYPE_ENCHANTED_COW.get(), level);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        int var3 = this.getRandom().nextInt(4) + this.getRandom().nextInt(1 + looting);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(new ItemStack(Items.APPLE));
        }
        this.spawnAtLocation(new ItemStack(Items.GOLDEN_APPLE, 2));
        this.dropEnchantedGoldenApple();
        super.dropCustomDeathLoot(source, looting, recentlyHit);
    }
}
