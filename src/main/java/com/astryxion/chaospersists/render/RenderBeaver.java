package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Beaver;
import com.astryxion.chaospersists.model.ModelBeaver;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderBeaver extends MobRenderer<Beaver, ModelBeaver> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/beavertexture.png");
    private final float scale;

    public RenderBeaver(EntityRendererProvider.Context context, ModelBeaver model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Beaver entity, PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Beaver entity) {
        return TEXTURE;
    }
}
