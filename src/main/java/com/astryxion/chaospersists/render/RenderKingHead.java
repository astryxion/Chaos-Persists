package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.KingHead;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RenderKingHead extends EntityRenderer<KingHead> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/thekingtexture.png");

    public RenderKingHead(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(
            KingHead entity,
            float entityYaw,
            float partialTicks,
            com.mojang.blaze3d.vertex.PoseStack poseStack,
            net.minecraft.client.renderer.MultiBufferSource buffer,
            int packedLight) {}

    @Override
    public ResourceLocation getTextureLocation(KingHead entity) {
        return TEXTURE;
    }
}
