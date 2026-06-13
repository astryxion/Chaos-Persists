package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class RockBase extends MobEntity {
    private static final DataParameter<Integer> ROCK_TYPE = EntityDataManager.defineId(RockBase.class, DataSerializers.INT);
    public int rock_type = 0;
    private double dx;
    private double dz;

    public RockBase(EntityType<? extends RockBase> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.25f, height=0.15f
        this.dz = 0.0;
        this.dx = 0.0;
    }
    @Override
    public boolean fireImmune() {
        return true;
    }


    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0).build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ROCK_TYPE, 0);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        if (e != null && e instanceof LivingEntity) {
            this.playSound(SoundEvents.ITEM_PICKUP, 0.75f, 2.25f);
        }
        return super.hurt(par1DamageSource, par2);
    }

    public int getRockType() {
        return this.entityData.get(ROCK_TYPE);
    }

    public void setRockType(int par1) {
        if (this.level == null) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        this.entityData.set(ROCK_TYPE, par1);
    }

    public void placeRock(int par1) {
        this.rock_type = par1;
        this.setRockType(par1);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) (1 + this.rock_type / 4));
        this.setHealth((float) (1 + this.rock_type / 4));
    }

    public int getArmorValue() {
        return 0;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public void tick() {
        if (this.dx == 0.0 && this.dz == 0.0) {
            this.dx = this.getX();
            this.dz = this.getZ();
        }
        super.tick();
        this.xRot = 0.0f;
        this.yHeadRot = 0.0f;
        this.yRot = 0.0f;
        if (this.level.isClientSide) {
            this.rock_type = this.getRockType();
        }
        if (!this.level.isClientSide && this.rock_type == 0) {
            if (ChaosPersists.getDimensionId(this.level) != ChaosPersists.getDimension(5)) {
                this.rock_type = 1;
                if (this.level.random.nextInt(10) == 0) {
                    this.rock_type = 2;
                }
                if (this.level.random.nextInt(20) == 0) {
                    this.rock_type = 3;
                }
                if (this.level.random.nextInt(30) == 0) {
                    this.rock_type = 4;
                }
                if (this.level.random.nextInt(40) == 0) {
                    this.rock_type = 5;
                }
                if (this.level.random.nextInt(50) == 0) {
                    this.rock_type = 6;
                }
                if (this.level.random.nextInt(100) == 0) {
                    this.rock_type = 7;
                }
                if (this.level.random.nextInt(200) == 0) {
                    this.rock_type = 8;
                }
                if (this.level.random.nextInt(500) == 0) {
                    this.rock_type = 9;
                }
                if (this.level.random.nextInt(500) == 0) {
                    this.rock_type = 10;
                }
                if (this.level.random.nextInt(500) == 0) {
                    this.rock_type = 11;
                }
                if (this.level.random.nextInt(1000) == 0) {
                    this.rock_type = 12;
                }
            } else {
                this.rock_type = 9;
                if (this.level.random.nextInt(3) == 0) {
                    this.rock_type = 10;
                }
                if (this.level.random.nextInt(5) == 0) {
                    this.rock_type = 11;
                }
                if (this.level.random.nextInt(10) == 0) {
                    this.rock_type = 12;
                }
            }
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) (1 + this.rock_type / 4));
            this.setHealth((float) (1 + this.rock_type / 4));
        }
        if (!this.level.isClientSide) {
            this.setRockType(this.rock_type);
        }
        if (this.level.isClientSide) {
            if (this.rock_type == 9 && this.level.random.nextInt(20) == 0) {
                this.level.addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f), (double) (this.level.random.nextFloat() / 10.0f), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f));
            }
            if (this.rock_type == 10 && this.level.random.nextInt(20) == 0) {
                this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY() + 0.25, this.getZ(), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f), (double) (this.level.random.nextFloat() / 2.0f), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f));
            }
            if (this.rock_type == 11 && this.level.random.nextInt(20) == 0) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f), (double) (this.level.random.nextFloat() / 10.0f), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f));
            }
            if (this.rock_type == 12 && this.level.random.nextInt(20) == 0) {
                this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY() + 0.25, this.getZ(), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f), (double) (this.level.random.nextFloat() / 5.0f), (double) ((this.level.random.nextFloat() - this.level.random.nextFloat()) / 60.0f));
            }
        }
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 0.65f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return null;
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public void animateHurt() {
        this.hurtDuration = 0;
        this.hurtTime = 0;
        this.hurtDir = 0.0f;
    }

    @Override
    protected void tickDeath() {
        this.remove();
    }

    @Override
    public void die(DamageSource par1DamageSource) {
        super.die(par1DamageSource);
        if (this.rock_type == 1) {
            this.dropItemRand(ChaosPersists.MySmallRock, 1);
        }
        if (this.rock_type == 2) {
            this.dropItemRand(ChaosPersists.MyRock, 1);
        }
        if (this.rock_type == 3) {
            this.dropItemRand(ChaosPersists.MyRedRock, 1);
        }
        if (this.rock_type == 4) {
            this.dropItemRand(ChaosPersists.MyGreenRock, 1);
        }
        if (this.rock_type == 5) {
            this.dropItemRand(ChaosPersists.MyBlueRock, 1);
        }
        if (this.rock_type == 6) {
            this.dropItemRand(ChaosPersists.MyPurpleRock, 1);
        }
        if (this.rock_type == 7) {
            this.dropItemRand(ChaosPersists.MySpikeyRock, 1);
        }
        if (this.rock_type == 8) {
            this.dropItemRand(ChaosPersists.MyTNTRock, 1);
        }
        if (this.rock_type == 9) {
            this.dropItemRand(ChaosPersists.MyCrystalRedRock, 1);
        }
        if (this.rock_type == 10) {
            this.dropItemRand(ChaosPersists.MyCrystalGreenRock, 1);
        }
        if (this.rock_type == 11) {
            this.dropItemRand(ChaosPersists.MyCrystalBlueRock, 1);
        }
        if (this.rock_type == 12) {
            this.dropItemRand(ChaosPersists.MyCrystalTNTRock, 1);
        }
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemStack is = new ItemStack(index, par1);
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double) ((ChaosPersists.ChaosRand.nextFloat() - ChaosPersists.ChaosRand.nextFloat()) / 3.0f), this.getY() + 0.25, this.getZ() + (double) ((ChaosPersists.ChaosRand.nextFloat() - ChaosPersists.ChaosRand.nextFloat()) / 3.0f), is);
        this.level.addFreshEntity(var3);
        return is;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("ButterflyType", this.rock_type);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.rock_type = par1CompoundNBT.getInt("ButterflyType");
    }

    /** 1.12.2 {@code LivingEntity#playAmbientSound()} for cross-class spawn helpers. */
    public static void playSpawnAmbientSound(LivingEntity entity) {
        if (!(entity instanceof MobEntity)) {
            return;
        }
        MobEntity mob = (MobEntity) entity;
        try {
            java.lang.reflect.Method getAmbient = MobEntity.class.getDeclaredMethod("getAmbientSound");
            getAmbient.setAccessible(true);
            SoundEvent sound = (SoundEvent) getAmbient.invoke(mob);
            if (sound == null) {
                return;
            }
            java.lang.reflect.Method getVolume = MobEntity.class.getDeclaredMethod("getSoundVolume");
            getVolume.setAccessible(true);
            java.lang.reflect.Method getPitch = MobEntity.class.getDeclaredMethod("getVoicePitch");
            getPitch.setAccessible(true);
            float volume = ((Float) getVolume.invoke(mob)).floatValue();
            float pitch = ((Float) getPitch.invoke(mob)).floatValue();
            mob.playSound(sound, volume, pitch);
        } catch (ReflectiveOperationException ignored) {
        }
    }
}
