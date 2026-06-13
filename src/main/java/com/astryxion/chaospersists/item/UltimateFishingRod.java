package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class UltimateFishingRod extends FishingRodItem {

    public UltimateFishingRod(int par1) {
        super(new net.minecraft.item.Item.Properties().stacksTo(1).durability(64).tab(ItemGroup.TAB_TOOLS));
    }

    @Override
    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        par1ItemStack.enchant(Enchantments.UNBREAKING, 2);
    }

    @Override
    public void onUseTick(World world, LivingEntity player, ItemStack stack, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack);
        if (lvl <= 0) {
            stack.enchant(Enchantments.UNBREAKING, 2);
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);

        UltimateFishHook hook = UltimateFishHook.getHookForPlayer(player);
        if (hook != null) {
            int dmg = hook.handleHookRetraction();
            stack.hurtAndBreak(dmg, player, (e) -> e.broadcastBreakEvent(hand));
            player.swing(hand);
        } else {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT,
                    player.getSoundSource(), 0.5f, 0.4f / (player.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClientSide) {
                world.addFreshEntity(new UltimateFishHook(world, player));
            }
            player.swing(hand);
        }
        return ActionResult.success(stack);
    }
}

