package com.astryxion.chaospersists.world.dimension.worldprovider;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

/**
 * Mining dimension (formerly {@code WorldProviderChaos2} extends {@code WorldProvider}).
 */
public class WorldProviderChaos2 {

    private WorldProviderChaos2() {
    }

    public static SingleBiomeProvider createBiomeSource(Registry<Biome> registry) {
        Biome biome = ChaosPersists.MINING_BIOME != null ? ChaosPersists.MINING_BIOME : registry.getOrThrow(Biomes.PLAINS);
        return new SingleBiomeProvider(biome);
    }

    public static boolean canRespawnHere() {
        return true;
    }

    public static boolean isSurfaceWorld() {
        return true;
    }

    public static void setWorldTime(long time) {
        ServerWorld ws = ChaosPersists.getServerWorldForDimensionIndex(ServerLifecycleHooks.getCurrentServer(), 2);
        if (ws != null) {
            boolean allPlayersAsleep = !ws.players().isEmpty();
            for (ServerPlayerEntity player : ws.players()) {
                if (!player.isSleeping()) {
                    allPlayersAsleep = false;
                    break;
                }
            }
            if (time % 24000L > 12000L && allPlayersAsleep) {
                long newTime = time + 24000L;
                newTime -= newTime % 24000L;
                for (int dimId : ChaosPersists.getRegisteredChaosDimensionIds()) {
                    ServerWorld worldServer = ChaosPersists.getServerWorldByDimensionId(dimId);
                    if (worldServer != null) {
                        worldServer.setDayTime(newTime);
                    }
                }
                return;
            }
            ws.setDayTime(time);
        }
    }
}
