package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.client.model.ModelGhostSkelly;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGhostSkelly extends MobRenderer<GhostSkelly, ModelGhostSkelly> {
    private static final net.minecraft.resources.ResourceLocation TEXTURE =
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                    "chaospersists", "textures/entity/ghostskellytexture.png");
    private final float scale;

    public RenderGhostSkelly(EntityRendererProvider.Context context, ModelGhostSkelly model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(GhostSkelly entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public net.minecraft.resources.ResourceLocation getTextureLocation(GhostSkelly entity) {
        return TEXTURE;
    }

    @Override
    protected RenderType getRenderType(GhostSkelly entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        ResourceLocation tex = this.getTextureLocation(entity);
        if (translucent) {
            return RenderType.itemEntityTranslucentCull(tex);
        } else if (bodyVisible) {
            return RenderType.entityTranslucent(tex);
        } else if (glowing) {
            return RenderType.outline(tex);
        }
        return null;
    }

    @Override
    protected float getWhiteOverlayProgress(GhostSkelly entity, float partialTicks) {
        return 0.0F;
    }
}
