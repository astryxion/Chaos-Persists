package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

/**
 * 1.12 {@link net.minecraft.block.TorchBlock} with wall + floor facing; 1.16 splits vanilla into
 * {@link TorchBlock} / {@link WallTorchBlock} — this block keeps the unified 1.12 behavior.
 */
public class BlockCrystalTorch extends Block {
    public static final net.minecraft.state.DirectionProperty FACING = BlockStateProperties.FACING;
    private static final VoxelShape SHAPE_FLOOR = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    private static final VoxelShape SHAPE_CEILING = Block.box(6.0D, 6.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private static final VoxelShape SHAPE_EAST = Block.box(0.0D, 6.0D, 6.0D, 6.0D, 10.0D, 10.0D);
    private static final VoxelShape SHAPE_WEST = Block.box(10.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
    private static final VoxelShape SHAPE_NORTH = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 6.0D);
    private static final VoxelShape SHAPE_SOUTH = Block.box(6.0D, 6.0D, 10.0D, 10.0D, 10.0D, 16.0D);

    public BlockCrystalTorch() {
        this(0);
    }

    public BlockCrystalTorch(int par1) {
        this(0.99F);
    }

    public BlockCrystalTorch(float lightLevel) {
        super(AbstractBlock.Properties.copy(Blocks.TORCH).lightLevel(state -> (int) (lightLevel * 15.0F)));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("block", this.getRegistryName());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch (state.getValue(FACING)) {
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            case NORTH:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_SOUTH;
            case DOWN:
                return SHAPE_CEILING;
            default:
                return SHAPE_FLOOR;
        }
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        Direction attachDir = facing.getOpposite();
        BlockPos supportPos = pos.offset(attachDir.getStepX(), attachDir.getStepY(), attachDir.getStepZ());
        return this.isItSolidOnSide((World) world, supportPos, facing)
                || this.isCrystalBlock((World) world, supportPos);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.random.nextInt(4) != 1) {
            return;
        }
        Direction var6 = stateIn.getValue(FACING);
        double var7 = pos.getX() + 0.5D;
        double var9 = pos.getY() + 0.7D;
        double var11 = pos.getZ() + 0.5D;
        double var13 = 0.213D;
        double var15 = 0.271D;

        if (var6 == Direction.EAST) {
            worldIn.addParticle(ParticleTypes.FIREWORK, var7 - var15, var9 + var13, var11,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D, worldIn.random.nextFloat() / 8.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D);
            worldIn.addParticle(ParticleTypes.FLAME, var7 - var15, var9 + var13, var11,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D, worldIn.random.nextFloat() / 10.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D);
        } else if (var6 == Direction.WEST) {
            worldIn.addParticle(ParticleTypes.FIREWORK, var7 + var15, var9 + var13, var11,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D, worldIn.random.nextFloat() / 8.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D);
            worldIn.addParticle(ParticleTypes.FLAME, var7 + var15, var9 + var13, var11,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D, worldIn.random.nextFloat() / 10.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D);
        } else if (var6 == Direction.NORTH) {
            worldIn.addParticle(ParticleTypes.FIREWORK, var7, var9 + var13, var11 - var15,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D, worldIn.random.nextFloat() / 8.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D);
            worldIn.addParticle(ParticleTypes.FLAME, var7, var9 + var13, var11 - var15,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D, worldIn.random.nextFloat() / 10.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D);
        } else if (var6 == Direction.SOUTH) {
            worldIn.addParticle(ParticleTypes.FIREWORK, var7, var9 + var13, var11 + var15,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D, worldIn.random.nextFloat() / 8.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D);
            worldIn.addParticle(ParticleTypes.FLAME, var7, var9 + var13, var11 + var15,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D, worldIn.random.nextFloat() / 10.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D);
        } else {
            worldIn.addParticle(ParticleTypes.FIREWORK, var7, var9, var11,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D, worldIn.random.nextFloat() / 8.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 8.0D);
            worldIn.addParticle(ParticleTypes.FLAME, var7, var9, var11,
                    (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D, worldIn.random.nextFloat() / 10.0D, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) / 60.0D);
        }
    }

    private boolean isCrystalBlock(World world, BlockPos pos) {
        Block l = world.getBlockState(pos).getBlock();
        return l == ChaosPersists.CrystalStone
                || l == ChaosPersists.CrystalGrass
                || l == ChaosPersists.MyCrystalTreeLog
                || l == ChaosPersists.CrystalPlanksBlock;
    }

    private boolean isItSolidOnSide(World world, BlockPos neighborPos, Direction side) {
        if (this.isCrystalBlock(world, neighborPos)) {
            return true;
        }
        return world.getBlockState(neighborPos).isFaceSturdy(world, neighborPos, side);
    }

    private boolean canPlaceTorchOn(World world, BlockPos floorPos) {
        if (this.isCrystalBlock(world, floorPos)) {
            return true;
        }
        BlockState st = world.getBlockState(floorPos);
        if (st.isFaceSturdy(world, floorPos, Direction.UP)) {
            return true;
        }
        Block l = st.getBlock();
        return st.isFaceSturdy(world, floorPos, Direction.UP);
    }

    private boolean canPlaceBlockAt(World world, BlockPos pos) {
        return this.isItSolidOnSide(world, pos.west(), Direction.EAST)
                || this.isItSolidOnSide(world, pos.east(), Direction.WEST)
                || this.isItSolidOnSide(world, pos.north(), Direction.SOUTH)
                || this.isItSolidOnSide(world, pos.south(), Direction.NORTH)
                || this.canPlaceTorchOn(world, pos.below());
    }

    /**
     * 1.7.10 {@code onBlockPlaced}: allow wall / floor attachment to crystal blocks and normal solids.
     */
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();
        if (!this.canPlaceBlockAt(world, pos)) {
            return null;
        }
        BlockState def = this.defaultBlockState();
        if (clickedFace == Direction.UP && this.canPlaceTorchOn(world, pos.below())) {
            return def.setValue(FACING, Direction.UP);
        }
        if (clickedFace != Direction.DOWN && clickedFace != Direction.UP) {
            Direction attachDir = clickedFace.getOpposite();
            BlockPos support = pos.offset(attachDir.getStepX(), attachDir.getStepY(), attachDir.getStepZ());
            if (world.getBlockState(support).isFaceSturdy(world, support, clickedFace) || this.isCrystalBlock(world, support)) {
                return def.setValue(FACING, clickedFace);
            }
        }
        return def;
    }
}
