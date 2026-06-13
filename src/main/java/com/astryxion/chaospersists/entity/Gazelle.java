/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Gazelle
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.block.DoublePlantBlock
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.TallGrassBlock
 *  net.minecraft.entity.Entity
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
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.entity.Entity;
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

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.server.ServerWorld;
import javax.annotation.Nullable;
import net.minecraft.util.SoundEvents;

public class Gazelle
extends TameableEntity {
    private float moveSpeed = 0.2f;
    private GenericTargetSorter TargetSorter = null;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Gazelle(EntityType<? extends Gazelle> type, World par1World) {
        super(type, par1World);
        this.moveSpeed = 0.3f;
                        this.setOrderedToSit(false);
        this.xpReward = 5;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal((AnimalEntity)this, 1.0));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner((TameableEntity)this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(3, new AvoidEntityGoal((CreatureEntity)this, MonsterEntity.class, 8.0f, 1.0, 1.7000000476837158));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2000000476837158, net.minecraft.item.crafting.Ingredient.of(Items.APPLE), false));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(6, new AvoidEntityGoal((CreatureEntity)this, PlayerEntity.class, 12.0f, 1.0, 2.0));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(8, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(10, new RestrictSunGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return (AgeableEntity)this.getType().create(level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.setOrderedToSit(false);
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
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
                if ((bid == ChaosPersists.MyStrawberryPlant || bid == Blocks.POTATOES || bid == Blocks.CARROTS || bid == Blocks.GRASS_BLOCK || bid == Blocks.TALL_GRASS) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x - dx, y + i, z + j)).getBlock()) != ChaosPersists.MyStrawberryPlant && bid != Blocks.POTATOES && bid != Blocks.CARROTS && bid != Blocks.GRASS_BLOCK && bid != Blocks.TALL_GRASS || (d = dx * dx + j * j + i * i) >= this.closest) continue;
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
                if ((bid == ChaosPersists.MyStrawberryPlant || bid == Blocks.POTATOES || bid == Blocks.CARROTS || bid == Blocks.GRASS_BLOCK || bid == Blocks.TALL_GRASS) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y - dy, z + j)).getBlock()) != ChaosPersists.MyStrawberryPlant && bid != Blocks.POTATOES && bid != Blocks.CARROTS && bid != Blocks.GRASS_BLOCK && bid != Blocks.TALL_GRASS || (d = dy * dy + j * j + i * i) >= this.closest) continue;
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
                if ((bid == ChaosPersists.MyStrawberryPlant || bid == Blocks.POTATOES || bid == Blocks.CARROTS || bid == Blocks.GRASS_BLOCK || bid == Blocks.TALL_GRASS) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z - dz)).getBlock()) != ChaosPersists.MyStrawberryPlant && bid != Blocks.POTATOES && bid != Blocks.CARROTS && bid != Blocks.GRASS_BLOCK && bid != Blocks.TALL_GRASS || (d = dz * dz + j * j + i * i) >= this.closest) continue;
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

    protected void fall(float par1) {
        float i = (float)MathHelper.ceil((double)(par1 - 3.0f));
        if (i > 0.0f) {
            if (i > 3.0f) {
                this.playSound(SoundEvents.GENERIC_BIG_FALL, 1.0f, 1.0f);
            } else {
                this.playSound(SoundEvents.GENERIC_SMALL_FALL, 1.0f, 1.0f);
            }
            if (i > 2.0f) {
                i = 2.0f;
            }
            this.hurt(DamageSource.FALL, i);
        }
    }

    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (!this.isOrderedToSit()) {
            Gazelle buddy;
            if ((this.level.random.nextInt(30) == 0 && this.getGazelleHealth() < this.mygetMaxHealth() || this.level.random.nextInt(750) == 1) && ChaosPersists.PlayNicely == 0) {
                this.closest = 99999;
                this.tz = 0;
                this.ty = 0;
                this.tx = 0;
                for (int i = 1; i < 11; ++i) {
                    int j = i;
                    if (j > 2) {
                        j = 2;
                    }
                    if (this.scan_it((int)this.getX(), (int)this.getY() + 1, (int)this.getZ(), i, j, i)) break;
                    if (i < 6) continue;
                    ++i;
                }
                if (this.closest < 99999) {
                    this.getNavigation().moveTo((double)this.tx, (double)this.ty, (double)this.tz, 1.0);
                    if (this.closest < 12) {
                        if (this.level.getGameRules().getBoolean(net.minecraft.world.GameRules.RULE_MOBGRIEFING)) {
                            this.level.setBlock(new net.minecraft.util.math.BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                        }
                        this.heal(1.0f);
                        this.playSound(net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("minecraft", "entity.player.burp")), 1.0f, this.level.random.nextFloat() * 0.2f + 0.9f);
                    }
                }
            }
            if (this.level.random.nextInt(250) == 1 && (buddy = this.findBuddy()) != null) {
                this.getNavigation().moveTo(buddy.getX(), buddy.getY(), buddy.getZ(), 0.5);
            }
        }
        if (this.level.random.nextInt(250) == 0) {
            this.heal(1.0f);
        }
        super.customServerAiStep();
    }

    private Gazelle findBuddy() {
        List var5 = this.level.getEntitiesOfClass(Gazelle.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        Gazelle var4 = null;
        if (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (Gazelle)var3;
            return var4;
        }
        return null;
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    public int mygetMaxHealth() {
        return 15;
    }

    public int getGazelleHealth() {
        return (int)this.getHealth();
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        ActionResultType superResult = super.mobInteract(par1PlayerEntityEntity, hand);
        if (superResult.consumesAction()) {
            return superResult;
        }
        if (!var2.isEmpty() && var2.getItem() == Items.APPLE && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level.isClientSide) {
                    if (this.random.nextInt(2) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.heal((float)this.mygetMaxHealth() - this.getHealth());
                    } else {
                        this.level.broadcastEntityEvent(this, (byte)6);
                        this.level.broadcastEntityEvent(this, (byte)6);
                    }
                }
            } else if (this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)7);
                }
                if ((float)this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float)this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && !var2.isEmpty() && var2.getItem() == Item.byBlock((Block)Blocks.DEAD_BUSH) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.setTame(false);
                this.setOwnerUUID(null);
                this.level.broadcastEntityEvent(this, (byte)6);
                this.level.broadcastEntityEvent(this, (byte)6);
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && !var2.isEmpty() && var2.getItem() == Items.NAME_TAG && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            this.setCustomName(var2.getHoverName());
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        if (this.isTame() && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.isOrderedToSit()) {
                this.setOrderedToSit(true);
            } else {
                this.setOrderedToSit(false);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("chaospersists", "scorpion_hit"));
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("chaospersists", "cryo_death"));
    }

    protected float getSoundVolume() {
        return 0.4f;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = 0;
        if (this.isTame()) {
            var3 = this.random.nextInt(5);
            for (int var4 = 0; var4 < (var3 += 2); ++var4) {
                this.spawnAtLocation(Items.POPPY, 1);
            }
        } else {
            super.dropCustomDeathLoot(source, looting, recentlyHit);
        }
    }

    protected float getVoicePitch() {
        return this.isBaby() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.1f + 1.5f : (this.random.nextFloat() - this.random.nextFloat()) * 0.1f + 1.0f;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        float p2 = par2;
        if (this.isTame() && p2 > 10.0f) {
            p2 = 10.0f;
        }
        ret = super.hurt(par1DamageSource, p2);
        return ret;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.getY() > 100.0) {
            return false;
        }
        Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)this.getY() - 1, (int)this.getZ())).getBlock();
        if (bid != Blocks.DIRT && bid != Blocks.GRASS_BLOCK && bid != Blocks.GRASS_BLOCK) {
            return false;
        }
        return true;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }


    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.APPLE;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == ChaosPersists.MyCrystalApple;
    }
}

