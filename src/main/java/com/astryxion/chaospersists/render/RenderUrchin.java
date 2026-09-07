package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Urchin;
import com.astryxion.chaospersists.model.ModelUrchin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderUrchin extends MobRenderer<Urchin, ModelUrchin> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/urchintexture.png");
    private final float scale;

    public RenderUrchin(EntityRendererProvider.Context context, ModelUrchin model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Urchin entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Urchin entity) {
        return TEXTURE;
    }
}
