/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelThePrincess
 *  com.astryxion.chaospersists.RenderThePrincess
 *  com.astryxion.chaospersists.ThePrincess
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
import com.astryxion.chaospersists.model.ModelThePrincess;
import com.astryxion.chaospersists.entity.ThePrincess;
import net.minecraft.util.ResourceLocation;

public class RenderThePrincess
extends LivingRenderer<ThePrincess, ModelThePrincess> {
    protected ModelThePrincess model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/theprincesstexture.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/theprincesstexture2.png");

    public RenderThePrincess(EntityRendererManager manager, ModelThePrincess par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(ThePrincess entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(ThePrincess entity) {
        ThePrincess t = (ThePrincess)entity;
        if (t.getAttacking() != 0) {
            return texture2;
        }
        return texture;
    }
}

