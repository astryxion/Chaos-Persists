package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
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

/**
 * Crystal leaves use 1.7 OreSpawn decay: only fall if no nearby log can sustain them.
 * Vanilla LeavesBlock distance decay is wrong here — crystal trees are placed with
 * {@code setBlockFast} and trunks were not in {@code #minecraft:logs}, so every leaf
 * sat at distance 7 and dropped as items.
 */
public class BlockCrystalLeaves extends LeavesBlock implements IForgeShearable {

    public BlockCrystalLeaves() {
        super(Block.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.GRASS).randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int radius = 2;
        if (!level.isAreaLoaded(pos, radius)) {
            return;
        }

        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -radius; dy <= 0; ++dy) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    int manhattan = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
                    if (manhattan > 3) {
                        continue;
                    }
                    BlockPos check = pos.offset(dx, dy, dz);
                    BlockState nearby = level.getBlockState(check);
                    if (nearby.is(BlockTags.LOGS) || nearby.is(ChaosPersists.MyCrystalTreeLog)) {
                        return;
                    }
                }
            }
        }

        dropResources(state, level, pos);
        level.removeBlock(pos, false);
    }

    @Override
    protected boolean decaying(BlockState state) {
        // Decay is handled entirely in {@link #randomTick} (1.7 style).
        return false;
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
        if (com.astryxion.chaospersists.loot.DatapackLoot.hasTable(level, this.getLootTable())) {
            return;
        }
        if (BlockAppleLeaves.isSilkOrShears(tool)) {
            return;
        }
        RandomSource random = level.getRandom();
        if (random.nextInt(100) == 1) {
            popResource(level, pos, new ItemStack(ChaosPersists.MyCrystalApple));
        }
        if (random.nextInt(50) == 1) {
            if (this == ChaosPersists.MyCrystalLeaves) {
                popResource(level, pos, new ItemStack(ChaosPersists.MyCrystalPlant));
            }
            if (this == ChaosPersists.MyCrystalLeaves2) {
                popResource(level, pos, new ItemStack(ChaosPersists.MyCrystalPlant2));
            }
            if (this == ChaosPersists.MyCrystalLeaves3) {
                popResource(level, pos, new ItemStack(ChaosPersists.MyCrystalPlant3));
            }
        }
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
    public List<ItemStack> onSheared(net.minecraft.world.entity.player.Player player, ItemStack item, Level level, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
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
