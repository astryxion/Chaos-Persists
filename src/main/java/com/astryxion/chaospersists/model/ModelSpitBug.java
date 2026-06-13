/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelSpitBug
 *  com.astryxion.chaospersists.SpitBug
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.SpitBug;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpitBug extends EntityModel<SpitBug> {
    private float wingspeed = 1.0f;
    ModelRenderer legintersection;
    ModelRenderer legintersectionpart2;
    ModelRenderer legintersectionpart3;
    ModelRenderer leg1start;
    ModelRenderer leg1startpart2;
    ModelRenderer leg1startpart3;
    ModelRenderer leg1;
    ModelRenderer leg1part2;
    ModelRenderer leg1part2b;
    ModelRenderer leg1part2c;
    ModelRenderer leg1part2d;
    ModelRenderer leg1part3;
    ModelRenderer leg1part3b;
    ModelRenderer leg1part3c;
    ModelRenderer leg2start;
    ModelRenderer leg2startpart2;
    ModelRenderer leg2startpart3;
    ModelRenderer leg2;
    ModelRenderer leg2part2;
    ModelRenderer leg2part2b;
    ModelRenderer leg2part2c;
    ModelRenderer leg2part2d;
    ModelRenderer leg2part3;
    ModelRenderer leg2part3b;
    ModelRenderer leg2part3c;
    ModelRenderer leg3start;
    ModelRenderer leg3startpart2;
    ModelRenderer leg3startpart3;
    ModelRenderer leg3;
    ModelRenderer leg3part2;
    ModelRenderer leg3part2b;
    ModelRenderer leg3part2c;
    ModelRenderer leg3part2d;
    ModelRenderer leg3part3;
    ModelRenderer leg3part3b;
    ModelRenderer leg3part3c;
    ModelRenderer leg4start;
    ModelRenderer leg4startpart2;
    ModelRenderer leg4startpart3;
    ModelRenderer leg4;
    ModelRenderer leg4part2;
    ModelRenderer leg4part2b;
    ModelRenderer leg4part2c;
    ModelRenderer leg4part2d;
    ModelRenderer leg4part3;
    ModelRenderer leg4part3b;
    ModelRenderer leg4part3c;
    ModelRenderer bodybase;
    ModelRenderer bodybasepart2;
    ModelRenderer bodybasepart3;
    ModelRenderer bodybasepart4;
    ModelRenderer bodybasepart5;
    ModelRenderer bodybasepart6;
    ModelRenderer bodybasepart7;
    ModelRenderer bodybasepart8;
    ModelRenderer bodybasepart9;
    ModelRenderer bodybasepart10;
    ModelRenderer bodybasepart11;
    ModelRenderer bodybasepart12;
    ModelRenderer bodybasepart13;
    ModelRenderer bodybasepart14;
    ModelRenderer bodybasepart15;
    ModelRenderer upperjawbase;
    ModelRenderer upperjawbasepart1;
    ModelRenderer upperjawbasepart2;
    ModelRenderer upperjawbasepart3;
    ModelRenderer tooth1;
    ModelRenderer tooth2;
    ModelRenderer tooth3;
    ModelRenderer tooth4;
    ModelRenderer tooth5;
    ModelRenderer lowerjawbase;
    ModelRenderer lowerjawbasepart1;
    ModelRenderer lowerjawbasepart2;
    ModelRenderer lowerjawbasepart3;
    ModelRenderer lowerjawbasepart4;
    ModelRenderer lowerjawbasepart5;
    ModelRenderer lowerjawbasepart6;
    ModelRenderer lowerjawbasepart7;
    ModelRenderer lowerjawbasepart8;
    ModelRenderer lowerjawbasepart9;
    ModelRenderer lowerjawbasepart10;
    ModelRenderer lowerjawbasepart11;
    ModelRenderer arm1start;
    ModelRenderer arm1;
    ModelRenderer arm1part2;
    ModelRenderer arm1end;
    ModelRenderer arm2start;
    ModelRenderer arm2;
    ModelRenderer arm2part2;
    ModelRenderer arm2end;
    ModelRenderer eye1;
    ModelRenderer eye2;

    public ModelSpitBug(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 256;
        this.legintersection = new ModelRenderer(this, 0, 0);
        this.legintersection.addBox(-6.0f, -2.0f, -7.0f, 12, 6, 14);
        this.legintersection.setPos(0.0f, 3.0f, 1.0f);
        this.legintersection.mirror = true;
        this.setRotation(this.legintersection, 0.0f, 0.0f, 0.0f);
        this.legintersectionpart2 = new ModelRenderer(this, 0, 21);
        this.legintersectionpart2.addBox(-8.0f, -2.0f, 0.0f, 8, 6, 8);
        this.legintersectionpart2.setPos(0.0f, 3.0f, 2.0f);
        this.legintersectionpart2.mirror = true;
        this.setRotation(this.legintersectionpart2, 0.0f, 0.7853982f, 0.0f);
        this.legintersectionpart3 = new ModelRenderer(this, 282, 18);
        this.legintersectionpart3.addBox(-7.0f, 3.0f, -6.0f, 14, 2, 12);
        this.legintersectionpart3.setPos(0.0f, 3.0f, 1.0f);
        this.legintersectionpart3.mirror = true;
        this.setRotation(this.legintersectionpart3, 0.0f, 0.0f, 0.0f);
        this.leg1start = new ModelRenderer(this, 53, 0);
        this.leg1start.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 4);
        this.leg1start.setPos(4.0f, 3.0f, -4.0f);
        this.leg1start.mirror = true;
        this.setRotation(this.leg1start, 0.0f, -0.7853982f, 0.0f);
        this.leg1startpart2 = new ModelRenderer(this, 53, 19);
        this.leg1startpart2.addBox(-2.5f, -2.0f, -3.0f, 5, 5, 4);
        this.leg1startpart2.setPos(6.0f, 3.0f, -6.0f);
        this.leg1startpart2.mirror = true;
        this.setRotation(this.leg1startpart2, 0.3717861f, -0.7853982f, 0.0f);
        this.leg1startpart3 = new ModelRenderer(this, 53, 29);
        this.leg1startpart3.addBox(-2.0f, -2.0f, -4.0f, 4, 4, 2);
        this.leg1startpart3.setPos(6.0f, 3.0f, -6.0f);
        this.leg1startpart3.mirror = true;
        this.setRotation(this.leg1startpart3, 0.669215f, -0.7853982f, 0.0f);
        this.leg1 = new ModelRenderer(this, 45, 36);
        this.leg1.addBox(-1.5f, -3.0f, -13.0f, 3, 3, 10);
        this.leg1.setPos(6.0f, 3.0f, -6.0f);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 1.041001f, -0.7853982f, 0.0f);
        this.leg1part2 = new ModelRenderer(this, 33, 50);
        this.leg1part2.addBox(-2.0f, -1.5f, -13.0f, 4, 3, 15);
        this.leg1part2.setPos(12.0f, 13.0f, -12.0f);
        this.leg1part2.mirror = true;
        this.setRotation(this.leg1part2, -1.152537f, -0.7853982f, 0.0f);
        this.leg1part2b = new ModelRenderer(this, 33, 50);
        this.leg1part2b.addBox(-2.0f, 0.5f, 1.0f, 4, 1, 3);
        this.leg1part2b.setPos(12.0f, 13.0f, -12.0f);
        this.leg1part2b.mirror = true;
        this.setRotation(this.leg1part2b, -0.7435722f, -0.7853982f, 0.0f);
        this.leg1part2c = new ModelRenderer(this, 33, 50);
        this.leg1part2c.addBox(-2.0f, -7.5f, -13.0f, 4, 1, 3);
        this.leg1part2c.setPos(12.0f, 13.0f, -12.0f);
        this.leg1part2c.mirror = true;
        this.setRotation(this.leg1part2c, -0.6320451f, -0.7853982f, 0.0f);
        this.leg1part2d = new ModelRenderer(this, 2, 50);
        this.leg1part2d.addBox(-1.5f, -1.5f, -10.0f, 3, 3, 12);
        this.leg1part2d.setPos(12.0f, 13.0f, -12.0f);
        this.leg1part2d.mirror = true;
        this.setRotation(this.leg1part2d, -1.041001f, -0.7853982f, 0.0f);
        this.leg1part3 = new ModelRenderer(this, 51, 69);
        this.leg1part3.addBox(-1.5f, -1.5f, -7.0f, 3, 3, 7);
        this.leg1part3.setPos(16.0f, 3.0f, -16.0f);
        this.leg1part3.mirror = true;
        this.setRotation(this.leg1part3, 0.669215f, -0.7853982f, 0.0f);
        this.leg1part3b = new ModelRenderer(this, 55, 80);
        this.leg1part3b.addBox(-2.0f, -2.0f, -2.0f, 4, 16, 4);
        this.leg1part3b.setPos(20.0f, 8.0f, -20.0f);
        this.leg1part3b.mirror = true;
        this.setRotation(this.leg1part3b, -0.4833219f, -0.7853982f, -0.0349066f);
        this.leg1part3c = new ModelRenderer(this, 42, 80);
        this.leg1part3c.addBox(-2.0f, 14.0f, 0.0f, 4, 3, 2);
        this.leg1part3c.setPos(20.0f, 8.0f, -20.0f);
        this.leg1part3c.mirror = true;
        this.setRotation(this.leg1part3c, -0.4833219f, -0.7853982f, -0.0349066f);
        this.leg2start = new ModelRenderer(this, 52, 0);
        this.leg2start.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 4);
        this.leg2start.setPos(-4.0f, 3.0f, -4.0f);
        this.leg2start.mirror = true;
        this.setRotation(this.leg2start, 0.0f, 0.7853982f, 0.0f);
        this.leg2startpart2 = new ModelRenderer(this, 53, 19);
        this.leg2startpart2.addBox(-2.5f, -2.0f, -3.0f, 5, 5, 4);
        this.leg2startpart2.setPos(-6.0f, 3.0f, -6.0f);
        this.leg2startpart2.mirror = true;
        this.setRotation(this.leg2startpart2, 0.3717861f, 0.7853982f, 0.0f);
        this.leg2startpart3 = new ModelRenderer(this, 53, 29);
        this.leg2startpart3.addBox(-2.0f, -2.0f, -4.0f, 4, 4, 2);
        this.leg2startpart3.setPos(-6.0f, 3.0f, -6.0f);
        this.leg2startpart3.mirror = true;
        this.setRotation(this.leg2startpart3, 0.669215f, 0.7853982f, 0.0f);
        this.leg2 = new ModelRenderer(this, 45, 36);
        this.leg2.addBox(-1.5f, -3.0f, -13.0f, 3, 3, 10);
        this.leg2.setPos(-6.0f, 3.0f, -6.0f);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 1.041001f, 0.7853982f, 0.0f);
        this.leg2part2 = new ModelRenderer(this, 72, 50);
        this.leg2part2.addBox(-2.0f, -1.5f, -13.0f, 4, 3, 15);
        this.leg2part2.setPos(-12.0f, 13.0f, -12.0f);
        this.leg2part2.mirror = true;
        this.setRotation(this.leg2part2, -1.152537f, 0.7853982f, 0.0f);
        this.leg2part2b = new ModelRenderer(this, 33, 50);
        this.leg2part2b.addBox(-2.0f, 0.5f, 1.0f, 4, 1, 3);
        this.leg2part2b.setPos(-12.0f, 13.0f, -12.0f);
        this.leg2part2b.mirror = true;
        this.setRotation(this.leg2part2b, -0.7435722f, 0.7853982f, 0.0f);
        this.leg2part2c = new ModelRenderer(this, 33, 50);
        this.leg2part2c.addBox(-2.0f, -7.5f, -13.0f, 4, 1, 3);
        this.leg2part2c.setPos(-12.0f, 13.0f, -12.0f);
        this.leg2part2c.mirror = true;
        this.setRotation(this.leg2part2c, -0.6320451f, 0.7853982f, 0.0f);
        this.leg2part2d = new ModelRenderer(this, 2, 50);
        this.leg2part2d.addBox(-1.5f, -1.5f, -10.0f, 3, 3, 12);
        this.leg2part2d.setPos(-12.0f, 13.0f, -12.0f);
        this.leg2part2d.mirror = true;
        this.setRotation(this.leg2part2d, -1.041001f, 0.7853982f, 0.0f);
        this.leg2part3 = new ModelRenderer(this, 51, 69);
        this.leg2part3.addBox(-1.5f, -1.5f, -7.0f, 3, 3, 7);
        this.leg2part3.setPos(-16.0f, 3.0f, -16.0f);
        this.leg2part3.mirror = true;
        this.setRotation(this.leg2part3, 0.669215f, 0.7853982f, 0.0f);
        this.leg2part3b = new ModelRenderer(this, 55, 80);
        this.leg2part3b.addBox(-2.0f, -2.0f, -2.0f, 4, 16, 4);
        this.leg2part3b.setPos(-20.0f, 8.0f, -20.0f);
        this.leg2part3b.mirror = true;
        this.setRotation(this.leg2part3b, -0.4833219f, 0.7853982f, -0.0349066f);
        this.leg2part3c = new ModelRenderer(this, 42, 80);
        this.leg2part3c.addBox(-2.0f, 14.0f, 0.0f, 4, 3, 2);
        this.leg2part3c.setPos(-20.0f, 8.0f, -20.0f);
        this.leg2part3c.mirror = true;
        this.setRotation(this.leg2part3c, -0.4833219f, 0.7853982f, -0.0349066f);
        this.leg3start = new ModelRenderer(this, 52, 0);
        this.leg3start.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 4);
        this.leg3start.setPos(4.0f, 3.0f, 6.0f);
        this.leg3start.mirror = true;
        this.setRotation(this.leg3start, 0.0f, -2.356194f, 0.0f);
        this.leg3startpart2 = new ModelRenderer(this, 72, 19);
        this.leg3startpart2.addBox(-2.5f, -2.0f, -3.0f, 5, 5, 4);
        this.leg3startpart2.setPos(6.0f, 3.0f, 8.0f);
        this.leg3startpart2.mirror = true;
        this.setRotation(this.leg3startpart2, 0.3717861f, -2.356194f, 0.0f);
        this.leg3startpart3 = new ModelRenderer(this, 72, 29);
        this.leg3startpart3.addBox(-2.0f, -2.0f, -4.0f, 4, 4, 2);
        this.leg3startpart3.setPos(6.0f, 3.0f, 8.0f);
        this.leg3startpart3.mirror = true;
        this.setRotation(this.leg3startpart3, 0.669215f, -2.356194f, 0.0f);
        this.leg3 = new ModelRenderer(this, 72, 36);
        this.leg3.addBox(-1.5f, -3.0f, -13.0f, 3, 3, 10);
        this.leg3.setPos(6.0f, 3.0f, 8.0f);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 1.041001f, -2.356194f, 0.0f);
        this.leg3part2 = new ModelRenderer(this, 33, 50);
        this.leg3part2.addBox(-2.0f, -1.5f, -13.0f, 4, 3, 15);
        this.leg3part2.setPos(12.0f, 13.0f, 14.0f);
        this.leg3part2.mirror = true;
        this.setRotation(this.leg3part2, -1.152537f, -2.356194f, 0.0f);
        this.leg3part2b = new ModelRenderer(this, 33, 50);
        this.leg3part2b.addBox(-2.0f, 0.5f, 1.0f, 4, 1, 3);
        this.leg3part2b.setPos(12.0f, 13.0f, 14.0f);
        this.leg3part2b.mirror = true;
        this.setRotation(this.leg3part2b, -0.7435722f, -2.356194f, 0.0f);
        this.leg3part2c = new ModelRenderer(this, 33, 50);
        this.leg3part2c.addBox(-2.0f, -7.5f, -13.0f, 4, 1, 3);
        this.leg3part2c.setPos(12.0f, 13.0f, 14.0f);
        this.leg3part2c.mirror = true;
        this.setRotation(this.leg3part2c, -0.6320451f, -2.356194f, 0.0f);
        this.leg3part2d = new ModelRenderer(this, 111, 50);
        this.leg3part2d.addBox(-1.5f, -1.5f, -10.0f, 3, 3, 12);
        this.leg3part2d.setPos(12.0f, 13.0f, 14.0f);
        this.leg3part2d.mirror = true;
        this.setRotation(this.leg3part2d, -1.041001f, -2.356194f, 0.0f);
        this.leg3part3 = new ModelRenderer(this, 72, 69);
        this.leg3part3.addBox(-1.5f, -1.5f, -7.0f, 3, 3, 7);
        this.leg3part3.setPos(16.0f, 3.0f, 18.0f);
        this.leg3part3.mirror = true;
        this.setRotation(this.leg3part3, 0.669215f, -2.356194f, 0.0f);
        this.leg3part3b = new ModelRenderer(this, 72, 80);
        this.leg3part3b.addBox(-2.0f, -2.0f, -2.0f, 4, 16, 4);
        this.leg3part3b.setPos(20.0f, 8.0f, 22.0f);
        this.leg3part3b.mirror = true;
        this.setRotation(this.leg3part3b, -0.4833219f, -2.356194f, -0.0349066f);
        this.leg3part3c = new ModelRenderer(this, 89, 80);
        this.leg3part3c.addBox(-2.0f, 14.0f, 0.0f, 4, 3, 2);
        this.leg3part3c.setPos(20.0f, 8.0f, 22.0f);
        this.leg3part3c.mirror = true;
        this.setRotation(this.leg3part3c, -0.4833219f, -2.356194f, -0.0349066f);
        this.leg4start = new ModelRenderer(this, 52, 0);
        this.leg4start.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 4);
        this.leg4start.setPos(-4.0f, 3.0f, 6.0f);
        this.leg4start.mirror = true;
        this.setRotation(this.leg4start, 0.0f, 2.356194f, 0.0f);
        this.leg4startpart2 = new ModelRenderer(this, 72, 19);
        this.leg4startpart2.addBox(-2.5f, -2.0f, -3.0f, 5, 5, 4);
        this.leg4startpart2.setPos(-6.0f, 3.0f, 8.0f);
        this.leg4startpart2.mirror = true;
        this.setRotation(this.leg4startpart2, 0.3717861f, 2.356194f, 0.0f);
        this.leg4startpart3 = new ModelRenderer(this, 72, 29);
        this.leg4startpart3.addBox(-2.0f, -2.0f, -4.0f, 4, 4, 2);
        this.leg4startpart3.setPos(-6.0f, 3.0f, 8.0f);
        this.leg4startpart3.mirror = true;
        this.setRotation(this.leg4startpart3, 0.669215f, 2.356194f, 0.0f);
        this.leg4 = new ModelRenderer(this, 72, 36);
        this.leg4.addBox(-1.5f, -3.0f, -13.0f, 3, 3, 10);
        this.leg4.setPos(-6.0f, 3.0f, 8.0f);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 1.041001f, 2.356194f, 0.0f);
        this.leg4part2 = new ModelRenderer(this, 72, 50);
        this.leg4part2.addBox(-2.0f, -1.5f, -13.0f, 4, 3, 15);
        this.leg4part2.setPos(-12.0f, 13.0f, 14.0f);
        this.leg4part2.mirror = true;
        this.setRotation(this.leg4part2, -1.152537f, 2.356194f, 0.0f);
        this.leg4part2b = new ModelRenderer(this, 33, 50);
        this.leg4part2b.addBox(-2.0f, 0.5f, 1.0f, 4, 1, 3);
        this.leg4part2b.setPos(-12.0f, 13.0f, 14.0f);
        this.leg4part2b.mirror = true;
        this.setRotation(this.leg4part2b, -0.7435722f, 2.363176f, 0.0f);
        this.leg4part2c = new ModelRenderer(this, 33, 50);
        this.leg4part2c.addBox(-2.0f, -7.5f, -13.0f, 4, 1, 3);
        this.leg4part2c.setPos(-12.0f, 13.0f, 14.0f);
        this.leg4part2c.mirror = true;
        this.setRotation(this.leg4part2c, -0.6320451f, 2.356194f, 0.0f);
        this.leg4part2d = new ModelRenderer(this, 111, 50);
        this.leg4part2d.addBox(-1.5f, -1.5f, -10.0f, 3, 3, 12);
        this.leg4part2d.setPos(-12.0f, 13.0f, 14.0f);
        this.leg4part2d.mirror = true;
        this.setRotation(this.leg4part2d, -1.041001f, 2.356194f, 0.0f);
        this.leg4part3 = new ModelRenderer(this, 72, 69);
        this.leg4part3.addBox(-1.5f, -1.5f, -7.0f, 3, 3, 7);
        this.leg4part3.setPos(-16.0f, 3.0f, 18.0f);
        this.leg4part3.mirror = true;
        this.setRotation(this.leg4part3, 0.669215f, 2.356194f, 0.0f);
        this.leg4part3b = new ModelRenderer(this, 72, 80);
        this.leg4part3b.addBox(-2.0f, -2.0f, -2.0f, 4, 16, 4);
        this.leg4part3b.setPos(-20.0f, 8.0f, 22.0f);
        this.leg4part3b.mirror = true;
        this.setRotation(this.leg4part3b, -0.4833219f, 2.356194f, -0.0349066f);
        this.leg4part3c = new ModelRenderer(this, 42, 80);
        this.leg4part3c.addBox(-2.0f, 14.0f, 0.0f, 4, 3, 2);
        this.leg4part3c.setPos(-20.0f, 8.0f, 22.0f);
        this.leg4part3c.mirror = true;
        this.setRotation(this.leg4part3c, -0.4833219f, 2.356194f, -0.0349066f);
        this.bodybase = new ModelRenderer(this, 98, 0);
        this.bodybase.addBox(-5.0f, -20.0f, -6.5f, 10, 20, 13);
        this.bodybase.setPos(0.0f, 1.0f, 1.0f);
        this.bodybase.mirror = true;
        this.setRotation(this.bodybase, 0.0f, 0.0f, 0.0f);
        this.bodybasepart2 = new ModelRenderer(this, 146, 0);
        this.bodybasepart2.addBox(-6.0f, -20.0f, -7.5f, 12, 12, 21);
        this.bodybasepart2.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart2.mirror = true;
        this.setRotation(this.bodybasepart2, 0.0f, 0.0f, 0.0f);
        this.bodybasepart3 = new ModelRenderer(this, 213, 0);
        this.bodybasepart3.addBox(-6.5f, -20.0f, -7.5f, 13, 12, 21);
        this.bodybasepart3.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart3.mirror = true;
        this.setRotation(this.bodybasepart3, 0.0f, 0.0f, 0.0f);
        this.bodybasepart4 = new ModelRenderer(this, 132, 34);
        this.bodybasepart4.addBox(-5.0f, -18.0f, -16.5f, 10, 8, 9);
        this.bodybasepart4.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart4.mirror = true;
        this.setRotation(this.bodybasepart4, 0.0f, 0.0f, 0.0f);
        this.bodybasepart5 = new ModelRenderer(this, 172, 36);
        this.bodybasepart5.addBox(-5.0f, -19.0f, 13.5f, 10, 10, 5);
        this.bodybasepart5.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart5.mirror = true;
        this.setRotation(this.bodybasepart5, 0.0f, 0.0f, 0.0f);
        this.bodybasepart6 = new ModelRenderer(this, 142, 53);
        this.bodybasepart6.addBox(-4.5f, -18.0f, 18.5f, 9, 4, 3);
        this.bodybasepart6.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart6.mirror = true;
        this.setRotation(this.bodybasepart6, 0.0f, 0.0f, 0.0f);
        this.bodybasepart7 = new ModelRenderer(this, 167, 53);
        this.bodybasepart7.addBox(-2.5f, -26.0f, -10.5f, 5, 2, 9);
        this.bodybasepart7.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart7.mirror = true;
        this.setRotation(this.bodybasepart7, -1.264073f, 0.0f, 0.0f);
        this.bodybasepart8 = new ModelRenderer(this, 111, 68);
        this.bodybasepart8.addBox(-6.0f, -13.0f, -18.5f, 12, 10, 11);
        this.bodybasepart8.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart8.mirror = true;
        this.setRotation(this.bodybasepart8, -0.4089647f, 0.0f, 0.0f);
        this.bodybasepart9 = new ModelRenderer(this, 157, 66);
        this.bodybasepart9.addBox(-7.5f, -14.0f, -11.5f, 15, 16, 7);
        this.bodybasepart9.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart9.mirror = true;
        this.setRotation(this.bodybasepart9, -1.412787f, 0.0f, 0.0f);
        this.bodybasepart10 = new ModelRenderer(this, 204, 35);
        this.bodybasepart10.addBox(-7.5f, -22.0f, -9.5f, 15, 5, 22);
        this.bodybasepart10.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart10.mirror = true;
        this.setRotation(this.bodybasepart10, 0.0f, 0.0f, 0.0f);
        this.bodybasepart11 = new ModelRenderer(this, 204, 63);
        this.bodybasepart11.addBox(-6.5f, -21.0f, -14.5f, 13, 4, 5);
        this.bodybasepart11.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart11.mirror = true;
        this.setRotation(this.bodybasepart11, 0.0f, 0.0f, 0.0f);
        this.bodybasepart12 = new ModelRenderer(this, 282, 0);
        this.bodybasepart12.addBox(-5.0f, -3.0f, 2.5f, 10, 4, 12);
        this.bodybasepart12.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart12.mirror = true;
        this.setRotation(this.bodybasepart12, 0.4833219f, 0.0f, 0.0f);
        this.bodybasepart13 = new ModelRenderer(this, 327, 0);
        this.bodybasepart13.addBox(4.0f, 1.0f, -3.5f, 4, 2, 10);
        this.bodybasepart13.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart13.mirror = true;
        this.setRotation(this.bodybasepart13, 0.1858931f, 0.0f, -1.003822f);
        this.bodybasepart14 = new ModelRenderer(this, 327, 0);
        this.bodybasepart14.addBox(-8.0f, 1.0f, -3.5f, 4, 2, 10);
        this.bodybasepart14.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart14.mirror = true;
        this.setRotation(this.bodybasepart14, 0.185895f, 0.0f, 1.003826f);
        this.bodybasepart15 = new ModelRenderer(this, 144, 91);
        this.bodybasepart15.addBox(-2.5f, -25.0f, 1.5f, 5, 3, 7);
        this.bodybasepart15.setPos(0.0f, 1.0f, 1.0f);
        this.bodybasepart15.mirror = true;
        this.setRotation(this.bodybasepart15, -0.7504916f, 0.0f, 0.0f);
        this.upperjawbase = new ModelRenderer(this, 0, 37);
        this.upperjawbase.addBox(-3.5f, -2.0f, -6.0f, 7, 5, 6);
        this.upperjawbase.setPos(0.0f, -13.0f, -15.0f);
        this.upperjawbase.mirror = true;
        this.setRotation(this.upperjawbase, 0.0f, 0.0f, 0.0f);
        this.upperjawbasepart1 = new ModelRenderer(this, 35, 28);
        this.upperjawbasepart1.addBox(-4.5f, -1.0f, -7.0f, 3, 3, 2);
        this.upperjawbasepart1.setPos(0.0f, -13.0f, -15.0f);
        this.upperjawbasepart1.mirror = true;
        this.setRotation(this.upperjawbasepart1, 0.0f, 0.0f, 0.0f);
        this.upperjawbasepart2 = new ModelRenderer(this, 35, 28);
        this.upperjawbasepart2.addBox(1.5f, -1.0f, -7.0f, 3, 3, 2);
        this.upperjawbasepart2.setPos(0.0f, -13.0f, -15.0f);
        this.upperjawbasepart2.mirror = true;
        this.setRotation(this.upperjawbasepart2, 0.0f, 0.0f, 0.0f);
        this.upperjawbasepart3 = new ModelRenderer(this, 27, 37);
        this.upperjawbasepart3.addBox(-1.0f, -1.0f, -7.0f, 2, 3, 2);
        this.upperjawbasepart3.setPos(0.0f, -13.0f, -15.0f);
        this.upperjawbasepart3.mirror = true;
        this.setRotation(this.upperjawbasepart3, 0.0f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer(this, 116, 34);
        this.tooth1.addBox(-1.5f, -2.0f, -14.0f, 2, 2, 7);
        this.tooth1.setPos(0.0f, -13.0f, -15.0f);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, 0.2602503f, 0.3717861f, 0.0f);
        this.tooth2 = new ModelRenderer(this, 116, 34);
        this.tooth2.addBox(-0.5f, -2.0f, -14.0f, 2, 2, 7);
        this.tooth2.setPos(0.0f, -13.0f, -15.0f);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, 0.2602503f, -0.3717861f, 0.0f);
        this.tooth3 = new ModelRenderer(this, 116, 34);
        this.tooth3.addBox(-1.0f, -2.0f, -14.0f, 2, 2, 7);
        this.tooth3.setPos(0.0f, -13.0f, -15.0f);
        this.tooth3.mirror = true;
        this.setRotation(this.tooth3, 0.2602503f, 0.0f, 0.0f);
        this.tooth4 = new ModelRenderer(this, 90, 111);
        this.tooth4.addBox(-5.5f, 1.5f, -23.5f, 3, 2, 5);
        this.tooth4.setPos(0.0f, 0.0f, -6.0f);
        this.tooth4.mirror = true;
        this.setRotation(this.tooth4, -0.2230717f, 0.0f, 0.0f);
        this.tooth5 = new ModelRenderer(this, 90, 111);
        this.tooth5.addBox(2.5f, 1.5f, -23.5f, 3, 2, 5);
        this.tooth5.setPos(0.0f, 0.0f, -6.0f);
        this.tooth5.mirror = true;
        this.setRotation(this.tooth5, -0.2230717f, 0.0f, 0.0f);
        this.lowerjawbase = new ModelRenderer(this, 90, 91);
        this.lowerjawbase.addBox(-5.0f, -1.0f, -15.5f, 10, 2, 16);
        this.lowerjawbase.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbase.mirror = true;
        this.setRotation(this.lowerjawbase, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart1 = new ModelRenderer(this, 0, 69);
        this.lowerjawbasepart1.addBox(-5.0f, -3.0f, -15.5f, 1, 2, 16);
        this.lowerjawbasepart1.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart1.mirror = true;
        this.setRotation(this.lowerjawbasepart1, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart2 = new ModelRenderer(this, 0, 69);
        this.lowerjawbasepart2.addBox(4.0f, -3.0f, -15.5f, 1, 2, 16);
        this.lowerjawbasepart2.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart2.mirror = true;
        this.setRotation(this.lowerjawbasepart2, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart3 = new ModelRenderer(this, 0, 88);
        this.lowerjawbasepart3.addBox(-4.0f, -2.0f, -15.5f, 1, 1, 16);
        this.lowerjawbasepart3.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart3.mirror = true;
        this.setRotation(this.lowerjawbasepart3, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart4 = new ModelRenderer(this, 0, 88);
        this.lowerjawbasepart4.addBox(3.0f, -2.0f, -15.5f, 1, 1, 16);
        this.lowerjawbasepart4.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart4.mirror = true;
        this.setRotation(this.lowerjawbasepart4, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart5 = new ModelRenderer(this, 35, 134);
        this.lowerjawbasepart5.addBox(5.0f, -5.0f, -16.5f, 2, 4, 20);
        this.lowerjawbasepart5.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart5.mirror = true;
        this.setRotation(this.lowerjawbasepart5, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart6 = new ModelRenderer(this, 35, 109);
        this.lowerjawbasepart6.addBox(-7.0f, -5.0f, -16.5f, 2, 4, 20);
        this.lowerjawbasepart6.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart6.mirror = true;
        this.setRotation(this.lowerjawbasepart6, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart7 = new ModelRenderer(this, 73, 101);
        this.lowerjawbasepart7.addBox(-6.0f, -3.0f, -19.5f, 4, 3, 4);
        this.lowerjawbasepart7.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart7.mirror = true;
        this.setRotation(this.lowerjawbasepart7, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart8 = new ModelRenderer(this, 73, 101);
        this.lowerjawbasepart8.addBox(2.0f, -3.0f, -19.5f, 4, 3, 4);
        this.lowerjawbasepart8.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart8.mirror = true;
        this.setRotation(this.lowerjawbasepart8, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart9 = new ModelRenderer(this, 95, 72);
        this.lowerjawbasepart9.addBox(-2.0f, -2.0f, -18.5f, 4, 2, 3);
        this.lowerjawbasepart9.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart9.mirror = true;
        this.setRotation(this.lowerjawbasepart9, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart10 = new ModelRenderer(this, 0, 106);
        this.lowerjawbasepart10.addBox(-8.0f, -3.0f, -12.5f, 1, 2, 16);
        this.lowerjawbasepart10.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart10.mirror = true;
        this.setRotation(this.lowerjawbasepart10, 0.0f, 0.0f, 0.0f);
        this.lowerjawbasepart11 = new ModelRenderer(this, 0, 106);
        this.lowerjawbasepart11.addBox(7.0f, -3.0f, -12.5f, 1, 2, 16);
        this.lowerjawbasepart11.setPos(0.0f, 0.0f, -6.0f);
        this.lowerjawbasepart11.mirror = true;
        this.setRotation(this.lowerjawbasepart11, 0.0f, 0.0f, 0.0f);
        this.arm1start = new ModelRenderer(this, 0, 50);
        this.arm1start.addBox(-0.5f, -1.0f, -1.0f, 3, 2, 2);
        this.arm1start.setPos(5.0f, -11.0f, -14.0f);
        this.arm1start.mirror = true;
        this.setRotation(this.arm1start, 0.0f, 0.0f, 0.0f);
        this.arm1 = new ModelRenderer(this, 9, 125);
        this.arm1.addBox(-0.5f, -1.0f, -1.0f, 2, 5, 2);
        this.arm1.setPos(7.0f, -11.0f, -14.0f);
        this.arm1.mirror = true;
        this.setRotation(this.arm1, -0.8922867f, 0.0f, 0.0f);
        this.arm1part2 = new ModelRenderer(this, 9, 133);
        this.arm1part2.addBox(-0.5f, -1.0f, -1.0f, 2, 5, 2);
        this.arm1part2.setPos(7.0f, -9.0f, -16.5f);
        this.arm1part2.mirror = true;
        this.setRotation(this.arm1part2, 0.7435722f, 0.0f, 0.0f);
        this.arm1end = new ModelRenderer(this, 9, 141);
        this.arm1end.addBox(1.0f, 3.0f, 1.0f, 1, 1, 2);
        this.arm1end.setPos(6.0f, -9.0f, -16.5f);
        this.arm1end.mirror = true;
        this.setRotation(this.arm1end, 0.7435722f, 0.0f, 0.0f);
        this.arm2start = new ModelRenderer(this, 0, 50);
        this.arm2start.addBox(-2.5f, -1.0f, -1.0f, 3, 2, 2);
        this.arm2start.setPos(-5.0f, -11.0f, -14.0f);
        this.arm2start.mirror = true;
        this.setRotation(this.arm2start, 0.0f, 0.0f, 0.0f);
        this.arm2 = new ModelRenderer(this, 0, 125);
        this.arm2.addBox(-1.5f, -1.0f, -1.0f, 2, 5, 2);
        this.arm2.setPos(-7.0f, -11.0f, -14.0f);
        this.arm2.mirror = true;
        this.setRotation(this.arm2, -0.8922867f, 0.0f, 0.0f);
        this.arm2part2 = new ModelRenderer(this, 0, 133);
        this.arm2part2.addBox(-1.5f, -1.0f, -1.0f, 2, 5, 2);
        this.arm2part2.setPos(-7.0f, -9.0f, -16.5f);
        this.arm2part2.mirror = true;
        this.setRotation(this.arm2part2, 0.7435722f, 0.0f, 0.0f);
        this.arm2end = new ModelRenderer(this, 0, 141);
        this.arm2end.addBox(-1.0f, 3.0f, 1.0f, 1, 1, 2);
        this.arm2end.setPos(-7.0f, -9.0f, -16.5f);
        this.arm2end.mirror = true;
        this.setRotation(this.arm2end, 0.7435722f, 0.0f, 0.0f);
        this.eye1 = new ModelRenderer(this, 36, 37);
        this.eye1.addBox(-0.5f, -1.0f, -1.0f, 1, 2, 2);
        this.eye1.setPos(6.5f, -10.0f, -11.0f);
        this.eye1.mirror = true;
        this.setRotation(this.eye1, 0.0f, 0.0f, 0.0f);
        this.eye2 = new ModelRenderer(this, 36, 37);
        this.eye2.addBox(-0.5f, -1.0f, -1.0f, 1, 2, 2);
        this.eye2.setPos(-6.5f, -10.0f, -11.0f);
        this.eye2.mirror = true;
        this.setRotation(this.eye2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(SpitBug entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        SpitBug e = (SpitBug)entity;
        java.lang.Object r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float pi4 = 1.570795f;
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
        newangle = e.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.015f : MathHelper.cos((float)(f2 * 2.6f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.upperjawbasepart1.xRot = newangle = Math.abs(newangle);
        this.upperjawbasepart2.xRot = newangle;
        this.upperjawbasepart3.xRot = newangle;
        this.tooth1.xRot = 0.26f + newangle;
        this.tooth2.xRot = 0.26f + newangle;
        this.tooth3.xRot = 0.26f + newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.legintersection.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.legintersectionpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.legintersectionpart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1startpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1startpart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2startpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2startpart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3startpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3startpart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4startpart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4startpart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2d.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3c.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodybasepart15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbasepart1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbasepart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperjawbasepart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerjawbasepart11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm1end.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2start.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arm2end.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eye1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eye2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }

    private void doRightFrontLeg(float angle, float upangle) {
        this.leg1part2.yRot = this.leg1.yRot = -1.2f + angle;
        this.leg1part2b.yRot = this.leg1.yRot;
        this.leg1part2c.yRot = this.leg1.yRot;
        this.leg1part2d.yRot = this.leg1.yRot;
        this.leg1part3.yRot = this.leg1.yRot;
        this.leg1part3b.yRot = this.leg1.yRot;
        this.leg1part3c.yRot = this.leg1.yRot;
        float dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg1.xRot));
        this.leg1part2c.z = this.leg1part2d.z = (float)((double)this.leg1.z - Math.cos(this.leg1.yRot) * (double)dist);
        this.leg1part2b.z = this.leg1part2d.z;
        this.leg1part2.z = this.leg1part2d.z;
        this.leg1part2c.x = this.leg1part2d.x = (float)((double)this.leg1.x - Math.sin(this.leg1.yRot) * (double)dist);
        this.leg1part2b.x = this.leg1part2d.x;
        this.leg1part2.x = this.leg1part2d.x;
        this.leg1part2.xRot = -1.152f + upangle;
        this.leg1part2b.xRot = -0.743f + upangle;
        this.leg1part2c.xRot = -0.632f + upangle;
        this.leg1part2d.xRot = -1.041f + upangle;
        dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg1part2.xRot));
        this.leg1part3.z = (float)((double)this.leg1part2.z - Math.cos(this.leg1part2.yRot) * (double)dist);
        this.leg1part3.x = (float)((double)this.leg1part2.x - Math.sin(this.leg1part2.yRot) * (double)dist);
        this.leg1part3.xRot = 0.669f - upangle;
        dist = 8.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg1part3.xRot));
        this.leg1part3b.z = this.leg1part3c.z = (float)((double)this.leg1part3.z - Math.cos(this.leg1part3.yRot) * (double)dist);
        this.leg1part3b.x = this.leg1part3c.x = (float)((double)this.leg1part3.x - Math.sin(this.leg1part3.yRot) * (double)dist);
        this.leg1part3b.xRot = -0.48f - upangle;
        this.leg1part3c.xRot = -0.48f - upangle;
    }

    private void doLeftFrontLeg(float angle, float upangle) {
        this.leg2part2.yRot = this.leg2.yRot = 1.2f + angle;
        this.leg2part2b.yRot = this.leg2.yRot;
        this.leg2part2c.yRot = this.leg2.yRot;
        this.leg2part2d.yRot = this.leg2.yRot;
        this.leg2part3.yRot = this.leg2.yRot;
        this.leg2part3b.yRot = this.leg2.yRot;
        this.leg2part3c.yRot = this.leg2.yRot;
        float dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg2.xRot));
        this.leg2part2c.z = this.leg2part2d.z = (float)((double)this.leg2.z - Math.cos(this.leg2.yRot) * (double)dist);
        this.leg2part2b.z = this.leg2part2d.z;
        this.leg2part2.z = this.leg2part2d.z;
        this.leg2part2c.x = this.leg2part2d.x = (float)((double)this.leg2.x - Math.sin(this.leg2.yRot) * (double)dist);
        this.leg2part2b.x = this.leg2part2d.x;
        this.leg2part2.x = this.leg2part2d.x;
        this.leg2part2.xRot = -1.152f + upangle;
        this.leg2part2b.xRot = -0.743f + upangle;
        this.leg2part2c.xRot = -0.632f + upangle;
        this.leg2part2d.xRot = -1.041f + upangle;
        dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg2part2.xRot));
        this.leg2part3.z = (float)((double)this.leg2part2.z - Math.cos(this.leg2part2.yRot) * (double)dist);
        this.leg2part3.x = (float)((double)this.leg2part2.x - Math.sin(this.leg2part2.yRot) * (double)dist);
        this.leg2part3.xRot = 0.669f - upangle;
        dist = 8.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg2part3.xRot));
        this.leg2part3b.z = this.leg2part3c.z = (float)((double)this.leg2part3.z - Math.cos(this.leg2part3.yRot) * (double)dist);
        this.leg2part3b.x = this.leg2part3c.x = (float)((double)this.leg2part3.x - Math.sin(this.leg2part3.yRot) * (double)dist);
        this.leg2part3b.xRot = -0.48f - upangle;
        this.leg2part3c.xRot = -0.48f - upangle;
    }

    private void doRightRearLeg(float angle, float upangle) {
        this.leg4part2.yRot = this.leg4.yRot = 2.1f + angle;
        this.leg4part2b.yRot = this.leg4.yRot;
        this.leg4part2c.yRot = this.leg4.yRot;
        this.leg4part2d.yRot = this.leg4.yRot;
        this.leg4part3.yRot = this.leg4.yRot;
        this.leg4part3b.yRot = this.leg4.yRot;
        this.leg4part3c.yRot = this.leg4.yRot;
        float dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg4.xRot));
        this.leg4part2c.z = this.leg4part2d.z = (float)((double)this.leg4.z - Math.cos(this.leg4.yRot) * (double)dist);
        this.leg4part2b.z = this.leg4part2d.z;
        this.leg4part2.z = this.leg4part2d.z;
        this.leg4part2c.x = this.leg4part2d.x = (float)((double)this.leg4.x - Math.sin(this.leg4.yRot) * (double)dist);
        this.leg4part2b.x = this.leg4part2d.x;
        this.leg4part2.x = this.leg4part2d.x;
        this.leg4part2.xRot = -1.152f + upangle;
        this.leg4part2b.xRot = -0.743f + upangle;
        this.leg4part2c.xRot = -0.632f + upangle;
        this.leg4part2d.xRot = -1.041f + upangle;
        dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg4part2.xRot));
        this.leg4part3.z = (float)((double)this.leg4part2.z - Math.cos(this.leg4part2.yRot) * (double)dist);
        this.leg4part3.x = (float)((double)this.leg4part2.x - Math.sin(this.leg4part2.yRot) * (double)dist);
        this.leg4part3.xRot = 0.669f - upangle;
        dist = 8.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg4part3.xRot));
        this.leg4part3b.z = this.leg4part3c.z = (float)((double)this.leg4part3.z - Math.cos(this.leg4part3.yRot) * (double)dist);
        this.leg4part3b.x = this.leg4part3c.x = (float)((double)this.leg4part3.x - Math.sin(this.leg4part3.yRot) * (double)dist);
        this.leg4part3b.xRot = -0.48f - upangle;
        this.leg4part3c.xRot = -0.48f - upangle;
    }

    private void doLeftRearLeg(float angle, float upangle) {
        this.leg3part2.yRot = this.leg3.yRot = -2.1f + angle;
        this.leg3part2b.yRot = this.leg3.yRot;
        this.leg3part2c.yRot = this.leg3.yRot;
        this.leg3part2d.yRot = this.leg3.yRot;
        this.leg3part3.yRot = this.leg3.yRot;
        this.leg3part3b.yRot = this.leg3.yRot;
        this.leg3part3c.yRot = this.leg3.yRot;
        float dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg3.xRot));
        this.leg3part2c.z = this.leg3part2d.z = (float)((double)this.leg3.z - Math.cos(this.leg3.yRot) * (double)dist);
        this.leg3part2b.z = this.leg3part2d.z;
        this.leg3part2.z = this.leg3part2d.z;
        this.leg3part2c.x = this.leg3part2d.x = (float)((double)this.leg3.x - Math.sin(this.leg3.yRot) * (double)dist);
        this.leg3part2b.x = this.leg3part2d.x;
        this.leg3part2.x = this.leg3part2d.x;
        this.leg3part2.xRot = -1.152f + upangle;
        this.leg3part2b.xRot = -0.743f + upangle;
        this.leg3part2c.xRot = -0.632f + upangle;
        this.leg3part2d.xRot = -1.041f + upangle;
        dist = 14.0f;
        dist = (float)((double)dist * Math.cos(this.leg3part2.xRot));
        this.leg3part3.z = (float)((double)this.leg3part2.z - Math.cos(this.leg3part2.yRot) * (double)dist);
        this.leg3part3.x = (float)((double)this.leg3part2.x - Math.sin(this.leg3part2.yRot) * (double)dist);
        this.leg3part3.xRot = 0.669f - upangle;
        dist = 8.0f;
        dist = (float)Math.abs((double)dist * Math.cos(this.leg3part3.xRot));
        this.leg3part3b.z = this.leg3part3c.z = (float)((double)this.leg3part3.z - Math.cos(this.leg3part3.yRot) * (double)dist);
        this.leg3part3b.x = this.leg3part3c.x = (float)((double)this.leg3part3.x - Math.sin(this.leg3part3.yRot) * (double)dist);
        this.leg3part3b.xRot = -0.48f - upangle;
        this.leg3part3c.xRot = -0.48f - upangle;
    }
}

