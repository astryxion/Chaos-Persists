/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.PurplePower
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.entity.projectile.ArrowEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.EffectInstance
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class PurplePower
extends MobEntity {
    private static final DataParameter<Integer> PURPLE_TYPE = EntityDataManager.defineId(PurplePower.class, DataSerializers.INT);
    private BlockPos currentFlightTarget = null;
    private GenericTargetSorter TargetSorter = null;
    private int purple_type = 0;

    public PurplePower(EntityType<? extends PurplePower> type, World par1World) {
        super(type, par1World);
        initPurplePower();
    }

    public PurplePower(World par1World) {
        this(resolveEntityType(), par1World);
    }

    private void initPurplePower() {
        this.xpReward = 35;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.noPhysics = true;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends PurplePower> resolveEntityType() {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "purple_power"));
        return type != null ? (EntityType<? extends PurplePower>) type : (EntityType<? extends PurplePower>)(EntityType<?>)EntityType.PIG;
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 500.0D)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PURPLE_TYPE, 0);
    }

    public void setPurpleType(int par1) {
        if (this.level == null) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        this.purple_type = par1;
        this.entityData.set(PURPLE_TYPE, par1);
    }

    public int getPurpleType() {
        return this.entityData.get(PURPLE_TYPE);
    }
    protected boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    protected float getSoundVolume() {
        return 0.75f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity par1Entity) {
    }

    public int mygetMaxHealth() {
        return 1000;
    }

    @Override
    public void tick() {
        super.tick();
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6, 1.0);
        if (this.getPurpleType() == 0) {
            if (this.level.isClientSide && this.level.random.nextInt(4) == 1) {
                this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY() + 1.25, this.getZ(), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 2.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 2.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 2.0f));
            }
        } else if (this.level.isClientSide && this.level.random.nextInt(6) == 1) {
            this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY() + 0.6499999761581421, this.getZ(), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 5.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 5.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 5.0f));
        }
        if (this.level.isClientSide) {
            this.purple_type = this.getPurpleType();
        } else {
            this.setPurpleType(this.purple_type);
        }
        if (!this.level.isClientSide && this.level.random.nextInt(2500) == 1) {
            if (this.getPurpleType() == 10) {
                this.level.explode(null, this.getX(), this.getY() + 0.25, this.getZ(), 9.1f, true, Explosion.Mode.DESTROY);
            }
            this.remove();
        }
        if (!this.level.isClientSide) {
            this.updatePurpleAITasks();
        }
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new net.minecraft.util.math.RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.55, this.getZ()), new Vector3d(pX, pY, pZ), net.minecraft.util.math.RayTraceContext.BlockMode.COLLIDER, net.minecraft.util.math.RayTraceContext.FluidMode.NONE, this)).getType() == net.minecraft.util.math.RayTraceResult.Type.MISS;
    }

    protected void updatePurpleAITasks() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        LivingEntity e = null;
        if (!this.isAlive()) {
            return;
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.random.nextInt(300) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.1f) {
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
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)this.getX() + xdir, (int)this.getY() + this.random.nextInt(20) - 10, (int)this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        } else if (this.random.nextInt(7) == 2 && this.level.getDifficulty() != Difficulty.PEACEFUL && (e = this.findSomethingToAttack()) != null) {
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)(e.getY() + (double)(e.getBbHeight() / 2.0f)), (int)e.getZ());
            if (this.distanceToSqr((Entity)e) < (double)((4.0f + e.getBbWidth() / 2.0f) * (4.0f + e.getBbWidth() / 2.0f))) {
                this.doHurtTarget((LivingEntity)e);
                this.remove();
            }
        }
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            this.remove();
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.4 - this.getDeltaMovement().x) * 0.2, (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.20000000149011612, (Math.signum(var5) * 0.4 - this.getDeltaMovement().z) * 0.2);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.zza = 0.75f;
        this.yRot += var8 / 4.0f;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public boolean hurt(net.minecraft.util.DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = par1DamageSource.getEntity();
        float dm = par2;
        if (e != null && e instanceof AbstractArrowEntity) {
            return false;
        }
        if (dm > 10.0f) {
            dm = 10.0f;
        }
        ret = super.hurt(par1DamageSource, dm);
        if (e != null && this.currentFlightTarget != null) {
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)(e.getY() + (double)(e.getBbHeight() / 2.0f)), (int)e.getZ());
        }
        return ret;
    }

    public boolean getCanSpawnHere() {
        return true;
    }

    public int getArmorValue() {
        return 25;
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        TameableEntity e;
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
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
            if (this.getPurpleType() > 0 && this.getPurpleType() != 10) {
                return false;
            }
            return true;
        }
        if (this.getPurpleType() != 0 && this.getPurpleType() != 10 && par1Mob instanceof TameableEntity && (e = (TameableEntity)par1Mob).isTame()) {
            return false;
        }
        if (MyUtils.isRoyalty((Entity)par1Mob)) {
            return false;
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(32.0, 24.0, 32.0));
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

    public boolean doHurtTarget(LivingEntity par1Entity) {
        boolean var4 = false;
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            LivingEntity e = (LivingEntity)par1Entity;
            if (this.getPurpleType() == 0 || this.getPurpleType() == 10) {
                e.setHealth(e.getHealth() / 4.0f - 1.0f);
                var4 = e.hurt(DamageSource.mobAttack((LivingEntity)this), e.getMaxHealth() / 8.0f);
                if (this.getPurpleType() == 10) {
                    this.level.explode(null, e.getX(), e.getY() - 0.25, e.getZ(), 9.1f, true, Explosion.Mode.DESTROY);
                }
            } else {
                e.setHealth(e.getHealth() * 15.0f / 16.0f);
                var4 = e.hurt(DamageSource.mobAttack((LivingEntity)this), 5.0f);
                if (this.getPurpleType() == 1) {
                    e.setSecondsOnFire(10);
                }
                if (this.getPurpleType() == 2) {
                    e.addEffect(new EffectInstance(Effects.POISON, 50, 0));
                }
                if (this.getPurpleType() == 3) {
                    e.addEffect(new EffectInstance(Effects.WEAKNESS, 50, 0));
                }
            }
        }
        return var4;
    }

    protected Item getDropItem() {
        return null;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("PurpleType", this.purple_type);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.purple_type = par1CompoundNBT.getInt("PurpleType");
    }
}

