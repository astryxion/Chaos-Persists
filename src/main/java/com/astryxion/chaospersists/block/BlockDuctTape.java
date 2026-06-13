package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.state.IntegerProperty;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.RenderType;

import java.util.Random;

public class BlockDuctTape extends Block {

    public static final IntegerProperty SLICES = IntegerProperty.create("slices", 0, 5);

    public BlockDuctTape() {
        super(AbstractBlock.Properties.of(Material.METAL).randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(SLICES, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SLICES);
    }

    public BlockState getStateFromMeta(int meta) {
        return this.defaultBlockState().setValue(SLICES, Math.min(5, meta & 7));
    }

    public int getMetaFromState(BlockState state) {
        return state.getValue(SLICES);
    }

    private static VoxelShape shapeForSlices(int l) {
        float f = 0.0625f;
        float f1 = (float) (1 + l * 2) / 16.0f;
        float f2 = 0.25f;
        return VoxelShapes.box(f1, 0.0, f, 1.0f - f, f2 - f, 1.0f - f);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, ISelectionContext context) {
        return shapeForSlices(getMetaFromState(state));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shapeForSlices(getMetaFromState(state));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
public RenderType getRenderType(BlockState state) {
        return RenderType.solid();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(SLICES, 0);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        this.eatDuctTapeSlice(world, pos.getX(), pos.getY(), pos.getZ(), player);
        return ActionResultType.SUCCESS;
    }

    @Override
    public void attack(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        this.eatDuctTapeSlice(world, pos.getX(), pos.getY(), pos.getZ(), player);
    }

    private void eatDuctTapeSlice(World world, int par2, int par3, int par4, PlayerEntity player) {
        ItemStack var2;
        if (player != null && !(var2 = player.getMainHandItem()).isEmpty() && var2.getCount() == 1) {
            int cd = var2.getMaxDamage();
            int fd = 0;
            if (cd > 0) {
                if ((cd /= 6) < 1) {
                    cd = 1;
                }
                if ((fd = var2.getDamageValue()) > 0) {
                    fd = fd > cd ? (fd -= cd) : 0;
                    var2.setDamageValue(fd);
                    BlockPos blockPos = new BlockPos(par2, par3, par4);
                    int l = getMetaFromState(world.getBlockState(blockPos)) + 1;
                    if (l >= 6) {
                        world.removeBlock(blockPos, false);
                    } else {
                        world.setBlock(blockPos, this.defaultBlockState().setValue(SLICES, Math.min(5, l)), 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        return canBlockStay(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!canBlockStay(world, pos.getX(), pos.getY(), pos.getZ())) {
            world.removeBlock(pos, false);
        }
    }

    public boolean canBlockStay(IWorldReader world, int par2, int par3, int par4) {
        BlockPos below = new BlockPos(par2, par3 - 1, par4);
        return world.getBlockState(below).isFaceSturdy(world, below, net.minecraft.util.Direction.UP);
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @OnlyIn(Dist.CLIENT)
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyDuctTapeItem);
    }
}
