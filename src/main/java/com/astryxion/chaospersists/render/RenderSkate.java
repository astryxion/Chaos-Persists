package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.model.ModelSkate;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSkate extends MobRenderer<Skate, ModelSkate> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/skatetexture.png");
    private final float scale;

    public RenderSkate(EntityRendererProvider.Context context, ModelSkate model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Skate entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Skate entity) {
        return TEXTURE;
    }
}
