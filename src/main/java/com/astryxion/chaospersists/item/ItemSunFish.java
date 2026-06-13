package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ItemSunFish extends Item {
    public ItemSunFish(int hunger, float saturation) {
        super(new Item.Properties().food(new Food.Builder().nutrition(hunger).saturationMod(saturation).alwaysEat().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, world, entity);
        if (!(entity instanceof PlayerEntity) || world.isClientSide) {
            return result;
        }
        PlayerEntity player = (PlayerEntity) entity;
        if (this == ChaosPersists.MySunFish) {
            player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0));
        }
        if (this == ChaosPersists.MyButterCandy) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2000, 0));
            player.addEffect(new EffectInstance(Effects.JUMP, 2000, 0));
        }
        if (this == ChaosPersists.MyBacon) {
            player.addEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2000, 0));
        }
        if (this == ChaosPersists.MyCrystalApple) {
            player.addEffect(new EffectInstance(Effects.REGENERATION, 3000, 0));
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 3000, 0));
        }
        if (this == ChaosPersists.MyLove) {
            player.addEffect(new EffectInstance(Effects.REGENERATION, 6000, 3));
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 6000, 2));
            player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 2));
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 6000, 1));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 5000, 0));
            player.addEffect(new EffectInstance(Effects.JUMP, 5000, 0));
        }
        return result;
    }
}
