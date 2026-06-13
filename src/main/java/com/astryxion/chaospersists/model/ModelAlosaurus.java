/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Alosaurus
 *  com.astryxion.chaospersists.ModelAlosaurus
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Alosaurus;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAlosaurus extends EntityModel<Alosaurus> {
    private float wingspeed = 1.0f;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer jaw;
    ModelRenderer leftleg;
    ModelRenderer leftleg2;
    ModelRenderer leftleg3;
    ModelRenderer Shape11;
    ModelRenderer rightleg;
    ModelRenderer rightleg2;
    ModelRenderer rightleg3;
    ModelRenderer leftleg4;
    ModelRenderer rightleg4;
    ModelRenderer Shape17;

    public ModelAlosaurus(float f1) {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 128;
        // textureHeight = 128;
        this.wingspeed = f1;
        this.Shape18 = new ModelRenderer(this, 91, 114);
        this.Shape18.addBox(0.0f, 0.0f, 0.0f, 2, 4, 5);
        this.Shape18.setPos(3.3f, -25.0f, -27.0f);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.5759587f, 0.0f, 0.5585054f);
        this.Shape19 = new ModelRenderer(this, 71, 114);
        this.Shape19.addBox(0.0f, 0.0f, 0.0f, 2, 4, 5);
        this.Shape19.setPos(-4.0f, -24.0f, -28.0f);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.5759587f, 0.0f, -0.5585054f);
        this.Shape20 = new ModelRenderer(this, 91, 30);
        this.Shape20.addBox(0.0f, 0.0f, 0.0f, 2, 7, 5);
        this.Shape20.setPos(5.0f, -8.0f, -6.0f);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.3839724f, 0.0f, 0.0f);
        this.Shape21 = new ModelRenderer(this, 93, 46);
        this.Shape21.addBox(-2.0f, 0.0f, 0.0f, 2, 7, 5);
        this.Shape21.setPos(-4.0f, -8.0f, -6.0f);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.3839724f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-7.0f, 0.0f, 0.0f, 10, 18, 31);
        this.Shape1.setPos(2.5f, -19.0f, -8.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 62, 0);
        this.Shape2.addBox(-5.0f, 0.0f, 0.0f, 10, 11, 11);
        this.Shape2.setPos(0.5f, -19.0f, 23.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 10, 54);
        this.Shape3.addBox(-3.0f, 0.0f, 0.0f, 7, 7, 25);
        this.Shape3.setPos(0.0f, -19.0f, 34.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer(this, 68, 88);
        this.Shape4.addBox(-5.0f, 0.0f, 0.0f, 8, 9, 16);
        this.Shape4.setPos(1.5f, -25.0f, -16.0f);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.4014257f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer(this, 75, 65);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 9, 9, 12);
        this.Shape5.setPos(-4.0f, -25.0f, -27.0f);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer(this, 0, 50);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 7, 9, 9);
        this.Shape6.setPos(-3.0f, -25.0f, -36.0f);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 0, 86);
        this.jaw.addBox(-5.0f, 0.0f, -10.0f, 7, 1, 13);
        this.jaw.setPos(2.0f, -15.0f, -24.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, 0.5201081f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer(this, 0, 0);
        this.leftleg.addBox(-1.0f, 0.0f, 0.0f, 3, 16, 10);
        this.leftleg.setPos(6.0f, -10.0f, 11.0f);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, -0.1745329f, 0.0f, 0.0f);
        this.leftleg2 = new ModelRenderer(this, 0, 106);
        this.leftleg2.addBox(-1.0f, 12.0f, -8.0f, 3, 15, 5);
        this.leftleg2.setPos(6.0f, -10.0f, 11.0f);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.5061455f, 0.0f, 0.0f);
        this.leftleg3 = new ModelRenderer(this, 112, 89);
        this.leftleg3.addBox(-1.0f, 19.0f, 16.0f, 3, 9, 3);
        this.leftleg3.setPos(6.0f, -10.0f, 11.0f);
        this.leftleg3.mirror = true;
        this.setRotation(this.leftleg3, -0.4014257f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer(this, 0, 72);
        this.Shape11.addBox(0.0f, 0.0f, 0.0f, 2, 10, 2);
        this.Shape11.setPos(5.0f, -5.0f, -3.0f);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, -0.5235988f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer(this, 54, 51);
        this.rightleg.addBox(0.0f, 0.0f, 0.0f, 3, 16, 10);
        this.rightleg.setPos(-7.0f, -10.0f, 11.0f);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, -0.1745329f, 0.0f, 0.0f);
        this.rightleg2 = new ModelRenderer(this, 23, 106);
        this.rightleg2.addBox(0.0f, 12.0f, -8.0f, 3, 15, 5);
        this.rightleg2.setPos(-7.0f, -10.0f, 11.0f);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.5061455f, 0.0f, 0.0f);
        this.rightleg3 = new ModelRenderer(this, 70, 90);
        this.rightleg3.addBox(0.0f, 19.0f, 16.0f, 3, 9, 3);
        this.rightleg3.setPos(-7.0f, -10.0f, 11.0f);
        this.rightleg3.mirror = true;
        this.setRotation(this.rightleg3, -0.4014257f, 0.0f, 0.0f);
        this.leftleg4 = new ModelRenderer(this, 42, 113);
        this.leftleg4.addBox(-1.0f, 31.0f, -1.0f, 3, 3, 8);
        this.leftleg4.setPos(6.0f, -10.0f, 11.0f);
        this.leftleg4.mirror = true;
        this.setRotation(this.leftleg4, 0.0f, 0.0f, 0.0f);
        this.rightleg4 = new ModelRenderer(this, 44, 93);
        this.rightleg4.addBox(0.0f, 31.0f, -1.0f, 3, 3, 8);
        this.rightleg4.setPos(-7.0f, -10.0f, 11.0f);
        this.rightleg4.mirror = true;
        this.setRotation(this.rightleg4, 0.0f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer(this, 112, 60);
        this.Shape17.addBox(-2.0f, 0.0f, 0.0f, 2, 10, 2);
        this.Shape17.setPos(-4.0f, -3.533333f, -3.0f);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, -0.5235988f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Alosaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Alosaurus e = (Alosaurus)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.rightleg.xRot = -0.174f + newangle;
        this.rightleg2.xRot = 0.506f + newangle;
        this.rightleg3.xRot = -0.401f + newangle;
        this.rightleg4.xRot = newangle;
        this.leftleg.xRot = -0.174f - newangle;
        this.leftleg2.xRot = 0.506f - newangle;
        this.leftleg3.xRot = -0.401f - newangle;
        this.leftleg4.xRot = - newangle;
        this.jaw.xRot = e.getAttacking() != 0 ? 0.52f + MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.18f : 0.1f;
        this.Shape17.xRot = -0.523f + MathHelper.cos((float)(f2 * 0.1f)) * 3.1415927f * 0.05f;
        this.Shape11.xRot = -0.523f + MathHelper.cos((float)(f2 * 0.1f)) * 3.1415927f * 0.05f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape18.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape19.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape20.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

