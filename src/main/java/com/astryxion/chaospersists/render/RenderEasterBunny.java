package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EasterBunny;
import com.astryxion.chaospersists.model.ModelEasterBunny;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderEasterBunny extends MobRenderer<EasterBunny, ModelEasterBunny> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/easterbunnytexture.png");
    private final float scale;

    public RenderEasterBunny(EntityRendererProvider.Context context, ModelEasterBunny model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(EasterBunny entity, PoseStack poseStack, float partialTick) {
        float s = this.scale;
        if (entity.isBaby()) {
            s /= 2.0f;
        }
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(EasterBunny entity) {
        return TEXTURE;
    }
}
