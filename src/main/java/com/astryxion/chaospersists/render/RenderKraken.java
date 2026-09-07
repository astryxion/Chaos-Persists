package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.model.ModelKraken;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderKraken extends MobRenderer<Kraken, ModelKraken> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/kraken.png");
    private final float scale;

    public RenderKraken(EntityRendererProvider.Context context, ModelKraken model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Kraken entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Kraken entity) {
        return TEXTURE;
    }
}
