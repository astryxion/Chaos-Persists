package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrystalAntBlock extends Block {

    public CrystalAntBlock(int par1) {
        super(AbstractBlock.Properties.of(Material.GRASS)
                .randomTicks()
                .noOcclusion());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (world.getBlockState(pos.above()).getBlock() != Blocks.AIR) {
            return;
        }
        if (!world.isDay()) {
            return;
        }
        String mobName = getMobNameForBlock();
        if (mobName == null) {
            return;
        }
        Class<? extends Entity> entityClass = getEntityClassForBlock();
        if (entityClass == null) {
            return;
        }
        int radius = 16;
        AxisAlignedBB aabb = new AxisAlignedBB(
                pos.getX() - radius, 0.0D, pos.getZ() - radius,
                pos.getX() + radius, 200.0D, pos.getZ() + radius);
        if (world.getEntitiesOfClass(entityClass, aabb).size() > 20) {
            return;
        }
        int count = rand.nextInt(6) + 2;
        for (int i = 0; i < count; i++) {
            AntBlock.spawnCreature(world, mobName, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        }
    }

    private String getMobNameForBlock() {
        if (this == ChaosPersists.CrystalTermiteBlock) {
            return ChaosPersists.TermiteEnable != 0 ? "Termite" : null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Entity> getEntityClassForBlock() {
        if (this == ChaosPersists.CrystalTermiteBlock) {
            return com.astryxion.chaospersists.entity.Termite.class;
        }
        return null;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
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
