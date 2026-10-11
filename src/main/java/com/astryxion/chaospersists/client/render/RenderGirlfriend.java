package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.client.model.LegacySkinModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class RenderGirlfriend extends HumanoidMobRenderer<Girlfriend, LegacySkinModel<Girlfriend>> {
    public RenderGirlfriend(EntityRendererProvider.Context context) {
        super(context, new LegacySkinModel<>(LegacySkinModel.createBodyLayer().bakeRoot()), 0.5f);
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this,
                        new LegacySkinModel<>(LegacySkinModel.createInnerArmorLayer().bakeRoot()),
                        new LegacySkinModel<>(LegacySkinModel.createOuterArmorLayer().bakeRoot()),
                        context.getModelManager()));
    }

    @Override
    protected void scale(Girlfriend entity, PoseStack poseStack, float partialTick) {
        if (ChaosPersists.valentines_day != 0 && entity.feelingBetter == 0) {
            poseStack.scale(5.0f, 5.0f, 5.0f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Girlfriend entity) {
        return entity.getTexture();
    }
}
