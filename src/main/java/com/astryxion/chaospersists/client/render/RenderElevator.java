package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.item.Elevator;
import com.astryxion.chaospersists.client.model.ModelElevator;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RenderElevator extends EntityRenderer<Elevator> {
    private final ModelElevator model = new ModelElevator();

    public RenderElevator(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.25f;
    }

    @Override
    public void render(
            Elevator entity,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.0, 0.0);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - entityYaw));
        float f2 = (float) entity.getTimeSinceHit() - partialTicks;
        float f3 = entity.getDamageTaken() - partialTicks;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f2 > 0.0f) {
            poseStack.mulPose(
                    Axis.XP.rotation(
                            Mth.sin(f2) * f2 * f3 / 10.0f * (float) entity.getForwardDirection()));
        }
        float f4 = 0.75f;
        poseStack.scale(f4, f4, f4);
        poseStack.scale(1.0f / f4, 1.0f / f4, 1.0f / f4);
        poseStack.scale(-1.0f, -1.0f, 1.0f);
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
        this.model.setupAnim(entity, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f);
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Elevator entity) {
        return entity.getTexture();
    }
}
