/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelPitchBlack
 *  com.astryxion.chaospersists.PitchBlack
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

import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelPitchBlack extends EntityModel<PitchBlack> {
    private float wingspeed = 1.0f;
    ModelRenderer lclaw1;
    ModelRenderer body;
    ModelRenderer leftleg1;
    ModelRenderer tail1;
    ModelRenderer leftleg2;
    ModelRenderer body2;
    ModelRenderer leftleg3;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer lclaw2;
    ModelRenderer lclaw3;
    ModelRenderer lclaw4;
    ModelRenderer lclaw5;
    ModelRenderer lclaw6;
    ModelRenderer lclaw7;
    ModelRenderer tail4;
    ModelRenderer tail5;
    ModelRenderer tail6;
    ModelRenderer tail7;
    ModelRenderer tail8;
    ModelRenderer tail9;
    ModelRenderer tailpoint1;
    ModelRenderer tailpoint2;
    ModelRenderer llegspike;
    ModelRenderer tailspike1;
    ModelRenderer tailspike2;
    ModelRenderer tailspike3;
    ModelRenderer tailspike4;
    ModelRenderer tailspike5;
    ModelRenderer tailspike6;
    ModelRenderer neck1;
    ModelRenderer neck2;
    ModelRenderer neck3;
    ModelRenderer head1;
    ModelRenderer leye;
    ModelRenderer reye;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;
    ModelRenderer jaw1;
    ModelRenderer jaw2;
    ModelRenderer jaw3;
    ModelRenderer jaw4;
    ModelRenderer tooth1;
    ModelRenderer tooth2;
    ModelRenderer tooth3;
    ModelRenderer tooth4;
    ModelRenderer tooth5;
    ModelRenderer jaw5;
    ModelRenderer head7;
    ModelRenderer tooth6;
    ModelRenderer tooth7;
    ModelRenderer tooth8;
    ModelRenderer tooth9;
    ModelRenderer tooth10;
    ModelRenderer tooth11;
    ModelRenderer tooth12;
    ModelRenderer tooth13;
    ModelRenderer rightleg1;
    ModelRenderer rightleg2;
    ModelRenderer tooth14;
    ModelRenderer tooth15;
    ModelRenderer tooth16;
    ModelRenderer tooth17;
    ModelRenderer tooth18;
    ModelRenderer tooth19;
    ModelRenderer tooth20;
    ModelRenderer tooth21;
    ModelRenderer tooth22;
    ModelRenderer tooth23;
    ModelRenderer rightleg3;
    ModelRenderer llegspike2;
    ModelRenderer rclaw2;
    ModelRenderer rclaw4;
    ModelRenderer rclaw1;
    ModelRenderer rclaw5;
    ModelRenderer rclaw7;
    ModelRenderer rclaw3;
    ModelRenderer rclaw6;
    ModelRenderer wing1;
    ModelRenderer wing2;
    ModelRenderer wing3;
    ModelRenderer mem1;
    ModelRenderer mem2;
    ModelRenderer mem3;
    ModelRenderer wingclaw1;
    ModelRenderer wingclaw2;
    ModelRenderer wingclaw3;
    ModelRenderer lshoulder;
    ModelRenderer rshoulder;
    ModelRenderer rwing1;
    ModelRenderer rmem1;
    ModelRenderer rwing2;
    ModelRenderer rmem2;
    ModelRenderer rwing3;
    ModelRenderer rmem3;
    ModelRenderer rwingclaw1;
    ModelRenderer rwingclaw2;
    ModelRenderer rwingclaw3;

    public ModelPitchBlack(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 256;
        this.lclaw1 = new ModelRenderer(this, 300, 111);
        this.lclaw1.addBox(-3.0f, 0.0f, -3.0f, 2, 3, 6);
        this.lclaw1.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw1.mirror = true;
        this.setRotation(this.lclaw1, 0.0f, 0.6632251f, 0.0f);
        this.body = new ModelRenderer(this, 400, 26);
        this.body.addBox(-6.0f, -12.0f, -9.0f, 12, 12, 9);
        this.body.setPos(0.0f, 0.0f, 9.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0698132f, 0.0f, 0.0f);
        this.leftleg1 = new ModelRenderer(this, 300, 10);
        this.leftleg1.addBox(-1.0f, -5.0f, -20.0f, 5, 10, 10);
        this.leftleg1.setPos(7.0f, 5.0f, 23.0f);
        this.leftleg1.mirror = true;
        this.setRotation(this.leftleg1, -0.5759587f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 400, 82);
        this.tail1.addBox(-5.0f, -6.0f, 0.0f, 8, 10, 12);
        this.tail1.setPos(1.0f, -3.0f, 22.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, -0.1745329f, 0.0f, 0.0f);
        this.leftleg2 = new ModelRenderer(this, 300, 31);
        this.leftleg2.addBox(-1.0f, -10.0f, -4.0f, 4, 12, 5);
        this.leftleg2.setPos(7.0f, 5.0f, 23.0f);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.9773844f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 400, 50);
        this.body2.addBox(0.0f, -3.0f, -3.0f, 12, 14, 16);
        this.body2.setPos(-6.0f, -9.0f, 10.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, -0.1047198f, 0.0f, 0.0f);
        this.leftleg3 = new ModelRenderer(this, 300, 51);
        this.leftleg3.addBox(-1.0f, -19.0f, 1.0f, 3, 18, 4);
        this.leftleg3.setPos(7.0f, 21.0f, 11.0f);
        this.leftleg3.mirror = true;
        this.setRotation(this.leftleg3, -0.5235988f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 400, 106);
        this.tail2.addBox(-3.0f, -4.0f, 0.0f, 6, 8, 10);
        this.tail2.setPos(0.0f, -2.0f, 33.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, -0.1396263f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 400, 126);
        this.tail3.addBox(-2.0f, -2.0f, 0.0f, 4, 5, 10);
        this.tail3.setPos(0.0f, -1.0f, 42.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, -0.1396263f, 0.0f, 0.0f);
        this.lclaw2 = new ModelRenderer(this, 300, 76);
        this.lclaw2.addBox(-1.0f, -1.0f, -6.0f, 3, 4, 13);
        this.lclaw2.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw2.mirror = true;
        this.setRotation(this.lclaw2, 0.0f, 0.0f, 0.0f);
        this.lclaw3 = new ModelRenderer(this, 300, 95);
        this.lclaw3.addBox(2.0f, 0.0f, -6.0f, 2, 3, 10);
        this.lclaw3.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw3.mirror = true;
        this.setRotation(this.lclaw3, 0.0f, -0.6632251f, 0.0f);
        this.lclaw4 = new ModelRenderer(this, 310, 123);
        this.lclaw4.addBox(0.0f, 1.0f, -9.0f, 1, 2, 3);
        this.lclaw4.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw4.mirror = true;
        this.setRotation(this.lclaw4, 0.0f, 0.0f, 0.0f);
        this.lclaw5 = new ModelRenderer(this, 300, 123);
        this.lclaw5.addBox(-2.5f, 1.0f, -5.0f, 1, 2, 2);
        this.lclaw5.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw5.mirror = true;
        this.setRotation(this.lclaw5, 0.0f, 0.6632251f, 0.0f);
        this.lclaw6 = new ModelRenderer(this, 322, 123);
        this.lclaw6.addBox(2.5f, 1.0f, -9.0f, 1, 2, 3);
        this.lclaw6.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw6.mirror = true;
        this.setRotation(this.lclaw6, 0.0f, -0.6632251f, 0.0f);
        this.lclaw7 = new ModelRenderer(this, 333, 123);
        this.lclaw7.addBox(0.0f, 1.0f, 7.0f, 1, 2, 3);
        this.lclaw7.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw7.mirror = true;
        this.setRotation(this.lclaw7, 0.0f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 400, 143);
        this.tail4.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 10);
        this.tail4.setPos(0.0f, 0.0f, 51.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, -0.1396263f, 0.0f, 0.0f);
        this.tail5 = new ModelRenderer(this, 400, 159);
        this.tail5.addBox(-1.5f, -2.0f, 0.0f, 3, 3, 10);
        this.tail5.setPos(0.0f, 1.0f, 59.0f);
        this.tail5.mirror = true;
        this.setRotation(this.tail5, -0.1396263f, 0.0f, 0.0f);
        this.tail6 = new ModelRenderer(this, 400, 180);
        this.tail6.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10);
        this.tail6.setPos(0.0f, 0.0f, 68.0f);
        this.tail6.mirror = true;
        this.setRotation(this.tail6, -0.1396263f, 0.1745329f, 0.0f);
        this.tail7 = new ModelRenderer(this, 400, 180);
        this.tail7.addBox(-2.0f, 0.0f, 0.0f, 2, 2, 10);
        this.tail7.setPos(0.0f, 0.0f, 68.0f);
        this.tail7.mirror = true;
        this.setRotation(this.tail7, -0.1396263f, -0.1745329f, 0.0f);
        this.tail8 = new ModelRenderer(this, 400, 180);
        this.tail8.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10);
        this.tail8.setPos(-4.0f, 1.0f, 77.0f);
        this.tail8.mirror = true;
        this.setRotation(this.tail8, -0.1396263f, -0.1745329f, 0.0f);
        this.tail9 = new ModelRenderer(this, 400, 180);
        this.tail9.addBox(0.0f, 0.0f, 0.0f, 2, 2, 10);
        this.tail9.setPos(2.0f, 1.0f, 77.0f);
        this.tail9.mirror = true;
        this.setRotation(this.tail9, -0.1396263f, 0.1745329f, 0.0f);
        this.tailpoint1 = new ModelRenderer(this, 400, 200);
        this.tailpoint1.addBox(-1.0f, -1.0f, 0.0f, 1, 1, 22);
        this.tailpoint1.setPos(5.0f, 3.0f, 85.0f);
        this.tailpoint1.mirror = true;
        this.setRotation(this.tailpoint1, -0.1919862f, 0.1745329f, 0.0f);
        this.tailpoint2 = new ModelRenderer(this, 400, 200);
        this.tailpoint2.addBox(-1.0f, -1.0f, 0.0f, 1, 1, 22);
        this.tailpoint2.setPos(-4.0f, 3.0f, 86.0f);
        this.tailpoint2.mirror = true;
        this.setRotation(this.tailpoint2, -0.1919862f, -0.1745329f, 0.0f);
        this.llegspike = new ModelRenderer(this, 300, 131);
        this.llegspike.addBox(0.0f, -28.0f, 1.0f, 1, 12, 1);
        this.llegspike.setPos(7.0f, 21.0f, 11.0f);
        this.llegspike.mirror = true;
        this.setRotation(this.llegspike, -0.6981317f, 0.0f, 0.0f);
        this.tailspike1 = new ModelRenderer(this, 400, 230);
        this.tailspike1.addBox(1.0f, -1.0f, 0.0f, 1, 1, 1);
        this.tailspike1.setPos(2.0f, 1.0f, 77.0f);
        this.tailspike1.mirror = true;
        this.setRotation(this.tailspike1, -0.1396263f, 0.1745329f, 0.0f);
        this.tailspike2 = new ModelRenderer(this, 400, 230);
        this.tailspike2.addBox(1.0f, -1.0f, 6.0f, 1, 1, 1);
        this.tailspike2.setPos(0.0f, 0.0f, 68.0f);
        this.tailspike2.mirror = true;
        this.setRotation(this.tailspike2, -0.1396263f, 0.1745329f, 0.0f);
        this.tailspike3 = new ModelRenderer(this, 400, 230);
        this.tailspike3.addBox(1.0f, -1.0f, 2.0f, 1, 1, 1);
        this.tailspike3.setPos(0.0f, 0.0f, 68.0f);
        this.tailspike3.mirror = true;
        this.setRotation(this.tailspike3, -0.1396263f, 0.1745329f, 0.0f);
        this.tailspike4 = new ModelRenderer(this, 400, 230);
        this.tailspike4.addBox(0.0f, -1.0f, 0.0f, 1, 1, 1);
        this.tailspike4.setPos(-4.0f, 1.0f, 77.0f);
        this.tailspike4.mirror = true;
        this.setRotation(this.tailspike4, -0.1396263f, -0.1745329f, 0.0f);
        this.tailspike5 = new ModelRenderer(this, 400, 230);
        this.tailspike5.addBox(-2.0f, -1.0f, 6.0f, 1, 1, 1);
        this.tailspike5.setPos(0.0f, 0.0f, 68.0f);
        this.tailspike5.mirror = true;
        this.setRotation(this.tailspike5, -0.1396263f, -0.1745329f, 0.0f);
        this.tailspike6 = new ModelRenderer(this, 400, 230);
        this.tailspike6.addBox(-2.0f, -1.0f, 2.0f, 1, 1, 1);
        this.tailspike6.setPos(0.0f, 0.0f, 68.0f);
        this.tailspike6.mirror = true;
        this.setRotation(this.tailspike6, -0.1396263f, -0.1745329f, 0.0f);
        this.neck1 = new ModelRenderer(this, 400, 7);
        this.neck1.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 8);
        this.neck1.setPos(0.0f, -4.0f, -5.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, 0.0872665f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 375, 10);
        this.neck2.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 4);
        this.neck2.setPos(0.0f, -4.0f, -5.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, -0.0523599f, 0.0f, 0.0f);
        this.neck3 = new ModelRenderer(this, 375, 23);
        this.neck3.addBox(-2.0f, -2.0f, -6.0f, 4, 4, 8);
        this.neck3.setPos(0.0f, -4.0f, -9.0f);
        this.neck3.mirror = true;
        this.setRotation(this.neck3, -0.2443461f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 123, 3);
        this.head1.addBox(-18.0f, -1.0f, -12.0f, 36, 1, 12);
        this.head1.setPos(0.0f, -6.0f, -14.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, -0.2443461f, 0.0f, 0.0f);
        this.leye = new ModelRenderer(this, 76, 2);
        this.leye.addBox(18.0f, -1.0f, -13.0f, 3, 3, 15);
        this.leye.setPos(0.0f, -6.0f, -14.0f);
        this.leye.mirror = true;
        this.setRotation(this.leye, -0.2443461f, 0.0f, 0.0f);
        this.reye = new ModelRenderer(this, 32, 2);
        this.reye.addBox(-21.0f, -1.0f, -13.0f, 3, 3, 15);
        this.reye.setPos(0.0f, -6.0f, -14.0f);
        this.reye.mirror = true;
        this.setRotation(this.reye, -0.2443461f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 140, 18);
        this.head2.addBox(-8.0f, -2.0f, -11.0f, 16, 1, 11);
        this.head2.setPos(0.0f, -6.0f, -14.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, -0.2443461f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer(this, 143, 32);
        this.head3.addBox(-2.0f, -4.0f, -14.0f, 4, 4, 16);
        this.head3.setPos(0.0f, -6.0f, -14.0f);
        this.head3.mirror = true;
        this.setRotation(this.head3, -0.2443461f, 0.0f, 0.0f);
        this.head4 = new ModelRenderer(this, 152, 55);
        this.head4.addBox(-1.0f, -10.0f, -13.0f, 2, 8, 12);
        this.head4.setPos(0.0f, -6.0f, -14.0f);
        this.head4.mirror = true;
        this.setRotation(this.head4, -0.3665191f, 0.0f, 0.0f);
        this.head5 = new ModelRenderer(this, 154, 77);
        this.head5.addBox(-0.5f, -18.0f, -11.0f, 1, 9, 9);
        this.head5.setPos(0.0f, -6.0f, -14.0f);
        this.head5.mirror = true;
        this.setRotation(this.head5, -0.4363323f, 0.0f, 0.0f);
        this.head6 = new ModelRenderer(this, 160, 97);
        this.head6.addBox(-0.5f, -24.0f, -10.0f, 1, 8, 4);
        this.head6.setPos(0.0f, -6.0f, -14.0f);
        this.head6.mirror = true;
        this.setRotation(this.head6, -0.6632251f, 0.0f, 0.0f);
        this.jaw1 = new ModelRenderer(this, 143, 114);
        this.jaw1.addBox(-2.0f, 1.0f, -14.0f, 4, 4, 15);
        this.jaw1.setPos(0.0f, -6.0f, -14.0f);
        this.jaw1.mirror = true;
        this.setRotation(this.jaw1, 0.1919862f, 0.0f, 0.0f);
        this.jaw2 = new ModelRenderer(this, 150, 149);
        this.jaw2.addBox(-1.0f, 4.0f, -12.0f, 2, 5, 11);
        this.jaw2.setPos(0.0f, -6.0f, -14.0f);
        this.jaw2.mirror = true;
        this.setRotation(this.jaw2, 0.2617994f, 0.0f, 0.0f);
        this.jaw3 = new ModelRenderer(this, 154, 168);
        this.jaw3.addBox(-0.5f, 8.0f, -10.0f, 1, 4, 8);
        this.jaw3.setPos(0.0f, -6.0f, -14.0f);
        this.jaw3.mirror = true;
        this.setRotation(this.jaw3, 0.3316126f, 0.0f, 0.0f);
        this.jaw4 = new ModelRenderer(this, 158, 182);
        this.jaw4.addBox(-0.5f, 11.0f, -7.0f, 1, 4, 4);
        this.jaw4.setPos(0.0f, -6.0f, -14.0f);
        this.jaw4.mirror = true;
        this.setRotation(this.jaw4, 0.4014257f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer(this, 0, 0);
        this.tooth1.addBox(-2.0f, 0.0f, -14.0f, 1, 3, 1);
        this.tooth1.setPos(0.0f, -6.0f, -14.0f);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, -0.2443461f, 0.0f, 0.0f);
        this.tooth2 = new ModelRenderer(this, 0, 0);
        this.tooth2.addBox(-0.5f, 0.0f, -14.0f, 1, 2, 1);
        this.tooth2.setPos(0.0f, -6.0f, -14.0f);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, -0.2443461f, 0.0f, 0.0f);
        this.tooth3 = new ModelRenderer(this, 0, 0);
        this.tooth3.addBox(1.0f, 0.0f, -14.0f, 1, 3, 1);
        this.tooth3.setPos(0.0f, -6.0f, -14.0f);
        this.tooth3.mirror = true;
        this.setRotation(this.tooth3, -0.2443461f, 0.0f, 0.0f);
        this.tooth4 = new ModelRenderer(this, 0, 0);
        this.tooth4.addBox(-2.0f, 0.0f, -12.0f, 1, 3, 1);
        this.tooth4.setPos(0.0f, -6.0f, -14.0f);
        this.tooth4.mirror = true;
        this.setRotation(this.tooth4, -0.2443461f, 0.0f, 0.0f);
        this.tooth5 = new ModelRenderer(this, 0, 0);
        this.tooth5.addBox(1.0f, 0.0f, -12.0f, 1, 3, 1);
        this.tooth5.setPos(0.0f, -6.0f, -14.0f);
        this.tooth5.mirror = true;
        this.setRotation(this.tooth5, -0.2443461f, 0.0f, 0.0f);
        this.jaw5 = new ModelRenderer(this, 151, 135);
        this.jaw5.addBox(-3.0f, 1.0f, -4.0f, 6, 5, 7);
        this.jaw5.setPos(0.0f, -6.0f, -14.0f);
        this.jaw5.mirror = true;
        this.setRotation(this.jaw5, 0.1919862f, 0.0f, 0.0f);
        this.head7 = new ModelRenderer(this, 185, 34);
        this.head7.addBox(-3.0f, -5.0f, -3.0f, 6, 5, 7);
        this.head7.setPos(0.0f, -6.0f, -14.0f);
        this.head7.mirror = true;
        this.setRotation(this.head7, -0.2443461f, 0.0f, 0.0f);
        this.tooth6 = new ModelRenderer(this, 0, 0);
        this.tooth6.addBox(-2.0f, 0.0f, -10.0f, 1, 2, 1);
        this.tooth6.setPos(0.0f, -6.0f, -14.0f);
        this.tooth6.mirror = true;
        this.setRotation(this.tooth6, -0.2443461f, 0.0f, 0.0f);
        this.tooth7 = new ModelRenderer(this, 0, 0);
        this.tooth7.addBox(1.0f, 0.0f, -10.0f, 1, 2, 1);
        this.tooth7.setPos(0.0f, -6.0f, -14.0f);
        this.tooth7.mirror = true;
        this.setRotation(this.tooth7, -0.2443461f, 0.0f, 0.0f);
        this.tooth8 = new ModelRenderer(this, 0, 0);
        this.tooth8.addBox(-2.0f, 0.0f, -8.0f, 1, 2, 1);
        this.tooth8.setPos(0.0f, -6.0f, -14.0f);
        this.tooth8.mirror = true;
        this.setRotation(this.tooth8, -0.2443461f, 0.0f, 0.0f);
        this.tooth9 = new ModelRenderer(this, 0, 0);
        this.tooth9.addBox(1.0f, 0.0f, -8.0f, 1, 2, 1);
        this.tooth9.setPos(0.0f, -6.0f, -14.0f);
        this.tooth9.mirror = true;
        this.setRotation(this.tooth9, -0.2443461f, 0.0f, 0.0f);
        this.tooth10 = new ModelRenderer(this, 0, 0);
        this.tooth10.addBox(-2.0f, 0.0f, -6.0f, 1, 2, 1);
        this.tooth10.setPos(0.0f, -6.0f, -14.0f);
        this.tooth10.mirror = true;
        this.setRotation(this.tooth10, -0.2443461f, 0.0f, 0.0f);
        this.tooth11 = new ModelRenderer(this, 0, 0);
        this.tooth11.addBox(1.0f, 0.0f, -6.0f, 1, 2, 1);
        this.tooth11.setPos(0.0f, -6.0f, -14.0f);
        this.tooth11.mirror = true;
        this.setRotation(this.tooth11, -0.2443461f, 0.0f, 0.0f);
        this.tooth12 = new ModelRenderer(this, 0, 0);
        this.tooth12.addBox(-2.0f, 0.0f, -4.0f, 1, 1, 1);
        this.tooth12.setPos(0.0f, -6.0f, -14.0f);
        this.tooth12.mirror = true;
        this.setRotation(this.tooth12, -0.2443461f, 0.0f, 0.0f);
        this.tooth13 = new ModelRenderer(this, 0, 0);
        this.tooth13.addBox(1.0f, 0.0f, -4.0f, 1, 1, 1);
        this.tooth13.setPos(0.0f, -6.0f, -14.0f);
        this.tooth13.mirror = true;
        this.setRotation(this.tooth13, -0.2443461f, 0.0f, 0.0f);
        this.rightleg1 = new ModelRenderer(this, 250, 10);
        this.rightleg1.addBox(-1.0f, -5.0f, -20.0f, 5, 10, 10);
        this.rightleg1.setPos(-10.0f, 5.0f, 23.0f);
        this.rightleg1.mirror = true;
        this.setRotation(this.rightleg1, -0.5934119f, 0.0f, 0.0f);
        this.rightleg2 = new ModelRenderer(this, 250, 32);
        this.rightleg2.addBox(0.0f, -10.0f, -4.0f, 4, 12, 5);
        this.rightleg2.setPos(-10.0f, 5.0f, 23.0f);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.9773844f, 0.0f, 0.0f);
        this.tooth14 = new ModelRenderer(this, 0, 0);
        this.tooth14.addBox(0.5f, -2.0f, -14.0f, 1, 3, 1);
        this.tooth14.setPos(0.0f, -6.0f, -14.0f);
        this.tooth14.mirror = true;
        this.setRotation(this.tooth14, 0.1919862f, 0.0f, 0.0f);
        this.tooth15 = new ModelRenderer(this, 0, 0);
        this.tooth15.addBox(-1.5f, -2.0f, -14.0f, 1, 3, 1);
        this.tooth15.setPos(0.0f, -6.0f, -14.0f);
        this.tooth15.mirror = true;
        this.setRotation(this.tooth15, 0.1919862f, 0.0f, 0.0f);
        this.tooth16 = new ModelRenderer(this, 0, 0);
        this.tooth16.addBox(1.0f, -1.0f, -12.0f, 1, 2, 1);
        this.tooth16.setPos(0.0f, -6.0f, -14.0f);
        this.tooth16.mirror = true;
        this.setRotation(this.tooth16, 0.1919862f, 0.0f, 0.0f);
        this.tooth17 = new ModelRenderer(this, 0, 0);
        this.tooth17.addBox(-2.0f, -1.0f, -12.0f, 1, 2, 1);
        this.tooth17.setPos(0.0f, -6.0f, -14.0f);
        this.tooth17.mirror = true;
        this.setRotation(this.tooth17, 0.1919862f, 0.0f, 0.0f);
        this.tooth18 = new ModelRenderer(this, 0, 0);
        this.tooth18.addBox(1.0f, -1.0f, -10.0f, 1, 2, 1);
        this.tooth18.setPos(0.0f, -6.0f, -14.0f);
        this.tooth18.mirror = true;
        this.setRotation(this.tooth18, 0.1919862f, 0.0f, 0.0f);
        this.tooth19 = new ModelRenderer(this, 0, 0);
        this.tooth19.addBox(-2.0f, -1.0f, -10.0f, 1, 2, 1);
        this.tooth19.setPos(0.0f, -6.0f, -14.0f);
        this.tooth19.mirror = true;
        this.setRotation(this.tooth19, 0.1919862f, 0.0f, 0.0f);
        this.tooth20 = new ModelRenderer(this, 0, 0);
        this.tooth20.addBox(-2.0f, -1.0f, -8.0f, 1, 2, 1);
        this.tooth20.setPos(0.0f, -6.0f, -14.0f);
        this.tooth20.mirror = true;
        this.setRotation(this.tooth20, 0.1919862f, 0.0f, 0.0f);
        this.tooth21 = new ModelRenderer(this, 0, 0);
        this.tooth21.addBox(1.0f, -1.0f, -8.0f, 1, 2, 1);
        this.tooth21.setPos(0.0f, -6.0f, -14.0f);
        this.tooth21.mirror = true;
        this.setRotation(this.tooth21, 0.1919862f, 0.0f, 0.0f);
        this.tooth22 = new ModelRenderer(this, 0, 0);
        this.tooth22.addBox(1.0f, 0.0f, -6.0f, 1, 1, 1);
        this.tooth22.setPos(0.0f, -6.0f, -14.0f);
        this.tooth22.mirror = true;
        this.setRotation(this.tooth22, 0.1919862f, 0.0f, 0.0f);
        this.tooth23 = new ModelRenderer(this, 0, 0);
        this.tooth23.addBox(-2.0f, 0.0f, -6.0f, 1, 1, 1);
        this.tooth23.setPos(0.0f, -6.0f, -14.0f);
        this.tooth23.mirror = true;
        this.setRotation(this.tooth23, 0.1919862f, 0.0f, 0.0f);
        this.rightleg3 = new ModelRenderer(this, 250, 52);
        this.rightleg3.addBox(-1.0f, -19.0f, 1.0f, 3, 18, 4);
        this.rightleg3.setPos(-8.0f, 21.0f, 11.0f);
        this.rightleg3.mirror = true;
        this.setRotation(this.rightleg3, -0.5235988f, 0.0f, 0.0f);
        this.llegspike2 = new ModelRenderer(this, 250, 130);
        this.llegspike2.addBox(0.0f, -28.0f, 1.0f, 1, 12, 1);
        this.llegspike2.setPos(-8.0f, 21.0f, 11.0f);
        this.llegspike2.mirror = true;
        this.setRotation(this.llegspike2, -0.6981317f, 0.0f, 0.0f);
        this.rclaw2 = new ModelRenderer(this, 250, 76);
        this.rclaw2.addBox(-1.0f, -1.0f, -6.0f, 3, 4, 13);
        this.rclaw2.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw2.mirror = true;
        this.setRotation(this.rclaw2, 0.0f, 0.0f, 0.0f);
        this.rclaw4 = new ModelRenderer(this, 250, 123);
        this.rclaw4.addBox(0.0f, 1.0f, -9.0f, 1, 2, 3);
        this.rclaw4.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw4.mirror = true;
        this.setRotation(this.rclaw4, 0.0f, 0.0f, 0.0f);
        this.rclaw1 = new ModelRenderer(this, 250, 111);
        this.rclaw1.addBox(2.0f, 0.0f, -4.0f, 2, 3, 6);
        this.rclaw1.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw1.mirror = true;
        this.setRotation(this.rclaw1, 0.0f, -0.6632251f, 0.0f);
        this.rclaw5 = new ModelRenderer(this, 261, 123);
        this.rclaw5.addBox(2.5f, 1.0f, -6.0f, 1, 2, 2);
        this.rclaw5.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw5.mirror = true;
        this.setRotation(this.rclaw5, 0.0f, -0.6632251f, 0.0f);
        this.rclaw7 = new ModelRenderer(this, 283, 123);
        this.rclaw7.addBox(0.0f, 1.0f, 7.0f, 1, 2, 3);
        this.rclaw7.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw7.mirror = true;
        this.setRotation(this.rclaw7, 0.0f, 0.0f, 0.0f);
        this.rclaw3 = new ModelRenderer(this, 250, 95);
        this.rclaw3.addBox(-3.0f, 0.0f, -6.0f, 2, 3, 10);
        this.rclaw3.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw3.mirror = true;
        this.setRotation(this.rclaw3, 0.0f, 0.6632251f, 0.0f);
        this.rclaw6 = new ModelRenderer(this, 270, 123);
        this.rclaw6.addBox(-2.5f, 1.0f, -9.0f, 1, 2, 3);
        this.rclaw6.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw6.mirror = true;
        this.setRotation(this.rclaw6, 0.0f, 0.6632251f, 0.0f);
        this.wing1 = new ModelRenderer(this, 10, 30);
        this.wing1.addBox(-1.0f, -1.0f, -1.0f, 23, 3, 3);
        this.wing1.setPos(6.0f, -12.0f, 3.0f);
        this.wing1.mirror = true;
        this.setRotation(this.wing1, 0.0f, 0.0872665f, -0.1396263f);
        this.wing2 = new ModelRenderer(this, 10, 40);
        this.wing2.addBox(-1.0f, -1.0f, -1.0f, 44, 2, 2);
        this.wing2.setPos(27.0f, -15.0f, 1.0f);
        this.wing2.mirror = true;
        this.setRotation(this.wing2, 0.0f, 0.0f, 0.0f);
        this.wing3 = new ModelRenderer(this, 10, 50);
        this.wing3.addBox(-1.0f, -1.0f, -1.0f, 23, 2, 2);
        this.wing3.setPos(70.0f, -15.0f, 1.0f);
        this.wing3.mirror = true;
        this.setRotation(this.wing3, 0.0f, -0.0872665f, 0.1745329f);
        this.mem1 = new ModelRenderer(this, 10, 60);
        this.mem1.addBox(-2.0f, 0.0f, 0.0f, 24, 1, 21);
        this.mem1.setPos(6.0f, -12.0f, 3.0f);
        this.mem1.mirror = true;
        this.setRotation(this.mem1, 0.0f, 0.0872665f, -0.1396263f);
        this.mem2 = new ModelRenderer(this, 10, 85);
        this.mem2.addBox(0.0f, 0.0f, 0.0f, 43, 1, 21);
        this.mem2.setPos(27.0f, -15.0f, 1.0f);
        this.mem2.mirror = true;
        this.setRotation(this.mem2, 0.0f, 0.0f, 0.0f);
        this.mem3 = new ModelRenderer(this, 10, 110);
        this.mem3.addBox(0.0f, 0.0f, 0.0f, 23, 1, 21);
        this.mem3.setPos(70.0f, -15.0f, 1.0f);
        this.mem3.mirror = true;
        this.setRotation(this.mem3, 0.0f, -0.0872665f, 0.1745329f);
        this.wingclaw1 = new ModelRenderer(this, 85, 49);
        this.wingclaw1.addBox(0.0f, 0.0f, -9.0f, 1, 1, 8);
        this.wingclaw1.setPos(70.0f, -15.0f, 1.0f);
        this.wingclaw1.mirror = true;
        this.setRotation(this.wingclaw1, 0.0f, 0.0f, 0.0f);
        this.wingclaw2 = new ModelRenderer(this, 67, 50);
        this.wingclaw2.addBox(0.0f, 0.0f, -7.0f, 1, 1, 6);
        this.wingclaw2.setPos(70.0f, -15.0f, 1.0f);
        this.wingclaw2.mirror = true;
        this.setRotation(this.wingclaw2, 0.0f, 0.6108652f, 0.0f);
        this.wingclaw3 = new ModelRenderer(this, 106, 50);
        this.wingclaw3.addBox(1.0f, 0.0f, -7.0f, 1, 1, 5);
        this.wingclaw3.setPos(69.0f, -15.0f, 1.0f);
        this.wingclaw3.mirror = true;
        this.setRotation(this.wingclaw3, 0.0f, -0.6108652f, 0.0f);
        this.lshoulder = new ModelRenderer(this, 370, 40);
        this.lshoulder.addBox(0.0f, 0.0f, 0.0f, 3, 2, 6);
        this.lshoulder.setPos(3.0f, -13.0f, 1.0f);
        this.lshoulder.mirror = true;
        this.setRotation(this.lshoulder, 0.0698132f, 0.0f, 0.0f);
        this.rshoulder = new ModelRenderer(this, 370, 50);
        this.rshoulder.addBox(0.0f, 0.0f, 0.0f, 3, 2, 6);
        this.rshoulder.setPos(-6.0f, -13.0f, 1.0f);
        this.rshoulder.mirror = true;
        this.setRotation(this.rshoulder, 0.0698132f, 0.0f, 0.0f);
        this.rwing1 = new ModelRenderer(this, 10, 140);
        this.rwing1.addBox(-22.0f, -1.0f, -1.0f, 23, 3, 3);
        this.rwing1.setPos(-6.0f, -12.0f, 3.0f);
        this.rwing1.mirror = true;
        this.setRotation(this.rwing1, 0.0f, -0.0872665f, 0.1396263f);
        this.rmem1 = new ModelRenderer(this, 10, 170);
        this.rmem1.addBox(-22.0f, 0.0f, 0.0f, 24, 1, 21);
        this.rmem1.setPos(-6.0f, -12.0f, 3.0f);
        this.rmem1.mirror = true;
        this.setRotation(this.rmem1, 0.0f, -0.0872665f, 0.1396263f);
        this.rwing2 = new ModelRenderer(this, 10, 150);
        this.rwing2.addBox(-43.0f, -1.0f, -1.0f, 44, 2, 2);
        this.rwing2.setPos(-27.0f, -15.0f, 1.0f);
        this.rwing2.mirror = true;
        this.setRotation(this.rwing2, 0.0f, 0.0f, 0.0f);
        this.rmem2 = new ModelRenderer(this, 10, 195);
        this.rmem2.addBox(-43.0f, 0.0f, 0.0f, 43, 1, 21);
        this.rmem2.setPos(-27.0f, -15.0f, 1.0f);
        this.rmem2.mirror = true;
        this.setRotation(this.rmem2, 0.0f, 0.0f, 0.0f);
        this.rwing3 = new ModelRenderer(this, 10, 160);
        this.rwing3.addBox(-22.0f, -1.0f, -1.0f, 23, 2, 2);
        this.rwing3.setPos(-70.0f, -15.0f, 1.0f);
        this.rwing3.mirror = true;
        this.setRotation(this.rwing3, 0.0f, 0.0872665f, -0.1745329f);
        this.rmem3 = new ModelRenderer(this, 10, 220);
        this.rmem3.addBox(-23.0f, 0.0f, 0.0f, 23, 1, 21);
        this.rmem3.setPos(-70.0f, -15.0f, 1.0f);
        this.rmem3.mirror = true;
        this.setRotation(this.rmem3, 0.0f, 0.0872665f, -0.1745329f);
        this.rwingclaw1 = new ModelRenderer(this, 81, 157);
        this.rwingclaw1.addBox(0.0f, 0.0f, -9.0f, 1, 1, 8);
        this.rwingclaw1.setPos(-70.0f, -15.0f, 1.0f);
        this.rwingclaw1.mirror = true;
        this.setRotation(this.rwingclaw1, 0.0f, 0.0f, 0.0f);
        this.rwingclaw2 = new ModelRenderer(this, 64, 160);
        this.rwingclaw2.addBox(0.0f, 0.0f, -7.0f, 1, 1, 6);
        this.rwingclaw2.setPos(-70.0f, -15.0f, 1.0f);
        this.rwingclaw2.mirror = true;
        this.setRotation(this.rwingclaw2, 0.0f, -0.6108652f, 0.0f);
        this.rwingclaw3 = new ModelRenderer(this, 103, 159);
        this.rwingclaw3.addBox(0.0f, 0.0f, -6.0f, 1, 1, 5);
        this.rwingclaw3.setPos(-70.0f, -15.0f, 1.0f);
        this.rwingclaw3.mirror = true;
        this.setRotation(this.rwingclaw3, 0.0f, 0.6108652f, 0.0f);
    }
    @Override
    public void setupAnim(PitchBlack entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        PitchBlack e = (PitchBlack)entity;
        float newangle = 0.0f;
        float lspeed = 0.0f;
        RenderInfo r = null;
        float tailspeed = 0.76f;
        float tailamp = 0.25f;
        float pi4 = 0.7853982f;
        float pscale = e.getPitchBlackScale();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        r = e.getRenderInfo();
        newangle = e.getActivity() != 0 ? MathHelper.cos((float)(f2 * 0.45f * this.wingspeed / pscale)) * 3.1415927f * 0.24f : - pi4 + MathHelper.cos((float)(f2 * 0.05f * this.wingspeed / pscale)) * 3.1415927f * 0.02f;
        this.wing1.zRot = newangle;
        this.mem1.zRot = newangle;
        this.wing2.zRot = newangle * 5.0f / 3.0f;
        this.wing2.y = this.wing1.y + (float)Math.sin(this.wing1.zRot) * 21.0f;
        this.wing2.x = this.wing1.x + (float)Math.cos(this.wing1.zRot) * 21.0f;
        this.mem2.zRot = newangle * 5.0f / 3.0f;
        this.mem2.y = this.wing2.y;
        this.mem2.x = this.wing2.x;
        this.wing3.zRot = newangle * 2.0f;
        this.wing3.y = this.wing2.y + (float)Math.sin(this.wing2.zRot) * 43.0f;
        this.wing3.x = this.wing2.x + (float)Math.cos(this.wing2.zRot) * 43.0f;
        this.mem3.zRot = newangle * 2.0f;
        this.mem3.y = this.wing3.y;
        this.mem3.x = this.wing3.x;
        this.wingclaw2.zRot = this.wingclaw3.zRot = newangle * 3.0f / 2.0f;
        this.wingclaw1.zRot = this.wingclaw3.zRot;
        this.wingclaw2.y = this.wingclaw3.y = this.wing3.y;
        this.wingclaw1.y = this.wingclaw3.y;
        this.wingclaw2.x = this.wingclaw3.x = this.wing3.x;
        this.wingclaw1.x = this.wingclaw3.x;
        this.rwing1.zRot = - newangle;
        this.rmem1.zRot = - newangle;
        this.rwing2.zRot = (- newangle) * 5.0f / 3.0f;
        this.rwing2.y = this.rwing1.y - (float)Math.sin(this.rwing1.zRot) * 21.0f;
        this.rwing2.x = this.rwing1.x - (float)Math.cos(this.rwing1.zRot) * 21.0f;
        this.rmem2.zRot = (- newangle) * 5.0f / 3.0f;
        this.rmem2.y = this.rwing2.y;
        this.rmem2.x = this.rwing2.x;
        this.rwing3.zRot = (- newangle) * 2.0f;
        this.rwing3.y = this.rwing2.y - (float)Math.sin(this.rwing2.zRot) * 43.0f;
        this.rwing3.x = this.rwing2.x - (float)Math.cos(this.rwing2.zRot) * 43.0f;
        this.rmem3.zRot = (- newangle) * 2.0f;
        this.rmem3.y = this.rwing3.y;
        this.rmem3.x = this.rwing3.x;
        this.rwingclaw2.zRot = this.rwingclaw3.zRot = (- newangle) * 3.0f / 2.0f;
        this.rwingclaw1.zRot = this.rwingclaw3.zRot;
        this.rwingclaw2.y = this.rwingclaw3.y = this.rwing3.y;
        this.rwingclaw1.y = this.rwingclaw3.y;
        this.rwingclaw2.x = this.rwingclaw3.x = this.rwing3.x;
        this.rwingclaw1.x = this.rwingclaw3.x;
        f3 %= 360.0f;
        f3 = e.getActivity() != 0 ? (f3 *= 0.2f) : (f3 *= 0.55f);
        this.neck3.yRot = (float)Math.toRadians(f3) * 0.5f;
        this.head3.yRot = this.head4.yRot = (float)Math.toRadians(f3);
        this.head2.yRot = this.head4.yRot;
        this.head6.yRot = this.head7.yRot = (this.head1.yRot = this.head4.yRot);
        this.head5.yRot = this.head7.yRot;
        this.jaw4.yRot = this.jaw5.yRot = this.head1.yRot;
        this.jaw3.yRot = this.jaw5.yRot;
        this.jaw2.yRot = this.jaw5.yRot;
        this.jaw1.yRot = this.jaw5.yRot;
        this.tooth4.yRot = this.tooth5.yRot = this.head1.yRot;
        this.tooth3.yRot = this.tooth5.yRot;
        this.tooth2.yRot = this.tooth5.yRot;
        this.tooth1.yRot = this.tooth5.yRot;
        this.tooth9.yRot = this.tooth10.yRot = this.head1.yRot;
        this.tooth8.yRot = this.tooth10.yRot;
        this.tooth7.yRot = this.tooth10.yRot;
        this.tooth6.yRot = this.tooth10.yRot;
        this.tooth14.yRot = this.tooth15.yRot = this.head1.yRot;
        this.tooth13.yRot = this.tooth15.yRot;
        this.tooth12.yRot = this.tooth15.yRot;
        this.tooth11.yRot = this.tooth15.yRot;
        this.tooth19.yRot = this.tooth20.yRot = this.head1.yRot;
        this.tooth18.yRot = this.tooth20.yRot;
        this.tooth17.yRot = this.tooth20.yRot;
        this.tooth16.yRot = this.tooth20.yRot;
        this.tooth22.yRot = this.tooth23.yRot = this.head1.yRot;
        this.tooth21.yRot = this.tooth23.yRot;
        this.reye.yRot = this.leye.yRot = this.head1.yRot;
        if (e.getAttacking() != 0) {
            newangle = MathHelper.cos((float)(f2 * 0.85f * this.wingspeed)) * 3.1415927f * 0.16f;
            newangle += 0.5f;
        } else {
            newangle = f2 * 0.7f * this.wingspeed % 6.2831855f;
            if ((newangle = Math.abs(newangle)) < r.rf1) {
                r.ri1 = 0;
                if (e.level.random.nextInt(20) == 1) {
                    r.ri1 |= 1;
                }
            }
            r.rf1 = newangle;
            if (r.ri1 != 0) {
                newangle = MathHelper.sin((float)(f2 * 0.85f * this.wingspeed)) * 3.1415927f * 0.16f;
                newangle += 0.5f;
            } else {
                newangle = pi4 / 4.0f;
            }
        }
        this.jaw4.xRot = this.jaw5.xRot = newangle;
        this.jaw3.xRot = this.jaw5.xRot;
        this.jaw2.xRot = this.jaw5.xRot;
        this.jaw1.xRot = this.jaw5.xRot;
        this.tooth14.xRot = this.tooth15.xRot = newangle;
        this.tooth19.xRot = this.tooth20.xRot = newangle;
        this.tooth18.xRot = this.tooth20.xRot;
        this.tooth17.xRot = this.tooth20.xRot;
        this.tooth16.xRot = this.tooth20.xRot;
        this.tooth22.xRot = this.tooth23.xRot = newangle;
        this.tooth21.xRot = this.tooth23.xRot;
        float clawZ = 7.0f;
        float clawY = 21.0f;
        float clawZamp = 12.0f * pscale;
        float clawYamp = 6.0f * pscale;
        if (e.getActivity() == 0) {
            float t1 = 0.0f;
            float t2 = 0.0f;
            if ((double)f1 > 0.001) {
                newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed / pscale));
                t1 = MathHelper.sin((float)(f2 * 0.75f * this.wingspeed / pscale));
            } else {
                newangle = 0.0f;
                t1 = 0.0f;
                t2 = 0.0f;
            }
            if (t1 > 0.0f) {
                t2 = t1 * clawYamp * f1;
                this.lclaw1.y = clawY - t2;
            } else {
                this.lclaw1.y = clawY;
            }
            this.lclaw6.z = this.lclaw7.z = (this.lclaw1.z = clawZ + clawZamp * newangle * f1);
            this.lclaw5.z = this.lclaw7.z;
            this.lclaw4.z = this.lclaw7.z;
            this.lclaw3.z = this.lclaw7.z;
            this.lclaw2.z = this.lclaw7.z;
            this.lclaw6.y = this.lclaw7.y = this.lclaw1.y;
            this.lclaw5.y = this.lclaw7.y;
            this.lclaw4.y = this.lclaw7.y;
            this.lclaw3.y = this.lclaw7.y;
            this.lclaw2.y = this.lclaw7.y;
            this.llegspike.z = this.leftleg3.z = this.lclaw1.z;
            this.llegspike.y = this.leftleg3.y = this.lclaw1.y;
            this.leftleg3.xRot = -0.61f + newangle * 3.1415927f * 0.18f * f1;
            this.llegspike.xRot = -0.785f + newangle * 3.1415927f * 0.18f * f1;
            this.leftleg1.xRot = -0.576f + newangle * 3.1415927f * 0.18f * f1;
            this.leftleg2.xRot = 0.977f + newangle * 3.1415927f * 0.18f * f1;
            this.leftleg1.y = this.leftleg2.y = this.leftleg3.y - (float)Math.cos(this.leftleg3.xRot) * 17.0f + t2 / 2.0f;
            this.leftleg1.z = this.leftleg2.z = this.leftleg3.z - (float)Math.sin(this.leftleg3.xRot) * 17.0f;
            t1 = 0.0f;
            t2 = 0.0f;
            if ((double)f1 > 0.001) {
                newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed / pscale + pi4 * 4.0f));
                t1 = MathHelper.sin((float)(f2 * 0.75f * this.wingspeed / pscale + pi4 * 4.0f));
            } else {
                newangle = 0.0f;
                t1 = 0.0f;
                t2 = 0.0f;
            }
            if (t1 > 0.0f) {
                t2 = t1 * clawYamp * f1;
                this.rclaw1.y = clawY - t2;
            } else {
                this.rclaw1.y = clawY;
            }
            this.rclaw6.z = this.rclaw7.z = (this.rclaw1.z = clawZ + clawZamp * newangle * f1);
            this.rclaw5.z = this.rclaw7.z;
            this.rclaw4.z = this.rclaw7.z;
            this.rclaw3.z = this.rclaw7.z;
            this.rclaw2.z = this.rclaw7.z;
            this.rclaw6.y = this.rclaw7.y = this.rclaw1.y;
            this.rclaw5.y = this.rclaw7.y;
            this.rclaw4.y = this.rclaw7.y;
            this.rclaw3.y = this.rclaw7.y;
            this.rclaw2.y = this.rclaw7.y;
            this.llegspike2.z = this.rightleg3.z = this.rclaw1.z;
            this.llegspike2.y = this.rightleg3.y = this.rclaw1.y;
            this.rightleg3.xRot = -0.61f + newangle * 3.1415927f * 0.18f * f1;
            this.llegspike2.xRot = -0.785f + newangle * 3.1415927f * 0.18f * f1;
            this.rightleg1.xRot = -0.576f + newangle * 3.1415927f * 0.18f * f1;
            this.rightleg2.xRot = 0.977f + newangle * 3.1415927f * 0.18f * f1;
            this.rightleg1.y = this.rightleg2.y = this.rightleg3.y - (float)Math.cos(this.rightleg3.xRot) * 17.0f + t2 / 2.0f;
            this.rightleg1.z = this.rightleg2.z = this.rightleg3.z - (float)Math.sin(this.rightleg3.xRot) * 17.0f;
            this.lclaw1.xRot = 0.0f;
            this.lclaw7.xRot = 0.0f;
            this.lclaw6.xRot = 0.0f;
            this.lclaw5.xRot = 0.0f;
            this.lclaw4.xRot = 0.0f;
            this.lclaw3.xRot = 0.0f;
            this.lclaw2.xRot = 0.0f;
            this.rclaw1.xRot = 0.0f;
            this.rclaw7.xRot = 0.0f;
            this.rclaw6.xRot = 0.0f;
            this.rclaw5.xRot = 0.0f;
            this.rclaw4.xRot = 0.0f;
            this.rclaw3.xRot = 0.0f;
            this.rclaw2.xRot = 0.0f;
        } else {
            clawZ = 7.0f;
            clawY = 9.0f;
            newangle = e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 0.85f * this.wingspeed / pscale)) * 0.2f : 0.0f;
            this.lclaw1.z = clawZ;
            this.lclaw1.y = clawY + newangle * 30.0f;
            this.lclaw1.xRot = -0.7f + newangle;
            this.lclaw6.z = this.lclaw7.z = this.lclaw1.z;
            this.lclaw5.z = this.lclaw7.z;
            this.lclaw4.z = this.lclaw7.z;
            this.lclaw3.z = this.lclaw7.z;
            this.lclaw2.z = this.lclaw7.z;
            this.lclaw6.y = this.lclaw7.y = this.lclaw1.y;
            this.lclaw5.y = this.lclaw7.y;
            this.lclaw4.y = this.lclaw7.y;
            this.lclaw3.y = this.lclaw7.y;
            this.lclaw2.y = this.lclaw7.y;
            this.lclaw6.xRot = this.lclaw7.xRot = this.lclaw1.xRot;
            this.lclaw5.xRot = this.lclaw7.xRot;
            this.lclaw4.xRot = this.lclaw7.xRot;
            this.lclaw3.xRot = this.lclaw7.xRot;
            this.lclaw2.xRot = this.lclaw7.xRot;
            this.llegspike.z = this.leftleg3.z = this.lclaw1.z;
            this.llegspike.y = this.leftleg3.y = this.lclaw1.y;
            this.leftleg3.xRot = -0.61f + this.lclaw1.xRot;
            this.llegspike.xRot = -0.785f + this.lclaw1.xRot;
            this.leftleg1.xRot = -0.576f - this.lclaw1.xRot / 4.0f;
            this.leftleg2.xRot = 0.977f - this.lclaw1.xRot / 4.0f;
            this.leftleg1.y = this.leftleg2.y = this.leftleg3.y - (float)Math.cos(this.leftleg3.xRot) * 17.0f;
            this.leftleg1.z = this.leftleg2.z = this.leftleg3.z - (float)Math.sin(this.leftleg3.xRot) * 17.0f;
            this.rclaw1.z = clawZ;
            this.rclaw1.y = clawY - newangle * 30.0f;
            this.rclaw1.xRot = -0.7f - newangle;
            this.rclaw6.z = this.rclaw7.z = this.rclaw1.z;
            this.rclaw5.z = this.rclaw7.z;
            this.rclaw4.z = this.rclaw7.z;
            this.rclaw3.z = this.rclaw7.z;
            this.rclaw2.z = this.rclaw7.z;
            this.rclaw6.y = this.rclaw7.y = this.rclaw1.y;
            this.rclaw5.y = this.rclaw7.y;
            this.rclaw4.y = this.rclaw7.y;
            this.rclaw3.y = this.rclaw7.y;
            this.rclaw2.y = this.rclaw7.y;
            this.rclaw6.xRot = this.rclaw7.xRot = this.rclaw1.xRot;
            this.rclaw5.xRot = this.rclaw7.xRot;
            this.rclaw4.xRot = this.rclaw7.xRot;
            this.rclaw3.xRot = this.rclaw7.xRot;
            this.rclaw2.xRot = this.rclaw7.xRot;
            this.llegspike2.z = this.rightleg3.z = this.rclaw1.z;
            this.llegspike2.y = this.rightleg3.y = this.rclaw1.y;
            this.rightleg3.xRot = -0.61f + this.rclaw1.xRot;
            this.llegspike2.xRot = -0.785f + this.rclaw1.xRot;
            this.rightleg1.xRot = -0.576f - this.rclaw1.xRot / 4.0f;
            this.rightleg2.xRot = 0.977f - this.rclaw1.xRot / 4.0f;
            this.rightleg1.y = this.rightleg2.y = this.rightleg3.y - (float)Math.cos(this.rightleg3.xRot) * 17.0f;
            this.rightleg1.z = this.rightleg2.z = this.rightleg3.z - (float)Math.sin(this.rightleg3.xRot) * 17.0f;
        }
        if (e.getAttacking() != 0) {
            tailspeed = 0.76f / pscale;
            tailamp = 0.25f;
        } else {
            tailspeed = 0.26f / pscale;
            tailamp = 0.08f;
        }
        this.tail1.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp / 2.0f;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 11.0f;
        this.tail2.x = this.tail1.x - 1.0f + (float)Math.sin(this.tail1.yRot) * 11.0f;
        this.tail2.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed - pi4)) * 3.1415927f * tailamp;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 9.0f;
        this.tail3.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 9.0f;
        this.tail3.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed - 2.0f * pi4)) * 3.1415927f * tailamp;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 9.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 9.0f;
        this.tail4.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed - 3.0f * pi4)) * 3.1415927f * tailamp;
        this.tail5.z = this.tail4.z + (float)Math.cos(this.tail4.yRot) * 9.0f;
        this.tail5.x = this.tail4.x + (float)Math.sin(this.tail4.yRot) * 9.0f;
        newangle = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed - 3.0f * pi4)) * 3.1415927f * tailamp;
        this.tail5.yRot = this.tail4.yRot + (newangle /= 2.0f);
        this.tail6.z = this.tail5.z + (float)Math.cos(this.tail5.yRot) * 9.0f;
        this.tail6.x = this.tail5.x + (float)Math.sin(this.tail5.yRot) * 9.0f;
        this.tail6.yRot = 0.174f + this.tail5.yRot + newangle;
        this.tailspike2.z = this.tailspike3.z = this.tail6.z;
        this.tailspike2.x = this.tailspike3.x = this.tail6.x;
        this.tailspike2.yRot = this.tailspike3.yRot = this.tail6.yRot;
        this.tail9.z = this.tail6.z + (float)Math.cos(this.tail6.yRot) * 9.0f;
        this.tail9.x = this.tail6.x + (float)Math.sin(this.tail6.yRot) * 9.0f;
        this.tail9.yRot = this.tail6.yRot + newangle;
        this.tailspike1.z = this.tail9.z;
        this.tailspike1.x = this.tail9.x;
        this.tailspike1.yRot = this.tail9.yRot;
        this.tailpoint1.z = this.tail9.z + (float)Math.cos(this.tail9.yRot) * 9.0f;
        this.tailpoint1.x = this.tail9.x + (float)Math.sin(this.tail9.yRot) * 9.0f;
        this.tailpoint1.yRot = this.tail9.yRot + newangle;
        this.tail7.z = this.tail5.z + (float)Math.cos(this.tail5.yRot) * 9.0f;
        this.tail7.x = this.tail5.x + (float)Math.sin(this.tail5.yRot) * 9.0f;
        this.tail7.yRot = -0.174f + this.tail5.yRot + newangle;
        this.tailspike5.z = this.tailspike6.z = this.tail7.z;
        this.tailspike5.x = this.tailspike6.x = this.tail7.x;
        this.tailspike5.yRot = this.tailspike6.yRot = this.tail7.yRot;
        this.tail8.z = this.tail7.z + (float)Math.cos(this.tail7.yRot) * 9.0f;
        this.tail8.x = this.tail7.x + (float)Math.sin(this.tail7.yRot) * 9.0f;
        this.tail8.yRot = this.tail7.yRot + newangle;
        this.tailspike4.z = this.tail8.z;
        this.tailspike4.x = this.tail8.x;
        this.tailspike4.yRot = this.tail8.yRot;
        this.tailpoint2.z = this.tail8.z + (float)Math.cos(this.tail8.yRot) * 9.0f;
        this.tailpoint2.x = this.tail8.x + (float)Math.sin(this.tail8.yRot) * 9.0f;
        this.tailpoint2.yRot = this.tail8.yRot + newangle;
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailpoint1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailpoint2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailspike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailspike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailspike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailspike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailspike5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailspike6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.reye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth18.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth19.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth20.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth22.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth23.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegspike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mem1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mem2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mem3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wingclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wingclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wingclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmem1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmem2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmem3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwingclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwingclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwingclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, PitchBlack par7Entity) {
        
    }
}

