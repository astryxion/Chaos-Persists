/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelNastysaurus
 *  com.astryxion.chaospersists.Nastysaurus
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

import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelNastysaurus extends EntityModel<Nastysaurus> {
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
    ModelRenderer neck3;
    ModelRenderer head3;
    ModelRenderer jaw1;
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
    ModelRenderer rclaw2;
    ModelRenderer rclaw4;
    ModelRenderer rclaw1;
    ModelRenderer rclaw5;
    ModelRenderer rclaw7;
    ModelRenderer rclaw3;
    ModelRenderer rclaw6;
    ModelRenderer neck1;
    ModelRenderer neck2;
    ModelRenderer tail4;
    ModelRenderer Spike1;
    ModelRenderer Spike2;
    ModelRenderer Spike3;

    public ModelNastysaurus(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 256;
        this.lclaw1 = new ModelRenderer(this, 300, 111);
        this.lclaw1.addBox(-3.0f, 0.0f, -3.0f, 2, 3, 6);
        this.lclaw1.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw1.mirror = true;
        this.setRotation(this.lclaw1, 0.0f, 0.6632251f, 0.0f);
        this.body = new ModelRenderer(this, 407, 3);
        this.body.addBox(-6.0f, -12.0f, -9.0f, 12, 17, 9);
        this.body.setPos(0.0f, -2.0f, 9.0f);
        this.body.mirror = true;
        this.setRotation(this.body, -0.3141593f, 0.0f, 0.0f);
        this.leftleg1 = new ModelRenderer(this, 300, 0);
        this.leftleg1.addBox(-3.0f, -4.0f, -21.0f, 6, 11, 11);
        this.leftleg1.setPos(9.0f, 2.0f, 26.0f);
        this.leftleg1.mirror = true;
        this.setRotation(this.leftleg1, -0.5759587f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 400, 75);
        this.tail1.addBox(-6.0f, -6.0f, 0.0f, 10, 12, 14);
        this.tail1.setPos(1.0f, -5.0f, 22.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, -0.1745329f, 0.0f, 0.0f);
        this.leftleg2 = new ModelRenderer(this, 300, 23);
        this.leftleg2.addBox(-3.0f, -10.0f, -5.0f, 5, 13, 7);
        this.leftleg2.setPos(9.0f, 2.0f, 26.0f);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.9773844f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 400, 39);
        this.body2.addBox(0.0f, -3.0f, -3.0f, 12, 18, 16);
        this.body2.setPos(-6.0f, -11.0f, 10.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, -0.1047198f, 0.0f, 0.0f);
        this.leftleg3 = new ModelRenderer(this, 300, 51);
        this.leftleg3.addBox(-1.0f, -19.0f, 0.0f, 4, 18, 6);
        this.leftleg3.setPos(7.0f, 21.0f, 11.0f);
        this.leftleg3.mirror = true;
        this.setRotation(this.leftleg3, -0.5235988f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 400, 103);
        this.tail2.addBox(-4.0f, -4.0f, 0.0f, 8, 10, 12);
        this.tail2.setPos(0.0f, -4.0f, 35.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, -0.1396263f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 400, 127);
        this.tail3.addBox(-3.0f, -3.0f, 0.0f, 6, 8, 12);
        this.tail3.setPos(0.0f, -3.0f, 46.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, -0.1396263f, 0.0f, 0.0f);
        this.lclaw2 = new ModelRenderer(this, 300, 76);
        this.lclaw2.addBox(-1.0f, -1.0f, -6.0f, 4, 4, 13);
        this.lclaw2.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw2.mirror = true;
        this.setRotation(this.lclaw2, 0.0f, 0.0f, 0.0f);
        this.lclaw3 = new ModelRenderer(this, 300, 95);
        this.lclaw3.addBox(2.0f, 0.0f, -6.0f, 2, 3, 10);
        this.lclaw3.setPos(7.0f, 21.0f, 11.0f);
        this.lclaw3.mirror = true;
        this.setRotation(this.lclaw3, 0.0f, -0.6632251f, 0.0f);
        this.lclaw4 = new ModelRenderer(this, 308, 123);
        this.lclaw4.addBox(0.0f, 0.0f, -10.0f, 2, 3, 4);
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
        this.neck3 = new ModelRenderer(this, 375, 23);
        this.neck3.addBox(-3.0f, -3.0f, -6.0f, 6, 6, 8);
        this.neck3.setPos(0.0f, -24.0f, -9.0f);
        this.neck3.mirror = true;
        this.setRotation(this.neck3, -0.2443461f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer(this, 130, 32);
        this.head3.addBox(-3.0f, -6.0f, -15.0f, 6, 6, 17);
        this.head3.setPos(0.0f, -26.0f, -14.0f);
        this.head3.mirror = true;
        this.setRotation(this.head3, -0.2443461f, 0.0f, 0.0f);
        this.jaw1 = new ModelRenderer(this, 143, 114);
        this.jaw1.addBox(-3.0f, 1.0f, -14.0f, 6, 3, 15);
        this.jaw1.setPos(0.0f, -26.0f, -14.0f);
        this.jaw1.mirror = true;
        this.setRotation(this.jaw1, 0.1919862f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer(this, 0, 0);
        this.tooth1.addBox(-3.0f, 0.0f, -14.0f, 1, 3, 1);
        this.tooth1.setPos(0.0f, -26.0f, -14.0f);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, -0.2443461f, 0.0f, 0.0f);
        this.tooth2 = new ModelRenderer(this, 0, 0);
        this.tooth2.addBox(-0.5f, 0.0f, -14.0f, 1, 2, 1);
        this.tooth2.setPos(0.0f, -26.0f, -14.0f);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, -0.2443461f, 0.0f, 0.0f);
        this.tooth3 = new ModelRenderer(this, 0, 0);
        this.tooth3.addBox(2.0f, 0.0f, -14.0f, 1, 3, 1);
        this.tooth3.setPos(0.0f, -26.0f, -14.0f);
        this.tooth3.mirror = true;
        this.setRotation(this.tooth3, -0.2443461f, 0.0f, 0.0f);
        this.tooth4 = new ModelRenderer(this, 0, 0);
        this.tooth4.addBox(-2.0f, 0.0f, -12.0f, 1, 3, 1);
        this.tooth4.setPos(0.0f, -26.0f, -14.0f);
        this.tooth4.mirror = true;
        this.setRotation(this.tooth4, -0.2443461f, 0.0f, 0.0f);
        this.tooth5 = new ModelRenderer(this, 0, 0);
        this.tooth5.addBox(1.0f, 0.0f, -12.0f, 1, 3, 1);
        this.tooth5.setPos(0.0f, -26.0f, -14.0f);
        this.tooth5.mirror = true;
        this.setRotation(this.tooth5, -0.2443461f, 0.0f, 0.0f);
        this.jaw5 = new ModelRenderer(this, 151, 135);
        this.jaw5.addBox(-4.0f, 1.0f, -4.0f, 8, 4, 7);
        this.jaw5.setPos(0.0f, -26.0f, -14.0f);
        this.jaw5.mirror = true;
        this.setRotation(this.jaw5, 0.1919862f, 0.0f, 0.0f);
        this.head7 = new ModelRenderer(this, 185, 34);
        this.head7.addBox(-4.0f, -7.0f, -3.0f, 8, 7, 10);
        this.head7.setPos(0.0f, -26.0f, -14.0f);
        this.head7.mirror = true;
        this.setRotation(this.head7, -0.2443461f, 0.0f, 0.0f);
        this.tooth6 = new ModelRenderer(this, 0, 0);
        this.tooth6.addBox(-3.0f, 0.0f, -10.0f, 1, 2, 1);
        this.tooth6.setPos(0.0f, -26.0f, -14.0f);
        this.tooth6.mirror = true;
        this.setRotation(this.tooth6, -0.2443461f, 0.0f, 0.0f);
        this.tooth7 = new ModelRenderer(this, 0, 0);
        this.tooth7.addBox(2.0f, 0.0f, -10.0f, 1, 2, 1);
        this.tooth7.setPos(0.0f, -26.0f, -14.0f);
        this.tooth7.mirror = true;
        this.setRotation(this.tooth7, -0.2443461f, 0.0f, 0.0f);
        this.tooth8 = new ModelRenderer(this, 0, 0);
        this.tooth8.addBox(-2.0f, 0.0f, -8.0f, 1, 2, 1);
        this.tooth8.setPos(0.0f, -26.0f, -14.0f);
        this.tooth8.mirror = true;
        this.setRotation(this.tooth8, -0.2443461f, 0.0f, 0.0f);
        this.tooth9 = new ModelRenderer(this, 0, 0);
        this.tooth9.addBox(1.0f, 0.0f, -8.0f, 1, 2, 1);
        this.tooth9.setPos(0.0f, -26.0f, -14.0f);
        this.tooth9.mirror = true;
        this.setRotation(this.tooth9, -0.2443461f, 0.0f, 0.0f);
        this.tooth10 = new ModelRenderer(this, 0, 0);
        this.tooth10.addBox(-3.0f, 0.0f, -6.0f, 1, 2, 1);
        this.tooth10.setPos(0.0f, -26.0f, -14.0f);
        this.tooth10.mirror = true;
        this.setRotation(this.tooth10, -0.2443461f, 0.0f, 0.0f);
        this.tooth11 = new ModelRenderer(this, 0, 0);
        this.tooth11.addBox(2.0f, 0.0f, -6.0f, 1, 2, 1);
        this.tooth11.setPos(0.0f, -26.0f, -14.0f);
        this.tooth11.mirror = true;
        this.setRotation(this.tooth11, -0.2443461f, 0.0f, 0.0f);
        this.tooth12 = new ModelRenderer(this, 0, 0);
        this.tooth12.addBox(-2.0f, 0.0f, -4.0f, 1, 1, 1);
        this.tooth12.setPos(0.0f, -26.0f, -14.0f);
        this.tooth12.mirror = true;
        this.setRotation(this.tooth12, -0.2443461f, 0.0f, 0.0f);
        this.tooth13 = new ModelRenderer(this, 0, 0);
        this.tooth13.addBox(1.0f, 0.0f, -4.0f, 1, 1, 1);
        this.tooth13.setPos(0.0f, -26.0f, -14.0f);
        this.tooth13.mirror = true;
        this.setRotation(this.tooth13, -0.2443461f, 0.0f, 0.0f);
        this.rightleg1 = new ModelRenderer(this, 246, 0);
        this.rightleg1.addBox(-2.0f, -4.0f, -21.0f, 6, 11, 11);
        this.rightleg1.setPos(-10.0f, 2.0f, 26.0f);
        this.rightleg1.mirror = true;
        this.setRotation(this.rightleg1, -0.5934119f, 0.0f, 0.0f);
        this.rightleg2 = new ModelRenderer(this, 250, 24);
        this.rightleg2.addBox(-1.0f, -10.0f, -5.0f, 5, 13, 7);
        this.rightleg2.setPos(-10.0f, 2.0f, 26.0f);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.9773844f, 0.0f, 0.0f);
        this.tooth14 = new ModelRenderer(this, 0, 0);
        this.tooth14.addBox(0.5f, -2.0f, -14.0f, 1, 3, 1);
        this.tooth14.setPos(0.0f, -26.0f, -14.0f);
        this.tooth14.mirror = true;
        this.setRotation(this.tooth14, 0.1919862f, 0.0f, 0.0f);
        this.tooth15 = new ModelRenderer(this, 0, 0);
        this.tooth15.addBox(-1.5f, -2.0f, -14.0f, 1, 3, 1);
        this.tooth15.setPos(0.0f, -26.0f, -14.0f);
        this.tooth15.mirror = true;
        this.setRotation(this.tooth15, 0.1919862f, 0.0f, 0.0f);
        this.tooth16 = new ModelRenderer(this, 0, 0);
        this.tooth16.addBox(2.0f, -1.0f, -12.0f, 1, 2, 1);
        this.tooth16.setPos(0.0f, -26.0f, -14.0f);
        this.tooth16.mirror = true;
        this.setRotation(this.tooth16, 0.1919862f, 0.0f, 0.0f);
        this.tooth17 = new ModelRenderer(this, 0, 0);
        this.tooth17.addBox(-3.0f, -1.0f, -12.0f, 1, 2, 1);
        this.tooth17.setPos(0.0f, -26.0f, -14.0f);
        this.tooth17.mirror = true;
        this.setRotation(this.tooth17, 0.1919862f, 0.0f, 0.0f);
        this.tooth18 = new ModelRenderer(this, 0, 0);
        this.tooth18.addBox(1.0f, -1.0f, -10.0f, 1, 2, 1);
        this.tooth18.setPos(0.0f, -26.0f, -14.0f);
        this.tooth18.mirror = true;
        this.setRotation(this.tooth18, 0.1919862f, 0.0f, 0.0f);
        this.tooth19 = new ModelRenderer(this, 0, 0);
        this.tooth19.addBox(-2.0f, -1.0f, -10.0f, 1, 2, 1);
        this.tooth19.setPos(0.0f, -26.0f, -14.0f);
        this.tooth19.mirror = true;
        this.setRotation(this.tooth19, 0.1919862f, 0.0f, 0.0f);
        this.tooth20 = new ModelRenderer(this, 0, 0);
        this.tooth20.addBox(-3.0f, -1.0f, -8.0f, 1, 2, 1);
        this.tooth20.setPos(0.0f, -26.0f, -14.0f);
        this.tooth20.mirror = true;
        this.setRotation(this.tooth20, 0.1919862f, 0.0f, 0.0f);
        this.tooth21 = new ModelRenderer(this, 0, 0);
        this.tooth21.addBox(2.0f, -1.0f, -8.0f, 1, 2, 1);
        this.tooth21.setPos(0.0f, -26.0f, -14.0f);
        this.tooth21.mirror = true;
        this.setRotation(this.tooth21, 0.1919862f, 0.0f, 0.0f);
        this.tooth22 = new ModelRenderer(this, 0, 0);
        this.tooth22.addBox(1.0f, 0.0f, -6.0f, 1, 1, 1);
        this.tooth22.setPos(0.0f, -26.0f, -14.0f);
        this.tooth22.mirror = true;
        this.setRotation(this.tooth22, 0.1919862f, 0.0f, 0.0f);
        this.tooth23 = new ModelRenderer(this, 0, 0);
        this.tooth23.addBox(-2.0f, 0.0f, -6.0f, 1, 1, 1);
        this.tooth23.setPos(0.0f, -26.0f, -14.0f);
        this.tooth23.mirror = true;
        this.setRotation(this.tooth23, 0.1919862f, 0.0f, 0.0f);
        this.rightleg3 = new ModelRenderer(this, 250, 47);
        this.rightleg3.addBox(-2.0f, -19.0f, 0.0f, 4, 18, 6);
        this.rightleg3.setPos(-8.0f, 21.0f, 11.0f);
        this.rightleg3.mirror = true;
        this.setRotation(this.rightleg3, -0.5235988f, 0.0f, 0.0f);
        this.rclaw2 = new ModelRenderer(this, 250, 76);
        this.rclaw2.addBox(-2.0f, -1.0f, -6.0f, 4, 4, 13);
        this.rclaw2.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw2.mirror = true;
        this.setRotation(this.rclaw2, 0.0f, 0.0f, 0.0f);
        this.rclaw4 = new ModelRenderer(this, 247, 123);
        this.rclaw4.addBox(-1.0f, 0.0f, -10.0f, 2, 3, 4);
        this.rclaw4.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw4.mirror = true;
        this.setRotation(this.rclaw4, 0.0f, 0.0f, 0.0f);
        this.rclaw1 = new ModelRenderer(this, 250, 111);
        this.rclaw1.addBox(2.0f, 0.0f, -3.0f, 2, 3, 6);
        this.rclaw1.setPos(-8.0f, 21.0f, 11.0f);
        this.rclaw1.mirror = true;
        this.setRotation(this.rclaw1, 0.0f, -0.6632251f, 0.0f);
        this.rclaw5 = new ModelRenderer(this, 261, 123);
        this.rclaw5.addBox(2.5f, 1.0f, -5.0f, 1, 2, 2);
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
        this.neck1 = new ModelRenderer(this, 45, 0);
        this.neck1.addBox(-5.0f, -6.0f, -14.0f, 10, 12, 15);
        this.neck1.setPos(0.0f, -9.0f, 5.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, -0.837758f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 48, 29);
        this.neck2.addBox(-4.5f, -4.0f, -10.0f, 9, 9, 10);
        this.neck2.setPos(0.0f, -19.0f, -2.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, -0.7853982f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 400, 150);
        this.tail4.addBox(-2.0f, -3.0f, 0.0f, 4, 6, 16);
        this.tail4.setPos(0.0f, -1.0f, 56.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, -0.1396263f, 0.0f, 0.0f);
        this.Spike1 = new ModelRenderer(this, 0, 100);
        this.Spike1.addBox(-2.0f, -16.0f, -1.0f, 4, 16, 18);
        this.Spike1.setPos(0.0f, -4.0f, 7.0f);
        this.Spike1.mirror = true;
        this.setRotation(this.Spike1, 0.5061455f, 0.0f, 0.0f);
        this.Spike2 = new ModelRenderer(this, 0, 72);
        this.Spike2.addBox(-1.5f, -12.0f, 0.0f, 3, 12, 10);
        this.Spike2.setPos(0.0f, 0.0f, 29.0f);
        this.Spike2.mirror = true;
        this.setRotation(this.Spike2, 0.4886922f, 0.0f, 0.0f);
        this.Spike3 = new ModelRenderer(this, 0, 44);
        this.Spike3.addBox(-1.0f, -7.0f, 0.0f, 2, 8, 7);
        this.Spike3.setPos(0.0f, -2.0f, 41.0f);
        this.Spike3.mirror = true;
        this.setRotation(this.Spike3, 0.5934119f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Nastysaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Nastysaurus e = (Nastysaurus)entity;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float pscale = 2.0f;
        float tailspeed = 0.0f;
        float tailamp = 0.0f;
        float clawZ = 15.0f;
        float clawY = 21.0f;
        float clawZamp = 5.0f * pscale;
        float clawYamp = 2.0f * pscale;
        float pi4 = 0.7853982f;
        r = e.getRenderInfo();
        f3 %= 360.0f;
        f3 *= 0.35f;
        this.neck2.yRot = (float)Math.toRadians(f3) * 0.5f;
        this.neck3.yRot = this.head7.yRot = (this.head3.yRot = (float)Math.toRadians(f3));
        this.jaw1.yRot = this.jaw5.yRot = this.head3.yRot;
        this.tooth4.yRot = this.tooth5.yRot = this.head3.yRot;
        this.tooth3.yRot = this.tooth5.yRot;
        this.tooth2.yRot = this.tooth5.yRot;
        this.tooth1.yRot = this.tooth5.yRot;
        this.tooth9.yRot = this.tooth10.yRot = this.head3.yRot;
        this.tooth8.yRot = this.tooth10.yRot;
        this.tooth7.yRot = this.tooth10.yRot;
        this.tooth6.yRot = this.tooth10.yRot;
        this.tooth14.yRot = this.tooth15.yRot = this.head3.yRot;
        this.tooth13.yRot = this.tooth15.yRot;
        this.tooth12.yRot = this.tooth15.yRot;
        this.tooth11.yRot = this.tooth15.yRot;
        this.tooth19.yRot = this.tooth20.yRot = this.head3.yRot;
        this.tooth18.yRot = this.tooth20.yRot;
        this.tooth17.yRot = this.tooth20.yRot;
        this.tooth16.yRot = this.tooth20.yRot;
        this.tooth22.yRot = this.tooth23.yRot = this.head3.yRot;
        this.tooth21.yRot = this.tooth23.yRot;
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
        this.jaw1.xRot = this.jaw5.xRot = newangle;
        this.tooth14.xRot = this.tooth15.xRot = newangle;
        this.tooth19.xRot = this.tooth20.xRot = newangle;
        this.tooth18.xRot = this.tooth20.xRot;
        this.tooth17.xRot = this.tooth20.xRot;
        this.tooth16.xRot = this.tooth20.xRot;
        this.tooth22.xRot = this.tooth23.xRot = newangle;
        this.tooth21.xRot = this.tooth23.xRot;
        float t1 = 0.0f;
        float t2 = 0.0f;
        if ((double)f1 > 0.001) {
            newangle = MathHelper.cos((float)(f2 * this.wingspeed / pscale));
            t1 = MathHelper.sin((float)(f2 * this.wingspeed / pscale));
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
        this.leftleg3.z = this.lclaw1.z;
        this.leftleg3.y = this.lclaw1.y;
        this.leftleg3.xRot = -0.523f + newangle * 3.1415927f * 0.15f * f1;
        this.leftleg1.xRot = -0.576f + newangle * 3.1415927f * 0.06f * f1;
        this.leftleg2.xRot = 0.977f + newangle * 3.1415927f * 0.06f * f1;
        this.leftleg1.y = this.leftleg2.y = this.leftleg3.y - (float)Math.cos(this.leftleg3.xRot) * 17.0f;
        this.leftleg1.z = this.leftleg2.z = this.leftleg3.z - (float)Math.sin(this.leftleg3.xRot) * 17.0f;
        t1 = 0.0f;
        t2 = 0.0f;
        if ((double)f1 > 0.001) {
            newangle = MathHelper.cos((float)(f2 * this.wingspeed / pscale + pi4 * 4.0f));
            t1 = MathHelper.sin((float)(f2 * this.wingspeed / pscale + pi4 * 4.0f));
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
        this.rightleg3.z = this.rclaw1.z;
        this.rightleg3.y = this.rclaw1.y;
        this.rightleg3.xRot = -0.523f + newangle * 3.1415927f * 0.15f * f1;
        this.rightleg1.xRot = -0.576f + newangle * 3.1415927f * 0.06f * f1;
        this.rightleg2.xRot = 0.977f + newangle * 3.1415927f * 0.06f * f1;
        this.rightleg1.y = this.rightleg2.y = this.rightleg3.y - (float)Math.cos(this.rightleg3.xRot) * 17.0f;
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
        if (e.getAttacking() != 0) {
            tailspeed = 0.76f;
            tailamp = 0.25f;
        } else {
            tailspeed = 0.26f;
            tailamp = 0.08f;
        }
        this.tail3.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp / 2.0f;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 11.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 11.0f;
        this.tail4.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * tailamp;
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
        this.neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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
        this.rclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

