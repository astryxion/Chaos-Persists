/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRockBase
 *  com.astryxion.chaospersists.RenderRockBase
 *  com.astryxion.chaospersists.RockBase
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
import com.astryxion.chaospersists.model.ModelRockBase;
import com.astryxion.chaospersists.entity.RockBase;
import net.minecraft.util.ResourceLocation;

public class RenderRockBase
extends LivingRenderer<RockBase, ModelRockBase> {
    protected ModelRockBase model;
    private float scale = 1.0f;
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/rocktexture.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/rocktexture.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/rockredtexture.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/rockgreentexture.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/entity/rockbluetexture.png");
    private static final ResourceLocation texture6 = new ResourceLocation("chaospersists", "textures/entity/rockpurpletexture.png");
    private static final ResourceLocation texture7 = new ResourceLocation("chaospersists", "textures/entity/rocktexture.png");
    private static final ResourceLocation texture8 = new ResourceLocation("chaospersists", "textures/entity/rocktnttexture.png");
    private static final ResourceLocation texture9 = new ResourceLocation("chaospersists", "textures/entity/rockcrystaltexture.png");
    private static final ResourceLocation texture10 = new ResourceLocation("chaospersists", "textures/entity/rockcrystalgreentexture.png");
    private static final ResourceLocation texture11 = new ResourceLocation("chaospersists", "textures/entity/rockcrystalbluetexture.png");
    private static final ResourceLocation texture12 = new ResourceLocation("chaospersists", "textures/entity/rockcrystaltnttexture.png");

    public RenderRockBase(EntityRendererManager manager, ModelRockBase par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
protected void applyScale(MatrixStack matrixStack) { matrixStack.scale(this.scale, this.scale, this.scale); }

    protected void scale(RockBase entity, MatrixStack matrixStack, float partialTick) { this.applyScale(matrixStack); }

    @Override
    public ResourceLocation getTextureLocation(RockBase entity) {
        RockBase r = (RockBase)entity;
        int i = r.getRockType();
        if (i == 1) {
            return texture1;
        }
        if (i == 2) {
            return texture2;
        }
        if (i == 3) {
            return texture3;
        }
        if (i == 4) {
            return texture4;
        }
        if (i == 5) {
            return texture5;
        }
        if (i == 6) {
            return texture6;
        }
        if (i == 7) {
            return texture7;
        }
        if (i == 8) {
            return texture8;
        }
        if (i == 9) {
            return texture9;
        }
        if (i == 10) {
            return texture10;
        }
        if (i == 11) {
            return texture11;
        }
        if (i == 12) {
            return texture12;
        }
        return texture1;
    }
}

