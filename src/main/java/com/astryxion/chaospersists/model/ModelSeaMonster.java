/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelSeaMonster
 *  com.astryxion.chaospersists.SeaMonster
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.SeaMonster;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSeaMonster extends EntityModel<SeaMonster> {
    private float wingspeed = 1.0f;
    ModelRenderer TailTip;
    ModelRenderer TailBase;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer BodyBack;
    ModelRenderer Neck6;
    ModelRenderer BodyFront;
    ModelRenderer NeckBase;
    ModelRenderer Neck2;
    ModelRenderer Neck3;
    ModelRenderer Neck4;
    ModelRenderer Neck5;
    ModelRenderer BottomJaw;
    ModelRenderer FinBackRight;
    ModelRenderer FinBackLeft;
    ModelRenderer FinFrontLeft;
    ModelRenderer FinFrontRight;
    ModelRenderer Tail4;
    ModelRenderer Tail5;
    ModelRenderer Tail6;
    ModelRenderer TopJaw;
    ModelRenderer RightEye;
    ModelRenderer LeftEye;

    public ModelSeaMonster(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 128;
        this.TailTip = new ModelRenderer(this, 158, 36);
        this.TailTip.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 6);
        this.TailTip.setPos(0.0f, 16.0f, 70.0f);
        this.TailTip.mirror = true;
        this.setRotation(this.TailTip, 0.0f, 0.0f, 0.0f);
        this.TailBase = new ModelRenderer(this, 68, 64);
        this.TailBase.addBox(-7.0f, -7.0f, 0.0f, 14, 14, 12);
        this.TailBase.setPos(0.0f, 16.0f, 26.0f);
        this.TailBase.mirror = true;
        this.setRotation(this.TailBase, 0.0f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 74, 90);
        this.Tail2.addBox(-6.0f, -6.0f, 0.0f, 12, 12, 8);
        this.Tail2.setPos(0.0f, 16.0f, 38.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, 0.0f, 0.0f, 0.0f);
        this.Tail3 = new ModelRenderer(this, 78, 110);
        this.Tail3.addBox(-5.0f, -5.0f, 0.0f, 10, 10, 6);
        this.Tail3.setPos(0.0f, 16.0f, 46.0f);
        this.Tail3.mirror = true;
        this.setRotation(this.Tail3, 0.0f, 0.0f, 0.0f);
        this.BodyBack = new ModelRenderer(this, 62, 32);
        this.BodyBack.addBox(-8.0f, -8.0f, 0.0f, 16, 16, 16);
        this.BodyBack.setPos(0.0f, 16.0f, 10.0f);
        this.BodyBack.mirror = true;
        this.setRotation(this.BodyBack, 0.0f, 0.0f, 0.0f);
        this.Neck6 = new ModelRenderer(this, 20, 28);
        this.Neck6.addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4);
        this.Neck6.setPos(0.0f, -21.0f, -25.0f);
        this.Neck6.mirror = true;
        this.setRotation(this.Neck6, 1.22173f, 0.0f, 0.0f);
        this.BodyFront = new ModelRenderer(this, 62, 0);
        this.BodyFront.addBox(-8.0f, -8.0f, -16.0f, 16, 16, 16);
        this.BodyFront.setPos(0.0f, 16.0f, 10.0f);
        this.BodyFront.mirror = true;
        this.setRotation(this.BodyFront, 0.0f, 0.0f, 0.0f);
        this.NeckBase = new ModelRenderer(this, 8, 96);
        this.NeckBase.addBox(-5.0f, -10.0f, -5.0f, 10, 10, 10);
        this.NeckBase.setPos(0.0f, 12.0f, -2.0f);
        this.NeckBase.mirror = true;
        this.setRotation(this.NeckBase, 0.7853982f, 0.0f, 0.0f);
        this.Neck2 = new ModelRenderer(this, 14, 78);
        this.Neck2.addBox(-3.0f, -10.0f, -4.0f, 6, 10, 8);
        this.Neck2.setPos(0.0f, 6.0f, -9.0f);
        this.Neck2.mirror = true;
        this.setRotation(this.Neck2, 0.6981317f, 0.0f, 0.0f);
        this.Neck3 = new ModelRenderer(this, 16, 62);
        this.Neck3.addBox(-3.0f, -10.0f, -3.0f, 6, 10, 6);
        this.Neck3.setPos(0.0f, -1.0f, -15.0f);
        this.Neck3.mirror = true;
        this.setRotation(this.Neck3, 0.5235988f, 0.0f, 0.0f);
        this.Neck4 = new ModelRenderer(this, 20, 48);
        this.Neck4.addBox(-2.0f, -10.0f, -2.0f, 4, 10, 4);
        this.Neck4.setPos(0.0f, -9.0f, -20.0f);
        this.Neck4.mirror = true;
        this.setRotation(this.Neck4, 0.2617994f, 0.0f, 0.0f);
        this.Neck5 = new ModelRenderer(this, 20, 38);
        this.Neck5.addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4);
        this.Neck5.setPos(0.0f, -17.6f, -22.0f);
        this.Neck5.mirror = true;
        this.setRotation(this.Neck5, 0.7853982f, 0.0f, 0.0f);
        this.BottomJaw = new ModelRenderer(this, 10, 0);
        this.BottomJaw.addBox(-4.0f, 0.0f, -10.0f, 8, 3, 10);
        this.BottomJaw.setPos(0.0f, -23.0f, -29.0f);
        this.BottomJaw.mirror = true;
        this.setRotation(this.BottomJaw, 0.1745329f, 0.0f, 0.0f);
        this.FinBackRight = new ModelRenderer(this, 132, 95);
        this.FinBackRight.addBox(-8.0f, 0.0f, 0.0f, 8, 1, 16);
        this.FinBackRight.setPos(-7.0f, 16.0f, 16.0f);
        this.FinBackRight.mirror = true;
        this.setRotation(this.FinBackRight, -0.5235988f, -0.6981317f, 0.0f);
        this.FinBackLeft = new ModelRenderer(this, 132, 61);
        this.FinBackLeft.addBox(0.0f, 0.0f, 0.0f, 8, 1, 16);
        this.FinBackLeft.setPos(7.0f, 16.0f, 16.0f);
        this.FinBackLeft.mirror = true;
        this.setRotation(this.FinBackLeft, -0.5235988f, 0.6981317f, 0.0f);
        this.FinFrontLeft = new ModelRenderer(this, 132, 44);
        this.FinFrontLeft.addBox(0.0f, 0.0f, 0.0f, 8, 1, 16);
        this.FinFrontLeft.setPos(7.0f, 16.0f, -1.0f);
        this.FinFrontLeft.mirror = true;
        this.setRotation(this.FinFrontLeft, -0.5235988f, 0.6981317f, 0.0f);
        this.FinFrontRight = new ModelRenderer(this, 132, 78);
        this.FinFrontRight.addBox(-8.0f, 0.0f, 0.0f, 8, 1, 16);
        this.FinFrontRight.setPos(-7.0f, 16.0f, -1.0f);
        this.FinFrontRight.mirror = true;
        this.setRotation(this.FinFrontRight, -0.5235988f, -0.6981317f, 0.0f);
        this.Tail4 = new ModelRenderer(this, 152, 0);
        this.Tail4.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 6);
        this.Tail4.setPos(0.0f, 16.0f, 52.0f);
        this.Tail4.mirror = true;
        this.setRotation(this.Tail4, 0.0f, 0.0f, 0.0f);
        this.Tail5 = new ModelRenderer(this, 154, 14);
        this.Tail5.addBox(-3.0f, -3.0f, 0.0f, 6, 6, 6);
        this.Tail5.setPos(0.0f, 16.0f, 58.0f);
        this.Tail5.mirror = true;
        this.setRotation(this.Tail5, 0.0f, 0.0f, 0.0f);
        this.Tail6 = new ModelRenderer(this, 156, 26);
        this.Tail6.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.Tail6.setPos(0.0f, 16.0f, 64.0f);
        this.Tail6.mirror = true;
        this.setRotation(this.Tail6, 0.0f, 0.0f, 0.0f);
        this.TopJaw = new ModelRenderer(this, 10, 13);
        this.TopJaw.addBox(-4.0f, -4.0f, -10.0f, 8, 4, 10);
        this.TopJaw.setPos(0.0f, -23.0f, -29.0f);
        this.TopJaw.mirror = true;
        this.setRotation(this.TopJaw, 0.0f, 0.0f, 0.0f);
        this.RightEye = new ModelRenderer(this, 46, 16);
        this.RightEye.addBox(-3.0f, -6.0f, -5.0f, 2, 2, 1);
        this.RightEye.setPos(0.0f, -23.0f, -29.0f);
        this.RightEye.mirror = true;
        this.setRotation(this.RightEye, 0.0f, 0.0f, 0.0f);
        this.LeftEye = new ModelRenderer(this, 4, 16);
        this.LeftEye.addBox(1.0f, -6.0f, -5.0f, 2, 2, 1);
        this.LeftEye.setPos(0.0f, -23.0f, -29.0f);
        this.LeftEye.mirror = true;
        this.setRotation(this.LeftEye, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(SeaMonster entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        SeaMonster e = (SeaMonster)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float pi4 = 0.7853982f;
        newangle = (double)f1 > 0.1 || e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.2f * f1 : 0.0f;
        this.TailBase.yRot = newangle / 7.0f;
        this.Tail2.z = this.TailBase.z + (float)Math.cos(this.TailBase.yRot) * 10.0f;
        this.Tail2.x = this.TailBase.x + (float)Math.sin(this.TailBase.yRot) * 10.0f;
        this.Tail2.yRot = newangle / 6.0f;
        this.Tail3.z = this.Tail2.z + (float)Math.cos(this.Tail2.yRot) * 7.0f;
        this.Tail3.x = this.Tail2.x + (float)Math.sin(this.Tail2.yRot) * 7.0f;
        this.Tail3.yRot = newangle / 5.0f;
        this.Tail4.z = this.Tail3.z + (float)Math.cos(this.Tail3.yRot) * 5.0f;
        this.Tail4.x = this.Tail3.x + (float)Math.sin(this.Tail3.yRot) * 5.0f;
        this.Tail4.yRot = newangle / 4.0f;
        this.Tail5.z = this.Tail4.z + (float)Math.cos(this.Tail4.yRot) * 5.0f;
        this.Tail5.x = this.Tail4.x + (float)Math.sin(this.Tail4.yRot) * 5.0f;
        this.Tail5.yRot = newangle / 3.0f;
        this.Tail6.z = this.Tail5.z + (float)Math.cos(this.Tail5.yRot) * 5.0f;
        this.Tail6.x = this.Tail5.x + (float)Math.sin(this.Tail5.yRot) * 5.0f;
        this.Tail6.yRot = newangle / 2.0f;
        this.TailTip.z = this.Tail6.z + (float)Math.cos(this.Tail6.yRot) * 5.0f;
        this.TailTip.x = this.Tail6.x + (float)Math.sin(this.Tail6.yRot) * 5.0f;
        this.TailTip.yRot = newangle;
        newangle = (double)f1 > 0.1 || e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.2f * f1 : MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.02f;
        this.FinFrontLeft.xRot = newangle - 0.523f;
        this.FinFrontLeft.yRot = newangle + 0.698f;
        this.FinBackLeft.xRot = - newangle - 0.523f;
        this.FinBackLeft.yRot = - newangle + 0.698f;
        this.FinFrontRight.xRot = newangle - 0.523f;
        this.FinFrontRight.yRot = newangle - 0.698f;
        this.FinBackRight.xRot = - newangle - 0.523f;
        this.FinBackRight.yRot = - newangle - 0.698f;
        newangle = (double)f1 > 0.1 || e.getAttacking() != 0 ? 0.455f * f1 + MathHelper.cos((float)(f2 * 0.9f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.02f;
        this.NeckBase.xRot = 0.455f + newangle / 5.0f;
        this.Neck2.z = this.NeckBase.z - (float)Math.sin(this.NeckBase.xRot) * 9.0f;
        this.Neck2.y = this.NeckBase.y - (float)Math.cos(this.NeckBase.xRot) * 9.0f;
        this.Neck2.xRot = this.NeckBase.xRot + newangle / 4.0f;
        this.Neck3.z = this.Neck2.z - (float)Math.sin(this.Neck2.xRot) * 9.0f;
        this.Neck3.y = this.Neck2.y - (float)Math.cos(this.Neck2.xRot) * 9.0f;
        this.Neck3.xRot = this.Neck2.xRot + newangle / 3.0f;
        this.Neck4.z = this.Neck3.z - (float)Math.sin(this.Neck3.xRot) * 9.0f;
        this.Neck4.y = this.Neck3.y - (float)Math.cos(this.Neck3.xRot) * 9.0f;
        this.Neck4.xRot = this.Neck3.xRot + newangle / 2.0f;
        this.Neck5.z = this.Neck4.z - (float)Math.sin(this.Neck4.xRot) * 9.0f;
        this.Neck5.y = this.Neck4.y - (float)Math.cos(this.Neck4.xRot) * 9.0f;
        this.Neck5.xRot = this.Neck4.xRot - newangle / 2.0f;
        this.Neck6.z = this.Neck5.z - (float)Math.sin(this.Neck5.xRot) * 5.0f;
        this.Neck6.y = this.Neck5.y - (float)Math.cos(this.Neck5.xRot) * 5.0f;
        this.Neck6.xRot = this.Neck5.xRot - newangle / 3.0f;
        this.RightEye.z = this.TopJaw.z = this.Neck6.z - (float)Math.sin(this.Neck6.xRot) * 5.0f;
        this.LeftEye.z = this.TopJaw.z;
        this.BottomJaw.z = this.TopJaw.z;
        this.RightEye.y = this.TopJaw.y = this.Neck6.y - (float)Math.cos(this.Neck6.xRot) * 5.0f;
        this.LeftEye.y = this.TopJaw.y;
        this.BottomJaw.y = this.TopJaw.y;
        this.RightEye.yRot = this.TopJaw.yRot = (newangle = (float)Math.toRadians(f3) * 0.5f);
        this.LeftEye.yRot = this.TopJaw.yRot;
        this.BottomJaw.yRot = this.TopJaw.yRot;
        if (e.getAttacking() != 0) {
            newangle = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.17f;
            this.BottomJaw.xRot = 0.45f + newangle;
        } else {
            newangle = MathHelper.cos((float)(f2 * 0.2f * this.wingspeed)) * 3.1415927f * 0.05f;
            this.BottomJaw.xRot = 0.17f + newangle;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.TailTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyBack.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyFront.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.NeckBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomJaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinBackRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinBackLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinFrontRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopJaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightEye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftEye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

