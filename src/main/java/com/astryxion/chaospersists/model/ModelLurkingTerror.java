/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.LurkingTerror
 *  com.astryxion.chaospersists.ModelLurkingTerror
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

import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelLurkingTerror extends EntityModel<LurkingTerror> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg1part2;
    ModelRenderer leg1part3;
    ModelRenderer leg2;
    ModelRenderer leg2part2;
    ModelRenderer leg2part3;
    ModelRenderer leg3;
    ModelRenderer leg3part2;
    ModelRenderer leg3part3;
    ModelRenderer leg4;
    ModelRenderer leg4part2;
    ModelRenderer leg4part3;
    ModelRenderer leg5;
    ModelRenderer leg5part2;
    ModelRenderer leg6;
    ModelRenderer leg6part2;
    ModelRenderer thorax;
    ModelRenderer abdomen;
    ModelRenderer head;
    ModelRenderer jaw1;
    ModelRenderer jaw1part2;
    ModelRenderer jaw1tooth1;
    ModelRenderer jaw1tooth2;
    ModelRenderer jaw1tooth3;
    ModelRenderer jaw1tooth4;
    ModelRenderer jaw1tooth5;
    ModelRenderer jaw1tooth6;
    ModelRenderer jaw2;
    ModelRenderer jaw2part2;
    ModelRenderer jaw2tooth1;
    ModelRenderer jaw2tooth2;
    ModelRenderer jaw2tooth3;
    ModelRenderer jaw2tooth4;
    ModelRenderer jaw2tooth5;
    ModelRenderer jaw2tooth6;
    ModelRenderer jaw3;
    ModelRenderer jaw3part2;
    ModelRenderer jaw3tooth1;
    ModelRenderer jaw3tooth2;
    ModelRenderer jaw3tooth3;
    ModelRenderer jaw3tooth4;
    ModelRenderer jaw3tooth5;
    ModelRenderer jaw3tooth6;
    ModelRenderer jaw4;
    ModelRenderer jaw4part2;
    ModelRenderer jaw4tooth1;
    ModelRenderer jaw4tooth2;
    ModelRenderer jaw4tooth3;
    ModelRenderer jaw4tooth4;
    ModelRenderer jaw4tooth5;
    ModelRenderer jaw4tooth6;
    ModelRenderer tonguepart1;
    ModelRenderer tonguepart2;
    ModelRenderer tonguepart3;
    ModelRenderer wing_1;
    ModelRenderer wing_2;
    ModelRenderer wing_3;
    ModelRenderer wing_4;

    public ModelLurkingTerror() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 256;
        // textureHeight = 64;
        this.body = new ModelRenderer(this, 39, 27);
        this.body.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 12);
        this.body.setPos(0.0f, 10.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer(this, 18, 0);
        this.leg1.addBox(-15.0f, -1.5f, -1.5f, 16, 3, 3);
        this.leg1.setPos(-4.0f, 10.0f, -1.0f);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, -0.5759587f, -0.1919862f);
        this.leg1part2 = new ModelRenderer(this, 58, 0);
        this.leg1part2.addBox(-15.0f, -1.5f, -1.5f, 3, 8, 3);
        this.leg1part2.setPos(-4.0f, 10.0f, -1.0f);
        this.leg1part2.mirror = true;
        this.setRotation(this.leg1part2, 0.0f, -0.5759587f, -0.1919862f);
        this.leg1part3 = new ModelRenderer(this, 0, 0);
        this.leg1part3.addBox(-15.0f, -1.0f, -1.0f, 2, 8, 2);
        this.leg1part3.setPos(-4.0f, 10.0f, -1.0f);
        this.leg1part3.mirror = true;
        this.setRotation(this.leg1part3, 0.0f, -0.5759587f, -0.6753082f);
        this.leg2 = new ModelRenderer(this, 18, 0);
        this.leg2.addBox(-1.0f, -1.5f, -1.5f, 16, 3, 3);
        this.leg2.setPos(4.0f, 10.0f, -1.0f);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.5759587f, 0.1919862f);
        this.leg2part2 = new ModelRenderer(this, 58, 0);
        this.leg2part2.addBox(12.0f, -1.5f, -1.5f, 3, 8, 3);
        this.leg2part2.setPos(4.0f, 10.0f, -1.0f);
        this.leg2part2.mirror = true;
        this.setRotation(this.leg2part2, 0.0f, 0.5759587f, 0.1919862f);
        this.leg2part3 = new ModelRenderer(this, 0, 0);
        this.leg2part3.addBox(13.0f, -1.0f, -1.0f, 2, 8, 2);
        this.leg2part3.setPos(4.0f, 10.0f, -1.0f);
        this.leg2part3.mirror = true;
        this.setRotation(this.leg2part3, 0.0f, 0.5759587f, 0.6753028f);
        this.leg3 = new ModelRenderer(this, 18, 0);
        this.leg3.addBox(-15.0f, -1.5f, -1.5f, 16, 3, 3);
        this.leg3.setPos(-4.0f, 10.0f, 1.0f);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.2792527f, -0.1919862f);
        this.leg3part2 = new ModelRenderer(this, 58, 0);
        this.leg3part2.addBox(-15.0f, -1.5f, -1.5f, 3, 8, 3);
        this.leg3part2.setPos(-4.0f, 10.0f, 1.0f);
        this.leg3part2.mirror = true;
        this.setRotation(this.leg3part2, 0.0f, 0.2792527f, -0.1919862f);
        this.leg3part3 = new ModelRenderer(this, 0, 0);
        this.leg3part3.addBox(-15.0f, -1.0f, -1.0f, 2, 8, 2);
        this.leg3part3.setPos(-4.0f, 10.0f, 1.0f);
        this.leg3part3.mirror = true;
        this.setRotation(this.leg3part3, 0.0f, 0.2792527f, -0.6753028f);
        this.leg4 = new ModelRenderer(this, 18, 0);
        this.leg4.addBox(-1.0f, -1.5f, -1.5f, 16, 3, 3);
        this.leg4.setPos(4.0f, 10.0f, 1.0f);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, -0.2792527f, 0.1919862f);
        this.leg4part2 = new ModelRenderer(this, 58, 0);
        this.leg4part2.addBox(12.0f, -1.5f, -1.5f, 3, 8, 3);
        this.leg4part2.setPos(4.0f, 10.0f, 1.0f);
        this.leg4part2.mirror = true;
        this.setRotation(this.leg4part2, 0.0f, -0.2792527f, 0.1919862f);
        this.leg4part3 = new ModelRenderer(this, 0, 0);
        this.leg4part3.addBox(13.0f, -1.0f, -1.0f, 2, 8, 2);
        this.leg4part3.setPos(4.0f, 10.0f, 1.0f);
        this.leg4part3.mirror = true;
        this.setRotation(this.leg4part3, 0.0f, -0.2792527f, 0.6753028f);
        this.leg5 = new ModelRenderer(this, 119, 0);
        this.leg5.addBox(-4.0f, -1.5f, -1.5f, 25, 3, 3);
        this.leg5.setPos(4.0f, 10.0f, 4.0f);
        this.leg5.mirror = true;
        this.setRotation(this.leg5, 0.0f, -1.134359f, 0.3407057f);
        this.leg5part2 = new ModelRenderer(this, 18, 9);
        this.leg5part2.addBox(18.0f, -1.5f, -1.5f, 3, 10, 3);
        this.leg5part2.setPos(4.0f, 10.0f, 4.0f);
        this.leg5part2.mirror = true;
        this.setRotation(this.leg5part2, 0.0f, -1.134359f, 0.3407057f);
        this.leg6 = new ModelRenderer(this, 119, 0);
        this.leg6.addBox(-21.0f, -1.5f, -1.5f, 25, 3, 3);
        this.leg6.setPos(-4.0f, 10.0f, 4.0f);
        this.leg6.mirror = true;
        this.setRotation(this.leg6, 0.0f, 1.134359f, -0.3407057f);
        this.leg6part2 = new ModelRenderer(this, 18, 9);
        this.leg6part2.addBox(-21.0f, -1.5f, -1.5f, 3, 10, 3);
        this.leg6part2.setPos(-4.0f, 10.0f, 4.0f);
        this.leg6part2.mirror = true;
        this.setRotation(this.leg6part2, 0.0f, 1.134359f, -0.3407057f);
        this.thorax = new ModelRenderer(this, 0, 42);
        this.thorax.addBox(-2.0f, -2.0f, -6.0f, 4, 4, 18);
        this.thorax.setPos(0.0f, 10.0f, 9.0f);
        this.thorax.mirror = true;
        this.setRotation(this.thorax, -0.2602503f, 0.0f, 0.0f);
        this.abdomen = new ModelRenderer(this, 118, 18);
        this.abdomen.addBox(-3.0f, -3.0f, 0.0f, 6, 6, 16);
        this.abdomen.setPos(0.0f, 13.0f, 20.0f);
        this.abdomen.mirror = true;
        this.setRotation(this.abdomen, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 27, 48);
        this.head.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 5);
        this.head.setPos(0.0f, 10.0f, -8.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.jaw1 = new ModelRenderer(this, 96, 31);
        this.jaw1.addBox(-1.0f, -1.0f, -13.0f, 1, 2, 14);
        this.jaw1.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1.mirror = true;
        this.setRotation(this.jaw1, 0.0f, 0.4089647f, 0.0f);
        this.jaw1part2 = new ModelRenderer(this, 39, 17);
        this.jaw1part2.addBox(-1.1f, -2.0f, -5.0f, 1, 4, 5);
        this.jaw1part2.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1part2.mirror = true;
        this.setRotation(this.jaw1part2, 0.0f, 0.4089647f, 0.0f);
        this.jaw1tooth1 = new ModelRenderer(this, 39, 27);
        this.jaw1tooth1.addBox(0.0f, -0.5f, -13.0f, 1, 1, 1);
        this.jaw1tooth1.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1tooth1.mirror = true;
        this.setRotation(this.jaw1tooth1, 0.0f, 0.4089647f, 0.0f);
        this.jaw1tooth2 = new ModelRenderer(this, 39, 27);
        this.jaw1tooth2.addBox(0.0f, -0.5f, -11.0f, 1, 1, 1);
        this.jaw1tooth2.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1tooth2.mirror = true;
        this.setRotation(this.jaw1tooth2, 0.0f, 0.4089647f, 0.0f);
        this.jaw1tooth3 = new ModelRenderer(this, 39, 27);
        this.jaw1tooth3.addBox(0.0f, -0.5f, -9.0f, 1, 1, 1);
        this.jaw1tooth3.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1tooth3.mirror = true;
        this.setRotation(this.jaw1tooth3, 0.0f, 0.4089647f, 0.0f);
        this.jaw1tooth4 = new ModelRenderer(this, 39, 27);
        this.jaw1tooth4.addBox(0.0f, -0.5f, -7.0f, 1, 1, 1);
        this.jaw1tooth4.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1tooth4.mirror = true;
        this.setRotation(this.jaw1tooth4, 0.0f, 0.4089647f, 0.0f);
        this.jaw1tooth5 = new ModelRenderer(this, 39, 27);
        this.jaw1tooth5.addBox(0.0f, -1.5f, -4.5f, 1, 1, 1);
        this.jaw1tooth5.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1tooth5.mirror = true;
        this.setRotation(this.jaw1tooth5, 0.0f, 0.4089647f, 0.0f);
        this.jaw1tooth6 = new ModelRenderer(this, 39, 27);
        this.jaw1tooth6.addBox(0.0f, 0.5f, -4.5f, 1, 1, 1);
        this.jaw1tooth6.setPos(-2.0f, 10.0f, -8.0f);
        this.jaw1tooth6.mirror = true;
        this.setRotation(this.jaw1tooth6, 0.0f, 0.4089647f, 0.0f);
        this.jaw2 = new ModelRenderer(this, 96, 48);
        this.jaw2.addBox(0.0f, -1.0f, -13.0f, 1, 2, 14);
        this.jaw2.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2.mirror = true;
        this.setRotation(this.jaw2, 0.0f, -0.4089656f, 0.0f);
        this.jaw2part2 = new ModelRenderer(this, 39, 7);
        this.jaw2part2.addBox(0.1f, -2.0f, -5.0f, 1, 4, 5);
        this.jaw2part2.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2part2.mirror = true;
        this.setRotation(this.jaw2part2, 0.0f, -0.4089656f, 0.0f);
        this.jaw2tooth1 = new ModelRenderer(this, 96, 48);
        this.jaw2tooth1.addBox(-1.0f, -0.5f, -13.0f, 1, 1, 1);
        this.jaw2tooth1.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2tooth1.mirror = true;
        this.setRotation(this.jaw2tooth1, 0.0f, -0.4089656f, 0.0f);
        this.jaw2tooth2 = new ModelRenderer(this, 96, 48);
        this.jaw2tooth2.addBox(-1.0f, -0.5f, -11.0f, 1, 1, 1);
        this.jaw2tooth2.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2tooth2.mirror = true;
        this.setRotation(this.jaw2tooth2, 0.0f, -0.4089656f, 0.0f);
        this.jaw2tooth3 = new ModelRenderer(this, 96, 48);
        this.jaw2tooth3.addBox(-1.0f, -0.5f, -9.0f, 1, 1, 1);
        this.jaw2tooth3.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2tooth3.mirror = true;
        this.setRotation(this.jaw2tooth3, 0.0f, -0.4089656f, 0.0f);
        this.jaw2tooth4 = new ModelRenderer(this, 96, 48);
        this.jaw2tooth4.addBox(-1.0f, -0.5f, -7.0f, 1, 1, 1);
        this.jaw2tooth4.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2tooth4.mirror = true;
        this.setRotation(this.jaw2tooth4, 0.0f, -0.4089656f, 0.0f);
        this.jaw2tooth5 = new ModelRenderer(this, 96, 48);
        this.jaw2tooth5.addBox(-1.0f, -1.5f, -4.5f, 1, 1, 1);
        this.jaw2tooth5.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2tooth5.mirror = true;
        this.setRotation(this.jaw2tooth5, 0.0f, -0.4089656f, 0.0f);
        this.jaw2tooth6 = new ModelRenderer(this, 96, 48);
        this.jaw2tooth6.addBox(-1.0f, 0.5f, -4.5f, 1, 1, 1);
        this.jaw2tooth6.setPos(2.0f, 10.0f, -8.0f);
        this.jaw2tooth6.mirror = true;
        this.setRotation(this.jaw2tooth6, 0.0f, -0.4089656f, 0.0f);
        this.jaw3 = new ModelRenderer(this, 95, 16);
        this.jaw3.addBox(-1.0f, -1.0f, -13.0f, 2, 1, 14);
        this.jaw3.setPos(0.0f, 8.0f, -8.0f);
        this.jaw3.mirror = true;
        this.setRotation(this.jaw3, -0.4089647f, 0.0f, 0.0f);
        this.jaw3part2 = new ModelRenderer(this, 0, 27);
        this.jaw3part2.addBox(-2.0f, -1.0f, -5.0f, 4, 1, 5);
        this.jaw3part2.setPos(0.0f, 7.9f, -8.0f);
        this.jaw3part2.mirror = true;
        this.setRotation(this.jaw3part2, -0.4089647f, 0.0f, 0.0f);
        this.jaw3tooth1 = new ModelRenderer(this, 95, 16);
        this.jaw3tooth1.addBox(-0.5f, 0.0f, -13.0f, 1, 1, 1);
        this.jaw3tooth1.setPos(0.0f, 8.0f, -8.0f);
        this.jaw3tooth1.mirror = true;
        this.setRotation(this.jaw3tooth1, -0.4089647f, 0.0f, 0.0f);
        this.jaw3tooth2 = new ModelRenderer(this, 95, 16);
        this.jaw3tooth2.addBox(-0.5f, 0.0f, -11.0f, 1, 1, 1);
        this.jaw3tooth2.setPos(0.0f, 8.0f, -8.0f);
        this.jaw3tooth2.mirror = true;
        this.setRotation(this.jaw3tooth2, -0.4089647f, 0.0f, 0.0f);
        this.jaw3tooth3 = new ModelRenderer(this, 95, 16);
        this.jaw3tooth3.addBox(-0.5f, 0.0f, -9.0f, 1, 1, 1);
        this.jaw3tooth3.setPos(0.0f, 8.0f, -8.0f);
        this.jaw3tooth3.mirror = true;
        this.setRotation(this.jaw3tooth3, -0.4089647f, 0.0f, 0.0f);
        this.jaw3tooth4 = new ModelRenderer(this, 95, 16);
        this.jaw3tooth4.addBox(-0.5f, 0.0f, -7.0f, 1, 1, 1);
        this.jaw3tooth4.setPos(0.0f, 8.0f, -8.0f);
        this.jaw3tooth4.mirror = true;
        this.setRotation(this.jaw3tooth4, -0.4089647f, 0.0f, 0.0f);
        this.jaw3tooth5 = new ModelRenderer(this, 95, 16);
        this.jaw3tooth5.addBox(-1.5f, 0.0f, -4.5f, 1, 1, 1);
        this.jaw3tooth5.setPos(0.0f, 8.0f, -8.0f);
        this.jaw3tooth5.mirror = true;
        this.setRotation(this.jaw3tooth5, -0.4089647f, 0.0f, 0.0f);
        this.jaw3tooth6 = new ModelRenderer(this, 95, 16);
        this.jaw3tooth6.addBox(0.5f, 0.0f, -4.5f, 1, 1, 1);
        this.jaw3tooth6.setPos(0.0f, 8.0f, -8.0f);
        this.jaw3tooth6.mirror = true;
        this.setRotation(this.jaw3tooth6, -0.4089647f, 0.0f, 0.0f);
        this.jaw4 = new ModelRenderer(this, 95, 0);
        this.jaw4.addBox(-1.0f, 0.0f, -13.0f, 2, 1, 14);
        this.jaw4.setPos(0.0f, 12.0f, -8.0f);
        this.jaw4.mirror = true;
        this.setRotation(this.jaw4, 0.4089656f, 0.0f, 0.0f);
        this.jaw4part2 = new ModelRenderer(this, 0, 20);
        this.jaw4part2.addBox(-2.0f, 0.0f, -5.0f, 4, 1, 5);
        this.jaw4part2.setPos(0.0f, 12.1f, -8.0f);
        this.jaw4part2.mirror = true;
        this.setRotation(this.jaw4part2, 0.4089656f, 0.0f, 0.0f);
        this.jaw4tooth1 = new ModelRenderer(this, 95, 0);
        this.jaw4tooth1.addBox(-0.5f, -1.0f, -13.0f, 1, 1, 1);
        this.jaw4tooth1.setPos(0.0f, 12.0f, -8.0f);
        this.jaw4tooth1.mirror = true;
        this.setRotation(this.jaw4tooth1, 0.4089656f, 0.0f, 0.0f);
        this.jaw4tooth2 = new ModelRenderer(this, 95, 0);
        this.jaw4tooth2.addBox(-0.5f, -1.0f, -11.0f, 1, 1, 1);
        this.jaw4tooth2.setPos(0.0f, 12.0f, -8.0f);
        this.jaw4tooth2.mirror = true;
        this.setRotation(this.jaw4tooth2, 0.4089656f, 0.0f, 0.0f);
        this.jaw4tooth3 = new ModelRenderer(this, 95, 0);
        this.jaw4tooth3.addBox(-0.5f, -1.0f, -9.0f, 1, 1, 1);
        this.jaw4tooth3.setPos(0.0f, 12.0f, -8.0f);
        this.jaw4tooth3.mirror = true;
        this.setRotation(this.jaw4tooth3, 0.4089656f, 0.0f, 0.0f);
        this.jaw4tooth4 = new ModelRenderer(this, 95, 0);
        this.jaw4tooth4.addBox(-0.5f, -1.0f, -7.0f, 1, 1, 1);
        this.jaw4tooth4.setPos(0.0f, 12.0f, -8.0f);
        this.jaw4tooth4.mirror = true;
        this.setRotation(this.jaw4tooth4, 0.4089656f, 0.0f, 0.0f);
        this.jaw4tooth5 = new ModelRenderer(this, 95, 0);
        this.jaw4tooth5.addBox(-1.5f, -1.0f, -4.5f, 1, 1, 1);
        this.jaw4tooth5.setPos(0.0f, 12.0f, -8.0f);
        this.jaw4tooth5.mirror = true;
        this.setRotation(this.jaw4tooth5, 0.4089656f, 0.0f, 0.0f);
        this.jaw4tooth6 = new ModelRenderer(this, 95, 0);
        this.jaw4tooth6.addBox(0.5f, -1.0f, -4.5f, 1, 1, 1);
        this.jaw4tooth6.setPos(0.0f, 12.0f, -8.0f);
        this.jaw4tooth6.mirror = true;
        this.setRotation(this.jaw4tooth6, 0.4089656f, 0.0f, 0.0f);
        this.tonguepart1 = new ModelRenderer(this, 24, 34);
        this.tonguepart1.addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5);
        this.tonguepart1.setPos(1.6f, 9.3f, -15.0f);
        this.tonguepart1.mirror = true;
        this.setRotation(this.tonguepart1, 1.041001f, 1.264073f, -1.07818f);
        this.tonguepart2 = new ModelRenderer(this, 0, 46);
        this.tonguepart2.addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5);
        this.tonguepart2.setPos(0.0f, 10.0f, -11.0f);
        this.tonguepart2.mirror = true;
        this.setRotation(this.tonguepart2, -0.1858931f, -0.2230717f, 0.669215f);
        this.tonguepart3 = new ModelRenderer(this, 24, 27);
        this.tonguepart3.addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5);
        this.tonguepart3.setPos(0.2f, 11.3f, -19.0f);
        this.tonguepart3.mirror = true;
        this.setRotation(this.tonguepart3, -0.2602503f, 0.3717861f, -1.07818f);
        this.wing_1 = new ModelRenderer(this, 108, 42);
        this.wing_1.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 22);
        this.wing_1.setPos(-2.0f, 6.0f, -5.0f);
        this.wing_1.mirror = true;
        this.setRotation(this.wing_1, 0.5948578f, -0.9294653f, 0.0f);
        this.wing_2 = new ModelRenderer(this, 141, 42);
        this.wing_2.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 22);
        this.wing_2.setPos(2.0f, 6.0f, -5.0f);
        this.wing_2.mirror = true;
        this.setRotation(this.wing_2, 0.5948606f, 0.9294576f, 0.0f);
        this.wing_3 = new ModelRenderer(this, 64, 27);
        this.wing_3.addBox(-2.0f, 0.0f, 0.0f, 4, 0, 18);
        this.wing_3.setPos(-2.0f, 6.0f, -1.0f);
        this.wing_3.mirror = true;
        this.setRotation(this.wing_3, 0.3346075f, -0.4089647f, 0.0f);
        this.wing_4 = new ModelRenderer(this, 153, 17);
        this.wing_4.addBox(-2.0f, 0.0f, 0.0f, 4, 0, 18);
        this.wing_4.setPos(2.0f, 6.0f, -1.0f);
        this.wing_4.mirror = true;
        this.setRotation(this.wing_4, 0.3346075f, 0.4089656f, 0.0f);
    }
    @Override
    public void setupAnim(LurkingTerror entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        LurkingTerror e = (LurkingTerror)entity;
        float newangle = 0.0f;
        float legspeed = 0.7f;
        float mouthspeed = 0.9f;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        r = e.getRenderInfo();
        newangle = f2 * legspeed * this.wingspeed % 6.2831855f;
        newangle = Math.abs(newangle);
        if (newangle < r.rf1) {
            r.ri1 = 0;
            if (e.level.random.nextInt(3) == 1) {
                r.ri1 |= 1;
            }
            if (e.level.random.nextInt(3) == 1) {
                r.ri1 |= 2;
            }
            if (e.level.random.nextInt(4) == 1) {
                r.ri1 |= 4;
            }
            if (e.level.random.nextInt(4) == 1) {
                r.ri1 |= 8;
            }
            if (e.level.random.nextInt(6) == 1) {
                r.ri1 |= 16;
            }
            if (e.level.random.nextInt(6) == 1) {
                r.ri1 |= 32;
            }
        }
        r.rf1 = newangle;
        newangle = f2 * mouthspeed * this.wingspeed % 6.2831855f;
        if ((newangle = Math.abs(newangle)) < r.rf2) {
            r.ri2 = 0;
            if (e.level.random.nextInt(20) == 1) {
                r.ri2 |= 1;
            }
            if (e.getAttacking() != 0) {
                r.ri2 = 1;
            }
        }
        r.rf2 = newangle;
        newangle = 0.0f;
        if ((r.ri1 & 1) != 0) {
            newangle = MathHelper.sin((float)(f2 * legspeed * this.wingspeed)) * 3.1415927f * 0.25f;
        }
        this.leg2.zRot = this.leg2part2.zRot = 0.191f + newangle;
        this.leg2part3.zRot = 0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 2) != 0) {
            newangle = MathHelper.sin((float)(f2 * legspeed * this.wingspeed)) * 3.1415927f * 0.25f;
        }
        this.leg1.zRot = this.leg1part2.zRot = -0.191f + newangle;
        this.leg1part3.zRot = -0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 4) != 0) {
            newangle = MathHelper.sin((float)(f2 * legspeed * this.wingspeed)) * 3.1415927f * 0.15f;
        }
        this.leg4.zRot = this.leg4part2.zRot = 0.191f + newangle;
        this.leg4part3.zRot = 0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 8) != 0) {
            newangle = MathHelper.sin((float)(f2 * legspeed * this.wingspeed)) * 3.1415927f * 0.15f;
        }
        this.leg3.zRot = this.leg3part2.zRot = -0.191f + newangle;
        this.leg3part3.zRot = -0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 16) != 0) {
            newangle = MathHelper.sin((float)(f2 * legspeed * this.wingspeed)) * 3.1415927f * 0.1f;
        }
        this.leg6.zRot = this.leg6part2.zRot = -0.34f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 32) != 0) {
            newangle = MathHelper.sin((float)(f2 * legspeed * this.wingspeed)) * 3.1415927f * 0.1f;
        }
        this.leg5.zRot = this.leg5part2.zRot = 0.34f + newangle;
        newangle = 0.0f;
        if ((r.ri2 & 1) != 0) {
            newangle = MathHelper.sin((float)(f2 * mouthspeed * this.wingspeed)) * 3.1415927f * 0.35f;
            newangle = Math.abs(newangle);
        }
        this.jaw1.yRot = newangle;
        this.jaw1part2.yRot = newangle;
        this.jaw1tooth3.yRot = this.jaw1tooth5.yRot = newangle;
        this.jaw1tooth1.yRot = this.jaw1tooth5.yRot;
        this.jaw1tooth4.yRot = this.jaw1tooth6.yRot = newangle;
        this.jaw1tooth2.yRot = this.jaw1tooth6.yRot;
        this.jaw2.yRot = - newangle;
        this.jaw2part2.yRot = - newangle;
        this.jaw2tooth3.yRot = this.jaw2tooth5.yRot = - newangle;
        this.jaw2tooth1.yRot = this.jaw2tooth5.yRot;
        this.jaw2tooth4.yRot = this.jaw2tooth6.yRot = - newangle;
        this.jaw2tooth2.yRot = this.jaw2tooth6.yRot;
        this.jaw3.xRot = - newangle;
        this.jaw3part2.xRot = - newangle;
        this.jaw3tooth3.xRot = this.jaw3tooth5.xRot = - newangle;
        this.jaw3tooth1.xRot = this.jaw3tooth5.xRot;
        this.jaw3tooth4.xRot = this.jaw3tooth6.xRot = - newangle;
        this.jaw3tooth2.xRot = this.jaw3tooth6.xRot;
        this.jaw4.xRot = newangle;
        this.jaw4part2.xRot = newangle;
        this.jaw4tooth3.xRot = this.jaw4tooth5.xRot = newangle;
        this.jaw4tooth1.xRot = this.jaw4tooth5.xRot;
        this.jaw4tooth4.xRot = this.jaw4tooth6.xRot = newangle;
        this.jaw4tooth2.xRot = this.jaw4tooth6.xRot;
        this.tonguepart3.xRot = 0.0f;
        this.tonguepart2.xRot = 0.0f;
        this.tonguepart1.xRot = 0.0f;
        this.tonguepart3.yRot = 0.0f;
        this.tonguepart2.yRot = 0.0f;
        this.tonguepart1.yRot = 0.0f;
        this.tonguepart3.zRot = 0.0f;
        this.tonguepart2.zRot = 0.0f;
        this.tonguepart1.zRot = 0.0f;
        this.tonguepart1.x = this.tonguepart3.x = this.tonguepart2.x;
        this.tonguepart1.y = this.tonguepart3.y = this.tonguepart2.y;
        this.tonguepart1.z = this.tonguepart2.z - newangle * 5.0f;
        this.tonguepart3.z = this.tonguepart2.z - newangle * 10.0f;
        this.thorax.xRot = newangle = MathHelper.sin((float)(f2 * 0.1f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.abdomen.y = (float)((double)this.thorax.y - Math.sin(newangle) * 14.0);
        newangle = MathHelper.cos((float)(f2 * 1.4f * this.wingspeed)) * 3.1415927f * 0.2f;
        this.wing_1.xRot = 0.455f + newangle;
        this.wing_2.xRot = 0.455f + newangle;
        this.wing_3.xRot = 0.455f - newangle;
        this.wing_4.xRot = 0.455f - newangle;
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg5part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg6part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tonguepart1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tonguepart2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tonguepart3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

