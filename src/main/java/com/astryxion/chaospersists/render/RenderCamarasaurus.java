/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Camarasaurus
 *  com.astryxion.chaospersists.ModelCamarasaurus
 *  com.astryxion.chaospersists.RenderCamarasaurus
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
import com.astryxion.chaospersists.entity.Camarasaurus;
import com.astryxion.chaospersists.model.ModelCamarasaurus;
import net.minecraft.util.ResourceLocation;

public class RenderCamarasaurus
extends LivingRenderer<Camarasaurus, ModelCamarasaurus> {
    protected ModelCamarasaurus model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/camarasaurus.png");

    public RenderCamarasaurus(EntityRendererManager manager, ModelCamarasaurus par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }

    protected void applyScale(MatrixStack matrixStack, Camarasaurus par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.isBaby()) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(Camarasaurus entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(Camarasaurus entity) {
        return texture;
    }
}
