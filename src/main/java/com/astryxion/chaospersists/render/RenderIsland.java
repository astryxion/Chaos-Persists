/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Island
 *  com.astryxion.chaospersists.ModelIsland
 *  com.astryxion.chaospersists.RenderIsland
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
import com.astryxion.chaospersists.entity.Island;
import com.astryxion.chaospersists.model.ModelIsland;
import net.minecraft.util.ResourceLocation;

public class RenderIsland
extends LivingRenderer<Island, ModelIsland> {
    protected ModelIsland model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/island.png");

    public RenderIsland(EntityRendererManager manager, ModelIsland par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
    @Override
    protected void scale(Island entity, MatrixStack matrixStack, float partialTick) {
        matrixStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Island entity) {
        return texture;
    }
}

