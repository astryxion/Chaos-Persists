/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EnderReaper
 *  com.astryxion.chaospersists.ModelEnderReaper
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.EnderReaper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEnderReaper extends EntityModel<EnderReaper> {
    ModelRenderer rwing1;
    ModelRenderer lwing1;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;
    ModelRenderer Shape32;
    ModelRenderer Shape33;
    ModelRenderer Shape34;
    ModelRenderer Shape35;
    ModelRenderer Shape36;
    ModelRenderer Shape37;
    ModelRenderer Shape38;
    ModelRenderer Shape39;
    ModelRenderer Shape40;
    ModelRenderer Shape41;
    ModelRenderer Shape42;
    ModelRenderer Shape43;
    ModelRenderer Shape44;
    ModelRenderer Shape45;
    ModelRenderer Shape46;
    ModelRenderer Shape47;
    ModelRenderer Shape48;
    ModelRenderer Shape49;
    ModelRenderer rarm2;
    ModelRenderer rarm3;
    ModelRenderer relbow;
    ModelRenderer rarm1;
    ModelRenderer Shape54;
    ModelRenderer larm3;
    ModelRenderer larm2;
    ModelRenderer lelbow;
    ModelRenderer larm1;
    ModelRenderer scythe1;
    ModelRenderer scythe2;
    ModelRenderer scythe3;
    ModelRenderer head;
    ModelRenderer lwing3;
    ModelRenderer lwing2;
    ModelRenderer rwing3;
    ModelRenderer rwing2;
    private float wingspeed = 1.0f;

    public ModelEnderReaper(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 512;
        this.rwing1 = new ModelRenderer(this, 20, 430);
        this.rwing1.addBox(0.0f, 0.0f, 0.0f, 0, 50, 17);
        this.rwing1.setPos(-4.0f, -6.9f, 8.5f);
        this.rwing1.mirror = true;
        this.setRotation(this.rwing1, 1.745f, -0.785f, 0.0f);
        this.lwing1 = new ModelRenderer(this, 20, 350);
        this.lwing1.addBox(0.0f, 0.0f, 0.0f, 0, 50, 17);
        this.lwing1.setPos(4.0f, -6.9f, 8.5f);
        this.lwing1.mirror = true;
        this.setRotation(this.lwing1, 1.745f, 0.785f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 20, 320);
        this.Shape3.addBox(-4.0f, 0.0f, -2.0f, 2, 12, 1);
        this.Shape3.setPos(3.0f, -14.0f, 10.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer(this, 40, 320);
        this.Shape4.addBox(-4.0f, 0.0f, -2.0f, 2, 6, 1);
        this.Shape4.setPos(3.0f, -2.0f, 10.0f);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.247f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer(this, 20, 310);
        this.Shape5.addBox(-4.0f, 0.0f, -2.0f, 1, 3, 1);
        this.Shape5.setPos(3.5f, 4.0f, 8.0f);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.768f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer(this, 20, 292);
        this.Shape6.addBox(-4.0f, 0.0f, -2.0f, 2, 6, 2);
        this.Shape6.setPos(3.0f, -12.0f, 7.5f);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, -2.356f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer(this, 20, 280);
        this.Shape7.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape7.setPos(5.0f, -14.0f, 10.0f);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer(this, 20, 269);
        this.Shape8.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape8.setPos(-1.0f, -14.0f, 10.0f);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer(this, 20, 257);
        this.Shape9.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape9.setPos(-1.0f, -12.0f, 10.0f);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer(this, 20, 246);
        this.Shape10.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape10.setPos(-1.0f, -10.0f, 10.0f);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer(this, 20, 237);
        this.Shape11.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape11.setPos(-1.0f, -8.0f, 10.0f);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer(this, 20, 228);
        this.Shape12.addBox(-4.0f, 0.0f, -2.0f, 2, 1, 1);
        this.Shape12.setPos(1.0f, -6.0f, 10.0f);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        this.Shape13 = new ModelRenderer(this, 20, 219);
        this.Shape13.addBox(-4.0f, 0.0f, -2.0f, 3, 1, 1);
        this.Shape13.setPos(1.0f, -4.0f, 10.0f);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        this.Shape14 = new ModelRenderer(this, 20, 209);
        this.Shape14.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 1);
        this.Shape14.setPos(3.5f, -14.0f, 11.0f);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        this.Shape15 = new ModelRenderer(this, 20, 201);
        this.Shape15.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 1);
        this.Shape15.setPos(3.5f, -12.0f, 11.0f);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        this.Shape16 = new ModelRenderer(this, 20, 194);
        this.Shape16.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 1);
        this.Shape16.setPos(3.5f, -10.0f, 11.0f);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer(this, 20, 185);
        this.Shape17.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 1);
        this.Shape17.setPos(3.5f, -8.0f, 11.0f);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
        this.Shape18 = new ModelRenderer(this, 20, 175);
        this.Shape18.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 1);
        this.Shape18.setPos(3.5f, -6.0f, 11.0f);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0f, 0.0f, 0.0f);
        this.Shape19 = new ModelRenderer(this, 20, 165);
        this.Shape19.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 1);
        this.Shape19.setPos(3.5f, -4.0f, 11.0f);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0f, 0.0f, 0.0f);
        this.Shape20 = new ModelRenderer(this, 20, 155);
        this.Shape20.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape20.setPos(5.0f, -12.0f, 10.0f);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0f, 0.0f, 0.0f);
        this.Shape21 = new ModelRenderer(this, 20, 146);
        this.Shape21.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape21.setPos(5.0f, -10.0f, 10.0f);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0f, 0.0f, 0.0f);
        this.Shape22 = new ModelRenderer(this, 20, 139);
        this.Shape22.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape22.setPos(5.0f, -8.0f, 10.0f);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0f, 0.0f, 0.0f);
        this.Shape23 = new ModelRenderer(this, 20, 132);
        this.Shape23.addBox(-4.0f, 0.0f, -2.0f, 3, 1, 1);
        this.Shape23.setPos(5.0f, -6.0f, 10.0f);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0f, 0.0f, 0.0f);
        this.Shape24 = new ModelRenderer(this, 20, 124);
        this.Shape24.addBox(-4.0f, 0.0f, -2.0f, 2, 1, 1);
        this.Shape24.setPos(5.0f, -4.0f, 10.0f);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.0f, 0.0f, 0.0f);
        this.Shape25 = new ModelRenderer(this, 20, 114);
        this.Shape25.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 3);
        this.Shape25.setPos(6.0f, -4.0f, 8.0f);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, 0.0f, 0.0f, 0.0f);
        this.Shape26 = new ModelRenderer(this, 20, 106);
        this.Shape26.addBox(-4.0f, 0.0f, -2.0f, 2, 1, 1);
        this.Shape26.setPos(5.0f, -4.0f, 8.0f);
        this.Shape26.mirror = true;
        this.setRotation(this.Shape26, 0.0f, 0.0f, 0.0f);
        this.Shape27 = new ModelRenderer(this, 20, 94);
        this.Shape27.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 5);
        this.Shape27.setPos(7.0f, -6.0f, 6.0f);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, 0.0f, 0.0f, 0.0f);
        this.Shape28 = new ModelRenderer(this, 20, 83);
        this.Shape28.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 5);
        this.Shape28.setPos(8.0f, -8.0f, 5.0f);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, 0.0f, 0.0f, 0.0f);
        this.Shape29 = new ModelRenderer(this, 20, 70);
        this.Shape29.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 6);
        this.Shape29.setPos(8.0f, -10.0f, 4.0f);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.0f, 0.0f, 0.0f);
        this.Shape30 = new ModelRenderer(this, 20, 59);
        this.Shape30.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 6);
        this.Shape30.setPos(8.0f, -12.0f, 4.0f);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, 0.0f, 0.0f, 0.0f);
        this.Shape31 = new ModelRenderer(this, 20, 47);
        this.Shape31.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 6);
        this.Shape31.setPos(8.0f, -14.0f, 4.0f);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, 0.0f, 0.0f, 0.0f);
        this.Shape32 = new ModelRenderer(this, 20, 37);
        this.Shape32.addBox(-4.0f, 0.0f, -2.0f, 2, 1, 1);
        this.Shape32.setPos(6.0f, -6.0f, 6.0f);
        this.Shape32.mirror = true;
        this.setRotation(this.Shape32, 0.0f, 0.0f, 0.0f);
        this.Shape33 = new ModelRenderer(this, 20, 29);
        this.Shape33.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape33.setPos(5.0f, -8.0f, 5.0f);
        this.Shape33.mirror = true;
        this.setRotation(this.Shape33, 0.0f, 0.0f, 0.0f);
        this.Shape34 = new ModelRenderer(this, 40, 312);
        this.Shape34.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape34.setPos(5.0f, -10.0f, 4.0f);
        this.Shape34.mirror = true;
        this.setRotation(this.Shape34, 0.0f, 0.0f, 0.0f);
        this.Shape35 = new ModelRenderer(this, 40, 301);
        this.Shape35.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape35.setPos(5.0f, -12.0f, 4.0f);
        this.Shape35.mirror = true;
        this.setRotation(this.Shape35, 0.0f, 0.0f, 0.0f);
        this.Shape36 = new ModelRenderer(this, 40, 291);
        this.Shape36.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape36.setPos(5.0f, -14.0f, 4.0f);
        this.Shape36.mirror = true;
        this.setRotation(this.Shape36, 0.0f, 0.0f, 0.0f);
        this.Shape37 = new ModelRenderer(this, 40, 278);
        this.Shape37.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 3);
        this.Shape37.setPos(1.0f, -4.0f, 8.0f);
        this.Shape37.mirror = true;
        this.setRotation(this.Shape37, 0.0f, 0.0f, 0.0f);
        this.Shape38 = new ModelRenderer(this, 40, 265);
        this.Shape38.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 5);
        this.Shape38.setPos(0.0f, -6.0f, 6.0f);
        this.Shape38.mirror = true;
        this.setRotation(this.Shape38, 0.0f, 0.0f, 0.0f);
        this.Shape39 = new ModelRenderer(this, 40, 251);
        this.Shape39.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 6);
        this.Shape39.setPos(-1.0f, -8.0f, 5.0f);
        this.Shape39.mirror = true;
        this.setRotation(this.Shape39, 0.0f, 0.0f, 0.0f);
        this.Shape40 = new ModelRenderer(this, 40, 235);
        this.Shape40.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 6);
        this.Shape40.setPos(-1.0f, -10.0f, 4.0f);
        this.Shape40.mirror = true;
        this.setRotation(this.Shape40, 0.0f, 0.0f, 0.0f);
        this.Shape41 = new ModelRenderer(this, 40, 222);
        this.Shape41.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 6);
        this.Shape41.setPos(-1.0f, -12.0f, 4.0f);
        this.Shape41.mirror = true;
        this.setRotation(this.Shape41, 0.0f, 0.0f, 0.0f);
        this.Shape42 = new ModelRenderer(this, 40, 209);
        this.Shape42.addBox(-4.0f, 0.0f, -2.0f, 1, 1, 6);
        this.Shape42.setPos(-1.0f, -14.0f, 4.0f);
        this.Shape42.mirror = true;
        this.setRotation(this.Shape42, 0.0f, 0.0f, 0.0f);
        this.Shape43 = new ModelRenderer(this, 40, 200);
        this.Shape43.addBox(-4.0f, 0.0f, -2.0f, 2, 1, 1);
        this.Shape43.setPos(1.0f, -4.0f, 8.0f);
        this.Shape43.mirror = true;
        this.setRotation(this.Shape43, 0.0f, 0.0f, 0.0f);
        this.Shape44 = new ModelRenderer(this, 40, 189);
        this.Shape44.addBox(-4.0f, 0.0f, -2.0f, 2, 1, 1);
        this.Shape44.setPos(0.0f, -6.0f, 6.0f);
        this.Shape44.mirror = true;
        this.setRotation(this.Shape44, 0.0f, 0.0f, 0.0f);
        this.Shape45 = new ModelRenderer(this, 40, 180);
        this.Shape45.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape45.setPos(-1.0f, -8.0f, 5.0f);
        this.Shape45.mirror = true;
        this.setRotation(this.Shape45, 0.0f, 0.0f, 0.0f);
        this.Shape46 = new ModelRenderer(this, 40, 170);
        this.Shape46.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape46.setPos(-1.0f, -10.0f, 4.0f);
        this.Shape46.mirror = true;
        this.setRotation(this.Shape46, 0.0f, 0.0f, 0.0f);
        this.Shape47 = new ModelRenderer(this, 40, 161);
        this.Shape47.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape47.setPos(-1.0f, -12.0f, 4.0f);
        this.Shape47.mirror = true;
        this.setRotation(this.Shape47, 0.0f, 0.0f, 0.0f);
        this.Shape48 = new ModelRenderer(this, 40, 151);
        this.Shape48.addBox(-4.0f, 0.0f, -2.0f, 4, 1, 1);
        this.Shape48.setPos(-1.0f, -14.0f, 4.0f);
        this.Shape48.mirror = true;
        this.setRotation(this.Shape48, 0.0f, 0.0f, 0.0f);
        this.Shape49 = new ModelRenderer(this, 40, 140);
        this.Shape49.addBox(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.Shape49.setPos(-7.5f, -15.5f, 3.0f);
        this.Shape49.mirror = true;
        this.setRotation(this.Shape49, 0.0f, 0.0f, 0.524f);
        this.rarm2 = new ModelRenderer(this, 40, 122);
        this.rarm2.addBox(-4.0f, 0.0f, -2.0f, 1, 12, 1);
        this.rarm2.setPos(-5.0f, -11.5f, 8.0f);
        this.rarm2.mirror = true;
        this.setRotation(this.rarm2, 0.0f, -0.5f, 0.524f);
        this.rarm3 = new ModelRenderer(this, 49, 122);
        this.rarm3.addBox(-4.0f, 0.0f, -2.0f, 1, 12, 1);
        this.rarm3.setPos(-4.0f, -11.5f, 6.0f);
        this.rarm3.mirror = true;
        this.setRotation(this.rarm3, 0.0f, -0.5f, 0.524f);
        this.relbow = new ModelRenderer(this, 40, 111);
        this.relbow.addBox(0.0f, 0.0f, 0.0f, 2, 2, 3);
        this.relbow.setPos(-11.0f, -3.5f, 3.0f);
        this.relbow.mirror = true;
        this.setRotation(this.relbow, 0.0f, -0.5f, 0.524f);
        this.rarm1 = new ModelRenderer(this, 40, 91);
        this.rarm1.addBox(-2.0f, -1.0f, -1.0f, 1, 11, 2);
        this.rarm1.setPos(-10.5f, -2.0f, 2.5f);
        this.rarm1.mirror = true;
        this.setRotation(this.rarm1, -0.76f, 0.0f, 0.3f);
        this.Shape54 = new ModelRenderer(this, 40, 78);
        this.Shape54.addBox(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.Shape54.setPos(5.0f, -14.0f, 3.0f);
        this.Shape54.mirror = true;
        this.setRotation(this.Shape54, 0.0f, 0.0f, -0.524f);
        this.larm3 = new ModelRenderer(this, 40, 58);
        this.larm3.addBox(-4.0f, 0.0f, -2.0f, 1, 12, 1);
        this.larm3.setPos(9.5f, -15.0f, 3.0f);
        this.larm3.mirror = true;
        this.setRotation(this.larm3, 0.0f, 0.5f, -0.524f);
        this.larm2 = new ModelRenderer(this, 40, 35);
        this.larm2.addBox(-4.0f, 0.0f, -2.0f, 1, 12, 1);
        this.larm2.setPos(10.5f, -15.0f, 5.0f);
        this.larm2.mirror = true;
        this.setRotation(this.larm2, 0.0f, 0.5f, -0.524f);
        this.lelbow = new ModelRenderer(this, 55, 38);
        this.lelbow.addBox(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.lelbow.setPos(10.0f, -3.0f, 3.0f);
        this.lelbow.mirror = true;
        this.setRotation(this.lelbow, 0.0f, 0.5f, -0.524f);
        this.larm1 = new ModelRenderer(this, 56, 53);
        this.larm1.addBox(0.0f, 0.0f, -1.0f, 1, 9, 2);
        this.larm1.setPos(12.0f, -3.0f, 2.5f);
        this.larm1.mirror = true;
        this.setRotation(this.larm1, 0.0f, -0.6f, -0.3f);
        this.scythe1 = new ModelRenderer(this, 57, 70);
        this.scythe1.addBox(0.0f, -39.0f, 1.0f, 1, 39, 1);
        this.scythe1.setPos(-17.0f, 6.0f, -2.0f);
        this.scythe1.mirror = true;
        this.setRotation(this.scythe1, 0.0f, 0.0f, 1.0f);
        this.scythe2 = new ModelRenderer(this, 58, 118);
        this.scythe2.addBox(0.0f, -39.0f, 1.0f, 16, 6, 0);
        this.scythe2.setPos(-17.0f, 6.0f, -2.0f);
        this.scythe2.mirror = true;
        this.setRotation(this.scythe2, 0.0f, 0.0f, 1.0f);
        this.scythe3 = new ModelRenderer(this, 61, 133);
        this.scythe3.addBox(9.0f, -34.0f, 1.0f, 7, 5, 0);
        this.scythe3.setPos(-17.0f, 6.0f, -2.0f);
        this.scythe3.mirror = true;
        this.setRotation(this.scythe3, 0.0f, 0.0f, 1.0f);
        this.head = new ModelRenderer(this, 58, 145);
        this.head.addBox(-3.0f, -6.0f, -3.0f, 6, 6, 5);
        this.head.setPos(0.0f, -16.0f, 4.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.lwing3 = new ModelRenderer(this, 71, 58);
        this.lwing3.addBox(-0.5f, 0.0f, 0.0f, 1, 19, 3);
        this.lwing3.setPos(4.0f, -11.7f, 8.5f);
        this.lwing3.mirror = true;
        this.setRotation(this.lwing3, 2.356f, 0.785f, 0.0f);
        this.lwing2 = new ModelRenderer(this, 58, 168);
        this.lwing2.addBox(-0.5f, 11.0f, -2.0f, 1, 19, 3);
        this.lwing2.setPos(4.0f, -23.9f, 8.5f);
        this.lwing2.mirror = true;
        this.setRotation(this.lwing2, 1.745f, 0.785f, 0.0f);
        this.rwing3 = new ModelRenderer(this, 71, 88);
        this.rwing3.addBox(-0.5f, 0.0f, 0.0f, 1, 19, 3);
        this.rwing3.setPos(-4.0f, -11.7f, 8.5f);
        this.rwing3.mirror = true;
        this.setRotation(this.rwing3, 2.356f, -0.785f, 0.0f);
        this.rwing2 = new ModelRenderer(this, 73, 168);
        this.rwing2.addBox(-0.5f, 12.0f, -2.0f, 1, 19, 3);
        this.rwing2.setPos(-4.0f, -23.9f, 8.5f);
        this.rwing2.mirror = true;
        this.setRotation(this.rwing2, 1.745f, -0.785f, 0.0f);
    }
    @Override
    public void setupAnim(EnderReaper entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        EnderReaper e = (EnderReaper)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.scythe2.zRot = this.scythe1.zRot = 1.0f - Math.abs(newangle);
        this.scythe3.zRot = this.scythe1.zRot;
        if (e.isScreaming()) {
            newangle = MathHelper.cos((float)(f2 * 1.9f * this.wingspeed)) * 3.1415927f * 0.25f;
            this.scythe2.zRot = this.scythe1.zRot = 1.0f + newangle;
            this.scythe3.zRot = this.scythe1.zRot;
            this.larm1.xRot = -0.436f;
            this.larm1.yRot = -0.488f;
            newangle = MathHelper.cos((float)(f2 * 2.7f * this.wingspeed)) * 3.1415927f * 0.3f;
        } else {
            this.larm1.xRot = -2.436f;
            this.larm1.yRot = 1.0f;
            newangle = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.06f;
        }
        this.lwing2.yRot = this.lwing3.yRot = 0.785f + newangle;
        this.lwing1.yRot = this.lwing3.yRot;
        this.rwing2.yRot = this.rwing3.yRot = -0.785f - newangle;
        this.rwing1.yRot = this.rwing3.yRot;
        this.head.yRot = (float)Math.toRadians(f3) * 0.45f;
        if (this.head.yRot > 0.45f) {
            this.head.yRot = 0.45f;
        }
        if (this.head.yRot < -0.45f) {
            this.head.yRot = -0.45f;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rwing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape18.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape19.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape20.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape22.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape23.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape24.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape25.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape26.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape27.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape28.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape29.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape30.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape31.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape32.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape33.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape34.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape35.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape36.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape37.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape38.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape39.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape40.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape41.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape42.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape43.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape44.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape45.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape46.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape47.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape48.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape49.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.relbow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape54.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lelbow.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.scythe1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.scythe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.scythe3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

