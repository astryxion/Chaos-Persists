/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.AttackSquid
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Ghost
 *  com.astryxion.chaospersists.GhostSkelly
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.InkSack
 *  com.astryxion.chaospersists.Lizard
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.WaterBall
 *  com.astryxion.chaospersists.WaterDragon
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.CaveSpiderEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.monster.Spider
 *  net.minecraft.entity.monster.ZombieEntity
 *  net.minecraft.entity.passive.VillagerEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Ghost;
import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.item.InkSack;
import com.astryxion.chaospersists.entity.Lizard;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.WaterBall;
import com.astryxion.chaospersists.entity.WaterDragon;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/*
 * Exception performing whole class analysis ignored.
 */
public class AttackSquid
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(AttackSquid.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private LivingEntity buddy = null;
    private float moveSpeed = 0.25f;
    private int wasshot = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public AttackSquid(EntityType<? extends AttackSquid> type, World par1World) {
        super(type, par1World);
        this.xpReward = 15;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean fireImmune() {
        return false;
    }

        public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.AttackSquid_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.AttackSquid_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public void setWasShot() {
        this.wasshot = 250;
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.AttackSquid_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.AttackSquid_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    public int getAttackStrength(Entity par1Entity) {
        int var2 = 2;
        return var2;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.SQUID_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.SQUID_DEATH;
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.util.ResourceLocation res = par1.contains(":")
                ? new net.minecraft.util.ResourceLocation(par1)
                : new net.minecraft.util.ResourceLocation("chaospersists", par1.toLowerCase().replace(" ", "_"));
        net.minecraft.entity.EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(res);
        if (entityType != null) {
            var8 = entityType.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            if (var8 instanceof MobEntity) {
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
            }
        }
        return var8;
    }

    protected float getSoundVolume() {
        return 1.0f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.COD;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        ItemStack is = null;
        int var4 = this.level.random.nextInt(50);
        switch (var4) {
            case 0: {
                is = this.dropItemRand(Items.GOLD_NUGGET, 1);
                break;
            }
            case 1: {
                is = this.dropItemRand(Items.GOLD_INGOT, 1);
                break;
            }
            case 2: {
                is = this.dropItemRand(Items.GOLDEN_CARROT, 1);
                break;
            }
            case 3: {
                is = this.dropItemRand(Items.GOLDEN_SWORD, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(18), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(19), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(21), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(20), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                break;
            }
            case 4: {
                is = this.dropItemRand(Items.GOLDEN_SHOVEL, 1);
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                break;
            }
            case 5: {
                is = this.dropItemRand(Items.GOLDEN_PICKAXE, 1);
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                break;
            }
            case 6: {
                is = this.dropItemRand(Items.GOLDEN_AXE, 1);
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                break;
            }
            case 7: {
                is = this.dropItemRand(Items.GOLDEN_HOE, 1);
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                break;
            }
            case 8: {
                is = this.dropItemRand((Item)Items.GOLDEN_HELMET, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(2));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                break;
            }
            case 9: {
                is = this.dropItemRand((Item)Items.GOLDEN_CHESTPLATE, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                break;
            }
            case 10: {
                is = this.dropItemRand((Item)Items.GOLDEN_LEGGINGS, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                break;
            }
            case 11: {
                is = this.dropItemRand((Item)Items.GOLDEN_BOOTS, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                break;
            }
            case 12: {
                this.dropItemRand(Items.GOLDEN_APPLE, 1);
                break;
            }
            case 13: {
                this.dropItemRand(Blocks.GOLD_BLOCK.asItem(), 1);
                break;
            }
            case 14: {
                ItemEntity var3 = null;
                is = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1);
                var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), is);
                if (var3 == null) break;
                this.level.addFreshEntity((Entity)var3);
                break;
            }
            case 15: 
            case 16: 
            case 17: {
                this.dropItemRand(Items.INK_SAC, 1);
                break;
            }
        }
        int i = 1 + this.level.random.nextInt(3);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.COD, 1);
        }
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        double ks = 1.8;
        double inair = 0.2;
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity != null) {
                float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!par1Entity.isAlive() || par1Entity instanceof PlayerEntity) {
                    inair *= 2.0;
                }
                par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean causeFallDamage(float par1, float damageMultiplier) {
        if (this.wasshot != 0) {
            return false;
        }
        return super.causeFallDamage(par1, damageMultiplier);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (!this.isAlive()) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof AttackSquid) {
            return false;
        }
        if (e != null && e instanceof WaterBall) {
            return false;
        }
        if (e != null && e instanceof WaterDragon) {
            return false;
        }
        if (e != null && e instanceof MobEntity) {
            if (e instanceof AttackSquid) {
                return false;
            }
            if (e instanceof WaterDragon) {
                return false;
            }
            this.setTarget((LivingEntity)((MobEntity)e));
            this.setTarget((LivingEntity)e);
            this.getNavigation().moveTo((Entity)((MobEntity)e), 1.2);
            ret = true;
        }
        ret = super.hurt(par1DamageSource, par2);
        if ((this.getHealth() <= 0.0f || this.removed) && com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level) != ChaosPersists.getDimension(5) && !this.level.isClientSide && e != null && e instanceof PlayerEntity && this.level.random.nextInt(15) == 1 && ChaosPersists.KrakenEnable != 0 && this.wasshot == 0) {
            int j = 1 + this.level.random.nextInt(3);
            for (int i = 0; i < j; ++i) {
                CreatureEntity newent = (CreatureEntity)AttackSquid.spawnCreature((World)this.level, (String)"chaospersists:the_kraken", (double)(this.getX() + (double)this.level.random.nextInt(4) - (double)this.level.random.nextInt(4)), (double)170.0, (double)(this.getZ() + (double)this.level.random.nextInt(4) - (double)this.level.random.nextInt(4)));
            }
        }
        return ret;
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
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.wasshot > 0) {
            --this.wasshot;
            if (this.wasshot == 0) {
                this.remove();
                return;
            }
        }
        if (!this.isInWater() && this.level.random.nextInt(10) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 5) {
                    j = 5;
                }
                if (this.scan_it((int)this.getX(), (int)this.getY() - 1, (int)this.getZ(), i, j, i)) break;
                if (i < 5) continue;
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
            } else {
                if (this.level.random.nextInt(25) == 1) {
                    this.heal(-1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.remove();
                    return;
                }
            }
        }
        if (this.level.random.nextInt(10) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr((Entity)e) < 9.0) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(4) == 0 || this.level.random.nextInt(5) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                    }
                } else {
                    this.getNavigation().moveTo((Entity)e, 1.2);
                    this.watercanon(e);
                }
            } else {
                if (this.buddy != null) {
                    this.getNavigation().moveTo((Entity)this.buddy, 1.0);
                }
                this.setAttacking(0);
            }
        }
    }

    private void watercanon(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 1.2;
        if (this.level.random.nextInt(5) == 1) {
            if (this.random.nextInt(3) == 1) {
                InkSack var2 = new InkSack(this.level);
                var2.moveTo(this.getX() - xzoff * Math.sin(Math.toRadians(this.yHeadRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot)), this.yHeadRot, this.xRot);
                double var3 = e.getX() - this.getX();
                double var5 = e.getY() + 0.25 - var2.getY();
                double var7 = e.getZ() - this.getZ();
                float var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
                var2.shoot(var3, var5 + (double)var9, var7, 1.4f, 5.0f);
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, net.minecraft.util.SoundCategory.NEUTRAL, 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level.addFreshEntity((Entity)var2);
            } else {
                WaterBall var2 = new WaterBall(this.level);
                var2.moveTo(this.getX() - xzoff * Math.sin(Math.toRadians(this.yHeadRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot)), this.yHeadRot, this.xRot);
                double var3 = e.getX() - this.getX();
                double var5 = e.getY() + 0.25 - var2.getY();
                double var7 = e.getZ() - this.getZ();
                float var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
                var2.shoot(var3, var5 + (double)var9, var7, 1.4f, 5.0f);
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, net.minecraft.util.SoundCategory.NEUTRAL, 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level.addFreshEntity((Entity)var2);
            }
        }
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
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof Girlfriend) {
            return true;
        }
        if (par1Mob instanceof Boyfriend) {
            return true;
        }
        if (par1Mob instanceof ZombieEntity) {
            return true;
        }
        if (par1Mob instanceof VillagerEntity) {
            return true;
        }
        if (par1Mob instanceof SpiderEntity) {
            return true;
        }
        if (par1Mob instanceof CaveSpiderEntity) {
            return true;
        }
        if (par1Mob instanceof Ghost) {
            return false;
        }
        if (par1Mob instanceof GhostSkelly) {
            return false;
        }
        if (par1Mob instanceof Lizard) {
            return true;
        }
        if (par1Mob instanceof AttackSquid) {
            if (this.level.random.nextInt(5) == 1) {
                this.buddy = par1Mob;
            }
            return false;
        }
        if (this.wasshot != 0) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10.0, 4.0, 10.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false)) continue;
            return var4;
        }
        return null;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("WasShot", this.wasshot);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.wasshot = par1CompoundNBT.getInt("WasShot");
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.level.isNight()) {
            return false;
        }
        return true;
    }
}

