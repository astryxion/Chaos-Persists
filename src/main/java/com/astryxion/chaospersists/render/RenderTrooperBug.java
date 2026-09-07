package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.model.ModelTrooperBug;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTrooperBug extends MobRenderer<TrooperBug, ModelTrooperBug> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/trooperbug.png");
    private final float scale;

    public RenderTrooperBug(
            EntityRendererProvider.Context context, ModelTrooperBug model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(TrooperBug entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(TrooperBug entity) {
        return TEXTURE;
    }
}
