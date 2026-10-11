package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class RedCow extends Cow {
    public RedCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder createAttributes() {
        return Cow.createAttributes();
    }

    @Override
    public Cow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return new RedCow(ChaosPersists.ENTITY_TYPE_RED_COW.get(), level);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        int appleCount = 1 + this.getRandom().nextInt(2 + looting);
        for (int i = 0; i < appleCount; ++i) {
            this.spawnAtLocation(new ItemStack(Items.APPLE));
        }
        this.spawnAtLocation(new ItemStack(Items.LEATHER, 1 + this.getRandom().nextInt(1 + looting)));
        this.spawnAtLocation(new ItemStack(Items.BEEF, 1 + this.getRandom().nextInt(2 + looting)));
    }

    @Override
    protected void customServerAiStep() {
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
            this.setTarget(null);
        }
        super.customServerAiStep();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}
