/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.LurkingTerror
 *  com.astryxion.chaospersists.ModelLurkingTerror
 *  com.astryxion.chaospersists.RenderLurkingTerror
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
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.model.ModelLurkingTerror;
import net.minecraft.util.ResourceLocation;

public class RenderLurkingTerror
extends LivingRenderer<LurkingTerror, ModelLurkingTerror> {
    protected ModelLurkingTerror model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/lurkingterror.png");

    public RenderLurkingTerror(EntityRendererManager manager, ModelLurkingTerror par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(LurkingTerror entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(LurkingTerror entity) {
        return texture;
    }
}

