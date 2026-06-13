/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CreepingHorror
 *  com.astryxion.chaospersists.ModelCreepingHorror
 *  com.astryxion.chaospersists.RenderCreepingHorror
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
import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.model.ModelCreepingHorror;
import net.minecraft.util.ResourceLocation;

public class RenderCreepingHorror
extends LivingRenderer<CreepingHorror, ModelCreepingHorror> {
    protected ModelCreepingHorror model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/creepinghorror.png");

    public RenderCreepingHorror(EntityRendererManager manager, ModelCreepingHorror par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(CreepingHorror entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(CreepingHorror entity) {
        return texture;
    }
}

