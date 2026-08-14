package com.astryxion.chaospersists.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class UltimateBow extends Item {

    public UltimateBow(int par1) {
        super(new Item.Properties().stacksTo(1).durability(1000));
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        applyEnchantments(stack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) <= 0) {
            applyEnchantments(stack);
        }
    }

    private void applyEnchantments(ItemStack stack) {
        stack.enchant(Enchantments.POWER_ARROWS, 5);
        stack.enchant(Enchantments.FLAMING_ARROWS, 3);
        stack.enchant(Enchantments.PUNCH_ARROWS, 2);
        stack.enchant(Enchantments.INFINITY_ARROWS, 1);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (!(entityLiving instanceof Player player)) {
            return;
        }

        if (!level.isClientSide) {
            UltimateArrow arrow = new UltimateArrow(level, player, 3.0F);
            if (level.getRandom().nextInt(4) == 1) {
                arrow.setCritArrow(true);
            }

            int punchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
            if (punchLevel > 0) {
                arrow.setKnockbackStrength(punchLevel);
            }
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0) {
                arrow.setSecondsOnFire(100);
            }

            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            level.addFreshEntity(arrow);
        }

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 9000;
    }

    @Override
    public int getEnchantmentValue() {
        return 50;
    }
}
