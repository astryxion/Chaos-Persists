package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.IceBall;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class MyDispenserBehaviorIceball extends ChaosProjectileDispenser {
    @Override
    protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
        IceBall ice =
                new IceBall(
                        ChaosPersists.ENTITY_TYPE_ICE_BALL.get(),
                        position.x(),
                        position.y(),
                        position.z(),
                        level);
        ice.setIceMaker(1);
        return ice;
    }
}
