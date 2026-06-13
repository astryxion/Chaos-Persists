/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.AttackSquid
 *  com.astryxion.chaospersists.Bee
 *  com.astryxion.chaospersists.CloudShark
 *  com.astryxion.chaospersists.Cockateil
 *  com.astryxion.chaospersists.EntityButterfly
 *  com.astryxion.chaospersists.Fairy
 *  com.astryxion.chaospersists.Flounder
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Irukandji
 *  com.astryxion.chaospersists.LurkingTerror
 *  com.astryxion.chaospersists.Mantis
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Rotator
 *  com.astryxion.chaospersists.Skate
 *  com.astryxion.chaospersists.TerribleTerror
 *  com.astryxion.chaospersists.WaterDragon
 *  com.astryxion.chaospersists.Whale
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.SquidEntity
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
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.entity.Bee;
import com.astryxion.chaospersists.entity.CloudShark;
import com.astryxion.chaospersists.entity.Cockateil;
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.Fairy;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.Whale;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.SquidEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class Mantis
extends MonsterEntity {
    private static final net.minecraft.network.datasync.DataParameter<Byte> ATTACKING = net.minecraft.network.datasync.EntityDataManager.defineId(Mantis.class, net.minecraft.network.datasync.DataSerializers.BYTE);
    private BlockPos currentFlightTarget = null;
    private GenericTargetSorter TargetSorter = null;
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private Entity rt = null;

    public Mantis(EntityType<? extends Mantis> type, World par1World) {
        super(type, par1World);
                this.xpReward = 100;
        
                this.TargetSorter = new GenericTargetSorter((Entity)this);
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Mantis_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3199999928474426)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Mantis_stats.attack)
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

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).byteValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    protected float getSoundVolume() {
        return 0.35f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.BEEBUZZ;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.DRAGONFLY_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_DEATH;
    }

    public boolean canBePushed() {
        return true;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Mantis_stats.health;
    }

    protected Item getDropItem() {
        return net.minecraft.item.Item.byBlock(Blocks.DANDELION);
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i;
        this.dropItemRand(ChaosPersists.MyMantisClaw, 1);
        this.dropItemRand(ChaosPersists.MyMantisClaw, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var4 = 2 + this.level.random.nextInt(10);
        for (i = 0; i < var4; ++i) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        var4 = 1 + this.level.random.nextInt(3);
        for (i = 0; i < var4; ++i) {
            this.dropItemRand(ChaosPersists.UraniumNugget, 1);
        }
        var4 = 1 + this.level.random.nextInt(3);
        for (i = 0; i < var4; ++i) {
            this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
        }
        var4 = 2 + this.level.random.nextInt(3);
        for (i = 0; i < var4; ++i) {
            this.dropItemRand(Items.DIAMOND, 1);
        }
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void tick() {
        super.tick();
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        if (this.isInWater() && this.level.random.nextInt(20) == 1) {
            this.doHurtTarget(this);
        }
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new net.minecraft.util.math.RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.75, this.getZ()), new Vector3d(pX, pY, pZ), net.minecraft.util.math.RayTraceContext.BlockMode.COLLIDER, net.minecraft.util.math.RayTraceContext.FluidMode.NONE, this)).getType() == net.minecraft.util.math.RayTraceResult.Type.MISS;
    }

    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.lastX == (int)this.getX() && this.lastZ == (int)this.getZ()) {
            ++this.stuck_count;
        } else {
            this.stuck_count = 0;
            this.lastX = (int)this.getX();
            this.lastZ = (int)this.getZ();
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.stuck_count > 50 || this.random.nextInt(300) == 0 || this.currentFlightTarget.distSqr((double)(int)this.getX(), (double)(int)this.getY(), (double)(int)this.getZ(), true) < 2.1) {
            Block bid = Blocks.STONE;
            this.stuck_count = 0;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.random.nextInt(9) + 4;
                xdir = this.random.nextInt(9) + 4;
                if (this.random.nextInt(2) == 0) {
                    zdir = - zdir;
                }
                if (this.random.nextInt(2) == 0) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)this.getX() + xdir, (int)this.getY() + this.random.nextInt(6) - 3, (int)this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        } else {
            LivingEntity e = this.getTarget();
            if (e != null && (!e.isAlive() || !this.isSuitableTarget(e, false))) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = (LivingEntity)this.rt;
                if (e != null && e.removed) {
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
                this.setAttacking(1);
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)e.getY() + 1, (int)e.getZ());
                if (this.distanceToSqr(e) < (double)((5.0f + e.getBbWidth() / 2.0f) * (5.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget(e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.5 - this.getDeltaMovement().x) * 0.30000000149011613, (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.20000000149011612, (Math.signum(var5) * 0.5 - this.getDeltaMovement().z) * 0.30000000149011613);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.zza = 1.0f;
        this.yRot += var8 / 4.0f;
        if (this.level.random.nextInt(100) == 1) {
            this.heal(1.0f);
        }
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = super.hurt(par1DamageSource, par2);
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof LivingEntity && this.currentFlightTarget != null) {
            this.rt = e;
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)e.getY(), (int)e.getZ());
        }
        return ret;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        Block bid;
        int j;
        int i;
        int k;
        for (k = -2; k <= 2; ++k) {
            for (j = -2; j <= 2; ++j) {
                for (i = 1; i < 4; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileMonsterspawner = null;
                    tileMonsterspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileMonsterspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Mantis")) continue;
                    return true;
                }
            }
        }
        for (k = -2; k < 2; ++k) {
            for (j = -2; j < 2; ++j) {
                for (i = 1; i < 6; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        if (ChaosPersists.getDimensionId(this.level) == ChaosPersists.getDimension(6) && this.level.random.nextInt(6) != 0) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        Mantis target = this.level.getNearestEntity(Mantis.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(32.0, 16.0, 32.0));
        if (target != null) {
            return false;
        }
        return true;
    }

    public int getArmorValue() {
        return ChaosPersists.Mantis_stats.defense;
    }

    public void initCreature() {
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
        if (par1Mob.isInWater()) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof Mantis) {
            return false;
        }
        if (par1Mob instanceof Irukandji) {
            return false;
        }
        if (par1Mob instanceof Skate) {
            return false;
        }
        if (par1Mob instanceof Flounder) {
            return false;
        }
        if (par1Mob instanceof Whale) {
            return false;
        }
        if (par1Mob instanceof SquidEntity) {
            return false;
        }
        if (par1Mob instanceof WaterDragon) {
            return false;
        }
        if (par1Mob instanceof AttackSquid) {
            return false;
        }
        if (par1Mob instanceof TerribleTerror) {
            return false;
        }
        if (par1Mob instanceof LurkingTerror) {
            return false;
        }
        if (par1Mob instanceof CloudShark) {
            return false;
        }
        if (par1Mob instanceof Rotator) {
            return false;
        }
        if (par1Mob instanceof Bee) {
            return false;
        }
        if (par1Mob instanceof Mothra) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof EntityButterfly) {
            return true;
        }
        if (par1Mob instanceof Cockateil) {
            return true;
        }
        if (par1Mob instanceof Fairy) {
            return true;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (!p.isCreative()) {
                return true;
            }
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 8.0, 16.0));
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
}

