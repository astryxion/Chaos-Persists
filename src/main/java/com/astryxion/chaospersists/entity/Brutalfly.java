/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.BetterFireball
 *  com.astryxion.chaospersists.Brutalfly
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Vortex
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.entity.projectile.SmallFireballEntity
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
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Vortex;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.projectile.SmallFireballEntity;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

/*
 * Exception performing whole class analysis ignored.
 */
public class Brutalfly
extends MonsterEntity {
    private BlockPos currentFlightTarget = null;
    private int lastX = 0;
    private int lastZ = 0;
    private int lastY = 0;
    private int stuck_count = 0;
    private int wing_sound = 0;
    private int health_ticker = 100;
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.35f;

    public Brutalfly(EntityType<? extends Brutalfly> type, World par1World) {
        super(type, par1World);
                this.xpReward = 100;
                this.TargetSorter = new GenericTargetSorter((Entity)this);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

        public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Brutalfly_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Brutalfly_stats.attack)
                .build();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public void aiStep() {
        super.aiStep();
    }

    public int getArmorValue() {
        return ChaosPersists.Brutalfly_stats.defense;
    }

    public int getBrutalflyHealth() {
        return (int)this.getHealth();
    }

    protected float getSoundVolume() {
        return 1.5f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return null;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_EXPLODE;
    }

    public boolean canBePushed() {
        return true;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    protected void collideWithNearbyEntities() {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Brutalfly_stats.health;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void tick() {
        super.tick();
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        ++this.wing_sound;
        if (this.wing_sound > 30) {
            if (!this.level.isClientSide) {
                this.playSound(com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, 1.0f, 1.0f);
            }
            this.wing_sound = 0;
        }
        --this.health_ticker;
        if (this.health_ticker <= 0) {
            if (this.getHealth() < (float)this.mygetMaxHealth()) {
                this.heal(1.0f);
            }
            this.health_ticker = 100;
        }
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d((double)this.getX(), (double)(this.getY() + 0.75), (double)this.getZ()), new Vector3d((double)pX, (double)pY, (double)pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    protected boolean isValidLightLevel() {
        net.minecraft.util.math.BlockPos pos = new net.minecraft.util.math.BlockPos(MathHelper.floor(this.getX()), MathHelper.floor(this.getBoundingBox().minY), MathHelper.floor(this.getZ()));
        if (this.level.getBrightness(LightType.SKY, pos) > this.random.nextInt(32)) {
            return false;
        }
        int l = this.level.getBrightness(LightType.BLOCK, pos);
        return l <= this.random.nextInt(8);
    }

    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 30;
        int shoot = 3;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.lastX == (int)this.getX() && this.lastY == (int)this.getY() && this.lastZ == (int)this.getZ()) {
            ++this.stuck_count;
        } else {
            this.stuck_count = 0;
            this.lastX = (int)this.getX();
            this.lastY = (int)this.getY();
            this.lastZ = (int)this.getZ();
        }
        if (this.level.getDifficulty() == Difficulty.HARD) {
            shoot = 2;
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.stuck_count > 30 || this.level.random.nextInt(200) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 9.0) {
            Block bid;
            int down = 0;
            int dist = 20;
            for (int i = -5; i <= 5; i += 5) {
                block1 : for (int j = -5; j <= 5; j += 5) {
                    for (int k = 1; k < 20; ++k) {
                        bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() - k, (int)this.getZ() + i)).getBlock();
                        if (bid == Blocks.AIR) continue;
                        if (k >= dist) continue block1;
                        dist = k;
                        continue block1;
                    }
                }
            }
            if (dist > 10) {
                down = dist - 10 + 1;
            }
            bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                xdir = 1;
                zdir = 1;
                if (this.level.random.nextInt(2) == 0) {
                    xdir = -1;
                }
                if (this.level.random.nextInt(2) == 0) {
                    zdir = -1;
                }
                int newz = this.random.nextInt(20) + 8;
                int newx = this.random.nextInt(20) + 8;
                newx *= xdir;
                newz *= zdir;
                this.currentFlightTarget = new BlockPos((int)this.getX() + newx, (int)this.getY() + this.level.random.nextInt(7) - 1 - down, (int)this.getZ() + newz);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
            this.stuck_count = 0;
        }
        if (this.level.random.nextInt(6) == 0) {
            PlayerEntity target = null;
            target = this.level.getNearestEntity(PlayerEntity.class, EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(30.0, 20.0, 30.0));
            if (target != null) {
                if (!target.isCreative()) {
                    if (this.getSensing().canSee((Entity)target)) {
                        this.currentFlightTarget = new BlockPos((int)target.getX(), (int)target.getY() + 4, (int)target.getZ());
                        if (this.random.nextInt(shoot) == 0) {
                            this.attackWithSomething((LivingEntity)target);
                        }
                    }
                } else {
                    target = null;
                }
            }
            if (target == null && this.level.random.nextInt(3) == 0) {
                LivingEntity e = null;
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.currentFlightTarget = new BlockPos((int)e.getX(), (int)e.getY() + 5, (int)e.getZ());
                    if (this.distanceToSqr((Entity)e) > 25.0) {
                        if (this.level.random.nextInt(shoot) == 0) {
                            this.attackWithSomething(e);
                        }
                    } else {
                        this.doHurtTarget((Entity)e);
                    }
                }
            }
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.5 - this.getDeltaMovement().x) * 0.30001, (Math.signum(var3) * 0.7 - this.getDeltaMovement().y) * 0.20001, (Math.signum(var5) * 0.5 - this.getDeltaMovement().z) * 0.30001);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = 1.0f;
        this.yRot += var8 / 8.0f;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof Brutalfly) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        if (e != null && this.currentFlightTarget != null) {
            this.currentFlightTarget = new BlockPos((int)e.getX(), (int)e.getY() + 2, (int)e.getZ());
        }
        return ret;
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
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
                    MobSpawnerTileEntity tileentitymobspawner = null;
                    tileentitymobspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Brutalfly")) continue;
                    return true;
                }
            }
        }
        if (this.getY() < 70.0) {
            return false;
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        for (k = -4; k < 4; ++k) {
            for (j = -3; j < 3; ++j) {
                for (i = 1; i < 10; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        Brutalfly target = null;
        target = this.level.getNearestEntity(Brutalfly.class, EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(64.0, 32.0, 64.0));
        if (target != null) {
            return false;
        }
        return true;
    }

    public void initCreature() {
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(8) - (double)ChaosPersists.ChaosRand.nextInt(8), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(8) - (double)ChaosPersists.ChaosRand.nextInt(8), new ItemStack(index, par1));
        this.level.addFreshEntity((Entity)var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        for (int i = 0; i < 20; ++i) {
            float var1 = (this.random.nextFloat() - 0.5f) * 8.0f;
            float var2 = (this.random.nextFloat() - 0.5f) * 4.0f;
            float var3 = (this.random.nextFloat() - 0.5f) * 8.0f;
            this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)var1, this.getY() + 2.0 + (double)var2, this.getZ() + (double)var3, 0.0, 0.0, 0.0);
        }
        for (var4 = 0; var4 < 53; ++var4) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        for (var4 = 0; var4 < 20; ++var4) {
            Brutalfly.spawnCreature((World)this.level, (String)"Butterfly", (double)(this.getX() + 0.5), (double)(this.getY() + 1.0), (double)(this.getZ() + 0.5));
        }
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = com.astryxion.chaospersists.util.EntitySpawnHelper.spawn(par0World, par1, par2, par4, par6);
        if (var8 instanceof MobEntity) {
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
        }
        return var8;
    }

    private void attackWithSomething(LivingEntity par1) {
        double xzoff = 2.25;
        double yoff = 0.0;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        if (this.level.getDifficulty() == Difficulty.EASY) {
            SmallFireballEntity sf = new SmallFireballEntity(this.level, (LivingEntity)this, par1.getX() - cx, par1.getY() + 0.55 - (this.getY() + yoff), par1.getZ() - cz);
            sf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
            sf.setPos(cx, this.getY() + yoff, cz);
            this.playSound(SoundEvents.SKELETON_SHOOT, 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level.addFreshEntity((Entity)sf);
        } else if (this.level.getDifficulty() == Difficulty.NORMAL) {
            if (this.level.random.nextInt(2) == 0) {
                SmallFireballEntity sf = new SmallFireballEntity(this.level, (LivingEntity)this, par1.getX() - cx, par1.getY() + 0.55 - (this.getY() + yoff), par1.getZ() - cz);
                sf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
                sf.setPos(cx, this.getY() + yoff, cz);
                this.playSound(SoundEvents.SKELETON_SHOOT, 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level.addFreshEntity((Entity)sf);
            } else {
                BetterFireball bf = new BetterFireball(this.level, (LivingEntity)this, par1.getX() - cx, par1.getY() + 0.55 - (this.getY() + yoff), par1.getZ() - cz);
                bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
                bf.setPos(cx, this.getY() + yoff, cz);
                bf.setNotMe();
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level.addFreshEntity((Entity)bf);
            }
        } else {
            BetterFireball bf = new BetterFireball(this.level, (LivingEntity)this, par1.getX() - cx, par1.getY() + 0.55 - (this.getY() + yoff), par1.getZ() - cz);
            bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
            bf.setPos(cx, this.getY() + yoff, cz);
            bf.setNotMe();
            this.playSound(SoundEvents.CREEPER_PRIMED, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level.addFreshEntity((Entity)bf);
        }
        if (this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(1.0f);
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
        if (par1Mob instanceof Brutalfly) {
            return false;
        }
        if (par1Mob instanceof Mothra) {
            return false;
        }
        if (par1Mob instanceof Vortex) {
            return false;
        }
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(25.0, 20.0, 25.0));
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

