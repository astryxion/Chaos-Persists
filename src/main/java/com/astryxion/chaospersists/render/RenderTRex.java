package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.model.ModelTRex;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTRex extends MobRenderer<TRex, ModelTRex> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/trextexture.png");
    private final float scale;

    public RenderTRex(EntityRendererProvider.Context context, ModelTRex model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(TRex entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(TRex entity) {
        return TEXTURE;
    }
}
