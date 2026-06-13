package com.astryxion.chaospersists.world.dimension.worldprovider;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

/** Village Mania dimension (formerly {@code WorldProviderChaos3}). */
public class WorldProviderChaos3 {

    private WorldProviderChaos3() {
    }

    private static Biome resolveVillageBiome(Registry<Biome> registry) {
        if (ChaosPersists.VILLAGE_BIOME != null) {
            return ChaosPersists.VILLAGE_BIOME;
        }
        Biome registered = registry.get(new ResourceLocation("chaospersists", "village_dimension"));
        return registered != null ? registered : registry.getOrThrow(Biomes.PLAINS);
    }

    public static SingleBiomeProvider createBiomeSource(Registry<Biome> registry) {
        return new SingleBiomeProvider(resolveVillageBiome(registry));
    }

    public static String getDimensionName() {
        return "Dimension-VillageMania";
    }

    public static boolean canRespawnHere() {
        return true;
    }

    public static boolean isSurfaceWorld() {
        return true;
    }

    public static void setWorldTime(long time) {
        ServerWorld ws = ChaosPersists.getServerWorldForDimensionIndex(ServerLifecycleHooks.getCurrentServer(), 3);
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
            } else {
                ws.setDayTime(time);
            }
        }
    }
}
