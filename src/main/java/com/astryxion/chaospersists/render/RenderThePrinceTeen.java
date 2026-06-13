/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelThePrinceTeen
 *  com.astryxion.chaospersists.RenderThePrinceTeen
 *  com.astryxion.chaospersists.ThePrinceTeen
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
import com.astryxion.chaospersists.model.ModelThePrinceTeen;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import net.minecraft.util.ResourceLocation;

public class RenderThePrinceTeen
extends LivingRenderer<ThePrinceTeen, ModelThePrinceTeen> {
    protected ModelThePrinceTeen model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/princeteentexture.png");

    public RenderThePrinceTeen(EntityRendererManager manager, ModelThePrinceTeen par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(ThePrinceTeen entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(ThePrinceTeen entity) {
        return texture;
    }
}

