/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelIsland
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Island;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelIsland extends EntityModel<Island> {
    private float wingspeed = 1.0f;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;

    public ModelIsland(float f) {
        super();
        // textureWidth = 64;
        // textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.Shape1.setPos(0.0f, 16.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 32, 0);
        this.Shape2.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.Shape2.setPos(0.0f, 16.0f, 0.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.7853982f, 0.7853982f, 0.7853982f);
        this.Shape3 = new ModelRenderer(this, 32, 16);
        this.Shape3.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.Shape3.setPos(0.0f, 16.0f, 0.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.7853982f, 0.7853982f, 0.7853982f);
    }
    @Override
    public void setupAnim(Island entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        this.Shape1.xRot = newangle = MathHelper.cos((float)(f2 * 0.05f * this.wingspeed)) * 3.1415927f;
        this.Shape1.yRot = newangle = MathHelper.cos((float)(f2 * 0.051f * this.wingspeed)) * 3.1415927f;
        this.Shape1.zRot = newangle = MathHelper.cos((float)(f2 * 0.052f * this.wingspeed)) * 3.1415927f;
        this.Shape2.xRot = newangle = MathHelper.cos((float)(f2 * 0.053f * this.wingspeed)) * 3.1415927f;
        this.Shape2.yRot = newangle = MathHelper.cos((float)(f2 * 0.054f * this.wingspeed)) * 3.1415927f;
        this.Shape2.zRot = newangle = MathHelper.cos((float)(f2 * 0.055f * this.wingspeed)) * 3.1415927f;
        this.Shape3.xRot = newangle = MathHelper.cos((float)(f2 * 0.056f * this.wingspeed)) * 3.1415927f;
        this.Shape3.yRot = newangle = MathHelper.cos((float)(f2 * 0.057f * this.wingspeed)) * 3.1415927f;
        this.Shape3.zRot = newangle = MathHelper.cos((float)(f2 * 0.058f * this.wingspeed)) * 3.1415927f;
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Island par7Entity) {
        
    }
}

