package com.astryxion.chaospersists.item;

import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ArmorStats;
import com.astryxion.chaospersists.util.WeaponStats;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

/**
 * Config weapon damage and armor points apply as soon as Done is pressed. SwordItem/ArmorItem bake
 * modifiers at construct time, so live values are swapped on the attribute event.
 *
 * <p>Reuse the sword's existing base attack-damage modifier id when swapping config damage so
 * tooltips still include the player base +1 and Sharpness (same intent as 1.20.1 UUID reuse).
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class ChaosLiveItemStats {
    private ChaosLiveItemStats() {}

    @SubscribeEvent
    public static void onItemAttributes(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack == null || stack.isEmpty()) {
            return;
        }
        Item item = stack.getItem();
        if (item instanceof SwordItem) {
            WeaponStats stats = weaponStats(item);
            if (stats == null) {
                return;
            }
            double amount = stats.damage + ChaosWeaponDamage.TIER_SWORD_MODIFIER;
            ResourceLocation damageId = attackDamageModifierId(event);
            event.removeIf(entry -> entry.attribute().is(Attributes.ATTACK_DAMAGE)
                    && entry.slot() == EquipmentSlotGroup.MAINHAND);
            event.addModifier(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(damageId, amount, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND);
            return;
        }
        if (item instanceof ItemChaosArmor armor) {
            ArmorStats stats = armor.liveArmorStats();
            if (stats == null) {
                return;
            }
            EquipmentSlot slot = armor.getEquipmentSlot();
            int defense =
                    switch (armor.get_armor_type()) {
                        case 0 -> stats.head_protection;
                        case 1 -> stats.chest_protection;
                        case 2 -> stats.leg_protection;
                        default -> stats.boot_protection;
                    };
            event.removeIf(entry -> entry.attribute().is(Attributes.ARMOR)
                    && entry.slot().test(slot));
            event.addModifier(
                    Attributes.ARMOR,
                    new AttributeModifier(
                            net.minecraft.resources.ResourceLocation.withDefaultNamespace("armor." + slot.getName()),
                            defense, AttributeModifier.Operation.ADD_VALUE),
                    net.minecraft.world.entity.EquipmentSlotGroup.bySlot(slot));
        }
    }

    /** Prefer the id already on the sword's default attack-damage modifier. */
    private static ResourceLocation attackDamageModifierId(ItemAttributeModifierEvent event) {
        for (ItemAttributeModifiers.Entry entry : event.getDefaultModifiers().modifiers()) {
            if (entry.attribute().is(Attributes.ATTACK_DAMAGE)
                    && entry.slot() == EquipmentSlotGroup.MAINHAND) {
                return entry.modifier().id();
            }
        }
        for (ItemAttributeModifiers.Entry entry : event.getModifiers()) {
            if (entry.attribute().is(Attributes.ATTACK_DAMAGE)
                    && entry.slot() == EquipmentSlotGroup.MAINHAND) {
                return entry.modifier().id();
            }
        }
        return ResourceLocation.withDefaultNamespace("base_attack_damage");
    }

    @Nullable
    private static WeaponStats weaponStats(Item item) {
        if (item == ChaosPersists.MyUltimateSword) {
            return ChaosPersists.ultimate_stats;
        }
        if (item == ChaosPersists.MyNightmareSword) {
            return ChaosPersists.nightmare_stats;
        }
        if (item == ChaosPersists.MyBertha || item == ChaosPersists.MySlice) {
            return ChaosPersists.bertha_stats;
        }
        if (item == ChaosPersists.MyRoyal) {
            return ChaosPersists.royal_stats;
        }
        if (item == ChaosPersists.MyHammy) {
            return ChaosPersists.hammy_stats;
        }
        if (item == ChaosPersists.MyBattleAxe) {
            return ChaosPersists.battleaxe_stats;
        }
        if (item == ChaosPersists.MyChainsaw) {
            return ChaosPersists.chainsaw_stats;
        }
        if (item == ChaosPersists.MyQueenBattleAxe) {
            return ChaosPersists.queenbattleaxe_stats;
        }
        if (item == ChaosPersists.MyEmeraldSword) {
            return ChaosPersists.emerald_stats;
        }
        if (item == ChaosPersists.MyRubySword) {
            return ChaosPersists.ruby_stats;
        }
        if (item == ChaosPersists.MyAmethystSword) {
            return ChaosPersists.amethyst_stats;
        }
        return null;
    }
}
