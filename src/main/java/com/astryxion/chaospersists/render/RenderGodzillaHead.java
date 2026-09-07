package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.GodzillaHead;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RenderGodzillaHead extends EntityRenderer<GodzillaHead> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/godzillatexture.png");

    public RenderGodzillaHead(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(
            GodzillaHead entity,
            float entityYaw,
            float partialTicks,
            com.mojang.blaze3d.vertex.PoseStack poseStack,
            net.minecraft.client.renderer.MultiBufferSource buffer,
            int packedLight) {}

    @Override
    public ResourceLocation getTextureLocation(GodzillaHead entity) {
        return TEXTURE;
    }
}
