package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyUtils;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

public class Tshirt extends Animal {
    private final float moveSpeed = 0.0f;

    public Tshirt(EntityType<? extends Tshirt> type, Level level) {
        super(type, level);
        this.xpReward = 40;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
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

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        this.spawnAtLocation(new ItemStack(Items.EMERALD));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(4.0f, 4.0f);
    }

    public static boolean checkTshirtSpawnRules(
            EntityType<Tshirt> type,
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
        return level.getEntitiesOfClass(Tshirt.class, box).isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (!(level instanceof ServerLevelAccessor serverLevel)) {
            return false;
        }
        return checkTshirtSpawnRules(
                ChaosPersists.ENTITY_TYPE_TSHIRT.get(), serverLevel, spawnReason, this.blockPosition(), this.getRandom());
    }
}
