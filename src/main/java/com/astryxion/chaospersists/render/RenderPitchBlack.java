/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelPitchBlack
 *  com.astryxion.chaospersists.PitchBlack
 *  com.astryxion.chaospersists.RenderPitchBlack
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
import com.astryxion.chaospersists.model.ModelPitchBlack;
import com.astryxion.chaospersists.entity.PitchBlack;
import net.minecraft.util.ResourceLocation;

public class RenderPitchBlack
extends LivingRenderer<PitchBlack, ModelPitchBlack> {
    protected ModelPitchBlack model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/pitchblacktexture.png");

    public RenderPitchBlack(EntityRendererManager manager, ModelPitchBlack par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
    @Override
    protected void scale(PitchBlack entity, MatrixStack matrixStack, float partialTick) {
        float pscale = entity.getPitchBlackScale();
        matrixStack.scale(pscale, pscale, pscale);
    }

    @Override
    public ResourceLocation getTextureLocation(PitchBlack entity) {
        return texture;
    }
}

