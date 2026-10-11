package com.astryxion.chaospersists.client.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.function.Function;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

/**
 * Translucent entity quads that do not write the depth buffer. 1.7.10 royal dragons blended wing
 * membranes with GL_BLEND after an opaque body pass; {@link RenderType#entityTranslucent} writes
 * depth and makes water/clouds disappear behind the wings.
 */
public abstract class ChaosRenderTypes extends RenderType {
    private static final Function<ResourceLocation, RenderType> ENTITY_TRANSLUCENT_NO_DEPTH =
            Util.memoize(ChaosRenderTypes::createEntityTranslucentNoDepth);

    private ChaosRenderTypes(
            String name,
            VertexFormat format,
            VertexFormat.Mode mode,
            int bufferSize,
            boolean crumbling,
            boolean sortOnUpload,
            Runnable setup,
            Runnable clear) {
        super(name, format, mode, bufferSize, crumbling, sortOnUpload, setup, clear);
    }

    public static RenderType entityTranslucentNoDepth(ResourceLocation texture) {
        return ENTITY_TRANSLUCENT_NO_DEPTH.apply(texture);
    }

    private static RenderType createEntityTranslucentNoDepth(ResourceLocation texture) {
        CompositeState state =
                CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_SHADER)
                        .setTextureState(new TextureStateShard(texture, false, false))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .setWriteMaskState(COLOR_WRITE)
                        .createCompositeState(true);
        return create(
                "chaospersists_entity_translucent_no_depth",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true,
                true,
                state);
    }
}
