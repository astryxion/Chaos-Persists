package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.model.ModelBasilisk;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderBasilisk extends MobRenderer<Basilisk, ModelBasilisk> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/basilisk.png");
    private final float scale;

    public RenderBasilisk(EntityRendererProvider.Context context, ModelBasilisk model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Basilisk entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Basilisk entity) {
        return TEXTURE;
    }
}
