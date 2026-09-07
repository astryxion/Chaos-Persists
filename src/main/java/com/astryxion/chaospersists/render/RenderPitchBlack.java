package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.model.ModelPitchBlack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderPitchBlack extends MobRenderer<PitchBlack, ModelPitchBlack> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/pitchblacktexture.png");

    public RenderPitchBlack(EntityRendererProvider.Context context, ModelPitchBlack model, float shadow, float scale) {
        super(context, model, shadow * scale);
    }

    @Override
    protected void scale(PitchBlack entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float pscale = entity.getPitchBlackScale();
        poseStack.scale(pscale, pscale, pscale);
    }

    @Override
    public ResourceLocation getTextureLocation(PitchBlack entity) {
        return TEXTURE;
    }
}
