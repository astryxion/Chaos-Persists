/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelCreepingHorror
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.CreepingHorror;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCreepingHorror extends EntityModel<CreepingHorror> {
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg1part2;
    ModelRenderer leg2;
    ModelRenderer leg2part2;
    ModelRenderer leg3;
    ModelRenderer leg3part2;
    ModelRenderer leg4;
    ModelRenderer leg4part2;
    ModelRenderer tailseg1;
    ModelRenderer tailseg2;
    ModelRenderer tailseg3;
    ModelRenderer pincer1;
    ModelRenderer pincer1part2;
    ModelRenderer pincer2;
    ModelRenderer pincer2part2;
    ModelRenderer spike1;
    ModelRenderer spike2;
    ModelRenderer spike3;
    ModelRenderer spike4;
    ModelRenderer spike5;
    ModelRenderer insides1;
    ModelRenderer insides2;
    ModelRenderer insides3;
    ModelRenderer insides4;
    ModelRenderer insides5;

    public ModelCreepingHorror() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 128;
        // textureHeight = 128;
        this.body = new ModelRenderer(this, 0, 30);
        this.body.addBox(-4.0f, -5.0f, -4.0f, 8, 8, 8);
        this.body.setPos(0.0f, 20.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer(this, 65, 0);
        this.leg1.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.leg1.setPos(4.0f, 18.0f, -2.0f);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.5759587f, 0.1919862f);
        this.leg1part2 = new ModelRenderer(this, 37, 5);
        this.leg1part2.addBox(13.01f, -1.01f, -1.0f, 2, 5, 2);
        this.leg1part2.setPos(4.0f, 18.0f, -2.0f);
        this.leg1part2.mirror = true;
        this.setRotation(this.leg1part2, 0.0f, 0.5759587f, 0.1919862f);
        this.leg2 = new ModelRenderer(this, 65, 0);
        this.leg2.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.leg2.setPos(4.0f, 18.0f, 2.0f);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, -0.5759587f, 0.1919862f);
        this.leg2part2 = new ModelRenderer(this, 37, 5);
        this.leg2part2.addBox(13.01f, -1.01f, -1.0f, 2, 5, 2);
        this.leg2part2.setPos(4.0f, 18.0f, 2.0f);
        this.leg2part2.mirror = true;
        this.setRotation(this.leg2part2, 0.0f, -0.5759587f, 0.1919862f);
        this.leg3 = new ModelRenderer(this, 28, 0);
        this.leg3.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.leg3.setPos(-4.0f, 18.0f, -2.0f);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, -0.5759587f, -0.1919862f);
        this.leg3part2 = new ModelRenderer(this, 28, 5);
        this.leg3part2.addBox(-15.01f, -1.01f, -1.0f, 2, 5, 2);
        this.leg3part2.setPos(-4.0f, 18.0f, -2.0f);
        this.leg3part2.mirror = true;
        this.setRotation(this.leg3part2, 0.0f, -0.5759587f, -0.1919862f);
        this.leg4 = new ModelRenderer(this, 28, 0);
        this.leg4.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.leg4.setPos(-4.0f, 18.0f, 2.0f);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.5759587f, -0.1919862f);
        this.leg4part2 = new ModelRenderer(this, 28, 5);
        this.leg4part2.addBox(-15.01f, -1.01f, -1.0f, 2, 5, 2);
        this.leg4part2.setPos(-4.0f, 18.0f, 2.0f);
        this.leg4part2.mirror = true;
        this.setRotation(this.leg4part2, 0.0f, 0.5759587f, -0.1919862f);
        this.tailseg1 = new ModelRenderer(this, 0, 13);
        this.tailseg1.addBox(-2.0f, -1.0f, 0.0f, 4, 2, 7);
        this.tailseg1.setPos(0.0f, 17.0f, 3.0f);
        this.tailseg1.mirror = true;
        this.setRotation(this.tailseg1, -0.5576792f, 0.0f, 0.0f);
        this.tailseg2 = new ModelRenderer(this, 0, 0);
        this.tailseg2.addBox(-1.0f, 3.0f, 7.0f, 2, 1, 11);
        this.tailseg2.setPos(0.0f, 17.0f, 3.0f);
        this.tailseg2.mirror = true;
        this.setRotation(this.tailseg2, -0.0349066f, 0.0f, 0.0f);
        this.tailseg3 = new ModelRenderer(this, 0, 24);
        this.tailseg3.addBox(-1.5f, 1.0f, 6.0f, 3, 2, 2);
        this.tailseg3.setPos(0.0f, 17.0f, 3.0f);
        this.tailseg3.mirror = true;
        this.setRotation(this.tailseg3, -0.2230717f, 0.0f, 0.0f);
        this.pincer1 = new ModelRenderer(this, 26, 30);
        this.pincer1.addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5);
        this.pincer1.setPos(-3.0f, 19.0f, -3.0f);
        this.pincer1.mirror = true;
        this.setRotation(this.pincer1, 0.0f, -0.2230717f, 0.0f);
        this.pincer1part2 = new ModelRenderer(this, 26, 30);
        this.pincer1part2.addBox(-0.5f, -0.5f, -5.01f, 2, 1, 0);
        this.pincer1part2.setPos(-3.0f, 19.0f, -3.0f);
        this.pincer1part2.mirror = true;
        this.setRotation(this.pincer1part2, 0.0f, -0.2230717f, 0.0f);
        this.pincer2 = new ModelRenderer(this, 26, 30);
        this.pincer2.addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5);
        this.pincer2.setPos(3.0f, 19.0f, -3.0f);
        this.pincer2.mirror = true;
        this.setRotation(this.pincer2, 0.0f, 0.2230705f, 0.0f);
        this.pincer2part2 = new ModelRenderer(this, 26, 28);
        this.pincer2part2.addBox(-1.5f, -0.5f, -5.01f, 2, 1, 0);
        this.pincer2part2.setPos(3.0f, 19.0f, -3.0f);
        this.pincer2part2.mirror = true;
        this.setRotation(this.pincer2part2, 0.0f, 0.2230705f, 0.0f);
        this.spike1 = new ModelRenderer(this, 26, 13);
        this.spike1.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6);
        this.spike1.setPos(-3.0f, 16.0f, -2.0f);
        this.spike1.mirror = true;
        this.setRotation(this.spike1, 0.7063936f, -0.2602503f, 0.0f);
        this.spike2 = new ModelRenderer(this, 26, 13);
        this.spike2.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6);
        this.spike2.setPos(-1.0f, 16.0f, 1.0f);
        this.spike2.mirror = true;
        this.setRotation(this.spike2, 0.7063936f, -0.111544f, 0.0f);
        this.spike3 = new ModelRenderer(this, 26, 13);
        this.spike3.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6);
        this.spike3.setPos(1.0f, 16.0f, 1.0f);
        this.spike3.mirror = true;
        this.setRotation(this.spike3, 0.7063936f, 0.1115358f, 0.0f);
        this.spike4 = new ModelRenderer(this, 26, 13);
        this.spike4.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6);
        this.spike4.setPos(3.0f, 16.0f, -2.0f);
        this.spike4.mirror = true;
        this.setRotation(this.spike4, 0.7063936f, 0.260246f, 0.0f);
        this.spike5 = new ModelRenderer(this, 26, 13);
        this.spike5.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6);
        this.spike5.setPos(0.0f, 16.0f, -3.0f);
        this.spike5.mirror = true;
        this.setRotation(this.spike5, 0.7063936f, 0.0f, 0.0f);
        this.insides1 = new ModelRenderer(this, 0, 30);
        this.insides1.addBox(-2.0f, -3.0f, -3.0f, 4, 4, 0);
        this.insides1.setPos(0.0f, 20.0f, 0.0f);
        this.insides1.mirror = true;
        this.setRotation(this.insides1, 0.0f, 0.0f, 0.0f);
        this.insides2 = new ModelRenderer(this, -1, 29);
        this.insides2.addBox(-2.0f, -3.0f, -4.0f, 4, 0, 1);
        this.insides2.setPos(0.0f, 20.0f, 0.0f);
        this.insides2.mirror = true;
        this.setRotation(this.insides2, 0.0f, 0.0f, 0.0f);
        this.insides3 = new ModelRenderer(this, -1, 29);
        this.insides3.addBox(-2.0f, 1.0f, -4.0f, 4, 0, 1);
        this.insides3.setPos(0.0f, 20.0f, 0.0f);
        this.insides3.mirror = true;
        this.setRotation(this.insides3, 0.0f, 0.0f, 0.0f);
        this.insides4 = new ModelRenderer(this, 0, 29);
        this.insides4.addBox(-2.0f, -3.0f, -4.0f, 0, 4, 1);
        this.insides4.setPos(0.0f, 20.0f, 0.0f);
        this.insides4.mirror = true;
        this.setRotation(this.insides4, 0.0f, 0.0f, 0.0f);
        this.insides5 = new ModelRenderer(this, 0, 29);
        this.insides5.addBox(2.0f, -3.0f, -4.0f, 0, 4, 1);
        this.insides5.setPos(0.0f, 20.0f, 0.0f);
        this.insides5.mirror = true;
        this.setRotation(this.insides5, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(CreepingHorror entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = MathHelper.cos((float)(f2 * 1.25f)) * 3.1415927f * 0.35f * f1;
        this.leg1part2.yRot = this.leg1.yRot = 0.576f + newangle;
        this.leg2part2.yRot = this.leg2.yRot = -0.576f - newangle;
        this.leg3part2.yRot = this.leg3.yRot = -0.576f - newangle;
        this.leg4part2.yRot = this.leg4.yRot = 0.576f + newangle;
        this.pincer1.yRot = newangle = MathHelper.cos((float)(f2 * 0.48f)) * 3.1415927f * 0.15f;
        this.pincer1part2.yRot = newangle;
        this.pincer2.yRot = - newangle;
        this.pincer2part2.yRot = - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.11f)) * 3.1415927f * 0.25f;
        newangle = Math.abs(newangle);
        this.tailseg1.xRot = -0.55f + newangle;
        this.tailseg3.xRot = -0.22f + newangle;
        this.tailseg2.xRot = newangle;
        newangle = MathHelper.cos((float)(f2 * 0.81f)) * 3.1415927f * 0.08f;
        this.spike1.xRot = 0.7f + newangle;
        newangle = MathHelper.cos((float)(f2 * 0.87f)) * 3.1415927f * 0.08f;
        this.spike2.xRot = 0.7f + newangle;
        newangle = MathHelper.cos((float)(f2 * 0.99f)) * 3.1415927f * 0.08f;
        this.spike3.xRot = 0.7f + newangle;
        newangle = MathHelper.cos((float)(f2 * 0.103f)) * 3.1415927f * 0.08f;
        this.spike4.xRot = 0.7f + newangle;
        newangle = MathHelper.cos((float)(f2 * 0.107f)) * 3.1415927f * 0.08f;
        this.spike5.xRot = 0.7f + newangle;
        this.spike1.yRot = newangle = MathHelper.cos((float)(f2 * 1.11f)) * 3.1415927f * 0.08f;
        this.spike2.yRot = newangle = MathHelper.cos((float)(f2 * 1.17f)) * 3.1415927f * 0.08f;
        this.spike3.yRot = newangle = MathHelper.cos((float)(f2 * 1.25f)) * 3.1415927f * 0.08f;
        this.spike4.yRot = newangle = MathHelper.cos((float)(f2 * 1.28f)) * 3.1415927f * 0.08f;
        this.spike5.yRot = newangle = MathHelper.cos((float)(f2 * 1.31f)) * 3.1415927f * 0.08f;
        this.spike1.zRot = newangle = MathHelper.cos((float)(f2 * 1.41f)) * 3.1415927f * 0.08f;
        this.spike2.zRot = newangle = MathHelper.cos((float)(f2 * 1.47f)) * 3.1415927f * 0.08f;
        this.spike3.zRot = newangle = MathHelper.cos((float)(f2 * 1.55f)) * 3.1415927f * 0.08f;
        this.spike4.zRot = newangle = MathHelper.cos((float)(f2 * 1.58f)) * 3.1415927f * 0.08f;
        this.spike5.zRot = newangle = MathHelper.cos((float)(f2 * 1.61f)) * 3.1415927f * 0.08f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailseg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailseg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailseg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer1part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer2part2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

