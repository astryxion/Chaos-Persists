package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.GoldFish;
import com.astryxion.chaospersists.model.ModelGoldFish;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGoldFish extends MobRenderer<GoldFish, ModelGoldFish> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/goldfish.png");
    private final float scale;

    public RenderGoldFish(EntityRendererProvider.Context context, ModelGoldFish model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(GoldFish entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(GoldFish entity) {
        return TEXTURE;
    }
}
