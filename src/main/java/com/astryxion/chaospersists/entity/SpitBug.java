/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Acid
 *  com.astryxion.chaospersists.EnderKnight
 *  com.astryxion.chaospersists.EnderReaper
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Hydrolisc
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.SpitBug
 *  com.astryxion.chaospersists.TrooperBug
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
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
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Creeper
 *  net.minecraft.entity.monster.EndermanEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.Path
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.item.Acid;
import com.astryxion.chaospersists.entity.EnderKnight;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Hydrolisc;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.TrooperBug;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SpitBug
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(SpitBug.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int force_sync = 50;
    private int hurt_timer = 0;
    private float moveSpeed = 0.33f;
    private int stream_count = 0;

    public SpitBug(EntityType<? extends SpitBug> type, World par1World) {
        super(type, par1World);
        this.xpReward = 50;
                
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 0.8999999761581421, false, 512, () -> true));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(3, new LookAtGoal((MobEntity)this, PlayerEntity.class, 10.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
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
        this.force_sync = 50;
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.SpitBug_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.33)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.SpitBug_stats.attack)
                .build();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        if (this.onGround) {
            this.getNavigation().stop();
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.SpitBug_stats.health;
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
        return ChaosPersists.SpitBug_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    protected void jump() {
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.75, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.75);
        float f = 0.2f + Math.abs(this.level.random.nextFloat() * 0.45f);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, -((double)f * Math.sin(Math.toRadians(this.yHeadRot))), 0.0, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.0, (double)f * Math.cos(Math.toRadians(this.yHeadRot)));
        this.setOnGround(false);
    }

    protected void jumpAtEntity(LivingEntity e) {
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.75, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.75);
        float f = 0.2f + Math.abs(this.level.random.nextFloat() * 0.25f);
        float d = (float)Math.atan2(e.getX() - this.getX(), e.getZ() - this.getZ());
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (double)f * Math.sin(d), 0.0, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.0, (double)f * Math.cos(d));
        this.setOnGround(false);
    }

    public int getSpitBugHealth() {
        return (int)this.getHealth();
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.random.nextInt(4) == 0) {
            return com.astryxion.chaospersists.core.ChaosSounds.CLATTER;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.CRUNCH;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.EMPERORSCORPION_DEATH;
    }

    protected float getSoundVolume() {
        return 0.75f;
    }

    protected float getVoicePitch() {
        return 1.5f;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(10);
        if (i == 0) {
            return Items.GOLD_NUGGET;
        }
        if (i == 1) {
            return ChaosPersists.UraniumNugget;
        }
        if (i == 2) {
            return ChaosPersists.TitaniumNugget;
        }
        return null;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = 1 + this.level.random.nextInt(3);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.MyAmethyst, 1);
        }
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        double ks = 0.5;
        double inair = 0.1;
        int var2 = 6;
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity != null && par1Entity instanceof LivingEntity) {
                float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                    inair *= 2.0;
                }
                par1Entity.setDeltaMovement(par1Entity.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
            }
            return true;
        }
        return false;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (!par1DamageSource.getMsgId().equals("cactus") && !par1DamageSource.getMsgId().equals("fall")) {
            ret = super.hurt(par1DamageSource, par2);
            this.hurt_timer = 15;
            Entity e = par1DamageSource.getEntity();
            if (e != null && e instanceof MobEntity) {
                this.setTarget((LivingEntity)((MobEntity)e));
                this.getNavigation().moveTo((Entity)((MobEntity)e), 1.2);
                ret = true;
            }
        }
        return ret;
    }

    protected void customServerAiStep() {
        LivingEntity e = null;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.level.random.nextInt(5) == 0) {
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.level.random.nextInt(15) == 1 && !this.onGround) {
                    this.jumpAtEntity(e);
                } else if (this.distanceToSqr((Entity)e) < 9.0) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(6) == 0 || this.level.random.nextInt(7) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                        if (!this.level.isClientSide && this.level.random.nextInt(3) != 1) {
                            this.level.playSound(null, e.getX(), e.getY(), e.getZ(), com.astryxion.chaospersists.core.ChaosSounds.CLATTER, this.getSoundSource(), 1.0f, 1.0f);
                        }
                    }
                } else if (!this.onGround) {
                    this.getNavigation().moveTo((Entity)e, 0.5);
                    this.watercanon(e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.level.random.nextInt(150) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(1.0f);
        }
    }

    private void watercanon(LivingEntity e) {
        double yoff = 1.5;
        double xzoff = 1.5;
        if (this.stream_count > 0) {
            this.setAttacking(1);
            Acid var2 = new Acid(this.level, e.getX() - this.getX(), e.getY() + 0.75 - (this.getY() + yoff), e.getZ() - this.getZ());
            var2.moveTo(this.getX() - xzoff * Math.sin(Math.toRadians(this.yHeadRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot)), this.yHeadRot, this.xRot);
            double var3 = e.getX() - var2.getX();
            double var5 = e.getY() + 0.25 - var2.getY();
            double var7 = e.getZ() - var2.getZ();
            float var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
            var2.shoot(var3, var5 + (double)var9, var7, 1.1f, 6.0f);
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level.addFreshEntity((Entity)var2);
            --this.stream_count;
        } else {
            this.setAttacking(0);
        }
        if (this.stream_count <= 0 && this.random.nextInt(7) == 1) {
            this.stream_count = 8;
        }
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
        if (par1Mob instanceof EnderReaper) {
            return false;
        }
        if (par1Mob instanceof EnderKnight) {
            return false;
        }
        if (par1Mob instanceof EndermanEntity) {
            return false;
        }
        if (par1Mob instanceof Hydrolisc) {
            return false;
        }
        if (par1Mob instanceof CreeperEntity) {
            return false;
        }
        if (par1Mob instanceof SpitBug) {
            return false;
        }
        if (par1Mob instanceof TrooperBug) {
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

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 7.0, 12.0));
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

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
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
                    if (s == null || !s.equals("Spit Bug")) continue;
                    return true;
                }
            }
        }
        if (this.level.isDay() && this.level.random.nextInt(20) > 1) {
            return false;
        }
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        for (k = -2; k < 2; ++k) {
            for (j = -2; j < 2; ++j) {
                for (i = 1; i < 4; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        return true;
    }
}

