/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.Molenoid
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.block.BlockSand
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
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.TallGrassBlock;
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
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class Molenoid
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Molenoid.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.35f;

    public Molenoid(EntityType<? extends Molenoid> type, World par1World) {
        super(type, par1World);
                this.xpReward = 40;
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
                .add(Attributes.MAX_HEALTH, ChaosPersists.Molenoid_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Molenoid_stats.attack)
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
        return ChaosPersists.Molenoid_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.Molenoid_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.random.nextInt(3) == 0) {
            return com.astryxion.chaospersists.core.ChaosSounds.MOLENOID_LIVING;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return com.astryxion.chaospersists.core.ChaosSounds.MOLENOID_HIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.MOLENOID_DEATH;
    }

    protected float getSoundVolume() {
        return 1.1f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        this.dropItemRand(ChaosPersists.MolenoidNose, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        for (var4 = 0; var4 < 10; ++var4) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        for (var4 = 0; var4 < 6; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity != null && par1Entity instanceof LivingEntity) {
                double ks = 0.8;
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

    protected void customServerAiStep() {
        double dx;
        double dz;
        LivingEntity e = null;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(4) == 0) {
            e = this.findSomethingToAttack();
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) < (double)((6.0f + e.getBbWidth() / 2.0f) * (6.0f + e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    if (this.distanceToSqr((Entity)e) < 16.0 && (this.level.random.nextInt(4) == 0 || this.level.random.nextInt(5) == 1)) {
                        this.doHurtTarget((LivingEntity)e);
                    } else if (ChaosPersists.PlayNicely == 0) {
                        int j = 1 + this.level.random.nextInt(4);
                        block0 : for (int k = 0; k < j; ++k) {
                            dx = e.getX();
                            dz = e.getZ();
                            dx += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 2.0;
                            dz += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 2.0;
                            for (int i = 4; i > -3; --i) {
                                if (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx, (int)e.getY() + i + 1, (int)dz)).getBlock() != Blocks.AIR || this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx, (int)e.getY() + i, (int)dz)).getBlock() == Blocks.AIR) continue;
                                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)dx, (int)e.getY() + i + 1, (int)dz), ChaosPersists.MyMoleDirtBlock.defaultBlockState(), 2);
                                continue block0;
                            }
                        }
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.25);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.level.isClientSide) {
            return;
        }
        if (this.level.random.nextInt(2) == 0) {
            int odds;
            double spd = 0.0;
            spd = this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z;
            if ((spd = Math.sqrt(spd)) > (double)this.moveSpeed) {
                spd = this.moveSpeed;
            }
            if ((odds = (int)(100.0 * spd / (double)this.moveSpeed)) > 0 && this.level.random.nextInt(100) < odds && ChaosPersists.PlayNicely == 0) {
                dx = this.getX() + 6.0 * Math.sin(Math.toRadians(this.yHeadRot));
                dz = this.getZ() - 6.0 * Math.cos(Math.toRadians(this.yHeadRot));
                dx += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 3.0;
                dz += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 3.0;
                for (int i = 4; i > -4; --i) {
                    if (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx, (int)this.getY() + i + 1, (int)dz)).getBlock() != Blocks.AIR || this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx, (int)this.getY() + i, (int)dz)).getBlock() == Blocks.AIR) continue;
                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)dx, (int)this.getY() + i + 1, (int)dz), ChaosPersists.MyMoleDirtBlock.defaultBlockState(), 2);
                    break;
                }
            }
        }
        dx = this.getX() - 3.0 * Math.sin(Math.toRadians(this.yHeadRot));
        dz = this.getZ() + 3.0 * Math.cos(Math.toRadians(this.yHeadRot));
        dx += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 3.0;
        dz += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 3.0;
        int dir = 1;
        if (e != null) {
            if ((int)e.getY() > (int)this.getY()) {
                dir = 2;
            }
            if ((int)e.getY() < (int)this.getY()) {
                dir = 0;
            }
        }
        if (ChaosPersists.PlayNicely == 0) {
            for (int i = dir; i < dir + 3; ++i) {
                Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx, (int)this.getY() + i, (int)dz)).getBlock();
                if ((bid == Blocks.DIRT || bid == Blocks.GRASS_BLOCK || bid == Blocks.GRAVEL || bid == Blocks.SAND || bid == Blocks.OAK_LEAVES) && this.level.getGameRules().getBoolean(net.minecraft.world.GameRules.RULE_MOBGRIEFING)) {
                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)dx, (int)this.getY() + i, (int)dz), Blocks.AIR.defaultBlockState(), 2);
                }
                if (bid != ChaosPersists.MyMoleDirtBlock) continue;
                this.level.setBlock(new net.minecraft.util.math.BlockPos((int)dx, (int)this.getY() + i, (int)dz), Blocks.AIR.defaultBlockState(), 2);
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
        if (!this.MyCanSee(par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof Molenoid) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 6.0, 12.0));
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
                    MobSpawnerTileEntity tileMonsterspawner = null;
                    tileMonsterspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileMonsterspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Molenoid")) continue;
                    return true;
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
        for (k = -1; k < 1; ++k) {
            for (j = -1; j < 1; ++j) {
                for (i = 1; i < 4; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        Molenoid target = null;
        target = this.level.getNearestEntity(Molenoid.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(16.0, 8.0, 16.0));
        if (target != null) {
            return false;
        }
        return true;
    }

    public boolean MyCanSee(LivingEntity e) {
        double xzoff = 2.0;
        int nblks = 10;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        float startx = (float)cx;
        float starty = (float)(this.getY() + 1.0);
        float startz = (float)cz;
        float dx = (float)((e.getX() - (double)startx) / 10.0);
        float dy = (float)((e.getY() + (double)(e.getBbHeight() / 2.0f) - (double)starty) / 10.0);
        float dz = (float)((e.getZ() - (double)startz) / 10.0);
        if ((double)Math.abs(dx) > 1.0) {
            dy /= Math.abs(dx);
            dz /= Math.abs(dx);
            nblks = (int)((float)nblks * Math.abs(dx));
            if (dx > 1.0f) {
                dx = 1.0f;
            }
            if (dx < -1.0f) {
                dx = -1.0f;
            }
        }
        if ((double)Math.abs(dy) > 1.0) {
            dx /= Math.abs(dy);
            dz /= Math.abs(dy);
            nblks = (int)((float)nblks * Math.abs(dy));
            if (dy > 1.0f) {
                dy = 1.0f;
            }
            if (dy < -1.0f) {
                dy = -1.0f;
            }
        }
        if ((double)Math.abs(dz) > 1.0) {
            dy /= Math.abs(dz);
            dx /= Math.abs(dz);
            nblks = (int)((float)nblks * Math.abs(dz));
            if (dz > 1.0f) {
                dz = 1.0f;
            }
            if (dz < -1.0f) {
                dz = -1.0f;
            }
        }
        for (int i = 0; i < nblks; ++i) {
            Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(startx += dx), (int)(starty += dy), (int)(startz += dz))).getBlock();
            if (bid == Blocks.AIR || bid == ChaosPersists.MyMoleDirtBlock || bid == Blocks.DIRT || bid == Blocks.GRASS_BLOCK || bid == Blocks.GRASS_BLOCK || bid == Blocks.SAND || bid == Blocks.GRAVEL) continue;
            return false;
        }
        return true;
    }
}

