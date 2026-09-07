package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Island;
import com.astryxion.chaospersists.model.ModelIsland;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderIsland extends MobRenderer<Island, ModelIsland<Island>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/island.png");
    private final float scale;

    public RenderIsland(EntityRendererProvider.Context context, ModelIsland<Island> model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Island entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Island entity) {
        return TEXTURE;
    }
}
