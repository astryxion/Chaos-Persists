/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Alien
 *  com.astryxion.chaospersists.ModelAlien
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

import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelAlien extends EntityModel<Alien> {
    private float wingspeed = 1.0f;
    ModelRenderer torso;
    ModelRenderer stomach;
    ModelRenderer rThigh;
    ModelRenderer lThigh;
    ModelRenderer lShin;
    ModelRenderer rShin;
    ModelRenderer lShin1;
    ModelRenderer rShin1;
    ModelRenderer lFoot;
    ModelRenderer rFoot;
    ModelRenderer neck;
    ModelRenderer fan;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tail5;
    ModelRenderer tail1;
    ModelRenderer fanl1;
    ModelRenderer fanr1;
    ModelRenderer fanl2;
    ModelRenderer fanr2;
    ModelRenderer fanl3;
    ModelRenderer fanr3;
    ModelRenderer fanl4;
    ModelRenderer fanr4;
    ModelRenderer fanl5;
    ModelRenderer fanr5;
    ModelRenderer fanl6;
    ModelRenderer fanr6;
    ModelRenderer spike4;
    ModelRenderer spike5;
    ModelRenderer spike3;
    ModelRenderer fanl7;
    ModelRenderer fanr7;
    ModelRenderer head;
    ModelRenderer head1;
    ModelRenderer jaw1;
    ModelRenderer head2;
    ModelRenderer jaw2;
    ModelRenderer fang1;
    ModelRenderer fang2;
    ModelRenderer fang3;
    ModelRenderer fang4;
    ModelRenderer spike2;
    ModelRenderer spike1;
    ModelRenderer arml1;
    ModelRenderer armr1;
    ModelRenderer arml2;
    ModelRenderer armr2;
    ModelRenderer clawr1;
    ModelRenderer clawr2;
    ModelRenderer clawr3;
    ModelRenderer clawl2;
    ModelRenderer clawl3;
    ModelRenderer clawl1;

    public ModelAlien(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 128;
        this.torso = new ModelRenderer(this, 0, 46);
        this.torso.addBox(-4.5f, -2.0f, 0.0f, 9, 8, 10);
        this.torso.setPos(0.0f, -2.5f, -8.0f);
        this.torso.mirror = true;
        this.setRotation(this.torso, -0.1919862f, 0.0f, 0.0f);
        this.stomach = new ModelRenderer(this, 0, 27);
        this.stomach.addBox(-3.5f, -5.0f, 8.0f, 7, 6, 12);
        this.stomach.setPos(0.0f, -2.5f, -8.0f);
        this.stomach.mirror = true;
        this.setRotation(this.stomach, -0.5585054f, 0.0f, 0.0f);
        this.rThigh = new ModelRenderer(this, 59, 45);
        this.rThigh.addBox(-1.5f, -4.0f, -2.5f, 4, 14, 5);
        this.rThigh.setPos(-4.5f, 7.0f, 8.0f);
        this.rThigh.mirror = true;
        this.setRotation(this.rThigh, -0.8028515f, 0.2443461f, 0.418879f);
        this.lThigh = new ModelRenderer(this, 40, 45);
        this.lThigh.addBox(-2.5f, -4.0f, -2.5f, 4, 14, 5);
        this.lThigh.setPos(4.5f, 7.0f, 8.0f);
        this.lThigh.mirror = true;
        this.setRotation(this.lThigh, -0.8028515f, -0.2443461f, -0.418879f);
        this.lShin = new ModelRenderer(this, 79, 49);
        this.lShin.addBox(-2.0f, 8.0f, -5.5f, 3, 3, 12);
        this.lShin.setPos(4.5f, 7.0f, 8.0f);
        this.lShin.mirror = true;
        this.setRotation(this.lShin, -0.4014257f, -0.2443461f, -0.418879f);
        this.rShin = new ModelRenderer(this, 79, 33);
        this.rShin.addBox(-1.0f, 8.0f, -5.5f, 3, 3, 12);
        this.rShin.setPos(-4.5f, 7.0f, 8.0f);
        this.rShin.mirror = true;
        this.setRotation(this.rShin, -0.4014257f, 0.2443461f, 0.418879f);
        this.lShin1 = new ModelRenderer(this, 113, 40);
        this.lShin1.addBox(-1.5f, 5.5f, 9.0f, 2, 9, 2);
        this.lShin1.setPos(4.5f, 7.0f, 8.0f);
        this.lShin1.mirror = true;
        this.setRotation(this.lShin1, -0.8028515f, -0.2443461f, -0.418879f);
        this.rShin1 = new ModelRenderer(this, 113, 53);
        this.rShin1.addBox(-0.5f, 5.5f, 9.0f, 2, 9, 2);
        this.rShin1.setPos(-4.5f, 7.0f, 8.0f);
        this.rShin1.mirror = true;
        this.setRotation(this.rShin1, -0.8028515f, 0.2443461f, 0.418879f);
        this.lFoot = new ModelRenderer(this, 110, 24);
        this.lFoot.addBox(5.0f, 15.0f, -8.0f, 2, 2, 6);
        this.lFoot.setPos(4.5f, 7.0f, 8.0f);
        this.lFoot.mirror = true;
        this.setRotation(this.lFoot, 0.0f, -0.2443461f, 0.0f);
        this.rFoot = new ModelRenderer(this, 95, 24);
        this.rFoot.addBox(-7.0f, 15.0f, -8.0f, 2, 2, 6);
        this.rFoot.setPos(-4.5f, 7.0f, 8.0f);
        this.rFoot.mirror = true;
        this.setRotation(this.rFoot, 0.0f, 0.2443461f, 0.0f);
        this.neck = new ModelRenderer(this, 23, 86);
        this.neck.addBox(-2.0f, -2.0f, -4.0f, 4, 6, 5);
        this.neck.setPos(0.0f, -2.5f, -8.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, -0.1919862f, 0.0f, 0.0f);
        this.fan = new ModelRenderer(this, 149, 10);
        this.fan.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fan.setPos(0.0f, -7.0f, -10.0f);
        this.fan.mirror = true;
        this.setRotation(this.fan, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 85, 66);
        this.tail2.addBox(-2.0f, -1.5f, 0.0f, 4, 4, 11);
        this.tail2.setPos(0.0f, 9.5f, 20.5f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, -0.3141593f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 118, 66);
        this.tail3.addBox(-1.5f, -1.5f, 0.0f, 3, 3, 11);
        this.tail3.setPos(0.0f, 13.5f, 30.5f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, -0.2094395f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 149, 66);
        this.tail4.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 11);
        this.tail4.setPos(0.0f, 15.5f, 40.5f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, -0.1396263f, 0.0f, 0.0f);
        this.tail5 = new ModelRenderer(this, 178, 66);
        this.tail5.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 11);
        this.tail5.setPos(0.0f, 17.5f, 50.5f);
        this.tail5.mirror = true;
        this.setRotation(this.tail5, -0.0523599f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 50, 66);
        this.tail1.addBox(-2.0f, -2.5f, 0.0f, 4, 4, 11);
        this.tail1.setPos(0.0f, 6.5f, 10.5f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, -0.4014257f, 0.0f, 0.0f);
        this.fanl1 = new ModelRenderer(this, 130, 10);
        this.fanl1.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanl1.setPos(0.0f, -7.0f, -10.0f);
        this.fanl1.mirror = true;
        this.setRotation(this.fanl1, 0.0f, 0.0f, 0.2617994f);
        this.fanr1 = new ModelRenderer(this, 130, 10);
        this.fanr1.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanr1.setPos(0.0f, -7.0f, -10.0f);
        this.fanr1.mirror = true;
        this.setRotation(this.fanr1, 0.0f, 0.0f, -0.2617994f);
        this.fanl2 = new ModelRenderer(this, 130, 10);
        this.fanl2.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanl2.setPos(0.0f, -7.0f, -10.0f);
        this.fanl2.mirror = true;
        this.setRotation(this.fanl2, 0.0f, 0.0f, 0.5235988f);
        this.fanr2 = new ModelRenderer(this, 130, 10);
        this.fanr2.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanr2.setPos(0.0f, -7.0f, -10.0f);
        this.fanr2.mirror = true;
        this.setRotation(this.fanr2, 0.0f, 0.0f, -0.5235988f);
        this.fanl3 = new ModelRenderer(this, 130, 10);
        this.fanl3.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanl3.setPos(0.0f, -7.0f, -10.0f);
        this.fanl3.mirror = true;
        this.setRotation(this.fanl3, 0.0f, 0.0f, 0.7853982f);
        this.fanr3 = new ModelRenderer(this, 130, 10);
        this.fanr3.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanr3.setPos(0.0f, -7.0f, -10.0f);
        this.fanr3.mirror = true;
        this.setRotation(this.fanr3, 0.0f, 0.0f, -0.7853982f);
        this.fanl4 = new ModelRenderer(this, 130, 10);
        this.fanl4.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanl4.setPos(0.0f, -7.0f, -10.0f);
        this.fanl4.mirror = true;
        this.setRotation(this.fanl4, 0.0f, 0.0f, 1.047198f);
        this.fanr4 = new ModelRenderer(this, 130, 10);
        this.fanr4.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanr4.setPos(0.0f, -7.0f, -10.0f);
        this.fanr4.mirror = true;
        this.setRotation(this.fanr4, 0.0f, 0.0f, -1.047198f);
        this.fanl5 = new ModelRenderer(this, 130, 10);
        this.fanl5.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanl5.setPos(0.0f, -7.0f, -10.0f);
        this.fanl5.mirror = true;
        this.setRotation(this.fanl5, 0.0f, 0.0f, 1.308997f);
        this.fanr5 = new ModelRenderer(this, 130, 10);
        this.fanr5.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanr5.setPos(0.0f, -7.0f, -10.0f);
        this.fanr5.mirror = true;
        this.setRotation(this.fanr5, 0.0f, 0.0f, -1.308997f);
        this.fanl6 = new ModelRenderer(this, 130, 10);
        this.fanl6.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanl6.setPos(0.0f, -7.0f, -10.0f);
        this.fanl6.mirror = true;
        this.setRotation(this.fanl6, 0.0f, 0.0f, 1.570796f);
        this.fanr6 = new ModelRenderer(this, 130, 10);
        this.fanr6.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanr6.setPos(0.0f, -7.0f, -10.0f);
        this.fanr6.mirror = true;
        this.setRotation(this.fanr6, 0.0f, 0.0f, -1.570796f);
        this.spike4 = new ModelRenderer(this, 178, 66);
        this.spike4.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 11);
        this.spike4.setPos(0.0f, 16.0f, 41.0f);
        this.spike4.mirror = true;
        this.setRotation(this.spike4, -0.0523599f, 0.5235988f, 0.0f);
        this.spike5 = new ModelRenderer(this, 178, 66);
        this.spike5.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 11);
        this.spike5.setPos(0.0f, 16.0f, 41.0f);
        this.spike5.mirror = true;
        this.setRotation(this.spike5, -0.0523599f, -0.5759587f, 0.0f);
        this.spike3 = new ModelRenderer(this, 178, 66);
        this.spike3.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 11);
        this.spike3.setPos(0.0f, 13.5f, 30.5f);
        this.spike3.mirror = true;
        this.setRotation(this.spike3, 0.3141593f, 0.0f, 0.0f);
        this.fanl7 = new ModelRenderer(this, 130, 10);
        this.fanl7.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanl7.setPos(0.0f, -7.0f, -10.0f);
        this.fanl7.mirror = true;
        this.setRotation(this.fanl7, 0.0f, 0.0f, 1.832596f);
        this.fanr7 = new ModelRenderer(this, 130, 10);
        this.fanr7.addBox(-3.0f, -24.0f, 0.0f, 6, 24, 1);
        this.fanr7.setPos(0.0f, -7.0f, -10.0f);
        this.fanr7.mirror = true;
        this.setRotation(this.fanr7, 0.0f, 0.0f, -1.832596f);
        this.head = new ModelRenderer(this, 200, 0);
        this.head.addBox(-3.0f, -4.0f, -7.0f, 6, 7, 8);
        this.head.setPos(0.0f, -3.0f, -11.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 200, 18);
        this.head1.addBox(-2.5f, -2.0f, -15.0f, 5, 2, 8);
        this.head1.setPos(0.0f, -3.0f, -11.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.jaw1 = new ModelRenderer(this, 200, 43);
        this.jaw1.addBox(-2.0f, -1.0f, -7.0f, 4, 2, 8);
        this.jaw1.setPos(0.0f, -2.0f, -19.0f);
        this.jaw1.mirror = true;
        this.setRotation(this.jaw1, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 200, 31);
        this.head2.addBox(-2.0f, -2.0f, -22.0f, 4, 2, 7);
        this.head2.setPos(0.0f, -3.0f, -11.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.jaw2 = new ModelRenderer(this, 200, 56);
        this.jaw2.addBox(-1.5f, -1.0f, -13.0f, 3, 2, 6);
        this.jaw2.setPos(0.0f, -2.0f, -19.0f);
        this.jaw2.mirror = true;
        this.setRotation(this.jaw2, 0.0f, 0.0f, 0.0f);
        this.fang1 = new ModelRenderer(this, 42, 0);
        this.fang1.addBox(1.0f, 0.0f, -20.0f, 1, 5, 1);
        this.fang1.setPos(0.0f, -3.0f, -11.0f);
        this.fang1.mirror = true;
        this.setRotation(this.fang1, 0.0f, 0.0f, 0.0f);
        this.fang2 = new ModelRenderer(this, 50, 0);
        this.fang2.addBox(-2.0f, 0.0f, -20.0f, 1, 5, 1);
        this.fang2.setPos(0.0f, -3.0f, -11.0f);
        this.fang2.mirror = true;
        this.setRotation(this.fang2, 0.0f, 0.0f, 0.0f);
        this.fang3 = new ModelRenderer(this, 60, 0);
        this.fang3.addBox(1.0f, 0.0f, -14.0f, 1, 3, 1);
        this.fang3.setPos(0.0f, -3.0f, -11.0f);
        this.fang3.mirror = true;
        this.setRotation(this.fang3, 0.0f, 0.0f, 0.0f);
        this.fang4 = new ModelRenderer(this, 69, 0);
        this.fang4.addBox(-2.0f, 0.0f, -14.0f, 1, 3, 1);
        this.fang4.setPos(0.0f, -3.0f, -11.0f);
        this.fang4.mirror = true;
        this.setRotation(this.fang4, 0.0f, 0.0f, 0.0f);
        this.spike2 = new ModelRenderer(this, 178, 66);
        this.spike2.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 11);
        this.spike2.setPos(0.0f, 9.5f, 20.5f);
        this.spike2.mirror = true;
        this.setRotation(this.spike2, 0.3141593f, 0.0f, 0.0f);
        this.spike1 = new ModelRenderer(this, 178, 66);
        this.spike1.addBox(-0.5f, -1.5f, 0.0f, 1, 1, 11);
        this.spike1.setPos(0.0f, 6.5f, 10.5f);
        this.spike1.mirror = true;
        this.setRotation(this.spike1, 0.3141593f, 0.0f, 0.0f);
        this.arml1 = new ModelRenderer(this, 50, 98);
        this.arml1.addBox(0.0f, 0.0f, -2.0f, 11, 3, 4);
        this.arml1.setPos(2.0f, -1.0f, -6.0f);
        this.arml1.mirror = true;
        this.setRotation(this.arml1, 0.0f, -0.5235988f, 0.1745329f);
        this.armr1 = new ModelRenderer(this, 49, 88);
        this.armr1.addBox(0.0f, 0.0f, -2.0f, 11, 3, 4);
        this.armr1.setPos(-3.0f, -1.0f, -6.0f);
        this.armr1.mirror = true;
        this.setRotation(this.armr1, 0.0f, -2.617994f, -0.1745329f);
        this.arml2 = new ModelRenderer(this, 41, 107);
        this.arml2.addBox(0.0f, -1.0f, -1.0f, 15, 3, 3);
        this.arml2.setPos(11.0f, 2.0f, -1.0f);
        this.arml2.mirror = true;
        this.setRotation(this.arml2, 0.0f, 0.8552113f, 0.0f);
        this.armr2 = new ModelRenderer(this, 42, 115);
        this.armr2.addBox(0.0f, -1.0f, -2.0f, 15, 3, 3);
        this.armr2.setPos(-11.0f, 2.0f, -1.0f);
        this.armr2.mirror = true;
        this.setRotation(this.armr2, 0.0f, 2.268928f, 0.0f);
        this.clawr1 = new ModelRenderer(this, 100, 85);
        this.clawr1.addBox(-0.5f, -1.0f, -6.0f, 1, 1, 6);
        this.clawr1.setPos(-21.0f, 2.0f, -12.0f);
        this.clawr1.mirror = true;
        this.setRotation(this.clawr1, -0.1745329f, 0.4363323f, 0.0f);
        this.clawr2 = new ModelRenderer(this, 100, 94);
        this.clawr2.addBox(0.0f, 0.0f, -10.0f, 1, 1, 10);
        this.clawr2.setPos(-21.0f, 2.0f, -12.0f);
        this.clawr2.mirror = true;
        this.setRotation(this.clawr2, 0.0f, 0.8726646f, 0.0f);
        this.clawr3 = new ModelRenderer(this, 100, 107);
        this.clawr3.addBox(0.0f, 1.0f, -6.0f, 1, 1, 6);
        this.clawr3.setPos(-21.0f, 2.0f, -12.0f);
        this.clawr3.mirror = true;
        this.setRotation(this.clawr3, 0.1745329f, 0.4363323f, 0.0f);
        this.clawl2 = new ModelRenderer(this, 130, 94);
        this.clawl2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 10);
        this.clawl2.setPos(21.0f, 2.0f, -12.0f);
        this.clawl2.mirror = true;
        this.setRotation(this.clawl2, 0.0f, 2.268928f, 0.0f);
        this.clawl3 = new ModelRenderer(this, 130, 109);
        this.clawl3.addBox(0.0f, 1.0f, 0.0f, 1, 1, 6);
        this.clawl3.setPos(21.0f, 2.0f, -12.0f);
        this.clawl3.mirror = true;
        this.setRotation(this.clawl3, -0.1745329f, 2.70526f, 0.0f);
        this.clawl1 = new ModelRenderer(this, 130, 83);
        this.clawl1.addBox(0.0f, -1.0f, 0.0f, 1, 1, 6);
        this.clawl1.setPos(21.0f, 2.0f, -12.0f);
        this.clawl1.mirror = true;
        this.setRotation(this.clawl1, 0.1745329f, 2.70526f, 0.0f);
    }
    @Override
    public void setupAnim(Alien entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Alien e = (Alien)entity;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float nextangle = 0.0f;
        newangle = MathHelper.cos((float)(f2 * 4.0f * this.wingspeed)) * 3.1415927f * 0.5f * f1;
        this.doLeftLeg(newangle);
        this.doRightLeg(- newangle);
        if (e.getAttacking() == 0) {
            this.fan.zRot = 0.0f;
            this.fanl1.zRot = 0.0f;
            this.fanl2.zRot = 0.0f;
            this.fanl3.zRot = 0.0f;
            this.fanl4.zRot = 0.0f;
            this.fanl5.zRot = 0.0f;
            this.fanl6.zRot = 0.0f;
            this.fanl7.zRot = 0.0f;
            this.fanr1.zRot = 0.0f;
            this.fanr2.zRot = 0.0f;
            this.fanr3.zRot = 0.0f;
            this.fanr4.zRot = 0.0f;
            this.fanr5.zRot = 0.0f;
            this.fanr6.zRot = 0.0f;
            this.fanr7.zRot = 0.0f;
            this.fan.xRot = -1.85f;
            this.fanl1.xRot = -1.85f;
            this.fanl2.xRot = -1.85f;
            this.fanl3.xRot = -1.85f;
            this.fanl4.xRot = -1.85f;
            this.fanl5.xRot = -1.85f;
            this.fanl6.xRot = -1.85f;
            this.fanl7.xRot = -1.85f;
            this.fanr1.xRot = -1.85f;
            this.fanr2.xRot = -1.85f;
            this.fanr3.xRot = -1.85f;
            this.fanr4.xRot = -1.85f;
            this.fanr5.xRot = -1.85f;
            this.fanr6.xRot = -1.85f;
            this.fanr7.xRot = -1.85f;
        } else {
            float pi6 = 0.5235988f;
            float fanspeed = 1.22f;
            float fanamp = 0.1f;
            this.fan.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed)) * 3.1415927f * fanamp;
            this.fanl1.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 1.0f * pi6)) * 3.1415927f * fanamp;
            this.fanl2.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 2.0f * pi6)) * 3.1415927f * fanamp;
            this.fanl3.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 3.0f * pi6)) * 3.1415927f * fanamp;
            this.fanl4.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 4.0f * pi6)) * 3.1415927f * fanamp;
            this.fanl5.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 5.0f * pi6)) * 3.1415927f * fanamp;
            this.fanl6.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 6.0f * pi6)) * 3.1415927f * fanamp;
            this.fanl7.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 7.0f * pi6)) * 3.1415927f * fanamp;
            this.fanr1.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 1.0f * pi6)) * 3.1415927f * fanamp;
            this.fanr2.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 2.0f * pi6)) * 3.1415927f * fanamp;
            this.fanr3.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 3.0f * pi6)) * 3.1415927f * fanamp;
            this.fanr4.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 4.0f * pi6)) * 3.1415927f * fanamp;
            this.fanr5.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 5.0f * pi6)) * 3.1415927f * fanamp;
            this.fanr6.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 6.0f * pi6)) * 3.1415927f * fanamp;
            this.fanr7.xRot = MathHelper.cos((float)(f2 * fanspeed * this.wingspeed - 7.0f * pi6)) * 3.1415927f * fanamp;
            this.fan.zRot = 0.0f;
            this.fanl1.zRot = 0.261f;
            this.fanl2.zRot = 0.523f;
            this.fanl3.zRot = 0.785f;
            this.fanl4.zRot = 1.047f;
            this.fanl5.zRot = 1.309f;
            this.fanl6.zRot = 1.571f;
            this.fanl7.zRot = 1.832f;
            this.fanr1.zRot = -0.261f;
            this.fanr2.zRot = -0.523f;
            this.fanr3.zRot = -0.785f;
            this.fanr4.zRot = -1.047f;
            this.fanr5.zRot = -1.309f;
            this.fanr6.zRot = -1.571f;
            this.fanr7.zRot = -1.832f;
        }
        this.neck.yRot = (float)Math.toRadians(f3) * 0.35f;
        this.head.yRot = (float)Math.toRadians(f3) * 0.75f;
        this.head.z = this.neck.z - (float)Math.cos(this.neck.yRot) * 3.0f;
        this.head.x = this.neck.x + (float)Math.sin(this.neck.yRot) * 3.0f;
        this.head1.yRot = this.head.yRot;
        this.head1.z = this.head.z;
        this.head1.x = this.head.x;
        this.head2.yRot = this.head.yRot;
        this.head2.z = this.head.z;
        this.head2.x = this.head.x;
        this.fang1.yRot = this.head.yRot;
        this.fang1.z = this.head.z;
        this.fang1.x = this.head.x;
        this.fang2.yRot = this.head.yRot;
        this.fang2.z = this.head.z;
        this.fang2.x = this.head.x;
        this.fang3.yRot = this.head.yRot;
        this.fang3.z = this.head.z;
        this.fang3.x = this.head.x;
        this.fang4.yRot = this.head.yRot;
        this.fang4.z = this.head.z;
        this.fang4.x = this.head.x;
        this.jaw1.yRot = this.head.yRot;
        this.jaw1.z = this.head.z - (float)Math.cos(this.head.yRot) * 8.0f;
        this.jaw1.x = this.head.x - (float)Math.sin(this.head.yRot) * 8.0f;
        this.jaw2.yRot = this.jaw1.yRot;
        this.jaw2.z = this.jaw1.z;
        this.jaw2.x = this.jaw1.x;
        r = e.getRenderInfo();
        newangle = MathHelper.cos((float)(f2 * 3.5f * this.wingspeed)) * 3.1415927f * 0.5f;
        nextangle = MathHelper.cos((float)((f2 + 0.2f) * 3.5f * this.wingspeed)) * 3.1415927f * 0.5f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            if (e.getAttacking() == 0) {
                r.ri1 = e.level.random.nextInt(15);
                r.ri2 = e.level.random.nextInt(15);
                r.ri3 = e.level.random.nextInt(15);
            } else {
                r.ri1 = e.level.random.nextInt(4);
                r.ri2 = e.level.random.nextInt(2);
                r.ri3 = 1;
            }
        }
        if (r.ri2 == 1) {
            this.doTail(newangle);
        } else {
            newangle = MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.05f;
            this.doTail(newangle);
        }
        if (r.ri3 == 1) {
            newangle = MathHelper.cos((float)(f2 * 3.5f * this.wingspeed)) * 3.1415927f * 0.35f;
            this.doJaw(newangle);
        } else {
            newangle = MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.02f;
            this.doJaw(newangle);
        }
        newangle = MathHelper.cos((float)(f2 * this.wingspeed * 3.5f)) * 3.1415927f * 0.2f;
        if (r.ri1 == 1 || r.ri1 == 3) {
            this.doLeftClaw(newangle);
        } else {
            newangle = MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.03f;
            this.doLeftClaw(newangle);
        }
        if (r.ri1 == 2 || r.ri1 == 3) {
            this.doRightClaw(- newangle);
        } else {
            newangle = MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.03f;
            this.doRightClaw(- newangle);
        }
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.stomach.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rThigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lThigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lShin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rShin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lShin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rShin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fang1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fang2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fang3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fang4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arml1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armr1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.arml2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armr2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.clawr1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.clawr2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.clawr3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.clawl2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.clawl3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.clawl1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fan.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanl1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanr1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanl2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanr2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanl3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanr3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanl4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanr4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanl5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanr5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanl6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanr6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanl7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fanr7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Alien par7Entity) {
        
    }

    private void doLeftLeg(float angle) {
        this.lFoot.xRot = angle;
        this.lShin.xRot = angle - 0.4f;
        this.lShin1.xRot = angle - 0.8f;
        this.lThigh.xRot = angle - 0.8f;
    }

    private void doRightLeg(float angle) {
        this.rFoot.xRot = angle;
        this.rShin.xRot = angle - 0.4f;
        this.rShin1.xRot = angle - 0.8f;
        this.rThigh.xRot = angle - 0.8f;
    }

    private void doJaw(float angle) {
        this.jaw2.xRot = this.jaw1.xRot = Math.abs(angle);
    }

    private void doTail(float angle) {
        this.spike1.yRot = this.tail1.yRot = angle * 0.25f;
        this.tail2.yRot = angle * 0.5f;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 10.0f;
        this.tail2.x = this.tail1.x + (float)Math.sin(this.tail1.yRot) * 10.0f;
        this.spike2.yRot = this.tail2.yRot;
        this.spike2.z = this.tail2.z;
        this.spike2.x = this.tail2.x;
        this.tail3.yRot = angle * 0.8f;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 10.0f;
        this.tail3.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 10.0f;
        this.spike3.yRot = this.tail3.yRot;
        this.spike3.z = this.tail3.z;
        this.spike3.x = this.tail3.x;
        this.tail4.yRot = angle * 1.25f;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 10.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 10.0f;
        this.spike4.yRot = this.tail4.yRot + 0.52f;
        this.spike4.z = this.tail4.z;
        this.spike4.x = this.tail4.x;
        this.spike5.yRot = this.tail4.yRot - 0.52f;
        this.spike5.z = this.tail4.z;
        this.spike5.x = this.tail4.x;
        this.tail5.yRot = angle * 1.5f;
        this.tail5.z = this.tail4.z + (float)Math.cos(this.tail4.yRot) * 10.0f;
        this.tail5.x = this.tail4.x + (float)Math.sin(this.tail4.yRot) * 10.0f;
    }

    private void doLeftClaw(float angle) {
        this.arml1.yRot = -0.52f + Math.abs(angle * 2.0f);
        this.arml2.z = this.arml1.z - (float)Math.sin(this.arml1.yRot) * 9.0f;
        this.arml2.x = this.arml1.x + (float)Math.cos(this.arml1.yRot) * 9.0f;
        this.arml2.yRot = 0.855f + Math.abs(angle);
        this.clawl1.z = this.arml2.z - (float)Math.sin(this.arml2.yRot) * 14.0f;
        this.clawl1.x = this.arml2.x + (float)Math.cos(this.arml2.yRot) * 14.0f;
        this.clawl1.yRot = 2.7f + Math.abs(angle * 4.0f);
        this.clawl2.z = this.clawl1.z;
        this.clawl2.x = this.clawl1.x;
        this.clawl2.yRot = 2.27f + Math.abs(angle * 4.0f);
        this.clawl3.z = this.clawl1.z;
        this.clawl3.x = this.clawl1.x;
        this.clawl3.yRot = 2.7f + Math.abs(angle * 4.0f);
    }

    private void doRightClaw(float angle) {
        this.armr1.yRot = -2.61f - Math.abs(angle * 2.0f);
        this.armr2.z = this.armr1.z - (float)Math.sin(this.armr1.yRot) * 9.0f;
        this.armr2.x = this.armr1.x + (float)Math.cos(this.armr1.yRot) * 9.0f;
        this.armr2.yRot = 2.27f - Math.abs(angle);
        this.clawr1.z = this.armr2.z - (float)Math.sin(this.armr2.yRot) * 14.0f;
        this.clawr1.x = this.armr2.x + (float)Math.cos(this.armr2.yRot) * 14.0f;
        this.clawr1.yRot = 0.436f - Math.abs(angle * 4.0f);
        this.clawr2.z = this.clawr1.z;
        this.clawr2.x = this.clawr1.x;
        this.clawr2.yRot = 0.87f - Math.abs(angle * 4.0f);
        this.clawr3.z = this.clawr1.z;
        this.clawr3.x = this.clawr1.x;
        this.clawr3.yRot = 0.436f - Math.abs(angle * 4.0f);
    }
}

