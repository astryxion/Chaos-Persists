package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.Acid;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class MyDispenserBehaviorAcid extends ChaosProjectileDispenser {
    @Override
    protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
        return new Acid(
                ChaosPersists.ENTITY_TYPE_ACID.get(),
                position.x(),
                position.y(),
                position.z(),
                level);
    }
}
