/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.BetterFireball
 *  com.astryxion.chaospersists.Dragonfly
 *  com.astryxion.chaospersists.EntityMosquito
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.IceBall
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.PurplePower
 *  com.astryxion.chaospersists.ThePrincess
 *  com.astryxion.chaospersists.ThunderBolt
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.entity.Dragonfly;
import com.astryxion.chaospersists.entity.EntityMosquito;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.item.ThunderBolt;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.server.ServerWorld;

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
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.Hand;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/*
 * Exception performing whole class analysis ignored.
 */
public class ThePrincess
extends TameableEntity {
    private static final DataParameter<Integer> SPYRO_FIRE = EntityDataManager.defineId(ThePrincess.class, DataSerializers.INT);
    private static final DataParameter<Integer> ACTIVITY = EntityDataManager.defineId(ThePrincess.class, DataSerializers.INT);
    private static final DataParameter<Integer> ATTACKING = EntityDataManager.defineId(ThePrincess.class, DataSerializers.INT);
    private static final DataParameter<Integer> POWER = EntityDataManager.defineId(ThePrincess.class, DataSerializers.INT);
    private BlockPos currentFlightTarget;
    private GenericTargetSorter TargetSorter = null;
    public int activity = 1;
    private int owner_flying = 0;
    private float moveSpeed = 0.3f;
    private int syncit = 0;
    private int head1ext = 0;
    private int head2ext = 0;
    private int head3ext = 0;
    private int head1dir = 1;
    private int head2dir = 1;
    private int head3dir = 1;
    private int ok_to_grow = 0;
    private int kill_count = 0;
    private int fed_count = 0;
    private int day_count = 0;
    private int is_day = 0;
    private int attack_level = 1;
    private int ticker = 0;

    public ThePrincess(EntityType<? extends ThePrincess> type, World par1World) {
        super(type, par1World);
        this.moveSpeed = 0.32f;
        this.setOrderedToSit(false);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner((TameableEntity)this, 1.15f, 12.0f, 2.0f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, net.minecraft.item.crafting.Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(4, new LookAtGoal(this, LivingEntity.class, 6.0f));
        this.goalSelector.addGoal(5, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new RestrictSunGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.xpReward = 50;
    }

    public static net.minecraft.entity.ai.attributes.AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 400.0)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.activity = 1;
        this.entityData.define(ATTACKING, 0);
        this.entityData.define(ACTIVITY, this.activity);
        this.entityData.define(SPYRO_FIRE, 1);
        this.entityData.define(POWER, this.attack_level);
        this.setOrderedToSit(false);
        this.setTame(false);
        this.noPhysics = false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public int getPower() {
        return this.entityData.get(POWER).intValue();
    }

    public void setPower(int par1) {
        this.entityData.set(POWER, par1);
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("SpyroActivity", this.entityData.get(ACTIVITY).intValue());
        par1CompoundNBT.putInt("SpyroFire", this.entityData.get(SPYRO_FIRE).intValue());
        par1CompoundNBT.putInt("SpyroGrow", this.ok_to_grow);
        par1CompoundNBT.putInt("SpyroKill", this.kill_count);
        par1CompoundNBT.putInt("SpyroFed", this.fed_count);
        par1CompoundNBT.putInt("SpyroDay", this.day_count);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.activity = par1CompoundNBT.getInt("SpyroActivity");
        this.entityData.set(ACTIVITY, this.activity);
        this.entityData.set(SPYRO_FIRE, par1CompoundNBT.getInt("SpyroFire"));
        this.ok_to_grow = par1CompoundNBT.getInt("SpyroGrow");
        this.kill_count = par1CompoundNBT.getInt("SpyroKill");
        this.fed_count = par1CompoundNBT.getInt("SpyroFed");
        this.day_count = par1CompoundNBT.getInt("SpyroDay");
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

    public int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, par1);
    }

    public int getHead1Ext() {
        return this.head1ext;
    }

    public int getHead2Ext() {
        return this.head2ext;
    }

    public int getHead3Ext() {
        return this.head3ext;
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public int mygetMaxHealth() {
        return 400;
    }

    protected void playTameEffect(boolean par1) {
        net.minecraft.particles.BasicParticleType type = par1 ? net.minecraft.particles.ParticleTypes.HEART : net.minecraft.particles.ParticleTypes.SMOKE;
        for (int i = 0; i < 20; ++i) {
            double d0 = this.random.nextGaussian() * 0.08;
            double d1 = this.random.nextGaussian() * 0.08;
            double d2 = this.random.nextGaussian() * 0.08;
            this.level.addParticle(type, this.getX() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), this.getY() + 0.5 + (double)this.random.nextFloat() * 1.5, this.getZ() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), d0, d1, d2);
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
        if (var2 != null && var2.getItem() == Item.byBlock((Block)Blocks.DIAMOND_BLOCK) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.level.isClientSide) {
                this.setTame(true);
                this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent(this, (byte)7);
                this.heal((float)this.mygetMaxHealth() - this.getHealth());
                this.playTameEffect(true);
                this.ok_to_grow = 1;
                this.kill_count = 1000;
                this.fed_count = 1000;
                this.day_count = 1000;
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && var2.isEdible()) {
            if (!this.level.isClientSide) {
                Item var3 = (Item)var2.getItem();
                if ((float)this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float)((var3.getFoodProperties() != null ? var3.getFoodProperties().getNutrition() : 0) * 10));
                }
                this.playTameEffect(true);
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent(this, (byte)7);
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && var2.getItem() == Item.byBlock((Block)Blocks.ICE) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent(this, (byte)6);
                this.setSpyroFire(0);
                String healthMessage = new String();
                healthMessage = String.format("Princess fireballs extinguished.", new Object[0]);
                par1PlayerEntityEntity.sendMessage(new net.minecraft.util.text.StringTextComponent(healthMessage), par1PlayerEntityEntity.getUUID());
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && var2.getItem() == Items.FLINT_AND_STEEL && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent(this, (byte)6);
                this.setSpyroFire(1);
                String healthMessage = new String();
                healthMessage = String.format("Princess fireballs lit!", new Object[0]);
                par1PlayerEntityEntity.sendMessage(new net.minecraft.util.text.StringTextComponent(healthMessage), par1PlayerEntityEntity.getUUID());
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && var2.getItem() == Items.NAME_TAG && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            this.setCustomName(var2.getHoverName());
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
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

    public void set_ok_to_grow() {
        this.ok_to_grow = 1;
        this.kill_count = 0;
        this.fed_count = 0;
        this.day_count = 0;
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.BEEF;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.isOrderedToSit()) {
            return null;
        }
        if (this.getAttacking() == 0) {
            return null;
        }
        return com.astryxion.chaospersists.core.ChaosSounds.ROAR;
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
        return 14;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = this.level.random.nextInt(4) + 1;
        this.spawnAtLocation(Items.BEEF, var3);
    }

    protected float getVoicePitch() {
        return (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2f + 1.5f;
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
        return true;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) { return null; }

    public float getAttackStrength(Entity par1Entity) {
        return 9.0f;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        LivingEntity el;
        float var2 = this.getAttackStrength(par1Entity);
        boolean var4 = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), var2);
        if (par1Entity instanceof LivingEntity && (el = (LivingEntity)par1Entity).getHealth() <= 0.0f) {
            ++this.kill_count;
        }
        return var4;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
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

    public void tick() {
        int i;
        super.tick();
        this.noPhysics = this.getActivity() == 2;
        if (this.level.random.nextInt(10) == 1) {
            i = this.level.random.nextInt(3);
            if (i == 0) {
                this.head1dir = 2;
            }
            if (i == 1) {
                this.head1dir = -2;
            }
            if (i == 2) {
                this.head1dir = 0;
            }
        }
        if (this.level.random.nextInt(10) == 1) {
            i = this.level.random.nextInt(3);
            if (i == 0) {
                this.head2dir = 2;
            }
            if (i == 1) {
                this.head2dir = -2;
            }
            if (i == 2) {
                this.head2dir = 0;
            }
        }
        if (this.level.random.nextInt(10) == 1) {
            i = this.level.random.nextInt(3);
            if (i == 0) {
                this.head3dir = 2;
            }
            if (i == 1) {
                this.head3dir = -2;
            }
            if (i == 2) {
                this.head3dir = 0;
            }
        }
        this.head1ext += this.head1dir;
        if (this.head1ext < 0) {
            this.head1ext = 0;
        }
        if (this.head1ext > 60) {
            this.head1ext = 60;
        }
        this.head2ext += this.head2dir;
        if (this.head2ext < 0) {
            this.head2ext = 0;
        }
        if (this.head2ext > 60) {
            this.head2ext = 60;
        }
        this.head3ext += this.head3dir;
        if (this.head3ext < 0) {
            this.head3ext = 0;
        }
        if (this.head3ext > 60) {
            this.head3ext = 60;
        }
        if (this.level.isClientSide && this.getPower() > 400) {
            float f = 0.25f;
            if (this.level.random.nextInt(6) == 1) {
                for (i = 0; i < 2; ++i) {
                    this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + 0.4, this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)), (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 7.0 + this.getDeltaMovement().x * 3.0, (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 7.0, (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 7.0 + this.getDeltaMovement().z * 3.0);
                }
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
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        ++this.syncit;
        if (this.syncit > 20) {
            this.syncit = 0;
            if (this.level.isClientSide) {
                this.getActivity();
            } else {
                int j = this.activity;
                this.setActivity(j);
            }
        }
        if (this.activity == 2) {
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        }
    }

    protected void customServerAiStep()
    {
      double xzoff = 1.5D;
      double yoff = 1.0D;
      MobEntity newent = null;

      if (!this.isAlive()) return;

      if (this.level.random.nextInt(200) == 1) setLastHurtByMob(null);

      if (this.activity != 2) {
        super.customServerAiStep();
      }
      this.ticker += 1;
      if (this.ticker % 10 == 0) {
        setPower(this.attack_level);
      }

      if ((this.level.random.nextInt(200) == 1) && 
        (getHealth() < mygetMaxHealth())) {
        heal(1.0F);
      }

      if (!this.isTame()) {
        PlayerEntity p = this.level.getNearestPlayer(this, 10.0D);
        if (p != null) {
          this.setTame(true);
          this.setOwnerUUID(p.getUUID());
          this.playTameEffect(true);
          this.level.broadcastEntityEvent(this, (byte) 7);
          this.heal((float)(this.mygetMaxHealth() - this.getHealth()));
        }
      }

      this.attack_level += 1;
      if (getAttacking() != 0) {
        this.attack_level += 4;
      }

      if (getSpyroFire() == 0) this.attack_level = 0;

      if (this.attack_level > 500) {
        if (getAttacking() != 0) {
          int j = 3;
          for (int i = 0; i < j; i++) {
            Entity ppwr = spawnCreature(this.level, "chaospersists:purple_power", this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot)));

            if (ppwr != null) {
              PurplePower p = (PurplePower)ppwr;
              p.setDeltaMovement((this.getDeltaMovement().x * 3.0D), p.getDeltaMovement().y, p.getDeltaMovement().z);
              p.setDeltaMovement(p.getDeltaMovement().x, p.getDeltaMovement().y, (this.getDeltaMovement().z * 3.0D));
              p.setPurpleType(1 + this.level.random.nextInt(3));
            }
          }
        }

        if (this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
          for (int m = 0; m < 5; m++) {
            int i = this.level.random.nextInt(5) - this.level.random.nextInt(5);
            int k = this.level.random.nextInt(5) - this.level.random.nextInt(5);
            for (int j = -5; j < 5; j++) {
              Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k)).getBlock();
              if (bid == Blocks.GRASS_BLOCK) {
                if (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() != Blocks.AIR) break;
                int which = this.level.random.nextInt(8);
                if (which == 0) this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.POPPY.defaultBlockState(), 3);
                if (which == 1) this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.DANDELION.defaultBlockState(), 3);
                if (which == 2) this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.MyFlowerBlueBlock.defaultBlockState(), 3);
                if (which == 3) this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.MyFlowerPinkBlock.defaultBlockState(), 3);
                if (which == 4) this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerRedBlock.defaultBlockState(), 3);
                if (which == 5) this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerGreenBlock.defaultBlockState(), 3);
                if (which == 6) this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerBlueBlock.defaultBlockState(), 3);
                if (which != 7) break; this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), ChaosPersists.CrystalFlowerYellowBlock.defaultBlockState(), 3); break;
              }

              if ((bid == Blocks.DIRT) && 
                (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR)) {
                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                break;
              }

              if ((bid == Blocks.STONE) && 
                (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR)) {
                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.DIRT.defaultBlockState(), 3);
                break;
              }

              if ((bid == Blocks.SAND) && 
                (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR)) {
                if (this.level.random.nextInt(2) == 0) {
                  this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k), Blocks.CACTUS.defaultBlockState(), 3); break;
                }
                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k), Blocks.DIRT.defaultBlockState(), 3);

                break;
              }

              if ((bid == Blocks.LAVA) && 
                (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() == Blocks.AIR)) {
                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k), Blocks.WATER.defaultBlockState(), 3);
                break;
              }


              if ((bid == Blocks.AIR) && (j > 0))
                break;
            }
          }
        }
        for (int m = 0; m < 2; m++) {
          int i = this.level.random.nextInt(4) - this.level.random.nextInt(4);
          int k = this.level.random.nextInt(4) - this.level.random.nextInt(4);
          int j = 1 + this.level.random.nextInt(4);
          Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k)).getBlock();
          if (bid == Blocks.AIR) {
            if (this.level.random.nextInt(2) == 0)
              newent = (MobEntity)spawnCreature(this.level, "Butterfly", this.getX() + i, this.getY() + j, this.getZ() + k);
            else {
              newent = (MobEntity)spawnCreature(this.level, "Bird", this.getX() + i, this.getY() + j, this.getZ() + k);
            }
          }
        }

        this.attack_level = 1;
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
        if ((this.isTame()) && (this.getOwner() != null)) {
          PlayerEntity e = (PlayerEntity)this.getOwner();

          if (e.abilities.flying) {
            this.owner_flying = 1;
            setActivity(2);
          }
        }

        if ((this.activity == 1) && (this.isTame()) && (this.getOwner() != null)) {
          LivingEntity e = this.getOwner();

          if (this.distanceToSqr((Entity)e) > 256.0D)
          {
            setActivity(2);
          }
        }

        do_movement();
      }
      else if ((this.isTame()) && (this.getOwner() != null)) {
        LivingEntity e = this.getOwner();

        if (this.distanceToSqr((Entity)e) > 256.0D)
        {
          this.setOrderedToSit(false);
          setActivity(2);
        }

      }

      if (this.is_day == 0) {
        this.is_day = 1;
        if (!this.level.isDay()) this.is_day = -1; 
      }
      else {
        if ((this.is_day == -1) && 
          (this.level.isDay()))
        {
          this.day_count += 1;
        }

        this.is_day = 1;
        if (!this.level.isDay()) this.is_day = -1;
      }
    }


    private void do_movement() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 10;
        boolean do_new = false;
        double ox = 0.0;
        double oy = 0.0;
        double oz = 0.0;
        boolean has_owner = false;
        double rr = 0.0;
        double rhdir = 0.0;
        double rdd = 0.0;
        double pi = 3.1415926545;
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
            oy = e.getY() + 1.0;
            oz = e.getZ();
            if (this.distanceToSqr((Entity)e) > 100.0) {
                do_new = true;
            }
            if (this.owner_flying != 0 && this.distanceToSqr((Entity)e) > 36.0) {
                do_new = true;
            }
        }
        e = this.getTarget();
        if (e != null && (!e.isAlive() || !this.isSuitableTarget(e, false))) {
            this.setTarget(null);
            e = null;
        }
        if (e == null && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            e = this.findSomethingToAttack();
            if (e != null) {
                this.setTarget(e);
            }
        }
        if (e != null) {
            if (this.isTame() && this.getHealth() / (float)this.mygetMaxHealth() < 0.25f) {
                this.setActivity(2);
                this.setAttacking(0);
                do_new = false;
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)(this.getX() + (this.getX() - e.getX())), (int)(this.getY() + 1.0), (int)(this.getZ() + (this.getZ() - e.getZ())));
            } else {
                this.setActivity(2);
                this.setAttacking(1);
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)(e.getY() + 1.0), (int)e.getZ());
                do_new = false;
                if (this.distanceToSqr((Entity)e) < (double)((3.0f + e.getBbWidth() / 2.0f) * (3.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget((LivingEntity)e);
                } else if (!(this.distanceToSqr((Entity)e) <= 25.0 || this.distanceToSqr((Entity)e) >= 144.0 || this.isInWater() || this.getSpyroFire() == 0 || this.level.random.nextInt(3) != 0 && this.level.random.nextInt(4) != 1)) {
                    int which = this.level.random.nextInt(3);
                    if (which == 0) {
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rdd = Math.abs(rr - (rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f))) % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            this.firecanon(e);
                        }
                    } else if (which == 1) {
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rdd = Math.abs(rr - (rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f))) % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            this.firecanonl(e);
                        }
                    } else {
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rdd = Math.abs(rr - (rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f))) % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            this.firecanoni(e);
                        }
                    }
                }
            }
        } else {
            this.setAttacking(0);
        }
        if (this.activity == 1) {
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
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos(gox + xdir, goy + (this.level.random.nextInt(6 + this.owner_flying * 2) - 2), goz + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
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
        if (MyUtils.isRoyalty((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof Mothra) {
            return true;
        }
        if (par1Mob instanceof Dragonfly) {
            return true;
        }
        if (par1Mob instanceof EntityMosquito) {
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
            if (!this.isSuitableTarget(var4, false)) continue;
            boolean canSee = this.canSeeTarget(var4.getX(), var4.getY(), var4.getZ()) || this.canSeeTarget(var4.getX(), var4.getY() + (double)(var4.getBbHeight() * 0.5f), var4.getZ());
            if (!canSee && this.distanceToSqr((Entity)var4) > 64.0) continue;
            return var4;
        }
        return null;
    }

    private void firecanon(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 3.0;
        BetterFireball bf = null;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
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
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
        this.level.addFreshEntity((Entity)bf);
    }

    private void firecanonl(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 3.0;
        double var3 = 0.0;
        double var5 = 0.0;
        double var7 = 0.0;
        float var9 = 0.0f;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
        float r1 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r2 = 3.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r3 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
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

    private void firecanoni(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 3.0;
        double var3 = 0.0;
        double var5 = 0.0;
        double var7 = 0.0;
        float var9 = 0.0f;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
        float r1 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r2 = 3.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r3 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        IceBall lb = new IceBall(this.level, cx, this.getY() + yoff, cz);
        lb.setIceMaker(1);
        lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
        var3 = e.getX() - lb.getX();
        var5 = e.getY() + 0.25 - lb.getY();
        var7 = e.getZ() - lb.getZ();
        var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
        lb.shoot(var3, var5 + (double)var9, var7, 1.4f, 4.0f);
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 3.0, 3.0, 3.0);
        this.level.addFreshEntity((Entity)lb);
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.util.ResourceLocation res = par1.contains(":")
            ? new net.minecraft.util.ResourceLocation(par1)
            : new net.minecraft.util.ResourceLocation("chaospersists", par1.toLowerCase(java.util.Locale.ROOT).replace(' ', '_'));
        EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(res);
        if (entityType != null) {
            var8 = entityType.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
        }
        return var8;
    }
}

