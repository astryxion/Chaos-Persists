package com.astryxion.chaospersists.compat.mobbattle;

import net.minecraftforge.fml.ModList;

/**
 * Mob Battle uses vanilla scoreboard teams: same-team entities are allies, and a teamed
 * mob should only hunt members of other teams ({@code Utils.canTargetEntity}).
 */
public final class MobBattleTeamCompat {

    public static final String MOBBATTLE_MODID = "mobbattle";

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
}
