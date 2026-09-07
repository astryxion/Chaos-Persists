package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.model.ModelBrutalfly;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderBrutalfly extends MobRenderer<Brutalfly, ModelBrutalfly> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/brutalflytexture.png");
    private static final ResourceLocation OVERLAY =
            new ResourceLocation("chaospersists", "textures/entity/brutalfly_overlay2.png");
    private final float scale;

    public RenderBrutalfly(EntityRendererProvider.Context context, ModelBrutalfly model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
        this.addLayer(new BrutalflyOverlayLayer(this));
    }

    @Override
    protected void scale(Brutalfly entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Brutalfly entity) {
        return TEXTURE;
    }

    private static class BrutalflyOverlayLayer extends RenderLayer<Brutalfly, ModelBrutalfly> {
        private final RenderBrutalfly renderer;

        BrutalflyOverlayLayer(RenderBrutalfly renderer) {
            super(renderer);
            this.renderer = renderer;
        }

        @Override
        public void render(
                PoseStack poseStack,
                MultiBufferSource buffer,
                int packedLight,
                Brutalfly entity,
                float limbSwing,
                float limbSwingAmount,
                float partialTicks,
                float ageInTicks,
                float netHeadYaw,
                float headPitch) {
            float scroll = (entity.tickCount + partialTicks) * 0.01f;
            poseStack.pushPose();
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.energySwirl(OVERLAY, scroll % 1.0f, scroll % 1.0f));
            this.renderer
                    .getModel()
                    .renderToBuffer(
                            poseStack,
                            vertexConsumer,
                            packedLight,
                            OverlayTexture.NO_OVERLAY,
                            0.5f,
                            0.5f,
                            0.5f,
                            1.0f);
            poseStack.popPose();
        }
    }
}
