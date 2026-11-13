package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.item.IrukandjiArrow;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

/**
 * Irukandji arrows are not throwables; vanilla projectile dispenser behavior does not fire them.
 */
public final class MyDispenserBehaviorArrow extends DefaultDispenseItemBehavior {

    @Override
    protected ItemStack execute(BlockSource source, ItemStack stack) {
        Level level = source.level();
        Position position = DispenserBlock.getDispensePosition(source);
        Direction facing = source.state().getValue(DispenserBlock.FACING);

        IrukandjiArrow arrow = new IrukandjiArrow(level, position.x(), position.y(), position.z());
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        arrow.shoot(
                (double) facing.getStepX(),
                (double) ((float) facing.getStepY() + 0.1f),
                (double) facing.getStepZ(),
                1.1f,
                6.0f);

        level.addFreshEntity(arrow);
        stack.shrink(1);
        return stack;
    }
}
