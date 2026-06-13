/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.SeaMonster
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
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
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
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
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class SeaMonster
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(SeaMonster.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private float moveSpeed = 0.25f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public SeaMonster(EntityType<? extends SeaMonster> type, World par1World) {
        super(type, par1World);
        this.xpReward = 150;
                
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.addGoal(3, new LookAtGoal(this, LivingEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.SeaMonster_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.SeaMonster_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
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

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.SeaMonster_stats.health;
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

    public int getArmorValue() {
        return ChaosPersists.SeaMonster_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.moveSpeed = this.isInWater() ? 0.55f : 0.25f;
    }

    public int getSeaMonsterHealth() {
        return (int)this.getHealth();
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.random.nextInt(3) == 0) {
            return com.astryxion.chaospersists.core.ChaosSounds.SEAMONSTER_LIVING;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.SEAMONSTER_HIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.SEAMONSTER_DEATH;
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
        int var4;
        ItemStack is = null;
        this.dropItemRand(ChaosPersists.SeaMonsterScale, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var5 = 9 + this.level.random.nextInt(6);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.COD, 1);
        }
        var4 = this.level.random.nextInt(20);
        switch (var4) {
            case 1: {
                is = this.dropItemRand(Items.IRON_INGOT, 1);
                break;
            }
            case 3: {
                is = this.dropItemRand(Items.IRON_SWORD, 1);
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
                is = this.dropItemRand(Items.IRON_SHOVEL, 1);
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                break;
            }
            case 5: {
                is = this.dropItemRand(Items.IRON_PICKAXE, 1);
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
                is = this.dropItemRand(Items.IRON_AXE, 1);
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                break;
            }
            case 7: {
                is = this.dropItemRand(Items.IRON_HOE, 1);
                if (this.level.random.nextInt(2) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                }
                if (this.level.random.nextInt(6) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                break;
            }
            case 8: {
                is = this.dropItemRand((Item)Items.IRON_HELMET, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
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
                is = this.dropItemRand((Item)Items.IRON_CHESTPLATE, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                break;
            }
            case 10: {
                is = this.dropItemRand((Item)Items.IRON_LEGGINGS, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(3), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(1), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(4), 1 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                break;
            }
            case 11: {
                is = this.dropItemRand((Item)Items.IRON_BOOTS, 1);
                if (this.level.random.nextInt(6) == 1) {
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                }
                if (this.level.random.nextInt(2) != 1) break;
                com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                break;
            }
            case 13: {
                this.dropItemRand(Item.byBlock((Block)Blocks.IRON_BLOCK), 1);
                break;
            }
        }
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity != null && par1Entity instanceof LivingEntity) {
                double ks = 0.6;
                double inair = 0.1;
                float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                    inair *= 2.0;
                }
                par1Entity.setDeltaMovement(par1Entity.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
            }
            return true;
        }
        return false;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (this.hurt_timer <= 0) {
            ret = super.hurt(par1DamageSource, par2);
            this.hurt_timer = 8;
        }
        if (e != null && e instanceof MobEntity) {
            if (e instanceof SeaMonster) {
                return false;
            }
            this.setTarget((LivingEntity)((MobEntity)e));
            this.getNavigation().moveTo((Entity)((MobEntity)e), 1.2);
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
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (!this.isInWater() && this.level.random.nextInt(25) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 10) {
                    j = 10;
                }
                if (this.scan_it((int)this.getX(), (int)this.getY() - 1, (int)this.getZ(), i, j, i)) break;
                if (i < 5) continue;
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
            } else {
                if (this.level.random.nextInt(40) == 1) {
                    this.heal(-1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.remove();
                    return;
                }
            }
        }
        if (this.level.random.nextInt(5) == 1) {
            LivingEntity e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) < (double)((4.0f + e.getBbWidth() / 2.0f) * (4.0f + e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(4) == 0 || this.level.random.nextInt(5) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                    }
                } else {
                    this.getNavigation().moveTo((Entity)e, 1.0);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.level.random.nextInt(120) == 1 && this.isInWater() && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.playSound(net.minecraft.util.SoundEvents.GENERIC_SPLASH, 1.5f, this.level.random.nextFloat() * 0.2f + 0.9f);
            this.heal(1.0f);
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
        if (par1Mob instanceof SeaMonster) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (MyUtils.isAttackableNonMob((LivingEntity)par1Mob)) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 4.0, 16.0));
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

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        SeaMonster target = null;
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
                    if (s == null || !s.equals("Sea Monster")) continue;
                    return true;
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.level.isDay()) {
            return false;
        }
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        target = this.level.getNearestEntity(SeaMonster.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(16.0, 5.0, 16.0));
        if (target != null) {
            return false;
        }
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }
}

