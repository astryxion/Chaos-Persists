package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockDuctTape extends Block {
    public static final IntegerProperty SLICES = IntegerProperty.create("slices", 0, 5);

    public BlockDuctTape() {
        super(net.minecraft.world.level.block.Block.Properties.of().strength(0.5f).sound(SoundType.METAL).noOcclusion().randomTicks());
        registerDefaultState(stateDefinition.any().setValue(SLICES, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SLICES);
    }

    private static VoxelShape sliceShape(BlockState state) {
        int l = state.getValue(SLICES);
        float f = 0.0625f;
        float f1 = (float) (1 + l * 2) / 16.0f;
        float f2 = 0.25f;
        return Block.box(f1 * 16.0, 0.0, f * 16.0, (1.0f - f) * 16.0, f2 * 16.0, (1.0f - f) * 16.0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return sliceShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return sliceShape(state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        eatDuctTapeSlice(level, pos, player);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        eatDuctTapeSlice(level, pos, player);
    }

    private void eatDuctTapeSlice(Level level, BlockPos pos, Player player) {
        ItemStack held = player.getMainHandItem();
        if (player != null && !held.isEmpty() && held.getCount() == 1) {
            int cd = held.getMaxDamage();
            int fd = 0;
            if (cd > 0) {
                if ((cd /= 6) < 1) {
                    cd = 1;
                }
                if ((fd = held.getDamageValue()) > 0) {
                    fd = fd > cd ? (fd -= cd) : 0;
                    held.setDamageValue(fd);
                    int l = level.getBlockState(pos).getValue(SLICES) + 1;
                    if (l >= 6) {
                        level.removeBlock(pos, false);
                    } else {
                        level.setBlock(pos, defaultBlockState().setValue(SLICES, l), 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return super.canSurvive(state, level, pos) && canBlockStay(level, pos);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!canBlockStay(level, pos)) {
            level.removeBlock(pos, false);
        }
    }

    private boolean canBlockStay(LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        return level.getBlockState(below).isFaceSturdy(level, below, net.minecraft.core.Direction.UP);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return Collections.emptyList();
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyDuctTapeItem);
    }
}
