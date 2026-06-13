/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Brutalfly
 *  com.astryxion.chaospersists.CrystalCow
 *  com.astryxion.chaospersists.Flounder
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Irukandji
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Peacock
 *  com.astryxion.chaospersists.Rotator
 *  com.astryxion.chaospersists.Skate
 *  com.astryxion.chaospersists.Urchin
 *  com.astryxion.chaospersists.Vortex
 *  com.astryxion.chaospersists.Whale
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
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
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.Urchin;
import com.astryxion.chaospersists.entity.Whale;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class Vortex
extends MonsterEntity {
    private BlockPos currentFlightTarget = null;
    private GenericTargetSorter TargetSorter = null;
    private int winded = 0;
    private int busy_fighting = 0;
    private int was_spawnered = 0;

    public Vortex(EntityType<? extends Vortex> type, World par1World) {
        super(type, par1World);
        this.xpReward = 200;
                this.TargetSorter = new GenericTargetSorter((Entity)this);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static net.minecraft.entity.ai.attributes.AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Vortex_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3499999940395355)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Vortex_stats.attack)
                .build();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.busy_fighting != 0) {
            return false;
        }
        if (this.was_spawnered != 0) {
            return false;
        }
        return true;
    }

    protected float getSoundVolume() {
        return 0.75f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.VORTEXLIVE;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return null;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.VORTEXLIVE;
    }

    public boolean canBePushed() {
        return true;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Vortex_stats.health;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void tick() {
        LivingEntity e = null;
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.6, this.getDeltaMovement().z);
        this.busy_fighting = 0;
        e = this.findSomethingToAttack();
        if (e != null) {
            this.busy_fighting = 1;
            if (this.level.isClientSide) {
                for (int i = 0; i < 20; ++i) {
                    double d = this.level.random.nextDouble() * 3.5;
                    d *= d;
                    double dir = this.level.random.nextDouble() * 2.0 * 3.141592653589793;
                    double dx = Math.cos(dir -= 3.141592653589793) * d / 2.0;
                    double dz = Math.sin(dir) * d / 2.0;
                    this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX() + dx, this.getY() + 0.75 + d, this.getZ() + dz, Math.cos(dir) * (double)this.level.random.nextFloat() / 4.0, (double)(this.level.random.nextFloat() / 2.0f), Math.sin(dir += 1.5707963267948966) * (double)this.level.random.nextFloat() / 4.0);
                }
            }
        }
        if (this.level.random.nextInt(200) == 1) {
            this.heal(1.0f);
        }
        if (this.isPersistenceRequired()) {
            return;
        }
        if (this.busy_fighting != 0) {
            return;
        }
        if (this.was_spawnered != 0) {
            return;
        }
        long t = this.level.getGameTime();
        if ((t %= 24000L) < 12000L && this.level.random.nextInt(500) == 1) {
            this.remove();
        }
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new net.minecraft.util.math.RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.75, this.getZ()), new Vector3d(pX, pY, pZ), net.minecraft.util.math.RayTraceContext.BlockMode.COLLIDER, net.minecraft.util.math.RayTraceContext.FluidMode.NONE, this)).getType() == net.minecraft.util.math.RayTraceResult.Type.MISS;
    }

    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        LivingEntity e = null;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.winded > 0) {
            --this.winded;
        }
        if (this.random.nextInt(300) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.1) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.random.nextInt(14) + 10;
                xdir = this.random.nextInt(14) + 10;
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
        }
        if ((e = this.findSomethingToAttack()) != null) {
            this.currentFlightTarget = new BlockPos((int) e.getX(), (int) e.getY(), (int) e.getZ());
            double d = this.distanceToSqr(e);
            if (d < 81.0 && this.winded == 0) {
                double a = Math.atan2(this.getZ() - e.getZ(), this.getX() - e.getX());
                double pm = 1.0;
                if (e instanceof PlayerEntity) {
                    pm = 2.0;
                }
                e.push(Math.cos(a) * (10.0 - Math.sqrt(d)) * 0.10000000149011612, (10.0 - Math.sqrt(d)) * 0.05000000074505806 * pm, Math.sin(a) * (10.0 - Math.sqrt(d)) * 0.10000000149011612);
            }
            if (this.distanceToSqr(e) < (double) ((4.0f + e.getBbWidth() / 2.0f) * (4.0f + e.getBbWidth() / 2.0f)) && this.random.nextInt(8) == 2) {
                this.doHurtTarget(e);
            }
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.4 - this.getDeltaMovement().x) * 0.2;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.20000000149011612;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.4 - this.getDeltaMovement().z) * 0.2;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = net.minecraft.util.math.MathHelper.wrapDegrees(var7 - this.yRot);
        this.xxa = 0.75f;
        this.yRot += var8 / 4.0f;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = par1DamageSource.getEntity();
        ret = super.hurt(par1DamageSource, par2);
        if (e != null && this.currentFlightTarget != null) {
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)e.getY(), (int)e.getZ());
        }
        this.winded = 20;
        return ret;
    }

    public int getArmorValue() {
        return ChaosPersists.Vortex_stats.defense;
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
                    if (s == null || !s.equals("Vortex")) continue;
                    this.was_spawnered = 1;
                    return true;
                }
            }
        }
        for (k = -2; k <= 2; ++k) {
            for (j = -2; j <= 2; ++j) {
                for (i = 1; i < 4; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (!CrystalDimensionSpawnHelper.isCrystalDimension(this.level)) {
            long t = this.level.getGameTime();
            if ((t %= 24000L) < 12000L) {
                return false;
            }
            if (this.level.random.nextInt(2) != 1) {
                return false;
            }
        }
        Vortex target = null;
        target = this.level.getNearestEntity(Vortex.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(20.0, 16.0, 20.0));
        if (target != null) {
            return false;
        }
        return true;
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
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
        }
        if (par1Mob instanceof Vortex) {
            return false;
        }
        if (par1Mob instanceof Rotator) {
            return false;
        }
        if (par1Mob instanceof Mothra) {
            return false;
        }
        if (par1Mob instanceof Brutalfly) {
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
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 10.0, 16.0));
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

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(6) - (double)ChaosPersists.ChaosRand.nextInt(6), this.getY() + 1.0 + (double)this.level.random.nextInt(10), this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(6) - (double)ChaosPersists.ChaosRand.nextInt(6), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.VortexEye, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int i = 5 + this.level.random.nextInt(7);
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(10);
            if (var3 == 0) {
                this.dropItemRand(Items.STICK, 1);
            }
            if (var3 == 1) {
                this.dropItemRand(ChaosPersists.MyTigersEyeIngot, 1);
            }
            if (var3 == 2) {
                this.dropItemRand(ChaosPersists.MyCrystalPinkIngot, 1);
            }
            if (var3 == 3) {
                this.dropItemRand(Items.IRON_INGOT, 1);
            }
            if (var3 == 4) {
                this.dropItemRand(ChaosPersists.UraniumNugget, 1);
            }
            if (var3 == 6) {
                this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
            }
            if (var3 == 7) {
                this.dropItemRand(ChaosPersists.MyIrukandji, 1);
            }
            if (var3 != 8) continue;
            this.dropItemRand(Item.byBlock((Block)ChaosPersists.CrystalCoal), 1);
        }
    }

    protected Item getDropItem() {
        return ChaosPersists.FairyEgg;
    }
}

