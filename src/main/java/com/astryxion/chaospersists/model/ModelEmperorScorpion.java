/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EmperorScorpion
 *  com.astryxion.chaospersists.ModelEmperorScorpion
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

import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelEmperorScorpion extends EntityModel<EmperorScorpion> {
    private float wingspeed = 1.0f;
    ModelRenderer Head;
    ModelRenderer Seg1;
    ModelRenderer Seg2;
    ModelRenderer Seg3;
    ModelRenderer Seg4;
    ModelRenderer Seg5;
    ModelRenderer Seg6;
    ModelRenderer Seg7;
    ModelRenderer Seg8;
    ModelRenderer Tailseg1;
    ModelRenderer Tailseg2;
    ModelRenderer Tailseg3;
    ModelRenderer Tailseg4;
    ModelRenderer Tailseg5;
    ModelRenderer Tailseg6;
    ModelRenderer Tailseg7;
    ModelRenderer Tailseg8;
    ModelRenderer Stinger1;
    ModelRenderer Stinger2;
    ModelRenderer Stinger3;
    ModelRenderer LeftShoulder;
    ModelRenderer LeftArmSeg1;
    ModelRenderer LeftArmSeg2;
    ModelRenderer LeftArmSeg3;
    ModelRenderer LeftArmSeg4;
    ModelRenderer RightShoulder;
    ModelRenderer RightArmSeg1;
    ModelRenderer RightArmSeg2;
    ModelRenderer RightArmSeg3;
    ModelRenderer RightArmSeg4;
    ModelRenderer RightPincer;
    ModelRenderer LeftPincer;
    ModelRenderer Lefteye;
    ModelRenderer Righteye;
    ModelRenderer RightMandible;
    ModelRenderer LeftMandible;
    ModelRenderer RightManPart2;
    ModelRenderer LeftManPart2;
    ModelRenderer Leg1Seg1;
    ModelRenderer Leg1Seg2;
    ModelRenderer Leg1Seg3;
    ModelRenderer Leg1Seg4;
    ModelRenderer Leg1Seg5;
    ModelRenderer Leg2Seg1;
    ModelRenderer Leg2Seg2;
    ModelRenderer Leg2Seg3;
    ModelRenderer Leg2Seg4;
    ModelRenderer Leg2Seg5;
    ModelRenderer Leg3Seg1;
    ModelRenderer Leg3Seg2;
    ModelRenderer Leg3Seg3;
    ModelRenderer Leg3Seg4;
    ModelRenderer Leg3Seg5;
    ModelRenderer Leg4Seg1;
    ModelRenderer Leg4Seg2;
    ModelRenderer Leg4Seg3;
    ModelRenderer Leg4Seg4;
    ModelRenderer Leg4Seg5;
    ModelRenderer Leg5Seg1;
    ModelRenderer Leg5Seg2;
    ModelRenderer Leg5Seg3;
    ModelRenderer Leg5Seg4;
    ModelRenderer Leg5Seg5;
    ModelRenderer Leg6Seg1;
    ModelRenderer Leg6Seg2;
    ModelRenderer Leg6Seg3;
    ModelRenderer Leg6Seg4;
    ModelRenderer Leg6Seg5;
    ModelRenderer Leg7Seg1;
    ModelRenderer Leg7Seg2;
    ModelRenderer Leg7Seg3;
    ModelRenderer Leg7Seg4;
    ModelRenderer Leg7Seg5;
    ModelRenderer Leg8Seg1;
    ModelRenderer Leg8Seg2;
    ModelRenderer Leg8Seg3;
    ModelRenderer Leg8Seg4;
    ModelRenderer Leg8Seg5;

    public ModelEmperorScorpion(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 128;
        this.Head = new ModelRenderer(this, 0, 104);
        this.Head.addBox(-9.0f, -4.0f, -16.0f, 18, 8, 16);
        this.Head.setPos(0.0f, 13.0f, -8.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Seg1 = new ModelRenderer(this, 0, 78);
        this.Seg1.addBox(-9.0f, -4.0f, 0.0f, 18, 8, 4);
        this.Seg1.setPos(0.0f, 13.0f, -8.0f);
        this.Seg1.mirror = true;
        this.setRotation(this.Seg1, 0.0f, 0.0f, 0.0f);
        this.Seg2 = new ModelRenderer(this, 0, 65);
        this.Seg2.addBox(-8.5f, -4.1f, 4.0f, 17, 8, 4);
        this.Seg2.setPos(0.0f, 13.0f, -8.0f);
        this.Seg2.mirror = true;
        this.setRotation(this.Seg2, 0.0f, 0.0f, 0.0f);
        this.Seg3 = new ModelRenderer(this, 0, 50);
        this.Seg3.addBox(-9.5f, -4.0f, 8.0f, 19, 8, 5);
        this.Seg3.setPos(0.0f, 13.0f, -8.0f);
        this.Seg3.mirror = true;
        this.setRotation(this.Seg3, 0.0f, 0.0f, 0.0f);
        this.Seg4 = new ModelRenderer(this, 0, 35);
        this.Seg4.addBox(-9.0f, -4.1f, 13.0f, 18, 8, 6);
        this.Seg4.setPos(0.0f, 13.0f, -8.0f);
        this.Seg4.mirror = true;
        this.setRotation(this.Seg4, 0.0f, 0.0f, 0.0f);
        this.Seg5 = new ModelRenderer(this, 45, 91);
        this.Seg5.addBox(-8.5f, -4.0f, 19.0f, 17, 8, 3);
        this.Seg5.setPos(0.0f, 13.0f, -8.0f);
        this.Seg5.mirror = true;
        this.setRotation(this.Seg5, 0.0f, 0.0f, 0.0f);
        this.Seg6 = new ModelRenderer(this, 45, 79);
        this.Seg6.addBox(-8.0f, -4.1f, 22.0f, 16, 8, 3);
        this.Seg6.setPos(0.0f, 13.0f, -8.0f);
        this.Seg6.mirror = true;
        this.setRotation(this.Seg6, 0.0f, 0.0f, 0.0f);
        this.Seg7 = new ModelRenderer(this, 43, 66);
        this.Seg7.addBox(-7.0f, -4.0f, 25.0f, 14, 8, 3);
        this.Seg7.setPos(0.0f, 13.0f, -8.0f);
        this.Seg7.mirror = true;
        this.setRotation(this.Seg7, 0.0f, 0.0f, 0.0f);
        this.Seg8 = new ModelRenderer(this, 49, 53);
        this.Seg8.addBox(-5.5f, -4.1f, 28.0f, 11, 8, 2);
        this.Seg8.setPos(0.0f, 13.0f, -8.0f);
        this.Seg8.mirror = true;
        this.setRotation(this.Seg8, 0.0f, 0.0f, 0.0f);
        this.Tailseg1 = new ModelRenderer(this, 92, 0);
        this.Tailseg1.addBox(-4.0f, -1.0f, 0.0f, 8, 4, 10);
        this.Tailseg1.setPos(0.0f, 13.0f, 20.0f);
        this.Tailseg1.mirror = true;
        this.setRotation(this.Tailseg1, 0.5948578f, 0.0f, 0.0f);
        this.Tailseg2 = new ModelRenderer(this, 90, 15);
        this.Tailseg2.addBox(-3.5f, -2.0f, 0.0f, 7, 4, 12);
        this.Tailseg2.setPos(0.0f, 10.0f, 27.0f);
        this.Tailseg2.mirror = true;
        this.setRotation(this.Tailseg2, 1.07818f, 0.0f, 0.0f);
        this.Tailseg3 = new ModelRenderer(this, 96, 32);
        this.Tailseg3.addBox(-3.0f, -2.0f, 1.0f, 6, 4, 10);
        this.Tailseg3.setPos(0.0f, 2.0f, 32.0f);
        this.Tailseg3.mirror = true;
        this.setRotation(this.Tailseg3, 1.710216f, 0.0f, 0.0f);
        this.Tailseg4 = new ModelRenderer(this, 96, 47);
        this.Tailseg4.addBox(-2.5f, -2.0f, 0.0f, 5, 4, 11);
        this.Tailseg4.setPos(0.0f, -7.0f, 31.0f);
        this.Tailseg4.mirror = true;
        this.setRotation(this.Tailseg4, 2.267895f, 0.0f, 0.0f);
        this.Tailseg5 = new ModelRenderer(this, 98, 63);
        this.Tailseg5.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 11);
        this.Tailseg5.setPos(0.0f, -14.0f, 25.0f);
        this.Tailseg5.mirror = true;
        this.setRotation(this.Tailseg5, 2.899932f, 0.0f, 0.0f);
        this.Tailseg6 = new ModelRenderer(this, 98, 79);
        this.Tailseg6.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 11);
        this.Tailseg6.setPos(0.0f, -17.0f, 16.0f);
        this.Tailseg6.mirror = true;
        this.setRotation(this.Tailseg6, -2.602503f, 0.0f, 0.0f);
        this.Tailseg7 = new ModelRenderer(this, 94, 95);
        this.Tailseg7.addBox(-3.0f, -2.0f, 0.0f, 6, 4, 11);
        this.Tailseg7.setPos(0.0f, -12.0f, 8.0f);
        this.Tailseg7.mirror = true;
        this.setRotation(this.Tailseg7, -0.2230717f, 0.0f, 0.0f);
        this.Tailseg8 = new ModelRenderer(this, 102, 111);
        this.Tailseg8.addBox(-4.0f, -2.0f, 4.0f, 8, 4, 5);
        this.Tailseg8.setPos(0.0f, -12.0f, 8.0f);
        this.Tailseg8.mirror = true;
        this.setRotation(this.Tailseg8, -0.2230717f, 0.0f, 0.0f);
        this.Stinger1 = new ModelRenderer(this, 83, 0);
        this.Stinger1.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 3);
        this.Stinger1.setPos(0.0f, -10.0f, 18.0f);
        this.Stinger1.mirror = true;
        this.setRotation(this.Stinger1, 0.2230717f, 0.0f, 0.0f);
        this.Stinger2 = new ModelRenderer(this, 83, 0);
        this.Stinger2.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 3);
        this.Stinger2.setPos(0.0f, -10.5f, 20.5f);
        this.Stinger2.mirror = true;
        this.setRotation(this.Stinger2, -0.2602503f, 0.0f, 0.0f);
        this.Stinger3 = new ModelRenderer(this, 79, 5);
        this.Stinger3.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 5);
        this.Stinger3.setPos(0.0f, -10.0f, 23.0f);
        this.Stinger3.mirror = true;
        this.setRotation(this.Stinger3, -0.8551081f, 0.0f, 0.0f);
        this.LeftShoulder = new ModelRenderer(this, 69, 103);
        this.LeftShoulder.addBox(-3.0f, -3.0f, -4.0f, 6, 6, 4);
        this.LeftShoulder.setPos(7.0f, 13.0f, -22.0f);
        this.LeftShoulder.mirror = true;
        this.setRotation(this.LeftShoulder, 0.0f, -0.8551081f, 0.0f);
        this.LeftArmSeg1 = new ModelRenderer(this, 55, 0);
        this.LeftArmSeg1.addBox(-3.0f, -3.0f, -10.0f, 4, 6, 13);
        this.LeftArmSeg1.setPos(10.0f, 13.0f, -24.0f);
        this.LeftArmSeg1.mirror = true;
        this.setRotation(this.LeftArmSeg1, 0.0f, -2.044824f, 0.0f);
        this.LeftArmSeg2 = new ModelRenderer(this, 130, 0);
        this.LeftArmSeg2.addBox(-7.0f, -3.0f, -17.0f, 8, 6, 17);
        this.LeftArmSeg2.setPos(19.0f, 13.0f, -22.0f);
        this.LeftArmSeg2.mirror = true;
        this.setRotation(this.LeftArmSeg2, 0.0f, -0.7435722f, 0.0f);
        this.LeftArmSeg3 = new ModelRenderer(this, 130, 50);
        this.LeftArmSeg3.addBox(-3.0f, -3.0f, -24.0f, 4, 6, 24);
        this.LeftArmSeg3.setPos(29.0f, 13.0f, -33.0f);
        this.LeftArmSeg3.mirror = true;
        this.setRotation(this.LeftArmSeg3, 0.0f, 0.3717861f, 0.0f);
        this.LeftArmSeg4 = new ModelRenderer(this, 181, 0);
        this.LeftArmSeg4.addBox(-3.0f, -3.0f, -14.0f, 8, 6, 12);
        this.LeftArmSeg4.setPos(29.0f, 13.0f, -33.0f);
        this.LeftArmSeg4.mirror = true;
        this.setRotation(this.LeftArmSeg4, 0.0f, 1.487144f, 0.0f);
        this.RightShoulder = new ModelRenderer(this, 69, 103);
        this.RightShoulder.addBox(-3.0f, -3.0f, -4.0f, 6, 6, 4);
        this.RightShoulder.setPos(-7.0f, 13.0f, -22.0f);
        this.RightShoulder.mirror = true;
        this.setRotation(this.RightShoulder, 0.0f, 0.8551066f, 0.0f);
        this.RightArmSeg1 = new ModelRenderer(this, 55, 0);
        this.RightArmSeg1.addBox(-1.0f, -3.0f, -10.0f, 4, 6, 13);
        this.RightArmSeg1.setPos(-10.0f, 13.0f, -24.0f);
        this.RightArmSeg1.mirror = true;
        this.setRotation(this.RightArmSeg1, 0.0f, 2.044828f, 0.0f);
        this.RightArmSeg2 = new ModelRenderer(this, 130, 0);
        this.RightArmSeg2.addBox(-1.0f, -3.0f, -17.0f, 8, 6, 17);
        this.RightArmSeg2.setPos(-19.0f, 13.0f, -22.0f);
        this.RightArmSeg2.mirror = true;
        this.setRotation(this.RightArmSeg2, 0.0f, 0.7435801f, 0.0f);
        this.RightArmSeg3 = new ModelRenderer(this, 130, 50);
        this.RightArmSeg3.addBox(-1.0f, -3.0f, -24.0f, 4, 6, 24);
        this.RightArmSeg3.setPos(-29.0f, 13.0f, -33.0f);
        this.RightArmSeg3.mirror = true;
        this.setRotation(this.RightArmSeg3, 0.0f, -0.37179f, 0.0f);
        this.RightArmSeg4 = new ModelRenderer(this, 181, 0);
        this.RightArmSeg4.addBox(-5.0f, -3.0f, -14.0f, 8, 6, 12);
        this.RightArmSeg4.setPos(-29.0f, 13.0f, -33.0f);
        this.RightArmSeg4.mirror = true;
        this.setRotation(this.RightArmSeg4, 0.0f, -1.487143f, 0.0f);
        this.RightPincer = new ModelRenderer(this, 130, 24);
        this.RightPincer.addBox(-1.0f, -3.0f, -19.0f, 2, 6, 19);
        this.RightPincer.setPos(-17.0f, 13.0f, -33.0f);
        this.RightPincer.mirror = true;
        this.setRotation(this.RightPincer, 0.0f, -0.0743611f, 0.0f);
        this.LeftPincer = new ModelRenderer(this, 130, 24);
        this.LeftPincer.addBox(-1.0f, -3.0f, -19.0f, 2, 6, 19);
        this.LeftPincer.setPos(17.0f, 13.0f, -33.0f);
        this.LeftPincer.mirror = true;
        this.setRotation(this.LeftPincer, 0.0f, 0.0743685f, 0.0f);
        this.Lefteye = new ModelRenderer(this, 0, 113);
        this.Lefteye.addBox(-0.5f, -5.0f, -7.5f, 3, 2, 3);
        this.Lefteye.setPos(0.0f, 13.0f, -8.0f);
        this.Lefteye.mirror = true;
        this.setRotation(this.Lefteye, 0.0f, 0.0f, 0.2974289f);
        this.Righteye = new ModelRenderer(this, 0, 113);
        this.Righteye.addBox(-2.5f, -5.0f, -7.5f, 3, 2, 3);
        this.Righteye.setPos(0.0f, 13.0f, -8.0f);
        this.Righteye.mirror = true;
        this.setRotation(this.Righteye, 0.0f, 0.0f, -0.2974216f);
        this.RightMandible = new ModelRenderer(this, 76, 55);
        this.RightMandible.addBox(-2.0f, -3.0f, -4.0f, 4, 4, 4);
        this.RightMandible.setPos(-2.0f, 13.0f, -23.0f);
        this.RightMandible.mirror = true;
        this.setRotation(this.RightMandible, 0.1115358f, 0.3346075f, 0.0f);
        this.LeftMandible = new ModelRenderer(this, 76, 55);
        this.LeftMandible.addBox(-2.0f, -3.0f, -4.0f, 4, 4, 4);
        this.LeftMandible.setPos(2.0f, 13.0f, -23.0f);
        this.LeftMandible.mirror = true;
        this.setRotation(this.LeftMandible, 0.111544f, -0.3346145f, 0.0f);
        this.RightManPart2 = new ModelRenderer(this, 82, 64);
        this.RightManPart2.addBox(-0.5f, -0.5f, -6.0f, 1, 1, 6);
        this.RightManPart2.setPos(-3.0f, 11.0f, -26.0f);
        this.RightManPart2.mirror = true;
        this.setRotation(this.RightManPart2, 1.189716f, 0.0f, 0.0f);
        this.LeftManPart2 = new ModelRenderer(this, 82, 64);
        this.LeftManPart2.addBox(-0.5f, -0.5f, -6.0f, 1, 1, 6);
        this.LeftManPart2.setPos(3.0f, 11.0f, -26.0f);
        this.LeftManPart2.mirror = true;
        this.setRotation(this.LeftManPart2, 1.188848f, 0.0f, 0.0f);
        this.Leg1Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg1Seg1.addBox(0.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg1Seg1.setPos(9.0f, 13.0f, -10.0f);
        this.Leg1Seg1.mirror = true;
        this.setRotation(this.Leg1Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg1Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg1Seg2.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg1Seg2.setPos(12.0f, 13.0f, -10.0f);
        this.Leg1Seg2.mirror = true;
        this.setRotation(this.Leg1Seg2, 0.0f, 0.0f, -0.9294576f);
        this.Leg1Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg1Seg3.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg1Seg3.setPos(18.0f, 3.0f, -10.0f);
        this.Leg1Seg3.mirror = true;
        this.setRotation(this.Leg1Seg3, 0.0f, 0.0f, 0.6320361f);
        this.Leg1Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg1Seg4.addBox(0.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg1Seg4.setPos(26.0f, 12.0f, -10.0f);
        this.Leg1Seg4.mirror = true;
        this.setRotation(this.Leg1Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg1Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg1Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg1Seg5.setPos(27.0f, 19.0f, -10.0f);
        this.Leg1Seg5.mirror = true;
        this.setRotation(this.Leg1Seg5, 0.0f, 0.0f, 0.669215f);
        this.Leg2Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg2Seg1.addBox(0.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg2Seg1.setPos(8.5f, 13.0f, -4.0f);
        this.Leg2Seg1.mirror = true;
        this.setRotation(this.Leg2Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg2Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg2Seg2.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg2Seg2.setPos(12.0f, 13.0f, -4.0f);
        this.Leg2Seg2.mirror = true;
        this.setRotation(this.Leg2Seg2, 0.0f, 0.0f, -0.9294576f);
        this.Leg2Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg2Seg3.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg2Seg3.setPos(18.0f, 3.0f, -4.0f);
        this.Leg2Seg3.mirror = true;
        this.setRotation(this.Leg2Seg3, 0.0f, 0.0f, 0.6320361f);
        this.Leg2Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg2Seg4.addBox(0.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg2Seg4.setPos(26.0f, 12.0f, -4.0f);
        this.Leg2Seg4.mirror = true;
        this.setRotation(this.Leg2Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg2Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg2Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg2Seg5.setPos(27.0f, 19.0f, -4.0f);
        this.Leg2Seg5.mirror = true;
        this.setRotation(this.Leg2Seg5, 0.0f, 0.0f, 0.669215f);
        this.Leg3Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg3Seg1.addBox(0.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg3Seg1.setPos(9.5f, 13.0f, 2.0f);
        this.Leg3Seg1.mirror = true;
        this.setRotation(this.Leg3Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg3Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg3Seg2.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg3Seg2.setPos(12.0f, 13.0f, 2.0f);
        this.Leg3Seg2.mirror = true;
        this.setRotation(this.Leg3Seg2, 0.0f, 0.0f, -0.9294576f);
        this.Leg3Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg3Seg3.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg3Seg3.setPos(18.0f, 3.0f, 2.0f);
        this.Leg3Seg3.mirror = true;
        this.setRotation(this.Leg3Seg3, 0.0f, 0.0f, 0.6320361f);
        this.Leg3Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg3Seg4.addBox(0.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg3Seg4.setPos(26.0f, 12.0f, 2.0f);
        this.Leg3Seg4.mirror = true;
        this.setRotation(this.Leg3Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg3Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg3Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg3Seg5.setPos(27.0f, 19.0f, 2.0f);
        this.Leg3Seg5.mirror = true;
        this.setRotation(this.Leg3Seg5, 0.0f, 0.0f, 0.669215f);
        this.Leg4Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg4Seg1.addBox(0.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg4Seg1.setPos(9.0f, 13.0f, 8.0f);
        this.Leg4Seg1.mirror = true;
        this.setRotation(this.Leg4Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg4Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg4Seg2.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg4Seg2.setPos(12.0f, 13.0f, 8.0f);
        this.Leg4Seg2.mirror = true;
        this.setRotation(this.Leg4Seg2, 0.0f, 0.0f, -0.9294576f);
        this.Leg4Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg4Seg3.addBox(0.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg4Seg3.setPos(18.0f, 3.0f, 8.0f);
        this.Leg4Seg3.mirror = true;
        this.setRotation(this.Leg4Seg3, 0.0f, 0.0f, 0.6320361f);
        this.Leg4Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg4Seg4.addBox(0.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg4Seg4.setPos(26.0f, 12.0f, 8.0f);
        this.Leg4Seg4.mirror = true;
        this.setRotation(this.Leg4Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg4Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg4Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg4Seg5.setPos(27.0f, 19.0f, 8.0f);
        this.Leg4Seg5.mirror = true;
        this.setRotation(this.Leg4Seg5, 0.0f, 0.0f, 0.669215f);
        this.Leg5Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg5Seg1.addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg5Seg1.setPos(-9.0f, 13.0f, -10.0f);
        this.Leg5Seg1.mirror = true;
        this.setRotation(this.Leg5Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg5Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg5Seg2.addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg5Seg2.setPos(-12.0f, 14.0f, -10.0f);
        this.Leg5Seg2.mirror = true;
        this.setRotation(this.Leg5Seg2, 0.0f, 0.0f, 0.9294653f);
        this.Leg5Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg5Seg3.addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg5Seg3.setPos(-18.0f, 4.0f, -10.0f);
        this.Leg5Seg3.mirror = true;
        this.setRotation(this.Leg5Seg3, 0.0f, 0.0f, -0.6320364f);
        this.Leg5Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg5Seg4.addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg5Seg4.setPos(-26.0f, 12.0f, -10.0f);
        this.Leg5Seg4.mirror = true;
        this.setRotation(this.Leg5Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg5Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg5Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg5Seg5.setPos(-27.0f, 19.0f, -10.0f);
        this.Leg5Seg5.mirror = true;
        this.setRotation(this.Leg5Seg5, 0.0f, 0.0f, 2.240008f);
        this.Leg6Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg6Seg1.addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg6Seg1.setPos(-8.5f, 13.0f, -4.0f);
        this.Leg6Seg1.mirror = true;
        this.setRotation(this.Leg6Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg6Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg6Seg2.addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg6Seg2.setPos(-12.0f, 14.0f, -4.0f);
        this.Leg6Seg2.mirror = true;
        this.setRotation(this.Leg6Seg2, 0.0f, 0.0f, 0.9294576f);
        this.Leg6Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg6Seg3.addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg6Seg3.setPos(-18.0f, 4.0f, -4.0f);
        this.Leg6Seg3.mirror = true;
        this.setRotation(this.Leg6Seg3, 0.0f, 0.0f, -0.6320361f);
        this.Leg6Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg6Seg4.addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg6Seg4.setPos(-26.0f, 12.0f, -4.0f);
        this.Leg6Seg4.mirror = true;
        this.setRotation(this.Leg6Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg6Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg6Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg6Seg5.setPos(-27.0f, 19.0f, -4.0f);
        this.Leg6Seg5.mirror = true;
        this.setRotation(this.Leg6Seg5, 0.0f, 0.0f, 2.240008f);
        this.Leg7Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg7Seg1.addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg7Seg1.setPos(-9.5f, 13.0f, 2.0f);
        this.Leg7Seg1.mirror = true;
        this.setRotation(this.Leg7Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg7Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg7Seg2.addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg7Seg2.setPos(-12.0f, 14.0f, 2.0f);
        this.Leg7Seg2.mirror = true;
        this.setRotation(this.Leg7Seg2, 0.0f, 0.0f, 0.9294576f);
        this.Leg7Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg7Seg3.addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg7Seg3.setPos(-18.0f, 4.0f, 2.0f);
        this.Leg7Seg3.mirror = true;
        this.setRotation(this.Leg7Seg3, 0.0f, 0.0f, -0.6320361f);
        this.Leg7Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg7Seg4.addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg7Seg4.setPos(-26.0f, 12.0f, 2.0f);
        this.Leg7Seg4.mirror = true;
        this.setRotation(this.Leg7Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg7Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg7Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg7Seg5.setPos(-27.0f, 19.0f, 2.0f);
        this.Leg7Seg5.mirror = true;
        this.setRotation(this.Leg7Seg5, 0.0f, 0.0f, 2.240008f);
        this.Leg8Seg1 = new ModelRenderer(this, 20, 20);
        this.Leg8Seg1.addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4);
        this.Leg8Seg1.setPos(-9.0f, 13.0f, 8.0f);
        this.Leg8Seg1.mirror = true;
        this.setRotation(this.Leg8Seg1, 0.0f, 0.0f, 0.0f);
        this.Leg8Seg2 = new ModelRenderer(this, 21, 0);
        this.Leg8Seg2.addBox(-12.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg8Seg2.setPos(-12.0f, 14.0f, 8.0f);
        this.Leg8Seg2.mirror = true;
        this.setRotation(this.Leg8Seg2, 0.0f, 0.0f, 0.9294576f);
        this.Leg8Seg3 = new ModelRenderer(this, 15, 8);
        this.Leg8Seg3.addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3);
        this.Leg8Seg3.setPos(-18.0f, 4.0f, 8.0f);
        this.Leg8Seg3.mirror = true;
        this.setRotation(this.Leg8Seg3, 0.0f, 0.0f, -0.6320361f);
        this.Leg8Seg4 = new ModelRenderer(this, 0, 14);
        this.Leg8Seg4.addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3);
        this.Leg8Seg4.setPos(-26.0f, 12.0f, 8.0f);
        this.Leg8Seg4.mirror = true;
        this.setRotation(this.Leg8Seg4, 0.0f, 0.0f, 0.0f);
        this.Leg8Seg5 = new ModelRenderer(this, 0, 0);
        this.Leg8Seg5.addBox(0.0f, -1.5f, -1.5f, 7, 3, 3);
        this.Leg8Seg5.setPos(-27.0f, 19.0f, 8.0f);
        this.Leg8Seg5.mirror = true;
        this.setRotation(this.Leg8Seg5, 0.0f, 0.0f, 2.240008f);
    }
    @Override
    public void setupAnim(EmperorScorpion entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        EmperorScorpion e = (EmperorScorpion)entity;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float pi4 = 1.570795f;
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * f1 - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg1Seg1, this.Leg1Seg2, this.Leg1Seg3, this.Leg1Seg4, this.Leg1Seg5, newangle, upangle);
        this.doRightLeg(this.Leg5Seg1, this.Leg5Seg2, this.Leg5Seg3, this.Leg5Seg4, this.Leg5Seg5, - newangle, upangle);
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 1.0f * pi4)) * 3.1415927f * 0.12f * f1;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 2.0f * this.wingspeed - 1.0f * pi4)) * 3.1415927f * 0.12f * f1;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * f1 - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg2Seg1, this.Leg2Seg2, this.Leg2Seg3, this.Leg2Seg4, this.Leg2Seg5, newangle, upangle);
        this.doRightLeg(this.Leg6Seg1, this.Leg6Seg2, this.Leg6Seg3, this.Leg6Seg4, this.Leg6Seg5, - newangle, upangle);
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.12f * f1;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 2.0f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.12f * f1;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * f1 - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg3Seg1, this.Leg3Seg2, this.Leg3Seg3, this.Leg3Seg4, this.Leg3Seg5, newangle, upangle);
        this.doRightLeg(this.Leg7Seg1, this.Leg7Seg2, this.Leg7Seg3, this.Leg7Seg4, this.Leg7Seg5, - newangle, upangle);
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.12f * f1;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 2.0f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.12f * f1;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * f1 - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg4Seg1, this.Leg4Seg2, this.Leg4Seg3, this.Leg4Seg4, this.Leg4Seg5, newangle, upangle);
        this.doRightLeg(this.Leg8Seg1, this.Leg8Seg2, this.Leg8Seg3, this.Leg8Seg4, this.Leg8Seg5, - newangle, upangle);
        newangle = e.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f : MathHelper.cos((float)(f2 * 2.5f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.LeftManPart2.zRot = newangle;
        this.RightManPart2.zRot = - newangle;
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
        } else {
            this.doLeftClaw(0.0f);
        }
        if (r.ri1 == 2 || r.ri1 == 3) {
            this.doRightClaw(newangle);
        } else {
            this.doRightClaw(0.0f);
        }
        if (r.ri2 == 1) {
            this.doTail(newangle);
        } else {
            this.doTail(0.0f);
        }
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Stinger1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Stinger2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Stinger3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftShoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightShoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPincer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPincer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lefteye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Righteye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightMandible.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftMandible.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightManPart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftManPart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }

    private void doLeftLeg(ModelRenderer seg1, ModelRenderer seg2, ModelRenderer seg3, ModelRenderer seg4, ModelRenderer seg5, float angle, float upangle) {
        seg2.yRot = angle;
        seg3.yRot = angle;
        seg4.yRot = angle;
        seg5.yRot = angle;
        seg3.z = (float)((double)seg2.z - Math.sin(angle) * 6.0);
        seg3.x = (float)((double)seg2.x - Math.abs(Math.sin(angle) * 6.0) + 6.0);
        seg4.z = (float)((double)seg3.z - Math.sin(angle) * 9.0);
        seg4.x = (float)((double)seg3.x - Math.abs(Math.sin(angle) * 9.0) + 9.0);
        seg5.z = (float)((double)seg4.z - Math.sin(angle) * 1.0);
        seg5.x = (float)((double)seg4.x - Math.abs(Math.sin(angle) * 1.0) + 1.0);
        seg2.zRot = - upangle - 0.929f;
        seg3.zRot = - upangle + 0.632f;
        seg3.y = seg2.y + (float)(11.5 * Math.sin(seg2.zRot));
        seg4.y = seg3.y + (float)(11.5 * Math.sin(seg3.zRot));
        seg5.y = seg4.y + 6.5f;
    }

    private void doRightLeg(ModelRenderer seg1, ModelRenderer seg2, ModelRenderer seg3, ModelRenderer seg4, ModelRenderer seg5, float angle, float upangle) {
        seg2.yRot = angle;
        seg3.yRot = angle;
        seg4.yRot = angle;
        seg5.yRot = - angle;
        seg3.z = (float)((double)seg2.z + Math.sin(angle) * 6.0);
        seg3.x = (float)((double)seg2.x + Math.abs(Math.sin(angle) * 6.0) - 6.0);
        seg4.z = (float)((double)seg3.z + Math.sin(angle) * 9.0);
        seg4.x = (float)((double)seg3.x + Math.abs(Math.sin(angle) * 9.0) - 9.0);
        seg5.z = (float)((double)seg4.z + Math.sin(angle) * 1.0);
        seg5.x = (float)((double)seg4.x + Math.abs(Math.sin(angle) * 1.0) - 1.0);
        seg2.zRot = upangle + 0.929f;
        seg3.zRot = upangle - 0.632f;
        seg3.y = seg2.y - (float)(11.5 * Math.sin(seg2.zRot));
        seg4.y = seg3.y - (float)(11.5 * Math.sin(seg3.zRot));
        seg5.y = seg4.y + 6.5f;
    }

    private void doLeftClaw(float angle) {
        this.LeftArmSeg1.yRot = -1.57f + angle;
        this.LeftArmSeg2.z = (float)(-22.0 - Math.cos(this.LeftArmSeg1.yRot) * 12.0);
        this.LeftArmSeg3.z = this.LeftArmSeg2.z - 11.0f;
        this.LeftArmSeg4.z = this.LeftArmSeg2.z - 11.0f;
        this.LeftPincer.z = this.LeftArmSeg2.z - 11.0f;
        this.LeftArmSeg3.yRot = 0.074f + angle;
        this.LeftPincer.yRot = 0.371f - angle;
    }

    private void doRightClaw(float angle) {
        this.RightArmSeg1.yRot = 1.57f - angle;
        this.RightArmSeg2.z = (float)(-22.0 - Math.cos(this.RightArmSeg1.yRot) * 12.0);
        this.RightArmSeg3.z = this.RightArmSeg2.z - 11.0f;
        this.RightArmSeg4.z = this.RightArmSeg2.z - 11.0f;
        this.RightPincer.z = this.RightArmSeg2.z - 11.0f;
        this.RightArmSeg3.yRot = -0.074f - angle;
        this.RightPincer.yRot = -0.371f + angle;
    }

    private void doTail(float angle) {
        this.Tailseg1.xRot = 0.594f + angle;
        this.Tailseg2.xRot = this.Tailseg1.xRot + 0.48399997f + angle;
        this.Tailseg2.y = (float)((double)this.Tailseg1.y - Math.sin(this.Tailseg1.xRot) * 9.0);
        this.Tailseg2.z = (float)((double)this.Tailseg1.z + Math.cos(this.Tailseg1.xRot) * 9.0);
        this.Tailseg3.xRot = this.Tailseg2.xRot + 0.6320001f + angle;
        this.Tailseg3.y = (float)((double)this.Tailseg2.y - Math.sin(this.Tailseg2.xRot) * 10.0);
        this.Tailseg3.z = (float)((double)this.Tailseg2.z + Math.cos(this.Tailseg2.xRot) * 10.0);
        this.Tailseg4.xRot = this.Tailseg3.xRot + 0.5569999f - angle;
        this.Tailseg4.y = (float)((double)this.Tailseg3.y - Math.sin(this.Tailseg3.xRot) * 10.0);
        this.Tailseg4.z = (float)((double)this.Tailseg3.z + Math.cos(this.Tailseg3.xRot) * 10.0);
        this.Tailseg5.xRot = this.Tailseg4.xRot + 0.63199997f - angle;
        this.Tailseg5.y = (float)((double)this.Tailseg4.y - Math.sin(this.Tailseg4.xRot) * 10.0);
        this.Tailseg5.z = (float)((double)this.Tailseg4.z + Math.cos(this.Tailseg4.xRot) * 10.0);
        this.Tailseg6.xRot = this.Tailseg5.xRot + -5.501f - angle * 3.0f / 2.0f - 0.4f;
        this.Tailseg6.y = (float)((double)this.Tailseg5.y - Math.sin(this.Tailseg5.xRot) * 10.0);
        this.Tailseg6.z = (float)((double)this.Tailseg5.z + Math.cos(this.Tailseg5.xRot) * 10.0);
        this.Tailseg7.xRot = this.Tailseg6.xRot + -2.822f - angle * 2.5f - 2.2f;
        this.Tailseg7.y = (float)((double)this.Tailseg6.y - Math.sin(this.Tailseg6.xRot) * 10.0);
        this.Tailseg7.z = (float)((double)this.Tailseg6.z + Math.cos(this.Tailseg6.xRot) * 10.0);
        this.Tailseg8.xRot = this.Tailseg7.xRot;
        this.Tailseg8.y = this.Tailseg7.y;
        this.Tailseg8.z = this.Tailseg7.z;
        this.Stinger1.xRot = this.Tailseg7.xRot + 0.0f + angle * 0.66f;
        this.Stinger1.y = (float)((double)this.Tailseg7.y - Math.sin(this.Tailseg7.xRot) * 10.0);
        this.Stinger1.z = (float)((double)this.Tailseg7.z + Math.cos(this.Tailseg7.xRot) * 10.0);
        this.Stinger2.xRot = this.Stinger1.xRot + -0.48f + angle;
        this.Stinger2.y = (float)((double)this.Stinger1.y - Math.sin(this.Stinger1.xRot) * 3.0);
        this.Stinger2.z = (float)((double)this.Stinger1.z + Math.cos(this.Stinger1.xRot) * 3.0);
        this.Stinger3.xRot = this.Stinger2.xRot + -1.01f + angle * 1.7f;
        this.Stinger3.y = (float)((double)this.Stinger2.y - Math.sin(this.Stinger2.xRot) * 3.0);
        this.Stinger3.z = (float)((double)this.Stinger2.z + Math.cos(this.Stinger2.xRot) * 3.0);
    }
}

