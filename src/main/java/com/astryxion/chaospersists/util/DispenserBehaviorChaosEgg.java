package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.item.ItemSpawnEgg;
import net.minecraft.util.Direction;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.DispenserBlock;

public final class DispenserBehaviorChaosEgg implements IDispenseItemBehavior {
    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        Direction facing = source.getBlockState().getValue(DispenserBlock.FACING);
        double d0 = source.x() + (double) facing.getStepX() * 2.0D;
        double d1 = (float) source.getPos().getY() + 0.2F;
        double d2 = source.z() + (double) facing.getStepZ() * 2.0D;
        Item it = stack.getItem();
        if (it instanceof ItemSpawnEgg) {
            ItemSpawnEgg ise = (ItemSpawnEgg) it;
            World level = source.getLevel();
            Entity entity = ItemSpawnEgg.spawn_something(ise.my_id, level, (int) d0, (int) d1, (int) d2);
            if (entity instanceof LivingEntity && stack.hasCustomHoverName()) {
                entity.setCustomName(stack.getHoverName());
            }
        }
        stack.shrink(1);
        return stack;
    }
}
