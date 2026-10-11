package com.astryxion.chaospersists.client.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;

/**
 * Marks an item as using a custom in-hand renderer and records per-item {@link BlockEntityWithoutLevelRenderer}
 * instances for {@link net.minecraftforge.client.extensions.common.IClientItemExtensions}.
 */
public class TeisrHandBakedModelWrapper implements BakedModel {

    private static final Map<Item, BlockEntityWithoutLevelRenderer> CUSTOM_RENDERERS = new HashMap<>();

    private final BakedModel inner;

    public TeisrHandBakedModelWrapper(BakedModel inner) {
        this.inner = inner;
    }

    public static void registerCustomRenderer(Item item, BlockEntityWithoutLevelRenderer renderer) {
        CUSTOM_RENDERERS.put(item, renderer);
    }

    @Nullable
    public static BlockEntityWithoutLevelRenderer getCustomRenderer(Item item) {
        return CUSTOM_RENDERERS.get(item);
    }

    public BakedModel getInner() {
        return inner;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand) {
        return inner.getQuads(state, side, rand);
    }

    @Override
    public List<BakedQuad> getQuads(
            @Nullable BlockState state,
            @Nullable Direction side,
            RandomSource rand,
            ModelData data,
            @Nullable net.minecraft.client.renderer.RenderType renderType) {
        return inner.getQuads(state, side, rand, data, renderType);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return inner.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return inner.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return inner.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return inner.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return inner.getOverrides();
    }

    @Override
    public ItemTransforms getTransforms() {
        return inner.getTransforms();
    }
}
