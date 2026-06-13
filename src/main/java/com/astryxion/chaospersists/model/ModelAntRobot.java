/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.AntRobot
 *  com.astryxion.chaospersists.ModelAntRobot
 *  com.astryxion.chaospersists.RenderSpiderRobotInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.render.RenderSpiderRobotInfo;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelAntRobot extends EntityModel<AntRobot> {
    private float wingspeed = 1.0f;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Foot1;
    ModelRenderer Foot2;
    ModelRenderer Foot3;
    ModelRenderer Foot4;
    ModelRenderer Foot5;
    ModelRenderer Foot6;
    ModelRenderer Foot7;
    ModelRenderer Body;
    ModelRenderer Abdomen;
    ModelRenderer Head;
    ModelRenderer Jet1;
    ModelRenderer Jet2;
    ModelRenderer Hip1;
    ModelRenderer Hip2;
    ModelRenderer LJaw1;
    ModelRenderer RJaw1;
    ModelRenderer LJaw2;
    ModelRenderer RJaw2;
    ModelRenderer LAntenna;
    ModelRenderer RAntenna;
    ModelRenderer Hip3;
    ModelRenderer Hip4;
    ModelRenderer Hip5;
    ModelRenderer Hip6;

    public ModelAntRobot(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 256;
        this.Leg1 = new ModelRenderer(this, 19, 40);
        this.Leg1.addBox(-1.5f, -1.5f, 0.0f, 3, 3, 50);
        this.Leg1.setPos(0.0f, 0.0f, 0.0f);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.7853982f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer(this, 19, 41);
        this.Leg2.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 50);
        this.Leg2.setPos(0.0f, -35.0f, 35.0f);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer(this, 20, 42);
        this.Leg3.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 50);
        this.Leg3.setPos(0.0f, -35.0f, 85.0f);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, -0.7853982f, 0.0f, 0.0f);
        this.Foot1 = new ModelRenderer(this, 28, 0);
        this.Foot1.addBox(-2.5f, -0.5f, 50.0f, 5, 1, 2);
        this.Foot1.setPos(0.0f, -35.0f, 85.0f);
        this.Foot1.mirror = true;
        this.setRotation(this.Foot1, -0.7853982f, 0.0f, 0.0f);
        this.Foot2 = new ModelRenderer(this, 30, 4);
        this.Foot2.addBox(1.5f, -0.5f, 52.0f, 1, 1, 3);
        this.Foot2.setPos(0.0f, -35.0f, 85.0f);
        this.Foot2.mirror = true;
        this.setRotation(this.Foot2, -0.7853982f, 0.0f, 0.0f);
        this.Foot3 = new ModelRenderer(this, 44, 0);
        this.Foot3.addBox(-0.5f, -0.5f, 52.0f, 1, 1, 5);
        this.Foot3.setPos(0.0f, -35.0f, 85.0f);
        this.Foot3.mirror = true;
        this.setRotation(this.Foot3, -0.7853982f, 0.0f, 0.0f);
        this.Foot4 = new ModelRenderer(this, 30, 9);
        this.Foot4.addBox(-2.5f, -0.5f, 52.0f, 1, 1, 3);
        this.Foot4.setPos(0.0f, -35.0f, 85.0f);
        this.Foot4.mirror = true;
        this.setRotation(this.Foot4, -0.7853982f, 0.0f, 0.0f);
        this.Foot5 = new ModelRenderer(this, 40, 8);
        this.Foot5.addBox(-0.5f, -2.5f, 50.0f, 1, 5, 2);
        this.Foot5.setPos(0.0f, -35.0f, 85.0f);
        this.Foot5.mirror = true;
        this.setRotation(this.Foot5, -0.7853982f, 0.0f, 0.0f);
        this.Foot6 = new ModelRenderer(this, 48, 9);
        this.Foot6.addBox(-0.5f, -2.5f, 52.0f, 1, 1, 2);
        this.Foot6.setPos(0.0f, -35.0f, 85.0f);
        this.Foot6.mirror = true;
        this.setRotation(this.Foot6, -0.7853982f, 0.0f, 0.0f);
        this.Foot7 = new ModelRenderer(this, 48, 14);
        this.Foot7.addBox(-0.5f, 1.5f, 52.0f, 1, 1, 2);
        this.Foot7.setPos(0.0f, -35.0f, 85.0f);
        this.Foot7.mirror = true;
        this.setRotation(this.Foot7, -0.7853982f, 0.0f, 0.0f);
        this.Body = new ModelRenderer(this, 0, 151);
        this.Body.addBox(-11.0f, 0.0f, -16.0f, 22, 14, 32);
        this.Body.setPos(0.0f, 0.0f, 0.0f);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Abdomen = new ModelRenderer(this, 0, 199);
        this.Abdomen.addBox(-15.0f, -10.0f, 16.0f, 30, 22, 34);
        this.Abdomen.setPos(0.0f, 0.0f, 0.0f);
        this.Abdomen.mirror = true;
        this.setRotation(this.Abdomen, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 0, 120);
        this.Head.addBox(-7.0f, 4.0f, -34.0f, 14, 11, 18);
        this.Head.setPos(0.0f, 0.0f, 0.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Jet1 = new ModelRenderer(this, 78, 0);
        this.Jet1.addBox(0.0f, 0.0f, 0.0f, 6, 6, 18);
        this.Jet1.setPos(8.0f, -12.0f, 35.0f);
        this.Jet1.mirror = true;
        this.setRotation(this.Jet1, 0.0f, 0.0f, 0.0f);
        this.Jet2 = new ModelRenderer(this, 78, 0);
        this.Jet2.addBox(0.0f, 0.0f, 0.0f, 6, 6, 18);
        this.Jet2.setPos(-14.0f, -12.0f, 35.0f);
        this.Jet2.mirror = true;
        this.setRotation(this.Jet2, 0.0f, 0.0f, 0.0f);
        this.Hip1 = new ModelRenderer(this, 0, 96);
        this.Hip1.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Hip1.setPos(11.0f, 9.0f, -3.0f);
        this.Hip1.mirror = true;
        this.setRotation(this.Hip1, 0.0f, 0.0f, 0.0f);
        this.Hip2 = new ModelRenderer(this, 0, 96);
        this.Hip2.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Hip2.setPos(-17.0f, 9.0f, -3.0f);
        this.Hip2.mirror = true;
        this.setRotation(this.Hip2, 0.0f, 0.0f, 0.0f);
        this.LJaw1 = new ModelRenderer(this, 0, 33);
        this.LJaw1.addBox(-2.0f, 0.0f, -2.0f, 17, 1, 4);
        this.LJaw1.setPos(5.0f, 13.0f, -33.0f);
        this.LJaw1.mirror = true;
        this.setRotation(this.LJaw1, 0.0f, 0.8901179f, 0.0f);
        this.RJaw1 = new ModelRenderer(this, 0, 33);
        this.RJaw1.addBox(-2.0f, 0.0f, -2.0f, 17, 1, 4);
        this.RJaw1.setPos(-5.0f, 13.0f, -33.0f);
        this.RJaw1.mirror = true;
        this.setRotation(this.RJaw1, 0.0f, 2.216568f, 0.0f);
        this.LJaw2 = new ModelRenderer(this, 0, 27);
        this.LJaw2.addBox(12.0f, 0.0f, 5.0f, 17, 1, 3);
        this.LJaw2.setPos(5.0f, 13.0f, -33.0f);
        this.LJaw2.mirror = true;
        this.setRotation(this.LJaw2, 0.0f, 1.37881f, 0.0f);
        this.RJaw2 = new ModelRenderer(this, 0, 27);
        this.RJaw2.addBox(12.0f, 0.0f, -8.0f, 17, 1, 3);
        this.RJaw2.setPos(-5.0f, 13.0f, -33.0f);
        this.RJaw2.mirror = true;
        this.setRotation(this.RJaw2, 0.0f, 1.745329f, 0.0f);
        this.LAntenna = new ModelRenderer(this, 70, 0);
        this.LAntenna.addBox(-0.5f, -12.0f, -0.5f, 1, 12, 1);
        this.LAntenna.setPos(0.0f, 4.0f, -32.0f);
        this.LAntenna.mirror = true;
        this.setRotation(this.LAntenna, 0.0f, 0.0f, 0.5410521f);
        this.RAntenna = new ModelRenderer(this, 70, 0);
        this.RAntenna.addBox(-0.5f, -12.0f, -0.5f, 1, 12, 1);
        this.RAntenna.setPos(0.0f, 4.0f, -32.0f);
        this.RAntenna.mirror = true;
        this.setRotation(this.RAntenna, 0.0f, 0.0f, -0.5410521f);
        this.Hip3 = new ModelRenderer(this, 0, 96);
        this.Hip3.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Hip3.setPos(-17.0f, 9.0f, 10.0f);
        this.Hip3.mirror = true;
        this.setRotation(this.Hip3, 0.0f, 0.0f, 0.0f);
        this.Hip4 = new ModelRenderer(this, 0, 96);
        this.Hip4.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Hip4.setPos(11.0f, 9.0f, 10.0f);
        this.Hip4.mirror = true;
        this.setRotation(this.Hip4, 0.0f, 0.0f, 0.0f);
        this.Hip5 = new ModelRenderer(this, 0, 96);
        this.Hip5.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Hip5.setPos(11.0f, 9.0f, -16.0f);
        this.Hip5.mirror = true;
        this.setRotation(this.Hip5, 0.0f, 0.0f, 0.0f);
        this.Hip6 = new ModelRenderer(this, 0, 96);
        this.Hip6.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Hip6.setPos(-17.0f, 9.0f, -16.0f);
        this.Hip6.mirror = true;
        this.setRotation(this.Hip6, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(AntRobot e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        RenderSpiderRobotInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        r = e.getRenderSpiderRobotInfo();
        for (int i = 0; i < 6; ++i) {
            this.Leg2.yRot = this.Leg3.yRot = r.ydisplayangle[i];
            this.Leg1.yRot = this.Leg3.yRot;
            this.Foot1.yRot = r.ydisplayangle[i];
            this.Foot2.yRot = r.ydisplayangle[i];
            this.Foot3.yRot = r.ydisplayangle[i];
            this.Foot4.yRot = r.ydisplayangle[i];
            this.Foot5.yRot = r.ydisplayangle[i];
            this.Foot6.yRot = r.ydisplayangle[i];
            this.Foot7.yRot = r.ydisplayangle[i];
            this.Leg1.xRot = (float)r.p1xangle[i] + r.uddisplayangle[i];
            this.Leg2.xRot = (float)r.p2xangle[i] + r.uddisplayangle[i];
            this.Foot1.xRot = this.Leg3.xRot = (float)r.p3xangle[i] + r.uddisplayangle[i];
            this.Foot2.xRot = this.Leg3.xRot;
            this.Foot3.xRot = this.Leg3.xRot;
            this.Foot4.xRot = this.Leg3.xRot;
            this.Foot5.xRot = this.Leg3.xRot;
            this.Foot6.xRot = this.Leg3.xRot;
            this.Foot7.xRot = this.Leg3.xRot;
            this.Leg1.x = (- (float)Math.cos(r.ymid[i])) * r.legoff[i] * 16.0f;
            this.Leg1.z = (float)Math.sin(r.ymid[i]) * r.legoff[i] * 16.0f;
            this.Leg1.y = r.yoff[i] * -16.0f;
            this.Leg2.y = this.Leg1.y - (float)Math.sin(this.Leg1.xRot) * 49.0f;
            this.Leg2.z = this.Leg1.z + (float)Math.cos(this.Leg1.xRot) * (float)Math.cos(this.Leg1.yRot) * 49.0f;
            this.Leg2.x = this.Leg1.x + (float)Math.cos(this.Leg1.xRot) * (float)Math.sin(this.Leg1.yRot) * 49.0f;
            this.Leg3.y = this.Leg2.y - (float)Math.sin(this.Leg2.xRot) * 49.0f;
            this.Leg3.z = this.Leg2.z + (float)Math.cos(this.Leg2.xRot) * (float)Math.cos(this.Leg2.yRot) * 49.0f;
            this.Foot1.x = this.Leg3.x = this.Leg2.x + (float)Math.cos(this.Leg2.xRot) * (float)Math.sin(this.Leg2.yRot) * 49.0f;
            this.Foot1.y = this.Leg3.y;
            this.Foot1.z = this.Leg3.z;
            this.Foot2.x = this.Leg3.x;
            this.Foot2.y = this.Leg3.y;
            this.Foot2.z = this.Leg3.z;
            this.Foot3.x = this.Leg3.x;
            this.Foot3.y = this.Leg3.y;
            this.Foot3.z = this.Leg3.z;
            this.Foot4.x = this.Leg3.x;
            this.Foot4.y = this.Leg3.y;
            this.Foot4.z = this.Leg3.z;
            this.Foot5.x = this.Leg3.x;
            this.Foot5.y = this.Leg3.y;
            this.Foot5.z = this.Leg3.z;
            this.Foot6.x = this.Leg3.x;
            this.Foot6.y = this.Leg3.y;
            this.Foot6.z = this.Leg3.z;
            this.Foot7.x = this.Leg3.x;
            this.Foot7.y = this.Leg3.y;
            this.Foot7.z = this.Leg3.z;
            
            
            
            
            
            
            
            
            
            
        }
        if (e.getAttacking() == 0) {
            this.LJaw1.yRot = 0.89f;
            this.LJaw2.yRot = 1.378f;
            this.RJaw1.yRot = 2.216f;
            this.RJaw2.yRot = 1.745f;
            this.LAntenna.xRot = MathHelper.cos((float)((float)r.gpcounter * 0.35f)) * 3.1415927f * 0.05f;
            this.LAntenna.zRot = 0.54f + MathHelper.cos((float)((float)r.gpcounter * 0.25f)) * 3.1415927f * 0.05f;
            this.RAntenna.xRot = MathHelper.cos((float)((float)r.gpcounter * 0.3f)) * 3.1415927f * 0.05f;
            this.RAntenna.zRot = -0.54f + MathHelper.cos((float)((float)r.gpcounter * 0.45f)) * 3.1415927f * 0.05f;
        } else {
            float newangle = MathHelper.cos((float)((float)r.gpcounter * 0.25f)) * 3.1415927f * 0.22f;
            this.LJaw1.yRot = newangle + 0.89f;
            this.LJaw2.yRot = newangle + 1.378f;
            this.RJaw1.yRot = - newangle + 2.216f;
            this.RJaw2.yRot = 1.745f - newangle;
            this.LAntenna.xRot = MathHelper.cos((float)((float)r.gpcounter * 0.45f)) * 3.1415927f * 0.1f;
            this.LAntenna.zRot = 0.54f + MathHelper.cos((float)((float)r.gpcounter * 0.35f)) * 3.1415927f * 0.1f;
            this.RAntenna.xRot = MathHelper.cos((float)((float)r.gpcounter * 0.4f)) * 3.1415927f * 0.1f;
            this.RAntenna.zRot = -0.54f + MathHelper.cos((float)((float)r.gpcounter * 0.55f)) * 3.1415927f * 0.1f;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Jet1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Jet2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hip6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LJaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RJaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LJaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RJaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LAntenna.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RAntenna.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    private void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, AntRobot par7Entity) {
        
    }
}

