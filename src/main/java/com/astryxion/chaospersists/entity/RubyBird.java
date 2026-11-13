package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

public class RubyBird extends Cockateil {
    public RubyBird(EntityType<? extends RubyBird> type, Level level) {
        super(type, level);
        this.birdtype = 5;
        this.setBirdType(this.birdtype);
        this.setFlyUp();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Cockateil.createAttributes();
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level().isDay() && !this.level().isRaining()) {
            return ChaosSounds.RUBYBIRD;
        }
        return null;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return true;
    }

    public static boolean checkRubyBirdSpawnRules(
            EntityType<RubyBird> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return true;
    }
}
