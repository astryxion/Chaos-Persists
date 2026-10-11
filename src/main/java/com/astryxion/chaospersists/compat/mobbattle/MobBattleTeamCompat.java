package com.astryxion.chaospersists.compat.mobbattle;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyUtils;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

/**
 * Mob Battle uses vanilla scoreboard teams: same-team entities are allies, and a teamed
 * mob should only hunt members of other teams ({@code Utils.canTargetEntity}).
 *
 * <p>King / Queen / Mobzilla fireballs still explode with a null source (1.7.10), so vanilla
 * friendly-fire never applies. Strip scoreboard allies out of that blast.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class MobBattleTeamCompat {

    public static final String MOBBATTLE_MODID = "mobbattle";

    private static final ThreadLocal<LivingEntity> EXPLOSION_SHOOTER = new ThreadLocal<>();

    private static boolean checked;
    private static boolean present;

    private MobBattleTeamCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(MOBBATTLE_MODID);
            checked = true;
        }
        return present;
    }

    public static void runShooterExplosion(@Nullable LivingEntity shooter, Runnable explode) {
        if (explode == null) {
            return;
        }
        if (shooter == null) {
            explode.run();
            return;
        }
        EXPLOSION_SHOOTER.set(shooter);
        try {
            explode.run();
        } finally {
            EXPLOSION_SHOOTER.remove();
        }
    }

    @SubscribeEvent
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        LivingEntity shooter = EXPLOSION_SHOOTER.get();
        if (shooter == null || event == null || event.getAffectedEntities() == null) {
            return;
        }
        event.getAffectedEntities()
                .removeIf(
                        entity ->
                                entity instanceof LivingEntity living
                                        && MyUtils.isScoreboardAlly(shooter, living));
    }
}
