/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Robot2
 *  net.minecraft.block.Block
 *  net.minecraft.block.ChestBlock
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
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
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
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class Robot2
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Robot2.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int just_for_fun = 0;
    private float moveSpeed = 0.3f;

    public Robot2(EntityType<? extends Robot2> type, World par1World) {
        super(type, par1World);
                this.xpReward = 100;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(2, new MoveThroughVillageGoal((CreatureEntity)(Object)this, 0.8999999761581421, false, 32, () -> true));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

        public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Robot2_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Robot2_stats.attack)
                .build();
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
        return ChaosPersists.Robot2_stats.health;
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
        return ChaosPersists.Robot2_stats.defense;
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
        return 1.0f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Blocks.IRON_BLOCK.asItem();
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
        int var5 = 2 + this.level.random.nextInt(8);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Blocks.IRON_BLOCK.asItem(), 1);
        }
        var5 = 5 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.IRON_INGOT, 1);
        }
        int i = 5 + this.level.random.nextInt(10);
        block14 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(15);
            switch (var3) {
                case 0: {
                    is = this.dropItemRand(Items.REDSTONE, 1);
                    continue block14;
                }
                case 1: {
                    is = this.dropItemRand(Items.REPEATER, 1);
                    continue block14;
                }
                case 2: {
                    is = this.dropItemRand(Items.COMPARATOR, 1);
                    continue block14;
                }
                case 3: {
                    is = this.dropItemRand(Blocks.REDSTONE_BLOCK.asItem(), 1);
                    continue block14;
                }
                case 4: {
                    is = this.dropItemRand(Blocks.DISPENSER.asItem(), 1);
                    continue block14;
                }
                case 5: {
                    is = this.dropItemRand(Blocks.STICKY_PISTON.asItem(), 1);
                    continue block14;
                }
                case 6: {
                    is = this.dropItemRand(Blocks.PISTON.asItem(), 1);
                    continue block14;
                }
                case 7: {
                    is = this.dropItemRand(Blocks.LEVER.asItem(), 1);
                    continue block14;
                }
                case 8: {
                    is = this.dropItemRand(Blocks.REDSTONE_BLOCK.asItem(), 1);
                    continue block14;
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

    protected void destroyBlock(LivingEntity e) {
        double z;
        double y;
        double x = e.getX() + (double)this.level.random.nextFloat() - (double)this.level.random.nextFloat();
        net.minecraft.util.math.BlockPos pos = new net.minecraft.util.math.BlockPos((int)x, (int)(y = e.getY() - 1.0), (int)(z = e.getZ() + (double)this.level.random.nextFloat() - (double)this.level.random.nextFloat()));
        Block bid = this.level.getBlockState(pos).getBlock();
        if (bid == Blocks.OBSIDIAN) {
            return;
        }
        if (bid == Blocks.BEDROCK) {
            return;
        }
        if (bid == Blocks.QUARTZ_BLOCK) {
            return;
        }
        if (bid == Blocks.SPAWNER) {
            return;
        }
        if (bid == Blocks.REDSTONE_BLOCK) {
            return;
        }
        if (bid == Blocks.IRON_BLOCK) {
            return;
        }
        if (bid == Blocks.CHEST) {
            return;
        }
        if (bid != Blocks.AIR && this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) {
            this.level.setBlock(new net.minecraft.util.math.BlockPos((int)x, (int)y, (int)z), Blocks.AIR.defaultBlockState(), 3);
        }
    }

    protected void destroyNearbyBlocks() {
        for (int i = 0; i < 50; ++i) {
            double y;
            double z;
            double x = this.getX() + (double)this.level.random.nextFloat() * 6.5 - (double)this.level.random.nextFloat() * 6.5;
            net.minecraft.util.math.BlockPos pos = new net.minecraft.util.math.BlockPos((int)x, (int)(y = this.getY() + 0.1 + (double)this.level.random.nextFloat() * 8.5), (int)(z = this.getZ() + (double)this.level.random.nextFloat() * 6.5 - (double)this.level.random.nextFloat() * 6.5));
            Block bid = this.level.getBlockState(pos).getBlock();
            if (bid == Blocks.OBSIDIAN || bid == Blocks.BEDROCK || bid == Blocks.QUARTZ_BLOCK || bid == Blocks.SPAWNER || bid == Blocks.REDSTONE_BLOCK || bid == Blocks.IRON_BLOCK || bid == Blocks.CHEST || bid == Blocks.AIR || !this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) continue;
            this.level.setBlock(new net.minecraft.util.math.BlockPos((int)x, (int)y, (int)z), Blocks.AIR.defaultBlockState(), 3);
        }
    }

    @Override
    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(6) == 1 && ChaosPersists.PlayNicely == 0) {
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
                double rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                double rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f);
                double pi = 3.1415926545;
                double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                if (rdd > pi) {
                    rdd -= pi * 2.0;
                }
                rdd = Math.abs(rdd);
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (rdd < 1.25) {
                    if (this.distanceToSqr((Entity)e) < (double)((5.0f + e.getBbWidth() / 2.0f) * (5.0f + e.getBbWidth() / 2.0f))) {
                        this.setAttacking(1);
                        if (this.level.random.nextInt(5) == 0 || this.level.random.nextInt(6) == 1) {
                            this.doHurtTarget((LivingEntity)e);
                            for (int i = 0; i < 6; ++i) {
                                this.destroyBlock(e);
                            }
                        }
                        this.destroyNearbyBlocks();
                    }
                } else {
                    this.setAttacking(0);
                }
                this.getNavigation().moveTo((Entity)e, 1.0);
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getAttacking() == 0 && ChaosPersists.PlayNicely == 0) {
            if (this.level.random.nextInt(450) == 1) {
                this.just_for_fun = 50;
            }
            if (this.just_for_fun > 0) {
                --this.just_for_fun;
            }
            if (this.just_for_fun > 0) {
                this.setAttacking(1);
                if (this.level.random.nextInt(3) == 1) {
                    this.destroyNearbyBlocks();
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
            Entity e = par1DamageSource.getEntity();
            if (e != null && e instanceof MobEntity) {
                this.setTarget((LivingEntity)((MobEntity)e));
                this.getNavigation().moveTo((Entity)((MobEntity)e), 1.2);
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
      List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(14.0D, 3.0D, 14.0D));
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
                    if (s == null || !s.equals("Robo-Pounder")) continue;
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

