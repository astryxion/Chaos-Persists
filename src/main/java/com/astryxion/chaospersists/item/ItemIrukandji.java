package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.DeadIrukandji;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemIrukandji
extends Item {
    public ItemIrukandji(int i) { super(new Item.Properties()); }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundCategory.PLAYERS,
                3.0F, 1.0F);
        if (!world.isClientSide) {
            DeadIrukandji e = new DeadIrukandji(world, (LivingEntity) player);
            e.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity((Entity) e);
        }
        player.swing(hand);
        return ActionResult.success(stack);
    }
}
