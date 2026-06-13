/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Crab
 *  com.astryxion.chaospersists.ModelCrab
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Crab;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCrab extends EntityModel<Crab> {
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer leg1;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer Leye1;
    ModelRenderer Reye1;
    ModelRenderer Leye2;
    ModelRenderer Reye2;
    ModelRenderer Lclaw1;
    ModelRenderer Lclaw2;
    ModelRenderer Lclaw3;
    ModelRenderer Lclaw4;
    ModelRenderer Lclaw5;
    ModelRenderer Rclaw1;
    ModelRenderer Rclaw2;
    ModelRenderer Rclaw3;
    ModelRenderer Rclaw4;
    ModelRenderer Rclaw5;
    ModelRenderer Rmouth;
    ModelRenderer Lmouth;

    public ModelCrab(float f) {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 256;
        // textureHeight = 512;
        this.body1 = new ModelRenderer(this, 0, 450);
        this.body1.addBox(-38.0f, -5.0f, -8.0f, 76, 10, 48);
        this.body1.setPos(0.0f, 0.0f, 0.0f);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 0, 406);
        this.body2.addBox(-32.0f, -10.0f, -10.0f, 64, 5, 34);
        this.body2.setPos(0.0f, 0.0f, 0.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer(this, 128, 0);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg1.setPos(36.0f, 3.0f, 0.0f);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, -1.343904f, -1.500983f, 0.0f);
        this.body3 = new ModelRenderer(this, 0, 357);
        this.body3.addBox(0.0f, 0.0f, 0.0f, 8, 4, 40);
        this.body3.setPos(38.0f, -5.0f, -6.0f);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer(this, 100, 357);
        this.body4.addBox(0.0f, 0.0f, 0.0f, 8, 4, 40);
        this.body4.setPos(-46.0f, -5.0f, -6.0f);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer(this, 128, 20);
        this.leg2.addBox(-1.0f, 10.0f, -6.0f, 3, 16, 3);
        this.leg2.setPos(36.0f, 3.0f, 0.0f);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, -0.9599311f, -1.500983f, 0.0f);
        this.leg3 = new ModelRenderer(this, 128, 43);
        this.leg3.addBox(0.0f, 21.0f, -15.0f, 2, 16, 2);
        this.leg3.setPos(36.0f, 3.0f, 0.0f);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, -0.5759587f, -1.500983f, 0.0f);
        this.body5 = new ModelRenderer(this, 0, 339);
        this.body5.addBox(-25.0f, 0.0f, 0.0f, 50, 4, 10);
        this.body5.setPos(0.0f, -4.0f, 40.0f);
        this.body5.mirror = true;
        this.setRotation(this.body5, 0.0f, 0.0f, 0.0f);
        this.body6 = new ModelRenderer(this, 124, 342);
        this.body6.addBox(-14.0f, 0.0f, 0.0f, 28, 3, 4);
        this.body6.setPos(0.0f, -10.0f, -14.0f);
        this.body6.mirror = true;
        this.setRotation(this.body6, 0.0f, 0.0f, 0.0f);
        this.Leye1 = new ModelRenderer(this, 62, 0);
        this.Leye1.addBox(-0.5f, -12.0f, -0.5f, 1, 12, 1);
        this.Leye1.setPos(9.0f, -9.0f, -11.0f);
        this.Leye1.mirror = true;
        this.setRotation(this.Leye1, 0.0f, 0.0f, 0.4886922f);
        this.Reye1 = new ModelRenderer(this, 40, 0);
        this.Reye1.addBox(-0.5f, -12.0f, -0.5f, 1, 12, 1);
        this.Reye1.setPos(-9.0f, -9.0f, -11.0f);
        this.Reye1.mirror = true;
        this.setRotation(this.Reye1, 0.0f, 0.0f, -0.4886922f);
        this.Leye2 = new ModelRenderer(this, 50, 0);
        this.Leye2.addBox(-1.0f, -14.0f, -1.0f, 2, 2, 2);
        this.Leye2.setPos(9.0f, -9.0f, -11.0f);
        this.Leye2.mirror = true;
        this.setRotation(this.Leye2, 0.0f, 0.0f, 0.4886922f);
        this.Reye2 = new ModelRenderer(this, 26, 0);
        this.Reye2.addBox(-1.0f, -14.0f, -1.0f, 2, 2, 2);
        this.Reye2.setPos(-9.0f, -9.0f, -11.0f);
        this.Reye2.mirror = true;
        this.setRotation(this.Reye2, 0.0f, 0.0f, -0.4886922f);
        this.Lclaw1 = new ModelRenderer(this, 0, 80);
        this.Lclaw1.addBox(-4.0f, 0.0f, -14.0f, 8, 4, 18);
        this.Lclaw1.setPos(31.0f, -2.0f, -8.0f);
        this.Lclaw1.mirror = true;
        this.setRotation(this.Lclaw1, 0.0f, -0.4886922f, 0.0f);
        this.Lclaw2 = new ModelRenderer(this, 0, 105);
        this.Lclaw2.addBox(-7.0f, -3.0f, -12.0f, 17, 6, 16);
        this.Lclaw2.setPos(37.0f, 0.0f, -20.0f);
        this.Lclaw2.mirror = true;
        this.setRotation(this.Lclaw2, 0.0f, -0.1745329f, 0.0f);
        this.Lclaw3 = new ModelRenderer(this, 0, 131);
        this.Lclaw3.addBox(0.0f, -5.0f, -25.0f, 17, 10, 30);
        this.Lclaw3.setPos(37.0f, 0.0f, -31.0f);
        this.Lclaw3.mirror = true;
        this.setRotation(this.Lclaw3, 0.0f, -0.4537856f, 0.0f);
        this.Lclaw4 = new ModelRenderer(this, 0, 175);
        this.Lclaw4.addBox(2.0f, -3.0f, -32.0f, 11, 5, 12);
        this.Lclaw4.setPos(37.0f, 0.0f, -31.0f);
        this.Lclaw4.mirror = true;
        this.setRotation(this.Lclaw4, 0.0f, -0.3490659f, 0.0f);
        this.Lclaw5 = new ModelRenderer(this, 0, 197);
        this.Lclaw5.addBox(-4.0f, -3.0f, -27.0f, 7, 5, 32);
        this.Lclaw5.setPos(36.0f, 0.0f, -31.0f);
        this.Lclaw5.mirror = true;
        this.setRotation(this.Lclaw5, 0.0f, 0.3839724f, 0.0f);
        this.Rclaw1 = new ModelRenderer(this, 102, 78);
        this.Rclaw1.addBox(-4.0f, 0.0f, -14.0f, 8, 4, 18);
        this.Rclaw1.setPos(-31.0f, -2.0f, -8.0f);
        this.Rclaw1.mirror = true;
        this.setRotation(this.Rclaw1, 0.0f, 0.4886922f, 0.0f);
        this.Rclaw2 = new ModelRenderer(this, 103, 106);
        this.Rclaw2.addBox(-10.0f, -3.0f, -12.0f, 17, 6, 16);
        this.Rclaw2.setPos(-37.0f, 0.0f, -20.0f);
        this.Rclaw2.mirror = true;
        this.setRotation(this.Rclaw2, 0.0f, 0.1745329f, 0.0f);
        this.Rclaw3 = new ModelRenderer(this, 100, 131);
        this.Rclaw3.addBox(-17.0f, -5.0f, -25.0f, 17, 10, 30);
        this.Rclaw3.setPos(-37.0f, 0.0f, -31.0f);
        this.Rclaw3.mirror = true;
        this.setRotation(this.Rclaw3, 0.0f, 0.4537856f, 0.0f);
        this.Rclaw4 = new ModelRenderer(this, 101, 175);
        this.Rclaw4.addBox(-13.0f, -3.0f, -32.0f, 11, 5, 12);
        this.Rclaw4.setPos(-37.0f, 0.0f, -31.0f);
        this.Rclaw4.mirror = true;
        this.setRotation(this.Rclaw4, 0.0f, 0.3490659f, 0.0f);
        this.Rclaw5 = new ModelRenderer(this, 100, 197);
        this.Rclaw5.addBox(-4.0f, -3.0f, -27.0f, 7, 5, 32);
        this.Rclaw5.setPos(-36.0f, 0.0f, -31.0f);
        this.Rclaw5.mirror = true;
        this.setRotation(this.Rclaw5, 0.0f, -0.3839724f, 0.0f);
        this.Rmouth = new ModelRenderer(this, 0, 28);
        this.Rmouth.addBox(0.0f, 0.0f, -0.5f, 6, 3, 1);
        this.Rmouth.setPos(-7.0f, 0.0f, -7.5f);
        this.Rmouth.mirror = true;
        this.setRotation(this.Rmouth, 0.0f, 0.3665191f, 0.0f);
        this.Lmouth = new ModelRenderer(this, 0, 19);
        this.Lmouth.addBox(-6.0f, 0.0f, -0.5f, 6, 3, 1);
        this.Lmouth.setPos(7.0f, 0.0f, -7.5f);
        this.Lmouth.mirror = true;
        this.setRotation(this.Lmouth, 0.0f, -0.3665191f, 0.0f);
    }
    @Override
    public void setupAnim(Crab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Crab e = (Crab)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.leg3.x = 36.0f;
        this.leg2.x = 36.0f;
        this.leg1.x = 36.0f;
        this.leg3.y = 3.0f;
        this.leg2.y = 3.0f;
        this.leg1.y = 3.0f;
        this.leg3.z = 0.0f;
        this.leg2.z = 0.0f;
        this.leg1.z = 0.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(-1.5707963267948966 + (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        this.leg3.z = 10.0f;
        this.leg2.z = 10.0f;
        this.leg1.z = 10.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(-1.5707963267948966 - (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        this.leg3.z = 20.0f;
        this.leg2.z = 20.0f;
        this.leg1.z = 20.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(-1.5707963267948966 + (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        this.leg3.z = 30.0f;
        this.leg2.z = 30.0f;
        this.leg1.z = 30.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(-1.5707963267948966 - (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        this.leg3.x = -36.0f;
        this.leg2.x = -36.0f;
        this.leg1.x = -36.0f;
        this.leg3.y = 3.0f;
        this.leg2.y = 3.0f;
        this.leg1.y = 3.0f;
        this.leg3.z = 0.0f;
        this.leg2.z = 0.0f;
        this.leg1.z = 0.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(- -1.5707963267948966 + (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        this.leg3.z = 10.0f;
        this.leg2.z = 10.0f;
        this.leg1.z = 10.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(- -1.5707963267948966 - (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        this.leg3.z = 20.0f;
        this.leg2.z = 20.0f;
        this.leg1.z = 20.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(- -1.5707963267948966 + (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        this.leg3.z = 30.0f;
        this.leg2.z = 30.0f;
        this.leg1.z = 30.0f;
        this.leg2.yRot = this.leg3.yRot = (float)(- -1.5707963267948966 - (double)(MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.15f * f1));
        this.leg1.yRot = this.leg3.yRot;
        
        
        
        if (e.getAttacking() == 0) {
            this.Leye1.xRot = this.Leye2.xRot = MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.05f;
            this.Leye1.zRot = this.Leye2.zRot = 0.54f + MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.05f;
            this.Reye1.xRot = this.Reye2.xRot = MathHelper.cos((float)(f2 * 0.3f)) * 3.1415927f * 0.05f;
            this.Reye1.zRot = this.Reye2.zRot = -0.54f + MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.05f;
            this.Lmouth.yRot = -0.72f + MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.05f;
            this.Rmouth.yRot = 0.72f - MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.05f;
            float newangle = MathHelper.cos((float)(f2 * 0.15f)) * 3.1415927f * 0.03f;
            this.Lclaw3.yRot = -0.453f + newangle;
            this.Lclaw4.yRot = -0.349f + newangle;
            this.Lclaw5.yRot = 0.384f - newangle;
            newangle = MathHelper.cos((float)(f2 * 0.13f)) * 3.1415927f * 0.02f;
            this.Rclaw3.yRot = 0.453f + newangle;
            this.Rclaw4.yRot = 0.349f + newangle;
            this.Rclaw5.yRot = -0.384f - newangle;
        } else {
            this.Leye1.xRot = this.Leye2.xRot = MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.1f;
            this.Leye1.zRot = this.Leye2.zRot = 0.54f + MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.1f;
            this.Reye1.xRot = this.Reye2.xRot = MathHelper.cos((float)(f2 * 0.4f)) * 3.1415927f * 0.1f;
            this.Reye1.zRot = this.Reye2.zRot = -0.54f + MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.1f;
            this.Lmouth.yRot = -0.72f + MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.15f;
            this.Rmouth.yRot = 0.72f - MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.15f;
            float newangle = MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.13f;
            this.Lclaw3.yRot = -0.453f + newangle;
            this.Lclaw4.yRot = -0.349f + newangle;
            this.Lclaw5.yRot = 0.384f - newangle;
            newangle = MathHelper.cos((float)(f2 * 0.43f)) * 3.1415927f * 0.12f;
            this.Rclaw3.yRot = 0.453f + newangle;
            this.Rclaw4.yRot = 0.349f + newangle;
            this.Rclaw5.yRot = -0.384f - newangle;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leye1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Reye1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leye2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Reye2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rmouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lmouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

