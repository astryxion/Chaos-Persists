package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.model.ModelThePrince;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderThePrince extends MobRenderer<ThePrince, ModelThePrince> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/theprincetexture.png");
    private final float scale;

    public RenderThePrince(EntityRendererProvider.Context context, ModelThePrince model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(ThePrince entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(ThePrince entity) {
        return TEXTURE;
    }

    /**
     * 1.7.10 {@code ModelThePrince#render} enabled GL blend and drew the membranes at alpha 0.55.
     * Cutout ignores that alpha, so the wings look solid unless this layer blends.
     */
    @Override
    protected RenderType getRenderType(ThePrince entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }
}
