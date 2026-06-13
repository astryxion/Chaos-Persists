/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.BetterFireball
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Ghost
 *  com.astryxion.chaospersists.GhostSkelly
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.PurplePower
 *  com.astryxion.chaospersists.QueenHead
 *  com.astryxion.chaospersists.TheKing
 *  com.astryxion.chaospersists.TheQueen
 *  com.astryxion.chaospersists.ThunderBolt
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.EnderDragonEntity
 *  net.minecraft.entity.boss.EnderDragonPartEntity
 *  net.minecraft.entity.effect.LightningBoltEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.HorseEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Ghost;
import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.entity.QueenHead;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.item.ThunderBolt;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

/*
 * Exception performing whole class analysis ignored.
 */
public class TheQueen
extends MonsterEntity {
    private static final DataParameter<Integer> ATTACKING = EntityDataManager.defineId(TheQueen.class, DataSerializers.INT);
    private static final DataParameter<Integer> PLAY_NICELY = EntityDataManager.defineId(TheQueen.class, DataSerializers.INT);
    private static final DataParameter<Integer> MOOD = EntityDataManager.defineId(TheQueen.class, DataSerializers.INT);
    private static final DataParameter<Integer> ATTACK_LEVEL = EntityDataManager.defineId(TheQueen.class, DataSerializers.INT);
    private BlockPos currentFlightTarget = null;
    private GenericTargetSorter TargetSorter = null;
    private LivingEntity rt = null;
    private double attdam = 250.0;
    private int hurt_timer = 0;
    private int homex = 0;
    private int homez = 0;
    private int stream_count = 0;
    private int stream_count_l = 0;
    private int ticker = 0;
    private int player_hit_count = 0;
    private int backoff_timer = 0;
    private int guard_mode = 0;
    private volatile int head_found = 0;
    private int wing_sound = 0;
    private int attack_level = 1;
    private LivingEntity ev = null;
    private float evh = 0.0f;
    private int mood = 0;
    private int always_mad = 0;

    public TheQueen(EntityType<? extends TheQueen> type, World par1World) {
        super(type, par1World);
        if (ChaosPersists.PlayNicely == 0) {
        } else {
        }
        this.xpReward = 25000;
        this.noPhysics = true;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        // renderDistanceWeight: EntityType tracking range set in ChaosPersists registration
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static net.minecraft.entity.ai.attributes.AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.TheQueen_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.6200000047683716)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.TheQueen_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        int i = 0;
        super.defineSynchedData();
        this.entityData.define(ATTACKING, i);
        this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
        this.entityData.define(MOOD, this.mood);
        this.entityData.define(ATTACK_LEVEL, this.attack_level);
    }

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY).intValue();
    }

    public int getIsHappy() {
        return this.entityData.get(MOOD).intValue();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double par1) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderVec3D(net.minecraft.util.math.vector.Vector3d par1Vec3) {
        return true;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, par1);
    }

    public int getPower() {
        return this.entityData.get(ATTACK_LEVEL).intValue();
    }

    public void setPower(int par1) {
        this.entityData.set(ATTACK_LEVEL, par1);
    }

    protected float getSoundVolume() {
        return 1.35f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.KING_LIVING;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.KING_HIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.TREX_DEATH;
    }

    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.TheQueen_stats.health;
    }

    protected Item getDropItem() {
        return Blocks.DANDELION.asItem();
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(20) - (double)ChaosPersists.ChaosRand.nextInt(20), this.getY() + 12.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(20) - (double)ChaosPersists.ChaosRand.nextInt(20), new ItemStack(index, par1));
        this.level.addFreshEntity((Entity)var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.MyRoyal, 1);
        this.dropItemRand(ChaosPersists.ThePrinceEgg, 1);
        TheQueen.spawnCreature((World)this.level, (String)"The Princess", (double)this.getX(), (double)(this.getY() + 10.0), (double)this.getZ());
        for (int i = 0; i < 56; ++i) {
            this.dropItemRand(ChaosPersists.MyQueenScale, 1);
            this.dropItemRand(Items.BEEF, 1);
            this.dropItemRand(Items.BONE, 1);
            this.dropItemRand(Items.ROTTEN_FLESH, 1);
        }
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public boolean isHappy() {
        if (this.getIsHappy() == 0) {
            return true;
        }
        return false;
    }

    public void tick() {
        super.tick();
        ++this.wing_sound;
        if (this.wing_sound > 30) {
            if (!this.level.isClientSide) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, this.getSoundSource(), 1.75f, 0.75f);
            }
            this.wing_sound = 0;
        }
        this.noPhysics = true;
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() * 3 / 4)) {
            this.attdam = ChaosPersists.TheQueen_stats.attack * 20;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) {
            this.attdam = ChaosPersists.TheQueen_stats.attack * 100;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 3)) {
            this.attdam = ChaosPersists.TheQueen_stats.attack * 500;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 4)) {
            this.attdam = ChaosPersists.TheQueen_stats.attack * 1000;
        }
        if (this.level.isClientSide && this.getPower() > 800) {
            float f = 7.0f;
            if (this.level.random.nextInt(4) == 1) {
                for (int i = 0; i < 10; ++i) {
                    this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + 14.0, this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)), (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 5.0 + this.getDeltaMovement().x * 3.0, (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 5.0, (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 5.0 + this.getDeltaMovement().z * 3.0);
                }
            }
        }
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        boolean var4;
        if (par1Entity != null && par1Entity instanceof LivingEntity && !this.level.isClientSide) {
            LivingEntity e = (LivingEntity)par1Entity;
            if (!e.removed) {
                if (this.ev == e) {
                    if (this.evh < e.getHealth()) {
                        e.setHealth(this.evh);
                    }
                } else {
                    this.ev = e;
                }
                if (e.getBbWidth() * e.getBbHeight() > 30.0f) {
                    e.setHealth(e.getHealth() * 3.0f / 4.0f);
                    e.hurt(DamageSource.mobAttack((LivingEntity)this), (float)this.attdam);
                }
                this.evh = e.getHealth();
                if (this.evh <= 0.0f) {
                    this.ev.remove();
                }
            } else {
                this.ev = null;
                this.evh = 0.0f;
            }
        }
        if (par1Entity != null && par1Entity instanceof EnderDragonEntity) {
            EnderDragonEntity dr = (EnderDragonEntity)par1Entity;
            DamageSource var21 = null;
            var21 = DamageSource.explosion((Explosion)null);
            dr.hurt(var21, (float)this.attdam);
        }
        if (var4 = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), (float)this.attdam)) {
            double ks = 2.75;
            double inair = 0.2;
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            inair += (double)(this.level.random.nextFloat() * 0.25f);
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 1.5;
            }
            par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return var4;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d((double)this.getX(), (double)(this.getY() + 8.75), (double)this.getZ()), new Vector3d((double)pX, (double)pY, (double)pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    private boolean tooFarFromHome() {
        float d1 = (float)(this.getX() - (double)this.homex);
        float d2 = (float)(this.getZ() - (double)this.homez);
        if ((d1 = (float)Math.sqrt(d1 * d1 + d2 * d2)) > 120.0f) {
            return true;
        }
        return false;
    }

    protected void customServerAiStep() {
        Block bid;
        int k;
        int i;
        int j;
        int xdir = 1;
        int zdir = 1;
        int attrand = 5;
        boolean updown = false;
        int which = 0;
        LivingEntity e = null;
        LivingEntity f = null;
        double rr = 0.0;
        double rhdir = 0.0;
        double rdd = 0.0;
        double pi = 3.1415926545;
        double var1 = 0.0;
        double var3 = 0.0;
        double var5 = 0.0;
        float var7 = 0.0f;
        float var8 = 0.0f;
        MobEntity newent = null;
        double xzoff = 8.0;
        double yoff = 14.0;
        List kinglist = null;
        Iterator var2 = null;
        TheKing var4 = null;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.ev != null) {
            if (this.distanceToSqr((Entity)this.ev) < 2000.0 && !this.ev.removed) {
                if (this.evh < this.ev.getHealth()) {
                    this.ev.setHealth(this.evh);
                } else {
                    this.evh = this.ev.getHealth();
                }
                if (this.evh <= 0.0f) {
                    this.ev.remove();
                }
            } else {
                this.ev = null;
                this.evh = 0.0f;
            }
        }
        if (this.attack_level > 1000) {
            if (this.mood == 1) {
                j = 15;
                if (this.player_hit_count < 10) {
                    j = 45;
                }
                for (i = 0; i < j; ++i) {
                    Entity ppwr = TheQueen.spawnCreature((World)this.level, (String)"chaospersists:purple_power", (double)(this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot))), (double)(this.getY() + yoff), (double)(this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot))));
                    if (ppwr == null) continue;
                    ppwr.setDeltaMovement(this.getDeltaMovement().x * 3.0, ppwr.getDeltaMovement().y, ppwr.getDeltaMovement().z);
                    ppwr.setDeltaMovement(ppwr.getDeltaMovement().x, ppwr.getDeltaMovement().y, this.getDeltaMovement().z * 3.0);
                }
            } else {
                int m;
                if (this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) {
                    block1 : for (m = 0; m < 25; ++m) {
                        i = this.level.random.nextInt(25) - this.level.random.nextInt(25);
                        k = this.level.random.nextInt(25) - this.level.random.nextInt(25);
                        for (j = -20; j < 20; ++j) {
                            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k)).getBlock();
                            if (bid == Blocks.GRASS_BLOCK) {
                                if (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() != Blocks.AIR) continue block1;
                                which = this.level.random.nextInt(8);
                                if (which == 0) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.POPPY.defaultBlockState(), 3);
                                }
                                if (which == 1) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.DANDELION.defaultBlockState(), 3);
                                }
                                if (which == 2) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.MyFlowerBlueBlock.defaultBlockState(), 3);
                                }
                                if (which == 3) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.MyFlowerPinkBlock.defaultBlockState(), 3);
                                }
                                if (which == 4) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerRedBlock.defaultBlockState(), 3);
                                }
                                if (which == 5) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerGreenBlock.defaultBlockState(), 3);
                                }
                                if (which == 6) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerBlueBlock.defaultBlockState(), 3);
                                }
                                if (which != 7) continue block1;
                                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerYellowBlock.defaultBlockState(), 3);
                                continue block1;
                            }
                            if (bid == Blocks.DIRT && this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                                continue block1;
                            }
                            if (bid == Blocks.STONE && this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.DIRT.defaultBlockState(), 3);
                                continue block1;
                            }
                            if (bid == Blocks.SAND && this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR) {
                                if (this.level.random.nextInt(2) == 0) {
                                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.CACTUS.defaultBlockState(), 3);
                                    continue block1;
                                }
                                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k), Blocks.DIRT.defaultBlockState(), 3);
                                continue block1;
                            }
                            if (bid == Blocks.LAVA && this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR) {
                                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k), Blocks.WATER.defaultBlockState(), 3);
                                continue block1;
                            }
                            if (bid == Blocks.AIR && j > 0) continue block1;
                        }
                    }
                }
                for (m = 0; m < 10; ++m) {
                    i = this.level.random.nextInt(15) - this.level.random.nextInt(15);
                    k = this.level.random.nextInt(15) - this.level.random.nextInt(15);
                    j = this.level.random.nextInt(20);
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.AIR) continue;
                    newent = this.level.random.nextInt(2) == 0 ? (MobEntity)TheQueen.spawnCreature((World)this.level, (String)"Butterfly", (double)(this.getX() + (double)i), (double)(this.getY() + (double)j), (double)(this.getZ() + (double)k)) : (MobEntity)TheQueen.spawnCreature((World)this.level, (String)"Bird", (double)(this.getX() + (double)i), (double)(this.getY() + (double)j), (double)(this.getZ() + (double)k));
                }
            }
            this.attack_level = 1;
        }
        if (this.attack_level > 1) {
            --this.attack_level;
        }
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.homex == 0 && this.homez == 0 || this.guard_mode == 0) {
            this.homex = (int)this.getX();
            this.homez = (int)this.getZ();
        }
        if (this.getHealth() > (float)(this.mygetMaxHealth() - 2) && this.level.random.nextInt(500) == 1) {
            this.mood = 0;
        }
        if (this.always_mad != 0) {
            this.mood = 1;
        }
        if (this.mood == 0) {
            this.attack_level += 10;
        }
        ++this.ticker;
        if (this.ticker > 30000) {
            this.ticker = 0;
        }
        if (this.ticker % 60 == 0) {
            this.stream_count = 10;
        }
        if (this.ticker % 70 == 0) {
            this.stream_count_l = 6;
        }
        if (this.ticker % 10 == 0) {
            this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
            this.entityData.set(MOOD, this.mood);
            this.setPower(this.attack_level);
        }
        if (this.backoff_timer > 0) {
            --this.backoff_timer;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) {
            attrand = 3;
        }
        this.noPhysics = true;
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.tooFarFromHome() || this.level.random.nextInt(200) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 9.1f) {
            zdir = this.level.random.nextInt(120);
            xdir = this.level.random.nextInt(120);
            if (this.level.random.nextInt(2) == 0) {
                zdir = - zdir;
            }
            if (this.level.random.nextInt(2) == 0) {
                xdir = - xdir;
            }
            int dist = 0;
            for (i = -5; i <= 5; i += 5) {
                block5 : for (j = -5; j <= 5; j += 5) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(this.homex + j, (int)this.getY(), this.homez + i)).getBlock();
                    if (bid != Blocks.AIR) {
                        for (k = 1; k < 20; ++k) {
                            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(this.homex + j, (int)this.getY() + k, this.homez + i)).getBlock();
                            ++dist;
                            if (bid == Blocks.AIR) continue block5;
                        }
                        continue;
                    }
                    for (k = 1; k < 20; ++k) {
                        bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(this.homex + j, (int)this.getY() - k, this.homez + i)).getBlock();
                        --dist;
                        if (bid != Blocks.AIR) continue block5;
                    }
                }
            }
            if ((int)(this.getY() + (double)(dist = dist / 9 + 2)) > 230) {
                dist = 230 - (int)this.getY();
            }
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos(this.homex + xdir, (int)(this.getY() + (double)dist), this.homez + zdir);
            if (this.mood == 0 && (kinglist = this.level.getEntitiesOfClass(TheKing.class, this.getBoundingBox().inflate(64.0, 32.0, 64.0))) != null) {
                Collections.sort(kinglist, this.TargetSorter);
                var2 = kinglist.iterator();
                if (var2.hasNext()) {
                    var4 = null;
                    var4 = (TheKing)var2.next();
                    this.guard_mode = 0;
                    zdir = this.level.random.nextInt(16);
                    xdir = this.level.random.nextInt(16);
                    if (this.level.random.nextInt(2) == 0) {
                        zdir = - zdir;
                    }
                    if (this.level.random.nextInt(2) == 0) {
                        xdir = - xdir;
                    }
                    this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)var4.getX() + xdir, (int)(var4.getY() + (double)(this.level.random.nextInt(8) - this.level.random.nextInt(8))), (int)var4.getZ() + zdir);
                }
            }
        } else if (this.level.random.nextInt(attrand) == 0) {
            float d1;
            e = this.rt;
            if (ChaosPersists.PlayNicely != 0 || this.isHappy()) {
                e = null;
            }
            if (e != null && (e instanceof TheQueen || e instanceof QueenHead)) {
                this.rt = null;
                e = null;
            }
            if (e != null) {
                d1 = (float)(e.getX() - (double)this.homex);
                float d2 = (float)(e.getZ() - (double)this.homez);
                d1 = (float)Math.sqrt(d1 * d1 + d2 * d2);
                if (e.isAlive() == false || this.level.random.nextInt(450) == 1 || d1 > 128.0f && this.guard_mode == 1) {
                    e = null;
                    this.rt = null;
                }
                if (e != null && !this.MyCanSee(e)) {
                    e = null;
                }
            }
            f = this.findSomethingToAttack();
            if (this.head_found == 0 && this.mood == 1) {
                newent = (MobEntity)TheQueen.spawnCreature((World)this.level, (String)"QueenHead", (double)this.getX(), (double)(this.getY() + 20.0), (double)this.getZ());
            }
            if (e == null) {
                e = f;
            }
            if (e != null) {
                d1 = e.getBbWidth() * e.getBbHeight();
                if (this.attack_level < 1000) {
                    this.attack_level += 15;
                    if (this.getHealth() < (float)(this.mygetMaxHealth() / 2)) {
                        this.attack_level += 15;
                    }
                    if (d1 > 50.0f) {
                        this.attack_level += 15;
                    }
                    if (d1 > 100.0f) {
                        this.attack_level += 15;
                    }
                    if (d1 > 200.0f) {
                        this.attack_level += 25;
                    }
                }
                this.setAttacking(1);
                if (this.backoff_timer == 0) {
                    int dist = (int)(e.getY() + (double)(e.getBbHeight() / 2.0f) + 1.0);
                    if (dist > 230) {
                        dist = 230;
                    }
                    this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), dist, (int)e.getZ());
                    if (this.level.random.nextInt(50) == 1) {
                        this.backoff_timer = 90 + this.level.random.nextInt(90);
                    }
                } else if (this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 9.1f) {
                    zdir = this.level.random.nextInt(20) + 30;
                    xdir = this.level.random.nextInt(20) + 30;
                    if (this.level.random.nextInt(2) == 0) {
                        zdir = - zdir;
                    }
                    if (this.level.random.nextInt(2) == 0) {
                        xdir = - xdir;
                    }
                    int dist = 0;
                    for (i = -5; i <= 5; i += 5) {
                        block9 : for (j = -5; j <= 5; j += 5) {
                            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)e.getX() + j, (int)this.getY(), (int)e.getZ() + i)).getBlock();
                            if (bid != Blocks.AIR) {
                                for (k = 1; k < 20; ++k) {
                                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)e.getX() + j, (int)this.getY() + k, (int)e.getZ() + i)).getBlock();
                                    ++dist;
                                    if (bid == Blocks.AIR) continue block9;
                                }
                                continue;
                            }
                            for (k = 1; k < 20; ++k) {
                                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)e.getX() + j, (int)this.getY() - k, (int)e.getZ() + i)).getBlock();
                                --dist;
                                if (bid != Blocks.AIR) continue block9;
                            }
                        }
                    }
                    if ((int)(this.getY() + (double)(dist = dist / 9 + 2)) > 230) {
                        dist = 230 - (int)this.getY();
                    }
                    this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX() + xdir, (int)(this.getY() + (double)dist), (int)e.getZ() + zdir);
                }
                if (this.distanceToSqr((Entity)e) < 900.0) {
                    if (this.level.random.nextInt(2) == 1) {
                        this.doJumpDamage(this.getX(), this.getY(), this.getZ(), 15.0, (double)(ChaosPersists.TheQueen_stats.attack / 4), 0);
                    }
                    this.doHurtTarget((LivingEntity)e);
                }
                double dx = this.getX() + 20.0 * Math.sin(Math.toRadians(this.yRot));
                double dz = this.getZ() - 20.0 * Math.cos(Math.toRadians(this.yRot));
                if (this.level.random.nextInt(3) == 1) {
                    this.doJumpDamage(dx, this.getY() + 10.0, dz, 15.0, (double)(ChaosPersists.TheQueen_stats.attack / 2), 1);
                }
                if (this.getHorizontalDistanceSqToEntity((Entity)e) > 900.0) {
                    which = this.level.random.nextInt(2);
                    if (which == 0) {
                        if (this.stream_count > 0) {
                            this.setAttacking(1);
                            rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                            rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f);
                            rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                            if (rdd > pi) {
                                rdd -= pi * 2.0;
                            }
                            if ((rdd = Math.abs(rdd)) < 0.5) {
                                this.firecanon(e);
                            }
                        }
                    } else if (this.stream_count_l > 0) {
                        this.setAttacking(1);
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f);
                        rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            this.firecanonl(e);
                        }
                    }
                }
            } else {
                this.setAttacking(0);
                this.stream_count = 10;
                this.stream_count_l = 6;
            }
        }
        var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.65 - this.getDeltaMovement().x) * 0.35, (Math.signum(var3) * 0.69999 - this.getDeltaMovement().y) * 0.3, (Math.signum(var5) * 0.65 - this.getDeltaMovement().z) * 0.35);
        var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = 0.75f;
        this.yRot += var8 / 8.0f;
        if (this.level.random.nextInt(32) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(5.0f);
            if (this.player_hit_count < 10) {
                this.heal(50.0f);
            }
        }
        if (this.player_hit_count < 10 && this.getHealth() < 2000.0f) {
            this.heal(2000.0f - this.getHealth());
        }
    }

    private double getHorizontalDistanceSqToEntity(Entity e) {
        double d1 = e.getZ() - this.getZ();
        double d2 = e.getX() - this.getX();
        return d1 * d1 + d2 * d2;
    }

    private void firecanon(LivingEntity e) {
        double yoff = 14.0;
        double xzoff = 32.0;
        BetterFireball bf = null;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        if (this.stream_count > 0) {
            bf = new BetterFireball(this.level, (LivingEntity)this, e.getX() - cx, e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff), e.getZ() - cz);
            bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
            bf.setPos(cx, this.getY() + yoff, cz);
            bf.setReallyBig();
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.TNT_PRIMED, this.getSoundSource(), 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level.addFreshEntity((Entity)bf);
            for (int i = 0; i < 6; ++i) {
                float r1 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
                float r2 = 3.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
                float r3 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
                bf = new BetterFireball(this.level, (LivingEntity)this, e.getX() - cx + (double)r1, e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff) + (double)r2, e.getZ() - cz + (double)r3);
                bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
                bf.setPos(cx, this.getY() + yoff, cz);
                bf.setBig();
                if (this.level.random.nextInt(2) == 1) {
                    bf.setSmall();
                }
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level.addFreshEntity((Entity)bf);
            }
            --this.stream_count;
        }
    }

    private void firecanonl(LivingEntity e) {
        double yoff = 14.0;
        double xzoff = 32.0;
        double var3 = 0.0;
        double var5 = 0.0;
        double var7 = 0.0;
        float var9 = 0.0f;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        if (this.stream_count_l > 0) {
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            for (int i = 0; i < 3; ++i) {
                ThunderBolt lb = new ThunderBolt(this.level, cx, this.getY() + yoff, cz);
                lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
                var3 = e.getX() - lb.getX();
                var5 = e.getY() + 0.25 - lb.getY();
                var7 = e.getZ() - lb.getZ();
                var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
                lb.shoot(var3, var5 + (double)var9, var7, 1.4f, 4.0f);
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 3.0, 3.0, 3.0);
                this.level.addFreshEntity((Entity)lb);
            }
            --this.stream_count_l;
        }
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        float dm = par2;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (dm > 750.0f) {
            dm = 750.0f;
        }
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        this.mood = 1;
        if (par1DamageSource.isExplosion()) {
            float s = this.getHealth();
            if ((s += par2 / 2.0f) > this.getMaxHealth()) {
                s = this.getMaxHealth();
            }
            this.setHealth(s);
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof LivingEntity) {
            if (e instanceof PurplePower) {
                return false;
            }
            float s = e.getBbHeight() * e.getBbWidth();
            if (e instanceof MonsterEntity && s < 3.0f) {
                e.remove();
                return false;
            }
        }
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            this.hurt_timer = 20;
            ret = super.hurt(par1DamageSource, dm);
            if (e != null && e instanceof PlayerEntity) {
                ++this.player_hit_count;
            }
            if (e != null && e instanceof LivingEntity && this.currentFlightTarget != null && !MyUtils.isRoyalty((Entity)e)) {
                this.rt = (LivingEntity)e;
                int dist = (int)e.getY();
                if (dist > 230) {
                    dist = 230;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), dist, (int)e.getZ());
            }
        }
        return ret;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        return true;
    }

    public int getArmorValue() {
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() * 2 / 3)) {
            return ChaosPersists.TheQueen_stats.defense + 2;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) {
            return ChaosPersists.TheQueen_stats.defense + 3;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 3)) {
            return ChaosPersists.TheQueen_stats.defense + 5;
        }
        return ChaosPersists.TheQueen_stats.defense;
    }

    public void onStruckByLightning(LightningBoltEntity par1LightningBoltEntity) {
    }

    public void initCreature() {
    }

    public boolean MyCanSee(LivingEntity e) {
        double xzoff = 10.0;
        int nblks = 20;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        float startx = (float)cx;
        float starty = (float)(this.getY() + 14.0);
        float startz = (float)cz;
        float dx = (float)((e.getX() - (double)startx) / 20.0);
        float dy = (float)((e.getY() + (double)(e.getBbHeight() / 2.0f) - (double)starty) / 20.0);
        float dz = (float)((e.getZ() - (double)startz) / 20.0);
        if ((double)Math.abs(dx) > 1.0) {
            dy /= Math.abs(dx);
            dz /= Math.abs(dx);
            nblks = (int)((float)nblks * Math.abs(dx));
            if (dx > 1.0f) {
                dx = 1.0f;
            }
            if (dx < -1.0f) {
                dx = -1.0f;
            }
        }
        if ((double)Math.abs(dy) > 1.0) {
            dx /= Math.abs(dy);
            dz /= Math.abs(dy);
            nblks = (int)((float)nblks * Math.abs(dy));
            if (dy > 1.0f) {
                dy = 1.0f;
            }
            if (dy < -1.0f) {
                dy = -1.0f;
            }
        }
        if ((double)Math.abs(dz) > 1.0) {
            dy /= Math.abs(dz);
            dx /= Math.abs(dz);
            nblks = (int)((float)nblks * Math.abs(dz));
            if (dz > 1.0f) {
                dz = 1.0f;
            }
            if (dz < -1.0f) {
                dz = -1.0f;
            }
        }
        for (int i = 0; i < nblks; ++i) {
            Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(startx += dx), (int)(starty += dy), (int)(startz += dz))).getBlock();
            if (bid == Blocks.AIR) continue;
            return false;
        }
        return true;
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == this) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (par1Mob instanceof QueenHead) {
            this.head_found = 1;
            return false;
        }
        if (MyUtils.isRoyalty((Entity)par1Mob)) {
            return false;
        }
        float d1 = (float)(par1Mob.getX() - (double)this.homex);
        float d2 = (float)(par1Mob.getZ() - (double)this.homez);
        if ((d1 = (float)Math.sqrt(d1 * d1 + d2 * d2)) > 144.0f) {
            return false;
        }
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof HorseEntity) {
            return true;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof EnderDragonEntity) {
            return true;
        }
        if (MyUtils.isAttackableNonMob((LivingEntity)par1Mob)) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0 || this.isHappy()) {
            this.head_found = 1;
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(80.0, 60.0, 80.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        LivingEntity ret = null;
        this.head_found = 0;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (this.isSuitableTarget(var4, false) && ret == null) {
                ret = var4;
            }
            if (ret == null || this.head_found == 0) continue;
        }
        return ret;
    }

    public void setGuardMode(int i) {
        this.guard_mode = i;
    }

    public void setBadMood(int i) {
        this.always_mad = i;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("KingHomeX", this.homex);
        par1CompoundNBT.putInt("KingHomeZ", this.homez);
        par1CompoundNBT.putInt("GuardMode", this.guard_mode);
        par1CompoundNBT.putInt("PlayerEntityHits", this.player_hit_count);
        par1CompoundNBT.putInt("MeanMode", this.always_mad);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.homex = par1CompoundNBT.getInt("KingHomeX");
        this.homez = par1CompoundNBT.getInt("KingHomeZ");
        this.guard_mode = par1CompoundNBT.getInt("GuardMode");
        this.player_hit_count = par1CompoundNBT.getInt("PlayerEntityHits");
        this.always_mad = par1CompoundNBT.getInt("MeanMode");
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        return com.astryxion.chaospersists.util.EntitySpawnHelper.spawn(par0World, par1, par2, par4, par6);
    }

    private LivingEntity doJumpDamage(double X, double Y, double Z, double dist, double damage, int knock) {
        AxisAlignedBB bb = new AxisAlignedBB((double)(X - dist), (double)(Y - 10.0), (double)(Z - dist), (double)(X + dist), (double)(Y + 10.0), (double)(Z + dist));
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, bb);
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (var4 == null || var4 == this || !var4.isAlive() || MyUtils.isRoyalty((Entity)var4) || var4 instanceof Ghost || var4 instanceof GhostSkelly) continue;
            DamageSource var21 = null;
            var21 = DamageSource.explosion((Explosion)null);
            var4.hurt(var21, (float)damage / 2.0f);
            var4.hurt(DamageSource.FALL, (float)damage / 2.0f);
            this.level.playSound(null, var4.getX(), var4.getY(), var4.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, this.getSoundSource(), 0.65f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.5f);
            if (knock == 0) continue;
            double ks = 2.75;
            double inair = 0.65;
            float f3 = (float)Math.atan2(var4.getZ() - this.getZ(), var4.getX() - this.getX());
            var4.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return null;
    }
}

