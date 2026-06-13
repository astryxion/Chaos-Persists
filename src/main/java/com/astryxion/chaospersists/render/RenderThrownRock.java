package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EntityThrownRock;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderThrownRock extends EntityRenderer<EntityThrownRock> {
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/items/rocksmall.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/items/rock.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/items/rockred.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/items/rockgreen.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/items/rockblue.png");
    private static final ResourceLocation texture6 = new ResourceLocation("chaospersists", "textures/items/rockpurple.png");
    private static final ResourceLocation texture7 = new ResourceLocation("chaospersists", "textures/items/rockspikey.png");
    private static final ResourceLocation texture8 = new ResourceLocation("chaospersists", "textures/items/rocktnt.png");
    private static final ResourceLocation texture9 = new ResourceLocation("chaospersists", "textures/items/rockcrystalred.png");
    private static final ResourceLocation texture10 = new ResourceLocation("chaospersists", "textures/items/rockcrystalgreen.png");
    private static final ResourceLocation texture11 = new ResourceLocation("chaospersists", "textures/items/rockcrystalblue.png");
    private static final ResourceLocation texture12 = new ResourceLocation("chaospersists", "textures/items/rockcrystaltnt.png");

    public RenderThrownRock(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(EntityThrownRock entity, float entityYaw, float partialTicks, MatrixStack matrixStack,
            IRenderTypeBuffer buffer, int packedLight) {
        matrixStack.pushPose();
        matrixStack.scale(0.5F, 0.5F, 0.5F);
        float spin = entity.xRotO + (entity.xRot - entity.xRotO) * partialTicks;
        drawBillboardQuad(matrixStack, buffer, packedLight, this.getTextureLocation(entity), 0, spin);
        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    private void drawBillboardQuad(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight,
            ResourceLocation texture, int spriteIndex, float spinDegrees) {
        float u0 = (float)(spriteIndex % 16 * 16 + 0) / 16.0F;
        float u1 = (float)(spriteIndex % 16 * 16 + 16) / 16.0F;
        float v0 = (float)(spriteIndex / 16 * 16 + 0) / 16.0F;
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
        IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityCutout(texture));
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
    public ResourceLocation getTextureLocation(EntityThrownRock entity) {
        if (entity.getRockType() == 1) {
            return texture1;
        }
        if (entity.getRockType() == 2) {
            return texture2;
        }
        if (entity.getRockType() == 3) {
            return texture3;
        }
        if (entity.getRockType() == 4) {
            return texture4;
        }
        if (entity.getRockType() == 5) {
            return texture5;
        }
        if (entity.getRockType() == 6) {
            return texture6;
        }
        if (entity.getRockType() == 7) {
            return texture7;
        }
        if (entity.getRockType() == 8) {
            return texture8;
        }
        if (entity.getRockType() == 9) {
            return texture9;
        }
        if (entity.getRockType() == 10) {
            return texture10;
        }
        if (entity.getRockType() == 11) {
            return texture11;
        }
        if (entity.getRockType() == 12) {
            return texture12;
        }
        return texture1;
    }
}
