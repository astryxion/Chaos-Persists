package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.item.WaterBall;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class MyDispenserBehaviorWDCharge extends ChaosProjectileDispenser {

    @Override
    protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
        WaterBall ball = new WaterBall(level);
        ball.setPos(position.x(), position.y(), position.z());
        return ball;
    }
}
