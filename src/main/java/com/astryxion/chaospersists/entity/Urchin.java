/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CrystalCow
 *  com.astryxion.chaospersists.Flounder
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Irukandji
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Peacock
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Rotator
 *  com.astryxion.chaospersists.Skate
 *  com.astryxion.chaospersists.Urchin
 *  com.astryxion.chaospersists.Vortex
 *  com.astryxion.chaospersists.Whale
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
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.Whale;
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
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Urchin
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Urchin.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private float moveSpeed = 0.3f;
    private int was_spawnered = 0;

    public Urchin(EntityType<? extends Urchin> type, World par1World) {
        super(type, par1World);
        this.xpReward = 20;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(2, new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }
    @Override
    public boolean fireImmune() {
        return true;
    }


    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Urchin_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Urchin_stats.attack)
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
        if (this.was_spawnered != 0) {
            return false;
        }
        return true;
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        if (this.isPersistenceRequired()) {
            return;
        }
        if (this.was_spawnered != 0) {
            return;
        }
        long t = this.level.getGameTime();
        if ((t %= 24000L) < 12000L && this.level.random.nextInt(400) == 1) {
            this.remove();
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Urchin_stats.health;
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
        return ChaosPersists.Urchin_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level.random.nextInt(3) == 1) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FLAME, this.getX(), this.getY() + 0.75, this.getZ(), 0.0, (double)(this.level.random.nextFloat() / 10.0f), 0.0);
            if (this.isInWater() && this.level.random.nextInt(5) == 1) {
                this.doHurtTarget((LivingEntity)this);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX(), this.getY() + 1.75, this.getZ(), 0.0, (double)(this.level.random.nextFloat() / 10.0f), 0.0);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 1.75, this.getZ(), 0.0, (double)(this.level.random.nextFloat() / 10.0f), 0.0);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX(), this.getY() + 2.0, this.getZ(), 0.0, (double)(this.level.random.nextFloat() / 10.0f), 0.0);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 2.0, this.getZ(), 0.0, (double)(this.level.random.nextFloat() / 10.0f), 0.0);
            }
        }
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.KYUUBI_LIVING;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.GLASSHIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.GLASSDEAD;
    }

    protected float getSoundVolume() {
        return 1.1f;
    }

    protected float getVoicePitch() {
        return 1.25f;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(3);
        if (i == 1) {
            return ChaosPersists.MyCrystalPinkIngot;
        }
        if (i == 2) {
            return ChaosPersists.MyCrystalApple;
        }
        return null;
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        par1Entity.setSecondsOnFire(5);
        return super.doHurtTarget(par1Entity);
    }

    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(8) == 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr((Entity)e) < 8.0) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(7) == 0 || this.level.random.nextInt(8) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                    }
                } else {
                    this.getNavigation().moveTo((Entity)e, 1.2);
                }
            } else {
                this.setAttacking(0);
            }
        }
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
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
        if (par1Mob instanceof Vortex) {
            return false;
        }
        if (par1Mob instanceof Rotator) {
            return false;
        }
        if (par1Mob instanceof Peacock) {
            return false;
        }
        if (par1Mob instanceof CrystalCow) {
            return false;
        }
        if (par1Mob instanceof Irukandji) {
            return false;
        }
        if (par1Mob instanceof Skate) {
            return false;
        }
        if (par1Mob instanceof Whale) {
            return false;
        }
        if (par1Mob instanceof Flounder) {
            return false;
        }
        if (par1Mob instanceof Urchin) {
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 3.0, 16.0));
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

    protected boolean isValidLightLevel() {
        if (CrystalDimensionSpawnHelper.isCrystalDimension(this.level)) {
            return true;
        }
        return MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random);
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        Block bid;
        int j;
        int k;
        int sc = 0;
        for (k = -2; k <= 2; ++k) {
            for (j = -2; j <= 2; ++j) {
                for (int i = 1; i < 4; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileentitymobspawner = null;
                    tileentitymobspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Crystal Urchin")) continue;
                    this.was_spawnered = 1;
                    return true;
                }
            }
        }
        for (k = -1; k <= 1; ++k) {
            for (j = -1; j <= 1; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + 1, (int)this.getZ() + k)).getBlock();
                if (bid != Blocks.AIR) continue;
                ++sc;
            }
        }
        if (sc < 6) {
            return false;
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        if (!CrystalDimensionSpawnHelper.isCrystalDimension(this.level)) {
            long t = this.level.getGameTime();
            if ((t %= 24000L) < 13000L) {
                return false;
            }
        }
        return true;
    }
}

