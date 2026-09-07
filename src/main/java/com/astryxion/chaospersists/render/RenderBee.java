package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Bee;
import com.astryxion.chaospersists.model.ModelBee;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderBee extends MobRenderer<Bee, ModelBee> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/beetexture.png");
    private final float scale;

    public RenderBee(EntityRendererProvider.Context context, ModelBee model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Bee entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Bee entity) {
        return TEXTURE;
    }
}
