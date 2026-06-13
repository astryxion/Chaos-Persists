/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelSpyro
 *  com.astryxion.chaospersists.Spyro
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Spyro;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpyro extends EntityModel<Spyro> {
    private float wingspeed = 1.0f;
    ModelRenderer RightFrontPaw;
    ModelRenderer WingLeft;
    ModelRenderer LegRightFrontTop;
    ModelRenderer LegRightFrontBottom;
    ModelRenderer LegRightBackTop;
    ModelRenderer LegRightBackBottom;
    ModelRenderer RightBackPaw;
    ModelRenderer LegLeftFrontTop;
    ModelRenderer SnoutRight;
    ModelRenderer LeftFrontPaw;
    ModelRenderer LegLeftBackTop;
    ModelRenderer LegLeftBackBottom;
    ModelRenderer LeftBackPaw;
    ModelRenderer LegLeftFrontBottom;
    ModelRenderer TailPieceSmall;
    ModelRenderer JawPiece;
    ModelRenderer HeadPieceBottom;
    ModelRenderer HeadPieceTop;
    ModelRenderer HornRightBottom;
    ModelRenderer HornLeftBottom;
    ModelRenderer HornRightTop;
    ModelRenderer HornLeftTop;
    ModelRenderer Torso;
    ModelRenderer SnoutLeft;
    ModelRenderer WingPieceLeft;
    ModelRenderer WingRight;
    ModelRenderer WingPieceRight;
    ModelRenderer Neck;
    ModelRenderer TailBack;
    ModelRenderer TailFront;
    ModelRenderer ScaleBackHead;
    ModelRenderer TailPieceLarge;
    ModelRenderer ScaleTailPiece;
    ModelRenderer ScaleHead;
    ModelRenderer ScaleTop1;
    ModelRenderer ScaleBackPiece1;
    ModelRenderer ScaleBackPiece2;

    public ModelSpyro(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.RightFrontPaw = new ModelRenderer(this, 12, 31);
        this.RightFrontPaw.addBox(0.0f, 5.0f, -4.0f, 2, 1, 4);
        this.RightFrontPaw.setPos(3.0f, 18.0f, -2.0f);
        this.RightFrontPaw.mirror = true;
        this.setRotation(this.RightFrontPaw, 0.0f, 0.0f, 0.0f);
        this.WingLeft = new ModelRenderer(this, 2, 51);
        this.WingLeft.addBox(-10.0f, -1.0f, -2.0f, 10, 0, 4);
        this.WingLeft.setPos(-1.0f, 16.0f, 0.0f);
        this.WingLeft.mirror = true;
        this.setRotation(this.WingLeft, 0.1745329f, 0.0f, -0.1745329f);
        this.LegRightFrontTop = new ModelRenderer(this, 20, 19);
        this.LegRightFrontTop.addBox(0.0f, 0.0f, -2.0f, 2, 3, 3);
        this.LegRightFrontTop.setPos(3.0f, 18.0f, -2.0f);
        this.LegRightFrontTop.mirror = true;
        this.setRotation(this.LegRightFrontTop, -0.0872665f, 0.0f, 0.0f);
        this.LegRightFrontBottom = new ModelRenderer(this, 0, 25);
        this.LegRightFrontBottom.addBox(0.0f, 2.0f, -1.5f, 2, 4, 2);
        this.LegRightFrontBottom.setPos(3.0f, 18.0f, -2.0f);
        this.LegRightFrontBottom.mirror = true;
        this.setRotation(this.LegRightFrontBottom, -0.1745329f, 0.0f, 0.0f);
        this.LegRightBackTop = new ModelRenderer(this, 30, 19);
        this.LegRightBackTop.addBox(0.0f, 0.0f, -2.0f, 2, 3, 3);
        this.LegRightBackTop.setPos(3.0f, 18.0f, 3.0f);
        this.LegRightBackTop.mirror = true;
        this.setRotation(this.LegRightBackTop, 0.1396263f, 0.0f, 0.0f);
        this.LegRightBackBottom = new ModelRenderer(this, 16, 25);
        this.LegRightBackBottom.addBox(0.0f, 2.0f, -1.0f, 2, 4, 2);
        this.LegRightBackBottom.setPos(3.0f, 18.0f, 3.0f);
        this.LegRightBackBottom.mirror = true;
        this.setRotation(this.LegRightBackBottom, -0.1745329f, 0.0f, 0.0f);
        this.RightBackPaw = new ModelRenderer(this, 36, 31);
        this.RightBackPaw.addBox(0.0f, 5.0f, -3.0f, 2, 1, 4);
        this.RightBackPaw.setPos(3.0f, 18.0f, 3.0f);
        this.RightBackPaw.mirror = true;
        this.setRotation(this.RightBackPaw, 0.0f, 0.0f, 0.0f);
        this.LegLeftFrontTop = new ModelRenderer(this, 0, 19);
        this.LegLeftFrontTop.addBox(-2.0f, 0.0f, -1.0f, 2, 3, 3);
        this.LegLeftFrontTop.setPos(-2.0f, 18.0f, -3.0f);
        this.LegLeftFrontTop.mirror = true;
        this.setRotation(this.LegLeftFrontTop, -0.0872665f, 0.0f, 0.0f);
        this.SnoutRight = new ModelRenderer(this, 48, 2);
        this.SnoutRight.addBox(1.0f, -3.0f, -5.0f, 1, 1, 1);
        this.SnoutRight.setPos(1.0f, 16.0f, -3.0f);
        this.SnoutRight.mirror = true;
        this.setRotation(this.SnoutRight, 0.0f, 0.0f, 0.0f);
        this.LeftFrontPaw = new ModelRenderer(this, 0, 31);
        this.LeftFrontPaw.addBox(-2.0f, 5.0f, -3.0f, 2, 1, 4);
        this.LeftFrontPaw.setPos(-2.0f, 18.0f, -3.0f);
        this.LeftFrontPaw.mirror = true;
        this.setRotation(this.LeftFrontPaw, 0.0f, 0.0f, 0.0f);
        this.LegLeftBackTop = new ModelRenderer(this, 10, 19);
        this.LegLeftBackTop.addBox(-2.0f, 0.0f, -2.0f, 2, 3, 3);
        this.LegLeftBackTop.setPos(-2.0f, 18.0f, 3.0f);
        this.LegLeftBackTop.mirror = true;
        this.setRotation(this.LegLeftBackTop, 0.1396263f, 0.0f, 0.0f);
        this.LegLeftBackBottom = new ModelRenderer(this, 24, 25);
        this.LegLeftBackBottom.addBox(-2.0f, 2.0f, -1.0f, 2, 4, 2);
        this.LegLeftBackBottom.setPos(-2.0f, 18.0f, 3.0f);
        this.LegLeftBackBottom.mirror = true;
        this.setRotation(this.LegLeftBackBottom, -0.1745329f, 0.0f, 0.0f);
        this.LeftBackPaw = new ModelRenderer(this, 24, 31);
        this.LeftBackPaw.addBox(-2.0f, 5.0f, -3.0f, 2, 1, 4);
        this.LeftBackPaw.setPos(-2.0f, 18.0f, 3.0f);
        this.LeftBackPaw.mirror = true;
        this.setRotation(this.LeftBackPaw, 0.0f, 0.0f, 0.0f);
        this.LegLeftFrontBottom = new ModelRenderer(this, 8, 25);
        this.LegLeftFrontBottom.addBox(-2.0f, 2.0f, -0.5f, 2, 4, 2);
        this.LegLeftFrontBottom.setPos(-2.0f, 18.0f, -3.0f);
        this.LegLeftFrontBottom.mirror = true;
        this.setRotation(this.LegLeftFrontBottom, -0.1745329f, 0.0f, 0.0f);
        this.TailPieceSmall = new ModelRenderer(this, 28, 36);
        this.TailPieceSmall.addBox(0.0f, -0.5f, 4.0f, 1, 1, 1);
        this.TailPieceSmall.setPos(0.0f, 16.0f, 7.0f);
        this.TailPieceSmall.mirror = true;
        this.setRotation(this.TailPieceSmall, 0.1745329f, 0.0f, 0.0f);
        this.JawPiece = new ModelRenderer(this, 52, 0);
        this.JawPiece.addBox(-2.0f, -1.0f, -4.0f, 3, 1, 3);
        this.JawPiece.setPos(1.0f, 16.0f, -3.0f);
        this.JawPiece.mirror = true;
        this.setRotation(this.JawPiece, 0.1745329f, 0.0f, 0.0f);
        this.HeadPieceBottom = new ModelRenderer(this, 30, 7);
        this.HeadPieceBottom.addBox(-3.0f, -2.0f, -5.0f, 5, 2, 6);
        this.HeadPieceBottom.setPos(1.0f, 16.0f, -3.0f);
        this.HeadPieceBottom.mirror = true;
        this.setRotation(this.HeadPieceBottom, 0.0f, 0.0f, 0.0f);
        this.HeadPieceTop = new ModelRenderer(this, 30, 0);
        this.HeadPieceTop.addBox(-3.0f, -5.0f, -3.0f, 5, 3, 4);
        this.HeadPieceTop.setPos(1.0f, 16.0f, -3.0f);
        this.HeadPieceTop.mirror = true;
        this.setRotation(this.HeadPieceTop, 0.0f, 0.0f, 0.0f);
        this.HornRightBottom = new ModelRenderer(this, 8, 14);
        this.HornRightBottom.addBox(0.0f, -6.0f, -3.5f, 2, 3, 2);
        this.HornRightBottom.setPos(1.0f, 16.0f, -3.0f);
        this.HornRightBottom.mirror = true;
        this.setRotation(this.HornRightBottom, -0.7853982f, 0.7853982f, 0.0f);
        this.HornLeftBottom = new ModelRenderer(this, 0, 14);
        this.HornLeftBottom.addBox(-2.75f, -6.5f, -3.0f, 2, 3, 2);
        this.HornLeftBottom.setPos(1.0f, 16.0f, -3.0f);
        this.HornLeftBottom.mirror = true;
        this.setRotation(this.HornLeftBottom, -0.7853982f, -0.7853982f, 0.0f);
        this.HornRightTop = new ModelRenderer(this, 20, 14);
        this.HornRightTop.addBox(0.5f, -9.0f, -3.0f, 1, 3, 1);
        this.HornRightTop.setPos(1.0f, 16.0f, -3.0f);
        this.HornRightTop.mirror = true;
        this.setRotation(this.HornRightTop, -0.7853982f, 0.7853982f, 0.0f);
        this.HornLeftTop = new ModelRenderer(this, 16, 14);
        this.HornLeftTop.addBox(-2.2f, -9.5f, -2.5f, 1, 3, 1);
        this.HornLeftTop.setPos(1.0f, 16.0f, -3.0f);
        this.HornLeftTop.mirror = true;
        this.setRotation(this.HornLeftTop, -0.7853982f, -0.7853982f, 0.0f);
        this.Torso = new ModelRenderer(this, 0, 0);
        this.Torso.addBox(-2.0f, -2.0f, -5.0f, 5, 4, 10);
        this.Torso.setPos(0.0f, 19.0f, 0.0f);
        this.Torso.mirror = true;
        this.setRotation(this.Torso, 0.0f, 0.0f, 0.0f);
        this.SnoutLeft = new ModelRenderer(this, 48, 0);
        this.SnoutLeft.addBox(-3.0f, -3.0f, -5.0f, 1, 1, 1);
        this.SnoutLeft.setPos(1.0f, 16.0f, -3.0f);
        this.SnoutLeft.mirror = true;
        this.setRotation(this.SnoutLeft, 0.0f, 0.0f, 0.0f);
        this.WingPieceLeft = new ModelRenderer(this, 4, 42);
        this.WingPieceLeft.addBox(-1.0f, -2.0f, -1.0f, 1, 2, 1);
        this.WingPieceLeft.setPos(0.0f, 17.2f, 0.0f);
        this.WingPieceLeft.mirror = true;
        this.setRotation(this.WingPieceLeft, 0.1745329f, 0.0f, -0.1745329f);
        this.WingRight = new ModelRenderer(this, 2, 45);
        this.WingRight.addBox(0.0f, -1.0f, -2.0f, 10, 0, 4);
        this.WingRight.setPos(2.0f, 16.0f, 0.0f);
        this.WingRight.mirror = true;
        this.setRotation(this.WingRight, 0.1745329f, 0.0f, 0.1745329f);
        this.WingPieceRight = new ModelRenderer(this, 0, 42);
        this.WingPieceRight.addBox(-1.0f, -2.0f, 0.0f, 1, 2, 1);
        this.WingPieceRight.setPos(2.0f, 17.5f, -1.0f);
        this.WingPieceRight.mirror = true;
        this.setRotation(this.WingPieceRight, 0.1745329f, 0.0f, 0.1745329f);
        this.Neck = new ModelRenderer(this, 52, 7);
        this.Neck.addBox(-1.0f, -2.0f, -1.0f, 3, 3, 3);
        this.Neck.setPos(0.0f, 17.0f, -4.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.4537856f, 0.0f, 0.0f);
        this.TailBack = new ModelRenderer(this, 0, 36);
        this.TailBack.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 4);
        this.TailBack.setPos(0.5f, 17.5f, 5.0f);
        this.TailBack.mirror = true;
        this.setRotation(this.TailBack, 0.4537856f, 0.0f, 0.0f);
        this.TailFront = new ModelRenderer(this, 12, 36);
        this.TailFront.addBox(0.0f, 0.0f, -1.0f, 1, 1, 4);
        this.TailFront.setPos(0.0f, 16.0f, 7.0f);
        this.TailFront.mirror = true;
        this.setRotation(this.TailFront, 0.2617994f, 0.0f, 0.0f);
        this.ScaleBackHead = new ModelRenderer(this, 38, 36);
        this.ScaleBackHead.addBox(-1.0f, -3.0f, 2.0f, 1, 2, 1);
        this.ScaleBackHead.setPos(1.0f, 16.0f, -4.0f);
        this.ScaleBackHead.mirror = true;
        this.setRotation(this.ScaleBackHead, 0.0f, 0.0f, 0.0f);
        this.TailPieceLarge = new ModelRenderer(this, 22, 36);
        this.TailPieceLarge.addBox(0.0f, -1.0f, 2.0f, 1, 2, 2);
        this.TailPieceLarge.setPos(0.0f, 16.0f, 7.0f);
        this.TailPieceLarge.mirror = true;
        this.setRotation(this.TailPieceLarge, 0.1745329f, 0.0f, 0.0f);
        this.ScaleTailPiece = new ModelRenderer(this, 48, 36);
        this.ScaleTailPiece.addBox(-0.5f, -2.0f, 0.2f, 1, 1, 2);
        this.ScaleTailPiece.setPos(0.5f, 17.5f, 5.0f);
        this.ScaleTailPiece.mirror = true;
        this.setRotation(this.ScaleTailPiece, 0.4537856f, 0.0f, 0.0f);
        this.ScaleHead = new ModelRenderer(this, 42, 36);
        this.ScaleHead.addBox(-1.0f, -6.0f, 0.0f, 1, 2, 2);
        this.ScaleHead.setPos(1.0f, 16.0f, -3.0f);
        this.ScaleHead.mirror = true;
        this.setRotation(this.ScaleHead, 0.0f, 0.0f, 0.0f);
        this.ScaleTop1 = new ModelRenderer(this, 48, 36);
        this.ScaleTop1.addBox(-1.0f, -6.0f, -4.0f, 1, 1, 2);
        this.ScaleTop1.setPos(1.0f, 16.0f, -2.0f);
        this.ScaleTop1.mirror = true;
        this.setRotation(this.ScaleTop1, 0.0f, 0.0f, 0.0f);
        this.ScaleBackPiece1 = new ModelRenderer(this, 48, 36);
        this.ScaleBackPiece1.addBox(0.0f, -1.0f, -1.0f, 1, 1, 2);
        this.ScaleBackPiece1.setPos(0.0f, 17.0f, 0.0f);
        this.ScaleBackPiece1.mirror = true;
        this.setRotation(this.ScaleBackPiece1, 0.0f, 0.0f, 0.0f);
        this.ScaleBackPiece2 = new ModelRenderer(this, 48, 36);
        this.ScaleBackPiece2.addBox(0.0f, -1.0f, -1.0f, 1, 1, 2);
        this.ScaleBackPiece2.setPos(0.0f, 17.0f, 3.0f);
        this.ScaleBackPiece2.mirror = true;
        this.setRotation(this.ScaleBackPiece2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Spyro c, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float hf = 0.0f;
        float newangle = 0.0f;
        int current_activity = c.getActivity();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, c);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.4f * f1 : 0.0f;
        if (current_activity == 3) {
            newangle *= 0.5f;
        }
        this.WingLeft.zRot = newangle;
        this.WingRight.zRot = - newangle;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        if (current_activity == 3) {
            newangle = 0.0f;
        }
        if (current_activity != 2) {
            this.LegRightFrontTop.xRot = newangle - 0.087f;
            this.LegRightFrontBottom.xRot = newangle - 0.17f;
            this.RightFrontPaw.xRot = newangle;
            this.LegLeftFrontTop.xRot = - newangle - 0.087f;
            this.LegLeftFrontBottom.xRot = - newangle - 0.17f;
            this.LeftFrontPaw.xRot = - newangle;
            this.LegRightBackBottom.xRot = - newangle + 0.139f;
            this.LegRightBackTop.xRot = - newangle - 0.174f;
            this.RightBackPaw.xRot = - newangle;
            this.LegLeftBackBottom.xRot = newangle + 0.139f;
            this.LegLeftBackTop.xRot = newangle - 0.174f;
            this.LeftBackPaw.xRot = newangle;
        } else {
            newangle = -1.0f;
            this.LegRightFrontTop.xRot = newangle - 0.087f;
            this.LegRightFrontBottom.xRot = newangle - 0.17f;
            this.RightFrontPaw.xRot = newangle;
            this.LegLeftFrontTop.xRot = newangle - 0.087f;
            this.LegLeftFrontBottom.xRot = newangle - 0.17f;
            this.LeftFrontPaw.xRot = newangle;
            newangle = 1.0f;
            this.LegRightBackBottom.xRot = newangle + 0.139f;
            this.LegRightBackTop.xRot = newangle - 0.174f;
            this.RightBackPaw.xRot = newangle;
            this.LegLeftBackBottom.xRot = newangle + 0.139f;
            this.LegLeftBackTop.xRot = newangle - 0.174f;
            this.LeftBackPaw.xRot = newangle;
        }
        newangle = MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.25f;
        if (c.isOrderedToSit() || current_activity == 3) {
            newangle = 0.0f;
        }
        this.TailBack.yRot = newangle;
        this.ScaleTailPiece.yRot = newangle;
        this.TailFront.z = this.TailBack.z + (float)Math.cos(this.TailBack.yRot) * 3.0f;
        this.TailFront.x = this.TailBack.x + (float)Math.sin(this.TailBack.yRot) * 3.0f - 0.5f;
        this.TailFront.yRot = newangle * 1.6f;
        this.TailPieceLarge.z = this.TailFront.z;
        this.TailPieceLarge.x = this.TailFront.x;
        this.TailPieceLarge.yRot = this.TailFront.yRot;
        this.TailPieceSmall.z = this.TailFront.z;
        this.TailPieceSmall.x = this.TailFront.x;
        this.TailPieceSmall.yRot = this.TailFront.yRot;
        this.HeadPieceTop.yRot = (float)Math.toRadians(f3);
        this.HeadPieceBottom.yRot = (float)Math.toRadians(f3);
        this.JawPiece.yRot = (float)Math.toRadians(f3);
        this.SnoutRight.yRot = (float)Math.toRadians(f3);
        this.SnoutLeft.yRot = (float)Math.toRadians(f3);
        this.ScaleTop1.yRot = (float)Math.toRadians(f3);
        this.ScaleHead.yRot = (float)Math.toRadians(f3);
        this.ScaleBackHead.yRot = (float)Math.toRadians(f3);
        this.HornRightBottom.yRot = (float)Math.toRadians(f3) + 0.785f;
        this.HornRightTop.yRot = (float)Math.toRadians(f3) + 0.785f;
        this.HornLeftBottom.yRot = (float)Math.toRadians(f3) - 0.785f;
        this.HornLeftTop.yRot = (float)Math.toRadians(f3) - 0.785f;
        this.HeadPieceTop.xRot = (float)Math.toRadians(f4);
        this.HeadPieceBottom.xRot = (float)Math.toRadians(f4);
        this.JawPiece.xRot = (float)Math.toRadians(f4);
        this.SnoutRight.xRot = (float)Math.toRadians(f4);
        this.SnoutLeft.xRot = (float)Math.toRadians(f4);
        this.ScaleTop1.xRot = (float)Math.toRadians(f4);
        this.ScaleHead.xRot = (float)Math.toRadians(f4);
        this.ScaleBackHead.xRot = (float)Math.toRadians(f4);
        this.HornRightBottom.xRot = (float)Math.toRadians(f4) - 0.785f;
        this.HornRightTop.xRot = (float)Math.toRadians(f4) - 0.785f;
        this.HornLeftBottom.xRot = (float)Math.toRadians(f4) - 0.785f;
        this.HornLeftTop.xRot = (float)Math.toRadians(f4) - 0.785f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.RightFrontPaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightFrontTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightFrontBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightBackTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightBackBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightBackPaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftFrontTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.SnoutRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftFrontPaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftBackTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftBackBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftBackPaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftFrontBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailPieceSmall.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.JawPiece.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadPieceBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadPieceTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornRightBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornLeftBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornRightTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornLeftTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.SnoutLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingPieceLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingPieceRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBack.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailFront.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleBackHead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailPieceLarge.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleTailPiece.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleHead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleTop1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleBackPiece1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleBackPiece2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

