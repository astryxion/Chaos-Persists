/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelUrchin
 *  com.astryxion.chaospersists.Urchin
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Urchin;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelUrchin extends EntityModel<Urchin> {
    private float wingspeed = 1.0f;
    ModelRenderer if1;
    ModelRenderer if2;
    ModelRenderer if3;
    ModelRenderer if4;
    ModelRenderer of1;
    ModelRenderer of2;
    ModelRenderer of3;
    ModelRenderer of4;
    ModelRenderer center;
    ModelRenderer tis1;
    ModelRenderer tis2;
    ModelRenderer tis3;
    ModelRenderer tis4;
    ModelRenderer tos1;
    ModelRenderer tos2;
    ModelRenderer tos3;
    ModelRenderer tos4;

    public ModelUrchin(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.if1 = new ModelRenderer(this, 0, 35);
        this.if1.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.if1.setPos(0.0f, 16.0f, 0.0f);
        this.if1.mirror = true;
        this.setRotation(this.if1, 0.2617994f, 0.0f, 0.0f);
        this.if2 = new ModelRenderer(this, 5, 35);
        this.if2.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.if2.setPos(0.0f, 16.0f, 0.0f);
        this.if2.mirror = true;
        this.setRotation(this.if2, -0.2617994f, 0.0f, 0.0f);
        this.if3 = new ModelRenderer(this, 10, 35);
        this.if3.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.if3.setPos(0.0f, 16.0f, 0.0f);
        this.if3.mirror = true;
        this.setRotation(this.if3, 0.0f, 0.0f, 0.2617994f);
        this.if4 = new ModelRenderer(this, 15, 35);
        this.if4.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.if4.setPos(0.0f, 16.0f, 0.0f);
        this.if4.mirror = true;
        this.setRotation(this.if4, 0.0f, 0.0f, -0.2617994f);
        this.of1 = new ModelRenderer(this, 0, 45);
        this.of1.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.of1.setPos(2.0f, 16.0f, 0.0f);
        this.of1.mirror = true;
        this.setRotation(this.of1, 0.0f, 0.0f, -0.5235988f);
        this.of2 = new ModelRenderer(this, 5, 45);
        this.of2.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.of2.setPos(-2.0f, 16.0f, 0.0f);
        this.of2.mirror = true;
        this.setRotation(this.of2, 0.0f, 0.0f, 0.5235988f);
        this.of3 = new ModelRenderer(this, 10, 45);
        this.of3.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.of3.setPos(0.0f, 16.0f, -2.0f);
        this.of3.mirror = true;
        this.setRotation(this.of3, -0.5235988f, 0.0f, 0.0f);
        this.of4 = new ModelRenderer(this, 15, 45);
        this.of4.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.of4.setPos(0.0f, 16.0f, 2.0f);
        this.of4.mirror = true;
        this.setRotation(this.of4, 0.5235988f, 0.0f, 0.0f);
        this.center = new ModelRenderer(this, 0, 0);
        this.center.addBox(0.0f, -30.0f, 0.0f, 1, 30, 1);
        this.center.setPos(0.0f, 16.0f, 0.0f);
        this.center.mirror = true;
        this.setRotation(this.center, 0.0f, 0.0f, 0.0f);
        this.tis1 = new ModelRenderer(this, 25, 0);
        this.tis1.addBox(0.0f, -25.0f, 0.0f, 1, 25, 1);
        this.tis1.setPos(0.0f, 16.0f, 0.0f);
        this.tis1.mirror = true;
        this.setRotation(this.tis1, 0.2617994f, 0.0f, 0.0f);
        this.tis2 = new ModelRenderer(this, 30, 0);
        this.tis2.addBox(0.0f, -25.0f, 0.0f, 1, 25, 1);
        this.tis2.setPos(0.0f, 16.0f, 0.0f);
        this.tis2.mirror = true;
        this.setRotation(this.tis2, -0.2617994f, 0.0f, 0.0f);
        this.tis3 = new ModelRenderer(this, 35, 0);
        this.tis3.addBox(0.0f, -25.0f, 0.0f, 1, 25, 1);
        this.tis3.setPos(0.0f, 16.0f, 0.0f);
        this.tis3.mirror = true;
        this.setRotation(this.tis3, 0.0f, 0.0f, 0.2617994f);
        this.tis4 = new ModelRenderer(this, 40, 0);
        this.tis4.addBox(0.0f, -25.0f, 0.0f, 1, 25, 1);
        this.tis4.setPos(0.0f, 16.0f, 0.0f);
        this.tis4.mirror = true;
        this.setRotation(this.tis4, 0.0f, 0.0f, -0.2617994f);
        this.tos1 = new ModelRenderer(this, 5, 0);
        this.tos1.addBox(0.0f, -20.0f, 0.0f, 1, 20, 1);
        this.tos1.setPos(0.0f, 16.0f, 2.0f);
        this.tos1.mirror = true;
        this.setRotation(this.tos1, -0.5235988f, 0.0f, 0.0f);
        this.tos2 = new ModelRenderer(this, 10, 0);
        this.tos2.addBox(-2.0f, -20.0f, 0.0f, 1, 20, 1);
        this.tos2.setPos(0.0f, 16.0f, 0.0f);
        this.tos2.mirror = true;
        this.setRotation(this.tos2, 0.0f, 0.0f, -0.5235988f);
        this.tos3 = new ModelRenderer(this, 15, 0);
        this.tos3.addBox(0.0f, -20.0f, 0.0f, 1, 20, 1);
        this.tos3.setPos(2.0f, 16.0f, 0.0f);
        this.tos3.mirror = true;
        this.setRotation(this.tos3, 0.0f, 0.0f, 0.5235988f);
        this.tos4 = new ModelRenderer(this, 20, 0);
        this.tos4.addBox(0.0f, -20.0f, 0.0f, 1, 20, 1);
        this.tos4.setPos(0.0f, 16.0f, -2.0f);
        this.tos4.mirror = true;
        this.setRotation(this.tos4, 0.5235988f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Urchin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle1;
        float newangle5;
        float newangle2;
        float newangle;
        float newangle3;
        float newangle8;
        float newangle4;
        float newangle7;
        float newangle6;
        Urchin u = (Urchin)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if ((double)f1 > 0.1) {
            newangle = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.15f * f1;
            newangle1 = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.15f * f1;
            newangle2 = MathHelper.cos((float)(f2 * 1.65f * this.wingspeed)) * 3.1415927f * 0.15f * f1;
            newangle3 = MathHelper.cos((float)(f2 * 1.75f * this.wingspeed)) * 3.1415927f * 0.15f * f1;
            newangle4 = MathHelper.cos((float)(f2 * 1.8f * this.wingspeed)) * 3.1415927f * 0.15f * f1;
        } else {
            newangle = 0.0f;
            newangle1 = 0.0f;
            newangle2 = 0.0f;
            newangle3 = 0.0f;
            newangle4 = 0.0f;
        }
        this.if1.xRot = 0.261f + newangle1;
        this.if2.xRot = -0.261f - newangle2;
        this.if3.xRot = newangle3;
        this.if4.xRot = - newangle4;
        this.of1.zRot = -0.523f + newangle;
        this.of2.zRot = 0.523f - newangle;
        this.of3.xRot = -0.523f + newangle;
        this.of4.xRot = 0.523f - newangle;
        if (u.getAttacking() != 0) {
            newangle = (float)((double)(f2 * 0.2f) % 6.283185307179586);
            newangle1 = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle2 = MathHelper.cos((float)(f2 * 0.65f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle3 = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle4 = MathHelper.cos((float)(f2 * 0.8f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle5 = MathHelper.cos((float)(f2 * 0.55f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle6 = MathHelper.cos((float)(f2 * 0.45f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle7 = MathHelper.cos((float)(f2 * 0.35f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle8 = MathHelper.cos((float)(f2 * 0.4f * this.wingspeed)) * 3.1415927f * 0.06f;
        } else {
            newangle = (float)((double)(f2 * 0.02f) % 6.283185307179586);
            newangle1 = MathHelper.cos((float)(f2 * 0.07f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle2 = MathHelper.cos((float)(f2 * 0.065f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle3 = MathHelper.cos((float)(f2 * 0.075f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle4 = MathHelper.cos((float)(f2 * 0.08f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle5 = MathHelper.cos((float)(f2 * 0.055f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle6 = MathHelper.cos((float)(f2 * 0.045f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle7 = MathHelper.cos((float)(f2 * 0.035f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle8 = MathHelper.cos((float)(f2 * 0.04f * this.wingspeed)) * 3.1415927f * 0.02f;
        }
        this.center.yRot = newangle;
        this.tis1.xRot = 0.261f + newangle1;
        this.tis2.xRot = -0.261f + newangle2;
        this.tis3.xRot = newangle3;
        this.tis4.xRot = newangle4;
        this.tis1.zRot = newangle5;
        this.tis2.zRot = newangle6;
        this.tis3.zRot = 0.261f + newangle7;
        this.tis4.zRot = -0.261f + newangle8;
        this.tos1.xRot = -0.532f + newangle1;
        this.tos2.xRot = newangle7;
        this.tos3.xRot = newangle3;
        this.tos4.xRot = 0.532f + newangle5;
        this.tos1.zRot = newangle4;
        this.tos2.zRot = -0.523f + newangle6;
        this.tos3.zRot = 0.523f + newangle2;
        this.tos4.zRot = newangle8;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.if1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.if2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.if3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.if4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.center.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

