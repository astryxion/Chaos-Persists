package com.astryxion.chaospersists.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

/**
 * 3D orespawn weapons in hand: Bertha / Slice / Royal use sword-tuned first- and third-person transforms;
 * other styles reuse {@link ChainsawItemStackRenderer} hand poses (hammy, axes, zooka).
 */
public class StaticBigWeaponItemStackRenderer extends ItemStackTileEntityRenderer {

    @FunctionalInterface
    public interface WeaponModelDraw {
        void draw(MatrixStack matrixStack, com.mojang.blaze3d.vertex.IVertexBuilder buffer, int combinedLight, int combinedOverlay);
    }

    public enum Style {
        BERTHA,
        HAMMY,
        SLICE,
        ROYAL,
        BATTLE_AXE,
        QUEEN_BATTLE_AXE,
        SQUID_ZOOKA
    }

    private final IBakedModel flatModel;
    private final WeaponModelDraw renderModel;
    private final ResourceLocation texture;
    private final Style style;

    public StaticBigWeaponItemStackRenderer(IBakedModel flatModel, ResourceLocation texture, Style style, WeaponModelDraw renderModel) {
        this.flatModel = flatModel;
        this.renderModel = renderModel;
        this.texture = texture;
        this.style = style;
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
                renderHand(matrixStack, buffer, combinedLight, combinedOverlay, true,
                        transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND);
            } else if (transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND
                    || transform == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
                renderHand(matrixStack, buffer, combinedLight, combinedOverlay, false,
                        transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
            } else {
                mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.NONE, true, matrixStack, buffer, combinedLight, combinedOverlay, flatModel);
            }
        } finally {
            TeisrHandTransformHolder.clear();
        }
    }

    private void renderHand(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay, boolean firstPerson, boolean leftHand) {
        matrixStack.pushPose();
        if (leftHand) {
            matrixStack.scale(-1.0f, 1.0f, 1.0f);
        }
        if (firstPerson) {
            if (style == Style.BERTHA) {
                applyBerthaSwordFirstPersonTransformsTuned(matrixStack);
            } else if (style == Style.SLICE || style == Style.ROYAL) {
                applySliceRoyalFirstPersonTransformsTuned(matrixStack);
            } else if (style == Style.HAMMY) {
                ChainsawItemStackRenderer.applyHammyFirstPersonTransforms(matrixStack);
            } else {
                ChainsawItemStackRenderer.applyHandFirstPersonTransforms(matrixStack);
            }
        } else {
            if (style == Style.BERTHA || style == Style.SLICE || style == Style.ROYAL) {
                applySwordThirdPersonTransformsTuned(matrixStack);
            } else {
                ChainsawItemStackRenderer.applyHandThirdPersonTransforms(matrixStack);
            }
        }
        RenderSystem.enableBlend();
        Minecraft.getInstance().getTextureManager().bind(texture);
        renderModel.draw(matrixStack, buffer.getBuffer(RenderType.entityCutoutNoCull(texture)), combinedLight, combinedOverlay);
        matrixStack.popPose();
    }

    /**
     * Sword-only first-person tuning for 1.12.2 TEISR hand matrices.
     * Keep blade visible (not buried at lower-right) while preserving a heavy two-handed feel.
     */
    private static void applyBerthaSwordFirstPersonTransformsTuned(MatrixStack ms) {
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(58.0f));
        ms.mulPose(Vector3f.ZP.rotationDegrees(58.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-20.0f));
        ms.scale(0.19f, 0.19f, 0.19f);
        ms.translate(0.72f, -0.12f, 0.16f);
    }

    private static void applySliceRoyalFirstPersonTransformsTuned(MatrixStack ms) {
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(60.0f));
        ms.mulPose(Vector3f.ZP.rotationDegrees(56.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-24.0f));
        ms.scale(0.19f, 0.19f, 0.19f);
        ms.translate(0.74f, -0.12f, 0.16f);
    }

    /**
     * Sword-only third-person tuning so giant blades sit out to the side like OreSpawn,
     * instead of drooping behind the leg.
     * Extra pitch lifts long blades off the ground (they otherwise read as stabbing the floor).
     */
    private static void applySwordThirdPersonTransformsTuned(MatrixStack ms) {
        ms.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-68.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(-46.0f));
        ms.scale(0.24f, 0.24f, 0.24f);
        ms.translate(0.85f, -0.06f, -0.14f);
    }
}
