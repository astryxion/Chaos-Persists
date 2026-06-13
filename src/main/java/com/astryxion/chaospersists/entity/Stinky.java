/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Stinky
 *  net.minecraft.block.Block
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
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
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.util.Hand;
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
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class Stinky
extends TameableEntity {
    private static final DataParameter<Integer> SPYRO_FIRE = EntityDataManager.defineId(Stinky.class, DataSerializers.INT);
    private static final DataParameter<Integer> ACTIVITY = EntityDataManager.defineId(Stinky.class, DataSerializers.INT);
    private static final DataParameter<Integer> SKIN_COLOR = EntityDataManager.defineId(Stinky.class, DataSerializers.INT);
    private BlockPos currentFlightTarget;
    private GenericTargetSorter TargetSorter = null;
    public int activity = 1;
    private int owner_flying = 0;
    private float moveSpeed = 0.3f;
    private int skin_color = -1;
    private int syncit = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Stinky(EntityType<? extends Stinky> type, World par1World) {
        super(type, par1World);
        this.moveSpeed = 0.3f;
                this.setOrderedToSit(false);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal((CreatureEntity)this, MonsterEntity.class, 8.0f, 0.30000001192092896, 0.4000000059604645));
        this.goalSelector.addGoal(3, new MyEntityAIFollowOwner((TameableEntity)this, 1.15f, 12.0f, 2.0f));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25, net.minecraft.item.crafting.Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(7, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new RestrictSunGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.xpReward = 35;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.activity = 1;
        this.entityData.define(SKIN_COLOR, 0);
        this.entityData.define(ACTIVITY, this.activity);
        this.entityData.define(SPYRO_FIRE, 1);
        this.setOrderedToSit(false);
        this.setTame(false);
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("SpyroActivity", this.entityData.get(ACTIVITY).intValue());
        par1CompoundNBT.putInt("SpyroFire", this.entityData.get(SPYRO_FIRE).intValue());
        par1CompoundNBT.putInt("StinkySkin", this.entityData.get(SKIN_COLOR).intValue());
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.activity = par1CompoundNBT.getInt("SpyroActivity");
        this.entityData.set(ACTIVITY, this.activity);
        this.entityData.set(SPYRO_FIRE, par1CompoundNBT.getInt("SpyroFire"));
        this.skin_color = par1CompoundNBT.getInt("StinkySkin");
        this.entityData.set(SKIN_COLOR, this.skin_color);
    }

    public int getActivity() {
        int i;
        this.activity = i = this.entityData.get(ACTIVITY).intValue();
        return i;
    }

    public void setActivity(int par1) {
        this.activity = par1;
        this.entityData.set(ACTIVITY, par1);
    }

    public int getSpyroFire() {
        return this.entityData.get(SPYRO_FIRE).intValue();
    }

    public void setSpyroFire(int par1) {
        this.entityData.set(SPYRO_FIRE, par1);
    }

    public int getSkin() {
        int i;
        this.skin_color = i = this.entityData.get(SKIN_COLOR).intValue();
        return i;
    }

    public void setSkin(int par1) {
        this.skin_color = par1;
        this.entityData.set(SKIN_COLOR, par1);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public int mygetMaxHealth() {
        return 100;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (var2 != null && var2.getCount() <= 0) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (var2 != null && var2.getItem() == Items.BEEF && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level.isClientSide) {
                    if (this.level.random.nextInt(2) == 1) {
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
            } else if (this.isOwnedBy(par1PlayerEntityEntity)) {
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
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && var2.getItem() == Blocks.DEAD_BUSH.asItem() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy(par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.setTame(false);
                this.setHealth((float)this.mygetMaxHealth());
                this.setOwnerUUID(null);
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
        if (this.isTame() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy(par1PlayerEntityEntity)) {
            if (!this.isOrderedToSit()) {
                this.setOrderedToSit(true);
                this.setActivity(1);
            } else {
                this.setOrderedToSit(false);
            }
            return ActionResultType.SUCCESS;
        }
        return super.mobInteract(par1PlayerEntityEntity, hand);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.BEEF;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.DUCK_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.CRYO_DEATH;
    }

    protected float getSoundVolume() {
        return 0.6f;
    }

    public int getArmorValue() {
        return 6;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = 0;
        if (this.isTame()) {
            var3 = this.level.random.nextInt(4);
            for (int var4 = 0; var4 < ++var3; ++var4) {
                this.spawnAtLocation(Items.BEEF, 1);
            }
        }
    }

    protected float getVoicePitch() {
        return this.isBaby() ? (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.1f + 1.5f : (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.1f + 1.0f;
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

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (!this.level.isDay()) {
            return false;
        }
        if (this.findBuddies() > 2) {
            return false;
        }
        return true;
    }

    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) { return null; }

    public float getAttackStrength(Entity par1Entity) {
        return 10.0f;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        float var2 = this.getAttackStrength(par1Entity);
        boolean var4 = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), var2);
        return var4;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            ret = super.hurt(par1DamageSource, par2);
            this.setOrderedToSit(false);
            this.setActivity(2);
        }
        return ret;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d((double)this.getX(), (double)(this.getY() + 0.75), (double)this.getZ()), new Vector3d((double)pX, (double)pY, (double)pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    private void dropItemFront(Item index, int par1) {
        float f = 0.75f + Math.abs(this.level.random.nextFloat() * 0.75f);
        ItemEntity var3 = new ItemEntity(this.level, this.getX() - (double)f * Math.sin(Math.toRadians(this.yHeadRot)), this.getY() + 0.9, this.getZ() + (double)f * Math.cos(Math.toRadians(this.yHeadRot)), new ItemStack(index, par1));
        this.level.addFreshEntity((Entity)var3);
    }

    private void dropItemRear(Item index, int par1) {
        float f = 0.55f + Math.abs(this.level.random.nextFloat() * 0.55f);
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)f * Math.sin(Math.toRadians(this.yHeadRot)), this.getY() + 0.25, this.getZ() - (double)f * Math.cos(Math.toRadians(this.yHeadRot)), new ItemStack(index, par1));
        this.level.addFreshEntity((Entity)var3);
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide && this.level.random.nextInt(1750) == 1) {
            this.playSound(net.minecraft.util.SoundEvents.PLAYER_BURP, 1.0f, 1.0f);
            this.dropItemFront(Items.COAL, 1);
        }
        if (!this.level.isClientSide && this.level.random.nextInt(2000) == 2) {
            this.playSound(com.astryxion.chaospersists.core.ChaosSounds.FART, 1.0f, 1.5f);
            if (this.skin_color == 0) {
                this.dropItemRear(Items.BLAZE_POWDER, 1);
            }
            if (this.skin_color == 1) {
                this.dropItemRear(Items.ROTTEN_FLESH, 1);
            }
            if (this.skin_color == 2) {
                this.dropItemRear(Items.MELON_SEEDS, 1);
            }
            if (this.skin_color == 3) {
                this.dropItemRear(ChaosPersists.UraniumNugget, 1);
            }
            if (this.skin_color == 4) {
                this.dropItemRear(Items.WHEAT, 1);
            }
            if (this.skin_color == 5) {
                this.dropItemRear(Items.BRICK, 1);
            }
            if (this.skin_color == 6) {
                this.dropItemRear(Item.byBlock((Block)Blocks.TORCH), 1);
            }
            if (this.skin_color == 7) {
                this.dropItemRear(Items.EMERALD, 1);
            }
            if (this.skin_color == 8) {
                this.dropItemRear(Items.GOLD_INGOT, 1);
            }
            if (this.skin_color == 9) {
                this.dropItemRear(Blocks.OAK_LEAVES.asItem(), 1);
            }
            if (this.skin_color == 10) {
                this.dropItemRear(ChaosPersists.TitaniumNugget, 1);
            }
            if (this.skin_color == 11) {
                this.dropItemRear(ChaosPersists.MyAppleSeed, 1);
            }
            if (this.skin_color == 12) {
                this.dropItemRear(Items.DIAMOND, 1);
            }
            if (this.skin_color == 13) {
                this.dropItemRear(Item.byBlock((Block)Blocks.SAND), 1);
            }
            if (this.skin_color == 14) {
                this.dropItemRear(Item.byBlock((Block)Blocks.COBBLESTONE), 1);
            }
            if (this.skin_color == 15) {
                this.dropItemRear(Items.BONE, 1);
            }
            if (this.skin_color == 16) {
                this.dropItemRear(Items.STRING, 1);
            }
            if (this.skin_color == 17) {
                this.dropItemRear(ChaosPersists.MyCherrySeed, 1);
            }
            if (this.skin_color == 18) {
                this.dropItemRear(ChaosPersists.MyPeachSeed, 1);
            }
        }
    }

    @Override
    public void aiStep() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.aiStep();
        if (this.isInWater()) {
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.07, 0.0);
        }
        if (!this.level.isClientSide && this.level.random.nextInt(2000) == 1) {
            int i = this.level.random.nextInt(19);
            this.setSkin(i);
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.skin_color < 0) {
            this.skin_color = this.level.random.nextInt(19);
        }
        ++this.syncit;
        if (this.syncit > 20) {
            this.syncit = 0;
            if (this.level.isClientSide) {
                this.getActivity();
                this.getSkin();
            } else {
                int j = this.activity;
                this.setActivity(j);
                j = this.skin_color;
                this.setSkin(j);
            }
        }
        if (this.activity == 2) {
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        }
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        Block bid;
        int d;
        int j;
        int found = 0;
        for (i = - dy; i <= dy; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + dx, y + i, z + j)).getBlock();
                if (bid == Blocks.COAL_ORE && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x - dx, y + i, z + j)).getBlock()) != Blocks.COAL_ORE || (d = dx * dx + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x - dx;
                this.ty = y + i;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + dy, z + j)).getBlock();
                if (bid == Blocks.COAL_ORE && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y - dy, z + j)).getBlock()) != Blocks.COAL_ORE || (d = dy * dy + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x + i;
                this.ty = y - dy;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dy; j <= dy; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z + dz)).getBlock();
                if (bid == Blocks.COAL_ORE && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z - dz)).getBlock()) != Blocks.COAL_ORE || (d = dz * dz + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x + i;
                this.ty = y + j;
                this.tz = z - dz;
                ++found;
            }
        }
        if (found != 0) {
            return true;
        }
        return false;
    }

    protected void customServerAiStep()
    {
      if (!this.isAlive()) return;

      if (this.level.random.nextInt(200) == 1) setLastHurtByMob(null);

      if (this.activity != 2) {
        super.customServerAiStep();
      }

      if ((this.level.random.nextInt(100) == 1) && 
        (getHealth() < mygetMaxHealth())) {
        heal(1.0F);
      }

      if (!isOrderedToSit()) {
        if (this.activity == 0) {
          setActivity(1);
        }

        if (this.level.random.nextInt(100) == 1) {
          if (this.level.random.nextInt(20) == 1)
            setActivity(2);
          else {
            setActivity(1);
          }
        }

        this.owner_flying = 0;
        if ((isTame()) && (getOwner() != null)) {
          PlayerEntity e = (PlayerEntity)getOwner();

          if (e.abilities.flying) {
            this.owner_flying = 1;
            setActivity(2);
          }
        }

        if ((this.activity == 1) && (isTame()) && (getOwner() != null)) {
          LivingEntity e = getOwner();

          if (distanceToSqr(e) > 256.0D)
          {
            setActivity(2);
          }
        }

        do_movement();
      }
    }
    private void do_movement() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        boolean do_new = false;
        double ox = 0.0;
        double oy = 0.0;
        double oz = 0.0;
        boolean has_owner = false;
        LivingEntity e = null;
        if (this.currentFlightTarget == null) {
            do_new = true;
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.activity == 2 && this.level.random.nextInt(300) == 0) {
            do_new = true;
        }
        if (this.isTame() && this.getOwner() != null) {
            e = this.getOwner();
            has_owner = true;
            ox = e.getX();
            oy = e.getY();
            oz = e.getZ();
            if (this.distanceToSqr((Entity)e) > 100.0) {
                do_new = true;
            }
            if (this.owner_flying != 0 && this.distanceToSqr((Entity)e) > 36.0) {
                do_new = true;
            }
        }
        e = this.getTarget();
        if (e != null && !e.isAlive()) {
            this.setTarget(null);
            e = null;
        }
        if (e == null && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            e = this.findSomethingToAttack();
            if (e != null) {
                this.setTarget(e);
            }
        }
        if (this.level.random.nextInt(7) == 1 && this.level.getDifficulty() != Difficulty.PEACEFUL && e != null) {
            if (this.isTame() && this.getHealth() / (float)this.mygetMaxHealth() < 0.25f) {
                this.setActivity(2);
                do_new = false;
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)(this.getX() + (this.getX() - e.getX())), (int)(this.getY() + 1.0), (int)(this.getZ() + (this.getZ() - e.getZ())));
            } else {
                this.setActivity(2);
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)(e.getY() + 1.0), (int)e.getZ());
                do_new = false;
                if (this.distanceToSqr((Entity)e) < (double)((3.0f + e.getBbWidth() / 2.0f) * (3.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget((LivingEntity)e);
                }
            }
        }
        if (this.activity == 1) {
            if (this.level.random.nextInt(50) == 0 && ChaosPersists.PlayNicely == 0) {
                this.closest = 99999;
                this.tz = 0;
                this.ty = 0;
                this.tx = 0;
                for (int i = 1; i < 9; ++i) {
                    int j = i;
                    if (j > 2) {
                        j = 2;
                    }
                    if (this.scan_it((int)this.getX(), (int)this.getY() + 1, (int)this.getZ(), i, j, i)) break;
                    if (i < 4) continue;
                    ++i;
                }
                if (this.closest < 99999) {
                    this.getNavigation().moveTo((double)this.tx, (double)this.ty, (double)this.tz, 1.25);
                    if (this.closest < 12) {
                        this.level.setBlock(new net.minecraft.util.math.BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                        this.heal(1.0f);
                        this.playSound(net.minecraft.util.SoundEvents.PLAYER_BURP, 0.5f, this.level.random.nextFloat() * 0.2f + 1.5f);
                    }
                }
            }
            return;
        }
        if (this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 2.1f) {
            do_new = true;
        }
        if (do_new) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                int gox = (int)this.getX();
                int goy = (int)this.getY();
                int goz = (int)this.getZ();
                if (has_owner) {
                    gox = (int)ox;
                    goy = (int)oy;
                    goz = (int)oz;
                    if (this.owner_flying == 0) {
                        zdir = this.level.random.nextInt(4) + 6;
                        xdir = this.level.random.nextInt(4) + 6;
                    } else {
                        zdir = this.level.random.nextInt(8);
                        xdir = this.level.random.nextInt(8);
                    }
                } else {
                    zdir = this.level.random.nextInt(5) + 6;
                    xdir = this.level.random.nextInt(5) + 6;
                }
                if (this.level.random.nextInt(2) == 0) {
                    zdir = - zdir;
                }
                if (this.level.random.nextInt(2) == 0) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos(gox + xdir, goy + this.level.random.nextInt(6 + this.owner_flying * 2) - 2, goz + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        }
        double speed_factor = 1.0;
        double var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        if (this.owner_flying != 0) {
            speed_factor = 1.75;
            if (this.isTame() && this.getOwner() != null && this.distanceToSqr((Entity)(e = this.getOwner())) > 49.0) {
                speed_factor = 3.5;
            }
        }
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.5 - this.getDeltaMovement().x) * 0.15 * speed_factor, (Math.signum(var3) * 0.7 - this.getDeltaMovement().y) * 0.21 * speed_factor, (Math.signum(var5) * 0.5 - this.getDeltaMovement().z) * 0.15 * speed_factor);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = (float)(0.75 * speed_factor);
        this.yRot += var8 / 3.0f;
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
        if (par1Mob instanceof Mothra) {
            return true;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 6.0, 12.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false) || !this.canSeeTarget(var4.getX(), var4.getY(), var4.getZ())) continue;
            return var4;
        }
        return null;
    }

    private int findBuddies() {
        List var5 = this.level.getEntitiesOfClass(Stinky.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
        return var5.size();
    }
}

