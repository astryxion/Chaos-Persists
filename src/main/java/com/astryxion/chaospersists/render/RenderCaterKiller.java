package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.model.ModelCaterKiller;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCaterKiller extends MobRenderer<CaterKiller, ModelCaterKiller> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/caterkillertexture.png");
    private final float scale;

    public RenderCaterKiller(EntityRendererProvider.Context context, ModelCaterKiller model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(CaterKiller entity, PoseStack poseStack, float partialTick) {
        float s = this.scale;
        if (entity.getPlayNicely() != 0) {
            s /= 2.0f;
        }
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(CaterKiller entity) {
        return TEXTURE;
    }
}
