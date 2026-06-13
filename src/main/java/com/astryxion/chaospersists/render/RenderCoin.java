package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.Coin;
import com.astryxion.chaospersists.model.ModelCoin;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderCoin extends LivingRenderer<Coin, ModelCoin> {
    protected ModelCoin model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/cointexture.png");

    public RenderCoin(EntityRendererManager manager, ModelCoin par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }

    protected void applyScale(MatrixStack matrixStack) {
        matrixStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    protected void scale(Coin entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack);
    }

    @Override
    public ResourceLocation getTextureLocation(Coin entity) {
        return texture;
    }
}
