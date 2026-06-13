/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Baryonyx
 *  com.astryxion.chaospersists.ModelBaryonyx
 *  com.astryxion.chaospersists.RenderBaryonyx
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
import com.astryxion.chaospersists.entity.Baryonyx;
import com.astryxion.chaospersists.model.ModelBaryonyx;
import net.minecraft.util.ResourceLocation;

public class RenderBaryonyx
extends LivingRenderer<Baryonyx, ModelBaryonyx> {
    protected ModelBaryonyx model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/baryonyx.png");

    public RenderBaryonyx(EntityRendererManager manager, ModelBaryonyx par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
@Override
    protected void scale(Baryonyx entity, MatrixStack matrixStack, float partialTick) {
        float s = this.scale;
        if (entity != null && entity.isBaby()) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }


    @Override
    public ResourceLocation getTextureLocation(Baryonyx entity) {
        return texture;
    }
}

