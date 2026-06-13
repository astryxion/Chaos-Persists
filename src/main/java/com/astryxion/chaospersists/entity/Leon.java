/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.Leon
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.block.Block
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.EnderDragonEntity
 *  net.minecraft.entity.boss.EnderDragonPartEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
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
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
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

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import com.google.common.base.Predicate;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.client.CInputPacket;
import net.minecraft.network.play.client.CPlayerPacket;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;
import javax.annotation.Nullable;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.particles.ParticleTypes;

public class Leon
extends TameableEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Leon.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> INT20 = EntityDataManager.defineId(Leon.class, DataSerializers.INT);
    private static final DataParameter<Integer> INT21 = EntityDataManager.defineId(Leon.class, DataSerializers.INT);
    private static final DataParameter<Integer> INT22 = EntityDataManager.defineId(Leon.class, DataSerializers.INT);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double boatYawHead;
    private double velocityX;
    private double velocityY;
    private double velocityZ;
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private int wing_sound = 0;
    private BlockPos currentFlightTarget = null;
    private boolean target_in_sight = false;
    private int owner_flying = 0;
    private int flyaway = 0;
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private int unstick_timer = 0;
    private float moveSpeed = 0.25f;
    private float deltasmooth = 0.0f;

    public Leon(EntityType<? extends Leon> type, World par1World) {
        super(type, par1World);
                this.xpReward = 300;
                
        this.setOrderedToSit(false);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner((TameableEntity)this, 1.1f, 16.0f, 2.0f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, net.minecraft.item.crafting.Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(3, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(4, new LookAtGoal(this, LivingEntity.class, 9.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal((CreatureEntity)this, LivingEntity.class, 0, true, false, (com.google.common.base.Predicate<LivingEntity>)null));
        }
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.ejectPassengers();
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
    }

    public Leon(World par1World, double par2, double par4, double par6) {
        this((EntityType<? extends Leon>)ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leonopteryx")), par1World);
        this.setPos(par2, par4 + (double)this.getMyRidingOffset(), par6);
        this.setDeltaMovement(0.0, 0.0, 0.0);
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Leon_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 55.0)
                .build();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return null;
    }

    public boolean shouldRiderSit() {
        return true;
    }

    public int getTrackingRange() {
        return 64;
    }

    public int getUpdateFrequency() {
        return 10;
    }

    public boolean sendsVelocityUpdates() {
        return true;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    /**
     * Same pipeline as {@link Dragon}: server needs rider input from {@code travel()}, synced via
     * {@code CPacketInput} on the client. Ambient flight sets {@code motion*} in {@code fly_without_rider}
     * and must not use uncapped obstruction loops (those could hang the game).
     */
    @Override
    public void travel(Vector3d travelVector) {
        float strafe = (float) travelVector.x;
        float vertical = (float) travelVector.y;
        float forward = (float) travelVector.z;
        if (!this.getPassengers().isEmpty() && this.getControllingPassenger() instanceof PlayerEntity && this.getActivity() != 0) {
            PlayerEntity pp = (PlayerEntity)this.getControllingPassenger();
            if (pp.removed) {
                this.ejectPassengers();
                this.setNoGravity(false);
                super.travel(travelVector);
                return;
            }
            this.setNoGravity(true);
            List list;
            Entity listEntity;

            if (this.getDeltaMovement().x < -2.0) this.setDeltaMovement(-2.0, this.getDeltaMovement().y, this.getDeltaMovement().z);
            if (this.getDeltaMovement().x > 2.0) this.setDeltaMovement(2.0, this.getDeltaMovement().y, this.getDeltaMovement().z);
            if (this.getDeltaMovement().z < -2.0) this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, -2.0);
            if (this.getDeltaMovement().z > 2.0) this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, 2.0);
            double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);

            double gh = 1.55;
            Block bid = this.level.getBlockState(new BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock();
            if (bid != Blocks.AIR) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.03, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.1);
            } else {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.018), 0.0);
            }

            double obstruction_factor = 0.0;
            int distLimit = 3 + (int)(velocity * 7.0);
            if (distLimit < 3) distLimit = 3;
            if (distLimit > 24) distLimit = 24;
            int iMax = Math.min(distLimit * 2, 48);
            for (int k = 1; k < distLimit; k++) {
                for (int i = 1; i < iMax; i++) {
                    double dx = i * Math.cos(Math.toRadians(this.yRot + 90.0f));
                    double dz = i * Math.sin(Math.toRadians(this.yRot + 90.0f));
                    bid = this.level.getBlockState(new BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + dz))).getBlock();
                    if (bid != Blocks.AIR) {
                        obstruction_factor += 0.05;
                    }
                }
            }
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.07, 0.0);
            com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.07);
            if (this.getDeltaMovement().y > 2.0) this.setDeltaMovement(this.getDeltaMovement().x, 2.0, this.getDeltaMovement().z);

            double d4 = pp.yRot;
            d4 %= 360.0;
            while (d4 < 0.0) d4 += 360.0;
            double d5 = this.yRot;
            d5 %= 360.0;
            while (d5 < 0.0) d5 += 360.0;
            double relative_g = (d4 - d5) % 180.0;
            while (relative_g < 0.0) relative_g += 180.0;
            if (relative_g > 90.0) relative_g -= 180.0;

            if (velocity > 0.01) {
                d4 = 1.85 - velocity;
                d4 = Math.abs(d4);
                if (d4 < 0.01) d4 = 0.01;
                if (d4 > 0.9) d4 = 0.9;
                this.yRot = pp.yRot + (float)(relative_g * d4);
            } else {
                this.yRot = pp.yRot;
            }
            relative_g = Math.abs(relative_g) * velocity;
            if (relative_g > 50.0) relative_g = 0.0;

            this.xRot = 2.0f * (float)velocity;
            this.yHeadRot = this.yRot;

            double max_speed = 1.15;
            double newvelocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
            float im = pp.yya;

            boolean riderJumping = false;
            try {
                Boolean b = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, pp, "jumping");
                if (b != null) {
                    riderJumping = b;
                }
            } catch (Exception ignored) {
            }
            if (riderJumping || ChaosPersists.flyup_keystate != 0) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.035, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, velocity * 0.038, 0.0);
            }

            double deltav = 0.0;
            if (Math.abs(im) > 0.001f) {
                if (im > 0.0f) {
                    deltav = 0.028;
                    if (max_speed > 1.0) deltav += 0.06;
                    if (this.deltasmooth < 0.0f) this.deltasmooth = 0.0f;
                    this.deltasmooth = (float)(this.deltasmooth + deltav / 10.0);
                    if ((double)this.deltasmooth > deltav) this.deltasmooth = (float)deltav;
                } else {
                    max_speed = 0.35;
                    deltav = -0.02;
                    if (this.deltasmooth > 0.0f) this.deltasmooth = 0.0f;
                    this.deltasmooth = (float)(this.deltasmooth + deltav / 10.0);
                    if ((double)this.deltasmooth < deltav) this.deltasmooth = (float)deltav;
                }
                newvelocity += this.deltasmooth;
                if (newvelocity >= 0.0) {
                    if (newvelocity > max_speed) newvelocity = max_speed;
                    this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 90.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 90.0f)) * newvelocity);
                } else {
                    if (newvelocity < -max_speed) newvelocity = -max_speed;
                    newvelocity = -newvelocity;
                    this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 270.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 270.0f)) * newvelocity);
                }
            } else if (newvelocity >= 0.0) {
                this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 90.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 90.0f)) * newvelocity);
            } else {
                this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 270.0f)) * (newvelocity * -1.0), this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 270.0f)) * (newvelocity * -1.0));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.985, 0.94, 0.985);

            if (!this.level.isClientSide) {
                list = this.level.getEntitiesOfClass(Entity.class, this.getBoundingBox().inflate(2.25, 2.0, 2.25), entity -> entity != this && entity.isAlive() && entity.isPushable());
                if (list != null && !list.isEmpty()) {
                    for (int l = 0; l < list.size(); ++l) {
                        listEntity = (Entity)list.get(l);
                        if (listEntity == pp || !listEntity.isAlive() || !listEntity.isPushable()) continue;
                        listEntity.push(this);
                    }
                }
            }
            return;
        }
        this.setNoGravity(false);
        super.travel(travelVector);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.level.isClientSide && this.getPassengers().isEmpty()) {
            this.setNoGravity(false);
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
            this.moveTowardsClosestSpace(this.getX(), this.getY(), this.getZ());
            MyUtils.enforceDragonMountGroundSafety(this);
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(INT20, 0);
        this.entityData.define(INT21, 0);
        this.entityData.define(INT22, 0);
        this.setActivity(0);
        this.setAttacking(0);
        this.setBeingRidden(0);
        this.setTame(false);
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

    public int mygetMaxHealth() {
        return 250;
    }

    public int getLeonHealth() {
        return (int)this.getHealth();
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

    public int getArmorValue() {
        return 16;
    }

    protected void jumpFromGround() {
        super.jumpFromGround();
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.25, 0.0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.isOrderedToSit()) {
            return null;
        }
        if (this.getActivity() == 1 && this.getControllingPassenger() == null) {
            return com.astryxion.chaospersists.core.ChaosSounds.LEON_LIVING;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.LEON_HIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.LEON_DEATH;
    }

    protected float getSoundVolume() {
        return 1.75f;
    }

    public float getVoicePitch() {
        return 0.85f;
    }

    public boolean canBePushed() {
        return false;
    }

    public double getMountedYOffset() {
        return 3.75;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), this.getY() + 2.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), is);
        if (var3 != null) {
            this.level.addFreshEntity(var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        int i = 4 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.CHICKEN, 1);
        }
        i = 16 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.FEATHER, 1);
        }
        i = 2 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.KrakenRepellent.asItem(), 1);
        }
        if (this.level.random.nextInt(5) == 1) {
            this.dropItemRand(ChaosPersists.MyBattleAxe, 1);
        }
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        double ks = 1.25;
        double inair = 0.15;
        float iskraken = 1.0f;
        if (par1Entity != null && par1Entity instanceof EnderDragonEntity) {
            EnderDragonEntity dr = (EnderDragonEntity)par1Entity;
            DamageSource var21 = DamageSource.explosion((net.minecraft.world.Explosion)null);
            if (this.level.random.nextInt(6) == 1) {
                dr.hurt(var21, 55.0f);
            } else {
                dr.hurt(var21, 55.0f);
            }
        } else if (par1Entity != null && par1Entity instanceof LivingEntity) {
            if (par1Entity instanceof Kraken) {
                iskraken = 4.0f;
            }
            par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), iskraken * 55.0f);
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            if (!par1Entity.isAlive() || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = null;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return ret;
        }
        if (!this.level.isClientSide) {
            this.setOrderedToSit(false);
        }
        if (!this.level.isClientSide) {
            this.setActivity(1);
        }
        if ((e = par1DamageSource.getEntity()) != null && e instanceof Leon) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        this.hurt_timer = 15;
        if (e != null && e instanceof LivingEntity && !this.level.isClientSide) {
            if (this.isTame() && e instanceof PlayerEntity) {
                return false;
            }
            this.setTarget((LivingEntity)e);
            this.getNavigation().moveTo(((LivingEntity)e), 1.2);
            ret = true;
        }
        return ret;
    }

    @Override
    protected void customServerAiStep() {
        if (this.level.isClientSide) {
            return;
        }
        if (this.getActivity() == 0 || this.getControllingPassenger() != null) {
            super.customServerAiStep();
        }
        if (this.level.random.nextInt(200) == 1) {
            this.setTarget(null);
        }
    }

    public void fly_with_rider() {
        LivingEntity e = null;
        int freq = 7;
        if (!this.isAlive()) {
            return;
        }
        if (this.isOrderedToSit()) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        if (this.level.random.nextInt(freq) == 1 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                this.setAttacking(1);
                if (this.distanceToSqr((Entity)e) < (double)((9.0f + e.getBbWidth() / 2.0f) * (9.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget((LivingEntity)e);
                }
                return;
            }
            this.setAttacking(0);
        }
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (ChaosPersists.PlayNicely != 0) {
            return false;
        }
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
        if (par1Mob instanceof Leon) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            if (this.isTame()) {
                return false;
            }
            return true;
        }
        if (!this.isTame() && MyUtils.isAttackableNonMob((LivingEntity)par1Mob)) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0, 20.0, 20.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false)) continue;
            return var4;
        }
        return null;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileMonsterspawner = null;
                    tileMonsterspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileMonsterspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Leonopteryx")) continue;
                    return true;
                }
            }
        }
        if (this.level.random.nextInt(16) != 0) {
            return false;
        }
        Leon target = null;
        if (!this.level.isDay()) {
            return false;
        }
        List<Leon> nearby = this.level.getEntitiesOfClass(Leon.class, this.getBoundingBox().inflate(48.0, 16.0, 48.0), e -> e != this);
        if (!nearby.isEmpty()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d((double)this.getX(), (double)(this.getY() + 0.75), (double)this.getZ()), new Vector3d((double)pX, (double)pY, (double)pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.setPos(par1, par3, par5);
        this.yRot = par7;
        this.xRot = par8;
        this.boatPosRotationIncrements = par9;
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.boatYawHead = par7;
    }

    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        this.setDeltaMovement(par1, par3, par5);
    }

    public void tick() {
        LivingEntity e = null;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        if (!this.level.isClientSide && this.getActivity() != 0 && this.getControllingPassenger() == null && !this.isOrderedToSit()) {
            this.fly_without_rider();
        }
        super.tick();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.getActivity() == 1) {
            ++this.wing_sound;
            if (this.wing_sound > 20) {
                if (!this.level.isClientSide) {
                    this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, net.minecraft.util.SoundCategory.NEUTRAL, 0.5f, 1.0f);
                }
                this.wing_sound = 0;
            }
        }
        if (this.isInWater()) {
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.07, 0.0);
        }
        if (this.level.isClientSide) {
            return;
        }
        if (this.getActivity() == 0 && this.isTame() && this.getOwner() != null && !this.isOrderedToSit() && (e = this.getOwner()) != null && this.distanceToSqr((Entity)e) > 144.0) {
            this.setActivity(1);
        }
    }

    private void fly_without_rider() {
        Block bid;
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        boolean do_new = false;
        double ox = 0.0;
        double oy = 0.0;
        double oz = 0.0;
        boolean has_owner = false;
        LivingEntity e = null;
        double speed_factor = 0.5;
        double var1 = 0.0;
        double var3 = 0.0;
        double var5 = 0.0;
        double obstruction_factor = 0.0;
        boolean toofar = false;
        if (this.level.isClientSide) {
            return;
        }
        if (this.currentFlightTarget == null) {
            do_new = true;
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.isOrderedToSit()) {
            return;
        }
        if (this.getControllingPassenger() != null) {
            return;
        }
        if (this.unstick_timer > 0) {
            --this.unstick_timer;
        }
        if (this.lastX == (int)this.getX() && this.lastZ == (int)this.getZ()) {
            ++this.stuck_count;
            if (this.stuck_count > 50) {
                this.stuck_count = 0;
                this.unstick_timer = 100;
                this.target_in_sight = false;
                this.setAttacking(0);
                this.setActivity(1);
                do_new = true;
            }
        } else {
            this.stuck_count = 0;
            this.lastX = (int)this.getX();
            this.lastZ = (int)this.getZ();
        }
        double flightMotionY = this.getDeltaMovement().y;
        if (this.getY() < (double)this.currentFlightTarget.getY() + 2.0) {
            flightMotionY *= 0.7;
        } else if (this.getY() > (double)this.currentFlightTarget.getY() - 2.0) {
            flightMotionY *= 0.5;
        } else {
            flightMotionY *= 0.61;
        }
        this.setDeltaMovement(this.getDeltaMovement().x, flightMotionY, this.getDeltaMovement().z);
        if (this.level.random.nextInt(300) == 1) {
            do_new = true;
        }
        if (this.isTame() && this.getOwner() != null) {
            e = this.getOwner();
            has_owner = true;
            ox = e.getX();
            oy = e.getY();
            oz = e.getZ();
            if (this.distanceToSqr((Entity)e) > 144.0) {
                toofar = true;
                this.target_in_sight = false;
                this.setAttacking(0);
                this.flyaway = 0;
                do_new = true;
            }
        }
        if (this.flyaway > 0) {
            --this.flyaway;
        }
        if (!toofar && this.unstick_timer == 0 && this.flyaway == 0 && this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(8) == 1) {
            e = this.findSomethingToAttack();
            if (e != null) {
                if (this.isTame() && this.getHealth() / (float)this.mygetMaxHealth() < 0.25f) {
                    this.setActivity(1);
                    this.setAttacking(0);
                    this.target_in_sight = false;
                    do_new = false;
                    this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)(this.getX() + (this.getX() - e.getX())), (int)(this.getY() + 1.0), (int)(this.getZ() + (this.getZ() - e.getZ())));
                } else {
                    this.setActivity(1);
                    this.setAttacking(1);
                    this.target_in_sight = true;
                    this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)(e.getY() + 1.0), (int)e.getZ());
                    do_new = false;
                    if (this.distanceToSqr((Entity)e) < (double)((7.0f + e.getBbWidth() / 2.0f) * (7.0f + e.getBbWidth() / 2.0f))) {
                        this.doHurtTarget((LivingEntity)e);
                    }
                }
            } else {
                this.target_in_sight = false;
                this.flyaway = 0;
                this.setAttacking(0);
            }
        }
        if (this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 4.1) {
            do_new = true;
        }
        if (do_new && !this.target_in_sight || do_new && this.flyaway != 0) {
            bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                int gox = (int)this.getX();
                int goy = (int)this.getY();
                int goz = (int)this.getZ();
                if (has_owner && this.unstick_timer == 0) {
                    gox = (int)ox;
                    goy = (int)oy;
                    goz = (int)oz;
                    if (this.owner_flying == 0) {
                        zdir = this.level.random.nextInt(12) + 6;
                        xdir = this.level.random.nextInt(12) + 6;
                    } else {
                        zdir = this.level.random.nextInt(8);
                        xdir = this.level.random.nextInt(8);
                    }
                } else {
                    zdir = this.level.random.nextInt(20) + 6;
                    xdir = this.level.random.nextInt(20) + 6;
                }
                if (this.level.random.nextInt(2) == 1) {
                    zdir = - zdir;
                }
                if (this.level.random.nextInt(2) == 1) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos(gox + xdir, goy + this.level.random.nextInt(9 + this.owner_flying * 2) - 4, goz + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        }
        obstruction_factor = 0.0;
        double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        int kMax = 2 + (int)(Math.max(0.0, velocity) * 4.0);
        if (kMax < 2) {
            kMax = 2;
        }
        if (kMax > 24) {
            kMax = 24;
        }
        for (int k = 1; k < kMax; ++k) {
            for (int i = 1; i < 4; ++i) {
                double dz;
                double dx = (double)i * Math.cos(Math.toRadians(this.yRot + 90.0f));
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + (dz = (double)i * Math.sin(Math.toRadians(this.yRot + 90.0f)))))).getBlock();
                if (bid == Blocks.AIR) continue;
                obstruction_factor += 0.05;
            }
        }
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.05, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.05);
        speed_factor = 0.5;
        var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        if (this.owner_flying != 0) {
            speed_factor = 1.75;
            if (this.isTame() && this.getOwner() != null && this.distanceToSqr((Entity)(e = this.getOwner())) > 49.0) {
                speed_factor = 3.5;
            }
        }
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) - this.getDeltaMovement().x) * 0.15 * speed_factor, (Math.signum(var3) - this.getDeltaMovement().y) * 0.21 * speed_factor, (Math.signum(var5) - this.getDeltaMovement().z) * 0.15 * speed_factor);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = (float)(0.75 * speed_factor);
        this.yRot += var8 / 5.0f;
    }

    @Override
    public void aiStep() {
        if (!this.isAlive()) {
            super.aiStep();
            return;
        }
        super.aiStep();
        if (this.level.isClientSide) {
            if (this.getActivity() != 0 && this.getControllingPassenger() instanceof PlayerEntity) {
                Entity rider = this.getControllingPassenger();
                if (rider instanceof ClientPlayerEntity) {
                    ClientPlayerEntity pp = (ClientPlayerEntity)rider;
                    pp.connection.send(new CPlayerPacket.RotationPacket(pp.yRot, pp.xRot, pp.isOnGround()));
                    pp.connection.send(new CInputPacket(pp.xxa, pp.yya, pp.input.jumping, pp.input.shiftKeyDown));
                }
            }
            if (this.boatPosRotationIncrements > 0 && this.getActivity() != 0) {
                double d4 = this.getX() + (this.boatX - this.getX()) / (double)this.boatPosRotationIncrements;
                double d5 = this.getY() + (this.boatY - this.getY()) / (double)this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double)this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                this.xRot = (float)((double)this.xRot + (this.boatPitch - (double)this.xRot) / (double)this.boatPosRotationIncrements);
                double d10 = (double)MathHelper.wrapDegrees((float)(this.boatYaw - (double)this.yRot));
                if (this.getControllingPassenger() != null) {
                    d10 = (double)MathHelper.wrapDegrees((float)((double)this.getControllingPassenger().yRot - (double)this.yRot));
                }
                this.yRot = (float)((double)this.yRot + d10 / (double)this.boatPosRotationIncrements);
                this.yHeadRot = this.yRot;
                --this.boatPosRotationIncrements;
            }
        } else {
            if (this.getControllingPassenger() != null) {
                this.setBeingRidden(1);
            } else {
                this.setBeingRidden(0);
            }
            if (this.getActivity() != 0 && this.getControllingPassenger() != null) {
                this.fly_with_rider();
            }
            this.always_do();
        }
    }

    public void always_do() {
        LivingEntity e = null;
        PlayerEntity pl = null;
        if (this.level.isClientSide) {
            return;
        }
        if (!this.isOrderedToSit() && this.getActivity() == 0 && this.getControllingPassenger() == null && this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(10) == 1 && (e = this.findSomethingToAttack()) != null) {
            this.setActivity(1);
        }
        if (this.level.random.nextInt(250) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(2.0f);
        }
        if (this.isOrderedToSit()) {
            return;
        }
        this.owner_flying = 0;
        if (this.isTame() && this.getOwner() != null && this.getControllingPassenger() == null && !this.isOrderedToSit()) {
            pl = (PlayerEntity)this.getOwner();
            if (pl.abilities.flying) {
                this.owner_flying = 1;
                this.setActivity(1);
            }
        }
        if (this.isTame() && this.getOwner() != null && !this.isOrderedToSit() && this.distanceToSqr((Entity)(pl = (PlayerEntity)this.getOwner())) > 400.0) {
            this.setActivity(1);
        }
        if (this.level.random.nextInt(50) == 1 && !this.isOrderedToSit() && !this.target_in_sight && this.getControllingPassenger() == null) {
            if (this.level.random.nextInt(15) == 1) {
                this.setActivity(1);
            }
        }
    }

    public void updateRiderPosition() {
        if (this.getControllingPassenger() != null) {
            float f = 0.65f;
            this.getControllingPassenger().setPos(this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + this.getMountedYOffset() + this.getControllingPassenger().getMyRidingOffset(), this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)));
        }
    }

    protected void playTameEffect(boolean par1) {
        String s = "heart";
        if (!par1) {
            s = "smoke";
        }
        for (int i = 0; i < 20; ++i) {
            double d0 = this.random.nextGaussian() * 0.08;
            double d1 = this.random.nextGaussian() * 0.08;
            double d2 = this.random.nextGaussian() * 0.08;
            net.minecraft.particles.BasicParticleType p = "smoke".equals(s) ? ParticleTypes.SMOKE : ParticleTypes.HEART;
            this.level.addParticle(p, this.getX() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), this.getY() + 0.5 + (double)this.random.nextFloat() * 1.5, this.getZ() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), d0, d1, d2);
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (var2.isEmpty()) {
            var2 = null;
        } else if (var2.getCount() <= 0) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = null;
        }
        if (var2 != null && var2.getItem() == Blocks.DIAMOND_BLOCK.asItem() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 49.0) {
            if (!this.level.isClientSide) {
                this.setTame(true);
                this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent(this, (byte)7);
                this.heal((float)this.mygetMaxHealth() - this.getHealth());
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (!this.isTame()) {
            if (var2 != null && var2.getItem() == Items.BEEF && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 49.0) {
                if (!this.level.isClientSide) {
                    if (this.level.random.nextInt(3) == 1) {
                        this.setTame(true);
                        this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.heal((float)this.mygetMaxHealth() - this.getHealth());
                    } else {
                        this.level.broadcastEntityEvent(this, (byte)6);
                        this.level.broadcastEntityEvent(this, (byte)6);
                    }
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
        } else {
            if (!this.isOwnedBy(par1PlayerEntityEntity)) {
                return ActionResultType.PASS;
            }
            if (var2 == null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 49.0) {
                if (!this.level.isClientSide) {
                    par1PlayerEntityEntity.startRiding(this);
                    this.setActivity(1);
                    this.setOrderedToSit(false);
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.BEEF && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 49.0) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)7);
                }
                if ((float)this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float)this.mygetMaxHealth() - this.getHealth());
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Blocks.DEAD_BUSH.asItem() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 49.0) {
                if (!this.level.isClientSide) {
                    this.setTame(false);
                    this.setOwnerUUID((UUID)null);
                    this.level.broadcastEntityEvent(this, (byte)6);
                    this.level.broadcastEntityEvent(this, (byte)6);
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (this.isTame() && var2 != null && var2.getItem() == Items.NAME_TAG && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 49.0 && this.isOwnedBy(par1PlayerEntityEntity)) {
                this.setCustomName(var2.getHoverName());
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 49.0 && this.getControllingPassenger() == null) {
                if (!this.isOrderedToSit()) {
                    this.setOrderedToSit(true);
                    this.setActivity(0);
                } else {
                    this.setOrderedToSit(false);
                    this.setActivity(0);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return super.mobInteract(par1PlayerEntityEntity, hand);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.BEEF;
    }

    public int getAttacking() {
        return this.entityData.get(INT20).intValue();
    }

    public void setAttacking(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(INT20, par1);
    }

    public int getActivity() {
        return this.entityData.get(INT21).intValue();
    }

    public void setActivity(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(INT21, par1);
    }

    public int getBeingRidden() {
        return this.entityData.get(INT22).intValue();
    }

    public void setBeingRidden(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(INT22, par1);
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.getControllingPassenger() != null) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("LeonAttacking", this.getAttacking());
        par1CompoundNBT.putInt("LeonActivity", this.getActivity());
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.setAttacking(par1CompoundNBT.getInt("LeonAttacking"));
        this.setActivity(par1CompoundNBT.getInt("LeonActivity"));
    }
}

