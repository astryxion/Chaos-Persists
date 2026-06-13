package com.astryxion.chaospersists.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ItemTomato extends BlockItem {
    public ItemTomato(int par2, float par3, Block cropBlock, Block soilBlock) {
        this(cropBlock, new Item.Properties());
    }

    public ItemTomato(Block cropBlock, Item.Properties properties) {
        super(cropBlock, properties);
    }
}
