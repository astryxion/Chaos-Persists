/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Irukandji
 *  com.astryxion.chaospersists.ModelIrukandji
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Irukandji;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelIrukandji extends EntityModel<Irukandji> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer t11;
    ModelRenderer t12;
    ModelRenderer t21;
    ModelRenderer t22;
    ModelRenderer t31;
    ModelRenderer t32;
    ModelRenderer t41;
    ModelRenderer t42;

    public ModelIrukandji(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 9);
        this.body.addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
        this.body.setPos(0.0f, 6.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.t11 = new ModelRenderer(this, 25, 0);
        this.t11.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t11.setPos(1.0f, 10.0f, -2.0f);
        this.t11.mirror = true;
        this.setRotation(this.t11, 0.0f, 0.0f, 0.0f);
        this.t12 = new ModelRenderer(this, 5, 0);
        this.t12.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t12.setPos(1.0f, 17.0f, -2.0f);
        this.t12.mirror = true;
        this.setRotation(this.t12, 0.0f, 0.0f, 0.0f);
        this.t21 = new ModelRenderer(this, 0, 0);
        this.t21.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t21.setPos(-2.0f, 10.0f, -2.0f);
        this.t21.mirror = true;
        this.setRotation(this.t21, 0.0f, 0.0f, 0.0f);
        this.t22 = new ModelRenderer(this, 20, 0);
        this.t22.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t22.setPos(-2.0f, 17.0f, -2.0f);
        this.t22.mirror = true;
        this.setRotation(this.t22, 0.0f, 0.0f, 0.0f);
        this.t31 = new ModelRenderer(this, 30, 0);
        this.t31.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t31.setPos(1.0f, 10.0f, 1.0f);
        this.t31.mirror = true;
        this.setRotation(this.t31, 0.0f, 0.0f, 0.0f);
        this.t32 = new ModelRenderer(this, 10, 0);
        this.t32.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t32.setPos(1.0f, 17.0f, 1.0f);
        this.t32.mirror = true;
        this.setRotation(this.t32, 0.0f, 0.0f, 0.0f);
        this.t41 = new ModelRenderer(this, 35, 0);
        this.t41.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t41.setPos(-2.0f, 10.0f, 1.0f);
        this.t41.mirror = true;
        this.setRotation(this.t41, 0.0f, 0.0f, 0.0f);
        this.t42 = new ModelRenderer(this, 15, 0);
        this.t42.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.t42.setPos(-2.0f, 17.0f, 1.0f);
        this.t42.mirror = true;
        this.setRotation(this.t42, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Irukandji entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Irukandji e = (Irukandji)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        this.t11.xRot = newangle = MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        float d1 = (float)(Math.sin(newangle) * 7.0);
        float d2 = (float)(Math.cos(newangle) * 7.0);
        this.t12.z = this.t11.z + d1;
        this.t11.zRot = newangle = MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.1f;
        float d3 = (float)(Math.cos(newangle) * (double)d2);
        float d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t12.x = this.t11.x - d4;
        this.t12.y = this.t11.y + d3;
        this.t12.xRot = newangle = MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.15f;
        this.t12.zRot = newangle = MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.1f;
        this.t21.xRot = newangle = MathHelper.cos((float)(f2 * 0.65f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 7.0);
        d2 = (float)(Math.cos(newangle) * 7.0);
        this.t22.z = this.t21.z + d1;
        this.t21.zRot = newangle = MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t22.x = this.t21.x - d4;
        this.t22.y = this.t21.y + d3;
        this.t22.xRot = newangle = MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        this.t22.zRot = newangle = MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.1f;
        this.t31.xRot = newangle = MathHelper.cos((float)(f2 * 0.5f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 7.0);
        d2 = (float)(Math.cos(newangle) * 7.0);
        this.t32.z = this.t31.z + d1;
        this.t31.zRot = newangle = MathHelper.cos((float)(f2 * 0.3f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t32.x = this.t31.x - d4;
        this.t32.y = this.t31.y + d3;
        this.t32.xRot = newangle = MathHelper.cos((float)(f2 * 0.4f)) * 3.1415927f * 0.15f;
        this.t32.zRot = newangle = MathHelper.cos((float)(f2 * 0.2f)) * 3.1415927f * 0.1f;
        this.t41.xRot = newangle = MathHelper.cos((float)(f2 * 0.57f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 7.0);
        d2 = (float)(Math.cos(newangle) * 7.0);
        this.t42.z = this.t41.z + d1;
        this.t41.zRot = newangle = MathHelper.cos((float)(f2 * 0.37f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t42.x = this.t41.x - d4;
        this.t42.y = this.t41.y + d3;
        this.t42.xRot = newangle = MathHelper.cos((float)(f2 * 0.48f)) * 3.1415927f * 0.15f;
        this.t42.zRot = newangle = MathHelper.cos((float)(f2 * 0.29f)) * 3.1415927f * 0.1f;
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t22.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t31.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t32.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t41.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t42.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

