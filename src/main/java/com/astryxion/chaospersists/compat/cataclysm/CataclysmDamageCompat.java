package com.astryxion.chaospersists.compat.cataclysm;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBosses;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Cataclysm often deals magic / armor-bypass hits plus a percent of max health (Ignis flame
 * strike is {@code damage + maxHealth * 0.01 * hpDamage} via {@code indirectMagic}). High-tier
 * Chaos Persists armor forces those hits back through armor and soaks the percent-health slice.
 * Chaos Persists bosses use the same OreSpawn defense curve on those hits so they are not
 * melted by percent-HP magic that skips {@code getArmorValue()}.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class CataclysmDamageCompat {

    /**
     * Ignis scaled melee stays under this. Anything above is treated as the baked-in percent of
     * max health (flame strike is ~6% HP; ultimate is up to 25%).
     */
    private static final float BOSS_WEAPON_DAMAGE_CAP = 40.0f;

    private CataclysmDamageCompat() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!CataclysmBlazingBrandCompat.isPresent()
                || event.isCanceled()
                || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity victim = event.getEntity();
        DamageSource source = event.getSource();
        if (victim == null || victim.level() == null || victim.level().isClientSide || source == null) {
            return;
        }
        if (!isCataclysmDamage(source)) {
            return;
        }
        if (ChaosBosses.isBoss(victim) && bypassesDefense(source)) {
            event.setAmount(applyBossDefense(victim, event.getAmount()));
            return;
        }
        float resist = CataclysmHighTierGear.resistStrength(victim);
        if (resist <= 0.0f) {
            return;
        }

        float amount = event.getAmount();
        float hpSlice = Math.min(amount, victim.getMaxHealth() * percentHealthSlice(source));
        amount = Math.max(0.0f, amount - hpSlice * resist);

        if (bypassesDefense(source) && amount > 0.0f) {
            float armor = (float) victim.getArmorValue();
            float toughness = 0.0f;
            if (victim.getAttribute(Attributes.ARMOR_TOUGHNESS) != null) {
                toughness = (float) victim.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
            }
            float afterArmor = getDamageAfterAbsorbUncapped(amount, armor, toughness);
            amount = amount * (1.0f - resist) + afterArmor * resist;
            if (source.is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
                int k = EnchantmentHelper.getDamageProtection(victim.getArmorSlots(), source);
                if (k > 0) {
                    float enchanted = amount * (1.0f - Mth.clamp(k, 0, 20) / 25.0f);
                    amount = amount * (1.0f - resist) + enchanted * resist;
                }
            }
        }

        event.setAmount(amount);
    }

    /**
     * Strip the percent-HP payload, then run 1.7.10 OreSpawn armor ({@code (25 - defense) / 25})
     * on the leftover weapon hit. Magic normally ignores {@code getArmorValue()}, which is why
     * Cornelia (custom defense on every hit) tanked Ignis better than the Emperor Scorpion.
     */
    private static float applyBossDefense(LivingEntity victim, float amount) {
        float weapon = Math.min(amount, BOSS_WEAPON_DAMAGE_CAP);
        int armor = Mth.clamp(victim.getArmorValue(), 0, 25);
        if (armor <= 0) {
            return weapon;
        }
        return Math.max(0.0f, weapon * (25.0f - (float) armor) / 25.0f);
    }

    private static boolean isCataclysmDamage(DamageSource source) {
        if (isCataclysmEntity(source.getEntity()) || isCataclysmEntity(source.getDirectEntity())) {
            return true;
        }
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(id -> CataclysmBlazingBrandCompat.CATACLYSM_MODID.equals(id.getNamespace()))
                .orElse(false);
    }

    private static boolean isCataclysmEntity(Entity entity) {
        if (entity == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && CataclysmBlazingBrandCompat.CATACLYSM_MODID.equals(id.getNamespace());
    }

    private static boolean bypassesDefense(DamageSource source) {
        return source.is(DamageTypeTags.BYPASSES_ARMOR)
                || source.is(DamageTypes.MAGIC)
                || source.is(DamageTypes.INDIRECT_MAGIC);
    }

    /**
     * Flame strike uses {@code maxHealth * 0.01 * 6}. Other Ignis moves use 0.03–0.25 of max
     * health baked into the hit. We strip a conservative slice, scaled by resist.
     */
    private static float percentHealthSlice(DamageSource source) {
        Entity direct = source.getDirectEntity();
        if (direct != null) {
            ResourceLocation id = EntityType.getKey(direct.getType());
            if (id != null && "flame_strike".equals(id.getPath())) {
                return 0.06f;
            }
        }
        return 0.10f;
    }

    /**
     * Vanilla {@link net.minecraft.world.damagesource.CombatRules} caps armor at 20. OreSpawn
     * sets go well past that, so leftover points keep soaking Cataclysm hits.
     */
    private static float getDamageAfterAbsorbUncapped(float damage, float armor, float toughness) {
        float f = 2.0f + toughness / 4.0f;
        float reduction = Mth.clamp(armor - damage / f, armor * 0.2f, 20.0f);
        float afterVanillaCap = damage * (1.0f - reduction / 25.0f);
        float excess = Math.max(0.0f, armor - 20.0f);
        float extraSoak = Mth.clamp(excess / 40.0f, 0.0f, 0.85f);
        return afterVanillaCap * (1.0f - extraSoak);
    }
}
