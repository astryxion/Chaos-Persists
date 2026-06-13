package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
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

public class SkateBow extends Item {

    public SkateBow(int par1) {
        super(new Item.Properties().stacksTo(1).durability(300).tab(ItemGroup.TAB_COMBAT));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return ActionResult.success(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {

        if (!(entity instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) entity;

        boolean creative = player.isCreative();

        if (!creative && countArrows(player) <= 0) {
            return;
        }

        int charge = this.getUseDuration(stack) - timeLeft;
        float pull = charge / 20.0F;
        pull = (pull * pull + pull * 2.0F) / 3.0F;
        if (pull < 0.1F) return;
        if (pull > 1.0F) pull = 1.0F;
        float arrowSpeed = pull * 3.0F;

        IrukandjiArrow arrow = new IrukandjiArrow(world, player, arrowSpeed);
        arrow.pickup = AbstractArrowEntity.PickupStatus.ALLOWED;

        if (world.random.nextInt(20) == 1) {
            arrow.setCritArrow(true);
        }

        int punchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
        if (punchLevel > 0) {
            arrow.setKnockback(punchLevel);
        }

        if (EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(50), stack) > 0) {
            arrow.setSecondsOnFire(100);
        }

        stack.hurtAndBreak(1, player, p -> {});

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundCategory.PLAYERS,
                1.0F,
                1.0F / (world.random.nextFloat() * 0.4F + 1.2F) + 0.5F
        );

        if (!creative) {
            consumeOneArrow(player);
        }

        if (!world.isClientSide) {
            world.addFreshEntity(arrow);
        }
    }

    @Override
    public int getEnchantmentValue() {
        return 50;
    }

    private int countArrows(PlayerEntity player) {
        int count = 0;
        for (int i = 0; i < player.inventory.getContainerSize(); i++) {
            ItemStack s = player.inventory.getItem(i);
            if (!s.isEmpty() && s.getItem() == ChaosPersists.MyIrukandjiArrow) {
                count += s.getCount();
            }
        }
        return count;
    }

    private void consumeOneArrow(PlayerEntity player) {
        for (int i = 0; i < player.inventory.getContainerSize(); i++) {
            ItemStack s = player.inventory.getItem(i);
            if (!s.isEmpty() && s.getItem() == ChaosPersists.MyIrukandjiArrow) {
                s.shrink(1);
                break;
            }
        }
    }
}
