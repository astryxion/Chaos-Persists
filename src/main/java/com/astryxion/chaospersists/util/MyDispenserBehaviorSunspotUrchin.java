package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.SunspotUrchin;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class MyDispenserBehaviorSunspotUrchin extends ChaosProjectileDispenser {

    @Override
    protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
        return new SunspotUrchin(
                ChaosPersists.ENTITY_TYPE_SUNSPOT_URCHIN.get(),
                position.x(),
                position.y(),
                position.z(),
                level);
    }
}
