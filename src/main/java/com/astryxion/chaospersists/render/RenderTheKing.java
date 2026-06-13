/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelTheKing
 *  com.astryxion.chaospersists.RenderTheKing
 *  com.astryxion.chaospersists.TheKing
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
import com.astryxion.chaospersists.model.ModelTheKing;
import com.astryxion.chaospersists.entity.TheKing;
import net.minecraft.util.ResourceLocation;

public class RenderTheKing
extends LivingRenderer<TheKing, ModelTheKing> {
    protected ModelTheKing model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/thekingtexture.png");

    public RenderTheKing(EntityRendererManager manager, ModelTheKing par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }

    protected void applyScale(MatrixStack matrixStack, TheKing par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.getPlayNicely() != 0) {
            s = this.scale / 4.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(TheKing entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(TheKing entity) {
        return texture;
    }
}
