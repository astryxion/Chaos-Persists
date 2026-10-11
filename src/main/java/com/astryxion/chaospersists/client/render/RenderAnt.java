package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.client.model.ModelAnt;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderAnt extends MobRenderer<EntityAnt, ModelAnt> {
    private final float scale;

    public RenderAnt(EntityRendererProvider.Context context, ModelAnt model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(EntityAnt entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityAnt entity) {
        return entity.getTexture(entity);
    }
}
