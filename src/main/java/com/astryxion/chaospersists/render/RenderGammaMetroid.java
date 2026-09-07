package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.model.ModelGammaMetroid;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGammaMetroid extends MobRenderer<GammaMetroid, ModelGammaMetroid> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/gammametroid.png");
    private final float scale;

    public RenderGammaMetroid(
            EntityRendererProvider.Context context, ModelGammaMetroid model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(GammaMetroid entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(GammaMetroid entity) {
        return TEXTURE;
    }

    /** 1.12 rendered the full model with GL_BLEND enabled in {@link ModelGammaMetroid}. */
    @Override
    protected RenderType getRenderType(GammaMetroid entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }
}
