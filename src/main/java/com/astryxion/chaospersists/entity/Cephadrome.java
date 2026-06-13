/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.Cephadrome
 *  com.astryxion.chaospersists.GammaMetroid
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.Leon
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.WaterDragon
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.EnderDragonEntity
 *  net.minecraft.entity.boss.EnderDragonPartEntity
 *  net.minecraft.entity.item.ItemEntity
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
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.WaterDragon;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Cephadrome
extends CreatureEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Cephadrome.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> STATE2 = EntityDataManager.defineId(Cephadrome.class, DataSerializers.BYTE);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double boatYawHead;
    private int damage_counter = 100;
    private int updateit = 1;
    private int color = 1;
    private int playing = 0;
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private int wasfed;
    private int shouldattack = 0;
    private int wing_sound = 0;
    private int hit_by_player = 0;
    private int badmood = 0;
    private float moveSpeed = 0.25f;

    private Entity getRiddenByEntity() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public Cephadrome(EntityType<? extends Cephadrome> type, World par1World) {
        super(type, par1World);
                this.xpReward = 200;
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(2, new LookAtGoal((MobEntity)this, PlayerEntity.class, 9.0f));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
    }

    public Cephadrome(World par1World, double par2, double par4, double par6) {
        this((EntityType<? extends Cephadrome>)ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cephadrome")), par1World);
        this.setPos(par2, par4, par6);
        this.setDeltaMovement(0.0, 0.0, 0.0);
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
    }

    public static AttributeModifierMap createAttributes() {
        return CreatureEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 70.0)
                .build();
    }

    public boolean shouldRiderSit() {
        return true;
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeSteered() {
        return true;
    }

    public boolean canPassengerSteer() {
        return true;
    }

    public void updatePassenger(Entity passenger) {
        if (passenger.getVehicle() == this) {
            float f = 0.75f;
            passenger.setPos(
                this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)),
                this.getY() + this.getMountedYOffset() + passenger.getMyRidingOffset(),
                this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)));
        }
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.level.isClientSide && this.getPassengers().isEmpty()) {
            this.setActivity(0);
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
            this.setNoGravity(false);
            this.moveTowardsClosestSpace(this.getX(), this.getY(), this.getZ());
            MyUtils.enforceDragonMountGroundSafety(this);
        }
    }

    /**
     * Same steering pipeline as {@link ThePrinceTeen}: vanilla calls {@code travel} with synced rider input
     * (via {@code CPacketInput} from client). Flight used to live only in {@code onLivingUpdate}, which never ran
     * through this path, so {@code moveForward} stayed wrong on the server.
     */
    @Override
    public void travel(Vector3d travelVector) {
        float strafe = (float)travelVector.x;
        float vertical = (float)travelVector.y;
        float forward = (float)travelVector.z;
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
            double obstruction_factor = 0.0;
            double relative_g = 0.0;
            double max_speed = 1.15;
            double gh = 1.0;
            double rt = 0.0;
            double pi = 3.1415926545;
            double deltav = 0.0;
            double rdv;
            if (this.getDeltaMovement().x < -2.0) {
                this.setDeltaMovement(-2.0, this.getDeltaMovement().y, this.getDeltaMovement().z);
            }
            if (this.getDeltaMovement().x > 2.0) {
                this.setDeltaMovement(2.0, this.getDeltaMovement().y, this.getDeltaMovement().z);
            }
            if (this.getDeltaMovement().z < -2.0) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, -2.0);
            }
            if (this.getDeltaMovement().z > 2.0) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, 2.0);
            }
            double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
            gh = 1.55;
            Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock();
            if (bid != Blocks.AIR) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.07, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.1);
            } else {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.018), 0.0);
            }
            obstruction_factor = 0.0;
            // Scan radius from speed, fixed bound ? do not mutate dist in the for-condition (was infinite loop).
            int scanDist = 2 + (int)(velocity * 6.0);
            if (scanDist < 2) {
                scanDist = 2;
            }
            if (scanDist > 32) {
                scanDist = 32;
            }
            for (int k = 1; k < scanDist; ++k) {
                for (int i = 1; i < scanDist * 2; ++i) {
                    double dz;
                    double dx = (double)i * Math.cos(Math.toRadians(this.yRot + 90.0f));
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + (dz = (double)i * Math.sin(Math.toRadians(this.yRot + 90.0f)))))).getBlock();
                    if (bid == Blocks.AIR) continue;
                    obstruction_factor += 0.04;
                }
            }
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.09, 0.0);
            com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.09);
            if (this.getDeltaMovement().y > 2.0) {
                this.setDeltaMovement(this.getDeltaMovement().x, 2.0, this.getDeltaMovement().z);
            }
            double d4 = pp.yRot;
            d4 %= 360.0;
            while (d4 < 0.0) {
                d4 += 360.0;
            }
            double d5 = this.yRot;
            d5 %= 360.0;
            while (d5 < 0.0) {
                d5 += 360.0;
            }
            for (relative_g = (d4 - d5) % 180.0; relative_g < 0.0; relative_g += 180.0) {
            }
            if (relative_g > 90.0) {
                relative_g -= 180.0;
            }
            if (velocity > 0.1) {
                d4 = 1.5 - velocity;
                if ((d4 = Math.abs(d4)) < 0.01) {
                    d4 = 0.01;
                }
                if (d4 > 0.9) {
                    d4 = 0.9;
                }
                this.yRot = pp.yRot + (float)(relative_g * d4);
            } else {
                this.yRot = pp.yRot;
            }
            relative_g = Math.abs(relative_g) * velocity;
            if (relative_g > 50.0) {
                relative_g = 0.0;
            }
            this.xRot = this.getDeltaMovement().y > 0.0 ? 360.0f - 2.0f * (float)velocity : 2.0f * (float)velocity;
            this.yHeadRot = this.yRot;
            double newvelocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
            double rhm = Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x);
            double rhdir = Math.toRadians((pp.yRot + 90.0f) % 360.0f);
            rt = 0.0;
            pi = 3.1415926545;
            deltav = 0.0;
            float im = pp.yya;
            if (ChaosPersists.flyup_keystate != 0) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.04, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, velocity * 0.05, 0.0);
            }
            if ((rdv = Math.abs(rhm - rhdir) % (pi * 2.0)) > pi) {
                rdv -= pi * 2.0;
            }
            rdv = Math.abs(rdv);
            if (Math.abs(newvelocity) < 0.01) {
                rdv = 0.0;
            }
            if (rdv > 1.5) {
                newvelocity = -newvelocity;
            }
            if (Math.abs(im) > 0.0010000000474974513) {
                if (im > 0.0) {
                    deltav = 0.03;
                    if (max_speed > 0.85) {
                        deltav += 0.05;
                    }
                } else {
                    max_speed = 0.35;
                    deltav = -0.03;
                }
                if ((newvelocity += deltav) >= 0.0) {
                    if (newvelocity > max_speed) {
                        newvelocity = max_speed;
                    }
                    this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 90.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 90.0f)) * newvelocity);
                } else {
                    if (newvelocity < -max_speed) {
                        newvelocity = -max_speed;
                    }
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

    public int getTrackingRange() {
        return 128;
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

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(STATE2, (byte)0);
        this.setActivity(0);
        this.setAttacking(0);
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
        return 300;
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
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.1, 0.0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.getActivity() != 1 && this.random.nextInt(6) == 1) {
            return com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSourceIn) {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_DEATH;
    }

    protected float getSoundVolume() {
        return 1.5f;
    }

    public float getVoicePitch() {
        return 1.0f;
    }

    public boolean canBePushed() {
        return false;
    }

    public double getMountedYOffset() {
        return 2.5;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        int i = 4 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.UraniumNugget, 1);
        }
        i = 4 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
        }
        i = 1 + this.level.random.nextInt(5);
        block17 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(20);
            ItemStack is;
            switch (var3) 
            {
                case 0: {
                    is = this.dropItemRand(ChaosPersists.MyRubySword, 1);
                    continue block17;
                }
                case 1: {
                    is = this.dropItemRand(Items.DIAMOND, 1);
                    continue block17;
                }
                case 2: {
                    is = this.dropItemRand(ChaosPersists.MyThunderStaff, 1);
                    continue block17;
                }
                case 3: {
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
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 4: {
                    is = this.dropItemRand(ChaosPersists.MyRubyShovel, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 5: {
                    is = this.dropItemRand(ChaosPersists.MyRubyPickaxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 6: {
                    is = this.dropItemRand(ChaosPersists.MyRubyAxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 7: {
                    is = this.dropItemRand(ChaosPersists.MyRubyHoe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 8: {
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
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(2));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 9: {
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
                    if (this.level.random.nextInt(2) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block17;
                }
                case 10: {
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
                    if (this.level.random.nextInt(2) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block17;
                }
                case 11: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyBoots, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block17;
                }
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 17: {
                    is = this.dropItemRand(ChaosPersists.MyRuby, 1);
                    break;
                }
            }
        }
    }

    public int getCephadromeHealth() {
        return (int)this.getHealth();
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        double ks = 2.5;
        double inair = 0.35;
        float iskraken = 1.0f;
        boolean ret = false;
        if (par1Entity != null && par1Entity instanceof EnderDragonEntity) {
            EnderDragonEntity dr = (EnderDragonEntity)par1Entity;
            DamageSource var21 = null;
            var21 = DamageSource.explosion((Explosion)null);
            dr.hurt(var21, 70.0f);
            ret = true;
        } else if (par1Entity != null && par1Entity instanceof LivingEntity) {
            if (par1Entity instanceof Kraken) {
                iskraken = 1.5f;
            }
            ret = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), iskraken * 70.0f);
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return ret;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (this.hurt_timer > 0) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        this.hurt_timer = 25;
        Entity e = par1DamageSource.getEntity();
        if (!this.level.isClientSide && e != null && e instanceof LivingEntity) {
            this.setTarget((LivingEntity)e);
            if (this.getActivity() == 0) {
                this.getNavigation().moveTo((Entity)((LivingEntity)e), 1.2);
            }
        }
        if (e != null && e instanceof PlayerEntity && this.getHealth() < this.getMaxHealth() * 9.0f / 10.0f) {
            this.hit_by_player = 1;
        }
        return ret;
    }

    public double getHorizontalDistanceSqToEntity(Entity par1Entity) {
        double d0 = this.getX() - par1Entity.getX();
        double d2 = this.getZ() - par1Entity.getZ();
        return d0 * d0 + d2 * d2;
    }

    public void customServerAiStep() {
        LivingEntity e = null;
        double maxdist = 10.0;
        if (!this.isAlive()) {
            return;
        }
        if (this.updateit > 0) {
            --this.updateit;
        }
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.updateit <= 0 && !this.level.isClientSide) {
            this.updateit = 30;
            if (this.getRiddenByEntity() != null) {
                this.setActivity(1);
            } else {
                this.setActivity(0);
            }
        }
        if (this.level.random.nextInt(100) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(2.0f);
        }
        if (this.getActivity() == 0) {
            super.customServerAiStep();
        }
        if (this.level.random.nextInt(7) == 1 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                if (this.getActivity() == 0) {
                    this.getNavigation().moveTo((Entity)e, 1.7);
                    maxdist = 6.0;
                }
                this.lookAt((Entity)e, 10.0f, 10.0f);
                this.setAttacking(1);
                if (this.distanceToSqr((Entity)e) < (maxdist + (double)(e.getBbWidth() / 2.0f)) * (maxdist + (double)(e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget((LivingEntity)e);
                } else if (e instanceof Kraken && this.getHorizontalDistanceSqToEntity((Entity)e) < (maxdist + (double)(e.getBbWidth() / 2.0f)) * (maxdist + (double)(e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget((LivingEntity)e);
                }
            } else if (this.getAttacking() != 0) {
                this.setAttacking(0);
            }
        }
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
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
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof Cephadrome) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof Mothra) {
            return true;
        }
        if (par1Mob instanceof Leon) {
            TameableEntity et = (TameableEntity)par1Mob;
            if (et.isTame()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof GammaMetroid) {
            TameableEntity et = (TameableEntity)par1Mob;
            if (et.isTame()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof WaterDragon) {
            TameableEntity et = (TameableEntity)par1Mob;
            if (et.isTame()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof EnderDragonEntity) {
            return true;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            if (this.hit_by_player != 0) {
                return true;
            }
            if (this.badmood != 0) {
                return true;
            }
            if (this.shouldattack > 0) {
                this.shouldattack = 0;
                return true;
            }
            return false;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 20.0, 16.0));
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

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        Block bid;
        int j;
        int i;
        int k;
        for (k = -3; k < 3; ++k) {
            for (j = -3; j < 3; ++j) {
                for (i = 0; i < 5; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileentitymobspawner = null;
                    tileentitymobspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Cephadrome")) continue;
                    this.badmood = 1;
                    return true;
                }
            }
        }
        if (!this.level.isDay()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        for (k = -2; k < 2; ++k) {
            for (j = -2; j < 2; ++j) {
                for (i = 1; i < 5; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        if (!this.level.getEntitiesOfClass(Cephadrome.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0), e -> e != this).isEmpty()) {
            return false;
        }
        return true;
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
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        this.noPhysics = this.getActivity() != 0;
        if (this.getActivity() == 1) {
            ++this.wing_sound;
            if (this.wing_sound > 22) {
                if (!this.level.isClientSide) {
                    this.playSound(com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, 0.5f, 1.0f);
                }
                this.wing_sound = 0;
            }
        }
        if (ChaosPersists.PlayNicely == 0) {
            this.wasfed = 1;
        }
        if (!this.level.isClientSide) {
            MyUtils.enforceDragonMountGroundSafety(this);
            if (this.getPassengers().isEmpty()) {
                this.moveTowardsClosestSpace(this.getX(), this.getY(), this.getZ());
            }
        }
    }

    public void aiStep() {
        if (!this.isAlive()) {
            super.aiStep();
            return;
        }
        super.aiStep();
        if (this.level.isClientSide) {
            if (this.getActivity() != 0 && !this.getPassengers().isEmpty()) {
                Entity rider = this.getPassengers().get(0);
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
                double d10 = MathHelper.wrapDegrees((double)(this.boatYaw - (double)this.yRot));
                if (!this.getPassengers().isEmpty()) {
                    d10 = MathHelper.wrapDegrees((double)((double)this.getPassengers().get(0).yRot - (double)this.yRot));
                }
                this.yRot = (float)((double)this.yRot + d10 / (double)this.boatPosRotationIncrements);
                this.yHeadRot = this.yRot;
                --this.boatPosRotationIncrements;
            }
        }
    }

    public void updateRiderPosition() {
        if (this.getRiddenByEntity() != null) {
            float f = 0.75f;
            this.getRiddenByEntity().setPos(this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + this.getMountedYOffset(), this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)));
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
            this.level.addParticle(par1 ? ParticleTypes.HEART : ParticleTypes.SMOKE, this.getX() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), this.getY() + 0.5 + (double)this.random.nextFloat() * 1.5, this.getZ() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), d0, d1, d2);
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (var2.isEmpty()) {
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty() && (var2.getItem() == Items.BEEF || var2.getItem() == Items.CHICKEN || var2.getItem() == Items.PORKCHOP) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
            if (!this.level.isClientSide) {
                this.heal((float)this.mygetMaxHealth() - this.getHealth());
            }
            this.wasfed = 1;
            this.shouldattack = 0;
            this.level.broadcastEntityEvent(this, (byte)7);
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        } else {
            if (this.getRiddenByEntity() != null && this.getRiddenByEntity() instanceof PlayerEntity && this.getRiddenByEntity() != par1PlayerEntityEntity) {
                return ActionResultType.PASS;
            }
            if (var2.isEmpty() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && !this.level.isClientSide) {
                if (this.wasfed == 0) {
                    this.getNavigation().moveTo((Entity)par1PlayerEntityEntity, 1.2);
                    this.shouldattack = 1;
                    return ActionResultType.FAIL;
                }
                par1PlayerEntityEntity.startRiding(this);
                this.wasfed = 0;
                this.setActivity(1);
            }
            return ActionResultType.SUCCESS;
        }
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setAttacking(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public int getActivity() {
        return this.entityData.get(STATE2).byteValue();
    }

    public void setActivity(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(STATE2, (byte)par1);
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.getRiddenByEntity() != null) {
            return false;
        }
        return true;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("CephaWasFed", this.wasfed);
        par1CompoundNBT.putInt("CephaAttacking", this.getAttacking());
        par1CompoundNBT.putInt("CephaActivity", this.getActivity());
        par1CompoundNBT.putInt("CephaHitByPlayerEntity", this.hit_by_player);
        par1CompoundNBT.putInt("CephaBadMood", this.badmood);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.wasfed = par1CompoundNBT.getInt("CephaWasFed");
        this.hit_by_player = par1CompoundNBT.getInt("CephaHitByPlayerEntity");
        this.badmood = par1CompoundNBT.getInt("CephaBadMood");
        this.setAttacking(par1CompoundNBT.getInt("CephaAttacking"));
        this.setActivity(par1CompoundNBT.getInt("CephaActivity"));
    }
}

