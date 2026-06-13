package com.astryxion.chaospersists.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Same approach as {@link RenderThrownRock}: single flat texture billboard (full 16×16 sheet UVs),
 * which is reliable in 1.12 unlike {@link net.minecraft.client.renderer.RenderItem} inside entity passes.
 */
@OnlyIn(Dist.CLIENT)
public final class RenderThrowableBillboard extends EntityRenderer<Entity> {

    private final ResourceLocation texture;

    public RenderThrowableBillboard(EntityRendererManager manager, ResourceLocation texture) {
        super(manager);
        this.texture = texture;
    }

    @Override
    public void render(Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack,
            IRenderTypeBuffer buffer, int packedLight) {
        matrixStack.pushPose();
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        float spin = entity.xRotO + (entity.xRot - entity.xRotO) * partialTicks;
        drawBillboardQuad(matrixStack, buffer, packedLight, 0, spin);
        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    /** spriteIndex 0 uses the whole texture (same math as {@link RenderThrownRock}). */
    private void drawBillboardQuad(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, int spriteIndex,
            float spinDegrees) {
        float u0 = (float)(spriteIndex % 16 * 16) / 16.0F;
        float u1 = (float)(spriteIndex % 16 * 16 + 16) / 16.0F;
        float v0 = (float)(spriteIndex / 16 * 16) / 16.0F;
        float v1 = (float)(spriteIndex / 16 * 16 + 16) / 16.0F;
        float size = 1.0F;
        float hx = 0.5F;
        float hy = 0.25F;
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - this.entityRenderDispatcher.camera.getYRot()));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(-this.entityRenderDispatcher.camera.getXRot()));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(spinDegrees));
        MatrixStack.Entry entry = matrixStack.last();
        Matrix4f matrix4f = entry.pose();
        Matrix3f matrix3f = entry.normal();
        IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityCutout(this.texture));
        billboardVertex(vertexBuilder, matrix4f, matrix3f, packedLight, 0.0F - hx, 0.0F - hy, u0, v1);
        billboardVertex(vertexBuilder, matrix4f, matrix3f, packedLight, size - hx, 0.0F - hy, u1, v1);
        billboardVertex(vertexBuilder, matrix4f, matrix3f, packedLight, size - hx, size - hy, u1, v0);
        billboardVertex(vertexBuilder, matrix4f, matrix3f, packedLight, 0.0F - hx, size - hy, u0, v0);
        matrixStack.popPose();
    }

    private static void billboardVertex(IVertexBuilder builder, Matrix4f pose, Matrix3f normal, int packedLight,
            float x, float y, float u, float v) {
        builder.vertex(pose, x, y, 0.0F).color(255, 255, 255, 255).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return this.texture;
    }
}
