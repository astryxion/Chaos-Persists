package com.astryxion.chaospersists.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;

public class ItemButterflySeed extends BlockItem {

    public ItemButterflySeed(Block cropBlock, Block soilBlock) {
        super(cropBlock, new Item.Properties());
    }
}
