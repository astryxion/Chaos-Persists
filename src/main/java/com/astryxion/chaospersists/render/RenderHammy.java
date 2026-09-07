package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.model.ModelHammy;
import com.astryxion.chaospersists.util.IItemRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class RenderHammy implements IItemRenderer {
    protected ModelHammy modelHammy = new ModelHammy();
    private static final ResourceLocation texture =
            new ResourceLocation("chaospersists", "textures/entity/attitudeadjustertexture.png");

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
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

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type.ordinal()) {
            case 1: {
                this.renderSwordF5(6.0f, -20.0f, -4.0f, 0.15f);
                break;
            }
            case 2: {
                this.renderSword(-10.0f, -13.0f, -5.0f, 0.15f);
                break;
            }
        }
    }

    private void renderSword(float x, float y, float z, float scale) {
        PoseStack poseStack = new PoseStack();
        poseStack.mulPose(Axis.YP.rotationDegrees(70.0f));
        poseStack.mulPose(Axis.XP.rotationDegrees(190.0f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(25.0f));
        poseStack.scale(scale, scale, scale);
        poseStack.translate(x, y, z);
        this.drawModel(poseStack);
    }

    private void renderSwordF5(float x, float y, float z, float scale) {
        PoseStack poseStack = new PoseStack();
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));
        poseStack.scale(scale, scale, scale);
        poseStack.translate(x, y, z);
        this.drawModel(poseStack);
    }

    private void drawModel(PoseStack poseStack) {
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        var vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
        this.modelHammy.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);
        buffer.endBatch();
    }
}
