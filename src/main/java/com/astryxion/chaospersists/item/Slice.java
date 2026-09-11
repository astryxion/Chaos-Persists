/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.BerthaHit
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.Slice
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  com.astryxion.chaospersists.legacy.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.IIcon
 *  com.astryxion.chaospersists.legacy.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.BerthaHit;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.InteractionHand;

@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Slice extends SwordItem {
    private static final Map<Player, Integer> SUPPRESS_INTERACT_SWING_TICK = new WeakHashMap<>();

    public Slice(Tier par2EnumToolMaterial) {
        super(par2EnumToolMaterial, 3, -2.4f, new Properties().stacksTo(1).durability(2600));
    }

    @Override
    public void onCraftedBy(ItemStack par1ItemStack, Level par2World, Player par3EntityPlayer) {
        ensureEnchantments(par1ItemStack);
    }

    private void ensureEnchantments(ItemStack stack) {
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack) <= 0) {
            stack.enchant(Enchantments.SHARPNESS, 5);
            stack.enchant(Enchantments.BANE_OF_ARTHROPODS, 1);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level par2World, Entity par3Entity, int par4, boolean par5) {
        ensureEnchantments(stack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity != null && (entity instanceof Player || entity instanceof Girlfriend || entity instanceof Boyfriend)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player && !player.level().isClientSide) {
            Integer suppressTick = SUPPRESS_INTERACT_SWING_TICK.get(player);
            if (suppressTick == null || suppressTick != player.tickCount) {
                InteractionHand hand = InteractionHand.MAIN_HAND;
                if (ItemStack.isSameItemSameTags(stack, player.getOffhandItem())) {
                    hand = InteractionHand.OFF_HAND;
                }
                spawnProjectile(player, stack, hand);
            }
        }
        return false;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getLevel().isClientSide) {
            suppressInteractSwing(event.getEntity());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (!event.getLevel().isClientSide) {
            suppressInteractSwing(event.getEntity());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!event.getLevel().isClientSide) {
            suppressInteractSwing(event.getEntity());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        if (!event.getLevel().isClientSide) {
            suppressInteractSwing(event.getEntity());
        }
    }

    private static void suppressInteractSwing(Player player) {
        SUPPRESS_INTERACT_SWING_TICK.put(player, player.tickCount);
    }

    private static void spawnProjectile(Player player, ItemStack stack, InteractionHand hand) {
        if (!(stack.getItem() instanceof Slice)) {
            return;
        }
        double xzoff = 2.0;
        double yoff = 1.55;
        BerthaHit lb = new BerthaHit(ChaosPersists.ENTITY_TYPE_BERTHA_HIT.get(), player, player.level());
        lb.setPos(
                player.getX() - xzoff * Mth.sin((float) Math.toRadians(player.getYHeadRot())),
                player.getY() + yoff,
                player.getZ() + xzoff * Mth.cos((float) Math.toRadians(player.getYHeadRot())));
        lb.setYRot(player.getYHeadRot());
        lb.setXRot(player.getXRot());
        lb.aimFromShooter(player, 2.0);
        if (!lb.tryHitAlongPath()) {
            player.level().addFreshEntity(lb);
        }
        EquipmentSlot slot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        stack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(slot));
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    @Override
    public boolean hurtEnemy(ItemStack par1ItemStack, LivingEntity par2EntityLiving, LivingEntity par3EntityLiving) {
        par1ItemStack.hurtAndBreak(1, par3EntityLiving, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public int getUseDuration(ItemStack par1ItemStack) {
        return 9000;
    }
}
