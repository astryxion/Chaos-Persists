/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelStinky
 *  com.astryxion.chaospersists.RenderStinky
 *  com.astryxion.chaospersists.Stinky
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
import com.astryxion.chaospersists.model.ModelStinky;
import com.astryxion.chaospersists.entity.Stinky;
import net.minecraft.util.ResourceLocation;

public class RenderStinky
extends LivingRenderer<Stinky, ModelStinky> {
    protected ModelStinky model;
    private float scale = 1.0f;
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture1.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture3.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture4.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture5.png");
    private static final ResourceLocation texture6 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture6.png");
    private static final ResourceLocation texture7 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture7.png");
    private static final ResourceLocation texture8 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture8.png");
    private static final ResourceLocation texture9 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture9.png");
    private static final ResourceLocation texture10 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture10.png");
    private static final ResourceLocation texture11 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture11.png");
    private static final ResourceLocation texture12 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture12.png");
    private static final ResourceLocation texture13 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture13.png");
    private static final ResourceLocation texture14 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture14.png");
    private static final ResourceLocation texture15 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture15.png");
    private static final ResourceLocation texture16 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture16.png");
    private static final ResourceLocation texture17 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture17.png");
    private static final ResourceLocation texture18 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture18.png");
    private static final ResourceLocation texture19 = new ResourceLocation("chaospersists", "textures/entity/stinkytexture19.png");

    public RenderStinky(EntityRendererManager manager, ModelStinky par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(Stinky entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(Stinky entity) {
        Stinky s = (Stinky)entity;
        int i = s.getSkin();
        if (i == 1) {
            return texture2;
        }
        if (i == 2) {
            return texture3;
        }
        if (i == 3) {
            return texture4;
        }
        if (i == 4) {
            return texture5;
        }
        if (i == 5) {
            return texture6;
        }
        if (i == 6) {
            return texture7;
        }
        if (i == 7) {
            return texture8;
        }
        if (i == 8) {
            return texture9;
        }
        if (i == 9) {
            return texture10;
        }
        if (i == 10) {
            return texture11;
        }
        if (i == 11) {
            return texture12;
        }
        if (i == 12) {
            return texture13;
        }
        if (i == 13) {
            return texture14;
        }
        if (i == 14) {
            return texture15;
        }
        if (i == 15) {
            return texture16;
        }
        if (i == 16) {
            return texture17;
        }
        if (i == 17) {
            return texture18;
        }
        if (i == 18) {
            return texture19;
        }
        return texture1;
    }
}

