package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Dragonfly;
import com.astryxion.chaospersists.model.ModelDragonfly;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderDragonfly extends MobRenderer<Dragonfly, ModelDragonfly> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/dragonfly.png");
    private final float scale;

    public RenderDragonfly(EntityRendererProvider.Context context, ModelDragonfly model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Dragonfly entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Dragonfly entity) {
        return TEXTURE;
    }
}
