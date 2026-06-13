/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Godzilla
 *  com.astryxion.chaospersists.ModelGodzilla
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

import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.util.ModelTextureSizeHelper;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelGodzilla extends EntityModel<Godzilla> {
    private float wingspeed = 1.0f;
    ModelRenderer LToe1;
    ModelRenderer LToe3;
    ModelRenderer LToe2;
    ModelRenderer LToe9;
    ModelRenderer LToe8;
    ModelRenderer LToe7;
    ModelRenderer LToe6;
    ModelRenderer LToe5;
    ModelRenderer LToe4;
    ModelRenderer RToe9;
    ModelRenderer RToe6;
    ModelRenderer RToe5;
    ModelRenderer RToe2;
    ModelRenderer RToe1;
    ModelRenderer RToe4;
    ModelRenderer RToe7;
    ModelRenderer RToe8;
    ModelRenderer RToe3;
    ModelRenderer LThigh;
    ModelRenderer LLowerLeg;
    ModelRenderer LUpperLeg;
    ModelRenderer TailTip;
    ModelRenderer RLegLower;
    ModelRenderer RLegUpper;
    ModelRenderer RThigh;
    ModelRenderer LowerJaw;
    ModelRenderer TailBase;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer Tail5;
    ModelRenderer Tail6;
    ModelRenderer Tail7;
    ModelRenderer BodyBottom;
    ModelRenderer RLowerArm;
    ModelRenderer BodyCenter;
    ModelRenderer Neck;
    ModelRenderer TopJaw;
    ModelRenderer Head;
    ModelRenderer BodyTop;
    ModelRenderer RShoulder;
    ModelRenderer RThumbTip;
    ModelRenderer RUpperArm;
    ModelRenderer RHand;
    ModelRenderer RThumbBase;
    ModelRenderer R3rdFingerTip;
    ModelRenderer R3rdFingerBase;
    ModelRenderer RIndexTip;
    ModelRenderer RIndexBase;
    ModelRenderer LShoulder;
    ModelRenderer LUpperArm;
    ModelRenderer LLowerArm;
    ModelRenderer LIndexBase;
    ModelRenderer LIndexTip;
    ModelRenderer LHand;
    ModelRenderer LThumbBase;
    ModelRenderer LThumbTip;
    ModelRenderer L3rdFingerTip;
    ModelRenderer L3rdFingerBase;
    ModelRenderer Lspikes1;
    ModelRenderer Rspikes1;
    ModelRenderer Lspike2;
    ModelRenderer Rspike2;
    ModelRenderer Lspike3;
    ModelRenderer Rspike3;
    ModelRenderer Lspike4;
    ModelRenderer Rspike4;
    ModelRenderer Lspike5;
    ModelRenderer Rspike5;
    ModelRenderer Spike6;
    ModelRenderer Spikes7;

    public ModelGodzilla(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        this.LToe1 = new ModelRenderer(this, 45, 1002);
        this.LToe1.addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6);
        this.LToe1.setPos(54.0f, 16.0f, 6.0f);
        this.LToe1.mirror = true;
        this.setRotation(this.LToe1, 0.0f, 0.7853982f, 0.0f);
        this.LToe3 = new ModelRenderer(this, 0, 955);
        this.LToe3.addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30);
        this.LToe3.setPos(54.0f, 16.0f, 6.0f);
        this.LToe3.mirror = true;
        this.setRotation(this.LToe3, 0.0f, 0.7853982f, 0.0f);
        this.LToe2 = new ModelRenderer(this, 0, 1002);
        this.LToe2.addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8);
        this.LToe2.setPos(54.0f, 16.0f, 6.0f);
        this.LToe2.mirror = true;
        this.setRotation(this.LToe2, 0.0f, 0.7853982f, 0.0f);
        this.LToe9 = new ModelRenderer(this, 0, 955);
        this.LToe9.addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30);
        this.LToe9.setPos(54.0f, 16.0f, 6.0f);
        this.LToe9.mirror = true;
        this.setRotation(this.LToe9, 0.0f, -0.7853982f, 0.0f);
        this.LToe8 = new ModelRenderer(this, 0, 1002);
        this.LToe8.addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8);
        this.LToe8.setPos(54.0f, 16.0f, 6.0f);
        this.LToe8.mirror = true;
        this.setRotation(this.LToe8, 0.0f, -0.7853982f, 0.0f);
        this.LToe7 = new ModelRenderer(this, 45, 1002);
        this.LToe7.addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6);
        this.LToe7.setPos(54.0f, 16.0f, 6.0f);
        this.LToe7.mirror = true;
        this.setRotation(this.LToe7, 0.0f, -0.7853982f, 0.0f);
        this.LToe6 = new ModelRenderer(this, 92, 955);
        this.LToe6.addBox(-8.0f, -8.0f, -26.0f, 16, 16, 36);
        this.LToe6.setPos(54.0f, 16.0f, 6.0f);
        this.LToe6.mirror = true;
        this.setRotation(this.LToe6, 0.0f, 0.0f, 0.0f);
        this.LToe5 = new ModelRenderer(this, 0, 1002);
        this.LToe5.addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8);
        this.LToe5.setPos(54.0f, 16.0f, 6.0f);
        this.LToe5.mirror = true;
        this.setRotation(this.LToe5, 0.0f, 0.0f, 0.0f);
        this.LToe4 = new ModelRenderer(this, 45, 1002);
        this.LToe4.addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6);
        this.LToe4.setPos(54.0f, 16.0f, 6.0f);
        this.LToe4.mirror = true;
        this.setRotation(this.LToe4, 0.0f, 0.0f, 0.0f);
        this.RToe9 = new ModelRenderer(this, 0, 955);
        this.RToe9.addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30);
        this.RToe9.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe9.mirror = true;
        this.setRotation(this.RToe9, 0.0f, 0.7853982f, 0.0f);
        this.RToe6 = new ModelRenderer(this, 92, 955);
        this.RToe6.addBox(-8.0f, -8.0f, -26.0f, 16, 16, 36);
        this.RToe6.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe6.mirror = true;
        this.setRotation(this.RToe6, 0.0f, 0.0f, 0.0f);
        this.RToe5 = new ModelRenderer(this, 0, 1002);
        this.RToe5.addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8);
        this.RToe5.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe5.mirror = true;
        this.setRotation(this.RToe5, 0.0f, 0.0f, 0.0f);
        this.RToe2 = new ModelRenderer(this, 0, 1002);
        this.RToe2.addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8);
        this.RToe2.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe2.mirror = true;
        this.setRotation(this.RToe2, 0.0f, -0.7853982f, 0.0f);
        this.RToe1 = new ModelRenderer(this, 45, 1002);
        this.RToe1.addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6);
        this.RToe1.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe1.mirror = true;
        this.setRotation(this.RToe1, 0.0f, -0.7853982f, 0.0f);
        this.RToe4 = new ModelRenderer(this, 45, 1002);
        this.RToe4.addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6);
        this.RToe4.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe4.mirror = true;
        this.setRotation(this.RToe4, 0.0f, 0.0f, 0.0f);
        this.RToe7 = new ModelRenderer(this, 45, 1002);
        this.RToe7.addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6);
        this.RToe7.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe7.mirror = true;
        this.setRotation(this.RToe7, 0.0f, 0.7853982f, 0.0f);
        this.RToe8 = new ModelRenderer(this, 0, 1002);
        this.RToe8.addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8);
        this.RToe8.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe8.mirror = true;
        this.setRotation(this.RToe8, 0.0f, 0.7853982f, 0.0f);
        this.RToe3 = new ModelRenderer(this, 0, 955);
        this.RToe3.addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30);
        this.RToe3.setPos(-54.0f, 16.0f, 6.0f);
        this.RToe3.mirror = true;
        this.setRotation(this.RToe3, 0.0f, -0.7853982f, 0.0f);
        this.LThigh = new ModelRenderer(this, 192, 350);
        this.LThigh.addBox(0.0f, -14.0f, -21.0f, 28, 28, 42);
        this.LThigh.setPos(40.0f, -91.0f, 2.0f);
        this.LThigh.mirror = true;
        this.setRotation(this.LThigh, -0.5585054f, 0.0f, 0.0f);
        this.LLowerLeg = new ModelRenderer(this, 202, 556);
        this.LLowerLeg.addBox(-15.0f, -62.0f, -15.0f, 30, 62, 30);
        this.LLowerLeg.setPos(54.0f, 14.0f, 6.0f);
        this.LLowerLeg.mirror = true;
        this.setRotation(this.LLowerLeg, 0.1745329f, -0.1308997f, 0.0f);
        this.LUpperLeg = new ModelRenderer(this, 152, 420);
        this.LUpperLeg.addBox(-16.0f, -52.0f, -16.0f, 32, 52, 32);
        this.LUpperLeg.setPos(56.0f, -36.0f, -5.0f);
        this.LUpperLeg.mirror = true;
        this.setRotation(this.LUpperLeg, -0.1745329f, -0.3926991f, -0.0872665f);
        this.TailTip = new ModelRenderer(this, 0, 694);
        this.TailTip.addBox(-6.0f, 0.0f, -5.0f, 12, 21, 10);
        this.TailTip.setPos(0.0f, 18.0f, 203.0f);
        this.TailTip.mirror = true;
        this.setRotation(this.TailTip, 1.53589f, 0.0f, 0.0f);
        this.RLegLower = new ModelRenderer(this, 200, 646);
        this.RLegLower.addBox(-15.0f, -62.0f, -15.0f, 30, 62, 30);
        this.RLegLower.setPos(-54.0f, 16.0f, 6.0f);
        this.RLegLower.mirror = true;
        this.setRotation(this.RLegLower, 0.1745329f, 0.1308997f, 0.0f);
        this.RLegUpper = new ModelRenderer(this, 152, 420);
        this.RLegUpper.addBox(-16.0f, -52.0f, -16.0f, 32, 52, 32);
        this.RLegUpper.setPos(-56.0f, -36.0f, -5.0f);
        this.RLegUpper.mirror = true;
        this.setRotation(this.RLegUpper, -0.1745329f, 0.3926991f, 0.0872665f);
        this.RThigh = new ModelRenderer(this, 192, 350);
        this.RThigh.addBox(-28.0f, -14.0f, -21.0f, 28, 28, 42);
        this.RThigh.setPos(-40.0f, -91.0f, 2.0f);
        this.RThigh.mirror = true;
        this.setRotation(this.RThigh, -0.5585054f, 0.0f, 0.0f);
        this.LowerJaw = new ModelRenderer(this, 272, 0);
        this.LowerJaw.addBox(-13.0f, -5.0f, -50.0f, 26, 11, 50);
        this.LowerJaw.setPos(0.0f, -142.0f, -109.0f);
        this.LowerJaw.mirror = true;
        this.setRotation(this.LowerJaw, 0.5235988f, 0.0f, 0.0f);
        this.TailBase = new ModelRenderer(this, 0, 240);
        this.TailBase.addBox(-32.0f, 0.0f, -29.0f, 64, 40, 58);
        this.TailBase.setPos(0.0f, -73.0f, 26.0f);
        this.TailBase.mirror = true;
        this.setRotation(this.TailBase, 0.7853982f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 0, 338);
        this.Tail2.addBox(-25.0f, 0.0f, -23.0f, 50, 36, 46);
        this.Tail2.setPos(0.0f, -48.0f, 48.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, 0.6981317f, 0.0f, 0.0f);
        this.Tail3 = new ModelRenderer(this, 0, 420);
        this.Tail3.addBox(-20.0f, 0.0f, -18.0f, 40, 36, 36);
        this.Tail3.setPos(0.0f, -24.0f, 66.0f);
        this.Tail3.mirror = true;
        this.setRotation(this.Tail3, 0.8726646f, 0.0f, 0.0f);
        this.Tail4 = new ModelRenderer(this, 0, 492);
        this.Tail4.addBox(-16.0f, 0.0f, -14.0f, 32, 42, 28);
        this.Tail4.setPos(0.0f, -3.0f, 87.0f);
        this.Tail4.mirror = true;
        this.setRotation(this.Tail4, 1.134464f, 0.0f, 0.0f);
        this.Tail5 = new ModelRenderer(this, 0, 556);
        this.Tail5.addBox(-13.0f, 0.0f, -11.0f, 26, 42, 22);
        this.Tail5.setPos(0.0f, 12.0f, 116.0f);
        this.Tail5.mirror = true;
        this.setRotation(this.Tail5, 1.53589f, 0.0f, 0.0f);
        this.Tail6 = new ModelRenderer(this, 0, 614);
        this.Tail6.addBox(-10.0f, 0.0f, -9.0f, 20, 32, 18);
        this.Tail6.setPos(0.0f, 14.0f, 154.0f);
        this.Tail6.mirror = true;
        this.setRotation(this.Tail6, 1.53589f, 0.0f, 0.0f);
        this.Tail7 = new ModelRenderer(this, 0, 658);
        this.Tail7.addBox(-8.0f, 0.0f, -7.0f, 16, 22, 14);
        this.Tail7.setPos(0.0f, 16.0f, 185.0f);
        this.Tail7.mirror = true;
        this.setRotation(this.Tail7, 1.53589f, 0.0f, 0.0f);
        this.BodyBottom = new ModelRenderer(this, 0, 104);
        this.BodyBottom.addBox(-40.0f, 0.0f, -36.0f, 80, 64, 72);
        this.BodyBottom.setPos(0.0f, -112.0f, -20.0f);
        this.BodyBottom.mirror = true;
        this.setRotation(this.BodyBottom, 0.8726646f, 0.0f, 0.0f);
        this.RLowerArm = new ModelRenderer(this, 245, 240);
        this.RLowerArm.addBox(-48.0f, -11.0f, -11.0f, 48, 22, 22);
        this.RLowerArm.setPos(-80.0f, -115.0f, -61.0f);
        this.RLowerArm.mirror = true;
        this.setRotation(this.RLowerArm, 0.0f, -0.7853982f, -0.2617994f);
        this.BodyCenter = new ModelRenderer(this, 0, 0);
        this.BodyCenter.addBox(-36.0f, -32.0f, -32.0f, 72, 40, 64);
        this.BodyCenter.setPos(0.0f, -112.0f, -20.0f);
        this.BodyCenter.mirror = true;
        this.setRotation(this.BodyCenter, 1.134464f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer(this, 0, 720);
        this.Neck.addBox(-23.0f, -23.0f, -32.0f, 46, 46, 32);
        this.Neck.setPos(0.0f, -144.0f, -71.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, -0.0698132f, 0.0f, 0.0f);
        this.TopJaw = new ModelRenderer(this, 0, 892);
        this.TopJaw.addBox(-14.0f, -8.0f, -73.0f, 28, 26, 33);
        this.TopJaw.setPos(0.0f, -156.0f, -98.0f);
        this.TopJaw.mirror = true;
        this.setRotation(this.TopJaw, 0.0872665f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 0, 808);
        this.Head.addBox(-17.0f, -18.0f, -40.0f, 34, 36, 40);
        this.Head.setPos(0.0f, -156.0f, -98.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0872665f, 0.0f, 0.0f);
        this.BodyTop = new ModelRenderer(this, 0, 0);
        this.BodyTop.addBox(-36.0f, -32.0f, -32.0f, 72, 40, 64);
        this.BodyTop.setPos(0.0f, -126.0f, -50.0f);
        this.BodyTop.mirror = true;
        this.setRotation(this.BodyTop, 1.308997f, 0.0f, 0.0f);
        this.RShoulder = new ModelRenderer(this, 304, 96);
        this.RShoulder.addBox(-16.0f, -32.0f, -32.0f, 16, 42, 46);
        this.RShoulder.setPos(-36.0f, -130.0f, -42.0f);
        this.RShoulder.mirror = true;
        this.setRotation(this.RShoulder, 1.308997f, 0.0f, 0.0f);
        this.RThumbTip = new ModelRenderer(this, 422, 18);
        this.RThumbTip.addBox(5.0f, 1.0f, -43.0f, 8, 8, 12);
        this.RThumbTip.setPos(-115.0f, -100.0f, -99.0f);
        this.RThumbTip.mirror = true;
        this.setRotation(this.RThumbTip, 0.0f, 0.0f, 0.0f);
        this.RUpperArm = new ModelRenderer(this, 304, 184);
        this.RUpperArm.addBox(-54.0f, -13.0f, -13.0f, 54, 26, 26);
        this.RUpperArm.setPos(-38.0f, -130.0f, -52.0f);
        this.RUpperArm.mirror = true;
        this.setRotation(this.RUpperArm, 0.0f, -0.2617994f, -0.3490659f);
        this.RHand = new ModelRenderer(this, 245, 292);
        this.RHand.addBox(-13.0f, -13.0f, -13.0f, 26, 26, 26);
        this.RHand.setPos(-115.0f, -100.0f, -99.0f);
        this.RHand.mirror = true;
        this.setRotation(this.RHand, -1.071467f, 2.007129f, 0.1745329f);
        this.RThumbBase = new ModelRenderer(this, 424, 57);
        this.RThumbBase.addBox(2.0f, 1.0f, -32.0f, 8, 8, 20);
        this.RThumbBase.setPos(-115.0f, -100.0f, -99.0f);
        this.RThumbBase.mirror = true;
        this.setRotation(this.RThumbBase, 0.0f, -0.1047198f, 0.0f);
        this.R3rdFingerTip = new ModelRenderer(this, 422, 18);
        this.R3rdFingerTip.addBox(-10.0f, 0.0f, -41.0f, 8, 8, 12);
        this.R3rdFingerTip.setPos(-115.0f, -100.0f, -99.0f);
        this.R3rdFingerTip.mirror = true;
        this.setRotation(this.R3rdFingerTip, 0.0f, 0.6806784f, 0.0f);
        this.R3rdFingerBase = new ModelRenderer(this, 424, 57);
        this.R3rdFingerBase.addBox(-11.0f, -3.0f, -30.0f, 8, 8, 20);
        this.R3rdFingerBase.setPos(-115.0f, -100.0f, -99.0f);
        this.R3rdFingerBase.mirror = true;
        this.setRotation(this.R3rdFingerBase, 0.122173f, 0.6457718f, 0.0f);
        this.RIndexTip = new ModelRenderer(this, 422, 18);
        this.RIndexTip.addBox(-4.0f, -12.0f, -43.0f, 8, 8, 12);
        this.RIndexTip.setPos(-115.0f, -100.0f, -99.0f);
        this.RIndexTip.mirror = true;
        this.setRotation(this.RIndexTip, -0.2094395f, 0.1745329f, 0.0f);
        this.RIndexBase = new ModelRenderer(this, 424, 57);
        this.RIndexBase.addBox(-4.0f, -9.0f, -34.0f, 8, 8, 20);
        this.RIndexBase.setPos(-115.0f, -100.0f, -99.0f);
        this.RIndexBase.mirror = true;
        this.setRotation(this.RIndexBase, -0.2792527f, 0.1570796f, 0.0f);
        this.LShoulder = new ModelRenderer(this, 304, 96);
        this.LShoulder.addBox(0.0f, -32.0f, -32.0f, 16, 42, 46);
        this.LShoulder.setPos(36.0f, -130.0f, -42.0f);
        this.LShoulder.mirror = true;
        this.setRotation(this.LShoulder, 1.308997f, 0.0f, 0.0f);
        this.LUpperArm = new ModelRenderer(this, 304, 184);
        this.LUpperArm.addBox(0.0f, -13.0f, -13.0f, 54, 26, 26);
        this.LUpperArm.setPos(38.0f, -130.0f, -52.0f);
        this.LUpperArm.mirror = true;
        this.setRotation(this.LUpperArm, 0.0f, 0.296706f, 0.3490659f);
        this.LLowerArm = new ModelRenderer(this, 245, 240);
        this.LLowerArm.addBox(0.0f, -11.0f, -11.0f, 48, 22, 22);
        this.LLowerArm.setPos(80.0f, -115.0f, -61.0f);
        this.LLowerArm.mirror = true;
        this.setRotation(this.LLowerArm, 0.0f, 0.7853982f, 0.2617994f);
        this.LIndexBase = new ModelRenderer(this, 424, 57);
        this.LIndexBase.addBox(-4.0f, -13.0f, -32.0f, 8, 8, 20);
        this.LIndexBase.setPos(115.0f, -100.0f, -99.0f);
        this.LIndexBase.mirror = true;
        this.setRotation(this.LIndexBase, -0.1570796f, -0.1396263f, 0.0f);
        this.LIndexTip = new ModelRenderer(this, 422, 18);
        this.LIndexTip.addBox(-1.0f, -18.0f, -41.0f, 8, 8, 12);
        this.LIndexTip.setPos(115.0f, -100.0f, -99.0f);
        this.LIndexTip.mirror = true;
        this.setRotation(this.LIndexTip, 0.0f, -0.0349066f, 0.0f);
        this.LHand = new ModelRenderer(this, 245, 292);
        this.LHand.addBox(-13.0f, -13.0f, -13.0f, 26, 26, 26);
        this.LHand.setPos(115.0f, -100.0f, -99.0f);
        this.LHand.mirror = true;
        this.setRotation(this.LHand, 0.9599311f, 1.308997f, 0.1745329f);
        this.LThumbBase = new ModelRenderer(this, 424, 57);
        this.LThumbBase.addBox(-8.0f, -2.0f, -32.0f, 8, 8, 20);
        this.LThumbBase.setPos(115.0f, -100.0f, -98.0f);
        this.LThumbBase.mirror = true;
        this.setRotation(this.LThumbBase, 0.1396263f, 0.2617994f, 0.0f);
        this.LThumbTip = new ModelRenderer(this, 422, 18);
        this.LThumbTip.addBox(-12.0f, 2.0f, -40.0f, 8, 8, 12);
        this.LThumbTip.setPos(115.0f, -100.0f, -99.0f);
        this.LThumbTip.mirror = true;
        this.setRotation(this.LThumbTip, 0.0f, 0.1396263f, 0.0f);
        this.L3rdFingerTip = new ModelRenderer(this, 422, 18);
        this.L3rdFingerTip.addBox(9.0f, 2.0f, -42.0f, 8, 8, 12);
        this.L3rdFingerTip.setPos(115.0f, -100.0f, -99.0f);
        this.L3rdFingerTip.mirror = true;
        this.setRotation(this.L3rdFingerTip, 0.0349066f, -0.3316126f, 0.0f);
        this.L3rdFingerBase = new ModelRenderer(this, 424, 57);
        this.L3rdFingerBase.addBox(4.0f, -5.0f, -33.0f, 8, 8, 20);
        this.L3rdFingerBase.setPos(115.0f, -100.0f, -99.0f);
        this.L3rdFingerBase.mirror = true;
        this.setRotation(this.L3rdFingerBase, 0.2617994f, -0.4712389f, 0.0f);
        this.Lspikes1 = new ModelRenderer(this, 500, 0);
        this.Lspikes1.addBox(0.0f, -10.0f, 0.0f, 0, 10, 11);
        this.Lspikes1.setPos(5.0f, -168.0f, -86.0f);
        this.Lspikes1.mirror = true;
        this.setRotation(this.Lspikes1, -0.0872665f, 0.0f, -0.0174533f);
        this.Rspikes1 = new ModelRenderer(this, 500, 0);
        this.Rspikes1.addBox(0.0f, -10.0f, 0.0f, 0, 10, 11);
        this.Rspikes1.setPos(-5.0f, -168.0f, -86.0f);
        this.Rspikes1.mirror = true;
        this.setRotation(this.Rspikes1, -0.0872665f, 0.0f, -0.0174533f);
        this.Lspike2 = new ModelRenderer(this, 500, 30);
        this.Lspike2.addBox(0.0f, -25.0f, 0.0f, 0, 25, 21);
        this.Lspike2.setPos(10.0f, -162.0f, -63.0f);
        this.Lspike2.mirror = true;
        this.setRotation(this.Lspike2, -0.2617994f, 0.0f, -0.0174533f);
        this.Rspike2 = new ModelRenderer(this, 500, 30);
        this.Rspike2.addBox(0.0f, -25.0f, 0.0f, 0, 25, 21);
        this.Rspike2.setPos(-10.0f, -162.0f, -63.0f);
        this.Rspike2.mirror = true;
        this.setRotation(this.Rspike2, -0.2617994f, 0.0f, -0.0174533f);
        this.Lspike3 = new ModelRenderer(this, 500, 80);
        this.Lspike3.addBox(0.0f, -45.0f, 0.0f, 0, 45, 34);
        this.Lspike3.setPos(14.0f, -153.0f, -32.0f);
        this.Lspike3.mirror = true;
        this.setRotation(this.Lspike3, -0.4363323f, 0.0f, -0.0174533f);
        this.Rspike3 = new ModelRenderer(this, 500, 80);
        this.Rspike3.addBox(0.0f, -45.0f, 0.0f, 0, 45, 34);
        this.Rspike3.setPos(-14.0f, -153.0f, -32.0f);
        this.Rspike3.mirror = true;
        this.setRotation(this.Rspike3, -0.4363323f, 0.0f, -0.0174533f);
        this.Lspike4 = new ModelRenderer(this, 500, 165);
        this.Lspike4.addBox(0.0f, -50.0f, 0.0f, 0, 50, 36);
        this.Lspike4.setPos(18.0f, -131.0f, 13.0f);
        this.Lspike4.mirror = true;
        this.setRotation(this.Lspike4, -0.715585f, 0.0f, -0.0174533f);
        this.Rspike4 = new ModelRenderer(this, 500, 165);
        this.Rspike4.addBox(0.0f, -50.0f, 0.0f, 0, 50, 36);
        this.Rspike4.setPos(-18.0f, -131.0f, 13.0f);
        this.Rspike4.mirror = true;
        this.setRotation(this.Rspike4, -0.715585f, 0.0f, -0.0174533f);
        this.Lspike5 = new ModelRenderer(this, 500, 255);
        this.Lspike5.addBox(12.0f, -67.0f, 5.0f, 0, 39, 27);
        this.Lspike5.setPos(0.0f, -73.0f, 26.0f);
        this.Lspike5.mirror = true;
        this.setRotation(this.Lspike5, -0.7853982f, 0.0f, -0.0174533f);
        this.Rspike5 = new ModelRenderer(this, 500, 255);
        this.Rspike5.addBox(-12.0f, -67.0f, 5.0f, 0, 39, 27);
        this.Rspike5.setPos(0.0f, -73.0f, 26.0f);
        this.Rspike5.mirror = true;
        this.setRotation(this.Rspike5, -0.7853982f, 0.0f, -0.0174533f);
        this.Spike6 = new ModelRenderer(this, 500, 325);
        this.Spike6.addBox(0.0f, -48.0f, 11.0f, 0, 25, 21);
        this.Spike6.setPos(0.0f, -48.0f, 48.0f);
        this.Spike6.mirror = true;
        this.setRotation(this.Spike6, -0.8901179f, 0.0f, -0.0174533f);
        this.Spikes7 = new ModelRenderer(this, 500, 376);
        this.Spikes7.addBox(0.0f, -29.0f, 20.0f, 0, 10, 11);
        this.Spikes7.setPos(0.0f, -24.0f, 66.0f);
        this.Spikes7.mirror = true;
        this.setRotation(this.Spikes7, -0.7504916f, 0.0f, -0.0174533f);
        ModelTextureSizeHelper.apply(this, 1024, 1024);
    }

    @Override
    public void setupAnim(Godzilla entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Godzilla e = entity;
        RenderInfo r = null;
        float newangle = 0.0f;
        float newangle2 = 0.0f;
        float pscale = 1.0f;
        float pi4 = 0.7853982f;
        float clawZ = 6.0f;
        float clawY = 16.0f;
        float clawZamp = 35.0f * pscale;
        float clawYamp = 18.0f * pscale;
        float spikeamp = 1.0f;
        float spikefreq = 1.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        r = e.getRenderInfo();
        float t1 = 0.0f;
        float t2 = 0.0f;
        if ((double)f1 > 0.001) {
            newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed / pscale));
            newangle2 = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed / pscale + pi4));
            t1 = MathHelper.sin((float)(f2 * 0.75f * this.wingspeed / pscale));
        } else {
            newangle2 = 0.0f;
            newangle = 0.0f;
            t1 = 0.0f;
            t2 = 0.0f;
        }
        if (t1 > 0.0f) {
            t2 = t1 * clawYamp * f1;
            this.LToe1.y = clawY - t2;
        } else {
            this.LToe1.y = clawY;
        }
        this.LToe8.z = this.LToe9.z = (this.LToe1.z = clawZ + clawZamp * newangle * f1);
        this.LToe7.z = this.LToe9.z;
        this.LToe6.z = this.LToe9.z;
        this.LToe5.z = this.LToe9.z;
        this.LToe4.z = this.LToe9.z;
        this.LToe3.z = this.LToe9.z;
        this.LToe2.z = this.LToe9.z;
        this.LToe8.y = this.LToe9.y = this.LToe1.y;
        this.LToe7.y = this.LToe9.y;
        this.LToe6.y = this.LToe9.y;
        this.LToe5.y = this.LToe9.y;
        this.LToe4.y = this.LToe9.y;
        this.LToe3.y = this.LToe9.y;
        this.LToe2.y = this.LToe9.y;
        this.LLowerLeg.z = this.LToe1.z;
        this.LLowerLeg.y = this.LToe1.y;
        this.LLowerLeg.xRot = 0.22f + newangle * 3.1415927f * 0.09f * f1;
        this.LUpperLeg.xRot = -0.17f + newangle2 * 3.1415927f * 0.15f * f1;
        this.LUpperLeg.y = this.LLowerLeg.y - (float)Math.cos(this.LLowerLeg.xRot) * 55.0f;
        this.LUpperLeg.z = this.LLowerLeg.z - (float)Math.sin(this.LLowerLeg.xRot) * 55.0f;
        this.LThigh.xRot = -0.558f + newangle2 * 3.1415927f * 0.1f * f1;
        this.LThigh.z = 2.0f + clawZamp * newangle * f1 / 4.0f;
        t1 = 0.0f;
        t2 = 0.0f;
        if ((double)f1 > 0.001) {
            newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed / pscale + pi4 * 4.0f));
            newangle2 = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed / pscale + pi4 * 5.0f));
            t1 = MathHelper.sin((float)(f2 * 0.75f * this.wingspeed / pscale + pi4 * 4.0f));
        } else {
            newangle = 0.0f;
            t1 = 0.0f;
            t2 = 0.0f;
        }
        if (t1 > 0.0f) {
            t2 = t1 * clawYamp * f1;
            this.RToe1.y = clawY - t2;
        } else {
            this.RToe1.y = clawY;
        }
        this.RToe8.z = this.RToe9.z = (this.RToe1.z = clawZ + clawZamp * newangle * f1);
        this.RToe7.z = this.RToe9.z;
        this.RToe6.z = this.RToe9.z;
        this.RToe5.z = this.RToe9.z;
        this.RToe4.z = this.RToe9.z;
        this.RToe3.z = this.RToe9.z;
        this.RToe2.z = this.RToe9.z;
        this.RToe8.y = this.RToe9.y = this.RToe1.y;
        this.RToe7.y = this.RToe9.y;
        this.RToe6.y = this.RToe9.y;
        this.RToe5.y = this.RToe9.y;
        this.RToe4.y = this.RToe9.y;
        this.RToe3.y = this.RToe9.y;
        this.RToe2.y = this.RToe9.y;
        this.RLegLower.z = this.RToe1.z;
        this.RLegLower.y = this.RToe1.y;
        this.RLegLower.xRot = 0.22f + newangle * 3.1415927f * 0.09f * f1;
        this.RLegUpper.xRot = -0.17f + newangle2 * 3.1415927f * 0.15f * f1;
        this.RLegUpper.y = this.RLegLower.y - (float)Math.cos(this.RLegLower.xRot) * 55.0f;
        this.RLegUpper.z = this.RLegLower.z - (float)Math.sin(this.RLegLower.xRot) * 55.0f;
        this.RThigh.xRot = -0.558f + newangle2 * 3.1415927f * 0.1f * f1;
        this.RThigh.z = 2.0f + clawZamp * newangle * f1 / 4.0f;
        this.LToe1.xRot = 0.0f;
        this.LToe9.xRot = 0.0f;
        this.LToe8.xRot = 0.0f;
        this.LToe7.xRot = 0.0f;
        this.LToe6.xRot = 0.0f;
        this.LToe5.xRot = 0.0f;
        this.LToe4.xRot = 0.0f;
        this.LToe3.xRot = 0.0f;
        this.LToe2.xRot = 0.0f;
        this.RToe1.xRot = 0.0f;
        this.RToe9.xRot = 0.0f;
        this.RToe8.xRot = 0.0f;
        this.RToe7.xRot = 0.0f;
        this.RToe6.xRot = 0.0f;
        this.RToe5.xRot = 0.0f;
        this.RToe4.xRot = 0.0f;
        this.RToe3.xRot = 0.0f;
        this.RToe2.xRot = 0.0f;
        newangle = e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * this.wingspeed * 1.75f)) * 3.1415927f * 0.2f : MathHelper.cos((float)(f2 * this.wingspeed * 0.75f)) * 3.1415927f * 0.05f;
        this.doTail(newangle);
        this.Head.yRot = newangle = (float)Math.toRadians(f3) * 0.55f;
        this.TopJaw.yRot = newangle;
        this.LowerJaw.yRot = newangle;
        this.LowerJaw.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 11.0f;
        this.LowerJaw.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 11.0f;
        this.TopJaw.xRot = this.Head.xRot = (float)Math.toRadians(f4);
        newangle = MathHelper.cos((float)(f2 * this.wingspeed * 1.5f)) * 3.1415927f * 0.12f;
        float newrf1 = f2 * 1.5f * this.wingspeed % 6.2831855f;
        newrf1 = Math.abs(newrf1);
        if (newrf1 < r.rf2) {
            r.ri2 = 0;
            if (e.getAttacking() == 0) {
                if (e.level.random.nextInt(20) == 1) {
                    r.ri2 |= 1;
                }
            } else if (e.level.random.nextInt(2) == 1) {
                r.ri2 |= 1;
            }
        }
        r.rf2 = newrf1;
        if ((r.ri2 & 1) == 0) {
            newangle = 0.0f;
        }
        this.LowerJaw.xRot = 0.52f + newangle + this.TopJaw.xRot;
        newangle = newangle2 = MathHelper.sin((float)(f2 * this.wingspeed * 1.75f)) * 3.1415927f * 0.16f;
        newrf1 = f2 * 1.75f * this.wingspeed % 6.2831855f;
        if ((newrf1 = Math.abs(newrf1)) < r.rf1) {
            r.ri1 = 0;
            if (e.getAttacking() == 0) {
                if (e.level.random.nextInt(20) == 1) {
                    r.ri1 |= 1;
                }
                if (e.level.random.nextInt(20) == 1) {
                    r.ri1 |= 2;
                }
            } else {
                if (e.level.random.nextInt(2) == 1) {
                    r.ri1 |= 1;
                }
                if (e.level.random.nextInt(2) == 1) {
                    r.ri1 |= 2;
                }
            }
        }
        r.rf1 = newrf1;
        if ((r.ri1 & 1) == 0) {
            newangle = 0.0f;
        }
        if ((r.ri1 & 2) == 0) {
            newangle2 = 0.0f;
        }
        this.LUpperArm.yRot = 0.65f + newangle;
        this.LLowerArm.yRot = 0.78f + newangle * 3.0f / 2.0f;
        this.LLowerArm.z = this.LUpperArm.z - (float)Math.sin(this.LUpperArm.yRot) * 50.0f;
        this.LLowerArm.x = this.LUpperArm.x + (float)Math.cos(this.LUpperArm.yRot) * 50.0f;
        this.LLowerArm.y = this.LUpperArm.y - (float)Math.sin(this.LUpperArm.yRot) * 10.0f + 18.0f;
        this.LHand.z = this.LLowerArm.z - (float)Math.sin(this.LLowerArm.yRot) * 45.0f;
        this.LHand.x = this.LLowerArm.x + (float)Math.cos(this.LLowerArm.yRot) * 45.0f;
        this.LHand.y = this.LLowerArm.y - (float)Math.sin(this.LLowerArm.yRot) * 10.0f + 15.0f;
        this.LThumbBase.z = this.L3rdFingerBase.z = this.LHand.z;
        this.LIndexBase.z = this.L3rdFingerBase.z;
        this.LThumbTip.z = this.L3rdFingerTip.z = this.LHand.z;
        this.LIndexTip.z = this.L3rdFingerTip.z;
        this.LThumbBase.y = this.L3rdFingerBase.y = this.LHand.y;
        this.LIndexBase.y = this.L3rdFingerBase.y;
        this.LThumbTip.y = this.L3rdFingerTip.y = this.LHand.y;
        this.LIndexTip.y = this.L3rdFingerTip.y;
        this.LThumbBase.x = this.L3rdFingerBase.x = this.LHand.x;
        this.LIndexBase.x = this.L3rdFingerBase.x;
        this.LThumbTip.x = this.L3rdFingerTip.x = this.LHand.x;
        this.LIndexTip.x = this.L3rdFingerTip.x;
        this.LHand.yRot = 1.308f + newangle * 2.0f;
        this.LIndexBase.yRot = -0.139f + newangle * 2.0f;
        this.LIndexTip.yRot = -0.034f + newangle * 2.0f;
        this.LThumbBase.yRot = 0.261f + newangle;
        this.LThumbTip.yRot = 0.139f + newangle;
        this.L3rdFingerBase.yRot = -0.471f + newangle * 3.0f;
        this.L3rdFingerTip.yRot = -0.331f + newangle * 3.0f;
        this.RUpperArm.yRot = -0.65f - newangle2;
        this.RLowerArm.yRot = -0.78f - newangle2 * 3.0f / 2.0f;
        this.RLowerArm.z = this.RUpperArm.z + (float)Math.sin(this.RUpperArm.yRot) * 50.0f;
        this.RLowerArm.x = this.RUpperArm.x - (float)Math.cos(this.RUpperArm.yRot) * 50.0f;
        this.RLowerArm.y = this.RUpperArm.y + (float)Math.sin(this.RUpperArm.yRot) * 10.0f + 18.0f;
        this.RHand.z = this.RLowerArm.z + (float)Math.sin(this.RLowerArm.yRot) * 45.0f;
        this.RHand.x = this.RLowerArm.x - (float)Math.cos(this.RLowerArm.yRot) * 45.0f;
        this.RHand.y = this.RLowerArm.y + (float)Math.sin(this.RLowerArm.yRot) * 10.0f + 15.0f;
        this.RThumbBase.z = this.R3rdFingerBase.z = this.RHand.z;
        this.RIndexBase.z = this.R3rdFingerBase.z;
        this.RThumbTip.z = this.R3rdFingerTip.z = this.RHand.z;
        this.RIndexTip.z = this.R3rdFingerTip.z;
        this.RThumbBase.y = this.R3rdFingerBase.y = this.RHand.y;
        this.RIndexBase.y = this.R3rdFingerBase.y;
        this.RThumbTip.y = this.R3rdFingerTip.y = this.RHand.y;
        this.RIndexTip.y = this.R3rdFingerTip.y;
        this.RThumbBase.x = this.R3rdFingerBase.x = this.RHand.x;
        this.RIndexBase.x = this.R3rdFingerBase.x;
        this.RThumbTip.x = this.R3rdFingerTip.x = this.RHand.x;
        this.RIndexTip.x = this.R3rdFingerTip.x;
        this.RHand.yRot = -2.0f - newangle2 * 2.0f;
        this.RIndexBase.yRot = 0.157f - newangle2 * 2.0f;
        this.RIndexTip.yRot = 0.174f - newangle2 * 2.0f;
        this.RThumbBase.yRot = -0.104f - newangle2;
        this.RThumbTip.yRot = 0.001f - newangle2;
        this.R3rdFingerTip.yRot = 0.68f - newangle2 * 3.0f;
        this.R3rdFingerBase.yRot = 0.645f - newangle2 * 3.0f;
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.LToe1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LThigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LLowerLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LUpperLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RLegLower.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RLegUpper.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RThigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LowerJaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyBottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RLowerArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyCenter.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopJaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RShoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RThumbTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RUpperArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RHand.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RThumbBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.R3rdFingerTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.R3rdFingerBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RIndexTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RIndexBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LShoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LUpperArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LLowerArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LIndexBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LIndexTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LHand.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LThumbBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LThumbTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.L3rdFingerTip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.L3rdFingerBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspikes1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspikes1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spikes7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Godzilla par7Entity) {
        
    }

    private void doTail(float angle) {
        this.Lspike5.yRot = this.Rspike5.yRot = (this.TailBase.yRot = angle * 0.25f);
        this.Tail2.yRot = angle * 0.5f;
        this.Tail2.z = this.TailBase.z + (float)Math.cos(this.TailBase.yRot) * 25.0f;
        this.Tail2.x = this.TailBase.x + (float)Math.sin(this.TailBase.yRot) * 25.0f;
        this.Spike6.yRot = this.Tail2.yRot;
        this.Spike6.z = this.Tail2.z;
        this.Spike6.x = this.Tail2.x;
        this.Tail3.yRot = angle * 0.75f;
        this.Tail3.z = this.Tail2.z + (float)Math.cos(this.Tail2.yRot) * 20.0f;
        this.Tail3.x = this.Tail2.x + (float)Math.sin(this.Tail2.yRot) * 20.0f;
        this.Spikes7.yRot = this.Tail3.yRot;
        this.Spikes7.z = this.Tail3.z;
        this.Spikes7.x = this.Tail3.x;
        this.Tail4.yRot = angle * 1.25f;
        this.Tail4.z = this.Tail3.z + (float)Math.cos(this.Tail3.yRot) * 20.0f;
        this.Tail4.x = this.Tail3.x + (float)Math.sin(this.Tail3.yRot) * 20.0f;
        this.Tail5.yRot = angle * 1.5f;
        this.Tail5.z = this.Tail4.z + (float)Math.cos(this.Tail4.yRot) * 25.0f;
        this.Tail5.x = this.Tail4.x + (float)Math.sin(this.Tail4.yRot) * 25.0f;
        this.Tail6.yRot = angle * 1.75f;
        this.Tail6.z = this.Tail5.z + (float)Math.cos(this.Tail5.yRot) * 27.0f;
        this.Tail6.x = this.Tail5.x + (float)Math.sin(this.Tail5.yRot) * 27.0f;
        this.Tail7.yRot = angle * 2.0f;
        this.Tail7.z = this.Tail6.z + (float)Math.cos(this.Tail6.yRot) * 28.0f;
        this.Tail7.x = this.Tail6.x + (float)Math.sin(this.Tail6.yRot) * 28.0f;
        this.TailTip.yRot = angle * 2.25f;
        this.TailTip.z = this.Tail7.z + (float)Math.cos(this.Tail7.yRot) * 18.0f;
        this.TailTip.x = this.Tail7.x + (float)Math.sin(this.Tail7.yRot) * 18.0f;
    }
}

