package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.model.ModelTriffid;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTriffid extends MobRenderer<Triffid, ModelTriffid> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/triffidtexture.png");
    private final float scale;

    public RenderTriffid(EntityRendererProvider.Context context, ModelTriffid model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Triffid entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Triffid entity) {
        return TEXTURE;
    }
}
