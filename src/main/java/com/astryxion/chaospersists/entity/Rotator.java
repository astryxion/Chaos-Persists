/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Bee
 *  com.astryxion.chaospersists.CloudShark
 *  com.astryxion.chaospersists.CrystalCow
 *  com.astryxion.chaospersists.DungeonBeast
 *  com.astryxion.chaospersists.Flounder
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Irukandji
 *  com.astryxion.chaospersists.LurkingTerror
 *  com.astryxion.chaospersists.Mantis
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Peacock
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Rotator
 *  com.astryxion.chaospersists.Skate
 *  com.astryxion.chaospersists.Termite
 *  com.astryxion.chaospersists.TerribleTerror
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
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.entity.projectile.ArrowEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
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

import com.astryxion.chaospersists.entity.Bee;
import com.astryxion.chaospersists.entity.CloudShark;
import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.Termite;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.Urchin;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.Whale;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class Rotator
extends MonsterEntity {
    private BlockPos currentFlightTarget = null;
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int busy_fighting = 0;
    private int was_spawnered = 0;

    public Rotator(EntityType<? extends Rotator> type, World par1World) {
        super(type, par1World);
        this.xpReward = 35;
                this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
    }
    @Override
    public boolean fireImmune() {
        return true;
    }


    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Rotator_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Rotator_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
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
        return com.astryxion.chaospersists.core.ChaosSounds.GLASSHIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.GLASSDEAD;
    }

    public boolean canBePushed() {
        return true;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Rotator_stats.health;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void tick() {
        LivingEntity e = null;
        super.tick();
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        if (this.level.isClientSide && this.level.random.nextInt(10) == 1) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX(), this.getY() + 1.399999976158142, this.getZ(), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 4.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 4.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 4.0f));
        }
        this.busy_fighting = 0;
        e = this.findSomethingToAttack();
        if (e != null) {
            double a = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX(), this.getY() + 1.399999976158142, this.getZ(), Math.cos(a), (e.getY() - this.getY()) / 10.0, Math.sin(a));
            this.busy_fighting = 1;
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
        if ((t %= 24000L) < 12000L && this.level.random.nextInt(400) == 1) {
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
        if (this.random.nextInt(300) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.1) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.random.nextInt(10) + 8;
                xdir = this.random.nextInt(10) + 8;
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
        } else if (this.random.nextInt(9) == 2 && (e = this.findSomethingToAttack()) != null) {
            double a = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)(e.getX() + 2.5 * Math.cos(a)), (int)e.getY(), (int)(e.getZ() + 2.5 * Math.sin(a += 1.5707963267948966)));
            if (this.distanceToSqr((Entity)e) < 9.0) {
                this.doHurtTarget(e);
            }
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.4 - this.getDeltaMovement().x) * 0.2, (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.20000000149011612, (Math.signum(var5) * 0.4 - this.getDeltaMovement().z) * 0.2);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
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

    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof ArrowEntity) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        if (e != null && this.currentFlightTarget != null) {
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)e.getY(), (int)e.getZ());
        }
        return ret;
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
                    if (s == null || !s.equals("Rotator")) continue;
                    this.was_spawnered = 1;
                    return true;
                }
            }
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        for (k = -1; k <= 1; ++k) {
            for (j = -1; j <= 1; ++j) {
                for (i = 1; i < 3; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
        }
        if (!CrystalDimensionSpawnHelper.isCrystalDimension(this.level)) {
            long t = this.level.getGameTime();
            if ((t %= 24000L) < 12000L) {
                return false;
            }
        }
        return true;
    }

    public int getArmorValue() {
        return ChaosPersists.Rotator_stats.defense;
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
        if (par1Mob instanceof Termite) {
            return false;
        }
        if (par1Mob instanceof Vortex) {
            return false;
        }
        if (par1Mob instanceof Rotator) {
            return false;
        }
        if (par1Mob instanceof DungeonBeast) {
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
        if (par1Mob instanceof TerribleTerror) {
            return false;
        }
        if (par1Mob instanceof LurkingTerror) {
            return false;
        }
        if (par1Mob instanceof CloudShark) {
            return false;
        }
        if (par1Mob instanceof Mothra) {
            return false;
        }
        if (par1Mob instanceof Bee) {
            return false;
        }
        if (par1Mob instanceof Mantis) {
            return false;
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 10.0, 12.0));
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

    protected Item getDropItem() {
        int i = this.level.random.nextInt(4);
        if (i == 0) {
            return ChaosPersists.MyCrystalPinkIngot;
        }
        if (i == 1) {
            return ChaosPersists.MyTigersEyeIngot;
        }
        if (i == 2) {
            return Item.byBlock((Block)ChaosPersists.CrystalCoal);
        }
        if (i == 3) {
            return Items.IRON_INGOT;
        }
        return null;
    }
}

