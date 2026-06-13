package com.astryxion.chaospersists.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Flat handheld hammer texture with in-hand scale/pose tuned separately from {@link ChainsawItemStackRenderer}
 * (swords/axes use that path; hammer head is offset to reduce clipping through the player).
 */
public class BigHammerItemStackRenderer extends ItemStackTileEntityRenderer {

    private final IBakedModel flatModel;

    public BigHammerItemStackRenderer(IBakedModel flatModel) {
        this.flatModel = flatModel;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        ItemCameraTransforms.TransformType transform = TeisrHandTransformHolder.get();
        if (transform == null) {
            transform = transformType;
        }
        try {
            Minecraft mc = Minecraft.getInstance();
            if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND
                    || transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                boolean left = transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND;
                matrixStack.pushPose();
                if (left) {
                    matrixStack.scale(-1.0f, 1.0f, 1.0f);
                }
                applyFirstPerson(matrixStack);
                mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.NONE, true, matrixStack, buffer, combinedLight, combinedOverlay, flatModel);
                matrixStack.popPose();
            } else if (transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND
                    || transform == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
                boolean left = transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND;
                matrixStack.pushPose();
                if (left) {
                    matrixStack.scale(-1.0f, 1.0f, 1.0f);
                }
                applyThirdPerson(matrixStack);
                mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.NONE, true, matrixStack, buffer, combinedLight, combinedOverlay, flatModel);
                matrixStack.popPose();
            } else {
                mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.NONE, true, matrixStack, buffer, combinedLight, combinedOverlay, flatModel);
            }
        } finally {
            TeisrHandTransformHolder.clear();
        }
    }

    private static void applyFirstPerson(MatrixStack ms) {
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(102.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-8.0f));
        ms.scale(0.32f, 0.32f, 0.32f);
        ms.translate(0.55f, -0.12f, 0.18f);
    }

    private static void applyThirdPerson(MatrixStack ms) {
        ms.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-38.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(-22.0f));
        ms.scale(0.38f, 0.38f, 0.38f);
        ms.translate(0.42f, -0.42f, 0.52f);
    }
}
