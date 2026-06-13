package com.astryxion.chaospersists.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ItemGenericFish extends Item {
    public ItemGenericFish(int hunger, float saturation) {
        super(new Item.Properties().food(new Food.Builder().nutrition(hunger).saturationMod(saturation).build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, world, entity);
        if (entity instanceof PlayerEntity && !world.isClientSide && world.random.nextInt(4) == 1) {
            ((PlayerEntity) entity).addEffect(new EffectInstance(Effects.HUNGER, 20, 0));
        }
        return result;
    }
}
