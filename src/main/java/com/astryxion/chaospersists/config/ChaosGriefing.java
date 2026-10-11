package com.astryxion.chaospersists.config;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.entity.Baryonyx;
import com.astryxion.chaospersists.entity.Beaver;
import com.astryxion.chaospersists.entity.Camarasaurus;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.Gazelle;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.Robot1;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.Termite;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.VelocityRaptor;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

/**
 * Cataclysm-style griefing: config can ignore the {@code mobGriefing} gamerule per mob group.
 */
public final class ChaosGriefing {
    private ChaosGriefing() {}

    public static boolean canGrief(@Nullable Entity entity) {
        Entity source = resolve(entity);
        Level level = source != null ? source.level() : (entity != null ? entity.level() : null);
        boolean rule =
                level != null && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
        if (source instanceof Player) {
            return rule;
        }
        int mode = modeFor(source);
        if (mode == ChaosPackConfig.GRIEF_ALWAYS) {
            return true;
        }
        if (mode == ChaosPackConfig.GRIEF_NEVER) {
            return false;
        }
        return rule;
    }

    @Nullable
    private static Entity resolve(@Nullable Entity entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof Projectile projectile && projectile.getOwner() != null) {
            return projectile.getOwner();
        }
        return entity;
    }

    private static int modeFor(@Nullable Entity entity) {
        if (entity == null) {
            return ChaosPersists.OtherMobsGriefing;
        }
        if (isRobot(entity)) {
            return ChaosPersists.RobotsGriefing;
        }
        if (isBoss(entity)) {
            return ChaosPersists.BossesGriefing;
        }
        if (isCritter(entity)) {
            return ChaosPersists.CrittersGriefing;
        }
        if (ChaosPackConfig.isChaosEntity(entity)) {
            return ChaosPersists.OtherMobsGriefing;
        }
        return ChaosPackConfig.GRIEF_FOLLOW;
    }

    private static boolean isRobot(Entity entity) {
        return entity instanceof Robot1
                || entity instanceof Robot2
                || entity instanceof Robot3
                || entity instanceof Robot4
                || entity instanceof Robot5
                || entity instanceof SpiderRobot;
    }

    private static boolean isBoss(Entity entity) {
        return entity instanceof Godzilla
                || entity instanceof TheKing
                || entity instanceof TheQueen;
    }

    private static boolean isCritter(Entity entity) {
        return entity instanceof Chipmunk
                || entity instanceof Gazelle
                || entity instanceof Beaver
                || entity instanceof Camarasaurus
                || entity instanceof Baryonyx
                || entity instanceof VelocityRaptor
                || entity instanceof CaterKiller
                || entity instanceof Molenoid
                || entity instanceof Termite
                || entity instanceof Alien
                || entity instanceof GammaMetroid;
    }
}
