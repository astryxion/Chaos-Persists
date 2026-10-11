package com.astryxion.chaospersists.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

/**
 * 3D orespawn weapons in hand; flat JSON model everywhere else (GUI, ground, frame).
 */
public class StaticBigWeaponItemStackRenderer extends BlockEntityWithoutLevelRenderer {

    public enum Style {
        BERTHA,
        HAMMY,
        SLICE,
        ROYAL,
        BATTLE_AXE,
        QUEEN_BATTLE_AXE,
        SQUID_ZOOKA
    }

    @FunctionalInterface
    public interface WeaponModelDraw {
        void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay);
    }

    private final BakedModel flatModel;
    private final WeaponModelDraw renderModel;
    private final ResourceLocation texture;
    private final Style style;

    public StaticBigWeaponItemStackRenderer(
            BakedModel flatModel,
            ResourceLocation texture,
            Style style,
            WeaponModelDraw renderModel) {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.flatModel = flatModel;
        this.renderModel = renderModel;
        this.texture = texture;
        this.style = style;
    }

    public StaticBigWeaponItemStackRenderer(
            BakedModel flatModel,
            ResourceLocation texture,
            Style style,
            WeaponModelDraw renderModel,
            BlockEntityRenderDispatcher dispatcher,
            net.minecraft.client.model.geom.EntityModelSet modelSet) {
        super(dispatcher, modelSet);
        this.flatModel = flatModel;
        this.renderModel = renderModel;
        this.texture = texture;
        this.style = style;
    }

    @Override
    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext ctx,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay) {
        if (isHandContext(ctx)) {
            renderHand(isFirstPerson(ctx), isLeftHand(ctx), poseStack, buffer, packedLight, packedOverlay);
        } else {
            FlatItemModelRenderer.render(flatModel, stack, poseStack, buffer, packedLight, packedOverlay);
        }
    }

    private void renderHand(
            boolean firstPerson,
            boolean leftHand,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay) {
        poseStack.pushPose();
        BigWeaponHandTransforms.apply(
                poseStack, BigWeaponHandTransforms.fromStaticStyle(this.style), firstPerson, leftHand);
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
        this.renderModel.render(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    private static boolean isHandContext(ItemDisplayContext ctx) {
        return ctx == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                || ctx == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND
                || ctx == ItemDisplayContext.THIRD_PERSON_LEFT_HAND
                || ctx == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
    }

    private static boolean isFirstPerson(ItemDisplayContext ctx) {
        return ctx == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                || ctx == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
    }

    private static boolean isLeftHand(ItemDisplayContext ctx) {
        return ctx == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
                || ctx == ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
    }
}
