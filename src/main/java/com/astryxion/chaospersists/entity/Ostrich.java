/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.MyEntityAIAvoidEntity
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Ostrich
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.block.Block
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.AnimalEntity
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
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
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
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.EntityPredicate;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityPredicate;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.entity.EntityCannonFodder;
import com.astryxion.chaospersists.util.MyEntityAIAvoidEntity;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class Ostrich
extends EntityCannonFodder {
    private float moveSpeed = 0.2f;
    private RenderInfo renderdata = new RenderInfo();
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
    float deltasmooth = 0.0f;
    private int didjump = 0;

    public Ostrich(EntityType<? extends Ostrich> type, World par1World) {
        super(type, par1World);
        this.moveSpeed = 0.38f;
                        this.setOrderedToSit(false);
        this.xpReward = 10;
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal((AnimalEntity)this, 1.0));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner((TameableEntity)this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(3, new MyEntityAIAvoidEntity((CreatureEntity)this, MonsterEntity.class, 8.0f, 1.0, 1.899999976158142));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2000000476837158, net.minecraft.item.crafting.Ingredient.of(Items.APPLE), false));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(7, new LookAtGoal(this, LivingEntity.class, 5.0f));
        this.goalSelector.addGoal(8, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(10, new RestrictSunGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .build();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return (AgeableEntity)this.getType().create(level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.setOrderedToSit(false);
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

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        if (this.isAlive() && this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.isAlive() && this.level.random.nextInt(250) == 0) {
            this.heal(1.0f);
        }
        super.tick();
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            super.hurt(par1DamageSource, par2);
        }
        return false;
    }


    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    public int mygetMaxHealth() {
        return 25;
    }

    public int getOstrichHealth() {
        return (int)this.getHealth();
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        ActionResultType superResult = super.mobInteract(par1PlayerEntityEntity, hand);
        if (superResult.consumesAction()) {
            return superResult;
        }
        if (var2 != null && !var2.isEmpty() && var2.getItem() == Items.APPLE && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level.isClientSide) {
                    if (this.random.nextInt(2) == 0) {
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
            } else if (this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)7);
                }
                if ((float)this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float)this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && !var2.isEmpty() && var2.getItem() == Blocks.DEAD_BUSH.asItem() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.setTame(false);
                this.setOwnerUUID((UUID)null);
                this.level.broadcastEntityEvent(this, (byte)6);
                this.level.broadcastEntityEvent(this, (byte)6);
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (var2 != null && !var2.isEmpty() && this.isTame() && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.level.isClientSide) {
                if (!this.isOrderedToSit()) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)this.getY() - 1, (int)this.getZ())).getBlock();
                    if (bid == Blocks.SAND || bid == Blocks.GRAVEL || bid == Blocks.DIRT || bid == Blocks.FARMLAND || bid == Blocks.GRASS_BLOCK) {
                        this.setOrderedToSit(true);
                    }
                } else {
                    this.setOrderedToSit(false);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && !var2.isEmpty() && var2.getItem() == Items.NAME_TAG && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            this.setCustomName(var2.getHoverName());
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if ((var2 == null || var2.isEmpty()) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.level.isClientSide) {
                par1PlayerEntityEntity.startRiding(this);
                this.setOrderedToSit(false);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    protected SoundEvent getAmbientSound() {
        if (this.isOrderedToSit()) {
            return null;
        }
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.CRYO_HURT;
    }

    protected SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.CRYO_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4f;
    }

    protected Item getDropItem() {
        return Items.FEATHER;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = 0;
        if (this.isTame()) {
            var3 = this.random.nextInt(5);
            for (int var4 = 0; var4 < (var3 += 2); ++var4) {
                this.spawnAtLocation(Items.POPPY, 1);
            }
        } else {
            super.dropCustomDeathLoot(source, looting, recentlyHit);
        }
    }

    protected float getVoicePitch() {
        return this.isBaby() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.1f + 1.5f : (this.random.nextFloat() - this.random.nextFloat()) * 0.1f + 1.0f;
    }

    public boolean shouldRiderSit() {
        return true;
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

    @Override
    protected void jumpFromGround() {
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.25, 0.0);
        super.jumpFromGround();
    }

    public double getMountedYOffset() {
        return 1.4;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.level.isNight()) {
            return false;
        }
        if (this.level.random.nextInt(4) != 1) {
            return false;
        }
        Ostrich target = null;
        target = this.level.getNearestEntity(Ostrich.class, EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        if (target != null) {
            return false;
        }
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.boatPosRotationIncrements = 10;
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.boatYawHead = par9;
        this.setDeltaMovement(this.velocityX, this.getDeltaMovement().y, this.getDeltaMovement().z);
        this.setDeltaMovement(this.getDeltaMovement().x, this.velocityY, this.getDeltaMovement().z);
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, this.velocityZ);
    }

    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        this.velocityX = par1;
        this.velocityY = par3;
        this.velocityZ = par5;
        this.setDeltaMovement(par1, par3, par5);
    }

    @Override
    public void aiStep() {
        Object list = null;
        Object listEntity = null;
        double d6 = this.random.nextFloat() * 2.0f - 1.0f;
        double d7 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7;
        double obstruction_factor = 0.0;
        double relative_g = 0.0;
        double max_speed = 0.75;
        double gh = 1.0;
        double rt = 0.0;
        double pi = 3.1415926545;
        double deltav = 0.0;
        int dist = 2;
        if (this.getControllingPassenger() == null) {
            super.aiStep();
            return;
        }
        if (!this.isAlive()) {
            return;
        }
        if (this.getControllingPassenger() == null) {
            float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
            float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
            this.yRot += var8 / 5.0f;
        }
        if (this.level.isClientSide) {
            if (this.boatPosRotationIncrements > 0) {
                double d4 = this.getX() + (this.boatX - this.getX()) / (double)this.boatPosRotationIncrements;
                double d5 = this.getY() + (this.boatY - this.getY()) / (double)this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double)this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                this.xRot = (float)((double)this.xRot + (this.boatPitch - (double)this.xRot) / (double)this.boatPosRotationIncrements);
                double d10 = MathHelper.wrapDegrees((double)(this.boatYaw - (double)this.yRot));
                if (this.getControllingPassenger() != null) {
                    d10 = MathHelper.wrapDegrees((double)((double)this.getControllingPassenger().yRot - (double)this.yRot));
                }
                this.yRot = (float)((double)this.yRot + d10 / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
            }
        } else if (this.getControllingPassenger() != null) {
            double rdv;
            PlayerEntity pp = (PlayerEntity)this.getControllingPassenger();
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
            obstruction_factor = 0.0;
            dist = 1 + (int)(velocity * 10.0);
            for (int k = 0; k < dist; ++k) {
                for (int i = 1; i < dist * 2; ++i) {
                    double dz = (double)i * Math.sin(Math.toRadians(this.yRot + 90.0f));
                    double dx = (double)i * Math.cos(Math.toRadians(this.yRot + 90.0f));
                    net.minecraft.util.math.BlockPos checkPos = new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() - 1 + k, (int)(this.getZ() + dz));
                    Block bid = this.level.getBlockState(checkPos).getBlock();
                    if (bid == Blocks.AIR) continue;
                    obstruction_factor += 0.075;
                }
            }
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor, 0.0);
            com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor);
            if (this.getDeltaMovement().y > 4.0) {
                this.setDeltaMovement(this.getDeltaMovement().x, 4.0, this.getDeltaMovement().z);
            }
            double d4 = this.getControllingPassenger().yRot;
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
            if (velocity > 0.01) {
                d4 = 1.85 - velocity;
                if ((d4 = Math.abs(d4)) < 0.01) {
                    d4 = 0.01;
                }
                if (d4 > 0.9) {
                    d4 = 0.9;
                }
                this.yRot = this.getControllingPassenger().yRot + (float)(relative_g * d4);
            } else {
                this.yRot = this.getControllingPassenger().yRot;
            }
            this.xRot = 2.0f * (float)velocity;
            double newvelocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
            double rr = Math.atan2(this.getControllingPassenger().getDeltaMovement().z, this.getControllingPassenger().getDeltaMovement().x);
            double rhm = Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x);
            double rhdir = Math.toRadians((this.getControllingPassenger().yRot + 90.0f) % 360.0f);
            rt = 0.0;
            pi = 3.1415926545;
            deltav = 0.0;
            float im = pp.yya;
            if (ChaosPersists.flyup_keystate != 0) {
                if (this.didjump == 0) {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 1.0, 0.0);
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, velocity * 6.0, 0.0);
                    this.didjump = 20;
                }
            } else if (this.didjump > 0) {
                --this.didjump;
            }
            if ((rdv = Math.abs(rhm - rhdir) % (pi * 2.0)) > pi) {
                rdv -= pi * 2.0;
            }
            rdv = Math.abs(rdv);
            if (Math.abs(newvelocity) < 0.01) {
                rdv = 0.0;
            }
            if (rdv > 1.5) {
                newvelocity = - newvelocity;
            }
            if (Math.abs(im) > 0.001f) {
                if (im > 0.0f) {
                    deltav = 0.045;
                    if (this.deltasmooth < 0.0f) {
                        this.deltasmooth = 0.0f;
                    }
                    this.deltasmooth = (float)((double)this.deltasmooth + deltav / 10.0);
                    if ((double)this.deltasmooth > deltav) {
                        this.deltasmooth = (float)deltav;
                    }
                } else {
                    max_speed = 0.25;
                    deltav = -0.03;
                    if (this.deltasmooth > 0.0f) {
                        this.deltasmooth = 0.0f;
                    }
                    this.deltasmooth = (float)((double)this.deltasmooth + deltav / 10.0);
                    if ((double)this.deltasmooth < deltav) {
                        this.deltasmooth = (float)deltav;
                    }
                }
                if ((newvelocity += (double)this.deltasmooth) >= 0.0) {
                    if (newvelocity > max_speed) {
                        newvelocity = max_speed;
                    }
                    this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 90.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 90.0f)) * newvelocity);
                } else {
                    if (newvelocity < - max_speed) {
                        newvelocity = - max_speed;
                    }
                    newvelocity = - newvelocity;
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
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.25), 0.0);
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.95, 0.85, 0.95);
            if (this.getControllingPassenger() != null && !this.getControllingPassenger().isAlive()) {
                this.ejectPassengers();
            }
        }
    }

    public void updateRiderPosition() {
        if (this.getControllingPassenger() != null) {
            float f = -0.15f;
            this.getControllingPassenger().setPos(this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + this.getMountedYOffset() + this.getControllingPassenger().getMyRidingOffset(), this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)));
        }
    }

    protected void playTameEffect(boolean par1) {
        if (!this.level.isClientSide) {
            return;
        }
        net.minecraft.particles.IParticleData type = par1 ? ParticleTypes.HEART : ParticleTypes.SMOKE;
        for (int i = 0; i < 20; ++i) {
            double d0 = this.random.nextGaussian() * 0.08;
            double d1 = this.random.nextGaussian() * 0.08;
            double d2 = this.random.nextGaussian() * 0.08;
            this.level.addParticle(type, this.getX() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), this.getY() + 0.5 + (double)this.random.nextFloat() * 1.5, this.getZ() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), d0, d1, d2);
        }
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isBaby()) {
            this.setPersistenceRequired();
            return false;
        }
        if (this.getControllingPassenger() != null) {
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }


    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.APPLE;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == ChaosPersists.MyCrystalApple;
    }
}

