/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Cricket
 *  com.astryxion.chaospersists.EntityAnt
 *  com.astryxion.chaospersists.EntityButterfly
 *  com.astryxion.chaospersists.EntityMosquito
 *  com.astryxion.chaospersists.Firefly
 *  com.astryxion.chaospersists.Frog
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.WormSmall
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraftforge.registries.ForgeRegistries.ENTITIES
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
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

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Cricket;
import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.EntityMosquito;
import com.astryxion.chaospersists.entity.Firefly;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.WormSmall;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
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
public class Frog
extends AnimalEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Frog.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    public double moveSpeed = 0.10000000149011612;
    private int singing = 0;
    private int jumpcount = 0;

    public Frog(EntityType<? extends Frog> type, World par1World) {
        super(type, par1World);
        this.xpReward = 5;
        this.TargetSorter = new GenericTargetSorter((Entity)this);
                this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(2, new MyEntityAIWander(this, 1.0f));
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getSinging() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setSinging(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    private void jumpAround() {
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, (double)(0.75f + Math.abs(this.level.random.nextFloat() * 0.55f)), 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.3499999940395355);
        float f = 0.7f + Math.abs(this.level.random.nextFloat() * 0.75f);
        float d = (float)Math.toRadians(this.yRot);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, -((double)f * Math.sin(d)), 0.0, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.0, (double)f * Math.cos(d));
        this.setOnGround(false);
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.moveSpeed);
        super.tick();
        if (!this.level.isClientSide) {
            if (this.singing != 0) {
                --this.singing;
                if (this.singing <= 0) {
                    this.setSinging(0);
                }
            }
            if (this.jumpcount > 0) {
                --this.jumpcount;
            }
            if (this.jumpcount == 0 && this.level.random.nextInt(70) == 1) {
                this.jumpAround();
                this.jumpcount = 50;
            }
        }
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        block2 : {
            World world;
            block3 : {
                block4 : {
                    if (par1PlayerEntityEntity == null || !par1PlayerEntityEntity.isCrouching() || par1PlayerEntityEntity.inventory.getSelected() != null) break block2;
                    world = par1PlayerEntityEntity.level;
                    this.remove();
                    par1PlayerEntityEntity.level.playSound(null, par1PlayerEntityEntity.getX(), par1PlayerEntityEntity.getY(), par1PlayerEntityEntity.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("minecraft", "entity.generic.explode")), net.minecraft.util.SoundCategory.PLAYERS, 1.0f, level.random.nextFloat() * 0.2f + 0.9f);
                    if (world.isClientSide) break block3;
                    if (level.random.nextInt(2) != 0) break block4;
                    Boyfriend ent = null;
                    ent = (Boyfriend)Frog.spawnCreature((World)world, (String)"Boyfriend", (double)this.getX(), (double)(this.getY() + 0.01), (double)this.getZ());
                    if (ent != null) {
                        ent.setPrince(1 + level.random.nextInt(2));
                    }
                    break block2;
                }
                Girlfriend ent = null;
                ent = (Girlfriend)Frog.spawnCreature((World)world, (String)"Girlfriend", (double)this.getX(), (double)(this.getY() + 0.01), (double)this.getZ());
                if (ent == null) break block2;
                ent.setPrincess(1 + level.random.nextInt(2));
                break block2;
            }
            for (int var3 = 0; var3 < 16; ++var3) {
                world.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, (double)((float)this.getX() + level.random.nextFloat() - level.random.nextFloat()), (double)((float)this.getY() + level.random.nextFloat()), (double)((float)this.getZ() + level.random.nextFloat() - level.random.nextFloat()), 0.0, 0.0, 0.0);
                world.addParticle(net.minecraft.particles.ParticleTypes.EXPLOSION, (double)((float)this.getX() + level.random.nextFloat() - level.random.nextFloat()), (double)((float)this.getY() + level.random.nextFloat()), (double)((float)this.getZ() + level.random.nextFloat() - level.random.nextFloat()), 0.0, 0.0, 0.0);
                world.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), (double)((float)this.getX() + level.random.nextFloat() - level.random.nextFloat()), (double)((float)this.getY() + level.random.nextFloat()), (double)((float)this.getZ() + level.random.nextFloat() - level.random.nextFloat()), 0.0, 0.0, 0.0);
            }
        }
        return false;
    }

    public boolean isAIEnabled() {
        return true;
    }

    public int mygetMaxHealth() {
        return 8;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (!this.level.isClientSide) {
            if (this.level.random.nextInt(2) == 0) {
                return null;
            }
            this.singing = 35;
            this.setSinging(this.singing);
        }
        return com.astryxion.chaospersists.core.ChaosSounds.FROG;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return com.astryxion.chaospersists.core.ChaosSounds.SCORPION_HIT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.BIG_SPLAT;
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        for (int i = 0; i < 4; ++i) {
            this.dropItemRand(Items.SLIME_BALL, 1);
        }
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        boolean var4 = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), 3.0f);
        if (!par1Entity.isAlive()) {
            this.heal(1.0f);
        }
        return var4;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        ret = super.hurt(par1DamageSource, par2);
        if (!this.level.isClientSide && this.jumpcount <= 0) {
            this.jumpAround();
            this.jumpcount = 25;
        }
        return ret;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new net.minecraft.util.math.RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.25, this.getZ()), new Vector3d(pX, pY, pZ), net.minecraft.util.math.RayTraceContext.BlockMode.COLLIDER, net.minecraft.util.math.RayTraceContext.FluidMode.NONE, this)).getType() == net.minecraft.util.math.RayTraceResult.Type.MISS;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) { return null; }

    private int findBuddies() {
        List var5 = this.level.getEntitiesOfClass(Frog.class, this.getBoundingBox().inflate(20.0, 8.0, 20.0));
        return var5.size();
    }

    /**
     * Utopia / Village Mania register frogs as {@link net.minecraft.entity.EntityClassification#WATER_CREATURE} only.
     * Without a water check, {@code getCanSpawnHere} still passed on land (Y/daytime/buddies), so bad candidates could
     * flood the surface. Vanilla jungle/river also use {@code AMBIENT} frogs, which must still spawn on land.
     */
    private boolean chaosUtopiaOrVillageDimension() {
        if (this.level == null || this.level.getServer() == null) {
            return false;
        }
        int d = com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level);
        return d == ChaosPersists.getDimension() || d == ChaosPersists.getDimension(3);
    }

    private boolean feetInWater() {
        BlockPos base = new BlockPos(this.getX(), this.getY(), this.getZ());
        for (int k = 0; k <= 2; k++) {
            if (this.level.getBlockState(base.below(k)).getFluidState().is(net.minecraft.tags.FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        if (this.chaosUtopiaOrVillageDimension()) {
            if (!this.feetInWater()) {
                return false;
            }
            if (this.findBuddies() > 2) {
                return false;
            }
        } else if (this.findBuddies() > 5) {
            return false;
        }
        return true;
    }

    protected void customServerAiStep() {
        boolean xdir = true;
        boolean zdir = true;
        int keep_trying = 50;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (this.random.nextInt(12) == 0 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity e = null;
            e = this.findSomethingToAttack();
            if (e != null) {
                this.getNavigation().moveTo(e, 1.25);
                if (this.distanceToSqr((Entity)e) < 6.0) {
                    this.doHurtTarget((LivingEntity)e);
                }
            }
        }
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
        if (par1Mob instanceof EntityAnt) {
            return true;
        }
        if (par1Mob instanceof EntityButterfly) {
            return true;
        }
        if (par1Mob instanceof Cricket) {
            return true;
        }
        if (par1Mob instanceof EntityMosquito) {
            return true;
        }
        if (par1Mob instanceof Firefly) {
            return true;
        }
        if (par1Mob instanceof WormSmall) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8.0, 3.0, 8.0));
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

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.entity.EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation("chaospersists", par1));
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
}

