package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class MyBlockFlower extends BushBlock implements IPlantable {
    private static final VoxelShape FLOWER_SHAPE = Block.box(4.8D, 0.0D, 4.8D, 11.2D, 9.6D, 11.2D);

    public MyBlockFlower() {
        super(Properties.of(Material.PLANT)
                .noOcclusion()
                .sound(SoundType.GRASS)
                );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return FLOWER_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    protected boolean mayPlaceOn(BlockState state, IBlockReader level, BlockPos pos) {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK
                || block == Blocks.DIRT
                || block == Blocks.FARMLAND
                || block == ChaosPersists.CrystalGrass;
    }

    public boolean canBlockStay(World worldIn, BlockPos pos) {
        BlockPos down = pos.below();
        return mayPlaceOn(worldIn.getBlockState(down), worldIn, down);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.PLAINS;
    }
}
