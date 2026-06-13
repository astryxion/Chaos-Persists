/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.BetterFireball
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Ghost
 *  com.astryxion.chaospersists.GhostSkelly
 *  com.astryxion.chaospersists.Godzilla
 *  com.astryxion.chaospersists.GodzillaHead
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.PitchBlack
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.block.Block
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.enchantment.Enchantment
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
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.EnderDragonEntity
 *  net.minecraft.entity.boss.EnderDragonPartEntity
 *  net.minecraft.entity.effect.LightningBoltEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Creeper
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.monster.SkeletonEntity
 *  net.minecraft.entity.monster.Spider
 *  net.minecraft.entity.monster.ZombieEntity
 *  net.minecraft.entity.passive.VillagerEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import java.util.Locale;

import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Ghost;
import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.entity.GodzillaHead;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

/*
 * Exception performing whole class analysis ignored.
 */
public class Godzilla
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Godzilla.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> PLAY_NICELY = EntityDataManager.defineId(Godzilla.class, DataSerializers.INT);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.75f;
    private int hurt_timer = 0;
    private int jumped = 0;
    private int jump_timer = 0;
    private int ticker = 0;
    private RenderInfo renderdata = new RenderInfo();
    private int stream_count = 8;
    private MyEntityAIWanderALot wander = null;
    private int head_found = 0;
    private int large_unknown_detected = 0;
    private boolean onGround = false;

    public Godzilla(EntityType<? extends Godzilla> type, World par1World) {
        super(type, par1World);
        if (ChaosPersists.PlayNicely == 0) {
        } else {
        }
        this.xpReward = 10000;
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal((CreatureEntity)(Object)this, 1.0, false, 32, () -> true));
        this.wander = new MyEntityAIWanderALot(this, 15, 1.0);
        this.goalSelector.addGoal(2, this.wander);
        this.goalSelector.addGoal(3, new LookAtGoal(this, LivingEntity.class, 50.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        // renderDistanceWeight not settable in 1.12.2
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Godzilla_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Godzilla_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
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
    }

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY).intValue();
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

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (ChaosPersists.PlayNicely != 0) {
            return true;
        }
        return false;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Godzilla_stats.health;
    }

    public int getArmorValue() {
        if (this.large_unknown_detected != 0) {
            return 25;
        }
        return ChaosPersists.Godzilla_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void tick() {
        double xzoff = 0.0;
        double myoff = 20.0;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        if (this.onGround) {
            this.getNavigation().stop();
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.level.random.nextInt(5) == 0) {
            return com.astryxion.chaospersists.core.ChaosSounds.GODZILLA_LIVING;
        }
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource ds) {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_HURT;
    }

    protected SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.GODZILLA_DEATH;
    }

    protected float getSoundVolume() {
        return 1.65f;
    }

    protected float getVoicePitch() {
        return 1.1f;
    }

    protected Item getDropItem() {
        return null;
    }

    @Override
    protected void jumpFromGround() {
        while (this.yRot < 0.0f) {
            this.yRot += 360.0f;
        }
        while (this.yHeadRot < 0.0f) {
            this.yHeadRot += 360.0f;
        }
        while (this.yRot > 360.0f) {
            this.yRot -= 360.0f;
        }
        while (this.yHeadRot > 360.0f) {
            this.yHeadRot -= 360.0f;
        }
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.44999998807907104, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.5);
        float f = 0.2f + Math.abs(this.level.random.nextFloat() * 0.45f);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (double)f * Math.cos(Math.toRadians(this.yHeadRot + 90.0f)), 0.0, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.0, (double)f * Math.sin(Math.toRadians(this.yHeadRot + 90.0f)));
        this.setOnGround(false);
        this.getNavigation().stop();
    }

    protected void jumpAtEntity(LivingEntity e) {
        float f2;
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 1.25, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 1.5499999523162842);
        double d1 = e.getX() - this.getX();
        double d2 = e.getZ() - this.getZ();
        float d = (float)Math.atan2(d2, d1);
        this.yRot = f2 = (float)((double)d * 180.0 / 3.141592653589793) - 90.0f;
        d1 = Math.sqrt(d1 * d1 + d2 * d2);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, d1 * 0.05 * Math.cos(d), 0.0, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.0, d1 * 0.05 * Math.sin(d));
        this.setOnGround(false);
        this.getNavigation().stop();
    }

    private double getHorizontalDistanceSqToEntity(Entity e) {
        double d1 = e.getZ() - this.getZ();
        double d2 = e.getX() - this.getX();
        return d1 * d1 + d2 * d2;
    }

    public double MygetDistanceSqToEntity(Entity par1Entity) {
        double d0 = this.getX() - par1Entity.getX();
        double d1 = par1Entity.getY() - this.getY();
        double d2 = this.getZ() - par1Entity.getZ();
        if (d1 > 0.0 && d1 < 20.0) {
            d1 = 0.0;
        }
        if (d1 > 20.0) {
            d1 -= 10.0;
        }
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    protected void customServerAiStep() {
        int j;
        int i;
        Block bid;
        LivingEntity e = null;
        int xzrange = 9;
        if (!this.isAlive()) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
        super.customServerAiStep();
        ++this.ticker;
        if (this.ticker > 30000) {
            this.ticker = 0;
        }
        if (this.ticker % 100 == 0) {
            this.stream_count = 8;
        }
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.jump_timer > 0) {
            --this.jump_timer;
        }
        ChaosPersists.godzilla_has_spawned = 1;
        if (this.level.random.nextInt(200) == 0) {
            this.setTarget(null);
        }
        if (ChaosPersists.PlayNicely == 0) {
            if (this.getDeltaMovement().y < -0.95) {
                this.jumped = 1;
            }
            if (this.getDeltaMovement().y < -1.5) {
                this.jumped = 2;
            }
            if (this.jumped != 0 && this.getDeltaMovement().y > -0.1) {
                double df = 1.0;
                if (this.jumped == 2) {
                    df = 1.5;
                }
                this.doJumpDamage(this.getX(), this.getY(), this.getZ(), 10.0, (double)ChaosPersists.Godzilla_stats.attack * df, 0);
                this.doJumpDamage(this.getX(), this.getY(), this.getZ(), 15.0, (double)(ChaosPersists.Godzilla_stats.attack / 2) * df, 0);
                this.doJumpDamage(this.getX(), this.getY(), this.getZ(), 25.0, (double)(ChaosPersists.Godzilla_stats.attack / 4) * df, 0);
                this.jumped = 0;
            }
        }
        xzrange = 12;
        if (this.getAttacking() != 0) {
            xzrange = 16;
        }
        int k = -3 + this.ticker % 30;
        if (ChaosPersists.PlayNicely == 0) {
            for (i = - xzrange; i <= xzrange; ++i) {
                for (j = - xzrange; j <= xzrange; ++j) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + k, (int)this.getZ() + j)).getBlock();
                    if (this.isCrushable(bid)) {
                        this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + k, (int)this.getZ() + j), Blocks.AIR.defaultBlockState(), 3);
                        if (this.level.random.nextInt(15) != 1) continue;
                        this.dropItemRand(bid.asItem(), 1);
                        continue;
                    }
                    if (bid == Blocks.GRASS_BLOCK && this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) {
                        this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + k, (int)this.getZ() + j), Blocks.DIRT.defaultBlockState(), 3);
                    }
                    if (bid != Blocks.FARMLAND || !this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) continue;
                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + k, (int)this.getZ() + j), Blocks.DIRT.defaultBlockState(), 3);
                }
            }
        }
        double dx = this.getX() + 16.0 * Math.sin(Math.toRadians(this.yHeadRot));
        double dz = this.getZ() - 16.0 * Math.cos(Math.toRadians(this.yHeadRot));
        k = -3 + this.ticker % 12;
        if (ChaosPersists.PlayNicely == 0) {
            for (i = - xzrange; i <= xzrange; ++i) {
                for (j = - xzrange; j <= xzrange; ++j) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx + i, (int)this.getY() + k, (int)dz + j)).getBlock();
                    if (this.isCrushable(bid)) {
                        this.level.setBlock(new net.minecraft.util.math.BlockPos((int)dx + i, (int)this.getY() + k, (int)dz + j), Blocks.AIR.defaultBlockState(), 3);
                        if (this.level.random.nextInt(15) != 1) continue;
                        this.dropItemRandAt(bid.asItem(), 1, dx, dz);
                        continue;
                    }
                    if (bid == Blocks.GRASS_BLOCK && this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) {
                        this.level.setBlock(new net.minecraft.util.math.BlockPos((int)dx + i, (int)this.getY() + k, (int)dz + j), Blocks.DIRT.defaultBlockState(), 3);
                    }
                    if (bid != Blocks.FARMLAND || !this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) continue;
                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)dx + i, (int)this.getY() + k, (int)dz + j), Blocks.DIRT.defaultBlockState(), 3);
                }
            }
        }
        if (ChaosPersists.PlayNicely == 0 && k == 0) {
            this.doJumpDamage(dx, this.getY(), dz, 15.0, (double)(ChaosPersists.Godzilla_stats.attack / 2), 1);
        }
        if (this.level.random.nextInt(5 - this.large_unknown_detected) == 1) {
            e = this.getTarget();
            if (ChaosPersists.PlayNicely != 0) {
                e = null;
            }
            if (e != null) {
                if (!e.isAlive()) {
                    this.setTarget(null);
                    e = null;
                } else if (e instanceof Godzilla || e instanceof GodzillaHead) {
                    this.setTarget(null);
                    e = null;
                }
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
                if (this.head_found == 0) {
                    MobEntity newent = (MobEntity)Godzilla.spawnCreature((World)this.level, (String)"chaospersists:mobzilla_head", (double)this.getX(), (double)(this.getY() + 20.0), (double)this.getZ());
                }
            }
            if (e != null) {
                this.wander.setBusy(1);
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.level.random.nextInt(65) == 1 && this.MygetDistanceSqToEntity((Entity)e) > 300.0) {
                    this.doLightningAttack(e);
                } else if (this.level.random.nextInt(20 - this.large_unknown_detected * 5) == 1 && this.jump_timer == 0) {
                    this.jumpAtEntity(e);
                    this.jump_timer = 30;
                } else if (this.MygetDistanceSqToEntity((Entity)e) < (double)(300.0f + e.getBbWidth() / 2.0f * (e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    this.getNavigation().moveTo((Entity)e, 1.0);
                    if (this.level.random.nextInt(4 - this.large_unknown_detected) == 0 || this.level.random.nextInt(3 - this.large_unknown_detected) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                    }
                } else {
                    this.getNavigation().moveTo((Entity)e, 1.0);
                    if (this.getHorizontalDistanceSqToEntity((Entity)e) > 625.0) {
                        if (this.stream_count > 0) {
                            this.setAttacking(1);
                            double rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                            double rhdir = Math.toRadians((this.yHeadRot + 90.0f) % 360.0f);
                            double pi = 3.1415926545;
                            double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                            if (rdd > pi) {
                                rdd -= pi * 2.0;
                            }
                            if ((rdd = Math.abs(rdd)) < 0.5) {
                                this.firecanon(e);
                            }
                        } else {
                            this.setAttacking(0);
                        }
                    } else {
                        this.setAttacking(0);
                    }
                }
            } else {
                this.setAttacking(0);
                this.wander.setBusy(0);
                this.stream_count = 8;
            }
        }
        if (this.level.random.nextInt(35) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(5.0f);
        }
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = com.astryxion.chaospersists.util.EntitySpawnHelper.spawn(par0World, par1, par2, par4, par6);
        if (var8 instanceof MobEntity) {
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
        }
        return var8;
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
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof Godzilla) {
            return false;
        }
        if (par1Mob instanceof GodzillaHead) {
            return false;
        }
        if (par1Mob instanceof CreeperEntity) {
            return false;
        }
        if (par1Mob instanceof ZombieEntity) {
            return false;
        }
        if (par1Mob instanceof SpiderEntity) {
            return false;
        }
        if (par1Mob instanceof SkeletonEntity) {
            return false;
        }
        if (par1Mob instanceof Ghost) {
            return false;
        }
        if (par1Mob instanceof GhostSkelly) {
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

    private boolean isVillagerTarget(LivingEntity par1Mob, boolean par2) {
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == this) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof VillagerEntity) {
            return true;
        }
        return false;
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
            if (var4 == null || var4 == this || !var4.isAlive() || var4 instanceof Godzilla || var4 instanceof GodzillaHead || var4 instanceof Ghost || var4 instanceof GhostSkelly) continue;
            DamageSource var21 = null;
            var21 = DamageSource.explosion((Explosion)null);
            var4.hurt(var21, (float)damage / 2.0f);
            var4.hurt(DamageSource.FALL, (float)damage / 2.0f);
            this.level.playSound(null, var4.getX(), var4.getY(), var4.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, SoundCategory.HOSTILE, 0.85f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.5f);
            if (knock == 0) continue;
            double ks = 3.5;
            double inair = 0.75;
            float f3 = (float)Math.atan2(var4.getZ() - this.getZ(), var4.getX() - this.getX());
            var4.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return null;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            this.head_found = 1;
            return null;
        }
        List var5 = null;
        Iterator var2 = null;
        Entity var3 = null;
        LivingEntity var4 = null;
        LivingEntity ret = null;
        boolean vf = false;
        var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(64.0, 40.0, 64.0));
        if (var5 == null) {
            return null;
        }
        Collections.sort(var5, this.TargetSorter);
        var2 = var5.iterator();
        this.head_found = 0;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (var4 instanceof GodzillaHead) {
                this.head_found = 1;
            }
            if (!vf && this.isVillagerTarget(var4, false)) {
                ret = var4;
                vf = true;
            }
            if (ret != null || vf || !this.isSuitableTarget(var4, false)) continue;
            ret = var4;
        }
        return ret;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (ChaosPersists.godzilla_has_spawned != 0) {
            return false;
        }
        if (this.level.random.nextInt(40) != 1) {
            return false;
        }
        for (int k = -8; k <= 8; ++k) {
            for (int j = -8; j <= 8; ++j) {
                for (int i = 5; i < 15; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        Godzilla target = null;
        List<Godzilla> nearby = this.level.getEntitiesOfClass(Godzilla.class, this.getBoundingBox().inflate(64.0, 16.0, 64.0), e -> e != this);
        if (!nearby.isEmpty()) {
            return false;
        }
        if (!this.level.isClientSide) {
            ChaosPersists.godzilla_has_spawned = 1;
        }
        return true;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(10) - (double)ChaosPersists.ChaosRand.nextInt(10), this.getY() + 4.0 + (double)this.level.random.nextInt(10), this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(10) - (double)ChaosPersists.ChaosRand.nextInt(10), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    private ItemStack dropItemRandAt(Item index, int par1, double dx, double dz) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, dx + (double)ChaosPersists.ChaosRand.nextInt(10) - (double)ChaosPersists.ChaosRand.nextInt(10), this.getY() + 4.0 + (double)this.level.random.nextInt(6), dz + (double)ChaosPersists.ChaosRand.nextInt(10) - (double)ChaosPersists.ChaosRand.nextInt(10), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    private boolean isCrushable(Block bid) {
        if (bid == null) {
            return false;
        }
        if (!this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) {
            return false;
        }
        if (bid == Blocks.GRASS_BLOCK) {
            return false;
        }
        if (bid == Blocks.DIRT) {
            return false;
        }
        if (bid == Blocks.STONE) {
            return false;
        }
        if (bid == Blocks.FARMLAND) {
            return false;
        }
        if (bid == Blocks.WATER) {
            return false;
        }
        if (bid == Blocks.WATER) {
            return false;
        }
        if (bid == Blocks.LAVA) {
            return false;
        }
        if (bid == Blocks.LAVA) {
            return false;
        }
        if (bid == Blocks.BEDROCK) {
            return false;
        }
        if (bid == Blocks.OBSIDIAN) {
            return false;
        }
        if (bid == Blocks.SAND) {
            return false;
        }
        if (bid == Blocks.GRAVEL) {
            return false;
        }
        if (bid == Blocks.IRON_BLOCK) {
            return false;
        }
        if (bid == Blocks.DIAMOND_BLOCK) {
            return false;
        }
        if (bid == Blocks.EMERALD_BLOCK) {
            return false;
        }
        if (bid == Blocks.GOLD_BLOCK) {
            return false;
        }
        if (bid == Blocks.NETHERRACK) {
            return false;
        }
        if (bid == Blocks.END_STONE) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockAmethystBlock) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockRubyBlock) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockUraniumBlock) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockTitaniumBlock) {
            return false;
        }
        if (bid == ChaosPersists.CrystalStone) {
            return false;
        }
        if (bid == ChaosPersists.CrystalGrass) {
            return false;
        }
        return true;
    }

    private void firecanon(LivingEntity e) {
        double yoff = 19.0;
        double xzoff = 22.0;
        BetterFireball bf = null;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        if (this.stream_count > 0) {
            bf = new BetterFireball(this.level, (LivingEntity)this, e.getX() - cx, e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff), e.getZ() - cz);
            bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
            bf.setPos(cx, this.getY() + yoff, cz);
            bf.setBig();
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.TNT_PRIMED, SoundCategory.HOSTILE, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level.addFreshEntity((Entity)bf);
            for (int i = 0; i < 5; ++i) {
                float r1 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
                float r2 = 3.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
                float r3 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
                bf = new BetterFireball(this.level, (LivingEntity)this, e.getX() - cx + (double)r1, e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff) + (double)r2, e.getZ() - cz + (double)r3);
                bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
                bf.setPos(cx, this.getY() + yoff, cz);
                if (this.level.random.nextInt(2) == 1) {
                    bf.setSmall();
                }
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, SoundCategory.HOSTILE, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level.addFreshEntity((Entity)bf);
            }
            --this.stream_count;
        }
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        float s;
        if (!(par1Entity == null || !(par1Entity instanceof LivingEntity) || (s = par1Entity.getBbHeight() * par1Entity.getBbWidth()) <= 30.0f || MyUtils.isRoyalty((Entity)par1Entity) || par1Entity instanceof Godzilla || par1Entity instanceof GodzillaHead || par1Entity instanceof PitchBlack || par1Entity instanceof Kraken)) {
            LivingEntity e = (LivingEntity)par1Entity;
            e.setHealth(e.getHealth() / 2.0f);
            e.hurt(DamageSource.mobAttack((LivingEntity)this), (float)ChaosPersists.Godzilla_stats.attack * 10.0f);
            this.large_unknown_detected = 1;
        }
        if (par1Entity != null && par1Entity instanceof EnderDragonEntity) {
            EnderDragonEntity dr = (EnderDragonEntity)par1Entity;
            DamageSource var21 = DamageSource.explosion((Explosion)null);
            if (this.level.random.nextInt(6) == 1) {
                dr.hurt(var21, (float)ChaosPersists.Godzilla_stats.attack / 2.0f);
            } else {
                dr.hurt(var21, (float)ChaosPersists.Godzilla_stats.attack / 2.0f);
            }
        }
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity != null && par1Entity instanceof LivingEntity) {
                double ks = 3.2;
                double inair = 0.3;
                float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!par1Entity.isAlive() || par1Entity instanceof PlayerEntity) {
                    inair *= 2.0;
                }
                par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e;
        boolean ret = false;
        float dm = par2;
        float s = 0.0f;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (dm > 120.0f) {
            dm = 120.0f;
        }
        if ((e = par1DamageSource.getEntity()) != null && e instanceof LivingEntity) {
            LivingEntity enl = (LivingEntity)e;
            s = enl.getBbHeight() * enl.getBbWidth();
            if (!(s <= 30.0f || MyUtils.isRoyalty((Entity)enl) || enl instanceof Godzilla || enl instanceof GodzillaHead || enl instanceof PitchBlack || enl instanceof Kraken)) {
                dm /= 10.0f;
                this.hurt_timer = 50;
                this.large_unknown_detected = 1;
            }
        }
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            ret = super.hurt(par1DamageSource, dm);
            this.hurt_timer = 20;
            e = par1DamageSource.getEntity();
            if (e != null && e instanceof LivingEntity && !(e instanceof GodzillaHead) && !(e instanceof Godzilla)) {
                this.setTarget((LivingEntity)e);
                this.getNavigation().moveTo((Entity)((LivingEntity)e), 1.2);
            }
        }
        return ret;
    }

    public void onStruckByLightning(LightningBoltEntity par1LightningBoltEntity) {
    }

    private void doLightningAttack(LivingEntity e) {
        if (e == null) {
            return;
        }
        float var2 = 100.0f;
        e.hurt(DamageSource.mobAttack((LivingEntity)this), var2);
        e.setSecondsOnFire(5);
        for (int var3 = 0; var3 < 20; ++var3) {
            this.level.addParticle(ParticleTypes.SMOKE, e.getX() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), e.getY() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), e.getZ() + (double)this.random.nextFloat(), 0.0, 0.0, 0.0);
            this.level.addParticle(ParticleTypes.SMOKE, e.getX() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), e.getY() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), e.getZ() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), 0.0, 0.0, 0.0);
            this.level.addParticle(ParticleTypes.FIREWORK, e.getX(), e.getY(), e.getZ(), this.level.random.nextGaussian(), this.level.random.nextGaussian(), this.level.random.nextGaussian());
        }
        this.level.playSound(null, e.getX(), e.getY(), e.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, SoundCategory.HOSTILE, 0.5f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.5f);
        if (!this.level.isClientSide) {
            this.level.explode(this, e.getX(), e.getY(), e.getZ(), 3.0f, this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
        }
        LightningBoltEntity bolt1 = EntityType.LIGHTNING_BOLT.create(this.level);
        if (bolt1 != null) {
            bolt1.moveTo(e.getX(), e.getY() + 1.0, e.getZ(), 0.0f, 0.0f);
            this.level.addFreshEntity(bolt1);
        }
        LightningBoltEntity bolt2 = EntityType.LIGHTNING_BOLT.create(this.level);
        if (bolt2 != null) {
            bolt2.moveTo(this.getX(), this.getY() + 15.0, this.getZ(), 0.0f, 0.0f);
            this.level.addFreshEntity(bolt2);
        }
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        ItemStack is = null;
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var5 = 50 + this.level.random.nextInt(30);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(ChaosPersists.MyGodzillaScale, 1);
        }
        var5 = 100 + this.level.random.nextInt(160);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
        var5 = 50 + this.level.random.nextInt(60);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.BONE, 1);
        }
        int i = 25 + this.level.random.nextInt(15);
        block80 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(80);
            switch (var3) {
                case 0: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateSword, 1);
                    continue block80;
                }
                case 1: {
                    is = this.dropItemRand(Items.DIAMOND, 1);
                    continue block80;
                }
                case 2: {
                    is = this.dropItemRand(Blocks.DIAMOND_BLOCK.asItem(), 1);
                    continue block80;
                }
                case 3: {
                    is = this.dropItemRand(Items.DIAMOND_SWORD, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(18), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(19), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(21), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(20), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 4: {
                    is = this.dropItemRand(Items.DIAMOND_SHOVEL, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 5: {
                    is = this.dropItemRand(Items.DIAMOND_PICKAXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 6: {
                    is = this.dropItemRand(Items.DIAMOND_AXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 7: {
                    is = this.dropItemRand(Items.DIAMOND_HOE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 8: {
                    is = this.dropItemRand((Item)Items.DIAMOND_HELMET, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(2));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 9: {
                    is = this.dropItemRand((Item)Items.DIAMOND_CHESTPLATE, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 10: {
                    is = this.dropItemRand((Item)Items.DIAMOND_LEGGINGS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 11: {
                    is = this.dropItemRand((Item)Items.DIAMOND_BOOTS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 12: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateBow, 1);
                    continue block80;
                }
                case 13: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateAxe, 1);
                    continue block80;
                }
                case 14: {
                    is = this.dropItemRand(Items.IRON_INGOT, 1);
                    continue block80;
                }
                case 15: {
                    is = this.dropItemRand(ChaosPersists.MyUltimatePickaxe, 1);
                    continue block80;
                }
                case 16: {
                    is = this.dropItemRand(Items.IRON_SWORD, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(18), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(19), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(21), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(20), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 17: {
                    is = this.dropItemRand(Items.IRON_SHOVEL, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 18: {
                    is = this.dropItemRand(Items.IRON_PICKAXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 19: {
                    is = this.dropItemRand(Items.IRON_AXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 20: {
                    is = this.dropItemRand(Items.IRON_HOE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 21: {
                    is = this.dropItemRand((Item)Items.IRON_HELMET, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 22: {
                    is = this.dropItemRand((Item)Items.IRON_CHESTPLATE, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 23: {
                    is = this.dropItemRand((Item)Items.IRON_LEGGINGS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 24: {
                    is = this.dropItemRand((Item)Items.IRON_BOOTS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 25: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateShovel, 1);
                    continue block80;
                }
                case 26: {
                    this.dropItemRand(Blocks.IRON_BLOCK.asItem(), 1);
                    continue block80;
                }
                case 27: {
                    is = this.dropItemRand(Items.GOLD_NUGGET, 1);
                    continue block80;
                }
                case 28: {
                    is = this.dropItemRand(Items.GOLD_INGOT, 1);
                    continue block80;
                }
                case 29: {
                    is = this.dropItemRand(Items.GOLDEN_CARROT, 1);
                    continue block80;
                }
                case 30: {
                    is = this.dropItemRand(Items.GOLDEN_SWORD, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(18), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(19), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(21), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(20), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 31: {
                    is = this.dropItemRand(Items.GOLDEN_SHOVEL, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 32: {
                    is = this.dropItemRand(Items.GOLDEN_PICKAXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 33: {
                    is = this.dropItemRand(Items.GOLDEN_AXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 34: {
                    is = this.dropItemRand(Items.GOLDEN_HOE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 35: {
                    is = this.dropItemRand((Item)Items.GOLDEN_HELMET, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 36: {
                    is = this.dropItemRand((Item)Items.GOLDEN_CHESTPLATE, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 37: {
                    is = this.dropItemRand((Item)Items.GOLDEN_LEGGINGS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 38: {
                    is = this.dropItemRand((Item)Items.GOLDEN_BOOTS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 39: {
                    this.dropItemRand(Items.GOLDEN_APPLE, 1);
                    continue block80;
                }
                case 40: {
                    this.dropItemRand(Blocks.GOLD_BLOCK.asItem(), 1);
                    continue block80;
                }
                case 41: {
                    ItemEntity var33 = null;
                    is = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1);
                    var33 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), is);
                    if (var33 == null) continue block80;
                    this.level.addFreshEntity((Entity)var33);
                    continue block80;
                }
                case 42: {
                    is = this.dropItemRand(ChaosPersists.MyExperienceSword, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(18), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(19), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(21), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(20), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 43: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceHelmet, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 44: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceBody, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 45: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceLegs, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 46: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceBoots, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 47: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystSword, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(18), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(19), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(21), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(20), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 48: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystShovel, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 49: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystPickaxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 50: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystAxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 51: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystHoe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 52: {
                    is = this.dropItemRand(ChaosPersists.MyBlockAmethystBlock.asItem(), 1);
                    continue block80;
                }
                case 53: {
                    is = this.dropItemRand((Item)ChaosPersists.AmethystHelmet, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 54: {
                    is = this.dropItemRand((Item)ChaosPersists.AmethystBody, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 55: {
                    is = this.dropItemRand((Item)ChaosPersists.AmethystLegs, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 56: {
                    is = this.dropItemRand((Item)ChaosPersists.AmethystBoots, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 57: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyHelmet, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 58: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyBody, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 59: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyLegs, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 60: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyBoots, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 61: {
                    is = this.dropItemRand(ChaosPersists.MyRubySword, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(18), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(19), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(21), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(20), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 62: {
                    is = this.dropItemRand(ChaosPersists.MyRubyShovel, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 63: {
                    is = this.dropItemRand(ChaosPersists.MyRubyPickaxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 64: {
                    is = this.dropItemRand(ChaosPersists.MyRubyAxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 65: {
                    is = this.dropItemRand(ChaosPersists.MyRubyHoe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 66: {
                    is = this.dropItemRand(ChaosPersists.MyBlockRubyBlock.asItem(), 1);
                    continue block80;
                }
                case 67: {
                    is = this.dropItemRand((Item)ChaosPersists.UltimateHelmet, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 68: {
                    is = this.dropItemRand((Item)ChaosPersists.UltimateBody, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 69: {
                    is = this.dropItemRand((Item)ChaosPersists.UltimateLegs, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 70: {
                    is = this.dropItemRand((Item)ChaosPersists.UltimateBoots, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block80;
                }
                case 71: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateShovel, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 73: {
                    is = this.dropItemRand(ChaosPersists.MyUltimatePickaxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 74: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateAxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block80;
                }
                case 75: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateHoe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block80;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    break;
                }
            }
        }
    }
}

