/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EnderReaper
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IndirectEntityDamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.LightType;
import net.minecraft.tags.FluidTags;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class EnderReaper
extends MonsterEntity {
    private static final DataParameter<Byte> SCREAMING = EntityDataManager.defineId(EnderReaper.class, DataSerializers.BYTE);
    private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier attackingSpeedBoostModifier = new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 6.199999809265137, AttributeModifier.Operation.ADDITION);
    private int teleportDelay;
    private int stareTimer;
    private Entity lastEntityToAttack;

    public EnderReaper(EntityType<? extends EnderReaper> type, World par1World) {
        super(type, par1World);
        this.maxUpStep = 1.0F;
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.EnderReaper_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.37)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.EnderReaper_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCREAMING, (byte)0);
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
    }

    protected Entity findPlayerEntityToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        PlayerEntity entityplayer = this.level.getNearestPlayer(this, 81.0);
        if (entityplayer != null) {
            if (this.shouldAttackPlayerEntity(entityplayer)) {
                if (this.stareTimer == 0) {
                    this.level.playSound(null, entityplayer.getX(), entityplayer.getY(), entityplayer.getZ(), SoundEvents.ENDERMAN_STARE, SoundCategory.HOSTILE, 1.0f, 1.0f);
                }
                if (this.stareTimer++ == 5) {
                    this.stareTimer = 0;
                }
                this.setScreaming(true);
                return entityplayer;
            }
            this.stareTimer = 0;
            this.setScreaming(false);
        }
        return null;
    }

    private boolean shouldAttackPlayerEntity(PlayerEntity par1PlayerEntityEntity) {
        ItemStack itemstack = par1PlayerEntityEntity.getItemBySlot(EquipmentSlotType.HEAD);
        if (!itemstack.isEmpty() && (itemstack.getItem() == Items.CARVED_PUMPKIN || itemstack.getItem() == Item.byBlock((Block)Blocks.PUMPKIN))) {
            return false;
        }
        Vector3d lookVec = par1PlayerEntityEntity.getLookAngle();
        Vector3d vec31 = new Vector3d((double)(this.getX() - par1PlayerEntityEntity.getX()), (double)(this.getBoundingBox().minY + (double)(this.getBbHeight() / 2.0f) - (par1PlayerEntityEntity.getY() + (double)par1PlayerEntityEntity.getEyeHeight())), (double)(this.getZ() - par1PlayerEntityEntity.getZ()));
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = lookVec.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 ? par1PlayerEntityEntity.canSee((Entity)this) : false;
    }

    @Override
    public void tick() {
        float f;
        if (this.isInWater()) {
            this.hurt(DamageSource.DROWN, 1.0f);
        }
        if (this.lastEntityToAttack != this.getTarget()) {
            ModifiableAttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            attributeinstance.removeModifier(attackingSpeedBoostModifier);
            if (this.getTarget() != null) {
                attributeinstance.addTransientModifier(attackingSpeedBoostModifier);
            }
        }
        this.lastEntityToAttack = this.getTarget();
        for (int i = 0; i < 2; ++i) {
            if (this.level.isClientSide) {
                this.level.addParticle(net.minecraft.particles.ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5) * (double)this.getBbWidth(), this.getY() + this.random.nextDouble() * (double)this.getBbHeight() - 0.25, this.getZ() + (this.random.nextDouble() - 0.5) * (double)this.getBbWidth(), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.0);
            }
        }
        net.minecraft.util.math.BlockPos brightnessPos = new net.minecraft.util.math.BlockPos(MathHelper.floor(this.getX()), MathHelper.floor(this.getY()), MathHelper.floor(this.getZ()));
        if (this.level.isDay() && !this.level.isClientSide && (f = this.level.getBrightness(LightType.BLOCK, brightnessPos)) > 0.5f && this.level.canSeeSky(brightnessPos) && this.random.nextFloat() * 30.0f < (f - 0.4f) * 2.0f) {
            this.setTarget(null);
            this.setScreaming(false);
            this.teleportRandomly();
        }
        if (this.isInWater() || this.isOnFire()) {
            this.setScreaming(false);
            this.teleportRandomly();
        }
        this.jumping = false;
        if (this.getTarget() != null) {
            this.lookAt(this.getTarget(), 100.0f, 100.0f);
        }
        if (!this.level.isClientSide && this.isAlive()) {
            if (this.getTarget() != null) {
                if (this.getTarget() instanceof PlayerEntity && this.shouldAttackPlayerEntity((PlayerEntity)this.getTarget())) {
                    if (this.getTarget().distanceToSqr((Entity)this) < 16.0) {
                        this.teleportRandomly();
                    }
                    this.teleportDelay = 0;
                } else if (this.getTarget().distanceToSqr((Entity)this) > 256.0 && this.teleportDelay++ >= 30 && this.teleportToEntity(this.getTarget())) {
                    this.teleportDelay = 0;
                }
            } else {
                this.setScreaming(false);
                this.teleportDelay = 0;
            }
        }
        super.tick();
    }

    protected boolean teleportRandomly() {
        double d0 = this.getX() + (this.random.nextDouble() - 0.5) * 64.0;
        double d1 = this.getY() + (double)(this.random.nextInt(64) - 32);
        double d2 = this.getZ() + (this.random.nextDouble() - 0.5) * 64.0;
        return this.attemptTeleportTo(d0, d1, d2);
    }

    protected boolean teleportToEntity(Entity par1Entity) {
        Vector3d vec = new Vector3d((double)(this.getX() - par1Entity.getX()), (double)(this.getBoundingBox().minY + (double)(this.getBbHeight() / 2.0f) - par1Entity.getY() + (double)par1Entity.getEyeHeight()), (double)(this.getZ() - par1Entity.getZ()));
        vec = vec.normalize();
        double d0 = 16.0;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5) * 8.0 - vec.x * d0;
        double d2 = this.getY() + (double)(this.random.nextInt(16) - 8) - vec.y * d0;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5) * 8.0 - vec.z * d0;
        return this.attemptTeleportTo(d1, d2, d3);
    }

    protected boolean attemptTeleportTo(double par1, double par3, double par5) {
        int j;
        int k;
        double d3 = this.getX();
        double d4 = this.getY();
        double d5 = this.getZ();
        double tryX = par1;
        double tryY = par3;
        double tryZ = par5;
        boolean flag = false;
        int i = MathHelper.floor(tryX);
        net.minecraft.util.math.BlockPos blockPos = new net.minecraft.util.math.BlockPos(i, j = MathHelper.floor(tryY), k = MathHelper.floor(tryZ));
        if (this.level.hasChunkAt(blockPos)) {
            boolean flag1 = false;
            while (!flag1 && j > 0) {
                net.minecraft.block.BlockState belowState = this.level.getBlockState(new net.minecraft.util.math.BlockPos(i, j - 1, k));
                if (!belowState.isAir() && belowState.getMaterial().blocksMotion()) {
                    flag1 = true;
                    continue;
                }
                tryY -= 1.0;
                --j;
            }
            if (flag1) {
                this.setPos(tryX, tryY, tryZ);
                if (this.level.noCollision(this) && !this.level.containsAnyLiquid(this.getBoundingBox())) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            this.setPos(d3, d4, d5);
            return false;
        }
        int short1 = 128;
        for (int lx = 0; lx < short1; ++lx) {
            double d6 = (double)lx / ((double)short1 - 1.0);
            float f = (this.random.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.random.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.random.nextFloat() - 0.5f) * 0.2f;
            double d7 = d3 + (this.getX() - d3) * d6 + (this.random.nextDouble() - 0.5) * (double)this.getBbWidth() * 2.0;
            double d8 = d4 + (this.getY() - d4) * d6 + this.random.nextDouble() * (double)this.getBbHeight();
            double d9 = d5 + (this.getZ() - d5) * d6 + (this.random.nextDouble() - 0.5) * (double)this.getBbWidth() * 2.0;
            if (this.level.isClientSide) {
                this.level.addParticle(net.minecraft.particles.ParticleTypes.PORTAL, d7, d8, d9, (double)f, (double)f1, (double)f2);
            }
        }
        this.level.playSound(null, d3, d4, d5, SoundEvents.ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0f, 1.0f);
        this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0f, 1.0f);
        return true;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return this.isScreaming() ? SoundEvents.ENDERMAN_SCREAM : SoundEvents.ENDERMAN_AMBIENT;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return SoundEvents.ENDERMAN_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return SoundEvents.ENDERMAN_DEATH;
    }

    protected Item getDropItem() {
        return Items.ENDER_EYE;
    }

    public int getArmorValue() {
        return ChaosPersists.EnderReaper_stats.defense;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        Item j = this.getDropItem();
        if (j != null) {
            int k = this.random.nextInt(2 + looting);
            for (int l = 0; l < k; ++l) {
                this.spawnAtLocation(j, 1);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        this.setScreaming(true);
        if (par1DamageSource instanceof net.minecraft.util.IndirectEntityDamageSource) {
            for (int i = 0; i < 16; ++i) {
                if (!this.teleportRandomly()) continue;
                return true;
            }
            return super.hurt(par1DamageSource, par2);
        }
        return super.hurt(par1DamageSource, par2);
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileentitymobspawner = null;
                    tileentitymobspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Ender Reaper")) continue;
                    return true;
                }
            }
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        if (this.getY() < 30.0) {
            return false;
        }
        EnderReaper target = null;
        target = this.level.getNearestEntity(EnderReaper.class, EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(16.0, 8.0, 16.0));
        if (target != null) {
            return false;
        }
        return true;
    }

    protected boolean isValidLightLevel() {
        net.minecraft.util.math.BlockPos pos = new net.minecraft.util.math.BlockPos(MathHelper.floor(this.getX()), MathHelper.floor(this.getBoundingBox().minY), MathHelper.floor(this.getZ()));
        if (this.level.getBrightness(LightType.SKY, pos) > this.random.nextInt(32)) {
            return false;
        }
        int l = this.level.getBrightness(LightType.BLOCK, pos);
        return l <= this.random.nextInt(8);
    }

    public boolean isScreaming() {
        return this.entityData.get(SCREAMING).byteValue() > 0;
    }

    public void setScreaming(boolean par1) {
        this.entityData.set(SCREAMING, (byte)(par1 ? 1 : 0));
    }
}

