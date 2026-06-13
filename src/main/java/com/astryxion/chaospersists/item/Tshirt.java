/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Tshirt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
public class Tshirt
extends AnimalEntity {
    private float moveSpeed = 0.0f;

    public Tshirt(EntityType<? extends Tshirt> type, World par1World) {
        super(type, par1World);
        this.xpReward = 40;
    }

    public static AttributeModifierMap createAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    protected boolean canDespawn() {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public void tick() {
        super.tick();
    }

    public int mygetMaxHealth() {
        return 1;
    }

    public int getArmorValue() {
        return 0;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return null;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    protected float getSoundVolume() {
        return 1.0f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.EMERALD;
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean checkSpawnRules(IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (!(level instanceof World) || !((World)level).isDay()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        Tshirt target = this.level.getNearestEntity(Tshirt.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(20.0, 8.0, 20.0));
        if (target != null) {
            return false;
        }
        return true;
    }

    @Override
    @javax.annotation.Nullable
    public AgeableEntity getBreedOffspring(ServerWorld server, AgeableEntity entityageable) {
        return null;
    }
}
