package com.astryxion.chaospersists.item;

import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class ItemIceBall extends Item {
    public ItemIceBall(int i) {
        this(new Item.Properties().stacksTo(64));
    }

    public ItemIceBall(Item.Properties properties) {
        super(properties);
    }

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
            IceBall ice = new IceBall(world, (LivingEntity) player);
            ice.setIceMaker(1);
            ice.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(ice);
        }
        player.swing(hand);
        return ActionResult.success(stack);
    }
}
