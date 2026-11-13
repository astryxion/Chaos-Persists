package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.world.dimension.teleporter.MiningTeleporter;
import com.astryxion.chaospersists.world.dimension.teleporter.UtopiaTeleporter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityRedAnt extends EntityAnt {
    int attack_delay = 20;

    public EntityRedAnt(EntityType<? extends EntityRedAnt> type, Level level) {
        super(type, level);
        this.moveSpeed = 0.20000000298023224;
        this.xpReward = 1;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.399999976158142));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 10, 1.0));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.20000000298023224)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    @Override
    public int mygetMaxHealth() {
        return 2;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (ChaosPersists.ChaosRand.nextInt(15) != 0) {
            return false;
        }
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        return target.hurt(this.damageSources().mobAttack(this), 1.0f);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }
        if (player == null) {
            return InteractionResult.PASS;
        }
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResult.PASS;
        }
        ItemStack var2 = player.getMainHandItem();
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()) {
            return InteractionResult.PASS;
        }
        if (serverPlayer.server == null) {
            return InteractionResult.PASS;
        }
        boolean toOverworld = player.level().dimension().equals(ChaosPersists.getMiningDimensionKey());
        ResourceKey<Level> targetDim = toOverworld ? Level.OVERWORLD : ChaosPersists.getMiningDimensionKey();
        ServerLevel world = serverPlayer.server.getLevel(targetDim);
        if (world == null) {
            return InteractionResult.FAIL;
        }
        if (toOverworld) {
            serverPlayer.changeDimension((new UtopiaTeleporter(serverPlayer.getX(), serverPlayer.getZ())).transition(serverPlayer, world));
        } else {
            serverPlayer.changeDimension((new MiningTeleporter(serverPlayer.getX(), serverPlayer.getZ())).transition(serverPlayer, world));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.attack_delay > 0) {
            --this.attack_delay;
        }
        if (this.attack_delay > 0) {
            return;
        }
        this.attack_delay = 20;
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        Player e = this.level().getNearestPlayer(this, 1.5);
        if (e != null) {
            this.doHurtTarget(e);
        }
    }
}
