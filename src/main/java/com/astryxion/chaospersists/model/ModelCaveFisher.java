/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CaveFisher
 *  com.astryxion.chaospersists.ModelCaveFisher
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.CaveFisher;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelCaveFisher extends EntityModel<CaveFisher> {
    private float wingspeed = 1.0f;
    ModelRenderer Nose;
    ModelRenderer EyeLeft;
    ModelRenderer HeadMid;
    ModelRenderer HeadEnd;
    ModelRenderer TailTuft;
    ModelRenderer EyeRight;
    ModelRenderer BodyTopLeft4;
    ModelRenderer BodyTopRight4;
    ModelRenderer BodyTopLeft1;
    ModelRenderer BodyTopRight1;
    ModelRenderer BodyTopRight2;
    ModelRenderer BodyTopLeft2;
    ModelRenderer BodyTopRight3;
    ModelRenderer BodyTopLeft3;
    ModelRenderer HeadBase;
    ModelRenderer TailBase;
    ModelRenderer BodyLow2;
    ModelRenderer BodyLow1;
    ModelRenderer Spine5;
    ModelRenderer Spine1;
    ModelRenderer Spine2;
    ModelRenderer Spine3;
    ModelRenderer Spine4;
    ModelRenderer RightArmSeg4;
    ModelRenderer LeftArmSeg1;
    ModelRenderer LeftArmSeg3;
    ModelRenderer RightArmSeg2;
    ModelRenderer RightArmSeg1;
    ModelRenderer LeftArmSeg5;
    ModelRenderer LeftArmSeg2;
    ModelRenderer LeftClawTop;
    ModelRenderer RightArmSeg3;
    ModelRenderer RightArmSeg5;
    ModelRenderer LeftArmSeg4;
    ModelRenderer LeftClawBase;
    ModelRenderer RightClawBase;
    ModelRenderer LeftClawLow;
    ModelRenderer RightClawTop;
    ModelRenderer RightClawLow;
    ModelRenderer LBLeg1;
    ModelRenderer LBLeg3;
    ModelRenderer RBLeg1;
    ModelRenderer RBLeg3;
    ModelRenderer LBLeg2;
    ModelRenderer RBLeg2;
    ModelRenderer LBLeg4;
    ModelRenderer RBLeg4;
    ModelRenderer RBLeg5;
    ModelRenderer LBLeg6;
    ModelRenderer RBLeg6;
    ModelRenderer LBLeg5;
    ModelRenderer RFLeg1;
    ModelRenderer RFLeg2;
    ModelRenderer RFLeg3;
    ModelRenderer RFLeg4;
    ModelRenderer RFLeg5;
    ModelRenderer RFLeg6;
    ModelRenderer RMLeg1;
    ModelRenderer RMLeg2;
    ModelRenderer RMLeg3;
    ModelRenderer RMLeg4;
    ModelRenderer RMLeg5;
    ModelRenderer RMLeg6;
    ModelRenderer LFLeg1;
    ModelRenderer LFLeg2;
    ModelRenderer LFLeg3;
    ModelRenderer LFLeg4;
    ModelRenderer LFLeg5;
    ModelRenderer LFLeg6;
    ModelRenderer LMLeg1;
    ModelRenderer LMLeg2;
    ModelRenderer LMLeg4;
    ModelRenderer LMLeg3;
    ModelRenderer LMLeg5;
    ModelRenderer LMLeg6;

    public ModelCaveFisher(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.Nose = new ModelRenderer(this, 0, 0);
        this.Nose.addBox(-0.5f, -0.5f, -12.0f, 1, 1, 6);
        this.Nose.setPos(0.0f, 19.0f, -4.0f);
        this.Nose.mirror = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.EyeLeft = new ModelRenderer(this, 0, 28);
        this.EyeLeft.addBox(0.5f, -2.5f, -2.5f, 3, 2, 2);
        this.EyeLeft.setPos(0.0f, 19.0f, -4.0f);
        this.EyeLeft.mirror = true;
        this.setRotation(this.EyeLeft, 0.0f, 0.0f, 0.0f);
        this.HeadMid = new ModelRenderer(this, 0, 0);
        this.HeadMid.addBox(-2.5f, -1.5f, -5.0f, 5, 3, 2);
        this.HeadMid.setPos(0.0f, 19.0f, -4.0f);
        this.HeadMid.mirror = true;
        this.setRotation(this.HeadMid, 0.0f, 0.0f, 0.0f);
        this.HeadEnd = new ModelRenderer(this, 0, 0);
        this.HeadEnd.addBox(-2.0f, -1.0f, -6.0f, 4, 2, 1);
        this.HeadEnd.setPos(0.0f, 19.0f, -4.0f);
        this.HeadEnd.mirror = true;
        this.setRotation(this.HeadEnd, 0.0f, 0.0f, 0.0f);
        this.TailTuft = new ModelRenderer(this, 0, 23);
        this.TailTuft.addBox(-2.0f, -1.0f, 3.0f, 4, 1, 2);
        this.TailTuft.setPos(0.0f, 19.0f, 10.0f);
        this.TailTuft.mirror = true;
        this.setRotation(this.TailTuft, 0.0f, 0.0f, 0.0f);
        this.EyeRight = new ModelRenderer(this, 0, 28);
        this.EyeRight.addBox(-3.5f, -2.5f, -2.5f, 3, 2, 2);
        this.EyeRight.setPos(0.0f, 19.0f, -4.0f);
        this.EyeRight.mirror = true;
        this.setRotation(this.EyeRight, 0.0f, 0.0f, 0.0f);
        this.BodyTopLeft4 = new ModelRenderer(this, 0, 0);
        this.BodyTopLeft4.addBox(0.0f, 0.0f, 0.0f, 6, 3, 4);
        this.BodyTopLeft4.setPos(0.0f, 16.2f, 7.0f);
        this.BodyTopLeft4.mirror = true;
        this.setRotation(this.BodyTopLeft4, 0.1047198f, 0.1047198f, 0.1047198f);
        this.BodyTopRight4 = new ModelRenderer(this, 0, 0);
        this.BodyTopRight4.addBox(-5.0f, 0.0f, 0.0f, 6, 3, 4);
        this.BodyTopRight4.setPos(-1.0f, 16.2f, 7.0f);
        this.BodyTopRight4.mirror = true;
        this.setRotation(this.BodyTopRight4, 0.1047198f, -0.1047198f, -0.1047198f);
        this.BodyTopLeft1 = new ModelRenderer(this, 0, 0);
        this.BodyTopLeft1.addBox(0.0f, 0.0f, 0.0f, 5, 3, 4);
        this.BodyTopLeft1.setPos(0.0f, 16.0f, -4.0f);
        this.BodyTopLeft1.mirror = true;
        this.setRotation(this.BodyTopLeft1, 0.1745329f, 0.1745329f, 0.1047198f);
        this.BodyTopRight1 = new ModelRenderer(this, 0, 0);
        this.BodyTopRight1.addBox(-5.0f, 0.0f, 0.0f, 5, 3, 4);
        this.BodyTopRight1.setPos(0.0f, 16.0f, -4.0f);
        this.BodyTopRight1.mirror = true;
        this.setRotation(this.BodyTopRight1, 0.1745329f, -0.1745329f, -0.1047198f);
        this.BodyTopRight2 = new ModelRenderer(this, 0, 0);
        this.BodyTopRight2.addBox(-5.0f, 0.0f, 0.0f, 7, 3, 4);
        this.BodyTopRight2.setPos(-1.0f, 16.0f, -1.0f);
        this.BodyTopRight2.mirror = true;
        this.setRotation(this.BodyTopRight2, 0.2094395f, -0.1745329f, -0.1047198f);
        this.BodyTopLeft2 = new ModelRenderer(this, 0, 0);
        this.BodyTopLeft2.addBox(-1.0f, 0.0f, 0.0f, 7, 3, 4);
        this.BodyTopLeft2.setPos(0.0f, 16.0f, -1.0f);
        this.BodyTopLeft2.mirror = true;
        this.setRotation(this.BodyTopLeft2, 0.2094395f, 0.1745329f, 0.1047198f);
        this.BodyTopRight3 = new ModelRenderer(this, 0, 0);
        this.BodyTopRight3.addBox(-5.0f, 0.0f, 0.0f, 6, 3, 4);
        this.BodyTopRight3.setPos(-1.0f, 16.0f, 3.0f);
        this.BodyTopRight3.mirror = true;
        this.setRotation(this.BodyTopRight3, 0.1396263f, -0.1396263f, -0.1047198f);
        this.BodyTopLeft3 = new ModelRenderer(this, 0, 0);
        this.BodyTopLeft3.addBox(0.0f, 0.0f, 0.0f, 6, 3, 4);
        this.BodyTopLeft3.setPos(0.0f, 16.0f, 3.0f);
        this.BodyTopLeft3.mirror = true;
        this.setRotation(this.BodyTopLeft3, 0.1396263f, 0.1396263f, 0.1047198f);
        this.HeadBase = new ModelRenderer(this, 0, 0);
        this.HeadBase.addBox(-3.0f, -2.0f, -3.0f, 6, 4, 3);
        this.HeadBase.setPos(0.0f, 19.0f, -4.0f);
        this.HeadBase.mirror = true;
        this.setRotation(this.HeadBase, 0.0f, 0.0f, 0.0f);
        this.TailBase = new ModelRenderer(this, 0, 0);
        this.TailBase.addBox(-3.0f, -2.0f, 0.0f, 6, 3, 3);
        this.TailBase.setPos(0.0f, 19.0f, 10.0f);
        this.TailBase.mirror = true;
        this.setRotation(this.TailBase, 0.0f, 0.0f, 0.0f);
        this.BodyLow2 = new ModelRenderer(this, 34, 0);
        this.BodyLow2.addBox(0.0f, 0.0f, 0.0f, 8, 2, 7);
        this.BodyLow2.setPos(-4.0f, 18.3f, 3.0f);
        this.BodyLow2.mirror = true;
        this.setRotation(this.BodyLow2, 0.0f, 0.0f, 0.0f);
        this.BodyLow1 = new ModelRenderer(this, 34, 0);
        this.BodyLow1.addBox(0.0f, 0.0f, 0.0f, 8, 2, 7);
        this.BodyLow1.setPos(-4.0f, 18.7f, -4.0f);
        this.BodyLow1.mirror = true;
        this.setRotation(this.BodyLow1, 0.0f, 0.0f, 0.0f);
        this.Spine5 = new ModelRenderer(this, 0, 0);
        this.Spine5.addBox(-0.5f, 0.0f, 0.0f, 1, 1, 4);
        this.Spine5.setPos(0.0f, 16.0f, 8.6f);
        this.Spine5.mirror = true;
        this.setRotation(this.Spine5, 0.0f, 0.0f, 0.0f);
        this.Spine1 = new ModelRenderer(this, 0, 0);
        this.Spine1.addBox(-0.5f, 0.0f, 0.0f, 1, 1, 4);
        this.Spine1.setPos(0.0f, 16.0f, -4.2f);
        this.Spine1.mirror = true;
        this.setRotation(this.Spine1, 0.2443461f, 0.0f, 0.0f);
        this.Spine2 = new ModelRenderer(this, 0, 0);
        this.Spine2.addBox(-0.5f, 0.0f, 0.0f, 1, 1, 5);
        this.Spine2.setPos(0.0f, 16.0f, -1.2f);
        this.Spine2.mirror = true;
        this.setRotation(this.Spine2, 0.3141593f, 0.0f, 0.0f);
        this.Spine3 = new ModelRenderer(this, 0, 0);
        this.Spine3.addBox(-0.5f, 0.0f, 0.0f, 1, 1, 6);
        this.Spine3.setPos(0.0f, 16.0f, 1.8f);
        this.Spine3.mirror = true;
        this.setRotation(this.Spine3, 0.2792527f, 0.0f, 0.0f);
        this.Spine4 = new ModelRenderer(this, 0, 0);
        this.Spine4.addBox(-0.5f, 0.0f, 0.0f, 1, 1, 8);
        this.Spine4.setPos(0.0f, 16.0f, 3.8f);
        this.Spine4.mirror = true;
        this.setRotation(this.Spine4, 0.1745329f, 0.0f, 0.0f);
        this.RightArmSeg4 = new ModelRenderer(this, 0, 0);
        this.RightArmSeg4.addBox(-3.2f, -1.0f, -10.5f, 2, 2, 4);
        this.RightArmSeg4.setPos(-4.7f, 17.5f, -3.0f);
        this.RightArmSeg4.mirror = true;
        this.setRotation(this.RightArmSeg4, 0.0f, 0.0872665f, 0.0f);
        this.LeftArmSeg1 = new ModelRenderer(this, 0, 13);
        this.LeftArmSeg1.addBox(-0.5f, -0.5f, -4.0f, 1, 1, 4);
        this.LeftArmSeg1.setPos(4.7f, 17.5f, -3.0f);
        this.LeftArmSeg1.mirror = true;
        this.setRotation(this.LeftArmSeg1, 0.0f, -0.5235988f, 0.0f);
        this.LeftArmSeg3 = new ModelRenderer(this, 0, 13);
        this.LeftArmSeg3.addBox(1.0f, -0.5f, -8.0f, 1, 1, 3);
        this.LeftArmSeg3.setPos(4.7f, 17.5f, -3.0f);
        this.LeftArmSeg3.mirror = true;
        this.setRotation(this.LeftArmSeg3, 0.0f, -0.1745329f, 0.0f);
        this.RightArmSeg2 = new ModelRenderer(this, 0, 0);
        this.RightArmSeg2.addBox(-1.5f, -1.0f, -6.0f, 2, 2, 4);
        this.RightArmSeg2.setPos(-4.7f, 17.5f, -3.0f);
        this.RightArmSeg2.mirror = true;
        this.setRotation(this.RightArmSeg2, 0.0f, 0.3490659f, 0.0f);
        this.RightArmSeg1 = new ModelRenderer(this, 0, 13);
        this.RightArmSeg1.addBox(-0.5f, -0.5f, -4.0f, 1, 1, 4);
        this.RightArmSeg1.setPos(-4.7f, 17.5f, -3.0f);
        this.RightArmSeg1.mirror = true;
        this.setRotation(this.RightArmSeg1, 0.0f, 0.5235988f, 0.0f);
        this.LeftArmSeg5 = new ModelRenderer(this, 0, 13);
        this.LeftArmSeg5.addBox(2.4f, -0.5f, -12.0f, 1, 1, 3);
        this.LeftArmSeg5.setPos(4.7f, 17.5f, -3.0f);
        this.LeftArmSeg5.mirror = true;
        this.setRotation(this.LeftArmSeg5, 0.0f, 0.0f, 0.0f);
        this.LeftArmSeg2 = new ModelRenderer(this, 0, 0);
        this.LeftArmSeg2.addBox(-0.5f, -1.0f, -6.0f, 2, 2, 4);
        this.LeftArmSeg2.setPos(4.7f, 17.5f, -3.0f);
        this.LeftArmSeg2.mirror = true;
        this.setRotation(this.LeftArmSeg2, 0.0f, -0.3490659f, 0.0f);
        this.LeftClawTop = new ModelRenderer(this, 15, 15);
        this.LeftClawTop.addBox(1.8f, 4.7f, -15.0f, 2, 2, 5);
        this.LeftClawTop.setPos(4.7f, 17.5f, -3.0f);
        this.LeftClawTop.mirror = true;
        this.setRotation(this.LeftClawTop, -0.5410521f, 0.0f, 0.0f);
        this.RightArmSeg3 = new ModelRenderer(this, 0, 13);
        this.RightArmSeg3.addBox(-2.0f, -0.5f, -8.0f, 1, 1, 3);
        this.RightArmSeg3.setPos(-4.7f, 17.5f, -3.0f);
        this.RightArmSeg3.mirror = true;
        this.setRotation(this.RightArmSeg3, 0.0f, 0.1745329f, 0.0f);
        this.RightArmSeg5 = new ModelRenderer(this, 0, 13);
        this.RightArmSeg5.addBox(-3.6f, -0.5f, -12.0f, 1, 1, 3);
        this.RightArmSeg5.setPos(-4.7f, 17.5f, -3.0f);
        this.RightArmSeg5.mirror = true;
        this.setRotation(this.RightArmSeg5, 0.0f, 0.0f, 0.0f);
        this.LeftArmSeg4 = new ModelRenderer(this, 0, 0);
        this.LeftArmSeg4.addBox(1.1f, -1.0f, -10.5f, 2, 2, 4);
        this.LeftArmSeg4.setPos(4.7f, 17.5f, -3.0f);
        this.LeftArmSeg4.mirror = true;
        this.setRotation(this.LeftArmSeg4, 0.0f, -0.0872665f, 0.0f);
        this.LeftClawBase = new ModelRenderer(this, 0, 0);
        this.LeftClawBase.addBox(1.8f, -1.0f, -13.0f, 2, 2, 2);
        this.LeftClawBase.setPos(4.7f, 17.5f, -3.0f);
        this.LeftClawBase.mirror = true;
        this.setRotation(this.LeftClawBase, 0.0f, 0.0f, 0.0f);
        this.RightClawBase = new ModelRenderer(this, 0, 0);
        this.RightClawBase.addBox(-4.2f, -1.0f, -13.0f, 2, 2, 2);
        this.RightClawBase.setPos(-4.7f, 17.5f, -3.0f);
        this.RightClawBase.mirror = true;
        this.setRotation(this.RightClawBase, 0.0f, 0.0f, 0.0f);
        this.LeftClawLow = new ModelRenderer(this, 25, 25);
        this.LeftClawLow.addBox(1.8f, -4.3f, -15.0f, 2, 1, 4);
        this.LeftClawLow.setPos(4.7f, 17.5f, -3.0f);
        this.LeftClawLow.mirror = true;
        this.setRotation(this.LeftClawLow, 0.3490659f, 0.0f, 0.0f);
        this.RightClawTop = new ModelRenderer(this, 15, 15);
        this.RightClawTop.addBox(-4.2f, 4.7f, -15.0f, 2, 2, 5);
        this.RightClawTop.setPos(-4.7f, 17.5f, -3.0f);
        this.RightClawTop.mirror = true;
        this.setRotation(this.RightClawTop, -0.5410521f, 0.0f, 0.0f);
        this.RightClawLow = new ModelRenderer(this, 25, 25);
        this.RightClawLow.addBox(-4.2f, -4.3f, -15.0f, 2, 1, 4);
        this.RightClawLow.setPos(-4.7f, 17.5f, -3.0f);
        this.RightClawLow.mirror = true;
        this.setRotation(this.RightClawLow, 0.3490659f, 0.0f, 0.0f);
        this.LBLeg1 = new ModelRenderer(this, 0, 13);
        this.LBLeg1.addBox(0.5f, -0.5f, -0.5f, 3, 1, 1);
        this.LBLeg1.setPos(5.0f, 18.0f, 8.5f);
        this.LBLeg1.mirror = true;
        this.setRotation(this.LBLeg1, 0.0f, 0.0f, -0.4363323f);
        this.LBLeg3 = new ModelRenderer(this, 2, 0);
        this.LBLeg3.addBox(5.1f, -1.5f, -1.0f, 3, 1, 2);
        this.LBLeg3.setPos(5.0f, 18.0f, 8.5f);
        this.LBLeg3.mirror = true;
        this.setRotation(this.LBLeg3, 0.0f, 0.0f, -0.5759587f);
        this.RBLeg1 = new ModelRenderer(this, 0, 13);
        this.RBLeg1.addBox(-3.5f, -0.5f, -0.5f, 3, 1, 1);
        this.RBLeg1.setPos(-5.0f, 18.0f, 8.5f);
        this.RBLeg1.mirror = true;
        this.setRotation(this.RBLeg1, 0.0f, 0.0f, 0.4363323f);
        this.RBLeg3 = new ModelRenderer(this, 2, 0);
        this.RBLeg3.addBox(-8.1f, -1.5f, -1.0f, 3, 1, 2);
        this.RBLeg3.setPos(-5.0f, 18.0f, 8.5f);
        this.RBLeg3.mirror = true;
        this.setRotation(this.RBLeg3, 0.0f, 0.0f, 0.5759587f);
        this.LBLeg2 = new ModelRenderer(this, 0, 0);
        this.LBLeg2.addBox(2.5f, 0.5f, -1.0f, 3, 2, 2);
        this.LBLeg2.setPos(5.0f, 18.0f, 8.5f);
        this.LBLeg2.mirror = true;
        this.setRotation(this.LBLeg2, 0.0f, 0.0f, -0.9599311f);
        this.RBLeg2 = new ModelRenderer(this, 0, 0);
        this.RBLeg2.addBox(-5.5f, 0.5f, -1.0f, 3, 2, 2);
        this.RBLeg2.setPos(-5.0f, 18.0f, 8.5f);
        this.RBLeg2.mirror = true;
        this.setRotation(this.RBLeg2, 0.0f, 0.0f, 0.9599311f);
        this.LBLeg4 = new ModelRenderer(this, 0, 13);
        this.LBLeg4.addBox(5.0f, -3.0f, -0.5f, 1, 3, 1);
        this.LBLeg4.setPos(5.0f, 18.0f, 8.5f);
        this.LBLeg4.mirror = true;
        this.setRotation(this.LBLeg4, 0.0f, 0.0f, -0.2094395f);
        this.RBLeg4 = new ModelRenderer(this, 0, 13);
        this.RBLeg4.addBox(-6.0f, -3.0f, -0.5f, 1, 3, 1);
        this.RBLeg4.setPos(-5.0f, 18.0f, 8.5f);
        this.RBLeg4.mirror = true;
        this.setRotation(this.RBLeg4, 0.0f, 0.0f, 0.2094395f);
        this.RBLeg5 = new ModelRenderer(this, 0, 0);
        this.RBLeg5.addBox(-6.4f, -1.0f, -1.0f, 2, 6, 2);
        this.RBLeg5.setPos(-5.0f, 18.0f, 8.5f);
        this.RBLeg5.mirror = true;
        this.setRotation(this.RBLeg5, 0.0f, 0.0f, 0.1047198f);
        this.LBLeg6 = new ModelRenderer(this, 0, 13);
        this.LBLeg6.addBox(5.5f, 3.0f, -0.5f, 1, 3, 1);
        this.LBLeg6.setPos(5.0f, 18.0f, 8.5f);
        this.LBLeg6.mirror = true;
        this.setRotation(this.LBLeg6, 0.0f, 0.0f, 0.0f);
        this.RBLeg6 = new ModelRenderer(this, 0, 13);
        this.RBLeg6.addBox(-6.5f, 3.0f, -0.5f, 1, 3, 1);
        this.RBLeg6.setPos(-5.0f, 18.0f, 8.5f);
        this.RBLeg6.mirror = true;
        this.setRotation(this.RBLeg6, 0.0f, 0.0f, 0.0f);
        this.LBLeg5 = new ModelRenderer(this, 0, 0);
        this.LBLeg5.addBox(4.6f, -1.0f, -1.0f, 2, 6, 2);
        this.LBLeg5.setPos(5.0f, 18.0f, 8.5f);
        this.LBLeg5.mirror = true;
        this.setRotation(this.LBLeg5, 0.0f, 0.0f, -0.1047198f);
        this.RFLeg1 = new ModelRenderer(this, 0, 13);
        this.RFLeg1.addBox(-3.5f, -0.5f, -0.5f, 3, 1, 1);
        this.RFLeg1.setPos(-5.0f, 18.0f, 0.5f);
        this.RFLeg1.mirror = true;
        this.setRotation(this.RFLeg1, 0.0f, 0.0f, 0.4363323f);
        this.RFLeg2 = new ModelRenderer(this, 0, 0);
        this.RFLeg2.addBox(-5.5f, 0.5f, -1.0f, 3, 2, 2);
        this.RFLeg2.setPos(-5.0f, 18.0f, 0.5f);
        this.RFLeg2.mirror = true;
        this.setRotation(this.RFLeg2, 0.0f, 0.0f, 0.9599311f);
        this.RFLeg3 = new ModelRenderer(this, 2, 0);
        this.RFLeg3.addBox(-8.1f, -1.5f, -1.0f, 3, 1, 2);
        this.RFLeg3.setPos(-5.0f, 18.0f, 0.5f);
        this.RFLeg3.mirror = true;
        this.setRotation(this.RFLeg3, 0.0f, 0.0f, 0.5759587f);
        this.RFLeg4 = new ModelRenderer(this, 0, 13);
        this.RFLeg4.addBox(-6.0f, -3.0f, -0.5f, 1, 3, 1);
        this.RFLeg4.setPos(-5.0f, 18.0f, 0.5f);
        this.RFLeg4.mirror = true;
        this.setRotation(this.RFLeg4, 0.0f, 0.0f, 0.2094395f);
        this.RFLeg5 = new ModelRenderer(this, 0, 0);
        this.RFLeg5.addBox(-6.4f, -1.0f, -1.0f, 2, 6, 2);
        this.RFLeg5.setPos(-5.0f, 18.0f, 0.5f);
        this.RFLeg5.mirror = true;
        this.setRotation(this.RFLeg5, 0.0f, 0.0f, 0.1047198f);
        this.RFLeg6 = new ModelRenderer(this, 0, 13);
        this.RFLeg6.addBox(-6.5f, 3.0f, -0.5f, 1, 3, 1);
        this.RFLeg6.setPos(-5.0f, 18.0f, 0.5f);
        this.RFLeg6.mirror = true;
        this.setRotation(this.RFLeg6, 0.0f, 0.0f, 0.0f);
        this.RMLeg1 = new ModelRenderer(this, 0, 13);
        this.RMLeg1.addBox(-3.5f, -0.5f, -0.5f, 3, 1, 1);
        this.RMLeg1.setPos(-5.0f, 18.0f, 4.5f);
        this.RMLeg1.mirror = true;
        this.setRotation(this.RMLeg1, 0.0f, 0.0f, 0.4363323f);
        this.RMLeg2 = new ModelRenderer(this, 0, 0);
        this.RMLeg2.addBox(-5.5f, 0.5f, -1.0f, 3, 2, 2);
        this.RMLeg2.setPos(-5.0f, 18.0f, 4.5f);
        this.RMLeg2.mirror = true;
        this.setRotation(this.RMLeg2, 0.0f, 0.0f, 0.9599311f);
        this.RMLeg3 = new ModelRenderer(this, 2, 0);
        this.RMLeg3.addBox(-8.1f, -1.5f, -1.0f, 3, 1, 2);
        this.RMLeg3.setPos(-5.0f, 18.0f, 4.5f);
        this.RMLeg3.mirror = true;
        this.setRotation(this.RMLeg3, 0.0f, 0.0f, 0.5759587f);
        this.RMLeg4 = new ModelRenderer(this, 0, 13);
        this.RMLeg4.addBox(-6.0f, -3.0f, -0.5f, 1, 3, 1);
        this.RMLeg4.setPos(-5.0f, 18.0f, 4.5f);
        this.RMLeg4.mirror = true;
        this.setRotation(this.RMLeg4, 0.0f, 0.0f, 0.2094395f);
        this.RMLeg5 = new ModelRenderer(this, 0, 0);
        this.RMLeg5.addBox(-6.4f, -1.0f, -1.0f, 2, 6, 2);
        this.RMLeg5.setPos(-5.0f, 18.0f, 4.5f);
        this.RMLeg5.mirror = true;
        this.setRotation(this.RMLeg5, 0.0f, 0.0f, 0.1047198f);
        this.RMLeg6 = new ModelRenderer(this, 0, 13);
        this.RMLeg6.addBox(-6.5f, 3.0f, -0.5f, 1, 3, 1);
        this.RMLeg6.setPos(-5.0f, 18.0f, 4.5f);
        this.RMLeg6.mirror = true;
        this.setRotation(this.RMLeg6, 0.0f, 0.0f, 0.0f);
        this.LFLeg1 = new ModelRenderer(this, 0, 13);
        this.LFLeg1.addBox(0.5f, -0.5f, -0.5f, 3, 1, 1);
        this.LFLeg1.setPos(5.0f, 18.0f, 0.5f);
        this.LFLeg1.mirror = true;
        this.setRotation(this.LFLeg1, 0.0f, 0.0f, -0.4363323f);
        this.LFLeg2 = new ModelRenderer(this, 0, 0);
        this.LFLeg2.addBox(2.5f, 0.5f, -1.0f, 3, 2, 2);
        this.LFLeg2.setPos(5.0f, 18.0f, 0.5f);
        this.LFLeg2.mirror = true;
        this.setRotation(this.LFLeg2, 0.0f, 0.0f, -0.9599311f);
        this.LFLeg3 = new ModelRenderer(this, 2, 0);
        this.LFLeg3.addBox(5.1f, -1.5f, -1.0f, 3, 1, 2);
        this.LFLeg3.setPos(5.0f, 18.0f, 0.5f);
        this.LFLeg3.mirror = true;
        this.setRotation(this.LFLeg3, 0.0f, 0.0f, -0.5759587f);
        this.LFLeg4 = new ModelRenderer(this, 0, 13);
        this.LFLeg4.addBox(5.0f, -3.0f, -0.5f, 1, 3, 1);
        this.LFLeg4.setPos(5.0f, 18.0f, 0.5f);
        this.LFLeg4.mirror = true;
        this.setRotation(this.LFLeg4, 0.0f, 0.0f, -0.2094395f);
        this.LFLeg5 = new ModelRenderer(this, 0, 0);
        this.LFLeg5.addBox(4.6f, -1.0f, -1.0f, 2, 6, 2);
        this.LFLeg5.setPos(5.0f, 18.0f, 0.5f);
        this.LFLeg5.mirror = true;
        this.setRotation(this.LFLeg5, 0.0f, 0.0f, -0.1047198f);
        this.LFLeg6 = new ModelRenderer(this, 0, 13);
        this.LFLeg6.addBox(5.5f, 3.0f, -0.5f, 1, 3, 1);
        this.LFLeg6.setPos(5.0f, 18.0f, 0.5f);
        this.LFLeg6.mirror = true;
        this.setRotation(this.LFLeg6, 0.0f, 0.0f, 0.0f);
        this.LMLeg1 = new ModelRenderer(this, 0, 13);
        this.LMLeg1.addBox(0.5f, -0.5f, -0.5f, 3, 1, 1);
        this.LMLeg1.setPos(5.0f, 18.0f, 4.5f);
        this.LMLeg1.mirror = true;
        this.setRotation(this.LMLeg1, 0.0f, 0.0f, -0.4363323f);
        this.LMLeg2 = new ModelRenderer(this, 0, 0);
        this.LMLeg2.addBox(2.5f, 0.5f, -1.0f, 3, 2, 2);
        this.LMLeg2.setPos(5.0f, 18.0f, 4.5f);
        this.LMLeg2.mirror = true;
        this.setRotation(this.LMLeg2, 0.0f, 0.0f, -0.9599311f);
        this.LMLeg4 = new ModelRenderer(this, 0, 13);
        this.LMLeg4.addBox(5.0f, -3.0f, -0.5f, 1, 3, 1);
        this.LMLeg4.setPos(5.0f, 18.0f, 4.5f);
        this.LMLeg4.mirror = true;
        this.setRotation(this.LMLeg4, 0.0f, 0.0f, -0.2094395f);
        this.LMLeg3 = new ModelRenderer(this, 2, 0);
        this.LMLeg3.addBox(5.1f, -1.5f, -1.0f, 3, 1, 2);
        this.LMLeg3.setPos(5.0f, 18.0f, 4.5f);
        this.LMLeg3.mirror = true;
        this.setRotation(this.LMLeg3, 0.0f, 0.0f, -0.5759587f);
        this.LMLeg5 = new ModelRenderer(this, 0, 0);
        this.LMLeg5.addBox(4.6f, -1.0f, -1.0f, 2, 6, 2);
        this.LMLeg5.setPos(5.0f, 18.0f, 4.5f);
        this.LMLeg5.mirror = true;
        this.setRotation(this.LMLeg5, 0.0f, 0.0f, -0.1047198f);
        this.LMLeg6 = new ModelRenderer(this, 0, 13);
        this.LMLeg6.addBox(5.5f, 3.0f, -0.5f, 1, 3, 1);
        this.LMLeg6.setPos(5.0f, 18.0f, 4.5f);
        this.LMLeg6.mirror = true;
        this.setRotation(this.LMLeg6, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(CaveFisher entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        CaveFisher e = (CaveFisher)entity;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float pi4 = 1.570795f;
        this.LFLeg1.yRot = newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        this.LFLeg2.yRot = newangle;
        this.LFLeg3.yRot = newangle;
        this.LFLeg4.yRot = newangle;
        this.LFLeg5.yRot = newangle;
        this.LFLeg6.yRot = newangle;
        this.RFLeg1.yRot = - newangle;
        this.RFLeg2.yRot = - newangle;
        this.RFLeg3.yRot = - newangle;
        this.RFLeg4.yRot = - newangle;
        this.RFLeg5.yRot = - newangle;
        this.RFLeg6.yRot = - newangle;
        this.LMLeg1.yRot = newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 1.0f * pi4)) * 3.1415927f * 0.12f * f1;
        this.LMLeg2.yRot = newangle;
        this.LMLeg3.yRot = newangle;
        this.LMLeg4.yRot = newangle;
        this.LMLeg5.yRot = newangle;
        this.LMLeg6.yRot = newangle;
        this.RMLeg1.yRot = - newangle;
        this.RMLeg2.yRot = - newangle;
        this.RMLeg3.yRot = - newangle;
        this.RMLeg4.yRot = - newangle;
        this.RMLeg5.yRot = - newangle;
        this.RMLeg6.yRot = - newangle;
        this.LBLeg1.yRot = newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.12f * f1;
        this.LBLeg2.yRot = newangle;
        this.LBLeg3.yRot = newangle;
        this.LBLeg4.yRot = newangle;
        this.LBLeg5.yRot = newangle;
        this.LBLeg6.yRot = newangle;
        this.RBLeg1.yRot = - newangle;
        this.RBLeg2.yRot = - newangle;
        this.RBLeg3.yRot = - newangle;
        this.RBLeg4.yRot = - newangle;
        this.RBLeg5.yRot = - newangle;
        this.RBLeg6.yRot = - newangle;
        r = e.getRenderInfo();
        newangle = MathHelper.cos((float)(f2 * 3.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 3.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (e.getAttacking() == 0) {
                r.ri1 = e.level.random.nextInt(20);
                r.ri2 = e.level.random.nextInt(25);
            } else {
                r.ri1 = e.level.random.nextInt(4);
                r.ri2 = e.level.random.nextInt(3);
            }
        }
        if (r.ri1 == 1 || r.ri1 == 3) {
            this.doLeftClaw(newangle);
            this.doRightClaw(newangle);
        } else {
            this.doLeftClaw(0.0f);
            this.doRightClaw(0.0f);
        }
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.EyeLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadMid.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadEnd.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailTuft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.EyeRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopLeft4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopRight4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopLeft1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopRight1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopRight2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopLeft2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopRight3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTopLeft3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyLow2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyLow1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spine5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spine1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spine2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spine3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spine4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftClawTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftClawBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightClawBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftClawLow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightClawTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightClawLow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LBLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LBLeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RBLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RBLeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LBLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RBLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LBLeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RBLeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RBLeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LBLeg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RBLeg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LBLeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RFLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RFLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RFLeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RFLeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RFLeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RFLeg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RMLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RMLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RMLeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RMLeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RMLeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RMLeg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LFLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LFLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LFLeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LFLeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LFLeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LFLeg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LMLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LMLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LMLeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LMLeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LMLeg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LMLeg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }

    private void doLeftClaw(float angle) {
        this.LeftArmSeg1.xRot = Math.abs(angle);
        this.LeftArmSeg2.xRot = Math.abs(angle);
        this.LeftArmSeg3.xRot = Math.abs(angle);
        this.LeftArmSeg4.xRot = Math.abs(angle);
        this.LeftArmSeg5.xRot = Math.abs(angle);
        this.LeftClawBase.xRot = Math.abs(angle);
        this.LeftClawTop.xRot = Math.abs(angle) - 0.54f;
        this.LeftClawLow.xRot = Math.abs(angle) + 0.35f;
    }

    private void doRightClaw(float angle) {
        this.RightArmSeg1.xRot = Math.abs(angle);
        this.RightArmSeg2.xRot = Math.abs(angle);
        this.RightArmSeg3.xRot = Math.abs(angle);
        this.RightArmSeg4.xRot = Math.abs(angle);
        this.RightArmSeg5.xRot = Math.abs(angle);
        this.RightClawBase.xRot = Math.abs(angle);
        this.RightClawTop.xRot = Math.abs(angle) - 0.54f;
        this.RightClawLow.xRot = Math.abs(angle) + 0.35f;
    }
}

