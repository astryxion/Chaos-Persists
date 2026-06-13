/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRobot1
 *  com.astryxion.chaospersists.Robot1
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Robot1;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;

import net.minecraft.util.math.MathHelper;

public class ModelRobot1 extends EntityModel<Robot1> {
    private float wingspeed = 1.0f;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape2a;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape15a;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer rfoot;
    ModelRenderer lfoot;
    ModelRenderer key2;
    ModelRenderer key1;
    ModelRenderer key3;
    ModelRenderer key4;
    ModelRenderer key5;

    public ModelRobot1(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 3, 9, 3);
        this.Shape1.setPos(-1.0f, 13.0f, -1.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 0, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 1, 9, 5);
        this.Shape2.setPos(0.0f, 13.0f, -2.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape2a = new ModelRenderer(this, 0, 0);
        this.Shape2a.addBox(0.0f, 0.0f, 0.0f, 5, 9, 1);
        this.Shape2a.setPos(-2.0f, 13.0f, 0.0f);
        this.Shape2a.mirror = true;
        this.setRotation(this.Shape2a, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 0, 0);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 7, 7, 3);
        this.Shape3.setPos(-3.0f, 14.0f, -1.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer(this, 0, 0);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 3, 7, 7);
        this.Shape4.setPos(-1.0f, 14.0f, -3.0f);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer(this, 0, 0);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 5, 7, 5);
        this.Shape5.setPos(-2.0f, 14.0f, -2.0f);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer(this, 0, 0);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 5, 5, 7);
        this.Shape6.setPos(-2.0f, 15.0f, -3.0f);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer(this, 0, 0);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.Shape7.setPos(0.0f, 15.0f, 4.0f);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer(this, 0, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 7, 5, 5);
        this.Shape8.setPos(-3.0f, 15.0f, -2.0f);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer(this, 0, 0);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 9, 5, 1);
        this.Shape9.setPos(-4.0f, 15.0f, 0.0f);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer(this, 0, 0);
        this.Shape10.addBox(0.0f, 0.0f, 1.0f, 3, 3, 8);
        this.Shape10.setPos(-1.0f, 16.0f, -4.0f);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer(this, 0, 0);
        this.Shape11.addBox(0.0f, 0.0f, 0.0f, 9, 3, 3);
        this.Shape11.setPos(-4.0f, 16.0f, -1.0f);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer(this, 0, 0);
        this.Shape12.addBox(0.0f, 0.0f, 0.0f, 7, 3, 7);
        this.Shape12.setPos(-3.0f, 16.0f, -3.0f);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        this.Shape13 = new ModelRenderer(this, 0, 0);
        this.Shape13.addBox(0.0f, 0.0f, 0.0f, 9, 1, 5);
        this.Shape13.setPos(-4.0f, 17.0f, -2.0f);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        this.Shape14 = new ModelRenderer(this, 0, 0);
        this.Shape14.addBox(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.Shape14.setPos(-2.0f, 17.0f, 4.0f);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        this.Shape15 = new ModelRenderer(this, 32, 0);
        this.Shape15.addBox(0.0f, 0.0f, 0.0f, 2, 3, 1);
        this.Shape15.setPos(-2.0f, 15.0f, -4.0f);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        this.Shape15a = new ModelRenderer(this, 32, 0);
        this.Shape15a.addBox(0.0f, 0.0f, 0.0f, 2, 3, 1);
        this.Shape15a.setPos(1.0f, 15.0f, -4.0f);
        this.Shape15a.mirror = true;
        this.setRotation(this.Shape15a, 0.0f, 0.0f, 0.0f);
        this.Shape16 = new ModelRenderer(this, 45, 0);
        this.Shape16.addBox(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.Shape16.setPos(-1.0f, 12.0f, -1.0f);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer(this, 33, 7);
        this.Shape17.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape17.setPos(0.0f, 10.0f, 0.0f);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
        this.Shape18 = new ModelRenderer(this, 33, 7);
        this.Shape18.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape18.setPos(1.7f, 8.733334f, 0.0f);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0f, 0.0f, 0.9667472f);
        this.rfoot = new ModelRenderer(this, 46, 8);
        this.rfoot.addBox(0.0f, 3.0f, -2.0f, 2, 2, 4);
        this.rfoot.setPos(-3.0f, 19.0f, 0.0f);
        this.rfoot.mirror = true;
        this.setRotation(this.rfoot, 0.0f, 0.0f, 0.0f);
        this.lfoot = new ModelRenderer(this, 46, 8);
        this.lfoot.addBox(0.0f, 3.0f, -2.0f, 2, 2, 4);
        this.lfoot.setPos(2.0f, 19.0f, 0.0f);
        this.lfoot.mirror = true;
        this.setRotation(this.lfoot, 0.0f, 0.0f, 0.0f);
        this.key2 = new ModelRenderer(this, 46, 8);
        this.key2.addBox(-0.5f, -1.5f, 1.0f, 1, 3, 1);
        this.key2.setPos(0.5f, 17.5f, 5.0f);
        this.key2.mirror = true;
        this.setRotation(this.key2, 0.0f, 0.0f, 0.0f);
        this.key1 = new ModelRenderer(this, 46, 8);
        this.key1.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 3);
        this.key1.setPos(0.5f, 17.5f, 5.0f);
        this.key1.mirror = true;
        this.setRotation(this.key1, 0.0f, 0.0f, 0.0f);
        this.key3 = new ModelRenderer(this, 46, 8);
        this.key3.addBox(-0.5f, -2.5f, 1.0f, 1, 1, 2);
        this.key3.setPos(0.5f, 17.5f, 5.0f);
        this.key3.mirror = true;
        this.setRotation(this.key3, 0.0f, 0.0f, 0.0f);
        this.key4 = new ModelRenderer(this, 46, 8);
        this.key4.addBox(-0.5f, 1.5f, 1.0f, 1, 1, 2);
        this.key4.setPos(0.5f, 17.5f, 5.0f);
        this.key4.mirror = true;
        this.setRotation(this.key4, 0.0f, 0.0f, 0.0f);
        this.key5 = new ModelRenderer(this, 46, 8);
        this.key5.addBox(-0.5f, -1.5f, 3.0f, 1, 3, 1);
        this.key5.setPos(0.5f, 17.5f, 5.0f);
        this.key5.mirror = true;
        this.setRotation(this.key5, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Robot1 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Robot1 e = (Robot1)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.75f * f1 : 0.0f;
        this.lfoot.xRot = newangle;
        this.rfoot.xRot = - newangle;
        this.key1.zRot = newangle = (float)Math.toRadians(f2 * 0.75f * this.wingspeed);
        this.key2.zRot = newangle;
        this.key3.zRot = newangle;
        this.key4.zRot = newangle;
        this.key5.zRot = newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2a.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15a.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape18.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Robot1 par7Entity) {
        
    }
}

