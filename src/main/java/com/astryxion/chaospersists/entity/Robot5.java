/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.LaserBall
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Robot5
 *  net.minecraft.block.Block
 *  net.minecraft.block.PistonBlock
 *  net.minecraft.block.TallGrassBlock
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
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
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

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.item.LaserBall;
import java.util.Iterator;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Robot5
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Robot5.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private int reload_ticker = 0;
    private float moveSpeed = 0.3f;

    public Robot5(EntityType<? extends Robot5> type, World par1World) {
        super(type, par1World);
                this.xpReward = 20;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(2, new MoveThroughVillageGoal((CreatureEntity)(Object)this, 0.8999999761581421, false, 32, () -> true));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

        public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Robot5_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Robot5_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
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
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Robot5_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.Robot5_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    @Override
    protected void jumpFromGround() {
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.25, 0.0);
        super.jumpFromGround();
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.random.nextInt(4) == 0) {
            return com.astryxion.chaospersists.core.ChaosSounds.ROBOT_LIVING;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.ROBOT_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ROBOT_DEATH;
    }

    protected float getSoundVolume() {
        return 0.5f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.IRON_INGOT;
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
        int var4;
        ItemStack is = null;
        int var5 = 5 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(ChaosPersists.MyLaserBall, 4);
        }
        int i = 2 + this.level.random.nextInt(5);
        block13 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(15);
            switch (var3) {
                case 0: {
                    is = this.dropItemRand(Items.REDSTONE, 1);
                    continue block13;
                }
                case 1: {
                    is = this.dropItemRand(Items.REPEATER, 1);
                    continue block13;
                }
                case 2: {
                    is = this.dropItemRand(Items.COMPARATOR, 1);
                    continue block13;
                }
                case 3: {
                    is = this.dropItemRand(Blocks.REDSTONE_BLOCK.asItem(), 1);
                    continue block13;
                }
                case 4: {
                    is = this.dropItemRand(Blocks.DISPENSER.asItem(), 1);
                    continue block13;
                }
                case 5: {
                    is = this.dropItemRand(Blocks.STICKY_PISTON.asItem(), 1);
                    continue block13;
                }
                case 6: {
                    is = this.dropItemRand(Blocks.PISTON.asItem(), 1);
                    continue block13;
                }
                case 7: {
                    is = this.dropItemRand(Blocks.LEVER.asItem(), 1);
                    continue block13;
                }
                case 8: {
                    is = this.dropItemRand(Blocks.REDSTONE_BLOCK.asItem(), 1);
                    continue block13;
                }
                case 9: {
                    is = this.dropItemRand(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE.asItem(), 1);
                    break;
                }
            }
        }
    }

    @Override
    public net.minecraft.util.ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, net.minecraft.util.Hand hand) {
        return net.minecraft.util.ActionResultType.PASS;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            if (super.doHurtTarget(par1Entity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.reload_ticker > 0) {
            --this.reload_ticker;
            if (this.reload_ticker < 15) {
                this.setAttacking(0);
            }
        }
        if (this.reload_ticker == 0) {
            LivingEntity e = null;
            if (this.level.random.nextInt(50) == 1) {
                this.setTarget(null);
            }
            if ((e = this.getTarget()) != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            this.reload_ticker = 20;
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) < 900.0) {
                    double rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                    double rhdir = Math.toRadians((this.yHeadRot + 90.0f) % 360.0f);
                    double pi = 3.1415926545;
                    double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                    if (rdd > pi) {
                        rdd -= pi * 2.0;
                    }
                    if ((rdd = Math.abs(rdd)) < 0.5) {
                        double yoff = 1.6;
                        double xzoff = 1.6;
                        LaserBall var2 = new LaserBall(this.level, e.getX() - this.getX(), e.getY() - (this.getY() + yoff), e.getZ() - this.getZ());
                        var2.moveTo(this.getX() - xzoff * Math.sin(Math.toRadians(this.yHeadRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yHeadRot)), this.yHeadRot, this.xRot);
                        double var3 = e.getX() - var2.getX();
                        double var5 = e.getY() - var2.getY();
                        double var7 = e.getZ() - var2.getZ();
                        float var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
                        var2.shoot(var3, var5 + (double)var9, var7, 1.4f, 5.0f);
                        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.FIREWORK_ROCKET_LAUNCH, net.minecraft.util.SoundCategory.HOSTILE, 3.0f, 1.0f);
                        this.level.addFreshEntity((Entity)var2);
                        this.setAttacking(1);
                    }
                    if (this.distanceToSqr((Entity)e) > 36.0) {
                        this.getNavigation().moveTo((Entity)e, 0.5);
                    }
                }
            } else {
                this.setAttacking(0);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            ret = super.hurt(par1DamageSource, par2);
        }
        return ret;
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
        if (par1Mob instanceof MonsterEntity) {
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

    private LivingEntity findSomethingToAttack()
    {
      if (ChaosPersists.PlayNicely != 0) return null;
      List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(30.0D, 6.0D, 30.0D));
      Collections.sort(var5, this.TargetSorter);
      Iterator var2 = var5.iterator();

      while (var2.hasNext())
      {
        Entity var3 = (Entity)var2.next();
        LivingEntity var4 = (LivingEntity)var3;

        if (isSuitableTarget(var4, false))
        {
          return var4;
        }
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
                    if (s == null || !s.equals("Robo-Sniper")) continue;
                    return true;
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        for (k = -1; k < 1; ++k) {
            for (j = -1; j <= 1; ++j) {
                for (i = 1; i < 3; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR || bid == Blocks.GRASS_BLOCK) continue;
                    return false;
                }
            }
        }
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        return true;
    }
}

