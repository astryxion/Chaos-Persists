/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Fairy
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class Fairy
extends FlyingEntity {
    private static final DataParameter<Integer> FAIRY_TYPE = EntityDataManager.defineId(Fairy.class, DataSerializers.INT);
    private static final ResourceLocation texture0 = new ResourceLocation("chaospersists", "textures/entity/fairytexture.png");
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/fairytexture2.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/fairytexture3.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/fairytexture4.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/fairytexture5.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/entity/fairytexture6.png");
    private static final ResourceLocation texture6 = new ResourceLocation("chaospersists", "textures/entity/fairytexture7.png");
    private static final ResourceLocation texture7 = new ResourceLocation("chaospersists", "textures/entity/fairytexture8.png");
    private static final ResourceLocation texture8 = new ResourceLocation("chaospersists", "textures/entity/fairytexture9.png");
    int my_blink = 0;
    int blinker = 0;
    int myspace = 0;
    public int fairy_type = 0;
    private int force_sync = 10;
    private BlockPos currentFlightTarget = null;
    private String myowner = null;
    private GenericTargetSorter TargetSorter = null;

    public Fairy(EntityType<? extends Fairy> type, World par1World) {
        super(type, par1World);
        this.my_blink = 20 + this.random.nextInt(20);
        if (par1World != null) {
            this.fairy_type = par1World.random.nextInt(9);
        }
                // renderDistanceWeight not settable in 1.12.2
        this.goalSelector.addGoal(0, new LookAtGoal(this, LivingEntity.class, 8.0f));
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 3.0).build();
    }

    public ResourceLocation getTexture(Fairy a) {
        if (this.fairy_type == 8) {
            return texture8;
        }
        if (this.fairy_type == 7) {
            return texture7;
        }
        if (this.fairy_type == 6) {
            return texture6;
        }
        if (this.fairy_type == 5) {
            return texture5;
        }
        if (this.fairy_type == 4) {
            return texture4;
        }
        if (this.fairy_type == 3) {
            return texture3;
        }
        if (this.fairy_type == 2) {
            return texture2;
        }
        if (this.fairy_type == 1) {
            return texture1;
        }
        return texture0;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FAIRY_TYPE, this.fairy_type);
    }

    public void setOwner(LivingEntity e) {
        String s;
        PlayerEntity p = null;
        if (e != null && e instanceof PlayerEntity && (s = (p = (PlayerEntity)e).getDisplayName().getString()) != null) {
            this.myowner = s;
        }
    }

    public float getBlink() {
        if (this.blinker < this.my_blink / 2) {
            return 240.0f;
        }
        return 0.0f;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        boolean var4 = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), 2.0f);
        return var4;
    }

    public int getArmorValue() {
        return 4;
    }

    protected float getSoundVolume() {
        return 0.25f;
    }

    protected float getVoicePitch() {
        return 1.7f;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return com.astryxion.chaospersists.core.ChaosSounds.RATHIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.BIG_SPLAT;
    }

    public boolean canBePushed() {
        return true;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    protected void collideWithNearbyEntities() {
    }

    public int mygetMaxHealth() {
        return 40;
    }

    protected Item getDropItem() {
        return Item.byBlock((Block)ChaosPersists.CrystalTorch);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void tick() {
        super.tick();
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.600000023841, 1.0);
        ++this.blinker;
        if (this.blinker > this.my_blink) {
            this.blinker = 0;
        }
        --this.force_sync;
        if (this.force_sync < 0) {
            this.force_sync = 10;
            if (this.level.isClientSide) {
                this.fairy_type = this.entityData.get(FAIRY_TYPE).intValue();
            } else {
                this.entityData.set(FAIRY_TYPE, this.fairy_type);
            }
        }
        long t = this.level.getGameTime();
        if ((t %= 24000L) < 12000L) {
            return;
        }
        if (this.level.isClientSide && this.level.random.nextInt(5) == 0 && this.getBlink() > 1.0f) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX(), this.getY() - 0.15000000596046448, this.getZ(), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 8.0f), (double)((- this.level.random.nextFloat()) / 8.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 8.0f));
        }
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        if (this.myowner == null) {
            this.myowner = "null";
        }
        par1CompoundNBT.putString("MyOwner", this.myowner);
        par1CompoundNBT.putInt("FairyType", this.fairy_type);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.myowner = par1CompoundNBT.getString("MyOwner");
        if (this.myowner != null && this.myowner.equals("null")) {
            this.myowner = null;
        }
        this.fairy_type = par1CompoundNBT.getInt("fairyType");
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
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8.0, 8.0, 8.0));
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

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new net.minecraft.util.math.RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.25, this.getZ()), new Vector3d(pX, pY, pZ), net.minecraft.util.math.RayTraceContext.BlockMode.COLLIDER, net.minecraft.util.math.RayTraceContext.FluidMode.NONE, this)).getType() == net.minecraft.util.math.RayTraceResult.Type.MISS;
    }

    protected void customServerAiStep() {
        PlayerEntity p;
        int keep_trying = 25;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.level.random.nextInt(200) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.5) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                int zdir = this.level.random.nextInt(8);
                int xdir = this.level.random.nextInt(8);
                if (this.level.random.nextInt(2) == 0) {
                    zdir = - zdir;
                }
                if (this.level.random.nextInt(2) == 0) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new BlockPos((int)this.getX() + xdir, (int)this.getY() + this.level.random.nextInt(5) - 2, (int)this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        } else if (this.level.random.nextInt(12) == 0 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity e = null;
            e = this.findSomethingToAttack();
            if (e != null) {
                this.currentFlightTarget = new BlockPos((int)e.getX(), (int)(e.getY() + 1.0), (int)e.getZ());
                if (this.distanceToSqr((Entity)e) < 6.0) {
                    this.doHurtTarget((LivingEntity)e);
                }
            }
        } else if (this.myowner != null && (p = (this.level.getServer() != null ? this.level.getServer().getPlayerList().getPlayerByName(this.myowner) : null)) != null) {
            if (this.distanceToSqr((Entity)p) > 64.0) {
                this.currentFlightTarget = new BlockPos((int)p.getX() + this.level.random.nextInt(3) - this.level.random.nextInt(3), (int)(p.getY() + 1.0), (int)p.getZ() + this.level.random.nextInt(3) - this.level.random.nextInt(3));
            }
            if (this.distanceToSqr((Entity)p) > 256.0) {
                this.setPos(p.getX() + (double)this.level.random.nextFloat() - (double)this.level.random.nextFloat(), p.getY(), p.getZ() + (double)this.level.random.nextFloat() - (double)this.level.random.nextFloat());
            }
        }
        if (this.level.random.nextInt(250) == 1) {
            this.heal(1.0f);
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.2 - this.getDeltaMovement().x) * 0.1, (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.1, (Math.signum(var5) * 0.2 - this.getDeltaMovement().z) * 0.1);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.zza = 0.2f;
        this.yRot += var8 / 4.0f;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        int sc = 0;
        for (int k = -1; k <= 1; ++k) {
            for (int j = -1; j <= 1; ++j) {
                Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY(), (int)this.getZ() + k)).getBlock();
                if (bid != Blocks.AIR) continue;
                ++sc;
            }
        }
        if (sc < 6) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    public void initCreature() {
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.myowner != null) {
            return false;
        }
        return true;
    }
}

