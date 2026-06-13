package com.astryxion.chaospersists.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUranium extends Block {

    public BlockUranium() {
        super(AbstractBlock.Properties.of(Material.STONE)
                .strength(5.0f, 5.0f)
                
                .lightLevel(s -> 4)
                .randomTicks());
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
            int which = world.random.nextInt(3);
            if (which == 0) {
                world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
            }
            if (which == 1) {
                world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
            }
            if (which == 2) {
                world.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }
}
