package com.astryxion.chaospersists.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ItemPopcorn extends Item {

    public ItemPopcorn(int nutrition, float saturation, boolean alwaysEdible) {
        super(createProperties(nutrition, saturation, alwaysEdible));
    }

    private static Properties createProperties(int nutrition, float saturation, boolean alwaysEdible) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
        if (alwaysEdible) {
            builder.alwaysEdible();
        }
        return new Properties().food(builder.build());
    }
}
