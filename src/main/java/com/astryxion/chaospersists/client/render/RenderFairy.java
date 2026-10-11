package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.Fairy;
import com.astryxion.chaospersists.client.model.ModelFairy;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderFairy extends MobRenderer<Fairy, ModelFairy> {
    private final float scale;

    public RenderFairy(EntityRendererProvider.Context context, ModelFairy model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Fairy entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Fairy entity) {
        return entity.getTexture(entity);
    }
}
