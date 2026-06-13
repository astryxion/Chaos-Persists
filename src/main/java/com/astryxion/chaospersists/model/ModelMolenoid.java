/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelMolenoid
 *  com.astryxion.chaospersists.Molenoid
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Molenoid;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMolenoid extends EntityModel<Molenoid> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer shoulders;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer nosestar1;
    ModelRenderer nosestar2;
    ModelRenderer nosestar3;
    ModelRenderer larm;
    ModelRenderer lhand;
    ModelRenderer lclaw1;
    ModelRenderer lclaw2;
    ModelRenderer lclaw3;
    ModelRenderer lclaw4;
    ModelRenderer nosestar4;
    ModelRenderer nosestar5;
    ModelRenderer nosestar6;
    ModelRenderer butt;
    ModelRenderer tail;
    ModelRenderer lleg;
    ModelRenderer lfoot;
    ModelRenderer ltoe1;
    ModelRenderer ltoe2;
    ModelRenderer ltoe3;
    ModelRenderer ltoe4;
    ModelRenderer rarm;
    ModelRenderer rhand;
    ModelRenderer rclaw1;
    ModelRenderer rclaw2;
    ModelRenderer rclaw3;
    ModelRenderer rclaw4;
    ModelRenderer rleg;
    ModelRenderer rfoot;
    ModelRenderer rtoe1;
    ModelRenderer rtoe2;
    ModelRenderer rtoe3;
    ModelRenderer rtoe4;

    public ModelMolenoid(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 256;
        this.body = new ModelRenderer(this, 0, 176);
        this.body.addBox(-16.0f, 0.0f, 0.0f, 32, 20, 56);
        this.body.setPos(0.0f, 1.0f, 6.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.shoulders = new ModelRenderer(this, 0, 143);
        this.shoulders.addBox(-17.0f, 0.0f, 0.0f, 34, 17, 10);
        this.shoulders.setPos(0.0f, 3.0f, -4.0f);
        this.shoulders.mirror = true;
        this.setRotation(this.shoulders, 0.0f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 0, 114);
        this.head1.addBox(-14.0f, 0.0f, 0.0f, 28, 14, 10);
        this.head1.setPos(0.0f, 5.0f, -14.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 0, 89);
        this.head2.addBox(-11.0f, 0.0f, 0.0f, 22, 10, 10);
        this.head2.setPos(0.0f, 6.0f, -24.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer(this, 0, 67);
        this.head3.addBox(-4.0f, 0.0f, 0.0f, 8, 8, 10);
        this.head3.setPos(0.0f, 7.0f, -34.0f);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.nosestar1 = new ModelRenderer(this, 0, 32);
        this.nosestar1.addBox(-0.5f, -8.0f, 0.0f, 1, 16, 1);
        this.nosestar1.setPos(0.0f, 11.0f, -35.0f);
        this.nosestar1.mirror = true;
        this.setRotation(this.nosestar1, 0.0f, 0.0f, 0.0f);
        this.nosestar2 = new ModelRenderer(this, 20, 32);
        this.nosestar2.addBox(-0.5f, -8.0f, 0.0f, 1, 16, 1);
        this.nosestar2.setPos(0.0f, 11.0f, -35.0f);
        this.nosestar2.mirror = true;
        this.setRotation(this.nosestar2, 0.0f, 0.0f, 1.047198f);
        this.nosestar3 = new ModelRenderer(this, 40, 32);
        this.nosestar3.addBox(-0.5f, -8.0f, 0.0f, 1, 16, 1);
        this.nosestar3.setPos(0.0f, 11.0f, -35.0f);
        this.nosestar3.mirror = true;
        this.setRotation(this.nosestar3, 0.0f, 0.0f, -1.047198f);
        this.larm = new ModelRenderer(this, 80, 0);
        this.larm.addBox(0.0f, 0.0f, -2.0f, 17, 11, 5);
        this.larm.setPos(13.0f, 8.0f, 0.0f);
        this.larm.mirror = true;
        this.setRotation(this.larm, 0.0f, 0.6283185f, 0.0f);
        this.lhand = new ModelRenderer(this, 80, 20);
        this.lhand.addBox(0.0f, 0.0f, -2.0f, 12, 14, 4);
        this.lhand.setPos(25.0f, 7.0f, -9.0f);
        this.lhand.mirror = true;
        this.setRotation(this.lhand, 0.0f, 0.0f, 0.0f);
        this.lclaw1 = new ModelRenderer(this, 80, 42);
        this.lclaw1.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.lclaw1.setPos(35.0f, 20.0f, -9.0f);
        this.lclaw1.mirror = true;
        this.setRotation(this.lclaw1, 0.0f, -0.1745329f, 0.0f);
        this.lclaw2 = new ModelRenderer(this, 80, 52);
        this.lclaw2.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.lclaw2.setPos(35.0f, 15.0f, -9.0f);
        this.lclaw2.mirror = true;
        this.setRotation(this.lclaw2, 0.0f, -0.1745329f, 0.0f);
        this.lclaw3 = new ModelRenderer(this, 80, 62);
        this.lclaw3.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.lclaw3.setPos(35.0f, 10.0f, -9.0f);
        this.lclaw3.mirror = true;
        this.setRotation(this.lclaw3, 0.0f, -0.1745329f, 0.0f);
        this.lclaw4 = new ModelRenderer(this, 80, 72);
        this.lclaw4.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.lclaw4.setPos(35.0f, 5.0f, -9.0f);
        this.lclaw4.mirror = true;
        this.setRotation(this.lclaw4, 0.0f, -0.1745329f, 0.0f);
        this.nosestar4 = new ModelRenderer(this, 10, 32);
        this.nosestar4.addBox(-0.5f, -8.0f, 0.0f, 1, 16, 1);
        this.nosestar4.setPos(0.0f, 11.0f, -35.0f);
        this.nosestar4.mirror = true;
        this.setRotation(this.nosestar4, 0.0f, 0.0f, 0.5235988f);
        this.nosestar5 = new ModelRenderer(this, 30, 32);
        this.nosestar5.addBox(-0.5f, -8.0f, 0.0f, 1, 16, 1);
        this.nosestar5.setPos(0.0f, 11.0f, -35.0f);
        this.nosestar5.mirror = true;
        this.setRotation(this.nosestar5, 0.0f, 0.0f, 1.570796f);
        this.nosestar6 = new ModelRenderer(this, 50, 32);
        this.nosestar6.addBox(-0.5f, -8.0f, 0.0f, 1, 16, 1);
        this.nosestar6.setPos(0.0f, 11.0f, -35.0f);
        this.nosestar6.mirror = true;
        this.setRotation(this.nosestar6, 0.0f, 0.0f, -0.5235988f);
        this.butt = new ModelRenderer(this, 196, 215);
        this.butt.addBox(-11.0f, 0.0f, 0.0f, 22, 11, 5);
        this.butt.setPos(0.0f, 6.0f, 62.0f);
        this.butt.mirror = true;
        this.setRotation(this.butt, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer(this, 196, 200);
        this.tail.addBox(-2.0f, 0.0f, 0.0f, 4, 3, 5);
        this.tail.setPos(0.0f, 7.0f, 67.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
        this.lleg = new ModelRenderer(this, 90, 80);
        this.lleg.addBox(0.0f, 0.0f, -2.0f, 17, 11, 5);
        this.lleg.setPos(14.0f, 9.0f, 58.0f);
        this.lleg.mirror = true;
        this.setRotation(this.lleg, 0.0f, 0.6283185f, 0.0f);
        this.lfoot = new ModelRenderer(this, 90, 100);
        this.lfoot.addBox(0.0f, 0.0f, -2.0f, 12, 14, 4);
        this.lfoot.setPos(26.0f, 8.0f, 49.0f);
        this.lfoot.mirror = true;
        this.setRotation(this.lfoot, 0.0f, 0.0f, 0.0f);
        this.ltoe1 = new ModelRenderer(this, 90, 120);
        this.ltoe1.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.ltoe1.setPos(36.0f, 21.0f, 48.0f);
        this.ltoe1.mirror = true;
        this.setRotation(this.ltoe1, 0.0f, -0.2617994f, 0.0f);
        this.ltoe2 = new ModelRenderer(this, 90, 130);
        this.ltoe2.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.ltoe2.setPos(36.0f, 16.0f, 48.0f);
        this.ltoe2.mirror = true;
        this.setRotation(this.ltoe2, 0.0f, -0.2617994f, 0.0f);
        this.ltoe3 = new ModelRenderer(this, 90, 140);
        this.ltoe3.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.ltoe3.setPos(36.0f, 11.0f, 48.0f);
        this.ltoe3.mirror = true;
        this.setRotation(this.ltoe3, 0.0f, -0.2617994f, 0.0f);
        this.ltoe4 = new ModelRenderer(this, 90, 150);
        this.ltoe4.addBox(0.0f, 0.0f, -1.0f, 13, 3, 2);
        this.ltoe4.setPos(36.0f, 6.0f, 48.0f);
        this.ltoe4.mirror = true;
        this.setRotation(this.ltoe4, 0.0f, -0.2617994f, 0.0f);
        this.rarm = new ModelRenderer(this, 130, 0);
        this.rarm.addBox(-17.0f, 0.0f, -2.0f, 17, 11, 5);
        this.rarm.setPos(-14.0f, 8.0f, 0.0f);
        this.rarm.mirror = true;
        this.setRotation(this.rarm, 0.0f, -0.6283185f, 0.0f);
        this.rhand = new ModelRenderer(this, 130, 20);
        this.rhand.addBox(-12.0f, 0.0f, -2.0f, 12, 14, 4);
        this.rhand.setPos(-26.0f, 7.0f, -9.0f);
        this.rhand.mirror = true;
        this.setRotation(this.rhand, 0.0f, 0.0f, 0.0f);
        this.rclaw1 = new ModelRenderer(this, 130, 42);
        this.rclaw1.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rclaw1.setPos(-36.0f, 20.0f, -9.0f);
        this.rclaw1.mirror = true;
        this.setRotation(this.rclaw1, 0.0f, 0.1745329f, 0.0f);
        this.rclaw2 = new ModelRenderer(this, 130, 52);
        this.rclaw2.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rclaw2.setPos(-36.0f, 15.0f, -9.0f);
        this.rclaw2.mirror = true;
        this.setRotation(this.rclaw2, 0.0f, 0.1745329f, 0.0f);
        this.rclaw3 = new ModelRenderer(this, 130, 62);
        this.rclaw3.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rclaw3.setPos(-36.0f, 10.0f, -9.0f);
        this.rclaw3.mirror = true;
        this.setRotation(this.rclaw3, 0.0f, 0.1745329f, 0.0f);
        this.rclaw4 = new ModelRenderer(this, 130, 72);
        this.rclaw4.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rclaw4.setPos(-36.0f, 5.0f, -9.0f);
        this.rclaw4.mirror = true;
        this.setRotation(this.rclaw4, 0.0f, 0.1745329f, 0.0f);
        this.rleg = new ModelRenderer(this, 150, 80);
        this.rleg.addBox(-17.0f, 0.0f, -2.0f, 17, 11, 5);
        this.rleg.setPos(-14.0f, 9.0f, 58.0f);
        this.rleg.mirror = true;
        this.setRotation(this.rleg, 0.0f, -0.6283185f, 0.0f);
        this.rfoot = new ModelRenderer(this, 150, 100);
        this.rfoot.addBox(-12.0f, 0.0f, -2.0f, 12, 14, 4);
        this.rfoot.setPos(-26.0f, 8.0f, 49.0f);
        this.rfoot.mirror = true;
        this.setRotation(this.rfoot, 0.0f, 0.0f, 0.0f);
        this.rtoe1 = new ModelRenderer(this, 150, 120);
        this.rtoe1.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rtoe1.setPos(-36.0f, 21.0f, 48.0f);
        this.rtoe1.mirror = true;
        this.setRotation(this.rtoe1, 0.0f, 0.2617994f, 0.0f);
        this.rtoe2 = new ModelRenderer(this, 150, 130);
        this.rtoe2.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rtoe2.setPos(-36.0f, 16.0f, 48.0f);
        this.rtoe2.mirror = true;
        this.setRotation(this.rtoe2, 0.0f, 0.2617994f, 0.0f);
        this.rtoe3 = new ModelRenderer(this, 150, 140);
        this.rtoe3.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rtoe3.setPos(-36.0f, 11.0f, 48.0f);
        this.rtoe3.mirror = true;
        this.setRotation(this.rtoe3, 0.0f, 0.2617994f, 0.0f);
        this.rtoe4 = new ModelRenderer(this, 150, 150);
        this.rtoe4.addBox(-13.0f, 0.0f, -1.0f, 13, 3, 2);
        this.rtoe4.setPos(-36.0f, 6.0f, 48.0f);
        this.rtoe4.mirror = true;
        this.setRotation(this.rtoe4, 0.0f, 0.2617994f, 0.0f);
    }
    @Override
    public void setupAnim(Molenoid entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Molenoid e = (Molenoid)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.25f : (f1 > 0.1f ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f);
        this.larm.yRot = newangle + 0.628f;
        this.lhand.z = this.larm.z - (float)Math.sin(this.larm.yRot) * 15.0f;
        this.lhand.x = this.larm.x + (float)Math.cos(this.larm.yRot) * 15.0f;
        this.lhand.yRot = newangle * 1.25f;
        this.lclaw1.z = this.lhand.z - (float)Math.sin(this.lhand.yRot) * 10.0f;
        this.lclaw1.x = this.lhand.x + (float)Math.cos(this.lhand.yRot) * 10.0f;
        this.lclaw1.yRot = newangle * 1.5f - 0.174f;
        this.lclaw2.z = this.lclaw1.z;
        this.lclaw2.x = this.lclaw1.x;
        this.lclaw2.yRot = this.lclaw1.yRot;
        this.lclaw3.z = this.lclaw1.z;
        this.lclaw3.x = this.lclaw1.x;
        this.lclaw3.yRot = this.lclaw1.yRot;
        this.lclaw4.z = this.lclaw1.z;
        this.lclaw4.x = this.lclaw1.x;
        this.lclaw4.yRot = this.lclaw1.yRot;
        this.rarm.yRot = newangle - 0.628f;
        this.rhand.z = this.rarm.z + (float)Math.sin(this.rarm.yRot) * 15.0f;
        this.rhand.x = this.rarm.x - (float)Math.cos(this.rarm.yRot) * 15.0f;
        this.rhand.yRot = newangle * 1.25f;
        this.rclaw1.z = this.rhand.z + (float)Math.sin(this.rhand.yRot) * 10.0f;
        this.rclaw1.x = this.rhand.x - (float)Math.cos(this.rhand.yRot) * 10.0f;
        this.rclaw1.yRot = newangle * 1.5f + 0.174f;
        this.rclaw2.z = this.rclaw1.z;
        this.rclaw2.x = this.rclaw1.x;
        this.rclaw2.yRot = this.rclaw1.yRot;
        this.rclaw3.z = this.rclaw1.z;
        this.rclaw3.x = this.rclaw1.x;
        this.rclaw3.yRot = this.rclaw1.yRot;
        this.rclaw4.z = this.rclaw1.z;
        this.rclaw4.x = this.rclaw1.x;
        this.rclaw4.yRot = this.rclaw1.yRot;
        newangle = f1 > 0.1f ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.lleg.yRot = - newangle + 0.628f;
        this.lfoot.z = this.lleg.z - (float)Math.sin(this.lleg.yRot) * 15.0f;
        this.lfoot.x = this.lleg.x + (float)Math.cos(this.lleg.yRot) * 15.0f;
        this.lfoot.yRot = (- newangle) * 1.25f;
        this.ltoe1.z = this.lfoot.z - (float)Math.sin(this.lfoot.yRot) * 10.0f;
        this.ltoe1.x = this.lfoot.x + (float)Math.cos(this.lfoot.yRot) * 10.0f;
        this.ltoe1.yRot = (- newangle) * 1.5f - 0.261f;
        this.ltoe2.z = this.ltoe1.z;
        this.ltoe2.x = this.ltoe1.x;
        this.ltoe2.yRot = this.ltoe1.yRot;
        this.ltoe3.z = this.ltoe1.z;
        this.ltoe3.x = this.ltoe1.x;
        this.ltoe3.yRot = this.ltoe1.yRot;
        this.ltoe4.z = this.ltoe1.z;
        this.ltoe4.x = this.ltoe1.x;
        this.ltoe4.yRot = this.ltoe1.yRot;
        this.rleg.yRot = - newangle - 0.628f;
        this.rfoot.z = this.rleg.z + (float)Math.sin(this.rleg.yRot) * 15.0f;
        this.rfoot.x = this.rleg.x - (float)Math.cos(this.rleg.yRot) * 15.0f;
        this.rfoot.yRot = (- newangle) * 1.25f;
        this.rtoe1.z = this.rfoot.z + (float)Math.sin(this.rfoot.yRot) * 10.0f;
        this.rtoe1.x = this.rfoot.x - (float)Math.cos(this.rfoot.yRot) * 10.0f;
        this.rtoe1.yRot = (- newangle) * 1.5f + 0.261f;
        this.rtoe2.z = this.rtoe1.z;
        this.rtoe2.x = this.rtoe1.x;
        this.rtoe2.yRot = this.rtoe1.yRot;
        this.rtoe3.z = this.rtoe1.z;
        this.rtoe3.x = this.rtoe1.x;
        this.rtoe3.yRot = this.rtoe1.yRot;
        this.rtoe4.z = this.rtoe1.z;
        this.rtoe4.x = this.rtoe1.x;
        this.rtoe4.yRot = this.rtoe1.yRot;
        this.nosestar1.zRot = newangle = MathHelper.cos((float)(f2 * 0.1f * this.wingspeed)) * 3.1415927f;
        this.nosestar2.zRot = newangle + 0.523f;
        this.nosestar3.zRot = newangle + 1.047f;
        this.nosestar4.zRot = newangle + 1.57f;
        this.nosestar5.zRot = newangle - 1.047f;
        this.nosestar6.zRot = newangle - 0.523f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shoulders.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nosestar1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nosestar2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nosestar3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lhand.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nosestar4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nosestar5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nosestar6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.butt.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltoe1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltoe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltoe3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltoe4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rhand.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtoe1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtoe2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtoe3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtoe4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

