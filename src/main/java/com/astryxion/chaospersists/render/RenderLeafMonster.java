package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.LeafMonster;
import com.astryxion.chaospersists.model.ModelLeafMonster;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderLeafMonster extends MobRenderer<LeafMonster, ModelLeafMonster> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/leafmonstertexture.png");
    private final float scale;

    public RenderLeafMonster(EntityRendererProvider.Context context, ModelLeafMonster model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(LeafMonster entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(LeafMonster entity) {
        return TEXTURE;
    }
}
