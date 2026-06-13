package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import javax.annotation.Nullable;

public class Coin extends AnimalEntity {
    private float moveSpeed = 0.0f;

    public Coin(EntityType<? extends Coin> type, World par1World) {
        super(type, par1World);
        this.xpReward = 10;
        this.goalSelector.addGoal(0, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return null;
    }
    protected boolean canDespawn() {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int mygetMaxHealth() {
        return 1;
    }

    public int getArmorValue() {
        return 0;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return null;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    @Override
    protected void dropCustomDeathLoot(net.minecraft.util.DamageSource source, int looting, boolean recentlyHit) {
        int i = this.level.random.nextInt(10);
        Item j = ChaosPersists.MyEmeraldSword;
        if (i == 0) {
            j = Items.DIAMOND;
        }
        if (i == 1) {
            j = ChaosPersists.UraniumNugget;
        }
        if (i == 2) {
            j = ChaosPersists.TitaniumNugget;
        }
        if (i == 3) {
            j = Items.EMERALD;
        }
        if (i == 4) {
            j = ChaosPersists.MyEmeraldAxe;
        }
        if (i == 5) {
            j = ChaosPersists.MyEmeraldShovel;
        }
        if (i == 6) {
            j = ChaosPersists.MyEmeraldPickaxe;
        }
        if (i == 7) {
            j = ChaosPersists.MyEmeraldHoe;
        }
        if (i == 8) {
            j = ChaosPersists.CoinEgg;
        }
        this.dropItemRand(j, 1);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        return ActionResultType.PASS;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (!this.level.isDay()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        Coin target = this.level.getNearestEntity(Coin.class, net.minecraft.entity.EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(20.0, 8.0, 20.0));
        if (target != null) {
            return false;
        }
        return true;
    }
}
