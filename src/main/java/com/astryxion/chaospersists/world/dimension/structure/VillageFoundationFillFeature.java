package com.astryxion.chaospersists.world.dimension.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

/** Runs after village pieces place so dirt foundations land in the same generation pass. */
public class VillageFoundationFillFeature extends Feature<NoneFeatureConfiguration> {

    public VillageFoundationFillFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        VillageFoundationFill.fillChunk(context.level(), new ChunkPos(context.origin()));
        return true;
    }
}
