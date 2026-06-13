/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.HerculesBeetle
 *  com.astryxion.chaospersists.ModelHerculesBeetle
 *  com.astryxion.chaospersists.RenderHerculesBeetle
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
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.model.ModelHerculesBeetle;
import net.minecraft.util.ResourceLocation;

public class RenderHerculesBeetle
extends LivingRenderer<HerculesBeetle, ModelHerculesBeetle> {
    protected ModelHerculesBeetle model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/beetletexture.png");

    public RenderHerculesBeetle(EntityRendererManager manager, ModelHerculesBeetle par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(HerculesBeetle entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(HerculesBeetle entity) {
        return texture;
    }
}

