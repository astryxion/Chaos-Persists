package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class BlockQuinoa extends SugarCaneBlock {

    private int myMaxHeight = 0;

    public BlockQuinoa() {
        this(0);
    }

    protected BlockQuinoa(int par1) {
        super(AbstractBlock.Properties.copy(Blocks.SUGAR_CANE).randomTicks().noCollission());
    }

    @OnlyIn(Dist.CLIENT)
public RenderType getRenderType(BlockState state) {
        return RenderType.cutout();
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        Block bid = world.getBlockState(pos.below()).getBlock();
        if (bid == Blocks.AIR) {
            return false;
        }
        return bid == ChaosPersists.MyQuinoaPlant1 || bid == ChaosPersists.MyQuinoaPlant2
                || bid == ChaosPersists.MyQuinoaPlant3 || bid == ChaosPersists.MyQuinoaPlant4
                || bid == Blocks.GRASS_BLOCK || bid == Blocks.DIRT || bid == Blocks.FARMLAND;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        Block bid;
        int height = 1;
        boolean dontGrow = false;
        if (this != ChaosPersists.MyQuinoaPlant1 && this != ChaosPersists.MyQuinoaPlant2) {
            return;
        }
        int var7 = state.getValue(AGE);
        this.myMaxHeight = var7 >> 8;
        var7 &= 255;
        if (this.myMaxHeight == 0) {
            this.myMaxHeight = 4 + ChaosPersists.ChaosRand.nextInt(4);
        }
        if (world.getBlockState(new BlockPos(par2, par3 + 1, par4)).getBlock() == Blocks.AIR) {
            for (int var6 = 1; var6 < 10 && ((bid = world.getBlockState(new BlockPos(par2, par3 - var6, par4)).getBlock()) == ChaosPersists.MyQuinoaPlant1 || bid == ChaosPersists.MyQuinoaPlant2 || bid == ChaosPersists.MyQuinoaPlant3 || bid == ChaosPersists.MyQuinoaPlant4); ++var6) {
                ++height;
                if (bid != ChaosPersists.MyQuinoaPlant3 && bid != ChaosPersists.MyQuinoaPlant4) {
                    continue;
                }
                dontGrow = true;
            }
            if (dontGrow) {
                this.myMaxHeight = height;
            }
            if (var7 >= 6 - this.myMaxHeight / 3) {
                if (height < this.myMaxHeight) {
                    world.setBlock(new BlockPos(par2, par3 + 1, par4), ChaosPersists.MyQuinoaPlant1.defaultBlockState(), 2);
                    world.setBlock(new BlockPos(par2, par3, par4), ChaosPersists.MyQuinoaPlant2.defaultBlockState(), 2);
                } else {
                    for (int i = 1; i < this.myMaxHeight - 1; ++i) {
                        bid = world.getBlockState(new BlockPos(par2, par3 - i, par4)).getBlock();
                        if (bid == ChaosPersists.MyQuinoaPlant2) {
                            world.setBlock(new BlockPos(par2, par3 - i, par4), ChaosPersists.MyQuinoaPlant3.defaultBlockState(), 2);
                            continue;
                        }
                        if (bid != ChaosPersists.MyQuinoaPlant3) {
                            continue;
                        }
                        world.setBlock(new BlockPos(par2, par3 - i, par4), ChaosPersists.MyQuinoaPlant4.defaultBlockState(), 2);
                    }
                    bid = world.getBlockState(new BlockPos(par2, par3, par4)).getBlock();
                    world.setBlock(new BlockPos(par2, par3, par4), bid.defaultBlockState(), 2);
                }
            } else {
                bid = world.getBlockState(new BlockPos(par2, par3, par4)).getBlock();
                world.setBlock(new BlockPos(par2, par3, par4),
                        bid.defaultBlockState().setValue(AGE, Math.min(15, var7 + 1)), 2);
            }
        }
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyQuinoa);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        LootContext ctx = builder.withParameter(LootParameters.BLOCK_STATE, state).create(LootParameterSets.BLOCK);
        if (this == ChaosPersists.MyQuinoaPlant4) {
            Random rand = ctx.getRandom();
            return Collections.singletonList(new ItemStack(ChaosPersists.MyQuinoa, 1 + rand.nextInt(2)));
        }
        return Collections.emptyList();
    }
}
