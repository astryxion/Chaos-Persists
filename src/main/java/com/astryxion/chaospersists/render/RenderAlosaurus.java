package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.model.ModelAlosaurus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderAlosaurus extends MobRenderer<Alosaurus, ModelAlosaurus> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/alosaurus.png");
    private final float scale;

    public RenderAlosaurus(
            EntityRendererProvider.Context context, ModelAlosaurus model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Alosaurus entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Alosaurus entity) {
        return TEXTURE;
    }
}
