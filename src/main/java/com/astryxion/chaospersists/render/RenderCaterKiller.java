/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CaterKiller
 *  com.astryxion.chaospersists.ModelCaterKiller
 *  com.astryxion.chaospersists.RenderCaterKiller
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
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.model.ModelCaterKiller;
import net.minecraft.util.ResourceLocation;

public class RenderCaterKiller
extends LivingRenderer<CaterKiller, ModelCaterKiller> {
    protected ModelCaterKiller model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/caterkillertexture.png");

    public RenderCaterKiller(EntityRendererManager manager, ModelCaterKiller par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
    protected void applyScale(MatrixStack matrixStack, CaterKiller par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.getPlayNicely() != 0) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(CaterKiller entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(CaterKiller entity) {
        return texture;
    }
}

