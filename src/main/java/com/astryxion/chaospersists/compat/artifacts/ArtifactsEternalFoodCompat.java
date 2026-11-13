package com.astryxion.chaospersists.compat.artifacts;

import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * Artifacts Everlasting Beef / Eternal Steak return the same stack from {@code finishUsingItem}
 * and sit on a 15s cooldown. Chaos pets that eat food {@code shrink(1)} instead, which deletes
 * the unique item.
 *
 * <p>{@link PlayerInteractEvent.EntityInteract} only fires before {@code mobInteract}, so a
 * same-event "after" listener cannot see the shrink. Queue a server restore for after the
 * interact task finishes; if the stack was consumed, put it back and apply the Artifacts
 * cooldown.
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class ArtifactsEternalFoodCompat {

    public static final String ARTIFACTS_MODID = "artifacts";

    private static final String EVERLASTING_BEEF = "everlasting_beef";
    private static final String ETERNAL_STEAK = "eternal_steak";

    /** Artifacts {@code durationSeconds(..., 15)} with factor 20. */
    private static final int DEFAULT_COOLDOWN_TICKS = 15 * 20;

    private static boolean checked;
    private static boolean present;

    private ArtifactsEternalFoodCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(ARTIFACTS_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isEverlastingFood(@Nullable ItemStack stack) {
        if (!isPresent() || stack == null || stack.isEmpty()) {
            return false;
        }
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return id != null
                && ARTIFACTS_MODID.equals(id.getNamespace())
                && (EVERLASTING_BEEF.equals(id.getPath()) || ETERNAL_STEAK.equals(id.getPath()));
    }

    private static boolean isChaosEntity(@Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && ChaosPersists.MODID.equals(id.getNamespace());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        protectEverlastingFood(event, event.getTarget());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        protectEverlastingFood(event, event.getTarget());
    }

    private static void protectEverlastingFood(PlayerInteractEvent event, Entity target) {
        if (!isPresent() || event.getLevel().isClientSide) {
            return;
        }
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        if (player == null || !isEverlastingFood(stack) || !isChaosEntity(target)) {
            return;
        }
        if (player.getCooldowns().isOnCooldown(stack.getItem())) {
            if (event instanceof PlayerInteractEvent.EntityInteract interact) {
                interact.setCanceled(true);
                interact.setCancellationResult(InteractionResult.SUCCESS);
            } else if (event instanceof PlayerInteractEvent.EntityInteractSpecific interact) {
                interact.setCanceled(true);
                interact.setCancellationResult(InteractionResult.SUCCESS);
            }
            return;
        }
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }
        ItemStack saved = stack.copy();
        InteractionHand hand = event.getHand();
        serverLevel.getServer().execute(() -> restoreIfConsumed(player, hand, saved));
    }

    private static void restoreIfConsumed(Player player, InteractionHand hand, ItemStack saved) {
        if (player == null || player.level().isClientSide || saved == null || saved.isEmpty()) {
            return;
        }
        if (!player.isAlive()) {
            return;
        }
        ItemStack current = player.getItemInHand(hand);
        if (!current.isEmpty()
                && current.getItem() == saved.getItem()
                && current.getCount() >= saved.getCount()) {
            return;
        }
        player.setItemInHand(hand, saved);
        player.getCooldowns().addCooldown(saved.getItem(), DEFAULT_COOLDOWN_TICKS);
    }
}
