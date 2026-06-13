/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.AttackSquid
 *  com.astryxion.chaospersists.ModelAttackSquid
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.AttackSquid;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelAttackSquid extends EntityModel<AttackSquid> {
    private float wingspeed = 1.0f;
    ModelRenderer tent1;
    ModelRenderer tent2;
    ModelRenderer tent3;
    ModelRenderer tent4;
    ModelRenderer tent5;
    ModelRenderer tent6;
    ModelRenderer tent7;
    ModelRenderer body;
    ModelRenderer tent8;

    public ModelAttackSquid(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.tent1 = new ModelRenderer(this, 0, 18);
        this.tent1.addBox(-1.0f, 0.0f, -1.0f, 2, 9, 2);
        this.tent1.setPos(5.0f, 15.0f, -1.0f);
        this.tent1.mirror = true;
        this.setRotation(this.tent1, -0.9250245f, -1.745329f, 0.0f);
        this.tent2 = new ModelRenderer(this, 0, 18);
        this.tent2.addBox(-8.0f, -1.0f, -1.0f, 8, 2, 2);
        this.tent2.setPos(-2.0f, 15.0f, -3.0f);
        this.tent2.mirror = true;
        this.setRotation(this.tent2, -0.1745329f, -0.6632251f, -0.2443461f);
        this.tent3 = new ModelRenderer(this, 0, 18);
        this.tent3.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2);
        this.tent3.setPos(1.0f, 15.0f, -4.0f);
        this.tent3.mirror = true;
        this.setRotation(this.tent3, -1.134464f, 0.3316126f, 0.0f);
        this.tent4 = new ModelRenderer(this, 0, 18);
        this.tent4.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2);
        this.tent4.setPos(-3.0f, 15.0f, -1.0f);
        this.tent4.mirror = true;
        this.setRotation(this.tent4, 0.5585054f, -1.692969f, 0.0f);
        this.tent5 = new ModelRenderer(this, 0, 18);
        this.tent5.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2);
        this.tent5.setPos(1.0f, 15.0f, 3.0f);
        this.tent5.mirror = true;
        this.setRotation(this.tent5, 0.5410521f, 0.2268928f, 0.0f);
        this.tent6 = new ModelRenderer(this, 0, 18);
        this.tent6.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8);
        this.tent6.setPos(-2.0f, 15.0f, 2.0f);
        this.tent6.mirror = true;
        this.setRotation(this.tent6, -0.418879f, -0.6806784f, 0.0f);
        this.tent7 = new ModelRenderer(this, 0, 18);
        this.tent7.addBox(0.0f, -1.0f, -1.0f, 8, 2, 2);
        this.tent7.setPos(3.0f, 15.0f, 1.0f);
        this.tent7.mirror = true;
        this.setRotation(this.tent7, -0.1919862f, -0.6632251f, 0.418879f);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8);
        this.body.setPos(1.0f, 16.0f, -1.0f);
        this.body.mirror = true;
        this.setRotation(this.body, -0.1919862f, -0.6806784f, 0.0f);
        this.tent8 = new ModelRenderer(this, 0, 18);
        this.tent8.addBox(-1.0f, -1.0f, -8.0f, 2, 2, 8);
        this.tent8.setPos(3.0f, 15.0f, -4.0f);
        this.tent8.mirror = true;
        this.setRotation(this.tent8, 0.1919862f, -0.6806784f, 0.0f);
    }
    @Override
    public void setupAnim(AttackSquid entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        AttackSquid e = (AttackSquid)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangleA = 0.0f;
        float newangleB = 0.0f;
        float newangle8 = 0.0f;
        float newangle1 = 0.0f;
        float newangle2 = 0.0f;
        float newangle3 = 0.0f;
        float newangle4 = 0.0f;
        float newangle5 = 0.0f;
        float newangle6 = 0.0f;
        float newangle7 = 0.0f;
        float pi4 = 0.7853982f;
        if ((double)f1 > 0.1) {
            newangleA = MathHelper.cos((float)(f2 * 0.25f * this.wingspeed)) * 3.1415927f * 0.04f * f1;
            newangleB = MathHelper.cos((float)(f2 * 0.39f * this.wingspeed)) * 3.1415927f * 0.04f * f1;
            newangle1 = MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
            newangle2 = MathHelper.cos((float)(f2 * 1.1f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
            newangle3 = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
            newangle4 = MathHelper.cos((float)(f2 * 1.9f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
            newangle5 = MathHelper.cos((float)(f2 * 1.8f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
            newangle6 = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
            newangle7 = MathHelper.cos((float)(f2 * 1.6f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
            newangle8 = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
        } else {
            newangleA = MathHelper.cos((float)(f2 * 0.25f * this.wingspeed)) * 3.1415927f * 0.01f;
            newangleB = MathHelper.cos((float)(f2 * 0.39f * this.wingspeed)) * 3.1415927f * 0.01f;
            newangle1 = MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.1f;
            newangle2 = MathHelper.cos((float)(f2 * 1.1f * this.wingspeed)) * 3.1415927f * 0.1f;
            newangle3 = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.1f;
            newangle4 = MathHelper.cos((float)(f2 * 1.9f * this.wingspeed)) * 3.1415927f * 0.1f;
            newangle5 = MathHelper.cos((float)(f2 * 1.8f * this.wingspeed)) * 3.1415927f * 0.1f;
            newangle6 = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.1f;
            newangle7 = MathHelper.cos((float)(f2 * 1.6f * this.wingspeed)) * 3.1415927f * 0.1f;
            newangle8 = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.1f;
        }
        this.tent1.xRot = newangle1 - 1.03f;
        this.tent7.zRot = newangle2 + 0.37f;
        this.tent5.xRot = newangle3 + 0.6f;
        this.tent6.xRot = newangle4 - 0.48f;
        this.tent4.xRot = newangle5 + 0.63f;
        this.tent2.zRot = newangle6 - 0.26f;
        this.tent3.xRot = newangle7 - 1.03f;
        this.tent8.xRot = newangle8 + 0.43f;
        this.body.xRot = newangleA;
        this.body.zRot = newangleB;
        this.body.yRot = newangleA = (float)Math.toRadians(f3) * 0.75f;
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.tent1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity) {
        
    }
}

