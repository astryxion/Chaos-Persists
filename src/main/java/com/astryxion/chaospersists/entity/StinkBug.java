/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.StinkBug
 *  net.minecraft.block.Block
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.EffectInstance
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class StinkBug
extends AnimalEntity {
    private float moveSpeed = 0.15f;

    public StinkBug(EntityType<? extends StinkBug> type, World par1World) {
        super(type, par1World);
        this.xpReward = 2;
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal((AnimalEntity)this, 1.0));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new AvoidEntityGoal((CreatureEntity)this, PlayerEntity.class, 4.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(6, new LookAtGoal((MobEntity)this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(8, new MyEntityAIWanderALot(this, 10, 1.0));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(10, new RestrictSunGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
    }

    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        super.customServerAiStep();
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (!this.isAlive()) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        if (this.getHealth() <= 0.0f || this.removed) {
            AxisAlignedBB bb = new AxisAlignedBB((double)(this.getX() - 8.0), (double)(this.getY() - 5.0), (double)(this.getZ() - 8.0), (double)(this.getX() + 8.0), (double)(this.getY() + 10.0), (double)(this.getZ() + 8.0));
            List var5 = this.level.getEntitiesOfClass(LivingEntity.class, bb);
            Iterator var2 = var5.iterator();
            LivingEntity var3 = null;
            while (var2.hasNext()) {
                var3 = (LivingEntity)var2.next();
                if (var3 == null) continue;
                var3.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 300, 0));
            }
        }
        return ret;
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    public int mygetMaxHealth() {
        return 5;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return null;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.FART;
    }

    protected float getSoundVolume() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return ChaosPersists.MyDeadStinkBug;
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
                    if (s == null || !s.equals("Stink Bug")) continue;
                    return true;
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isBaby()) {
            this.setAge(0);
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @javax.annotation.Nullable
    @Override
    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) {
        return (StinkBug) this.getType().create(level);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.COD;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == ChaosPersists.MyCrystalApple;
    }
}

