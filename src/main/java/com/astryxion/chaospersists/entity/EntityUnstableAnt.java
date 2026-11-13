package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.world.dimension.teleporter.DangerTeleporter;
import com.astryxion.chaospersists.world.dimension.teleporter.UtopiaTeleporter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityUnstableAnt extends EntityAnt {
    public EntityUnstableAnt(EntityType<? extends EntityUnstableAnt> type, Level level) {
        super(type, level);
        this.xpReward = 0;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.399999976158142));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 9, 1.0));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15000000596046448)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
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
        boolean toOverworld = player.level().dimension().equals(ChaosPersists.getDimensionKey(4));
        ResourceKey<Level> targetDim = toOverworld ? Level.OVERWORLD : ChaosPersists.getDimensionKey(4);
        ServerLevel world = serverPlayer.server.getLevel(targetDim);
        if (world == null) {
            return InteractionResult.FAIL;
        }
        if (toOverworld) {
            serverPlayer.changeDimension((new UtopiaTeleporter(serverPlayer.getX(), serverPlayer.getZ())).transition(serverPlayer, world));
        } else {
            serverPlayer.changeDimension((new DangerTeleporter(serverPlayer.getX(), serverPlayer.getZ())).transition(serverPlayer, world));
        }
        return InteractionResult.SUCCESS;
    }
}
