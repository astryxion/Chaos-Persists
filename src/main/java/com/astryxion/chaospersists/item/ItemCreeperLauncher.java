package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemCreeperLauncher extends Item {

    public ItemCreeperLauncher(int i) { super(new Item.Properties()); }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getItemInHand(hand);

        // Play launch sound
        world.playSound(null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.FIREWORK_ROCKET_LAUNCH,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f);

        if (!world.isClientSide) {

            CreeperEntity creeper = new CreeperEntity(net.minecraft.entity.EntityType.CREEPER, world);

            // Spawn slightly in front of player
            creeper.setPos(
                    player.getX(),
                    player.getY() + player.getEyeHeight(),
                    player.getZ()
            );

            // Make it shoot forward like a projectile
            creeper.setDeltaMovement(-Math.sin(Math.toRadians(player.yRot)) * Math.cos(Math.toRadians(player.xRot)) * 1.5, creeper.getDeltaMovement().y, creeper.getDeltaMovement().z);
            creeper.setDeltaMovement(creeper.getDeltaMovement().x, creeper.getDeltaMovement().y,  Math.cos(Math.toRadians(player.yRot)) * Math.cos(Math.toRadians(player.xRot)) * 1.5);
            creeper.setDeltaMovement(creeper.getDeltaMovement().x, -Math.sin(Math.toRadians(player.xRot)) * 1.5, creeper.getDeltaMovement().z);

            world.addFreshEntity(creeper);
        }

        if (!player.isCreative()) {
            stack.shrink(1);
        }

        return ActionResult.success(stack);
    }
}
