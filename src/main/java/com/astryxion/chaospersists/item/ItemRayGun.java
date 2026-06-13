package com.astryxion.chaospersists.item;
import net.minecraft.util.math.vector.Vector3d;

import net.minecraft.item.ItemGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemRayGun extends Item {

    public ItemRayGun(int i) { super(new Item.Properties().stacksTo(1).durability(300)); }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getItemInHand(hand);

        // Prevent use if nearly broken (same logic)
        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            return ActionResult.fail(stack);
        }

        // Play sound (same sound as original)
        SoundEvent sound = net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("minecraft", "entity.firework.launch"));
        if (sound != null) {
            world.playSound(
                    player,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    sound,
                    SoundCategory.PLAYERS,
                    3.5f,
                    0.5f
            );
        }

        if (!world.isClientSide) {
            LaserBall lb = new LaserBall(world, player);
            lb.setSpecial();
            Vector3d look = player.getLookAngle();
            double spawnDist = 0.65;
            double px = player.getX() + look.x * spawnDist;
            double py = player.getY() + player.getEyeHeight() + look.y * spawnDist;
            double pz = player.getZ() + look.z * spawnDist;
            lb.setPos(px, py, pz);
            double speed = 1.85;
            lb.setDeltaMovement(look.x * speed, lb.getDeltaMovement().y, lb.getDeltaMovement().z);
            lb.setDeltaMovement(lb.getDeltaMovement().x, look.y * speed, lb.getDeltaMovement().z);
            lb.setDeltaMovement(lb.getDeltaMovement().x, lb.getDeltaMovement().y, look.z * speed);
            world.addFreshEntity(lb);
        }

        // Swing animation
        player.swing(hand);

        // Strong recoil boost (same math)
        player.setDeltaMovement(player.getDeltaMovement().add(
                new Vector3d(
                        Math.cos(Math.toRadians(player.yRot - 90.0f)) * 1.5,
                        0.3,
                        Math.sin(Math.toRadians(player.yRot - 90.0f)) * 1.5
                )
        ));

        // Damage item
        stack.hurtAndBreak(1, player, (broken) -> broken.broadcastBreakEvent(hand));

        return ActionResult.success(stack);
    }

    public String getMaterialName() {
        return "Unknown";
    }
}
