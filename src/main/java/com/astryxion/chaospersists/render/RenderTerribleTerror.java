/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelTerribleTerror
 *  com.astryxion.chaospersists.RenderTerribleTerror
 *  com.astryxion.chaospersists.TerribleTerror
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
import com.astryxion.chaospersists.model.ModelTerribleTerror;
import com.astryxion.chaospersists.entity.TerribleTerror;
import net.minecraft.util.ResourceLocation;

public class RenderTerribleTerror
extends LivingRenderer<TerribleTerror, ModelTerribleTerror> {
    protected ModelTerribleTerror model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/terribleterror.png");

    public RenderTerribleTerror(EntityRendererManager manager, ModelTerribleTerror par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(TerribleTerror entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(TerribleTerror entity) {
        return texture;
    }
}

