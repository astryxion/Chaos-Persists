/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.KingHead
 *  com.astryxion.chaospersists.ModelTheKing
 *  com.astryxion.chaospersists.RenderKingHead
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.ResourceLocation
 */
package com.astryxion.chaospersists.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import com.astryxion.chaospersists.entity.KingHead;
import net.minecraft.util.ResourceLocation;

public class RenderKingHead
extends EntityRenderer<KingHead> {
    public RenderKingHead(EntityRendererManager manager) {
        super(manager);
    }

    public void renderKingHead(KingHead par1EntityKingHead, double par2, double par4, double par6, float par8, float par9) {
    }

    @Override
    public void render(KingHead par1Entity, float par2, float par3, MatrixStack par4MatrixStack, IRenderTypeBuffer par5Buffer, int par6PackedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(KingHead entity) {
        return null;
    }
}
