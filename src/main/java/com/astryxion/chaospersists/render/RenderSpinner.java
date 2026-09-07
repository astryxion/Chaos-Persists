package com.astryxion.chaospersists.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
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

public class RenderSpinner extends EntityRenderer<Entity> {
    public int spinItemIconIndex = 160;
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/spinners.png");

    public RenderSpinner(EntityRendererProvider.Context context) {
        super(context);
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
        poseStack.scale(0.5f, 0.5f, 0.5f);
        float pitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
        Camera camera = this.entityRenderDispatcher.camera;
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - camera.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(-camera.getXRot()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(pitch));
        VertexConsumer vertexConsumer =
                buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity)));
        this.drawSpinnerQuad(poseStack, vertexConsumer, packedLight, this.spinItemIconIndex);
        poseStack.popPose();
    }

    protected void drawSpinnerQuad(
            PoseStack poseStack, VertexConsumer buffer, int packedLight, int spriteIndex) {
        float u0 = (float) (spriteIndex % 16 * 16 + 0) / 256.0f;
        float u1 = (float) (spriteIndex % 16 * 16 + 16) / 256.0f;
        float v0 = (float) (spriteIndex / 16 * 16 + 0) / 256.0f;
        float v1 = (float) (spriteIndex / 16 * 16 + 16) / 256.0f;
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
        return TEXTURE;
    }
}
