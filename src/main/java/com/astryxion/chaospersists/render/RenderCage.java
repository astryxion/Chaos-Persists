package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EntityCage;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;

public class RenderCage extends RenderSpinner {
    public RenderCage(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(Entity entity, float entityYaw, float partialTicks, MatrixStack matrixStack,
            net.minecraft.client.renderer.IRenderTypeBuffer buffer, int packedLight) {
        this.spinItemIconIndex = 160;
        if (entity instanceof EntityCage) {
            EntityCage var2 = (EntityCage) entity;
            this.spinItemIconIndex = var2.getCageIndex();
        }
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
}
