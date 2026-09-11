package com.astryxion.chaospersists.world.biome;

import com.astryxion.chaospersists.legacy.forge.fml.common.registry.EntityRegistry;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

/**
 * Alex's Mobs {@code am_mob_spawns} pattern: one global Forge biome modifier, Java-side biome
 * filtering via {@link LegacyBiomeMatcher}. Overworld spawns are applied here only; runtime
 * re-injection is disabled for the overworld in {@link DimensionSpawnPopulation}.
 */
public record LegacyOverworldSpawnBiomeModifier() implements BiomeModifier {
  public static final LegacyOverworldSpawnBiomeModifier INSTANCE = new LegacyOverworldSpawnBiomeModifier();

  public static Codec<LegacyOverworldSpawnBiomeModifier> makeCodec() {
    return Codec.unit(INSTANCE);
  }

  @Override
  public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    if (phase != Phase.ADD) {
      return;
    }
    if (LegacyBiomeMatcher.isModBiome(biome)) {
      return;
    }
    EntityRegistry.applyLegacySpawns(biome, builder.getMobSpawnSettings());
  }

  @Override
  public Codec<? extends BiomeModifier> codec() {
    return ChaosPersists.LEGACY_OVERWORLD_SPAWNS.get();
  }
}
