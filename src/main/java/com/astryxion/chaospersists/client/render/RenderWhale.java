package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.Whale;
import com.astryxion.chaospersists.client.model.ModelWhale;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderWhale extends MobRenderer<Whale, ModelWhale> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/whaletexture.png");
    private final float scale;

    public RenderWhale(EntityRendererProvider.Context context, ModelWhale model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Whale entity, PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Whale entity) {
        return TEXTURE;
    }
}
