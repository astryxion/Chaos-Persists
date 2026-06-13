/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Chipmunk
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.ModelChipmunk
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.EntityCannonFodder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelChipmunk extends EntityModel<Chipmunk> {
    private float wingspeed = 1.0f;
    ModelRenderer Cheek2;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Tail2;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer MouthUnder;
    ModelRenderer Cheek1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
    ModelRenderer Ear1;
    ModelRenderer Body;
    ModelRenderer BodyTail;
    ModelRenderer Tail1;
    ModelRenderer Hat1;
    ModelRenderer Hat2;

    public ModelChipmunk(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.Cheek2 = new ModelRenderer(this, 14, 0);
        this.Cheek2.addBox(0.5f, -1.5f, -3.5f, 2, 2, 2);
        this.Cheek2.setPos(0.0f, 20.0f, -3.0f);
        this.Cheek2.mirror = true;
        this.setRotation(this.Cheek2, 0.0f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer(this, 22, 7);
        this.Leg1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Leg1.setPos(-2.0f, 23.0f, -4.0f);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer(this, 22, 9);
        this.Leg2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Leg2.setPos(1.0f, 23.0f, -4.0f);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer(this, 22, 11);
        this.Leg3.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Leg3.setPos(1.0f, 23.0f, 0.0f);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer(this, 22, 13);
        this.Leg4.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Leg4.setPos(-2.0f, 23.0f, 0.0f);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 28, 15);
        this.Tail2.addBox(-0.5f, 1.0f, 2.5f, 3, 3, 4);
        this.Tail2.setPos(-1.0f, 20.0f, 1.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, 0.7662421f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer(this, 26, 9);
        this.Neck.addBox(0.0f, 0.0f, 0.0f, 3, 2, 4);
        this.Neck.setPos(-1.5f, 22.0f, -5.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 1.570796f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.addBox(-2.0f, -3.0f, 0.0f, 4, 4, 3);
        this.Head.setPos(0.0f, 20.0f, -3.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 1.570796f, 0.0f, 0.0f);
        this.MouthUnder = new ModelRenderer(this, 20, 4);
        this.MouthUnder.addBox(-1.0f, -1.9f, -3.8f, 2, 2, 1);
        this.MouthUnder.setPos(0.0f, 20.0f, -3.0f);
        this.MouthUnder.mirror = true;
        this.setRotation(this.MouthUnder, 0.0f, 0.0f, 0.0f);
        this.Cheek1 = new ModelRenderer(this, 22, 0);
        this.Cheek1.addBox(-2.5f, -1.5f, -3.5f, 2, 2, 2);
        this.Cheek1.setPos(0.0f, 20.0f, -3.0f);
        this.Cheek1.mirror = true;
        this.setRotation(this.Cheek1, 0.0f, 0.0f, 0.0f);
        this.Ear2 = new ModelRenderer(this, 18, 11);
        this.Ear2.addBox(1.0f, 0.0f, 3.0f, 1, 1, 1);
        this.Ear2.setPos(0.0f, 20.0f, -3.0f);
        this.Ear2.mirror = true;
        this.setRotation(this.Ear2, 1.570796f, 0.0f, 0.0f);
        this.Nose = new ModelRenderer(this, 18, 7);
        this.Nose.addBox(-0.5f, -2.0f, -4.2f, 1, 1, 1);
        this.Nose.setPos(0.0f, 20.0f, -3.0f);
        this.Nose.mirror = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.Ear1 = new ModelRenderer(this, 18, 9);
        this.Ear1.addBox(-2.0f, 0.0f, 3.0f, 1, 1, 1);
        this.Ear1.setPos(0.0f, 20.0f, -3.0f);
        this.Ear1.mirror = true;
        this.setRotation(this.Ear1, 1.570796f, 0.0f, 0.0f);
        this.Body = new ModelRenderer(this, 0, 7);
        this.Body.addBox(0.0f, 0.0f, 0.0f, 4, 3, 5);
        this.Body.setPos(-2.0f, 20.0f, -4.0f);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.BodyTail = new ModelRenderer(this, 0, 15);
        this.BodyTail.addBox(0.0f, 0.0f, 0.0f, 5, 4, 3);
        this.BodyTail.setPos(-2.5f, 19.0f, -1.0f);
        this.BodyTail.mirror = true;
        this.setRotation(this.BodyTail, 0.0f, 0.0f, 0.0f);
        this.Tail1 = new ModelRenderer(this, 16, 15);
        this.Tail1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.Tail1.setPos(-1.0f, 20.0f, 1.0f);
        this.Tail1.mirror = true;
        this.setRotation(this.Tail1, 0.3064968f, 0.0f, 0.0f);
        this.Hat1 = new ModelRenderer(this, 40, 0);
        this.Hat1.addBox(-2.5f, -4.0f, -4.0f, 5, 1, 5);
        this.Hat1.setPos(0.0f, 20.0f, -3.0f);
        this.Hat1.mirror = true;
        this.setRotation(this.Hat1, 0.0f, 0.0f, 0.0f);
        this.Hat2 = new ModelRenderer(this, 40, 0);
        this.Hat2.addBox(-2.0f, -6.0f, -3.0f, 4, 2, 4);
        this.Hat2.setPos(0.0f, 20.0f, -3.0f);
        this.Hat2.mirror = true;
        this.setRotation(this.Hat2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Chipmunk entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Chipmunk c = (Chipmunk)entity;
        float hf = 0.0f;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.Leg1.xRot = newangle;
        this.Leg3.xRot = newangle;
        this.Leg2.xRot = - newangle;
        this.Leg4.xRot = - newangle;
        this.Nose.yRot = this.Head.yRot = (float)Math.toRadians(f3) * 0.45f;
        this.Ear1.yRot = this.Head.yRot;
        this.Ear2.yRot = this.Head.yRot;
        this.MouthUnder.yRot = this.Head.yRot;
        this.Cheek1.yRot = this.Head.yRot;
        this.Cheek2.yRot = this.Head.yRot;
        this.Hat1.yRot = this.Head.yRot;
        this.Hat2.yRot = this.Head.yRot;
        if (!c.isOrderedToSit()) {
            this.Tail1.xRot = 0.306f + MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.06f;
            newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1;
            this.Tail1.xRot += newangle;
            this.Tail2.xRot = 0.306f + this.Tail1.xRot;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (c instanceof EntityCannonFodder && c.get_is_activated() != 0) {
            
            if (c.get_is_activated() > 1) {
                
            }
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Cheek2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MouthUnder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Cheek1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Ear2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Ear1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity) {
        
    }
}

