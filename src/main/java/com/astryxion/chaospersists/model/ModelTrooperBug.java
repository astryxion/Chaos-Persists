/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelTrooperBug
 *  com.astryxion.chaospersists.TrooperBug
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.TrooperBug;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTrooperBug extends EntityModel<TrooperBug> {
    private float wingspeed = 1.0f;
    ModelRenderer legintersection;
    ModelRenderer legintersectionb;
    ModelRenderer leg1start;
    ModelRenderer leg1shoulder;
    ModelRenderer leg1shoulderpart2;
    ModelRenderer leg1part1;
    ModelRenderer leg1part1b;
    ModelRenderer leg1elbow;
    ModelRenderer leg1part2;
    ModelRenderer leg1part2b;
    ModelRenderer leg1part2c;
    ModelRenderer leg1part3;
    ModelRenderer leg1part3b;
    ModelRenderer leg1part3c;
    ModelRenderer leg1part3d;
    ModelRenderer leg2start;
    ModelRenderer leg2shoulder;
    ModelRenderer leg2shoulderpart2;
    ModelRenderer leg2part1;
    ModelRenderer leg2part1b;
    ModelRenderer leg2elbow;
    ModelRenderer leg2part2;
    ModelRenderer leg2part2b;
    ModelRenderer leg2part2c;
    ModelRenderer leg2part3;
    ModelRenderer leg2part3b;
    ModelRenderer leg2part3c;
    ModelRenderer leg2part3d;
    ModelRenderer leg3start;
    ModelRenderer leg3shoulder;
    ModelRenderer leg3shoulderpart2;
    ModelRenderer leg3part1;
    ModelRenderer leg3part1b;
    ModelRenderer leg3elbow;
    ModelRenderer leg3part2;
    ModelRenderer leg3part2b;
    ModelRenderer leg3part2c;
    ModelRenderer leg3part3;
    ModelRenderer leg3part3b;
    ModelRenderer leg3part3c;
    ModelRenderer leg3part3d;
    ModelRenderer leg4start;
    ModelRenderer leg4shoulder;
    ModelRenderer leg4shoulderpart2;
    ModelRenderer leg4part1;
    ModelRenderer leg4part1b;
    ModelRenderer leg4elbow;
    ModelRenderer leg4part2;
    ModelRenderer leg4part2b;
    ModelRenderer leg4part2c;
    ModelRenderer leg4part3;
    ModelRenderer leg4part3b;
    ModelRenderer leg4part3c;
    ModelRenderer leg4part3d;
    ModelRenderer jawbase;
    ModelRenderer jawbase2;
    ModelRenderer jawbase3;
    ModelRenderer jawbase4;
    ModelRenderer jawbase5;
    ModelRenderer jawbase6;
    ModelRenderer jawbase7;
    ModelRenderer jawbase8;
    ModelRenderer jawbase9;
    ModelRenderer jawleft;
    ModelRenderer jawright;
    ModelRenderer jawend;
    ModelRenderer headstart;
    ModelRenderer headbase;
    ModelRenderer headbase2;
    ModelRenderer headbase3;
    ModelRenderer headbase4;
    ModelRenderer headbase5;
    ModelRenderer headbase6;
    ModelRenderer headbase7;
    ModelRenderer headbase8;
    ModelRenderer headbase9;
    ModelRenderer headbase10;
    ModelRenderer headleftridge;
    ModelRenderer headrightridge;
    ModelRenderer antenna1;
    ModelRenderer antenna1part2;
    ModelRenderer antenna2;
    ModelRenderer antenna2part2;
    ModelRenderer eyebase1;
    ModelRenderer eye1;
    ModelRenderer eyebase2;
    ModelRenderer eye2;
    ModelRenderer arm1start;
    ModelRenderer arm1part1;
    ModelRenderer arm1part2;
    ModelRenderer arm1part2b;
    ModelRenderer arm1part2c;
    ModelRenderer arm1part3;
    ModelRenderer arm1part3b;
    ModelRenderer arm2start;
    ModelRenderer arm2part1;
    ModelRenderer arm2part2;
    ModelRenderer arm2part2b;
    ModelRenderer arm2part2c;
    ModelRenderer arm2part3;
    ModelRenderer arm2part3b;
    ModelRenderer innermouth;
    ModelRenderer innermouth2;
    ModelRenderer tooth1;
    ModelRenderer tooth2;
    ModelRenderer tooth3;
    ModelRenderer tooth4;
    ModelRenderer tooth5;
    ModelRenderer tooth6;
    ModelRenderer mouthedge1;
    ModelRenderer mouthedge2;
    ModelRenderer mouthedge3;
    ModelRenderer mouthedge4;
    ModelRenderer arm3start;
    ModelRenderer arm3part1;
    ModelRenderer arm3part1b;
    ModelRenderer arm3part1c;
    ModelRenderer arm4start;
    ModelRenderer arm4part1;
    ModelRenderer arm4part1b;
    ModelRenderer arm4part1c;
    ModelRenderer tongue;
    ModelRenderer tonguepart2;
    ModelRenderer upperjawridgeleft;
    ModelRenderer upperjawridgeright;
    ModelRenderer upperjawbaseleft;
    ModelRenderer upperjawbaseright;
    ModelRenderer upperjawbase;
    ModelRenderer upperjawbase2;
    ModelRenderer upperjawbase3;
    ModelRenderer upperjawbase4;
    ModelRenderer upperjawend;
    ModelRenderer upperjawleftend;
    ModelRenderer upperjawrightend;

    public ModelTrooperBug(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 256;
        this.legintersection = new ModelRenderer(this, 0, 0);
        this.legintersection.addBox(-9.0f, -2.0f, -9.0f, 18, 4, 18);
        this.legintersection.setPos(0.0f, 0.0f, 0.0f);
        this.legintersection.mirror = true;
        this.setRotation(this.legintersection, 0.0f, 0.0f, 0.0f);
        this.legintersectionb = new ModelRenderer(this, 0, 156);
        this.legintersectionb.addBox(-9.0f, -2.0f, -16.0f, 18, 4, 18);
        this.legintersectionb.setPos(0.0f, 0.0f, 7.0f);
        this.legintersectionb.mirror = true;
        this.setRotation(this.legintersectionb, 0.2230717f, 0.0f, 0.0f);
        this.leg1start = new ModelRenderer(this, 55, 0);
        this.leg1start.addBox(-3.0f, -2.5f, -4.0f, 6, 5, 5);
        this.leg1start.setPos(-7.0f, 0.0f, -7.0f);
        this.leg1start.mirror = true;
        this.setRotation(this.leg1start, 0.0f, 0.7853982f, 0.0f);
        this.leg1shoulder = new ModelRenderer(this, 77, 0);
        this.leg1shoulder.addBox(-2.5f, -4.5f, -6.0f, 5, 6, 10);
        this.leg1shoulder.setPos(-10.0f, 0.0f, -10.0f);
        this.leg1shoulder.mirror = true;
        this.setRotation(this.leg1shoulder, -0.5576792f, 0.7853982f, 0.0f);
        this.leg1shoulderpart2 = new ModelRenderer(this, 70, 18);
        this.leg1shoulderpart2.addBox(-1.5f, -2.5f, -11.0f, 3, 3, 6);
        this.leg1shoulderpart2.setPos(-10.0f, 0.0f, -10.0f);
        this.leg1shoulderpart2.mirror = true;
        this.setRotation(this.leg1shoulderpart2, -0.4089647f, 0.7853982f, 0.0f);
        this.leg1part1 = new ModelRenderer(this, 68, 17);
        this.leg1part1.addBox(-2.0f, -2.5f, -23.0f, 4, 3, 26);
        this.leg1part1.setPos(-17.0f, -5.0f, -17.0f);
        this.leg1part1.mirror = true;
        this.setRotation(this.leg1part1, 1.115358f, 0.7853982f, 0.0f);
        this.leg1part1b = new ModelRenderer(this, 19, 23);
        this.leg1part1b.addBox(-1.5f, 0.5f, -23.0f, 3, 3, 21);
        this.leg1part1b.setPos(-17.0f, -5.0f, -17.0f);
        this.leg1part1b.mirror = true;
        this.setRotation(this.leg1part1b, 1.07818f, 0.7853982f, 0.0f);
        this.leg1elbow = new ModelRenderer(this, 68, 28);
        this.leg1elbow.addBox(-1.5f, -5.5f, -22.0f, 3, 6, 3);
        this.leg1elbow.setPos(-17.0f, -5.0f, -17.0f);
        this.leg1elbow.mirror = true;
        this.setRotation(this.leg1elbow, 1.115358f, 0.7853982f, 0.0f);
        this.leg1part2 = new ModelRenderer(this, 48, 47);
        this.leg1part2.addBox(-3.0f, -2.5f, -2.0f, 6, 4, 34);
        this.leg1part2.setPos(-27.0f, 12.0f, -27.0f);
        this.leg1part2.mirror = true;
        this.setRotation(this.leg1part2, 1.784573f, 0.7853982f, 0.0f);
        this.leg1part2b = new ModelRenderer(this, 3, 53);
        this.leg1part2b.addBox(-2.5f, -2.5f, -2.0f, 5, 4, 33);
        this.leg1part2b.setPos(-27.0f, 12.0f, -27.0f);
        this.leg1part2b.mirror = true;
        this.setRotation(this.leg1part2b, 1.747395f, 0.7853982f, 0.0f);
        this.leg1part2c = new ModelRenderer(this, 48, 86);
        this.leg1part2c.addBox(-2.0f, -2.5f, -2.0f, 4, 4, 32);
        this.leg1part2c.setPos(-27.0f, 12.0f, -27.0f);
        this.leg1part2c.mirror = true;
        this.setRotation(this.leg1part2c, 1.710216f, 0.7853982f, 0.0f);
        this.leg1part3 = new ModelRenderer(this, 0, 91);
        this.leg1part3.addBox(-1.5f, -2.5f, -17.0f, 3, 4, 18);
        this.leg1part3.setPos(-32.0f, -17.0f, -32.0f);
        this.leg1part3.mirror = true;
        this.setRotation(this.leg1part3, 1.189716f, 0.7853982f, 0.0f);
        this.leg1part3b = new ModelRenderer(this, 15, 30);
        this.leg1part3b.addBox(-1.0f, -1.5f, -27.0f, 2, 3, 10);
        this.leg1part3b.setPos(-32.0f, -17.0f, -32.0f);
        this.leg1part3b.mirror = true;
        this.setRotation(this.leg1part3b, 1.189716f, 0.7853982f, 0.0f);
        this.leg1part3c = new ModelRenderer(this, 47, 23);
        this.leg1part3c.addBox(-0.5f, -0.5f, -34.0f, 1, 2, 7);
        this.leg1part3c.setPos(-32.0f, -17.0f, -32.0f);
        this.leg1part3c.mirror = true;
        this.setRotation(this.leg1part3c, 1.189716f, 0.7853982f, 0.0f);
        this.leg1part3d = new ModelRenderer(this, 0, 23);
        this.leg1part3d.addBox(-0.5f, 0.5f, -44.0f, 1, 1, 10);
        this.leg1part3d.setPos(-32.0f, -17.0f, -32.0f);
        this.leg1part3d.mirror = true;
        this.setRotation(this.leg1part3d, 1.189716f, 0.7853982f, 0.0f);
        this.leg2start = new ModelRenderer(this, 55, 0);
        this.leg2start.addBox(-3.0f, -2.5f, -4.0f, 6, 5, 5);
        this.leg2start.setPos(7.0f, 0.0f, -7.0f);
        this.leg2start.mirror = true;
        this.setRotation(this.leg2start, 0.0f, -0.7853982f, 0.0f);
        this.leg2shoulder = new ModelRenderer(this, 77, 0);
        this.leg2shoulder.addBox(-2.5f, -4.5f, -6.0f, 5, 6, 10);
        this.leg2shoulder.setPos(10.0f, 0.0f, -10.0f);
        this.leg2shoulder.mirror = true;
        this.setRotation(this.leg2shoulder, -0.5576792f, -0.7853982f, 0.0f);
        this.leg2shoulderpart2 = new ModelRenderer(this, 70, 18);
        this.leg2shoulderpart2.addBox(-1.5f, -2.5f, -11.0f, 3, 3, 6);
        this.leg2shoulderpart2.setPos(10.0f, 0.0f, -10.0f);
        this.leg2shoulderpart2.mirror = true;
        this.setRotation(this.leg2shoulderpart2, -0.4089647f, -0.7853982f, 0.0f);
        this.leg2part1 = new ModelRenderer(this, 68, 17);
        this.leg2part1.addBox(-2.0f, -2.5f, -23.0f, 4, 3, 26);
        this.leg2part1.setPos(17.0f, -5.0f, -17.0f);
        this.leg2part1.mirror = true;
        this.setRotation(this.leg2part1, 1.115358f, -0.7853982f, 0.0f);
        this.leg2part1b = new ModelRenderer(this, 19, 23);
        this.leg2part1b.addBox(-1.5f, 0.5f, -23.0f, 3, 3, 21);
        this.leg2part1b.setPos(17.0f, -5.0f, -17.0f);
        this.leg2part1b.mirror = true;
        this.setRotation(this.leg2part1b, 1.07818f, -0.7853982f, 0.0f);
        this.leg2elbow = new ModelRenderer(this, 68, 28);
        this.leg2elbow.addBox(-1.5f, -5.5f, -22.0f, 3, 6, 3);
        this.leg2elbow.setPos(17.0f, -5.0f, -17.0f);
        this.leg2elbow.mirror = true;
        this.setRotation(this.leg2elbow, 1.115358f, -0.7853982f, 0.0f);
        this.leg2part2 = new ModelRenderer(this, 48, 47);
        this.leg2part2.addBox(-3.0f, -2.5f, -2.0f, 6, 4, 34);
        this.leg2part2.setPos(27.0f, 12.0f, -27.0f);
        this.leg2part2.mirror = true;
        this.setRotation(this.leg2part2, 1.784573f, -0.7853982f, 0.0f);
        this.leg2part2b = new ModelRenderer(this, 3, 53);
        this.leg2part2b.addBox(-2.5f, -2.5f, -2.0f, 5, 4, 33);
        this.leg2part2b.setPos(27.0f, 12.0f, -27.0f);
        this.leg2part2b.mirror = true;
        this.setRotation(this.leg2part2b, 1.747395f, -0.7853982f, 0.0f);
        this.leg2part2c = new ModelRenderer(this, 48, 86);
        this.leg2part2c.addBox(-2.0f, -2.5f, -2.0f, 4, 4, 32);
        this.leg2part2c.setPos(27.0f, 12.0f, -27.0f);
        this.leg2part2c.mirror = true;
        this.setRotation(this.leg2part2c, 1.710216f, -0.7853982f, 0.0f);
        this.leg2part3 = new ModelRenderer(this, 0, 91);
        this.leg2part3.addBox(-1.5f, -2.5f, -17.0f, 3, 4, 18);
        this.leg2part3.setPos(32.0f, -17.0f, -32.0f);
        this.leg2part3.mirror = true;
        this.setRotation(this.leg2part3, 1.189716f, -0.7853982f, 0.0f);
        this.leg2part3b = new ModelRenderer(this, 15, 30);
        this.leg2part3b.addBox(-1.0f, -1.5f, -27.0f, 2, 3, 10);
        this.leg2part3b.setPos(32.0f, -17.0f, -32.0f);
        this.leg2part3b.mirror = true;
        this.setRotation(this.leg2part3b, 1.189716f, -0.7853982f, 0.0f);
        this.leg2part3c = new ModelRenderer(this, 47, 23);
        this.leg2part3c.addBox(-0.5f, -0.5f, -34.0f, 1, 2, 7);
        this.leg2part3c.setPos(32.0f, -17.0f, -32.0f);
        this.leg2part3c.mirror = true;
        this.setRotation(this.leg2part3c, 1.189716f, -0.7853982f, 0.0f);
        this.leg2part3d = new ModelRenderer(this, 0, 23);
        this.leg2part3d.addBox(-0.5f, 0.5f, -44.0f, 1, 1, 10);
        this.leg2part3d.setPos(32.0f, -17.0f, -32.0f);
        this.leg2part3d.mirror = true;
        this.setRotation(this.leg2part3d, 1.189716f, -0.7853982f, 0.0f);
        this.leg3start = new ModelRenderer(this, 55, 0);
        this.leg3start.addBox(-3.0f, -2.5f, -1.0f, 6, 5, 5);
        this.leg3start.setPos(7.0f, 0.0f, 7.0f);
        this.leg3start.mirror = true;
        this.setRotation(this.leg3start, 0.0f, 0.7853982f, 0.0f);
        this.leg3shoulder = new ModelRenderer(this, 114, 0);
        this.leg3shoulder.addBox(-2.5f, -4.5f, -4.0f, 5, 6, 10);
        this.leg3shoulder.setPos(10.0f, 0.0f, 10.0f);
        this.leg3shoulder.mirror = true;
        this.setRotation(this.leg3shoulder, 0.5576851f, 0.7853982f, 0.0f);
        this.leg3shoulderpart2 = new ModelRenderer(this, 70, 18);
        this.leg3shoulderpart2.addBox(-1.5f, -2.5f, 5.0f, 3, 3, 6);
        this.leg3shoulderpart2.setPos(10.0f, 0.0f, 10.0f);
        this.leg3shoulderpart2.mirror = true;
        this.setRotation(this.leg3shoulderpart2, 0.4089656f, 0.7853982f, 0.0f);
        this.leg3part1 = new ModelRenderer(this, 68, 17);
        this.leg3part1.addBox(-2.0f, -2.5f, -3.0f, 4, 3, 26);
        this.leg3part1.setPos(17.0f, -5.0f, 17.0f);
        this.leg3part1.mirror = true;
        this.setRotation(this.leg3part1, -1.115353f, 0.7853982f, 0.0f);
        this.leg3part1b = new ModelRenderer(this, 117, 55);
        this.leg3part1b.addBox(-1.5f, 0.5f, 2.0f, 3, 3, 21);
        this.leg3part1b.setPos(17.0f, -5.0f, 17.0f);
        this.leg3part1b.mirror = true;
        this.setRotation(this.leg3part1b, -1.078177f, 0.7853982f, 0.0f);
        this.leg3elbow = new ModelRenderer(this, 68, 28);
        this.leg3elbow.addBox(-1.5f, -5.5f, 19.0f, 3, 6, 3);
        this.leg3elbow.setPos(17.0f, -5.0f, 17.0f);
        this.leg3elbow.mirror = true;
        this.setRotation(this.leg3elbow, -1.115353f, 0.7853982f, 0.0f);
        this.leg3part2 = new ModelRenderer(this, 0, 206);
        this.leg3part2.addBox(-3.0f, -2.5f, -32.0f, 6, 4, 34);
        this.leg3part2.setPos(27.0f, 12.0f, 27.0f);
        this.leg3part2.mirror = true;
        this.setRotation(this.leg3part2, -1.784582f, 0.7853982f, 0.0f);
        this.leg3part2b = new ModelRenderer(this, 3, 53);
        this.leg3part2b.addBox(-2.5f, -2.5f, -31.0f, 5, 4, 33);
        this.leg3part2b.setPos(27.0f, 12.0f, 27.0f);
        this.leg3part2b.mirror = true;
        this.setRotation(this.leg3part2b, -1.747389f, 0.7853982f, 0.0f);
        this.leg3part2c = new ModelRenderer(this, 48, 86);
        this.leg3part2c.addBox(-2.0f, -2.5f, -30.0f, 4, 4, 32);
        this.leg3part2c.setPos(27.0f, 12.0f, 27.0f);
        this.leg3part2c.mirror = true;
        this.setRotation(this.leg3part2c, -1.710213f, 0.7853982f, 0.0f);
        this.leg3part3 = new ModelRenderer(this, 0, 178);
        this.leg3part3.addBox(-1.5f, -2.5f, -1.0f, 3, 4, 18);
        this.leg3part3.setPos(32.0f, -17.0f, 32.0f);
        this.leg3part3.mirror = true;
        this.setRotation(this.leg3part3, -1.189721f, 0.7853982f, 0.0f);
        this.leg3part3b = new ModelRenderer(this, 12, 62);
        this.leg3part3b.addBox(-1.0f, -1.5f, 17.0f, 2, 3, 10);
        this.leg3part3b.setPos(32.0f, -17.0f, 32.0f);
        this.leg3part3b.mirror = true;
        this.setRotation(this.leg3part3b, -1.189721f, 0.7853982f, 0.0f);
        this.leg3part3c = new ModelRenderer(this, 47, 32);
        this.leg3part3c.addBox(-0.5f, -0.5f, 27.0f, 1, 2, 7);
        this.leg3part3c.setPos(32.0f, -17.0f, 32.0f);
        this.leg3part3c.mirror = true;
        this.setRotation(this.leg3part3c, -1.189721f, 0.7853982f, 0.0f);
        this.leg3part3d = new ModelRenderer(this, 0, 23);
        this.leg3part3d.addBox(-0.5f, 0.5f, 34.0f, 1, 1, 10);
        this.leg3part3d.setPos(32.0f, -17.0f, 32.0f);
        this.leg3part3d.mirror = true;
        this.setRotation(this.leg3part3d, -1.189721f, 0.7853982f, 0.0f);
        this.leg4start = new ModelRenderer(this, 55, 0);
        this.leg4start.addBox(-3.0f, -2.5f, -1.0f, 6, 5, 5);
        this.leg4start.setPos(-7.0f, 0.0f, 7.0f);
        this.leg4start.mirror = true;
        this.setRotation(this.leg4start, 0.0f, -0.7853982f, 0.0f);
        this.leg4shoulder = new ModelRenderer(this, 114, 0);
        this.leg4shoulder.addBox(-2.5f, -4.5f, -4.0f, 5, 6, 10);
        this.leg4shoulder.setPos(-10.0f, 0.0f, 10.0f);
        this.leg4shoulder.mirror = true;
        this.setRotation(this.leg4shoulder, 0.5576851f, -0.7853982f, 0.0f);
        this.leg4shoulderpart2 = new ModelRenderer(this, 70, 18);
        this.leg4shoulderpart2.addBox(-1.5f, -2.5f, 5.0f, 3, 3, 6);
        this.leg4shoulderpart2.setPos(-10.0f, 0.0f, 10.0f);
        this.leg4shoulderpart2.mirror = true;
        this.setRotation(this.leg4shoulderpart2, 0.4089656f, -0.7853982f, 0.0f);
        this.leg4part1 = new ModelRenderer(this, 68, 17);
        this.leg4part1.addBox(-2.0f, -2.5f, -3.0f, 4, 3, 26);
        this.leg4part1.setPos(-17.0f, -5.0f, 17.0f);
        this.leg4part1.mirror = true;
        this.setRotation(this.leg4part1, -1.115353f, -0.7853982f, 0.0f);
        this.leg4part1b = new ModelRenderer(this, 117, 55);
        this.leg4part1b.addBox(-1.5f, 0.5f, 2.0f, 3, 3, 21);
        this.leg4part1b.setPos(-17.0f, -5.0f, 17.0f);
        this.leg4part1b.mirror = true;
        this.setRotation(this.leg4part1b, -1.078177f, -0.7853982f, 0.0f);
        this.leg4elbow = new ModelRenderer(this, 68, 28);
        this.leg4elbow.addBox(-1.5f, -5.5f, 19.0f, 3, 6, 3);
        this.leg4elbow.setPos(-17.0f, -5.0f, 17.0f);
        this.leg4elbow.mirror = true;
        this.setRotation(this.leg4elbow, -1.115353f, -0.7853982f, 0.0f);
        this.leg4part2 = new ModelRenderer(this, 0, 206);
        this.leg4part2.addBox(-3.0f, -2.5f, -32.0f, 6, 4, 34);
        this.leg4part2.setPos(-27.0f, 12.0f, 27.0f);
        this.leg4part2.mirror = true;
        this.setRotation(this.leg4part2, -1.784582f, -0.7853982f, 0.0f);
        this.leg4part2b = new ModelRenderer(this, 3, 53);
        this.leg4part2b.addBox(-2.5f, -2.5f, -31.0f, 5, 4, 33);
        this.leg4part2b.setPos(-27.0f, 12.0f, 27.0f);
        this.leg4part2b.mirror = true;
        this.setRotation(this.leg4part2b, -1.747389f, -0.7853982f, 0.0f);
        this.leg4part2c = new ModelRenderer(this, 48, 86);
        this.leg4part2c.addBox(-2.0f, -2.5f, -30.0f, 4, 4, 32);
        this.leg4part2c.setPos(-27.0f, 12.0f, 27.0f);
        this.leg4part2c.mirror = true;
        this.setRotation(this.leg4part2c, -1.710213f, -0.7853982f, 0.0f);
        this.leg4part3 = new ModelRenderer(this, 0, 178);
        this.leg4part3.addBox(-1.5f, -2.5f, -1.0f, 3, 4, 18);
        this.leg4part3.setPos(-32.0f, -17.0f, 32.0f);
        this.leg4part3.mirror = true;
        this.setRotation(this.leg4part3, -1.189721f, -0.7853982f, 0.0f);
        this.leg4part3b = new ModelRenderer(this, 12, 62);
        this.leg4part3b.addBox(-1.0f, -1.5f, 17.0f, 2, 3, 10);
        this.leg4part3b.setPos(-32.0f, -17.0f, 32.0f);
        this.leg4part3b.mirror = true;
        this.setRotation(this.leg4part3b, -1.189721f, -0.7853982f, 0.0f);
        this.leg4part3c = new ModelRenderer(this, 47, 32);
        this.leg4part3c.addBox(-0.5f, -0.5f, 27.0f, 1, 2, 7);
        this.leg4part3c.setPos(-32.0f, -17.0f, 32.0f);
        this.leg4part3c.mirror = true;
        this.setRotation(this.leg4part3c, -1.189721f, -0.7853982f, 0.0f);
        this.leg4part3d = new ModelRenderer(this, 0, 23);
        this.leg4part3d.addBox(-0.5f, 0.5f, 34.0f, 1, 1, 10);
        this.leg4part3d.setPos(-32.0f, -17.0f, 32.0f);
        this.leg4part3d.mirror = true;
        this.setRotation(this.leg4part3d, -1.189721f, -0.7853982f, 0.0f);
        this.jawbase = new ModelRenderer(this, 104, 0);
        this.jawbase.addBox(-2.0f, -2.0f, -40.0f, 4, 3, 40);
        this.jawbase.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase.mirror = true;
        this.setRotation(this.jawbase, 0.2230717f, 0.0f, 0.0f);
        this.jawbase2 = new ModelRenderer(this, 133, 44);
        this.jawbase2.addBox(-6.0f, -2.0f, -8.0f, 12, 3, 8);
        this.jawbase2.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase2.mirror = true;
        this.setRotation(this.jawbase2, 0.2230705f, 0.0f, 0.0f);
        this.jawbase3 = new ModelRenderer(this, 96, 55);
        this.jawbase3.addBox(-7.0f, -2.0f, -15.0f, 14, 3, 7);
        this.jawbase3.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase3.mirror = true;
        this.setRotation(this.jawbase3, 0.2230705f, 0.0f, 0.0f);
        this.jawbase4 = new ModelRenderer(this, 96, 47);
        this.jawbase4.addBox(-6.0f, -2.0f, -19.0f, 12, 3, 4);
        this.jawbase4.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase4.mirror = true;
        this.setRotation(this.jawbase4, 0.2230705f, 0.0f, 0.0f);
        this.jawbase5 = new ModelRenderer(this, 104, 32);
        this.jawbase5.addBox(-5.0f, -2.0f, -23.0f, 10, 3, 4);
        this.jawbase5.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase5.mirror = true;
        this.setRotation(this.jawbase5, 0.2230705f, 0.0f, 0.0f);
        this.jawbase6 = new ModelRenderer(this, 104, 25);
        this.jawbase6.addBox(-4.0f, -2.0f, -26.0f, 8, 3, 3);
        this.jawbase6.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase6.mirror = true;
        this.setRotation(this.jawbase6, 0.2230705f, 0.0f, 0.0f);
        this.jawbase7 = new ModelRenderer(this, 104, 19);
        this.jawbase7.addBox(-3.0f, -2.0f, -28.0f, 6, 3, 2);
        this.jawbase7.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase7.mirror = true;
        this.setRotation(this.jawbase7, 0.2230705f, 0.0f, 0.0f);
        this.jawbase8 = new ModelRenderer(this, 104, 150);
        this.jawbase8.addBox(-2.0f, -2.0f, -40.0f, 4, 4, 40);
        this.jawbase8.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase8.mirror = true;
        this.setRotation(this.jawbase8, 0.2146755f, 0.1487144f, -0.7679449f);
        this.jawbase9 = new ModelRenderer(this, 104, 112);
        this.jawbase9.addBox(-3.0f, -3.0f, -21.0f, 6, 6, 29);
        this.jawbase9.setPos(0.0f, 0.0f, -8.0f);
        this.jawbase9.mirror = true;
        this.setRotation(this.jawbase9, 0.1031397f, 0.0743623f, -0.837758f);
        this.jawleft = new ModelRenderer(this, 76, 61);
        this.jawleft.addBox(-14.0f, -10.0f, -37.0f, 1, 11, 1);
        this.jawleft.setPos(0.0f, 0.0f, -8.0f);
        this.jawleft.mirror = true;
        this.setRotation(this.jawleft, 0.2230717f, -0.3346075f, 0.0f);
        this.jawright = new ModelRenderer(this, 71, 61);
        this.jawright.addBox(13.0f, -10.0f, -37.0f, 1, 11, 1);
        this.jawright.setPos(0.0f, 0.0f, -8.0f);
        this.jawright.mirror = true;
        this.setRotation(this.jawright, 0.2230717f, 0.3346145f, 0.0f);
        this.jawend = new ModelRenderer(this, 76, 47);
        this.jawend.addBox(-0.5f, -11.0f, -39.5f, 1, 12, 1);
        this.jawend.setPos(0.0f, 0.0f, -8.0f);
        this.jawend.mirror = true;
        this.setRotation(this.jawend, 0.2230717f, 0.0f, 0.0f);
        this.headstart = new ModelRenderer(this, 92, 87);
        this.headstart.addBox(-6.5f, -2.0f, -7.0f, 13, 9, 13);
        this.headstart.setPos(0.0f, -9.0f, 2.0f);
        this.headstart.mirror = true;
        this.setRotation(this.headstart, 0.0f, 0.0f, 0.0f);
        this.headbase = new ModelRenderer(this, 206, 0);
        this.headbase.addBox(-8.0f, -2.0f, -6.0f, 16, 13, 24);
        this.headbase.setPos(0.0f, -17.0f, -2.0f);
        this.headbase.mirror = true;
        this.setRotation(this.headbase, 0.0f, 0.0f, 0.0f);
        this.headbase2 = new ModelRenderer(this, 300, 136);
        this.headbase2.addBox(-8.5f, -2.0f, -6.0f, 17, 1, 34);
        this.headbase2.setPos(0.0f, -17.0f, -3.0f);
        this.headbase2.mirror = true;
        this.setRotation(this.headbase2, 0.0f, 0.0f, 0.0f);
        this.headbase3 = new ModelRenderer(this, 300, 129);
        this.headbase3.addBox(-9.0f, -2.0f, -6.0f, 18, 1, 39);
        this.headbase3.setPos(0.0f, -18.0f, -4.0f);
        this.headbase3.mirror = true;
        this.setRotation(this.headbase3, 0.0f, 0.0f, 0.0f);
        this.headbase4 = new ModelRenderer(this, 300, 121);
        this.headbase4.addBox(-9.5f, -2.0f, -6.0f, 19, 1, 45);
        this.headbase4.setPos(0.0f, -19.0f, -5.0f);
        this.headbase4.mirror = true;
        this.setRotation(this.headbase4, 0.0f, 0.0f, 0.0f);
        this.headbase5 = new ModelRenderer(this, 300, 117);
        this.headbase5.addBox(-10.0f, -2.0f, -6.0f, 20, 1, 47);
        this.headbase5.setPos(0.0f, -20.0f, -6.0f);
        this.headbase5.mirror = true;
        this.setRotation(this.headbase5, 0.0f, 0.0f, 0.0f);
        this.headbase6 = new ModelRenderer(this, 300, 112);
        this.headbase6.addBox(-11.0f, -2.0f, -6.0f, 22, 1, 50);
        this.headbase6.setPos(0.0f, -21.0f, -9.0f);
        this.headbase6.mirror = true;
        this.setRotation(this.headbase6, 0.0f, 0.0f, 0.0f);
        this.headbase7 = new ModelRenderer(this, 0, 123);
        this.headbase7.addBox(-8.0f, -7.0f, 0.0f, 16, 8, 24);
        this.headbase7.setPos(0.0f, -8.0f, 13.0f);
        this.headbase7.mirror = true;
        this.setRotation(this.headbase7, 0.5576792f, 0.0f, 0.0f);
        this.headbase8 = new ModelRenderer(this, 300, 109);
        this.headbase8.addBox(-11.0f, -1.0f, -6.0f, 22, 1, 51);
        this.headbase8.setPos(0.0f, -23.0f, -9.0f);
        this.headbase8.mirror = true;
        this.setRotation(this.headbase8, 0.0f, 0.0f, 0.0f);
        this.headbase9 = new ModelRenderer(this, 300, 50);
        this.headbase9.addBox(-11.0f, -1.0f, -6.0f, 22, 1, 51);
        this.headbase9.setPos(0.0f, -24.0f, -9.0f);
        this.headbase9.mirror = true;
        this.setRotation(this.headbase9, 0.0f, 0.0f, 0.0f);
        this.headbase10 = new ModelRenderer(this, 185, 107);
        this.headbase10.addBox(-7.0f, -1.5f, -20.0f, 14, 1, 42);
        this.headbase10.setPos(0.0f, -24.0f, 7.0f);
        this.headbase10.mirror = true;
        this.setRotation(this.headbase10, 0.0261799f, 0.0f, 0.0f);
        this.headleftridge = new ModelRenderer(this, 180, 153);
        this.headleftridge.addBox(-12.0f, -1.0f, -1.0f, 13, 1, 46);
        this.headleftridge.setPos(0.0f, -22.0f, -9.0f);
        this.headleftridge.mirror = true;
        this.setRotation(this.headleftridge, 0.0743572f, -0.2230717f, 0.1487144f);
        this.headrightridge = new ModelRenderer(this, 300, 174);
        this.headrightridge.addBox(-1.0f, -1.0f, -1.0f, 13, 1, 46);
        this.headrightridge.setPos(0.0f, -22.0f, -9.0f);
        this.headrightridge.mirror = true;
        this.setRotation(this.headrightridge, 0.0743572f, 0.2230705f, -0.1487195f);
        this.antenna1 = new ModelRenderer(this, 332, 72);
        this.antenna1.addBox(-3.0f, -1.0f, -1.0f, 5, 2, 2);
        this.antenna1.setPos(-10.0f, -20.0f, 19.0f);
        this.antenna1.mirror = true;
        this.setRotation(this.antenna1, 0.0f, 0.0f, 0.0f);
        this.antenna1part2 = new ModelRenderer(this, 300, 81);
        this.antenna1part2.addBox(-3.0f, -1.0f, 1.0f, 2, 2, 9);
        this.antenna1part2.setPos(-10.0f, -20.0f, 19.0f);
        this.antenna1part2.mirror = true;
        this.setRotation(this.antenna1part2, 0.0f, -0.7807508f, 0.0f);
        this.antenna2 = new ModelRenderer(this, 325, 79);
        this.antenna2.addBox(-2.0f, -1.0f, -1.0f, 5, 2, 2);
        this.antenna2.setPos(10.0f, -20.0f, 19.0f);
        this.antenna2.mirror = true;
        this.setRotation(this.antenna2, 0.0f, 0.0f, 0.0f);
        this.antenna2part2 = new ModelRenderer(this, 300, 81);
        this.antenna2part2.addBox(1.0f, -1.0f, 1.0f, 2, 2, 9);
        this.antenna2part2.setPos(10.0f, -20.0f, 19.0f);
        this.antenna2part2.mirror = true;
        this.setRotation(this.antenna2part2, 0.0f, 0.7807556f, 0.0f);
        this.eyebase1 = new ModelRenderer(this, 300, 121);
        this.eyebase1.addBox(-2.5f, -2.5f, -1.5f, 5, 5, 3);
        this.eyebase1.setPos(-6.0f, -14.0f, -8.0f);
        this.eyebase1.mirror = true;
        this.setRotation(this.eyebase1, 0.0f, 0.7807556f, -0.37179f);
        this.eye1 = new ModelRenderer(this, 0, 0);
        this.eye1.addBox(-1.0f, -1.0f, -2.5f, 2, 2, 2);
        this.eye1.setPos(-6.0f, -14.0f, -8.0f);
        this.eye1.mirror = true;
        this.setRotation(this.eye1, 0.0f, 0.7807556f, -0.37179f);
        this.eyebase2 = new ModelRenderer(this, 300, 121);
        this.eyebase2.addBox(-2.5f, -2.5f, -1.5f, 5, 5, 3);
        this.eyebase2.setPos(6.0f, -14.0f, -8.0f);
        this.eyebase2.mirror = true;
        this.setRotation(this.eyebase2, 0.0f, -0.7807508f, 0.3717861f);
        this.eye2 = new ModelRenderer(this, 0, 0);
        this.eye2.addBox(-1.0f, -1.0f, -2.5f, 2, 2, 2);
        this.eye2.setPos(6.0f, -14.0f, -8.0f);
        this.eye2.mirror = true;
        this.setRotation(this.eye2, 0.0f, -0.7807508f, 0.3717861f);
        this.arm1start = new ModelRenderer(this, 300, 93);
        this.arm1start.addBox(-3.0f, -1.5f, -1.5f, 4, 3, 3);
        this.arm1start.setPos(-9.0f, -20.0f, -3.0f);
        this.arm1start.mirror = true;
        this.setRotation(this.arm1start, 0.0f, 0.0f, 0.0f);
        this.arm1part1 = new ModelRenderer(this, 250, 83);
        this.arm1part1.addBox(-2.0f, -1.5f, -16.5f, 3, 3, 18);
        this.arm1part1.setPos(-11.0f, -20.0f, -4.0f);
        this.arm1part1.mirror = true;
        this.setRotation(this.arm1part1, 0.0f, 0.2777036f, 0.0f);
        this.arm1part2 = new ModelRenderer(this, 250, 83);
        this.arm1part2.addBox(-2.0f, -9.5f, -1.5f, 3, 11, 3);
        this.arm1part2.setPos(-15.3f, -20.0f, -20.0f);
        this.arm1part2.mirror = true;
        this.setRotation(this.arm1part2, -0.7807508f, 0.0f, 0.0f);
        this.arm1part2b = new ModelRenderer(this, 250, 69);
        this.arm1part2b.addBox(-2.0f, -15.5f, -3.5f, 3, 8, 3);
        this.arm1part2b.setPos(-15.3f, -20.0f, -20.0f);
        this.arm1part2b.mirror = true;
        this.setRotation(this.arm1part2b, -1.041001f, 0.0f, 0.0f);
        this.arm1part2c = new ModelRenderer(this, 300, 66);
        this.arm1part2c.addBox(-2.0f, -17.5f, 8.5f, 3, 8, 3);
        this.arm1part2c.setPos(-15.3f, -20.0f, -19.0f);
        this.arm1part2c.mirror = true;
        this.setRotation(this.arm1part2c, -0.0743572f, 0.0f, 0.0f);
        this.arm1part3 = new ModelRenderer(this, 278, 60);
        this.arm1part3.addBox(-1.0f, -19.5f, -1.0f, 2, 21, 2);
        this.arm1part3.setPos(-15.8f, -36.0f, -8.0f);
        this.arm1part3.mirror = true;
        this.setRotation(this.arm1part3, 1.561502f, 0.0f, 0.0f);
        this.arm1part3b = new ModelRenderer(this, 279, 84);
        this.arm1part3b.addBox(-0.5f, -10.5f, -1.0f, 1, 12, 2);
        this.arm1part3b.setPos(-15.8f, -36.0f, -8.0f);
        this.arm1part3b.mirror = true;
        this.setRotation(this.arm1part3b, 1.710216f, 0.0f, 0.0f);
        this.arm2start = new ModelRenderer(this, 300, 105);
        this.arm2start.addBox(-1.0f, -1.5f, -1.5f, 4, 3, 3);
        this.arm2start.setPos(9.0f, -20.0f, -3.0f);
        this.arm2start.mirror = true;
        this.setRotation(this.arm2start, 0.0f, 0.0f, 0.0f);
        this.arm2part1 = new ModelRenderer(this, 250, 83);
        this.arm2part1.addBox(-2.0f, -1.5f, -16.5f, 3, 3, 18);
        this.arm2part1.setPos(11.0f, -20.0f, -4.0f);
        this.arm2part1.mirror = true;
        this.setRotation(this.arm2part1, 0.0f, -0.2776993f, 0.0f);
        this.arm2part2 = new ModelRenderer(this, 250, 83);
        this.arm2part2.addBox(-2.0f, -9.5f, -1.5f, 3, 11, 3);
        this.arm2part2.setPos(15.3f, -20.0f, -20.0f);
        this.arm2part2.mirror = true;
        this.setRotation(this.arm2part2, -0.7807508f, 0.0f, 0.0f);
        this.arm2part2b = new ModelRenderer(this, 250, 69);
        this.arm2part2b.addBox(-2.0f, -15.5f, -3.5f, 3, 8, 3);
        this.arm2part2b.setPos(15.3f, -20.0f, -20.0f);
        this.arm2part2b.mirror = true;
        this.setRotation(this.arm2part2b, -1.041001f, 0.0f, 0.0f);
        this.arm2part2c = new ModelRenderer(this, 300, 66);
        this.arm2part2c.addBox(-2.0f, -17.5f, 8.5f, 3, 8, 3);
        this.arm2part2c.setPos(15.3f, -20.0f, -19.0f);
        this.arm2part2c.mirror = true;
        this.setRotation(this.arm2part2c, -0.0743572f, 0.0f, 0.0f);
        this.arm2part3 = new ModelRenderer(this, 287, 60);
        this.arm2part3.addBox(-1.0f, -19.5f, -1.0f, 2, 21, 2);
        this.arm2part3.setPos(14.8f, -36.0f, -8.0f);
        this.arm2part3.mirror = true;
        this.setRotation(this.arm2part3, 1.561502f, 0.0f, 0.0f);
        this.arm2part3b = new ModelRenderer(this, 279, 84);
        this.arm2part3b.addBox(-0.5f, -10.5f, -1.0f, 1, 12, 2);
        this.arm2part3b.setPos(14.8f, -36.0f, -8.0f);
        this.arm2part3b.mirror = true;
        this.setRotation(this.arm2part3b, 1.710216f, 0.0f, 0.0f);
        this.innermouth = new ModelRenderer(this, 176, 44);
        this.innermouth.addBox(-6.0f, -2.0f, -8.0f, 12, 3, 8);
        this.innermouth.setPos(0.0f, 0.0f, -12.0f);
        this.innermouth.mirror = true;
        this.setRotation(this.innermouth, -1.933289f, 0.0f, 0.0f);
        this.innermouth2 = new ModelRenderer(this, 159, 4);
        this.innermouth2.addBox(-6.0f, -2.0f, -8.0f, 12, 3, 8);
        this.innermouth2.setPos(0.0f, -24.0f, -12.0f);
        this.innermouth2.mirror = true;
        this.setRotation(this.innermouth2, 1.933284f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer(this, 0, 6);
        this.tooth1.addBox(-5.0f, 0.0f, -10.0f, 2, 1, 2);
        this.tooth1.setPos(1.0f, 0.0f, -12.0f);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, -1.933289f, 0.0f, 0.0f);
        this.tooth2 = new ModelRenderer(this, 0, 6);
        this.tooth2.addBox(1.0f, 0.0f, -10.0f, 2, 1, 2);
        this.tooth2.setPos(1.0f, 0.0f, -12.0f);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, -1.933289f, 0.0f, 0.0f);
        this.tooth3 = new ModelRenderer(this, 0, 6);
        this.tooth3.addBox(1.0f, 0.0f, -10.0f, 2, 1, 2);
        this.tooth3.setPos(-2.0f, 0.0f, -12.0f);
        this.tooth3.mirror = true;
        this.setRotation(this.tooth3, -1.933289f, 0.0f, 0.0f);
        this.tooth4 = new ModelRenderer(this, 0, 6);
        this.tooth4.addBox(-4.0f, -2.0f, -10.0f, 2, 1, 2);
        this.tooth4.setPos(0.0f, -24.0f, -12.0f);
        this.tooth4.mirror = true;
        this.setRotation(this.tooth4, 1.933284f, 0.0f, 0.0f);
        this.tooth5 = new ModelRenderer(this, 0, 6);
        this.tooth5.addBox(2.0f, -2.0f, -10.0f, 2, 1, 2);
        this.tooth5.setPos(0.0f, -24.0f, -12.0f);
        this.tooth5.mirror = true;
        this.setRotation(this.tooth5, 1.933284f, 0.0f, 0.0f);
        this.tooth6 = new ModelRenderer(this, 0, 6);
        this.tooth6.addBox(-1.0f, -2.0f, -10.0f, 2, 1, 2);
        this.tooth6.setPos(0.0f, -24.0f, -12.0f);
        this.tooth6.mirror = true;
        this.setRotation(this.tooth6, 1.933284f, 0.0f, 0.0f);
        this.mouthedge1 = new ModelRenderer(this, 400, 77);
        this.mouthedge1.addBox(-1.0f, -4.5f, -1.5f, 3, 9, 14);
        this.mouthedge1.setPos(9.0f, -12.0f, -6.0f);
        this.mouthedge1.mirror = true;
        this.setRotation(this.mouthedge1, 0.0f, -0.2230717f, 0.0f);
        this.mouthedge2 = new ModelRenderer(this, 400, 52);
        this.mouthedge2.addBox(-2.0f, -4.5f, -1.5f, 3, 9, 14);
        this.mouthedge2.setPos(-9.0f, -12.0f, -6.0f);
        this.mouthedge2.mirror = true;
        this.setRotation(this.mouthedge2, 0.0f, 0.2230717f, 0.0f);
        this.mouthedge3 = new ModelRenderer(this, 450, 69);
        this.mouthedge3.addBox(-1.0f, -4.5f, -1.5f, 3, 9, 14);
        this.mouthedge3.setPos(8.0f, -6.0f, -7.0f);
        this.mouthedge3.mirror = true;
        this.setRotation(this.mouthedge3, 0.5576792f, -0.2230717f, 0.0f);
        this.mouthedge4 = new ModelRenderer(this, 450, 93);
        this.mouthedge4.addBox(-2.0f, -4.5f, -1.5f, 3, 9, 14);
        this.mouthedge4.setPos(-8.0f, -6.0f, -7.0f);
        this.mouthedge4.mirror = true;
        this.setRotation(this.mouthedge4, 0.5576792f, 0.2230705f, 0.0f);
        this.arm3start = new ModelRenderer(this, 335, 85);
        this.arm3start.addBox(-1.0f, -1.5f, -1.5f, 4, 3, 3);
        this.arm3start.setPos(10.0f, -9.0f, -3.0f);
        this.arm3start.mirror = true;
        this.setRotation(this.arm3start, 0.0f, 0.0f, 0.0f);
        this.arm3part1 = new ModelRenderer(this, 264, 108);
        this.arm3part1.addBox(-1.0f, -1.5f, -9.5f, 3, 3, 11);
        this.arm3part1.setPos(13.0f, -9.0f, -3.0f);
        this.arm3part1.mirror = true;
        this.setRotation(this.arm3part1, -0.2602503f, 0.0f, 0.0f);
        this.arm3part1b = new ModelRenderer(this, 317, 67);
        this.arm3part1b.addBox(0.0f, -2.5f, -12.5f, 3, 5, 4);
        this.arm3part1b.setPos(13.0f, -9.0f, -3.0f);
        this.arm3part1b.mirror = true;
        this.setRotation(this.arm3part1b, -0.2602503f, 0.0f, 0.0f);
        this.arm3part1c = new ModelRenderer(this, 300, 81);
        this.arm3part1c.addBox(-2.0f, -2.5f, -12.51f, 3, 5, 0);
        this.arm3part1c.setPos(13.0f, -9.0f, -3.0f);
        this.arm3part1c.mirror = true;
        this.setRotation(this.arm3part1c, -0.2602503f, 0.0f, 0.0f);
        this.arm4start = new ModelRenderer(this, 335, 93);
        this.arm4start.addBox(-3.0f, -1.5f, -1.5f, 4, 3, 3);
        this.arm4start.setPos(-10.0f, -9.0f, -3.0f);
        this.arm4start.mirror = true;
        this.setRotation(this.arm4start, 0.0f, 0.0f, 0.0f);
        this.arm4part1 = new ModelRenderer(this, 300, 51);
        this.arm4part1.addBox(-2.0f, -1.5f, -9.5f, 3, 3, 11);
        this.arm4part1.setPos(-13.0f, -9.0f, -3.0f);
        this.arm4part1.mirror = true;
        this.setRotation(this.arm4part1, -0.2602503f, 0.0f, 0.0f);
        this.arm4part1b = new ModelRenderer(this, 322, 106);
        this.arm4part1b.addBox(-3.0f, -2.5f, -12.5f, 3, 5, 4);
        this.arm4part1b.setPos(-13.0f, -9.0f, -3.0f);
        this.arm4part1b.mirror = true;
        this.setRotation(this.arm4part1b, -0.2602503f, 0.0f, 0.0f);
        this.arm4part1c = new ModelRenderer(this, 316, 81);
        this.arm4part1c.addBox(-1.0f, -2.5f, -12.51f, 3, 5, 0);
        this.arm4part1c.setPos(-13.0f, -9.0f, -3.0f);
        this.arm4part1c.mirror = true;
        this.setRotation(this.arm4part1c, -0.2602503f, 0.0f, 0.0f);
        this.tongue = new ModelRenderer(this, 0, 49);
        this.tongue.addBox(-1.0f, -1.0f, -10.0f, 2, 2, 11);
        this.tongue.setPos(0.0f, -12.0f, -8.0f);
        this.tongue.mirror = true;
        this.setRotation(this.tongue, 0.0f, 0.0f, 0.0f);
        this.tonguepart2 = new ModelRenderer(this, 0, 12);
        this.tonguepart2.addBox(-1.0f, -1.5f, -8.0f, 2, 3, 1);
        this.tonguepart2.setPos(0.0f, -12.0f, -8.0f);
        this.tonguepart2.mirror = true;
        this.setRotation(this.tonguepart2, 0.0f, 0.0f, 0.0f);
        this.upperjawridgeleft = new ModelRenderer(this, 350, 27);
        this.upperjawridgeleft.addBox(-3.0f, -4.0f, -2.0f, 5, 1, 18);
        this.upperjawridgeleft.setPos(0.0f, -22.0f, -33.0f);
        this.upperjawridgeleft.mirror = true;
        this.setRotation(this.upperjawridgeleft, 0.2602503f, -0.3717861f, 0.0f);
        this.upperjawridgeright = new ModelRenderer(this, 350, 27);
        this.upperjawridgeright.addBox(-2.0f, -4.0f, -2.0f, 5, 1, 18);
        this.upperjawridgeright.setPos(0.0f, -22.0f, -33.0f);
        this.upperjawridgeright.mirror = true;
        this.setRotation(this.upperjawridgeright, 0.260246f, 0.37179f, 0.0f);
        this.upperjawbaseleft = new ModelRenderer(this, 235, 44);
        this.upperjawbaseleft.addBox(-5.0f, -3.0f, -16.0f, 3, 4, 14);
        this.upperjawbaseleft.setPos(0.0f, -22.0f, -35.0f);
        this.upperjawbaseleft.mirror = true;
        this.setRotation(this.upperjawbaseleft, 0.0f, -0.2230717f, 0.0f);
        this.upperjawbaseright = new ModelRenderer(this, 210, 68);
        this.upperjawbaseright.addBox(2.0f, -3.0f, -16.0f, 3, 4, 14);
        this.upperjawbaseright.setPos(0.0f, -22.0f, -35.0f);
        this.upperjawbaseright.mirror = true;
        this.setRotation(this.upperjawbaseright, 0.0f, 0.2230767f, 0.0f);
        this.upperjawbase = new ModelRenderer(this, 146, 76);
        this.upperjawbase.addBox(-5.0f, -3.0f, -29.0f, 10, 4, 29);
        this.upperjawbase.setPos(0.0f, -22.0f, -10.0f);
        this.upperjawbase.mirror = true;
        this.setRotation(this.upperjawbase, 0.0f, 0.0f, 0.0f);
        this.upperjawbase2 = new ModelRenderer(this, 168, 111);
        this.upperjawbase2.addBox(-5.0f, -4.0f, -2.0f, 10, 5, 18);
        this.upperjawbase2.setPos(0.0f, -22.0f, -33.0f);
        this.upperjawbase2.mirror = true;
        this.setRotation(this.upperjawbase2, 0.2602503f, 0.0f, 0.0f);
        this.upperjawbase3 = new ModelRenderer(this, 300, 0);
        this.upperjawbase3.addBox(-1.5f, -3.01f, -16.0f, 3, 3, 14);
        this.upperjawbase3.setPos(0.0f, -22.0f, -35.0f);
        this.upperjawbase3.mirror = true;
        this.setRotation(this.upperjawbase3, 0.0f, 5.1E-6f, 0.0f);
        this.upperjawbase4 = new ModelRenderer(this, 45, 161);
        this.upperjawbase4.addBox(-2.0f, -2.0f, -29.0f, 4, 4, 41);
        this.upperjawbase4.setPos(0.0f, -22.0f, -22.0f);
        this.upperjawbase4.mirror = true;
        this.setRotation(this.upperjawbase4, 0.0698132f, -0.0488692f, 0.7807508f);
        this.upperjawend = new ModelRenderer(this, 160, 57);
        this.upperjawend.addBox(-0.5f, 0.0f, -0.5f, 1, 10, 1);
        this.upperjawend.setPos(0.0f, -20.0f, -50.0f);
        this.upperjawend.mirror = true;
        this.setRotation(this.upperjawend, 0.0f, 5.1E-6f, 0.0f);
        this.upperjawleftend = new ModelRenderer(this, 171, 58);
        this.upperjawleftend.addBox(-0.5f, 0.0f, -0.5f, 1, 9, 1);
        this.upperjawleftend.setPos(-1.0f, -20.0f, -50.0f);
        this.upperjawleftend.mirror = true;
        this.setRotation(this.upperjawleftend, -0.1115358f, -1.52433f, 0.0f);
        this.upperjawrightend = new ModelRenderer(this, 165, 58);
        this.upperjawrightend.addBox(-0.5f, 0.0f, -0.5f, 1, 9, 1);
        this.upperjawrightend.setPos(1.0f, -20.0f, -50.0f);
        this.upperjawrightend.mirror = true;
        this.setRotation(this.upperjawrightend, -0.1115358f, 1.52433f, 0.0f);
    }
    @Override
    public void setupAnim(TrooperBug entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        TrooperBug e = (TrooperBug)entity;
        java.lang.Object r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float pi4 = 1.570795f;
        newangle = e.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.4f * this.wingspeed)) * 3.1415927f * 0.05f : MathHelper.cos((float)(f2 * 1.4f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.antenna2part2.yRot = 0.78f + newangle;
        this.antenna1part2.yRot = -0.78f - newangle;
        newangle = e.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f : MathHelper.cos((float)(f2 * 2.5f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.arm4part1.yRot = newangle;
        this.arm4part1b.yRot = newangle;
        this.arm4part1c.yRot = newangle;
        this.arm3part1.yRot = - newangle;
        this.arm3part1b.yRot = - newangle;
        this.arm3part1c.yRot = - newangle;
        newangle = e.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.05f : MathHelper.cos((float)(f2 * 2.6f * this.wingspeed)) * 3.1415927f * 0.2f;
        this.arm1part3.xRot = 1.56f + newangle;
        this.arm1part3b.xRot = 1.56f + newangle;
        this.arm2part3.xRot = 1.56f - newangle;
        this.arm2part3b.xRot = 1.56f - newangle;
        newangle = e.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.1f * this.wingspeed)) * 3.1415927f * 0.02f : MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.headleftridge.yRot = -0.25f + newangle;
        this.headrightridge.yRot = 0.25f - newangle;
        this.upperjawridgeleft.yRot = -0.372f + newangle;
        this.upperjawridgeright.yRot = 0.372f - newangle;
        newangle = e.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.015f : MathHelper.cos((float)(f2 * 2.6f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.jawbase.xRot = 0.22f + newangle;
        this.jawbase2.xRot = 0.22f + newangle;
        this.jawbase3.xRot = 0.22f + newangle;
        this.jawbase4.xRot = 0.22f + newangle;
        this.jawbase5.xRot = 0.22f + newangle;
        this.jawbase6.xRot = 0.22f + newangle;
        this.jawbase7.xRot = 0.22f + newangle;
        this.jawbase8.xRot = 0.2146f + newangle;
        this.jawbase8.yRot = 0.1487f + newangle;
        this.jawbase9.xRot = 0.1f + newangle;
        this.jawbase9.yRot = 0.07f + newangle;
        this.jawend.xRot = 0.22f + newangle;
        this.jawleft.xRot = 0.22f + newangle;
        this.jawright.xRot = 0.22f + newangle;
        newangle = MathHelper.sin((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        nextangle = MathHelper.sin((float)((f2 + 0.1f) * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = Math.abs(MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1);
        }
        this.doLeftFrontLeg(newangle, upangle);
        this.doLeftRearLeg(- newangle, upangle);
        newangle = MathHelper.sin((float)((float)((double)(f2 * 2.0f * this.wingspeed) + 3.141592653589793))) * 3.1415927f * 0.12f * f1;
        nextangle = MathHelper.sin((float)((float)((double)((f2 + 0.1f) * 2.0f * this.wingspeed) + 3.141592653589793))) * 3.1415927f * 0.12f * f1;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = Math.abs(MathHelper.cos((float)((float)((double)(f2 * 2.0f * this.wingspeed) + 3.141592653589793))) * 3.1415927f * 0.12f * f1);
        }
        this.doRightFrontLeg(- newangle, upangle);
        this.doRightRearLeg(newangle, upangle);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.legintersection.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.legintersectionb.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1shoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1shoulderpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part1b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1elbow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2shoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2shoulderpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part1b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2elbow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3shoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3shoulderpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part1b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3elbow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4shoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4shoulderpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part1b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4elbow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawbase9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawleft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawright.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawend.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headstart.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headbase10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headleftridge.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headrightridge.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.antenna1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.antenna1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.antenna2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.antenna2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eyebase1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eye1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eyebase2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eye2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.innermouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.innermouth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouthedge1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouthedge2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouthedge3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouthedge4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm3start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm3part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm3part1b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm3part1c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm4start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm4part1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm4part1b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm4part1c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tongue.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tonguepart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawridgeleft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawridgeright.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbaseleft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbaseright.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbase2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbase3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbase4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawend.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawleftend.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawrightend.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, TrooperBug par7Entity) {
        
    }

    private void doRightFrontLeg(float angle, float upangle) {
        this.leg1part1b.yRot = this.leg1part1.yRot = 1.2f + angle;
        this.leg1elbow.yRot = this.leg1part1.yRot;
        this.leg1part2.yRot = this.leg1part1.yRot;
        this.leg1part2b.yRot = this.leg1part1.yRot;
        this.leg1part2c.yRot = this.leg1part1.yRot;
        this.leg1part3.yRot = this.leg1part1.yRot;
        this.leg1part3b.yRot = this.leg1part1.yRot;
        this.leg1part3c.yRot = this.leg1part1.yRot;
        this.leg1part3d.yRot = this.leg1part1.yRot;
        this.leg1part1.xRot = 1.115f + upangle;
        this.leg1part1b.xRot = 1.078f + upangle;
        this.leg1elbow.xRot = this.leg1part1.xRot;
        float dist = 26.0f;
        dist = (float)((double)dist * Math.cos(this.leg1part1.xRot));
        this.leg1part2b.z = this.leg1part2c.z = (float)((double)this.leg1part1.z - Math.cos(this.leg1part1.yRot) * (double)dist);
        this.leg1part2.z = this.leg1part2c.z;
        this.leg1part2b.x = this.leg1part2c.x = (float)((double)this.leg1part1.x - Math.sin(this.leg1part1.yRot) * (double)dist);
        this.leg1part2.x = this.leg1part2c.x;
        this.leg1part2.xRot = 1.871f - upangle;
        this.leg1part2b.xRot = 1.817f - upangle;
        this.leg1part2c.xRot = 1.762f - upangle;
        dist = 32.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg1part2.xRot));
        this.leg1part3c.z = this.leg1part3d.z = (float)((double)this.leg1part2.z - Math.cos(this.leg1part2.yRot) * (double)dist);
        this.leg1part3b.z = this.leg1part3d.z;
        this.leg1part3.z = this.leg1part3d.z;
        this.leg1part3c.x = this.leg1part3d.x = (float)((double)this.leg1part2.x - Math.sin(this.leg1part2.yRot) * (double)dist);
        this.leg1part3b.x = this.leg1part3d.x;
        this.leg1part3.x = this.leg1part3d.x;
        this.leg1part3.xRot = 1.08f + upangle;
        this.leg1part3b.xRot = 1.08f + upangle;
        this.leg1part3c.xRot = 1.08f + upangle;
        this.leg1part3d.xRot = 1.08f + upangle;
    }

    private void doLeftFrontLeg(float angle, float upangle) {
        this.leg2part1b.yRot = this.leg2part1.yRot = -1.2f + angle;
        this.leg2elbow.yRot = this.leg2part1.yRot;
        this.leg2part2.yRot = this.leg2part1.yRot;
        this.leg2part2b.yRot = this.leg2part1.yRot;
        this.leg2part2c.yRot = this.leg2part1.yRot;
        this.leg2part3.yRot = this.leg2part1.yRot;
        this.leg2part3b.yRot = this.leg2part1.yRot;
        this.leg2part3c.yRot = this.leg2part1.yRot;
        this.leg2part3d.yRot = this.leg2part1.yRot;
        this.leg2part1.xRot = 1.115f + upangle;
        this.leg2part1b.xRot = 1.078f + upangle;
        this.leg2elbow.xRot = this.leg2part1.xRot;
        float dist = 26.0f;
        dist = (float)((double)dist * Math.cos(this.leg2part1.xRot));
        this.leg2part2b.z = this.leg2part2c.z = (float)((double)this.leg2part1.z - Math.cos(this.leg2part1.yRot) * (double)dist);
        this.leg2part2.z = this.leg2part2c.z;
        this.leg2part2b.x = this.leg2part2c.x = (float)((double)this.leg2part1.x - Math.sin(this.leg2part1.yRot) * (double)dist);
        this.leg2part2.x = this.leg2part2c.x;
        this.leg2part2.xRot = 1.871f - upangle;
        this.leg2part2b.xRot = 1.817f - upangle;
        this.leg2part2c.xRot = 1.762f - upangle;
        dist = 32.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg2part2.xRot));
        this.leg2part3c.z = this.leg2part3d.z = (float)((double)this.leg2part2.z - Math.cos(this.leg2part2.yRot) * (double)dist);
        this.leg2part3b.z = this.leg2part3d.z;
        this.leg2part3.z = this.leg2part3d.z;
        this.leg2part3c.x = this.leg2part3d.x = (float)((double)this.leg2part2.x - Math.sin(this.leg2part2.yRot) * (double)dist);
        this.leg2part3b.x = this.leg2part3d.x;
        this.leg2part3.x = this.leg2part3d.x;
        this.leg2part3.xRot = 1.08f + upangle;
        this.leg2part3b.xRot = 1.08f + upangle;
        this.leg2part3c.xRot = 1.08f + upangle;
        this.leg2part3d.xRot = 1.08f + upangle;
    }

    private void doRightRearLeg(float angle, float upangle) {
        this.leg4part1b.yRot = this.leg4part1.yRot = -1.2f + angle;
        this.leg4elbow.yRot = this.leg4part1.yRot;
        this.leg4part2.yRot = this.leg4part1.yRot;
        this.leg4part2b.yRot = this.leg4part1.yRot;
        this.leg4part2c.yRot = this.leg4part1.yRot;
        this.leg4part3.yRot = this.leg4part1.yRot;
        this.leg4part3b.yRot = this.leg4part1.yRot;
        this.leg4part3c.yRot = this.leg4part1.yRot;
        this.leg4part3d.yRot = this.leg4part1.yRot;
        this.leg4part1.xRot = -1.115f + upangle;
        this.leg4part1b.xRot = -1.078f + upangle;
        this.leg4elbow.xRot = this.leg4part1.xRot;
        float dist = 26.0f;
        dist = (float)((double)dist * Math.cos(this.leg4part1.xRot));
        this.leg4part2b.z = this.leg4part2c.z = (float)((double)this.leg4part1.z + Math.cos(this.leg4part1.yRot) * (double)dist);
        this.leg4part2.z = this.leg4part2c.z;
        this.leg4part2b.x = this.leg4part2c.x = (float)((double)this.leg4part1.x + Math.sin(this.leg4part1.yRot) * (double)dist);
        this.leg4part2.x = this.leg4part2c.x;
        this.leg4part2.xRot = -1.871f - upangle;
        this.leg4part2b.xRot = -1.817f - upangle;
        this.leg4part2c.xRot = -1.762f - upangle;
        dist = 32.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg4part2.xRot));
        this.leg4part3c.z = this.leg4part3d.z = (float)((double)this.leg4part2.z + Math.cos(this.leg4part2.yRot) * (double)dist);
        this.leg4part3b.z = this.leg4part3d.z;
        this.leg4part3.z = this.leg4part3d.z;
        this.leg4part3c.x = this.leg4part3d.x = (float)((double)this.leg4part2.x + Math.sin(this.leg4part2.yRot) * (double)dist);
        this.leg4part3b.x = this.leg4part3d.x;
        this.leg4part3.x = this.leg4part3d.x;
        this.leg4part3.xRot = -1.08f + upangle;
        this.leg4part3b.xRot = -1.08f + upangle;
        this.leg4part3c.xRot = -1.08f + upangle;
        this.leg4part3d.xRot = -1.08f + upangle;
    }

    private void doLeftRearLeg(float angle, float upangle) {
        this.leg3part1b.yRot = this.leg3part1.yRot = 1.2f + angle;
        this.leg3elbow.yRot = this.leg3part1.yRot;
        this.leg3part2.yRot = this.leg3part1.yRot;
        this.leg3part2b.yRot = this.leg3part1.yRot;
        this.leg3part2c.yRot = this.leg3part1.yRot;
        this.leg3part3.yRot = this.leg3part1.yRot;
        this.leg3part3b.yRot = this.leg3part1.yRot;
        this.leg3part3c.yRot = this.leg3part1.yRot;
        this.leg3part3d.yRot = this.leg3part1.yRot;
        this.leg3part1.xRot = -1.115f + upangle;
        this.leg3part1b.xRot = -1.078f + upangle;
        this.leg3elbow.xRot = this.leg3part1.xRot;
        float dist = 26.0f;
        dist = (float)((double)dist * Math.cos(this.leg3part1.xRot));
        this.leg3part2b.z = this.leg3part2c.z = (float)((double)this.leg3part1.z + Math.cos(this.leg3part1.yRot) * (double)dist);
        this.leg3part2.z = this.leg3part2c.z;
        this.leg3part2b.x = this.leg3part2c.x = (float)((double)this.leg3part1.x + Math.sin(this.leg3part1.yRot) * (double)dist);
        this.leg3part2.x = this.leg3part2c.x;
        this.leg3part2.xRot = -1.871f - upangle;
        this.leg3part2b.xRot = -1.817f - upangle;
        this.leg3part2c.xRot = -1.762f - upangle;
        dist = 32.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg3part2.xRot));
        this.leg3part3c.z = this.leg3part3d.z = (float)((double)this.leg3part2.z + Math.cos(this.leg3part2.yRot) * (double)dist);
        this.leg3part3b.z = this.leg3part3d.z;
        this.leg3part3.z = this.leg3part3d.z;
        this.leg3part3c.x = this.leg3part3d.x = (float)((double)this.leg3part2.x + Math.sin(this.leg3part2.yRot) * (double)dist);
        this.leg3part3b.x = this.leg3part3d.x;
        this.leg3part3.x = this.leg3part3d.x;
        this.leg3part3.xRot = -1.08f + upangle;
        this.leg3part3b.xRot = -1.08f + upangle;
        this.leg3part3c.xRot = -1.08f + upangle;
        this.leg3part3d.xRot = -1.08f + upangle;
    }
}

