package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.model.ModelTheKing;
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
     * 1.7.10 {@code ModelTheKing#render} enabled GL blend and drew the membranes at alpha 0.55.
     * Cutout ignores that alpha, so the wings look solid unless this layer blends.
     */
    @Override
    protected RenderType getRenderType(TheKing entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }
}
