/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GammaMetroid
 *  com.astryxion.chaospersists.ModelGammaMetroid
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.GammaMetroid;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelGammaMetroid extends EntityModel<GammaMetroid> {
    private float wingspeed = 1.0f;
    ModelRenderer Shell3;
    ModelRenderer Shell4;
    ModelRenderer Head;
    ModelRenderer BeakUpper;
    ModelRenderer BeakLower;
    ModelRenderer LeftTusk;
    ModelRenderer MiddleTusk;
    ModelRenderer RightTusk;
    ModelRenderer LeftFrontUpperLeg;
    ModelRenderer LeftFrontLowerLeg;
    ModelRenderer LeftRearUpperLeg;
    ModelRenderer LeftRearLowerLeg;
    ModelRenderer RightFrontUpperLeg;
    ModelRenderer RightFrontLowerLeg;
    ModelRenderer RightRearUpperLeg;
    ModelRenderer RightRearLowerLeg;
    ModelRenderer Core;
    ModelRenderer Bellyinside;
    ModelRenderer Bellyoutside;
    ModelRenderer Shell1;
    ModelRenderer Shell2;

    public ModelGammaMetroid(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 64;
        this.Shell3 = new ModelRenderer(this, 128, 0);
        this.Shell3.addBox(-6.0f, -6.0f, 0.0f, 12, 12, 7);
        this.Shell3.setPos(0.0f, 7.0f, 10.0f);
        this.Shell3.mirror = true;
        this.setRotation(this.Shell3, -0.9599311f, 0.6283185f, 0.5235988f);
        this.Shell4 = new ModelRenderer(this, 48, 34);
        this.Shell4.addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
        this.Shell4.setPos(-3.0f, 9.0f, 13.0f);
        this.Shell4.mirror = true;
        this.setRotation(this.Shell4, -0.2792527f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 48, 48);
        this.Head.addBox(0.0f, 0.0f, 0.0f, 16, 8, 6);
        this.Head.setPos(-8.0f, -1.0f, -11.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.BeakUpper = new ModelRenderer(this, 114, 44);
        this.BeakUpper.addBox(-3.0f, 0.0f, -3.0f, 6, 4, 6);
        this.BeakUpper.setPos(0.0f, 5.0f, -11.0f);
        this.BeakUpper.mirror = true;
        this.setRotation(this.BeakUpper, 0.1047198f, 0.7853982f, 0.1047198f);
        this.BeakLower = new ModelRenderer(this, 120, 54);
        this.BeakLower.addBox(-1.5f, 0.0f, -1.5f, 3, 6, 3);
        this.BeakLower.setPos(0.0f, 9.0f, -12.0f);
        this.BeakLower.mirror = true;
        this.setRotation(this.BeakLower, 0.1396263f, 0.7853982f, 0.1396263f);
        this.LeftTusk = new ModelRenderer(this, 76, 50);
        this.LeftTusk.addBox(0.0f, 0.0f, -12.0f, 2, 2, 12);
        this.LeftTusk.setPos(5.0f, 6.0f, -10.0f);
        this.LeftTusk.mirror = true;
        this.setRotation(this.LeftTusk, 0.1047198f, 0.0872665f, 0.0f);
        this.MiddleTusk = new ModelRenderer(this, 76, 50);
        this.MiddleTusk.addBox(-1.0f, 0.0f, -12.0f, 2, 2, 12);
        this.MiddleTusk.setPos(0.0f, -2.0f, -10.0f);
        this.MiddleTusk.mirror = true;
        this.setRotation(this.MiddleTusk, 0.122173f, 0.0f, 0.0f);
        this.RightTusk = new ModelRenderer(this, 76, 50);
        this.RightTusk.addBox(-2.0f, 0.0f, -12.0f, 2, 2, 12);
        this.RightTusk.setPos(-5.0f, 6.0f, -10.0f);
        this.RightTusk.mirror = true;
        this.setRotation(this.RightTusk, 0.1047198f, -0.0872665f, 0.0f);
        this.LeftFrontUpperLeg = new ModelRenderer(this, 64, 0);
        this.LeftFrontUpperLeg.addBox(0.0f, 0.0f, -1.5f, 3, 8, 3);
        this.LeftFrontUpperLeg.setPos(8.0f, 8.0f, -2.0f);
        this.LeftFrontUpperLeg.mirror = true;
        this.setRotation(this.LeftFrontUpperLeg, -0.1745329f, 0.0f, -0.6632251f);
        this.LeftFrontLowerLeg = new ModelRenderer(this, 48, 0);
        this.LeftFrontLowerLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2);
        this.LeftFrontLowerLeg.setPos(14.0f, 13.0f, -3.5f);
        this.LeftFrontLowerLeg.mirror = true;
        this.setRotation(this.LeftFrontLowerLeg, -0.2617994f, 0.1396263f, 0.0f);
        this.LeftRearUpperLeg = new ModelRenderer(this, 64, 0);
        this.LeftRearUpperLeg.addBox(-1.0f, 0.0f, -1.5f, 3, 8, 3);
        this.LeftRearUpperLeg.setPos(8.0f, 9.0f, 7.0f);
        this.LeftRearUpperLeg.mirror = true;
        this.setRotation(this.LeftRearUpperLeg, 0.1745329f, 0.0f, -0.8203047f);
        this.LeftRearLowerLeg = new ModelRenderer(this, 48, 0);
        this.LeftRearLowerLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2);
        this.LeftRearLowerLeg.setPos(14.0f, 14.0f, 8.5f);
        this.LeftRearLowerLeg.mirror = true;
        this.setRotation(this.LeftRearLowerLeg, 0.3141593f, -0.1570796f, -0.2792527f);
        this.RightFrontUpperLeg = new ModelRenderer(this, 64, 0);
        this.RightFrontUpperLeg.addBox(-3.0f, 0.0f, -1.5f, 3, 8, 3);
        this.RightFrontUpperLeg.setPos(-8.0f, 8.0f, -2.0f);
        this.RightFrontUpperLeg.mirror = true;
        this.setRotation(this.RightFrontUpperLeg, -0.1745329f, 0.0f, 0.6632251f);
        this.RightFrontLowerLeg = new ModelRenderer(this, 48, 0);
        this.RightFrontLowerLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2);
        this.RightFrontLowerLeg.setPos(-14.0f, 13.0f, -3.5f);
        this.RightFrontLowerLeg.mirror = true;
        this.setRotation(this.RightFrontLowerLeg, -0.2617994f, -0.1396263f, 0.0f);
        this.RightRearUpperLeg = new ModelRenderer(this, 64, 0);
        this.RightRearUpperLeg.addBox(-2.0f, 0.0f, -1.5f, 3, 8, 3);
        this.RightRearUpperLeg.setPos(-8.0f, 9.0f, 7.0f);
        this.RightRearUpperLeg.mirror = true;
        this.setRotation(this.RightRearUpperLeg, 0.1745329f, 0.0f, 0.8203047f);
        this.RightRearLowerLeg = new ModelRenderer(this, 48, 0);
        this.RightRearLowerLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2);
        this.RightRearLowerLeg.setPos(-14.0f, 14.0f, 8.5f);
        this.RightRearLowerLeg.mirror = true;
        this.setRotation(this.RightRearLowerLeg, 0.3141593f, 0.1570796f, 0.2792527f);
        this.Core = new ModelRenderer(this, 82, 33);
        this.Core.addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
        this.Core.setPos(0.0f, 8.0f, 3.0f);
        this.Core.mirror = true;
        this.setRotation(this.Core, -0.122173f, 0.0f, 0.0f);
        this.Bellyinside = new ModelRenderer(this, 150, 3);
        this.Bellyinside.addBox(-8.0f, -1.0f, -8.0f, 16, 1, 16);
        this.Bellyinside.setPos(0.0f, 8.0f, 2.0f);
        this.Bellyinside.mirror = true;
        this.setRotation(this.Bellyinside, -0.122173f, 0.0f, 0.0f);
        this.Bellyoutside = new ModelRenderer(this, 0, 0);
        this.Bellyoutside.addBox(-8.0f, -6.0f, -8.0f, 16, 14, 16);
        this.Bellyoutside.setPos(0.0f, 8.0f, 2.0f);
        this.Bellyoutside.mirror = true;
        this.setRotation(this.Bellyoutside, -0.122173f, 0.0f, 0.0f);
        this.Shell1 = new ModelRenderer(this, 64, 0);
        this.Shell1.addBox(-10.0f, -10.0f, 2.0f, 19, 19, 12);
        this.Shell1.setPos(0.0f, 4.0f, -7.0f);
        this.Shell1.mirror = true;
        this.setRotation(this.Shell1, 0.0f, 0.0f, 0.7853982f);
        this.Shell2 = new ModelRenderer(this, 0, 30);
        this.Shell2.addBox(-9.0f, -9.0f, 0.0f, 16, 16, 8);
        this.Shell2.setPos(0.0f, 4.5f, 5.0f);
        this.Shell2.mirror = true;
        this.setRotation(this.Shell2, -0.5235988f, 0.3665191f, 0.715585f);
    }
    @Override
    public void setupAnim(GammaMetroid entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        GammaMetroid e = (GammaMetroid)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        this.LeftTusk.xRot = newangle = MathHelper.cos((float)(f2 * 0.81f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.RightTusk.xRot = newangle = MathHelper.cos((float)(f2 * 0.87f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.MiddleTusk.xRot = newangle = MathHelper.cos((float)(f2 * 0.99f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.LeftTusk.yRot = newangle = MathHelper.cos((float)(f2 * 1.11f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.RightTusk.yRot = newangle = MathHelper.cos((float)(f2 * 1.17f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.MiddleTusk.yRot = newangle = MathHelper.cos((float)(f2 * 1.25f * this.wingspeed)) * 3.1415927f * 0.08f;
        float nextangle = 0.0f;
        float upangle = 0.0f;
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * f1 - Math.abs(newangle);
        }
        this.doLeftFLeg(this.LeftFrontUpperLeg, this.LeftFrontLowerLeg, newangle, upangle);
        this.doRightFLeg(this.RightFrontUpperLeg, this.RightFrontLowerLeg, - newangle, upangle);
        this.doLeftRLeg(this.LeftRearUpperLeg, this.LeftRearLowerLeg, - newangle, upangle);
        this.doRightRLeg(this.RightRearUpperLeg, this.RightRearLowerLeg, newangle, upangle);
        newangle = MathHelper.cos((float)(f2 * 0.4f * this.wingspeed)) * 3.1415927f * 0.05f;
        if (e.isOrderedToSit()) {
            newangle = 0.0f;
        }
        this.Shell1.xRot = newangle / 4.0f;
        this.Shell1.yRot = - newangle / 4.0f;
        this.Shell2.xRot = newangle - 0.49f;
        this.Shell2.yRot = - newangle + 0.33f;
        this.Shell3.xRot = newangle - 0.96f;
        this.Shell3.yRot = - newangle + 0.63f;
        this.Shell4.xRot = newangle - 0.28f;
        newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed)) * 3.1415927f * 0.1f;
        newangle = Math.abs(newangle);
        this.BeakLower.xRot = newangle + 0.14f;
        this.BeakLower.zRot = newangle + 0.14f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        this.Core.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BeakUpper.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BeakLower.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftTusk.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MiddleTusk.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightTusk.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftFrontUpperLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftFrontLowerLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftRearUpperLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftRearLowerLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightFrontUpperLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightFrontLowerLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightRearUpperLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightRearLowerLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bellyinside.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bellyoutside.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RenderSystem.disableBlend();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity) {
        
    }

    private void doLeftFLeg(ModelRenderer seg2, ModelRenderer seg3, float angle, float upangle) {
        seg2.xRot = angle - 0.17f;
        seg3.xRot = angle - 0.26f;
        seg3.z = (float)((double)seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = - upangle - 0.66f;
        seg3.zRot = - upangle;
        seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
        seg3.x = (float)((double)seg2.x + Math.abs(Math.sin(seg2.zRot) * 7.0) + 1.0);
    }

    private void doLeftRLeg(ModelRenderer seg2, ModelRenderer seg3, float angle, float upangle) {
        seg2.xRot = angle + 0.17f;
        seg3.xRot = angle + 0.31f;
        seg3.z = (float)((double)seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = - upangle - 0.82f;
        seg3.zRot = - upangle;
        seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
        seg3.x = (float)((double)seg2.x + Math.abs(Math.sin(seg2.zRot) * 7.0) + 1.5);
    }

    private void doRightFLeg(ModelRenderer seg2, ModelRenderer seg3, float angle, float upangle) {
        seg2.xRot = angle - 0.17f;
        seg3.xRot = angle - 0.26f;
        seg3.z = (float)((double)seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = - upangle + 0.34f;
        seg3.zRot = - upangle;
        seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
        seg3.x = (float)((double)seg2.x - Math.abs(Math.sin(seg2.zRot) * 7.0) - 1.0);
    }

    private void doRightRLeg(ModelRenderer seg2, ModelRenderer seg3, float angle, float upangle) {
        seg2.xRot = angle + 0.17f;
        seg3.xRot = angle + 0.31f;
        seg3.z = (float)((double)seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = - upangle + 0.82f;
        seg3.zRot = - upangle;
        seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
        seg3.x = (float)((double)seg2.x - Math.abs(Math.sin(seg2.zRot) * 7.0) - 1.5);
    }
}

