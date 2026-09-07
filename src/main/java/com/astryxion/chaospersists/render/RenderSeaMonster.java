package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.model.ModelSeaMonster;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSeaMonster extends MobRenderer<SeaMonster, ModelSeaMonster> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/seamonstertexture.png");
    private final float scale;

    public RenderSeaMonster(EntityRendererProvider.Context context, ModelSeaMonster model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(SeaMonster entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(SeaMonster entity) {
        return TEXTURE;
    }
}
