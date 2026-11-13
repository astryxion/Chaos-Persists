package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Rat;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class RatSword extends SwordItem {
    private static final int WEAPON_DAMAGE = 10;

    public RatSword(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1300).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.4f)));
    }

    public String getMaterialName() {
        return "Rat";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target != null && !target.level().isClientSide) {
            int num = 1 + target.getRandom().nextInt(6);
            for (int i = 0; i < num; ++i) {
                Entity ent =
                        ItemSpawnEgg.spawnCreature(
                                target.level(),
                                0,
                                "Rat",
                                target.getX()
                                        + (double) (target.getRandom().nextFloat()
                                                - target.getRandom().nextFloat())
                                                * 0.5,
                                target.getY() + (double) target.getRandom().nextFloat() + 0.01,
                                target.getZ()
                                        + (double) (target.getRandom().nextFloat()
                                                - target.getRandom().nextFloat())
                                                * 0.5);
                if (ent instanceof Rat rat) {
                    rat.setOwner(attacker);
                }
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
