/*
 * Decompiled with CFR 0_125.
 */
package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.util.IItemRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import com.astryxion.chaospersists.model.ModelSquidZooka;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RenderSquidZooka
implements IItemRenderer {
    protected ModelSquidZooka modelSquidZooka = new ModelSquidZooka();
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/squidzookatexture.png");

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
                this.renderSwordF5(2.0f, 8.0f, 2.0f, 0.35f);
                break;
            }
            case 2: {
                this.renderSword(4.0f, 2.0f, 2.0f, 0.35f);
                break;
            }
        }
    }

    private void renderSword(float x, float y, float z, float scale) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-30F));
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(x, y, z);
        this.drawModel(matrixStack);
        matrixStack.popPose();
    }

    private void renderSwordF5(float x, float y, float z, float scale) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(30F));
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(x, y, z);
        this.drawModel(matrixStack);
        matrixStack.popPose();
    }

    private void drawModel(MatrixStack matrixStack) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bind(texture);
        IVertexBuilder vertexBuilder = mc.renderBuffers().bufferSource().getBuffer(RenderType.entityCutoutNoCull(texture));
        this.modelSquidZooka.renderToBuffer(matrixStack, vertexBuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderBuffers().bufferSource().endBatch();
    }
}