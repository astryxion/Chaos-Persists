package com.astryxion.chaospersists.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemPopcorn extends Item {
    public ItemPopcorn(int hunger, float saturation) {
        this(hunger, saturation, false);
    }

    public ItemPopcorn(int hunger, float saturation, boolean wolfFood) {
        super(new Item.Properties().food(buildFood(hunger, saturation, wolfFood)));
    }

    private static Food buildFood(int hunger, float saturation, boolean wolfFood) {
        Food.Builder builder = new Food.Builder().nutrition(hunger).saturationMod(saturation);
        if (wolfFood) {
            builder.meat();
        }
        return builder.build();
    }
}
