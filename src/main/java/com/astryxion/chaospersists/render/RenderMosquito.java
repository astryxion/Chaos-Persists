package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.EntityMosquito;
import com.astryxion.chaospersists.model.ModelMosquito;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderMosquito extends MobRenderer<EntityMosquito, ModelMosquito> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/mosquito.png");
    private final float scale;

    public RenderMosquito(EntityRendererProvider.Context context, ModelMosquito model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(EntityMosquito entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityMosquito entity) {
        return TEXTURE;
    }
}
