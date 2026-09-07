package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EnderKnight;
import com.astryxion.chaospersists.model.ModelEnderKnight;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderEnderKnight extends MobRenderer<EnderKnight, ModelEnderKnight> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/enderknighttexture.png");
    private final float scale;

    public RenderEnderKnight(EntityRendererProvider.Context context, ModelEnderKnight model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(EnderKnight entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(EnderKnight entity) {
        return TEXTURE;
    }
}
