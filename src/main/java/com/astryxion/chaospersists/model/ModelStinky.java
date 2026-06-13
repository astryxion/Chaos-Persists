/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelStinky
 *  com.astryxion.chaospersists.Stinky
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Stinky;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStinky extends EntityModel<Stinky> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer neck1;
    ModelRenderer neck;
    ModelRenderer neckbase;
    ModelRenderer head;
    ModelRenderer Rleg1;
    ModelRenderer Lleg1;
    ModelRenderer Lhorn1;
    ModelRenderer Rhorn1;
    ModelRenderer snout;
    ModelRenderer Lhorn2;
    ModelRenderer Rhorn2;
    ModelRenderer tail1;
    ModelRenderer Rleg2;
    ModelRenderer Lleg2;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer Lwing;
    ModelRenderer Rwing;

    public ModelStinky(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 12);
        this.body.addBox(-4.5f, -3.0f, -5.0f, 8, 8, 10);
        this.body.setPos(0.5f, 15.0f, 1.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 0, 31);
        this.neck1.addBox(-2.0f, -3.0f, -2.0f, 4, 5, 5);
        this.neck1.setPos(0.0f, 16.0f, -5.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, 0.715585f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 0, 42);
        this.neck.addBox(-2.0f, -8.0f, -3.0f, 4, 8, 4);
        this.neck.setPos(0.0f, 15.0f, -5.5f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        this.neckbase = new ModelRenderer(this, 0, 55);
        this.neckbase.addBox(-3.0f, -4.0f, 0.0f, 6, 6, 3);
        this.neckbase.setPos(0.0f, 17.0f, 5.0f);
        this.neckbase.mirror = true;
        this.setRotation(this.neckbase, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-2.5f, -10.0f, -3.5f, 5, 5, 5);
        this.head.setPos(0.0f, 15.0f, -5.5f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.Rleg1 = new ModelRenderer(this, 19, 53);
        this.Rleg1.addBox(-1.5f, 0.0f, -1.0f, 3, 8, 3);
        this.Rleg1.setPos(2.0f, 16.0f, 5.5f);
        this.Rleg1.mirror = true;
        this.setRotation(this.Rleg1, 0.0f, 0.0f, 0.0f);
        this.Lleg1 = new ModelRenderer(this, 19, 53);
        this.Lleg1.addBox(-1.5f, 0.0f, -0.5f, 3, 8, 3);
        this.Lleg1.setPos(-2.0f, 16.0f, 5.0f);
        this.Lleg1.mirror = true;
        this.setRotation(this.Lleg1, 0.0f, 0.0f, 0.0f);
        this.Lhorn1 = new ModelRenderer(this, 19, 47);
        this.Lhorn1.addBox(-3.0f, -10.5f, -1.0f, 2, 2, 3);
        this.Lhorn1.setPos(0.0f, 15.0f, -5.5f);
        this.Lhorn1.mirror = true;
        this.setRotation(this.Lhorn1, 0.0f, 0.0f, 0.0f);
        this.Rhorn1 = new ModelRenderer(this, 19, 47);
        this.Rhorn1.addBox(1.0f, -10.5f, -1.0f, 2, 2, 3);
        this.Rhorn1.setPos(0.0f, 15.0f, -5.5f);
        this.Rhorn1.mirror = true;
        this.setRotation(this.Rhorn1, 0.0f, 0.0f, 0.0f);
        this.snout = new ModelRenderer(this, 32, 57);
        this.snout.addBox(-1.5f, -8.0f, -6.5f, 3, 3, 4);
        this.snout.setPos(0.0f, 15.0f, -5.5f);
        this.snout.mirror = true;
        this.setRotation(this.snout, 0.0f, 0.0f, 0.0f);
        this.Lhorn2 = new ModelRenderer(this, 19, 42);
        this.Lhorn2.addBox(-2.5f, -10.0f, 1.0f, 1, 1, 3);
        this.Lhorn2.setPos(0.0f, 15.0f, -5.5f);
        this.Lhorn2.mirror = true;
        this.setRotation(this.Lhorn2, 0.0f, 0.0f, 0.0f);
        this.Rhorn2 = new ModelRenderer(this, 19, 42);
        this.Rhorn2.addBox(1.5f, -10.0f, 1.0f, 1, 1, 3);
        this.Rhorn2.setPos(0.0f, 15.0f, -5.5f);
        this.Rhorn2.mirror = true;
        this.setRotation(this.Rhorn2, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 47, 55);
        this.tail1.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 3);
        this.tail1.setPos(0.0f, 16.5f, -2.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.Rleg2 = new ModelRenderer(this, 19, 53);
        this.Rleg2.addBox(-1.5f, 0.0f, -1.5f, 3, 8, 3);
        this.Rleg2.setPos(2.0f, 16.0f, -3.0f);
        this.Rleg2.mirror = true;
        this.setRotation(this.Rleg2, 0.0f, 0.0f, 0.0f);
        this.Lleg2 = new ModelRenderer(this, 19, 53);
        this.Lleg2.addBox(-1.5f, 0.0f, -1.5f, 3, 8, 3);
        this.Lleg2.setPos(-2.0f, 16.0f, -3.0f);
        this.Lleg2.mirror = true;
        this.setRotation(this.Lleg2, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 19, 31);
        this.tail2.addBox(-2.5f, -2.5f, 0.0f, 5, 5, 5);
        this.tail2.setPos(0.0f, 16.0f, 7.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, -0.3839724f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 32, 46);
        this.tail3.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 4);
        this.tail3.setPos(0.0f, 17.2f, 11.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, -0.2094395f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 37, 13);
        this.tail4.addBox(-1.5f, -1.5f, 0.0f, 3, 3, 5);
        this.tail4.setPos(0.0f, 17.5f, 14.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, -0.0698132f, 0.0f, 0.0f);
        this.Lwing = new ModelRenderer(this, 59, 0);
        this.Lwing.addBox(-18.0f, 0.0f, -5.0f, 18, 0, 10);
        this.Lwing.setPos(-2.0f, 12.6f, 0.0f);
        this.Lwing.mirror = true;
        this.setRotation(this.Lwing, 0.0f, 0.0f, 0.4014257f);
        this.Rwing = new ModelRenderer(this, 59, 11);
        this.Rwing.addBox(0.0f, 0.0f, -5.0f, 18, 0, 10);
        this.Rwing.setPos(2.0f, 12.6f, 0.0f);
        this.Rwing.mirror = true;
        this.setRotation(this.Rwing, 0.0f, 0.0f, -0.4014257f);
    }
    @Override
    public void setupAnim(Stinky entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Stinky c = (Stinky)entity;
        float hf = 0.0f;
        float newangle = 0.0f;
        int current_activity = c.getActivity();
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.4f * f1 : 0.0f;
        this.Rwing.zRot = newangle - 0.4f;
        this.Lwing.zRot = - newangle + 0.4f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        if (current_activity != 2) {
            this.Rleg1.xRot = newangle;
            this.Lleg1.xRot = - newangle;
            this.Rleg2.xRot = - newangle;
            this.Lleg2.xRot = newangle;
        } else {
            this.Rleg2.xRot = newangle = -1.0f;
            this.Lleg2.xRot = newangle;
            this.Rleg1.xRot = newangle = 1.0f;
            this.Lleg1.xRot = newangle;
        }
        newangle = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.2f;
        if (c.isOrderedToSit()) {
            newangle = 0.0f;
        }
        this.tail2.yRot = newangle;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 4.0f;
        this.tail3.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 4.0f - 0.5f;
        this.tail3.yRot = newangle * 1.6f;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 3.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 3.0f - 0.5f;
        this.tail4.yRot = newangle * 2.6f;
        this.head.yRot = (float)Math.toRadians(f3);
        this.snout.yRot = (float)Math.toRadians(f3);
        this.neck.yRot = (float)Math.toRadians(f3) / 2.0f;
        this.Rhorn1.yRot = (float)Math.toRadians(f3);
        this.Rhorn2.yRot = (float)Math.toRadians(f3);
        this.Lhorn1.yRot = (float)Math.toRadians(f3);
        this.Lhorn2.yRot = (float)Math.toRadians(f3);
        this.head.xRot = (float)Math.toRadians(f4) / 3.0f;
        this.snout.xRot = (float)Math.toRadians(f4) / 3.0f;
        this.neck.xRot = (float)Math.toRadians(f4) / 3.0f;
        this.Rhorn1.xRot = (float)Math.toRadians(f4) / 3.0f;
        this.Rhorn2.xRot = (float)Math.toRadians(f4) / 3.0f;
        this.Lhorn1.xRot = (float)Math.toRadians(f4) / 3.0f;
        this.Lhorn2.xRot = (float)Math.toRadians(f4) / 3.0f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neckbase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lhorn1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rhorn1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.snout.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lhorn2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rhorn2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

