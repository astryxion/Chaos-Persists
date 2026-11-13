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
        applyEnchantments(stack, level);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.INFINITY, stack) <= 0) {
            applyEnchantments(stack, level);
        }
    }

    private void applyEnchantments(ItemStack stack, Level level) {
        stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.POWER), 5);
        stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FLAME), 3);
        stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.PUNCH), 2);
        stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.INFINITY), 1);
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

            int punchLevel = com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.PUNCH, stack);
            if (punchLevel > 0) {
                arrow.setKnockbackStrength(punchLevel);
            }
            if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.FLAME, stack) > 0) {
                arrow.igniteForSeconds(100);
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
        stack.hurtAndBreak(1, player, net.minecraft.world.entity.LivingEntity.getSlotForHand(player.getUsedItemHand()));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 9000;
    }

    @Override
    public int getEnchantmentValue() {
        return 50;
    }
}
