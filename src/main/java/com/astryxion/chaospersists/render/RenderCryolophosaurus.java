package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Cryolophosaurus;
import com.astryxion.chaospersists.model.ModelCryolophosaurus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCryolophosaurus extends MobRenderer<Cryolophosaurus, ModelCryolophosaurus> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/cryolophosaurus.png");
    private final float scale;

    public RenderCryolophosaurus(
            EntityRendererProvider.Context context, ModelCryolophosaurus model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Cryolophosaurus entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Cryolophosaurus entity) {
        return TEXTURE;
    }
}
