package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class UltimateShovel extends ShovelItem {
    private static final float WEAPON_DAMAGE = 37.0f;

    public UltimateShovel(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(3000).attributes(ShovelItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -3.0f)));
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        ensureEnchantments(stack, level);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        ensureEnchantments(stack, level);
    }

    private void ensureEnchantments(ItemStack stack, Level level) {
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.EFFICIENCY, stack) <= 0) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.EFFICIENCY), 5);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof Girlfriend || target instanceof Player) {
            if (attacker instanceof Player player) {
                target.hurt(player.damageSources().playerAttack(player), 1.0f);
            } else {
                target.hurt(target.damageSources().generic(), 1.0f);
            }
            stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
            return true;
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity != null && ChaosPersists.ultimate_sword_pvp == 0) {
            if (entity instanceof Player
                    || entity instanceof Girlfriend
                    || entity instanceof Boyfriend
                    || (entity instanceof TamableAnimal t && t.isTame())) {
                return true;
            }
        }
        return false;
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }
}
