/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.BandP
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.MonsterEntity
 *  net.minecraft.entity.passive.VillagerEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.nbt.NBTINBTList
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import java.util.Locale;

public class BandP
extends CreatureEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(BandP.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.32f;
    private int whatset = 0;
    private int whatami = 0;
    public ItemStack[] MymainInventory = new ItemStack[100];
    int got_stuff = 0;

    public BandP(EntityType<? extends BandP> type, World par1World) {
        super(type, par1World);
        this.xpReward = 1000;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new MoveThroughVillageGoal(this, 0.5D, false, 32, () -> true));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 0.5D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new OpenDoorGoal(this, true));
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static AttributeModifierMap createAttributes() {
        return CreatureEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.BandP_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.BandP_stats.attack)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
    }
    protected boolean canDespawn(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.got_stuff != 0) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        if (!this.level.isClientSide && this.whatset == 0) {
            this.whatset = 1;
            this.whatami = this.level.random.nextInt(2);
            this.setWhat(this.whatami);
        }
        if (!this.level.isClientSide) {
            this.updateAITasks();
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.BandP_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.BandP_stats.defense;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return net.minecraft.util.SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return net.minecraft.util.SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return net.minecraft.util.SoundEvents.VILLAGER_DEATH;
    }

    protected float getSoundVolume() {
        return 1.5f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.EMERALD;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropFewItems(boolean par1, int par2) {
        int i;
        int var4 = 10 + this.level.random.nextInt(5);
        for (i = 0; i < var4; ++i) {
            this.dropItemRand(Items.EMERALD, 1);
        }
        if (this.getWhat() == 0) {
            var4 = 2 + this.level.random.nextInt(3);
            for (i = 0; i < var4; ++i) {
                this.dropItemRand(ChaosPersists.UraniumNugget, 1);
                this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
            }
        }
        for (i = 0; i < this.MymainInventory.length; ++i) {
            if (this.MymainInventory[i] == null || this.MymainInventory[i].getCount() == 0) continue;
            ItemStack is = this.dropItemRand(this.MymainInventory[i].getItem(), this.MymainInventory[i].getCount());
            if (this.MymainInventory[i].getCount() != 1) continue;
            is.setDamageValue(this.MymainInventory[i].getDamageValue());
        }
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (par1Entity == null) {
            return false;
        }
        return par1Entity.hurt(net.minecraft.util.DamageSource.mobAttack(this), (float)ChaosPersists.BandP_stats.attack);
    }

    protected void updateAITasks() {
        LivingEntity e;
        if (!this.isAlive()) {
            return;
        }
        if (this.level.random.nextInt(12) == 1 && (e = this.findSomethingToAttack()) != null) {
            this.lookAt((Entity)e, 10.0f, 10.0f);
            if (this.distanceToSqr((Entity)e) < 9.0) {
                this.doHurtTarget((LivingEntity)e);
                if (e instanceof PlayerEntity) {
                    int i;
                    PlayerEntity p = (PlayerEntity)e;
                    int k = -1;
                    int kp = -1;
                    for (i = 0; i < this.MymainInventory.length; ++i) {
                        if (this.MymainInventory[i] != null) continue;
                        k = i;
                        break;
                    }
                    if (k >= 0) {
                        for (i = p.inventory.armor.size() - 1; i >= 0; --i) {
                            if (p.inventory.armor.get(i).isEmpty()) continue;
                            kp = i;
                            break;
                        }
                        if (kp >= 0) {
                            this.MymainInventory[k] = p.inventory.armor.get(kp);
                            p.inventory.armor.set(kp, ItemStack.EMPTY);
                            ++this.got_stuff;
                        }
                        if (kp < 0) {
                            for (i = p.inventory.items.size() - 1; i >= 0; --i) {
                                if (p.inventory.items.get(i).isEmpty()) continue;
                                kp = i;
                                break;
                            }
                            if (kp >= 0) {
                                this.MymainInventory[k] = p.inventory.items.get(kp);
                                p.inventory.items.set(kp, ItemStack.EMPTY);
                                ++this.got_stuff;
                            }
                        }
                    }
                }
            } else {
                this.getNavigation().moveTo((Entity)e, 1.25D);
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
        if (!this.canSee(par1Mob)) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1Mob instanceof VillagerEntity) {
            return true;
        }
        if (par1Mob instanceof Girlfriend) {
            return true;
        }
        if (par1Mob instanceof Boyfriend) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0, 6.0, 20.0));
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

    public int getWhat() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setWhat(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public boolean getCanSpawnHere() {
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
                    if (s == null || !"criminal".equals(s.toLowerCase(Locale.ROOT))) continue;
                    return true;
                }
            }
        }
        if (this.level.isNight()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.getY() < 100.0) {
            return false;
        }
        BandP target = null;
        target = this.level.getNearestEntity(BandP.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(32.0, 12.0, 32.0));
        if (target != null) {
            return false;
        }
        VillagerEntity target2 = null;
        target2 = this.level.getNearestEntity(VillagerEntity.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(36.0, 12.0, 36.0));
        if (target2 == null) {
            return false;
        }
        return true;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        if (this.got_stuff != 0) {
            par1CompoundNBT.put("Inventory", this.writeToNBT(new ListNBT()));
        }
        par1CompoundNBT.putInt("GotStuff", this.got_stuff);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.got_stuff = par1CompoundNBT.getInt("GotStuff");
        if (this.got_stuff != 0) {
            ListNBT nbttaglist = par1CompoundNBT.getList("Inventory", 10);
            this.readFromNBT(nbttaglist);
        }
    }

    public ListNBT writeToNBT(ListNBT par1ListTag) {
        for (int i = 0; i < this.MymainInventory.length; ++i) {
            if (this.MymainInventory[i] == null) continue;
            CompoundNBT nbttagcompound = new CompoundNBT();
            nbttagcompound.putByte("Slot", (byte)i);
            this.MymainInventory[i].save(nbttagcompound);
            par1ListTag.add(nbttagcompound);
        }
        return par1ListTag;
    }

    public void readFromNBT(ListNBT par1ListTag) {
        this.MymainInventory = new ItemStack[100];
        for (int i = 0; i < par1ListTag.size(); ++i) {
            CompoundNBT nbttagcompound = par1ListTag.getCompound(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.of(nbttagcompound);
            if (itemstack.isEmpty() || j < 0 || j >= this.MymainInventory.length) continue;
            this.MymainInventory[j] = itemstack;
        }
    }
}

