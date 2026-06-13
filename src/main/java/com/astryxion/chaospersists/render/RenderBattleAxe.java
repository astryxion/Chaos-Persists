/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  com.astryxion.chaospersists.ModelBattleAxe
 *  com.astryxion.chaospersists.RenderBattleAxe
 *  com.astryxion.chaospersists.RenderBattleAxe$1
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.util.IItemRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import com.astryxion.chaospersists.model.ModelBattleAxe;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RenderBattleAxe
implements IItemRenderer {
    protected ModelBattleAxe modelBattleAxe = new ModelBattleAxe();
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/battleaxetexture.png");

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
                this.renderSwordF5(3.0f, -8.0f, -2.0f, 0.35f);
                break;
            }
            case 2: {
                this.renderSword(-2.0f, -4.0f, -6.0f, 0.35f);
                break;
            }
        }
    }

    private void renderSword(float x, float y, float z, float scale) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(50.0F));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(190.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(15.0F));
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(x, y, z);
        this.drawModel(matrixStack);
        matrixStack.popPose();
    }

    private void renderSwordF5(float x, float y, float z, float scale) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.mulPose(new net.minecraft.util.math.vector.Quaternion(new Vector3f(1.0F, 0.25F, 0.0F), (float)Math.toRadians(180.0F), true));
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(x, y, z);
        this.drawModel(matrixStack);
        matrixStack.popPose();
    }

    private void drawModel(MatrixStack matrixStack) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bind(texture);
        IVertexBuilder vertexBuilder = mc.renderBuffers().bufferSource().getBuffer(RenderType.entityCutoutNoCull(texture));
        this.modelBattleAxe.renderToBuffer(matrixStack, vertexBuilder, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderBuffers().bufferSource().endBatch();
    }
}
