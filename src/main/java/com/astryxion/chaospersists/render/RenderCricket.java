package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Cricket;
import com.astryxion.chaospersists.model.ModelCricket;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCricket extends MobRenderer<Cricket, ModelCricket> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/crickettexture.png");
    private final float scale;

    public RenderCricket(EntityRendererProvider.Context context, ModelCricket model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Cricket entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Cricket entity) {
        return TEXTURE;
    }
}
