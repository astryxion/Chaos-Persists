package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class EntityLunaMoth extends EntityButterfly {
    private BlockPos mothFlightTarget = null;
    public int moth_type = ChaosPersists.ChaosRand.nextInt(4);
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public EntityLunaMoth(EntityType<? extends EntityLunaMoth> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityButterfly.createAttributes();
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
    }

    private boolean scanIt(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        Block extremeTorch = ChaosPersists.BLOCK_EXTREME_TORCH.get();
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                Block bid = state.getBlock();
                if ((bid == Blocks.TORCH || bid == Blocks.WALL_TORCH || bid == extremeTorch)
                        && (dx * dx + j * j + i * i) < this.closest) {
                    this.closest = dx * dx + j * j + i * i;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                bid = state.getBlock();
                if ((bid == Blocks.TORCH || bid == Blocks.WALL_TORCH || bid == extremeTorch)
                        && (dx * dx + j * j + i * i) < this.closest) {
                    this.closest = dx * dx + j * j + i * i;
                    this.tx = x - dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                Block bid = state.getBlock();
                if ((bid == Blocks.TORCH || bid == Blocks.WALL_TORCH || bid == extremeTorch)
                        && (dy * dy + j * j + i * i) < this.closest) {
                    this.closest = dy * dy + j * j + i * i;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                bid = state.getBlock();
                if ((bid == Blocks.TORCH || bid == Blocks.WALL_TORCH || bid == extremeTorch)
                        && (dy * dy + j * j + i * i) < this.closest) {
                    this.closest = dy * dy + j * j + i * i;
                    this.tx = x + i;
                    this.ty = y - dy;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                Block bid = state.getBlock();
                if ((bid == Blocks.TORCH || bid == Blocks.WALL_TORCH || bid == extremeTorch)
                        && (dz * dz + j * j + i * i) < this.closest) {
                    this.closest = dz * dz + j * j + i * i;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                bid = state.getBlock();
                if ((bid == Blocks.TORCH || bid == Blocks.WALL_TORCH || bid == extremeTorch)
                        && (dz * dz + j * j + i * i) < this.closest) {
                    this.closest = dz * dz + j * j + i * i;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z - dz;
                    ++found;
                }
            }
        }
        return found != 0;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }
    @Override
    protected void customServerAiStep() {
        int keepTrying = 25;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.mothFlightTarget == null) {
            this.mothFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.getRandom().nextInt(100) == 0
                || this.mothFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 4.0) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (bid.getBlock() != Blocks.AIR && keepTrying != 0) {
                this.mothFlightTarget = new BlockPos(
                        (int) this.getX() + this.getRandom().nextInt(10) - this.getRandom().nextInt(10),
                        (int) this.getY() + this.getRandom().nextInt(6) - 2,
                        (int) this.getZ() + this.getRandom().nextInt(10) - this.getRandom().nextInt(10));
                bid = this.level().getBlockState(this.mothFlightTarget);
                --keepTrying;
            }
        } else if (!this.level().isDay() && this.getRandom().nextInt(10) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 2; i < 15 && !this.scanIt((int) this.getX(), (int) this.getY(), (int) this.getZ(), i, i, i); ++i) {
                if (i < 6) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.mothFlightTarget = new BlockPos(this.tx, this.ty + 1, this.tz);
            }
        }
        MyUtils.steerChaosFlightForward(
                this,
                (double) this.mothFlightTarget.getX() + 0.5,
                (double) this.mothFlightTarget.getY() + 0.1,
                (double) this.mothFlightTarget.getZ() + 0.5,
                1.0);
        MyUtils.applyChaosFlightMovement(this);
    }

    public static boolean checkMothSpawnRules(
            EntityType<EntityLunaMoth> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (MyUtils.getBlockStateForSpawnRules(level, pos).getBlock() != Blocks.AIR) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        if (level.getLevel().dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        if (pos.getY() < 50) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (MyUtils.getBlockStateForSpawnRules(level, this.blockPosition()).getBlock() != Blocks.AIR) {
            return false;
        }
        if (level instanceof Level world && world.isDay()) {
            return false;
        }
        if (level instanceof Level world && world.dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }
}
