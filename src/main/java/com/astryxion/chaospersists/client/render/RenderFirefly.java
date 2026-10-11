package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.Firefly;
import com.astryxion.chaospersists.client.model.ModelFirefly;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderFirefly extends MobRenderer<Firefly, ModelFirefly> {
    private final float scale;

    public RenderFirefly(EntityRendererProvider.Context context, ModelFirefly model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Firefly entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Firefly entity) {
        return entity.getTexture(entity);
    }
}
