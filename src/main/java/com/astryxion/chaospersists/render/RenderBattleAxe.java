package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.model.ModelBattleAxe;
import com.astryxion.chaospersists.util.IItemRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class RenderBattleAxe implements IItemRenderer {
    protected ModelBattleAxe modelBattleAxe = new ModelBattleAxe();
    private static final ResourceLocation texture =
            new ResourceLocation("chaospersists", "textures/entity/battleaxetexture.png");

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
        PoseStack poseStack = new PoseStack();
        poseStack.mulPose(Axis.YP.rotationDegrees(50.0f));
        poseStack.mulPose(Axis.XP.rotationDegrees(190.0f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(15.0f));
        poseStack.scale(scale, scale, scale);
        poseStack.translate(x, y, z);
        this.drawModel(poseStack);
    }

    private void renderSwordF5(float x, float y, float z, float scale) {
        PoseStack poseStack = new PoseStack();
        Vector3f axis = new Vector3f(1.0f, 0.25f, 0.0f).normalize();
        poseStack.mulPose(new Quaternionf().rotateAxis((float) Math.toRadians(180.0), axis));
        poseStack.scale(scale, scale, scale);
        poseStack.translate(x, y, z);
        this.drawModel(poseStack);
    }

    private void drawModel(PoseStack poseStack) {
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        var vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
        this.modelBattleAxe.render(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);
        buffer.endBatch();
    }
}
