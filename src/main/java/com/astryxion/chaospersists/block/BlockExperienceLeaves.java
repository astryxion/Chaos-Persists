package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.ExperienceBottleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlockExperienceLeaves extends LeavesBlock implements IForgeShearable, IPlantable {

    public BlockExperienceLeaves() {
        super(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)
                .randomTicks()
                .strength(0.2F)
                .sound(SoundType.GRASS)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PERSISTENT, false)
                .setValue(DISTANCE, 7));
    }

    @Override
    public boolean isShearable(ItemStack item, World world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(PlayerEntity player, ItemStack item, World world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 1;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        int var7 = 2;
        if (world.hasChunksAt(pos.offset(-var7, -var7, -var7), pos.offset(var7, var7, var7))) {
            for (int var12 = -var7; var12 <= var7; ++var12) {
                for (int var13 = -var7; var13 <= 0; ++var13) {
                    for (int var14 = -var7; var14 <= var7; ++var14) {
                        BlockPos off = new BlockPos(par2 + var12, par3 + var13, par4 + var14);
                        int totaldist = Math.abs(var12) + Math.abs(var13) + Math.abs(var14);
                        BlockState offState = world.getBlockState(off);
                        Block bid = offState.getBlock();
                        if (totaldist > 3
                                || bid == Blocks.AIR
                                || !offState.canSustainPlant(world, off, net.minecraft.util.Direction.UP, this)) {
                            continue;
                        }
                        long t = world.getDayTime();
                        if ((t % 24000L) < 14000L || t > 22000L) {
                            return;
                        }
                        if (world.random.nextInt(65) == 1
                                && world.getBlockState(new BlockPos(par2, par3 + 1, par4)).getBlock() == Blocks.AIR) {
                            Block.popResource(world, new BlockPos(par2, par3 + 2, par4), new ItemStack(Items.EXPERIENCE_BOTTLE));
                        }
                        if (world.random.nextInt(75) == 1
                                && world.getBlockState(new BlockPos(par2, par3 - 1, par4)).getBlock() == Blocks.AIR) {
                            ExperienceBottleEntity entity = new ExperienceBottleEntity(world, par2, par3 - 1, par4);
                            entity.setPos(par2, par3 - 1, par4);
                            entity.shoot(
                                    (double) ((world.random.nextFloat() - world.random.nextFloat()) / 2.0f),
                                    -0.10000000149011612,
                                    (double) ((world.random.nextFloat() - world.random.nextFloat()) / 2.0f),
                                    0.4f,
                                    5.0f
                            );
                            world.addFreshEntity(entity);
                        }
                        return;
                    }
                }
            }
            removeLeaves(world, par2, par3, par4);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        long t = world.getDayTime();
        if ((t % 24000L) < 13000L || t > 23000L) {
            return;
        }
        int rate = 0;
        if (t < 14000L) {
            rate = (14000 - (int) t) / 2;
        }
        if (t > 22000L) {
            rate = (int) (t - 22000L) / 2;
        }
        if (world.random.nextInt(200 + rate) == 1
                && world.getBlockState(pos.above()).getBlock() == Blocks.AIR) {
            for (int i = 0; i < 10; ++i) {
                world.addParticle(ParticleTypes.FIREWORK,
                        (double) pos.getX(),
                        (double) pos.getY() + 1.25,
                        (double) pos.getZ(),
                        world.random.nextGaussian(),
                        Math.abs(world.random.nextGaussian()),
                        world.random.nextGaussian());
            }
        }
        if (world.random.nextInt(40 + rate) == 1
                && world.getBlockState(pos.below()).getBlock() == Blocks.AIR) {
            for (int i = 0; i < 4; ++i) {
                world.addParticle(ParticleTypes.FIREWORK,
                        (double) pos.getX(),
                        (double) pos.getY() - 1.25,
                        (double) pos.getZ(),
                        (double) (world.random.nextFloat() - world.random.nextFloat()),
                        (double) (-Math.abs(world.random.nextFloat())),
                        (double) (world.random.nextFloat() - world.random.nextFloat()));
            }
        }
    }

    private void removeLeaves(ServerWorld world, int par2, int par3, int par4) {
        BlockPos pos = new BlockPos(par2, par3, par4);
        BlockState st = world.getBlockState(pos);
        Block.dropResources(st, world, pos);
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return ChaosPersists.FastGraphicsLeaves != 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        return ChaosPersists.FastGraphicsLeaves == 0 || adjacentState.getBlock() != this;
    }

    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType(BlockState state) {
        return RenderType.translucent();
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.PLAINS;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return this.defaultBlockState();
    }
}
