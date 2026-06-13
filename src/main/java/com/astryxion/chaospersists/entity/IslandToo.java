/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.IslandToo
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Trees
 *  net.minecraft.block.Block
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.Trees;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class IslandToo
extends AnimalEntity {
    private int dir = 0;
    private float speed = 0.1f;
    private int width = 5;
    private int depth = 3;
    private int length = 10;
    private int timer = 42;
    private int just_spawned = 1;
    private int ticker = 0;
    private int once = 1;
    private double myX;
    private double myY;
    private double myZ;
    private int dirchange = 0;
    private int blocktype = 0;

    public IslandToo(EntityType<? extends IslandToo> type, World par1World) {
        super(type, par1World);
        this.ticker = par1World.random.nextInt(50);
        this.dirchange = this.level.random.nextInt(5000);
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
            this.dir = this.level.random.nextInt(4);
            if (this.level.random.nextInt(40) != 1) {
                this.length = this.width = 1 + this.level.random.nextInt(5 * ChaosPersists.IslandSizeFactor);
                this.depth = 1 + this.level.random.nextInt(4);
                this.speed = this.level.random.nextFloat() / 40.0f * (float)ChaosPersists.IslandSpeedFactor;
                if (this.length * this.width * this.depth <= 64) {
                    this.speed *= 2.0f;
                }
                if (this.length * this.width * this.depth <= 32) {
                    this.speed *= 2.0f;
                }
            } else {
                this.length = this.width = 5 + this.level.random.nextInt(8 * ChaosPersists.IslandSizeFactor);
                this.depth = 3 + this.level.random.nextInt(6);
                this.speed = this.level.random.nextFloat() / 150.0f * (float)ChaosPersists.IslandSpeedFactor;
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
            this.dir = this.level.random.nextInt(4);
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
        this.width = par1CompoundNBT.getInt("Iwidth");
        this.depth = par1CompoundNBT.getInt("Idepth");
        this.length = par1CompoundNBT.getInt("Ilength");
        this.speed = par1CompoundNBT.getFloat("Ispeed");
        this.dir = par1CompoundNBT.getInt("Idir");
        this.blocktype = par1CompoundNBT.getInt("Iblocktype");
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("JustSpawned", this.just_spawned);
        par1CompoundNBT.putInt("Iwidth", this.width);
        par1CompoundNBT.putInt("Idepth", this.depth);
        par1CompoundNBT.putInt("Ilength", this.length);
        par1CompoundNBT.putFloat("Ispeed", this.speed);
        par1CompoundNBT.putInt("Idir", this.dir);
        par1CompoundNBT.putInt("Iblocktype", this.blocktype);
    }

    @Override
    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity entityageable) {
        return null;
    }

    @Override
    public boolean hurt(net.minecraft.util.DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        double newX;
        double newZ;
        int ix = (int)this.getX();
        int iz = (int)this.getZ();
        if (ix < 0) {
            newX = ix - 0.5;
        } else {
            newX = ix + 0.5;
        }
        if (iz < 0) {
            newZ = iz - 0.5;
        } else {
            newZ = iz + 0.5;
        }
        this.setPos(newX, this.getY(), newZ);
        super.hurt(par1DamageSource, par2);
        return ret;
    }

    private void create_island() {
        int xoff = 0;
        int zoff = 0;
        if (this.getX() < 0.0) {
            xoff = 1;
        }
        if (this.getZ() < 0.0) {
            zoff = 1;
        }
        for (int k = 0; k <= this.depth; ++k) {
            int il = this.length / (this.depth - k + 1);
            if (il < 1) {
                il = 1;
            }
            for (int i = - il; i <= il; ++i) {
                for (int j = - il; j <= il; ++j) {
                    int ix = (int)this.getX() + j - xoff;
                    int iz = (int)this.getZ() + i - zoff;
                    if (k == this.depth) {
                        Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + k, iz)).getBlock();
                        if (bid == Blocks.AIR) {
                            if (this.level.random.nextInt(5000) == 1) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + k, iz), Blocks.WATER.defaultBlockState(), 3);
                                continue;
                            }
                            this.FastSetBlock(ix, (int)this.getY() + k, iz, (Block)Blocks.GRASS_BLOCK);
                            if (this.level.random.nextInt(30) == 1) {
                                if (this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + k + 1, iz)).getBlock() != Blocks.AIR) continue;
                                if (this.level.random.nextInt(2) == 1) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + k + 1, iz), ChaosPersists.MyFlowerPinkBlock.defaultBlockState(), 3);
                                    continue;
                                }
                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + k + 1, iz), ChaosPersists.MyFlowerBlueBlock.defaultBlockState(), 3);
                                continue;
                            }
                            if (this.level.random.nextInt(100) != 1 || this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + k + 1, iz)).getBlock() != Blocks.AIR) continue;
                            ChaosPersists.chaospersistsTrees.SmallTree(this.level, ix, (int)this.getY() + k + 1, iz);
                            continue;
                        }
                        if (bid != Blocks.BEDROCK) continue;
                        this.remove();
                        return;
                    }
                    this.mySetBlock(ix, (int)this.getY() + k, iz);
                }
            }
        }
        this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() - xoff, (int)this.getY(), (int)this.getZ() - zoff), Blocks.AIR.defaultBlockState(), 3);
    }

    private void mySetBlock(int ix, int iy, int iz) {
        Block bid = Blocks.STONE;
        if (this.blocktype == 0) {
            this.blocktype = 1 + this.level.random.nextInt(8);
        }
        if (this.blocktype == 1 && this.level.random.nextInt(5) == 1) {
            bid = Blocks.COAL_ORE;
        }
        if (this.blocktype == 2 && this.level.random.nextInt(10) == 1) {
            bid = Blocks.IRON_ORE;
        }
        if (this.blocktype == 3 && this.level.random.nextInt(20) == 1) {
            bid = Blocks.EMERALD_ORE;
        }
        if (this.blocktype == 4 && this.level.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreTitaniumBlock;
        }
        if (this.blocktype == 5 && this.level.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreUraniumBlock;
        }
        if (this.blocktype == 6 && this.level.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreRubyBlock;
        }
        if (this.blocktype == 7 && this.level.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreAmethystBlock;
        }
        if (this.blocktype == 8 && this.level.random.nextInt(20) == 1) {
            bid = Blocks.GOLD_ORE;
        }
        if (bid == Blocks.STONE) {
            if (this.level.random.nextInt(3000) == 1) {
                bid = ChaosPersists.MyEnderPearlBlock;
            }
            if (this.level.random.nextInt(3000) == 2) {
                bid = ChaosPersists.MyEyeOfEnderBlock;
            }
            if (this.level.random.nextInt(3000) == 3) {
                bid = ChaosPersists.MyBlockAmethystBlock;
            }
            if (this.level.random.nextInt(3000) == 4) {
                bid = ChaosPersists.MyBlockRubyBlock;
            }
            if (this.level.random.nextInt(3000) == 5) {
                bid = ChaosPersists.MyBlockUraniumBlock;
            }
            if (this.level.random.nextInt(3000) == 6) {
                bid = ChaosPersists.MyBlockTitaniumBlock;
            }
            if (this.level.random.nextInt(3000) == 7) {
                bid = Blocks.GOLD_BLOCK;
            }
            if (this.level.random.nextInt(3000) == 8) {
                bid = Blocks.DIAMOND_BLOCK;
            }
        }
        this.FastSetBlock(ix, iy, iz, bid);
    }

    private void update_island() {
        int xoff = 0;
        int zoff = 0;
        if (this.dir == 0) {
            this.myZ -= (double)this.speed;
        } else if (this.dir == 1) {
            this.myZ += (double)this.speed;
        } else {
            this.myX = this.dir == 2 ? (this.myX += (double)this.speed) : (this.myX -= (double)this.speed);
        }
        int ke = 0;
        int ks = 0;
        int je = 0;
        int js = 0;
        int mx = (int)this.myX;
        int mz = (int)this.myZ;
        int px = (int)this.getX();
        int pz = (int)this.getZ();
        if (mx != px || mz != pz) {
            int k;
            int il;
            int iz;
            int j;
            int ix;
            Block bid;
            int i;
            if (this.dir == 0) {
                js = 1;
                je = 1;
                ks = -1;
                ke = 1;
            } else if (this.dir == 1) {
                js = -1;
                je = -1;
                ks = -1;
                ke = 1;
            } else if (this.dir == 2) {
                js = -1;
                je = 1;
                ks = -1;
                ke = -1;
            } else {
                js = -1;
                je = 1;
                ks = 1;
                ke = 1;
            }
            if (this.getX() < 0.0) {
                xoff = 1;
            }
            if (this.getZ() < 0.0) {
                zoff = 1;
            }
            for (i = 0; i <= this.depth; ++i) {
                il = this.length / (this.depth - i + 1);
                if (il < 1) {
                    il = 1;
                }
                for (j = js * il; j <= je * il; ++j) {
                    for (k = ks * il; k <= ke * il; ++k) {
                        ix = (int)this.getX() + k - xoff;
                        iz = (int)this.getZ() + j - zoff;
                        if (i == this.depth) {
                            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 1, iz)).getBlock();
                            if (bid == ChaosPersists.MyFlowerPinkBlock || bid == ChaosPersists.MyFlowerBlueBlock || bid == ChaosPersists.MyFlowerBlackBlock || bid == ChaosPersists.MyFlowerScaryBlock) {
                                this.FastSetBlock(ix, (int)this.getY() + i + 1, iz, Blocks.AIR);
                            }
                            if (bid == Blocks.WATER || bid == Blocks.WATER) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i, iz), Blocks.AIR.defaultBlockState(), 3);
                            }
                            if (bid == ChaosPersists.MySkyTreeLog) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 1, iz), Blocks.AIR.defaultBlockState(), 3);
                                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 2, iz)).getBlock();
                                if (bid == ChaosPersists.MySkyTreeLog) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 2, iz), Blocks.AIR.defaultBlockState(), 3);
                                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 3, iz)).getBlock();
                                    if (bid == ChaosPersists.MySkyTreeLog) {
                                        this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 3, iz), Blocks.AIR.defaultBlockState(), 3);
                                    }
                                }
                            }
                            if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i, iz)).getBlock()) == Blocks.WATER || bid == Blocks.WATER) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i, iz), Blocks.AIR.defaultBlockState(), 3);
                            }
                        }
                        this.FastSetBlock(ix, (int)this.getY() + i, iz, Blocks.AIR);
                    }
                }
            }
            this.mySetBlock((int)this.getX() - xoff, (int)this.getY(), (int)this.getZ() - zoff);
            double newX = this.myX < 0.0 ? (int)this.myX - 0.5 : (int)this.myX + 0.5;
            double newZ = this.myZ < 0.0 ? (int)this.myZ - 0.5 : (int)this.myZ + 0.5;
            this.setPos(newX, this.getY(), newZ);
            if (this.dir == 0) {
                js = -1;
                je = -1;
                ks = -1;
                ke = 1;
            } else if (this.dir == 1) {
                js = 1;
                je = 1;
                ks = -1;
                ke = 1;
            } else if (this.dir == 2) {
                js = -1;
                je = 1;
                ks = 1;
                ke = 1;
            } else {
                js = -1;
                je = 1;
                ks = -1;
                ke = -1;
            }
            zoff = 0;
            xoff = 0;
            if (this.getX() < 0.0) {
                xoff = 1;
            }
            if (this.getZ() < 0.0) {
                zoff = 1;
            }
            this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() - xoff, (int)this.getY(), (int)this.getZ() - zoff), Blocks.AIR.defaultBlockState(), 3);
            for (i = 0; i <= this.depth; ++i) {
                il = this.length / (this.depth - i + 1);
                if (il < 1) {
                    il = 1;
                }
                for (j = js * il; j <= je * il; ++j) {
                    for (k = ks * il; k <= ke * il; ++k) {
                        ix = (int)this.getX() + k - xoff;
                        iz = (int)this.getZ() + j - zoff;
                        if (i == this.depth) {
                            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i, iz)).getBlock();
                            if (bid == Blocks.AIR) {
                                if (this.level.random.nextInt(5000) == 1) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i, iz), Blocks.WATER.defaultBlockState(), 3);
                                    continue;
                                }
                                this.FastSetBlock(ix, (int)this.getY() + i, iz, (Block)Blocks.GRASS_BLOCK);
                                if (this.level.random.nextInt(30) == 1) {
                                    if (this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 1, iz)).getBlock() != Blocks.AIR) continue;
                                    if (this.level.random.nextInt(2) == 1) {
                                        this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 1, iz), ChaosPersists.MyFlowerPinkBlock.defaultBlockState(), 3);
                                        continue;
                                    }
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 1, iz), ChaosPersists.MyFlowerBlueBlock.defaultBlockState(), 3);
                                    continue;
                                }
                                if (this.level.random.nextInt(100) != 1 || this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i + 1, iz)).getBlock() != Blocks.AIR) continue;
                                ChaosPersists.chaospersistsTrees.SmallTree(this.level, ix, (int)this.getY() + i + 1, iz);
                                continue;
                            }
                            if (bid != Blocks.BEDROCK) continue;
                            this.remove();
                            return;
                        }
                        bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(ix, (int)this.getY() + i, iz)).getBlock();
                        if (bid == Blocks.END_STONE) {
                            if (this.level.isClientSide) continue;
                            this.level.explode((Entity)this, (double)ix, this.getY() + (double)i, (double)iz, 5.0f, true, net.minecraft.world.Explosion.Mode.BREAK);
                            continue;
                        }
                        this.mySetBlock(ix, (int)this.getY() + i, iz);
                    }
                }
            }
            this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() - xoff, (int)this.getY(), (int)this.getZ() - zoff), Blocks.AIR.defaultBlockState(), 3);
        }
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

