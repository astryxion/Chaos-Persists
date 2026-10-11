package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.client.item.BigWeaponClientExtensions;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

/**
 * Big hammer launches enemies upward on hit (1.12.2 {@code BigHammer#hitEntity}).
 */
public class BigHammer extends SwordItem {

    public BigHammer(Tier tier) {
        super(tier, 3, -2.4f, new Properties().stacksTo(1).durability(9000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide) {
            var motion = target.getDeltaMovement();
            target.setDeltaMovement(motion.x, motion.y + 1.2D, motion.z);

            double dx = target.getX() - attacker.getX();
            double dz = target.getZ() - attacker.getZ();
            double distance = Mth.sqrt((float) (dx * dx + dz * dz));
            if (distance > 0.0D) {
                motion = target.getDeltaMovement();
                target.setDeltaMovement(
                        motion.x + (dx / distance) * 0.8D,
                        motion.y,
                        motion.z + (dz / distance) * 0.8D);
            }

            target.hurtMarked = true;
        }

        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 3000;
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        BigWeaponClientExtensions.register(consumer, this);
    }
}
