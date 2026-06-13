package com.astryxion.chaospersists.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MoleDirtBlock extends Block {

    public MoleDirtBlock() {
        this(0);
    }

    public MoleDirtBlock(float hardness) {
        super(AbstractBlock.Properties.of(Material.STONE)
                .sound(SoundType.STONE)
                .strength(hardness)
                .randomTicks());
    }

    public MoleDirtBlock(int i) {
        this(0.6F);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.IBlockReader world, BlockPos pos, net.minecraft.util.math.shapes.ISelectionContext context) {
        float f = 0.125f;
        return VoxelShapes.box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D - f, 1.0D);
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity != null) {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.3D, 1.0D, 0.3D));
        }
    }
}
