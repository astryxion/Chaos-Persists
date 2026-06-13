package com.astryxion.chaospersists.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.MathHelper;

public class BigHammer extends SwordItem {

    public BigHammer(IItemTier material) {
        this(material, new Item.Properties().stacksTo(1).durability(9000));
    }

    public BigHammer(IItemTier material, Item.Properties properties) {
        super(material, 3, -2.4F, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level.isClientSide) {
            net.minecraft.util.math.vector.Vector3d motion = target.getDeltaMovement();
            double dx = target.getX() - attacker.getX();
            double dz = target.getZ() - attacker.getZ();
            double distance = MathHelper.sqrt(dx * dx + dz * dz);
            double knockX = distance > 0 ? (dx / distance) * 0.8D : 0.0D;
            double knockZ = distance > 0 ? (dz / distance) * 0.8D : 0.0D;
            target.setDeltaMovement(motion.x + knockX, motion.y + 1.2D, motion.z + knockZ);
            target.hurtMarked = true;
        }
        stack.hurtAndBreak(1, attacker, (e) -> e.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 3000;
    }
}
