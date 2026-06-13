package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.server.ServerWorld;


public class EntityLunaMoth extends EntityButterfly {
    private BlockPos currentFlightTarget = null;
    public int moth_type = ChaosPersists.ChaosRand.nextInt(4);
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public EntityLunaMoth(EntityType<? extends EntityLunaMoth> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.5f, height=0.5f
    }

    public static net.minecraft.entity.ai.attributes.AttributeModifierMap createAttributes() {
        return EntityButterfly.createAttributes();
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.6, this.getDeltaMovement().z);
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        Block bid;
        int d;
        int j;
        int found = 0;
        for (i = -dy; i <= dy; ++i) {
            for (j = -dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new BlockPos(x + dx, y + i, z + j)).getBlock();
                if ((bid == Blocks.TORCH || bid == ChaosPersists.ExtremeTorch) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new BlockPos(x - dx, y + i, z + j)).getBlock()) != Blocks.TORCH && bid != ChaosPersists.ExtremeTorch || (d = dx * dx + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x - dx;
                this.ty = y + i;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = -dx; i <= dx; ++i) {
            for (j = -dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new BlockPos(x + i, y + dy, z + j)).getBlock();
                if ((bid == Blocks.TORCH || bid == ChaosPersists.ExtremeTorch) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new BlockPos(x + i, y - dy, z + j)).getBlock()) != Blocks.TORCH && bid != ChaosPersists.ExtremeTorch || (d = dy * dy + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y - dy;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = -dx; i <= dx; ++i) {
            for (j = -dy; j <= dy; ++j) {
                bid = this.level.getBlockState(new BlockPos(x + i, y + j, z + dz)).getBlock();
                if ((bid == Blocks.TORCH || bid == ChaosPersists.ExtremeTorch) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new BlockPos(x + i, y + j, z - dz)).getBlock()) != Blocks.TORCH && bid != ChaosPersists.ExtremeTorch || (d = dz * dz + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y + j;
                this.tz = z - dz;
                ++found;
            }
        }
        return found != 0;
    }

    @Override
    protected void customServerAiStep() {
        int keep_trying = 25;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (this.random.nextInt(100) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 4.0f) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                this.currentFlightTarget = new BlockPos((int) this.getX() + this.random.nextInt(10) - this.random.nextInt(10), (int) this.getY() + this.random.nextInt(6) - 2, (int) this.getZ() + this.random.nextInt(10) - this.random.nextInt(10));
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                --keep_trying;
            }
        } else if (this.level.isDay() && this.random.nextInt(10) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 2; i < 15 && !this.scan_it((int) this.getX(), (int) this.getY(), (int) this.getZ(), i, i, i); ++i) {
                if (i < 6) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.currentFlightTarget = new BlockPos(this.tx, this.ty + 1, this.tz);
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.5 - this.getDeltaMovement().x) * 0.10000000149011612;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.68 - this.getDeltaMovement().y) * 0.10000000149011612;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.5 - this.getDeltaMovement().z) * 0.10000000149011612;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.zza = 0.75f;
        this.yRot += var8;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        Block bid = level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())).getBlock();
        if (bid != Blocks.AIR) {
            return false;
        }
        if (level instanceof World && ((World)level).isDay()) {
            return false;
        }
        if (level instanceof ServerWorld) {
            ServerWorld dim = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(4));
            if (dim != null && level == dim) {
                return true;
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }
}
