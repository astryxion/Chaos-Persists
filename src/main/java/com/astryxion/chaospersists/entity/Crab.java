/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Crab
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.Lizard
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RubberDucky
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
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
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.VillagerEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.Lizard;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.RubberDucky;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.FlowingFluidBlock;
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
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Crab
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Crab.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> CRAB_SCALE = EntityDataManager.defineId(Crab.class, DataSerializers.INT);
    private float crabWidth = 3.75f;
    private float crabHeight = 3.5f;
    private GenericTargetSorter TargetSorter = null;
    private int hurt_timer = 0;
    private float moveSpeed = 0.55f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Crab(EntityType<? extends Crab> type, World par1World) {
        super(type, par1World);
                this.xpReward = 150;
                
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.addGoal(3, new LookAtGoal(this, LivingEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Crab_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.55)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Crab_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(CRAB_SCALE, 25);
        float t = 0.25f;
        if (this.level != null) {
            if (this.level.random.nextInt(4) == 1) {
                t = 0.5f;
            }
            if (this.level.random.nextInt(8) == 2) {
                t = 1.0f;
            }
        } else {
            if (ChaosPersists.ChaosRand.nextInt(4) == 1) {
                t = 0.5f;
            }
            if (ChaosPersists.ChaosRand.nextInt(8) == 2) {
                t = 1.0f;
            }
        }
        this.setCrabScale(t);
        this.xpReward = (int)(400.0f * t);
        this.crabWidth = 3.75f;
        this.crabHeight = 3.5f;
        this.refreshDimensions();
    }

    @Override
    public net.minecraft.entity.EntitySize getDimensions(net.minecraft.entity.Pose pose) {
        float s = this.getCrabScale();
        return net.minecraft.entity.EntitySize.scalable(this.crabWidth * s, this.crabHeight * s);
    }

    public float getCrabScale() {
        int i = this.entityData.get(CRAB_SCALE).intValue();
        float f = i;
        return f / 100.0f;
    }

    public void setCrabScale(float par1) {
        float f = par1 * 100.0f;
        int i = (int)f;
        this.entityData.set(CRAB_SCALE, i);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.setCrabScale(par1CompoundNBT.getFloat("Fscale"));
        this.crabWidth = 3.75f;
        this.crabHeight = 3.5f;
        this.refreshDimensions();
        this.xpReward = (int)(400.0f * this.getCrabScale());
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putFloat("Fscale", this.getCrabScale());
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public void tick() {
        this.moveSpeed = this.isInWater() ? 0.95f : 0.55f;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(this.moveSpeed * this.getCrabScale()));
        super.tick();
        this.crabWidth = 2.5f;
        this.crabHeight = 3.5f;
        this.refreshDimensions();
    }

    public int mygetMaxHealth() {
        return (int)((float)ChaosPersists.PitchBlack_stats.health * this.getCrabScale());
    }

    public int getArmorValue() {
        return ChaosPersists.Crab_stats.defense + (int)(2.0f * this.getCrabScale());
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    public int getCrabHealth() {
        return (int)this.getHealth();
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("chaospersists", "leaves_hit"));
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    protected float getSoundVolume() {
        return 0.75f;
    }

    protected float getVoicePitch() {
        return 2.0f - 0.3f * (1.0f / this.getCrabScale());
    }

    protected Item getDropItem() {
        return Items.COD;
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
        Object is = null;
        int var5 = 4 + this.level.random.nextInt(8);
        if ((var5 = (int)((float)var5 * this.getCrabScale())) < 1) {
            var5 = 1;
        }
        for (int var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(ChaosPersists.MyRawCrabMeat, 1);
        }
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        boolean var4 = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), (float)ChaosPersists.Crab_stats.attack * this.getCrabScale());
        if (var4 && par1Entity != null && par1Entity instanceof LivingEntity) {
            double ks = 1.15 * (double)this.getCrabScale();
            double inair = 0.48 * (double)this.getCrabScale();
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            par1Entity.setDeltaMovement(par1Entity.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
        }
        return var4;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (this.hurt_timer <= 0) {
            ret = super.hurt(par1DamageSource, par2);
            this.hurt_timer = 8;
        }
        if (e != null && e instanceof MobEntity) {
            if (e instanceof Crab) {
                return false;
            }
            this.setTarget((LivingEntity)((MobEntity)e));
            this.getNavigation().moveTo((Entity)((MobEntity)e), 1.2);
        }
        return ret;
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
                if ((bid == Blocks.WATER || bid == Blocks.WATER) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x - dx, y + i, z + j)).getBlock()) != Blocks.WATER && bid != Blocks.WATER || (d = dx * dx + j * j + i * i) >= this.closest) continue;
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
                if ((bid == Blocks.WATER || bid == Blocks.WATER) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y - dy, z + j)).getBlock()) != Blocks.WATER && bid != Blocks.WATER || (d = dy * dy + j * j + i * i) >= this.closest) continue;
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
                if ((bid == Blocks.WATER || bid == Blocks.WATER) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z - dz)).getBlock()) != Blocks.WATER && bid != Blocks.WATER || (d = dz * dz + j * j + i * i) >= this.closest) continue;
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

    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (!this.isInWater() && this.level.random.nextInt(25) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 10) {
                    j = 10;
                }
                if (this.scan_it((int)this.getX(), (int)this.getY() - 1, (int)this.getZ(), i, j, i)) break;
                if (i < 5) continue;
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
            } else {
                if (this.level.random.nextInt(100) == 1) {
                    this.heal(-1.0f * this.getCrabScale());
                }
                if (this.getHealth() <= 0.0f) {
                    this.remove();
                    return;
                }
            }
        }
        if (this.level.random.nextInt(5) == 1) {
            LivingEntity e = null;
            if (this.level.random.nextInt(100) == 1) {
                this.setTarget(null);
            }
            if ((e = this.getTarget()) != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) < (double)((6.0f + e.getBbWidth() / 2.0f) * (6.0f + e.getBbWidth() / 2.0f) * this.getCrabScale())) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(4) == 0 || this.level.random.nextInt(5) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                        if (!this.level.isClientSide) {
                            if (this.level.random.nextInt(3) == 1) {
                                this.level.playSound(null, e.getX(), e.getY(), e.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("chaospersists", "scorpion_attack")), net.minecraft.util.SoundCategory.HOSTILE, 0.75f, 1.5f);
                            } else {
                                this.level.playSound(null, e.getX(), e.getY(), e.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("chaospersists", "scorpion_living")), net.minecraft.util.SoundCategory.HOSTILE, 0.75f, 1.5f);
                            }
                        }
                    }
                } else {
                    this.getNavigation().moveTo((Entity)e, 1.0);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.level.random.nextInt(120) == 1 && this.isInWater() && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.playSound(net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("entity.generic.splash")), 1.5f, this.level.random.nextFloat() * 0.2f + 0.9f);
            this.heal(4.0f * this.getCrabScale());
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
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof Crab) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof Lizard) {
            return true;
        }
        if (par1Mob instanceof RubberDucky) {
            return true;
        }
        if (par1Mob instanceof VillagerEntity) {
            return true;
        }
        if (par1Mob instanceof Girlfriend) {
            return true;
        }
        if (par1Mob instanceof Boyfriend) {
            return true;
        }
        if (MyUtils.isAttackableNonMob((LivingEntity)par1Mob)) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
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

    private int findBuddies() {
        List var5 = this.level.getEntitiesOfClass(Crab.class, this.getBoundingBox().inflate(24.0, 8.0, 24.0));
        return var5.size();
    }

    /** Crystal lists crabs as water creatures; without this they still pass on dry land and crowd shores. */
    private boolean feetInWater() {
        BlockPos base = new BlockPos(this.getX(), this.getY(), this.getZ());
        for (int k = 0; k <= 2; k++) {
            if (this.level.getFluidState(base.below(k)).is(net.minecraft.tags.FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileentitymobspawner = null;
                    tileentitymobspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Crab")) continue;
                    this.setCrabScale(0.35f);
                    return true;
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        if (CrystalDimensionSpawnHelper.isCrystalDimension(this.level)) {
            if (!this.feetInWater()) {
                return false;
            }
            if (this.findBuddies() > 2) {
                return false;
            }
        }
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }
}

