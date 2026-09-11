package com.astryxion.chaospersists.compat.guardvillagers;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

/**
 * Same rules Girlfriend / Boyfriend use when they wear Chaos armor:
 * {@code hurt} caps every hit at 10, then vanilla armor applies, and they are
 * fire-immune. Guard Villagers never run that {@code hurt} override, and their
 * GUI inventory does not copy pieces onto {@code ARMOR} attributes.
 *
 * <p>Do not run the player OreSpawn invert here. Full Royal/Ultimate is defense
 * 34+, which would zero every hit and look like immunity.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class GuardVillagersArmorCompat {

    public static final String GUARDVILLAGERS_MODID = "guardvillagers";

    /** Same cap as {@code Girlfriend#hurt} / {@code Boyfriend#hurt}. */
    private static final float COMPANION_HIT_CAP = 10.0f;

    private static final UUID ARMOR_MODIFIER_ID =
            UUID.fromString("6e2c8d41-9b70-4f3a-a1d8-0c5e7b94a216");
    private static final UUID TOUGHNESS_MODIFIER_ID =
            UUID.fromString("b8a14e03-2d57-4c9f-8e61-1f0a3c7d5b90");

    private static boolean checked;
    private static boolean present;

    private GuardVillagersArmorCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(GUARDVILLAGERS_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isGuard(@Nullable LivingEntity living) {
        if (!isPresent() || living == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(living.getType());
        return id != null
                && GUARDVILLAGERS_MODID.equals(id.getNamespace())
                && "guard".equals(id.getPath());
    }

    public static boolean shouldApplyArmorParity(@Nullable LivingEntity living) {
        return isGuard(living) && hasChaosArmorEquipped(living);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event == null || event.getEntity() == null || event.getEntity().level().isClientSide()) {
            return;
        }
        if (!shouldApplyArmorParity(event.getEntity())) {
            return;
        }
        if (event.getSource() != null && event.getSource().is(DamageTypeTags.IS_FIRE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event == null || event.getEntity() == null || event.getEntity().level().isClientSide()) {
            return;
        }
        LivingEntity living = event.getEntity();
        if (!isGuard(living)) {
            return;
        }
        if (!hasChaosArmorEquipped(living)) {
            clearWornArmorAttributes(living);
            return;
        }
        syncWornArmorAttributes(living);
        float incoming = event.getAmount();
        if (incoming <= 0.0f) {
            return;
        }
        float toughness = 0.0f;
        AttributeInstance toughAttr = living.getAttribute(Attributes.ARMOR_TOUGHNESS);
        if (toughAttr != null) {
            toughness = (float) toughAttr.getValue();
        }
        float companionMax =
                CombatRules.getDamageAfterAbsorb(
                        COMPANION_HIT_CAP, (float) living.getArmorValue(), toughness);
        event.setAmount(Math.min(incoming, companionMax));
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();
        if (living == null || living.level().isClientSide() || living.tickCount % 20 != 0) {
            return;
        }
        if (!isGuard(living)) {
            return;
        }
        if (hasChaosArmorEquipped(living)) {
            syncWornArmorAttributes(living);
        } else {
            clearWornArmorAttributes(living);
        }
    }

    private static boolean hasChaosArmorEquipped(LivingEntity living) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (!slot.isArmor()) {
                continue;
            }
            ItemStack stack = living.getItemBySlot(slot);
            if (!stack.isEmpty() && stack.getItem() instanceof ItemChaosArmor) {
                return true;
            }
        }
        return false;
    }

    /**
     * Guard GUI writes the custom inventory only. Add the missing {@code ARMOR} /
     * {@code ARMOR_TOUGHNESS} so {@code getArmorValue()} matches the pieces shown.
     */
    private static void syncWornArmorAttributes(LivingEntity living) {
        int desiredArmor = 0;
        float desiredToughness = 0.0f;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (!slot.isArmor()) {
                continue;
            }
            ItemStack stack = living.getItemBySlot(slot);
            if (stack.getItem() instanceof ArmorItem armorItem) {
                desiredArmor += armorItem.getDefense();
                desiredToughness += armorItem.getToughness();
            }
        }
        replaceTransient(living, Attributes.ARMOR, ARMOR_MODIFIER_ID, desiredArmor);
        replaceTransient(living, Attributes.ARMOR_TOUGHNESS, TOUGHNESS_MODIFIER_ID, desiredToughness);
    }

    private static void clearWornArmorAttributes(LivingEntity living) {
        removeTransient(living.getAttribute(Attributes.ARMOR), ARMOR_MODIFIER_ID);
        removeTransient(living.getAttribute(Attributes.ARMOR_TOUGHNESS), TOUGHNESS_MODIFIER_ID);
    }

    private static void replaceTransient(
            LivingEntity living,
            net.minecraft.world.entity.ai.attributes.Attribute attribute,
            UUID id,
            double desiredTotal) {
        AttributeInstance instance = living.getAttribute(attribute);
        if (instance == null) {
            return;
        }
        removeTransient(instance, id);
        double already = instance.getValue();
        double missing = desiredTotal - already;
        if (missing > 0.01) {
            instance.addTransientModifier(
                    new AttributeModifier(
                            id, "chaospersists_guard_armor", missing, AttributeModifier.Operation.ADDITION));
        }
    }

    private static void removeTransient(@Nullable AttributeInstance instance, UUID id) {
        if (instance != null && instance.getModifier(id) != null) {
            instance.removeModifier(id);
        }
    }
}
