package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.Crab;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.EnderKnight;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.GodzillaHead;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.entity.Pointysaurus;
import com.astryxion.chaospersists.entity.Robot1;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.item.BandP;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;

/**
 * Named OreSpawn / Chaos Persists bosses and minibosses. Pets and trash mobs are not included.
 */
public final class ChaosBosses {

    private ChaosBosses() {}

    public static boolean isBoss(@Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        if (MyUtils.isRoyalty(entity)) {
            return true;
        }
        return entity instanceof EmperorScorpion
                || entity instanceof HerculesBeetle
                || entity instanceof TrooperBug
                || entity instanceof SpitBug
                || entity instanceof Godzilla
                || entity instanceof GodzillaHead
                || entity instanceof TheKing
                || entity instanceof TheQueen
                || entity instanceof Kraken
                || entity instanceof PitchBlack
                || entity instanceof Basilisk
                || entity instanceof Nastysaurus
                || entity instanceof Alosaurus
                || entity instanceof TRex
                || entity instanceof CaterKiller
                || entity instanceof Hammerhead
                || entity instanceof Molenoid
                || entity instanceof Mothra
                || entity instanceof Brutalfly
                || entity instanceof Vortex
                || entity instanceof Triffid
                || entity instanceof SeaMonster
                || entity instanceof SeaViper
                || entity instanceof SpiderRobot
                || entity instanceof AntRobot
                || entity instanceof GiantRobot
                || entity instanceof Robot1
                || entity instanceof Robot2
                || entity instanceof Robot3
                || entity instanceof Robot4
                || entity instanceof Robot5
                || entity instanceof BandP
                || entity instanceof Alien
                || entity instanceof Kyuubi
                || entity instanceof WormLarge
                || entity instanceof Cephadrome
                || entity instanceof WaterDragon
                || entity instanceof Dragon
                || entity instanceof Spyro
                || entity instanceof Leon
                || entity instanceof GammaMetroid
                || entity instanceof Crab
                || entity instanceof Mantis
                || entity instanceof Pointysaurus
                || entity instanceof EnderReaper
                || entity instanceof EnderKnight
                || entity instanceof DungeonBeast
                || entity instanceof Rotator;
    }
}
