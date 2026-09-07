package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.model.ModelSeaViper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSeaViper extends MobRenderer<SeaViper, ModelSeaViper> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/seavipertexture.png");
    private final float scale;

    public RenderSeaViper(EntityRendererProvider.Context context, ModelSeaViper model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(SeaViper entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(SeaViper entity) {
        return TEXTURE;
    }
}
