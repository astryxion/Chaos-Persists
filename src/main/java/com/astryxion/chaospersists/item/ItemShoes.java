package com.astryxion.chaospersists.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemShoes extends Item {
    private int my_id = 0;

    public ItemShoes(int i, int j) {
        super(new Item.Properties().tab(ItemGroup.TAB_MISC));
        this.my_id = j;
    }

    public ItemShoes(int type) {
        this(0, type);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW,
                SoundCategory.PLAYERS, 0.5f, 0.4f / (world.random.nextFloat() * 0.4f + 0.8f));
        if (!world.isClientSide) {
            Shoes entity = new Shoes(world, (LivingEntity) player, this.my_id);
            entity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(entity);
        }
        player.swing(hand);
        return ActionResult.success(stack);
    }
}
