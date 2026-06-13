package com.astryxion.chaospersists.util;

import net.minecraft.item.ItemStack;
import java.util.Random;

public class WeightedRandomFishable extends net.minecraft.util.WeightedRandom.Item {
    private final ItemStack stack;
    private final int weight;
    private float chance = 1.0f;
    private boolean treasure = false;

    public WeightedRandomFishable(ItemStack stack, int itemWeightIn) {
        super(itemWeightIn);
        this.stack = stack;
        this.weight = itemWeightIn;
    }

    public WeightedRandomFishable func_150709_a(float chanceIn) {
        this.chance = chanceIn;
        return this;
    }

    public WeightedRandomFishable func_150707_a() {
        this.treasure = true;
        return this;
    }

    public ItemStack getItemStack(Random random) {
        ItemStack out = this.stack.copy();
        if (out.getCount() > 1) {
            out.setCount(1 + random.nextInt(out.getCount()));
        }
        return out;
    }

    public float getChance() {
        return chance;
    }

    public boolean isTreasure() {
        return treasure;
    }

    public int getWeight() {
        return weight;
    }
}
