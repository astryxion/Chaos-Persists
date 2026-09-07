package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.model.ModelPeacock;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderPeacock extends MobRenderer<Peacock, ModelPeacock> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/peacocktexture.png");
    private final float scale;

    public RenderPeacock(EntityRendererProvider.Context context, ModelPeacock model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Peacock entity, PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Peacock entity) {
        return TEXTURE;
    }
}
