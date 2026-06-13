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
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Robot4
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
 *  net.minecraft.world.Difficulty
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
import com.astryxion.chaospersists.render.RenderInfo;
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
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class Robot4
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Robot4.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> STATE2 = EntityDataManager.defineId(Robot4.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int reload_ticker = 0;
    private int was_attacked_ticker = 0;
    private float moveSpeed = 0.34f;

    public Robot4(EntityType<? extends Robot4> type, World par1World) {
        super(type, par1World);
                this.xpReward = 120;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
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
                .add(Attributes.MAX_HEALTH, ChaosPersists.Robot4_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.34)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Robot4_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(STATE2, (byte)0);
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
        return ChaosPersists.Robot4_stats.health;
    }

    public int getRobot4Health() {
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
        return ChaosPersists.Robot4_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void jumpFromGround() {
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.25, 0.0);
        super.jumpFromGround();
    }

    public void aiStep() {
        super.aiStep();
        if (this.level.isClientSide) {
            if (this.random.nextInt(3) == 1) {
                this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX() - 1.25 * Math.sin(Math.toRadians(this.yRot + 180.0f)), this.getY() + 3.0 + (double)this.level.random.nextFloat(), this.getZ() + 1.25 * Math.cos(Math.toRadians(this.yRot + 180.0f)), 0.0, (double)this.level.random.nextFloat() / 2.0, 0.0);
            }
            if (this.getAttacking() != 0) {
                this.level.addParticle(net.minecraft.particles.ParticleTypes.CRIT, this.getX() - 1.55 * Math.sin(Math.toRadians(this.yRot + 35.0f)), this.getY() + 2.25 + (double)this.level.random.nextFloat(), this.getZ() + 1.55 * Math.cos(Math.toRadians(this.yRot + 35.0f)), 0.0, (double)this.level.random.nextFloat(), 0.0);
            }
        }
    }

    public int getAttackStrength(Entity par1Entity) {
        int var2 = 0;
        if (this.level.getDifficulty() == Difficulty.EASY) {
            var2 = 15;
            if (this.level.getDifficulty() == Difficulty.NORMAL) {
                var2 = 20;
            } else if (this.level.getDifficulty() == Difficulty.HARD) {
                var2 = 25;
            }
        }
        return var2;
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
        return 1.0f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.QUARTZ;
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
        int var5 = 5 + this.level.random.nextInt(10);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(ChaosPersists.MyLaserBall, 4);
        }
        this.dropItemRand(ChaosPersists.MyRayGun, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int i = 10 + this.level.random.nextInt(15);
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
            double ks = 2.0;
            double inair = 0.12;
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
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
        }
        if (this.was_attacked_ticker > 0) {
            --this.was_attacked_ticker;
        }
        if (this.reload_ticker == 0 && this.level.random.nextInt(8) == 1) {
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
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) < 256.0) {
                    if (this.distanceToSqr((Entity)e) < (double)((3.0f + e.getBbWidth() / 2.0f) * (3.0f + e.getBbWidth() / 2.0f))) {
                        this.doHurtTarget((LivingEntity)e);
                    } else {
                        double rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        double rhdir = Math.toRadians((this.yHeadRot + 90.0f) % 360.0f);
                        double pi = 3.1415926545;
                        double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            double yoff = 2.0;
                            double xzoff = 1.75;
                            LaserBall var2 = new LaserBall(this.level, e.getX() - this.getX(), e.getY() - (this.getY() + yoff), e.getZ() - this.getZ());
                            var2.moveTo(this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot + 45.0f)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot + 45.0f)), this.yRot, this.xRot);
                            double var3 = e.getX() - var2.getX();
                            double var5 = e.getY() - var2.getY();
                            double var7 = e.getZ() - var2.getZ();
                            float var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
                            var2.shoot(var3, var5 + (double)var9, var7, 2.0f, 4.0f);
                            if (this.distanceToSqr((Entity)e) > 65.0) {
                                var2.setSpecial();
                                this.reload_ticker = 30;
                                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.FIREWORK_ROCKET_LAUNCH, net.minecraft.util.SoundCategory.HOSTILE, 3.5f, 0.5f);
                            } else {
                                this.reload_ticker = 10;
                                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.FIREWORK_ROCKET_LAUNCH, net.minecraft.util.SoundCategory.HOSTILE, 2.5f, 1.0f);
                            }
                            this.level.addFreshEntity((Entity)var2);
                        }
                        this.setAttacking(1);
                    }
                    this.getNavigation().moveTo((Entity)e, 0.75);
                }
            }
        }
        if (this.reload_ticker <= 0 && this.was_attacked_ticker <= 0) {
            this.setAttacking(0);
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            if (this.getShielding() != 0 || this.was_attacked_ticker != 0) {
                return false;
            }
            this.was_attacked_ticker = 65;
            this.setAttacking(1);
            ret = super.hurt(par1DamageSource, par2);
            Entity e = par1DamageSource.getEntity();
            if (e != null && e instanceof MobEntity) {
                this.setTarget((LivingEntity)((MobEntity)e));
                this.getNavigation().moveTo((Entity)((MobEntity)e), 1.2);
                ret = true;
            }
            return ret;
        }
        return false;
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
      List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
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

    public final int getShielding() {
        return this.entityData.get(STATE2).byteValue();
    }

    public final void setShielding(int par1) {
        this.entityData.set(STATE2, (byte)par1);
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
                    if (s == null || !s.equals("Robo-Warrior")) continue;
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
                for (i = 1; i < 6; ++i) {
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

