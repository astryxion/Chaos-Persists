package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.model.ModelHammerhead;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderHammerhead extends MobRenderer<Hammerhead, ModelHammerhead> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/hammerheadtexture.png");
    private final float scale;

    public RenderHammerhead(EntityRendererProvider.Context context, ModelHammerhead model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Hammerhead entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float s = this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Hammerhead entity) {
        return TEXTURE;
    }
}
