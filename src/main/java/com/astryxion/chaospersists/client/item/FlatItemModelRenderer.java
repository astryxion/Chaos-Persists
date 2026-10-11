package com.astryxion.chaospersists.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;

/**
 * Renders the unwrapped flat item model at the current pose stack without re-entering BEWLR.
 * The caller's pose stack already has display-context transforms from {@link ItemRenderer}.
 */
public final class FlatItemModelRenderer {

    private FlatItemModelRenderer() {}

    public static void render(
            BakedModel flatModel,
            ItemStack stack,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            int packedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        boolean foil = stack.hasFoil();
        for (BakedModel pass : flatModel.getRenderPasses(stack, true)) {
            for (RenderType type : pass.getRenderTypes(stack, true)) {
                VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(buffer, type, true, foil);
                itemRenderer.renderModelLists(pass, stack, packedLight, packedOverlay, poseStack, consumer);
            }
        }
    }
}
