package com.astryxion.chaospersists.compat.hundred_years_war;

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
 * Hundred Years Warfare soldiers, workers, bandits, and desert raiders wear armor through
 * {@code BaseCombatEntity} equipment slots and never run Girlfriend / Boyfriend {@code hurt}:
 * cap every hit at 10, then armor. OreSpawn sets otherwise soak like netherite on unit HP.
 *
 * <p>Same Guard Villagers companion pass: copy missing {@code ARMOR} points, cap the hit,
 * and cancel fire while Chaos armor is worn. Do not run the player OreSpawn invert here.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class HundredYearsWarArmorCompat {

    public static final String HUNDRED_YEARS_WAR_MODID = "hundred_years_war";

    /** Same cap as {@code Girlfriend#hurt} / {@code Boyfriend#hurt}. */
    private static final float COMPANION_HIT_CAP = 10.0f;

    private static final UUID ARMOR_MODIFIER_ID =
            UUID.fromString("6f1a2c80-4d39-4b7e-9e15-8a0c3f5d72b1");
    private static final UUID TOUGHNESS_MODIFIER_ID =
            UUID.fromString("b4e8d017-5c26-48a9-a3f0-1d79e6c4b852");

    private static boolean checked;
    private static boolean present;

    private HundredYearsWarArmorCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(HUNDRED_YEARS_WAR_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isHywMob(@Nullable LivingEntity living) {
        if (!isPresent() || living == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(living.getType());
        return id != null && HUNDRED_YEARS_WAR_MODID.equals(id.getNamespace());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event == null || event.getEntity() == null || event.getEntity().level().isClientSide()) {
            return;
        }
        if (!isHywMob(event.getEntity()) || !hasChaosArmorEquipped(event.getEntity())) {
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
        if (!isHywMob(living)) {
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
        if (!isHywMob(living)) {
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
                            id, "chaospersists_hyw_armor", missing, AttributeModifier.Operation.ADDITION));
        }
    }

    private static void removeTransient(@Nullable AttributeInstance instance, UUID id) {
        if (instance != null && instance.getModifier(id) != null) {
            instance.removeModifier(id);
        }
    }
}
