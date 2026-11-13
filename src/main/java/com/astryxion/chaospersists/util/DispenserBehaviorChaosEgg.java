package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.item.ItemSpawnEgg;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

public final class DispenserBehaviorChaosEgg extends DefaultDispenseItemBehavior {

    @Override
    protected ItemStack execute(BlockSource source, ItemStack stack) {
        Direction facing = source.state().getValue(DispenserBlock.FACING);
        double d0 = source.center().x() + (double) facing.getStepX() * 2.0;
        double d1 = source.center().y() + 0.2;
        double d2 = source.center().z() + (double) facing.getStepZ() * 2.0;
        Item it = stack.getItem();
        if (it instanceof ItemSpawnEgg ise) {
            Entity entity =
                    ItemSpawnEgg.spawn_something(ise.my_id, source.level(), (int) d0, (int) d1, (int) d2);
            if (entity instanceof LivingEntity living && stack.has(net.minecraft.core.component.DataComponents.CUSTOM_NAME)) {
                living.setCustomName(stack.getHoverName());
            }
        }
        stack.shrink(1);
        return stack;
    }
}
