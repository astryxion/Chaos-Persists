package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

public class Coin extends Animal {
    private final float moveSpeed = 0.0f;

    public Coin(EntityType<? extends Coin> type, Level level) {
        super(type, level);
        this.xpReward = 10;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RandomLookAroundGoal(this));
    }

    public int mygetMaxHealth() {
        return 1;
    }

    @Override
    public int getArmorValue() {
        return 0;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.isPersistenceRequired();
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return null;
    }

    private void dropItemRand(Item item, int count) {
        if (item == null) {
            return;
        }
        double ox = ChaosPersists.ChaosRand.nextInt(2) - ChaosPersists.ChaosRand.nextInt(2);
        double oz = ChaosPersists.ChaosRand.nextInt(2) - ChaosPersists.ChaosRand.nextInt(2);
        ItemEntity drop = new ItemEntity(
                this.level(),
                this.getX() + ox,
                this.getY() + 1.0,
                this.getZ() + oz,
                new ItemStack(item, count));
        this.level().addFreshEntity(drop);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        int i = this.random.nextInt(10);
        Item drop = ChaosPersists.MyEmeraldSword;
        if (i == 0) {
            drop = Items.DIAMOND;
        } else if (i == 1) {
            drop = ChaosPersists.UraniumNugget;
        } else if (i == 2) {
            drop = ChaosPersists.TitaniumNugget;
        } else if (i == 3) {
            drop = Items.EMERALD;
        } else if (i == 4) {
            drop = ChaosPersists.MyEmeraldAxe;
        } else if (i == 5) {
            drop = ChaosPersists.MyEmeraldShovel;
        } else if (i == 6) {
            drop = ChaosPersists.MyEmeraldPickaxe;
        } else if (i == 7) {
            drop = ChaosPersists.MyEmeraldHoe;
        } else if (i == 8) {
            drop = ChaosPersists.CoinEgg;
        }
        this.dropItemRand(drop, 1);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(1.5f, 1.5f);
    }

    public static boolean checkCoinSpawnRules(
            EntityType<Coin> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            net.minecraft.core.BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (pos.getY() < 50) {
            return false;
        }
        AABB box = new AABB(pos).inflate(20.0, 8.0, 20.0);
        return level.getEntitiesOfClass(Coin.class, box, Entity::isAlive).isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (!(level instanceof ServerLevelAccessor serverLevel)) {
            return false;
        }
        return checkCoinSpawnRules(
                (EntityType<Coin>) this.getType(), serverLevel, spawnReason, this.blockPosition(), this.getRandom());
    }
}
