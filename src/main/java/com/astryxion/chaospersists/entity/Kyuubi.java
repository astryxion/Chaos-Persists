/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Kyuubi
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.monster.ZombifiedPiglinEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.entity.projectile.SmallFireballEntity
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
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class Kyuubi
extends MonsterEntity {
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.25f;

    public Kyuubi(EntityType<? extends Kyuubi> type, World par1World) {
        super(type, par1World);
        this.xpReward = 30;
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.350000023841858));
        this.goalSelector.addGoal(2, new MoveThroughVillageGoal(this, 1.0, false, 512, () -> true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }
    @Override
    public boolean fireImmune() {
        return true;
    }


    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Kyuubi_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Kyuubi_stats.attack)
                .build();
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
        return ChaosPersists.Kyuubi_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.Kyuubi_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level.random.nextInt(10) == 1) {
            this.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), this.getX(), this.getY() + 2.0, this.getZ(), 0.0, 0.0, 0.0);
            this.level.addParticle(net.minecraft.particles.ParticleTypes.LAVA, this.getX(), this.getY() + 2.0, this.getZ(), 0.0, 0.0, 0.0);
            this.setSecondsOnFire(5);
            if (this.isInWater()) {
                this.doHurtTarget(this);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX(), this.getY() + 1.75, this.getZ(), 0.0, 0.0, 0.0);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 1.75, this.getZ(), 0.0, 0.0, 0.0);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX(), this.getY() + 2.0, this.getZ(), 0.0, 0.0, 0.0);
                this.level.addParticle(net.minecraft.particles.ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 2.0, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    public int getAttackStrength(Entity par1Entity) {
        return 3;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.KYUUBI_LIVING;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_DEATH;
    }

    protected float getSoundVolume() {
        return 0.75f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(6);
        if (i == 0) {
            return Items.GOLD_NUGGET;
        }
        if (i == 1) {
            return ChaosPersists.UraniumNugget;
        }
        if (i == 2) {
            return ChaosPersists.TitaniumNugget;
        }
        return null;
    }

    public void initCreature() {
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    protected void customServerAiStep() {
        LivingEntity e;
        if (!this.isAlive()) {
            return;
        }
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(10) == 1 && (e = this.findSomethingToAttack()) != null) {
            this.lookAt((Entity)e, 10.0f, 10.0f);
            this.getNavigation().moveTo(e, 1.25);
            if (this.distanceToSqr((Entity)e) < 64.0 && (this.random.nextInt(6) == 0 || this.random.nextInt(8) == 1)) {
                SmallFireballEntity var2 = new SmallFireballEntity(this.level, (LivingEntity)this, e.getX() - this.getX(), e.getY() + 0.75 - (this.getY() + 1.25), e.getZ() - this.getZ());
                var2.moveTo(this.getX(), this.getY() + 1.25, this.getZ(), this.yRot, this.xRot);
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.bow")), net.minecraft.util.SoundCategory.HOSTILE, 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level.addFreshEntity(var2);
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
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return false;
        }
        if (par1Mob instanceof ZombifiedPiglinEntity) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 4.0, 12.0));
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

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        return true;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        for (var4 = 0; var4 < 10; ++var4) {
            this.dropItemRand(Items.COAL, 1);
        }
        for (var4 = 0; var4 < 3; ++var4) {
            this.dropItemRand(Item.byBlock((Block)Blocks.REDSTONE_BLOCK), 1);
        }
        for (var4 = 0; var4 < 4; ++var4) {
            this.dropItemRand(Item.byBlock((Block)Blocks.QUARTZ_BLOCK), 1);
        }
    }
}

