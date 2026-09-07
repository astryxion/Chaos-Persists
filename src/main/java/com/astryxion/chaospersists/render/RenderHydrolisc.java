package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Hydrolisc;
import com.astryxion.chaospersists.model.ModelHydrolisc;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderHydrolisc extends MobRenderer<Hydrolisc, ModelHydrolisc> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/hydrolisc.png");
    private final float scale;

    public RenderHydrolisc(EntityRendererProvider.Context context, ModelHydrolisc model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Hydrolisc entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Hydrolisc entity) {
        return TEXTURE;
    }
}
