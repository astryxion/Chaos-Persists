/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.AttackSquid
 *  com.astryxion.chaospersists.Cephadrome
 *  com.astryxion.chaospersists.Chipmunk
 *  com.astryxion.chaospersists.Dragon
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.Leon
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Spyro
 *  com.astryxion.chaospersists.StinkBug
 *  com.astryxion.chaospersists.ThePrinceAdult
 *  com.astryxion.chaospersists.ThePrinceTeen
 *  net.minecraft.block.Block
 *  net.minecraft.block.TallGrassBlock
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
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.effect.LightningBoltEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.ChickenEntity
 *  net.minecraft.entity.passive.SquidEntity
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
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldInfo
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.StinkBug;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.TallGrassBlock;
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
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.SquidEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.EntityType;

/*
 * Exception performing whole class analysis ignored.
 */
public class Kraken
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Kraken.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> PLAY_NICELY = EntityDataManager.defineId(Kraken.class, DataSerializers.INT);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private BlockPos currentFlightTarget = null;
    private LivingEntity caught = null;
    private int newtarget = 0;
    private int release = 0;
    private int weather_set = 10;
    private int long_enough = 3600;
    private int call_reinforcements = 0;
    private boolean hit_by_player = false;
    private int straight_down = 1;
    private int hurt_timer = 0;

    public Kraken(EntityType<? extends Kraken> type, World par1World) {
        super(type, par1World);
        if (ChaosPersists.PlayNicely == 0) {
        } else {
        }
        this.xpReward = 500;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Kraken_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3700000047683716)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Kraken_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
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

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY).intValue();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Kraken_stats.health;
    }

    public int getKrakenHealth() {
        return (int)this.getHealth();
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
        return ChaosPersists.Kraken_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.util.ResourceLocation res = new net.minecraft.util.ResourceLocation("chaospersists", (String)par1);
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

    public void tick() {
        super.tick();
        if (!this.isAlive()) {
            return;
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)(this.getY() - 10.0), (int)this.getZ());
        } else {
            double my = this.getDeltaMovement().y;
            if (this.getY() < (double)this.currentFlightTarget.getY()) {
                my *= 0.72;
            } else {
                my *= 0.5;
            }
            this.setDeltaMovement(this.getDeltaMovement().x, my, this.getDeltaMovement().z);
        }
        if (this.weather_set > 0 && ChaosPersists.PlayNicely == 0) {
            --this.weather_set;
            if (this.weather_set == 0 && !this.level.isClientSide && this.level instanceof ServerWorld) {
                ServerWorld sw = (ServerWorld)this.level;
                if (!this.level.isRaining()) {
                    sw.setWeatherParameters(0, 300, true, true);
                } else {
                    sw.setWeatherParameters(0, 300, true, this.level.isThundering());
                }
                this.weather_set = 100;
            }
        }
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("LongEnough", this.long_enough);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.long_enough = par1CompoundNBT.getInt("LongEnough");
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.random.nextInt(5) == 0) {
            return com.astryxion.chaospersists.core.ChaosSounds.KRAKEN_LIVING;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return null;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_DEATH;
    }

    protected float getSoundVolume() {
        return 2.0f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.QUARTZ;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(8) - (double)ChaosPersists.ChaosRand.nextInt(8), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(8) - (double)ChaosPersists.ChaosRand.nextInt(8), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        ItemStack is = null;
        this.dropItemRand(ChaosPersists.MyKrakenTooth, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var5 = 120 + this.level.random.nextInt(160);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.INK_SAC, 1);
        }
        int i = 5 + this.level.random.nextInt(10);
        block56 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(53);
            switch (var3) {
                case 0: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateSword, 1);
                    continue block56;
                }
                case 1: {
                    is = this.dropItemRand(Items.DIAMOND, 1);
                    continue block56;
                }
                case 2: {
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.DIAMOND_BLOCK), 1);
                    continue block56;
                }
                case 3: {
                    is = this.dropItemRand(Items.DIAMOND_SWORD, 1);
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
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 4: {
                    is = this.dropItemRand(Items.DIAMOND_SHOVEL, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 5: {
                    is = this.dropItemRand(Items.DIAMOND_PICKAXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 6: {
                    is = this.dropItemRand(Items.DIAMOND_AXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 7: {
                    is = this.dropItemRand(Items.DIAMOND_HOE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 8: {
                    is = this.dropItemRand((Item)Items.DIAMOND_HELMET, 1);
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
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 9: {
                    is = this.dropItemRand((Item)Items.DIAMOND_CHESTPLATE, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 10: {
                    is = this.dropItemRand((Item)Items.DIAMOND_LEGGINGS, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 11: {
                    is = this.dropItemRand((Item)Items.DIAMOND_BOOTS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 12: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateBow, 1);
                    continue block56;
                }
                case 13: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateAxe, 1);
                    continue block56;
                }
                case 14: {
                    is = this.dropItemRand(Items.IRON_INGOT, 1);
                    continue block56;
                }
                case 15: {
                    is = this.dropItemRand(ChaosPersists.MyUltimatePickaxe, 1);
                    continue block56;
                }
                case 16: {
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
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 17: {
                    is = this.dropItemRand(Items.IRON_SHOVEL, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 18: {
                    is = this.dropItemRand(Items.IRON_PICKAXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 19: {
                    is = this.dropItemRand(Items.IRON_AXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 20: {
                    is = this.dropItemRand(Items.IRON_HOE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 21: {
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
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 22: {
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 23: {
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 24: {
                    is = this.dropItemRand((Item)Items.IRON_BOOTS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 25: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateShovel, 1);
                    continue block56;
                }
                case 26: {
                    this.dropItemRand(Item.byBlock((Block)Blocks.IRON_BLOCK), 1);
                    continue block56;
                }
                case 27: {
                    is = this.dropItemRand(Items.GOLD_NUGGET, 1);
                    continue block56;
                }
                case 28: {
                    is = this.dropItemRand(Items.GOLD_INGOT, 1);
                    continue block56;
                }
                case 29: {
                    is = this.dropItemRand(Items.GOLDEN_CARROT, 1);
                    continue block56;
                }
                case 30: {
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
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 31: {
                    is = this.dropItemRand(Items.GOLDEN_SHOVEL, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 32: {
                    is = this.dropItemRand(Items.GOLDEN_PICKAXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 33: {
                    is = this.dropItemRand(Items.GOLDEN_AXE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 34: {
                    is = this.dropItemRand(Items.GOLDEN_HOE, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 35: {
                    is = this.dropItemRand((Item)Items.GOLDEN_HELMET, 1);
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
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 36: {
                    is = this.dropItemRand((Item)Items.GOLDEN_CHESTPLATE, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 37: {
                    is = this.dropItemRand((Item)Items.GOLDEN_LEGGINGS, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 38: {
                    is = this.dropItemRand((Item)Items.GOLDEN_BOOTS, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 39: {
                    this.dropItemRand(Items.GOLDEN_APPLE, 1);
                    continue block56;
                }
                case 40: {
                    this.dropItemRand(Blocks.GOLD_BLOCK.asItem(), 1);
                    continue block56;
                }
                case 41: {
                    ItemEntity var33 = null;
                    is = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1);
                    var33 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(3) - (double)ChaosPersists.ChaosRand.nextInt(3), is);
                    if (var33 == null) continue block56;
                    this.level.addFreshEntity((Entity)var33);
                    continue block56;
                }
                case 42: {
                    is = this.dropItemRand(ChaosPersists.MyExperienceSword, 1);
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
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 43: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceHelmet, 1);
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
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(5), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 44: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceBody, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 45: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceLegs, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 46: {
                    is = this.dropItemRand((Item)ChaosPersists.ExperienceBoots, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block56;
                }
                case 47: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystSword, 1);
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
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 48: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystShovel, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 49: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystPickaxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 50: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystAxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 51: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystHoe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block56;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block56;
                }
                case 52: {
                    is = this.dropItemRand(Item.byBlock((Block)ChaosPersists.MyBlockAmethystBlock), 1);
                    break;
                }
            }
        }
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.long_enough <= 0) {
            return true;
        }
        if (this.getY() > 150.0 && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) {
            return true;
        }
        if (this.getY() > 180.0 && this.long_enough <= 0) {
            this.remove();
            return true;
        }
        return false;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d((double)this.getX(), (double)(this.getY() + 0.75), (double)this.getZ()), new Vector3d((double)pX, (double)pY, (double)pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    protected void customServerAiStep() {
        int i;
        Block bid;
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.long_enough > 0) {
            --this.long_enough;
        }
        this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
        if (this.level.random.nextInt(400) == 1 && ChaosPersists.PlayNicely == 0) {
            LightningBoltEntity bolt = EntityType.LIGHTNING_BOLT.create(this.level);
            if (bolt != null) {
                bolt.moveTo(this.getX(), this.getY() - 16.0, this.getZ(), 0.0f, 0.0f);
                this.level.addFreshEntity(bolt);
            }
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.newtarget != 0 || this.random.nextInt(250) == 1 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 9.1) {
            int ground_dist;
            this.newtarget = 0;
            for (ground_dist = 0; ground_dist < 31; ++ground_dist) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)this.getY() - ground_dist, (int)this.getZ())).getBlock();
                if (bid == Blocks.AIR) continue;
                this.straight_down = 0;
                break;
            }
            ground_dist = 20 - ground_dist;
            bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.level.random.nextInt(6) + 12;
                xdir = this.level.random.nextInt(6) + 12;
                if (this.level.random.nextInt(2) == 0) {
                    zdir = - zdir;
                }
                if (this.level.random.nextInt(2) == 0) {
                    xdir = - xdir;
                }
                if (this.straight_down != 0) {
                    xdir = 0;
                    zdir = 0;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)this.getX() + xdir, (int)this.getY() + ground_dist + this.random.nextInt(9) - 6, (int)this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
                if (this.long_enough <= 0 || this.getY() < 200.0 && this.getHealth() < (float)(this.mygetMaxHealth() / 4)) {
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos(this.currentFlightTarget.getX(), this.currentFlightTarget.getY() + 30, this.currentFlightTarget.getZ());
                if (this.hit_by_player && this.call_reinforcements == 0 && this.getHealth() < (float)(this.mygetMaxHealth() / 8) && this.getY() > 130.0) {
                    this.call_reinforcements = 1;
                    for (i = 0; i < 10; ++i) {
                        CreatureEntity newent = (CreatureEntity)Kraken.spawnCreature((World)this.level, (String)"The Kraken", (double)(this.getX() + (double)this.level.random.nextInt(10) - (double)this.level.random.nextInt(10)), (double)170.0, (double)(this.getZ() + (double)this.level.random.nextInt(10) - (double)this.level.random.nextInt(10)));
                    }
                }
            }
        } else if (this.caught == null && this.level.random.nextInt(8) == 1 && ChaosPersists.PlayNicely == 0) {
            PlayerEntity target = null;
            target = this.level.getNearestPlayer(this, 25.0D);
            if (target != null) {
                if (!target.isCreative()) {
                    if (this.getSensing().canSee((Entity)target)) {
                        this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)target.getX(), (int)target.getY() + 15, (int)target.getZ());
                        this.attackWithSomething((LivingEntity)target);
                    }
                } else {
                    target = null;
                }
            }
            if (target == null && this.level.random.nextInt(2) == 0) {
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
                    this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)e.getY() + 15, (int)e.getZ());
                    this.attackWithSomething(e);
                }
            }
        }
        if (this.caught != null) {
            if (!this.caught.removed) {
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)this.getX(), 200, (int)this.getZ());
                if (this.getY() > 190.0) {
                    this.release = 1;
                }
                this.caught.setDeltaMovement(this.getDeltaMovement().x, this.caught.getDeltaMovement().y, this.caught.getDeltaMovement().z);
                this.caught.setDeltaMovement(this.caught.getDeltaMovement().x, this.caught.getDeltaMovement().y, this.getDeltaMovement().z);
                this.caught.setDeltaMovement(this.caught.getDeltaMovement().x, this.getDeltaMovement().y, this.caught.getDeltaMovement().z);
                this.caught.setPos(this.getX(), this.caught.getY(), this.caught.getZ());
                if (this.getY() - this.caught.getY() > 16.0) {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this.caught, 0.0, 0.25, 0.0);
                }
                this.caught.setPos(this.getX(), this.getY() - 15.0, this.getZ());
                this.caught.yRot = this.yRot;
                if (this.level.random.nextInt(50) == 1) {
                    this.doHurtTarget(this.caught);
                }
                if (this.release != 0 || this.level.random.nextInt(250) == 1) {
                    this.caught = null;
                    this.newtarget = 1;
                    this.release = 0;
                    this.setAttacking(0);
                }
            } else {
                this.caught = null;
                this.newtarget = 1;
                this.release = 0;
                this.setAttacking(0);
            }
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.3 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.3 - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.45 - this.getDeltaMovement().x) * 0.15, (Math.signum(var3) * 0.70999 - this.getDeltaMovement().y) * 0.202, (Math.signum(var5) * 0.45 - this.getDeltaMovement().z) * 0.15);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = 0.4f;
        if (Math.abs(this.getDeltaMovement().x) + Math.abs(this.getDeltaMovement().z) < 0.15) {
            var8 = 0.0f;
        }
        this.yRot += var8 / 5.0f;
        double obstruction_factor = 0.0;
        double dx = 0.0;
        double dz = 0.0;
        int dist = 10;
        for (int k = -20; k < 18; k += 2) {
            for (i = 1; i < dist; i += 2) {
                dx = (double)i * Math.cos(Math.toRadians(this.yRot + 90.0f));
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() + k, (int)(this.getZ() + (dz = (double)i * Math.sin(Math.toRadians(this.yRot + 90.0f)))))).getBlock();
                if (bid == Blocks.AIR) continue;
                obstruction_factor += 0.1;
            }
        }
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.08, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.08);
        if (this.getY() > 256.0 && !this.isPersistenceRequired()) {
            this.remove();
        }
    }

    private void attackWithSomething(LivingEntity par1) {
        if (this.caught != null) {
            return;
        }
        double dist = (this.getX() - par1.getX()) * (this.getX() - par1.getX());
        dist += (this.getZ() - par1.getZ()) * (this.getZ() - par1.getZ());
        if ((dist += (this.getY() - par1.getY() - 15.0) * (this.getY() - par1.getY() - 15.0)) < 30.0) {
            this.caught = par1;
            this.release = 0;
            this.setAttacking(1);
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
            if (p.abilities.flying) {
                return false;
            }
            return true;
        }
        if (!par1Mob.isOnGround() && !par1Mob.isInWater()) {
            return false;
        }
        if (par1Mob instanceof SquidEntity) {
            return false;
        }
        if (par1Mob instanceof AttackSquid) {
            return false;
        }
        if (par1Mob instanceof Kraken) {
            return false;
        }
        if (par1Mob instanceof Spyro) {
            return false;
        }
        if (par1Mob instanceof Dragon) {
            Dragon c = (Dragon)par1Mob;
            if (c.getControllingPassenger() != null) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof Cephadrome) {
            Cephadrome c = (Cephadrome)par1Mob;
            if (c.getControllingPassenger() != null) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof Leon) {
            Leon c = (Leon)par1Mob;
            if (c.getControllingPassenger() != null) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof ThePrinceTeen) {
            ThePrinceTeen c = (ThePrinceTeen)par1Mob;
            if (c.getControllingPassenger() != null) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof ThePrinceAdult) {
            ThePrinceAdult c = (ThePrinceAdult)par1Mob;
            if (c.getControllingPassenger() != null) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof ChickenEntity) {
            return false;
        }
        if (par1Mob instanceof Chipmunk) {
            return false;
        }
        if (par1Mob instanceof StinkBug) {
            return false;
        }
        if (par1Mob instanceof Mothra) {
            return false;
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0, 40.0, 20.0));
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

    public void onStruckByLightning(LightningBoltEntity par1LightningBoltEntity) {
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        boolean ret = false;
        if (this.currentFlightTarget != null && e != null && e instanceof PlayerEntity && this.getHealth() > (float)(this.mygetMaxHealth() / 4)) {
            this.hit_by_player = true;
            this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)e.getX(), (int)e.getY() + 15, (int)e.getZ());
        }
        if (this.hurt_timer > 0) {
            return false;
        }
        this.hurt_timer = 30;
        ret = super.hurt(par1DamageSource, par2);
        if (this.level.random.nextInt(2) == 1) {
            this.release = 1;
        }
        return ret;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 6; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR || bid == Blocks.GRASS_BLOCK) continue;
                    return false;
                }
            }
        }
        return true;
    }
}

