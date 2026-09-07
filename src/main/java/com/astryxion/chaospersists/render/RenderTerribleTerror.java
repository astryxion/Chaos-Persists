package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.model.ModelTerribleTerror;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTerribleTerror extends MobRenderer<TerribleTerror, ModelTerribleTerror> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/terribleterror.png");
    private final float scale;

    public RenderTerribleTerror(EntityRendererProvider.Context context, ModelTerribleTerror model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(TerribleTerror entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(TerribleTerror entity) {
        return TEXTURE;
    }
}
