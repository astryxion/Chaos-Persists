/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelSkate
 *  com.astryxion.chaospersists.Skate
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Skate;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSkate extends EntityModel<Skate> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer tail1;
    ModelRenderer Shape1;

    public ModelSkate(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 13);
        this.body.addBox(-3.0f, 0.0f, -3.0f, 6, 1, 6);
        this.body.setPos(0.0f, 22.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.7853982f, 0.0f);
        this.tail1 = new ModelRenderer(this, 0, 0);
        this.tail1.addBox(-0.5f, 0.0f, 0.0f, 1, 1, 11);
        this.tail1.setPos(0.0f, 22.0f, 3.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer(this, 0, 21);
        this.Shape1.addBox(-0.5f, 0.0f, 0.0f, 1, 1, 4);
        this.Shape1.setPos(0.0f, 22.0f, 5.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.7853982f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Skate entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Skate e = (Skate)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.2f)) * 3.1415927f * 0.15f * f1 : MathHelper.cos((float)(f2 * 0.4f)) * 3.1415927f * 0.05f;
        this.Shape1.xRot = 0.785f + newangle;
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

