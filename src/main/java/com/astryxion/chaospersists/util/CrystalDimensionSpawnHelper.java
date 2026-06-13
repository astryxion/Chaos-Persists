package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.world.World;

public final class CrystalDimensionSpawnHelper {

    private CrystalDimensionSpawnHelper() {
    }

    public static boolean isCrystalDimension(World world) {
        return world != null && ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(5)) == world;
    }
}
