/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelBattleAxe
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelBattleAxe extends Model {
    ModelRenderer Handle1;
    ModelRenderer Head1;
    ModelRenderer Grip;
    ModelRenderer Pin;
    ModelRenderer Top;
    ModelRenderer Blade1;
    ModelRenderer Blade2;
    ModelRenderer Blade3;
    ModelRenderer Blade4;
    ModelRenderer Blade5;
    ModelRenderer Blade6;
    ModelRenderer Blade7;
    ModelRenderer Blade8;
    ModelRenderer Blade9;
    ModelRenderer Blade10;

    public ModelBattleAxe() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 128;
        // textureHeight = 64;
        this.Handle1 = new ModelRenderer(this, 0, 0);
        this.Handle1.addBox(-7.0f, -0.5f, 0.0f, 31, 2, 1);
        this.Handle1.setPos(0.0f, -12.0f, 0.0f);
        this.Handle1.mirror = true;
        this.setRotation(this.Handle1, 0.0f, 0.0f, 1.570796f);
        this.Head1 = new ModelRenderer(this, 29, 18);
        this.Head1.addBox(-2.0f, -4.5f, -0.5f, 3, 4, 2);
        this.Head1.setPos(0.0f, -12.0f, 0.0f);
        this.Head1.mirror = true;
        this.setRotation(this.Head1, 0.0f, 0.0f, 0.0f);
        this.Grip = new ModelRenderer(this, 0, 7);
        this.Grip.addBox(-1.92f, 13.0f, -0.5f, 3, 11, 2);
        this.Grip.setPos(0.0f, -12.0f, 0.0f);
        this.Grip.mirror = true;
        this.setRotation(this.Grip, 0.0f, 0.0f, 0.0f);
        this.Pin = new ModelRenderer(this, 38, 11);
        this.Pin.addBox(-1.0f, -3.0f, -1.0f, 1, 1, 3);
        this.Pin.setPos(0.0f, -12.0f, 0.0f);
        this.Pin.mirror = true;
        this.setRotation(this.Pin, 0.0f, 0.0f, 0.0f);
        this.Top = new ModelRenderer(this, 24, 11);
        this.Top.addBox(-2.0f, -8.0f, -0.5f, 3, 2, 2);
        this.Top.setPos(0.0f, -12.0f, 0.0f);
        this.Top.mirror = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
        this.Blade1 = new ModelRenderer(this, 70, 0);
        this.Blade1.addBox(6.0f, -8.0f, 0.0f, 3, 10, 1);
        this.Blade1.setPos(0.0f, -12.0f, 0.0f);
        this.Blade1.mirror = true;
        this.setRotation(this.Blade1, 0.0f, 0.0f, 0.5061455f);
        this.Blade2 = new ModelRenderer(this, 70, 0);
        this.Blade2.addBox(8.5f, -6.9f, 0.0f, 3, 10, 1);
        this.Blade2.setPos(0.0f, -12.0f, 0.0f);
        this.Blade2.mirror = true;
        this.setRotation(this.Blade2, 0.0f, 0.0f, -0.5061455f);
        this.Blade3 = new ModelRenderer(this, 0, 0);
        this.Blade3.addBox(-1.5f, -3.0f, 0.0f, 10, 1, 1);
        this.Blade3.setPos(0.0f, -12.0f, 0.0f);
        this.Blade3.mirror = true;
        this.setRotation(this.Blade3, 0.0f, 0.0f, 0.0f);
        this.Blade4 = new ModelRenderer(this, 0, 0);
        this.Blade4.addBox(-1.0f, -2.0f, 0.0f, 7, 1, 1);
        this.Blade4.setPos(0.0f, -12.0f, 0.0f);
        this.Blade4.mirror = true;
        this.setRotation(this.Blade4, 0.0f, 0.0f, 0.5061455f);
        this.Blade5 = new ModelRenderer(this, 0, 0);
        this.Blade5.addBox(0.5f, -3.5f, 0.0f, 8, 1, 1);
        this.Blade5.setPos(0.0f, -12.0f, 0.0f);
        this.Blade5.mirror = true;
        this.setRotation(this.Blade5, 0.0f, 0.0f, -0.5061455f);
        this.Blade6 = new ModelRenderer(this, 70, 0);
        this.Blade6.addBox(-12.2f, -5.2f, 0.0f, 3, 10, 1);
        this.Blade6.setPos(0.0f, -13.0f, 0.0f);
        this.Blade6.mirror = true;
        this.setRotation(this.Blade6, 0.0f, 0.0f, 0.5061455f);
        this.Blade7 = new ModelRenderer(this, 0, 0);
        this.Blade7.addBox(-9.9f, -3.0f, 0.0f, 8, 1, 1);
        this.Blade7.setPos(0.0f, -12.0f, 0.0f);
        this.Blade7.mirror = true;
        this.setRotation(this.Blade7, 0.0f, 0.0f, 0.5061455f);
        this.Blade8 = new ModelRenderer(this, 0, 0);
        this.Blade8.addBox(-10.0f, -3.0f, 0.0f, 10, 1, 1);
        this.Blade8.setPos(0.0f, -12.0f, 0.0f);
        this.Blade8.mirror = true;
        this.setRotation(this.Blade8, 0.0f, 0.0f, 0.0f);
        this.Blade9 = new ModelRenderer(this, 70, 0);
        this.Blade9.addBox(-10.0f, -8.5f, 0.0f, 3, 10, 1);
        this.Blade9.setPos(0.0f, -12.0f, 0.0f);
        this.Blade9.mirror = true;
        this.setRotation(this.Blade9, 0.0f, 0.0f, -0.5061455f);
        this.Blade10 = new ModelRenderer(this, 0, 0);
        this.Blade10.addBox(-7.0f, -2.5f, 0.0f, 7, 1, 1);
        this.Blade10.setPos(0.0f, -12.0f, 0.0f);
        this.Blade10.mirror = true;
        this.setRotation(this.Blade10, 0.0f, 0.0f, -0.5061455f);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float f5 = 1.0f;
        this.Handle1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Head1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Grip.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Pin.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Top.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade3.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade4.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade5.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade6.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade7.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade8.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade9.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade10.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}

