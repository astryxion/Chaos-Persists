package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.BandP;
import com.astryxion.chaospersists.model.ModelBandP;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderBandP extends MobRenderer<BandP, ModelBandP> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/bandptexture.png");
    private final float scale;

    public RenderBandP(EntityRendererProvider.Context context, ModelBandP model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(BandP entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(BandP entity) {
        return TEXTURE;
    }
}
