/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CreepingHorror
 *  com.astryxion.chaospersists.EnderReaper
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Godzilla
 *  com.astryxion.chaospersists.GodzillaHead
 *  com.astryxion.chaospersists.Island
 *  com.astryxion.chaospersists.IslandToo
 *  com.astryxion.chaospersists.LeafMonster
 *  com.astryxion.chaospersists.LurkingTerror
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.PitchBlack
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.TerribleTerror
 *  com.astryxion.chaospersists.Triffid
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.EnderDragonEntity
 *  net.minecraft.entity.boss.EnderDragonPartEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.GodzillaHead;
import com.astryxion.chaospersists.entity.Island;
import com.astryxion.chaospersists.entity.IslandToo;
import com.astryxion.chaospersists.entity.LeafMonster;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.Triffid;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.world.LightType;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class PitchBlack
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(PitchBlack.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> ACTIVITY = EntityDataManager.defineId(PitchBlack.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> SCALE_INT = EntityDataManager.defineId(PitchBlack.class, DataSerializers.INT);
    private BlockPos currentFlightTarget = null;
    private GenericTargetSorter TargetSorter = null;
    /** When true, bypasses canDespawn (egg/command spawns persist). Natural spawns leave this false. */
    private boolean spawnedFromEgg = false;
    /** Spawner-spawned nightmares should also bypass daytime despawn rules. */
    private boolean spawnedFromSpawner = false;
    private RenderInfo renderdata = new RenderInfo();
    private float MyMoveSpeed = 0.2f;
    private int damage_ticker = 0;
    private int wing_sound = 0;

    public PitchBlack(EntityType<? extends PitchBlack> type, World par1World) {
        super(type, par1World);
                this.xpReward = 200;
        
                this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal((CreatureEntity)(Object)this, 1.0, false, 32, () -> true));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.PitchBlack_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.PitchBlack_stats.attack)
                .build();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    private void applyPitchBlackSize() {
        this.refreshDimensions();
    }

    @Override
    public net.minecraft.entity.EntitySize getDimensions(net.minecraft.entity.Pose pose) {
        float s = this.getPitchBlackScale();
        return net.minecraft.entity.EntitySize.scalable(2.5f * s, 3.5f * s);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(ACTIVITY, (byte)0);
        this.entityData.define(SCALE_INT, 0);
        if (this.renderdata == null) {
            this.renderdata = new RenderInfo();
        }
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
        float t = 0.5f;
        if (this.level != null) {
            if (this.level.random.nextInt(4) == 1) {
                t = 1.0f;
            }
            if (this.level.random.nextInt(8) == 2) {
                t = 2.0f;
            }
            if (this.level.random.nextInt(32) == 3) {
                t = 3.0f;
            }
            if (this.level.random.nextInt(64) == 4) {
                t = 4.0f;
            }
        } else {
            if (ChaosPersists.ChaosRand.nextInt(4) == 1) {
                t = 1.0f;
            }
            if (ChaosPersists.ChaosRand.nextInt(8) == 2) {
                t = 2.0f;
            }
            if (ChaosPersists.ChaosRand.nextInt(32) == 3) {
                t = 3.0f;
            }
            if (ChaosPersists.ChaosRand.nextInt(64) == 4) {
                t = 4.0f;
            }
        }
        if (ChaosPersists.NightmareSize == 1) {
            t = 0.5f;
        }
        if (ChaosPersists.NightmareSize == 2) {
            t = 1.0f;
        }
        if (ChaosPersists.NightmareSize == 3) {
            t = 2.0f;
        }
        if (ChaosPersists.NightmareSize == 4) {
            t = 3.0f;
        }
        if (ChaosPersists.NightmareSize == 5) {
            t = 4.0f;
        }
        this.setPitchBlackScale(t);
        this.xpReward = (int)(100.0f * t);
        this.applyPitchBlackSize();
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.spawnedFromEgg = par1CompoundNBT.getBoolean("SpawnedFromEgg");
        this.spawnedFromSpawner = par1CompoundNBT.getBoolean("SpawnedFromSpawner");
        this.setPitchBlackScale(par1CompoundNBT.getFloat("Fscale"));
        this.applyPitchBlackSize();
        this.xpReward = (int)(100.0f * this.getPitchBlackScale());
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putBoolean("SpawnedFromEgg", this.spawnedFromEgg);
        par1CompoundNBT.putBoolean("SpawnedFromSpawner", this.spawnedFromSpawner);
        par1CompoundNBT.putFloat("Fscale", this.getPitchBlackScale());
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    /**
     * Defensive fallback: if a Nightmare spawns near a Nightmare spawner but missed
     * the normal spawn flag handoff, mark it as spawner-spawned on early ticks.
     */
    private void syncSpawnerSpawnStateIfNeeded() {
        if (this.level == null || this.level.isClientSide || this.spawnedFromSpawner || this.tickCount > 40) {
            return;
        }
        BlockPos base = new BlockPos(this.getX(), this.getY(), this.getZ());
        for (int dx = -8; dx <= 8; ++dx) {
            for (int dy = -4; dy <= 8; ++dy) {
                for (int dz = -8; dz <= 8; ++dz) {
                    TileEntity te = this.level.getBlockEntity(base.offset(dx, dy, dz));
                    if (!(te instanceof MobSpawnerTileEntity)) {
                        continue;
                    }
                    String path = null;
                    net.minecraft.util.ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityId(((MobSpawnerTileEntity) te).getSpawner());
                    if (id != null) {
                        path = SpawnerFixHelper.normalizeSpawnerEntityId(id).getPath();
                    }
                    if (path != null && "nightmare".equalsIgnoreCase(path)) {
                        this.setSpawnedFromSpawner();
                        float t = this.getPitchBlackScale();
                        if (t > 1.0f) {
                            this.setPitchBlackScale(1.0f);
                        }
                        return;
                    }
                }
            }
        }
    }

    public final int getActivity() {
        return this.entityData.get(ACTIVITY).byteValue();
    }

    public final void setActivity(int par1) {
        this.entityData.set(ACTIVITY, (byte)par1);
    }

    public float getPitchBlackScale() {
        int i = this.entityData.get(SCALE_INT).intValue();
        float f = i;
        f /= 10.0f;
        // Defensive clamp: avoid zero/invalid scale causing invisible spawner previews.
        if (f < 0.5f) {
            return 0.5f;
        }
        return f;
    }

    public void setPitchBlackScale(float par1) {
        if (par1 < 0.5f) {
            par1 = 0.5f;
        }
        float f = par1 * 10.0001f;
        int i = (int)f;
        this.entityData.set(SCALE_INT, i);
    }

    public int getArmorValue() {
        return ChaosPersists.PitchBlack_stats.defense + (int)(2.0f * this.getPitchBlackScale());
    }

    public RenderInfo getRenderInfo() {
        return this.renderdata;
    }

    public void setRenderInfo(RenderInfo r) {
        this.renderdata.rf1 = r.rf1;
        this.renderdata.rf2 = r.rf2;
        this.renderdata.rf3 = r.rf3;
        this.renderdata.rf4 = r.rf4;
        this.renderdata.ri1 = r.ri1;
        this.renderdata.ri2 = r.ri2;
        this.renderdata.ri3 = r.ri3;
        this.renderdata.ri4 = r.ri4;
    }

    /** Set when spawned via egg or /summon {SpawnedFromEgg:1b} — bypasses daytime despawn. */
    public void setSpawnedFromEgg() {
        this.spawnedFromEgg = true;
    }

    /** Set when spawned from a mob spawner — bypasses daytime despawn. */
    public void setSpawnedFromSpawner() {
        this.spawnedFromSpawner = true;
        this.setPersistenceRequired();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.spawnedFromEgg || this.spawnedFromSpawner) {
            return false;
        }
        // Avoid edge cases where the spawn flag arrives a tick late for spawner spawns.
        if (this.tickCount < 40) {
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        return true;
    }

    protected float getSoundVolume() {
        return 0.75f;
    }

    protected float getVoicePitch() {
        return 1.0f - 0.7f * (4.0f / this.getPitchBlackScale());
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.level.random.nextInt(5) != 2) {
            return null;
        }
        return com.astryxion.chaospersists.core.ChaosSounds.PITCHBLACK_LIVING;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.PITCHBLACK_HIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.PITCHBLACK_DEAD;
    }

    public int mygetMaxHealth() {
        return (int)((float)ChaosPersists.PitchBlack_stats.health * this.getPitchBlackScale());
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void tick() {
        this.syncSpawnerSpawnStateIfNeeded();
        if (this.getPitchBlackScale() < 0.5f) {
            this.setPitchBlackScale(0.5f);
        }
        this.MyMoveSpeed = 0.2f;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(this.MyMoveSpeed + 0.1f * this.getPitchBlackScale()));
        super.tick();
        this.applyPitchBlackSize();
        ++this.wing_sound;
        if (this.wing_sound > 20) {
            if (!this.level.isClientSide) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, this.getSoundSource(), 1.0f, 1.0f);
            }
            this.wing_sound = 0;
        }
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        if (!this.level.isClientSide && this.level.random.nextInt(250) == 1) {
            this.heal(1.0f + this.getPitchBlackScale());
            if (this.level.random.nextInt(5) == 0) {
                Block bid = Blocks.AIR;
                if (this.getY() > 10.0) {
                    for (int i = 0; i < 10 && (bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)this.getY() - i, (int)this.getZ())).getBlock()) == Blocks.AIR; ++i) {
                    }
                } else {
                    bid = Blocks.STONE;
                }
                if (bid != Blocks.AIR) {
                    Entity e = null;
                    e = this.findSomethingToAttack();
                    if (e == null) {
                        this.setActivity(0);
                    }
                }
            } else {
                this.setActivity(1);
                this.getNavigation().stop();
            }
        }
        if (this.getActivity() == 0 && this.level.random.nextInt(10) == 1) {
            Entity e = null;
            e = this.findSomethingToAttack();
            if (e != null) {
                this.setActivity(1);
                this.getNavigation().stop();
            }
        }
    }

    @Override
    public void remove() {
        // Spawner/egg Nightmares must not be culled by generic despawn/peaceful cleanup.
        // Allow death from real damage (health <= 0).
        if (!this.level.isClientSide && (this.spawnedFromEgg || this.spawnedFromSpawner) && this.getHealth() > 0.0f) {
            return;
        }
        super.remove();
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        boolean var4 = false;
        if (par1Entity != null && par1Entity instanceof EnderDragonEntity) {
            EnderDragonEntity dr = (EnderDragonEntity)par1Entity;
            DamageSource var21 = DamageSource.explosion((Explosion)null);
            if (this.level.random.nextInt(8) == 1) {
                dr.hurt(var21, (float)ChaosPersists.PitchBlack_stats.attack * this.getPitchBlackScale());
            } else {
                dr.hurt(var21, (float)ChaosPersists.PitchBlack_stats.attack * this.getPitchBlackScale());
            }
            var4 = true;
        } else {
            var4 = super.doHurtTarget(par1Entity);
            if (var4 && par1Entity != null && par1Entity instanceof LivingEntity) {
                double ks = 1.15 * (double)this.getPitchBlackScale();
                double inair = 0.08 * (double)this.getPitchBlackScale();
                float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!par1Entity.isAlive() || par1Entity instanceof PlayerEntity) {
                    inair *= 2.0;
                }
                par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
        }
        return var4;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d((double)this.getX(), (double)(this.getY() + 0.75), (double)this.getZ()), new Vector3d((double)pX, (double)pY, (double)pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        if (this.damage_ticker > 0) {
            --this.damage_ticker;
        }
        if (this.getActivity() == 0) {
            super.customServerAiStep();
            return;
        }
        if (!this.isAlive()) {
            return;
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.getActivity() == 0) {
            return;
        }
        if (this.random.nextInt(150) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 2.1f) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying > 0) {
                zdir = this.random.nextInt(20) + 5 * (int)this.getPitchBlackScale();
                xdir = this.random.nextInt(20) + 5 * (int)this.getPitchBlackScale();
                if (this.random.nextInt(2) == 0) {
                    zdir = - zdir;
                }
                if (this.random.nextInt(2) == 0) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)this.getX() + xdir, (int)this.getY() + this.random.nextInt(11) - 5, (int)this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        } else if (this.random.nextInt(8) == 0) {
            Entity e = null;
            e = this.findSomethingToAttack();
            if (e != null) {
                double d1 = 5.0 + (double)(e.getBbWidth() / 2.0f);
                d1 += (double)this.getPitchBlackScale();
                d1 *= d1;
                this.setAttacking(1);
                if (e instanceof EnderDragonEntity && d1 < 100.0) {
                    d1 = 100.0;
                }
                if (e instanceof Godzilla && d1 < 100.0) {
                    d1 = 100.0;
                }
                if (e instanceof GodzillaHead && d1 < 100.0) {
                    d1 = 100.0;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)(e.getY() + 2.0), (int)e.getZ());
                if (this.distanceToSqr(e) < d1) {
                    this.doHurtTarget((LivingEntity)e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.4 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.4 - this.getZ();
        double myspeed = 0.5f + this.getPitchBlackScale() / 10.0f;
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * myspeed - this.getDeltaMovement().x) * 0.33, (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.20000000149011612, (Math.signum(var5) * myspeed - this.getDeltaMovement().z) * 0.33);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = 0.1f + (float)myspeed;
        this.yRot += var8 / 5.0f;
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

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (this.damage_ticker > 0) {
            return ret;
        }
        this.damage_ticker = 20;
        ret = super.hurt(par1DamageSource, par2);
        Entity e = par1DamageSource.getEntity();
        if (e != null && this.currentFlightTarget != null) {
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)(e.getY() + 2.0), (int)e.getZ());
        }
        this.setActivity(1);
        this.getNavigation().stop();
        return ret;
    }

    protected boolean isValidLightLevel() {
        net.minecraft.util.math.BlockPos pos = new net.minecraft.util.math.BlockPos(MathHelper.floor(this.getX()), MathHelper.floor(this.getBoundingBox().minY), MathHelper.floor(this.getZ()));
        if (this.level.getBrightness(LightType.SKY, pos) > this.random.nextInt(32)) {
            return false;
        }
        int l = this.level.getBrightness(LightType.BLOCK, pos);
        return l <= this.random.nextInt(8);
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        Block bid;
        int j;
        int i;
        int k;
        for (k = -5; k <= 5; ++k) {
            for (j = -5; j <= 5; ++j) {
                for (i = -2; i <= 6; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    // Keep spawner checks mapping-agnostic (reflection on getEntityId can fail by runtime names).
                    Float t = Float.valueOf(this.getPitchBlackScale());
                    if (t.floatValue() > 1.0f) {
                        t = Float.valueOf(1.0f);
                    }
                    this.setPitchBlackScale(t.floatValue());
                    this.setSpawnedFromSpawner();
                    return true;
                }
            }
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level) == ChaosPersists.getDimension(6)) {
            PitchBlack target = null;
            target = this.level.getNearestEntity(PitchBlack.class, EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(16.0, 16.0, 16.0));
            if (target != null) {
                return false;
            }
        }
        if (this.getPitchBlackScale() < 1.1f) {
            return true;
        }
        int ix = 1;
        if (this.getPitchBlackScale() > 3.1f) {
            ix = 2;
        }
        int iy = ix * 3;
        for (k = - ix; k <= ix; ++k) {
            for (j = - ix; j <= ix; ++j) {
                for (i = 1; i <= iy; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
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
        if (!(par1Mob instanceof LivingEntity)) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PitchBlack) {
            return false;
        }
        if (par1Mob instanceof EnderReaper) {
            return false;
        }
        if (par1Mob instanceof LeafMonster) {
            return false;
        }
        if (par1Mob instanceof TerribleTerror) {
            return false;
        }
        if (par1Mob instanceof LurkingTerror) {
            return false;
        }
        if (par1Mob instanceof CreepingHorror) {
            return false;
        }
        if (par1Mob instanceof Island) {
            return false;
        }
        if (par1Mob instanceof IslandToo) {
            return false;
        }
        if (par1Mob instanceof Triffid) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private Entity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        double d1 = 16.0 + (double)(this.getPitchBlackScale() * 6.0f);
        double d2 = 10.0 + (double)(this.getPitchBlackScale() * 4.0f);
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(d1, d2, d1));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        LivingEntity var3 = null;
        while (var2.hasNext()) {
            var3 = (LivingEntity)var2.next();
            if (!this.isSuitableTarget(var3, false)) continue;
            return var3;
        }
        return null;
    }

    protected Item getDropItem() {
        return ChaosPersists.MyNightmareScale;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)((float)ChaosPersists.ChaosRand.nextInt(5) * this.getPitchBlackScale()) - (double)((float)ChaosPersists.ChaosRand.nextInt(5) * this.getPitchBlackScale()), this.getY() + 1.0, this.getZ() + (double)((float)ChaosPersists.ChaosRand.nextInt(5) * this.getPitchBlackScale()) - (double)((float)ChaosPersists.ChaosRand.nextInt(5) * this.getPitchBlackScale()), is);
        if (var3 != null) {
            this.level.addFreshEntity(var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        int i = 3 + this.level.random.nextInt(2 + (int)(5.0f * this.getPitchBlackScale()));
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.ROTTEN_FLESH, 1);
            int j = this.level.random.nextInt(10);
            if (j == 0) {
                this.dropItemRand(Items.FEATHER, 1);
            }
            if (j == 1) {
                this.dropItemRand(Items.STRING, 1);
            }
            if (j == 2) {
                this.dropItemRand(Items.FLINT, 1);
            }
            if (j != 3) continue;
            this.dropItemRand(Items.BEEF, 1);
        }
        this.dropItemRand(ChaosPersists.MyNightmareScale, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        i = 2 + (int)this.getPitchBlackScale() + this.level.random.nextInt(2 + (int)(5.0f * this.getPitchBlackScale()));
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.ZooKeeper, 1);
        }
    }
}

