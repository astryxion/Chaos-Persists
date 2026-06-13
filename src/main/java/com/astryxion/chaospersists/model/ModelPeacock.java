/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelPeacock
 *  com.astryxion.chaospersists.Peacock
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Peacock;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPeacock extends EntityModel<Peacock> {
    private float wingspeed = 1.0f;
    ModelRenderer lleg;
    ModelRenderer rleg;
    ModelRenderer body;
    ModelRenderer neck;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer hf1;
    ModelRenderer hf2;
    ModelRenderer hf3;
    ModelRenderer tailf1;
    ModelRenderer tailf2;
    ModelRenderer tailf3;
    ModelRenderer tailf4;
    ModelRenderer tailf5;
    ModelRenderer tailf6;
    ModelRenderer tailf7;

    public ModelPeacock(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.lleg = new ModelRenderer(this, 0, 20);
        this.lleg.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.lleg.setPos(1.0f, 17.0f, 0.0f);
        this.lleg.mirror = true;
        this.setRotation(this.lleg, 0.0f, 0.0f, 0.0f);
        this.rleg = new ModelRenderer(this, 5, 20);
        this.rleg.addBox(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.rleg.setPos(-1.0f, 17.0f, 0.0f);
        this.rleg.mirror = true;
        this.setRotation(this.rleg, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 88, 0);
        this.body.addBox(-2.0f, -2.0f, -5.0f, 5, 4, 11);
        this.body.setPos(0.0f, 15.0f, 1.0f);
        this.body.mirror = true;
        this.setRotation(this.body, -0.1396263f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 70, 0);
        this.neck.addBox(-0.5f, -1.0f, -6.0f, 2, 2, 6);
        this.neck.setPos(0.0f, 14.0f, -3.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, -0.5585054f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 56, 0);
        this.head1.addBox(-0.5f, -2.0f, -2.0f, 2, 2, 4);
        this.head1.setPos(0.0f, 12.0f, -8.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 48, 0);
        this.head2.addBox(0.0f, -1.0f, -4.0f, 1, 1, 2);
        this.head2.setPos(0.0f, 12.0f, -8.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.hf1 = new ModelRenderer(this, 8, 0);
        this.hf1.addBox(0.5f, -9.0f, -1.5f, 0, 7, 3);
        this.hf1.setPos(0.0f, 12.0f, -8.0f);
        this.hf1.mirror = true;
        this.setRotation(this.hf1, 0.4014257f, 0.0f, 0.0f);
        this.hf2 = new ModelRenderer(this, 8, 0);
        this.hf2.addBox(0.5f, -9.0f, -1.5f, 0, 7, 3);
        this.hf2.setPos(0.0f, 12.0f, -8.0f);
        this.hf2.mirror = true;
        this.setRotation(this.hf2, -0.1745329f, 0.0f, 0.0f);
        this.hf3 = new ModelRenderer(this, 8, 0);
        this.hf3.addBox(0.5f, -9.0f, -1.5f, 0, 7, 3);
        this.hf3.setPos(0.0f, 12.0f, -8.0f);
        this.hf3.mirror = true;
        this.setRotation(this.hf3, -0.6981317f, 0.0f, 0.0f);
        this.tailf1 = new ModelRenderer(this, 0, 50);
        this.tailf1.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30);
        this.tailf1.setPos(0.5f, 14.0f, 7.0f);
        this.tailf1.mirror = true;
        this.setRotation(this.tailf1, 0.0f, 0.0f, 0.0f);
        this.tailf2 = new ModelRenderer(this, 0, 50);
        this.tailf2.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30);
        this.tailf2.setPos(0.5f, 14.0f, 7.0f);
        this.tailf2.mirror = true;
        this.setRotation(this.tailf2, 0.0f, 0.0f, 0.0f);
        this.tailf3 = new ModelRenderer(this, 0, 50);
        this.tailf3.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30);
        this.tailf3.setPos(0.5f, 14.0f, 7.0f);
        this.tailf3.mirror = true;
        this.setRotation(this.tailf3, 0.0f, 0.0f, 0.0f);
        this.tailf4 = new ModelRenderer(this, 0, 50);
        this.tailf4.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30);
        this.tailf4.setPos(0.5f, 14.0f, 7.0f);
        this.tailf4.mirror = true;
        this.setRotation(this.tailf4, 0.0f, 0.0f, 0.0f);
        this.tailf5 = new ModelRenderer(this, 0, 50);
        this.tailf5.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30);
        this.tailf5.setPos(0.5f, 14.0f, 7.0f);
        this.tailf5.mirror = true;
        this.setRotation(this.tailf5, 0.0f, 0.0f, 0.0f);
        this.tailf6 = new ModelRenderer(this, 0, 50);
        this.tailf6.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30);
        this.tailf6.setPos(0.5f, 14.0f, 7.0f);
        this.tailf6.mirror = true;
        this.setRotation(this.tailf6, 0.0f, 0.0f, 0.0f);
        this.tailf7 = new ModelRenderer(this, 0, 50);
        this.tailf7.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30);
        this.tailf7.setPos(0.514f, 14.0f, 7.0f);
        this.tailf7.mirror = true;
        this.setRotation(this.tailf7, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Peacock entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Peacock p = (Peacock)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.15f * f1 : 0.0f;
        this.lleg.xRot = newangle;
        this.rleg.xRot = - newangle;
        if (p.getBlink() > 0) {
            this.hf1.xRot = 0.401f;
            this.hf2.xRot = -0.174f;
            this.hf3.xRot = -0.698f;
            this.tailf1.xRot = 1.047f;
            this.tailf2.xRot = 1.047f;
            this.tailf3.xRot = 1.047f;
            this.tailf4.xRot = 1.047f;
            this.tailf5.xRot = 1.047f;
            this.tailf6.xRot = 1.047f;
            this.tailf7.xRot = 1.047f;
            this.tailf1.zRot = -0.4f;
            this.tailf2.zRot = -0.8f;
            this.tailf3.zRot = -1.2f;
            this.tailf4.zRot = 0.4f;
            this.tailf5.zRot = 0.8f;
            this.tailf6.zRot = 1.2f;
        } else {
            this.hf1.xRot = -1.06f;
            this.hf2.xRot = -1.06f;
            this.hf3.xRot = -1.06f;
            this.tailf1.xRot = 0.0f;
            this.tailf2.xRot = 0.0f;
            this.tailf3.xRot = 0.0f;
            this.tailf4.xRot = 0.0f;
            this.tailf5.xRot = 0.0f;
            this.tailf6.xRot = 0.0f;
            this.tailf7.xRot = 0.0f;
            this.tailf1.zRot = 0.0f;
            this.tailf2.zRot = 0.0f;
            this.tailf3.zRot = 0.0f;
            this.tailf4.zRot = 0.0f;
            this.tailf5.zRot = 0.0f;
            this.tailf6.zRot = 0.0f;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

