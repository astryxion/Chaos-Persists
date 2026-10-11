package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.IForgeShearable;

public class BlockScaryLeaves extends LeavesBlock implements IForgeShearable {

    public BlockScaryLeaves() {
        super(net.minecraft.world.level.block.Block.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.GRASS).randomTicks());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        ItemStack tool = builder.getOptionalParameter(LootContextParams.TOOL);
        if (BlockAppleLeaves.isSilkOrShears(tool)) {
            return Collections.singletonList(new ItemStack(this));
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isShearable(ItemStack item, Level level, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(
            net.minecraft.world.entity.player.Player player, ItemStack item, Level level, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
    }

    /** OreSpawn 1.7.10: 1/25 cherry or peach at {@code dropPos}. */
    private void maybeDropCherryOrPeach(ServerLevel level, BlockPos dropPos, RandomSource rand) {
        if (rand.nextInt(25) != 1) {
            return;
        }
        if (this == ChaosPersists.MyCherryLeaves) {
            popResource(level, dropPos, new ItemStack(ChaosPersists.MyCherry));
        } else if (this == ChaosPersists.MyPeachLeaves) {
            popResource(level, dropPos, new ItemStack(ChaosPersists.MyPeach));
        }
    }

    @Override
    public void spawnAfterBreak(
            BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
        if (com.astryxion.chaospersists.loot.DatapackLoot.hasTable(level, this.getLootTable())) {
            super.spawnAfterBreak(state, level, pos, tool, dropExperience);
            return;
        }
        if (BlockAppleLeaves.isSilkOrShears(tool)) {
            return;
        }
        maybeDropCherryOrPeach(level, pos, level.getRandom());
        super.spawnAfterBreak(state, level, pos, tool, dropExperience);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        int var7 = 2;
        if (level.hasChunksAt(
                new BlockPos(par2 - var7, par3 - var7, par4 - var7),
                new BlockPos(par2 + var7, par3 + var7, par4 + var7))) {
            for (int var12 = -var7; var12 <= var7; ++var12) {
                for (int var13 = -var7; var13 <= 0; ++var13) {
                    for (int var14 = -var7; var14 <= var7; ++var14) {
                        Block bid;
                        BlockPos off = new BlockPos(par2 + var12, par3 + var13, par4 + var14);
                        int totaldist = Math.abs(var12) + Math.abs(var13) + Math.abs(var14);
                        BlockState offState = level.getBlockState(off);
                        bid = offState.getBlock();
                        if (totaldist > 3 || !offState.isFaceSturdy(level, off, net.minecraft.core.Direction.UP)) {
                            continue;
                        }
                        long t = level.getDayTime();
                        if (this == ChaosPersists.MyScaryLeaves && (t %= 24000L) < 12000L) {
                            level.setBlock(
                                    new BlockPos(par2, par3, par4),
                                    ChaosPersists.MyAppleLeaves.defaultBlockState(),
                                    2);
                        }
                        if (level.getBlockState(new BlockPos(par2, par3 - 1, par4)).isAir()
                                && level.getRandom().nextInt(20) == 3) {
                            maybeDropCherryOrPeach(level, new BlockPos(par2, par3 - 1, par4), level.getRandom());
                        }
                        return;
                    }
                }
            }
            removeLeaves(level, par2, par3, par4);
        }
    }

    private void removeLeaves(ServerLevel level, int par2, int par3, int par4) {
        BlockPos blockPos = new BlockPos(par2, par3, par4);
        BlockState st = level.getBlockState(blockPos);
        spawnAfterBreak(st, level, blockPos, ItemStack.EMPTY, false);
        level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (ChaosPersists.FastGraphicsLeaves == 0 && adjacentState.getBlock() == this) {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return ChaosPersists.FastGraphicsLeaves != 0;
    }
}
