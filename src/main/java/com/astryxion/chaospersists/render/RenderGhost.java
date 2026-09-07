package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Ghost;
import com.astryxion.chaospersists.model.ModelGhost;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGhost extends MobRenderer<Ghost, ModelGhost> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/ghosttexture.png");
    private final float scale;

    public RenderGhost(EntityRendererProvider.Context context, ModelGhost model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Ghost entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Ghost entity) {
        return TEXTURE;
    }

    /**
     * Use default {@link MobRenderer#render} (applies the -Y flip 1.12 {@code RenderLiving} used).
     * 1.12 alpha was 0.25 via GL blend in {@link ModelGhost#render}.
     */
    @Override
    protected RenderType getRenderType(Ghost entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

    @Override
    protected float getWhiteOverlayProgress(Ghost entity, float partialTicks) {
        return 0.0F;
    }
}
