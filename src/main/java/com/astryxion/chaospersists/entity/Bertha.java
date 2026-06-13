package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.world.World;
import net.minecraft.util.math.vector.Vector3d;

public class Bertha extends SwordItem {
    public Bertha(IItemTier par2EnumToolMaterial) {
        super(par2EnumToolMaterial, 3, -2.4F, new Item.Properties().stacksTo(1).durability(9000));
    }

    @Override
    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        if (this == ChaosPersists.MyRoyal) {
            this.applyRoyalEnchant(par1ItemStack);
        } else if (this != ChaosPersists.MyHammy) {
            this.applyBerthaEnchant(par1ItemStack);
        }
    }

    @Override
    public void onUseTick(World world, LivingEntity player, ItemStack stack, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantment.byId(19), stack);
        if (lvl == 0) {
            lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantment.byId(34), stack);
        }
        if (lvl <= 0) {
            if (this == ChaosPersists.MyRoyal) {
                this.applyRoyalEnchant(stack);
            } else if (this != ChaosPersists.MyHammy) {
                this.applyBerthaEnchant(stack);
            }
        }
    }

    private void applyRoyalEnchant(ItemStack stack) {
        Enchantment e = Enchantment.byId(34);
        if (e != null) {
            stack.enchant(e, 5);
        }
    }

    private void applyBerthaEnchant(ItemStack stack) {
        Enchantment e19 = Enchantment.byId(19);
        Enchantment e18 = Enchantment.byId(18);
        Enchantment e20 = Enchantment.byId(20);
        if (e19 != null) {
            stack.enchant(e19, 5);
        }
        if (e18 != null) {
            stack.enchant(e18, 1);
        }
        if (e20 != null) {
            stack.enchant(e20, 1);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        this.onUseTick(par2World, null, stack, 0);
    }

    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity != null && ChaosPersists.big_bertha_pvp == 0) {
            TameableEntity t;
            if (entity instanceof PlayerEntity || entity instanceof Girlfriend || entity instanceof Boyfriend) {
                return true;
            }
            if (entity instanceof TameableEntity && (t = (TameableEntity) entity).isTame()) {
                return true;
            }
        }
        return false;
    }

    public boolean onEntitySwing(LivingEntity entityLiving, ItemStack stack) {
        if (entityLiving != null && entityLiving instanceof PlayerEntity && !entityLiving.level.isClientSide) {
            PlayerEntity p = (PlayerEntity) entityLiving;
            double xzoff = 2.0;
            double yoff = 1.55;
            BerthaHit lb = new BerthaHit(p.level, p);
            lb.moveTo(p.getX() - xzoff * MathHelper.sin((float) Math.toRadians(p.yHeadRot)), p.getY() + yoff, p.getZ() + xzoff * MathHelper.cos((float) Math.toRadians(p.yHeadRot)), p.yHeadRot, p.xRot);
            net.minecraft.util.math.vector.Vector3d motion = lb.getDeltaMovement();
            lb.setDeltaMovement(motion.x * 2.0, motion.y * 2.0, motion.z * 2.0);
            if (this == ChaosPersists.MyRoyal) {
                lb.setHitType(2);
            }
            if (this == ChaosPersists.MyHammy) {
                lb.setHitType(3);
            }
            p.level.addFreshEntity(lb);
            stack.hurtAndBreak(1, p, (e) -> e.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
        }
        return false;
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    @Override
    public boolean hurtEnemy(ItemStack par1ItemStack, LivingEntity par2LivingEntity, LivingEntity par3LivingEntity) {
        par1ItemStack.hurtAndBreak(1, par3LivingEntity, (e) -> e.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
        return true;
    }

    @Override
    public int getUseDuration(ItemStack par1ItemStack) {
        return 9000;
    }
}
