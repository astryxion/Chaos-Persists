package com.astryxion.chaospersists.compat;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.world.dimension.structure.GroundedJigsawStructure;
import java.lang.reflect.Method;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Witchery Walls only schedules towns in {@code #minecraft:village}. Village Mania uses
 * {@code chaospersists:grounded_jigsaw} instead of vanilla village IDs, so this forwards
 * those starts into Witchery's scheduler when the mod is present.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class WitcheryWallsCompat {

    public static final String WITCHERY_MODID = "witcherywalls";
    private static final String SCHEDULER_CLASS = "com.witcherywalls.worldgen.VillageWallScheduler";
    private static final Logger LOGGER = LogManager.getLogger();

    private static boolean checked;
    private static boolean present;
    private static Method schedule;

    private WitcheryWallsCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(WITCHERY_MODID);
            checked = true;
        }
        return present;
    }

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        if (!isPresent() || !(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        if (!level.dimension().equals(ChaosPersists.getVillageDimensionKey())) {
            return;
        }
        if (!(event.getChunk() instanceof LevelChunk chunk)) {
            return;
        }

        for (StructureStart start : chunk.getAllStarts().values()) {
            if (!start.isValid() || !(start.getStructure() instanceof GroundedJigsawStructure)) {
                continue;
            }
            BlockPos origin =
                    start.getPieces().isEmpty()
                            ? start.getBoundingBox().getCenter()
                            : start.getPieces().get(0).getBoundingBox().getCenter();
            scheduleWalls(level, origin);
            return;
        }
    }

    private static void scheduleWalls(ServerLevel level, BlockPos center) {
        Method method = scheduler();
        if (method == null) {
            return;
        }
        level.getServer()
                .execute(
                        () -> {
                            try {
                                method.invoke(null, level, center);
                            } catch (ReflectiveOperationException e) {
                                LOGGER.debug("Witchery Walls schedule failed at {}", center, e);
                            }
                        });
    }

    private static Method scheduler() {
        if (schedule != null) {
            return schedule;
        }
        try {
            schedule =
                    Class.forName(SCHEDULER_CLASS)
                            .getMethod("schedule", ServerLevel.class, BlockPos.class);
            return schedule;
        } catch (ReflectiveOperationException e) {
            LOGGER.warn("Witchery Walls is loaded but VillageWallScheduler.schedule was not found");
            present = false;
            return null;
        }
    }
}
