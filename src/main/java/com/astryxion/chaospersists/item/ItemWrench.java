package com.astryxion.chaospersists.item;

import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.SpiderRobot;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

/**
 * Same behavior as OreSpawn 1.7.10: left-click specific robots to dismantle and drop a damaged kit.
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ItemWrench extends Item {
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public ItemWrench(int i) {
        super(new Properties().durability(100));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return tryDismantle(player, entity, stack, InteractionHand.MAIN_HAND);
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        if (player.level().isClientSide) {
            return;
        }
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(ChaosPersists.MyWrench) && tryDismantle(player, target, stack, hand)) {
                event.setCanceled(true);
                return;
            }
        }
    }

    /**
     * Dismantles a spider or ant robot. Wild ants must be at or below 50% health (1.7.10 parity).
     *
     * @return true when the interaction should cancel vanilla attack / count as handled
     */
    public static boolean tryDismantle(
            Player player, Entity entity, ItemStack stack, InteractionHand hand) {
        if (entity == null || entity instanceof Player || entity.isRemoved()) {
            return false;
        }
        if (!stack.is(ChaosPersists.MyWrench)) {
            return false;
        }

        boolean spider = entity instanceof SpiderRobot && entity.getPassengers().isEmpty();
        boolean ant = entity instanceof AntRobot && entity.getPassengers().isEmpty();
        if (!spider && !ant) {
            return false;
        }

        if (ant) {
            AntRobot antRobot = (AntRobot) entity;
            if (antRobot.getOwned() == 0
                    && antRobot.getHealth() > antRobot.getMaxHealth() * 0.5f) {
                return false;
            }
        }

        Level level = player.level();
        if (level.isClientSide) {
            return true;
        }

        if (ant) {
            AntRobot antRobot = (AntRobot) entity;
            if (antRobot.getOwned() == 0) {
                antRobot.setOwned();
            }
        }
        LivingEntity living = (LivingEntity) entity;
        float damageTaken = living.getMaxHealth() - living.getHealth();
        living.discard();
        if (spider) {
            dropKit(level, living, ChaosPersists.SpiderRobotKit, damageTaken);
        } else {
            dropKit(level, living, ChaosPersists.AntRobotKit, damageTaken);
        }
        stack.hurtAndBreak(2, player, net.minecraft.world.entity.LivingEntity.getSlotForHand(hand));
        clearSlotIfBroken(player, stack, hand);
        playDismantleEffects(level, entity);
        return true;
    }

    private static void playDismantleEffects(Level level, Entity entity) {
        if (!(level instanceof ServerLevel server)) {
            return;
        }
        for (int i = 0; i < 8; ++i) {
            float f1 = server.random.nextFloat() * 3.0f - server.random.nextFloat() * 3.0f;
            float f2 = 0.25f + server.random.nextFloat() * 2.0f;
            float f3 = server.random.nextFloat() * 3.0f - server.random.nextFloat() * 3.0f;
            server.sendParticles(
                    ParticleTypes.SMOKE,
                    entity.getX() + f1,
                    entity.getY() + f2,
                    entity.getZ() + f3,
                    1,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            f1 = server.random.nextFloat() * 3.0f - server.random.nextFloat() * 3.0f;
            f2 = 0.25f + server.random.nextFloat() * 2.0f;
            f3 = server.random.nextFloat() * 3.0f - server.random.nextFloat() * 3.0f;
            server.sendParticles(
                    ParticleTypes.EXPLOSION,
                    entity.getX() + f1,
                    entity.getY() + f2,
                    entity.getZ() + f3,
                    1,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            f1 = server.random.nextFloat() * 3.0f - server.random.nextFloat() * 3.0f;
            f2 = 0.25f + server.random.nextFloat() * 2.0f;
            f3 = server.random.nextFloat() * 3.0f - server.random.nextFloat() * 3.0f;
            server.sendParticles(
                    RED_DUST,
                    entity.getX() + f1,
                    entity.getY() + f2,
                    entity.getZ() + f3,
                    1,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
        }
        server.playSound(
                null,
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                SoundEvents.GENERIC_EXPLODE.value(),
                SoundSource.PLAYERS,
                0.5f,
                1.5f);
    }

    private static void dropKit(Level level, LivingEntity e, Item kit, float damageMeta) {
        if (level.isClientSide) {
            return;
        }
        ItemStack drop = new ItemStack(kit, 1);
        drop.setDamageValue((int) damageMeta);
        level.addFreshEntity(new ItemEntity(level, e.getX(), e.getY() + 1.0, e.getZ(), drop));
    }

    private static void clearSlotIfBroken(Player player, ItemStack stack, InteractionHand hand) {
        if (!stack.isEmpty()) {
            return;
        }
        player.setItemInHand(hand, ItemStack.EMPTY);
    }
}
