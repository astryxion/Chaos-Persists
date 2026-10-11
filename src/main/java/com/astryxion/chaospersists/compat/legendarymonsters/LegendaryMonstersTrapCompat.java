package com.astryxion.chaospersists.compat.legendarymonsters;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Possessed Paladin dungeon traps: swinging axes deal {@code legendary_monsters:cut} (13)
 * and spike traps deal {@code legendary_monsters:impale} (8 / 12). Both are in
 * {@code minecraft:tags/damage_type/bypasses_armor.json}, so vanilla never runs armor.
 * High-tier Chaos Persists armor soaks those hits the same way Cataclysm bypasses do.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class LegendaryMonstersTrapCompat {

    private static final EquipmentSlot[] ARMOR_SLOTS = {
        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    private LegendaryMonstersTrapCompat() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!LegendaryMonstersBeamCompat.isPresent()
                || event.isCanceled()
                || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity victim = event.getEntity();
        DamageSource source = event.getSource();
        if (victim == null || victim.level() == null || victim.level().isClientSide || source == null) {
            return;
        }
        if (!isDungeonTrapDamage(source)) {
            return;
        }
        float resist = resistStrength(victim);
        if (resist <= 0.0f) {
            return;
        }
        float amount = event.getAmount();
        float armor = (float) victim.getArmorValue();
        float toughness = 0.0f;
        if (victim.getAttribute(Attributes.ARMOR_TOUGHNESS) != null) {
            toughness = (float) victim.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        }
        float afterArmor = getDamageAfterAbsorbUncapped(amount, armor, toughness);
        event.setAmount(amount * (1.0f - resist) + afterArmor * resist);
    }

    private static boolean isDungeonTrapDamage(DamageSource source) {
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .filter(id -> LegendaryMonstersBeamCompat.LEGENDARY_MONSTERS_MODID.equals(id.getNamespace()))
                .map(ResourceLocation::getPath)
                .filter(path -> "cut".equals(path) || "impale".equals(path))
                .isPresent();
    }

    private static float resistStrength(LivingEntity entity) {
        int count = 0;
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            if (isHighTierPiece(entity.getItemBySlot(slot))) {
                count++;
            }
        }
        return count / 4.0f;
    }

    private static boolean isHighTierPiece(ItemStack stack) {
        if (stack == null || stack.isEmpty() || !(stack.getItem() instanceof ItemChaosArmor chaos)) {
            return false;
        }
        int material = chaos.get_armor_material();
        if (material == 10 || material == 11 || material == 13) {
            return true;
        }
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return id != null
                && ChaosPersists.MODID.equals(id.getNamespace())
                && id.getPath().startsWith("ultimate_");
    }

    /** Vanilla CombatRules caps armor at 20; OreSpawn sets go past that. */
    private static float getDamageAfterAbsorbUncapped(float damage, float armor, float toughness) {
        float f = 2.0f + toughness / 4.0f;
        float reduction = Mth.clamp(armor - damage / f, armor * 0.2f, 20.0f);
        float afterVanillaCap = damage * (1.0f - reduction / 25.0f);
        float excess = Math.max(0.0f, armor - 20.0f);
        float extraSoak = Mth.clamp(excess / 40.0f, 0.0f, 0.85f);
        return afterVanillaCap * (1.0f - extraSoak);
    }
}
