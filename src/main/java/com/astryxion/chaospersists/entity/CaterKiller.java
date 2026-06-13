/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CaterKiller
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.LeavesBlock
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
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.util.SoundEvents;
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
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

/*
 * Exception performing whole class analysis ignored.
 */
public class CaterKiller
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(CaterKiller.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> PLAY_NICELY = EntityDataManager.defineId(CaterKiller.class, DataSerializers.INT);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.35f;
    int foundmob = 0;
    int ticker = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;
    private boolean isInWeb = false;

    public CaterKiller(EntityType<? extends CaterKiller> type, World par1World) {
        super(type, par1World);
        if (ChaosPersists.PlayNicely == 0) {
        } else {
        }
                this.xpReward = 200;
                this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal((CreatureEntity)(Object)this, 1.0, false, 32, () -> true));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

        public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.CaterKiller_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.CaterKiller_stats.attack)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
    }

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY).intValue();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e = null;
        boolean ret = super.hurt(par1DamageSource, par2);
        e = par1DamageSource.getEntity();
        if (e != null && e instanceof MobEntity) {
            this.setTarget((LivingEntity)((MobEntity)e));
        }
        return ret;
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.CaterKiller_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.CaterKiller_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    protected SoundEvent getAmbientSound() {
        if (this.random.nextInt(3) == 0) {
            return net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("chaospersists", "caterkiller_living"));
        }
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ChaosSounds.CATERKILLER_HIT;
    }

    protected SoundEvent getDeathSound() {
        return ChaosSounds.CATERKILLER_DEATH;
    }

    protected float getSoundVolume() {
        return 1.5f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        ItemStack is = null;
        this.dropItemRand(ChaosPersists.CaterKillerJaw, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        for (var4 = 0; var4 < 10; ++var4) {
            this.dropItemRand(Items.LEATHER, 1);
        }
        for (var4 = 0; var4 < 6; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
        int i = 1 + this.level.random.nextInt(5);
        block17 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(20);
            switch (var3) {
                case 0: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateSword, 1);
                    continue block17;
                }
                case 1: {
                    is = this.dropItemRand(ChaosPersists.MyRuby, 1);
                    continue block17;
                }
                case 2: {
                    is = this.dropItemRand(Blocks.DIAMOND_BLOCK.asItem(), 1);
                    continue block17;
                }
                case 3: {
                    is = this.dropItemRand(ChaosPersists.MyRubySword, 1);
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
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(16), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 4: {
                    is = this.dropItemRand(ChaosPersists.MyRubyShovel, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 5: {
                    is = this.dropItemRand(ChaosPersists.MyRubyPickaxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 6: {
                    is = this.dropItemRand(ChaosPersists.MyRubyAxe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 7: {
                    is = this.dropItemRand(ChaosPersists.MyRubyHoe, 1);
                    if (this.level.random.nextInt(2) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    }
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 8: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyHelmet, 1);
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
                    if (this.level.random.nextInt(6) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(6), 1 + this.level.random.nextInt(5));
                    continue block17;
                }
                case 9: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyBody, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block17;
                }
                case 10: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyLegs, 1);
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
                    if (this.level.random.nextInt(2) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block17;
                }
                case 11: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyBoots, 1);
                    if (this.level.random.nextInt(6) == 1) {
                        com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), 5 + this.level.random.nextInt(5));
                    }
                    if (this.level.random.nextInt(2) != 1) continue block17;
                    com.astryxion.chaospersists.core.ChaosPersists.enchantItemStack(is, com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(34), 2 + this.level.random.nextInt(4));
                    continue block17;
                }
                case 12: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateBow, 1);
                    break;
                }
            }
        }
        for (var4 = 0; var4 < 25; ++var4) {
            CaterKiller.spawnCreature((World)this.level, (String)"Butterfly", (double)this.getX(), (double)(this.getY() + 1.0), (double)this.getZ());
        }
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = com.astryxion.chaospersists.util.EntitySpawnHelper.spawn(par0World, par1, par2, par4, par6);
        if (var8 instanceof MobEntity) {
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
        }
        return var8;
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity != null && par1Entity instanceof LivingEntity) {
                double ks = 1.2;
                double inair = 0.1;
                float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                    inair *= 2.0;
                }
                par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    private boolean isEdibleTreeBlock(Block bid) {
        return bid == Blocks.VINE || bid.is(BlockTags.LOGS) || bid instanceof LeavesBlock || bid == ChaosPersists.MyDT || bid == ChaosPersists.MyAppleLeaves || bid == ChaosPersists.MyExperienceLeaves || bid == ChaosPersists.MyScaryLeaves || bid == ChaosPersists.MyPeachLeaves || bid == ChaosPersists.MyCherryLeaves;
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
                if (this.isEdibleTreeBlock(bid) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if (!this.isEdibleTreeBlock((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x - dx, y + i, z + j)).getBlock())) || (d = dx * dx + j * j + i * i) >= this.closest) continue;
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
                if (this.isEdibleTreeBlock(bid) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if (!this.isEdibleTreeBlock((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y - dy, z + j)).getBlock())) || (d = dy * dy + j * j + i * i) >= this.closest) continue;
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
                if (this.isEdibleTreeBlock(bid) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if (!this.isEdibleTreeBlock((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z - dz)).getBlock())) || (d = dz * dz + j * j + i * i) >= this.closest) continue;
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
        int i;
        int j;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
        if (this.getHealth() + 1.0f < this.getMaxHealth()) {
            ++this.ticker;
            if (this.ticker > 2400) {
                CaterKiller.spawnCreature((World)this.level, (String)"Brutalfly", (double)this.getX(), (double)(this.getY() + 4.0), (double)this.getZ());
                this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0f, this.level.random.nextFloat() * 0.2f + 0.9f);
                for (int i2 = 0; i2 < 10; ++i2) {
                    CaterKiller.spawnCreature((World)this.level, (String)"Butterfly", (double)this.getX(), (double)(this.getY() + 1.0 + (double)this.level.random.nextInt(4)), (double)this.getZ());
                }
                this.remove();
                return;
            }
        }
        this.isInWeb = this.level.getBlockState(this.blockPosition()).getBlock() == Blocks.COBWEB;
        if (this.isInWeb) {
            for (i = -2; i <= 2; ++i) {
                for (j = -1; j < 5; ++j) {
                    for (int k = -2; k <= 2; ++k) {
                        if (this.level.getBlockState(new BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k)).getBlock() != Blocks.COBWEB) continue;
                        this.level.setBlock(new BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
            this.isInWeb = false;
        }
        if (this.level.random.nextInt(4) == 0) {
            LivingEntity e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (this.level.random.nextInt(200) == 0) {
                this.setTarget(null);
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                this.foundmob = 1;
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) < (double)((5.0f + e.getBbWidth() / 2.0f) * (5.0f + e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(3) == 0 || this.level.random.nextInt(4) == 1) {
                        this.doHurtTarget((Entity)e);
                    }
                } else {
                    this.setAttacking(0);
                    this.getNavigation().moveTo((Entity)e, 1.25);
                    if (this.level.random.nextInt(4) == 0) {
                        double dx = e.getX();
                        double dz = e.getZ();
                        dx += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 2.0;
                        dz += (double)(this.level.random.nextFloat() - this.level.random.nextFloat()) * 2.0;
                        for (i = 2; i > -2; --i) {
                            if (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx, (int)e.getY() + i + 1, (int)dz)).getBlock() != Blocks.AIR || this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)dx, (int)e.getY() + i, (int)dz)).getBlock() == Blocks.AIR) continue;
                            this.level.setBlock(new BlockPos((int)dx, (int)e.getY() + i + 1, (int)dz), Blocks.COBWEB.defaultBlockState(), 3);
                            break;
                        }
                    }
                }
            } else {
                this.setAttacking(0);
                this.foundmob = 0;
            }
        }
        if ((this.level.random.nextInt(8) == 0 && this.getHealth() < (float)this.mygetMaxHealth() || this.level.random.nextInt(30) == 0) && ChaosPersists.PlayNicely == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (i = 1; i < 13; ++i) {
                j = i;
                if (j > 9) {
                    j = 9;
                }
                if (this.scan_it((int)this.getX(), (int)this.getY() + 1, (int)this.getZ(), i, j, i)) break;
                if (i < 9) continue;
                ++i;
            }
            if (this.closest < 99999) {
                if (this.foundmob == 0) {
                    this.getNavigation().moveTo((double)this.tx, (double)this.ty, (double)this.tz, 1.0);
                }
                if (this.closest < 81) {
                    if (this.level.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).get()) {
                        this.level.setBlock(new BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                    }
                    this.heal(2.0f);
                    if (this.level.random.nextInt(20) == 1) {
                        this.playSound(SoundEvents.PLAYER_BURP, 1.0f, this.level.random.nextFloat() * 0.2f + 0.9f);
                    }
                }
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
        if (!this.MyCanSee(par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof CaterKiller) {
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
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0, 8.0, 20.0));
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

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        Block bid;
        int j;
        int i;
        int k;
        for (k = -3; k < 3; ++k) {
            for (j = -3; j < 3; ++j) {
                for (i = 0; i < 5; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileentitymobspawner = null;
                    tileentitymobspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("CaterKiller")) continue;
                    return true;
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.level.random.nextInt(10) != 0) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        for (k = -1; k < 2; ++k) {
            for (j = -1; j < 2; ++j) {
                for (i = 1; i < 5; ++i) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid == Blocks.AIR || bid instanceof LeavesBlock || bid.is(BlockTags.LOGS)) continue;
                    return false;
                }
            }
        }
        if (!this.level.getEntitiesOfClass(CaterKiller.class, this.getBoundingBox().inflate(48.0, 16.0, 48.0), e -> e != this).isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean MyCanSee(LivingEntity e) {
        double xzoff = 2.5;
        int nblks = 10;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
        float startx = (float)cx;
        float starty = (float)(this.getY() + 3.0);
        float startz = (float)cz;
        float dx = (float)((e.getX() - (double)startx) / 10.0);
        float dy = (float)((e.getY() + (double)(e.getBbHeight() / 2.0f) - (double)starty) / 10.0);
        float dz = (float)((e.getZ() - (double)startz) / 10.0);
        if ((double)Math.abs(dx) > 1.0) {
            dy /= Math.abs(dx);
            dz /= Math.abs(dx);
            nblks = (int)((float)nblks * Math.abs(dx));
            if (dx > 1.0f) {
                dx = 1.0f;
            }
            if (dx < -1.0f) {
                dx = -1.0f;
            }
        }
        if ((double)Math.abs(dy) > 1.0) {
            dx /= Math.abs(dy);
            dz /= Math.abs(dy);
            nblks = (int)((float)nblks * Math.abs(dy));
            if (dy > 1.0f) {
                dy = 1.0f;
            }
            if (dy < -1.0f) {
                dy = -1.0f;
            }
        }
        if ((double)Math.abs(dz) > 1.0) {
            dy /= Math.abs(dz);
            dx /= Math.abs(dz);
            nblks = (int)((float)nblks * Math.abs(dz));
            if (dz > 1.0f) {
                dz = 1.0f;
            }
            if (dz < -1.0f) {
                dz = -1.0f;
            }
        }
        for (int i = 0; i < nblks; ++i) {
            Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(startx += dx), (int)(starty += dy), (int)(startz += dz))).getBlock();
            if (bid == Blocks.AIR || bid == Blocks.COBWEB || bid == Blocks.GRASS_BLOCK || bid instanceof LeavesBlock) continue;
            return false;
        }
        return true;
    }
}

