package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.model.ModelKyuubi;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderKyuubi extends MobRenderer<Kyuubi, ModelKyuubi> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/kyuubi.png");
    private final float scale;

    public RenderKyuubi(EntityRendererProvider.Context context, ModelKyuubi model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Kyuubi entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Kyuubi entity) {
        return TEXTURE;
    }

    /**
     * 1.12 {@link ModelKyuubi#render} enabled GL blend for the whole model so the red aura
     * (*Fire parts) uses texture alpha over the solid inner body.
     */
    @Override
    protected RenderType getRenderType(Kyuubi entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

    @Override
    protected float getWhiteOverlayProgress(Kyuubi entity, float partialTicks) {
        return 0.0F;
    }
}
