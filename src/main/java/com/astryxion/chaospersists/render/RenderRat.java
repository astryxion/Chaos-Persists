package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Rat;
import com.astryxion.chaospersists.model.ModelRat;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRat extends MobRenderer<Rat, ModelRat> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/rattexture.png");
    private final float scale;

    public RenderRat(EntityRendererProvider.Context context, ModelRat model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Rat entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Rat entity) {
        return TEXTURE;
    }
}
