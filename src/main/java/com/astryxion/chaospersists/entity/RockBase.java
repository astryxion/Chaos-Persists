package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class RockBase extends Mob {
    private static final net.minecraft.network.syncher.EntityDataAccessor<Integer> ROCK_TYPE =
            net.minecraft.network.syncher.SynchedEntityData.defineId(
                    RockBase.class, net.minecraft.network.syncher.EntityDataSerializers.INT);
    public int rock_type = 0;
    private double dx;
    private double dz;

    public RockBase(EntityType<? extends RockBase> type, Level level) {
        super(type, level);
        this.dz = 0.0;
        this.dx = 0.0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected void registerGoals() {
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ROCK_TYPE, 0);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if ("inWall".equals(par1DamageSource.getMsgId())) {
            return false;
        }
        if (e instanceof LivingEntity) {
            this.playSound(SoundEvents.ITEM_PICKUP, 0.75f, 2.25f);
        }
        return super.hurt(par1DamageSource, par2);
    }

    public int getRockType() {
        return this.entityData.get(ROCK_TYPE);
    }

    public void setRockType(int par1) {
        if (this.level() == null) {
            return;
        }
        if (this.level().isClientSide) {
            return;
        }
        this.entityData.set(ROCK_TYPE, par1);
    }

    public void placeRock(int par1) {
        this.rock_type = par1;
        this.setRockType(par1);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
                .setBaseValue(1.0 + this.rock_type / 4.0);
        this.setHealth((float) (1 + this.rock_type / 4));
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, net.minecraft.core.BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public void tick() {
        if (this.dx == 0.0 && this.dz == 0.0) {
            this.dx = this.getX();
            this.dz = this.getZ();
        }
        super.tick();
        this.setXRot(0.0f);
        this.setYHeadRot(0.0f);
        this.setYRot(0.0f);
        if (this.level().isClientSide) {
            this.rock_type = this.getRockType();
        }
        if (!this.level().isClientSide && this.rock_type == 0) {
            if (!this.level().dimension().equals(ChaosPersists.getDimensionKey(5))) {
                this.rock_type = 1;
                if (this.getRandom().nextInt(10) == 0) {
                    this.rock_type = 2;
                }
                if (this.getRandom().nextInt(20) == 0) {
                    this.rock_type = 3;
                }
                if (this.getRandom().nextInt(30) == 0) {
                    this.rock_type = 4;
                }
                if (this.getRandom().nextInt(40) == 0) {
                    this.rock_type = 5;
                }
                if (this.getRandom().nextInt(50) == 0) {
                    this.rock_type = 6;
                }
                if (this.getRandom().nextInt(100) == 0) {
                    this.rock_type = 7;
                }
                if (this.getRandom().nextInt(200) == 0) {
                    this.rock_type = 8;
                }
                if (this.getRandom().nextInt(500) == 0) {
                    this.rock_type = 9;
                }
                if (this.getRandom().nextInt(500) == 0) {
                    this.rock_type = 10;
                }
                if (this.getRandom().nextInt(500) == 0) {
                    this.rock_type = 11;
                }
                if (this.getRandom().nextInt(1000) == 0) {
                    this.rock_type = 12;
                }
            } else {
                this.rock_type = 9;
                if (this.getRandom().nextInt(3) == 0) {
                    this.rock_type = 10;
                }
                if (this.getRandom().nextInt(5) == 0) {
                    this.rock_type = 11;
                }
                if (this.getRandom().nextInt(10) == 0) {
                    this.rock_type = 12;
                }
            }
            this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
                    .setBaseValue(1.0 + this.rock_type / 4.0);
            this.setHealth((float) (1 + this.rock_type / 4));
        }
        if (!this.level().isClientSide) {
            this.setRockType(this.rock_type);
        }
        if (this.level().isClientSide) {
            if (this.rock_type == 9 && this.getRandom().nextInt(20) == 0) {
                this.level()
                        .addParticle(
                                ParticleTypes.FLAME,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f,
                                this.getRandom().nextFloat() / 10.0f,
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f);
            }
            if (this.rock_type == 10 && this.getRandom().nextInt(20) == 0) {
                this.level()
                        .addParticle(
                                ParticleTypes.HAPPY_VILLAGER,
                                this.getX(),
                                this.getY() + 0.25,
                                this.getZ(),
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f,
                                this.getRandom().nextFloat() / 2.0f,
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f);
            }
            if (this.rock_type == 11 && this.getRandom().nextInt(20) == 0) {
                this.level()
                        .addParticle(
                                ParticleTypes.SMOKE,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f,
                                this.getRandom().nextFloat() / 10.0f,
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f);
            }
            if (this.rock_type == 12 && this.getRandom().nextInt(20) == 0) {
                this.level()
                        .addParticle(
                                ParticleTypes.FIREWORK,
                                this.getX(),
                                this.getY() + 0.25,
                                this.getZ(),
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f,
                                this.getRandom().nextFloat() / 5.0f,
                                (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 60.0f);
            }
        }
    }

    @Override
    protected float getSoundVolume() {
        return 0.65f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return this.getY() >= 50.0;
    }

    public static boolean checkRockSpawnRules(
            EntityType<RockBase> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            net.minecraft.core.BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return pos.getY() >= 50;
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
    public void animateHurt(float yaw) {
        this.hurtDuration = 0;
        this.hurtTime = 0;
    }

    @Override
    public void die(DamageSource par1DamageSource) {
        if (this.rock_type == 1) {
            dropItemRand(ChaosPersists.MySmallRock, 1);
        }
        if (this.rock_type == 2) {
            dropItemRand(ChaosPersists.MyRock, 1);
        }
        if (this.rock_type == 3) {
            dropItemRand(ChaosPersists.MyRedRock, 1);
        }
        if (this.rock_type == 4) {
            dropItemRand(ChaosPersists.MyGreenRock, 1);
        }
        if (this.rock_type == 5) {
            dropItemRand(ChaosPersists.MyBlueRock, 1);
        }
        if (this.rock_type == 6) {
            dropItemRand(ChaosPersists.MyPurpleRock, 1);
        }
        if (this.rock_type == 7) {
            dropItemRand(ChaosPersists.MySpikeyRock, 1);
        }
        if (this.rock_type == 8) {
            dropItemRand(ChaosPersists.MyTNTRock, 1);
        }
        if (this.rock_type == 9) {
            dropItemRand(ChaosPersists.MyCrystalRedRock, 1);
        }
        if (this.rock_type == 10) {
            dropItemRand(ChaosPersists.MyCrystalGreenRock, 1);
        }
        if (this.rock_type == 11) {
            dropItemRand(ChaosPersists.MyCrystalBlueRock, 1);
        }
        if (this.rock_type == 12) {
            dropItemRand(ChaosPersists.MyCrystalTNTRock, 1);
        }
        super.die(par1DamageSource);
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemStack is = new ItemStack(index, par1);
        if (index == null) {
            return is;
        }
        Vec3 pos = this.position();
        net.minecraft.world.entity.item.ItemEntity entityItem =
                new net.minecraft.world.entity.item.ItemEntity(
                        this.level(),
                        pos.x + (ChaosPersists.ChaosRand.nextFloat() - ChaosPersists.ChaosRand.nextFloat()) / 3.0f,
                        pos.y + 0.25,
                        pos.z + (ChaosPersists.ChaosRand.nextFloat() - ChaosPersists.ChaosRand.nextFloat()) / 3.0f,
                        is);
        this.level().addFreshEntity(entityItem);
        return is;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        par1NBTTagCompound.putInt("ButterflyType", this.rock_type);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.rock_type = par1NBTTagCompound.getInt("ButterflyType");
    }
}
