/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelThePrince
 *  com.astryxion.chaospersists.ThePrince
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.ThePrince;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelThePrince extends EntityModel<ThePrince> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer neck1;
    ModelRenderer neck;
    ModelRenderer neckbase;
    ModelRenderer head;
    ModelRenderer Rleg1;
    ModelRenderer Lleg1;
    ModelRenderer snout;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer Lwing;
    ModelRenderer Rwing;
    ModelRenderer Tail5;
    ModelRenderer Tail6;
    ModelRenderer Lneck1;
    ModelRenderer Lneck;
    ModelRenderer Lhead;
    ModelRenderer Lsnout;
    ModelRenderer Rneck1;
    ModelRenderer Rneck;
    ModelRenderer Rhead;
    ModelRenderer Rsnout;
    ModelRenderer headfin;
    ModelRenderer Lheadfin;
    ModelRenderer Rheadfin;
    ModelRenderer Backfin;
    ModelRenderer Rwing2;
    ModelRenderer Rwing3;
    ModelRenderer Lwing2;
    ModelRenderer Lwing3;
    ModelRenderer Ljaw;
    ModelRenderer jaw;
    ModelRenderer Rjaw;

    public ModelThePrince(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.body = new ModelRenderer(this, 59, 34);
        this.body.addBox(-7.0f, -3.0f, -5.0f, 13, 8, 10);
        this.body.setPos(0.5f, 15.0f, 1.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 20, 45);
        this.neck1.addBox(-1.5f, -2.0f, -1.0f, 3, 4, 4);
        this.neck1.setPos(0.0f, 16.0f, -5.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, 0.715585f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 20, 31);
        this.neck.addBox(-1.5f, -8.0f, -1.0f, 3, 8, 3);
        this.neck.setPos(0.0f, 15.0f, -6.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        this.neckbase = new ModelRenderer(this, 0, 76);
        this.neckbase.addBox(-4.5f, -4.0f, 0.0f, 9, 6, 3);
        this.neckbase.setPos(0.0f, 17.0f, 5.0f);
        this.neckbase.mirror = true;
        this.setRotation(this.neckbase, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 20, 20);
        this.head.addBox(-2.0f, -3.0f, -3.5f, 4, 4, 5);
        this.head.setPos(0.0f, 8.0f, -6.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.Rleg1 = new ModelRenderer(this, 0, 58);
        this.Rleg1.addBox(-1.5f, 0.0f, -2.0f, 3, 8, 4);
        this.Rleg1.setPos(6.0f, 16.0f, 1.0f);
        this.Rleg1.mirror = true;
        this.setRotation(this.Rleg1, 0.0f, 0.0f, 0.0f);
        this.Lleg1 = new ModelRenderer(this, 15, 58);
        this.Lleg1.addBox(-1.5f, 0.0f, -2.0f, 3, 8, 4);
        this.Lleg1.setPos(-6.0f, 16.0f, 1.0f);
        this.Lleg1.mirror = true;
        this.setRotation(this.Lleg1, 0.0f, 0.0f, 0.0f);
        this.snout = new ModelRenderer(this, 20, 11);
        this.snout.addBox(-1.5f, -2.0f, -8.5f, 3, 3, 5);
        this.snout.setPos(0.0f, 8.0f, -6.0f);
        this.snout.mirror = true;
        this.setRotation(this.snout, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 59, 55);
        this.tail1.addBox(-6.0f, -3.0f, -3.0f, 12, 5, 3);
        this.tail1.setPos(0.0f, 16.5f, -2.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 0, 86);
        this.tail2.addBox(-3.0f, -2.5f, 0.0f, 6, 4, 7);
        this.tail2.setPos(0.0f, 16.0f, 7.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, -0.3839724f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 0, 98);
        this.tail3.addBox(-2.0f, -2.0f, 0.0f, 4, 3, 6);
        this.tail3.setPos(0.0f, 18.2f, 13.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, -0.2094395f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 0, 108);
        this.tail4.addBox(-1.5f, -1.5f, 0.0f, 3, 2, 5);
        this.tail4.setPos(0.0f, 19.5f, 18.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, -0.0698132f, 0.0f, 0.0f);
        this.Lwing = new ModelRenderer(this, 59, 0);
        this.Lwing.addBox(-22.0f, 0.0f, -3.0f, 22, 0, 10);
        this.Lwing.setPos(-6.0f, 12.6f, 0.0f);
        this.Lwing.mirror = true;
        this.setRotation(this.Lwing, 0.0f, 0.0f, 0.4014257f);
        this.Rwing = new ModelRenderer(this, 59, 66);
        this.Rwing.addBox(0.0f, 0.0f, -3.0f, 22, 0, 10);
        this.Rwing.setPos(6.0f, 12.6f, 0.0f);
        this.Rwing.mirror = true;
        this.setRotation(this.Rwing, 0.0f, 0.0f, -0.4014257f);
        this.Tail5 = new ModelRenderer(this, 0, 116);
        this.Tail5.addBox(-3.0f, 0.0f, 0.0f, 6, 2, 4);
        this.Tail5.setPos(0.0f, 18.0f, 22.0f);
        this.Tail5.mirror = true;
        this.setRotation(this.Tail5, 0.0f, 0.0f, 0.0f);
        this.Tail6 = new ModelRenderer(this, 0, 123);
        this.Tail6.addBox(-1.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Tail6.setPos(0.0f, 18.0f, 26.0f);
        this.Tail6.mirror = true;
        this.setRotation(this.Tail6, 0.0f, 0.0f, 0.0f);
        this.Lneck1 = new ModelRenderer(this, 0, 45);
        this.Lneck1.addBox(-1.5f, -2.0f, -1.0f, 3, 4, 4);
        this.Lneck1.setPos(4.5f, 16.0f, -5.0f);
        this.Lneck1.mirror = true;
        this.setRotation(this.Lneck1, 0.715585f, 0.0f, 0.0f);
        this.Lneck = new ModelRenderer(this, 0, 30);
        this.Lneck.addBox(-1.5f, -8.0f, -1.0f, 3, 8, 3);
        this.Lneck.setPos(4.5f, 15.0f, -6.0f);
        this.Lneck.mirror = true;
        this.setRotation(this.Lneck, 0.0f, 0.0f, 0.0f);
        this.Lhead = new ModelRenderer(this, 0, 20);
        this.Lhead.addBox(-2.0f, -3.0f, -3.5f, 4, 4, 5);
        this.Lhead.setPos(4.5f, 8.0f, -6.0f);
        this.Lhead.mirror = true;
        this.setRotation(this.Lhead, -0.0174533f, 0.0f, 0.0f);
        this.Lsnout = new ModelRenderer(this, 0, 11);
        this.Lsnout.addBox(-1.5f, -2.0f, -8.5f, 3, 3, 5);
        this.Lsnout.setPos(4.5f, 8.0f, -6.0f);
        this.Lsnout.mirror = true;
        this.setRotation(this.Lsnout, 0.0f, 0.0f, 0.0f);
        this.Rneck1 = new ModelRenderer(this, 40, 45);
        this.Rneck1.addBox(-1.5f, -2.0f, -1.0f, 3, 4, 4);
        this.Rneck1.setPos(-4.5f, 16.0f, -5.0f);
        this.Rneck1.mirror = true;
        this.setRotation(this.Rneck1, 0.715585f, 0.0f, 0.0f);
        this.Rneck = new ModelRenderer(this, 40, 31);
        this.Rneck.addBox(-1.5f, -8.0f, -1.0f, 3, 8, 3);
        this.Rneck.setPos(-4.5f, 15.0f, -6.0f);
        this.Rneck.mirror = true;
        this.setRotation(this.Rneck, 0.0f, 0.0f, 0.0f);
        this.Rhead = new ModelRenderer(this, 40, 20);
        this.Rhead.addBox(-2.0f, -3.0f, -3.5f, 4, 4, 5);
        this.Rhead.setPos(-4.5f, 8.0f, -6.0f);
        this.Rhead.mirror = true;
        this.setRotation(this.Rhead, 0.0f, 0.0f, 0.0f);
        this.Rsnout = new ModelRenderer(this, 40, 11);
        this.Rsnout.addBox(-1.5f, -2.0f, -8.5f, 3, 3, 5);
        this.Rsnout.setPos(-4.5f, 8.0f, -6.0f);
        this.Rsnout.mirror = true;
        this.setRotation(this.Rsnout, 0.0f, 0.0f, 0.0f);
        this.headfin = new ModelRenderer(this, 20, 0);
        this.headfin.addBox(-0.5f, -3.0f, 1.0f, 1, 4, 3);
        this.headfin.setPos(0.0f, 8.0f, -6.0f);
        this.headfin.mirror = true;
        this.setRotation(this.headfin, -0.122173f, 0.0f, 0.0f);
        this.Lheadfin = new ModelRenderer(this, 0, 0);
        this.Lheadfin.addBox(-0.5f, -3.0f, 1.0f, 1, 4, 3);
        this.Lheadfin.setPos(4.5f, 8.0f, -6.0f);
        this.Lheadfin.mirror = true;
        this.setRotation(this.Lheadfin, -0.122173f, 0.0f, 0.0f);
        this.Rheadfin = new ModelRenderer(this, 40, 0);
        this.Rheadfin.addBox(-0.5f, -3.0f, 1.0f, 1, 4, 3);
        this.Rheadfin.setPos(-4.5f, 8.0f, -6.0f);
        this.Rheadfin.mirror = true;
        this.setRotation(this.Rheadfin, -0.122173f, 0.0f, 0.0f);
        this.Backfin = new ModelRenderer(this, 35, 57);
        this.Backfin.addBox(-0.5f, 0.0f, 0.0f, 1, 3, 5);
        this.Backfin.setPos(0.0f, 12.0f, -1.0f);
        this.Backfin.mirror = true;
        this.setRotation(this.Backfin, 0.5061455f, 0.0f, 0.0f);
        this.Rwing2 = new ModelRenderer(this, 59, 77);
        this.Rwing2.addBox(0.0f, 0.0f, -3.0f, 12, 0, 10);
        this.Rwing2.setPos(6.0f, 12.6f, 0.0f);
        this.Rwing2.mirror = true;
        this.setRotation(this.Rwing2, 0.0f, 0.0f, -0.6981317f);
        this.Rwing3 = new ModelRenderer(this, 59, 88);
        this.Rwing3.addBox(0.0f, 0.0f, -3.0f, 10, 0, 10);
        this.Rwing3.setPos(6.0f, 12.6f, 0.0f);
        this.Rwing3.mirror = true;
        this.setRotation(this.Rwing3, 0.0f, 0.0f, -0.0698132f);
        this.Lwing2 = new ModelRenderer(this, 59, 11);
        this.Lwing2.addBox(-12.0f, 0.0f, -3.0f, 12, 0, 10);
        this.Lwing2.setPos(-6.0f, 12.6f, 0.0f);
        this.Lwing2.mirror = true;
        this.setRotation(this.Lwing2, 0.0f, 0.0f, 0.6981317f);
        this.Lwing3 = new ModelRenderer(this, 59, 22);
        this.Lwing3.addBox(-10.0f, 0.0f, -3.0f, 10, 0, 10);
        this.Lwing3.setPos(-6.0f, 12.6f, 0.0f);
        this.Lwing3.mirror = true;
        this.setRotation(this.Lwing3, 0.0f, 0.0f, 0.0698132f);
        this.Ljaw = new ModelRenderer(this, 30, 70);
        this.Ljaw.addBox(-1.5f, 1.0f, -5.0f, 3, 1, 5);
        this.Ljaw.setPos(4.5f, 8.0f, -7.0f);
        this.Ljaw.mirror = true;
        this.setRotation(this.Ljaw, 0.2443461f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 30, 80);
        this.jaw.addBox(-1.5f, 1.0f, -5.0f, 3, 1, 5);
        this.jaw.setPos(0.0f, 8.0f, -7.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, 0.2443461f, 0.0f, 0.0f);
        this.Rjaw = new ModelRenderer(this, 30, 90);
        this.Rjaw.addBox(-1.5f, 1.0f, -5.0f, 3, 1, 5);
        this.Rjaw.setPos(-4.5f, 8.0f, -7.0f);
        this.Rjaw.mirror = true;
        this.setRotation(this.Rjaw, 0.2443461f, 0.0f, 0.0f);
    }

    @Override
    public void setupAnim(ThePrince c, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float h3;
        float d3;
        float hf = 0.0f;
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0625F;
        float newangle = 0.0f;
        int current_activity = c.getActivity();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, c);
        newangle = (double)f1 > 0.1 || c.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.4f * f1 : MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.04f;
        this.Rwing.zRot = newangle - 0.4f;
        this.Rwing2.zRot = newangle - 0.6f;
        this.Rwing3.zRot = newangle - 0.2f;
        this.Lwing.zRot = - newangle + 0.4f;
        this.Lwing2.zRot = - newangle + 0.6f;
        this.Lwing3.zRot = - newangle + 0.2f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        if (current_activity != 2 || c.getAttacking() != 0) {
            this.Rleg1.xRot = newangle;
            this.Lleg1.xRot = - newangle;
        } else {
            this.Rleg1.xRot = newangle = -1.0f;
            this.Lleg1.xRot = newangle;
        }
        newangle = MathHelper.cos((float)(f2 * 0.9f * this.wingspeed)) * 3.1415927f * 0.06f;
        if (c.isOrderedToSit()) {
            newangle = 0.0f;
        }
        if (c.getAttacking() != 0) {
            newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.12f;
        }
        this.tail2.yRot = newangle;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 6.0f;
        this.tail3.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 6.0f;
        this.tail3.yRot = newangle * 1.6f;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 5.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 5.0f;
        this.tail4.yRot = newangle * 2.6f;
        this.Tail5.z = this.tail4.z + (float)Math.cos(this.tail4.yRot) * 4.0f;
        this.Tail5.x = this.tail4.x + (float)Math.sin(this.tail4.yRot) * 4.0f;
        this.Tail5.yRot = newangle * 3.6f;
        this.Tail6.z = this.Tail5.z + (float)Math.cos(this.Tail5.yRot) * 4.0f;
        this.Tail6.x = this.Tail5.x + (float)Math.sin(this.Tail5.yRot) * 4.0f;
        this.Tail6.yRot = newangle * 4.6f;
        float h2 = h3 = f3 * 2.0f / 3.0f;
        float h1 = h3;
        float d2 = d3 = f4 * 2.0f / 3.0f;
        float d1 = d3;
        if (h1 < 0.0f) {
            h2 = h3 = h1 / 2.0f;
            d2 = d3 = d1 / 2.0f;
        } else {
            h2 = h1 = h3 / 2.0f;
            d2 = d1 = d3 / 2.0f;
        }
        this.head.yRot = (float)Math.toRadians(h2);
        this.snout.yRot = (float)Math.toRadians(h2);
        this.headfin.yRot = (float)Math.toRadians(h2);
        this.jaw.yRot = (float)Math.toRadians(h2);
        this.jaw.z = this.snout.z - (float)Math.cos(this.snout.yRot);
        this.jaw.x = this.snout.x - (float)Math.sin(this.snout.yRot);
        this.neck.yRot = (float)Math.toRadians(h2) / 2.0f;
        this.Lhead.yRot = (float)Math.toRadians(h1);
        this.Lsnout.yRot = (float)Math.toRadians(h1);
        this.Lheadfin.yRot = (float)Math.toRadians(h1);
        this.Ljaw.yRot = (float)Math.toRadians(h1);
        this.Ljaw.z = this.Lsnout.z - (float)Math.cos(this.Lsnout.yRot);
        this.Ljaw.x = this.Lsnout.x - (float)Math.sin(this.Lsnout.yRot);
        this.Lneck.yRot = (float)Math.toRadians(h1) / 2.0f;
        this.Rhead.yRot = (float)Math.toRadians(h3);
        this.Rsnout.yRot = (float)Math.toRadians(h3);
        this.Rheadfin.yRot = (float)Math.toRadians(h3);
        this.Rjaw.yRot = (float)Math.toRadians(h3);
        this.Rjaw.z = this.Rsnout.z - (float)Math.cos(this.Rsnout.yRot);
        this.Rjaw.x = this.Rsnout.x - (float)Math.sin(this.Rsnout.yRot);
        this.Rneck.yRot = (float)Math.toRadians(h3) / 2.0f;
        float Rjx = 0.0f;
        float jx = 0.0f;
        float Ljx = 0.0f;
        if (c.getAttacking() != 0) {
            newangle = MathHelper.cos((float)(f2 * 1.9f * this.wingspeed)) * 3.1415927f * 0.2f;
            Ljx = 0.2f + newangle;
            newangle = MathHelper.cos((float)(f2 * 2.1f * this.wingspeed)) * 3.1415927f * 0.2f;
            Rjx = 0.2f + newangle;
            newangle = MathHelper.cos((float)(f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.2f;
            jx = 0.2f + newangle;
        }
        this.head.xRot = (float)Math.toRadians(d2);
        this.snout.xRot = (float)Math.toRadians(d2);
        this.headfin.xRot = (float)Math.toRadians(d2);
        this.jaw.xRot = (float)Math.toRadians(d2) + jx;
        this.Lhead.xRot = (float)Math.toRadians(d1);
        this.Lsnout.xRot = (float)Math.toRadians(d1);
        this.Lheadfin.xRot = (float)Math.toRadians(d1);
        this.Ljaw.xRot = (float)Math.toRadians(d1) + Ljx;
        this.Rhead.xRot = (float)Math.toRadians(d3);
        this.Rsnout.xRot = (float)Math.toRadians(d3);
        this.Rheadfin.xRot = (float)Math.toRadians(d3);
        this.Rjaw.xRot = (float)Math.toRadians(d3) + Rjx;
        d1 = c.getHead1Ext();
        d2 = c.getHead2Ext();
        d3 = c.getHead3Ext();
        this.Lneck.xRot = (float)Math.toRadians(d1);
        this.neck.xRot = (float)Math.toRadians(d2);
        this.Rneck.xRot = (float)Math.toRadians(d3);
        this.Lsnout.y = this.Ljaw.y = (this.Lhead.y = this.Lneck.y - (float)Math.cos(this.Lneck.xRot) * 7.0f);
        this.Lheadfin.y = this.Ljaw.y;
        this.Lsnout.z = this.Ljaw.z = (this.Lhead.z = this.Lneck.z - (float)Math.sin(this.Lneck.xRot) * 7.0f);
        this.Lheadfin.z = this.Ljaw.z;
        this.Lsnout.x = this.Ljaw.x = (this.Lhead.x = this.Lneck.x - (float)Math.sin(this.Lneck.yRot) * 7.0f * (float)Math.sin(this.Lneck.xRot));
        this.Lheadfin.x = this.Ljaw.x;
        this.Rsnout.y = this.Rjaw.y = (this.Rhead.y = this.Rneck.y - (float)Math.cos(this.Rneck.xRot) * 7.0f);
        this.Rheadfin.y = this.Rjaw.y;
        this.Rsnout.z = this.Rjaw.z = (this.Rhead.z = this.Rneck.z - (float)Math.sin(this.Rneck.xRot) * 7.0f);
        this.Rheadfin.z = this.Rjaw.z;
        this.Rsnout.x = this.Rjaw.x = (this.Rhead.x = this.Rneck.x - (float)Math.sin(this.Rneck.yRot) * 7.0f * (float)Math.sin(this.Rneck.xRot));
        this.Rheadfin.x = this.Rjaw.x;
        this.snout.y = this.jaw.y = (this.head.y = this.neck.y - (float)Math.cos(this.neck.xRot) * 7.0f);
        this.headfin.y = this.jaw.y;
        this.snout.z = this.jaw.z = (this.head.z = this.neck.z - (float)Math.sin(this.neck.xRot) * 7.0f);
        this.headfin.z = this.jaw.z;
        this.snout.x = this.jaw.x = (this.head.x = this.neck.x - (float)Math.sin(this.neck.yRot) * 7.0f * (float)Math.sin(this.neck.xRot));
        this.headfin.x = this.jaw.x;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neckbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.snout.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lneck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lneck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lhead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lsnout.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rneck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rneck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rhead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rsnout.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lheadfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rheadfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Backfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Ljaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rjaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rwing2.render(matrixStack, buffer, packedLight, packedOverlay, 0.75F, 0.75F, 0.75F, 0.55F);
        this.Rwing3.render(matrixStack, buffer, packedLight, packedOverlay, 0.75F, 0.75F, 0.75F, 0.55F);
        this.Lwing2.render(matrixStack, buffer, packedLight, packedOverlay, 0.75F, 0.75F, 0.75F, 0.55F);
        this.Lwing3.render(matrixStack, buffer, packedLight, packedOverlay, 0.75F, 0.75F, 0.75F, 0.55F);
        this.Lwing.render(matrixStack, buffer, packedLight, packedOverlay, 0.75F, 0.75F, 0.75F, 0.55F);
        this.Rwing.render(matrixStack, buffer, packedLight, packedOverlay, 0.75F, 0.75F, 0.75F, 0.55F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

