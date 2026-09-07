package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.IslandToo;
import com.astryxion.chaospersists.model.ModelIsland;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderIslandToo extends MobRenderer<IslandToo, ModelIsland<IslandToo>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/island.png");
    private final float scale;

    public RenderIslandToo(EntityRendererProvider.Context context, ModelIsland<IslandToo> model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(IslandToo entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(IslandToo entity) {
        return TEXTURE;
    }
}
