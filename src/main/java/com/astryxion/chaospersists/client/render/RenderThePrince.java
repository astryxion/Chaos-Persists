package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.client.model.ModelThePrince;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderThePrince extends MobRenderer<ThePrince, ModelThePrince> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/entity/theprincetexture.png");
    private final float scale;

    public RenderThePrince(EntityRendererProvider.Context context, ModelThePrince model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
        this.addLayer(new RoyalWingLayer<>(this));
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
     * Body is cutout so it does not write a translucent depth mask over water/clouds.
     * Wing membranes are drawn by {@link RoyalWingLayer} without depth writes.
     */
    @Override
    protected RenderType getRenderType(ThePrince entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        ResourceLocation tex = this.getTextureLocation(entity);
        if (translucent) {
            return RenderType.itemEntityTranslucentCull(tex);
        } else if (bodyVisible) {
            return RenderType.entityCutoutNoCull(tex);
        } else if (glowing) {
            return RenderType.outline(tex);
        }
        return null;
    }
}
