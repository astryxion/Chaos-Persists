package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.item.Acid;
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

public class ItemAcid extends Item {

    public ItemAcid(int i) {
        this(new Item.Properties().stacksTo(64));
    }

    public ItemAcid(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundCategory.PLAYERS,
                0.5F,
                0.4F / (random.nextFloat() * 0.4F + 0.8F)
        );
        if (!world.isClientSide) {
            Acid acid = new Acid(world, (LivingEntity) player);
            acid.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(acid);
        }
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        player.swing(hand);
        return ActionResult.success(stack);
    }
}
