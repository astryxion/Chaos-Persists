package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MiningDropHelper;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 1.12 {@code BlockTorch} behavior: floor ({@link Direction#UP}) and wall attachment via one block id.
 */
public abstract class ChaosDirectionalTorchBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    private static final VoxelShape STANDING_SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    private static final Map<Direction, VoxelShape> WALL_SHAPES =
            Maps.newEnumMap(
                    Map.of(
                            Direction.NORTH,
                            Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D),
                            Direction.SOUTH,
                            Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D),
                            Direction.WEST,
                            Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D),
                            Direction.EAST,
                            Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

    protected final ParticleOptions flameParticle;

    protected ChaosDirectionalTorchBlock(Properties properties, ParticleOptions flameParticle) {
        super(properties);
        this.flameParticle = flameParticle;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        if (facing == Direction.UP) {
            return STANDING_SHAPE;
        }
        if (facing.getAxis().isHorizontal()) {
            return WALL_SHAPES.get(facing);
        }
        return Shapes.empty();
    }

    protected boolean canAttachTo(LevelReader level, BlockPos torchPos, Direction attachmentDirection) {
        BlockPos supportPos = torchPos.relative(attachmentDirection.getOpposite());
        return level.getBlockState(supportPos).isFaceSturdy(level, supportPos, attachmentDirection);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        if (facing == Direction.UP) {
            return canAttachTo(level, pos, Direction.UP);
        }
        if (facing.getAxis().isHorizontal()) {
            return canAttachTo(level, pos, facing);
        }
        return false;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();
        if (clickedFace == Direction.DOWN) {
            return null;
        }
        if (clickedFace == Direction.UP && canAttachTo(level, pos, Direction.UP)) {
            return this.defaultBlockState().setValue(FACING, Direction.UP);
        }
        if (clickedFace.getAxis().isHorizontal() && canAttachTo(level, pos, clickedFace)) {
            return this.defaultBlockState().setValue(FACING, clickedFace);
        }
        return null;
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor level,
            BlockPos pos,
            BlockPos neighborPos) {
        if (direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
        }
        return state;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void spawnDefaultTorchParticles(BlockState state, net.minecraft.world.level.Level level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.7D;
        double z = pos.getZ() + 0.5D;
        if (facing.getAxis().isHorizontal()) {
            Direction attach = facing.getOpposite();
            x += attach.getStepX() * 0.3D;
            y += 0.22D;
            z += attach.getStepZ() * 0.3D;
        } else if (facing == Direction.UP) {
            y -= 0.1D;
        } else {
            y += 0.15D;
        }
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
        level.addParticle(this.flameParticle, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void animateTick(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, RandomSource random) {
        spawnDefaultTorchParticles(state, level, pos);
    }
}
