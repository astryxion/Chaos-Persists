package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;

/**
 * OreSpawn 1.7.10 "ZooKeeper Shard": left-click a mob with it to run {@link Mob#enablePersistence()}
 * (mob no longer despawns). Same particles/sound as the original; uses one durability (max damage 1, takes 2 "damage steps").
 */
public class ItemZooKeeper extends Item {

    public ItemZooKeeper(int i) { super(new Item.Properties()); }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity == null) {
            return false;
        }

        playEffects(player, entity);

        if (!(entity instanceof MobEntity)) {
            return false;
        }

        MobEntity e = (MobEntity) entity;
        if (!player.level.isClientSide) {
            e.setPersistenceRequired();
            stack.hurtAndBreak(2, player, (p) -> p.broadcastBreakEvent(net.minecraft.util.Hand.MAIN_HAND));
            clearSlotIfBroken(player, stack);
        }

        return true;
    }

    private static void playEffects(PlayerEntity player, Entity entity) {
        for (int i = 0; i < 8; ++i) {
            float f1 = player.level.random.nextFloat() * 3.0f - player.level.random.nextFloat() * 3.0f;
            float f2 = 0.25f + player.level.random.nextFloat() * 2.0f;
            float f3 = player.level.random.nextFloat() * 3.0f - player.level.random.nextFloat() * 3.0f;
            player.level.addParticle(ParticleTypes.SMOKE,
                    entity.getX() + f1, entity.getY() + f2, entity.getZ() + f3, 0.0, 0.0, 0.0);
            f1 = player.level.random.nextFloat() * 3.0f - player.level.random.nextFloat() * 3.0f;
            f2 = 0.25f + player.level.random.nextFloat() * 2.0f;
            f3 = player.level.random.nextFloat() * 3.0f - player.level.random.nextFloat() * 3.0f;
            player.level.addParticle(ParticleTypes.EXPLOSION,
                    entity.getX() + f1, entity.getY() + f2, entity.getZ() + f3, 0.0, 0.0, 0.0);
            f1 = player.level.random.nextFloat() * 3.0f - player.level.random.nextFloat() * 3.0f;
            f2 = 0.25f + player.level.random.nextFloat() * 2.0f;
            f3 = player.level.random.nextFloat() * 3.0f - player.level.random.nextFloat() * 3.0f;
            player.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F),
                    entity.getX() + f1, entity.getY() + f2, entity.getZ() + f3, 0.0, 0.0, 0.0);
        }
        player.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.5f, 1.5f);
    }

    private static void clearSlotIfBroken(PlayerEntity player, ItemStack stack) {
        if (stack.getCount() > 0) {
            return;
        }
        if (player.getMainHandItem() == stack) {
            player.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
        } else if (player.getOffhandItem() == stack) {
            player.setItemInHand(Hand.OFF_HAND, ItemStack.EMPTY);
        } else {
            player.inventory.setItem(player.inventory.selected, ItemStack.EMPTY);
        }
    }
}
