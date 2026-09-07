package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.model.ModelEmperorScorpion;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderEmperorScorpion extends MobRenderer<EmperorScorpion, ModelEmperorScorpion> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/emperorscorpion.png");
    private final float scale;

    public RenderEmperorScorpion(
            EntityRendererProvider.Context context, ModelEmperorScorpion model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(EmperorScorpion entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(EmperorScorpion entity) {
        return TEXTURE;
    }
}
