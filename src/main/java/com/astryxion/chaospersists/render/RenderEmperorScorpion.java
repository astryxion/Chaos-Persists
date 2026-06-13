/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EmperorScorpion
 *  com.astryxion.chaospersists.ModelEmperorScorpion
 *  com.astryxion.chaospersists.RenderEmperorScorpion
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
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.model.ModelEmperorScorpion;
import net.minecraft.util.ResourceLocation;

public class RenderEmperorScorpion
extends LivingRenderer<EmperorScorpion, ModelEmperorScorpion> {
    protected ModelEmperorScorpion model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/emperorscorpion.png");

    public RenderEmperorScorpion(EntityRendererManager manager, ModelEmperorScorpion par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(EmperorScorpion entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(EmperorScorpion entity) {
        return texture;
    }
}

