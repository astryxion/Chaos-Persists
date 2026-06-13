package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Boyfriend;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

public class RenderBoyfriend extends BipedRenderer<Boyfriend, PlayerModel<Boyfriend>> {
    protected PlayerModel<Boyfriend> model;

    public RenderBoyfriend(EntityRendererManager manager, PlayerModel<Boyfriend> par1Model, float par2) {
        super(manager, par1Model, par2);
        this.model = this.getModel();
        this.addLayer(new BipedArmorLayer<>(this, new PlayerModel<>(0.5F, false), new PlayerModel<>(1.0F, false)));
    }

    @Override
    public ResourceLocation getTextureLocation(Boyfriend entity) {
        return entity.getTexture();
    }
}
