/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelSquidZooka
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelSquidZooka extends Model {
    ModelRenderer Barrel;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tail5;
    ModelRenderer tail6;
    ModelRenderer tail7;
    ModelRenderer sight3;
    ModelRenderer sight2;
    ModelRenderer sight1;
    ModelRenderer handle1;

    public ModelSquidZooka() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 128;
        // textureHeight = 128;
        this.Barrel = new ModelRenderer(this, 29, 19);
        this.Barrel.addBox(-1.0f, -1.0f, -19.0f, 2, 2, 34);
        this.Barrel.setPos(0.0f, 0.0f, 0.0f);
        this.Barrel.mirror = true;
        this.setRotation(this.Barrel, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 0, 53);
        this.tail1.addBox(-1.5f, -1.5f, 15.0f, 3, 3, 1);
        this.tail1.setPos(0.0f, 0.0f, 0.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 0, 58);
        this.tail2.addBox(-2.0f, -2.0f, 16.0f, 4, 4, 1);
        this.tail2.setPos(0.0f, 0.0f, 0.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 0, 64);
        this.tail3.addBox(-2.5f, -2.5f, 17.0f, 5, 5, 1);
        this.tail3.setPos(0.0f, 0.0f, 0.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.0f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 0, 71);
        this.tail4.addBox(-3.0f, -3.0f, 18.0f, 6, 6, 6);
        this.tail4.setPos(0.0f, 0.0f, 0.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, 0.0f, 0.0f, 0.0f);
        this.tail5 = new ModelRenderer(this, 0, 84);
        this.tail5.addBox(-2.5f, -2.5f, 24.0f, 5, 5, 1);
        this.tail5.setPos(0.0f, 0.0f, 0.0f);
        this.tail5.mirror = true;
        this.setRotation(this.tail5, 0.0f, 0.0f, 0.0f);
        this.tail6 = new ModelRenderer(this, 0, 91);
        this.tail6.addBox(-2.0f, -2.0f, 25.0f, 4, 4, 1);
        this.tail6.setPos(0.0f, 0.0f, 0.0f);
        this.tail6.mirror = true;
        this.setRotation(this.tail6, 0.0f, 0.0f, 0.0f);
        this.tail7 = new ModelRenderer(this, 0, 97);
        this.tail7.addBox(-1.5f, -1.5f, 26.0f, 3, 3, 1);
        this.tail7.setPos(0.0f, 0.0f, 0.0f);
        this.tail7.mirror = true;
        this.setRotation(this.tail7, 0.0f, 0.0f, 0.0f);
        this.sight3 = new ModelRenderer(this, 25, 0);
        this.sight3.addBox(1.0f, -2.0f, -10.0f, 1, 1, 2);
        this.sight3.setPos(0.0f, 0.0f, 0.0f);
        this.sight3.mirror = true;
        this.setRotation(this.sight3, 0.0f, 0.0f, 0.0f);
        this.sight2 = new ModelRenderer(this, 32, 0);
        this.sight2.addBox(0.5f, -4.0f, -12.0f, 2, 2, 6);
        this.sight2.setPos(0.0f, 0.0f, 0.0f);
        this.sight2.mirror = true;
        this.setRotation(this.sight2, 0.0f, 0.0f, 0.0f);
        this.sight1 = new ModelRenderer(this, 18, 0);
        this.sight1.addBox(1.0f, -1.0f, -10.0f, 1, 1, 2);
        this.sight1.setPos(0.0f, 0.0f, 0.0f);
        this.sight1.mirror = true;
        this.setRotation(this.sight1, 0.0f, 0.0f, 0.0f);
        this.handle1 = new ModelRenderer(this, 0, 0);
        this.handle1.addBox(0.0f, 1.0f, 0.0f, 1, 7, 1);
        this.handle1.setPos(0.0f, 0.0f, 0.0f);
        this.handle1.mirror = true;
        this.setRotation(this.handle1, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float f5 = 1.0f;
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.Barrel.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.tail5.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.tail6.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.tail7.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.sight3.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.sight2.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.sight1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.handle1.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}

