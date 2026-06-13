package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.BerthaHit;
import com.astryxion.chaospersists.entity.SunspotUrchin;
import com.astryxion.chaospersists.item.WaterBall;
import com.astryxion.chaospersists.item.InkSack;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;

public class RenderItemUrchin extends RenderSpinner {
    public RenderItemUrchin(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack,
            net.minecraft.client.renderer.IRenderTypeBuffer buffer, int packedLight) {
        if (entity instanceof BerthaHit) {
            return;
        }
        if (entity instanceof SunspotUrchin) {
            SunspotUrchin var2 = (SunspotUrchin) entity;
            this.spinItemIconIndex = var2.getUrchinIndex();
        }
        if (entity instanceof WaterBall) {
            WaterBall var2 = (WaterBall) entity;
            this.spinItemIconIndex = var2.getWaterBallIndex();
        }
        if (entity instanceof InkSack) {
            InkSack var2 = (InkSack) entity;
            this.spinItemIconIndex = var2.getInkSackIndex();
        }
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
}
