/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelPointysaurus
 *  com.astryxion.chaospersists.Pointysaurus
 *  com.astryxion.chaospersists.RenderPointysaurus
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
import com.astryxion.chaospersists.model.ModelPointysaurus;
import com.astryxion.chaospersists.entity.Pointysaurus;
import net.minecraft.util.ResourceLocation;

public class RenderPointysaurus
extends LivingRenderer<Pointysaurus, ModelPointysaurus> {
    protected ModelPointysaurus model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/pointysaurustexture.png");

    public RenderPointysaurus(EntityRendererManager manager, ModelPointysaurus par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(Pointysaurus entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(Pointysaurus entity) {
        return texture;
    }
}

