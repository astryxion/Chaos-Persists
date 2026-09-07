package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.model.ModelCephadrome;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCephadrome extends MobRenderer<Cephadrome, ModelCephadrome> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/cephadrome.png");
    private final float scale;

    public RenderCephadrome(
            EntityRendererProvider.Context context, ModelCephadrome model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Cephadrome entity, PoseStack poseStack, float partialTick) {
        float s = this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    protected RenderType getRenderType(Cephadrome entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityCutoutNoCull(this.getTextureLocation(entity));
    }

    @Override
    public ResourceLocation getTextureLocation(Cephadrome entity) {
        return TEXTURE;
    }
}
