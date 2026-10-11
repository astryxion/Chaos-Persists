package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.client.model.RoyalWingMembranes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;

public class RoyalWingLayer<T extends LivingEntity, M extends EntityModel<T> & RoyalWingMembranes>
        extends RenderLayer<T, M> {
    private final RenderLayerParent<T, M> parent;

    public RoyalWingLayer(RenderLayerParent<T, M> parent) {
        super(parent);
        this.parent = parent;
    }

    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            T entity,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        VertexConsumer consumer =
                buffer.getBuffer(ChaosRenderTypes.entityTranslucentNoDepth(this.parent.getTextureLocation(entity)));
        this.getParentModel()
                .renderWingMembranes(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
    }
}
