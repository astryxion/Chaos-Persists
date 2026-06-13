/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.ModelKraken
 *  com.astryxion.chaospersists.RenderKraken
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.model.ModelKraken;
import net.minecraft.util.ResourceLocation;

public class RenderKraken
extends LivingRenderer<Kraken, ModelKraken> {
    protected ModelKraken model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/kraken.png");

    public RenderKraken(EntityRendererManager manager, ModelKraken par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }

    protected void applyScale(MatrixStack matrixStack, Kraken par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.getPlayNicely() != 0) {
            s = this.scale / 3.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(Kraken entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(Kraken entity) {
        return texture;
    }
}
