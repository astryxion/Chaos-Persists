package com.astryxion.chaospersists.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrystalGrass extends Block {

    public CrystalGrass(float hardness, float resistance) {
        super(AbstractBlock.Properties.of(Material.GRASS)
                .strength(hardness, resistance)
                
                .sound(SoundType.GRASS)
                .noOcclusion());
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world,
                                   BlockPos pos, Direction direction,
                                   IPlantable plantable) {
        return true;
    }

    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos,
                                    net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType type,
                                    EntityType<?> entityType) {
        return Blocks.GRASS_BLOCK.canCreatureSpawn(Blocks.GRASS_BLOCK.defaultBlockState(), world, pos, type, entityType);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
public RenderType getRenderType(BlockState state) {
        return RenderType.cutout();
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
