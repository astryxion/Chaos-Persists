package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemSunFish extends Item {

    public ItemSunFish(int nutrition, float saturation, boolean alwaysEdible) {
        super(createProperties(nutrition, saturation, alwaysEdible));
    }

    private static Properties createProperties(int nutrition, float saturation, boolean alwaysEdible) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
        if (alwaysEdible) {
            builder.alwaysEdible();
        }
        return new Properties().food(builder.build());
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!level.isClientSide && entity instanceof Player player) {
            Item item = stack.getItem();
            if (item == ChaosPersists.MySunFish) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));
            }
            if (item == ChaosPersists.MyButterCandy) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 2000, 0));
            }
            if (item == ChaosPersists.MyBacon) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2000, 0));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 0));
            }
            if (item == ChaosPersists.MyCrystalApple) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3000, 0));
            }
            if (item == ChaosPersists.MyLove) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 6000, 3));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 2));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 2));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 5000, 0));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 5000, 0));
            }
        }
        return result;
    }
}
