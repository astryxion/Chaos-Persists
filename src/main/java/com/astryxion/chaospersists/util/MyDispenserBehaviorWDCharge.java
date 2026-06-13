package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.item.WaterBall;
import net.minecraft.util.Direction;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.DispenserBlock;

public final class MyDispenserBehaviorWDCharge implements IDispenseItemBehavior {
    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        World level = source.getLevel();
        IPosition position = DispenserBlock.getDispensePosition(source);
        Direction facing = source.getBlockState().getValue(DispenserBlock.FACING);
        WaterBall projectile = new WaterBall(level, position.x(), position.y(), position.z());
        projectile.shoot(facing.getStepX(), (float) facing.getStepY() + 0.1F, facing.getStepZ(), 1.1F, 6.0F);
        level.addFreshEntity(projectile);
        stack.shrink(1);
        return stack;
    }
}
