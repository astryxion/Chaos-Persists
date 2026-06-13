/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelSpiderRobot
 *  com.astryxion.chaospersists.RenderSpiderRobotInfo
 *  com.astryxion.chaospersists.SpiderRobot
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.render.RenderSpiderRobotInfo;
import com.astryxion.chaospersists.entity.SpiderRobot;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelSpiderRobot extends EntityModel<SpiderRobot> {
    private float wingspeed = 1.0f;
    ModelRenderer Leg1p1;
    ModelRenderer Leg1p2;
    ModelRenderer Leg1p3;
    ModelRenderer Foot;
    ModelRenderer FootSpike1;
    ModelRenderer FootSpike2;
    ModelRenderer FootSpike3;
    ModelRenderer FootSpike4;
    ModelRenderer AnkleSpike1;
    ModelRenderer AnkleSpike2;
    ModelRenderer AnkleSpike3;
    ModelRenderer AnkleSpike4;
    ModelRenderer LowerKnee;
    ModelRenderer UpperKnee;
    ModelRenderer LegBump1;
    ModelRenderer LegBump2;
    ModelRenderer LowerKnee2;
    ModelRenderer UpperKnee2;
    ModelRenderer HipJoint;
    ModelRenderer BodyCenter;
    ModelRenderer Abdomen;
    ModelRenderer Head;
    ModelRenderer Ljaw1;
    ModelRenderer Rjaw1;
    ModelRenderer Ljaw2;
    ModelRenderer Rjaw2;
    ModelRenderer Ljaw3;
    ModelRenderer Rjaw3;
    ModelRenderer Tail;
    ModelRenderer HeadSpike1;
    ModelRenderer HeadSpike2;
    ModelRenderer Hip1;
    ModelRenderer Hip2;
    ModelRenderer Hip3;
    ModelRenderer Hip4;
    ModelRenderer Hip5;
    ModelRenderer Hip6;
    ModelRenderer Hip7;
    ModelRenderer Hip8;

    public ModelSpiderRobot(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 512;
        this.Leg1p1 = new ModelRenderer(this, 0, 149);
        this.Leg1p1.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 100);
        this.Leg1p1.setPos(0.0f, 0.0f, 0.0f);
        this.Leg1p1.mirror = true;
        this.setRotation(this.Leg1p1, 0.7853982f, 0.0f, 0.0f);
        this.Leg1p2 = new ModelRenderer(this, 0, 149);
        this.Leg1p2.addBox(-1.5f, -1.5f, 0.0f, 3, 3, 100);
        this.Leg1p2.setPos(0.0f, -70.0f, 70.0f);
        this.Leg1p2.mirror = true;
        this.setRotation(this.Leg1p2, 0.0f, 0.0f, 0.0f);
        this.Leg1p3 = new ModelRenderer(this, 0, 149);
        this.Leg1p3.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 100);
        this.Leg1p3.setPos(0.0f, -70.0f, 169.0f);
        this.Leg1p3.mirror = true;
        this.setRotation(this.Leg1p3, -0.7853982f, 0.0f, 0.0f);
        this.Foot = new ModelRenderer(this, 0, 28);
        this.Foot.addBox(-3.0f, -3.0f, 93.0f, 6, 6, 6);
        this.Foot.setPos(0.0f, -70.0f, 169.0f);
        this.Foot.mirror = true;
        this.setRotation(this.Foot, -0.7853982f, 0.0f, 0.0f);
        this.FootSpike1 = new ModelRenderer(this, 29, 27);
        this.FootSpike1.addBox(2.0f, 2.0f, 99.0f, 1, 1, 5);
        this.FootSpike1.setPos(0.0f, -70.0f, 169.0f);
        this.FootSpike1.mirror = true;
        this.setRotation(this.FootSpike1, -0.7853982f, 0.0f, 0.0f);
        this.FootSpike2 = new ModelRenderer(this, 29, 34);
        this.FootSpike2.addBox(-3.0f, 2.0f, 99.0f, 1, 1, 5);
        this.FootSpike2.setPos(0.0f, -70.0f, 169.0f);
        this.FootSpike2.mirror = true;
        this.setRotation(this.FootSpike2, -0.7853982f, 0.0f, 0.0f);
        this.FootSpike3 = new ModelRenderer(this, 43, 27);
        this.FootSpike3.addBox(2.0f, -3.0f, 99.0f, 1, 1, 5);
        this.FootSpike3.setPos(0.0f, -70.0f, 169.0f);
        this.FootSpike3.mirror = true;
        this.setRotation(this.FootSpike3, -0.7853982f, 0.0f, 0.0f);
        this.FootSpike4 = new ModelRenderer(this, 43, 34);
        this.FootSpike4.addBox(-3.0f, -3.0f, 99.0f, 1, 1, 5);
        this.FootSpike4.setPos(0.0f, -70.0f, 169.0f);
        this.FootSpike4.mirror = true;
        this.setRotation(this.FootSpike4, -0.7853982f, 0.0f, 0.0f);
        this.AnkleSpike1 = new ModelRenderer(this, 1, 42);
        this.AnkleSpike1.addBox(3.0f, -10.0f, 92.0f, 1, 20, 1);
        this.AnkleSpike1.setPos(0.0f, -70.0f, 169.0f);
        this.AnkleSpike1.mirror = true;
        this.setRotation(this.AnkleSpike1, -0.7853982f, 0.0f, 0.0f);
        this.AnkleSpike2 = new ModelRenderer(this, 7, 42);
        this.AnkleSpike2.addBox(-4.0f, -10.0f, 92.0f, 1, 20, 1);
        this.AnkleSpike2.setPos(0.0f, -70.0f, 169.0f);
        this.AnkleSpike2.mirror = true;
        this.setRotation(this.AnkleSpike2, -0.7853982f, 0.0f, 0.0f);
        this.AnkleSpike3 = new ModelRenderer(this, 14, 42);
        this.AnkleSpike3.addBox(-10.0f, 3.0f, 92.0f, 20, 1, 1);
        this.AnkleSpike3.setPos(0.0f, -70.0f, 169.0f);
        this.AnkleSpike3.mirror = true;
        this.setRotation(this.AnkleSpike3, -0.7853982f, 0.0f, 0.0f);
        this.AnkleSpike4 = new ModelRenderer(this, 14, 46);
        this.AnkleSpike4.addBox(-10.0f, -4.0f, 92.0f, 20, 1, 1);
        this.AnkleSpike4.setPos(0.0f, -70.0f, 169.0f);
        this.AnkleSpike4.mirror = true;
        this.setRotation(this.AnkleSpike4, -0.7853982f, 0.0f, 0.0f);
        this.LowerKnee = new ModelRenderer(this, 14, 49);
        this.LowerKnee.addBox(-1.5f, -1.5f, -1.0f, 3, 3, 15);
        this.LowerKnee.setPos(0.0f, -70.0f, 169.0f);
        this.LowerKnee.mirror = true;
        this.setRotation(this.LowerKnee, -0.7853982f, 0.0f, 0.0f);
        this.UpperKnee = new ModelRenderer(this, 0, 69);
        this.UpperKnee.addBox(-2.5f, -2.5f, 81.0f, 5, 5, 20);
        this.UpperKnee.setPos(0.0f, -70.0f, 70.0f);
        this.UpperKnee.mirror = true;
        this.setRotation(this.UpperKnee, 0.0f, 0.0f, 0.0f);
        this.LegBump1 = new ModelRenderer(this, 52, 50);
        this.LegBump1.addBox(-0.5f, -2.0f, 80.0f, 1, 1, 1);
        this.LegBump1.setPos(0.0f, -70.0f, 169.0f);
        this.LegBump1.mirror = true;
        this.setRotation(this.LegBump1, -0.7853982f, 0.0f, 0.0f);
        this.LegBump2 = new ModelRenderer(this, 52, 54);
        this.LegBump2.addBox(-0.5f, -2.0f, 70.0f, 1, 1, 1);
        this.LegBump2.setPos(0.0f, -70.0f, 169.0f);
        this.LegBump2.mirror = true;
        this.setRotation(this.LegBump2, -0.7853982f, 0.0f, 0.0f);
        this.LowerKnee2 = new ModelRenderer(this, 0, 96);
        this.LowerKnee2.addBox(-2.5f, -2.5f, -1.0f, 5, 5, 15);
        this.LowerKnee2.setPos(0.0f, -70.0f, 70.0f);
        this.LowerKnee2.mirror = true;
        this.setRotation(this.LowerKnee2, 0.0f, 0.0f, 0.0f);
        this.UpperKnee2 = new ModelRenderer(this, 0, 119);
        this.UpperKnee2.addBox(-3.0f, -3.0f, 81.0f, 6, 6, 20);
        this.UpperKnee2.setPos(0.0f, 0.0f, 0.0f);
        this.UpperKnee2.mirror = true;
        this.setRotation(this.UpperKnee2, 0.7853982f, 0.0f, 0.0f);
        this.HipJoint = new ModelRenderer(this, 0, 149);
        this.HipJoint.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 16);
        this.HipJoint.setPos(0.0f, 0.0f, 0.0f);
        this.HipJoint.mirror = true;
        this.setRotation(this.HipJoint, 0.7853982f, 0.0f, 0.0f);
        this.BodyCenter = new ModelRenderer(this, 0, 321);
        this.BodyCenter.addBox(-18.0f, -12.0f, -21.0f, 36, 24, 51);
        this.BodyCenter.setPos(0.0f, -4.0f, 0.0f);
        this.BodyCenter.mirror = true;
        this.setRotation(this.BodyCenter, 0.0f, 0.0f, 0.0f);
        this.Abdomen = new ModelRenderer(this, 0, 398);
        this.Abdomen.addBox(-24.0f, -30.0f, 29.0f, 48, 40, 73);
        this.Abdomen.setPos(0.0f, -4.0f, 0.0f);
        this.Abdomen.mirror = true;
        this.setRotation(this.Abdomen, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 0, 256);
        this.Head.addBox(-15.0f, -16.0f, -57.0f, 30, 26, 36);
        this.Head.setPos(0.0f, -4.0f, 0.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Ljaw1 = new ModelRenderer(this, 75, 26);
        this.Ljaw1.addBox(-4.0f, 0.0f, -4.0f, 8, 3, 8);
        this.Ljaw1.setPos(14.0f, -3.0f, -56.0f);
        this.Ljaw1.mirror = true;
        this.setRotation(this.Ljaw1, 0.0f, 0.0f, 0.0f);
        this.Rjaw1 = new ModelRenderer(this, 75, 26);
        this.Rjaw1.addBox(-4.0f, 0.0f, -4.0f, 8, 3, 8);
        this.Rjaw1.setPos(-14.0f, -3.0f, -56.0f);
        this.Rjaw1.mirror = true;
        this.setRotation(this.Rjaw1, 0.0f, 0.0f, 0.0f);
        this.Ljaw2 = new ModelRenderer(this, 63, 40);
        this.Ljaw2.addBox(0.0f, 1.0f, -3.0f, 21, 2, 6);
        this.Ljaw2.setPos(14.0f, -3.0f, -56.0f);
        this.Ljaw2.mirror = true;
        this.setRotation(this.Ljaw2, 0.0f, 0.7504916f, 0.0f);
        this.Rjaw2 = new ModelRenderer(this, 63, 40);
        this.Rjaw2.addBox(0.0f, 1.0f, -3.0f, 21, 2, 6);
        this.Rjaw2.setPos(-14.0f, -3.0f, -56.0f);
        this.Rjaw2.mirror = true;
        this.setRotation(this.Rjaw2, 0.0f, 2.303835f, 0.0f);
        this.Ljaw3 = new ModelRenderer(this, 0, 18);
        this.Ljaw3.addBox(11.0f, 2.0f, 14.0f, 23, 1, 4);
        this.Ljaw3.setPos(14.0f, -3.0f, -56.0f);
        this.Ljaw3.mirror = true;
        this.setRotation(this.Ljaw3, 0.0f, 1.710423f, 0.0f);
        this.Rjaw3 = new ModelRenderer(this, 0, 18);
        this.Rjaw3.addBox(11.0f, 2.0f, -17.0f, 23, 1, 4);
        this.Rjaw3.setPos(-14.0f, -3.0f, -56.0f);
        this.Rjaw3.mirror = true;
        this.setRotation(this.Rjaw3, 0.0f, 1.413717f, 0.0f);
        this.Tail = new ModelRenderer(this, 130, 0);
        this.Tail.addBox(-5.0f, -5.0f, -5.0f, 10, 10, 49);
        this.Tail.setPos(0.0f, -32.0f, 69.0f);
        this.Tail.mirror = true;
        this.setRotation(this.Tail, 0.0f, 0.0f, 0.0f);
        this.HeadSpike1 = new ModelRenderer(this, 74, 0);
        this.HeadSpike1.addBox(-1.0f, -1.0f, -10.0f, 2, 2, 21);
        this.HeadSpike1.setPos(6.0f, -20.0f, -60.0f);
        this.HeadSpike1.mirror = true;
        this.setRotation(this.HeadSpike1, 0.0f, 0.0f, 0.0f);
        this.HeadSpike2 = new ModelRenderer(this, 74, 0);
        this.HeadSpike2.addBox(-1.0f, -1.0f, -10.0f, 2, 2, 21);
        this.HeadSpike2.setPos(-6.0f, -20.0f, -60.0f);
        this.HeadSpike2.mirror = true;
        this.setRotation(this.HeadSpike2, 0.0f, 0.0f, 0.0f);
        this.Hip1 = new ModelRenderer(this, 70, 60);
        this.Hip1.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip1.setPos(22.0f, -3.0f, 44.0f);
        this.Hip1.mirror = true;
        this.setRotation(this.Hip1, 0.0f, 0.0f, 0.0f);
        this.Hip2 = new ModelRenderer(this, 70, 60);
        this.Hip2.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip2.setPos(-32.0f, -3.0f, 44.0f);
        this.Hip2.mirror = true;
        this.setRotation(this.Hip2, 0.0f, 0.0f, 0.0f);
        this.Hip3 = new ModelRenderer(this, 70, 60);
        this.Hip3.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip3.setPos(16.0f, -1.0f, 12.0f);
        this.Hip3.mirror = true;
        this.setRotation(this.Hip3, 0.0f, 0.0f, 0.0f);
        this.Hip4 = new ModelRenderer(this, 70, 60);
        this.Hip4.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip4.setPos(-26.0f, -1.0f, 12.0f);
        this.Hip4.mirror = true;
        this.setRotation(this.Hip4, 0.0f, 0.0f, 0.0f);
        this.Hip5 = new ModelRenderer(this, 70, 60);
        this.Hip5.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip5.setPos(16.0f, -1.0f, -11.0f);
        this.Hip5.mirror = true;
        this.setRotation(this.Hip5, 0.0f, 0.0f, 0.0f);
        this.Hip6 = new ModelRenderer(this, 70, 60);
        this.Hip6.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip6.setPos(-26.0f, -1.0f, -11.0f);
        this.Hip6.mirror = true;
        this.setRotation(this.Hip6, 0.0f, 0.0f, 0.0f);
        this.Hip7 = new ModelRenderer(this, 70, 60);
        this.Hip7.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip7.setPos(13.0f, -3.0f, -33.0f);
        this.Hip7.mirror = true;
        this.setRotation(this.Hip7, 0.0f, 0.0f, 0.0f);
        this.Hip8 = new ModelRenderer(this, 70, 60);
        this.Hip8.addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
        this.Hip8.setPos(-23.0f, -3.0f, -33.0f);
        this.Hip8.mirror = true;
        this.setRotation(this.Hip8, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(SpiderRobot e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        RenderSpiderRobotInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        r = e.getRenderSpiderRobotInfo();
        for (int i = 0; i < 8; ++i) {
            this.Leg1p2.yRot = this.Leg1p3.yRot = r.ydisplayangle[i];
            this.Leg1p1.yRot = this.Leg1p3.yRot;
            this.Foot.yRot = r.ydisplayangle[i];
            this.FootSpike1.yRot = r.ydisplayangle[i];
            this.FootSpike2.yRot = r.ydisplayangle[i];
            this.FootSpike3.yRot = r.ydisplayangle[i];
            this.FootSpike4.yRot = r.ydisplayangle[i];
            this.AnkleSpike1.yRot = r.ydisplayangle[i];
            this.AnkleSpike2.yRot = r.ydisplayangle[i];
            this.AnkleSpike3.yRot = r.ydisplayangle[i];
            this.AnkleSpike4.yRot = r.ydisplayangle[i];
            this.LowerKnee.yRot = r.ydisplayangle[i];
            this.UpperKnee.yRot = r.ydisplayangle[i];
            this.LegBump1.yRot = r.ydisplayangle[i];
            this.LegBump2.yRot = r.ydisplayangle[i];
            this.LowerKnee2.yRot = r.ydisplayangle[i];
            this.UpperKnee2.yRot = r.ydisplayangle[i];
            this.HipJoint.yRot = r.ydisplayangle[i];
            this.UpperKnee2.xRot = this.Leg1p1.xRot = (float)r.p1xangle[i] + r.uddisplayangle[i];
            this.HipJoint.xRot = this.Leg1p1.xRot;
            this.UpperKnee.xRot = this.Leg1p2.xRot = (float)r.p2xangle[i] + r.uddisplayangle[i];
            this.LowerKnee2.xRot = this.Leg1p2.xRot;
            this.Foot.xRot = this.Leg1p3.xRot = (float)r.p3xangle[i] + r.uddisplayangle[i];
            this.FootSpike1.xRot = this.Leg1p3.xRot;
            this.FootSpike2.xRot = this.Leg1p3.xRot;
            this.FootSpike3.xRot = this.Leg1p3.xRot;
            this.FootSpike4.xRot = this.Leg1p3.xRot;
            this.AnkleSpike1.xRot = this.Leg1p3.xRot;
            this.AnkleSpike2.xRot = this.Leg1p3.xRot;
            this.AnkleSpike3.xRot = this.Leg1p3.xRot;
            this.AnkleSpike4.xRot = this.Leg1p3.xRot;
            this.LegBump1.xRot = this.Leg1p3.xRot;
            this.LegBump2.xRot = this.Leg1p3.xRot;
            this.LowerKnee.xRot = this.Leg1p3.xRot;
            this.Leg1p1.x = (- (float)Math.cos(r.ymid[i])) * r.legoff[i] * 16.0f;
            this.Leg1p1.z = (float)Math.sin(r.ymid[i]) * r.legoff[i] * 16.0f;
            this.Leg1p1.y = r.yoff[i] * -16.0f;
            this.UpperKnee2.x = this.Leg1p1.x;
            this.UpperKnee2.y = this.Leg1p1.y;
            this.UpperKnee2.z = this.Leg1p1.z;
            this.HipJoint.x = this.Leg1p1.x;
            this.HipJoint.y = this.Leg1p1.y;
            this.HipJoint.z = this.Leg1p1.z;
            this.Leg1p2.y = this.Leg1p1.y - (float)Math.sin(this.Leg1p1.xRot) * 99.0f;
            this.Leg1p2.z = this.Leg1p1.z + (float)Math.cos(this.Leg1p1.xRot) * (float)Math.cos(this.Leg1p1.yRot) * 99.0f;
            this.UpperKnee.x = this.Leg1p2.x = this.Leg1p1.x + (float)Math.cos(this.Leg1p1.xRot) * (float)Math.sin(this.Leg1p1.yRot) * 99.0f;
            this.UpperKnee.y = this.Leg1p2.y;
            this.UpperKnee.z = this.Leg1p2.z;
            this.LowerKnee2.x = this.Leg1p2.x;
            this.LowerKnee2.y = this.Leg1p2.y;
            this.LowerKnee2.z = this.Leg1p2.z;
            this.Leg1p3.y = this.Leg1p2.y - (float)Math.sin(this.Leg1p2.xRot) * 99.0f;
            this.Leg1p3.z = this.Leg1p2.z + (float)Math.cos(this.Leg1p2.xRot) * (float)Math.cos(this.Leg1p2.yRot) * 99.0f;
            this.Foot.x = this.Leg1p3.x = this.Leg1p2.x + (float)Math.cos(this.Leg1p2.xRot) * (float)Math.sin(this.Leg1p2.yRot) * 99.0f;
            this.Foot.y = this.Leg1p3.y;
            this.Foot.z = this.Leg1p3.z;
            this.FootSpike1.x = this.Leg1p3.x;
            this.FootSpike1.y = this.Leg1p3.y;
            this.FootSpike1.z = this.Leg1p3.z;
            this.FootSpike2.x = this.Leg1p3.x;
            this.FootSpike2.y = this.Leg1p3.y;
            this.FootSpike2.z = this.Leg1p3.z;
            this.FootSpike3.x = this.Leg1p3.x;
            this.FootSpike3.y = this.Leg1p3.y;
            this.FootSpike3.z = this.Leg1p3.z;
            this.FootSpike4.x = this.Leg1p3.x;
            this.FootSpike4.y = this.Leg1p3.y;
            this.FootSpike4.z = this.Leg1p3.z;
            this.AnkleSpike1.x = this.Leg1p3.x;
            this.AnkleSpike1.y = this.Leg1p3.y;
            this.AnkleSpike1.z = this.Leg1p3.z;
            this.AnkleSpike2.x = this.Leg1p3.x;
            this.AnkleSpike2.y = this.Leg1p3.y;
            this.AnkleSpike2.z = this.Leg1p3.z;
            this.AnkleSpike3.x = this.Leg1p3.x;
            this.AnkleSpike3.y = this.Leg1p3.y;
            this.AnkleSpike3.z = this.Leg1p3.z;
            this.AnkleSpike4.x = this.Leg1p3.x;
            this.AnkleSpike4.y = this.Leg1p3.y;
            this.AnkleSpike4.z = this.Leg1p3.z;
            this.LegBump1.x = this.Leg1p3.x;
            this.LegBump1.y = this.Leg1p3.y;
            this.LegBump1.z = this.Leg1p3.z;
            this.LegBump2.x = this.Leg1p3.x;
            this.LegBump2.y = this.Leg1p3.y;
            this.LegBump2.z = this.Leg1p3.z;
            this.LowerKnee.x = this.Leg1p3.x;
            this.LowerKnee.y = this.Leg1p3.y;
            this.LowerKnee.z = this.Leg1p3.z;
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        }
        if (e.getAttacking() == 0) {
            this.Ljaw1.yRot = 0.0f;
            this.Ljaw2.yRot = 0.75f;
            this.Ljaw3.yRot = 1.71f;
            this.Rjaw1.yRot = 0.0f;
            this.Rjaw2.yRot = 2.3f;
            this.Rjaw3.yRot = 1.41f;
        } else {
            float newangle;
            this.Ljaw1.yRot = newangle = MathHelper.cos((float)((float)r.gpcounter * 0.25f)) * 3.1415927f * 0.22f;
            this.Ljaw2.yRot = newangle + 0.75f;
            this.Ljaw3.yRot = newangle + 1.71f;
            this.Rjaw1.yRot = - newangle;
            this.Rjaw2.yRot = 2.3f - newangle;
            this.Rjaw3.yRot = 1.41f - newangle;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Leg1p1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1p2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1p3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FootSpike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FootSpike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FootSpike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FootSpike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.AnkleSpike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.AnkleSpike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.AnkleSpike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.AnkleSpike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LowerKnee.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.UpperKnee.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegBump1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegBump2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LowerKnee2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.UpperKnee2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HipJoint.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyCenter.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Ljaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rjaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Ljaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rjaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Ljaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rjaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadSpike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadSpike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    private void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, SpiderRobot par7Entity) {
        
    }
}

