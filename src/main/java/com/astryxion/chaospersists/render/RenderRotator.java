package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.model.ModelRotator;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRotator extends MobRenderer<Rotator, ModelRotator> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/rotatortexture.png");
    private final float scale;

    public RenderRotator(EntityRendererProvider.Context context, ModelRotator model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Rotator entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Rotator entity) {
        return TEXTURE;
    }
}
