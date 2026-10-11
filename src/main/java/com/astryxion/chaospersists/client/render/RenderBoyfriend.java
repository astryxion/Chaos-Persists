package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.client.model.LegacySkinModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class RenderBoyfriend extends HumanoidMobRenderer<Boyfriend, LegacySkinModel<Boyfriend>> {
    public RenderBoyfriend(EntityRendererProvider.Context context) {
        super(context, new LegacySkinModel<>(LegacySkinModel.createBodyLayer().bakeRoot()), 0.55f);
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this,
                        new LegacySkinModel<>(LegacySkinModel.createInnerArmorLayer().bakeRoot()),
                        new LegacySkinModel<>(LegacySkinModel.createOuterArmorLayer().bakeRoot()),
                        context.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(Boyfriend entity) {
        return entity.getTexture();
    }
}
