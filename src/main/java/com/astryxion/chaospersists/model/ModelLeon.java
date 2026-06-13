/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Leon
 *  com.astryxion.chaospersists.ModelLeon
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.render.RenderInfo;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelLeon extends EntityModel<Leon> {
    private float wingspeed = 1.0f;
    ModelRenderer chest;
    ModelRenderer neck_1;
    ModelRenderer neck_2;
    ModelRenderer neck_3;
    ModelRenderer abdomen;
    ModelRenderer head;
    ModelRenderer upper_jaw;
    ModelRenderer bottom_jaw;
    ModelRenderer chest_ridge;
    ModelRenderer upper_sail_1;
    ModelRenderer upper_sail2_;
    ModelRenderer upper_sail3;
    ModelRenderer lower_sail1;
    ModelRenderer lower_sail2;
    ModelRenderer lower_sail_3;
    ModelRenderer eye_ridge_L;
    ModelRenderer eye_ridge_R;
    ModelRenderer anntena_1_L;
    ModelRenderer anntena_1_R;
    ModelRenderer anntena_2_L;
    ModelRenderer anntena_2_R;
    ModelRenderer arm_1_L;
    ModelRenderer arm_2_L;
    ModelRenderer wing_1_L;
    ModelRenderer wing_2_L;
    ModelRenderer arm_1_R;
    ModelRenderer arm_2_R;
    ModelRenderer wing_1_R;
    ModelRenderer wing_2_R;
    ModelRenderer leg_1_L;
    ModelRenderer leg_1_R;
    ModelRenderer leg_2_L;
    ModelRenderer leg_2_R;
    ModelRenderer footL;
    ModelRenderer footR;
    ModelRenderer wing_3_L;
    ModelRenderer wing_3_R;
    ModelRenderer wing_4_L;
    ModelRenderer wing_4_R;
    ModelRenderer claw_L;
    ModelRenderer claw_R;
    ModelRenderer claw_L2;
    ModelRenderer claw_R_2;
    ModelRenderer wing_5_L;
    ModelRenderer wing_6_L;
    ModelRenderer wing_7_L;
    ModelRenderer wing_5_R;
    ModelRenderer wing_6_R;
    ModelRenderer wing_7_R;
    ModelRenderer fchest;
    ModelRenderer fneck_1;
    ModelRenderer fneck_2;
    ModelRenderer fneck_3;
    ModelRenderer fabdomen;
    ModelRenderer fhead;
    ModelRenderer fupper_jaw;
    ModelRenderer fbottom_jaw;
    ModelRenderer fchest_ridge;
    ModelRenderer fupper_sail_1;
    ModelRenderer fupper_sail2_;
    ModelRenderer fupper_sail3;
    ModelRenderer flower_sail1;
    ModelRenderer flower_sail2;
    ModelRenderer flower_sail_3;
    ModelRenderer feye_ridge_L;
    ModelRenderer feye_ridge_R;
    ModelRenderer fanntena_1_L;
    ModelRenderer fanntena_1_R;
    ModelRenderer fanntena_2_L;
    ModelRenderer fanntena_2_R;
    ModelRenderer farm_1_L;
    ModelRenderer farm_2_L;
    ModelRenderer fwing_1_L;
    ModelRenderer fwing_2_L;
    ModelRenderer farm_1_R;
    ModelRenderer farm_2_R;
    ModelRenderer fwing_1_R;
    ModelRenderer fwing_2_R;
    ModelRenderer fleg_1_L;
    ModelRenderer fleg_1_R;
    ModelRenderer fleg_2_L;
    ModelRenderer fleg_2_R;
    ModelRenderer ffootL;
    ModelRenderer ffootR;
    ModelRenderer fwing_3_L;
    ModelRenderer fwing_3_R;
    ModelRenderer fwing_4_L;
    ModelRenderer fwing_4_R;
    ModelRenderer fclaw_L;
    ModelRenderer fclaw_R;
    ModelRenderer fclaw_L2;
    ModelRenderer fclaw_R_2;
    ModelRenderer fwing_5_L;
    ModelRenderer fwing_6_L;
    ModelRenderer fwing_7_L;
    ModelRenderer fwing_5_R;
    ModelRenderer fwing_6_R;
    ModelRenderer fwing_7_R;

    public ModelLeon(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 256;
        this.chest = new ModelRenderer(this, 80, 0);
        this.chest.addBox(-8.0f, -9.5f, -9.5f, 16, 19, 19);
        this.chest.setPos(0.0f, -2.0f, -7.0f);
        this.chest.mirror = true;
        this.setRotation(this.chest, -0.4363323f, 0.0f, 0.0f);
        this.neck_1 = new ModelRenderer(this, 106, 68);
        this.neck_1.addBox(-5.5f, -7.0f, -9.0f, 11, 14, 11);
        this.neck_1.setPos(0.0f, -6.0f, -13.0f);
        this.neck_1.mirror = true;
        this.setRotation(this.neck_1, -0.8726646f, 0.0f, 0.0f);
        this.neck_2 = new ModelRenderer(this, 71, 69);
        this.neck_2.addBox(-4.0f, -5.0f, -8.0f, 8, 10, 9);
        this.neck_2.setPos(0.0f, -12.0f, -17.0f);
        this.neck_2.mirror = true;
        this.setRotation(this.neck_2, -1.064651f, 0.0f, 0.0f);
        this.neck_3 = new ModelRenderer(this, 102, 94);
        this.neck_3.addBox(-3.0f, -4.0f, -17.0f, 6, 8, 18);
        this.neck_3.setPos(0.0f, -19.0f, -21.0f);
        this.neck_3.mirror = true;
        this.setRotation(this.neck_3, -1.029744f, 0.0f, 0.0f);
        this.abdomen = new ModelRenderer(this, 96, 39);
        this.abdomen.addBox(-5.0f, -2.0f, 1.0f, 10, 11, 17);
        this.abdomen.setPos(0.0f, -5.0f, 4.0f);
        this.abdomen.mirror = true;
        this.setRotation(this.abdomen, -0.6457718f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 61, 49);
        this.head.addBox(-4.0f, -2.0f, -4.0f, 8, 8, 9);
        this.head.setPos(0.0f, -32.0f, -29.0f);
        this.head.mirror = true;
        this.setRotation(this.head, -1.413717f, 0.0f, 0.0f);
        this.upper_jaw = new ModelRenderer(this, 83, 89);
        this.upper_jaw.addBox(-3.0f, 4.0f, -4.0f, 6, 13, 5);
        this.upper_jaw.setPos(0.0f, -32.0f, -29.0f);
        this.upper_jaw.mirror = true;
        this.setRotation(this.upper_jaw, -1.37881f, 0.0f, 0.0f);
        this.bottom_jaw = new ModelRenderer(this, 85, 108);
        this.bottom_jaw.addBox(-2.5f, -1.0f, -1.5f, 5, 12, 3);
        this.bottom_jaw.setPos(0.0f, -28.0f, -34.0f);
        this.bottom_jaw.mirror = true;
        this.setRotation(this.bottom_jaw, -1.413717f, 0.0f, 0.0f);
        this.chest_ridge = new ModelRenderer(this, 113, 129);
        this.chest_ridge.addBox(-2.0f, 7.0f, -3.0f, 4, 3, 17);
        this.chest_ridge.setPos(0.0f, -2.0f, -7.0f);
        this.chest_ridge.mirror = true;
        this.setRotation(this.chest_ridge, -0.6283185f, 0.0f, 0.0f);
        this.upper_sail_1 = new ModelRenderer(this, 76, 110);
        this.upper_sail_1.addBox(-1.0f, -17.0f, -16.0f, 2, 14, 2);
        this.upper_sail_1.setPos(0.0f, -32.0f, -29.0f);
        this.upper_sail_1.mirror = true;
        this.setRotation(this.upper_sail_1, 0.2443461f, 0.0f, 0.0f);
        this.upper_sail2_ = new ModelRenderer(this, 63, 110);
        this.upper_sail2_.addBox(-0.5f, -15.0f, -16.0f, 1, 12, 5);
        this.upper_sail2_.setPos(0.0f, -32.0f, -29.0f);
        this.upper_sail2_.mirror = true;
        this.setRotation(this.upper_sail2_, 0.1396263f, 0.0f, 0.0f);
        this.upper_sail3 = new ModelRenderer(this, 0, 82);
        this.upper_sail3.addBox(0.0f, -1.5f, -18.0f, 0, 9, 13);
        this.upper_sail3.setPos(0.0f, -32.0f, -29.0f);
        this.upper_sail3.mirror = true;
        this.setRotation(this.upper_sail3, -0.7504916f, 0.0f, 0.0f);
        this.lower_sail1 = new ModelRenderer(this, 0, 2);
        this.lower_sail1.addBox(-1.0f, 0.0f, -10.0f, 2, 11, 2);
        this.lower_sail1.setPos(0.0f, -28.0f, -34.0f);
        this.lower_sail1.mirror = true;
        this.setRotation(this.lower_sail1, 0.1919862f, 0.0f, 0.0f);
        this.lower_sail2 = new ModelRenderer(this, 52, 94);
        this.lower_sail2.addBox(-0.5f, 0.5f, -9.0f, 1, 9, 4);
        this.lower_sail2.setPos(0.0f, -28.0f, -34.0f);
        this.lower_sail2.mirror = true;
        this.setRotation(this.lower_sail2, 0.296706f, 0.0f, 0.0f);
        this.lower_sail_3 = new ModelRenderer(this, 66, 90);
        this.lower_sail_3.addBox(0.0f, 1.5f, -4.0f, 0, 9, 7);
        this.lower_sail_3.setPos(0.0f, -28.0f, -34.0f);
        this.lower_sail_3.mirror = true;
        this.setRotation(this.lower_sail_3, -0.4886922f, 0.0f, 0.0f);
        this.eye_ridge_L = new ModelRenderer(this, 0, 68);
        this.eye_ridge_L.addBox(0.0f, -4.0f, -5.0f, 5, 2, 11);
        this.eye_ridge_L.setPos(0.0f, -32.0f, -29.0f);
        this.eye_ridge_L.mirror = true;
        this.setRotation(this.eye_ridge_L, 0.2094395f, 0.5585054f, 0.2268928f);
        this.eye_ridge_R = new ModelRenderer(this, 0, 68);
        this.eye_ridge_R.addBox(-5.0f, -4.0f, -5.0f, 5, 2, 11);
        this.eye_ridge_R.setPos(0.0f, -32.0f, -29.0f);
        this.eye_ridge_R.mirror = true;
        this.setRotation(this.eye_ridge_R, 0.2094395f, -0.5585054f, -0.2268928f);
        this.anntena_1_L = new ModelRenderer(this, 0, 40);
        this.anntena_1_L.addBox(3.0f, -4.2f, 5.0f, 2, 2, 5);
        this.anntena_1_L.setPos(0.0f, -32.0f, -29.0f);
        this.anntena_1_L.mirror = true;
        this.setRotation(this.anntena_1_L, 0.2443461f, 0.3665191f, 0.2268928f);
        this.anntena_1_R = new ModelRenderer(this, 0, 40);
        this.anntena_1_R.addBox(-5.0f, -4.2f, 5.0f, 2, 2, 5);
        this.anntena_1_R.setPos(0.0f, -32.0f, -29.0f);
        this.anntena_1_R.mirror = true;
        this.setRotation(this.anntena_1_R, 0.2443461f, -0.3665191f, -0.2268928f);
        this.anntena_2_L = new ModelRenderer(this, 46, 91);
        this.anntena_2_L.addBox(5.0f, -6.0f, 7.0f, 1, 1, 17);
        this.anntena_2_L.setPos(0.0f, -32.0f, -29.0f);
        this.anntena_2_L.mirror = true;
        this.setRotation(this.anntena_2_L, 0.0698132f, 0.1396263f, 0.2268928f);
        this.anntena_2_R = new ModelRenderer(this, 46, 91);
        this.anntena_2_R.addBox(-6.0f, -6.0f, 7.0f, 1, 1, 17);
        this.anntena_2_R.setPos(0.0f, -32.0f, -29.0f);
        this.anntena_2_R.mirror = true;
        this.setRotation(this.anntena_2_R, 0.0698132f, -0.1396263f, -0.2268928f);
        this.arm_1_L = new ModelRenderer(this, 77, 150);
        this.arm_1_L.addBox(0.0f, -1.0f, -1.0f, 3, 18, 5);
        this.arm_1_L.setPos(8.0f, -8.0f, -10.0f);
        this.arm_1_L.mirror = true;
        this.setRotation(this.arm_1_L, -0.0698132f, 0.0f, -0.7679449f);
        this.arm_2_L = new ModelRenderer(this, 102, 150);
        this.arm_2_L.addBox(-0.5f, 0.0f, -1.0f, 2, 24, 3);
        this.arm_2_L.setPos(20.0f, 3.0f, -10.0f);
        this.arm_2_L.mirror = true;
        this.setRotation(this.arm_2_L, -0.4712389f, 0.0f, -0.4886922f);
        this.wing_1_L = new ModelRenderer(this, 0, 33);
        this.wing_1_L.addBox(1.5f, -1.0f, 3.0f, 1, 19, 15);
        this.wing_1_L.setPos(8.0f, -8.0f, -10.0f);
        this.wing_1_L.mirror = true;
        this.setRotation(this.wing_1_L, -0.1745329f, -0.1919862f, -0.7504916f);
        this.wing_2_L = new ModelRenderer(this, 33, 50);
        this.wing_2_L.addBox(0.0f, -1.0f, 1.0f, 1, 23, 17);
        this.wing_2_L.setPos(20.0f, 3.0f, -10.0f);
        this.wing_2_L.mirror = true;
        this.setRotation(this.wing_2_L, -0.5235988f, -0.0349066f, -0.4712389f);
        this.arm_1_R = new ModelRenderer(this, 77, 127);
        this.arm_1_R.addBox(-3.0f, -1.0f, -1.0f, 3, 18, 5);
        this.arm_1_R.setPos(-8.0f, -8.0f, -10.0f);
        this.arm_1_R.mirror = true;
        this.setRotation(this.arm_1_R, -0.0698132f, 0.0f, 0.7679449f);
        this.arm_2_R = new ModelRenderer(this, 102, 123);
        this.arm_2_R.addBox(-1.5f, 0.0f, -1.0f, 2, 24, 3);
        this.arm_2_R.setPos(-20.0f, 3.0f, -10.0f);
        this.arm_2_R.mirror = true;
        this.setRotation(this.arm_2_R, -0.4712389f, 0.0f, 0.4886922f);
        this.wing_1_R = new ModelRenderer(this, 24, 150);
        this.wing_1_R.addBox(-2.5f, -1.0f, 3.0f, 1, 19, 15);
        this.wing_1_R.setPos(-8.0f, -8.0f, -10.0f);
        this.wing_1_R.mirror = true;
        this.setRotation(this.wing_1_R, -0.1745329f, 0.1919862f, 0.7504916f);
        this.wing_2_R = new ModelRenderer(this, 150, 50);
        this.wing_2_R.addBox(-1.0f, -1.0f, 0.0f, 1, 23, 17);
        this.wing_2_R.setPos(-20.0f, 3.0f, -10.0f);
        this.wing_2_R.mirror = true;
        this.setRotation(this.wing_2_R, -0.5235988f, 0.0349066f, 0.4712389f);
        this.leg_1_L = new ModelRenderer(this, 0, 104);
        this.leg_1_L.addBox(0.0f, -3.0f, -4.0f, 3, 15, 7);
        this.leg_1_L.setPos(5.0f, 5.0f, 10.0f);
        this.leg_1_L.mirror = true;
        this.setRotation(this.leg_1_L, -0.6108652f, 0.0f, -0.3316126f);
        this.leg_1_R = new ModelRenderer(this, 0, 149);
        this.leg_1_R.addBox(-3.0f, -3.0f, -4.0f, 3, 15, 7);
        this.leg_1_R.setPos(-6.0f, 5.0f, 10.0f);
        this.leg_1_R.mirror = true;
        this.setRotation(this.leg_1_R, -0.6108652f, 0.0f, 0.3316126f);
        this.leg_2_L = new ModelRenderer(this, 21, 108);
        this.leg_2_L.addBox(1.0f, 0.0f, -3.0f, 2, 14, 4);
        this.leg_2_L.setPos(8.0f, 13.0f, 6.0f);
        this.leg_2_L.mirror = true;
        this.setRotation(this.leg_2_L, 0.6108652f, 0.0f, -0.1745329f);
        this.leg_2_R = new ModelRenderer(this, 21, 108);
        this.leg_2_R.addBox(-2.0f, 0.0f, -3.0f, 2, 14, 4);
        this.leg_2_R.setPos(-10.0f, 13.0f, 6.0f);
        this.leg_2_R.mirror = true;
        this.setRotation(this.leg_2_R, 0.6108652f, 0.0f, 0.1745329f);
        this.footL = new ModelRenderer(this, 50, 29);
        this.footL.addBox(-2.0f, -1.0f, -8.0f, 4, 2, 9);
        this.footL.setPos(12.0f, 24.0f, 11.0f);
        this.footL.mirror = true;
        this.setRotation(this.footL, 0.0f, 0.0f, 0.0f);
        this.footR = new ModelRenderer(this, 50, 29);
        this.footR.addBox(-1.0f, 1.0f, -8.0f, 4, 2, 9);
        this.footR.setPos(-14.0f, 22.0f, 11.0f);
        this.footR.mirror = true;
        this.setRotation(this.footR, 0.0f, 0.0f, 0.0f);
        this.wing_3_L = new ModelRenderer(this, 0, 0);
        this.wing_3_L.addBox(-7.5f, 0.0f, -5.0f, 16, 1, 26);
        this.wing_3_L.setPos(-5.0f, 0.0f, 12.0f);
        this.wing_3_L.mirror = true;
        this.setRotation(this.wing_3_L, -0.4886922f, -0.5235988f, 0.4014257f);
        this.wing_3_R = new ModelRenderer(this, 150, 0);
        this.wing_3_R.addBox(-8.5f, 0.0f, -5.0f, 16, 1, 26);
        this.wing_3_R.setPos(4.0f, 0.0f, 12.0f);
        this.wing_3_R.mirror = true;
        this.setRotation(this.wing_3_R, -0.4886922f, 0.5235988f, -0.4014257f);
        this.wing_4_L = new ModelRenderer(this, 8, 117);
        this.wing_4_L.addBox(-1.5f, -0.5f, -2.0f, 3, 1, 31);
        this.wing_4_L.setPos(6.0f, 6.0f, 24.0f);
        this.wing_4_L.mirror = true;
        this.setRotation(this.wing_4_L, -0.6283185f, -0.0174533f, 0.0f);
        this.wing_4_R = new ModelRenderer(this, 8, 117);
        this.wing_4_R.addBox(-1.5f, -0.5f, -2.0f, 3, 1, 31);
        this.wing_4_R.setPos(-7.0f, 6.0f, 24.0f);
        this.wing_4_R.mirror = true;
        this.setRotation(this.wing_4_R, -0.6283185f, 0.0174533f, 0.0f);
        this.claw_L = new ModelRenderer(this, 0, 129);
        this.claw_L.addBox(0.0f, -1.0f, -9.0f, 1, 2, 10);
        this.claw_L.setPos(30.0f, 23.0f, -20.0f);
        this.claw_L.mirror = true;
        this.setRotation(this.claw_L, 0.0f, 0.1570796f, 0.0f);
        this.claw_R = new ModelRenderer(this, 0, 129);
        this.claw_R.addBox(0.0f, -1.0f, -9.0f, 1, 2, 10);
        this.claw_R.setPos(-31.0f, 23.0f, -20.0f);
        this.claw_R.mirror = true;
        this.setRotation(this.claw_R, 0.0f, -0.1570796f, 0.0f);
        this.claw_L2 = new ModelRenderer(this, 18, 38);
        this.claw_L2.addBox(0.0f, -2.5f, -6.0f, 1, 2, 7);
        this.claw_L2.setPos(-30.0f, 23.0f, -28.0f);
        this.claw_L2.mirror = true;
        this.setRotation(this.claw_L2, 0.5061455f, -0.2792527f, 0.0f);
        this.claw_R_2 = new ModelRenderer(this, 18, 38);
        this.claw_R_2.addBox(-1.0f, -2.5f, -6.0f, 1, 2, 7);
        this.claw_R_2.setPos(30.0f, 23.0f, -28.0f);
        this.claw_R_2.mirror = true;
        this.setRotation(this.claw_R_2, 0.5061455f, 0.2792527f, 0.0f);
        this.wing_5_L = new ModelRenderer(this, 46, 10);
        this.wing_5_L.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.wing_5_L.setPos(31.0f, 21.0f, -19.0f);
        this.wing_5_L.mirror = true;
        this.setRotation(this.wing_5_L, 0.6806784f, 0.0523599f, -0.2792527f);
        this.wing_6_L = new ModelRenderer(this, 46, 10);
        this.wing_6_L.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.wing_6_L.setPos(31.0f, 21.0f, -19.0f);
        this.wing_6_L.mirror = true;
        this.setRotation(this.wing_6_L, 0.4537856f, 0.2443461f, -0.3665191f);
        this.wing_7_L = new ModelRenderer(this, 46, 10);
        this.wing_7_L.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.wing_7_L.setPos(-30.0f, 21.0f, -19.0f);
        this.wing_7_L.mirror = true;
        this.setRotation(this.wing_7_L, 0.1396263f, -0.3316126f, 0.4014257f);
        this.wing_5_R = new ModelRenderer(this, 46, 10);
        this.wing_5_R.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.wing_5_R.setPos(-30.0f, 21.0f, -19.0f);
        this.wing_5_R.mirror = true;
        this.setRotation(this.wing_5_R, 0.6806784f, -0.0523599f, 0.2792527f);
        this.wing_6_R = new ModelRenderer(this, 46, 10);
        this.wing_6_R.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.wing_6_R.setPos(-30.0f, 21.0f, -19.0f);
        this.wing_6_R.mirror = true;
        this.setRotation(this.wing_6_R, 0.4537856f, -0.2443461f, 0.3665191f);
        this.wing_7_R = new ModelRenderer(this, 46, 10);
        this.wing_7_R.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.wing_7_R.setPos(31.0f, 21.0f, -19.0f);
        this.wing_7_R.mirror = true;
        this.setRotation(this.wing_7_R, 0.1396263f, 0.3316126f, -0.4014257f);
        this.fchest = new ModelRenderer(this, 80, 0);
        this.fchest.addBox(-8.0f, -9.5f, -9.5f, 16, 19, 19);
        this.fchest.setPos(0.0f, -2.0f, -7.0f);
        this.fchest.mirror = true;
        this.setRotation(this.fchest, -0.4363323f, 0.0f, 0.0f);
        this.fneck_1 = new ModelRenderer(this, 106, 68);
        this.fneck_1.addBox(-5.5f, -7.0f, -9.0f, 11, 14, 11);
        this.fneck_1.setPos(0.0f, -6.0f, -13.0f);
        this.fneck_1.mirror = true;
        this.setRotation(this.fneck_1, -0.8726646f, 0.0f, 0.0f);
        this.fneck_2 = new ModelRenderer(this, 71, 69);
        this.fneck_2.addBox(-4.0f, -5.0f, -8.0f, 8, 10, 9);
        this.fneck_2.setPos(0.0f, -12.0f, -17.0f);
        this.fneck_2.mirror = true;
        this.setRotation(this.fneck_2, -1.064651f, 0.0f, 0.0f);
        this.fneck_3 = new ModelRenderer(this, 102, 94);
        this.fneck_3.addBox(-3.0f, -4.0f, -17.0f, 6, 8, 18);
        this.fneck_3.setPos(0.0f, -19.0f, -21.0f);
        this.fneck_3.mirror = true;
        this.setRotation(this.fneck_3, -1.029744f, 0.0f, 0.0f);
        this.fabdomen = new ModelRenderer(this, 96, 39);
        this.fabdomen.addBox(-5.0f, -2.0f, 1.0f, 10, 11, 17);
        this.fabdomen.setPos(0.0f, -5.0f, 4.0f);
        this.fabdomen.mirror = true;
        this.setRotation(this.fabdomen, -0.6457718f, 0.0f, 0.0f);
        this.fhead = new ModelRenderer(this, 61, 49);
        this.fhead.addBox(-4.0f, -2.0f, -4.0f, 8, 8, 9);
        this.fhead.setPos(0.0f, -32.0f, -29.0f);
        this.fhead.mirror = true;
        this.setRotation(this.fhead, -1.413717f, 0.0f, 0.0f);
        this.fupper_jaw = new ModelRenderer(this, 83, 89);
        this.fupper_jaw.addBox(-3.0f, 4.0f, -4.0f, 6, 13, 5);
        this.fupper_jaw.setPos(0.0f, -32.0f, -29.0f);
        this.fupper_jaw.mirror = true;
        this.setRotation(this.fupper_jaw, -1.37881f, 0.0f, 0.0f);
        this.fbottom_jaw = new ModelRenderer(this, 85, 108);
        this.fbottom_jaw.addBox(-2.5f, -1.0f, -1.5f, 5, 12, 3);
        this.fbottom_jaw.setPos(0.0f, -28.0f, -34.0f);
        this.fbottom_jaw.mirror = true;
        this.setRotation(this.fbottom_jaw, -1.413717f, 0.0f, 0.0f);
        this.fchest_ridge = new ModelRenderer(this, 113, 129);
        this.fchest_ridge.addBox(-2.0f, 7.0f, -3.0f, 4, 3, 17);
        this.fchest_ridge.setPos(0.0f, -2.0f, -7.0f);
        this.fchest_ridge.mirror = true;
        this.setRotation(this.fchest_ridge, -0.6283185f, 0.0f, 0.0f);
        this.fupper_sail_1 = new ModelRenderer(this, 76, 110);
        this.fupper_sail_1.addBox(-1.0f, -17.0f, -16.0f, 2, 14, 2);
        this.fupper_sail_1.setPos(0.0f, -32.0f, -29.0f);
        this.fupper_sail_1.mirror = true;
        this.setRotation(this.fupper_sail_1, 0.2443461f, 0.0f, 0.0f);
        this.fupper_sail2_ = new ModelRenderer(this, 63, 110);
        this.fupper_sail2_.addBox(-0.5f, -15.0f, -16.0f, 1, 12, 5);
        this.fupper_sail2_.setPos(0.0f, -32.0f, -29.0f);
        this.fupper_sail2_.mirror = true;
        this.setRotation(this.fupper_sail2_, 0.1396263f, 0.0f, 0.0f);
        this.fupper_sail3 = new ModelRenderer(this, 0, 82);
        this.fupper_sail3.addBox(0.0f, -1.5f, -18.0f, 0, 9, 13);
        this.fupper_sail3.setPos(0.0f, -32.0f, -29.0f);
        this.fupper_sail3.mirror = true;
        this.setRotation(this.fupper_sail3, -0.7504916f, 0.0f, 0.0f);
        this.flower_sail1 = new ModelRenderer(this, 0, 2);
        this.flower_sail1.addBox(-1.0f, 0.0f, -10.0f, 2, 11, 2);
        this.flower_sail1.setPos(0.0f, -28.0f, -34.0f);
        this.flower_sail1.mirror = true;
        this.setRotation(this.flower_sail1, 0.1919862f, 0.0f, 0.0f);
        this.flower_sail2 = new ModelRenderer(this, 52, 94);
        this.flower_sail2.addBox(-0.5f, 0.5f, -9.0f, 1, 9, 4);
        this.flower_sail2.setPos(0.0f, -28.0f, -34.0f);
        this.flower_sail2.mirror = true;
        this.setRotation(this.flower_sail2, 0.296706f, 0.0f, 0.0f);
        this.flower_sail_3 = new ModelRenderer(this, 66, 90);
        this.flower_sail_3.addBox(0.0f, 1.5f, -4.0f, 0, 9, 7);
        this.flower_sail_3.setPos(0.0f, -28.0f, -34.0f);
        this.flower_sail_3.mirror = true;
        this.setRotation(this.flower_sail_3, -0.4886922f, 0.0f, 0.0f);
        this.feye_ridge_L = new ModelRenderer(this, 0, 68);
        this.feye_ridge_L.addBox(0.0f, -4.0f, -5.0f, 5, 2, 11);
        this.feye_ridge_L.setPos(0.0f, -32.0f, -29.0f);
        this.feye_ridge_L.mirror = true;
        this.setRotation(this.feye_ridge_L, 0.2094395f, 0.5585054f, 0.2268928f);
        this.feye_ridge_R = new ModelRenderer(this, 0, 68);
        this.feye_ridge_R.addBox(-5.0f, -4.0f, -5.0f, 5, 2, 11);
        this.feye_ridge_R.setPos(0.0f, -32.0f, -29.0f);
        this.feye_ridge_R.mirror = true;
        this.setRotation(this.feye_ridge_R, 0.2094395f, -0.5585054f, -0.2268928f);
        this.fanntena_1_L = new ModelRenderer(this, 0, 40);
        this.fanntena_1_L.addBox(3.0f, -4.2f, 5.0f, 2, 2, 5);
        this.fanntena_1_L.setPos(0.0f, -32.0f, -29.0f);
        this.fanntena_1_L.mirror = true;
        this.setRotation(this.fanntena_1_L, 0.2443461f, 0.3665191f, 0.2268928f);
        this.fanntena_1_R = new ModelRenderer(this, 0, 40);
        this.fanntena_1_R.addBox(-5.0f, -4.2f, 5.0f, 2, 2, 5);
        this.fanntena_1_R.setPos(0.0f, -32.0f, -29.0f);
        this.fanntena_1_R.mirror = true;
        this.setRotation(this.fanntena_1_R, 0.2443461f, -0.3665191f, -0.2268928f);
        this.fanntena_2_L = new ModelRenderer(this, 46, 91);
        this.fanntena_2_L.addBox(5.0f, -6.0f, 7.0f, 1, 1, 17);
        this.fanntena_2_L.setPos(0.0f, -32.0f, -29.0f);
        this.fanntena_2_L.mirror = true;
        this.setRotation(this.fanntena_2_L, 0.0698132f, 0.1396263f, 0.2268928f);
        this.fanntena_2_R = new ModelRenderer(this, 46, 91);
        this.fanntena_2_R.addBox(-6.0f, -6.0f, 7.0f, 1, 1, 17);
        this.fanntena_2_R.setPos(0.0f, -32.0f, -29.0f);
        this.fanntena_2_R.mirror = true;
        this.setRotation(this.fanntena_2_R, 0.0698132f, -0.1396263f, -0.2268928f);
        this.farm_1_L = new ModelRenderer(this, 77, 150);
        this.farm_1_L.addBox(0.0f, -1.0f, -1.0f, 3, 18, 5);
        this.farm_1_L.setPos(8.0f, -8.0f, -10.0f);
        this.farm_1_L.mirror = true;
        this.setRotation(this.farm_1_L, -0.0698132f, 0.0f, -0.7679449f);
        this.farm_2_L = new ModelRenderer(this, 102, 150);
        this.farm_2_L.addBox(-0.5f, 0.0f, -1.0f, 2, 24, 3);
        this.farm_2_L.setPos(20.0f, 3.0f, -10.0f);
        this.farm_2_L.mirror = true;
        this.setRotation(this.farm_2_L, -0.4712389f, 0.0f, -0.4886922f);
        this.fwing_1_L = new ModelRenderer(this, 0, 33);
        this.fwing_1_L.addBox(1.5f, -1.0f, 3.0f, 1, 19, 15);
        this.fwing_1_L.setPos(8.0f, -8.0f, -10.0f);
        this.fwing_1_L.mirror = true;
        this.setRotation(this.fwing_1_L, -0.1745329f, -0.1919862f, -0.7504916f);
        this.fwing_2_L = new ModelRenderer(this, 33, 50);
        this.fwing_2_L.addBox(0.0f, -1.0f, 1.0f, 1, 23, 17);
        this.fwing_2_L.setPos(20.0f, 3.0f, -10.0f);
        this.fwing_2_L.mirror = true;
        this.setRotation(this.fwing_2_L, -0.5235988f, -0.0349066f, -0.4712389f);
        this.farm_1_R = new ModelRenderer(this, 77, 127);
        this.farm_1_R.addBox(-3.0f, -1.0f, -1.0f, 3, 18, 5);
        this.farm_1_R.setPos(-8.0f, -8.0f, -10.0f);
        this.farm_1_R.mirror = true;
        this.setRotation(this.farm_1_R, -0.0698132f, 0.0f, 0.7679449f);
        this.farm_2_R = new ModelRenderer(this, 102, 123);
        this.farm_2_R.addBox(-1.5f, 0.0f, -1.0f, 2, 24, 3);
        this.farm_2_R.setPos(-20.0f, 3.0f, -10.0f);
        this.farm_2_R.mirror = true;
        this.setRotation(this.farm_2_R, -0.4712389f, 0.0f, 0.4886922f);
        this.fwing_1_R = new ModelRenderer(this, 24, 150);
        this.fwing_1_R.addBox(-2.5f, -1.0f, 3.0f, 1, 19, 15);
        this.fwing_1_R.setPos(-8.0f, -8.0f, -10.0f);
        this.fwing_1_R.mirror = true;
        this.setRotation(this.fwing_1_R, -0.1745329f, 0.1919862f, 0.7504916f);
        this.fwing_2_R = new ModelRenderer(this, 150, 50);
        this.fwing_2_R.addBox(-1.0f, -1.0f, 0.0f, 1, 23, 17);
        this.fwing_2_R.setPos(-20.0f, 3.0f, -10.0f);
        this.fwing_2_R.mirror = true;
        this.setRotation(this.fwing_2_R, -0.5235988f, 0.0349066f, 0.4712389f);
        this.fleg_1_L = new ModelRenderer(this, 0, 104);
        this.fleg_1_L.addBox(0.0f, -3.0f, -4.0f, 3, 15, 7);
        this.fleg_1_L.setPos(5.0f, 5.0f, 10.0f);
        this.fleg_1_L.mirror = true;
        this.setRotation(this.fleg_1_L, -0.6108652f, 0.0f, -0.3316126f);
        this.fleg_1_R = new ModelRenderer(this, 0, 149);
        this.fleg_1_R.addBox(-3.0f, -3.0f, -4.0f, 3, 15, 7);
        this.fleg_1_R.setPos(-6.0f, 5.0f, 10.0f);
        this.fleg_1_R.mirror = true;
        this.setRotation(this.fleg_1_R, -0.6108652f, 0.0f, 0.3316126f);
        this.fleg_2_L = new ModelRenderer(this, 21, 108);
        this.fleg_2_L.addBox(1.0f, 0.0f, -3.0f, 2, 14, 4);
        this.fleg_2_L.setPos(8.0f, 13.0f, 6.0f);
        this.fleg_2_L.mirror = true;
        this.setRotation(this.fleg_2_L, 0.6108652f, 0.0f, -0.1745329f);
        this.fleg_2_R = new ModelRenderer(this, 21, 108);
        this.fleg_2_R.addBox(-2.0f, 0.0f, -3.0f, 2, 14, 4);
        this.fleg_2_R.setPos(-10.0f, 13.0f, 6.0f);
        this.fleg_2_R.mirror = true;
        this.setRotation(this.fleg_2_R, 0.6108652f, 0.0f, 0.1745329f);
        this.ffootL = new ModelRenderer(this, 50, 29);
        this.ffootL.addBox(-2.0f, -1.0f, -8.0f, 4, 2, 9);
        this.ffootL.setPos(12.0f, 24.0f, 11.0f);
        this.ffootL.mirror = true;
        this.setRotation(this.ffootL, 0.0f, 0.0f, 0.0f);
        this.ffootR = new ModelRenderer(this, 50, 29);
        this.ffootR.addBox(-1.0f, 1.0f, -8.0f, 4, 2, 9);
        this.ffootR.setPos(-14.0f, 22.0f, 11.0f);
        this.ffootR.mirror = true;
        this.setRotation(this.ffootR, 0.0f, 0.0f, 0.0f);
        this.fwing_3_L = new ModelRenderer(this, 0, 0);
        this.fwing_3_L.addBox(-7.5f, 0.0f, -5.0f, 16, 1, 26);
        this.fwing_3_L.setPos(-5.0f, 0.0f, 12.0f);
        this.fwing_3_L.mirror = true;
        this.setRotation(this.fwing_3_L, -0.4886922f, -0.5235988f, 0.4014257f);
        this.fwing_3_R = new ModelRenderer(this, 150, 0);
        this.fwing_3_R.addBox(-8.5f, 0.0f, -5.0f, 16, 1, 26);
        this.fwing_3_R.setPos(4.0f, 0.0f, 12.0f);
        this.fwing_3_R.mirror = true;
        this.setRotation(this.fwing_3_R, -0.4886922f, 0.5235988f, -0.4014257f);
        this.fwing_4_L = new ModelRenderer(this, 8, 117);
        this.fwing_4_L.addBox(-1.5f, -0.5f, -2.0f, 3, 1, 31);
        this.fwing_4_L.setPos(6.0f, 6.0f, 24.0f);
        this.fwing_4_L.mirror = true;
        this.setRotation(this.fwing_4_L, -0.6283185f, -0.0174533f, 0.0f);
        this.fwing_4_R = new ModelRenderer(this, 8, 117);
        this.fwing_4_R.addBox(-1.5f, -0.5f, -2.0f, 3, 1, 31);
        this.fwing_4_R.setPos(-7.0f, 6.0f, 24.0f);
        this.fwing_4_R.mirror = true;
        this.setRotation(this.fwing_4_R, -0.6283185f, 0.0174533f, 0.0f);
        this.fclaw_L = new ModelRenderer(this, 0, 129);
        this.fclaw_L.addBox(0.0f, -1.0f, -9.0f, 1, 2, 10);
        this.fclaw_L.setPos(30.0f, 23.0f, -20.0f);
        this.fclaw_L.mirror = true;
        this.setRotation(this.fclaw_L, 0.0f, 0.1570796f, 0.0f);
        this.fclaw_R = new ModelRenderer(this, 0, 129);
        this.fclaw_R.addBox(0.0f, -1.0f, -9.0f, 1, 2, 10);
        this.fclaw_R.setPos(-31.0f, 23.0f, -20.0f);
        this.fclaw_R.mirror = true;
        this.setRotation(this.fclaw_R, 0.0f, -0.1570796f, 0.0f);
        this.fclaw_L2 = new ModelRenderer(this, 18, 38);
        this.fclaw_L2.addBox(0.0f, -2.5f, -6.0f, 1, 2, 7);
        this.fclaw_L2.setPos(-30.0f, 23.0f, -28.0f);
        this.fclaw_L2.mirror = true;
        this.setRotation(this.fclaw_L2, 0.5061455f, -0.2792527f, 0.0f);
        this.fclaw_R_2 = new ModelRenderer(this, 18, 38);
        this.fclaw_R_2.addBox(-1.0f, -2.5f, -6.0f, 1, 2, 7);
        this.fclaw_R_2.setPos(30.0f, 23.0f, -28.0f);
        this.fclaw_R_2.mirror = true;
        this.setRotation(this.fclaw_R_2, 0.5061455f, 0.2792527f, 0.0f);
        this.fwing_5_L = new ModelRenderer(this, 46, 10);
        this.fwing_5_L.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.fwing_5_L.setPos(31.0f, 21.0f, -19.0f);
        this.fwing_5_L.mirror = true;
        this.setRotation(this.fwing_5_L, 0.6806784f, 0.0523599f, -0.2792527f);
        this.fwing_6_L = new ModelRenderer(this, 46, 10);
        this.fwing_6_L.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.fwing_6_L.setPos(31.0f, 21.0f, -19.0f);
        this.fwing_6_L.mirror = true;
        this.setRotation(this.fwing_6_L, 0.4537856f, 0.2443461f, -0.3665191f);
        this.fwing_7_L = new ModelRenderer(this, 46, 10);
        this.fwing_7_L.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.fwing_7_L.setPos(-30.0f, 21.0f, -19.0f);
        this.fwing_7_L.mirror = true;
        this.setRotation(this.fwing_7_L, 0.1396263f, -0.3316126f, 0.4014257f);
        this.fwing_5_R = new ModelRenderer(this, 46, 10);
        this.fwing_5_R.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.fwing_5_R.setPos(-30.0f, 21.0f, -19.0f);
        this.fwing_5_R.mirror = true;
        this.setRotation(this.fwing_5_R, 0.6806784f, -0.0523599f, 0.2792527f);
        this.fwing_6_R = new ModelRenderer(this, 46, 10);
        this.fwing_6_R.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.fwing_6_R.setPos(-30.0f, 21.0f, -19.0f);
        this.fwing_6_R.mirror = true;
        this.setRotation(this.fwing_6_R, 0.4537856f, -0.2443461f, 0.3665191f);
        this.fwing_7_R = new ModelRenderer(this, 46, 10);
        this.fwing_7_R.addBox(-1.0f, -3.0f, -1.0f, 1, 8, 31);
        this.fwing_7_R.setPos(31.0f, 21.0f, -19.0f);
        this.fwing_7_R.mirror = true;
        this.setRotation(this.fwing_7_R, 0.1396263f, 0.3316126f, -0.4014257f);
    }
    @Override
    public void setupAnim(Leon e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        RenderInfo r = null;
        float newangle = 0.0f;
        float newangle2 = 0.0f;
        float newangle3 = 0.0f;
        float spd = 1.0f;
        float amp = 1.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        if ((double)f1 > 0.1) {
            newangle = MathHelper.cos((float)(f2 * 1.8f * this.wingspeed)) * 3.1415927f * 0.25f * f1;
            newangle2 = MathHelper.cos((float)(f2 * 0.9f * this.wingspeed)) * 3.1415927f * 0.25f * f1;
        } else {
            newangle = 0.0f;
            newangle2 = MathHelper.cos((float)(f2 * 0.9f * this.wingspeed)) * 3.1415927f * 0.02f;
            if (e.isOrderedToSit()) {
                newangle2 = 0.0f;
            }
        }
        if (e.getActivity() == 0) {
            this.leg_1_L.xRot = -0.611f + newangle;
            this.leg_1_R.xRot = -0.611f - newangle;
            this.leg_2_L.xRot = 0.611f + newangle;
            this.leg_2_L.z = (float)((double)this.leg_1_L.z + Math.sin(this.leg_1_L.xRot) * 9.0);
            this.leg_2_L.y = (float)((double)this.leg_1_L.y + Math.cos(this.leg_1_L.xRot) * 9.0);
            this.leg_2_R.xRot = 0.611f - newangle;
            this.leg_2_R.z = (float)((double)this.leg_1_R.z + Math.sin(this.leg_1_R.xRot) * 9.0);
            this.leg_2_R.y = (float)((double)this.leg_1_R.y + Math.cos(this.leg_1_R.xRot) * 9.0);
            this.footL.z = (float)((double)this.leg_2_L.z + Math.sin(this.leg_2_L.xRot) * 13.0);
            this.footL.y = (float)((double)this.leg_2_L.y + Math.cos(this.leg_2_L.xRot) * 13.0);
            this.footR.z = (float)((double)this.leg_2_R.z + Math.sin(this.leg_2_R.xRot) * 11.0);
            this.footR.y = (float)((double)this.leg_2_R.y + Math.cos(this.leg_2_R.xRot) * 11.0);
            this.wing_3_R.yRot = 0.523f - newangle / 10.0f;
            this.wing_3_L.yRot = -0.523f - newangle / 10.0f;
            this.arm_1_L.xRot = -0.07f - (newangle /= 2.0f);
            this.arm_1_R.xRot = -0.07f + newangle;
            this.wing_1_L.xRot = -0.17f - newangle;
            this.wing_1_R.xRot = -0.17f + newangle;
            this.arm_2_L.xRot = -0.471f - newangle;
            this.wing_2_L.xRot = -0.523f - newangle;
            this.arm_2_L.z = this.wing_2_L.z = (float)((double)this.arm_1_L.z + Math.sin(this.arm_1_L.xRot) * 11.0);
            this.arm_2_L.y = this.wing_2_L.y = (float)((double)this.arm_1_L.y + Math.cos(this.arm_1_L.xRot) * 11.0);
            this.wing_5_L.xRot = 0.68f + newangle2 / 2.0f;
            this.wing_6_L.xRot = 0.453f + newangle2 / 4.0f;
            this.wing_7_R.xRot = 0.119f + newangle2 / 8.0f;
            this.wing_5_L.z = (float)((double)this.arm_2_L.z + Math.sin(this.arm_2_L.xRot) * 20.0);
            this.wing_5_L.y = (float)((double)this.arm_2_L.y + Math.cos(this.arm_2_L.xRot) * 20.0);
            this.wing_6_L.z = this.wing_5_L.z;
            this.wing_6_L.y = this.wing_5_L.y;
            this.wing_7_R.z = this.wing_5_L.z;
            this.wing_7_R.y = this.wing_5_L.y;
            this.claw_L.z = this.wing_5_L.z - 1.0f;
            this.claw_R_2.z = this.wing_5_L.z - 9.0f;
            this.claw_L.y = this.wing_5_L.y + 2.0f;
            this.claw_R_2.y = this.wing_5_L.y + 2.0f;
            this.arm_2_R.xRot = -0.471f + newangle;
            this.wing_2_R.xRot = -0.523f + newangle;
            this.arm_2_R.z = this.wing_2_R.z = (float)((double)this.arm_1_R.z + Math.sin(this.arm_1_R.xRot) * 11.0);
            this.arm_2_R.y = this.wing_2_R.y = (float)((double)this.arm_1_R.y + Math.cos(this.arm_1_R.xRot) * 11.0);
            this.wing_5_R.xRot = 0.68f + newangle2 / 2.0f;
            this.wing_6_R.xRot = 0.453f + newangle2 / 4.0f;
            this.wing_7_L.xRot = 0.119f + newangle2 / 8.0f;
            this.wing_5_R.z = (float)((double)this.arm_2_R.z + Math.sin(this.arm_2_R.xRot) * 20.0);
            this.wing_5_R.y = (float)((double)this.arm_2_R.y + Math.cos(this.arm_2_R.xRot) * 20.0);
            this.wing_6_R.z = this.wing_5_R.z;
            this.wing_6_R.y = this.wing_5_R.y;
            this.wing_7_L.z = this.wing_5_R.z;
            this.wing_7_L.y = this.wing_5_R.y;
            this.claw_R.z = this.wing_5_R.z - 1.0f;
            this.claw_L2.z = this.wing_5_R.z - 9.0f;
            this.claw_R.y = this.wing_5_R.y + 2.0f;
            this.claw_L2.y = this.wing_5_R.y + 2.0f;
            newangle2 = MathHelper.cos((float)(f2 * 0.6f * this.wingspeed)) * 3.1415927f * 0.02f;
            this.chest_ridge.xRot = this.chest.xRot = -0.436f + newangle2 / 8.0f;
            this.bottom_jaw.xRot = -1.308f + newangle2 / 2.0f;
            this.lower_sail1.xRot = 0.297f + newangle2 / 2.0f;
            this.lower_sail2.xRot = 0.384f + newangle2 / 2.0f;
            this.lower_sail_3.xRot = -0.384f + newangle2 / 2.0f;
            this.upper_sail2_.yRot = this.upper_sail3.yRot = (newangle = (float)Math.toRadians(f3) * 0.5f);
            this.upper_sail_1.yRot = this.upper_sail3.yRot;
            this.upper_jaw.yRot = this.upper_sail3.yRot;
            this.head.yRot = this.upper_sail3.yRot;
            this.eye_ridge_L.yRot = 0.558f + newangle;
            this.anntena_1_L.yRot = 0.366f + newangle;
            this.anntena_2_L.yRot = 0.139f + newangle;
            this.eye_ridge_R.yRot = -0.558f + newangle;
            this.anntena_1_R.yRot = -0.366f + newangle;
            this.anntena_2_R.yRot = -0.139f + newangle;
            this.lower_sail2.yRot = this.lower_sail_3.yRot = newangle;
            this.lower_sail1.yRot = this.lower_sail_3.yRot;
            this.bottom_jaw.yRot = this.lower_sail_3.yRot;
            this.bottom_jaw.z = (float)((double)this.head.z - Math.cos(newangle) * 5.0);
            this.bottom_jaw.x = (float)((double)this.head.x - Math.sin(newangle) * 5.0);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        } else {
            if (e.getAttacking() != 0) {
                spd = 1.7f;
                amp = 1.4f;
            }
            newangle2 = MathHelper.cos((float)(f2 * 1.6f * this.wingspeed * spd)) * 3.1415927f * 0.06f;
            this.fchest.xRot = newangle2 / 8.0f;
            this.fchest_ridge.xRot = -0.18f + this.fchest.xRot;
            this.fchest.y = e.getBeingRidden() == 0 ? (float)(-2.0 + Math.sin(newangle2) * 10.0 * (double)amp) : -2.0f;
            this.fchest_ridge.y = this.fchest.y;
            this.fabdomen.xRot = 0.0f;
            this.fabdomen.z = (float)((double)this.fchest.z + Math.cos(this.fchest.xRot) * 8.0);
            this.fwing_3_R.y = this.fabdomen.y = (float)((double)this.fchest.y - Math.sin(this.fchest.xRot) * 8.0 - 6.0);
            this.fwing_3_L.y = this.fabdomen.y;
            this.fwing_3_R.zRot = 0.0f;
            this.fwing_3_R.xRot = 0.0f;
            this.fwing_3_L.zRot = 0.0f;
            this.fwing_3_L.xRot = 0.0f;
            this.fwing_3_R.yRot = 0.785f;
            this.fwing_3_L.yRot = -0.785f;
            this.fwing_4_R.y = this.fabdomen.y + 0.55f;
            this.fwing_4_L.y = this.fabdomen.y + 0.55f;
            this.fwing_4_R.z = this.fabdomen.z + 26.0f;
            this.fwing_4_L.z = this.fabdomen.z + 26.0f;
            this.fwing_4_R.x = this.fabdomen.z + 8.0f;
            this.fwing_4_L.x = this.fabdomen.z - 9.0f;
            this.fwing_4_R.xRot = newangle2 / 10.0f;
            this.fwing_4_L.xRot = (- newangle2) / 10.0f;
            if (e.getAttacking() == 0) {
                newangle = 1.5707964f;
                this.fleg_1_L.y = this.fabdomen.y + 5.0f;
                this.fleg_1_R.y = this.fabdomen.y + 5.0f;
                this.fleg_1_L.xRot = -0.1f + newangle;
                this.fleg_1_R.xRot = -0.1f + newangle;
                this.fleg_2_L.xRot = 0.1f + newangle;
                this.fleg_2_L.z = (float)((double)this.fleg_1_L.z + Math.sin(this.fleg_1_L.xRot) * 9.0);
                this.fleg_2_L.y = (float)((double)this.fleg_1_L.y + Math.cos(this.fleg_1_L.xRot) * 9.0);
                this.fleg_2_R.xRot = 0.1f + newangle;
                this.fleg_2_R.z = (float)((double)this.fleg_1_R.z + Math.sin(this.fleg_1_R.xRot) * 9.0);
                this.fleg_2_R.y = (float)((double)this.fleg_1_R.y + Math.cos(this.fleg_1_R.xRot) * 9.0);
                this.ffootL.z = (float)((double)this.fleg_2_L.z + Math.sin(this.fleg_2_L.xRot) * 13.0);
                this.ffootL.y = (float)((double)this.fleg_2_L.y + Math.cos(this.fleg_2_L.xRot) * 13.0);
                this.ffootR.z = (float)((double)this.fleg_2_R.z + Math.sin(this.fleg_2_R.xRot) * 11.0);
                this.ffootR.y = (float)((double)this.fleg_2_R.y + Math.cos(this.fleg_2_R.xRot) * 11.0);
                this.ffootL.xRot = 3.1415927f;
                this.ffootR.xRot = 3.1415927f;
                this.fleg_2_L.x = this.fleg_1_L.x;
                this.ffootL.x = this.fleg_1_L.x;
                this.fleg_2_R.x = this.fleg_1_R.x;
                this.ffootR.x = this.fleg_1_R.x;
            } else {
                newangle = -0.7853982f;
                newangle3 = MathHelper.cos((float)(f2 * 3.6f * this.wingspeed)) * 3.1415927f * 0.1f;
                this.fleg_1_L.y = this.fabdomen.y + 5.0f;
                this.fleg_1_R.y = this.fabdomen.y + 5.0f;
                this.fleg_1_L.xRot = -0.1f + newangle + newangle3;
                this.fleg_1_R.xRot = -0.1f + newangle - newangle3;
                this.fleg_2_L.xRot = 0.2f + newangle + newangle3 * 3.0f / 2.0f;
                this.fleg_2_L.z = (float)((double)this.fleg_1_L.z + Math.sin(this.fleg_1_L.xRot) * 9.0);
                this.fleg_2_L.y = (float)((double)this.fleg_1_L.y + Math.cos(this.fleg_1_L.xRot) * 9.0);
                this.fleg_2_R.xRot = 0.2f + newangle - newangle3 * 3.0f / 2.0f;
                this.fleg_2_R.z = (float)((double)this.fleg_1_R.z + Math.sin(this.fleg_1_R.xRot) * 9.0);
                this.fleg_2_R.y = (float)((double)this.fleg_1_R.y + Math.cos(this.fleg_1_R.xRot) * 9.0);
                this.ffootL.z = (float)((double)this.fleg_2_L.z + Math.sin(this.fleg_2_L.xRot) * 13.0);
                this.ffootL.y = (float)((double)this.fleg_2_L.y + Math.cos(this.fleg_2_L.xRot) * 13.0);
                this.ffootR.z = (float)((double)this.fleg_2_R.z + Math.sin(this.fleg_2_R.xRot) * 11.0);
                this.ffootR.y = (float)((double)this.fleg_2_R.y + Math.cos(this.fleg_2_R.xRot) * 11.0);
                this.ffootL.xRot = -0.7853982f + newangle3 * 2.0f;
                this.ffootR.xRot = -0.7853982f - newangle3 * 2.0f;
                this.fleg_2_L.x = 7.0f;
                this.ffootL.x = 11.0f;
                this.fleg_2_R.x = -9.0f;
                this.ffootR.x = -13.0f;
            }
            newangle = MathHelper.cos((float)(f2 * 1.6f * this.wingspeed * spd)) * 3.1415927f * 0.26f * amp;
            this.farm_1_L.zRot = (float)(-1.5707963267948966 - (double)newangle);
            this.farm_1_R.zRot = (float)(1.5707963267948966 + (double)newangle);
            this.fwing_1_L.zRot = (float)(-1.5707963267948966 - (double)newangle);
            this.fwing_1_R.zRot = (float)(1.5707963267948966 + (double)newangle);
            this.farm_2_L.zRot = (float)(-1.5707963267948966 - (double)(newangle * 1.3f));
            this.fwing_2_L.zRot = (float)(-1.5707963267948966 - (double)(newangle * 1.3f));
            this.farm_2_L.x = this.fwing_2_L.x = (float)((double)this.farm_1_L.x + Math.cos(newangle) * 14.0);
            this.farm_2_L.y = this.fwing_2_L.y = (float)((double)this.farm_1_L.y - Math.sin(newangle) * 14.0);
            this.fwing_5_L.x = (float)((double)this.farm_2_L.x + Math.cos(newangle * 1.3f) * 20.0);
            this.fwing_5_L.y = (float)((double)this.farm_2_L.y - Math.sin(newangle * 1.3f) * 20.0);
            this.fwing_6_L.x = this.fwing_5_L.x;
            this.fwing_6_L.y = this.fwing_5_L.y;
            this.fwing_7_R.x = this.fwing_5_L.x;
            this.fwing_7_R.y = this.fwing_5_L.y;
            this.fclaw_L.x = this.fwing_5_L.x;
            this.fclaw_R_2.x = this.fwing_5_L.x;
            this.fclaw_L.y = this.fwing_5_L.y;
            this.fclaw_R_2.y = this.fwing_5_L.y;
            this.fwing_5_L.zRot = (float)(-1.5707963267948966 - (double)(newangle * 1.65f));
            this.fwing_6_L.zRot = (float)(-1.5707963267948966 - (double)(newangle * 1.65f));
            this.fwing_7_R.zRot = (float)(-1.5707963267948966 - (double)(newangle * 1.65f));
            this.fwing_7_R.xRot = -1.5707964f;
            this.fwing_6_L.xRot = -1.1780972f;
            this.fwing_5_L.xRot = -0.7853982f;
            this.farm_2_R.zRot = (float)(1.5707963267948966 + (double)(newangle * 1.3f));
            this.fwing_2_R.zRot = (float)(1.5707963267948966 + (double)(newangle * 1.3f));
            this.farm_2_R.x = this.fwing_2_R.x = (float)((double)this.farm_1_R.x - Math.cos(newangle) * 14.0);
            this.farm_2_R.y = this.fwing_2_R.y = (float)((double)this.farm_1_R.y - Math.sin(newangle) * 14.0);
            this.fwing_5_R.x = (float)((double)this.farm_2_R.x - Math.cos(newangle * 1.3f) * 20.0);
            this.fwing_5_R.y = (float)((double)this.farm_2_R.y - Math.sin(newangle * 1.3f) * 20.0);
            this.fwing_6_R.x = this.fwing_5_R.x;
            this.fwing_6_R.y = this.fwing_5_R.y;
            this.fwing_7_L.x = this.fwing_5_R.x;
            this.fwing_7_L.y = this.fwing_5_R.y;
            this.fclaw_R.x = this.fwing_5_R.x;
            this.fclaw_L2.x = this.fwing_5_R.x;
            this.fclaw_R.y = this.fwing_5_R.y;
            this.fclaw_L2.y = this.fwing_5_R.y;
            this.fwing_5_R.zRot = (float)(1.5707963267948966 + (double)(newangle * 1.65f));
            this.fwing_6_R.zRot = (float)(1.5707963267948966 + (double)(newangle * 1.65f));
            this.fwing_7_L.zRot = (float)(1.5707963267948966 + (double)(newangle * 1.65f));
            this.fwing_7_L.xRot = -1.5707964f;
            this.fwing_6_R.xRot = -1.1780972f;
            this.fwing_5_R.xRot = -0.7853982f;
            this.fneck_1.xRot = (- newangle) / 12.0f;
            this.fneck_1.z = (float)((double)this.fchest.z - Math.cos(this.fchest.xRot) * 10.0);
            this.fneck_1.y = (float)((double)this.fchest.y + Math.sin(this.fchest.xRot) * 8.0 - 1.0);
            this.fneck_2.xRot = (- newangle) / 10.0f;
            this.fneck_2.z = (float)((double)this.fneck_1.z - Math.cos(this.fneck_1.xRot) * 7.0);
            this.fneck_2.y = (float)((double)this.fneck_1.y + Math.sin(this.fneck_1.xRot) * 6.0 - 1.0);
            this.fneck_3.xRot = (- newangle) / 8.0f;
            this.fneck_3.z = (float)((double)this.fneck_2.z - Math.cos(this.fneck_2.xRot) * 7.0);
            this.fneck_3.y = (float)((double)this.fneck_2.y + Math.sin(this.fneck_2.xRot) * 5.0);
            this.fhead.z = (float)((double)this.fneck_3.z - Math.cos(this.fneck_3.xRot) * 16.0);
            this.fhead.y = (float)((double)this.fneck_3.y + Math.sin(this.fneck_3.xRot) * 15.0);
            this.fupper_jaw.z = this.fhead.z;
            this.fupper_sail_1.z = this.fhead.z;
            this.fupper_sail2_.z = this.fhead.z;
            this.fupper_sail3.z = this.fhead.z;
            this.feye_ridge_L.z = this.fhead.z;
            this.fanntena_1_L.z = this.fhead.z;
            this.fanntena_2_L.z = this.fhead.z;
            this.feye_ridge_R.z = this.fhead.z;
            this.fanntena_1_R.z = this.fhead.z;
            this.fanntena_2_R.z = this.fhead.z;
            this.fbottom_jaw.z = this.fhead.z - 5.0f;
            this.flower_sail1.z = this.fhead.z - 5.0f;
            this.flower_sail2.z = this.fhead.z - 5.0f;
            this.flower_sail_3.z = this.fhead.z - 5.0f;
            this.fupper_jaw.y = this.fhead.y;
            this.fupper_sail_1.y = this.fhead.y;
            this.fupper_sail2_.y = this.fhead.y;
            this.fupper_sail3.y = this.fhead.y;
            this.feye_ridge_L.y = this.fhead.y;
            this.fanntena_1_L.y = this.fhead.y;
            this.fanntena_2_L.y = this.fhead.y;
            this.feye_ridge_R.y = this.fhead.y;
            this.fanntena_1_R.y = this.fhead.y;
            this.fanntena_2_R.y = this.fhead.y;
            this.fbottom_jaw.y = this.fhead.y + 4.0f;
            this.flower_sail1.y = this.fhead.y + 4.0f;
            this.flower_sail2.y = this.fhead.y + 4.0f;
            this.flower_sail_3.y = this.fhead.y + 4.0f;
            if (e.getBeingRidden() == 0) {
                newangle = (float)Math.toRadians(f3) * 0.5f;
            } else {
                r = e.getRenderInfo();
                f3 = (e.yRotO - e.yRot) * 8.0f;
                f3 = - f3;
                r.rf1 += (f3 - r.rf1) / 60.0f;
                if (r.rf1 > 50.0f) {
                    r.rf1 = 50.0f;
                }
                if (r.rf1 < -50.0f) {
                    r.rf1 = -50.0f;
                }
                f3 = r.rf1;
                e.setRenderInfo(r);
                newangle = (float)Math.toRadians(f3) * 0.5f;
            }
            this.fupper_sail2_.yRot = this.fupper_sail3.yRot = newangle;
            this.fupper_sail_1.yRot = this.fupper_sail3.yRot;
            this.fupper_jaw.yRot = this.fupper_sail3.yRot;
            this.fhead.yRot = this.fupper_sail3.yRot;
            this.feye_ridge_L.yRot = 0.558f + newangle;
            this.fanntena_1_L.yRot = 0.366f + newangle;
            this.fanntena_2_L.yRot = 0.139f + newangle;
            this.feye_ridge_R.yRot = -0.558f + newangle;
            this.fanntena_1_R.yRot = -0.366f + newangle;
            this.fanntena_2_R.yRot = -0.139f + newangle;
            this.flower_sail2.yRot = this.flower_sail_3.yRot = newangle;
            this.flower_sail1.yRot = this.flower_sail_3.yRot;
            this.fbottom_jaw.yRot = this.flower_sail_3.yRot;
            this.fbottom_jaw.z = (float)((double)this.fhead.z - Math.cos(newangle) * 5.0);
            this.fbottom_jaw.x = (float)((double)this.fhead.x - Math.sin(newangle) * 5.0);
            float tf1 = 1.605f;
            float tf2 = 1.6919999f;
            float tf3 = 0.92399997f;
            if (e.getAttacking() == 0) {
                this.fbottom_jaw.xRot = -1.308f + newangle2 / 2.0f;
            } else {
                newangle2 = MathHelper.cos((float)(f2 * 2.6f * this.wingspeed)) * 3.1415927f * 0.16f;
                this.fbottom_jaw.xRot = -0.9f + newangle2;
            }
            this.flower_sail1.xRot = this.fbottom_jaw.xRot + tf1;
            this.flower_sail2.xRot = this.fbottom_jaw.xRot + tf2;
            this.flower_sail_3.xRot = this.fbottom_jaw.xRot + tf3;
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upper_jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottom_jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest_ridge.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upper_sail_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upper_sail2_.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upper_sail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lower_sail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lower_sail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lower_sail_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eye_ridge_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eye_ridge_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.anntena_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.anntena_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.anntena_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.anntena_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.footL.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.footR.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_3_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_3_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_4_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_4_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.claw_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.claw_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.claw_L2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.claw_R_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_5_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_6_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_7_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_5_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_6_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_7_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fchest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fneck_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fneck_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fneck_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fabdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fhead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fupper_jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fbottom_jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fchest_ridge.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fupper_sail_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fupper_sail2_.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fupper_sail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.flower_sail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.flower_sail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.flower_sail_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feye_ridge_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feye_ridge_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanntena_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanntena_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanntena_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanntena_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.farm_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.farm_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.farm_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.farm_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fleg_1_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fleg_1_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fleg_2_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fleg_2_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ffootL.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ffootR.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_3_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_3_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_4_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_4_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fclaw_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fclaw_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fclaw_L2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fclaw_R_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_5_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_6_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_7_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_5_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_6_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fwing_7_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Leon par7Entity) {
        
    }
}

