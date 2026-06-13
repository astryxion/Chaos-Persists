package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.registries.ForgeRegistries;
public class Island
extends AnimalEntity {
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

    public Island(EntityType<? extends Island> type, World par1World) {
        super(type, par1World);
        this.ticker = par1World.random.nextInt(50);
        this.dirchange = this.level.random.nextInt(2500);
    }

    public void tick() {
        super.tick();
        this.setDeltaMovement(0.0, 0.0, 0.0);
        if (this.level.isClientSide) {
            return;
        }
        if (this.once != 0) {
            this.myX = this.getX();
            this.myY = this.getY();
            this.myZ = this.getZ();
            this.once = 0;
        }
        if (this.just_spawned != 0) {
            this.dir = this.level.random.nextFloat() * 3.1415927f;
            if (this.level.random.nextInt(2) == 1) {
                this.dir *= -1.0f;
            }
            if (this.level.random.nextInt(40) != 1) {
                this.radius = 3 + this.level.random.nextInt(4);
                this.depth = 2 + this.level.random.nextInt(3);
                this.speed = this.level.random.nextFloat() / 50.0f * (float)ChaosPersists.IslandSpeedFactor;
            } else {
                this.radius = 6 + this.level.random.nextInt(5);
                this.depth = 3 + this.level.random.nextInt(4);
                this.speed = this.level.random.nextFloat() / 200.0f * (float)ChaosPersists.IslandSpeedFactor;
            }
            this.create_island();
            this.ticker = this.level.random.nextInt(50);
            this.dirchange = this.level.random.nextInt(10000);
        }
        ++this.ticker;
        if (this.ticker >= this.timer) {
            this.update_island();
            this.ticker = 0;
        }
        --this.dirchange;
        if (this.dirchange <= 0) {
            this.dirchange = this.level.random.nextInt(5000);
            this.dir = this.level.random.nextFloat() * 3.1415927f;
            if (this.level.random.nextInt(2) == 1) {
                this.dir *= -1.0f;
            }
        }
        this.just_spawned = 0;
    }

    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            super.aiStep();
        }
    }

    @Override
    protected void customServerAiStep() {
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayerEntity) {
        return false;
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.just_spawned = par1CompoundNBT.getInt("JustSpawned");
        this.depth = par1CompoundNBT.getInt("Idepth");
        this.radius = par1CompoundNBT.getInt("Iradius");
        this.speed = par1CompoundNBT.getFloat("Ispeed");
        this.dir = par1CompoundNBT.getFloat("Idir");
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("JustSpawned", this.just_spawned);
        par1CompoundNBT.putInt("Idepth", this.depth);
        par1CompoundNBT.putInt("Iradius", this.radius);
        par1CompoundNBT.putFloat("Ispeed", this.speed);
        par1CompoundNBT.putFloat("Idir", this.dir);
    }

    @Override
    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity entityageable) {
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
                for (double h = 0.75; h < (tradius /= (double)(i + 1)); h += deltamag) {
                    int ix = (int)(this.getX() + Math.cos(curdir + (double)this.dir) * h);
                    int iz = (int)(this.getZ() + Math.sin(curdir + (double)this.dir) * h);
                    if (ix == ixlast && iz == izlast) continue;
                    ixlast = ix;
                    izlast = iz;
                    if (i == 0) {
                        Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 1, iz)).getBlock();
                        if (bid == Blocks.AIR) {
                            if (this.level.random.nextInt(5000) == 1) {
                                this.level.setBlock(new BlockPos(ix, (int)this.getY() - i + 1, iz), Blocks.LAVA.defaultBlockState(), 3);
                                continue;
                            }
                            this.FastSetBlock(ix, (int)this.getY() - i + 1, iz, (Block)Blocks.MYCELIUM);
                            if (this.level.random.nextInt(20) != 1 || this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 2, iz)).getBlock() != Blocks.AIR) continue;
                            if (this.level.random.nextInt(2) == 1) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 2, iz), Blocks.BROWN_MUSHROOM.defaultBlockState(), 3);
                                continue;
                            }
                            this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 2, iz), Blocks.RED_MUSHROOM.defaultBlockState(), 3);
                            continue;
                        }
                        if (bid != Blocks.BEDROCK) continue;
                        this.remove();
                        return;
                    }
                    if (this.level.random.nextInt(10) == 1) {
                        this.FastSetBlock(ix, (int)this.getY() - i + 1, iz, Blocks.DIAMOND_ORE);
                        continue;
                    }
                    this.FastSetBlock(ix, (int)this.getY() - i + 1, iz, Blocks.END_STONE);
                }
            }
        }
        if (this.getX() < 0.0) {
            xoff = -1;
        }
        if (this.getZ() < 0.0) {
            zoff = -1;
        }
        this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + xoff, (int)this.getY(), (int)this.getZ() + zoff), Blocks.AIR.defaultBlockState(), 3);
        this.FastSetBlock((int)this.getX() + xoff, (int)this.getY(), (int)this.getZ() + zoff, Blocks.AIR);
    }

    private void update_island() {
        AxisAlignedBB bb;
        Iterator var2;
        List var5;
        double deltadir = 0.10471975333333333;
        double deltamag = 0.3499999940395355;
        double pi2 = 1.57079632675;
        int ixlast = 0;
        int izlast = 0;
        int xoff = 0;
        int zoff = 0;
        this.myX += (double)this.speed * Math.cos(this.dir);
        this.myZ += (double)this.speed * Math.sin(this.dir);
        int mx = (int)this.myX;
        int mz = (int)this.myZ;
        int px = (int)this.getX();
        int pz = (int)this.getZ();
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
                    for (h = 0.75; h < (tradius /= (double)(i + 1)); h += deltamag) {
                    }
                    if ((h -= deltamag) < 0.75) {
                        h = 0.75;
                    }
                    while (h < tradius + deltamag) {
                        ix = (int)(this.getX() + Math.cos(curdir + (double)this.dir) * h);
                        iz = (int)(this.getZ() + Math.sin(curdir + (double)this.dir) * h);
                        if (ix != ixlast || iz != izlast) {
                            ixlast = ix;
                            izlast = iz;
                            if (i == 0 && ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + 1 + 1, iz)).getBlock()) == Blocks.RED_MUSHROOM || bid == Blocks.BROWN_MUSHROOM)) {
                                this.FastSetBlock(ix, (int)this.getY() + 1 + 1, iz, Blocks.AIR);
                            }
                            this.FastSetBlock(ix, (int)this.getY() - i + 1, iz, Blocks.AIR);
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
            this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + xoff, (int)this.getY(), (int)this.getZ() + zoff), Blocks.END_STONE.defaultBlockState(), 3);
            double newX = this.myX < 0.0 ? (int)this.myX - 0.5 : (int)this.myX + 0.5;
            double newZ = this.myZ < 0.0 ? (int)this.myZ - 0.5 : (int)this.myZ + 0.5;
            this.setPos(newX, this.getY(), newZ);
            for (i = 0; i < this.depth; ++i) {
                izlast = 0;
                ixlast = 0;
                for (curdir = -3.1415926; curdir < 3.1415926; curdir += deltadir) {
                    tradius = this.radius;
                    for (h = 0.75; h < (tradius /= (double)(i + 1)); h += deltamag) {
                    }
                    if ((h -= deltamag * 3.0) < 0.75) {
                        h = 0.75;
                    }
                    while (h < tradius) {
                        ix = (int)(this.getX() + Math.cos(curdir + (double)this.dir) * h);
                        iz = (int)(this.getZ() + Math.sin(curdir + (double)this.dir) * h);
                        if (ix != ixlast || iz != izlast) {
                            ixlast = ix;
                            izlast = iz;
                            if (i == 0) {
                                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 1, iz)).getBlock();
                                if (bid == Blocks.AIR) {
                                    if (this.level.random.nextInt(5000) == 1) {
                                        this.level.setBlock(new BlockPos(ix, (int)this.getY() - i + 1, iz), Blocks.LAVA.defaultBlockState(), 3);
                                    } else {
                                        this.FastSetBlock(ix, (int)this.getY() - i + 1, iz, (Block)Blocks.MYCELIUM);
                                        if (this.level.random.nextInt(20) == 1 && this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 2, iz)).getBlock() == Blocks.AIR) {
                                            if (this.level.random.nextInt(2) == 1) {
                                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 2, iz), Blocks.BROWN_MUSHROOM.defaultBlockState(), 3);
                                            } else {
                                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 2, iz), Blocks.RED_MUSHROOM.defaultBlockState(), 3);
                                            }
                                        }
                                    }
                                } else if (bid == Blocks.BEDROCK) {
                                    this.remove();
                                    return;
                                }
                            } else {
                                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() - i + 1, iz)).getBlock();
                                if (bid == Blocks.STONE) {
                                    if (!this.level.isClientSide) {
                                        this.level.explode((Entity)this, (double)ix, this.getY() - (double)i + 1.0, (double)iz, 5.0f, true, net.minecraft.world.Explosion.Mode.BREAK);
                                    }
                                } else if (this.level.random.nextInt(10) == 1) {
                                    this.FastSetBlock(ix, (int)this.getY() - i + 1, iz, Blocks.DIAMOND_ORE);
                                } else {
                                    this.FastSetBlock(ix, (int)this.getY() - i + 1, iz, Blocks.END_STONE);
                                }
                            }
                        }
                        h += deltamag;
                    }
                }
            }
            xoff = 0;
            if (this.getX() < 0.0) {
                xoff = -1;
            }
            zoff = 0;
            if (this.getZ() < 0.0) {
                zoff = -1;
            }
            this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + xoff, (int)this.getY(), (int)this.getZ() + zoff), Blocks.AIR.defaultBlockState(), 3);
            this.FastSetBlock((int)this.getX() + xoff, (int)this.getY(), (int)this.getZ() + zoff, Blocks.AIR);
        }
        if (this.level.random.nextInt(2 + 2000 / this.timer) == 1 && !(var2 = (var5 = this.level.getEntitiesOfClass(Triffid.class, bb = new AxisAlignedBB((double)(this.getX() - 10.0), (double)(this.getY() - 5.0), (double)(this.getZ() - 10.0), (double)(this.getX() + 10.0), (double)(this.getY() + 5.0), (double)(this.getZ() + 10.0)))).iterator()).hasNext()) {
            CreatureEntity newent = (CreatureEntity)Island.spawnCreature(this.level, "Triffid", this.getX(), this.getY() + 2.01, this.getZ());
        }
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        net.minecraft.util.ResourceLocation rl = new net.minecraft.util.ResourceLocation("chaospersists", par1.toLowerCase().replace(" ", "_"));
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(rl);
        Entity var8 = null;
        if (type != null) {
            var8 = type.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
        }
        return var8;
    }

    protected net.minecraft.item.Item getDropItem() {
        return net.minecraft.item.Item.byBlock(ChaosPersists.MyIslandBlock);
    }

    public void FastSetBlock(int ix, int iy, int iz, Block id) {
        if (id != null && !this.level.isClientSide) {
            this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, iy, iz), id.defaultBlockState(), 3);
        }
    }
}

