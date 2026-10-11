package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

public class BlockExperiencePlant extends Block {

    public BlockExperiencePlant() {
        this(0);
    }

    protected BlockExperiencePlant(int par1) {
        super(net.minecraft.world.level.block.Block.Properties.of().noCollission().randomTicks().noOcclusion().sound(SoundType.GRASS));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor level,
            BlockPos pos,
            BlockPos neighborPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Block bid = level.getBlockState(pos.below()).getBlock();
        if (bid == Blocks.AIR) {
            return false;
        }
        return bid == Blocks.GRASS_BLOCK || bid == Blocks.DIRT || bid == Blocks.FARMLAND;
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (worldIn.random.nextInt(20) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 20; ++j1) {
            worldIn.addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    (float) pos.getX() + rand.nextFloat(),
                    (double) pos.getY() + rand.nextFloat(),
                    (float) pos.getZ() + rand.nextFloat(),
                    0.0,
                    0.0,
                    0.0);
        }
    }

    /**
     * 1.7.10 grew via random tick; 1.12.2 must override updateTick.
     * Do not call super â€” sugar cane growth must not apply.
     */
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(10) != 1) {
            return;
        }
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        ChaosPersists.chaospersistsTrees.ExperienceTree(level, pos.getX(), pos.getY() - 1, pos.getZ());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return Collections.singletonList(new ItemStack(ChaosPersists.MyExperiencePlant));
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyExperienceTreeSeed);
    }
}
