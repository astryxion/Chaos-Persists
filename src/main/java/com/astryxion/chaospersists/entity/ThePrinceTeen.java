/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.BetterFireball
 *  com.astryxion.chaospersists.GammaMetroid
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.IceBall
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.Leon
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Spyro
 *  com.astryxion.chaospersists.ThePrince
 *  com.astryxion.chaospersists.ThePrinceAdult
 *  com.astryxion.chaospersists.ThePrinceTeen
 *  com.astryxion.chaospersists.ThunderBolt
 *  com.astryxion.chaospersists.WaterDragon
 *  net.minecraft.block.Block
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
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
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.entity.projectile.SmallFireballEntity
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
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.item.ThunderBolt;
import com.astryxion.chaospersists.entity.WaterDragon;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import com.google.common.base.Predicate;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.monster.IMob;
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
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.client.CInputPacket;
import net.minecraft.network.play.client.CPlayerPacket;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;

/*
 * Exception performing whole class analysis ignored.
 */
public class ThePrinceTeen
extends TameableEntity {
    private static final DataParameter<Integer> I20 = EntityDataManager.defineId(ThePrinceTeen.class, DataSerializers.INT);
    private static final DataParameter<Integer> I21 = EntityDataManager.defineId(ThePrinceTeen.class, DataSerializers.INT);
    private static final DataParameter<Integer> I22 = EntityDataManager.defineId(ThePrinceTeen.class, DataSerializers.INT);
    private static final DataParameter<Integer> I23 = EntityDataManager.defineId(ThePrinceTeen.class, DataSerializers.INT);
    private static final DataParameter<Integer> I24 = EntityDataManager.defineId(ThePrinceTeen.class, DataSerializers.INT);
    private static final DataParameter<Integer> I25 = EntityDataManager.defineId(ThePrinceTeen.class, DataSerializers.INT);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double boatYawHead;
    private int updateit = 1;
    private int playing = 0;
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private int wing_sound = 0;
    private BlockPos currentFlightTarget = null;
    private boolean target_in_sight = false;
    private int owner_flying = 0;
    private int flyaway = 0;
    private float moveSpeed = 0.32f;
    private float deltasmooth = 0.0f;
    private int which_attack = 0;
    private int fireballticker = 0;
    private int head1ext = 0;
    private int head2ext = 0;
    private int head3ext = 0;
    private int head1dir = 1;
    private int head2dir = 1;
    private int head3dir = 1;
    private int kill_count = 0;
    private int day_count = 0;
    private int is_day = 0;

    public ThePrinceTeen(EntityType<? extends ThePrinceTeen> type, World par1World) {
        super(type, par1World);
        this.xpReward = 300;
        this.setOrderedToSit(false);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner((TameableEntity)this, 1.1f, 12.0f, 2.0f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, net.minecraft.item.crafting.Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(3, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(4, new LookAtGoal(this, LivingEntity.class, 9.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new RestrictSunGoal(this));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal((CreatureEntity)this, LivingEntity.class, 0, true, false, new com.google.common.base.Predicate<LivingEntity>() { @Override public boolean apply(LivingEntity e) { return e instanceof IMob; } }));
        }
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.ejectPassengers();
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
    }

    public ThePrinceTeen(World par1World, double par2, double par4, double par6) {
        this((EntityType<? extends ThePrinceTeen>)net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation("chaospersists", "the_young_prince")), par1World);
        this.setPos(par2, par4, par6);
        this.setDeltaMovement(0.0, 0.0, 0.0);
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return null;
    }

    public static net.minecraft.entity.ai.attributes.AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1500.0)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, 50.0)
                .build();
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

    @Nullable
    @Override
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
            double offsetY = this.getMountedYOffset() + passenger.getMyRidingOffset();
            float yawRad = (float)Math.toRadians(this.yBodyRot);
            double offsetX = -Math.sin(yawRad) * 0.5;
            double offsetZ = Math.cos(yawRad) * 0.5;
            passenger.setPos(this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ);
        }
    }

    @Override
    public void travel(Vector3d travelVector) {
        float strafe = (float)travelVector.x;
        float vertical = (float)travelVector.y;
        float forward = (float)travelVector.z;
        if (!this.getPassengers().isEmpty() && this.getControllingPassenger() instanceof LivingEntity) {
            LivingEntity rider = (LivingEntity)this.getControllingPassenger();
            this.yRot = rider.yRot;
            this.yRotO = this.yRot;
            this.xRot = rider.xRot;
            this.yBodyRot = this.yRot;
            this.yHeadRot = this.yRot;
            this.setNoGravity(true);

            float moveStrafe = rider.xxa;
            float moveForward = rider.zza;
            double flightSpeed = 1.13;

            float f = moveStrafe * moveStrafe + moveForward * moveForward;
            if (f >= 1.0e-4F) {
                f = MathHelper.sqrt(f);
                if (f < 1.0F) {
                    f = 1.0F;
                }
                f = (float)(flightSpeed / (double)f);
                moveStrafe *= f;
                moveForward *= f;
                float f1 = MathHelper.sin(this.yRot * 0.017453292F);
                float f2 = MathHelper.cos(this.yRot * 0.017453292F);
                this.setDeltaMovement((double)(moveStrafe * f2 - moveForward * f1), this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, (double)(moveForward * f2 + moveStrafe * f1));
            } else {
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.5, 1.0, 1.0);
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 1.0, 0.5);
            }

            boolean riderJumping = false;
            try {
                Boolean b = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, rider, "jumping");
                if (b != null) {
                    riderJumping = b;
                }
            } catch (Exception ignored) {
            }

            if (riderJumping || ChaosPersists.flyup_keystate != 0) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.06, 0.0);
            } else if (rider.xRot > 45.0F && moveForward > 0.0F) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.05), 0.0);
            } else {
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.91, 1.0);
                if (Math.abs(this.getDeltaMovement().y) < 0.05) {
                    this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
                }
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setNoGravity(false);
            super.travel(travelVector);
        }
    }

    public int getHead1Ext() {
        return this.entityData.get(I22).intValue();
    }

    public int getHead2Ext() {
        return this.entityData.get(I23).intValue();
    }

    public int getHead3Ext() {
        return this.entityData.get(I25).intValue();
    }

    public void setHead1Ext(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(I22, par1);
    }

    public void setHead2Ext(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(I23, par1);
    }

    public void setHead3Ext(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(I25, par1);
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
        this.entityData.define(I20, 0);
        this.entityData.define(I21, 0);
        this.entityData.define(I24, 1);
        this.entityData.define(I22, 0);
        this.entityData.define(I23, 0);
        this.entityData.define(I25, 0);
        this.setActivity(0);
        this.setAttacking(0);
        this.setTame(false);
        this.setThePrinceTeenFire(1);
        this.noPhysics = false;
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
        return 1500;
    }

    public int getThePrinceTeenHealth() {
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
        return 18;
    }

    protected void jumpFromGround() {
        super.jumpFromGround();
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.25, 0.0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.isOrderedToSit()) {
            return null;
        }
        if (this.getActivity() == 1 && !this.onGround && this.getPassengers().isEmpty()) {
            return com.astryxion.chaospersists.core.ChaosSounds.ROAR;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_DEATH;
    }

    protected float getSoundVolume() {
        return 0.6f;
    }

    public float getVoicePitch() {
        return 0.75f;
    }

    public boolean canBePushed() {
        return false;
    }

    public double getMountedYOffset() {
        return 2.75;
    }

    protected Item getDropItem() {
        return ChaosPersists.ThePrinceEgg;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.ThePrinceEgg, 1);
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        double ks = 1.75;
        double inair = 0.1;
        float iskraken = 1.0f;
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            MobEntity e;
            if (par1Entity instanceof Kraken) {
                iskraken = 2.0f;
            }
            par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), iskraken * 45.0f);
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            if (par1Entity instanceof MobEntity && (e = (MobEntity)par1Entity).getHealth() <= 0.0f) {
                ++this.kill_count;
            }
        }
        return true;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = null;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("inFire")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("onFire")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("lava")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return ret;
        }
        this.setOrderedToSit(false);
        this.setActivity(1);
        e = par1DamageSource.getEntity();
        if (e != null && e instanceof BetterFireball) {
            e.remove();
            return ret;
        }
        if (e != null && e instanceof SmallFireballEntity) {
            e.remove();
            return ret;
        }
        if (e != null && e instanceof ThePrinceTeen) {
            return false;
        }
        if (e != null && e instanceof Spyro) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        this.hurt_timer = 20;
        if (e != null && e instanceof LivingEntity) {
            if (this.isTame() && e instanceof PlayerEntity) {
                return false;
            }
            this.setTarget((LivingEntity)e);
            this.setTarget(e instanceof LivingEntity ? (LivingEntity)e : null);
            this.getNavigation().moveTo((Entity)((LivingEntity)e), 1.2);
            ret = true;
        }
        return ret;
    }

    public void customServerAiStep() {
        LivingEntity e = null;
        if (this.getActivity() == 0 || !this.getPassengers().isEmpty()) {
            super.customServerAiStep();
        }
        if (!this.isOrderedToSit() && this.getActivity() == 0 && this.getPassengers().isEmpty() && this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(10) == 1) {
            e = this.findSomethingToAttack();
            if (e != null) {
                this.setActivity(1);
            } else {
                this.setAttacking(0);
            }
        }
        if (this.kill_count > 25 && this.day_count > 10) {
            Entity ent = null;
            ThePrinceAdult d = null;
            ent = ThePrinceTeen.spawnCreature((World)this.level, (String)"The Young Adult Prince", (double)this.getX(), (double)this.getY(), (double)this.getZ());
            if (ent != null) {
                d = (ThePrinceAdult)ent;
                if (this.isTame()) {
                    d.setTame(true);
                    d.setOwnerUUID(this.getOwnerUUID());
                }
                this.remove();
            }
        }
        if (this.is_day == 0) {
            this.is_day = 1;
            if (!this.level.isDay()) {
                this.is_day = -1;
            }
        } else {
            if (this.is_day == -1 && this.level.isDay()) {
                ++this.day_count;
            }
            this.is_day = 1;
            if (!this.level.isDay()) {
                this.is_day = -1;
            }
        }
    }

    public void always_do() {
        if (this.level.random.nextInt(250) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(2.0f);
        }
        if (this.level.random.nextInt(250) == 0) {
            this.setTarget(null);
        }
        if (this.isOrderedToSit()) {
            return;
        }
        this.owner_flying = 0;
        if (this.level.random.nextInt(50) == 1 && !this.isOrderedToSit() && !this.target_in_sight && this.getPassengers().isEmpty()) {
            if (this.level.random.nextInt(15) == 1) {
                this.setActivity(1);
            }
        }
    }

    public void fly_with_rider() {
        LivingEntity e = null;
        if (!this.isAlive()) {
            return;
        }
        if (this.isOrderedToSit()) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        if (this.level.random.nextInt(5) == 1 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            e = this.findSomethingToAttack();
            if (e != null) {
                this.setAttacking(1);
                if (this.distanceToSqr((Entity)e) < (double)((8.0f + e.getBbWidth() / 2.0f) * (8.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget((LivingEntity)e);
                } else if (this.distanceToSqr((Entity)e) > 100.0 && this.distanceToSqr((Entity)e) < 625.0 && !this.isInWater() && this.getThePrinceTeenFire() != 0) {
                    this.shoot_something(e.getX(), e.getY(), e.getZ());
                }
            } else {
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
        if (MyUtils.isRoyalty((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof Mothra) {
            return true;
        }
        if (par1Mob instanceof Kraken) {
            return true;
        }
        if (par1Mob instanceof Leon) {
            Leon l = (Leon)par1Mob;
            if (l.isTame()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof WaterDragon) {
            WaterDragon l = (WaterDragon)par1Mob;
            if (l.isTame()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof GammaMetroid) {
            GammaMetroid l = (GammaMetroid)par1Mob;
            if (l.isTame()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(25.0, 20.0, 25.0));
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
        return false;
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
        super.tick();
        this.noPhysics = this.getActivity() != 0;
        if (!this.level.isClientSide) {
            int i;
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
            this.setHead1Ext(this.head1ext);
            this.setHead2Ext(this.head2ext);
            this.setHead3Ext(this.head3ext);
        }
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.getActivity() == 1 && !this.onGround) {
            ++this.wing_sound;
            if (this.wing_sound > 20) {
                if (!this.level.isClientSide) {
                    this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, this.getSoundSource(), 0.5f, 1.0f);
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
        if (this.getActivity() == 0 && this.isTame() && this.getOwner() != null && !this.isOrderedToSit() && this.distanceToSqr((Entity)(e = this.getOwner())) > 400.0) {
            this.setActivity(1);
        }
        MyUtils.enforceDragonMountGroundSafety(this);
        if (this.getPassengers().isEmpty()) {
            this.moveTowardsClosestSpace(this.getX(), this.getY(), this.getZ());
        }
    }

    private void fly_without_rider() {
        Block bid;
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 10;
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
        boolean toofar = false;
        if (this.currentFlightTarget == null) {
            do_new = true;
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (!this.getPassengers().isEmpty()) {
            return;
        }
        if (this.isTame() && this.getOwner() != null) {
            e = this.getOwner();
            has_owner = true;
            ox = e.getX();
            oy = e.getY();
            oz = e.getZ();
            if (this.distanceToSqr((Entity)e) > 400.0) {
                toofar = true;
                this.target_in_sight = false;
                this.setAttacking(0);
                this.setOrderedToSit(false);
                this.flyaway = 0;
                do_new = true;
            }
        }
        if (this.isOrderedToSit()) {
            return;
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
        if (this.flyaway > 0) {
            --this.flyaway;
        }
        if (!toofar && this.flyaway == 0 && this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(7) == 1) {
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
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
                    if (this.distanceToSqr((Entity)e) < (double)((8.0f + e.getBbWidth() / 2.0f) * (8.0f + e.getBbWidth() / 2.0f))) {
                        this.doHurtTarget((LivingEntity)e);
                        this.flyaway = 5 + this.level.random.nextInt(15);
                        do_new = true;
                    } else if (this.distanceToSqr((Entity)e) < 400.0 && !this.isInWater() && this.getThePrinceTeenFire() != 0 && this.level.random.nextInt(2) == 1) {
                        this.shoot_something(e.getX(), e.getY(), e.getZ());
                    }
                }
            } else {
                this.target_in_sight = false;
                this.flyaway = 0;
                this.setAttacking(0);
            }
        }
        if (this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 2.1f) {
            do_new = true;
        }
        if (do_new && !this.target_in_sight || do_new && this.flyaway != 0) {
            bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                int gox = (int)this.getX();
                int goy = (int)this.getY();
                int goz = (int)this.getZ();
                if (has_owner) {
                    gox = (int)ox;
                    goy = (int)oy;
                    goz = (int)oz;
                    if (this.owner_flying == 0) {
                        zdir = this.level.random.nextInt(14) + 5;
                        xdir = this.level.random.nextInt(14) + 5;
                    } else {
                        zdir = this.level.random.nextInt(6);
                        xdir = this.level.random.nextInt(6);
                    }
                } else {
                    zdir = this.level.random.nextInt(10) + 16;
                    xdir = this.level.random.nextInt(10) + 16;
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
        double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        double obstruction_factor = 0.0;
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
            if (this.isTame() && this.getOwner() != null && this.distanceToSqr((Entity)(e = this.getOwner())) > 64.0) {
                speed_factor = 3.5;
            }
        }
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) - this.getDeltaMovement().x) * 0.15 * speed_factor, (Math.signum(var3) - this.getDeltaMovement().y) * 0.21 * speed_factor, (Math.signum(var5) - this.getDeltaMovement().z) * 0.15 * speed_factor);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = (float)(0.75 * speed_factor);
        this.yRot += var8 / 4.0f;
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.level.isClientSide && this.getPassengers().isEmpty()) {
            this.setActivity(0);
            this.owner_flying = 0;
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
            this.noPhysics = false;
            this.setNoGravity(false);
            this.moveTowardsClosestSpace(this.getX(), this.getY(), this.getZ());
            MyUtils.enforceDragonMountGroundSafety(this);
        }
    }

    @Override
    public void aiStep() {
        List list = null;
        Entity listEntity = null;
        double d6 = this.random.nextFloat() * 2.0f - 1.0f;
        double d7 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7;
        double obstruction_factor = 0.0;
        double relative_g = 0.0;
        double max_speed = 0.95;
        double gh = 1.0;
        double rt = 0.0;
        double pi = 3.1415926545;
        double deltav = 0.0;
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
        } else {
            if (this.getActivity() != 0) {
                if (this.fireballticker > 0) {
                    --this.fireballticker;
                }
                if (!this.getPassengers().isEmpty()) {
                    PlayerEntity pp = (PlayerEntity)this.getPassengers().get(0);
                    if (this.fireballticker == 0 && (pp.xxa < -0.001f || pp.xxa > 0.001f)) {
                        double cx;
                        double cz;
                        double yoff = 1.5;
                        double xzoff = 7.5;
                        ++this.which_attack;
                        if (this.which_attack > 2) {
                            this.which_attack = 0;
                        }
                        if (this.which_attack == 0) {
                            cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot - 10.0f));
                            cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot - 10.0f));
                            BetterFireball bf = new BetterFireball(this.level, (LivingEntity)this, 0.0, 0.0, 0.0);
                            bf.setNotMe();
                            bf.setPos(cx, this.getY() + (yoff += (double)((float)this.getHead1Ext() * 0.04f)), cz);
                            cx = Math.cos(Math.toRadians(pp.yHeadRot + 90.0f));
                            cz = Math.sin(Math.toRadians(pp.yHeadRot + 90.0f));
                            double cy = - Math.sin(Math.toRadians(pp.xRot));
                            double d3 = MathHelper.sqrt((double)(cx * cx + cy * cy + cz * cz));
                            bf.accelerationX = cx / d3 * 0.1;
                            bf.accelerationY = cy / d3 * 0.1;
                            bf.accelerationZ = cz / d3 * 0.1;
                            bf.setDeltaMovement(this.getDeltaMovement().x, bf.getDeltaMovement().y, bf.getDeltaMovement().z);
                            bf.setDeltaMovement(bf.getDeltaMovement().x, this.getDeltaMovement().y, bf.getDeltaMovement().z);
                            bf.setDeltaMovement(bf.getDeltaMovement().x, bf.getDeltaMovement().y, this.getDeltaMovement().z);
                            com.astryxion.chaospersists.util.MyUtils.addEntityX(bf, -(this.getDeltaMovement().x * 3.0));
                            com.astryxion.chaospersists.util.MyUtils.addEntityY(bf, -(this.getDeltaMovement().y * 3.0));
                            com.astryxion.chaospersists.util.MyUtils.addEntityZ(bf, -(this.getDeltaMovement().z * 3.0));
                            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.TNT_PRIMED, this.getSoundSource(), 1.0f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
                            this.level.addFreshEntity((Entity)bf);
                        }
                        if (this.which_attack == 1) {
                            cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot + 10.0f));
                            cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot + 10.0f));
                            IceBall var2 = new IceBall(this.level, cx, this.getY() + (yoff += (double)((float)this.getHead3Ext() * 0.04f)), cz);
                            var2.moveTo(cx, this.getY() + yoff, cz, pp.yRot + 90.0f, pp.xRot);
                            var2.setIceMaker(1);
                            double var3 = Math.cos(Math.toRadians(pp.yRot + 90.0f));
                            double var5 = - Math.sin(Math.toRadians(pp.xRot));
                            double var77 = Math.sin(Math.toRadians(pp.yRot + 90.0f));
                            float var9 = MathHelper.sqrt((double)(var3 * var3 + var77 * var77)) * 0.2f;
                            var2.shoot(var3, var5 + (double)var9, var77, 1.4f, 5.0f);
                            com.astryxion.chaospersists.util.MyUtils.addEntityX(var2, -(this.getDeltaMovement().x * 3.0));
                            com.astryxion.chaospersists.util.MyUtils.addEntityY(var2, -(this.getDeltaMovement().y * 3.0));
                            com.astryxion.chaospersists.util.MyUtils.addEntityZ(var2, -(this.getDeltaMovement().z * 3.0));
                            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(var2, 2.0, 2.0, 2.0);
                            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.FIREWORK_ROCKET_LAUNCH, this.getSoundSource(), 0.75f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
                            this.level.addFreshEntity((Entity)var2);
                        }
                        if (this.which_attack == 2) {
                            cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
                            cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
                            ThunderBolt lb = new ThunderBolt(this.level, (LivingEntity)pp);
                            lb.moveTo(cx, this.getY() + (yoff += (double)((float)this.getHead2Ext() * 0.04f)), cz, pp.yRot + 90.0f, pp.xRot);
                            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 3.0, 3.0, 3.0);
                            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 0.75f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
                            this.level.addFreshEntity((Entity)lb);
                        }
                        this.fireballticker = 10;
                    }
                    if (!this.level.isClientSide && (list = this.level.getEntities((Entity)this, this.getBoundingBox().inflate(3.25, 4.0, 3.25))) != null && !list.isEmpty()) {
                        for (int l = 0; l < list.size(); ++l) {
                            listEntity = (Entity)list.get(l);
                            if (listEntity == this.getPassengers().get(0) || !listEntity.isAlive() || !listEntity.isPushable()) continue;
                            listEntity.push((Entity)this);
                        }
                    }
                    this.fly_with_rider();
                    if (!this.getPassengers().isEmpty() && this.getPassengers().get(0).removed) {
                        this.ejectPassengers();
                    }
                } else {
                    this.fly_without_rider();
                }
            }
            this.always_do();
        }
    }

    public void updateRiderPosition() {
        if (!this.getPassengers().isEmpty()) {
            float f = 0.65f;
            this.getPassengers().get(0).setPos(this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + this.getMountedYOffset() + this.getPassengers().get(0).getMyRidingOffset(), this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)));
        }
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
        if (var2 != null && var2.getItem() == Item.byBlock((Block)Blocks.DIAMOND_BLOCK) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
            if (!this.level.isClientSide) {
                this.setTame(true);
                this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent(this, (byte)7);
                this.heal((float)this.mygetMaxHealth() - this.getHealth());
                this.kill_count = 1000;
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
        if (this.isTame()) {
            if (!this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                return super.mobInteract(par1PlayerEntityEntity, hand);
            }
            if (var2 == null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (!this.level.isClientSide) {
                    par1PlayerEntityEntity.startRiding(this);
                    this.setActivity(1);
                    this.setOrderedToSit(false);
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.BEEF && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
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
            if (var2 != null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && var2.isEdible()) {
                if (!this.level.isClientSide) {
                    Item var3 = (Item)var2.getItem();
                    if ((float)this.mygetMaxHealth() > this.getHealth()) {
                        this.heal((float)((var3.getFoodProperties() != null ? var3.getFoodProperties().getNutrition() : 0) * 10));
                    }
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
            if (var2 != null && var2.getItem() == Item.byBlock((Block)Blocks.ICE) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)6);
                    this.setThePrinceTeenFire(0);
                    String healthMessage = new String();
                    healthMessage = String.format("Fireballs extinguished.", new Object[0]);
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
            if (var2 != null && var2.getItem() == Items.FLINT_AND_STEEL && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)6);
                    this.setThePrinceTeenFire(1);
                    String healthMessage = new String();
                    healthMessage = String.format("Fireballs lit!", new Object[0]);
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
            if (var2 != null && var2.getItem() == Items.DIAMOND && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && !this.level.isClientSide) {
                Entity ent = null;
                ThePrince d = null;
                ent = ThePrinceTeen.spawnCreature((World)this.level, (String)"The Prince", (double)this.getX(), (double)this.getY(), (double)this.getZ());
                if (ent != null) {
                    d = (ThePrince)ent;
                    if (this.isTame()) {
                        d.setTame(true);
                        d.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                        d.set_ok_to_grow();
                    }
                    this.remove();
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (this.isTame() && var2 != null && var2.getItem() == Items.NAME_TAG && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                this.setCustomName(var2.getHoverName());
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
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
        return this.entityData.get(I20).intValue();
    }

    public void setAttacking(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(I20, par1);
    }

    public int getActivity() {
        return this.entityData.get(I21).intValue();
    }

    public void setActivity(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(I21, par1);
    }

    public int getThePrinceTeenFire() {
        return this.entityData.get(I24).intValue();
    }

    public void setThePrinceTeenFire(int par1) {
        if (this.level.isClientSide) {
            return;
        }
        this.entityData.set(I24, par1);
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.util.ResourceLocation res = par1.contains(":")
            ? new net.minecraft.util.ResourceLocation(par1)
            : new net.minecraft.util.ResourceLocation("chaospersists", par1.toLowerCase(Locale.ROOT).replace(' ', '_'));
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

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (!this.getPassengers().isEmpty()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("ThePrinceTeenAttacking", this.getAttacking());
        par1CompoundNBT.putInt("ThePrinceTeenActivity", this.getActivity());
        par1CompoundNBT.putInt("ThePrinceTeenFire", this.getThePrinceTeenFire());
        par1CompoundNBT.putInt("SpyroKill", this.kill_count);
        par1CompoundNBT.putInt("SpyroDay", this.day_count);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.setAttacking(par1CompoundNBT.getInt("ThePrinceTeenAttacking"));
        this.setActivity(par1CompoundNBT.getInt("ThePrinceTeenActivity"));
        this.setThePrinceTeenFire(par1CompoundNBT.getInt("ThePrinceTeenFire"));
        this.kill_count = par1CompoundNBT.getInt("SpyroKill");
        this.day_count = par1CompoundNBT.getInt("SpyroDay");
    }

    private void shoot_something(double x, double y, double z) {
        double rr = 0.0;
        double rhdir = 0.0;
        double rdd = 0.0;
        double pi = 3.1415926545;
        int which = this.level.random.nextInt(3);
        if (which == 0) {
            rr = Math.atan2(z - this.getZ(), x - this.getX());
            rdd = Math.abs(rr - (rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f))) % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            if ((rdd = Math.abs(rdd)) < 0.5) {
                this.firecanon(x, y, z);
            }
        } else if (which == 1) {
            rr = Math.atan2(z - this.getZ(), x - this.getX());
            rdd = Math.abs(rr - (rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f))) % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            if ((rdd = Math.abs(rdd)) < 0.5) {
                this.firecanonl(x, y, z);
            }
        } else {
            rr = Math.atan2(z - this.getZ(), x - this.getX());
            rdd = Math.abs(rr - (rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f))) % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            if ((rdd = Math.abs(rdd)) < 0.5) {
                this.firecanoni(x, y, z);
            }
        }
    }

    private void firecanon(double x, double y, double z) {
        double yoff = 3.5;
        double xzoff = 6.0;
        BetterFireball bf = null;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        float r1 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r2 = 3.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r3 = 5.0f * (this.level.random.nextFloat() - this.level.random.nextFloat());
        bf = new BetterFireball(this.level, (LivingEntity)this, x - cx + (double)r1, y + 0.25 - (this.getY() + yoff) + (double)r2, z - cz + (double)r3);
        bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
        bf.setPos(cx, this.getY() + yoff, cz);
        bf.setBig();
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
        this.level.addFreshEntity((Entity)bf);
    }

    private void firecanonl(double x, double y, double z) {
        double yoff = 3.5;
        double xzoff = 6.0;
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
        var3 = x - lb.getX();
        var5 = y + 0.25 - lb.getY();
        var7 = z - lb.getZ();
        var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
        lb.shoot(var3, var5 + (double)var9, var7, 1.4f, 4.0f);
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 3.0, 3.0, 3.0);
        this.level.addFreshEntity((Entity)lb);
    }

    private void firecanoni(double x, double y, double z) {
        double yoff = 3.5;
        double xzoff = 6.0;
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
        var3 = x - lb.getX();
        var5 = y + 0.25 - lb.getY();
        var7 = z - lb.getZ();
        var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
        lb.shoot(var3, var5 + (double)var9, var7, 1.4f, 4.0f);
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 3.0, 3.0, 3.0);
        this.level.addFreshEntity((Entity)lb);
    }
}

