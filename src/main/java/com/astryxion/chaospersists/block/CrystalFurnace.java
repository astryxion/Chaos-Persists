package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.tileentity.TileEntityCrystalFurnace;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.inventory.InventoryHelper;

import javax.annotation.Nullable;
import java.util.Random;

public class CrystalFurnace extends Block implements ITileEntityProvider {
    private static boolean keepInventory;

    public static final net.minecraft.state.DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = AbstractFurnaceBlock.LIT;

    public CrystalFurnace(float hardness, float resistance) {
        super(AbstractBlock.Properties.of(Material.STONE)
                .strength(hardness, resistance)
                .randomTicks()
                .lightLevel(state -> state.getValue(LIT) ? 13 : 0)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockState getStateForPlacement(net.minecraft.item.BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) {
            world.setBlock(pos, state.setValue(FACING, placer.getDirection().getOpposite()), 2);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                Hand hand, BlockRayTraceResult hit) {
        if (!world.isClientSide) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof INamedContainerProvider) {
                ((ServerPlayerEntity) player).openMenu((INamedContainerProvider) tileentity);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
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
                world.addParticle(ParticleTypes.SMOKE, x - offset, y, z + randomOffset, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.FLAME, x - offset, y, z + randomOffset, 0.0, 0.0, 0.0);
                break;
            case EAST:
                world.addParticle(ParticleTypes.SMOKE, x + offset, y, z + randomOffset, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.FLAME, x + offset, y, z + randomOffset, 0.0, 0.0, 0.0);
                break;
            case NORTH:
                world.addParticle(ParticleTypes.SMOKE, x + randomOffset, y, z - offset, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.FLAME, x + randomOffset, y, z - offset, 0.0, 0.0, 0.0);
                break;
            case SOUTH:
                world.addParticle(ParticleTypes.SMOKE, x + randomOffset, y, z + offset, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.FLAME, x + randomOffset, y, z + offset, 0.0, 0.0, 0.0);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new TileEntityCrystalFurnace();
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, World world, BlockPos pos) {
        return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
    }

    public static void setKeepInventory(boolean keep) {
        keepInventory = keep;
    }

    public static void setState(boolean active, World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (!(state.getBlock() instanceof CrystalFurnace)) {
            return;
        }

        TileEntity te = world.getBlockEntity(pos);
        keepInventory = true;
        world.setBlock(pos, state.setValue(LIT, active), 3);
        keepInventory = false;
        if (te != null) {
            te.setChanged();
            world.sendBlockUpdated(pos, state, world.getBlockState(pos), 3);
        }
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (!keepInventory && tileEntity instanceof IInventory) {
                InventoryHelper.dropContents(world, pos, (IInventory) tileEntity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }
}
