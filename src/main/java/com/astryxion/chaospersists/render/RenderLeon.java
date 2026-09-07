package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.model.ModelLeon;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderLeon extends MobRenderer<Leon, ModelLeon> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/leon.png");
    private final float scale;

    public RenderLeon(EntityRendererProvider.Context context, ModelLeon model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Leon entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float s = this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Leon entity) {
        return TEXTURE;
    }
}
