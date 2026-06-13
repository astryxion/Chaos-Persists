/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Cryolophosaurus
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.TRex
 *  com.astryxion.chaospersists.VelocityRaptor
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
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.Cryolophosaurus;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.VelocityRaptor;
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
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.SpawnerFixHelper;

public class TRex
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(TRex.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.38f;
    private LivingEntity rt = null;

    public TRex(EntityType<? extends TRex> type, World par1World) {
        super(type, par1World);
        this.xpReward = 150;
                this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 1.0, false, 512, () -> true));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.TRex_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.38)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.TRex_stats.attack)
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
        return ChaosPersists.TRex_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.TRex_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.random.nextInt(4) == 0) {
            return com.astryxion.chaospersists.core.ChaosSounds.TREX_LIVING;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.TREX_DEATH;
    }

    protected float getSoundVolume() {
        return 1.5f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), new ItemStack(index, par1));
        this.level.addFreshEntity((Entity)var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        this.dropItemRand(ChaosPersists.TRexTooth, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        for (var4 = 0; var4 < 7; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
        var4 = 2 + this.level.random.nextInt(4);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(ChaosPersists.UraniumNugget, 1);
            this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
        }
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity != null && par1Entity instanceof LivingEntity) {
                double ks = 1.2;
                double inair = 0.1;
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
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            ret = super.hurt(par1DamageSource, par2);
            Entity e = par1DamageSource.getEntity();
            if (e != null && e instanceof LivingEntity) {
                this.rt = (LivingEntity)e;
            }
        }
        return ret;
    }

    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(5) == 1) {
            LivingEntity e = null;
            e = this.getTarget();
            if (e == null) {
                e = this.rt;
            }
            if (ChaosPersists.PlayNicely != 0) {
                e = null;
            }
            if (e != null) {
                if (e.isAlive() == false || this.level.random.nextInt(200) == 1) {
                    e = null;
                    this.rt = null;
                    this.setTarget(null);
                }
                if (e != null && !this.getSensing().canSee((Entity)e)) {
                    e = null;
                }
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) < (double)((4.0f + e.getBbWidth() / 2.0f) * (4.0f + e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(4) == 0 || this.level.random.nextInt(5) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                    }
                } else {
                    this.getNavigation().moveTo((Entity)e, 1.25);
                }
            } else {
                this.setAttacking(0);
            }
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
        if (par1Mob instanceof TRex) {
            return false;
        }
        if (par1Mob instanceof Cryolophosaurus) {
            return false;
        }
        if (par1Mob instanceof VelocityRaptor) {
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0, 6.0, 20.0));
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
        // Same search volume as ChaosPersists.hasNearbyMatchingSpawner: large mobs can sit above/beside the spawner.
        int baseX = MathHelper.floor(this.getX());
        int baseY = MathHelper.floor(this.getY());
        int baseZ = MathHelper.floor(this.getZ());
        ResourceLocation trexId = new ResourceLocation("chaospersists", "trex");
        for (int dz = -8; dz <= 8; ++dz) {
            for (int dx = -8; dx <= 8; ++dx) {
                for (int dy = -4; dy <= 8; ++dy) {
                    BlockPos bp = new BlockPos(baseX + dx, baseY + dy, baseZ + dz);
                    if (this.level.getBlockState(bp).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    TileEntity te = this.level.getBlockEntity(bp);
                    if (!(te instanceof MobSpawnerTileEntity)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityId(((MobSpawnerTileEntity)te).getSpawner());
                    if (id == null) {
                        continue;
                    }
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, trexId)) {
                        return true;
                    }
                }
            }
        }
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        for (k = -1; k <= 1; ++k) {
            for (j = -1; j <= 1; ++j) {
                for (i = 1; i < 6; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        TRex target = null;
        target = this.level.getNearestEntity(TRex.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(24.0, 12.0, 24.0));
        if (target != null) {
            return false;
        }
        return true;
    }
}

