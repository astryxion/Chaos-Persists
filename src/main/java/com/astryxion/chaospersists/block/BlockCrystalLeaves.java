package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.server.ServerWorld;
import javax.annotation.Nullable;

public class BlockCrystalLeaves extends LeavesBlock implements IForgeShearable {

    public BlockCrystalLeaves() {
        this(0.2F);
    }

    public BlockCrystalLeaves(float hardness) {
        super(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)
                .randomTicks()
                .strength(hardness)
                .sound(SoundType.GRASS)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PERSISTENT, false)
                .setValue(DISTANCE, 7));
    }

    @Override
    public boolean isShearable(ItemStack item, World world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(PlayerEntity player, ItemStack item, World world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
    }

    private void dropCrystalBreakLoot(ServerWorld world, BlockPos pos) {
        if (world.random.nextInt(100) == 1) {
            popResource(world, pos, new ItemStack(ChaosPersists.MyCrystalApple));
        }
        if (world.random.nextInt(50) == 1) {
            if (this == ChaosPersists.MyCrystalLeaves) {
                popResource(world, pos, new ItemStack(ChaosPersists.MyCrystalPlant));
            }
            if (this == ChaosPersists.MyCrystalLeaves2) {
                popResource(world, pos, new ItemStack(ChaosPersists.MyCrystalPlant2));
            }
            if (this == ChaosPersists.MyCrystalLeaves3) {
                popResource(world, pos, new ItemStack(ChaosPersists.MyCrystalPlant3));
            }
        }
    }

    @Override
    public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.playerDestroy(world, player, pos, state, te, stack);
        if (!world.isClientSide && !player.isCreative() && world instanceof ServerWorld) {
            this.dropCrystalBreakLoot((ServerWorld) world, pos);
        }
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 1;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return ChaosPersists.FastGraphicsLeaves != 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        return ChaosPersists.FastGraphicsLeaves == 0 || adjacentState.getBlock() != this;
    }

    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType(BlockState state) {
        return RenderType.translucent();
    }
}
