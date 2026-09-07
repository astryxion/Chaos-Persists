package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Scorpion;
import com.astryxion.chaospersists.model.ModelScorpion;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderScorpion extends MobRenderer<Scorpion, ModelScorpion> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/scorpion.png");
    private final float scale;

    public RenderScorpion(
            EntityRendererProvider.Context context, ModelScorpion model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Scorpion entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Scorpion entity) {
        return TEXTURE;
    }
}
