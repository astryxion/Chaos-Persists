package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.FriendlyWeaponHits;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
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

    /**
     * Old {@link BerthaHit} allowed {@code owner.distanceToSqr(target)} up to 81 / 101 / 64
     * (9 / {@code sqrt(101)} / 8 blocks). Forge survival entity reach is 3, so extra is those
     * distances minus 3.
     */
    private double extraEntityReach() {
        if (this == ChaosPersists.MyRoyal) {
            return Math.sqrt(101.0) - 3.0;
        }
        if (this == ChaosPersists.MyHammy) {
            return 5.0;
        }
        return 6.0;
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

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        if (this == ChaosPersists.MyHammy && !attacker.level().isClientSide) {
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
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 9000;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
        com.astryxion.chaospersists.client.BigWeaponClientExtensions.register(consumer, this);
    }
}
