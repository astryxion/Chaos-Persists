/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EntityAnt
 *  com.astryxion.chaospersists.EntityButterfly
 *  com.astryxion.chaospersists.EntityLunaMoth
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.LeafMonster
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.EntityLunaMoth;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
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
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class LeafMonster
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(LeafMonster.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.25f;

    public LeafMonster(EntityType<? extends LeafMonster> type, World par1World) {
        super(type, par1World);
                this.xpReward = 5;
                this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.350000023841858));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.LeafMonster_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.LeafMonster_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public int mygetMaxHealth() {
        return ChaosPersists.LeafMonster_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.LeafMonster_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected void fall(float par1) {
        float i = (float)MathHelper.ceil((double)(par1 - 3.0f));
        if (i > 0.0f) {
            if (i > 2.0f) {
                this.playSound(net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("entity.generic.big_fall")), 1.0f, 1.0f);
                i = 2.0f;
            } else {
                this.playSound(net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("entity.generic.small_fall")), 1.0f, 1.0f);
            }
            this.hurt(DamageSource.FALL, i);
        }
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        if (this.getAttacking() == 0) {
            int px = (int)this.getX();
            int py = (int)this.getY();
            int pz = (int)this.getZ();
            this.setPos((double)px, (double)py, (double)pz);
            if (this.getX() > 0.0) {
                com.astryxion.chaospersists.util.MyUtils.addEntityX(this, 0.5);
            }
            if (this.getZ() > 0.0) {
                com.astryxion.chaospersists.util.MyUtils.addEntityZ(this, 0.5);
            }
            if (this.getX() < 0.0) {
                com.astryxion.chaospersists.util.MyUtils.addEntityX(this, -(0.5));
            }
            if (this.getZ() < 0.0) {
                com.astryxion.chaospersists.util.MyUtils.addEntityZ(this, -(0.5));
            }
            this.xRot = 0.0f;
            px = (int)this.yHeadRot;
            this.yRot = this.yHeadRot = (float)((px /= 90) * 90);
        }
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.LEAVES_HIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.LEAVES_DEATH;
    }

    protected float getSoundVolume() {
        return 0.65f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(3);
        if (i == 0) {
            return Item.byBlock((Block)Blocks.OAK_LOG);
        }
        if (i == 1) {
            return Item.byBlock((Block)Blocks.OAK_LEAVES);
        }
        return Items.ROTTEN_FLESH;
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        if (!this.isAlive()) {
            return;
        }
        if (this.level.random.nextInt(100) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.level.random.nextInt(4) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                this.setAttacking(1);
                this.getNavigation().moveTo(e, 1.25);
                if (this.distanceToSqr((Entity)e) < 5.0 && (this.random.nextInt(8) == 0 || this.random.nextInt(10) == 1)) {
                    this.doHurtTarget(e);
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
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof EntityAnt) {
            return true;
        }
        if (par1Mob instanceof EntityButterfly) {
            return true;
        }
        if (par1Mob instanceof EntityLunaMoth) {
            return true;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (!p.isCreative()) {
                return true;
            }
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0, 6.0, 4.0));
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

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileMonsterspawner = null;
                    tileMonsterspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileMonsterspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Leaf Monster")) continue;
                    return true;
                }
            }
        }
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level) == ChaosPersists.getDimension(4) ? this.getY() > 20.0 : this.getY() < 50.0) {
            return false;
        }
        if (this.findBuddies() > 4) {
            return false;
        }
        return true;
    }

    private int findBuddies() {
        List var5 = this.level.getEntitiesOfClass(LeafMonster.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
        return var5.size();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }
}

