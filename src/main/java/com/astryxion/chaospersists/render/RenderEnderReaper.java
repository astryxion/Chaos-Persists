package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.model.ModelEnderReaper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderEnderReaper extends MobRenderer<EnderReaper, ModelEnderReaper> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/enderreapertexture.png");
    private final float scale;

    public RenderEnderReaper(EntityRendererProvider.Context context, ModelEnderReaper model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(EnderReaper entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(EnderReaper entity) {
        return TEXTURE;
    }
}
