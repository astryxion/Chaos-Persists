package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class GoldCow extends RedCow {
    public GoldCow(EntityType<? extends GoldCow> type, Level level) {
        super(type, level);
    }

    @Override
    public RedCow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return new GoldCow(ChaosPersists.ENTITY_TYPE_GOLD_COW.get(), level);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        int var3 = this.getRandom().nextInt(3) + this.getRandom().nextInt(1 + looting);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(new ItemStack(Items.APPLE));
        }
        this.spawnAtLocation(new ItemStack(Items.GOLDEN_APPLE));
        super.dropCustomDeathLoot(source, looting, recentlyHit);
    }
}
