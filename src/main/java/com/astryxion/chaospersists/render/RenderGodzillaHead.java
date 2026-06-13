/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GodzillaHead
 *  com.astryxion.chaospersists.ModelGodzilla
 *  com.astryxion.chaospersists.RenderGodzillaHead
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 */
package com.astryxion.chaospersists.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import com.astryxion.chaospersists.entity.GodzillaHead;
import net.minecraft.util.ResourceLocation;

public class RenderGodzillaHead
extends EntityRenderer<GodzillaHead> {
    public RenderGodzillaHead(EntityRendererManager manager) {
        super(manager);
    }

    public void renderGodzillaHead(GodzillaHead par1EntityGodzillaHead, double par2, double par4, double par6, float par8, float par9) {
    }

    @Override
    public void render(GodzillaHead par1Entity, float par2, float par3, MatrixStack par4MatrixStack, IRenderTypeBuffer par5Buffer, int par6PackedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(GodzillaHead entity) {
        return null;
    }
}
