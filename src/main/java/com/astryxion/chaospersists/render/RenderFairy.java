/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Fairy
 *  com.astryxion.chaospersists.ModelFairy
 *  com.astryxion.chaospersists.RenderFairy
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
import com.astryxion.chaospersists.entity.Fairy;
import com.astryxion.chaospersists.model.ModelFairy;
import net.minecraft.util.ResourceLocation;

public class RenderFairy
extends LivingRenderer<Fairy, ModelFairy> {
    protected ModelFairy model;
    private float scale = 1.0f;

    public RenderFairy(EntityRendererManager manager, ModelFairy par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(Fairy entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(Fairy entity) {
        Fairy a = (Fairy)entity;
        return a.getTexture(a);
    }
}

