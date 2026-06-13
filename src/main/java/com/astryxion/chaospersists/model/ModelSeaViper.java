/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelSeaViper
 *  com.astryxion.chaospersists.SeaViper
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.SeaViper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSeaViper extends EntityModel<SeaViper> {
    private float wingspeed = 1.0f;
    ModelRenderer TailTip;
    ModelRenderer Neck;
    ModelRenderer tBase;
    ModelRenderer t2;
    ModelRenderer t3;
    ModelRenderer t4;
    ModelRenderer t5;
    ModelRenderer t6;
    ModelRenderer t7;
    ModelRenderer t8;
    ModelRenderer t9;
    ModelRenderer t10;
    ModelRenderer t12;
    ModelRenderer t11;
    ModelRenderer t13;
    ModelRenderer t14;
    ModelRenderer t15;
    ModelRenderer t16;
    ModelRenderer t17;
    ModelRenderer t18;
    ModelRenderer t19;
    ModelRenderer t20;
    ModelRenderer t21;
    ModelRenderer MouthBottom;
    ModelRenderer ToungBase;
    ModelRenderer MiddleTounge;
    ModelRenderer EyeRight;
    ModelRenderer EyeLeft;
    ModelRenderer MouthTop;
    ModelRenderer Head;
    ModelRenderer FangRight;
    ModelRenderer FangLeft;
    ModelRenderer ForkRight;
    ModelRenderer ForkLeft;

    public ModelSeaViper(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.TailTip = new ModelRenderer(this, 0, 90);
        this.TailTip.addBox(-1.0f, -1.0f, 0.0f, 2, 5, 10);
        this.TailTip.setPos(1.0f, 20.0f, 120.0f);
        this.TailTip.mirror = true;
        this.setRotation(this.TailTip, 0.0f, -0.6981317f, 0.0f);
        this.Neck = new ModelRenderer(this, 60, 60);
        this.Neck.addBox(-4.0f, -4.0f, -10.0f, 8, 8, 10);
        this.Neck.setPos(0.0f, 4.5f, -33.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, -0.2617994f, 0.0f, 0.0f);
        this.tBase = new ModelRenderer(this, 0, 31);
        this.tBase.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.tBase.setPos(0.0f, 4.0f, -34.0f);
        this.tBase.mirror = true;
        this.setRotation(this.tBase, -0.5235988f, 0.0f, 0.0f);
        this.t2 = new ModelRenderer(this, 0, 31);
        this.t2.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t2.setPos(0.0f, 7.0f, -27.0f);
        this.t2.mirror = true;
        this.setRotation(this.t2, -1.047198f, 0.0f, 0.0f);
        this.t3 = new ModelRenderer(this, 0, 31);
        this.t3.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t3.setPos(0.0f, 14.0f, -24.0f);
        this.t3.mirror = true;
        this.setRotation(this.t3, -0.5235988f, 0.0f, 0.0f);
        this.t4 = new ModelRenderer(this, 0, 31);
        this.t4.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t4.setPos(0.0f, 19.0f, -17.0f);
        this.t4.mirror = true;
        this.setRotation(this.t4, -0.0872665f, 0.0f, 0.0f);
        this.t5 = new ModelRenderer(this, 0, 31);
        this.t5.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t5.setPos(0.0f, 20.0f, -9.0f);
        this.t5.mirror = true;
        this.setRotation(this.t5, 0.0f, 0.0f, 0.0f);
        this.t6 = new ModelRenderer(this, 0, 31);
        this.t6.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t6.setPos(0.0f, 20.0f, -1.0f);
        this.t6.mirror = true;
        this.setRotation(this.t6, 0.0f, 0.3490659f, 0.0f);
        this.t7 = new ModelRenderer(this, 0, 31);
        this.t7.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t7.setPos(2.0f, 20.0f, 6.0f);
        this.t7.mirror = true;
        this.setRotation(this.t7, 0.0f, 0.6981317f, 0.0f);
        this.t8 = new ModelRenderer(this, 0, 31);
        this.t8.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t8.setPos(7.0f, 20.0f, 12.0f);
        this.t8.mirror = true;
        this.setRotation(this.t8, 0.0f, 0.3490659f, 0.0f);
        this.t9 = new ModelRenderer(this, 0, 31);
        this.t9.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t9.setPos(10.0f, 20.0f, 20.0f);
        this.t9.mirror = true;
        this.setRotation(this.t9, 0.0f, 0.0f, 0.0f);
        this.t10 = new ModelRenderer(this, 0, 31);
        this.t10.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t10.setPos(10.0f, 20.0f, 28.0f);
        this.t10.mirror = true;
        this.setRotation(this.t10, 0.0f, -0.3490659f, 0.0f);
        this.t12 = new ModelRenderer(this, 0, 31);
        this.t12.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t12.setPos(2.0f, 20.0f, 42.0f);
        this.t12.mirror = true;
        this.setRotation(this.t12, 0.0f, -0.6981317f, 0.0f);
        this.t11 = new ModelRenderer(this, 0, 31);
        this.t11.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t11.setPos(8.0f, 20.0f, 35.0f);
        this.t11.mirror = true;
        this.setRotation(this.t11, 0.0f, -0.6981317f, 0.0f);
        this.t13 = new ModelRenderer(this, 0, 31);
        this.t13.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10);
        this.t13.setPos(-4.0f, 20.0f, 48.0f);
        this.t13.mirror = true;
        this.setRotation(this.t13, 0.0f, -0.3490659f, 0.0f);
        this.t14 = new ModelRenderer(this, 0, 51);
        this.t14.addBox(-3.0f, -3.0f, 0.0f, 6, 7, 10);
        this.t14.setPos(-8.0f, 20.0f, 56.0f);
        this.t14.mirror = true;
        this.setRotation(this.t14, 0.0f, 0.0f, 0.0f);
        this.t15 = new ModelRenderer(this, 0, 51);
        this.t15.addBox(-3.0f, -3.0f, 0.0f, 6, 7, 10);
        this.t15.setPos(-8.0f, 20.0f, 65.0f);
        this.t15.mirror = true;
        this.setRotation(this.t15, 0.0f, 0.3490659f, 0.0f);
        this.t16 = new ModelRenderer(this, 0, 51);
        this.t16.addBox(-3.0f, -3.0f, 0.0f, 6, 7, 10);
        this.t16.setPos(-5.0f, 20.0f, 73.0f);
        this.t16.mirror = true;
        this.setRotation(this.t16, 0.0f, 0.6981317f, 0.0f);
        this.t17 = new ModelRenderer(this, 0, 70);
        this.t17.addBox(-2.0f, -2.0f, 0.0f, 4, 6, 10);
        this.t17.setPos(1.0f, 20.0f, 80.0f);
        this.t17.mirror = true;
        this.setRotation(this.t17, 0.0f, 0.6981317f, 0.0f);
        this.t18 = new ModelRenderer(this, 0, 70);
        this.t18.addBox(-2.0f, -2.0f, 0.0f, 4, 6, 10);
        this.t18.setPos(7.0f, 20.0f, 87.0f);
        this.t18.mirror = true;
        this.setRotation(this.t18, 0.0f, 0.3490659f, 0.0f);
        this.t19 = new ModelRenderer(this, 0, 70);
        this.t19.addBox(-2.0f, -2.0f, 0.0f, 4, 6, 10);
        this.t19.setPos(10.0f, 20.0f, 95.0f);
        this.t19.mirror = true;
        this.setRotation(this.t19, 0.0f, 0.0f, 0.0f);
        this.t20 = new ModelRenderer(this, 0, 90);
        this.t20.addBox(-1.0f, -1.0f, 0.0f, 2, 5, 10);
        this.t20.setPos(10.0f, 20.0f, 104.0f);
        this.t20.mirror = true;
        this.setRotation(this.t20, 0.0f, -0.3490659f, 0.0f);
        this.t21 = new ModelRenderer(this, 0, 90);
        this.t21.addBox(-1.0f, -1.0f, 0.0f, 2, 5, 10);
        this.t21.setPos(7.0f, 20.0f, 113.0f);
        this.t21.mirror = true;
        this.setRotation(this.t21, 0.0f, -0.6981317f, 0.0f);
        this.MouthBottom = new ModelRenderer(this, 58, 78);
        this.MouthBottom.addBox(-4.0f, 0.0f, -12.0f, 8, 2, 12);
        this.MouthBottom.setPos(0.0f, 4.0f, -42.0f);
        this.MouthBottom.mirror = true;
        this.setRotation(this.MouthBottom, 0.5235988f, 0.0f, 0.0f);
        this.ToungBase = new ModelRenderer(this, 70, 17);
        this.ToungBase.addBox(-1.0f, -2.0f, -11.0f, 2, 1, 6);
        this.ToungBase.setPos(0.0f, 6.0f, -40.0f);
        this.ToungBase.mirror = true;
        this.setRotation(this.ToungBase, 0.2617994f, 0.0f, 0.0f);
        this.MiddleTounge = new ModelRenderer(this, 70, 10);
        this.MiddleTounge.addBox(-1.0f, -1.0f, -17.0f, 2, 1, 6);
        this.MiddleTounge.setPos(0.0f, 6.0f, -40.0f);
        this.MiddleTounge.mirror = true;
        this.setRotation(this.MiddleTounge, 0.1745329f, 0.0f, 0.0f);
        this.EyeRight = new ModelRenderer(this, 96, 60);
        this.EyeRight.addBox(-7.0f, -7.0f, -3.0f, 1, 3, 4);
        this.EyeRight.setPos(0.0f, 6.0f, -40.0f);
        this.EyeRight.mirror = true;
        this.setRotation(this.EyeRight, 0.3490659f, 0.0f, 0.0f);
        this.EyeLeft = new ModelRenderer(this, 50, 60);
        this.EyeLeft.addBox(6.0f, -7.0f, -3.0f, 1, 3, 4);
        this.EyeLeft.setPos(0.0f, 6.0f, -40.0f);
        this.EyeLeft.mirror = true;
        this.setRotation(this.EyeLeft, 0.3490659f, 0.0f, 0.0f);
        this.MouthTop = new ModelRenderer(this, 52, 24);
        this.MouthTop.addBox(-5.0f, -6.0f, -16.0f, 10, 6, 16);
        this.MouthTop.setPos(0.0f, 6.0f, -40.0f);
        this.MouthTop.mirror = true;
        this.setRotation(this.MouthTop, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 60, 46);
        this.Head.addBox(-6.0f, -8.0f, -6.0f, 12, 8, 6);
        this.Head.setPos(0.0f, 6.0f, -40.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.FangRight = new ModelRenderer(this, 92, 18);
        this.FangRight.addBox(-4.0f, -3.0f, -15.0f, 1, 5, 1);
        this.FangRight.setPos(0.0f, 6.0f, -40.0f);
        this.FangRight.mirror = true;
        this.setRotation(this.FangRight, 0.1745329f, 0.0f, 0.0f);
        this.FangLeft = new ModelRenderer(this, 60, 18);
        this.FangLeft.addBox(3.0f, -3.0f, -15.0f, 1, 5, 1);
        this.FangLeft.setPos(0.0f, 6.0f, -40.0f);
        this.FangLeft.mirror = true;
        this.setRotation(this.FangLeft, 0.1745329f, 0.0f, 0.0f);
        this.ForkRight = new ModelRenderer(this, 60, 3);
        this.ForkRight.addBox(6.0f, 0.6f, -21.0f, 2, 1, 6);
        this.ForkRight.setPos(0.0f, 6.0f, -40.0f);
        this.ForkRight.mirror = true;
        this.setRotation(this.ForkRight, 0.0872665f, 0.4363323f, 0.0f);
        this.ForkLeft = new ModelRenderer(this, 80, 3);
        this.ForkLeft.addBox(-8.0f, 0.6f, -21.0f, 2, 1, 6);
        this.ForkLeft.setPos(0.0f, 6.0f, -40.0f);
        this.ForkLeft.mirror = true;
        this.setRotation(this.ForkLeft, 0.0872665f, -0.4363323f, 0.0f);
        this.TailTip.z += 32.0f;
        this.Neck.z += 32.0f;
        this.tBase.z += 32.0f;
        this.t2.z += 32.0f;
        this.t3.z += 32.0f;
        this.t4.z += 32.0f;
        this.t5.z += 32.0f;
        this.t6.z += 32.0f;
        this.t7.z += 32.0f;
        this.t8.z += 32.0f;
        this.t9.z += 32.0f;
        this.t10.z += 32.0f;
        this.t12.z += 32.0f;
        this.t11.z += 32.0f;
        this.t13.z += 32.0f;
        this.t14.z += 32.0f;
        this.t15.z += 32.0f;
        this.t16.z += 32.0f;
        this.t17.z += 32.0f;
        this.t18.z += 32.0f;
        this.t19.z += 32.0f;
        this.t20.z += 32.0f;
        this.t21.z += 32.0f;
        this.MouthBottom.z += 32.0f;
        this.ToungBase.z += 32.0f;
        this.MiddleTounge.z += 32.0f;
        this.EyeRight.z += 32.0f;
        this.EyeLeft.z += 32.0f;
        this.MouthTop.z += 32.0f;
        this.Head.z += 32.0f;
        this.FangRight.z += 32.0f;
        this.FangLeft.z += 32.0f;
        this.ForkRight.z += 32.0f;
        this.ForkLeft.z += 32.0f;
    }
    @Override
    public void setupAnim(SeaViper entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        SeaViper e = (SeaViper)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        this.tBase.yRot = newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f * f1;
        this.doseg(this.tBase, this.t2, 2.0f, f1, f2);
        this.doseg(this.t2, this.t3, 2.0f, f1, f2);
        this.doseg(this.t3, this.t4, 3.0f, f1, f2);
        this.doseg(this.t4, this.t5, 4.0f, f1, f2);
        this.doseg(this.t5, this.t6, 5.0f, f1, f2);
        this.doseg(this.t6, this.t7, 6.0f, f1, f2);
        this.doseg(this.t7, this.t8, 7.0f, f1, f2);
        this.doseg(this.t8, this.t9, 8.0f, f1, f2);
        this.doseg(this.t9, this.t10, 9.0f, f1, f2);
        this.doseg(this.t10, this.t11, 10.0f, f1, f2);
        this.doseg(this.t11, this.t12, 11.0f, f1, f2);
        this.doseg(this.t12, this.t13, 12.0f, f1, f2);
        this.doseg(this.t13, this.t14, 13.0f, f1, f2);
        this.doseg(this.t14, this.t15, 14.0f, f1, f2);
        this.doseg(this.t15, this.t16, 15.0f, f1, f2);
        this.doseg(this.t16, this.t17, 16.0f, f1, f2);
        this.doseg(this.t17, this.t18, 17.0f, f1, f2);
        this.doseg(this.t18, this.t19, 18.0f, f1, f2);
        this.doseg(this.t19, this.t20, 19.0f, f1, f2);
        this.doseg(this.t20, this.t21, 20.0f, f1, f2);
        this.doseg(this.t21, this.TailTip, 21.0f, f1, f2);
        if (e.getAttacking() != 0) {
            newangle = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.17f;
            this.MouthBottom.xRot = 0.65f + newangle;
            newangle = MathHelper.cos((float)(f2 * 4.7f * this.wingspeed)) * 3.1415927f * 0.07f;
            this.ToungBase.xRot = 0.261f + newangle;
            this.MiddleTounge.xRot = 0.174f + newangle;
            this.ForkLeft.xRot = 0.087f + newangle;
            this.ForkRight.xRot = 0.087f + newangle;
            this.ForkLeft.z = this.ForkRight.z = (newangle = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.05f);
            this.MiddleTounge.z = this.ForkRight.z;
            this.ToungBase.z = this.ForkRight.z;
        } else {
            newangle = MathHelper.cos((float)(f2 * 0.2f * this.wingspeed)) * 3.1415927f * 0.02f;
            this.MouthBottom.xRot = 0.45f + newangle;
            newangle = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.03f;
            this.ToungBase.xRot = 0.261f + newangle;
            this.MiddleTounge.xRot = 0.174f + newangle;
            this.ForkLeft.xRot = 0.087f + newangle;
            this.ForkRight.xRot = 0.087f + newangle;
            this.ForkLeft.z = this.ForkRight.z = (newangle = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f);
            this.MiddleTounge.z = this.ForkRight.z;
            this.ToungBase.z = this.ForkRight.z;
        }
        this.EyeLeft.yRot = this.EyeRight.yRot = (newangle = (float)Math.toRadians(f3) * 0.5f);
        this.MouthTop.yRot = this.EyeRight.yRot;
        this.Head.yRot = this.EyeRight.yRot;
        this.FangLeft.yRot = this.FangRight.yRot = newangle;
        this.MouthBottom.yRot = newangle;
        this.MouthBottom.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 2.0f;
        this.MouthBottom.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 2.0f;
        this.ToungBase.yRot = newangle;
        this.MiddleTounge.yRot = newangle;
        this.ForkLeft.yRot = newangle - 0.436f;
        this.ForkRight.yRot = newangle + 0.436f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.TailTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t18.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t19.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t20.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MouthBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ToungBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MiddleTounge.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.EyeRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.EyeLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MouthTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FangRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FangLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ForkRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ForkLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void doseg(ModelRenderer inn, ModelRenderer notinn, float f, float f1, float f2) {
        float pi4 = 0.7853982f;
        float newangle = 0.0f;
        notinn.z = (float)((double)inn.z + (double)((float)Math.cos(inn.yRot)) * (9.0 * Math.abs(Math.cos(inn.xRot))));
        notinn.x = (float)((double)inn.x + (double)((float)Math.sin(inn.yRot) * 9.0f) * Math.abs(Math.cos(inn.xRot)));
        newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - pi4 * f)) * 3.1415927f * 0.2f * f1;
        float a = MathHelper.cos((float)(- pi4 * f));
        notinn.yRot = newangle + a - a * f1;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

