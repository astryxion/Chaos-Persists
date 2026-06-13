package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.util.SoundEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;

public class RubyBird extends Cockateil {
    public RubyBird(EntityType<? extends RubyBird> type, World par1World) {
        super(type, par1World);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.birdtype = 5;
        this.setBirdType(this.birdtype);
        this.setFlyUp();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level.isDay() && !this.level.isRaining()) {
            return ChaosSounds.RUBYBIRD;
        }
        return null;
    }

    public boolean checkSpawnRules(IWorldReader world, SpawnReason reason) {
        return true;
    }
}
