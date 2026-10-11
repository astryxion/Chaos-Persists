package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

public class Cassowary extends Animal {
    private float moveSpeed = 0.25f;

    public Cassowary(EntityType<? extends Cassowary> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.moveSpeed = 0.25f;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(
                2, new AvoidEntityGoal<>(this, Monster.class, 8.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(
                3, new AvoidEntityGoal<>(this, Player.class, 8.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, LivingEntity.class, 12.0f));
        this.goalSelector.addGoal(6, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 8.0);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return 10;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        int var3 = this.getRandom().nextInt(3) + 2;
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(Items.CHICKEN);
        }
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
    }

    public static boolean checkCassowarySpawnRules(
            EntityType<Cassowary> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            net.minecraft.core.BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return MyUtils.isDay(level);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isBaby()) {
            this.setAge(-24000);
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public Cassowary getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (Cassowary) this.getType().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ChaosPersists.MyCrystalApple);
    }
}
