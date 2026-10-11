package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.client.model.ModelThePrinceAdult;
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
        this.addLayer(new RoyalWingLayer<>(this));
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
     * Body is cutout so it does not write a translucent depth mask over water/clouds.
     * Wing membranes are drawn by {@link RoyalWingLayer} without depth writes.
     */
    @Override
    protected RenderType getRenderType(
            ThePrinceAdult entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityCutoutNoCull(this.getTextureLocation(entity));
    }
}
