/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EasterBunny
 *  com.astryxion.chaospersists.ModelEasterBunny
 *  com.astryxion.chaospersists.RenderEasterBunny
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
import com.astryxion.chaospersists.entity.EasterBunny;
import com.astryxion.chaospersists.model.ModelEasterBunny;
import net.minecraft.util.ResourceLocation;

public class RenderEasterBunny
extends LivingRenderer<EasterBunny, ModelEasterBunny> {
    protected ModelEasterBunny model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/easterbunnytexture.png");

    public RenderEasterBunny(EntityRendererManager manager, ModelEasterBunny par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
@Override
    protected void scale(EasterBunny entity, MatrixStack matrixStack, float partialTick) {
        float s = this.scale;
        if (entity != null && entity.isBaby()) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }


    @Override
    public ResourceLocation getTextureLocation(EasterBunny entity) {
        return texture;
    }
}

