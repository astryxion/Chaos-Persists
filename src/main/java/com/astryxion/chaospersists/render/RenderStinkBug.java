package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.StinkBug;
import com.astryxion.chaospersists.model.ModelStinkBug;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderStinkBug extends MobRenderer<StinkBug, ModelStinkBug> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/stinkbug.png");
    private final float scale;

    public RenderStinkBug(
            EntityRendererProvider.Context context, ModelStinkBug model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(StinkBug entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        if (entity.isBaby()) {
            poseStack.scale(this.scale / 2.0f, this.scale / 2.0f, this.scale / 2.0f);
            return;
        }
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(StinkBug entity) {
        return TEXTURE;
    }
}
