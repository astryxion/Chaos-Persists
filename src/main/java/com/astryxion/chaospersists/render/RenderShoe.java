package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.Shoes;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;

public class RenderShoe extends RenderSpinner {
    public RenderShoe(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack,
            net.minecraft.client.renderer.IRenderTypeBuffer buffer, int packedLight) {
        if (entity instanceof Shoes) {
            Shoes var2 = (Shoes) entity;
            this.spinItemIconIndex = var2.getShoeId();
        }
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
}
