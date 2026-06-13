/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Bee
 *  com.astryxion.chaospersists.ModelBee
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Bee;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBee extends EntityModel<Bee> {
    private float wingspeed = 1.0f;
    ModelRenderer Sting;
    ModelRenderer Abdomnem1;
    ModelRenderer Abdomnem2;
    ModelRenderer Abdomnem3;
    ModelRenderer Abdomnem4;
    ModelRenderer Abdomnem5;
    ModelRenderer MainBody;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer WingRight;
    ModelRenderer WingLeft;
    ModelRenderer RA1;
    ModelRenderer LA1;
    ModelRenderer LA2;
    ModelRenderer RA2;
    ModelRenderer RA3;
    ModelRenderer LA3;
    ModelRenderer LeftPom;
    ModelRenderer RightPom;
    ModelRenderer LeftPincerExtra;
    ModelRenderer LeftPincerMain;
    ModelRenderer RightPincerMain;
    ModelRenderer RightPincerExtra;

    public ModelBee(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 256;
        this.Sting = new ModelRenderer(this, 68, 0);
        this.Sting.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2);
        this.Sting.setPos(0.0f, 16.0f, 1.0f);
        this.Sting.mirror = true;
        this.setRotation(this.Sting, -0.7853982f, 0.0f, 0.0f);
        this.Abdomnem1 = new ModelRenderer(this, 64, 12);
        this.Abdomnem1.addBox(-2.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Abdomnem1.setPos(0.0f, 9.0f, 2.0f);
        this.Abdomnem1.mirror = true;
        this.setRotation(this.Abdomnem1, -0.5235988f, 0.0f, 0.0f);
        this.Abdomnem2 = new ModelRenderer(this, 60, 24);
        this.Abdomnem2.addBox(-3.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Abdomnem2.setPos(0.0f, 5.0f, 0.0f);
        this.Abdomnem2.mirror = true;
        this.setRotation(this.Abdomnem2, 0.0f, 0.0f, 0.0f);
        this.Abdomnem3 = new ModelRenderer(this, 56, 36);
        this.Abdomnem3.addBox(-4.0f, 0.0f, 0.0f, 8, 7, 8);
        this.Abdomnem3.setPos(0.0f, 1.0f, -2.0f);
        this.Abdomnem3.mirror = true;
        this.setRotation(this.Abdomnem3, 0.2617994f, 0.0f, 0.0f);
        this.Abdomnem4 = new ModelRenderer(this, 53, 51);
        this.Abdomnem4.addBox(-5.0f, 0.0f, 0.0f, 10, 12, 10);
        this.Abdomnem4.setPos(0.0f, -6.0f, -8.0f);
        this.Abdomnem4.mirror = true;
        this.setRotation(this.Abdomnem4, 0.5934119f, 0.0f, 0.0f);
        this.Abdomnem5 = new ModelRenderer(this, 48, 73);
        this.Abdomnem5.addBox(-6.0f, 0.0f, 0.0f, 12, 12, 12);
        this.Abdomnem5.setPos(0.0f, -6.0f, -15.0f);
        this.Abdomnem5.mirror = true;
        this.setRotation(this.Abdomnem5, 1.099557f, 0.0f, 0.0f);
        this.MainBody = new ModelRenderer(this, 48, 97);
        this.MainBody.addBox(-6.0f, 0.0f, -6.0f, 12, 14, 12);
        this.MainBody.setPos(0.0f, -12.0f, -24.0f);
        this.MainBody.mirror = true;
        this.setRotation(this.MainBody, 1.48353f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer(this, 55, 123);
        this.Neck.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
        this.Neck.setPos(0.0f, -12.0f, -23.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 51, 139);
        this.Head.addBox(-5.0f, -5.0f, -10.0f, 10, 10, 10);
        this.Head.setPos(0.0f, -13.0f, -28.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.2617994f, 0.0f, 0.0f);
        this.WingRight = new ModelRenderer(this, 0, 91);
        this.WingRight.addBox(0.0f, 0.0f, 0.0f, 0, 8, 24);
        this.WingRight.setPos(-4.0f, -14.0f, -15.0f);
        this.WingRight.mirror = true;
        this.setRotation(this.WingRight, -0.7853982f, -0.5235988f, 2.617994f);
        this.WingLeft = new ModelRenderer(this, 96, 91);
        this.WingLeft.addBox(0.0f, 0.0f, 0.0f, 0, 8, 24);
        this.WingLeft.setPos(3.0f, -14.0f, -15.0f);
        this.WingLeft.mirror = true;
        this.setRotation(this.WingLeft, -0.7853982f, 0.5235988f, -2.617994f);
        this.RA1 = new ModelRenderer(this, 47, 152);
        this.RA1.addBox(0.0f, -6.0f, -1.0f, 1, 6, 1);
        this.RA1.setPos(-3.0f, -17.0f, -31.0f);
        this.RA1.mirror = true;
        this.setRotation(this.RA1, 0.2617994f, 0.5235988f, 0.0f);
        this.LA1 = new ModelRenderer(this, 91, 152);
        this.LA1.addBox(0.0f, -6.0f, -1.0f, 1, 6, 1);
        this.LA1.setPos(2.0f, -17.0f, -32.0f);
        this.LA1.mirror = true;
        this.setRotation(this.LA1, 0.2617994f, -0.5235988f, 0.0f);
        this.LA2 = new ModelRenderer(this, 91, 145);
        this.LA2.addBox(0.0f, -11.0f, 0.0f, 1, 6, 1);
        this.LA2.setPos(2.0f, -17.0f, -32.0f);
        this.LA2.mirror = true;
        this.setRotation(this.LA2, 0.4363323f, -0.6108652f, 0.0f);
        this.RA2 = new ModelRenderer(this, 47, 145);
        this.RA2.addBox(0.0f, -11.0f, 0.0f, 1, 6, 1);
        this.RA2.setPos(-3.0f, -17.0f, -31.0f);
        this.RA2.mirror = true;
        this.setRotation(this.RA2, 0.4363323f, 0.6108652f, 0.0f);
        this.RA3 = new ModelRenderer(this, 47, 138);
        this.RA3.addBox(0.0f, -16.0f, 2.0f, 1, 6, 1);
        this.RA3.setPos(-3.0f, -17.0f, -31.0f);
        this.RA3.mirror = true;
        this.setRotation(this.RA3, 0.6108652f, 0.6981317f, 0.0f);
        this.LA3 = new ModelRenderer(this, 91, 138);
        this.LA3.addBox(0.0f, -16.0f, 2.0f, 1, 6, 1);
        this.LA3.setPos(2.0f, -17.0f, -32.0f);
        this.LA3.mirror = true;
        this.setRotation(this.LA3, 0.6108652f, -0.6981317f, 0.0f);
        this.LeftPom = new ModelRenderer(this, 89, 134);
        this.LeftPom.addBox(4.0f, -16.0f, -6.0f, 2, 2, 2);
        this.LeftPom.setPos(2.0f, -17.0f, -32.0f);
        this.LeftPom.mirror = true;
        this.setRotation(this.LeftPom, 0.0f, 0.0f, 0.0f);
        this.RightPom = new ModelRenderer(this, 45, 134);
        this.RightPom.addBox(-5.0f, -16.0f, -7.0f, 2, 2, 2);
        this.RightPom.setPos(-3.0f, -17.0f, -31.0f);
        this.RightPom.mirror = true;
        this.setRotation(this.RightPom, 0.0f, 0.0f, 0.0f);
        this.LeftPincerExtra = new ModelRenderer(this, 71, 166);
        this.LeftPincerExtra.addBox(-2.0f, 0.0f, -6.0f, 2, 1, 2);
        this.LeftPincerExtra.setPos(2.0f, -8.0f, -36.0f);
        this.LeftPincerExtra.mirror = true;
        this.setRotation(this.LeftPincerExtra, 0.1745329f, -0.1745329f, 0.0f);
        this.LeftPincerMain = new ModelRenderer(this, 71, 159);
        this.LeftPincerMain.addBox(0.0f, 0.0f, -6.0f, 2, 1, 6);
        this.LeftPincerMain.setPos(2.0f, -8.0f, -36.0f);
        this.LeftPincerMain.mirror = true;
        this.setRotation(this.LeftPincerMain, 0.1745329f, -0.1745329f, 0.0f);
        this.RightPincerMain = new ModelRenderer(this, 55, 159);
        this.RightPincerMain.addBox(0.0f, 0.0f, -6.0f, 2, 1, 6);
        this.RightPincerMain.setPos(-4.0f, -8.0f, -36.0f);
        this.RightPincerMain.mirror = true;
        this.setRotation(this.RightPincerMain, 0.1745329f, 0.1745329f, 0.0f);
        this.RightPincerExtra = new ModelRenderer(this, 63, 166);
        this.RightPincerExtra.addBox(2.0f, 0.0f, -6.0f, 2, 1, 2);
        this.RightPincerExtra.setPos(-4.0f, -8.0f, -36.0f);
        this.RightPincerExtra.mirror = true;
        this.setRotation(this.RightPincerExtra, 0.1745329f, 0.1745329f, 0.0f);
    }
    @Override
    public void setupAnim(Bee entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        Bee b = (Bee)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = MathHelper.cos((float)(f2 * 1.1f * this.wingspeed)) * 3.1415927f * 0.3f;
        this.WingLeft.zRot = -1.745f - newangle;
        this.WingRight.zRot = 1.754f + newangle;
        newangle = MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.LeftPincerMain.yRot = -0.274f + newangle;
        this.LeftPincerExtra.yRot = -0.274f + newangle;
        this.RightPincerMain.yRot = 0.274f - newangle;
        this.RightPincerExtra.yRot = 0.274f - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.21f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.LA1.xRot = 0.261f + newangle;
        this.LA2.xRot = 0.436f + newangle;
        this.LA3.xRot = 0.611f + newangle;
        this.LeftPom.xRot = newangle;
        newangle = MathHelper.cos((float)(f2 * 0.27f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.RA1.xRot = 0.261f + newangle;
        this.RA2.xRot = 0.436f + newangle;
        this.RA3.xRot = 0.611f + newangle;
        this.RightPom.xRot = newangle;
        this.LA1.zRot = newangle = MathHelper.cos((float)(f2 * 0.31f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.LA2.zRot = newangle;
        this.LA3.zRot = newangle;
        this.LeftPom.zRot = newangle;
        this.RA1.zRot = newangle = MathHelper.cos((float)(f2 * 0.37f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.RA2.zRot = newangle;
        this.RA3.zRot = newangle;
        this.RightPom.zRot = newangle;
        newangle = b.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.021f * this.wingspeed)) * 3.1415927f * 0.023f : MathHelper.cos((float)(f2 * 0.11f * this.wingspeed)) * 3.1415927f * 0.055f;
        this.Abdomnem5.xRot = 1.099f + newangle;
        this.Abdomnem4.xRot = this.Abdomnem5.xRot + newangle - 0.35f;
        this.Abdomnem4.y = (float)((double)this.Abdomnem5.y + Math.cos(this.Abdomnem5.xRot) * 10.0);
        this.Abdomnem4.z = (float)((double)this.Abdomnem5.z + Math.sin(this.Abdomnem5.xRot) * 10.0);
        this.Abdomnem3.xRot = this.Abdomnem4.xRot + newangle - 0.35f;
        this.Abdomnem3.y = (float)((double)this.Abdomnem4.y + Math.cos(this.Abdomnem4.xRot) * 10.0);
        this.Abdomnem3.z = (float)((double)this.Abdomnem4.z + Math.sin(this.Abdomnem4.xRot) * 10.0);
        this.Abdomnem2.xRot = this.Abdomnem3.xRot + newangle - 0.35f;
        this.Abdomnem2.y = (float)((double)this.Abdomnem3.y + Math.cos(this.Abdomnem3.xRot) * 6.0);
        this.Abdomnem2.z = (float)((double)this.Abdomnem3.z + Math.sin(this.Abdomnem3.xRot) * 6.0);
        this.Abdomnem1.xRot = this.Abdomnem2.xRot + newangle - 0.35f;
        this.Abdomnem1.y = (float)((double)this.Abdomnem2.y + Math.cos(this.Abdomnem2.xRot) * 5.0);
        this.Abdomnem1.z = (float)((double)this.Abdomnem2.z + Math.sin(this.Abdomnem2.xRot) * 5.0);
        this.Sting.xRot = this.Abdomnem1.xRot + newangle - 0.35f;
        this.Sting.y = (float)((double)this.Abdomnem1.y + Math.cos(this.Abdomnem1.xRot) * 7.0);
        this.Sting.z = 1.0f + (float)((double)this.Abdomnem1.z + Math.sin(this.Abdomnem1.xRot) * 7.0);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Sting.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MainBody.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RA1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LA1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LA2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RA2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RA3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LA3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPincerExtra.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPincerMain.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPincerMain.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPincerExtra.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

