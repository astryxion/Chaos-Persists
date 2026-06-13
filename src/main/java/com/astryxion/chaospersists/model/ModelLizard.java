/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.Lizard
 *  com.astryxion.chaospersists.ModelLizard
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Lizard;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLizard extends EntityModel<Lizard> {
    private float wingspeed = 1.0f;
    ModelRenderer BodyBack;
    ModelRenderer TopBackLeftLeg;
    ModelRenderer TailTip;
    ModelRenderer BodyFront;
    ModelRenderer TailBase1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer Neck;
    ModelRenderer TopFrontLeftLeg;
    ModelRenderer TopBackRightLeg;
    ModelRenderer BottomBackRightLeg;
    ModelRenderer TopFrontRightLeg;
    ModelRenderer BottomBackLeftLeg;
    ModelRenderer BottomFrontRightLeg;
    ModelRenderer BottomFrontLeftLeg;
    ModelRenderer BodyCenter;
    ModelRenderer Toe7;
    ModelRenderer Toe6;
    ModelRenderer BackLeftFoot;
    ModelRenderer Toe4;
    ModelRenderer Toe5;
    ModelRenderer BackRightFoot;
    ModelRenderer Toe8;
    ModelRenderer Toe1;
    ModelRenderer FrontLeftFoot;
    ModelRenderer Toe3;
    ModelRenderer Toe2;
    ModelRenderer FrontRightFoot;
    ModelRenderer FinRidge7;
    ModelRenderer FinRidge6;
    ModelRenderer FinRidge5;
    ModelRenderer FinRidge4;
    ModelRenderer FinRidge3;
    ModelRenderer FinRidge2;
    ModelRenderer FinRidge1;
    ModelRenderer Fin10;
    ModelRenderer Fin9;
    ModelRenderer Fin8;
    ModelRenderer Fin7;
    ModelRenderer Fin6;
    ModelRenderer Fin5;
    ModelRenderer Fin3;
    ModelRenderer Fin2;
    ModelRenderer Tooth11;
    ModelRenderer Tooth10;
    ModelRenderer Tooth8;
    ModelRenderer Tooth7;
    ModelRenderer Tooth6;
    ModelRenderer Tooth5;
    ModelRenderer Tooth4;
    ModelRenderer Tooth3;
    ModelRenderer Tooth2;
    ModelRenderer CenterRightNose;
    ModelRenderer CenterLeftNose;
    ModelRenderer Tooth1;
    ModelRenderer BottomNose;
    ModelRenderer TopNose;
    ModelRenderer JawTop;
    ModelRenderer CenterMiddleNose;
    ModelRenderer RightEye;
    ModelRenderer LeftEye;
    ModelRenderer Tooth16;
    ModelRenderer Tooth15;
    ModelRenderer Tooth14;
    ModelRenderer Tooth13;
    ModelRenderer Tooth12;
    ModelRenderer Tooth9;
    ModelRenderer BottomJaw;
    ModelRenderer Hat1;
    ModelRenderer Hat2;

    public ModelLizard(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.BodyBack = new ModelRenderer(this, 92, 48);
        this.BodyBack.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 8);
        this.BodyBack.setPos(0.0f, 14.0f, 0.0f);
        this.BodyBack.mirror = true;
        this.setRotation(this.BodyBack, 0.0f, 0.0f, 0.0f);
        this.TopBackLeftLeg = new ModelRenderer(this, 54, 32);
        this.TopBackLeftLeg.addBox(0.0f, -2.0f, -2.0f, 8, 3, 3);
        this.TopBackLeftLeg.setPos(3.0f, 13.0f, 2.0f);
        this.TopBackLeftLeg.mirror = true;
        this.setRotation(this.TopBackLeftLeg, 0.0f, 0.0f, 0.2617994f);
        this.TailTip = new ModelRenderer(this, 100, 118);
        this.TailTip.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8);
        this.TailTip.setPos(0.0f, 23.0f, 41.0f);
        this.TailTip.mirror = true;
        this.setRotation(this.TailTip, 0.0f, 0.0f, 0.0f);
        this.BodyFront = new ModelRenderer(this, 92, 16);
        this.BodyFront.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
        this.BodyFront.setPos(0.0f, 14.0f, -8.0f);
        this.BodyFront.mirror = true;
        this.setRotation(this.BodyFront, 0.0f, 0.0f, 0.0f);
        this.TailBase1 = new ModelRenderer(this, 88, 64);
        this.TailBase1.addBox(-3.0f, -3.0f, 0.0f, 6, 6, 14);
        this.TailBase1.setPos(0.0f, 14.0f, 7.0f);
        this.TailBase1.mirror = true;
        this.setRotation(this.TailBase1, -0.2617994f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 95, 84);
        this.Tail2.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 10);
        this.Tail2.setPos(0.0f, 17.0f, 19.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, -0.5235988f, 0.0f, 0.0f);
        this.Tail3 = new ModelRenderer(this, 100, 98);
        this.Tail3.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8);
        this.Tail3.setPos(0.0f, 21.0f, 26.0f);
        this.Tail3.mirror = true;
        this.setRotation(this.Tail3, -0.2617994f, 0.0f, 0.0f);
        this.Tail4 = new ModelRenderer(this, 100, 108);
        this.Tail4.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8);
        this.Tail4.setPos(0.0f, 23.0f, 33.0f);
        this.Tail4.mirror = true;
        this.setRotation(this.Tail4, 0.0f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer(this, 100, 9);
        this.Neck.addBox(-3.0f, -2.0f, -2.0f, 6, 5, 2);
        this.Neck.setPos(0.0f, 12.0f, -16.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.0f, 0.0f, 0.0f);
        this.TopFrontLeftLeg = new ModelRenderer(this, 26, 12);
        this.TopFrontLeftLeg.addBox(0.0f, -2.0f, -2.0f, 8, 3, 3);
        this.TopFrontLeftLeg.setPos(3.0f, 13.0f, -12.0f);
        this.TopFrontLeftLeg.mirror = true;
        this.setRotation(this.TopFrontLeftLeg, 0.0f, 0.0f, 0.2617994f);
        this.TopBackRightLeg = new ModelRenderer(this, 26, 32);
        this.TopBackRightLeg.addBox(-8.0f, -2.0f, -2.0f, 8, 3, 3);
        this.TopBackRightLeg.setPos(-3.0f, 13.0f, 2.0f);
        this.TopBackRightLeg.mirror = true;
        this.setRotation(this.TopBackRightLeg, 0.0f, 0.0f, -0.2617994f);
        this.BottomBackRightLeg = new ModelRenderer(this, 25, 26);
        this.BottomBackRightLeg.addBox(-12.0f, -8.0f, -2.0f, 9, 3, 3);
        this.BottomBackRightLeg.setPos(-3.0f, 13.0f, 2.0f);
        this.BottomBackRightLeg.mirror = true;
        this.setRotation(this.BottomBackRightLeg, 0.0f, 0.0f, -1.308997f);
        this.TopFrontRightLeg = new ModelRenderer(this, 54, 12);
        this.TopFrontRightLeg.addBox(-8.0f, -2.0f, -2.0f, 8, 3, 3);
        this.TopFrontRightLeg.setPos(-3.0f, 13.0f, -12.0f);
        this.TopFrontRightLeg.mirror = true;
        this.setRotation(this.TopFrontRightLeg, 0.0f, 0.0f, -0.2617994f);
        this.BottomBackLeftLeg = new ModelRenderer(this, 53, 26);
        this.BottomBackLeftLeg.addBox(3.0f, -8.0f, -2.0f, 9, 3, 3);
        this.BottomBackLeftLeg.setPos(3.0f, 13.0f, 2.0f);
        this.BottomBackLeftLeg.mirror = true;
        this.setRotation(this.BottomBackLeftLeg, 0.0f, 0.0f, 1.308997f);
        this.BottomFrontRightLeg = new ModelRenderer(this, 53, 18);
        this.BottomFrontRightLeg.addBox(-12.0f, -8.0f, -2.0f, 9, 3, 3);
        this.BottomFrontRightLeg.setPos(-3.0f, 13.0f, -12.0f);
        this.BottomFrontRightLeg.mirror = true;
        this.setRotation(this.BottomFrontRightLeg, 0.0f, 0.0f, -1.308997f);
        this.BottomFrontLeftLeg = new ModelRenderer(this, 25, 18);
        this.BottomFrontLeftLeg.addBox(3.0f, -8.0f, -2.0f, 9, 3, 3);
        this.BottomFrontLeftLeg.setPos(3.0f, 13.0f, -12.0f);
        this.BottomFrontLeftLeg.mirror = true;
        this.setRotation(this.BottomFrontLeftLeg, 0.0f, 0.0f, 1.308997f);
        this.BodyCenter = new ModelRenderer(this, 92, 32);
        this.BodyCenter.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.BodyCenter.setPos(0.0f, 14.0f, -4.0f);
        this.BodyCenter.mirror = true;
        this.setRotation(this.BodyCenter, 0.0f, 0.0f, 0.0f);
        this.Toe7 = new ModelRenderer(this, 104, 0);
        this.Toe7.addBox(10.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe7.setPos(3.0f, 13.0f, 2.0f);
        this.Toe7.mirror = true;
        this.setRotation(this.Toe7, 0.0f, 0.0f, 0.0f);
        this.Toe6 = new ModelRenderer(this, 108, 0);
        this.Toe6.addBox(8.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe6.setPos(3.0f, 13.0f, 2.0f);
        this.Toe6.mirror = true;
        this.setRotation(this.Toe6, 0.0f, 0.0f, 0.0f);
        this.BackLeftFoot = new ModelRenderer(this, 20, 0);
        this.BackLeftFoot.addBox(7.0f, 9.0f, -4.0f, 4, 2, 6);
        this.BackLeftFoot.setPos(3.0f, 13.0f, 2.0f);
        this.BackLeftFoot.mirror = true;
        this.setRotation(this.BackLeftFoot, 0.0f, 0.0f, 0.0f);
        this.Toe4 = new ModelRenderer(this, 80, 0);
        this.Toe4.addBox(-11.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe4.setPos(-3.0f, 13.0f, 2.0f);
        this.Toe4.mirror = true;
        this.setRotation(this.Toe4, 0.0f, 0.0f, 0.0f);
        this.Toe5 = new ModelRenderer(this, 84, 0);
        this.Toe5.addBox(-9.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe5.setPos(-3.0f, 13.0f, 2.0f);
        this.Toe5.mirror = true;
        this.setRotation(this.Toe5, 0.0f, 0.0f, 0.0f);
        this.BackRightFoot = new ModelRenderer(this, 60, 0);
        this.BackRightFoot.addBox(-11.0f, 9.0f, -4.0f, 4, 2, 6);
        this.BackRightFoot.setPos(-3.0f, 13.0f, 2.0f);
        this.BackRightFoot.mirror = true;
        this.setRotation(this.BackRightFoot, 0.0f, 0.0f, 0.0f);
        this.Toe8 = new ModelRenderer(this, 100, 0);
        this.Toe8.addBox(10.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe8.setPos(3.0f, 13.0f, -12.0f);
        this.Toe8.mirror = true;
        this.setRotation(this.Toe8, 0.0f, 0.0f, 0.0f);
        this.Toe1 = new ModelRenderer(this, 96, 0);
        this.Toe1.addBox(8.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe1.setPos(3.0f, 13.0f, -12.0f);
        this.Toe1.mirror = true;
        this.setRotation(this.Toe1, 0.0f, 0.0f, 0.0f);
        this.FrontLeftFoot = new ModelRenderer(this, 40, 0);
        this.FrontLeftFoot.addBox(7.0f, 9.0f, -4.0f, 4, 2, 6);
        this.FrontLeftFoot.setPos(3.0f, 13.0f, -12.0f);
        this.FrontLeftFoot.mirror = true;
        this.setRotation(this.FrontLeftFoot, 0.0f, 0.0f, 0.0f);
        this.Toe3 = new ModelRenderer(this, 88, 0);
        this.Toe3.addBox(-11.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe3.setPos(-3.0f, 13.0f, -12.0f);
        this.Toe3.mirror = true;
        this.setRotation(this.Toe3, 0.0f, 0.0f, 0.0f);
        this.Toe2 = new ModelRenderer(this, 92, 0);
        this.Toe2.addBox(-9.0f, 10.0f, -5.0f, 1, 1, 1);
        this.Toe2.setPos(-3.0f, 13.0f, -12.0f);
        this.Toe2.mirror = true;
        this.setRotation(this.Toe2, 0.0f, 0.0f, 0.0f);
        this.FrontRightFoot = new ModelRenderer(this, 0, 0);
        this.FrontRightFoot.addBox(-11.0f, 9.0f, -4.0f, 4, 2, 6);
        this.FrontRightFoot.setPos(-3.0f, 13.0f, -12.0f);
        this.FrontRightFoot.mirror = true;
        this.setRotation(this.FrontRightFoot, 0.0f, 0.0f, 0.0f);
        this.FinRidge7 = new ModelRenderer(this, 0, 99);
        this.FinRidge7.addBox(0.0f, -13.0f, 0.0f, 2, 13, 1);
        this.FinRidge7.setPos(-1.0f, 10.0f, -4.5f);
        this.FinRidge7.mirror = true;
        this.setRotation(this.FinRidge7, -0.9666439f, 0.0f, 0.0f);
        this.FinRidge6 = new ModelRenderer(this, 6, 98);
        this.FinRidge6.addBox(0.0f, -13.0f, 0.0f, 2, 13, 1);
        this.FinRidge6.setPos(-1.0f, 10.0f, -4.0f);
        this.FinRidge6.mirror = true;
        this.setRotation(this.FinRidge6, -0.5205006f, 0.0f, 0.0f);
        this.FinRidge5 = new ModelRenderer(this, 12, 99);
        this.FinRidge5.addBox(0.0f, -13.0f, 0.0f, 2, 13, 1);
        this.FinRidge5.setPos(-1.0f, 10.0f, -4.0f);
        this.FinRidge5.mirror = true;
        this.setRotation(this.FinRidge5, 0.0f, 0.0f, 0.0f);
        this.FinRidge4 = new ModelRenderer(this, 6, 114);
        this.FinRidge4.addBox(0.0f, -13.0f, 0.0f, 2, 13, 1);
        this.FinRidge4.setPos(-1.0f, 10.0f, -3.5f);
        this.FinRidge4.mirror = true;
        this.setRotation(this.FinRidge4, 0.9666439f, 0.0f, 0.0f);
        this.FinRidge3 = new ModelRenderer(this, 12, 115);
        this.FinRidge3.addBox(0.0f, -13.0f, 0.0f, 2, 13, 1);
        this.FinRidge3.setPos(-1.0f, 10.0f, -4.0f);
        this.FinRidge3.mirror = true;
        this.setRotation(this.FinRidge3, 0.5205006f, 0.0f, 0.0f);
        this.FinRidge2 = new ModelRenderer(this, 0, 84);
        this.FinRidge2.addBox(0.0f, -13.0f, 0.0f, 2, 13, 1);
        this.FinRidge2.setPos(-1.0f, 10.0f, -4.5f);
        this.FinRidge2.mirror = true;
        this.setRotation(this.FinRidge2, -1.375609f, 0.0f, 0.0f);
        this.FinRidge1 = new ModelRenderer(this, 0, 114);
        this.FinRidge1.addBox(0.0f, -13.0f, 0.0f, 2, 13, 1);
        this.FinRidge1.setPos(-1.0f, 10.0f, -3.5f);
        this.FinRidge1.mirror = true;
        this.setRotation(this.FinRidge1, 1.412787f, 0.0f, 0.0f);
        this.Fin10 = new ModelRenderer(this, 0, 58);
        this.Fin10.addBox(0.0f, -13.0f, -2.0f, 0, 11, 6);
        this.Fin10.setPos(0.0f, 10.5f, -5.0f);
        this.Fin10.mirror = true;
        this.setRotation(this.Fin10, 0.2094395f, 0.0f, 0.0f);
        this.Fin9 = new ModelRenderer(this, 7, 84);
        this.Fin9.addBox(0.0f, -11.0f, 0.0f, 0, 11, 3);
        this.Fin9.setPos(0.0f, 10.0f, -5.0f);
        this.Fin9.mirror = true;
        this.setRotation(this.Fin9, 1.570796f, 0.0f, 0.0f);
        this.Fin8 = new ModelRenderer(this, 12, 34);
        this.Fin8.addBox(0.0f, -7.0f, -4.0f, 0, 7, 4);
        this.Fin8.setPos(0.0f, 10.0f, 1.0f);
        this.Fin8.mirror = true;
        this.setRotation(this.Fin8, -1.570796f, 0.0f, 0.0f);
        this.Fin7 = new ModelRenderer(this, 12, 46);
        this.Fin7.addBox(0.0f, -8.0f, -4.0f, 0, 8, 4);
        this.Fin7.setPos(0.0f, 10.0f, 1.0f);
        this.Fin7.mirror = true;
        this.setRotation(this.Fin7, -1.033256f, 0.0f, 0.0f);
        this.Fin6 = new ModelRenderer(this, 0, 31);
        this.Fin6.addBox(0.0f, -10.0f, -4.0f, 0, 10, 4);
        this.Fin6.setPos(0.0f, 10.0f, -1.0f);
        this.Fin6.mirror = true;
        this.setRotation(this.Fin6, -0.7267386f, 0.0f, 0.0f);
        this.Fin5 = new ModelRenderer(this, 30, 59);
        this.Fin5.addBox(0.0f, -12.0f, -5.0f, 0, 11, 6);
        this.Fin5.setPos(0.0f, 10.0f, -2.0f);
        this.Fin5.mirror = true;
        this.setRotation(this.Fin5, -0.3003206f, 0.0f, 0.0f);
        this.Fin3 = new ModelRenderer(this, 14, 60);
        this.Fin3.addBox(0.0f, -12.0f, -3.0f, 0, 12, 6);
        this.Fin3.setPos(0.0f, 10.0f, -4.0f);
        this.Fin3.mirror = true;
        this.setRotation(this.Fin3, 0.7073231f, 0.0f, 0.0f);
        this.Fin2 = new ModelRenderer(this, 14, 79);
        this.Fin2.addBox(0.0f, -12.0f, -4.0f, 0, 11, 6);
        this.Fin2.setPos(0.0f, 10.0f, -4.0f);
        this.Fin2.mirror = true;
        this.setRotation(this.Fin2, 1.048747f, 0.0f, 0.0f);
        this.Tooth11 = new ModelRenderer(this, 24, 110);
        this.Tooth11.addBox(3.0f, 3.0f, -8.0f, 1, 1, 1);
        this.Tooth11.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth11.mirror = true;
        this.setRotation(this.Tooth11, 0.0f, 0.0f, 0.0f);
        this.Tooth10 = new ModelRenderer(this, 24, 106);
        this.Tooth10.addBox(3.0f, 3.0f, -10.0f, 1, 1, 1);
        this.Tooth10.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth10.mirror = true;
        this.setRotation(this.Tooth10, 0.0f, 0.0f, 0.0f);
        this.Tooth8 = new ModelRenderer(this, 28, 95);
        this.Tooth8.addBox(3.0f, 3.0f, -14.0f, 1, 1, 1);
        this.Tooth8.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth8.mirror = true;
        this.setRotation(this.Tooth8, 0.0f, 0.0f, 0.0f);
        this.Tooth7 = new ModelRenderer(this, 70, 106);
        this.Tooth7.addBox(-4.0f, 3.0f, -10.0f, 1, 1, 1);
        this.Tooth7.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth7.mirror = true;
        this.setRotation(this.Tooth7, 0.0f, 0.0f, 0.0f);
        this.Tooth6 = new ModelRenderer(this, 70, 102);
        this.Tooth6.addBox(-4.0f, 3.0f, -12.0f, 1, 1, 1);
        this.Tooth6.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth6.mirror = true;
        this.setRotation(this.Tooth6, 0.0f, 0.0f, 0.0f);
        this.Tooth5 = new ModelRenderer(this, 66, 95);
        this.Tooth5.addBox(-4.0f, 3.0f, -14.0f, 1, 1, 1);
        this.Tooth5.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth5.mirror = true;
        this.setRotation(this.Tooth5, 0.0f, 0.0f, 0.0f);
        this.Tooth4 = new ModelRenderer(this, 60, 95);
        this.Tooth4.addBox(1.0f, 3.0f, -14.0f, 1, 1, 1);
        this.Tooth4.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth4.mirror = true;
        this.setRotation(this.Tooth4, 0.0f, 0.0f, 0.0f);
        this.Tooth3 = new ModelRenderer(this, 34, 95);
        this.Tooth3.addBox(-2.0f, 3.0f, -14.0f, 1, 1, 1);
        this.Tooth3.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth3.mirror = true;
        this.setRotation(this.Tooth3, 0.0f, 0.0f, 0.0f);
        this.Tooth2 = new ModelRenderer(this, 70, 110);
        this.Tooth2.addBox(-4.0f, 3.0f, -8.0f, 1, 1, 1);
        this.Tooth2.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth2.mirror = true;
        this.setRotation(this.Tooth2, 0.0f, 0.0f, 0.0f);
        this.CenterRightNose = new ModelRenderer(this, 40, 88);
        this.CenterRightNose.addBox(-4.0f, 0.0f, -14.0f, 1, 1, 1);
        this.CenterRightNose.setPos(0.0f, 12.0f, -18.0f);
        this.CenterRightNose.mirror = true;
        this.setRotation(this.CenterRightNose, 0.0f, 0.0f, 0.0f);
        this.CenterLeftNose = new ModelRenderer(this, 54, 88);
        this.CenterLeftNose.addBox(3.0f, 0.0f, -14.0f, 1, 1, 1);
        this.CenterLeftNose.setPos(0.0f, 12.0f, -18.0f);
        this.CenterLeftNose.mirror = true;
        this.setRotation(this.CenterLeftNose, 0.0f, 0.0f, 0.0f);
        this.Tooth1 = new ModelRenderer(this, 24, 102);
        this.Tooth1.addBox(3.0f, 3.0f, -12.0f, 1, 1, 1);
        this.Tooth1.setPos(0.0f, 12.0f, -18.0f);
        this.Tooth1.mirror = true;
        this.setRotation(this.Tooth1, 0.0f, 0.0f, 0.0f);
        this.BottomNose = new ModelRenderer(this, 40, 90);
        this.BottomNose.addBox(-4.0f, 1.0f, -14.0f, 8, 2, 1);
        this.BottomNose.setPos(0.0f, 12.0f, -18.0f);
        this.BottomNose.mirror = true;
        this.setRotation(this.BottomNose, 0.0f, 0.0f, 0.0f);
        this.TopNose = new ModelRenderer(this, 40, 84);
        this.TopNose.addBox(-4.0f, -3.0f, -14.0f, 8, 3, 1);
        this.TopNose.setPos(0.0f, 12.0f, -18.0f);
        this.TopNose.mirror = true;
        this.setRotation(this.TopNose, 0.0f, 0.0f, 0.0f);
        this.JawTop = new ModelRenderer(this, 28, 97);
        this.JawTop.addBox(-4.0f, -3.0f, -13.0f, 8, 6, 13);
        this.JawTop.setPos(0.0f, 12.0f, -18.0f);
        this.JawTop.mirror = true;
        this.setRotation(this.JawTop, 0.0f, 0.0f, 0.0f);
        this.CenterMiddleNose = new ModelRenderer(this, 46, 88);
        this.CenterMiddleNose.addBox(-1.0f, 0.0f, -14.0f, 2, 1, 1);
        this.CenterMiddleNose.setPos(0.0f, 12.0f, -18.0f);
        this.CenterMiddleNose.mirror = true;
        this.setRotation(this.CenterMiddleNose, 0.0f, 0.0f, 0.0f);
        this.RightEye = new ModelRenderer(this, 116, 10);
        this.RightEye.addBox(-2.0f, -4.0f, -4.0f, 2, 2, 1);
        this.RightEye.setPos(0.0f, 12.0f, -18.0f);
        this.RightEye.mirror = true;
        this.setRotation(this.RightEye, 0.0f, 0.7853982f, 0.3490659f);
        this.LeftEye = new ModelRenderer(this, 94, 10);
        this.LeftEye.addBox(0.0f, -4.0f, -4.0f, 2, 2, 1);
        this.LeftEye.setPos(0.0f, 12.0f, -18.0f);
        this.LeftEye.mirror = true;
        this.setRotation(this.LeftEye, 0.0f, -0.7853982f, -0.3490659f);
        this.Tooth16 = new ModelRenderer(this, 24, 97);
        this.Tooth16.addBox(3.0f, -1.0f, -10.0f, 1, 1, 1);
        this.Tooth16.setPos(0.0f, 14.0f, -19.0f);
        this.Tooth16.mirror = true;
        this.setRotation(this.Tooth16, 0.5235988f, 0.0f, 0.0f);
        this.Tooth15 = new ModelRenderer(this, 70, 97);
        this.Tooth15.addBox(-4.0f, -1.0f, -10.0f, 1, 1, 1);
        this.Tooth15.setPos(0.0f, 14.0f, -19.0f);
        this.Tooth15.mirror = true;
        this.setRotation(this.Tooth15, 0.5235988f, 0.0f, 0.0f);
        this.Tooth14 = new ModelRenderer(this, 42, 95);
        this.Tooth14.addBox(-2.0f, -1.0f, -10.0f, 1, 1, 1);
        this.Tooth14.setPos(0.0f, 14.0f, -19.0f);
        this.Tooth14.mirror = true;
        this.setRotation(this.Tooth14, 0.5235988f, 0.0f, 0.0f);
        this.Tooth13 = new ModelRenderer(this, 52, 95);
        this.Tooth13.addBox(1.0f, -1.0f, -10.0f, 1, 1, 1);
        this.Tooth13.setPos(0.0f, 14.0f, -19.0f);
        this.Tooth13.mirror = true;
        this.setRotation(this.Tooth13, 0.5235988f, 0.0f, 0.0f);
        this.Tooth12 = new ModelRenderer(this, 24, 114);
        this.Tooth12.addBox(3.0f, -1.0f, -7.0f, 1, 1, 1);
        this.Tooth12.setPos(0.0f, 14.0f, -19.0f);
        this.Tooth12.mirror = true;
        this.setRotation(this.Tooth12, 0.5235988f, 0.0f, 0.0f);
        this.Tooth9 = new ModelRenderer(this, 70, 114);
        this.Tooth9.addBox(-4.0f, -1.0f, -7.0f, 1, 1, 1);
        this.Tooth9.setPos(0.0f, 14.0f, -19.0f);
        this.Tooth9.mirror = true;
        this.setRotation(this.Tooth9, 0.5235988f, 0.0f, 0.0f);
        this.BottomJaw = new ModelRenderer(this, 31, 116);
        this.BottomJaw.addBox(-4.0f, 0.0f, -10.0f, 8, 2, 10);
        this.BottomJaw.setPos(0.0f, 14.0f, -19.0f);
        this.BottomJaw.mirror = true;
        this.setRotation(this.BottomJaw, 0.5235988f, 0.0f, 0.0f);
        this.Hat1 = new ModelRenderer(this, 30, 40);
        this.Hat1.addBox(-2.0f, -4.0f, -6.0f, 4, 1, 6);
        this.Hat1.setPos(0.0f, 12.0f, -18.0f);
        this.Hat1.mirror = true;
        this.setRotation(this.Hat1, 0.0f, 0.0f, 0.0f);
        this.Hat2 = new ModelRenderer(this, 30, 40);
        this.Hat2.addBox(-1.5f, -6.0f, -4.0f, 3, 2, 4);
        this.Hat2.setPos(0.0f, 12.0f, -18.0f);
        this.Hat2.mirror = true;
        this.setRotation(this.Hat2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Lizard entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Lizard e = (Lizard)entity;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.TopFrontLeftLeg.yRot = newangle;
        this.BottomFrontLeftLeg.xRot = newangle;
        this.FrontLeftFoot.yRot = newangle;
        this.Toe8.yRot = newangle;
        this.Toe1.yRot = newangle;
        this.TopFrontRightLeg.yRot = newangle;
        this.BottomFrontRightLeg.xRot = - newangle;
        this.FrontRightFoot.yRot = newangle;
        this.Toe3.yRot = newangle;
        this.Toe2.yRot = newangle;
        this.TopBackLeftLeg.yRot = - newangle;
        this.BottomBackLeftLeg.xRot = - newangle;
        this.BackLeftFoot.yRot = - newangle;
        this.Toe7.yRot = - newangle;
        this.Toe6.yRot = - newangle;
        this.TopBackRightLeg.yRot = - newangle;
        this.BottomBackRightLeg.xRot = newangle;
        this.BackRightFoot.yRot = - newangle;
        this.Toe4.yRot = - newangle;
        this.Toe5.yRot = - newangle;
        this.BottomJaw.xRot = e.getAttacking() != 0 ? 0.52f + MathHelper.cos((float)(f2 * 0.45f)) * 0.35f : 0.25f;
        this.Tooth9.xRot = this.BottomJaw.xRot;
        this.Tooth15.xRot = this.BottomJaw.xRot;
        this.Tooth14.xRot = this.BottomJaw.xRot;
        this.Tooth13.xRot = this.BottomJaw.xRot;
        this.Tooth16.xRot = this.BottomJaw.xRot;
        this.Tooth12.xRot = this.BottomJaw.xRot;
        newangle = MathHelper.cos((float)(f2 * 0.25f * this.wingspeed)) * 3.1415927f * 0.05f;
        if (e.getAttacking() != 0) {
            newangle = MathHelper.cos((float)(f2 * 1.25f * this.wingspeed)) * 3.1415927f * 0.35f;
        }
        this.TailBase1.yRot = newangle * 0.25f;
        this.Tail2.z = this.TailBase1.z + (float)Math.cos(this.TailBase1.yRot) * 12.0f;
        this.Tail2.x = this.TailBase1.x + (float)Math.sin(this.TailBase1.yRot) * 12.0f;
        this.Tail2.yRot = newangle * 0.5f;
        this.Tail3.z = this.Tail2.z + (float)Math.cos(this.Tail2.yRot) * 9.0f;
        this.Tail3.x = this.Tail2.x + (float)Math.sin(this.Tail2.yRot) * 9.0f;
        this.Tail3.yRot = newangle * 0.75f;
        this.Tail4.z = this.Tail3.z + (float)Math.cos(this.Tail3.yRot) * 7.0f;
        this.Tail4.x = this.Tail3.x + (float)Math.sin(this.Tail3.yRot) * 7.0f;
        this.Tail4.yRot = newangle * 1.0f;
        this.TailTip.z = this.Tail4.z + (float)Math.cos(this.Tail4.yRot) * 7.0f;
        this.TailTip.x = this.Tail4.x + (float)Math.sin(this.Tail4.yRot) * 7.0f;
        this.TailTip.yRot = newangle * 1.25f;
        this.Neck.yRot = (float)Math.toRadians(f3) * 0.25f;
        this.JawTop.z = this.Neck.z - (float)Math.cos(this.Neck.yRot) * 2.0f;
        this.JawTop.x = this.Neck.x - (float)Math.sin(this.Neck.yRot) * 2.0f;
        this.JawTop.yRot = (float)Math.toRadians(f3) * 0.5f;
        this.TopNose.z = this.JawTop.z;
        this.TopNose.x = this.JawTop.x;
        this.TopNose.yRot = this.JawTop.yRot;
        this.BottomNose.z = this.JawTop.z;
        this.BottomNose.x = this.JawTop.x;
        this.BottomNose.yRot = this.JawTop.yRot;
        this.CenterRightNose.z = this.JawTop.z;
        this.CenterRightNose.x = this.JawTop.x;
        this.CenterRightNose.yRot = this.JawTop.yRot;
        this.CenterMiddleNose.z = this.JawTop.z;
        this.CenterMiddleNose.x = this.JawTop.x;
        this.CenterMiddleNose.yRot = this.JawTop.yRot;
        this.CenterLeftNose.z = this.JawTop.z;
        this.CenterLeftNose.x = this.JawTop.x;
        this.CenterLeftNose.yRot = this.JawTop.yRot;
        this.RightEye.z = this.JawTop.z;
        this.RightEye.x = this.JawTop.x;
        this.RightEye.yRot = this.JawTop.yRot + 0.78f;
        this.LeftEye.z = this.JawTop.z;
        this.LeftEye.x = this.JawTop.x;
        this.LeftEye.yRot = this.JawTop.yRot - 0.78f;
        this.Tooth11.z = this.JawTop.z;
        this.Tooth11.x = this.JawTop.x;
        this.Tooth11.yRot = this.JawTop.yRot;
        this.Tooth10.z = this.JawTop.z;
        this.Tooth10.x = this.JawTop.x;
        this.Tooth10.yRot = this.JawTop.yRot;
        this.Tooth1.z = this.JawTop.z;
        this.Tooth1.x = this.JawTop.x;
        this.Tooth1.yRot = this.JawTop.yRot;
        this.Tooth8.z = this.JawTop.z;
        this.Tooth8.x = this.JawTop.x;
        this.Tooth8.yRot = this.JawTop.yRot;
        this.Tooth4.z = this.JawTop.z;
        this.Tooth4.x = this.JawTop.x;
        this.Tooth4.yRot = this.JawTop.yRot;
        this.Tooth3.z = this.JawTop.z;
        this.Tooth3.x = this.JawTop.x;
        this.Tooth3.yRot = this.JawTop.yRot;
        this.Tooth5.z = this.JawTop.z;
        this.Tooth5.x = this.JawTop.x;
        this.Tooth5.yRot = this.JawTop.yRot;
        this.Tooth6.z = this.JawTop.z;
        this.Tooth6.x = this.JawTop.x;
        this.Tooth6.yRot = this.JawTop.yRot;
        this.Tooth7.z = this.JawTop.z;
        this.Tooth7.x = this.JawTop.x;
        this.Tooth7.yRot = this.JawTop.yRot;
        this.Tooth2.z = this.JawTop.z;
        this.Tooth2.x = this.JawTop.x;
        this.Tooth2.yRot = this.JawTop.yRot;
        this.Hat1.z = this.JawTop.z;
        this.Hat1.x = this.JawTop.x;
        this.Hat1.yRot = this.JawTop.yRot;
        this.Hat2.z = this.JawTop.z;
        this.Hat2.x = this.JawTop.x;
        this.Hat2.yRot = this.JawTop.yRot;
        this.BottomJaw.z = this.Neck.z - (float)Math.cos(this.Neck.yRot) * 3.0f;
        this.BottomJaw.x = this.Neck.x - (float)Math.sin(this.Neck.yRot) * 3.0f;
        this.BottomJaw.yRot = (float)Math.toRadians(f3) * 0.5f;
        this.Tooth9.z = this.BottomJaw.z;
        this.Tooth9.x = this.BottomJaw.x;
        this.Tooth9.yRot = this.BottomJaw.yRot;
        this.Tooth16.z = this.BottomJaw.z;
        this.Tooth16.x = this.BottomJaw.x;
        this.Tooth16.yRot = this.BottomJaw.yRot;
        this.Tooth15.z = this.BottomJaw.z;
        this.Tooth15.x = this.BottomJaw.x;
        this.Tooth15.yRot = this.BottomJaw.yRot;
        this.Tooth14.z = this.BottomJaw.z;
        this.Tooth14.x = this.BottomJaw.x;
        this.Tooth14.yRot = this.BottomJaw.yRot;
        this.Tooth13.z = this.BottomJaw.z;
        this.Tooth13.x = this.BottomJaw.x;
        this.Tooth13.yRot = this.BottomJaw.yRot;
        this.Tooth12.z = this.BottomJaw.z;
        this.Tooth12.x = this.BottomJaw.x;
        this.Tooth12.yRot = this.BottomJaw.yRot;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (entity.get_is_activated() != 0) {
            
            if (entity.get_is_activated() > 1) {
                
            }
        }
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.BodyBack.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopBackLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyFront.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBase1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopFrontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopBackRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomBackRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopFrontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomBackLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomFrontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomFrontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyCenter.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BackLeftFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BackRightFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FrontLeftFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Toe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FrontRightFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinRidge7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinRidge6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinRidge5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinRidge4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinRidge3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinRidge2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinRidge1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.CenterRightNose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.CenterLeftNose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomNose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopNose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.JawTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.CenterMiddleNose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightEye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftEye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tooth9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomJaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

