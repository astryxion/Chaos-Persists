package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.model.ModelSpyro;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSpyro extends MobRenderer<Spyro, ModelSpyro> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/spyrotexture.png");
    private final float scale;

    public RenderSpyro(EntityRendererProvider.Context context, ModelSpyro model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Spyro entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Spyro entity) {
        return TEXTURE;
    }
}
