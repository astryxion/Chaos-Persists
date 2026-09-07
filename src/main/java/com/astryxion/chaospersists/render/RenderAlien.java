package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.model.ModelAlien;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderAlien extends MobRenderer<Alien, ModelAlien> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/myalien.png");
    private final float scale;

    public RenderAlien(EntityRendererProvider.Context context, ModelAlien model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Alien entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Alien entity) {
        return TEXTURE;
    }
}
