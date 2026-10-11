package com.astryxion.chaospersists.compat.artifacts;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Artifacts Everlasting Beef / Eternal Steak return the same stack from {@code finishUsingItem}
 * and sit on a 15s cooldown. Chaos pets that eat any {@code isEdible} food {@code shrink(1)}
 * instead, which deletes the unique item.
 *
 * <p>If a Chaos entity ate one, put the stack back and apply the same default cooldown.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ArtifactsEternalFoodCompat {

    public static final String ARTIFACTS_MODID = "artifacts";

    private static final String EVERLASTING_BEEF = "everlasting_beef";
    private static final String ETERNAL_STEAK = "eternal_steak";

    /** Artifacts {@code durationSeconds(..., 15)} with factor 20. */
    private static final int DEFAULT_COOLDOWN_TICKS = 15 * 20;

    private static final Map<UUID, ItemStack> PENDING = new HashMap<>();

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
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
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
        beforePetEat(event, event.getTarget());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        beforePetEat(event, event.getTarget());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void afterEntityInteract(PlayerInteractEvent.EntityInteract event) {
        afterPetEat(event.getLevel(), event.getEntity(), event.getHand());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void afterEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        afterPetEat(event.getLevel(), event.getEntity(), event.getHand());
    }

    private static void beforePetEat(PlayerInteractEvent event, Entity target) {
        if (!isPresent() || event.getLevel().isClientSide) {
            return;
        }
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        if (player == null || !isEverlastingFood(stack) || !isChaosEntity(target)) {
            return;
        }
        if (player.getCooldowns().isOnCooldown(stack.getItem())) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
            return;
        }
        PENDING.put(player.getUUID(), stack.copy());
    }

    private static void afterPetEat(Level level, Player player, InteractionHand hand) {
        if (player == null || level.isClientSide) {
            return;
        }
        ItemStack saved = PENDING.remove(player.getUUID());
        if (saved == null || saved.isEmpty()) {
            return;
        }
        ItemStack current = player.getItemInHand(hand);
        if (!current.isEmpty() && current.getItem() == saved.getItem() && current.getCount() >= saved.getCount()) {
            return;
        }
        player.setItemInHand(hand, saved);
        player.getCooldowns().addCooldown(saved.getItem(), DEFAULT_COOLDOWN_TICKS);
    }
}
