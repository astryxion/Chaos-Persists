package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.EntityCage;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;

public class RenderCage extends RenderSpinner {
    public RenderCage(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(
            Entity entity,
            float entityYaw,
            float partialTicks,
            com.mojang.blaze3d.vertex.PoseStack poseStack,
            net.minecraft.client.renderer.MultiBufferSource buffer,
            int packedLight) {
        this.spinItemIconIndex = 160;
        if (entity instanceof EntityCage cage) {
            this.spinItemIconIndex = cage.getCageIndex();
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
