package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.item.Shoes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;

public class RenderShoe extends RenderSpinner {
    public RenderShoe(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(
            Entity entity,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight) {
        if (entity instanceof Shoes shoes) {
            this.spinItemIconIndex = shoes.getShoeId();
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
