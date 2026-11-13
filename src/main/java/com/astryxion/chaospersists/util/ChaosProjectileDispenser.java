package com.astryxion.chaospersists.util;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

abstract class ChaosProjectileDispenser extends DefaultDispenseItemBehavior {
    protected abstract Projectile getProjectile(Level level, Position position, ItemStack stack);

    @Override
    protected ItemStack execute(BlockSource source, ItemStack stack) {
        Direction facing = source.state().getValue(DispenserBlock.FACING);
        Projectile projectile = getProjectile(source.level(), DispenserBlock.getDispensePosition(source), stack);
        projectile.shoot(facing.getStepX(), facing.getStepY() + 0.1F, facing.getStepZ(), 1.1F, 6.0F);
        if (source.level().addFreshEntity(projectile)) {
            stack.shrink(1);
        }
        return stack;
    }

    @Override
    protected void playSound(BlockSource source) {
        source.level().levelEvent(1002, source.pos(), 0);
    }
}
