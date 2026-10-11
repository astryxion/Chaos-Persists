package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
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

public class IslandToo extends Animal {
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

    public IslandToo(EntityType<? extends IslandToo> type, Level level) {
        super(type, level);
        this.ticker = level.getRandom().nextInt(50);
        this.dirchange = level.getRandom().nextInt(5000);
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
            this.dir = this.random.nextInt(4);
            if (this.random.nextInt(40) != 1) {
                this.length = this.width = 1 + this.random.nextInt(5 * ChaosPersists.IslandSizeFactor);
                this.depth = 1 + this.random.nextInt(4);
                this.speed = this.random.nextFloat() / 40.0f * (float) ChaosPersists.IslandSpeedFactor;
                if (this.length * this.width * this.depth <= 64) {
                    this.speed *= 2.0f;
                }
                if (this.length * this.width * this.depth <= 32) {
                    this.speed *= 2.0f;
                }
            } else {
                this.length = this.width = 5 + this.random.nextInt(8 * ChaosPersists.IslandSizeFactor);
                this.depth = 3 + this.random.nextInt(6);
                this.speed = this.random.nextFloat() / 150.0f * (float) ChaosPersists.IslandSpeedFactor;
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
            this.dir = this.random.nextInt(4);
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
        tag.putInt("Iwidth", this.width);
        tag.putInt("Idepth", this.depth);
        tag.putInt("Ilength", this.length);
        tag.putFloat("Ispeed", this.speed);
        tag.putInt("Idir", this.dir);
        tag.putInt("Iblocktype", this.blocktype);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.just_spawned = tag.getInt("JustSpawned");
        this.width = tag.getInt("Iwidth");
        this.depth = tag.getInt("Idepth");
        this.length = tag.getInt("Ilength");
        this.speed = tag.getFloat("Ispeed");
        this.dir = tag.getInt("Idir");
        this.blocktype = tag.getInt("Iblocktype");
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob mate) {
        return null;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        double ix = (int) this.getX();
        if (ix < 0.0) {
            ix -= 0.5;
        } else {
            ix += 0.5;
        }
        double iz = (int) this.getZ();
        if (iz < 0.0) {
            iz -= 0.5;
        } else {
            iz += 0.5;
        }
        this.setPos(ix, this.getY(), iz);
        super.hurt(par1DamageSource, par2);
        return false;
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
            for (int i = -il; i <= il; ++i) {
                for (int j = -il; j <= il; ++j) {
                    int bx = (int) this.getX() + j - xoff;
                    int bz = (int) this.getZ() + i - zoff;
                    if (k == this.depth) {
                        Block bid = this.level().getBlockState(new BlockPos(bx, (int) this.getY() + k, bz)).getBlock();
                        if (bid == Blocks.AIR) {
                            if (this.random.nextInt(5000) == 1) {
                                this.level()
                                        .setBlock(
                                                new BlockPos(bx, (int) this.getY() + k, bz),
                                                Blocks.WATER.defaultBlockState(),
                                                3);
                                continue;
                            }
                            this.FastSetBlock(bx, (int) this.getY() + k, bz, Blocks.GRASS_BLOCK);
                            if (this.random.nextInt(30) == 1) {
                                if (this.level()
                                                .getBlockState(new BlockPos(bx, (int) this.getY() + k + 1, bz))
                                                .getBlock()
                                        != Blocks.AIR) {
                                    continue;
                                }
                                if (this.random.nextInt(2) == 1) {
                                    this.level()
                                            .setBlock(
                                                    new BlockPos(bx, (int) this.getY() + k + 1, bz),
                                                    ChaosPersists.MyFlowerPinkBlock.defaultBlockState(),
                                                    3);
                                    continue;
                                }
                                this.level()
                                        .setBlock(
                                                new BlockPos(bx, (int) this.getY() + k + 1, bz),
                                                ChaosPersists.MyFlowerBlueBlock.defaultBlockState(),
                                                3);
                                continue;
                            }
                            if (this.random.nextInt(100) != 1
                                    || this.level()
                                                    .getBlockState(new BlockPos(bx, (int) this.getY() + k + 1, bz))
                                                    .getBlock()
                                            != Blocks.AIR) {
                                continue;
                            }
                            ChaosPersists.chaospersistsTrees.SmallTree(
                                    this.level(), bx, (int) this.getY() + k + 1, bz);
                            continue;
                        }
                        if (bid != Blocks.BEDROCK) {
                            continue;
                        }
                        this.discard();
                        return;
                    }
                    this.mySetBlock(bx, (int) this.getY() + k, bz);
                }
            }
        }
        this.ensureControllerAirPocket();
    }

    private void ensureControllerAirPocket() {
        int xoff = 0;
        int zoff = 0;
        if (this.getX() < 0.0) {
            xoff = 1;
        }
        if (this.getZ() < 0.0) {
            zoff = 1;
        }
        int cx = (int) this.getX() - xoff;
        int cy = (int) this.getY();
        int cz = (int) this.getZ() - zoff;
        for (int dy = 0; dy <= 3; ++dy) {
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dz = -1; dz <= 1; ++dz) {
                    this.FastSetBlock(cx + dx, cy + dy, cz + dz, Blocks.AIR);
                }
            }
        }
    }

    private void mySetBlock(int ix, int iy, int iz) {
        Block bid = Blocks.STONE;
        if (this.blocktype == 0) {
            this.blocktype = 1 + this.random.nextInt(8);
        }
        if (this.blocktype == 1 && this.random.nextInt(5) == 1) {
            bid = Blocks.COAL_ORE;
        }
        if (this.blocktype == 2 && this.random.nextInt(10) == 1) {
            bid = Blocks.IRON_ORE;
        }
        if (this.blocktype == 3 && this.random.nextInt(20) == 1) {
            bid = Blocks.EMERALD_ORE;
        }
        if (this.blocktype == 4 && this.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreTitaniumBlock;
        }
        if (this.blocktype == 5 && this.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreUraniumBlock;
        }
        if (this.blocktype == 6 && this.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreRubyBlock;
        }
        if (this.blocktype == 7 && this.random.nextInt(30) == 1) {
            bid = ChaosPersists.MyOreAmethystBlock;
        }
        if (this.blocktype == 8 && this.random.nextInt(20) == 1) {
            bid = Blocks.GOLD_ORE;
        }
        if (bid == Blocks.STONE) {
            if (this.random.nextInt(3000) == 1) {
                bid = ChaosPersists.MyEnderPearlBlock;
            }
            if (this.random.nextInt(3000) == 2) {
                bid = ChaosPersists.MyEyeOfEnderBlock;
            }
            if (this.random.nextInt(3000) == 3) {
                bid = ChaosPersists.MyBlockAmethystBlock;
            }
            if (this.random.nextInt(3000) == 4) {
                bid = ChaosPersists.MyBlockRubyBlock;
            }
            if (this.random.nextInt(3000) == 5) {
                bid = ChaosPersists.MyBlockUraniumBlock;
            }
            if (this.random.nextInt(3000) == 6) {
                bid = ChaosPersists.MyBlockTitaniumBlock;
            }
            if (this.random.nextInt(3000) == 7) {
                bid = Blocks.GOLD_BLOCK;
            }
            if (this.random.nextInt(3000) == 8) {
                bid = Blocks.DIAMOND_BLOCK;
            }
        }
        this.FastSetBlock(ix, iy, iz, bid);
    }

    private void update_island() {
        int xoff = 0;
        int zoff = 0;
        if (this.dir == 0) {
            this.myZ -= (double) this.speed;
        } else if (this.dir == 1) {
            this.myZ += (double) this.speed;
        } else if (this.dir == 2) {
            this.myX += (double) this.speed;
        } else {
            this.myX -= (double) this.speed;
        }
        int ke = 0;
        int ks = 0;
        int je = 0;
        int js = 0;
        int mx = (int) this.myX;
        int mz = (int) this.myZ;
        int px = (int) this.getX();
        int pz = (int) this.getZ();
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
                        ix = (int) this.getX() + k - xoff;
                        iz = (int) this.getZ() + j - zoff;
                        if (i == this.depth) {
                            bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() + i + 1, iz)).getBlock();
                            if (bid == ChaosPersists.MyFlowerPinkBlock
                                    || bid == ChaosPersists.MyFlowerBlueBlock
                                    || bid == ChaosPersists.MyFlowerBlackBlock
                                    || bid == ChaosPersists.MyFlowerScaryBlock) {
                                this.FastSetBlock(ix, (int) this.getY() + i + 1, iz, Blocks.AIR);
                            }
                            if (bid == Blocks.WATER) {
                                this.level()
                                        .setBlock(
                                                new BlockPos(ix, (int) this.getY() + i, iz),
                                                Blocks.AIR.defaultBlockState(),
                                                3);
                            }
                            if (bid == ChaosPersists.MySkyTreeLog) {
                                this.level()
                                        .setBlock(
                                                new BlockPos(ix, (int) this.getY() + i + 1, iz),
                                                Blocks.AIR.defaultBlockState(),
                                                3);
                                bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() + i + 2, iz)).getBlock();
                                if (bid == ChaosPersists.MySkyTreeLog) {
                                    this.level()
                                            .setBlock(
                                                    new BlockPos(ix, (int) this.getY() + i + 2, iz),
                                                    Blocks.AIR.defaultBlockState(),
                                                    3);
                                    bid =
                                            this.level()
                                                    .getBlockState(new BlockPos(ix, (int) this.getY() + i + 3, iz))
                                                    .getBlock();
                                    if (bid == ChaosPersists.MySkyTreeLog) {
                                        this.level()
                                                .setBlock(
                                                        new BlockPos(ix, (int) this.getY() + i + 3, iz),
                                                        Blocks.AIR.defaultBlockState(),
                                                        3);
                                    }
                                }
                            }
                            bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() + i, iz)).getBlock();
                            if (bid == Blocks.WATER) {
                                this.level()
                                        .setBlock(
                                                new BlockPos(ix, (int) this.getY() + i, iz),
                                                Blocks.AIR.defaultBlockState(),
                                                3);
                            }
                        }
                        this.FastSetBlock(ix, (int) this.getY() + i, iz, Blocks.AIR);
                    }
                }
            }
            this.mySetBlock((int) this.getX() - xoff, (int) this.getY(), (int) this.getZ() - zoff);
            double npx = mx;
            npx = this.myX < 0.0 ? npx - 0.5 : npx + 0.5;
            double npz = mz;
            npz = this.myZ < 0.0 ? npz - 0.5 : npz + 0.5;
            this.setPos(npx, this.getY(), npz);
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
            this.level()
                    .setBlock(
                            new BlockPos((int) this.getX() - xoff, (int) this.getY(), (int) this.getZ() - zoff),
                            Blocks.AIR.defaultBlockState(),
                            3);
            for (i = 0; i <= this.depth; ++i) {
                il = this.length / (this.depth - i + 1);
                if (il < 1) {
                    il = 1;
                }
                for (j = js * il; j <= je * il; ++j) {
                    for (k = ks * il; k <= ke * il; ++k) {
                        ix = (int) this.getX() + k - xoff;
                        iz = (int) this.getZ() + j - zoff;
                        if (i == this.depth) {
                            bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() + i, iz)).getBlock();
                            if (bid == Blocks.AIR) {
                                if (this.random.nextInt(5000) == 1) {
                                    this.level()
                                            .setBlock(
                                                    new BlockPos(ix, (int) this.getY() + i, iz),
                                                    Blocks.WATER.defaultBlockState(),
                                                    3);
                                    continue;
                                }
                                this.FastSetBlock(ix, (int) this.getY() + i, iz, Blocks.GRASS_BLOCK);
                                if (this.random.nextInt(30) == 1) {
                                    if (this.level()
                                                    .getBlockState(new BlockPos(ix, (int) this.getY() + i + 1, iz))
                                                    .getBlock()
                                            != Blocks.AIR) {
                                        continue;
                                    }
                                    if (this.random.nextInt(2) == 1) {
                                        this.level()
                                                .setBlock(
                                                        new BlockPos(ix, (int) this.getY() + i + 1, iz),
                                                        ChaosPersists.MyFlowerPinkBlock.defaultBlockState(),
                                                        3);
                                        continue;
                                    }
                                    this.level()
                                            .setBlock(
                                                    new BlockPos(ix, (int) this.getY() + i + 1, iz),
                                                    ChaosPersists.MyFlowerBlueBlock.defaultBlockState(),
                                                    3);
                                    continue;
                                }
                                if (this.random.nextInt(100) != 1
                                        || this.level()
                                                        .getBlockState(new BlockPos(ix, (int) this.getY() + i + 1, iz))
                                                        .getBlock()
                                                != Blocks.AIR) {
                                    continue;
                                }
                                ChaosPersists.chaospersistsTrees.SmallTree(
                                        this.level(), ix, (int) this.getY() + i + 1, iz);
                                continue;
                            }
                            if (bid != Blocks.BEDROCK) {
                                continue;
                            }
                            this.discard();
                            return;
                        }
                        bid = this.level().getBlockState(new BlockPos(ix, (int) this.getY() + i, iz)).getBlock();
                        if (bid == Blocks.END_STONE) {
                            if (this.level().isClientSide) {
                                continue;
                            }
                            this.level()
                                    .explode(
                                            this,
                                            ix,
                                            this.getY() + (double) i,
                                            iz,
                                            5.0f,
                                            Level.ExplosionInteraction.MOB);
                            continue;
                        }
                        this.mySetBlock(ix, (int) this.getY() + i, iz);
                    }
                }
            }
            this.ensureControllerAirPocket();
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
