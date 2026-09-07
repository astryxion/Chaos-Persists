package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Crab;
import com.astryxion.chaospersists.model.ModelCrab;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCrab extends MobRenderer<Crab, ModelCrab> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/robotcrabtexture.png");
    private final float scale;

    public RenderCrab(EntityRendererProvider.Context context, ModelCrab model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Crab entity, PoseStack poseStack, float partialTick) {
        float pscale = entity.getCrabScale();
        poseStack.scale(pscale, pscale, pscale);
    }

    @Override
    public ResourceLocation getTextureLocation(Crab entity) {
        return TEXTURE;
    }
}
