/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Basilisk
 *  com.astryxion.chaospersists.ModelBasilisk
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Basilisk;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBasilisk extends EntityModel<Basilisk> {
    private float wingspeed = 1.0f;
    ModelRenderer body3;
    ModelRenderer body2;
    ModelRenderer body1;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer neck2;
    ModelRenderer neck1;
    ModelRenderer head;
    ModelRenderer rog_1;
    ModelRenderer rog_2;
    ModelRenderer rog_3;
    ModelRenderer rog_4;
    ModelRenderer rog_5;
    ModelRenderer rog_6;
    ModelRenderer snout;
    ModelRenderer jaw;

    public ModelBasilisk(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 64;
        this.body3 = new ModelRenderer(this, 0, 32);
        this.body3.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.body3.setPos(-8.0f, 8.0f, 0.0f);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 0, 32);
        this.body2.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.body2.setPos(-8.0f, 4.0f, -10.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, -0.2974289f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer(this, 0, 32);
        this.body1.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.body1.setPos(-8.0f, 2.0f, -25.0f);
        this.body1.mirror = true;
        this.setRotation(this.body1, -0.1487144f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer(this, 0, 32);
        this.body4.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.body4.setPos(-8.0f, 8.0f, 13.0f);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.1487144f, 0.0f, 0.0f);
        this.body5 = new ModelRenderer(this, 0, 32);
        this.body5.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.body5.setPos(-8.0f, 5.8f, 28.8f);
        this.body5.mirror = true;
        this.setRotation(this.body5, 0.0f, 0.0f, 0.0f);
        this.body6 = new ModelRenderer(this, 148, 4);
        this.body6.addBox(0.0f, 0.0f, 0.0f, 15, 15, 17);
        this.body6.setPos(-7.5f, 6.166667f, 44.0f);
        this.body6.mirror = true;
        this.setRotation(this.body6, -0.1115358f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 140, 36);
        this.tail1.addBox(0.0f, 0.0f, 0.0f, 13, 13, 15);
        this.tail1.setPos(-6.5f, 9.0f, 58.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.1115358f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 64, 41);
        this.tail2.addBox(0.0f, 0.0f, 0.0f, 10, 10, 13);
        this.tail2.setPos(-5.0f, 10.0f, 70.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.4089647f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 64, 20);
        this.tail3.addBox(0.0f, 0.0f, 0.0f, 8, 8, 13);
        this.tail3.setPos(-4.0f, 6.0f, 82.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.2230717f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 64, 1);
        this.tail4.addBox(0.0f, 0.0f, 0.0f, 6, 6, 13);
        this.tail4.setPos(-3.0f, 4.0f, 95.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, -0.0743572f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 0, 32);
        this.neck2.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.neck2.setPos(-8.0f, -4.9f, -26.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, -0.8464847f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 0, 32);
        this.neck1.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.neck1.setPos(-8.0f, -15.0f, -29.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, -1.181092f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(0.0f, 0.0f, 0.0f, 16, 18, 10);
        this.head.setPos(-8.0f, -21.0f, -30.0f);
        this.head.mirror = true;
        this.setRotation(this.head, -1.404164f, 0.0f, 0.0f);
        this.rog_1 = new ModelRenderer(this, 110, 45);
        this.rog_1.addBox(0.0f, 0.0f, 0.0f, 3, 3, 5);
        this.rog_1.setPos(3.0f, -21.0f, -32.0f);
        this.rog_1.mirror = true;
        this.setRotation(this.rog_1, 0.6320364f, 0.2230717f, 0.0f);
        this.rog_2 = new ModelRenderer(this, 110, 45);
        this.rog_2.addBox(0.0f, 0.0f, 0.0f, 3, 3, 5);
        this.rog_2.setPos(-6.0f, -21.0f, -32.8f);
        this.rog_2.mirror = true;
        this.setRotation(this.rog_2, 0.6320364f, -0.2230705f, 0.0f);
        this.rog_3 = new ModelRenderer(this, 52, 0);
        this.rog_3.addBox(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.rog_3.setPos(0.4666667f, -21.0f, -31.0f);
        this.rog_3.mirror = true;
        this.setRotation(this.rog_3, 0.6320364f, 0.2230717f, 0.0f);
        this.rog_4 = new ModelRenderer(this, 52, 0);
        this.rog_4.addBox(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.rog_4.setPos(-2.466667f, -21.0f, -31.46667f);
        this.rog_4.mirror = true;
        this.setRotation(this.rog_4, 0.6320364f, -0.2230705f, 0.0f);
        this.rog_5 = new ModelRenderer(this, 52, 0);
        this.rog_5.addBox(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.rog_5.setPos(-8.0f, -17.0f, -32.0f);
        this.rog_5.mirror = true;
        this.setRotation(this.rog_5, 0.6320364f, -0.6692139f, 0.0f);
        this.rog_6 = new ModelRenderer(this, 52, 0);
        this.rog_6.addBox(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.rog_6.setPos(6.4f, -17.0f, -32.0f);
        this.rog_6.mirror = true;
        this.setRotation(this.rog_6, 0.6320364f, 0.6692116f, 0.0f);
        this.snout = new ModelRenderer(this, 102, 1);
        this.snout.addBox(0.0f, 0.0f, 0.0f, 14, 16, 9);
        this.snout.setPos(-7.0f, -17.0f, -43.0f);
        this.snout.mirror = true;
        this.setRotation(this.snout, -1.404164f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 106, 26);
        this.jaw.addBox(0.0f, 0.0f, 0.0f, 14, 16, 3);
        this.jaw.setPos(-7.0f, -11.0f, -39.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, -0.8836633f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Basilisk entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Basilisk e = (Basilisk)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f * f1 : 0.0f;
        float pi4 = 0.7853975f;
        this.body1.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f * f1;
        this.body2.z = this.body1.z + (float)Math.cos(this.body1.yRot) * 12.0f;
        this.body2.x = this.body1.x + (float)Math.sin(this.body1.yRot) * 12.0f;
        this.body2.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - pi4)) * 3.1415927f * 0.1f * f1;
        this.body3.z = this.body2.z + (float)Math.cos(this.body2.yRot) * 11.0f;
        this.body3.x = this.body2.x + (float)Math.sin(this.body2.yRot) * 11.0f;
        this.body3.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.body4.z = this.body3.z + (float)Math.cos(this.body3.yRot) * 12.0f;
        this.body4.x = this.body3.x + (float)Math.sin(this.body3.yRot) * 12.0f;
        this.body4.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.body5.z = this.body4.z + (float)Math.cos(this.body4.yRot) * 12.0f;
        this.body5.x = this.body4.x + (float)Math.sin(this.body4.yRot) * 12.0f;
        this.body5.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 4.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.body6.z = this.body5.z + (float)Math.cos(this.body5.yRot) * 12.0f;
        this.body6.x = this.body5.x + 0.5f + (float)Math.sin(this.body5.yRot) * 12.0f;
        this.body6.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 5.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.tail1.z = this.body6.z + (float)Math.cos(this.body6.yRot) * 12.0f;
        this.tail1.x = this.body6.x + 1.0f + (float)Math.sin(this.body6.yRot) * 12.0f;
        this.tail1.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 6.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 10.0f;
        this.tail2.x = this.tail1.x + 1.5f + (float)Math.sin(this.tail1.yRot) * 10.0f;
        this.tail2.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 7.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 10.0f;
        this.tail3.x = this.tail2.x + 1.0f + (float)Math.sin(this.tail2.yRot) * 10.0f;
        this.tail3.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 8.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 10.0f;
        this.tail4.x = this.tail3.x + 1.0f + (float)Math.sin(this.tail3.yRot) * 10.0f;
        this.tail4.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 9.0f * pi4)) * 3.1415927f * 0.1f * f1;
        this.jaw.xRot = e.getAttacking() != 0 ? -1.0f + MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.18f : -1.1f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.snout.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

