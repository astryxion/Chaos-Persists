package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.item.BandP;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 1.7.10 parity: {@code attackEntityFrom} set both {@code attackTarget} and {@code entityToAttack},
 * so vanilla HurtByTarget could not drop a boss by walking out of 16-block follow range.
 * 1.20 only has one target field, so while the damaging entity is alive we keep that target
 * and raise follow range to the attribute cap.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class BossAggroHandler {
    private static final String NBT_ATTACKER = "ChaosBossAggroAtk";
    /** Vanilla FOLLOW_RANGE max is 2048; this is far enough that running away is not a cheese. */
    private static final double HURT_FOLLOW_RANGE = 1024.0;
    private static final UUID FOLLOW_RANGE_BONUS_ID =
            UUID.fromString("8c3d7e21-4b9a-4f0e-9c1d-2a6b8e5f7d10");

    private BossAggroHandler() {}

    @SubscribeEvent
    public static void onBossHurt(LivingHurtEvent event) {
        if (event == null || event.isCanceled() || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity victim = event.getEntity();
        if (!(victim instanceof Mob boss)
                || victim.level() == null
                || victim.level().isClientSide
                || !isOreSpawnBoss(boss)) {
            return;
        }
        DamageSource source = event.getSource();
        if (source == null) {
            return;
        }
        Entity responsible = source.getEntity();
        if (!(responsible instanceof LivingEntity attacker)
                || attacker == boss
                || !MyUtils.isValidAggroTarget(attacker)) {
            return;
        }
        applyHurtAggro(boss, attacker);
    }

    @SubscribeEvent
    public static void onBossTick(LivingEvent.LivingTickEvent event) {
        if (event == null || event.getEntity() == null) {
            return;
        }
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Mob boss)
                || boss.level() == null
                || boss.level().isClientSide
                || !(boss.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        CompoundTag data = boss.getPersistentData();
        if (!data.hasUUID(NBT_ATTACKER)) {
            return;
        }
        Entity tracked = serverLevel.getEntity(data.getUUID(NBT_ATTACKER));
        if (!(tracked instanceof LivingEntity attacker) || !attacker.isAlive()) {
            resetHurtAggro(boss);
            return;
        }
        if (!MyUtils.isValidAggroTarget(attacker)) {
            resetHurtAggro(boss);
            return;
        }
        applyFollowRangeBonus(boss);
        boss.setTarget(attacker);
        boss.setLastHurtByMob(attacker);
    }

    static boolean isOreSpawnBoss(Entity entity) {
        return entity instanceof EmperorScorpion
                || entity instanceof Robot2
                || entity instanceof Robot3
                || entity instanceof Robot4
                || entity instanceof Robot5
                || entity instanceof GiantRobot
                || entity instanceof Godzilla
                || entity instanceof TheKing
                || entity instanceof TheQueen
                || entity instanceof Kraken
                || entity instanceof HerculesBeetle
                || entity instanceof PitchBlack
                || entity instanceof Basilisk
                || entity instanceof Nastysaurus
                || entity instanceof Alosaurus
                || entity instanceof TRex
                || entity instanceof CaterKiller
                || entity instanceof Hammerhead
                || entity instanceof Molenoid
                || entity instanceof Mothra
                || entity instanceof Vortex
                || entity instanceof Triffid
                || entity instanceof SeaMonster
                || entity instanceof SeaViper
                || entity instanceof SpiderRobot
                || entity instanceof AntRobot
                || entity instanceof BandP
                || entity instanceof Alien
                || entity instanceof Kyuubi
                || entity instanceof WormLarge
                || entity instanceof TrooperBug
                || entity instanceof SpitBug;
    }

    private static void applyHurtAggro(Mob boss, LivingEntity attacker) {
        boss.getPersistentData().putUUID(NBT_ATTACKER, attacker.getUUID());
        applyFollowRangeBonus(boss);
        boss.setTarget(attacker);
        boss.setLastHurtByMob(attacker);
    }

    private static void applyFollowRangeBonus(Mob boss) {
        AttributeInstance follow = boss.getAttribute(Attributes.FOLLOW_RANGE);
        if (follow == null || follow.getModifier(FOLLOW_RANGE_BONUS_ID) != null) {
            return;
        }
        double bonus = HURT_FOLLOW_RANGE - follow.getValue();
        if (bonus <= 0.0) {
            return;
        }
        follow.addTransientModifier(
                new AttributeModifier(
                        FOLLOW_RANGE_BONUS_ID,
                        "OreSpawn boss hurt aggro",
                        bonus,
                        AttributeModifier.Operation.ADDITION));
    }

    private static void resetHurtAggro(Mob boss) {
        boss.getPersistentData().remove(NBT_ATTACKER);
        AttributeInstance follow = boss.getAttribute(Attributes.FOLLOW_RANGE);
        if (follow != null) {
            follow.removeModifier(FOLLOW_RANGE_BONUS_ID);
        }
    }
}
