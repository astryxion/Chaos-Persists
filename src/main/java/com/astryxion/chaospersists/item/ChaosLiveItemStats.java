package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ArmorStats;
import com.astryxion.chaospersists.util.WeaponStats;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Config weapon damage and armor points apply as soon as Done is pressed. SwordItem/ArmorItem bake
 * modifiers at construct time, so live values are swapped on the attribute event.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ChaosLiveItemStats {
    private static final UUID ATTACK_DAMAGE_UUID =
            UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private static final UUID BOOTS_UUID = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
    private static final UUID LEGS_UUID = UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D");
    private static final UUID CHEST_UUID = UUID.fromString("9F3D146D-049D-412F-B78-BFC0FD80BA5B");
    private static final UUID HELM_UUID = UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150");

    private ChaosLiveItemStats() {}

    @SubscribeEvent
    public static void onItemAttributes(ItemAttributeModifierEvent event) {
        Item item = event.getItemStack().getItem();
        EquipmentSlot slot = event.getSlotType();
        if (item instanceof SwordItem && slot == EquipmentSlot.MAINHAND) {
            WeaponStats stats = weaponStats(item);
            if (stats == null) {
                return;
            }
            double amount = stats.damage + ChaosWeaponDamage.TIER_SWORD_MODIFIER;
            event.removeAttribute(Attributes.ATTACK_DAMAGE);
            event.addModifier(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                            ATTACK_DAMAGE_UUID,
                            "Weapon modifier",
                            amount,
                            AttributeModifier.Operation.ADDITION));
            return;
        }
        if (item instanceof ItemChaosArmor armor && slot == armor.getEquipmentSlot()) {
            ArmorStats stats = armor.liveArmorStats();
            if (stats == null) {
                return;
            }
            UUID uuid = armorUuid(slot);
            if (uuid == null) {
                return;
            }
            int defense =
                    switch (armor.get_armor_type()) {
                        case 0 -> stats.head_protection;
                        case 1 -> stats.chest_protection;
                        case 2 -> stats.leg_protection;
                        default -> stats.boot_protection;
                    };
            event.removeAttribute(Attributes.ARMOR);
            event.addModifier(
                    Attributes.ARMOR,
                    new AttributeModifier(
                            uuid, "Armor modifier", defense, AttributeModifier.Operation.ADDITION));
        }
    }

    @Nullable
    private static UUID armorUuid(EquipmentSlot slot) {
        return switch (slot) {
            case FEET -> BOOTS_UUID;
            case LEGS -> LEGS_UUID;
            case CHEST -> CHEST_UUID;
            case HEAD -> HELM_UUID;
            default -> null;
        };
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
