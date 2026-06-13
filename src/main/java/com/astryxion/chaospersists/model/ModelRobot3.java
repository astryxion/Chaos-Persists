/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRobot3
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Robot3
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Robot3;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;

import net.minecraft.util.math.MathHelper;

public class ModelRobot3 extends EntityModel<Robot3> {
    private float wingspeed = 1.0f;
    ModelRenderer rleg1;
    ModelRenderer lleg1;
    ModelRenderer rleg2;
    ModelRenderer lleg2;
    ModelRenderer hips;
    ModelRenderer waist1;
    ModelRenderer waist2;
    ModelRenderer body3;
    ModelRenderer lazer;
    ModelRenderer body2;
    ModelRenderer body1;
    ModelRenderer body4;
    ModelRenderer waist3;
    ModelRenderer larm3;
    ModelRenderer rarm3;
    ModelRenderer larm2;
    ModelRenderer rarm2;
    ModelRenderer larm1;
    ModelRenderer rarm1;

    public ModelRobot3(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 512;
        this.rleg1 = new ModelRenderer(this, 20, 100);
        this.rleg1.addBox(-23.0f, 26.0f, -8.0f, 16, 29, 16);
        this.rleg1.setPos(-9.0f, -31.0f, 0.0f);
        this.rleg1.mirror = true;
        this.setRotation(this.rleg1, 0.0f, 0.0f, 0.0f);
        this.lleg1 = new ModelRenderer(this, 20, 159);
        this.lleg1.addBox(7.0f, 25.0f, -8.0f, 16, 29, 16);
        this.lleg1.setPos(9.0f, -30.0f, 0.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, 0.0f, 0.0f, 0.0f);
        this.rleg2 = new ModelRenderer(this, 20, 212);
        this.rleg2.addBox(-14.0f, 0.0f, -7.0f, 14, 29, 14);
        this.rleg2.setPos(-9.0f, -31.0f, 0.0f);
        this.rleg2.mirror = true;
        this.setRotation(this.rleg2, 0.0f, 0.0f, 0.2792527f);
        this.lleg2 = new ModelRenderer(this, 20, 265);
        this.lleg2.addBox(0.0f, 0.0f, -7.0f, 13, 29, 14);
        this.lleg2.setPos(9.0f, -31.0f, 0.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, 0.0f, 0.0f, -0.2792527f);
        this.hips = new ModelRenderer(this, 20, 316);
        this.hips.addBox(0.0f, 0.0f, 0.0f, 18, 16, 16);
        this.hips.setPos(-9.0f, -43.0f, -8.0f);
        this.hips.mirror = true;
        this.setRotation(this.hips, 0.0f, 0.0f, 0.0f);
        this.waist1 = new ModelRenderer(this, 20, 359);
        this.waist1.addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
        this.waist1.setPos(-6.0f, -55.0f, -4.0f);
        this.waist1.mirror = true;
        this.setRotation(this.waist1, -0.1f, 0.0f, 0.0f);
        this.waist2 = new ModelRenderer(this, 20, 391);
        this.waist2.addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
        this.waist2.setPos(-6.0f, -67.0f, -4.0f);
        this.waist2.mirror = true;
        this.setRotation(this.waist2, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer(this, 20, 426);
        this.body3.addBox(-23.0f, -25.0f, 10.0f, 47, 47, 25);
        this.body3.setPos(0.0f, -88.0f, -10.0f);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.2f, 0.0f, 0.0f);
        this.lazer = new ModelRenderer(this, 20, 50);
        this.lazer.addBox(-8.0f, -8.0f, -22.0f, 17, 16, 22);
        this.lazer.setPos(0.0f, -88.0f, -11.0f);
        this.lazer.mirror = true;
        this.setRotation(this.lazer, 0.4f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 101, 103);
        this.body2.addBox(9.0f, -24.0f, -12.0f, 15, 47, 47);
        this.body2.setPos(0.0f, -88.0f, -11.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.2f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer(this, 101, 210);
        this.body1.addBox(-23.0f, -24.0f, -12.0f, 15, 47, 47);
        this.body1.setPos(0.0f, -88.0f, -11.0f);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.2f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer(this, 101, 321);
        this.body4.addBox(-8.0f, -24.0f, -12.0f, 18, 16, 22);
        this.body4.setPos(0.0f, -88.0f, -11.0f);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.2f, 0.0f, 0.0f);
        this.waist3 = new ModelRenderer(this, 99, 375);
        this.waist3.addBox(0.0f, 0.0f, -1.0f, 12, 17, 12);
        this.waist3.setPos(-6.0f, -83.0f, -6.0f);
        this.waist3.mirror = true;
        this.setRotation(this.waist3, 0.2f, 0.0f, 0.0f);
        this.larm3 = new ModelRenderer(this, 121, 54);
        this.larm3.addBox(0.0f, -10.0f, -9.0f, 20, 18, 18);
        this.larm3.setPos(24.0f, -92.0f, 2.0f);
        this.larm3.mirror = true;
        this.setRotation(this.larm3, 1.0f, 0.0f, 0.0f);
        this.rarm3 = new ModelRenderer(this, 26, 8);
        this.rarm3.addBox(-20.0f, -9.0f, -9.0f, 20, 18, 18);
        this.rarm3.setPos(-23.0f, -92.0f, 2.0f);
        this.rarm3.mirror = true;
        this.setRotation(this.rarm3, 1.0f, 0.0f, 0.0f);
        this.larm2 = new ModelRenderer(this, 207, 47);
        this.larm2.addBox(3.0f, 8.0f, -7.0f, 14, 29, 14);
        this.larm2.setPos(24.0f, -92.0f, 2.0f);
        this.larm2.mirror = true;
        this.setRotation(this.larm2, 1.0f, 0.0f, 0.0f);
        this.rarm2 = new ModelRenderer(this, 161, 372);
        this.rarm2.addBox(-17.0f, 9.0f, -7.0f, 14, 29, 14);
        this.rarm2.setPos(-23.0f, -92.0f, 2.0f);
        this.rarm2.mirror = true;
        this.setRotation(this.rarm2, 1.0f, 0.0f, 0.0f);
        this.larm1 = new ModelRenderer(this, 185, 433);
        this.larm1.addBox(0.0f, -12.0f, 30.0f, 14, 37, 14);
        this.larm1.setPos(27.0f, -92.0f, 2.0f);
        this.larm1.mirror = true;
        this.setRotation(this.larm1, -1.0f, 0.0f, 0.0f);
        this.rarm1 = new ModelRenderer(this, 239, 105);
        this.rarm1.addBox(-17.0f, -12.0f, 30.0f, 14, 37, 14);
        this.rarm1.setPos(-23.0f, -92.0f, 2.0f);
        this.rarm1.mirror = true;
        this.setRotation(this.rarm1, -1.0f, 0.0f, 0.0f);
    }
    public void setupAnim(Robot3 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Robot3 e = (Robot3)entity;
        RenderInfo r = null;
        float newangle = 0.0f;
        float nextangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 0.55f * this.wingspeed)) * 3.1415927f * 0.12f * f1 : 0.0f;
        this.lleg1.xRot = newangle;
        this.lleg2.xRot = newangle;
        this.rleg1.xRot = - newangle;
        this.rleg2.xRot = - newangle;
        this.lazer.yRot = (float)Math.toRadians((double)f3 / 2.0);
        r = e.getRenderInfo();
        newangle = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = MathHelper.cos((float)((f2 + 0.3f) * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (e.getAttacking() != 0) {
                r.ri1 = 1;
            }
        }
        if (r.ri1 == 0) {
            newangle = 0.0f;
        }
        this.rarm1.xRot = newangle - 1.0f;
        this.rarm2.xRot = newangle + 1.0f;
        this.rarm3.xRot = newangle + 1.0f;
        this.larm1.xRot = newangle - 1.0f;
        this.larm2.xRot = newangle + 1.0f;
        this.larm3.xRot = newangle + 1.0f;
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hips.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.waist1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.waist2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lazer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.waist3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Robot3 par7Entity) {
        
    }
}

