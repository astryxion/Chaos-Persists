package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.Cockateil;
import com.astryxion.chaospersists.client.model.ModelCockateil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCockateil extends MobRenderer<Cockateil, ModelCockateil> {
    private final float scale;

    public RenderCockateil(EntityRendererProvider.Context context, ModelCockateil model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Cockateil entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Cockateil entity) {
        return entity.getTexture();
    }
}
