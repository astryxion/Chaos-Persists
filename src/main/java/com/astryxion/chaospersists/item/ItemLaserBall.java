package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemLaserBall
extends Item {
    public ItemLaserBall(int i) { super(new Item.Properties()); }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        SoundEvent firework = net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("minecraft:entity.firework.launch"));
        if (firework != null) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    firework, SoundCategory.PLAYERS, 3.0F, 1.0F);
        }
        if (!world.isClientSide) {
            LaserBall e = new LaserBall(world, (LivingEntity) player);
            e.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity((Entity) e);
        }
        player.swing(hand);
        return ActionResult.success(stack);
    }
}
