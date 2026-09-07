package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.EntityLunaMoth;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.model.ModelButterfly;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
public class RenderButterfly extends MobRenderer<EntityButterfly, ModelButterfly> {
    private static final ResourceLocation CREEPER_ARMOR =
            new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final float scale;

    public RenderButterfly(EntityRendererProvider.Context context, ModelButterfly model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
        this.addLayer(new MothArmorLayer(this));
    }

    @Override
    protected void scale(EntityButterfly entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityButterfly entity) {
        return entity.getTexture(entity);
    }

    private static class MothArmorLayer extends RenderLayer<EntityButterfly, ModelButterfly> {
        private final RenderButterfly renderer;

        MothArmorLayer(RenderButterfly renderer) {
            super(renderer);
            this.renderer = renderer;
        }

        @Override
        public void render(
                PoseStack poseStack,
                MultiBufferSource buffer,
                int packedLight,
                EntityButterfly entity,
                float limbSwing,
                float limbSwingAmount,
                float partialTicks,
                float ageInTicks,
                float netHeadYaw,
                float headPitch) {
            boolean doit = false;
            if (entity instanceof Mothra) {
                doit = true;
            } else if (entity instanceof EntityLunaMoth lunaMoth && lunaMoth.moth_type == 0) {
                doit = true;
            }
            if (!doit) {
                return;
            }
            float scroll = (entity.tickCount + partialTicks) * 0.01f;
            poseStack.pushPose();
            VertexConsumer vertexConsumer =
                    buffer.getBuffer(RenderType.energySwirl(CREEPER_ARMOR, scroll % 1.0f, scroll % 1.0f));
            this.renderer.getModel()
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
