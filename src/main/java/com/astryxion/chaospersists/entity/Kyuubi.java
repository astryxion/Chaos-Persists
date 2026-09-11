package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;

public class Kyuubi extends Monster {
    private final GenericTargetSorter targetSorter;
    private final float moveSpeed = 0.25f;

    public Kyuubi(EntityType<? extends Kyuubi> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 30;
        this.fireImmune();
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.350000023841858));
        this.goalSelector.addGoal(2, new MoveThroughVillageGoal(this, 1.0, false, 14, () -> false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Kyuubi_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Kyuubi_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Kyuubi_stats.defense);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (this.getRandom().nextInt(10) == 1) {
            if (!this.level().isClientSide()) {
                this.setRemainingFireTicks(5);
            }
            if (this.level().isClientSide) {
                this.level()
                        .addParticle(
                                new DustParticleOptions(new Vector3f(0.5f, 0.5f, 0.5f), 1.0f),
                                this.getX(),
                                this.getY() + 2.0,
                                this.getZ(),
                                0.0,
                                0.0,
                                0.0);
                this.level()
                        .addParticle(
                                ParticleTypes.LAVA,
                                this.getX(),
                                this.getY() + 2.0,
                                this.getZ(),
                                0.0,
                                0.0,
                                0.0);
                if (this.isInWater()) {
                    this.level()
                            .addParticle(
                                    ParticleTypes.SMOKE,
                                    this.getX(),
                                    this.getY() + 1.75,
                                    this.getZ(),
                                    0.0,
                                    0.0,
                                    0.0);
                    this.level()
                            .addParticle(
                                    ParticleTypes.LARGE_SMOKE,
                                    this.getX(),
                                    this.getY() + 1.75,
                                    this.getZ(),
                                    0.0,
                                    0.0,
                                    0.0);
                    this.level()
                            .addParticle(
                                    ParticleTypes.SMOKE,
                                    this.getX(),
                                    this.getY() + 2.0,
                                    this.getZ(),
                                    0.0,
                                    0.0,
                                    0.0);
                    this.level()
                            .addParticle(
                                    ParticleTypes.LARGE_SMOKE,
                                    this.getX(),
                                    this.getY() + 2.0,
                                    this.getZ(),
                                    0.0,
                                    0.0,
                                    0.0);
                }
            } else if (this.isInWater()) {
                this.hurt(this.damageSources().mobAttack(this), (float) ChaosPersists.Kyuubi_stats.attack);
            }
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Kyuubi_stats.health;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.Kyuubi_stats.defense;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.KYUUBI_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.ALO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        for (var4 = 0; var4 < 10; ++var4) {
            this.dropItemRand(Items.COAL, 1);
        }
        for (var4 = 0; var4 < 3; ++var4) {
            this.dropItemRand(Items.REDSTONE_BLOCK, 1);
        }
        for (var4 = 0; var4 < 4; ++var4) {
            this.dropItemRand(Blocks.QUARTZ_BLOCK.asItem(), 1);
        }
    }

    private void dropItemRand(Item index, int count) {
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        new ItemStack(index, count));
        this.level().addFreshEntity(entityItem);
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        super.customServerAiStep();
        LivingEntity target;
        if (this.getRandom().nextInt(10) == 1 && (target = this.findSomethingToAttack()) != null) {
            this.setTarget(target);
            this.getNavigation().moveTo(target, 1.25);
            if (this.distanceToSqr(target) < 64.0
                    && (this.getRandom().nextInt(6) == 0 || this.getRandom().nextInt(8) == 1)) {
                SmallFireball fireball =
                        new SmallFireball(
                                this.level(),
                                this,
                                target.getX() - this.getX(),
                                target.getY() + 0.75 - (this.getY() + 1.25),
                                target.getZ() - this.getZ());
                fireball.moveTo(this.getX(), this.getY() + 1.25, this.getZ(), this.getYRot(), this.getXRot());
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.ARROW_SHOOT,
                                this.getSoundSource(),
                                0.75f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(fireball);
            }
        }
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return false;
        }
        if (par1EntityLiving instanceof ZombifiedPiglin) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.getAbilities().instabuild) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class,
                                this.getBoundingBox().inflate(12.0, 4.0, 12.0));
        Collections.sort(candidates, this.targetSorter);
        Iterator<LivingEntity> it = candidates.iterator();
        while (it.hasNext()) {
            LivingEntity candidate = it.next();
            if (!this.isSuitableTarget(candidate, false)) {
                continue;
            }
            return candidate;
        }
        return null;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return true;
    }

    public static boolean checkKyuubiSpawnRules(
            EntityType<Kyuubi> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return Monster.checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }
}
