package com.astryxion.chaospersists.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ExperienceSword extends SwordItem {
    private static final int WEAPON_DAMAGE = 10;
    private Level worldObj = null;
    private Level worldObjr = null;

    public ExperienceSword(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1400).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.4f)));
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 2);
        stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOOTING), 3);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.SHARPNESS, stack) <= 0) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 2);
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.LOOTING), 3);
        }
        LivingEntity e = null;
        ItemChaosArmor ia = null;
        Player p = null;
        if (!level.isClientSide) {
            this.worldObj = level;
        }
        if (level.isClientSide) {
            this.worldObjr = level;
        }
        if (level.getRandom().nextInt(60) == 1 && entity instanceof LivingEntity living) {
            e = living;
            if (e instanceof Player player) {
                p = player;
            }
            for (int i = 1; i < 5 && p != null; ++i) {
                Item it;
                EquipmentSlot armorSlot =
                        switch (i) {
                            case 1 -> EquipmentSlot.FEET;
                            case 2 -> EquipmentSlot.LEGS;
                            case 3 -> EquipmentSlot.CHEST;
                            default -> EquipmentSlot.HEAD;
                        };
                ItemStack is = p.getItemBySlot(armorSlot);
                if (is.isEmpty()
                        || !((it = is.getItem()) instanceof ItemChaosArmor armor)
                        || armor.get_armor_material() != 4) {
                    continue;
                }
                ia = armor;
                switch (ia.get_armor_type()) {
                    case 0 -> {
                        if (!level.isClientSide && level.getRandom().nextInt(10) == 1) {
                            p.giveExperiencePoints(1);
                        }
                        level.addParticle(
                                ParticleTypes.PORTAL,
                                e.getX(),
                                e.getY() + 1.5,
                                e.getZ(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian());
                    }
                    case 1 -> {
                        if (!level.isClientSide && level.getRandom().nextInt(20) == 1) {
                            p.giveExperiencePoints(1);
                        }
                        level.addParticle(
                                ParticleTypes.PORTAL,
                                e.getX(),
                                e.getY() + 1.25,
                                e.getZ(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian());
                    }
                    case 2 -> {
                        if (!level.isClientSide && level.getRandom().nextInt(30) == 1) {
                            p.giveExperiencePoints(1);
                        }
                        level.addParticle(
                                ParticleTypes.PORTAL,
                                e.getX(),
                                e.getY() + 0.75,
                                e.getZ(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian());
                    }
                    case 3 -> {
                        if (!level.isClientSide && level.getRandom().nextInt(40) == 1) {
                            p.giveExperiencePoints(1);
                        }
                        level.addParticle(
                                ParticleTypes.PORTAL,
                                e.getX(),
                                e.getY() + 0.25,
                                e.getZ(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian(),
                                level.getRandom().nextGaussian());
                    }
                    default -> {}
                }
            }
        }
    }

    public String getMaterialName() {
        return "Emerald";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        float i = 0.0f;
        Player p = null;
        if (attacker instanceof Player player) {
            p = player;
        }
        if (target != null && target instanceof Mob) {
            i = 10.0f;
        }
        if (i > 0.0f && p != null) {
            p.giveExperiencePoints((int) i);
        }
        if (p != null && (i = (float) (p.experienceLevel / 2)) > 0.0f && target != null) {
            target.hurt(p.damageSources().playerAttack(p), i);
        }
        if (this.worldObjr != null && target != null) {
            int j = 0;
            while ((float) j <= i / 2.0f) {
                this.worldObjr.addParticle(
                        ParticleTypes.PORTAL,
                        target.getX(),
                        target.getY() + 1.0,
                        target.getZ(),
                        this.worldObjr.getRandom().nextGaussian(),
                        this.worldObjr.getRandom().nextGaussian(),
                        this.worldObjr.getRandom().nextGaussian());
                ++j;
            }
        }
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 3000;
    }
}
