package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Pointysaurus;
import com.astryxion.chaospersists.model.ModelPointysaurus;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderPointysaurus extends MobRenderer<Pointysaurus, ModelPointysaurus> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/pointysaurustexture.png");
    private final float scale;

    public RenderPointysaurus(EntityRendererProvider.Context context, ModelPointysaurus model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Pointysaurus entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Pointysaurus entity) {
        return TEXTURE;
    }
}
