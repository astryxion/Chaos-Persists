package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.block.IslandBlock;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class Island extends Animal {
    private float dir = 0.0f;
    private float speed = 0.1f;
    private int radius = 5;
    private int depth = 3;
    private int timer = 73;
    private int just_spawned = 1;
    private int ticker = 0;
    private int once = 1;
    private double myX;
    private double myY;
    private double myZ;
    private int dirchange;

    public Island(EntityType<? extends Island> type, Level level) {
        super(type, level);
        this.ticker = level.getRandom().nextInt(50);
        this.dirchange = level.getRandom().nextInt(2500);
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes().add(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH, 20.0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(0.0, 0.0, 0.0);
        if (this.level().isClientSide) {
            return;
        }
        if (this.once != 0) {
            this.myX = this.getX();
            this.myY = this.getY();
            this.myZ = this.getZ();
            this.once = 0;
        }
        if (this.just_spawned != 0) {
            this.dir = this.random.nextFloat() * 3.1415927f;
            if (this.random.nextInt(2) == 1) {
                this.dir *= -1.0f;
            }
            if (this.random.nextInt(40) != 1) {
                this.radius = 3 + this.random.nextInt(4);
                this.depth = 2 + this.random.nextInt(3);
                this.speed = this.random.nextFloat() / 50.0f * (float) ChaosPersists.IslandSpeedFactor;
            } else {
                this.radius = 6 + this.random.nextInt(5);
                this.depth = 3 + this.random.nextInt(4);
                this.speed = this.random.nextFloat() / 200.0f * (float) ChaosPersists.IslandSpeedFactor;
            }
            this.create_island();
            this.ticker = this.random.nextInt(50);
            this.dirchange = this.random.nextInt(10000);
        }
        ++this.ticker;
        if (this.ticker >= this.timer) {
            this.update_island();
            this.ticker = 0;
        }
        --this.dirchange;
        if (this.dirchange <= 0) {
            this.dirchange = this.random.nextInt(5000);
            this.dir = this.random.nextFloat() * 3.1415927f;
            if (this.random.nextInt(2) == 1) {
                this.dir *= -1.0f;
            }
        }
        this.just_spawned = 0;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("JustSpawned", this.just_spawned);
        tag.putInt("Idepth", this.depth);
        tag.putInt("Iradius", this.radius);
        tag.putFloat("Ispeed", this.speed);
        tag.putFloat("Idir", this.dir);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.just_spawned = tag.getInt("JustSpawned");
        this.depth = tag.getInt("Idepth");
        this.radius = tag.getInt("Iradius");
        this.speed = tag.getFloat("Ispeed");
        this.dir = tag.getFloat("Idir");
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob mate) {
        return null;
    }

    private void create_island() {
        double deltadir = 0.10471975333333333;
        double deltamag = 0.3499999940395355;
        int ixlast = 0;
        int izlast = 0;
        int xoff = 0;
        int zoff = 0;
        for (int i = 0; i < this.depth; ++i) {
            izlast = 0;
            ixlast = 0;
            for (double curdir = -3.1415926; curdir < 3.1415926; curdir += deltadir) {
                double tradius = this.radius;
                for (double h = 0.75; h < (tradius /= (double) (i + 1)); h += deltamag) {
                    int ix = (int) (this.getX() + Math.cos(curdir + (double) this.dir) * h);
                    int iz = (int) (this.getZ() + Math.sin(curdir + (double) this.dir) * h);
                    if (ix == ixlast && iz == izlast) {
                        continue;
                    }
                    ixlast = ix;
                    izlast = iz;
                    if (i == 0) {
                        Block bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() - i + 1, iz)).getBlock();
                        if (bid == Blocks.AIR) {
                            if (this.random.nextInt(5000) == 1) {
                                this.level()
                                        .setBlock(
                                                new BlockPos(ix, (int) this.getY() - i + 1, iz),
                                                Blocks.LAVA.defaultBlockState(),
                                                3);
                                continue;
                            }
                            this.FastSetBlock(ix, (int) this.getY() - i + 1, iz, Blocks.MYCELIUM);
                            if (this.random.nextInt(20) != 1
                                    || this.level()
                                                    .getBlockState(new BlockPos(ix, (int) this.getY() - i + 2, iz))
                                                    .getBlock()
                                            != Blocks.AIR) {
                                continue;
                            }
                            if (this.random.nextInt(2) == 1) {
                                this.level()
                                        .setBlock(
                                                new BlockPos(ix, (int) this.getY() - i + 2, iz),
                                                Blocks.BROWN_MUSHROOM.defaultBlockState(),
                                                3);
                                continue;
                            }
                            this.level()
                                    .setBlock(
                                            new BlockPos(ix, (int) this.getY() - i + 2, iz),
                                            Blocks.RED_MUSHROOM.defaultBlockState(),
                                            3);
                            continue;
                        }
                        if (bid != Blocks.BEDROCK) {
                            continue;
                        }
                        this.discard();
                        return;
                    }
                    if (this.random.nextInt(10) == 1) {
                        this.FastSetBlock(ix, (int) this.getY() - i + 1, iz, Blocks.DIAMOND_ORE);
                        continue;
                    }
                    this.FastSetBlock(ix, (int) this.getY() - i + 1, iz, Blocks.END_STONE);
                }
            }
        }
        this.ensureControllerAirPocket();
    }

    private void ensureControllerAirPocket() {
        int xoff = 0;
        int zoff = 0;
        if (this.getX() < 0.0) {
            xoff = -1;
        }
        if (this.getZ() < 0.0) {
            zoff = -1;
        }
        int cx = (int) this.getX() + xoff;
        int cy = (int) this.getY();
        int cz = (int) this.getZ() + zoff;
        for (int dy = 0; dy <= 3; ++dy) {
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dz = -1; dz <= 1; ++dz) {
                    this.FastSetBlock(cx + dx, cy + dy, cz + dz, Blocks.AIR);
                }
            }
        }
    }

    private void update_island() {
        double deltadir = 0.10471975333333333;
        double deltamag = 0.3499999940395355;
        int ixlast = 0;
        int izlast = 0;
        int xoff = 0;
        int zoff = 0;
        this.myX += (double) this.speed * Math.cos(this.dir);
        this.myZ += (double) this.speed * Math.sin(this.dir);
        int mx = (int) this.myX;
        int mz = (int) this.myZ;
        int px = (int) this.getX();
        int pz = (int) this.getZ();
        if (mx != px || mz != pz) {
            double h;
            int ix;
            int i;
            double curdir;
            int iz;
            Block bid;
            double tradius;
            for (i = 0; i < this.depth; ++i) {
                izlast = 0;
                ixlast = 0;
                for (curdir = -3.3; curdir < 3.3; curdir += deltadir / 2.0) {
                    tradius = this.radius;
                    for (h = 0.75; h < (tradius /= (double) (i + 1)); h += deltamag) {}
                    if ((h -= deltamag) < 0.75) {
                        h = 0.75;
                    }
                    while (h < tradius + deltamag) {
                        ix = (int) (this.getX() + Math.cos(curdir + (double) this.dir) * h);
                        iz = (int) (this.getZ() + Math.sin(curdir + (double) this.dir) * h);
                        if (ix != ixlast || iz != izlast) {
                            ixlast = ix;
                            izlast = iz;
                            if (i == 0) {
                                bid =
                                        this.level()
                                                .getBlockState(new BlockPos(ix, (int) this.getY() + 1 + 1, iz))
                                                .getBlock();
                                if (bid == Blocks.RED_MUSHROOM || bid == Blocks.BROWN_MUSHROOM) {
                                    this.FastSetBlock(ix, (int) this.getY() + 1 + 1, iz, Blocks.AIR);
                                }
                            }
                            this.FastSetBlock(ix, (int) this.getY() - i + 1, iz, Blocks.AIR);
                        }
                        h += deltamag / 2.0;
                    }
                }
            }
            if (this.getX() < 0.0) {
                xoff = -1;
            }
            if (this.getZ() < 0.0) {
                zoff = -1;
            }
            this.level()
                    .setBlock(
                            new BlockPos((int) this.getX() + xoff, (int) this.getY(), (int) this.getZ() + zoff),
                            Blocks.END_STONE.defaultBlockState(),
                            3);
            double npx = (int) this.myX;
            npx = this.myX < 0.0 ? npx - 0.5 : npx + 0.5;
            double npz = (int) this.myZ;
            npz = this.myZ < 0.0 ? npz - 0.5 : npz + 0.5;
            this.setPos(npx, this.getY(), npz);
            for (i = 0; i < this.depth; ++i) {
                izlast = 0;
                ixlast = 0;
                for (curdir = -3.1415926; curdir < 3.1415926; curdir += deltadir) {
                    tradius = this.radius;
                    for (h = 0.75; h < (tradius /= (double) (i + 1)); h += deltamag) {}
                    if ((h -= deltamag * 3.0) < 0.75) {
                        h = 0.75;
                    }
                    while (h < tradius) {
                        ix = (int) (this.getX() + Math.cos(curdir + (double) this.dir) * h);
                        iz = (int) (this.getZ() + Math.sin(curdir + (double) this.dir) * h);
                        if (ix != ixlast || iz != izlast) {
                            ixlast = ix;
                            izlast = iz;
                            if (i == 0) {
                                bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() - i + 1, iz)).getBlock();
                                if (bid == Blocks.AIR) {
                                    if (this.random.nextInt(5000) == 1) {
                                        this.level()
                                                .setBlock(
                                                        new BlockPos(ix, (int) this.getY() - i + 1, iz),
                                                        Blocks.LAVA.defaultBlockState(),
                                                        3);
                                    } else {
                                        this.FastSetBlock(ix, (int) this.getY() - i + 1, iz, Blocks.MYCELIUM);
                                        if (this.random.nextInt(20) == 1
                                                && this.level()
                                                                .getBlockState(
                                                                        new BlockPos(ix, (int) this.getY() - i + 2, iz))
                                                                .getBlock()
                                                        == Blocks.AIR) {
                                            if (this.random.nextInt(2) == 1) {
                                                this.level()
                                                        .setBlock(
                                                                new BlockPos(ix, (int) this.getY() - i + 2, iz),
                                                                Blocks.BROWN_MUSHROOM.defaultBlockState(),
                                                                3);
                                            } else {
                                                this.level()
                                                        .setBlock(
                                                                new BlockPos(ix, (int) this.getY() - i + 2, iz),
                                                                Blocks.RED_MUSHROOM.defaultBlockState(),
                                                                3);
                                            }
                                        }
                                    }
                                } else if (bid == Blocks.BEDROCK) {
                                    this.discard();
                                    return;
                                }
                            } else {
                                bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() - i + 1, iz)).getBlock();
                                if (bid == Blocks.STONE) {
                                    if (!this.level().isClientSide) {
                                        this.level()
                                                .explode(
                                                        this,
                                                        ix,
                                                        this.getY() - (double) i + 1.0,
                                                        iz,
                                                        5.0f,
                                                        Level.ExplosionInteraction.MOB);
                                    }
                                } else if (this.random.nextInt(10) == 1) {
                                    this.FastSetBlock(ix, (int) this.getY() - i + 1, iz, Blocks.DIAMOND_ORE);
                                } else {
                                    this.FastSetBlock(ix, (int) this.getY() - i + 1, iz, Blocks.END_STONE);
                                }
                            }
                        }
                        h += deltamag;
                    }
                }
            }
            this.ensureControllerAirPocket();
        }
        if (this.random.nextInt(2 + 2000 / this.timer) == 1) {
            AABB bb =
                    new AABB(
                            this.getX() - 10.0,
                            this.getY() - 5.0,
                            this.getZ() - 10.0,
                            this.getX() + 10.0,
                            this.getY() + 5.0,
                            this.getZ() + 10.0);
            List<Triffid> triffids = this.level().getEntitiesOfClass(Triffid.class, bb);
            if (triffids.isEmpty()) {
                IslandBlock.spawnCreature(this.level(), "triffid", this.getX(), this.getY() + 2.01, this.getZ());
            }
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        this.spawnAtLocation(new ItemStack(ChaosPersists.MyIslandBlock));
    }

    public void FastSetBlock(int ix, int iy, int iz, Block id) {
        ChaosPersists.setBlockFast(this.level(), ix, iy, iz, id, 0, 3);
    }
}
