/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelElevator
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.item.Elevator;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelElevator extends EntityModel<Elevator> {
    private float wingspeed = 1.0f;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;

    public ModelElevator() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 64;
        this.Shape2 = new ModelRenderer(this, 0, 18);
        this.Shape2.addBox(-3.0f, 0.0f, -9.0f, 6, 1, 1);
        this.Shape2.setPos(0.0f, 0.0f, 0.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 0, 21);
        this.Shape3.addBox(-1.0f, 0.0f, -10.0f, 2, 1, 1);
        this.Shape3.setPos(0.0f, 0.0f, 0.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer(this, 17, 18);
        this.Shape4.addBox(-3.0f, 0.0f, 8.0f, 6, 1, 1);
        this.Shape4.setPos(0.0f, 0.0f, 0.0f);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer(this, 17, 21);
        this.Shape5.addBox(-1.0f, 0.0f, 9.0f, 2, 1, 1);
        this.Shape5.setPos(0.0f, 0.0f, 0.0f);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-4.0f, 0.0f, -8.0f, 8, 1, 16);
        this.Shape1.setPos(0.0f, 0.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Elevator entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

