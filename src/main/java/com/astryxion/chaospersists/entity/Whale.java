/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Whale
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.TemptGoal;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Whale
extends AnimalEntity {
    private float moveSpeed = 0.35f;
    private int spray = 0;
    private int spray_timer = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Whale(EntityType<? extends Whale> type, World par1World) {
        super(type, par1World);
        this.moveSpeed = 0.35f;
        this.xpReward = 40;
                this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal((AnimalEntity)this, 1.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2000000476837158, net.minecraft.item.crafting.Ingredient.of(Items.COD), false));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new LookAtGoal((MobEntity)this, PlayerEntity.class, 12.0f));
        this.goalSelector.addGoal(6, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        if (this.spray == 0) {
            if (this.spray_timer > 0) {
                --this.spray_timer;
            }
            if (this.spray_timer == 0) {
                this.spray_timer = 250 + this.level.random.nextInt(250);
                this.spray = 25 + this.level.random.nextInt(25);
            }
        }
        if (this.level.isClientSide && this.spray > 0) {
            for (int i = 0; i < 20; ++i) {
                double d = this.level.random.nextDouble() * 0.75;
                d *= d;
                double dir = this.level.random.nextDouble() * 2.0 * 3.141592653589793;
                double dx = Math.cos(dir -= 3.141592653589793) * d / 2.0;
                double dz = Math.sin(dir) * d / 2.0;
                dir += 1.5707963267948966;
                if (i < 10) {
                    this.level.addParticle(net.minecraft.particles.ParticleTypes.BUBBLE, this.getX() + dx, this.getY() + 1.0 + d, this.getZ() + dz, Math.cos(dir) * (double)this.level.random.nextFloat() / 4.0, (double)(this.level.random.nextFloat() * 2.0f), Math.sin(dir) * (double)this.level.random.nextFloat() / 4.0);
                    continue;
                }
                this.level.addParticle(net.minecraft.particles.ParticleTypes.SPLASH, this.getX() + dx, this.getY() + 1.0 + d, this.getZ() + dz, Math.cos(dir) * (double)this.level.random.nextFloat() / 4.0, (double)(this.level.random.nextFloat() * 2.0f), Math.sin(dir) * (double)this.level.random.nextFloat() / 4.0);
            }
            --this.spray;
        }
        if (this.level.random.nextInt(200) == 1) {
            this.heal(1.0f);
        }
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public int mygetMaxHealth() {
        return 100;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return net.minecraft.util.SoundEvents.GENERIC_SPLASH;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.LITTLE_SPLAT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.BIG_SPLAT;
    }

    protected float getSoundVolume() {
        return 0.9f;
    }

    protected float getVoicePitch() {
        return 0.5f;
    }

    protected Item getDropItem() {
        return Items.COD;
    }

    /**
     * 1.7.10 {@code dropFewItems} was {@code nextInt(25) + 20} iterations each spawning a separate fish item entity
     * (20–44 entities per whale). The 1.12 port matched that literally; it tanks TPS. Same practical reward, one stack.
     */
    @Override
    public void die(net.minecraft.util.DamageSource cause) {
        int n = 2 + this.random.nextInt(5);
        n = Math.min(n, 12);
        if (n > 0) {
            this.spawnAtLocation(new ItemStack(Items.COD, n), 0.0f);
        }
        super.die(cause);
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        Block bid;
        int d;
        int j;
        int found = 0;
        for (i = - dy; i <= dy; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + dx, y + i, z + j)).getBlock();
                if ((bid == Blocks.WATER || bid == Blocks.WATER) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x - dx, y + i, z + j)).getBlock()) != Blocks.WATER && bid != Blocks.WATER || (d = dx * dx + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x - dx;
                this.ty = y + i;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + dy, z + j)).getBlock();
                if ((bid == Blocks.WATER || bid == Blocks.WATER) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y - dy, z + j)).getBlock()) != Blocks.WATER && bid != Blocks.WATER || (d = dy * dy + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x + i;
                this.ty = y - dy;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dy; j <= dy; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z + dz)).getBlock();
                if ((bid == Blocks.WATER || bid == Blocks.WATER) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z - dz)).getBlock()) != Blocks.WATER && bid != Blocks.WATER || (d = dz * dz + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x + i;
                this.ty = y + j;
                this.tz = z - dz;
                ++found;
            }
        }
        if (found != 0) {
            return true;
        }
        return false;
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        if (!this.isAlive()) {
            return;
        }
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (!this.isInWater() && this.level.random.nextInt(20) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 11; ++i) {
                int j = i;
                if (j > 4) {
                    j = 4;
                }
                if (this.scan_it((int)this.getX(), (int)this.getY() - 1, (int)this.getZ(), i, j, i)) break;
                if (i < 5) continue;
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.0);
            } else {
                if (this.level.random.nextInt(25) == 1) {
                    this.heal(-4.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.remove();
                    return;
                }
            }
        }
        if (this.isInWater() && this.level.random.nextInt(50) == 0) {
            this.playSound(net.minecraft.util.SoundEvents.GENERIC_SPLASH, 1.0f, this.level.random.nextFloat() * 0.2f + 0.9f);
            this.heal(1.0f);
        }
    }

    private int findBuddies() {
        List var5 = this.level.getEntitiesOfClass(Whale.class, this.getBoundingBox().inflate(32.0, 8.0, 32.0));
        return var5.size();
    }

    private boolean chaosUtopiaOrVillageDimension() {
        if (this.level == null || this.level.getServer() == null) {
            return false;
        }
        int d = com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level);
        return d == ChaosPersists.getDimension() || d == ChaosPersists.getDimension(3);
    }

    /** Require a short column of water under the mob so Utopia/Village grassland spawns fail. */
    private boolean whaleOverDeepWater() {
        BlockPos base = new BlockPos(this.getX(), this.getY(), this.getZ());
        int depth = 0;
        for (int k = 0; k < 12; k++) {
            if (this.level.getBlockState(base.below(k)).getFluidState().is(net.minecraft.tags.FluidTags.WATER)) {
                depth++;
            } else if (k > 0) {
                break;
            }
        }
        return depth >= 4;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        if (this.level.random.nextInt(50) != 1) {
            return false;
        }
        if (this.findBuddies() > 0) {
            return false;
        }
        if (this.chaosUtopiaOrVillageDimension() && !this.whaleOverDeepWater()) {
            return false;
        }
        return true;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isBaby()) {
            this.setPersistenceRequired();
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
        return (Whale) this.getType().create(level);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.COD;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == ChaosPersists.MyCrystalApple;
    }
}

