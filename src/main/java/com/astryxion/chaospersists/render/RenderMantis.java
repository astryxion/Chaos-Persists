package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.model.ModelMantis;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderMantis extends MobRenderer<Mantis, ModelMantis> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/mantistexture.png");
    private final float scale;

    public RenderMantis(EntityRendererProvider.Context context, ModelMantis model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Mantis entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Mantis entity) {
        return TEXTURE;
    }
}
