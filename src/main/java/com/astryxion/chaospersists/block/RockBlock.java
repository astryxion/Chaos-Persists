package com.astryxion.chaospersists.block;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class RockBlock extends Block {

    public RockBlock() {
        super(AbstractBlock.Properties.of(Material.STONE)
                .strength(2.0f, 1.0f)
                );
    }
}
