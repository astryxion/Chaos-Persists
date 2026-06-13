/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelTheQueen
 *  com.astryxion.chaospersists.RenderTheQueen
 *  com.astryxion.chaospersists.TheQueen
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
import com.astryxion.chaospersists.model.ModelTheQueen;
import com.astryxion.chaospersists.entity.TheQueen;
import net.minecraft.util.ResourceLocation;

public class RenderTheQueen
extends LivingRenderer<TheQueen, ModelTheQueen> {
    protected ModelTheQueen model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/thequeentexture.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/thequeentexture2.png");

    public RenderTheQueen(EntityRendererManager manager, ModelTheQueen par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
    protected void applyScale(MatrixStack matrixStack, TheQueen par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.getPlayNicely() != 0) {
            s = this.scale / 4.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(TheQueen entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(TheQueen entity) {
        TheQueen q = (TheQueen)entity;
        if (q.isHappy()) {
            return texture2;
        }
        return texture;
    }
}

