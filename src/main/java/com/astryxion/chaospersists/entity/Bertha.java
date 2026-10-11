package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.FriendlyWeaponHits;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class Bertha extends SwordItem {
    /**
     * Extra {@link ForgeMod#ENTITY_REACH} while held in the main hand. Same approach as
     * popular 1.20.1 reach weapons (e.g. EEEABs chainsword): additive on the Forge 3-block base.
     */
    private static final UUID ENTITY_REACH_UUID = UUID.fromString("A3C8E1B4-6D2F-4A91-8B7C-5E0F9D4A2C18");

    public Bertha(Tier tier) {
        super(tier, 3, -2.4f, new Properties().stacksTo(1).durability(9000));
    }

    /** Forge survival {@link ForgeMod#ENTITY_REACH} base. Extra modifiers add on top of this. */
    private static final double SURVIVAL_ENTITY_REACH_BASE = 3.0;

    /**
     * Survival attack reach while held. Royal 8.05, Bertha/Slice 7, Attitude Adjuster 6.
     */
    private double survivalAttackReach() {
        if (this == ChaosPersists.MyRoyal) {
            return 8.05;
        }
        if (this == ChaosPersists.MyHammy) {
            return 6.0;
        }
        return 7.0;
    }

    private double extraEntityReach() {
        return survivalAttackReach() - SURVIVAL_ENTITY_REACH_BASE;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot != EquipmentSlot.MAINHAND) {
            return super.getDefaultAttributeModifiers(slot);
        }
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(slot));
        builder.put(
                ForgeMod.ENTITY_REACH.get(),
                new AttributeModifier(
                        ENTITY_REACH_UUID,
                        "Weapon modifier",
                        extraEntityReach(),
                        AttributeModifier.Operation.ADDITION));
        return builder.build();
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        if (this == ChaosPersists.MyRoyal) {
            stack.enchant(Enchantments.UNBREAKING, 5);
        } else if (this != ChaosPersists.MyHammy) {
            stack.enchant(Enchantments.KNOCKBACK, 5);
            stack.enchant(Enchantments.BANE_OF_ARTHROPODS, 1);
            stack.enchant(Enchantments.FIRE_ASPECT, 1);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        this.ensureEnchantments(stack);
    }

    private void ensureEnchantments(ItemStack stack) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, stack);
        if (lvl == 0) {
            lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack);
        }
        if (lvl <= 0) {
            if (this == ChaosPersists.MyRoyal) {
                stack.enchant(Enchantments.UNBREAKING, 5);
            } else if (this != ChaosPersists.MyHammy) {
                stack.enchant(Enchantments.KNOCKBACK, 5);
                stack.enchant(Enchantments.BANE_OF_ARTHROPODS, 1);
                stack.enchant(Enchantments.FIRE_ASPECT, 1);
            }
        }
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity != null
                && (FriendlyWeaponHits.isCompanion(entity)
                        || FriendlyWeaponHits.isListedIgnore(entity)
                        || (ChaosPersists.big_bertha_pvp == 0
                                && FriendlyWeaponHits.isFriendlyWhenPvpOff(entity)))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player && !player.level().isClientSide) {
            applySwingHits(player);
        }
        return false;
    }

    private void applySwingHits(Player player) {
        double maxDistSq;
        float damage;
        double knockback;
        double inair;
        boolean setFire;
        boolean explode;
        double maxReach = survivalAttackReach();
        maxDistSq = maxReach * maxReach;
        if (this == ChaosPersists.MyRoyal) {
            damage = ChaosPersists.royal_stats.damage;
            knockback = 1.5;
            inair = 0.25;
            setFire = false;
            explode = false;
        } else if (this == ChaosPersists.MyHammy) {
            damage = ChaosPersists.hammy_stats.damage;
            knockback = 1.25;
            inair = 0.65;
            setFire = false;
            explode = true;
        } else {
            damage = ChaosPersists.bertha_stats.damage;
            knockback = 2.25;
            inair = 0.35;
            setFire = true;
            explode = false;
        }

        float yaw = player.getYHeadRot();
        float pitch = player.getXRot();
        double xzoff = 2.0;
        Vec3 start =
                new Vec3(
                        player.getX() - xzoff * Math.sin(Math.toRadians(yaw)),
                        player.getY() + 1.55,
                        player.getZ() + xzoff * Math.cos(Math.toRadians(yaw)));
        Vec3 look = Vec3.directionFromRotation(pitch, yaw);
        Vec3 end = start.add(look.scale(maxReach));
        double pad = 1.0 + 0.33 / 2.0;
        AABB pathBox = new AABB(start, end).inflate(pad);
        AABB search = player.getBoundingBox().inflate(maxReach + pad);
        List<Entity> found = player.level().getEntities(player, search, Entity::isPickable);
        Set<Entity> damaged = new HashSet<>();
        boolean exploded = false;
        for (Entity raw : found) {
            if (raw == null || raw == player || !raw.isAlive()) {
                continue;
            }
            if (FriendlyWeaponHits.isCompanion(raw)
                    || FriendlyWeaponHits.isListedIgnore(raw)
                    || (ChaosPersists.big_bertha_pvp == 0
                            && FriendlyWeaponHits.isFriendlyWhenPvpOff(raw))) {
                continue;
            }
            Entity target = FriendlyWeaponHits.root(raw);
            if (target == null || target == player || !damaged.add(target)) {
                continue;
            }
            if (player.distanceToSqr(target) >= maxDistSq) {
                continue;
            }
            AABB hitBox = raw.getBoundingBox().inflate(0.3);
            if (hitBox.clip(start, end).isEmpty() && !hitBox.intersects(pathBox)) {
                continue;
            }
            // Vanilla attack already ran this tick (hurtEnemy knockback). Do not shove twice.
            if (target instanceof LivingEntity living
                    && living.invulnerableTime > 10
                    && living.getLastHurtByMob() == player) {
                continue;
            }
            target.hurt(player.damageSources().playerAttack(player), damage);
            applyBerthaImpact(player, target, knockback, inair, setFire);
            if (explode && !exploded && !player.level().isClientSide) {
                exploded = true;
                player.level()
                        .explode(
                                null,
                                target.getX(),
                                target.getY(),
                                target.getZ(),
                                1.5f,
                                true,
                                player.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                                        ? ExplosionInteraction.MOB
                                        : ExplosionInteraction.NONE);
            }
        }
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    /**
     * 1.7.10 {@code BerthaHit} {@code addVelocity}. Applied on the vanilla connect (hurtEnemy)
     * and on extra swing-scan targets. Sets {@code hurtMarked} so the client actually receives it.
     */
    private void applyBerthaImpact(
            LivingEntity attacker, Entity target, double knockback, double inair, boolean setFire) {
        if (setFire) {
            target.setRemainingFireTicks(200);
        }
        double air = inair;
        if (!target.isAlive()) {
            air *= 2.0;
        }
        float f3 =
                (float) Math.atan2(target.getZ() - attacker.getZ(), target.getX() - attacker.getX());
        target.push(Math.cos(f3) * knockback, air, Math.sin(f3) * knockback);
        if (target instanceof LivingEntity living) {
            living.hurtMarked = true;
        }
    }

    private void applyBerthaImpact(LivingEntity attacker, Entity target) {
        if (this == ChaosPersists.MyRoyal) {
            applyBerthaImpact(attacker, target, 1.5, 0.25, false);
        } else if (this == ChaosPersists.MyHammy) {
            applyBerthaImpact(attacker, target, 1.25, 0.65, false);
        } else {
            applyBerthaImpact(attacker, target, 2.25, 0.35, true);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        if (!attacker.level().isClientSide) {
            applyBerthaImpact(attacker, target);
            if (this == ChaosPersists.MyHammy) {
                attacker.level()
                        .explode(
                                null,
                                target.getX(),
                                target.getY(),
                                target.getZ(),
                                1.5f,
                                true,
                                attacker.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                                        ? ExplosionInteraction.MOB
                                        : ExplosionInteraction.NONE);
            }
        }
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 9000;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
        com.astryxion.chaospersists.client.item.BigWeaponClientExtensions.register(consumer, this);
    }
}
