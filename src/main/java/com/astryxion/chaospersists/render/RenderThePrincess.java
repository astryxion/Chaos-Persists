package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.ThePrincess;
import com.astryxion.chaospersists.model.ModelThePrincess;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderThePrincess extends MobRenderer<ThePrincess, ModelThePrincess> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/theprincesstexture.png");
    private static final ResourceLocation TEXTURE_ATTACK =
            new ResourceLocation("chaospersists", "textures/entity/theprincesstexture2.png");
    private final float scale;

    public RenderThePrincess(
            EntityRendererProvider.Context context, ModelThePrincess model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(ThePrincess entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(ThePrincess entity) {
        if (entity.getAttacking() != 0) {
            return TEXTURE_ATTACK;
        }
        return TEXTURE;
    }

    /**
     * 1.7.10 {@code ModelThePrincess#render} enabled GL blend and drew the membranes at alpha 0.55.
     * Cutout ignores that alpha, so the wings look solid unless this layer blends.
     */
    @Override
    protected RenderType getRenderType(ThePrincess entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }
}
