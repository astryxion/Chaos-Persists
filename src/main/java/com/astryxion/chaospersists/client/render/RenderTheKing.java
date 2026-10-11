package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.client.model.ModelTheKing;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTheKing extends MobRenderer<TheKing, ModelTheKing> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/thekingtexture.png");
    private final float scale;

    public RenderTheKing(EntityRendererProvider.Context context, ModelTheKing model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
        this.addLayer(new RoyalWingLayer<>(this));
    }

    @Override
    protected void scale(TheKing entity, PoseStack poseStack, float partialTick) {
        if (entity.getPlayNicely() != 0) {
            float s = this.scale / 4.0f;
            poseStack.scale(s, s, s);
        } else {
            poseStack.scale(this.scale, this.scale, this.scale);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(TheKing entity) {
        return TEXTURE;
    }

    /**
     * Body is cutout so it does not write a translucent depth mask over water/clouds.
     * Wing membranes are drawn by {@link RoyalWingLayer} without depth writes.
     */
    @Override
    protected RenderType getRenderType(TheKing entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityCutoutNoCull(this.getTextureLocation(entity));
    }
}
