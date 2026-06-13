/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelBrutalfly
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Brutalfly;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelBrutalfly extends EntityModel<Brutalfly> {
    ModelRenderer body;
    ModelRenderer leftwing;
    ModelRenderer rightwing;
    ModelRenderer leftwing2;
    ModelRenderer rightwing2;
    ModelRenderer leftwing3;
    ModelRenderer rightwing3;
    ModelRenderer head;
    ModelRenderer leftwing4;
    ModelRenderer rightwing4;
    ModelRenderer leftwing5;
    ModelRenderer leftwing6;
    ModelRenderer rightwing5;
    ModelRenderer rightwing6;
    private float wingspeed = 1.0f;

    public ModelBrutalfly(float f1) {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 32;
        this.wingspeed = f1;
        this.body = new ModelRenderer(this, 21, 19);
        this.body.addBox(0.0f, 0.0f, -4.0f, 1, 1, 8);
        this.body.setPos(0.0f, 17.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.leftwing = new ModelRenderer(this, 43, 24);
        this.leftwing.addBox(0.0f, 0.0f, -4.0f, 1, 1, 5);
        this.leftwing.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing.mirror = true;
        this.setRotation(this.leftwing, 0.0f, 0.0f, 0.0f);
        this.rightwing = new ModelRenderer(this, 43, 17);
        this.rightwing.addBox(-1.0f, 0.0f, -4.0f, 1, 1, 5);
        this.rightwing.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing.mirror = true;
        this.setRotation(this.rightwing, 0.0f, 0.0f, 0.0f);
        this.leftwing2 = new ModelRenderer(this, 0, 0);
        this.leftwing2.addBox(1.0f, 0.0f, -6.0f, 6, 1, 7);
        this.leftwing2.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing2.mirror = true;
        this.setRotation(this.leftwing2, 0.0f, 0.0f, 0.0f);
        this.rightwing2 = new ModelRenderer(this, 29, 0);
        this.rightwing2.addBox(-7.0f, 0.0f, -6.0f, 6, 1, 7);
        this.rightwing2.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing2.mirror = true;
        this.setRotation(this.rightwing2, 0.0f, 0.0f, 0.0f);
        this.leftwing3 = new ModelRenderer(this, 0, 9);
        this.leftwing3.addBox(0.0f, 0.0f, 1.0f, 5, 1, 5);
        this.leftwing3.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing3.mirror = true;
        this.setRotation(this.leftwing3, 0.0f, 0.0f, 0.0f);
        this.rightwing3 = new ModelRenderer(this, 27, 9);
        this.rightwing3.addBox(-5.0f, 0.0f, 1.0f, 5, 1, 5);
        this.rightwing3.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing3.mirror = true;
        this.setRotation(this.rightwing3, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 21, 11);
        this.head.addBox(0.0f, 0.0f, -6.0f, 1, 1, 1);
        this.head.setPos(0.0f, 17.0f, 1.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.leftwing4 = new ModelRenderer(this, 2, 24);
        this.leftwing4.addBox(0.0f, 0.0f, 6.0f, 2, 1, 7);
        this.leftwing4.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing4.mirror = true;
        this.setRotation(this.leftwing4, 0.0f, 0.0f, 0.0f);
        this.rightwing4 = new ModelRenderer(this, 2, 16);
        this.rightwing4.addBox(-2.0f, 0.0f, 6.0f, 2, 1, 7);
        this.rightwing4.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing4.mirror = true;
        this.setRotation(this.rightwing4, 0.0f, 0.0f, 0.0f);
        this.leftwing5 = new ModelRenderer(this, 21, 16);
        this.leftwing5.addBox(1.0f, 0.0f, -7.0f, 1, 1, 1);
        this.leftwing5.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing5.mirror = true;
        this.setRotation(this.leftwing5, 0.0f, 0.0f, 0.0f);
        this.leftwing6 = new ModelRenderer(this, 50, 10);
        this.leftwing6.addBox(7.0f, 0.0f, -6.0f, 2, 1, 1);
        this.leftwing6.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing6.mirror = true;
        this.setRotation(this.leftwing6, 0.0f, 0.0f, 0.0f);
        this.rightwing5 = new ModelRenderer(this, 27, 16);
        this.rightwing5.addBox(-2.0f, 0.0f, -7.0f, 1, 1, 1);
        this.rightwing5.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing5.mirror = true;
        this.setRotation(this.rightwing5, 0.0f, 0.0f, 0.0f);
        this.rightwing6 = new ModelRenderer(this, 50, 13);
        this.rightwing6.addBox(-9.0f, 0.0f, -6.0f, 2, 1, 1);
        this.rightwing6.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing6.mirror = true;
        this.setRotation(this.rightwing6, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Brutalfly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        
        this.rightwing2.zRot = this.rightwing.zRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.rightwing3.zRot = this.rightwing.zRot;
        this.rightwing4.zRot = this.rightwing.zRot;
        this.rightwing5.zRot = this.rightwing.zRot;
        this.rightwing6.zRot = this.rightwing.zRot;
        this.leftwing.zRot = - this.rightwing.zRot;
        this.leftwing2.zRot = - this.rightwing.zRot;
        this.leftwing3.zRot = - this.rightwing.zRot;
        this.leftwing4.zRot = - this.rightwing.zRot;
        this.leftwing5.zRot = - this.rightwing.zRot;
        this.leftwing6.zRot = - this.rightwing.zRot;
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity) {
        
    }
}

