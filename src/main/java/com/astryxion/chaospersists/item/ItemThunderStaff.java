package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemThunderStaff extends Item {

    private int ticker = 50;

    public ItemThunderStaff(int i) {
        super(new Properties().stacksTo(1).durability(50));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            return InteractionResultHolder.fail(stack);
        }

        if (!level.isClientSide) {
            ThunderBolt lb =
                    new ThunderBolt(ChaosPersists.ENTITY_TYPE_THUNDER_BOLT.get(), player, level);
            // Match OreSpawn 1.7.10 spawn offset (ItemThunderStaff.onItemRightClick).
            double xzoff = 1.0;
            double yoff = 1.55;
            float yawHead = player.getYHeadRot();
            double px =
                    player.getX()
                            - xzoff * Math.sin(Math.toRadians(yawHead + 45.0f));
            double py = player.getY() + yoff;
            double pz =
                    player.getZ()
                            + xzoff * Math.cos(Math.toRadians(yawHead + 45.0f));
            lb.setPos(px, py, pz);
            lb.setYRot(player.getYRot());
            lb.setXRot(player.getXRot());
            // 1.7.10 EntityThrowable.setThrowableHeading: look direction only, then motion *= 3.
            // Do not use shootFromRotation — it adds shooter velocity, so staff recoil (and
            // flight) gets tripled into later shots and they drift backward when spammed.
            float xRot = player.getXRot();
            float yRot = player.getYRot();
            float lookX = -Mth.sin(yRot * Mth.DEG_TO_RAD) * Mth.cos(xRot * Mth.DEG_TO_RAD);
            float lookY = -Mth.sin(xRot * Mth.DEG_TO_RAD);
            float lookZ = Mth.cos(yRot * Mth.DEG_TO_RAD) * Mth.cos(xRot * Mth.DEG_TO_RAD);
            lb.shoot(lookX, lookY, lookZ, 1.5F, 1.0F);
            lb.setDeltaMovement(lb.getDeltaMovement().scale(3.0));
            level.addFreshEntity(lb);
        }

        player.swing(hand);

        player.push(
                Mth.cos((player.getYRot() - 90.0f) * Mth.DEG_TO_RAD) * 0.5,
                0.15,
                Mth.sin((player.getYRot() - 90.0f) * Mth.DEG_TO_RAD) * 0.5);

        stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(hand));

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (level.isRaining() && level.isThundering()) {
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
