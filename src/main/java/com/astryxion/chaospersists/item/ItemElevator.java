package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Places a hoverboard entity. 1.7.10 used {@code EntityList.createEntityByName("Hoverboard", world)};
 * we construct {@link Elevator} directly so spawn never depends on registry lookup succeeding.
 */
public class ItemElevator extends Item {

    public ItemElevator(int par1) {
        super(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_TRANSPORTATION));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.FAIL;
        }
        ItemStack stack = context.getItemInHand();
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        BlockPos pos = context.getClickedPos();
        Elevator elevator = new Elevator(world);
        double x = (double) pos.getX() + 0.5D;
        double y = (double) pos.getY() + 1.2D;
        double z = (double) pos.getZ() + 0.5D;
        elevator.moveTo(x, y, z, world.random.nextFloat() * 360.0f, 0.0f);
        world.addFreshEntity(elevator);
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return ActionResultType.SUCCESS;
    }
}
