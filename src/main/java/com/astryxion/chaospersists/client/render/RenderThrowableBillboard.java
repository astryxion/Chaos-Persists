package com.astryxion.chaospersists.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public final class RenderThrowableBillboard extends EntityRenderer<Entity> {
    private final ResourceLocation texture;

    public RenderThrowableBillboard(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        this.texture = texture;
    }

    @Override
    public void render(
            Entity entity,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.15, 0.0);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        float spin = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.ZP.rotationDegrees(spin));
        VertexConsumer vertexConsumer =
                buffer.getBuffer(RenderType.entityCutoutNoCull(this.texture));
        drawBillboardQuad(poseStack, vertexConsumer, packedLight, 0);
        poseStack.popPose();
    }

    private static void drawBillboardQuad(
            PoseStack poseStack, VertexConsumer buffer, int packedLight, int spriteIndex) {
        float u0 = (float) (spriteIndex % 16 * 16) / 16.0f;
        float u1 = (float) (spriteIndex % 16 * 16 + 16) / 16.0f;
        float v0 = (float) (spriteIndex / 16 * 16) / 16.0f;
        float v1 = (float) (spriteIndex / 16 * 16 + 16) / 16.0f;
        float size = 1.0f;
        float hx = 0.5f;
        float hy = 0.25f;
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix = pose.pose();
        Matrix3f normal = pose.normal();
        buffer.vertex(matrix, 0.0f - hx, 0.0f - hy, 0.0f)
                .color(255, 255, 255, 255)
                .uv(u0, v1)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight)
                .normal(normal, 0.0f, 1.0f, 0.0f)
                .endVertex();
        buffer.vertex(matrix, size - hx, 0.0f - hy, 0.0f)
                .color(255, 255, 255, 255)
                .uv(u1, v1)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight)
                .normal(normal, 0.0f, 1.0f, 0.0f)
                .endVertex();
        buffer.vertex(matrix, size - hx, size - hy, 0.0f)
                .color(255, 255, 255, 255)
                .uv(u1, v0)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight)
                .normal(normal, 0.0f, 1.0f, 0.0f)
                .endVertex();
        buffer.vertex(matrix, 0.0f - hx, size - hy, 0.0f)
                .color(255, 255, 255, 255)
                .uv(u0, v0)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight)
                .normal(normal, 0.0f, 1.0f, 0.0f)
                .endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return this.texture;
    }
}
