package com.astryxion.chaospersists.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrystalWood extends Block {

    public CrystalWood(float hardness, float resistance) {
        super(AbstractBlock.Properties.of(Material.WOOD)
                .strength(hardness, resistance)
                .noOcclusion());
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, net.minecraft.util.math.BlockPos pos) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (adjacentState.getBlock() == this) {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }
}
