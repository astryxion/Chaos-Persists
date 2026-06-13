/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Beaver
 *  com.astryxion.chaospersists.ModelBeaver
 *  com.astryxion.chaospersists.RenderBeaver
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
import com.astryxion.chaospersists.entity.Beaver;
import com.astryxion.chaospersists.model.ModelBeaver;
import net.minecraft.util.ResourceLocation;

public class RenderBeaver
extends LivingRenderer<Beaver, ModelBeaver> {
    protected ModelBeaver model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/beavertexture.png");

    public RenderBeaver(EntityRendererManager manager, ModelBeaver par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
@Override
    protected void scale(Beaver entity, MatrixStack matrixStack, float partialTick) {
        float s = this.scale;
        if (entity != null && entity.isBaby()) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }


    @Override
    public ResourceLocation getTextureLocation(Beaver entity) {
        return texture;
    }
}

