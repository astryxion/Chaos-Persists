/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EasterBunny
 *  com.astryxion.chaospersists.ModelEasterBunny
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.EasterBunny;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEasterBunny extends EntityModel<EasterBunny> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer tail;
    ModelRenderer lfoot;
    ModelRenderer lleg;
    ModelRenderer upperbody;
    ModelRenderer head;
    ModelRenderer nose;
    ModelRenderer lear;
    ModelRenderer lpaw;
    ModelRenderer rleg;
    ModelRenderer rfoot;
    ModelRenderer rear;
    ModelRenderer rpaw;

    public ModelEasterBunny(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 128;
        this.body = new ModelRenderer(this, 0, 44);
        this.body.addBox(-3.0f, 0.0f, -3.0f, 6, 6, 7);
        this.body.setPos(0.0f, 17.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer(this, 0, 58);
        this.tail.addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
        this.tail.setPos(0.0f, 19.0f, 6.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
        this.lfoot = new ModelRenderer(this, 0, 30);
        this.lfoot.addBox(-1.0f, 2.0f, -5.0f, 3, 1, 7);
        this.lfoot.setPos(3.0f, 21.0f, 1.0f);
        this.lfoot.mirror = true;
        this.setRotation(this.lfoot, 0.0f, 0.0f, 0.0f);
        this.lleg = new ModelRenderer(this, 0, 20);
        this.lleg.addBox(0.0f, -2.0f, -2.0f, 1, 4, 5);
        this.lleg.setPos(3.0f, 21.0f, 1.0f);
        this.lleg.mirror = true;
        this.setRotation(this.lleg, 0.0f, 0.0f, 0.0f);
        this.upperbody = new ModelRenderer(this, 42, 27);
        this.upperbody.addBox(-2.0f, 0.0f, -2.0f, 4, 1, 5);
        this.upperbody.setPos(0.0f, 16.0f, -1.0f);
        this.upperbody.mirror = true;
        this.setRotation(this.upperbody, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 40, 17);
        this.head.addBox(-2.5f, 0.0f, -2.0f, 5, 4, 5);
        this.head.setPos(0.0f, 12.0f, -2.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.nose = new ModelRenderer(this, 44, 9);
        this.nose.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 1);
        this.nose.setPos(0.0f, 15.0f, -5.0f);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0f, 0.0f, 0.0f);
        this.lear = new ModelRenderer(this, 54, 0);
        this.lear.addBox(0.0f, -10.0f, -1.0f, 1, 10, 3);
        this.lear.setPos(2.0f, 13.0f, -1.0f);
        this.lear.mirror = true;
        this.setRotation(this.lear, -0.2268928f, 0.0f, 0.0f);
        this.lpaw = new ModelRenderer(this, 6, 7);
        this.lpaw.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.lpaw.setPos(0.5f, 19.0f, -4.0f);
        this.lpaw.mirror = true;
        this.setRotation(this.lpaw, 0.0f, 0.0f, 0.0f);
        this.rleg = new ModelRenderer(this, 21, 20);
        this.rleg.addBox(0.0f, -2.0f, -2.0f, 1, 4, 5);
        this.rleg.setPos(-4.0f, 21.0f, 1.0f);
        this.rleg.mirror = true;
        this.setRotation(this.rleg, 0.0f, 0.0f, 0.0f);
        this.rfoot = new ModelRenderer(this, 21, 30);
        this.rfoot.addBox(-1.0f, 2.0f, -5.0f, 3, 1, 7);
        this.rfoot.setPos(-4.0f, 21.0f, 1.0f);
        this.rfoot.mirror = true;
        this.setRotation(this.rfoot, 0.0f, 0.0f, 0.0f);
        this.rear = new ModelRenderer(this, 32, 0);
        this.rear.addBox(0.0f, -10.0f, -1.0f, 1, 10, 3);
        this.rear.setPos(-3.0f, 13.0f, -1.0f);
        this.rear.mirror = true;
        this.setRotation(this.rear, -0.418879f, 0.0f, 0.0f);
        this.rpaw = new ModelRenderer(this, 0, 7);
        this.rpaw.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.rpaw.setPos(-1.5f, 19.0f, -4.0f);
        this.rpaw.mirror = true;
        this.setRotation(this.rpaw, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(EasterBunny entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        EasterBunny e = (EasterBunny)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float newangle2 = 0.0f;
        if ((double)f1 > 0.1) {
            newangle = MathHelper.cos((float)(f2 * 2.6f * this.wingspeed)) * 3.1415927f * 0.15f * f1;
            newangle2 = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f * f1;
        } else {
            newangle = 0.0f;
            newangle2 = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.01f;
        }
        this.lleg.xRot = this.lfoot.xRot = newangle;
        this.rleg.xRot = this.rfoot.xRot = - newangle;
        this.lear.xRot = -0.226f + newangle2;
        this.rear.xRot = -0.418f - newangle2;
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperbody.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lpaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rpaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

