package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Cassowary;
import com.astryxion.chaospersists.model.ModelCassowary;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCassowary extends MobRenderer<Cassowary, ModelCassowary> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/cassowary.png");
    private final float scale;

    public RenderCassowary(EntityRendererProvider.Context context, ModelCassowary model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Cassowary entity, PoseStack poseStack, float partialTick) {
        float s = this.scale;
        if (entity.isBaby()) {
            s /= 2.0f;
        }
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Cassowary entity) {
        return TEXTURE;
    }
}
