/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.DungeonBeast
 *  com.astryxion.chaospersists.ModelDungeonBeast
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ModelDungeonBeast extends EntityModel<DungeonBeast> {
    private float wingspeed = 1.0f;
    ModelRenderer tail7;
    ModelRenderer head3;
    ModelRenderer neck;
    ModelRenderer lhornbase;
    ModelRenderer leye;
    ModelRenderer ljaw3;
    ModelRenderer ljaw1;
    ModelRenderer ljaw2;
    ModelRenderer rjaw1;
    ModelRenderer rjaw2;
    ModelRenderer rjaw3;
    ModelRenderer t1s3;
    ModelRenderer rshoulder;
    ModelRenderer rheel;
    ModelRenderer lshoulder;
    ModelRenderer rleg1;
    ModelRenderer rleg2;
    ModelRenderer lleg1;
    ModelRenderer lleg2;
    ModelRenderer rfoot;
    ModelRenderer ltoe3;
    ModelRenderer ltoe2;
    ModelRenderer ltoe1;
    ModelRenderer head1;
    ModelRenderer horn2;
    ModelRenderer rhornbase;
    ModelRenderer rh1;
    ModelRenderer lh1;
    ModelRenderer lh2;
    ModelRenderer rh2;
    ModelRenderer rh3;
    ModelRenderer lh3;
    ModelRenderer lh4;
    ModelRenderer rh4;
    ModelRenderer horn1;
    ModelRenderer t2s3;
    ModelRenderer tail3;
    ModelRenderer t4s1;
    ModelRenderer t6s1;
    ModelRenderer tail6;
    ModelRenderer body;
    ModelRenderer bodys1;
    ModelRenderer bodys2;
    ModelRenderer tail1;
    ModelRenderer bodys3;
    ModelRenderer t1s1;
    ModelRenderer t1s2;
    ModelRenderer tail2;
    ModelRenderer t3s2;
    ModelRenderer t2s2;
    ModelRenderer t2s1;
    ModelRenderer t3s1;
    ModelRenderer tail4;
    ModelRenderer tail5;
    ModelRenderer t5s1;
    ModelRenderer head2;
    ModelRenderer reye;
    ModelRenderer lfoot;
    ModelRenderer rfoot2;
    ModelRenderer lfoot2;
    ModelRenderer lheel;
    ModelRenderer rtoe3;
    ModelRenderer rtoe2;
    ModelRenderer rtoe1;

    public ModelDungeonBeast(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 64;
        this.tail7 = new ModelRenderer(this, 0, 0);
        this.tail7.addBox(-1.0f, -0.5f, -0.5333334f, 3, 1, 1);
        this.tail7.setPos(-24.0f, 23.5f, 0.0f);
        this.tail7.mirror = true;
        this.setRotation(this.tail7, 0.0f, 0.0f, 3.141593f);
        this.head3 = new ModelRenderer(this, 0, 0);
        this.head3.addBox(2.0f, -2.466667f, 4.3f, 4, 4, 2);
        this.head3.setPos(5.0f, 15.0f, 0.0f);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.8028515f, 0.0f);
        this.neck = new ModelRenderer(this, 0, 0);
        this.neck.addBox(0.0f, -1.5f, -2.533333f, 3, 3, 5);
        this.neck.setPos(5.0f, 15.0f, 0.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, -0.1745329f);
        this.lhornbase = new ModelRenderer(this, 0, 0);
        this.lhornbase.addBox(2.5f, -3.0f, 0.5f, 3, 1, 2);
        this.lhornbase.setPos(5.0f, 15.0f, 0.0f);
        this.lhornbase.mirror = true;
        this.setRotation(this.lhornbase, 0.0f, 0.0f, 0.0f);
        this.leye = new ModelRenderer(this, 14, 15);
        this.leye.addBox(3.0f, -1.466667f, 3.3f, 2, 1, 2);
        this.leye.setPos(5.0f, 15.0f, 0.0f);
        this.leye.mirror = true;
        this.setRotation(this.leye, 0.0f, 0.4363323f, 0.0f);
        this.ljaw3 = new ModelRenderer(this, 10, 28);
        this.ljaw3.addBox(3.5f, 0.0f, 1.5f, 1, 1, 1);
        this.ljaw3.setPos(10.0f, 16.0f, 2.0f);
        this.ljaw3.mirror = true;
        this.setRotation(this.ljaw3, 0.0f, 0.5235988f, 0.0f);
        this.ljaw1 = new ModelRenderer(this, 10, 20);
        this.ljaw1.addBox(0.0f, 0.0f, -1.466667f, 3, 1, 2);
        this.ljaw1.setPos(10.0f, 16.0f, 2.0f);
        this.ljaw1.mirror = true;
        this.setRotation(this.ljaw1, 0.0f, -0.3490659f, 0.0f);
        this.ljaw2 = new ModelRenderer(this, 10, 24);
        this.ljaw2.addBox(2.0f, 0.0f, 0.3f, 2, 1, 2);
        this.ljaw2.setPos(10.0f, 16.0f, 2.0f);
        this.ljaw2.mirror = true;
        this.setRotation(this.ljaw2, 0.0f, 0.3490659f, 0.0f);
        this.rjaw1 = new ModelRenderer(this, 10, 20);
        this.rjaw1.addBox(0.0f, 0.0f, -0.4666667f, 3, 1, 2);
        this.rjaw1.setPos(10.0f, 16.0f, -2.0f);
        this.rjaw1.mirror = true;
        this.setRotation(this.rjaw1, 0.0f, 0.3490659f, 0.0f);
        this.rjaw2 = new ModelRenderer(this, 10, 24);
        this.rjaw2.addBox(2.0f, 0.0f, -2.3f, 2, 1, 2);
        this.rjaw2.setPos(10.0f, 16.0f, -2.0f);
        this.rjaw2.mirror = true;
        this.setRotation(this.rjaw2, 0.0f, -0.3490659f, 0.0f);
        this.rjaw3 = new ModelRenderer(this, 10, 28);
        this.rjaw3.addBox(3.5f, 0.0f, -2.5f, 1, 1, 1);
        this.rjaw3.setPos(10.0f, 16.0f, -2.0f);
        this.rjaw3.mirror = true;
        this.setRotation(this.rjaw3, 0.0f, -0.5235988f, 0.0f);
        this.t1s3 = new ModelRenderer(this, 75, 0);
        this.t1s3.addBox(-3.0f, -7.0f, -0.5f, 1, 4, 1);
        this.t1s3.setPos(-1.0f, 15.0f, 0.0f);
        this.t1s3.mirror = true;
        this.setRotation(this.t1s3, 0.0f, 0.0f, -0.8726646f);
        this.rshoulder = new ModelRenderer(this, 0, 0);
        this.rshoulder.addBox(2.0f, -2.2f, -5.0f, 4, 4, 2);
        this.rshoulder.setPos(-1.0f, 15.0f, 0.0f);
        this.rshoulder.mirror = true;
        this.setRotation(this.rshoulder, 0.0f, 0.0f, 0.0f);
        this.rheel = new ModelRenderer(this, 0, 0);
        this.rheel.addBox(-2.3f, 0.3f, 6.0f, 1, 1, 1);
        this.rheel.setPos(3.0f, 17.0f, -7.0f);
        this.rheel.mirror = true;
        this.setRotation(this.rheel, -1.570796f, 0.0f, 0.0f);
        this.lshoulder = new ModelRenderer(this, 0, 0);
        this.lshoulder.addBox(2.0f, -2.2f, 3.0f, 4, 4, 2);
        this.lshoulder.setPos(-1.0f, 15.0f, 0.0f);
        this.lshoulder.mirror = true;
        this.setRotation(this.lshoulder, 0.0f, 0.0f, 0.0f);
        this.rleg1 = new ModelRenderer(this, 0, 0);
        this.rleg1.addBox(-1.466667f, -2.0f, -5.0f, 3, 3, 6);
        this.rleg1.setPos(3.0f, 15.0f, -4.0f);
        this.rleg1.mirror = true;
        this.setRotation(this.rleg1, 0.6981317f, 0.0f, 0.0f);
        this.rleg2 = new ModelRenderer(this, 0, 0);
        this.rleg2.addBox(-1.0f, -0.2f, 0.0f, 2, 2, 6);
        this.rleg2.setPos(3.0f, 17.0f, -7.0f);
        this.rleg2.mirror = true;
        this.setRotation(this.rleg2, -1.570796f, 0.0f, 0.0f);
        this.lleg1 = new ModelRenderer(this, 0, 0);
        this.lleg1.addBox(-1.466667f, -2.0f, -1.0f, 3, 3, 6);
        this.lleg1.setPos(3.0f, 15.0f, 4.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, -0.6981317f, 0.0f, 0.0f);
        this.lleg2 = new ModelRenderer(this, 0, 0);
        this.lleg2.addBox(-1.0f, -1.8f, 0.0f, 2, 2, 6);
        this.lleg2.setPos(3.0f, 17.0f, 7.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, -1.570796f, 0.0f, 0.0f);
        this.rfoot = new ModelRenderer(this, 0, 0);
        this.rfoot.addBox(-1.5f, -0.7f, 5.0f, 3, 3, 2);
        this.rfoot.setPos(3.0f, 17.0f, -7.0f);
        this.rfoot.mirror = true;
        this.setRotation(this.rfoot, -1.570796f, 0.0f, 0.0f);
        this.ltoe3 = new ModelRenderer(this, 32, 0);
        this.ltoe3.addBox(-3.7f, -1.5f, 4.5f, 1, 1, 2);
        this.ltoe3.setPos(3.0f, 17.0f, 7.0f);
        this.ltoe3.mirror = true;
        this.setRotation(this.ltoe3, -1.570796f, 0.7853982f, -0.7853982f);
        this.ltoe2 = new ModelRenderer(this, 32, 0);
        this.ltoe2.addBox(-3.0f, -1.3f, 5.2f, 1, 1, 2);
        this.ltoe2.setPos(3.0f, 17.0f, 7.0f);
        this.ltoe2.mirror = true;
        this.setRotation(this.ltoe2, -1.570796f, 0.0f, -0.7853982f);
        this.ltoe1 = new ModelRenderer(this, 32, 0);
        this.ltoe1.addBox(-3.0f, -0.6f, 5.2f, 1, 1, 2);
        this.ltoe1.setPos(3.0f, 17.0f, 7.0f);
        this.ltoe1.mirror = true;
        this.setRotation(this.ltoe1, -1.570796f, -0.7853982f, -0.7853982f);
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.addBox(2.0f, -2.466667f, -3.0f, 4, 4, 6);
        this.head1.setPos(5.0f, 15.0f, 0.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.horn2 = new ModelRenderer(this, 75, 6);
        this.horn2.addBox(-7.0f, -4.0f, -0.5f, 1, 2, 1);
        this.horn2.setPos(5.0f, 15.0f, 0.0f);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, 0.0f, 0.0f, 2.181662f);
        this.rhornbase = new ModelRenderer(this, 0, 0);
        this.rhornbase.addBox(2.5f, -3.0f, -2.5f, 3, 1, 2);
        this.rhornbase.setPos(5.0f, 15.0f, 0.0f);
        this.rhornbase.mirror = true;
        this.setRotation(this.rhornbase, 0.0f, 0.0f, 0.0f);
        this.rh1 = new ModelRenderer(this, 0, 28);
        this.rh1.addBox(4.0f, -3.0f, -2.5f, 2, 3, 2);
        this.rh1.setPos(5.0f, 15.0f, 0.0f);
        this.rh1.mirror = true;
        this.setRotation(this.rh1, 0.0f, 0.0f, -0.5235988f);
        this.lh1 = new ModelRenderer(this, 0, 28);
        this.lh1.addBox(4.0f, -3.0f, 0.5f, 2, 3, 2);
        this.lh1.setPos(5.0f, 15.0f, 0.0f);
        this.lh1.mirror = true;
        this.setRotation(this.lh1, 0.0f, 0.0f, -0.5235988f);
        this.lh2 = new ModelRenderer(this, 0, 23);
        this.lh2.addBox(5.0f, -4.0f, 1.0f, 1, 3, 1);
        this.lh2.setPos(5.0f, 15.0f, 0.0f);
        this.lh2.mirror = true;
        this.setRotation(this.lh2, 0.0f, 0.0f, -0.8726646f);
        this.rh2 = new ModelRenderer(this, 0, 23);
        this.rh2.addBox(5.0f, -4.0f, -2.0f, 1, 3, 1);
        this.rh2.setPos(5.0f, 15.0f, 0.0f);
        this.rh2.mirror = true;
        this.setRotation(this.rh2, 0.0f, 0.0f, -0.8726646f);
        this.rh3 = new ModelRenderer(this, 0, 19);
        this.rh3.addBox(6.1f, -2.4f, -2.0f, 1, 2, 1);
        this.rh3.setPos(5.0f, 15.0f, 0.0f);
        this.rh3.mirror = true;
        this.setRotation(this.rh3, 0.0f, 0.0f, -1.396263f);
        this.lh3 = new ModelRenderer(this, 0, 19);
        this.lh3.addBox(6.1f, -2.4f, 1.0f, 1, 2, 1);
        this.lh3.setPos(5.0f, 15.0f, 0.0f);
        this.lh3.mirror = true;
        this.setRotation(this.lh3, 0.0f, 0.0f, -1.396263f);
        this.lh4 = new ModelRenderer(this, 0, 15);
        this.lh4.addBox(6.5f, -1.8f, 1.0f, 1, 2, 1);
        this.lh4.setPos(5.0f, 15.0f, 0.0f);
        this.lh4.mirror = true;
        this.setRotation(this.lh4, 0.0f, 0.0f, -1.745329f);
        this.rh4 = new ModelRenderer(this, 0, 15);
        this.rh4.addBox(6.5f, -1.8f, -2.0f, 1, 2, 1);
        this.rh4.setPos(5.0f, 15.0f, 0.0f);
        this.rh4.mirror = true;
        this.setRotation(this.rh4, 0.0f, 0.0f, -1.745329f);
        this.horn1 = new ModelRenderer(this, 75, 6);
        this.horn1.addBox(-8.0f, -2.5f, -0.5f, 1, 2, 1);
        this.horn1.setPos(5.0f, 15.0f, 0.0f);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, 0.0f, 0.0f, 2.617994f);
        this.t2s3 = new ModelRenderer(this, 75, 0);
        this.t2s3.addBox(3.0f, 3.466667f, -0.5333334f, 1, 3, 1);
        this.t2s3.setPos(-6.0f, 17.0f, 0.0f);
        this.t2s3.mirror = true;
        this.setRotation(this.t2s3, 0.0f, 0.0f, 2.007129f);
        this.tail3 = new ModelRenderer(this, 0, 0);
        this.tail3.addBox(-1.0f, -1.5f, -2.5f, 6, 3, 5);
        this.tail3.setPos(-10.0f, 20.0f, 0.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.0f, 0.0f, 2.530727f);
        this.t4s1 = new ModelRenderer(this, 75, 0);
        this.t4s1.addBox(0.5333334f, 1.533333f, -0.4666667f, 1, 2, 1);
        this.t4s1.setPos(-14.0f, 22.8f, 0.0f);
        this.t4s1.mirror = true;
        this.setRotation(this.t4s1, 0.0f, 0.0f, 2.356194f);
        this.t6s1 = new ModelRenderer(this, 75, 0);
        this.t6s1.addBox(0.0f, 0.5f, -0.5f, 1, 1, 1);
        this.t6s1.setPos(-21.0f, 23.5f, 0.0f);
        this.t6s1.mirror = true;
        this.setRotation(this.t6s1, 0.0f, 0.0f, 2.356194f);
        this.tail6 = new ModelRenderer(this, 0, 0);
        this.tail6.addBox(-1.0f, -0.5f, -1.0f, 4, 1, 2);
        this.tail6.setPos(-21.0f, 23.5f, 0.0f);
        this.tail6.mirror = true;
        this.setRotation(this.tail6, 0.0f, 0.0f, 3.141593f);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0.0f, -3.0f, -4.0f, 7, 6, 8);
        this.body.setPos(-1.0f, 15.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.bodys1 = new ModelRenderer(this, 75, 0);
        this.bodys1.addBox(6.0f, -3.0f, -0.5f, 1, 4, 1);
        this.bodys1.setPos(-1.0f, 15.0f, 0.0f);
        this.bodys1.mirror = true;
        this.setRotation(this.bodys1, 0.0f, 0.0f, -0.5235988f);
        this.bodys2 = new ModelRenderer(this, 75, 0);
        this.bodys2.addBox(4.0f, -4.0f, -0.5f, 1, 4, 1);
        this.bodys2.setPos(-1.0f, 15.0f, 0.0f);
        this.bodys2.mirror = true;
        this.setRotation(this.bodys2, 0.0f, 0.0f, -0.5235988f);
        this.tail1 = new ModelRenderer(this, 0, 0);
        this.tail1.addBox(-1.0f, -2.533333f, -3.5f, 7, 5, 7);
        this.tail1.setPos(-1.0f, 15.0f, 0.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 2.792527f);
        this.bodys3 = new ModelRenderer(this, 75, 0);
        this.bodys3.addBox(2.0f, -5.0f, -0.5f, 1, 4, 1);
        this.bodys3.setPos(-1.0f, 15.0f, 0.0f);
        this.bodys3.mirror = true;
        this.setRotation(this.bodys3, 0.0f, 0.0f, -0.5235988f);
        this.t1s1 = new ModelRenderer(this, 75, 0);
        this.t1s1.addBox(1.0f, -5.0f, -0.5f, 1, 4, 1);
        this.t1s1.setPos(-1.0f, 15.0f, 0.0f);
        this.t1s1.mirror = true;
        this.setRotation(this.t1s1, 0.0f, 0.0f, -0.8726646f);
        this.t1s2 = new ModelRenderer(this, 75, 0);
        this.t1s2.addBox(-1.0f, -6.0f, -0.5f, 1, 4, 1);
        this.t1s2.setPos(-1.0f, 15.0f, 0.0f);
        this.t1s2.mirror = true;
        this.setRotation(this.t1s2, 0.0f, 0.0f, -0.8726646f);
        this.tail2 = new ModelRenderer(this, 0, 0);
        this.tail2.addBox(-1.0f, -2.0f, -3.0f, 7, 4, 6);
        this.tail2.setPos(-6.0f, 17.0f, 0.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 2.530727f);
        this.t3s2 = new ModelRenderer(this, 75, 0);
        this.t3s2.addBox(2.5f, 2.466667f, -0.5333334f, 1, 3, 1);
        this.t3s2.setPos(-10.0f, 20.0f, 0.0f);
        this.t3s2.mirror = true;
        this.setRotation(this.t3s2, 0.0f, 0.0f, 2.007129f);
        this.t2s2 = new ModelRenderer(this, 75, 0);
        this.t2s2.addBox(1.0f, 2.466667f, -0.5333334f, 1, 3, 1);
        this.t2s2.setPos(-6.0f, 17.0f, 0.0f);
        this.t2s2.mirror = true;
        this.setRotation(this.t2s2, 0.0f, 0.0f, 2.007129f);
        this.t2s1 = new ModelRenderer(this, 75, 0);
        this.t2s1.addBox(-1.0f, 1.466667f, -0.5333334f, 1, 3, 1);
        this.t2s1.setPos(-6.0f, 17.0f, 0.0f);
        this.t2s1.mirror = true;
        this.setRotation(this.t2s1, 0.0f, 0.0f, 2.007129f);
        this.t3s1 = new ModelRenderer(this, 75, 0);
        this.t3s1.addBox(0.5f, 1.466667f, -0.5333334f, 1, 3, 1);
        this.t3s1.setPos(-10.0f, 20.0f, 0.0f);
        this.t3s1.mirror = true;
        this.setRotation(this.t3s1, 0.0f, 0.0f, 2.007129f);
        this.tail4 = new ModelRenderer(this, 0, 0);
        this.tail4.addBox(-1.0f, -1.0f, -2.0f, 5, 2, 4);
        this.tail4.setPos(-14.0f, 22.8f, 0.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, 0.0f, 0.0f, 3.054326f);
        this.tail5 = new ModelRenderer(this, 0, 0);
        this.tail5.addBox(-1.0f, -0.5f, -1.5f, 4, 1, 3);
        this.tail5.setPos(-18.0f, 23.2f, 0.0f);
        this.tail5.mirror = true;
        this.setRotation(this.tail5, 0.0f, 0.0f, 3.054326f);
        this.t5s1 = new ModelRenderer(this, 75, 0);
        this.t5s1.addBox(0.0f, 0.5f, -0.5f, 1, 2, 1);
        this.t5s1.setPos(-18.0f, 23.2f, 0.0f);
        this.t5s1.mirror = true;
        this.setRotation(this.t5s1, 0.0f, 0.0f, 2.356194f);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.addBox(2.0f, -2.466667f, -6.3f, 4, 4, 2);
        this.head2.setPos(5.0f, 15.0f, 0.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, -0.8028515f, 0.0f);
        this.reye = new ModelRenderer(this, 5, 15);
        this.reye.addBox(3.0f, -1.466667f, -5.3f, 2, 1, 2);
        this.reye.setPos(5.0f, 15.0f, 0.0f);
        this.reye.mirror = true;
        this.setRotation(this.reye, 0.0f, -0.4363323f, 0.0f);
        this.lfoot = new ModelRenderer(this, 0, 0);
        this.lfoot.addBox(-1.5f, -2.3f, 5.0f, 3, 3, 2);
        this.lfoot.setPos(3.0f, 17.0f, 7.0f);
        this.lfoot.mirror = true;
        this.setRotation(this.lfoot, -1.570796f, 0.0f, 0.0f);
        this.rfoot2 = new ModelRenderer(this, 0, 0);
        this.rfoot2.addBox(0.6f, -1.5f, 5.0f, 2, 2, 2);
        this.rfoot2.setPos(3.0f, 17.0f, -7.0f);
        this.rfoot2.mirror = true;
        this.setRotation(this.rfoot2, -1.570796f, 0.7853982f, 0.0f);
        this.lfoot2 = new ModelRenderer(this, 0, 0);
        this.lfoot2.addBox(0.7f, -0.5f, 5.0f, 2, 2, 2);
        this.lfoot2.setPos(3.0f, 17.0f, 7.0f);
        this.lfoot2.mirror = true;
        this.setRotation(this.lfoot2, -1.570796f, -0.7853982f, 0.0f);
        this.lheel = new ModelRenderer(this, 0, 0);
        this.lheel.addBox(-2.3f, -1.3f, 6.0f, 1, 1, 1);
        this.lheel.setPos(3.0f, 17.0f, 7.0f);
        this.lheel.mirror = true;
        this.setRotation(this.lheel, -1.570796f, 0.0f, 0.0f);
        this.rtoe3 = new ModelRenderer(this, 32, 0);
        this.rtoe3.addBox(-3.7f, 0.6f, 4.5f, 1, 1, 2);
        this.rtoe3.setPos(3.0f, 17.0f, -7.0f);
        this.rtoe3.mirror = true;
        this.setRotation(this.rtoe3, -1.570796f, -0.7853982f, -0.7853982f);
        this.rtoe2 = new ModelRenderer(this, 32, 0);
        this.rtoe2.addBox(-3.0f, 0.3f, 5.2f, 1, 1, 2);
        this.rtoe2.setPos(3.0f, 17.0f, -7.0f);
        this.rtoe2.mirror = true;
        this.setRotation(this.rtoe2, -1.570796f, 0.0f, -0.7853982f);
        this.rtoe1 = new ModelRenderer(this, 32, 0);
        this.rtoe1.addBox(-3.0f, -0.6f, 5.2f, 1, 1, 2);
        this.rtoe1.setPos(3.0f, 17.0f, -7.0f);
        this.rtoe1.mirror = true;
        this.setRotation(this.rtoe1, -1.570796f, 0.7853982f, -0.7853982f);
    }
    @Override
    public void setupAnim(DungeonBeast entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        DungeonBeast e = (DungeonBeast)entity;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float tailamp = 0.0f;
        float pi4 = 0.39269876f;
        this.rfoot2.zRot = this.rheel.zRot = (newangle = MathHelper.cos((float)(f2 * 1.4f * this.wingspeed)) * 3.1415927f * 0.22f * f1);
        this.rfoot.zRot = this.rheel.zRot;
        this.rleg2.zRot = this.rheel.zRot;
        this.rleg1.zRot = this.rheel.zRot;
        this.rtoe2.zRot = -0.785f + newangle;
        this.lfoot2.zRot = this.lheel.zRot = - newangle;
        this.lfoot.zRot = this.lheel.zRot;
        this.lleg2.zRot = this.lheel.zRot;
        this.lleg1.zRot = this.lheel.zRot;
        this.ltoe2.zRot = -0.785f - newangle;
        this.bodys1.xRot = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.07f;
        this.bodys2.xRot = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + pi4)) * 3.1415927f * 0.07f;
        this.bodys3.xRot = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 2.0f * pi4)) * 3.1415927f * 0.07f;
        this.t1s1.xRot = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 3.0f * pi4)) * 3.1415927f * 0.07f;
        this.t1s2.xRot = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 4.0f * pi4)) * 3.1415927f * 0.07f;
        this.t1s3.xRot = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 5.0f * pi4)) * 3.1415927f * 0.07f;
        this.t2s1.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 6.0f * pi4))) * 3.1415927f * 0.07f;
        this.t2s2.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 7.0f * pi4))) * 3.1415927f * 0.07f;
        this.t2s3.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 8.0f * pi4))) * 3.1415927f * 0.07f;
        this.t3s1.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 9.0f * pi4))) * 3.1415927f * 0.07f;
        this.t3s2.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 10.0f * pi4))) * 3.1415927f * 0.07f;
        this.t4s1.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 11.0f * pi4))) * 3.1415927f * 0.07f;
        this.t5s1.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 12.0f * pi4))) * 3.1415927f * 0.07f;
        this.t6s1.xRot = (- MathHelper.cos((float)(f2 * 0.5f * this.wingspeed + 13.0f * pi4))) * 3.1415927f * 0.07f;
        tailamp = e.getAttacking() == 0 ? f1 : 1.25f;
        newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed)) * 3.1415927f * 0.25f * tailamp;
        this.t1s2.yRot = this.t1s3.yRot = (this.tail1.yRot = newangle * 0.25f);
        this.t1s1.yRot = this.t1s3.yRot;
        this.tail2.yRot = newangle * 0.5f;
        this.tail2.x = this.tail1.x - (float)Math.cos(this.tail1.yRot) * 6.0f;
        this.tail2.z = this.tail1.z - (float)Math.sin(this.tail1.yRot) * 6.0f;
        this.t2s2.yRot = this.t2s3.yRot = this.tail2.yRot;
        this.t2s1.yRot = this.t2s3.yRot;
        this.t2s2.z = this.t2s3.z = this.tail2.z;
        this.t2s1.z = this.t2s3.z;
        this.t2s2.x = this.t2s3.x = this.tail2.x;
        this.t2s1.x = this.t2s3.x;
        this.tail3.yRot = newangle * 0.75f;
        this.tail3.x = this.tail2.x - (float)Math.cos(this.tail2.yRot) * 5.0f;
        this.tail3.z = this.tail2.z - (float)Math.sin(this.tail2.yRot) * 5.0f;
        this.t3s1.yRot = this.t3s2.yRot = this.tail3.yRot;
        this.t3s1.z = this.t3s2.z = this.tail3.z;
        this.t3s1.x = this.t3s2.x = this.tail3.x;
        this.tail4.yRot = newangle;
        this.tail4.x = this.tail3.x - (float)Math.cos(this.tail3.yRot) * 4.5f;
        this.tail4.z = this.tail3.z - (float)Math.sin(this.tail3.yRot) * 4.5f;
        this.t4s1.yRot = this.tail4.yRot;
        this.t4s1.z = this.tail4.z;
        this.t4s1.x = this.tail4.x;
        this.tail5.yRot = newangle * 1.25f;
        this.tail5.x = this.tail4.x - (float)Math.cos(this.tail4.yRot) * 4.0f;
        this.tail5.z = this.tail4.z - (float)Math.sin(this.tail4.yRot) * 4.0f;
        this.t5s1.yRot = this.tail5.yRot;
        this.t5s1.z = this.tail5.z;
        this.t5s1.x = this.tail5.x;
        this.tail6.yRot = newangle * 1.5f;
        this.tail6.x = this.tail5.x - (float)Math.cos(this.tail5.yRot) * 3.0f;
        this.tail6.z = this.tail5.z - (float)Math.sin(this.tail5.yRot) * 3.0f;
        this.t6s1.yRot = this.tail6.yRot;
        this.t6s1.z = this.tail6.z;
        this.t6s1.x = this.tail6.x;
        this.tail7.yRot = newangle * 1.75f;
        this.tail7.x = this.tail6.x - (float)Math.cos(this.tail6.yRot) * 3.0f;
        this.tail7.z = this.tail6.z - (float)Math.sin(this.tail6.yRot) * 3.0f;
        r = e.getRenderInfo();
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 2.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            if (e.getAttacking() == 0) {
                r.ri1 = e.level.random.nextInt(15);
                r.ri2 = e.level.random.nextInt(15);
            } else {
                r.ri1 = 0;
                r.ri2 = 0;
            }
        }
        if (r.ri1 == 0) {
            this.ljaw1.yRot = -0.349f + newangle;
            this.ljaw2.yRot = 0.349f + newangle;
            this.ljaw3.yRot = 0.523f + newangle;
            this.rjaw1.yRot = 0.349f - newangle;
            this.rjaw2.yRot = -0.349f - newangle;
            this.rjaw3.yRot = -0.523f - newangle;
        } else {
            this.ljaw1.yRot = -0.349f;
            this.ljaw2.yRot = 0.349f;
            this.ljaw3.yRot = 0.523f;
            this.rjaw1.yRot = 0.349f;
            this.rjaw2.yRot = -0.349f;
            this.rjaw3.yRot = -0.523f;
        }
        e.setRenderInfo(r);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.tail7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lhornbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t1s3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rheel.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltoe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rhornbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2s3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t4s1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t6s1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodys1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodys2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodys3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t1s1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t1s2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t3s2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2s2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2s1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t3s1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t5s1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.reye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lheel.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtoe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

