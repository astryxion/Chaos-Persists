package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BlockCrystalTorch extends ChaosDirectionalTorchBlock {

    public BlockCrystalTorch() {
        this(0);
    }

    public BlockCrystalTorch(int par1) {
        super(
                net.minecraft.world.level.block.Block.Properties.of()
                        .noCollission()
                        .instabreak()
                        .lightLevel(state -> 14)
                        .sound(SoundType.WOOD),
                ParticleTypes.FLAME);
    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("block", BuiltInRegistries.BLOCK.getKey(this));
    }

    private boolean isCrystalBlock(BlockGetter level, BlockPos pos) {
        Block l = level.getBlockState(pos).getBlock();
        return l == ChaosPersists.CrystalStone
                || l == ChaosPersists.CrystalGrass
                || l == ChaosPersists.MyCrystalTreeLog
                || l == ChaosPersists.CrystalPlanksBlock;
    }

    @Override
    protected boolean canAttachTo(LevelReader level, BlockPos torchPos, Direction attachmentDirection) {
        BlockPos supportPos = torchPos.relative(attachmentDirection.getOpposite());
        if (isCrystalBlock(level, supportPos)) {
            return true;
        }
        return super.canAttachTo(level, torchPos, attachmentDirection);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (level.getRandom().nextInt(4) != 1) {
            return;
        }
        Direction facing = state.getValue(FACING);
        double var7 = pos.getX() + 0.5D;
        double var9 = pos.getY() + 0.7D;
        double var11 = pos.getZ() + 0.5D;
        double var13 = 0.213D;
        double var15 = 0.271D;

        if (facing == Direction.EAST) {
            spawnTorchParticles(level, var7 - var15, var9 + var13, var11, rand);
        } else if (facing == Direction.WEST) {
            spawnTorchParticles(level, var7 + var15, var9 + var13, var11, rand);
        } else if (facing == Direction.NORTH) {
            spawnTorchParticles(level, var7, var9 + var13, var11 - var15, rand);
        } else if (facing == Direction.SOUTH) {
            spawnTorchParticles(level, var7, var9 + var13, var11 + var15, rand);
        } else {
            spawnTorchParticles(level, var7, var9, var11, rand);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnTorchParticles(Level level, double x, double y, double z, RandomSource rand) {
        level.addParticle(
                ParticleTypes.FIREWORK,
                x,
                y,
                z,
                (rand.nextFloat() - rand.nextFloat()) / 8.0D,
                rand.nextFloat() / 8.0D,
                (rand.nextFloat() - rand.nextFloat()) / 8.0D);
        level.addParticle(
                ParticleTypes.FLAME,
                x,
                y,
                z,
                (rand.nextFloat() - rand.nextFloat()) / 60.0D,
                rand.nextFloat() / 10.0D,
                (rand.nextFloat() - rand.nextFloat()) / 60.0D);
    }
}
