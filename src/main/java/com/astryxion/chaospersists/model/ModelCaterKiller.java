/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CaterKiller
 *  com.astryxion.chaospersists.ModelCaterKiller
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.CaterKiller;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCaterKiller extends EntityModel<CaterKiller> {
    private float wingspeed = 1.0f;
    ModelRenderer Head;
    ModelRenderer falsehead;
    ModelRenderer seg1;
    ModelRenderer ltusk1;
    ModelRenderer ltusk2;
    ModelRenderer rtusk1;
    ModelRenderer rtusk2;
    ModelRenderer ljaw;
    ModelRenderer rjaw;
    ModelRenderer seg1lspike;
    ModelRenderer seg1rspike;
    ModelRenderer seg1ltopspike;
    ModelRenderer seg1rtopspike;
    ModelRenderer seg1lleg;
    ModelRenderer seg1rleg;
    ModelRenderer seg2;
    ModelRenderer seg2lfoot;
    ModelRenderer seg2rfoot;
    ModelRenderer seg2ltopspike;
    ModelRenderer seg2rtopspike;
    ModelRenderer seg2lspike;
    ModelRenderer seg2rspike;
    ModelRenderer seg3;
    ModelRenderer seg3lfoot;
    ModelRenderer seg3rfoot;
    ModelRenderer seg3lspike;
    ModelRenderer seg3rspike;
    ModelRenderer seg3ltopspike;
    ModelRenderer seg3rtopspike;
    ModelRenderer seg3lbackspike;
    ModelRenderer seg3rbackspike;

    public ModelCaterKiller(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 512;
        this.Head = new ModelRenderer(this, 0, 50);
        this.Head.addBox(-8.0f, -8.0f, -8.0f, 16, 16, 8);
        this.Head.setPos(0.0f, -8.0f, -12.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.falsehead = new ModelRenderer(this, 0, 100);
        this.falsehead.addBox(-10.0f, -27.0f, -11.0f, 20, 20, 10);
        this.falsehead.setPos(0.0f, -8.0f, -12.0f);
        this.falsehead.mirror = true;
        this.setRotation(this.falsehead, -0.1570796f, 0.0f, 0.0f);
        this.seg1 = new ModelRenderer(this, 0, 200);
        this.seg1.addBox(-14.0f, -31.0f, 0.0f, 28, 32, 14);
        this.seg1.setPos(0.0f, -8.0f, -12.0f);
        this.seg1.mirror = true;
        this.setRotation(this.seg1, 0.0f, 0.0f, 0.0f);
        this.ltusk1 = new ModelRenderer(this, 0, 140);
        this.ltusk1.addBox(-1.0f, -1.0f, -1.0f, 33, 3, 3);
        this.ltusk1.setPos(9.0f, -25.0f, -19.0f);
        this.ltusk1.mirror = true;
        this.setRotation(this.ltusk1, 0.0f, 0.5585054f, 0.0f);
        this.ltusk2 = new ModelRenderer(this, 0, 160);
        this.ltusk2.addBox(0.0f, 0.0f, 0.0f, 20, 1, 1);
        this.ltusk2.setPos(36.0f, -25.0f, -36.0f);
        this.ltusk2.mirror = true;
        this.setRotation(this.ltusk2, 0.0f, 0.8028515f, 0.0f);
        this.rtusk1 = new ModelRenderer(this, 0, 150);
        this.rtusk1.addBox(-33.0f, 0.0f, 0.0f, 33, 3, 3);
        this.rtusk1.setPos(-8.0f, -25.0f, -17.0f);
        this.rtusk1.mirror = true;
        this.setRotation(this.rtusk1, 0.0f, -0.5585054f, 0.0f);
        this.rtusk2 = new ModelRenderer(this, 0, 170);
        this.rtusk2.addBox(-20.0f, 0.0f, 0.0f, 20, 1, 1);
        this.rtusk2.setPos(-36.0f, -24.0f, -34.0f);
        this.rtusk2.mirror = true;
        this.setRotation(this.rtusk2, 0.0f, -0.8028515f, 0.0f);
        this.ljaw = new ModelRenderer(this, 100, 50);
        this.ljaw.addBox(0.0f, 0.0f, 0.0f, 1, 7, 4);
        this.ljaw.setPos(4.0f, -1.0f, -18.0f);
        this.ljaw.mirror = true;
        this.setRotation(this.ljaw, 0.0f, 0.0f, 0.1396263f);
        this.rjaw = new ModelRenderer(this, 125, 50);
        this.rjaw.addBox(0.0f, 0.0f, 0.0f, 1, 7, 4);
        this.rjaw.setPos(-5.0f, -1.0f, -18.0f);
        this.rjaw.mirror = true;
        this.setRotation(this.rjaw, 0.0f, 0.0f, -0.1396263f);
        this.seg1lspike = new ModelRenderer(this, 0, 260);
        this.seg1lspike.addBox(-1.0f, -1.0f, -1.0f, 33, 2, 2);
        this.seg1lspike.setPos(14.0f, -32.0f, -6.0f);
        this.seg1lspike.mirror = true;
        this.setRotation(this.seg1lspike, 0.0f, 0.3316126f, -0.122173f);
        this.seg1rspike = new ModelRenderer(this, 0, 270);
        this.seg1rspike.addBox(-33.0f, -1.0f, -1.0f, 33, 2, 2);
        this.seg1rspike.setPos(-13.0f, -32.0f, -6.0f);
        this.seg1rspike.mirror = true;
        this.setRotation(this.seg1rspike, 0.0f, -0.3316126f, 0.122173f);
        this.seg1ltopspike = new ModelRenderer(this, 125, 260);
        this.seg1ltopspike.addBox(-2.0f, -8.0f, -2.0f, 4, 9, 4);
        this.seg1ltopspike.setPos(8.0f, -39.0f, -6.0f);
        this.seg1ltopspike.mirror = true;
        this.setRotation(this.seg1ltopspike, 0.0f, 0.0f, 0.1396263f);
        this.seg1rtopspike = new ModelRenderer(this, 150, 260);
        this.seg1rtopspike.addBox(-2.0f, -8.0f, -2.0f, 4, 9, 4);
        this.seg1rtopspike.setPos(-10.0f, -39.0f, -6.0f);
        this.seg1rtopspike.mirror = true;
        this.setRotation(this.seg1rtopspike, 0.0f, 0.0f, -0.1396263f);
        this.seg1lleg = new ModelRenderer(this, 125, 200);
        this.seg1lleg.addBox(-1.0f, 0.0f, -1.0f, 2, 16, 2);
        this.seg1lleg.setPos(8.0f, -8.0f, -5.0f);
        this.seg1lleg.mirror = true;
        this.setRotation(this.seg1lleg, 0.0f, 0.0f, 0.1570796f);
        this.seg1rleg = new ModelRenderer(this, 150, 200);
        this.seg1rleg.addBox(0.0f, 0.0f, 0.0f, 2, 16, 2);
        this.seg1rleg.setPos(-9.0f, -8.0f, -5.0f);
        this.seg1rleg.mirror = true;
        this.setRotation(this.seg1rleg, 0.0f, 0.0f, -0.1570796f);
        this.seg2 = new ModelRenderer(this, 0, 300);
        this.seg2.addBox(-20.0f, -17.0f, -9.0f, 40, 34, 18);
        this.seg2.setPos(0.0f, -2.0f, 32.0f);
        this.seg2.mirror = true;
        this.setRotation(this.seg2, 0.0f, 0.0f, 0.0f);
        this.seg2lfoot = new ModelRenderer(this, 125, 300);
        this.seg2lfoot.addBox(-5.0f, 0.0f, -5.0f, 10, 10, 10);
        this.seg2lfoot.setPos(13.0f, 14.0f, 32.0f);
        this.seg2lfoot.mirror = true;
        this.setRotation(this.seg2lfoot, 0.0f, 0.0f, 0.0f);
        this.seg2rfoot = new ModelRenderer(this, 175, 300);
        this.seg2rfoot.addBox(-5.0f, 0.0f, -5.0f, 10, 10, 10);
        this.seg2rfoot.setPos(-13.0f, 14.0f, 32.0f);
        this.seg2rfoot.mirror = true;
        this.setRotation(this.seg2rfoot, 0.0f, 0.0f, 0.0f);
        this.seg2ltopspike = new ModelRenderer(this, 100, 360);
        this.seg2ltopspike.addBox(-2.0f, -9.0f, -2.0f, 4, 9, 4);
        this.seg2ltopspike.setPos(14.0f, -18.0f, 32.0f);
        this.seg2ltopspike.mirror = true;
        this.setRotation(this.seg2ltopspike, 0.0f, 0.0f, 0.1396263f);
        this.seg2rtopspike = new ModelRenderer(this, 125, 360);
        this.seg2rtopspike.addBox(-2.0f, -9.0f, -2.0f, 4, 9, 4);
        this.seg2rtopspike.setPos(-14.0f, -18.0f, 32.0f);
        this.seg2rtopspike.mirror = true;
        this.setRotation(this.seg2rtopspike, 0.0f, 0.0f, -0.1396263f);
        this.seg2lspike = new ModelRenderer(this, 0, 360);
        this.seg2lspike.addBox(0.0f, -1.0f, -1.0f, 20, 2, 2);
        this.seg2lspike.setPos(18.0f, -9.0f, 32.0f);
        this.seg2lspike.mirror = true;
        this.setRotation(this.seg2lspike, 0.0f, 0.0f, -0.0698132f);
        this.seg2rspike = new ModelRenderer(this, 0, 370);
        this.seg2rspike.addBox(-20.0f, -1.0f, -1.0f, 20, 2, 2);
        this.seg2rspike.setPos(-18.0f, -9.0f, 32.0f);
        this.seg2rspike.mirror = true;
        this.setRotation(this.seg2rspike, 0.0f, 0.0f, 0.0698132f);
        this.seg3 = new ModelRenderer(this, 0, 400);
        this.seg3.addBox(-15.0f, -14.0f, -7.0f, 30, 28, 14);
        this.seg3.setPos(0.0f, 3.0f, 48.0f);
        this.seg3.mirror = true;
        this.setRotation(this.seg3, 0.0f, 0.0f, 0.0f);
        this.seg3lfoot = new ModelRenderer(this, 100, 400);
        this.seg3lfoot.addBox(-4.0f, 0.0f, -6.0f, 8, 8, 12);
        this.seg3lfoot.setPos(10.0f, 16.0f, 48.0f);
        this.seg3lfoot.mirror = true;
        this.setRotation(this.seg3lfoot, 0.0f, 0.0f, 0.0f);
        this.seg3rfoot = new ModelRenderer(this, 150, 400);
        this.seg3rfoot.addBox(-4.0f, 0.0f, -6.0f, 8, 8, 12);
        this.seg3rfoot.setPos(-10.0f, 16.0f, 48.0f);
        this.seg3rfoot.mirror = true;
        this.setRotation(this.seg3rfoot, 0.0f, 0.0f, 0.0f);
        this.seg3lspike = new ModelRenderer(this, 0, 450);
        this.seg3lspike.addBox(0.0f, -1.0f, -1.0f, 14, 2, 2);
        this.seg3lspike.setPos(14.0f, -4.0f, 48.0f);
        this.seg3lspike.mirror = true;
        this.setRotation(this.seg3lspike, 0.0f, 0.0f, -0.0698132f);
        this.seg3rspike = new ModelRenderer(this, 0, 460);
        this.seg3rspike.addBox(-14.0f, -1.0f, -1.0f, 14, 2, 2);
        this.seg3rspike.setPos(-14.0f, -4.0f, 48.0f);
        this.seg3rspike.mirror = true;
        this.setRotation(this.seg3rspike, 0.0f, 0.0f, 0.0698132f);
        this.seg3ltopspike = new ModelRenderer(this, 100, 450);
        this.seg3ltopspike.addBox(-2.0f, -13.0f, -2.0f, 3, 13, 3);
        this.seg3ltopspike.setPos(10.0f, -10.0f, 48.0f);
        this.seg3ltopspike.mirror = true;
        this.setRotation(this.seg3ltopspike, 0.0f, 0.0f, 0.1396263f);
        this.seg3rtopspike = new ModelRenderer(this, 120, 450);
        this.seg3rtopspike.addBox(-2.0f, -13.0f, -2.0f, 3, 13, 3);
        this.seg3rtopspike.setPos(-10.0f, -10.0f, 48.0f);
        this.seg3rtopspike.mirror = true;
        this.setRotation(this.seg3rtopspike, 0.0f, 0.0f, -0.1396263f);
        this.seg3lbackspike = new ModelRenderer(this, 50, 450);
        this.seg3lbackspike.addBox(-2.0f, -20.0f, -2.0f, 4, 20, 4);
        this.seg3lbackspike.setPos(13.0f, -8.0f, 54.0f);
        this.seg3lbackspike.mirror = true;
        this.setRotation(this.seg3lbackspike, -0.9773844f, 0.2792527f, 0.1396263f);
        this.seg3rbackspike = new ModelRenderer(this, 75, 450);
        this.seg3rbackspike.addBox(-2.0f, -20.0f, -2.0f, 4, 20, 4);
        this.seg3rbackspike.setPos(-13.0f, -8.0f, 54.0f);
        this.seg3rbackspike.mirror = true;
        this.setRotation(this.seg3rbackspike, -0.9773844f, -0.3490659f, 0.1396263f);
    }
    @Override
    public void setupAnim(CaterKiller e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        int i;
        float newangle = 0.0f;
        float headoff = 0.0f;
        float zpi = 0.0f;
        float zdist = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        newangle = e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.07f : MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.025f;
        this.ljaw.zRot = 0.139f + newangle;
        this.rjaw.zRot = -0.139f - newangle;
        headoff = e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 8.0f : MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 2.0f;
        this.Head.y = -8.0f + headoff;
        this.falsehead.y = -8.0f + headoff;
        this.ltusk1.y = -25.0f + headoff;
        this.ltusk2.y = -25.0f + headoff;
        this.rtusk1.y = -25.0f + headoff;
        this.rtusk2.y = -25.0f + headoff;
        this.ljaw.y = -1.0f + headoff;
        this.rjaw.y = -1.0f + headoff;
        newangle = MathHelper.cos((float)(f2 * 2.11f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.ltusk2.yRot = 0.802f + newangle;
        newangle = MathHelper.cos((float)(f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.rtusk2.yRot = -0.802f + newangle;
        
        
        
        
        
        
        
        
        for (i = 0; i < 3; ++i) {
            this.seg1.y = -8.0f + headoff / (float)(i + 1) + (float)(8 * i);
            this.seg1lspike.y = -32.0f + headoff / (float)(i + 1) + (float)(8 * i);
            this.seg1rspike.y = -32.0f + headoff / (float)(i + 1) + (float)(8 * i);
            this.seg1ltopspike.y = -39.0f + headoff / (float)(i + 1) + (float)(8 * i);
            this.seg1rtopspike.y = -39.0f + headoff / (float)(i + 1) + (float)(8 * i);
            this.seg1lleg.y = -8.0f + headoff / (float)(i + 1) + (float)(8 * i);
            this.seg1rleg.y = -8.0f + headoff / (float)(i + 1) + (float)(8 * i);
            this.seg1.z = -12 + 14 * i;
            this.seg1lspike.z = -6 + 14 * i;
            this.seg1rspike.z = -6 + 14 * i;
            this.seg1ltopspike.z = -6 + 14 * i;
            this.seg1rtopspike.z = -6 + 14 * i;
            this.seg1lleg.z = -5 + 14 * i;
            this.seg1rleg.z = -5 + 14 * i;
            this.seg1lspike.zRot = newangle = MathHelper.cos((float)((float)((double)(f2 * 0.91f * this.wingspeed) + 0.39269908169872414 * (double)i))) * 3.1415927f * 0.08f;
            this.seg1rspike.zRot = - newangle;
            newangle = e.getAttacking() != 0 ? MathHelper.cos((float)((float)((double)(f2 * 2.91f * this.wingspeed) + 0.39269908169872414 * (double)i))) * 3.1415927f * 0.15f : MathHelper.cos((float)((float)((double)(f2 * 0.35f * this.wingspeed) + 0.39269908169872414 * (double)i))) * 3.1415927f * 0.04f;
            this.seg1lleg.xRot = newangle;
            this.seg1rleg.xRot = - newangle;
            
            
            
            
            
            
            
        }
        for (i = 0; i < 6; ++i) {
            zdist = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed + zpi)) * 1.5f * f1;
            this.seg2.z = 39.0f + (16.0f + zdist) * (float)i;
            this.seg2lfoot.z = 39.0f + (16.0f + zdist) * (float)i;
            this.seg2rfoot.z = 39.0f + (16.0f + zdist) * (float)i;
            this.seg2ltopspike.z = 39.0f + (16.0f + zdist) * (float)i;
            this.seg2rtopspike.z = 39.0f + (16.0f + zdist) * (float)i;
            this.seg2lspike.z = 39.0f + (16.0f + zdist) * (float)i;
            this.seg2rspike.z = 39.0f + (16.0f + zdist) * (float)i;
            this.seg2lspike.zRot = newangle = MathHelper.cos((float)((float)((double)(f2 * 0.4f * this.wingspeed) - 0.39269908169872414 * (double)i))) * 3.1415927f * 0.07f;
            this.seg2rspike.zRot = - newangle;
            
            
            
            
            
            
            
            zpi += 0.7853982f;
        }
        this.seg3lfoot.z = this.seg3.z = this.seg2rspike.z + 16.0f;
        this.seg3rfoot.z = this.seg3.z;
        this.seg3lspike.z = this.seg3.z;
        this.seg3rspike.z = this.seg3.z;
        this.seg3ltopspike.z = this.seg3.z;
        this.seg3rtopspike.z = this.seg3.z;
        this.seg3lbackspike.z = this.seg3.z + 6.0f;
        this.seg3rbackspike.z = this.seg3.z + 6.0f;
        i = 6;
        this.seg3lspike.zRot = newangle = MathHelper.cos((float)((float)((double)(f2 * 0.4f * this.wingspeed) - 0.39269908169872414 * (double)i))) * 3.1415927f * 0.07f;
        this.seg3rspike.zRot = - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.81f * this.wingspeed)) * 3.1415927f * 0.04f;
        this.seg3lbackspike.xRot = -0.977f + newangle;
        newangle = MathHelper.cos((float)(f2 * 0.87f * this.wingspeed)) * 3.1415927f * 0.04f;
        this.seg3rbackspike.xRot = -0.977f + newangle;
        newangle = MathHelper.cos((float)(f2 * 1.11f * this.wingspeed)) * 3.1415927f * 0.04f;
        this.seg3lbackspike.yRot = 0.28f + newangle;
        newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.04f;
        this.seg3rbackspike.yRot = -0.28f + newangle;
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.falsehead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltusk1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltusk2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtusk1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtusk2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg1lspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg1rspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg1ltopspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg1rtopspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg1lleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg1rleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg2lfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg2rfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg2ltopspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg2rtopspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg2lspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg2rspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3lfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3rfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3lspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3rspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3ltopspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3rtopspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3lbackspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seg3rbackspike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

