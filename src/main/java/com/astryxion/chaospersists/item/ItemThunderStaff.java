package com.astryxion.chaospersists.item;
import net.minecraft.util.math.vector.Vector3d;

import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemThunderStaff extends Item {

    private int ticker = 50;

    public ItemThunderStaff(int i) { super(new Item.Properties().stacksTo(1).durability(300)); }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getItemInHand(hand);

        // Prevent use if almost broken (same logic)
        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            return ActionResult.fail(stack);
        }

        if (!world.isClientSide) {
            ThunderBolt lb = new ThunderBolt(world, player);
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

        // Swing animation (correct hand)
        player.swing(hand);

        // PlayerEntity knockback boost (same math)
        player.setDeltaMovement(player.getDeltaMovement().add(
                Math.cos(Math.toRadians(player.yRot - 90.0f)) * 0.5,
                0.15,
                Math.sin(Math.toRadians(player.yRot - 90.0f)) * 0.5
        ));

        // Damage item
        stack.hurtAndBreak(1, player, (broken) -> broken.broadcastBreakEvent(hand));

        return ActionResult.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

        if (world.isRaining() && world.isThundering()) {

            if (this.ticker > 0) {
                --this.ticker;
            }

            if (this.ticker <= 0 && stack.getDamageValue() > 0) {
                stack.setDamageValue(stack.getDamageValue() - 1);
                this.ticker = 50;
            }
        }
    }

    public String getMaterialName() {
        return "Unknown";
    }
}
