/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Chipmunk
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Lizard
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.VelocityRaptor
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Lizard;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.VelocityRaptor;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import javax.annotation.Nullable;

/*
 * Exception performing whole class analysis ignored.
 */
public class EntityCannonFodder
extends TameableEntity {
    private static final DataParameter<Integer> IS_ACTIVATED = EntityDataManager.defineId(EntityCannonFodder.class, DataSerializers.INT);
    private static final DataParameter<Integer> HAT_COLOR = EntityDataManager.defineId(EntityCannonFodder.class, DataSerializers.INT);
    String name_one = null;
    String name_two = null;
    private int is_activated = 0;
    private int hat_color = 0;
    private int syncer = 0;
    private int px = 0;
    private int pz = 0;
    private int py = 0;
    private GenericTargetSorter LocalTargetSorter = null;

    public EntityCannonFodder(EntityType<? extends EntityCannonFodder> type, World par1World) {
        super(type, par1World);
        this.LocalTargetSorter = new GenericTargetSorter((Entity)this);
    }

    public static AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes().build();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return null;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ACTIVATED, 0);
        this.entityData.define(HAT_COLOR, 0);
    }

    public void tick() {
        super.tick();
        ++this.syncer;
        if (this.syncer > 5) {
            if (this.level.isClientSide) {
                this.is_activated = this.entityData.get(IS_ACTIVATED).intValue();
                this.hat_color = this.entityData.get(HAT_COLOR).intValue();
            } else {
                this.entityData.set(IS_ACTIVATED, this.is_activated);
                this.entityData.set(HAT_COLOR, this.hat_color);
            }
            this.syncer = 0;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (var2 != null && !var2.isEmpty() && var2.getCount() <= 0) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        ActionResultType superResult = super.mobInteract(par1PlayerEntityEntity, hand);
        if (superResult.consumesAction()) {
            return superResult;
        }
        if (this.name_one != null && this.isTame()) {
            if (this.name_one.equals(par1PlayerEntityEntity.getUUID().toString())) {
                if (this.name_two == null) {
                    this.name_two = this.name_one;
                    this.name_one = par1PlayerEntityEntity.getUUID().toString();
                    this.setOwnerUUID(this.name_one != null && !this.name_one.isEmpty() ? java.util.UUID.fromString(this.name_one) : null);
                    this.is_activated = 2;
                }
            } else if (this.name_two != null) {
                if (!this.name_two.equals(par1PlayerEntityEntity.getUUID().toString())) return ActionResultType.SUCCESS;
                this.name_two = this.name_one;
                this.name_one = par1PlayerEntityEntity.getUUID().toString();
                this.setOwnerUUID(this.name_one != null && !this.name_one.isEmpty() ? java.util.UUID.fromString(this.name_one) : null);
                this.is_activated = 2;
            } else {
                this.name_two = this.name_one;
                this.name_one = par1PlayerEntityEntity.getUUID().toString();
                this.setOwnerUUID(this.name_one != null && !this.name_one.isEmpty() ? java.util.UUID.fromString(this.name_one) : null);
                this.is_activated = 2;
            }
        }
        if (var2 != null && !var2.isEmpty() && var2.getItem() == Items.CARROT && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            this.hat_color = 1;
            if (this.name_one == null) {
                this.name_one = par1PlayerEntityEntity.getUUID().toString();
            }
            if (this.is_activated == 0) {
                this.is_activated = 1;
            }
            this.setTame(true);
            this.setOwnerUUID(this.name_one != null && !this.name_one.isEmpty() ? java.util.UUID.fromString(this.name_one) : null);
            this.level.broadcastEntityEvent(this, (byte)7);
            this.heal(this.getMaxHealth() - this.getHealth());
            this.setAge(-24000);
            if (par1PlayerEntityEntity.isCreative()) return ActionResultType.SUCCESS;
            var2.shrink(1);
            if (var2.getCount() > 0) return ActionResultType.SUCCESS;
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            return ActionResultType.SUCCESS;
        }
        if (var2 != null && !var2.isEmpty() && var2.getItem() == Items.POTATO && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            this.hat_color = 3;
            if (this.name_one == null) {
                this.name_one = par1PlayerEntityEntity.getUUID().toString();
            }
            if (this.is_activated == 0) {
                this.is_activated = 1;
            }
            this.setTame(true);
            this.setOwnerUUID(this.name_one != null && !this.name_one.isEmpty() ? java.util.UUID.fromString(this.name_one) : null);
            this.level.broadcastEntityEvent(this, (byte)7);
            this.heal(this.getMaxHealth() - this.getHealth());
            this.setAge(-24000);
            if (par1PlayerEntityEntity.isCreative()) return ActionResultType.SUCCESS;
            var2.shrink(1);
            if (var2.getCount() > 0) return ActionResultType.SUCCESS;
            par1PlayerEntityEntity.setItemInHand(net.minecraft.util.Hand.MAIN_HAND, ItemStack.EMPTY);
            return ActionResultType.SUCCESS;
        }
        if (var2 != null && !var2.isEmpty() && var2.getItem() == ChaosPersists.MyQuinoa && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            this.hat_color = 2;
            if (this.name_one == null) {
                this.name_one = par1PlayerEntityEntity.getUUID().toString();
            }
            if (this.is_activated == 0) {
                this.is_activated = 1;
            }
            this.setTame(true);
            this.setOwnerUUID(this.name_one != null && !this.name_one.isEmpty() ? java.util.UUID.fromString(this.name_one) : null);
            this.level.broadcastEntityEvent(this, (byte)7);
            this.heal(this.getMaxHealth() - this.getHealth());
            this.setAge(-24000);
            if (par1PlayerEntityEntity.isCreative()) return ActionResultType.SUCCESS;
            var2.shrink(1);
            if (var2.getCount() > 0) return ActionResultType.SUCCESS;
            par1PlayerEntityEntity.setItemInHand(net.minecraft.util.Hand.MAIN_HAND, ItemStack.EMPTY);
            return ActionResultType.SUCCESS;
        }
        if (var2 != null && !var2.isEmpty() && this.is_activated == 2 && var2.getItem() == ChaosPersists.MyCornCob && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            Entity newent;
            String myname = "Ostrich";
            if (this instanceof Lizard) {
                myname = "Lizard";
            }
            if (this instanceof Chipmunk) {
                myname = "Chipmunk";
            }
            if (this instanceof VelocityRaptor) {
                myname = "Velocity Raptor";
            }
            if (!this.level.isClientSide && (newent = EntityCannonFodder.spawnCreature((World)this.level, (String)myname, (double)(this.getX() + (double)this.level.random.nextFloat()), (double)(this.getY() + 0.01), (double)(this.getZ() + (double)this.level.random.nextFloat()))) != null) {
                EntityCannonFodder cf = (EntityCannonFodder)newent;
                cf.setOwnerUUID(this.getOwnerUUID());
                cf.setTame(true);
                cf.setStuff(this.hat_color, this.is_activated, this.name_one, this.name_two);
            }
            this.level.broadcastEntityEvent(this, (byte)7);
            par1PlayerEntityEntity.playSound(SoundEvents.GENERIC_EXPLODE, 0.75f, 2.0f);
            if (par1PlayerEntityEntity.isCreative()) return ActionResultType.SUCCESS;
            var2.shrink(1);
            if (var2.getCount() > 0) return ActionResultType.SUCCESS;
            par1PlayerEntityEntity.setItemInHand(net.minecraft.util.Hand.MAIN_HAND, ItemStack.EMPTY);
            return ActionResultType.SUCCESS;
        }
        if (this.is_activated != 2 || par1PlayerEntityEntity.distanceToSqr((Entity)this) >= 16.0) return ActionResultType.PASS;
        if (this.isOrderedToSit()) {
            this.setOrderedToSit(false);
            this.level.broadcastEntityEvent(this, (byte)7);
            return ActionResultType.SUCCESS;
        } else {
            this.setOrderedToSit(true);
            this.level.broadcastEntityEvent(this, (byte)6);
            this.px = (int)this.getX();
            this.py = (int)this.getY();
            this.pz = (int)this.getZ();
        }
        return ActionResultType.SUCCESS;
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        String registryName = par1;
        if ("Ostrich".equals(par1)) {
            registryName = "ostrich";
        } else if ("Lizard".equals(par1)) {
            registryName = "lizard";
        } else if ("Chipmunk".equals(par1)) {
            registryName = "chipmunk";
        } else if ("Velocity Raptor".equals(par1)) {
            registryName = "velocity_raptor";
        }
        net.minecraft.util.ResourceLocation key = registryName.indexOf(':') >= 0 ? new net.minecraft.util.ResourceLocation(registryName) : new net.minecraft.util.ResourceLocation("chaospersists", registryName);
        net.minecraft.entity.EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(key);
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

    public void setStuff(int hc, int ia, String s1, String s2) {
        this.hat_color = hc;
        this.is_activated = ia;
        this.name_one = s1;
        this.name_two = s2;
        this.setAge(-24000);
    }

    public int getHatColor() {
        return this.hat_color;
    }

    public int get_is_activated() {
        return this.is_activated;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        if (this.name_one == null) {
            par1CompoundNBT.putString("NameOne", "");
        } else {
            par1CompoundNBT.putString("NameOne", this.name_one);
        }
        if (this.name_two == null) {
            par1CompoundNBT.putString("NameTwo", "");
        } else {
            par1CompoundNBT.putString("NameTwo", this.name_two);
        }
        par1CompoundNBT.putInt("IsActivated", this.is_activated);
        par1CompoundNBT.putInt("HatColor", this.hat_color);
        par1CompoundNBT.putInt("PatrolX", this.px);
        par1CompoundNBT.putInt("PatrolY", this.py);
        par1CompoundNBT.putInt("PatrolZ", this.pz);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.name_one = par1CompoundNBT.getString("NameOne");
        if (this.name_one != null && this.name_one.equals("")) {
            this.name_one = null;
        }
        this.name_two = par1CompoundNBT.getString("NameTwo");
        if (this.name_two != null && this.name_two.equals("")) {
            this.name_two = null;
        }
        this.is_activated = par1CompoundNBT.getInt("IsActivated");
        this.hat_color = par1CompoundNBT.getInt("HatColor");
        this.px = par1CompoundNBT.getInt("PatrolX");
        this.py = par1CompoundNBT.getInt("PatrolY");
        this.pz = par1CompoundNBT.getInt("PatrolZ");
        if (this.name_one != null) {
            this.setTame(true);
            this.setOwnerUUID(this.name_one != null && !this.name_one.isEmpty() ? java.util.UUID.fromString(this.name_one) : null);
        }
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        double dx;
        double dy;
        double dz;
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
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
        if (this.isOrderedToSit() && (dx = (double)this.px - par1Mob.getX()) * dx + (dy = (double)this.py - par1Mob.getY()) * dy + (dz = (double)this.pz - par1Mob.getZ()) * dz > 144.0) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof EntityCannonFodder) {
            EntityCannonFodder cf = (EntityCannonFodder)par1Mob;
            int i = cf.getHatColor();
            if (i != 0 && i != this.hat_color) {
                return true;
            }
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            if (this.name_one != null && this.name_one.equals(p.getUUID().toString())) {
                return false;
            }
            if (this.name_two != null && this.name_two.equals(p.getUUID().toString())) {
                return false;
            }
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10.0, 4.0, 10.0));
        Collections.sort(var5, this.LocalTargetSorter);
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

    public int getArmorValue() {
        if (this.is_activated == 2) {
            return 3;
        }
        return 0;
    }

    public void attackEntityAsFodder(Entity par1Entity, float f) {
        par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), f);
    }

    protected void customServerAiStep() {
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.is_activated != 2) {
            return;
        }
        int pfreq = 5;
        int sfreq = 7;
        float dm = 4.0f;
        if (this instanceof Chipmunk) {
            dm = 3.0f;
            sfreq = 6;
        }
        if (this instanceof Lizard) {
            dm = 6.0f;
            sfreq = 8;
        }
        if (this instanceof VelocityRaptor) {
            sfreq = 6;
            pfreq = 4;
        }
        if (this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(pfreq) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.getNavigation().moveTo((Entity)e, 1.25);
                if (this.distanceToSqr((Entity)e) < 9.0 && (this.random.nextInt(sfreq + 1) == 0 || this.random.nextInt(sfreq) == 1)) {
                    this.attackEntityAsFodder((Entity)e, dm);
                }
            } else if (this.isOrderedToSit()) {
                this.getNavigation().moveTo((double)this.px, (double)this.py, (double)this.pz, 0.6499999761581421);
            }
        }
        if (this.level.random.nextInt(250) == 1) {
            this.heal(1.0f);
        }
    }

}

