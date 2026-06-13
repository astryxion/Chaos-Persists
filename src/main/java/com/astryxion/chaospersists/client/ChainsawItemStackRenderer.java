package com.astryxion.chaospersists.client;

import com.astryxion.chaospersists.client.model.ModelChainsaw;
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
 * 3D animated chainsaw in first/third person; flat JSON model everywhere else (GUI, ground, frame).
 */
public class ChainsawItemStackRenderer extends ItemStackTileEntityRenderer {

    /** Same layout as 1.7 orespawn:Chainsawtexture.png — place at assets/chaospersists/textures/entity/chainsawtexture.png */
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/chainsawtexture.png");

    private final IBakedModel flatModel;
    private final ModelChainsaw modelChainsaw = new ModelChainsaw();

    public ChainsawItemStackRenderer(IBakedModel flatModel) {
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
                renderFirstPerson(matrixStack, buffer, combinedLight, combinedOverlay,
                        transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND);
            } else if (transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND
                    || transform == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
                renderThirdPerson(matrixStack, buffer, combinedLight, combinedOverlay,
                        transform == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
            } else {
                renderFlat(mc, stack, matrixStack, buffer, combinedLight, combinedOverlay);
            }
        } finally {
            TeisrHandTransformHolder.clear();
        }
    }

    /**
     * Hand pose for first person (after vanilla in-hand matrix). Shared with {@link StaticBigWeaponItemStackRenderer}
     * for non-sword weapons (axes, zooka, hammy, chainsaw).
     */
    public static void applyHandFirstPersonTransforms(MatrixStack ms) {
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(110.0f));
        ms.scale(0.18f, 0.18f, 0.18f);
        ms.translate(0.8f, -0.2f, 0.2f);
    }

    /**
     * First person for {@link com.astryxion.chaospersists.model.ModelBertha}. Shallow pitch + strong roll so the
     * blade reads vertical (not a flat banner across the view); scale 0.12 limits FOV blockage vs. chainsaw 0.18.
     */
    public static void applyBerthaSwordFirstPersonTransforms(MatrixStack ms) {
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(50.0f));
        ms.mulPose(Vector3f.ZP.rotationDegrees(52.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-22.0f));
        ms.scale(0.12f, 0.12f, 0.12f);
        ms.translate(0.4f, 0.02f, 0.1f);
    }

    /**
     * First person for {@link com.astryxion.chaospersists.model.ModelSlice} (Slice + Royal Guardian): offset pieces
     * need a touch more yaw than Bertha so the flat faces are not edge-on.
     */
    public static void applySliceStyleSwordFirstPersonTransforms(MatrixStack ms) {
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(54.0f));
        ms.mulPose(Vector3f.ZP.rotationDegrees(50.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-26.0f));
        ms.scale(0.12f, 0.12f, 0.12f);
        ms.translate(0.44f, 0.0f, 0.12f);
    }

    /**
     * First person for Attitude Adjuster ({@link com.astryxion.chaospersists.model.ModelHammy}): the chainsaw pose
     * scales the huge hammer toward the camera and fills the screen; use a smaller scale and upright-style roll.
     */
    public static void applyHammyFirstPersonTransforms(MatrixStack ms) {
        ms.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(46.0f));
        ms.mulPose(Vector3f.ZP.rotationDegrees(48.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-24.0f));
        ms.scale(0.09f, 0.09f, 0.09f);
        ms.translate(0.36f, 0.02f, 0.1f);
    }

    /**
     * Hand pose for third person (after vanilla equipped matrix). Shared with {@link StaticBigWeaponItemStackRenderer}.
     */
    public static void applyHandThirdPersonTransforms(MatrixStack ms) {
        ms.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
        ms.mulPose(Vector3f.YP.rotationDegrees(-35.0f));
        ms.mulPose(Vector3f.XP.rotationDegrees(-15.0f));
        ms.scale(0.18f, 0.18f, 0.18f);
        ms.translate(0.5f, -0.4f, 0.0f);
    }

    private void renderFirstPerson(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay, boolean leftHand) {
        matrixStack.pushPose();
        if (leftHand) {
            matrixStack.scale(-1.0f, 1.0f, 1.0f);
        }
        applyHandFirstPersonTransforms(matrixStack);
        RenderSystem.enableBlend();
        Minecraft.getInstance().getTextureManager().bind(TEXTURE);
        modelChainsaw.renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.popPose();
    }

    private void renderThirdPerson(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay, boolean leftHand) {
        matrixStack.pushPose();
        if (leftHand) {
            matrixStack.scale(-1.0f, 1.0f, 1.0f);
        }
        applyHandThirdPersonTransforms(matrixStack);
        RenderSystem.enableBlend();
        Minecraft.getInstance().getTextureManager().bind(TEXTURE);
        modelChainsaw.renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.popPose();
    }

    private void renderFlat(Minecraft mc, ItemStack stack, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.NONE, true, matrixStack, buffer, combinedLight, combinedOverlay, flatModel);
    }
}
