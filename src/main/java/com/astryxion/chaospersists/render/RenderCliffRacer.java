package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.CliffRacer;
import com.astryxion.chaospersists.model.ModelCliffRacer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCliffRacer extends MobRenderer<CliffRacer, ModelCliffRacer> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/cliffracertexture.png");
    private final float scale;

    public RenderCliffRacer(EntityRendererProvider.Context context, ModelCliffRacer model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(CliffRacer entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(CliffRacer entity) {
        return TEXTURE;
    }
}
