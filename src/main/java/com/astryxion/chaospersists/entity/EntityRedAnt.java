package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosTeleporter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ITeleporter;

public class EntityRedAnt extends EntityAnt {
    int attack_delay = 20;

    public EntityRedAnt(EntityType<? extends EntityRedAnt> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.2f, height=0.2f
        this.moveSpeed = 0.20000000298023224;
        this.xpReward = 1;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.399999976158142));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 10, 1.0));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        }
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.20000000298023224)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .build();
    }

    @Override
    public int mygetMaxHealth() {
        return 2;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (ChaosPersists.ChaosRand.nextInt(15) != 0) {
            return false;
        }
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        return par1Entity.hurt(DamageSource.mobAttack(this), 1.0f);
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
        ServerWorld targetWorld = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(2));
        ServerWorld overworld = server.getLevel(World.OVERWORLD);
        if (targetWorld == null || overworld == null) {
            return false;
        }
        if (serverPlayerEntity.getLevel() != targetWorld) {
            serverPlayerEntity.changeDimension(targetWorld, (ITeleporter) new ChaosTeleporter(targetWorld, ChaosPersists.getDimension(2), this.level));
        } else {
            serverPlayerEntity.changeDimension(overworld, (ITeleporter) new ChaosTeleporter(overworld, 0, this.level));
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.removed) {
            return;
        }
        if (this.attack_delay > 0) {
            --this.attack_delay;
        }
        if (this.attack_delay > 0) {
            return;
        }
        this.attack_delay = 20;
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        PlayerEntity e = this.level.getNearestPlayer(this, 1.5);
        if (e != null) {
            this.doHurtTarget(e);
        }
    }
}
