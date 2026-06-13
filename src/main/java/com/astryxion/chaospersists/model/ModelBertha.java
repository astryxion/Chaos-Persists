/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelBertha
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelBertha extends Model {
    ModelRenderer Grip;
    ModelRenderer Blade;
    ModelRenderer Handguard2;
    ModelRenderer Handguard1;
    ModelRenderer hg2;
    ModelRenderer hg4;
    ModelRenderer hg3;
    ModelRenderer hg1;
    ModelRenderer BaseGrip;
    ModelRenderer Tip1;
    ModelRenderer Tip2;
    ModelRenderer Bottom;

    public ModelBertha() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 128;
        this.Grip = new ModelRenderer(this, 0, 0);
        this.Grip.addBox(0.0f, -6.0f, 0.0f, 1, 12, 1);
        this.Grip.setPos(0.0f, 0.0f, 0.0f);
        this.Grip.mirror = true;
        this.setRotation(this.Grip, 0.0f, 0.0f, 0.0f);
        this.Blade = new ModelRenderer(this, 6, 0);
        this.Blade.addBox(0.0f, -41.0f, -1.0f, 1, 34, 3);
        this.Blade.setPos(0.0f, 0.0f, 0.0f);
        this.Blade.mirror = true;
        this.setRotation(this.Blade, 0.0f, 0.0f, 0.0f);
        this.Handguard2 = new ModelRenderer(this, 16, 0);
        this.Handguard2.addBox(0.0f, -7.0f, -4.0f, 1, 1, 9);
        this.Handguard2.setPos(0.0f, 0.0f, 0.0f);
        this.Handguard2.mirror = true;
        this.setRotation(this.Handguard2, 0.0f, 0.0f, 0.0f);
        this.Handguard1 = new ModelRenderer(this, 18, 12);
        this.Handguard1.addBox(-3.0f, -7.0f, 0.0f, 7, 1, 1);
        this.Handguard1.setPos(0.0f, 0.0f, 0.0f);
        this.Handguard1.mirror = true;
        this.setRotation(this.Handguard1, 0.0f, 0.0f, 0.0f);
        this.hg2 = new ModelRenderer(this, 0, 15);
        this.hg2.addBox(0.0f, -8.0f, -5.0f, 1, 1, 1);
        this.hg2.setPos(0.0f, 0.0f, 0.0f);
        this.hg2.mirror = true;
        this.setRotation(this.hg2, 0.0f, 0.0f, 0.0f);
        this.hg4 = new ModelRenderer(this, 0, 18);
        this.hg4.addBox(0.0f, -8.0f, 5.0f, 1, 1, 1);
        this.hg4.setPos(0.0f, 0.0f, 0.0f);
        this.hg4.mirror = true;
        this.setRotation(this.hg4, 0.0f, 0.0f, 0.0f);
        this.hg3 = new ModelRenderer(this, 0, 21);
        this.hg3.addBox(-4.0f, -8.0f, 0.0f, 1, 1, 1);
        this.hg3.setPos(0.0f, 0.0f, 0.0f);
        this.hg3.mirror = true;
        this.setRotation(this.hg3, 0.0f, 0.0f, 0.0f);
        this.hg1 = new ModelRenderer(this, 0, 24);
        this.hg1.addBox(4.0f, -8.0f, 0.0f, 1, 1, 1);
        this.hg1.setPos(0.0f, 0.0f, 0.0f);
        this.hg1.mirror = true;
        this.setRotation(this.hg1, 0.0f, 0.0f, 0.0f);
        this.BaseGrip = new ModelRenderer(this, 0, 39);
        this.BaseGrip.addBox(-1.0f, 5.0f, -1.0f, 3, 1, 3);
        this.BaseGrip.setPos(0.0f, 0.0f, 0.0f);
        this.BaseGrip.mirror = true;
        this.setRotation(this.BaseGrip, 0.0f, 0.0f, 0.0f);
        this.Tip1 = new ModelRenderer(this, 21, 16);
        this.Tip1.addBox(0.0f, -42.0f, -0.5f, 1, 1, 2);
        this.Tip1.setPos(0.0f, 0.0f, 0.0f);
        this.Tip1.mirror = true;
        this.setRotation(this.Tip1, 0.0f, 0.0f, 0.0f);
        this.Tip2 = new ModelRenderer(this, 22, 20);
        this.Tip2.addBox(0.0f, -43.0f, 0.0f, 1, 1, 1);
        this.Tip2.setPos(0.0f, 0.0f, 0.0f);
        this.Tip2.mirror = true;
        this.setRotation(this.Tip2, 0.0f, 0.0f, 0.0f);
        this.Bottom = new ModelRenderer(this, 0, 45);
        this.Bottom.addBox(0.0f, 6.0f, 0.0f, 1, 1, 1);
        this.Bottom.setPos(0.0f, 0.0f, 0.0f);
        this.Bottom.mirror = true;
        this.setRotation(this.Bottom, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float f5 = 1.0f;
        this.Grip.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Blade.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Handguard2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Handguard1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.hg2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.hg4.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.hg3.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.hg1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.BaseGrip.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Tip1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Tip2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.Bottom.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}

