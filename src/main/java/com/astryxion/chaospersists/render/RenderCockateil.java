/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Cockateil
 *  com.astryxion.chaospersists.ModelCockateil
 *  com.astryxion.chaospersists.RenderCockateil
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
import com.astryxion.chaospersists.entity.Cockateil;
import com.astryxion.chaospersists.model.ModelCockateil;
import net.minecraft.util.ResourceLocation;

public class RenderCockateil
extends LivingRenderer<Cockateil, ModelCockateil> {
    protected ModelCockateil model;
    private float scale = 1.0f;

    public RenderCockateil(EntityRendererManager manager, ModelCockateil par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(Cockateil entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(Cockateil entity) {
        Cockateil c = (Cockateil)entity;
        return c.getTexture();
    }
}

