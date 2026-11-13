package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ItemRayGun extends Item {
    public ItemRayGun(int i) {
        super(new Item.Properties().stacksTo(1).durability(50));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            return InteractionResultHolder.fail(stack);
        }
        world.playSound(
                player,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.FIREWORK_ROCKET_LAUNCH,
                SoundSource.PLAYERS,
                3.5f,
                0.5f);
        if (!world.isClientSide) {
            LaserBall lb = new LaserBall(ChaosPersists.ENTITY_TYPE_LASER_BALL.get(), player, world);
            lb.setSpecial();
            Vec3 look = player.getLookAngle();
            double spawnDist = 0.65;
            double px = player.getX() + look.x * spawnDist;
            double py = player.getEyeY() + look.y * spawnDist;
            double pz = player.getZ() + look.z * spawnDist;
            lb.moveTo(px, py, pz, player.getYRot(), player.getXRot());
            double speed = 1.85;
            lb.setDeltaMovement(look.x * speed, look.y * speed, look.z * speed);
            world.addFreshEntity(lb);
        }
        player.swing(hand);
        player.push(
                Math.cos(Math.toRadians(player.getYRot() - 90.0f)) * 1.5,
                0.3,
                Math.sin(Math.toRadians(player.getYRot() - 90.0f)) * 1.5);
        stack.hurtAndBreak(1, player, net.minecraft.world.entity.LivingEntity.getSlotForHand(hand));
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide);
    }

    public String getMaterialName() {
        return "Unknown";
    }
}
