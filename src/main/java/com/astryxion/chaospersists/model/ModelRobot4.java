/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRobot4
 *  com.astryxion.chaospersists.Robot4
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Robot4;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;

import net.minecraft.util.math.MathHelper;

public class ModelRobot4 extends EntityModel<Robot4> {
    private float wingspeed = 1.0f;
    ModelRenderer leftfootfront;
    ModelRenderer leftfootbase;
    ModelRenderer leftfootback;
    ModelRenderer leftfoottip;
    ModelRenderer leftshin;
    ModelRenderer leftcalf;
    ModelRenderer leftkneegaurd;
    ModelRenderer leftthigh;
    ModelRenderer rightfootfront;
    ModelRenderer rightfoottip;
    ModelRenderer rightfootbase;
    ModelRenderer rightfootback;
    ModelRenderer rightshin;
    ModelRenderer rightcalf;
    ModelRenderer rightkneegaurd;
    ModelRenderer rightthigh;
    ModelRenderer hips;
    ModelRenderer stomach;
    ModelRenderer chest;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer righttopspinebase;
    ModelRenderer lefttopspinebase;
    ModelRenderer righttopspinetip;
    ModelRenderer lefttopspinetip;
    ModelRenderer middlerightspinebase;
    ModelRenderer middleleftspinebase;
    ModelRenderer middleleftspinetip;
    ModelRenderer middlerightspinetip;
    ModelRenderer torso;
    ModelRenderer rightsholder;
    ModelRenderer leftsholder;
    ModelRenderer rightsholdergaurd;
    ModelRenderer sheildbase;
    ModelRenderer sheildtip;
    ModelRenderer rightupperarm;
    ModelRenderer rightlowerarm;
    ModelRenderer sheildend;
    ModelRenderer leftupperarm;
    ModelRenderer sholdergaurdtip;
    ModelRenderer cannonbase;
    ModelRenderer cannonend;
    ModelRenderer leftcannonpiece;
    ModelRenderer topcannonpiece;
    ModelRenderer rightcannonpiece;
    ModelRenderer bottomcannonpiece;
    ModelRenderer glowycannonbit1;
    ModelRenderer glowycannonbit2;
    ModelRenderer glowycannonbit3;
    ModelRenderer glowycannonbit4;
    ModelRenderer glowycannonbit5;
    ModelRenderer cannonammo;
    ModelRenderer lowerrightspinebase;
    ModelRenderer lowerleftspinebase;
    ModelRenderer lowerrightspinetip;
    ModelRenderer lowerleftspinetip;

    public ModelRobot4(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 512;
        this.leftfootfront = new ModelRenderer(this, 20, 50);
        this.leftfootfront.addBox(-6.0f, 22.0f, -9.0f, 8, 5, 7);
        this.leftfootfront.setPos(-8.0f, -3.0f, 6.0f);
        this.leftfootfront.mirror = true;
        this.setRotation(this.leftfootfront, 0.0f, 0.0f, 0.0f);
        this.leftfootbase = new ModelRenderer(this, 20, 100);
        this.leftfootbase.addBox(-4.0f, 22.0f, -4.0f, 4, 5, 5);
        this.leftfootbase.setPos(-8.0f, -3.0f, 6.0f);
        this.leftfootbase.mirror = true;
        this.setRotation(this.leftfootbase, 0.0f, 0.0f, 0.0f);
        this.leftfootback = new ModelRenderer(this, 20, 150);
        this.leftfootback.addBox(-4.5f, 22.0f, 1.0f, 5, 5, 4);
        this.leftfootback.setPos(-8.0f, -3.0f, 6.0f);
        this.leftfootback.mirror = true;
        this.setRotation(this.leftfootback, 0.0f, 0.0f, 0.0f);
        this.leftfoottip = new ModelRenderer(this, 20, 200);
        this.leftfoottip.addBox(-4.5f, 23.0f, -12.0f, 5, 4, 3);
        this.leftfoottip.setPos(-8.0f, -3.0f, 6.0f);
        this.leftfoottip.mirror = true;
        this.setRotation(this.leftfoottip, 0.0f, 0.0f, 0.0f);
        this.leftshin = new ModelRenderer(this, 20, 250);
        this.leftshin.addBox(-5.0f, 10.0f, -9.0f, 6, 13, 6);
        this.leftshin.setPos(-8.0f, -3.0f, 6.0f);
        this.leftshin.mirror = true;
        this.setRotation(this.leftshin, 0.1745329f, 0.0f, 0.0f);
        this.leftcalf = new ModelRenderer(this, 20, 300);
        this.leftcalf.addBox(-6.0f, 10.0f, -9.0f, 8, 8, 9);
        this.leftcalf.setPos(-8.0f, -3.0f, 6.0f);
        this.leftcalf.mirror = true;
        this.setRotation(this.leftcalf, 0.1745329f, 0.0f, 0.0f);
        this.leftkneegaurd = new ModelRenderer(this, 20, 350);
        this.leftkneegaurd.addBox(-5.5f, 4.0f, -14.0f, 7, 7, 1);
        this.leftkneegaurd.setPos(-8.0f, -3.0f, 6.0f);
        this.leftkneegaurd.mirror = true;
        this.setRotation(this.leftkneegaurd, 0.6283185f, 0.0f, 0.0f);
        this.leftthigh = new ModelRenderer(this, 20, 400);
        this.leftthigh.addBox(-5.0f, 0.0f, -4.0f, 6, 13, 8);
        this.leftthigh.setPos(-8.0f, -3.0f, 6.0f);
        this.leftthigh.mirror = true;
        this.setRotation(this.leftthigh, -0.1745329f, 0.1745329f, 0.0f);
        this.rightfootfront = new ModelRenderer(this, 20, 450);
        this.rightfootfront.addBox(0.0f, 22.0f, -9.0f, 8, 5, 7);
        this.rightfootfront.setPos(5.0f, -3.0f, 6.0f);
        this.rightfootfront.mirror = true;
        this.setRotation(this.rightfootfront, 0.0f, 0.0f, 0.0f);
        this.rightfoottip = new ModelRenderer(this, 100, 50);
        this.rightfoottip.addBox(1.5f, 23.0f, -12.0f, 5, 4, 3);
        this.rightfoottip.setPos(5.0f, -3.0f, 6.0f);
        this.rightfoottip.mirror = true;
        this.setRotation(this.rightfoottip, 0.0f, 0.0f, 0.0f);
        this.rightfootbase = new ModelRenderer(this, 100, 150);
        this.rightfootbase.addBox(2.0f, 22.0f, -4.0f, 4, 5, 5);
        this.rightfootbase.setPos(5.0f, -3.0f, 6.0f);
        this.rightfootbase.mirror = true;
        this.setRotation(this.rightfootbase, 0.0f, 0.0f, 0.0f);
        this.rightfootback = new ModelRenderer(this, 100, 100);
        this.rightfootback.addBox(1.5f, 22.0f, 1.0f, 5, 5, 4);
        this.rightfootback.setPos(5.0f, -3.0f, 6.0f);
        this.rightfootback.mirror = true;
        this.setRotation(this.rightfootback, 0.0f, 0.0f, 0.0f);
        this.rightshin = new ModelRenderer(this, 100, 200);
        this.rightshin.addBox(1.0f, 10.0f, -9.0f, 6, 13, 6);
        this.rightshin.setPos(5.0f, -3.0f, 6.0f);
        this.rightshin.mirror = true;
        this.setRotation(this.rightshin, 0.1745329f, 0.0f, 0.0f);
        this.rightcalf = new ModelRenderer(this, 100, 250);
        this.rightcalf.addBox(0.0f, 10.0f, -10.0f, 8, 8, 9);
        this.rightcalf.setPos(5.0f, -3.0f, 6.0f);
        this.rightcalf.mirror = true;
        this.setRotation(this.rightcalf, 0.1745329f, 0.0f, 0.0f);
        this.rightkneegaurd = new ModelRenderer(this, 100, 300);
        this.rightkneegaurd.addBox(0.5f, 4.0f, -15.0f, 7, 7, 1);
        this.rightkneegaurd.setPos(5.0f, -3.0f, 6.0f);
        this.rightkneegaurd.mirror = true;
        this.setRotation(this.rightkneegaurd, 0.6283185f, 0.0f, 0.0f);
        this.rightthigh = new ModelRenderer(this, 100, 400);
        this.rightthigh.addBox(0.0f, 0.0f, -5.0f, 6, 13, 8);
        this.rightthigh.setPos(5.0f, -3.0f, 6.0f);
        this.rightthigh.mirror = true;
        this.setRotation(this.rightthigh, -0.1745329f, -0.1745329f, 0.0f);
        this.rightthigh.mirror = false;
        this.hips = new ModelRenderer(this, 100, 350);
        this.hips.addBox(0.0f, 0.0f, 0.0f, 14, 7, 8);
        this.hips.setPos(-8.0f, -3.0f, 2.0f);
        this.hips.mirror = true;
        this.setRotation(this.hips, 0.1396263f, 0.0f, 0.0f);
        this.stomach = new ModelRenderer(this, 100, 450);
        this.stomach.addBox(0.0f, 0.0f, 0.0f, 12, 6, 7);
        this.stomach.setPos(-7.0f, -9.0f, 2.0f);
        this.stomach.mirror = true;
        this.setRotation(this.stomach, 0.1396263f, 0.0f, 0.0f);
        this.chest = new ModelRenderer(this, 200, 50);
        this.chest.addBox(0.0f, 0.0f, 0.0f, 18, 12, 13);
        this.chest.setPos(-10.0f, -23.0f, -4.0f);
        this.chest.mirror = true;
        this.setRotation(this.chest, 0.2443461f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 200, 100);
        this.neck.addBox(0.0f, 0.0f, 0.0f, 6, 7, 6);
        this.neck.setPos(-4.0f, -22.0f, -7.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.8726646f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 200, 150);
        this.head.addBox(-3.0f, -3.0f, -5.0f, 6, 6, 8);
        this.head.setPos(-1.0f, -26.0f, -5.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.5235988f, 0.0f, 0.0f);
        this.righttopspinebase = new ModelRenderer(this, 200, 200);
        this.righttopspinebase.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.righttopspinebase.setPos(3.0f, -29.0f, 5.0f);
        this.righttopspinebase.mirror = true;
        this.setRotation(this.righttopspinebase, -0.1396263f, 0.0f, 0.0f);
        this.lefttopspinebase = new ModelRenderer(this, 200, 250);
        this.lefttopspinebase.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.lefttopspinebase.setPos(-7.0f, -29.0f, 5.0f);
        this.lefttopspinebase.mirror = true;
        this.setRotation(this.lefttopspinebase, -0.1396263f, 0.0f, 0.0f);
        this.righttopspinetip = new ModelRenderer(this, 200, 300);
        this.righttopspinetip.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.righttopspinetip.setPos(3.5f, -35.0f, 8.0f);
        this.righttopspinetip.mirror = true;
        this.setRotation(this.righttopspinetip, -0.3316126f, 0.0f, 0.0f);
        this.lefttopspinetip = new ModelRenderer(this, 200, 350);
        this.lefttopspinetip.addBox(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.lefttopspinetip.setPos(-6.5f, -35.0f, 8.0f);
        this.lefttopspinetip.mirror = true;
        this.setRotation(this.lefttopspinetip, -0.3316126f, 0.0f, 0.0f);
        this.middlerightspinebase = new ModelRenderer(this, 200, 400);
        this.middlerightspinebase.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.middlerightspinebase.setPos(-6.0f, -25.0f, 14.0f);
        this.middlerightspinebase.mirror = true;
        this.setRotation(this.middlerightspinebase, -0.6981317f, 0.0f, 0.0f);
        this.middleleftspinebase = new ModelRenderer(this, 200, 450);
        this.middleleftspinebase.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.middleleftspinebase.setPos(2.0f, -25.0f, 14.0f);
        this.middleleftspinebase.mirror = true;
        this.setRotation(this.middleleftspinebase, -0.6981317f, 0.0f, 0.0f);
        this.middleleftspinetip = new ModelRenderer(this, 300, 50);
        this.middleleftspinetip.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.middleleftspinetip.setPos(2.5f, -28.0f, 18.0f);
        this.middleleftspinetip.mirror = true;
        this.setRotation(this.middleleftspinetip, -0.7853982f, 0.0f, 0.0f);
        this.middlerightspinetip = new ModelRenderer(this, 300, 100);
        this.middlerightspinetip.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.middlerightspinetip.setPos(-5.5f, -28.0f, 18.0f);
        this.middlerightspinetip.mirror = true;
        this.setRotation(this.middlerightspinetip, -0.7853982f, 0.0f, 0.0f);
        this.torso = new ModelRenderer(this, 300, 150);
        this.torso.addBox(0.0f, 0.0f, 0.0f, 14, 6, 10);
        this.torso.setPos(-8.0f, -13.0f, 0.0f);
        this.torso.mirror = true;
        this.setRotation(this.torso, 0.1396263f, 0.0f, 0.0f);
        this.rightsholder = new ModelRenderer(this, 300, 200);
        this.rightsholder.addBox(0.0f, -3.0f, -3.0f, 6, 6, 6);
        this.rightsholder.setPos(7.0f, -18.0f, 4.0f);
        this.rightsholder.mirror = true;
        this.setRotation(this.rightsholder, 0.0f, 0.0f, 0.0f);
        this.leftsholder = new ModelRenderer(this, 300, 250);
        this.leftsholder.addBox(-6.0f, -3.0f, -3.0f, 6, 6, 6);
        this.leftsholder.setPos(-9.0f, -18.0f, 4.0f);
        this.leftsholder.mirror = true;
        this.setRotation(this.leftsholder, 0.0f, 0.0f, 0.0f);
        this.rightsholdergaurd = new ModelRenderer(this, 300, 300);
        this.rightsholdergaurd.addBox(8.0f, -4.0f, -4.0f, 4, 12, 9);
        this.rightsholdergaurd.setPos(7.0f, -18.0f, 4.0f);
        this.rightsholdergaurd.mirror = true;
        this.setRotation(this.rightsholdergaurd, -0.2094395f, 0.0f, 0.0f);
        this.sheildbase = new ModelRenderer(this, 300, 350);
        this.sheildbase.addBox(8.0f, -4.0f, -30.0f, 3, 12, 19);
        this.sheildbase.setPos(7.0f, -18.0f, 4.0f);
        this.sheildbase.mirror = true;
        this.setRotation(this.sheildbase, 1.047198f, 0.0f, 0.0f);
        this.sheildtip = new ModelRenderer(this, 300, 400);
        this.sheildtip.addBox(9.0f, -2.0f, -34.0f, 3, 8, 4);
        this.sheildtip.setPos(6.0f, -18.0f, 4.0f);
        this.sheildtip.mirror = true;
        this.setRotation(this.sheildtip, 1.047198f, 0.0f, 0.0f);
        this.rightupperarm = new ModelRenderer(this, 300, 450);
        this.rightupperarm.addBox(3.0f, -1.0f, -4.0f, 6, 13, 6);
        this.rightupperarm.setPos(7.0f, -18.0f, 4.0f);
        this.rightupperarm.mirror = true;
        this.setRotation(this.rightupperarm, -0.2094395f, 0.0f, 0.0f);
        this.rightlowerarm = new ModelRenderer(this, 350, 50);
        this.rightlowerarm.addBox(3.0f, 0.0f, -25.0f, 6, 6, 14);
        this.rightlowerarm.setPos(7.0f, -18.0f, 4.0f);
        this.rightlowerarm.mirror = true;
        this.setRotation(this.rightlowerarm, 1.047198f, 0.0f, 0.0f);
        this.sheildend = new ModelRenderer(this, 350, 100);
        this.sheildend.addBox(8.0f, -1.0f, -11.0f, 3, 8, 4);
        this.sheildend.setPos(7.0f, -18.0f, 4.0f);
        this.sheildend.mirror = true;
        this.setRotation(this.sheildend, 1.047198f, 0.0f, 0.0f);
        this.leftupperarm = new ModelRenderer(this, 350, 200);
        this.leftupperarm.addBox(-9.0f, -1.0f, -4.0f, 6, 15, 6);
        this.leftupperarm.setPos(-9.0f, -18.0f, 4.0f);
        this.leftupperarm.mirror = true;
        this.setRotation(this.leftupperarm, -0.2094395f, 0.0f, 0.0f);
        this.sholdergaurdtip = new ModelRenderer(this, 350, 250);
        this.sholdergaurdtip.addBox(10.0f, -3.0f, -7.0f, 2, 5, 3);
        this.sholdergaurdtip.setPos(7.0f, -18.0f, 4.0f);
        this.sholdergaurdtip.mirror = true;
        this.setRotation(this.sholdergaurdtip, -0.2094395f, 0.0f, 0.0f);
        this.cannonbase = new ModelRenderer(this, 350, 300);
        this.cannonbase.addBox(-4.0f, 0.0f, -4.0f, 8, 12, 8);
        this.cannonbase.setPos(-15.0f, -5.0f, 1.0f);
        this.cannonbase.mirror = true;
        this.setRotation(this.cannonbase, -0.6981317f, 0.0f, 0.0f);
        this.cannonend = new ModelRenderer(this, 350, 400);
        this.cannonend.addBox(-3.0f, 11.0f, -3.0f, 6, 4, 6);
        this.cannonend.setPos(-15.0f, -5.0f, 1.0f);
        this.cannonend.mirror = true;
        this.setRotation(this.cannonend, -0.6981317f, 0.0f, 0.0f);
        this.leftcannonpiece = new ModelRenderer(this, 20, 20);
        this.leftcannonpiece.addBox(-5.0f, 11.0f, -1.5f, 3, 6, 3);
        this.leftcannonpiece.setPos(-15.0f, -5.0f, 1.0f);
        this.leftcannonpiece.mirror = true;
        this.setRotation(this.leftcannonpiece, -0.6981317f, 0.0f, 0.0f);
        this.topcannonpiece = new ModelRenderer(this, 40, 20);
        this.topcannonpiece.addBox(-1.5f, 11.0f, -5.0f, 3, 6, 3);
        this.topcannonpiece.setPos(-15.0f, -5.0f, 1.0f);
        this.topcannonpiece.mirror = true;
        this.setRotation(this.topcannonpiece, -0.6981317f, 0.0f, 0.0f);
        this.rightcannonpiece = new ModelRenderer(this, 80, 20);
        this.rightcannonpiece.addBox(2.0f, 11.0f, -1.5f, 3, 6, 3);
        this.rightcannonpiece.setPos(-15.0f, -5.0f, 1.0f);
        this.rightcannonpiece.mirror = true;
        this.setRotation(this.rightcannonpiece, -0.6981317f, 0.0f, 0.0f);
        this.bottomcannonpiece = new ModelRenderer(this, 100, 20);
        this.bottomcannonpiece.addBox(-1.5f, 11.0f, 2.0f, 3, 6, 3);
        this.bottomcannonpiece.setPos(-15.0f, -5.0f, 1.0f);
        this.bottomcannonpiece.mirror = true;
        this.setRotation(this.bottomcannonpiece, -0.6981317f, 0.0f, 0.0f);
        this.glowycannonbit1 = new ModelRenderer(this, 150, 20);
        this.glowycannonbit1.addBox(-3.5f, 0.0f, -11.0f, 2, 5, 2);
        this.glowycannonbit1.setPos(-15.0f, -5.0f, 1.0f);
        this.glowycannonbit1.mirror = true;
        this.setRotation(this.glowycannonbit1, 0.1745329f, 0.0f, 0.0f);
        this.glowycannonbit2 = new ModelRenderer(this, 200, 20);
        this.glowycannonbit2.addBox(1.5f, 0.0f, -11.0f, 2, 5, 2);
        this.glowycannonbit2.setPos(-15.0f, -5.0f, 1.0f);
        this.glowycannonbit2.mirror = true;
        this.setRotation(this.glowycannonbit2, 0.1745329f, 0.0f, 0.0f);
        this.glowycannonbit3 = new ModelRenderer(this, 250, 20);
        this.glowycannonbit3.addBox(-3.0f, -2.0f, -8.0f, 2, 5, 2);
        this.glowycannonbit3.setPos(-15.0f, -5.0f, 1.0f);
        this.glowycannonbit3.mirror = true;
        this.setRotation(this.glowycannonbit3, 0.0872665f, 0.0f, 0.0f);
        this.glowycannonbit4 = new ModelRenderer(this, 300, 20);
        this.glowycannonbit4.addBox(1.0f, -2.0f, -8.0f, 2, 5, 2);
        this.glowycannonbit4.setPos(-15.0f, -5.0f, 1.0f);
        this.glowycannonbit4.mirror = true;
        this.setRotation(this.glowycannonbit4, 0.0872665f, 0.0f, 0.0f);
        this.glowycannonbit5 = new ModelRenderer(this, 350, 20);
        this.glowycannonbit5.addBox(-1.0f, -5.0f, -5.0f, 2, 5, 2);
        this.glowycannonbit5.setPos(-15.0f, -5.0f, 1.0f);
        this.glowycannonbit5.mirror = true;
        this.setRotation(this.glowycannonbit5, 0.0f, 0.0f, 0.0f);
        this.cannonammo = new ModelRenderer(this, 400, 400);
        this.cannonammo.addBox(-6.0f, 3.0f, 0.0f, 5, 5, 5);
        this.cannonammo.setPos(-15.0f, -5.0f, 1.0f);
        this.cannonammo.mirror = true;
        this.setRotation(this.cannonammo, -0.6981317f, 0.0f, 0.0f);
        this.lowerrightspinebase = new ModelRenderer(this, 400, 450);
        this.lowerrightspinebase.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.lowerrightspinebase.setPos(4.0f, -19.0f, 15.0f);
        this.lowerrightspinebase.mirror = true;
        this.setRotation(this.lowerrightspinebase, -1.047198f, 0.0f, 0.0f);
        this.lowerleftspinebase = new ModelRenderer(this, 360, 450);
        this.lowerleftspinebase.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.lowerleftspinebase.setPos(-8.0f, -19.0f, 15.0f);
        this.lowerleftspinebase.mirror = true;
        this.setRotation(this.lowerleftspinebase, -1.047198f, 0.0f, 0.0f);
        this.lowerrightspinetip = new ModelRenderer(this, 250, 100);
        this.lowerrightspinetip.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.lowerrightspinetip.setPos(4.5f, -21.0f, 20.0f);
        this.lowerrightspinetip.mirror = true;
        this.setRotation(this.lowerrightspinetip, -1.134464f, 0.0f, 0.0f);
        this.lowerleftspinetip = new ModelRenderer(this, 150, 100);
        this.lowerleftspinetip.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.lowerleftspinetip.setPos(-7.5f, -21.0f, 20.0f);
        this.lowerleftspinetip.mirror = true;
        this.setRotation(this.lowerleftspinetip, -1.134464f, 0.0f, 0.0f);
    }
    public void setupAnim(Robot4 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Robot4 e = (Robot4)entity;
        float newangle = 0.0f;
        float nextangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.15f * f1 : 0.0f;
        this.leftfootfront.xRot = newangle;
        this.leftfootbase.xRot = newangle;
        this.leftfootback.xRot = newangle;
        this.leftfoottip.xRot = newangle;
        this.leftshin.xRot = newangle;
        this.leftcalf.xRot = newangle + 0.175f;
        this.leftkneegaurd.xRot = newangle + 0.63f;
        this.leftthigh.xRot = newangle - 0.175f;
        this.rightfootfront.xRot = - newangle;
        this.rightfoottip.xRot = - newangle;
        this.rightfootbase.xRot = - newangle;
        this.rightfootback.xRot = - newangle;
        this.rightshin.xRot = - newangle;
        this.rightcalf.xRot = - newangle + 0.175f;
        this.rightkneegaurd.xRot = - newangle + 0.63f;
        this.rightthigh.xRot = - newangle - 0.175f;
        this.head.yRot = (float)Math.toRadians((double)f3 / 1.5);
        float amp = 0.7853982f;
        if (e.getAttacking() != 0) {
            newangle = MathHelper.cos((float)((float)Math.toRadians(f2 % 360.0f) * this.wingspeed * 6.0f)) * amp;
            newangle = Math.abs(newangle);
            newangle += 0.75f;
        } else {
            newangle = 0.0f;
        }
        if ((double)newangle > (double)amp / 3.0) {
            e.setShielding(1);
        } else {
            e.setShielding(0);
        }
        this.rightsholder.xRot = - newangle;
        this.rightsholdergaurd.xRot = - newangle - 0.21f;
        this.sheildbase.xRot = - newangle + 1.047f;
        this.sheildtip.xRot = - newangle + 1.047f;
        this.rightupperarm.xRot = - newangle - 0.21f;
        this.rightlowerarm.xRot = - newangle + 1.047f;
        this.sheildend.xRot = - newangle + 1.04f;
        this.sholdergaurdtip.xRot = - newangle - 0.21f;
        newangle = e.getAttacking() != 0 ? 0.85f : 0.0f;
        this.leftsholder.xRot = - newangle;
        this.leftupperarm.xRot = - newangle - 0.21f;
        this.cannonbase.xRot = - newangle - 0.7f;
        this.cannonend.xRot = - newangle - 0.7f;
        this.leftcannonpiece.xRot = - newangle - 0.7f;
        this.topcannonpiece.xRot = - newangle - 0.7f;
        this.rightcannonpiece.xRot = - newangle - 0.7f;
        this.bottomcannonpiece.xRot = - newangle - 0.7f;
        this.glowycannonbit1.xRot = - newangle + 0.17f;
        this.glowycannonbit2.xRot = - newangle + 0.17f;
        this.glowycannonbit3.xRot = - newangle + 0.08f;
        this.glowycannonbit4.xRot = - newangle + 0.08f;
        this.glowycannonbit5.xRot = - newangle;
        this.cannonammo.xRot = - newangle - 0.7f;
        double newposy = (float)((double)this.leftsholder.y + Math.cos(this.leftupperarm.xRot) * 14.0);
        double newposz = (float)((double)this.leftsholder.z + Math.sin(this.leftupperarm.xRot) * 14.0);
        this.cannonbase.y = (float)newposy;
        this.cannonbase.z = (float)newposz;
        this.cannonend.y = (float)newposy;
        this.cannonend.z = (float)newposz;
        this.leftcannonpiece.y = (float)newposy;
        this.leftcannonpiece.z = (float)newposz;
        this.topcannonpiece.y = (float)newposy;
        this.topcannonpiece.z = (float)newposz;
        this.rightcannonpiece.y = (float)newposy;
        this.rightcannonpiece.z = (float)newposz;
        this.bottomcannonpiece.y = (float)newposy;
        this.bottomcannonpiece.z = (float)newposz;
        this.glowycannonbit1.y = (float)newposy;
        this.glowycannonbit1.z = (float)newposz;
        this.glowycannonbit2.y = (float)newposy;
        this.glowycannonbit2.z = (float)newposz;
        this.glowycannonbit3.y = (float)newposy;
        this.glowycannonbit3.z = (float)newposz;
        this.glowycannonbit4.y = (float)newposy;
        this.glowycannonbit4.z = (float)newposz;
        this.glowycannonbit5.y = (float)newposy;
        this.glowycannonbit5.z = (float)newposz;
        this.cannonammo.y = (float)newposy;
        this.cannonammo.z = (float)newposz;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.leftfootfront.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftfootbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftfootback.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftfoottip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftshin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftcalf.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftkneegaurd.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftthigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfootfront.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfoottip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfootbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfootback.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightshin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightcalf.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightkneegaurd.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightthigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hips.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.stomach.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.righttopspinebase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lefttopspinebase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.righttopspinetip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lefttopspinetip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.middlerightspinebase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.middleleftspinebase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.middleleftspinetip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.middlerightspinetip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightsholder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftsholder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightsholdergaurd.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.sheildbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.sheildtip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightupperarm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightlowerarm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.sheildend.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftupperarm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.sholdergaurdtip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cannonbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cannonend.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftcannonpiece.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topcannonpiece.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightcannonpiece.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottomcannonpiece.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.glowycannonbit1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.glowycannonbit2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.glowycannonbit3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.glowycannonbit4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.glowycannonbit5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cannonammo.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerrightspinebase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerleftspinebase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerrightspinetip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerleftspinetip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Robot4 par7Entity) {
        
    }
}

