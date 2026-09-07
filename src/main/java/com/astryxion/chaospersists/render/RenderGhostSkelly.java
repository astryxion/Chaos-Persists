package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.model.ModelGhostSkelly;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;

public class RenderGhostSkelly extends MobRenderer<GhostSkelly, ModelGhostSkelly> {
    private static final net.minecraft.resources.ResourceLocation TEXTURE =
            new net.minecraft.resources.ResourceLocation(
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
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

    @Override
    protected float getWhiteOverlayProgress(GhostSkelly entity, float partialTicks) {
        return 0.0F;
    }
}
