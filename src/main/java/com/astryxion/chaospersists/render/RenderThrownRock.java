package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EntityThrownRock;
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
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderThrownRock extends EntityRenderer<EntityThrownRock> {
    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/item/rocksmall.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/item/rock.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/item/rockred.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/item/rockgreen.png");
    private static final ResourceLocation TEXTURE5 =
            new ResourceLocation("chaospersists", "textures/item/rockblue.png");
    private static final ResourceLocation TEXTURE6 =
            new ResourceLocation("chaospersists", "textures/item/rockpurple.png");
    private static final ResourceLocation TEXTURE7 =
            new ResourceLocation("chaospersists", "textures/item/rockspikey.png");
    private static final ResourceLocation TEXTURE8 =
            new ResourceLocation("chaospersists", "textures/item/rocktnt.png");
    private static final ResourceLocation TEXTURE9 =
            new ResourceLocation("chaospersists", "textures/item/rockcrystalred.png");
    private static final ResourceLocation TEXTURE10 =
            new ResourceLocation("chaospersists", "textures/item/rockcrystalgreen.png");
    private static final ResourceLocation TEXTURE11 =
            new ResourceLocation("chaospersists", "textures/item/rockcrystalblue.png");
    private static final ResourceLocation TEXTURE12 =
            new ResourceLocation("chaospersists", "textures/item/rockcrystaltnt.png");

    public RenderThrownRock(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(
            EntityThrownRock entity,
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
                buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entity)));
        drawBillboardQuad(poseStack, vertexConsumer, packedLight);
        poseStack.popPose();
    }

    private static void drawBillboardQuad(PoseStack poseStack, VertexConsumer buffer, int packedLight) {
        float u0 = 0.0f;
        float u1 = 1.0f;
        float v0 = 0.0f;
        float v1 = 1.0f;
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
    public ResourceLocation getTextureLocation(EntityThrownRock entity) {
        return switch (entity.getRockType()) {
            case 2 -> TEXTURE2;
            case 3 -> TEXTURE3;
            case 4 -> TEXTURE4;
            case 5 -> TEXTURE5;
            case 6 -> TEXTURE6;
            case 7 -> TEXTURE7;
            case 8 -> TEXTURE8;
            case 9 -> TEXTURE9;
            case 10 -> TEXTURE10;
            case 11 -> TEXTURE11;
            case 12 -> TEXTURE12;
            default -> TEXTURE1;
        };
    }
}
