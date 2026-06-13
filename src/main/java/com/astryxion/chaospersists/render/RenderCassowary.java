/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Cassowary
 *  com.astryxion.chaospersists.ModelCassowary
 *  com.astryxion.chaospersists.RenderCassowary
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
import com.astryxion.chaospersists.entity.Cassowary;
import com.astryxion.chaospersists.model.ModelCassowary;
import net.minecraft.util.ResourceLocation;

public class RenderCassowary
extends LivingRenderer<Cassowary, ModelCassowary> {
    protected ModelCassowary model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/cassowary.png");

    public RenderCassowary(EntityRendererManager manager, ModelCassowary par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
    protected void applyScale(MatrixStack matrixStack, Cassowary par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.isBaby()) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(Cassowary entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(Cassowary entity) {
        return texture;
    }
}

