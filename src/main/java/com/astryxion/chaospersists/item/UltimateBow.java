package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.item.UltimateArrow;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class UltimateBow extends Item {

    public UltimateBow(int par1) {
        super(new Item.Properties().stacksTo(1).durability(1000).tab(ItemGroup.TAB_COMBAT));
    }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity player) {
        applyEnchantments(stack);
    }

    private void applyEnchantments(ItemStack stack) {
        if (EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(51), stack) > 0) {
            return;
        }
        stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(48), 5);
        stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(50), 3);
        stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(49), 2);
        stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(51), 1);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return ActionResult.success(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity entityLiving, int timeLeft) {
        if (!(entityLiving instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entityLiving;
        applyEnchantments(stack);

        if (!world.isClientSide) {
            UltimateArrow arrow = new UltimateArrow(world, player, 3.0f);
            if (world.random.nextInt(4) == 1) {
                arrow.setCritArrow(true);
            }

            int punchLevel = EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(49), stack);
            if (punchLevel > 0) {
                arrow.setKnockback(punchLevel);
            }
            if (EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(50), stack) > 0) {
                arrow.setSecondsOnFire(100);
            }

            arrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
            world.addFreshEntity(arrow);
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (world.random.nextFloat() * 0.4f + 1.2f) + 0.5f);
        stack.hurtAndBreak(1, player, (e) -> e.broadcastBreakEvent(player.getUsedItemHand()));
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
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
