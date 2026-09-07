package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.model.ModelWaterDragon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderWaterDragon extends MobRenderer<WaterDragon, ModelWaterDragon> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/waterdragon.png");
    private final float scale;

    public RenderWaterDragon(
            EntityRendererProvider.Context context, ModelWaterDragon model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(WaterDragon entity, PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(WaterDragon entity) {
        return TEXTURE;
    }
}
