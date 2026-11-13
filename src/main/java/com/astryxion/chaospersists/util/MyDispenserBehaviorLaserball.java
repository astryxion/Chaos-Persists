package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.LaserBall;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class MyDispenserBehaviorLaserball extends ChaosProjectileDispenser {
    @Override
    protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
        return new LaserBall(
                ChaosPersists.ENTITY_TYPE_LASER_BALL.get(),
                position.x(),
                position.y(),
                position.z(),
                level);
    }
}
