/*
 * Decompiled with CFR 0_125.
 */
package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.util.IItemRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import com.astryxion.chaospersists.model.ModelSlice;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RenderRoyal
implements IItemRenderer {
    protected ModelSlice modelSlice = new ModelSlice();
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/royaltexture.png");

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        switch (type.ordinal()) {
            case 1: {
                return true;
            }
            case 2: {
                return true;
            }
        }
        return false;
    }

public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

public /* varargs */ void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        switch (type.ordinal()) {
            case 1: {
                this.renderSwordF5(-4.0f, 2.0f, -3.0f, 0.35f);
                break;
            }
            case 2: {
                this.renderSword(6.0f, 3.0f, -5.0f, 0.35f);
                break;
            }
        }
    }

    private void renderSword(float x, float y, float z, float scale) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(190F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(25F));
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(x, y, z);
        this.drawModel(matrixStack);
        matrixStack.popPose();
    }

    private void renderSwordF5(float x, float y, float z, float scale) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(-90F));
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(x, y, z);
        this.drawModel(matrixStack);
        matrixStack.popPose();
    }

    private void drawModel(MatrixStack matrixStack) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bind(texture);
        IVertexBuilder vertexBuilder = mc.renderBuffers().bufferSource().getBuffer(RenderType.entityCutoutNoCull(texture));
        this.modelSlice.renderToBuffer(matrixStack, vertexBuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderBuffers().bufferSource().endBatch();
    }
}