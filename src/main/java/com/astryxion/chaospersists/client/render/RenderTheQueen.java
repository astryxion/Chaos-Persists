package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.client.model.ModelTheQueen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTheQueen extends MobRenderer<TheQueen, ModelTheQueen> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/thequeentexture.png");
    private static final ResourceLocation TEXTURE_HAPPY =
            new ResourceLocation("chaospersists", "textures/entity/thequeentexture2.png");
    private final float scale;

    public RenderTheQueen(EntityRendererProvider.Context context, ModelTheQueen model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
        this.addLayer(new RoyalWingLayer<>(this));
    }

    @Override
    protected void scale(TheQueen entity, PoseStack poseStack, float partialTick) {
        if (entity.getPlayNicely() != 0) {
            float s = this.scale / 4.0f;
            poseStack.scale(s, s, s);
        } else {
            poseStack.scale(this.scale, this.scale, this.scale);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(TheQueen entity) {
        if (entity.isHappy()) {
            return TEXTURE_HAPPY;
        }
        return TEXTURE;
    }

    /**
     * Body is cutout so it does not write a translucent depth mask over water/clouds.
     * Wing membranes are drawn by {@link RoyalWingLayer} without depth writes.
     */
    @Override
    protected RenderType getRenderType(TheQueen entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityCutoutNoCull(this.getTextureLocation(entity));
    }
}
