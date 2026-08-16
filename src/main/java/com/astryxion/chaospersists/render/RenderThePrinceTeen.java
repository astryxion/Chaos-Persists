package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.model.ModelThePrinceTeen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderThePrinceTeen extends MobRenderer<ThePrinceTeen, ModelThePrinceTeen> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/entity/princeteentexture.png");
    private final float scale;

    public RenderThePrinceTeen(
            EntityRendererProvider.Context context, ModelThePrinceTeen model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(ThePrinceTeen entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(ThePrinceTeen entity) {
        return TEXTURE;
    }

    /**
     * 1.7.10 {@code ModelThePrinceTeen#render} enabled GL blend and drew the membranes at alpha 0.55.
     * Cutout ignores that alpha, so the wings look solid unless this layer blends.
     */
    @Override
    protected RenderType getRenderType(
            ThePrinceTeen entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }
}
