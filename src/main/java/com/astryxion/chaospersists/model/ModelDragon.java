/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Dragon
 *  com.astryxion.chaospersists.ModelDragon
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.render.RenderInfo;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDragon extends EntityModel<Dragon> {
    private float wingspeed = 1.0f;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer tail6;
    ModelRenderer wing15;
    ModelRenderer spike1;
    ModelRenderer spike2;
    ModelRenderer spike3;
    ModelRenderer spike4;
    ModelRenderer wing14;
    ModelRenderer spike5;
    ModelRenderer spike6;
    ModelRenderer spike7;
    ModelRenderer spike8;
    ModelRenderer spike9;
    ModelRenderer spike10;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer body2;
    ModelRenderer neck1;
    ModelRenderer body3;
    ModelRenderer neck2;
    ModelRenderer neck3;
    ModelRenderer leg5;
    ModelRenderer leg6;
    ModelRenderer leg7;
    ModelRenderer leg9;
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer leg10;
    ModelRenderer leg11;
    ModelRenderer foot3;
    ModelRenderer foot4;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer mouth1;
    ModelRenderer mouth2;
    ModelRenderer tail5;
    ModelRenderer wing1;
    ModelRenderer wing2;
    ModelRenderer wing3;
    ModelRenderer wing4;
    ModelRenderer wing5;
    ModelRenderer wing6;
    ModelRenderer wing7;
    ModelRenderer wing8;
    ModelRenderer wing9;
    ModelRenderer wing10;
    ModelRenderer wing11;
    ModelRenderer wing12;
    ModelRenderer tail4;

    public ModelDragon(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 128;
        this.horn1 = new ModelRenderer(this, 0, 39);
        this.horn1.addBox(2.0f, -4.0f, 1.0f, 2, 2, 6);
        this.horn1.setPos(0.0f, 6.0f, -23.0f);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, 0.4089647f, 0.2602503f, 0.0f);
        this.horn2 = new ModelRenderer(this, 0, 39);
        this.horn2.addBox(-4.0f, -4.0f, 1.0f, 2, 2, 6);
        this.horn2.setPos(0.0f, 6.0f, -23.0f);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, 0.4089647f, -0.2602503f, 0.0f);
        this.tail6 = new ModelRenderer(this, 0, 49);
        this.tail6.addBox(-1.0f, 0.0f, -2.0f, 2, 6, 4);
        this.tail6.setPos(0.0f, 7.0f, 43.0f);
        this.tail6.mirror = true;
        this.setRotation(this.tail6, 1.570796f, 0.0f, 0.0f);
        this.wing15 = new ModelRenderer(this, 0, 62);
        this.wing15.addBox(1.0f, -1.0f, 1.0f, 12, 1, 8);
        this.wing15.setPos(4.0f, 3.0f, -5.0f);
        this.wing15.mirror = true;
        this.setRotation(this.wing15, -0.0743572f, -0.4089594f, 0.0f);
        this.spike1 = new ModelRenderer(this, 0, 73);
        this.spike1.addBox(-1.0f, -3.0f, -5.0f, 2, 2, 4);
        this.spike1.setPos(0.0f, 6.0f, -17.0f);
        this.spike1.mirror = true;
        this.setRotation(this.spike1, 0.0f, 0.0f, 0.0f);
        this.spike2 = new ModelRenderer(this, 0, 73);
        this.spike2.addBox(-1.0f, -3.0f, -5.0f, 2, 2, 4);
        this.spike2.setPos(0.0f, 6.0f, -11.0f);
        this.spike2.mirror = true;
        this.setRotation(this.spike2, 0.0f, 0.0f, 0.0f);
        this.spike3 = new ModelRenderer(this, 0, 73);
        this.spike3.addBox(-1.0f, -4.0f, 1.0f, 2, 2, 4);
        this.spike3.setPos(0.0f, 7.0f, 25.0f);
        this.spike3.mirror = true;
        this.setRotation(this.spike3, 0.0f, 0.0f, 0.0f);
        this.spike4 = new ModelRenderer(this, 0, 73);
        this.spike4.addBox(-1.0f, -2.0f, 0.0f, 2, 2, 4);
        this.spike4.setPos(0.0f, 3.0f, -7.0f);
        this.spike4.mirror = true;
        this.setRotation(this.spike4, 0.0f, 0.0f, 0.0f);
        this.wing14 = new ModelRenderer(this, 0, 62);
        this.wing14.addBox(-13.0f, -1.0f, 0.0f, 12, 1, 8);
        this.wing14.setPos(-4.0f, 3.0f, -5.0f);
        this.wing14.mirror = true;
        this.setRotation(this.wing14, -0.0698132f, 0.4089656f, 0.0f);
        this.spike5 = new ModelRenderer(this, 0, 73);
        this.spike5.addBox(-1.0f, -2.0f, 0.0f, 2, 2, 4);
        this.spike5.setPos(0.0f, 3.0f, -1.0f);
        this.spike5.mirror = true;
        this.setRotation(this.spike5, 0.0f, 0.0f, 0.0f);
        this.spike6 = new ModelRenderer(this, 0, 73);
        this.spike6.addBox(-1.0f, -2.0f, 0.0f, 2, 2, 4);
        this.spike6.setPos(0.0f, 3.0f, 5.0f);
        this.spike6.mirror = true;
        this.setRotation(this.spike6, 0.0f, 0.0f, 0.0f);
        this.spike7 = new ModelRenderer(this, 0, 73);
        this.spike7.addBox(-1.0f, -4.0f, 1.0f, 2, 2, 4);
        this.spike7.setPos(0.0f, 7.0f, 13.0f);
        this.spike7.mirror = true;
        this.setRotation(this.spike7, 0.0f, 0.0f, 0.0f);
        this.spike8 = new ModelRenderer(this, 0, 73);
        this.spike8.addBox(-1.0f, -2.0f, 1.0f, 2, 2, 4);
        this.spike8.setPos(0.0f, 5.0f, 19.0f);
        this.spike8.mirror = true;
        this.setRotation(this.spike8, 0.0f, 0.0f, 0.0f);
        this.spike9 = new ModelRenderer(this, 0, 73);
        this.spike9.addBox(-1.0f, -4.0f, 1.0f, 2, 2, 4);
        this.spike9.setPos(0.0f, 7.0f, 31.0f);
        this.spike9.mirror = true;
        this.setRotation(this.spike9, 0.0f, 0.0f, 0.0f);
        this.spike10 = new ModelRenderer(this, 0, 73);
        this.spike10.addBox(-1.0f, -4.0f, 2.0f, 2, 2, 4);
        this.spike10.setPos(0.0f, 7.0f, 36.0f);
        this.spike10.mirror = true;
        this.setRotation(this.spike10, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 81);
        this.head.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
        this.head.setPos(0.0f, 6.0f, -23.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 1, 99);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
        this.body.setPos(0.0f, 5.0f, 2.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 1.570796f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer(this, 47, 112);
        this.leg1.addBox(-4.0f, -1.0f, -2.0f, 4, 9, 5);
        this.leg1.setPos(-4.0f, 11.0f, 8.0f);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, -0.6320364f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer(this, 47, 112);
        this.leg2.addBox(1.0f, -1.0f, -2.0f, 4, 9, 5);
        this.leg2.setPos(3.0f, 11.0f, 8.0f);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, -0.6320364f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer(this, 18, 47);
        this.leg3.addBox(-3.0f, -2.0f, -2.0f, 4, 9, 4);
        this.leg3.setPos(-4.0f, 11.0f, -5.0f);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.5576792f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer(this, 18, 47);
        this.leg4.addBox(0.0f, -2.0f, -2.0f, 4, 9, 4);
        this.leg4.setPos(3.0f, 11.0f, -5.0f);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.5576792f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 68, 94);
        this.body2.addBox(-5.0f, 0.0f, 0.0f, 10, 22, 10);
        this.body2.setPos(0.0f, 12.0f, -10.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 1.570796f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 43, 85);
        this.neck1.addBox(-3.0f, -3.0f, 0.0f, 6, 6, 6);
        this.neck1.setPos(0.0f, 7.0f, 25.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer(this, 70, 59);
        this.body3.addBox(-4.0f, 0.0f, 0.0f, 8, 24, 8);
        this.body3.setPos(0.0f, 11.0f, -11.0f);
        this.body3.mirror = true;
        this.setRotation(this.body3, 1.570796f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 43, 85);
        this.neck2.addBox(-3.0f, -2.0f, -6.0f, 6, 6, 6);
        this.neck2.setPos(0.0f, 6.0f, -11.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, 0.0f, 0.0f, 0.0f);
        this.neck3 = new ModelRenderer(this, 43, 85);
        this.neck3.addBox(-3.0f, -2.0f, -6.0f, 6, 6, 6);
        this.neck3.setPos(0.0f, 6.0f, -17.0f);
        this.neck3.mirror = true;
        this.setRotation(this.neck3, 0.0f, 0.0f, 0.0f);
        this.leg5 = new ModelRenderer(this, 47, 99);
        this.leg5.addBox(0.0f, 3.0f, 3.0f, 4, 8, 4);
        this.leg5.setPos(3.0f, 11.0f, -5.0f);
        this.leg5.mirror = true;
        this.setRotation(this.leg5, -0.5576792f, 0.0f, 0.0f);
        this.leg6 = new ModelRenderer(this, 47, 99);
        this.leg6.addBox(-3.0f, 3.0f, 3.0f, 4, 8, 4);
        this.leg6.setPos(-4.0f, 11.0f, -5.0f);
        this.leg6.mirror = true;
        this.setRotation(this.leg6, -0.5576792f, 0.0f, 0.0f);
        this.leg7 = new ModelRenderer(this, 38, 73);
        this.leg7.addBox(1.0f, 2.0f, -8.0f, 4, 5, 4);
        this.leg7.setPos(3.0f, 11.0f, 8.0f);
        this.leg7.mirror = true;
        this.setRotation(this.leg7, 0.8922867f, 0.0f, 0.0f);
        this.leg9 = new ModelRenderer(this, 38, 73);
        this.leg9.addBox(-4.0f, 2.0f, -8.0f, 4, 5, 4);
        this.leg9.setPos(-4.0f, 11.0f, 8.0f);
        this.leg9.mirror = true;
        this.setRotation(this.leg9, 0.8922867f, 0.0f, 0.0f);
        this.foot1 = new ModelRenderer(this, 43, 63);
        this.foot1.addBox(-3.0f, 11.0f, -5.0f, 4, 2, 6);
        this.foot1.setPos(-4.0f, 11.0f, -5.0f);
        this.foot1.mirror = true;
        this.setRotation(this.foot1, 0.0f, 0.0f, 0.0f);
        this.foot2 = new ModelRenderer(this, 43, 63);
        this.foot2.addBox(0.0f, 11.0f, -5.0f, 4, 2, 6);
        this.foot2.setPos(3.0f, 11.0f, -5.0f);
        this.foot2.mirror = true;
        this.setRotation(this.foot2, 0.0f, 0.0f, 0.0f);
        this.leg10 = new ModelRenderer(this, 39, 52);
        this.leg10.addBox(1.0f, 6.0f, 2.0f, 4, 5, 4);
        this.leg10.setPos(3.0f, 11.0f, 8.0f);
        this.leg10.mirror = true;
        this.setRotation(this.leg10, -0.5576792f, 0.0f, 0.0f);
        this.leg11 = new ModelRenderer(this, 39, 52);
        this.leg11.addBox(-4.0f, 6.0f, 2.0f, 4, 5, 4);
        this.leg11.setPos(-4.0f, 11.0f, 8.0f);
        this.leg11.mirror = true;
        this.setRotation(this.leg11, -0.5576792f, 0.0f, 0.0f);
        this.foot3 = new ModelRenderer(this, 43, 63);
        this.foot3.addBox(1.0f, 11.0f, -7.0f, 4, 2, 6);
        this.foot3.setPos(3.0f, 11.0f, 8.0f);
        this.foot3.mirror = true;
        this.setRotation(this.foot3, 0.0f, 0.0f, 0.0f);
        this.foot4 = new ModelRenderer(this, 43, 63);
        this.foot4.addBox(-4.0f, 11.0f, -7.0f, 4, 2, 6);
        this.foot4.setPos(-4.0f, 11.0f, 8.0f);
        this.foot4.mirror = true;
        this.setRotation(this.foot4, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 43, 85);
        this.tail1.addBox(-3.0f, -3.0f, 0.0f, 6, 6, 6);
        this.tail1.setPos(0.0f, 7.0f, 13.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 43, 85);
        this.tail2.addBox(-3.0f, -3.0f, 0.0f, 6, 6, 6);
        this.tail2.setPos(0.0f, 7.0f, 19.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 56, 45);
        this.tail3.addBox(-3.0f, -3.0f, 0.0f, 4, 6, 6);
        this.tail3.setPos(1.0f, 7.0f, 31.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.0f, 0.0f, 0.0f);
        this.mouth1 = new ModelRenderer(this, 90, 22);
        this.mouth1.addBox(-3.0f, -1.0f, -15.0f, 6, 3, 8);
        this.mouth1.setPos(0.0f, 6.0f, -23.0f);
        this.mouth1.mirror = true;
        this.setRotation(this.mouth1, 0.0f, 0.0f, 0.0f);
        this.mouth2 = new ModelRenderer(this, 90, 6);
        this.mouth2.addBox(-2.0f, 1.0f, -5.0f, 4, 2, 8);
        this.mouth2.setPos(0.0f, 7.0f, -32.0f);
        this.mouth2.mirror = true;
        this.setRotation(this.mouth2, 0.0698132f, 0.0f, 0.0f);
        this.tail5 = new ModelRenderer(this, 87, 36);
        this.tail5.addBox(0.0f, 0.0f, -5.0f, 0, 11, 10);
        this.tail5.setPos(0.0f, 7.0f, 49.0f);
        this.tail5.mirror = true;
        this.setRotation(this.tail5, 1.570796f, 0.0f, 0.0f);
        this.wing1 = new ModelRenderer(this, 26, 40);
        this.wing1.addBox(0.0f, -1.0f, -1.0f, 11, 2, 2);
        this.wing1.setPos(4.0f, 3.0f, -5.0f);
        this.wing1.mirror = true;
        this.setRotation(this.wing1, 0.0f, -0.4833219f, 0.0f);
        this.wing2 = new ModelRenderer(this, 110, 88);
        this.wing2.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 36);
        this.wing2.setPos(19.0f, 3.0f, -23.0f);
        this.wing2.mirror = true;
        this.setRotation(this.wing2, 0.0f, 1.041001f, 0.0f);
        this.wing3 = new ModelRenderer(this, 109, 60);
        this.wing3.addBox(-1.0f, -1.0f, -24.0f, 2, 2, 24);
        this.wing3.setPos(12.0f, 3.0f, -1.0f);
        this.wing3.mirror = true;
        this.setRotation(this.wing3, -0.0090881f, -0.3497888f, 0.0f);
        this.wing4 = new ModelRenderer(this, 26, 40);
        this.wing4.addBox(-11.0f, -1.0f, -1.0f, 11, 2, 2);
        this.wing4.setPos(-4.0f, 3.0f, -5.0f);
        this.wing4.mirror = true;
        this.setRotation(this.wing4, 0.0f, 0.4833166f, 0.0f);
        this.wing5 = new ModelRenderer(this, 109, 60);
        this.wing5.addBox(-1.0f, -1.0f, -24.0f, 2, 2, 24);
        this.wing5.setPos(-12.0f, 3.0f, -1.0f);
        this.wing5.mirror = true;
        this.setRotation(this.wing5, -0.0090932f, 0.3323281f, 0.0f);
        this.wing6 = new ModelRenderer(this, 110, 88);
        this.wing6.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 36);
        this.wing6.setPos(-20.0f, 3.0f, -23.0f);
        this.wing6.mirror = true;
        this.setRotation(this.wing6, 0.0f, -1.041002f, 0.0f);
        this.wing7 = new ModelRenderer(this, 124, 21);
        this.wing7.addBox(-8.0f, 0.0f, 1.0f, 8, 1, 36);
        this.wing7.setPos(19.0f, 2.0f, -23.0f);
        this.wing7.mirror = true;
        this.setRotation(this.wing7, 0.0f, 1.041001f, 0.0f);
        this.wing8 = new ModelRenderer(this, 122, 10);
        this.wing8.addBox(-11.0f, -1.0f, 0.0f, 28, 1, 8);
        this.wing8.setPos(12.0f, 3.0f, -1.0f);
        this.wing8.mirror = true;
        this.setRotation(this.wing8, 0.002272f, 1.264073f, -0.0174533f);
        this.wing9 = new ModelRenderer(this, 0, 10);
        this.wing9.addBox(-25.0f, -1.0f, 7.0f, 18, 1, 26);
        this.wing9.setPos(19.0f, 3.0f, -23.0f);
        this.wing9.mirror = true;
        this.setRotation(this.wing9, 0.002272f, 1.264073f, 0.0f);
        this.wing10 = new ModelRenderer(this, 122, 10);
        this.wing10.addBox(-23.0f, -1.0f, 0.0f, 33, 1, 8);
        this.wing10.setPos(-12.0f, 3.0f, -1.0f);
        this.wing10.mirror = true;
        this.setRotation(this.wing10, -0.0022689f, -1.226894f, 0.0f);
        this.wing11 = new ModelRenderer(this, 124, 21);
        this.wing11.addBox(0.0f, -1.0f, 1.0f, 8, 1, 36);
        this.wing11.setPos(-20.0f, 3.0f, -23.0f);
        this.wing11.mirror = true;
        this.setRotation(this.wing11, 0.0f, -1.041002f, 0.0f);
        this.wing12 = new ModelRenderer(this, 0, 10);
        this.wing12.addBox(7.0f, -1.0f, 7.0f, 18, 1, 26);
        this.wing12.setPos(-20.0f, 3.0f, -23.0f);
        this.wing12.mirror = true;
        this.setRotation(this.wing12, 0.002272f, -1.264072f, 0.0f);
        this.tail4 = new ModelRenderer(this, 56, 45);
        this.tail4.addBox(-3.0f, -3.0f, 0.0f, 4, 6, 6);
        this.tail4.setPos(1.0f, 7.0f, 37.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Dragon e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;
        float newangle = 0.0f;
        float lspeed = 0.0f;
        RenderInfo r = null;
        float tailspeed = 0.76f;
        float tailamp = 0.45f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        r = e.getRenderInfo();
        if ((double)f1 > 0.001) {
            lspeed = (float)((e.xo - e.getX()) * (e.xo - e.getX()) + (e.zo - e.getZ()) * (e.zo - e.getZ()));
            lspeed = (float)Math.sqrt(lspeed);
            newangle = MathHelper.cos((float)(f2 * 1.25f * this.wingspeed)) * 3.1415927f * lspeed * 0.6f;
        } else {
            newangle = 0.0f;
        }
        if (e.getActivity() != 0) {
            newangle = 1.0f;
            this.leg4.xRot = 0.557f - newangle;
            this.leg5.xRot = -0.557f - newangle;
            this.foot2.xRot = - newangle;
            this.leg3.xRot = 0.557f - newangle;
            this.leg6.xRot = -0.557f - newangle;
            this.foot1.xRot = - newangle;
            this.leg2.xRot = -0.632f + newangle;
            this.leg7.xRot = 0.89f + newangle;
            this.leg10.xRot = -0.557f + newangle;
            this.foot3.xRot = newangle;
            this.leg1.xRot = -0.632f + newangle;
            this.leg9.xRot = 0.89f + newangle;
            this.leg11.xRot = -0.557f + newangle;
            this.foot4.xRot = newangle;
        } else {
            this.leg4.xRot = 0.557f + newangle;
            this.leg5.xRot = -0.557f + newangle;
            this.foot2.xRot = newangle;
            this.leg3.xRot = 0.557f - newangle;
            this.leg6.xRot = -0.557f - newangle;
            this.foot1.xRot = - newangle;
            this.leg2.xRot = -0.632f - newangle;
            this.leg7.xRot = 0.89f - newangle;
            this.leg10.xRot = -0.557f - newangle;
            this.foot3.xRot = - newangle;
            this.leg1.xRot = -0.632f + newangle;
            this.leg9.xRot = 0.89f + newangle;
            this.leg11.xRot = -0.557f + newangle;
            this.foot4.xRot = newangle;
        }
        newangle = e.getAttacking() != 0 ? (e.getActivity() != 0 ? MathHelper.cos((float)(f2 * 0.75f * this.wingspeed)) * 3.1415927f * 0.28f : -0.45f + MathHelper.cos((float)(f2 * 0.85f * this.wingspeed)) * 3.1415927f * 0.2f) : (e.getActivity() != 0 ? MathHelper.cos((float)(f2 * 0.75f * this.wingspeed)) * 3.1415927f * 0.28f : -0.85f + MathHelper.cos((float)(f2 * 0.2f * this.wingspeed)) * 3.1415927f * 0.028f);
        this.wing1.zRot = newangle;
        this.wing15.zRot = newangle;
        this.wing3.zRot = newangle * 4.0f / 3.0f;
        this.wing3.y = this.wing1.y + (float)Math.sin(this.wing1.zRot) * 7.0f;
        this.wing3.x = this.wing1.x + (float)Math.cos(this.wing1.zRot) * 7.0f;
        this.wing8.zRot = newangle * 4.0f / 3.0f;
        this.wing8.y = this.wing3.y;
        this.wing8.x = this.wing3.x;
        this.wing2.zRot = newangle * 3.0f / 2.0f;
        this.wing2.y = this.wing3.y + (float)Math.sin(this.wing3.zRot) * 6.0f;
        this.wing2.x = this.wing3.x + (float)Math.cos(this.wing3.zRot) * 6.0f;
        this.wing7.zRot = newangle * 3.0f / 2.0f;
        this.wing7.y = this.wing2.y;
        this.wing7.x = this.wing2.x;
        this.wing9.zRot = newangle * 3.0f / 2.0f;
        this.wing9.y = this.wing2.y;
        this.wing9.x = this.wing2.x;
        this.wing4.zRot = - newangle;
        this.wing14.zRot = - newangle;
        this.wing5.zRot = (- newangle) * 4.0f / 3.0f;
        this.wing5.y = this.wing4.y - (float)Math.sin(this.wing4.zRot) * 7.0f;
        this.wing5.x = this.wing4.x - (float)Math.cos(this.wing4.zRot) * 7.0f;
        this.wing10.zRot = (- newangle) * 4.0f / 3.0f;
        this.wing10.y = this.wing5.y;
        this.wing10.x = this.wing5.x;
        this.wing6.zRot = (- newangle) * 3.0f / 2.0f;
        this.wing6.y = this.wing5.y - (float)Math.sin(this.wing5.zRot) * 6.0f;
        this.wing6.x = this.wing5.x - (float)Math.cos(this.wing5.zRot) * 6.0f;
        this.wing11.zRot = (- newangle) * 3.0f / 2.0f;
        this.wing11.y = this.wing6.y;
        this.wing11.x = this.wing6.x;
        this.wing12.zRot = (- newangle) * 3.0f / 2.0f;
        this.wing12.y = this.wing6.y;
        this.wing12.x = this.wing6.x;
        if (e.getAttacking() != 0) {
            tailspeed = 0.96f;
            tailamp = 0.75f;
        }
        if (e.getActivity() == 0 && e.getAttacking() == 0) {
            tailspeed = 0.22f;
            tailamp = 0.22f;
        }
        if (e.isOrderedToSit()) {
            tailspeed = 0.0f;
            tailamp = 0.0f;
        }
        this.tail1.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * 0.04f;
        this.spike10.z = this.tail1.z;
        this.spike10.x = this.tail1.x;
        this.spike10.yRot = this.tail1.yRot;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 6.0f;
        this.tail2.x = this.tail1.x + (float)Math.sin(this.tail1.yRot) * 6.0f;
        this.tail2.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp * 0.125f;
        this.spike7.z = this.tail2.z;
        this.spike7.x = this.tail2.x;
        this.spike7.yRot = this.tail2.yRot;
        this.neck1.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 6.0f;
        this.neck1.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 6.0f;
        this.neck1.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp * 0.25f;
        this.spike8.z = this.neck1.z;
        this.spike8.x = this.neck1.x;
        this.spike8.yRot = this.neck1.yRot;
        this.tail3.z = this.neck1.z + (float)Math.cos(this.neck1.yRot) * 6.0f;
        this.tail3.x = this.neck1.x + 1.0f + (float)Math.sin(this.neck1.yRot) * 6.0f;
        this.tail3.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp * 0.375f;
        this.spike3.z = this.tail3.z;
        this.spike3.x = this.tail3.x - 1.0f;
        this.spike3.yRot = this.tail3.yRot;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 6.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 6.0f;
        this.tail4.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp * 0.5f;
        this.spike9.z = this.tail4.z;
        this.spike9.x = this.tail4.x - 1.0f;
        this.spike9.yRot = this.tail4.yRot;
        this.tail6.z = this.tail4.z + (float)Math.cos(this.tail4.yRot) * 6.0f;
        this.tail6.x = this.tail4.x - 1.0f + (float)Math.sin(this.tail4.yRot) * 6.0f;
        this.tail6.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp * 0.625f;
        this.tail5.z = this.tail6.z + (float)Math.cos(this.tail6.yRot) * 6.0f;
        this.tail5.x = this.tail6.x - 0.5f + (float)Math.sin(this.tail6.yRot) * 6.0f;
        this.tail5.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp * 0.75f;
        if (e.getActivity() == 1) {
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
        } else {
            f3 /= 2.0f;
        }
        this.spike2.yRot = this.neck2.yRot = (float)Math.toRadians(f3) * 0.25f;
        this.neck3.z = this.neck2.z - (float)Math.cos(this.neck2.yRot) * 6.0f;
        this.neck3.x = this.neck2.x - (float)Math.sin(this.neck2.yRot) * 6.0f;
        this.neck3.yRot = (float)Math.toRadians(f3) * 0.5f;
        this.spike1.z = this.neck3.z;
        this.spike1.x = this.neck3.x;
        this.spike1.yRot = this.neck3.yRot;
        this.head.z = this.neck3.z - (float)Math.cos(this.neck3.yRot) * 6.0f;
        this.head.x = this.neck3.x - (float)Math.sin(this.neck3.yRot) * 6.0f;
        this.head.yRot = (float)Math.toRadians(f3) * 0.75f;
        this.mouth1.z = this.head.z;
        this.mouth1.x = this.head.x;
        this.mouth1.yRot = this.head.yRot;
        this.horn1.z = this.head.z;
        this.horn1.x = this.head.x;
        this.horn1.yRot = this.head.yRot + 0.26f;
        this.horn2.z = this.head.z;
        this.horn2.x = this.head.x;
        this.horn2.yRot = this.head.yRot - 0.26f;
        this.mouth2.z = this.head.z - (float)Math.cos(this.head.yRot) * 9.0f;
        this.mouth2.x = this.head.x - (float)Math.sin(this.head.yRot) * 9.0f;
        this.mouth2.yRot = this.head.yRot;
        newangle = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.14f;
        this.mouth2.xRot = e.getAttacking() != 0 ? 0.4f + newangle : 0.07f;
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.horn1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

