package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.SpiderRobot;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Same behavior as OreSpawn 1.7.10: left-click specific robots to dismantle and drop a damaged kit.
 */
public class ItemWrench extends Item {

    public ItemWrench(int i) { super(new Item.Properties()); }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity == null || entity instanceof PlayerEntity) {
            return false;
        }

        boolean spider = entity instanceof SpiderRobot && entity.getPassengers().isEmpty();
        boolean ant = entity instanceof AntRobot && entity.getPassengers().isEmpty();

        if (!spider && !ant) {
            return false;
        }

        if (ant) {
            AntRobot e = (AntRobot) entity;
            if (e.getOwned() == 0 && e.getHealth() / e.getMaxHealth() > 0.5f) {
                return false;
            }
        }

        if (!player.level.isClientSide) {
            if (ant) {
                AntRobot e = (AntRobot) entity;
                if (e.getOwned() == 0) {
                    e.setOwned();
                }
            }
            MobEntity e = (MobEntity) entity;
            float damageTaken = e.getMaxHealth() - e.getHealth();
            e.remove();
            if (spider) {
                dropKit(player.level, e, ChaosPersists.SpiderRobotKit, damageTaken);
            } else {
                dropKit(player.level, e, ChaosPersists.AntRobotKit, damageTaken);
            }
            stack.hurtAndBreak(2, player, (p) -> p.broadcastBreakEvent(net.minecraft.util.Hand.MAIN_HAND));
            clearSlotIfBroken(player, stack);
        }

        playDismantleEffects(player.level, entity);
        return true;
    }

    private static void playDismantleEffects(World world, Entity entity) {
        for (int i = 0; i < 8; ++i) {
            float f1 = world.random.nextFloat() * 3.0f - world.random.nextFloat() * 3.0f;
            float f2 = 0.25f + world.random.nextFloat() * 2.0f;
            float f3 = world.random.nextFloat() * 3.0f - world.random.nextFloat() * 3.0f;
            world.addParticle(ParticleTypes.SMOKE,
                    entity.getX() + f1, entity.getY() + f2, entity.getZ() + f3, 0.0, 0.0, 0.0);
            f1 = world.random.nextFloat() * 3.0f - world.random.nextFloat() * 3.0f;
            f2 = 0.25f + world.random.nextFloat() * 2.0f;
            f3 = world.random.nextFloat() * 3.0f - world.random.nextFloat() * 3.0f;
            world.addParticle(ParticleTypes.EXPLOSION,
                    entity.getX() + f1, entity.getY() + f2, entity.getZ() + f3, 0.0, 0.0, 0.0);
            f1 = world.random.nextFloat() * 3.0f - world.random.nextFloat() * 3.0f;
            f2 = 0.25f + world.random.nextFloat() * 2.0f;
            f3 = world.random.nextFloat() * 3.0f - world.random.nextFloat() * 3.0f;
            world.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F),
                    entity.getX() + f1, entity.getY() + f2, entity.getZ() + f3, 0.0, 0.0, 0.0);
        }
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.5f, 1.5f);
    }

    private static void dropKit(World world, MobEntity e, Item kit, float damageMeta) {
        if (world.isClientSide) {
            return;
        }
        ItemStack drop = new ItemStack(kit, 1);
        drop.setDamageValue((int) damageMeta);
        world.addFreshEntity(new ItemEntity(world, e.getX(), e.getY() + 1.0, e.getZ(), drop));
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
