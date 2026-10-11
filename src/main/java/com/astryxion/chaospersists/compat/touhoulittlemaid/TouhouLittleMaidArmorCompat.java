package com.astryxion.chaospersists.compat.touhoulittlemaid;

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
 * Touhou Little Maid {@code EntityMaid} uses vanilla armor slots, but never runs Girlfriend /
 * Boyfriend {@code hurt}: cap every hit at 10, then armor. Without that, OreSpawn sets soak
 * like netherite on a 20 HP mob instead of a companion in Royal/Ultimate.
 *
 * <p>Same Guard Villagers companion pass: copy missing {@code ARMOR} points, cap the hit,
 * and cancel fire while Chaos armor is worn. Do not run the player OreSpawn invert here.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class TouhouLittleMaidArmorCompat {

    public static final String TOUHOU_LITTLE_MAID_MODID = "touhou_little_maid";

    /** Same cap as {@code Girlfriend#hurt} / {@code Boyfriend#hurt}. */
    private static final float COMPANION_HIT_CAP = 10.0f;

    private static final UUID ARMOR_MODIFIER_ID =
            UUID.fromString("a3d91c50-7e2b-4f18-9c64-2b0e8d5a17f4");
    private static final UUID TOUGHNESS_MODIFIER_ID =
            UUID.fromString("c1f07e84-5a39-4d2c-b816-9e4a1c70d5b2");

    private static boolean checked;
    private static boolean present;

    private TouhouLittleMaidArmorCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(TOUHOU_LITTLE_MAID_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isMaid(@Nullable LivingEntity living) {
        if (!isPresent() || living == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(living.getType());
        return id != null
                && TOUHOU_LITTLE_MAID_MODID.equals(id.getNamespace())
                && "maid".equals(id.getPath());
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event == null || event.getEntity() == null || event.getEntity().level().isClientSide()) {
            return;
        }
        if (!isMaid(event.getEntity()) || !hasChaosArmorEquipped(event.getEntity())) {
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
        if (!isMaid(living)) {
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
        if (!isMaid(living)) {
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
                            id, "chaospersists_maid_armor", missing, AttributeModifier.Operation.ADDITION));
        }
    }

    private static void removeTransient(@Nullable AttributeInstance instance, UUID id) {
        if (instance != null && instance.getModifier(id) != null) {
            instance.removeModifier(id);
        }
    }
}
