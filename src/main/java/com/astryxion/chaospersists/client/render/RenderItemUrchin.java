package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.BerthaHit;
import com.astryxion.chaospersists.entity.SunspotUrchin;
import com.astryxion.chaospersists.item.InkSack;
import com.astryxion.chaospersists.item.WaterBall;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.Entity;

public class RenderItemUrchin extends RenderSpinner {
    public RenderItemUrchin(EntityRendererProvider.Context context) {
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
        if (entity instanceof BerthaHit) {
            return;
        }
        if (entity instanceof SunspotUrchin var2) {
            this.spinItemIconIndex = var2.getUrchinIndex();
        }
        if (entity instanceof WaterBall var2) {
            this.spinItemIconIndex = var2.getWaterBallIndex();
        }
        if (entity instanceof InkSack var2) {
            this.spinItemIconIndex = var2.getInkSackIndex();
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
