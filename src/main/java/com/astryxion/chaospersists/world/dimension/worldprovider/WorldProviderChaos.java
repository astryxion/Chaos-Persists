package com.astryxion.chaospersists.world.dimension.worldprovider;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.DimensionType;
import net.minecraft.world.storage.IWorldInfo;

/**
 * Utopia dimension settings (formerly {@code Dimension}).
 * Biome source and chunk generator factories are used from dimension registration.
 */
public class WorldProviderChaos {

    private WorldProviderChaos() {
    }

    public static Biome resolveUtopiaBiome(Registry<Biome> registry) {
        if (ChaosPersists.UTOPIA_BIOME != null) {
            return ChaosPersists.UTOPIA_BIOME;
        }
        Biome registered = registry.get(new ResourceLocation("chaospersists", "utopia"));
        return registered != null ? registered : registry.getOrThrow(Biomes.PLAINS);
    }

    public static SingleBiomeProvider createBiomeSource(Registry<Biome> registry) {
        return new SingleBiomeProvider(resolveUtopiaBiome(registry));
    }

    public static DimensionType getDimensionType(int dimensionId) {
        return ChaosPersists.getDimensionTypeForLegacyId(dimensionId);
    }

    public static String getDimensionName() {
        return "Dimension-Utopia";
    }

    public static boolean canRespawnHere() {
        return true;
    }

    public static boolean isSurfaceWorld() {
        return true;
    }

    public static void setWorldTime(int dimensionId, long time) {
        ServerWorld ws = ChaosPersists.getServerWorldByDimensionId(dimensionId);
        if (ws != null) {
            IWorldInfo w = ws.getLevelData();
            if (w != null) {
                boolean allPlayersAsleep = !ws.players().isEmpty();
                for (ServerPlayerEntity player : ws.players()) {
                    if (!player.isSleeping()) {
                        allPlayersAsleep = false;
                        break;
                    }
                }
                if (time % 24000L > 12000L && allPlayersAsleep) {
                    long i = time + 24000L;
                    i -= i % 24000L;
                    for (int dimId : ChaosPersists.getRegisteredChaosDimensionIds()) {
                        ServerWorld worldServer = ChaosPersists.getServerWorldByDimensionId(dimId);
                        if (worldServer != null) {
                            worldServer.setDayTime(i);
                        }
                    }
                } else {
                    ws.setDayTime(time);
                }
            } else {
                ws.setDayTime(time);
            }
        }
    }
}
