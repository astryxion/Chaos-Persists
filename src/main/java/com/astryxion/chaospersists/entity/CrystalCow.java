package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.util.RandomSource;

public class CrystalCow extends RedCow {
    public CrystalCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
    }

    @Override
    public Cow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return new CrystalCow(ChaosPersists.ENTITY_TYPE_CRYSTAL_COW.get(), level);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        this.spawnAtLocation(new ItemStack(ChaosPersists.MyCrystalApple));
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos below = this.blockPosition().below();
        return MyUtils.getBlockStateForSpawnRules(level, below).is(ChaosPersists.CrystalGrass);
    }

    public static boolean checkCrystalCowSpawnRules(
            EntityType<CrystalCow> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        return MyUtils.getBlockStateForSpawnRules(level, pos.below()).is(ChaosPersists.CrystalGrass);
    }
}
