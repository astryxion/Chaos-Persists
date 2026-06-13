package com.astryxion.chaospersists.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ItemLavaEel extends Item {
    public ItemLavaEel(int hunger, float saturation) {
        super(new Item.Properties().food(new Food.Builder().nutrition(hunger).saturationMod(saturation).alwaysEat().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, world, entity);
        if (entity instanceof PlayerEntity && !world.isClientSide) {
            ((PlayerEntity) entity).addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 0));
        }
        return result;
    }
}
