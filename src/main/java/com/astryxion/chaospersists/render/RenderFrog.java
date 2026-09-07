package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Frog;
import com.astryxion.chaospersists.model.ModelFrog;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderFrog extends MobRenderer<Frog, ModelFrog> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/frogtexture.png");
    private final float scale;

    public RenderFrog(EntityRendererProvider.Context context, ModelFrog model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Frog entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Frog entity) {
        return TEXTURE;
    }
}
