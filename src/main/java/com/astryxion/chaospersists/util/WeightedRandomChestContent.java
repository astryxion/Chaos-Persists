package com.astryxion.chaospersists.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 1.12.2 compatibility: replaces removed net.minecraft.util.WeightedRandomChestContent.
 * Fills chests with weighted random items (same API as 1.7.10).
 */
public class WeightedRandomChestContent extends WeightedRandom.Item {

    public final Item item;
    public final int metadata;
    public final int minStackSize;
    public final int maxStackSize;

    public WeightedRandomChestContent(Item item, int metadata, int minStackSize, int maxStackSize, int weight) {
        super(weight);
        this.item = item;
        this.metadata = metadata;
        this.minStackSize = minStackSize;
        this.maxStackSize = maxStackSize;
    }

    public WeightedRandomChestContent(ItemStack stack, int minChance, int maxChance, int weight) {
        super(weight);
        this.item = stack.getItem();
        this.metadata = 0;
        this.minStackSize = minChance;
        this.maxStackSize = maxChance;
    }

    public static void generateChestContents(Random random, WeightedRandomChestContent[] content, IInventory inv, int max) {
        generateChestContents(random, Arrays.asList(content), inv, max);
    }

    public static void generateChestContents(Random random, List<WeightedRandomChestContent> content, IInventory inv, int max) {
        for (int i = 0; i < max; i++) {
            WeightedRandomChestContent entry = (WeightedRandomChestContent) WeightedRandom.getRandomItem(random, content);
            if (entry == null) {
                continue;
            }
            int count = entry.minStackSize
                    + (entry.maxStackSize > entry.minStackSize ? random.nextInt(entry.maxStackSize - entry.minStackSize + 1) : 0);
            int limit = entry.item.getMaxStackSize();
            ItemStack stack = new ItemStack(entry.item, Math.min(count, limit));
            int slot = random.nextInt(inv.getContainerSize());
            inv.setItem(slot, stack);
        }
    }
}
