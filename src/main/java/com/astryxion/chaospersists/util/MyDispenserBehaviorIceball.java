package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.item.IceBall;
import net.minecraft.util.Direction;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.DispenserBlock;

public final class MyDispenserBehaviorIceball implements IDispenseItemBehavior {
    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        World level = source.getLevel();
        IPosition position = DispenserBlock.getDispensePosition(source);
        Direction facing = source.getBlockState().getValue(DispenserBlock.FACING);
        IceBall projectile = new IceBall(level, position.x(), position.y(), position.z());
        projectile.shoot(facing.getStepX(), (float) facing.getStepY() + 0.1F, facing.getStepZ(), 1.1F, 6.0F);
        projectile.setIceMaker(1);
        level.addFreshEntity(projectile);
        stack.shrink(1);
        return stack;
    }
}
