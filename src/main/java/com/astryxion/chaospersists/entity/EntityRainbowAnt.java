package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosTeleporter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ITeleporter;

public class EntityRainbowAnt extends EntityAnt {
    public EntityRainbowAnt(EntityType<? extends EntityRainbowAnt> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.1f, height=0.1f
        this.xpReward = 0;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.399999976158142));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 9, 1.0));
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15000000596046448)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    @Override
    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        if (par1PlayerEntityEntity == null) {
            return false;
        }
        if (!(par1PlayerEntityEntity instanceof ServerPlayerEntity)) {
            return false;
        }
        ItemStack var2 = par1PlayerEntityEntity.getMainHandItem();
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()) {
            return false;
        }
        MinecraftServer server = this.level.getServer();
        if (server == null) {
            return true;
        }
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) par1PlayerEntityEntity;
        ServerWorld targetWorld = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(3));
        ServerWorld overworld = server.getLevel(World.OVERWORLD);
        if (targetWorld == null || overworld == null) {
            return false;
        }
        if (serverPlayerEntity.getLevel() != targetWorld) {
            serverPlayerEntity.changeDimension(targetWorld, (ITeleporter) new ChaosTeleporter(targetWorld, ChaosPersists.getDimension(3), this.level));
        } else {
            serverPlayerEntity.changeDimension(overworld, (ITeleporter) new ChaosTeleporter(overworld, 0, this.level));
        }
        return true;
    }
}
