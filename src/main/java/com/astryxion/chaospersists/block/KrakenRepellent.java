package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.entity.Kraken;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Vector3f;

public class KrakenRepellent extends ChaosDirectionalTorchBlock {
    private static final int TICK_RATE = 10;
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public KrakenRepellent() {
        this(0);
    }

    public KrakenRepellent(int par1) {
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

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        findSomethingToRepell(level, pos);
        level.scheduleTick(pos, this, TICK_RATE);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide()) {
            level.scheduleTick(pos, this, TICK_RATE);
        }
    }

    @Override
    public void neighborChanged(
            BlockState state,
            Level level,
            BlockPos pos,
            Block block,
            BlockPos fromPos,
            boolean isMoving) {
        if (!level.isClientSide()) {
            level.scheduleTick(pos, this, TICK_RATE);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        Direction facing = state.getValue(FACING);
        double var7 = pos.getX() + 0.5D;
        double var9 = pos.getY() + 0.7D;
        double var11 = pos.getZ() + 0.5D;
        double var13 = 0.413D;
        double var15 = 0.271D;

        if (facing == Direction.EAST) {
            spawnRepellentParticles(level, var7 - var15, var9 + var13, var11);
        } else if (facing == Direction.WEST) {
            spawnRepellentParticles(level, var7 + var15, var9 + var13, var11);
        } else if (facing == Direction.NORTH) {
            spawnRepellentParticles(level, var7, var9 + var13, var11 - var15);
        } else if (facing == Direction.SOUTH) {
            spawnRepellentParticles(level, var7, var9 + var13, var11 + var15);
        } else {
            spawnRepellentParticles(level, var7, var9 + 0.21D, var11);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnRepellentParticles(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
        level.addParticle(RED_DUST, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    private void findSomethingToRepell(Level level, BlockPos pos) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        AABB bb = new AABB(
                (double) par2 - 20.0D,
                (double) par3 - 10.0D,
                (double) par4 - 20.0D,
                (double) par2 + 20.0D,
                (double) par3 + 40.0D,
                (double) par4 + 20.0D);
        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, bb);
        for (LivingEntity var3 : list) {
            if (var3 instanceof Kraken) {
                applyRepel(var3, par2, par3 - 15, par4);
            }
            if (var3 instanceof EntityAnt) {
                applyRepel(var3, par2, par3, par4);
            }
        }
    }

    private static void applyRepel(LivingEntity var3, int par2, int par3, int par4) {
        double d1 = var3.getX() - (double) par2;
        double d2 = var3.getY() - (double) par3;
        double d3 = var3.getZ() - (double) par4;
        double f = d1 * d1 + d2 * d2 + d3 * d3;
        f = Math.sqrt(f);
        f = 20.0D - f;
        if (f > 20.0D) {
            f = 20.0D;
        }
        if (f < 0.0D) {
            f = 0.0D;
        }
        double dir = Mth.atan2(var3.getX() - (double) par2, var3.getZ() - (double) par4);
        f *= 0.4D;
        Vec3 motion = var3.getDeltaMovement();
        var3.setDeltaMovement(motion.x + f * Mth.sin((float) dir), motion.y, motion.z + f * Mth.cos((float) dir));
    }
}
