/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Irukandji
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.HurtByTargetGoal
 *  net.minecraft.entity.ai.LookRandomlyGoal
 *  net.minecraft.entity.ai.SwimGoal
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.LookAtGoal
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
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
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Irukandji
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Irukandji.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private LivingEntity buddy = null;
    private float moveSpeed = 0.15f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Irukandji(EntityType<? extends Irukandji> type, World par1World) {
        super(type, par1World);
                this.xpReward = 50;
                
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(2, new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Irukandji_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Irukandji_stats.attack)
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

    public boolean canBreatheUnderwater() {
        return true;
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Irukandji_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.Irukandji_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    public int getAttackStrength(Entity par1Entity) {
        int var2 = 2;
        return var2;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return com.astryxion.chaospersists.core.ChaosSounds.LITTLE_SPLAT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("chaospersists", "ratdead"));
    }

    protected float getSoundVolume() {
        return 0.25f;
    }

    protected float getVoicePitch() {
        return 2.0f;
    }

    protected Item getDropItem() {
        return ChaosPersists.MyIrukandji;
    }

    public void initCreature() {
    }

    @Override
    public net.minecraft.util.ActionResultType mobInteract(net.minecraft.entity.player.PlayerEntity par1PlayerEntityEntity, net.minecraft.util.Hand hand) {
        if (par1PlayerEntityEntity != null && par1PlayerEntityEntity.getMainHandItem().isEmpty()) {
            par1PlayerEntityEntity.hurt(DamageSource.mobAttack((LivingEntity)this), 200.0f);
        }
        return net.minecraft.util.ActionResultType.PASS;
    }

    public boolean hurt(DamageSource par1DamageSource, float par2) {
        PlayerEntity p;
        boolean ret = false;
        if (this.removed) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof PlayerEntity && (p = (PlayerEntity)e).getMainHandItem().isEmpty()) {
            p.hurt(DamageSource.mobAttack((LivingEntity)this), 200.0f);
            return false;
        }
        if (e != null && e instanceof MobEntity) {
            if (e instanceof Irukandji) {
                return false;
            }
            this.setTarget((LivingEntity)((MobEntity)e));
            this.getNavigation().moveTo((Entity)((MobEntity)e), 1.2);
            ret = true;
        }
        ret = super.hurt(par1DamageSource, par2);
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
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (!this.isInWater() && this.level.random.nextInt(10) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 5) {
                    j = 5;
                }
                if (this.scan_it((int)this.getX(), (int)this.getY() - 1, (int)this.getZ(), i, j, i)) break;
                if (i < 5) continue;
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
            } else {
                if (this.level.random.nextInt(25) == 1) {
                    this.heal(-1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.remove();
                    return;
                }
            }
        }
        if (this.level.random.nextInt(8) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr((Entity)e) < 3.0) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(4) == 0 || this.level.random.nextInt(5) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.getNavigation().moveTo((Entity)e, 1.2);
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
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
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
        List var5 = this.level.getEntitiesOfClass(Irukandji.class, this.getBoundingBox().inflate(16.0, 8.0, 16.0));
        return var5.size();
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        if (this.level.random.nextInt(60) != 1) {
            return false;
        }
        if (this.findBuddies() > 2) {
            return false;
        }
        return true;
    }
}

