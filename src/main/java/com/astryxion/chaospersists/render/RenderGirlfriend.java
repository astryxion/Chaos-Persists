package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;

public class RenderGirlfriend extends BipedRenderer<Girlfriend, PlayerModel<Girlfriend>> {
    protected PlayerModel<Girlfriend> model;

    public RenderGirlfriend(EntityRendererManager manager, PlayerModel<Girlfriend> par1Model, float par2) {
        super(manager, par1Model, par2);
        this.model = this.getModel();
        this.addLayer(new BipedArmorLayer<>(this, new PlayerModel<>(0.5F, false), new PlayerModel<>(1.0F, false)));
    }

    @Override
    protected void scale(Girlfriend entity, MatrixStack matrixStack, float partialTick) {
        if (ChaosPersists.valentines_day != 0 && entity.feelingBetter == 0) {
            matrixStack.scale(5.0f, 5.0f, 5.0f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Girlfriend entity) {
        return entity.getTexture();
    }
}
