package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
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

public class SkateBow extends Item {

    public SkateBow(int par1) {
        super(new Item.Properties().stacksTo(1).durability(300));
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) {
            return;
        }

        boolean creative = player.getAbilities().instabuild;

        if (!creative && countArrows(player) <= 0) {
            return;
        }

        int charge = this.getUseDuration(stack, entity) - timeLeft;
        float pull = charge / 20.0F;
        pull = (pull * pull + pull * 2.0F) / 3.0F;
        if (pull < 0.1F) {
            return;
        }
        if (pull > 1.0F) {
            pull = 1.0F;
        }
        float arrowSpeed = pull * 3.0F;

        IrukandjiArrow arrow = new IrukandjiArrow(level, player, arrowSpeed);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;

        if (level.getRandom().nextInt(20) == 1) {
            arrow.setCritArrow(true);
        }

        int punchLevel = com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.PUNCH, stack);
        if (punchLevel > 0) {
            arrow.setKnockbackStrength(punchLevel);
        }

        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.FLAME, stack) > 0) {
            arrow.setSharedFlagOnFire(true);
        }

        stack.hurtAndBreak(1, player, net.minecraft.world.entity.LivingEntity.getSlotForHand(player.getUsedItemHand()));

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);

        if (!creative) {
            consumeOneArrow(player);
        }

        if (!level.isClientSide) {
            level.addFreshEntity(arrow);
        }
    }

    @Override
    public int getEnchantmentValue() {
        return 50;
    }

    private static Item irukandjiArrowItem() {
        return (Item) (Object) ChaosPersists.MyIrukandjiArrow;
    }

    private int countArrows(Player player) {
        int count = 0;
        Item irukandjiArrow = irukandjiArrowItem();
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack s = player.getInventory().getItem(i);
            if (!s.isEmpty() && s.getItem() == irukandjiArrow) {
                count += s.getCount();
            }
        }
        return count;
    }

    private void consumeOneArrow(Player player) {
        Item irukandjiArrow = irukandjiArrowItem();
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack s = player.getInventory().getItem(i);
            if (!s.isEmpty() && s.getItem() == irukandjiArrow) {
                s.shrink(1);
                break;
            }
        }
    }
}
