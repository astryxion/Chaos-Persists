package com.astryxion.chaospersists.util;

import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ExperienceBottleItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.item.UseAnim;

/**
 * Owner right-click on tamed pets: usable items (bows, food-use, throwables) fire instead of
 * toggling sit. Stick or sneak-right-click still orders them.
 */
public final class PetOrderInteract {
    private PetOrderInteract() {}

    public static boolean shouldPassItemUse(ItemStack stack, @Nullable Player player) {
        if (isExplicitOrderClick(stack, player)) {
            return false;
        }
        return hasPlayerUse(stack);
    }

    public static boolean isExplicitOrderClick(ItemStack stack, @Nullable Player player) {
        if (player != null && player.isShiftKeyDown()) {
            return true;
        }
        return !stack.isEmpty() && stack.is(Items.STICK);
    }

    public static boolean hasPlayerUse(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.getUseDuration() > 0 || stack.getUseAnimation() != UseAnim.NONE) {
            return true;
        }
        Item item = stack.getItem();
        return item instanceof ProjectileWeaponItem
                || item instanceof FishingRodItem
                || item instanceof BucketItem
                || item instanceof SnowballItem
                || item instanceof EggItem
                || item instanceof EnderpearlItem
                || item instanceof ExperienceBottleItem
                || item instanceof ThrowablePotionItem;
    }

    /** Toggle sit/follow and show the Alex's Mobs-style action bar. */
    public static void toggleSit(Player player, TamableAnimal pet) {
        setSit(player, pet, !pet.isOrderedToSit());
    }

    public static void setSit(Player player, TamableAnimal pet, boolean sitting) {
        pet.setOrderedToSit(sitting);
        announceSitFollow(player, pet, sitting);
    }

    /**
     * Action bar: "{Name} is now sitting/following". Uses a nametag name when present.
     */
    public static void announceSitFollow(@Nullable Player player, Entity pet, boolean sitting) {
        if (player == null || pet.level().isClientSide) {
            return;
        }
        player.displayClientMessage(
                Component.translatable(
                        sitting
                                ? "message.chaospersists.pet.sitting"
                                : "message.chaospersists.pet.following",
                        pet.getName()),
                true);
    }
}
