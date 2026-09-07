package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.QueenHead;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RenderQueenHead extends EntityRenderer<QueenHead> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/thequeentexture.png");

    public RenderQueenHead(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(
            QueenHead entity,
            float entityYaw,
            float partialTicks,
            com.mojang.blaze3d.vertex.PoseStack poseStack,
            net.minecraft.client.renderer.MultiBufferSource buffer,
            int packedLight) {}

    @Override
    public ResourceLocation getTextureLocation(QueenHead entity) {
        return TEXTURE;
    }
}
