package com.astryxion.chaospersists.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

public class Lavafoam extends Block {
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public Lavafoam() {
        super(net.minecraft.world.level.block.Block.Properties.of()
                .strength(5.0f, 5.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .randomTicks()
                .friction(1.1f));
    }

    @Override
    public java.util.List<net.minecraft.world.item.ItemStack> getDrops(
            BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
        return com.astryxion.chaospersists.util.MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public net.minecraft.world.item.ItemStack getCloneItemStack(
            net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new net.minecraft.world.item.ItemStack(this);
    }

    public int tickRate() {
        return 10;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getRandom().nextInt(20) == 0) {
            sparkle(level, pos.getX(), pos.getY(), pos.getZ());
        }
    }

    private void sparkle(Level level, int par2, int par3, int par4) {
        RandomSource var5 = level.getRandom();
        double var6 = 0.0625;
        for (int var8 = 0; var8 < 6; ++var8) {
            double var9 = (float) par2 + var5.nextFloat();
            double var11 = (float) par3 + var5.nextFloat();
            double var13 = (float) par4 + var5.nextFloat();
            BlockPos up = new BlockPos(par2, par3 + 1, par4);
            if (var8 == 0 && !level.getBlockState(up).isCollisionShapeFullBlock(level, up)) {
                var11 = (double) (par3 + 1) + var6;
            }
            BlockPos down = new BlockPos(par2, par3 - 1, par4);
            if (var8 == 1 && !level.getBlockState(down).isCollisionShapeFullBlock(level, down)) {
                var11 = (double) (par3 + 0) - var6;
            }
            BlockPos south = new BlockPos(par2, par3, par4 + 1);
            if (var8 == 2 && !level.getBlockState(south).isCollisionShapeFullBlock(level, south)) {
                var13 = (double) (par4 + 1) + var6;
            }
            BlockPos north = new BlockPos(par2, par3, par4 - 1);
            if (var8 == 3 && !level.getBlockState(north).isCollisionShapeFullBlock(level, north)) {
                var13 = (double) (par4 + 0) - var6;
            }
            BlockPos east = new BlockPos(par2 + 1, par3, par4);
            if (var8 == 4 && !level.getBlockState(east).isCollisionShapeFullBlock(level, east)) {
                var9 = (double) (par2 + 1) + var6;
            }
            BlockPos west = new BlockPos(par2 - 1, par3, par4);
            if (var8 == 5 && !level.getBlockState(west).isCollisionShapeFullBlock(level, west)) {
                var9 = (double) (par2 + 0) - var6;
            }
            if (var9 >= (double) par2 && var9 <= (double) (par2 + 1) && var11 >= 0.0 && var11 <= (double) (par3 + 1) && var13 >= (double) par4
                    && var13 <= (double) (par4 + 1)) {
                continue;
            }
            int which = level.getRandom().nextInt(10);
            if (which == 1) {
                level.addParticle(ParticleTypes.SMOKE, var9, var11, var13, 0.0, 0.0, 0.0);
            }
            if (which == 2) {
                level.addParticle(RED_DUST, var9, var11, var13, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        double pi = 3.14159;
        double pi2 = pi / 2.0;
        double pi4 = pi / 4.0;
        int par2 = pos.getX();
        int par4 = pos.getZ();
        double d = Mth.atan2(entity.getX() - ((float) par2 + 0.5f), entity.getZ() - ((float) par4 + 0.5f));
        if (d < 0.0) {
            d = pi * 2.0 + d;
        }
        Vec3 motion = entity.getDeltaMovement();
        double motionX = motion.x;
        double motionZ = motion.z;
        if (d > pi2 - pi4 && d < pi2 + pi4) {
            motionX = 0.44999998807907104;
            motionZ *= 1.350000023841858;
        } else if (d > pi - pi4 && d < pi + pi4) {
            motionZ = -0.44999998807907104;
            motionX *= 1.350000023841858;
        } else if (d > pi + pi2 - pi4 && d < pi + pi2 + pi4) {
            motionX = -0.44999998807907104;
            motionZ *= 1.350000023841858;
        } else {
            motionZ = 0.44999998807907104;
            motionX *= 1.350000023841858;
        }
        entity.setDeltaMovement(motionX, motion.y, motionZ);
        double speed = Math.sqrt(motionZ * motionZ + motionX * motionX);
        if (speed > 1.0) {
            entity.hurt(level.damageSources().fall(), (float) speed);
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, net.minecraft.world.item.ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
        int j1 = 5 + level.getRandom().nextInt(5) + level.getRandom().nextInt(5);
        if (level.dimension() == Level.NETHER) {
            this.popExperience(level, pos, j1);
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, CollisionContext context) {
        float f = 0.0125f;
        return Shapes.box(f, 0.0, f, 1.0 - f, 1.0, 1.0 - f);
    }
}
