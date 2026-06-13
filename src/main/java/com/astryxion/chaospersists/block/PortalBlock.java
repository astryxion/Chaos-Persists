package com.astryxion.chaospersists.block;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;

public class PortalBlock extends NetherPortalBlock {

    public PortalBlock(int i, int j) {
        super(AbstractBlock.Properties.of(Material.METAL)
                .noCollission()
                .strength(-1.0F)
                .lightLevel(s -> 11)
                .randomTicks());
    }
}
