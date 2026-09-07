package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.model.ModelThePrinceAdult;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderThePrinceAdult extends MobRenderer<ThePrinceAdult, ModelThePrinceAdult> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/thekingtexture.png");
    private final float scale;

    public RenderThePrinceAdult(
            EntityRendererProvider.Context context, ModelThePrinceAdult model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(ThePrinceAdult entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(ThePrinceAdult entity) {
        return TEXTURE;
    }

    /**
     * 1.7.10 {@code ModelThePrinceAdult#render} enabled GL blend and drew the membranes at alpha 0.55.
     * Cutout ignores that alpha, so the wings look solid unless this layer blends.
     */
    @Override
    protected RenderType getRenderType(
            ThePrinceAdult entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }
}
