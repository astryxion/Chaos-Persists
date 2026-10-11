package com.astryxion.chaospersists.config;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.entity.Bee;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.CaveFisher;
import com.astryxion.chaospersists.entity.CloudShark;
import com.astryxion.chaospersists.entity.Crab;
import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.entity.Cryolophosaurus;
import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.EnderKnight;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.entity.LeafMonster;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.entity.Pointysaurus;
import com.astryxion.chaospersists.entity.Rat;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.Scorpion;
import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.Urchin;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.entity.WormMedium;
import com.astryxion.chaospersists.entity.WormSmall;
import com.astryxion.chaospersists.util.MobStats;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 1.7 applied {@code *_stats} in {@code applyEntityAttributes()} on each spawn. 1.20 bakes
 * attributes once at register time, so live GUI/cfg values are applied when the mob joins.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ChaosMobStatsApplier {
    private static final Map<Class<?>, Supplier<MobStats>> BY_CLASS = new HashMap<>();

    static {
        put(Alien.class, () -> ChaosPersists.Alien_stats);
        put(Alosaurus.class, () -> ChaosPersists.Alosaurus_stats);
        put(AntRobot.class, () -> ChaosPersists.AntRobot_stats);
        put(AttackSquid.class, () -> ChaosPersists.AttackSquid_stats);
        put(Basilisk.class, () -> ChaosPersists.Basilisk_stats);
        put(Bee.class, () -> ChaosPersists.Bee_stats);
        put(Boyfriend.class, () -> ChaosPersists.BandP_stats);
        put(Brutalfly.class, () -> ChaosPersists.Brutalfly_stats);
        put(CaterKiller.class, () -> ChaosPersists.CaterKiller_stats);
        put(CaveFisher.class, () -> ChaosPersists.CaveFisher_stats);
        put(CloudShark.class, () -> ChaosPersists.CloudShark_stats);
        put(Crab.class, () -> ChaosPersists.Crab_stats);
        put(CreepingHorror.class, () -> ChaosPersists.CreepingHorror_stats);
        put(Cryolophosaurus.class, () -> ChaosPersists.Cryolophosaurus_stats);
        put(DungeonBeast.class, () -> ChaosPersists.DungeonBeast_stats);
        put(EmperorScorpion.class, () -> ChaosPersists.EmperorScorpion_stats);
        put(EnderKnight.class, () -> ChaosPersists.EnderKnight_stats);
        put(EnderReaper.class, () -> ChaosPersists.EnderReaper_stats);
        put(GammaMetroid.class, () -> ChaosPersists.GammaMetroid_stats);
        put(GiantRobot.class, () -> ChaosPersists.Jeffery_stats);
        put(Girlfriend.class, () -> ChaosPersists.BandP_stats);
        put(Godzilla.class, () -> ChaosPersists.Godzilla_stats);
        put(Hammerhead.class, () -> ChaosPersists.Hammerhead_stats);
        put(HerculesBeetle.class, () -> ChaosPersists.HerculesBeetle_stats);
        put(Irukandji.class, () -> ChaosPersists.Irukandji_stats);
        put(Kraken.class, () -> ChaosPersists.Kraken_stats);
        put(Kyuubi.class, () -> ChaosPersists.Kyuubi_stats);
        put(LeafMonster.class, () -> ChaosPersists.LeafMonster_stats);
        put(Leon.class, () -> ChaosPersists.Leon_stats);
        put(LurkingTerror.class, () -> ChaosPersists.LurkingTerror_stats);
        put(Mantis.class, () -> ChaosPersists.Mantis_stats);
        put(Molenoid.class, () -> ChaosPersists.Molenoid_stats);
        put(Mothra.class, () -> ChaosPersists.Mothra_stats);
        put(Nastysaurus.class, () -> ChaosPersists.Nastysaurus_stats);
        put(PitchBlack.class, () -> ChaosPersists.PitchBlack_stats);
        put(Pointysaurus.class, () -> ChaosPersists.Pointysaurus_stats);
        put(Rat.class, () -> ChaosPersists.Rat_stats);
        put(Robot2.class, () -> ChaosPersists.Robot2_stats);
        put(Robot3.class, () -> ChaosPersists.Robot3_stats);
        put(Robot4.class, () -> ChaosPersists.Robot4_stats);
        put(Robot5.class, () -> ChaosPersists.Robot5_stats);
        put(Rotator.class, () -> ChaosPersists.Rotator_stats);
        put(Scorpion.class, () -> ChaosPersists.Scorpion_stats);
        put(SeaMonster.class, () -> ChaosPersists.SeaMonster_stats);
        put(SeaViper.class, () -> ChaosPersists.SeaViper_stats);
        put(Skate.class, () -> ChaosPersists.Skate_stats);
        put(SpiderRobot.class, () -> ChaosPersists.SpiderRobot_stats);
        put(SpitBug.class, () -> ChaosPersists.SpitBug_stats);
        put(TRex.class, () -> ChaosPersists.TRex_stats);
        put(TerribleTerror.class, () -> ChaosPersists.TerribleTerror_stats);
        put(TheKing.class, () -> ChaosPersists.TheKing_stats);
        put(TheQueen.class, () -> ChaosPersists.TheQueen_stats);
        put(Triffid.class, () -> ChaosPersists.Triffid_stats);
        put(TrooperBug.class, () -> ChaosPersists.TrooperBug_stats);
        put(Urchin.class, () -> ChaosPersists.Urchin_stats);
        put(Vortex.class, () -> ChaosPersists.Vortex_stats);
        put(WaterDragon.class, () -> ChaosPersists.WaterDragon_stats);
        put(WormLarge.class, () -> ChaosPersists.WormLarge_stats);
        put(WormMedium.class, () -> ChaosPersists.WormMedium_stats);
        put(WormSmall.class, () -> ChaosPersists.WormSmall_stats);
    }

    private ChaosMobStatsApplier() {}

    private static void put(Class<?> type, Supplier<MobStats> stats) {
        BY_CLASS.put(type, stats);
    }

    @SubscribeEvent
    public static void onJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity.level().isClientSide() || !(entity instanceof LivingEntity living)) {
            return;
        }
        Supplier<MobStats> supplier = BY_CLASS.get(living.getClass());
        if (supplier == null) {
            return;
        }
        MobStats stats = supplier.get();
        if (stats == null) {
            return;
        }
        apply(living, stats, !event.loadedFromDisk());
    }

    private static void apply(LivingEntity living, MobStats stats, boolean fillHealth) {
        var health = living.getAttribute(Attributes.MAX_HEALTH);
        if (health != null) {
            health.setBaseValue(stats.health);
        }
        var attack = living.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attack != null) {
            attack.setBaseValue(stats.attack);
        }
        var armor = living.getAttribute(Attributes.ARMOR);
        if (armor != null) {
            armor.setBaseValue(stats.defense);
        }
        float max = living.getMaxHealth();
        if (fillHealth) {
            living.setHealth(max);
        } else if (living.getHealth() > max) {
            living.setHealth(max);
        }
    }
}
