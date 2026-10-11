package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

/** 1.7.10 royal-dragon wing membranes (and other GL_BLEND parts at alpha 0.55). */
public interface RoyalWingMembranes {
    void renderWingMembranes(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay);
}
