/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.IslandToo
 *  com.astryxion.chaospersists.ModelIsland
 *  com.astryxion.chaospersists.RenderIslandToo
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
import com.astryxion.chaospersists.entity.IslandToo;
import com.astryxion.chaospersists.model.ModelIslandToo;
import net.minecraft.util.ResourceLocation;

public class RenderIslandToo
extends LivingRenderer<IslandToo, ModelIslandToo> {
    protected ModelIslandToo model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/islandtoo.png");

    public RenderIslandToo(EntityRendererManager manager, ModelIslandToo par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
    @Override
    protected void scale(IslandToo entity, MatrixStack matrixStack, float partialTick) {
        matrixStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(IslandToo entity) {
        return texture;
    }
}

