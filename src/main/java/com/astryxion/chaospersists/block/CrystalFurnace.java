package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.tileentity.TileEntityCrystalFurnace;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
public class CrystalFurnace extends BaseEntityBlock {
    private static boolean keepInventory;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public CrystalFurnace(float hardness, float resistance) {
        super(net.minecraft.world.level.block.Block.Properties.of().strength(hardness, resistance).lightLevel(state -> state.getValue(LIT) ? 13 : 0).noOcclusion().randomTicks());
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @Override
    public java.util.List<ItemStack> getDrops(
            BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
        return com.astryxion.chaospersists.util.MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (placer != null) {
            level.setBlock(pos, state.setValue(FACING, placer.getDirection().getOpposite()), 2);
        }
    }

    @Override
    public InteractionResult use(
            BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof TileEntityCrystalFurnace furnace) {
                serverPlayer.openMenu(furnace);
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (!state.getValue(LIT)) {
            return;
        }
        Direction facing = state.getValue(FACING);
        double x = pos.getX() + 0.5;
        double y = pos.getY() + rand.nextDouble() * 0.6;
        double z = pos.getZ() + 0.5;
        double offset = 0.52;
        double randomOffset = rand.nextDouble() * 0.6 - 0.3;
        switch (facing) {
            case WEST:
                level.addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE, x - offset, y, z + randomOffset, 0, 0, 0);
                level.addParticle(net.minecraft.core.particles.ParticleTypes.FLAME, x - offset, y, z + randomOffset, 0, 0, 0);
                break;
            case EAST:
                level.addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE, x + offset, y, z + randomOffset, 0, 0, 0);
                level.addParticle(net.minecraft.core.particles.ParticleTypes.FLAME, x + offset, y, z + randomOffset, 0, 0, 0);
                break;
            case NORTH:
                level.addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE, x + randomOffset, y, z - offset, 0, 0, 0);
                level.addParticle(net.minecraft.core.particles.ParticleTypes.FLAME, x + randomOffset, y, z - offset, 0, 0, 0);
                break;
            case SOUTH:
                level.addParticle(net.minecraft.core.particles.ParticleTypes.SMOKE, x + randomOffset, y, z + offset, 0, 0, 0);
                level.addParticle(net.minecraft.core.particles.ParticleTypes.FLAME, x + randomOffset, y, z + offset, 0, 0, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityCrystalFurnace(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide
                ? null
                : createTickerHelper(
                        type,
                        ChaosPersists.BLOCK_ENTITY_CRYSTAL_FURNACE.get(),
                        TileEntityCrystalFurnace::serverTick);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof net.minecraft.world.Container container) {
            return AbstractContainerMenu.getRedstoneSignalFromContainer(container);
        }
        return 0;
    }

    public static void setKeepInventory(boolean keep) {
        keepInventory = keep;
    }

    /**
     * Vanilla furnace TE swaps vanilla blocks; crystal furnace must swap only its own lit property.
     */
    public static void setState(boolean active, Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof CrystalFurnace)) {
            return;
        }
        BlockEntity te = level.getBlockEntity(pos);
        keepInventory = true;
        level.setBlock(pos, state.setValue(LIT, active), 3);
        keepInventory = false;
        if (te != null) {
            level.setBlockEntity(te);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (!keepInventory && blockEntity instanceof net.minecraft.world.Container container) {
                Containers.dropContents(level, pos, container);
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
