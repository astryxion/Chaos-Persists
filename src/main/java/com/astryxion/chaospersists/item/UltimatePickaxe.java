package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class UltimatePickaxe extends PickaxeItem {
    private static final int WEAPON_DAMAGE = 38;

    public UltimatePickaxe(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(3000).attributes(PickaxeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.8f)));
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
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FORTUNE), 5);
        }
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof Girlfriend || target instanceof Boyfriend || target instanceof Player) {
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

    @Override
    public boolean mineBlock(
            ItemStack stack,
            Level level,
            BlockState state,
            BlockPos pos,
            LivingEntity entityLiving) {
        Block block = state.getBlock();
        if (state.getDestroySpeed(level, pos) != 0.0f) {
            stack.hurtAndBreak(1, entityLiving, EquipmentSlot.MAINHAND);
        }
        if (!level.isClientSide) {
            if (block == Blocks.IRON_ORE && level.getRandom().nextInt(2) != 0) {
                dropItemAt(level, pos, Items.IRON_INGOT, 1 + level.getRandom().nextInt(2));
            }
            if (block == Blocks.GOLD_ORE && level.getRandom().nextInt(2) != 0) {
                dropItemAt(level, pos, Items.GOLD_INGOT, 1 + level.getRandom().nextInt(2));
            }
            if (block == Blocks.STONE && level.getRandom().nextInt(100) == 2) {
                int i = level.getRandom().nextInt(10);
                if (i == 0) {
                    dropItemAt(level, pos, Items.DIAMOND, 1);
                } else if (i == 1) {
                    dropItemAt(level, pos, Items.EMERALD, 1);
                } else if (i == 2) {
                    dropItemAt(level, pos, ChaosPersists.MyAmethyst, 1);
                } else if (i == 3) {
                    dropItemAt(level, pos, ChaosPersists.MyRuby, 1);
                } else if (i == 4) {
                    dropItemAt(level, pos, ChaosPersists.UraniumNugget, 1);
                } else if (i == 5) {
                    dropItemAt(level, pos, ChaosPersists.TitaniumNugget, 1);
                }
            }
        }
        return true;
    }

    private void dropItemAt(Level world, BlockPos pos, Item index, int count) {
        ItemStack is = new ItemStack(index, count);
        ItemEntity drop =
                new ItemEntity(
                        world,
                        (double) pos.getX(),
                        (double) pos.getY(),
                        (double) pos.getZ(),
                        is);
        world.addFreshEntity(drop);
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }
}
