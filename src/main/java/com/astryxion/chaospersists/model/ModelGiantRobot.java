/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GiantRobot
 *  com.astryxion.chaospersists.ModelGiantRobot
 *  com.astryxion.chaospersists.RenderGiantRobotInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.render.RenderGiantRobotInfo;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelGiantRobot extends EntityModel<GiantRobot> {
    private float wingspeed = 1.0f;
    private float hipy = 0.0f;
    ModelRenderer Hip;
    ModelRenderer Thigh;
    ModelRenderer Shin;
    ModelRenderer Foot1;
    ModelRenderer Foot2;
    ModelRenderer Foot3;
    ModelRenderer Thigh2;
    ModelRenderer Thigh3;
    ModelRenderer Back1;
    ModelRenderer Back2;
    ModelRenderer Back3;
    ModelRenderer Shoulders;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer Arm1;
    ModelRenderer Arm2;
    ModelRenderer Arm3;
    ModelRenderer Knuckles;

    public ModelGiantRobot(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 512;
        this.Hip = new ModelRenderer(this, 0, 0);
        this.Hip.addBox(-4.0f, -4.0f, -15.0f, 8, 8, 30);
        this.Hip.setPos(0.0f, -60.0f, 0.0f);
        this.Hip.mirror = true;
        this.setRotation(this.Hip, 0.0f, 0.0f, 0.0f);
        this.Thigh = new ModelRenderer(this, 0, 115);
        this.Thigh.addBox(-3.0f, -3.0f, -3.0f, 6, 43, 6);
        this.Thigh.setPos(0.0f, -58.0f, 0.0f);
        this.Thigh.mirror = true;
        this.setRotation(this.Thigh, 0.0f, 0.0f, 0.0f);
        this.Shin = new ModelRenderer(this, 0, 167);
        this.Shin.addBox(-3.0f, -3.0f, -3.0f, 6, 43, 6);
        this.Shin.setPos(0.0f, -18.0f, 0.0f);
        this.Shin.mirror = true;
        this.setRotation(this.Shin, 0.0f, 0.0f, 0.0f);
        this.Foot1 = new ModelRenderer(this, 0, 282);
        this.Foot1.addBox(-7.0f, 38.0f, -11.0f, 14, 4, 17);
        this.Foot1.setPos(0.0f, -18.0f, 0.0f);
        this.Foot1.mirror = true;
        this.setRotation(this.Foot1, 0.0f, 0.0f, 0.0f);
        this.Foot2 = new ModelRenderer(this, 0, 246);
        this.Foot2.addBox(-6.0f, 19.0f, -8.0f, 12, 19, 13);
        this.Foot2.setPos(0.0f, -18.0f, 0.0f);
        this.Foot2.mirror = true;
        this.setRotation(this.Foot2, 0.0f, 0.0f, 0.0f);
        this.Foot3 = new ModelRenderer(this, 0, 219);
        this.Foot3.addBox(-5.0f, 5.0f, -5.0f, 10, 14, 9);
        this.Foot3.setPos(0.0f, -18.0f, 0.0f);
        this.Foot3.mirror = true;
        this.setRotation(this.Foot3, 0.0f, 0.0f, 0.0f);
        this.Thigh2 = new ModelRenderer(this, 0, 43);
        this.Thigh2.addBox(-7.0f, -8.0f, -7.0f, 14, 24, 14);
        this.Thigh2.setPos(0.0f, -58.0f, 0.0f);
        this.Thigh2.mirror = true;
        this.setRotation(this.Thigh2, 0.0f, 0.0f, 0.0f);
        this.Thigh3 = new ModelRenderer(this, 0, 84);
        this.Thigh3.addBox(-5.0f, 16.0f, -5.0f, 10, 17, 10);
        this.Thigh3.setPos(0.0f, -58.0f, 0.0f);
        this.Thigh3.mirror = true;
        this.setRotation(this.Thigh3, 0.0f, 0.0f, 0.0f);
        this.Back1 = new ModelRenderer(this, 125, 138);
        this.Back1.addBox(-4.0f, -20.0f, -4.0f, 8, 24, 8);
        this.Back1.setPos(0.0f, -60.0f, 0.0f);
        this.Back1.mirror = true;
        this.setRotation(this.Back1, 0.0f, 0.0f, 0.0f);
        this.Back2 = new ModelRenderer(this, 125, 95);
        this.Back2.addBox(-13.0f, -42.0f, -10.0f, 26, 24, 16);
        this.Back2.setPos(0.0f, -60.0f, 0.0f);
        this.Back2.mirror = true;
        this.setRotation(this.Back2, 0.0f, 0.0f, 0.0f);
        this.Back3 = new ModelRenderer(this, 125, 43);
        this.Back3.addBox(-17.0f, -68.0f, -13.0f, 34, 26, 20);
        this.Back3.setPos(0.0f, -60.0f, 0.0f);
        this.Back3.mirror = true;
        this.setRotation(this.Back3, 0.0f, 0.0f, 0.0f);
        this.Shoulders = new ModelRenderer(this, 60, 200);
        this.Shoulders.addBox(-22.0f, -64.0f, -4.0f, 44, 8, 8);
        this.Shoulders.setPos(0.0f, -60.0f, 0.0f);
        this.Shoulders.mirror = true;
        this.setRotation(this.Shoulders, 0.0f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer(this, 125, 29);
        this.Neck.addBox(-4.0f, -70.0f, -4.0f, 8, 2, 8);
        this.Neck.setPos(0.0f, -60.0f, 0.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 127, 0);
        this.Head.addBox(-7.0f, -82.0f, -7.0f, 14, 12, 14);
        this.Head.setPos(0.0f, -60.0f, 0.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Arm1 = new ModelRenderer(this, 77, 250);
        this.Arm1.addBox(-6.0f, -6.0f, -6.0f, 12, 21, 12);
        this.Arm1.setPos(28.0f, -120.0f, 0.0f);
        this.Arm1.mirror = true;
        this.setRotation(this.Arm1, 0.0f, 0.0f, 0.0f);
        this.Arm2 = new ModelRenderer(this, 73, 300);
        this.Arm2.addBox(-4.0f, 15.0f, -4.0f, 8, 24, 8);
        this.Arm2.setPos(28.0f, -120.0f, 0.0f);
        this.Arm2.mirror = true;
        this.setRotation(this.Arm2, 0.0f, 0.0f, 0.0f);
        this.Arm3 = new ModelRenderer(this, 61, 350);
        this.Arm3.addBox(-3.0f, -3.0f, -3.0f, 6, 33, 6);
        this.Arm3.setPos(28.0f, -81.0f, 0.0f);
        this.Arm3.mirror = true;
        this.setRotation(this.Arm3, 0.0f, 0.0f, 0.0f);
        this.Knuckles = new ModelRenderer(this, 56, 400);
        this.Knuckles.addBox(-7.0f, 30.0f, -5.0f, 14, 12, 10);
        this.Knuckles.setPos(28.0f, -81.0f, 0.0f);
        this.Knuckles.mirror = true;
        this.setRotation(this.Knuckles, 0.0f, 0.0f, 0.0f);
        this.hipy = this.Hip.y;
    }
    @Override
    public void setupAnim(GiantRobot e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float b2angle;
        float a2angle;
        RenderGiantRobotInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        r = e.getRenderGiantRobotInfo();
        float movescale = f1 * 0.65f;
        if (movescale > 1.0f) {
            movescale = 1.0f;
        }
        r.hipxdisplayangle = (float)(Math.cos((- f2) * this.wingspeed) * 3.141592653589793 * 0.10000000149011612 * (double)movescale);
        r.hipydisplayangle = (float)(Math.sin((- f2) * this.wingspeed) * 3.141592653589793 * 0.10000000149011612 * (double)movescale);
        r.thighdisplayangle[0] = (float)(Math.cos((double)((- f2) * this.wingspeed) + 1.5707963267948966) * 3.141592653589793 * 0.15000000596046448 * (double)movescale) - (float)(0.19634954084936207 * (double)movescale);
        r.thighdisplayangle[1] = (float)(Math.cos((double)((- f2) * this.wingspeed) + 3.141592653589793 + 1.5707963267948966) * 3.141592653589793 * 0.15000000596046448 * (double)movescale) - (float)(0.19634954084936207 * (double)movescale);
        r.shindisplayangle[0] = (float)((double)((float)(Math.cos((double)((- f2) * this.wingspeed) + 3.141592653589793) * 3.141592653589793 * 0.20000000298023224 * (double)movescale)) + 0.6283185400806344 * (double)movescale);
        r.shindisplayangle[1] = (float)((double)((float)(Math.cos((- f2) * this.wingspeed) * 3.141592653589793 * 0.20000000298023224 * (double)movescale)) + 0.6283185400806344 * (double)movescale);
        float newangle = (float)(Math.cos((- f2) * this.wingspeed * 2.0f) * (double)movescale);
        this.Hip.y = this.hipy + newangle * 4.0f;
        this.Hip.xRot = r.hipxdisplayangle;
        this.Hip.yRot = (float)((double)r.hipydisplayangle + 1.5707963267948966);
        
        this.Thigh2.xRot = this.Thigh3.xRot = r.thighdisplayangle[0];
        this.Thigh.xRot = this.Thigh3.xRot;
        this.Thigh2.y = this.Thigh3.y = this.Hip.y - (float)Math.sin(this.Hip.xRot) * 13.0f;
        this.Thigh.y = this.Thigh3.y;
        this.Thigh2.z = this.Thigh3.z = this.Hip.z + (float)Math.cos(this.Hip.xRot) * (float)Math.cos(this.Hip.yRot) * 13.0f;
        this.Thigh.z = this.Thigh3.z;
        this.Thigh2.x = this.Thigh3.x = this.Hip.x + (float)Math.cos(this.Hip.xRot) * (float)Math.sin(this.Hip.yRot) * 13.0f;
        this.Thigh.x = this.Thigh3.x;
        
        
        
        this.Shin.xRot = r.shindisplayangle[0];
        this.Shin.y = this.Thigh.y + (float)Math.cos(this.Thigh.xRot) * 40.0f;
        this.Shin.z = this.Thigh.z + (float)Math.sin(this.Thigh.xRot) * 40.0f;
        this.Shin.x = this.Thigh.x;
        
        this.Foot2.xRot = this.Foot3.xRot = r.shindisplayangle[0];
        this.Foot1.xRot = this.Foot3.xRot;
        this.Foot2.y = this.Foot3.y = this.Shin.y;
        this.Foot1.y = this.Foot3.y;
        this.Foot2.z = this.Foot3.z = this.Shin.z;
        this.Foot1.z = this.Foot3.z;
        this.Foot2.x = this.Foot3.x = this.Shin.x;
        this.Foot1.x = this.Foot3.x;
        
        
        
        this.Thigh2.xRot = this.Thigh3.xRot = r.thighdisplayangle[1];
        this.Thigh.xRot = this.Thigh3.xRot;
        this.Thigh2.y = this.Thigh3.y = this.Hip.y + (float)Math.sin(this.Hip.xRot) * 13.0f;
        this.Thigh.y = this.Thigh3.y;
        this.Thigh2.z = this.Thigh3.z = this.Hip.z - (float)Math.cos(this.Hip.xRot) * (float)Math.cos(this.Hip.yRot) * 13.0f;
        this.Thigh.z = this.Thigh3.z;
        this.Thigh2.x = this.Thigh3.x = this.Hip.x - (float)Math.cos(this.Hip.xRot) * (float)Math.sin(this.Hip.yRot) * 13.0f;
        this.Thigh.x = this.Thigh3.x;
        
        
        
        this.Shin.xRot = r.shindisplayangle[1];
        this.Shin.y = this.Thigh.y + (float)Math.cos(this.Thigh.xRot) * 40.0f;
        this.Shin.z = this.Thigh.z + (float)Math.sin(this.Thigh.xRot) * 40.0f;
        this.Shin.x = this.Thigh.x;
        
        this.Foot2.xRot = this.Foot3.xRot = r.shindisplayangle[1];
        this.Foot1.xRot = this.Foot3.xRot;
        this.Foot2.y = this.Foot3.y = this.Shin.y;
        this.Foot1.y = this.Foot3.y;
        this.Foot2.z = this.Foot3.z = this.Shin.z;
        this.Foot1.z = this.Foot3.z;
        this.Foot2.x = this.Foot3.x = this.Shin.x;
        this.Foot1.x = this.Foot3.x;
        
        
        
        float shoulderangle = - r.hipydisplayangle;
        float a1angle = a2angle = r.thighdisplayangle[1];
        float b1angle = b2angle = r.thighdisplayangle[0];
        if (e.getAttacking() != 0) {
            shoulderangle = (float)(- Math.sin(f2 * this.wingspeed * 2.0f) * 3.141592653589793 * 0.20000000298023224);
            a1angle = (float)((double)((float)(Math.sin(f2 * this.wingspeed * 2.0f) * 3.141592653589793 / 5.0)) - 0.7853981633974483);
            a2angle = (float)((double)(- a1angle) + 3.141592653589793);
            a1angle = (float)((double)a1angle + 0.6283185307179586);
            a2angle = (float)((double)a2angle + 0.6283185307179586);
            b1angle = (float)((double)((float)(- Math.sin(f2 * this.wingspeed * 2.0f) * 3.141592653589793 / 5.0)) - 0.7853981633974483);
            b2angle = (float)((double)(- b1angle) + 3.141592653589793);
            b1angle = (float)((double)b1angle + 0.6283185307179586);
            b2angle = (float)((double)b2angle + 0.6283185307179586);
        }
        this.Back3.yRot = shoulderangle / 2.0f;
        this.Shoulders.yRot = shoulderangle;
        this.Arm1.y = this.Arm2.y = this.Hip.y - 60.0f;
        this.Arm1.x = this.Arm2.x = this.Hip.x + 26.0f;
        this.Arm1.z = this.Arm2.z = this.Shoulders.z - (float)Math.sin(this.Shoulders.yRot) * 26.0f;
        this.Arm1.xRot = this.Arm2.xRot = a1angle;
        
        
        this.Arm3.xRot = this.Knuckles.xRot = (float)((double)a2angle - 0.19634954084936207);
        this.Arm3.y = this.Knuckles.y = this.Arm1.y + (float)Math.cos(this.Arm1.xRot) * 41.0f;
        this.Arm3.z = this.Knuckles.z = this.Arm1.z + (float)Math.sin(this.Arm1.xRot) * 41.0f;
        this.Arm3.x = this.Knuckles.x = this.Arm1.x;
        
        
        this.Arm1.y = this.Arm2.y = this.Hip.y - 60.0f;
        this.Arm1.x = this.Arm2.x = this.Hip.x - 26.0f;
        this.Arm1.z = this.Arm2.z = this.Shoulders.z + (float)Math.sin(this.Shoulders.yRot) * 26.0f;
        this.Arm1.xRot = this.Arm2.xRot = b1angle;
        
        
        this.Arm3.xRot = this.Knuckles.xRot = (float)((double)b2angle - 0.19634954084936207);
        this.Arm3.y = this.Knuckles.y = this.Arm1.y + (float)Math.cos(this.Arm1.xRot) * 41.0f;
        this.Arm3.z = this.Knuckles.z = this.Arm1.z + (float)Math.sin(this.Arm1.xRot) * 41.0f;
        this.Arm3.x = this.Knuckles.x = this.Arm1.x;
        
        
        this.Back2.y = this.Back3.y = this.Hip.y;
        this.Back1.y = this.Back3.y;
        this.Neck.y = this.Head.y = this.Hip.y;
        this.Shoulders.y = this.Head.y;
        this.Head.yRot = (float)Math.toRadians(f3);
        this.Head.xRot = (float)Math.toRadians(f4) / 3.0f;
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Hip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Knuckles.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Knuckles.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Back1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Back2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Back3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shoulders.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    private void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, GiantRobot par7Entity) {
        
    }
}

