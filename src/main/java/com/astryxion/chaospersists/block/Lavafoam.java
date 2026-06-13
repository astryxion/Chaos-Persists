package com.astryxion.chaospersists.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Lavafoam extends Block {

    public Lavafoam() {
        super(AbstractBlock.Properties.of(Material.STONE)
                .strength(5.0f, 5.0f)
                
                .randomTicks()
                .friction(1.1f));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (world.random.nextInt(20) == 0) {
            sparkle(world, pos, rand);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void sparkle(World world, BlockPos pos, Random rand) {
        double offset = 0.0625;
        for (int i = 0; i < 6; ++i) {
            double x = (double) pos.getX() + rand.nextFloat();
            double y = (double) pos.getY() + rand.nextFloat();
            double z = (double) pos.getZ() + rand.nextFloat();
            BlockState up = world.getBlockState(pos.above());
            if (i == 0 && !up.isSolidRender(world, pos.above())) {
                y = (double) (pos.getY() + 1) + offset;
            }
            BlockState down = world.getBlockState(pos.below());
            if (i == 1 && !down.isSolidRender(world, pos.below())) {
                y = (double) pos.getY() - offset;
            }
            BlockState south = world.getBlockState(pos.south());
            if (i == 2 && !south.isSolidRender(world, pos.south())) {
                z = (double) (pos.getZ() + 1) + offset;
            }
            BlockState north = world.getBlockState(pos.north());
            if (i == 3 && !north.isSolidRender(world, pos.north())) {
                z = (double) pos.getZ() - offset;
            }
            BlockState east = world.getBlockState(pos.east());
            if (i == 4 && !east.isSolidRender(world, pos.east())) {
                x = (double) (pos.getX() + 1) + offset;
            }
            BlockState west = world.getBlockState(pos.west());
            if (i == 5 && !west.isSolidRender(world, pos.west())) {
                x = (double) pos.getX() - offset;
            }
            if (x >= (double) pos.getX() && x <= (double) (pos.getX() + 1)
                    && y >= 0.0 && y <= (double) (pos.getY() + 1)
                    && z >= (double) pos.getZ() && z <= (double) (pos.getZ() + 1)) {
                continue;
            }
            int which = world.random.nextInt(10);
            if (which == 1) {
                world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
            }
            if (which == 2) {
                world.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        double pi = 3.14159;
        double pi2 = pi / 2.0;
        double pi4 = pi / 4.0;
        int par2 = pos.getX();
        int par4 = pos.getZ();
        double d = Math.atan2(entity.getX() - (double) ((float) par2 + 0.5f), entity.getZ() - (double) ((float) par4 + 0.5f));
        if (d < 0.0) {
            d = pi * 2.0 + d;
        }
        double mx = entity.getDeltaMovement().x;
        double mz = entity.getDeltaMovement().z;
        if (d > pi2 - pi4 && d < pi2 + pi4) {
            mx = 0.44999998807907104;
            mz *= 1.350000023841858;
        } else if (d > pi - pi4 && d < pi + pi4) {
            mz = -0.44999998807907104;
            mx *= 1.350000023841858;
        } else if (d > pi + pi2 - pi4 && d < pi + pi2 + pi4) {
            mx = -0.44999998807907104;
            mz *= 1.350000023841858;
        } else {
            mz = 0.44999998807907104;
            mx *= 1.350000023841858;
        }
        entity.setDeltaMovement(mx, entity.getDeltaMovement().y, mz);
        d = Math.sqrt(mz * mz + mx * mx);
        if (d > 1.0) {
            entity.hurt(DamageSource.FALL, (float) d);
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.IBlockReader world, BlockPos pos, net.minecraft.util.math.shapes.ISelectionContext context) {
        float f = 0.0125f;
        return VoxelShapes.box(f, 0.0D, f, 1.0D - f, 1.0D, 1.0D - f);
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.item.ItemStack stack) {
        super.spawnAfterBreak(state, world, pos, stack);
        if (world.dimension() == World.NETHER) {
            int j1 = 5 + world.random.nextInt(5) + world.random.nextInt(5);
            popExperience(world, pos, j1);
        }
    }
}
