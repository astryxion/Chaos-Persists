package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Camarasaurus;
import com.astryxion.chaospersists.model.ModelCamarasaurus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCamarasaurus extends MobRenderer<Camarasaurus, ModelCamarasaurus> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/camarasaurus.png");
    private final float scale;

    public RenderCamarasaurus(
            EntityRendererProvider.Context context, ModelCamarasaurus model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Camarasaurus entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Camarasaurus entity) {
        return TEXTURE;
    }
}
