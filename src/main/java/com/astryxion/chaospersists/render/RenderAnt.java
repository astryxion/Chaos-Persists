/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EntityAnt
 *  com.astryxion.chaospersists.ModelAnt
 *  com.astryxion.chaospersists.RenderAnt
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
import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.model.ModelAnt;
import net.minecraft.util.ResourceLocation;

public class RenderAnt
extends LivingRenderer<EntityAnt, ModelAnt> {
    protected ModelAnt model;
    private float scale = 0.25f;

    public RenderAnt(EntityRendererManager manager, ModelAnt par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(EntityAnt entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(EntityAnt entity) {
        EntityAnt a = (EntityAnt)entity;
        return a.getTexture(a);
    }
}

