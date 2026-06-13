/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.AttackSquid
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.RubberDucky
 *  net.minecraft.block.Block
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.passive.SquidEntity
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.Difficulty
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

import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

/*
 * Exception performing whole class analysis ignored.
 */
public class RubberDucky
extends TameableEntity {
    private static final DataParameter<Byte> BYTE22 = EntityDataManager.defineId(RubberDucky.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> BYTE23 = EntityDataManager.defineId(RubberDucky.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    public boolean should_despawn = true;
    private LivingEntity buddy = null;
    private float moveSpeed = 0.22f;
    private int killcount = 0;
    private int died = 0;
    private RenderInfo renderdata = new RenderInfo();
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public RubberDucky(EntityType<? extends RubberDucky> type, World par1World) {
        super(type, par1World);
                this.xpReward = 15;
                
        this.renderdata = new RenderInfo();
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal((AnimalEntity)this, 1.0));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner((TameableEntity)this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(2, new BreedGoal((AnimalEntity)this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, net.minecraft.item.crafting.Ingredient.of(Items.COD), false));
        this.goalSelector.addGoal(4, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(5, new LookAtGoal(this, LivingEntity.class, 6.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .build();
    }

    @javax.annotation.Nullable
    @Override
    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) {
        return (AgeableEntity)this.getType().create(level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BYTE23, (byte)0);
        this.entityData.define(BYTE22, (byte)0);
        this.setOrderedToSit(false);
        if (this.getAge() < 0) {
            this.setAge(- this.getAge());
        }
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

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        if (this.isInWater()) {
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.10000000149011612, 0.0);
            if (this.getDeltaMovement().y < -0.05000000074505806) {
                this.setDeltaMovement(this.getDeltaMovement().x, -0.05000000074505806, this.getDeltaMovement().z);
            }
        }
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity w = null;
        w = par1DamageSource.getEntity();
        ret = super.hurt(par1DamageSource, par2);
        this.setOrderedToSit(false);
        if (!this.level.isClientSide && w != null && w instanceof PlayerEntity && (this.isAlive() == false || this.getHealth() <= 0.0f) && this.died == 0) {
            this.died = 1;
            ++this.killcount;
            this.setKillCount(this.killcount);
            if (this.killcount < 10) {
                for (int m = 0; m < 20; ++m) {
                    int i = this.level.random.nextInt(3);
                    if (this.level.random.nextInt(2) == 1) {
                        i = - i;
                    }
                    int k = this.level.random.nextInt(3);
                    if (this.level.random.nextInt(2) == 1) {
                        k = - k;
                    }
                    for (int j = 3; j > -3; --j) {
                        if (this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j + 1, (int)this.getZ() + k)).getBlock() != Blocks.AIR || this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + i, (int)this.getY() + j, (int)this.getZ() + k)).getBlock() == Blocks.AIR) continue;
                        Entity e = RubberDucky.spawnCreature((World)this.level, (String)"Rubber Ducky", (double)((int)this.getX() + i + 1), (double)((int)this.getY() + j + 1), (double)((int)this.getZ() + k));
                        if (e != null) {
                            RubberDucky d = (RubberDucky)e;
                            d.setKillCount(this.killcount);
                        }
                        return ret;
                    }
                }
            }
        }
        return ret;
    }

    public int mygetMaxHealth() {
        return 5;
    }

    public int getArmorValue() {
        return 1;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.util.ResourceLocation res = par1.contains(":") ? new net.minecraft.util.ResourceLocation(par1) : new net.minecraft.util.ResourceLocation("chaospersists", par1);
        net.minecraft.entity.EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(res);
        if (entityType != null) {
            var8 = entityType.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity)var8);
        }
        return var8;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.level.random.nextInt(10) == 1) {
            return com.astryxion.chaospersists.core.ChaosSounds.DUCK_HURT;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.DUCK_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.DUCK_HURT;
    }

    protected float getSoundVolume() {
        return 0.8f;
    }

    protected float getVoicePitch() {
        return 1.2f;
    }

    protected Item getDropItem() {
        if (this.level.random.nextInt(2) == 1) {
            return Items.FEATHER;
        }
        if (this.level.random.nextInt(2) == 1) {
            return ChaosPersists.RubberDuckyEgg;
        }
        return null;
    }

    @Override
    public net.minecraft.util.ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, net.minecraft.util.Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (var2 != null && var2.isEmpty()) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        net.minecraft.util.ActionResultType superResult = super.mobInteract(par1PlayerEntityEntity, hand);
        if (superResult.consumesAction()) {
            return superResult;
        }
        if (var2 != null && !var2.isEmpty() && var2.getItem() == Items.COD && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level.isClientSide) {
                    if (this.random.nextInt(2) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.heal((float)this.mygetMaxHealth() - this.getHealth());
                    } else {
                        this.level.broadcastEntityEvent(this, (byte)6);
                    }
                }
            } else if (this.isOwnedBy(par1PlayerEntityEntity)) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                }
                if ((float)this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float)this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return net.minecraft.util.ActionResultType.SUCCESS;
        }
        if (this.isTame() && var2 != null && !var2.isEmpty() && var2.getItem() == Blocks.DEAD_BUSH.asItem() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy(par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.setTame(false);
                this.setOwnerUUID(null);
                this.level.broadcastEntityEvent(this, (byte)6);
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return net.minecraft.util.ActionResultType.SUCCESS;
        }
        if (this.isTame() && this.isOwnedBy(par1PlayerEntityEntity) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.isOrderedToSit() && this.getKillCount() < 5) {
                this.setOrderedToSit(true);
            } else {
                this.setOrderedToSit(false);
            }
            return net.minecraft.util.ActionResultType.SUCCESS;
        }
        return net.minecraft.util.ActionResultType.SUCCESS;
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
        if (!this.isInWater() && this.level.random.nextInt(50) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 14; ++i) {
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
            }
        }
        if (this.killcount > 0 && this.level.random.nextInt(200) == 1) {
            --this.killcount;
            this.setKillCount(this.killcount);
        }
        if (this.getHealth() < (float)this.mygetMaxHealth() && this.level.random.nextInt(300) == 1) {
            this.heal(1.0f);
        }
        if (this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(5) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr((Entity)e) < 12.0) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(4) == 0 || this.level.random.nextInt(5) == 1) {
                        this.doHurtTarget((LivingEntity)e);
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.2);
                }
            } else {
                if (this.buddy != null && !this.buddy.removed && this.level.random.nextInt(15) == 1) {
                    this.getNavigation().moveTo(this.buddy, 1.0);
                }
                this.setAttacking(0);
            }
        }
        if (this.buddy != null && !this.buddy.removed && this.level.random.nextInt(20) == 1) {
            this.getNavigation().moveTo(this.buddy, 1.0);
        }
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        float i = 1.0f;
        if (this.getKillCount() >= 5) {
            i = 2.0f;
        }
        boolean flag = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), i);
        return flag;
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
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
        if (par1Mob instanceof AttackSquid) {
            return true;
        }
        if (par1Mob instanceof SquidEntity) {
            return true;
        }
        if (par1Mob instanceof RubberDucky && this.level.random.nextInt(10) == 1) {
            this.buddy = par1Mob;
        }
        if (this.getKillCount() >= 5 && par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8.0, 4.0, 8.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
        this.buddy = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false)) continue;
            return var4;
        }
        return null;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("Killcount", this.killcount);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.killcount = par1CompoundNBT.getInt("Killcount");
        this.setKillCount(this.killcount);
    }

    public final int getAttacking() {
        return this.entityData.get(BYTE23).byteValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(BYTE23, (byte)par1);
    }

    public final int getKillCount() {
        return this.entityData.get(BYTE22).byteValue();
    }

    public final void setKillCount(int par1) {
        this.entityData.set(BYTE22, (byte)par1);
        this.killcount = par1;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileMonsterspawner = null;
                    tileMonsterspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileMonsterspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Rubber Ducky")) continue;
                    return true;
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level.isDay()) {
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
        if (this.isTame()) {
            return false;
        }
        return this.should_despawn;
    }


    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.COD;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return par1ItemStack.getItem() == ChaosPersists.MyCrystalApple;
    }
}

