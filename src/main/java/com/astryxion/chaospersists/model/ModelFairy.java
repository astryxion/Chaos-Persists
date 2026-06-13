/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Fairy
 *  com.astryxion.chaospersists.ModelFairy
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Fairy;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFairy extends EntityModel<Fairy> {
    private float wingspeed = 1.0f;
    private int fairyPackedLight = 0;
    ModelRenderer head;
    ModelRenderer chest;
    ModelRenderer waist;
    ModelRenderer hips;
    ModelRenderer lleg1;
    ModelRenderer lleg2;
    ModelRenderer rleg;
    ModelRenderer b1;
    ModelRenderer b2;
    ModelRenderer larm;
    ModelRenderer rarm;
    ModelRenderer lwing2;
    ModelRenderer lwing1;
    ModelRenderer rwing2;
    ModelRenderer rwing1;

    public ModelFairy(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-2.5f, -5.0f, -2.5f, 5, 5, 5);
        this.head.setPos(0.0f, 0.0f, 0.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.chest = new ModelRenderer(this, 31, 5);
        this.chest.addBox(-3.5f, 0.0f, -1.0f, 7, 4, 3);
        this.chest.setPos(0.0f, 0.0f, 0.0f);
        this.chest.mirror = true;
        this.setRotation(this.chest, 0.0f, 0.0f, 0.0f);
        this.waist = new ModelRenderer(this, 33, 13);
        this.waist.addBox(-2.5f, 4.0f, -1.0f, 5, 3, 3);
        this.waist.setPos(0.0f, 0.0f, 0.0f);
        this.waist.mirror = true;
        this.setRotation(this.waist, 0.0f, 0.0f, 0.0f);
        this.hips = new ModelRenderer(this, 31, 20);
        this.hips.addBox(-3.0f, 7.0f, -1.0f, 6, 4, 4);
        this.hips.setPos(0.0f, 0.0f, 0.0f);
        this.hips.mirror = true;
        this.setRotation(this.hips, 0.0f, 0.0f, 0.0f);
        this.lleg1 = new ModelRenderer(this, 53, 8);
        this.lleg1.addBox(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.lleg1.setPos(1.0f, 10.0f, 0.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, -0.7853982f, 0.0f, 0.0f);
        this.lleg2 = new ModelRenderer(this, 53, 18);
        this.lleg2.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.lleg2.setPos(1.0f, 15.0f, -5.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, 0.7679449f, 0.0f, 0.0f);
        this.rleg = new ModelRenderer(this, 51, 30);
        this.rleg.addBox(-3.0f, 0.0f, 0.0f, 2, 13, 2);
        this.rleg.setPos(0.0f, 11.0f, 0.0f);
        this.rleg.mirror = true;
        this.setRotation(this.rleg, 0.0f, 0.0f, 0.0f);
        this.b1 = new ModelRenderer(this, 42, 1);
        this.b1.addBox(1.0f, 1.0f, -2.0f, 2, 2, 1);
        this.b1.setPos(0.0f, 1.0f, 0.0f);
        this.b1.mirror = true;
        this.setRotation(this.b1, 0.0f, 0.0f, 0.0f);
        this.b2 = new ModelRenderer(this, 32, 1);
        this.b2.addBox(-3.0f, 2.0f, -2.0f, 2, 2, 1);
        this.b2.setPos(0.0f, 0.0f, 0.0f);
        this.b2.mirror = true;
        this.setRotation(this.b2, 0.0f, 0.0f, 0.0f);
        this.larm = new ModelRenderer(this, 7, 14);
        this.larm.addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
        this.larm.setPos(3.0f, 0.0f, 0.0f);
        this.larm.mirror = true;
        this.setRotation(this.larm, -0.0174533f, 0.0f, -0.122173f);
        this.rarm = new ModelRenderer(this, 2, 14);
        this.rarm.addBox(-1.0f, 0.0f, 0.0f, 1, 10, 1);
        this.rarm.setPos(-3.0f, 0.0f, 0.0f);
        this.rarm.mirror = true;
        this.setRotation(this.rarm, -0.0174533f, 0.0f, 0.122173f);
        this.lwing2 = new ModelRenderer(this, 0, 47);
        this.lwing2.addBox(0.0f, -9.0f, 0.0f, 26, 16, 0);
        this.lwing2.setPos(2.0f, 0.0f, 2.0f);
        this.lwing2.mirror = true;
        this.setRotation(this.lwing2, 0.0f, -0.5934119f, 0.0f);
        this.lwing1 = new ModelRenderer(this, 0, 30);
        this.lwing1.addBox(0.0f, -7.0f, 0.0f, 24, 16, 0);
        this.lwing1.setPos(2.0f, 3.0f, 2.0f);
        this.lwing1.mirror = true;
        this.setRotation(this.lwing1, 0.0f, -0.8203047f, 0.0f);
        this.rwing2 = new ModelRenderer(this, 0, 30);
        this.rwing2.addBox(0.0f, -7.0f, 0.0f, 24, 16, 0);
        this.rwing2.setPos(-2.0f, 3.0f, 2.0f);
        this.rwing2.mirror = true;
        this.setRotation(this.rwing2, 0.0f, -2.356194f, 0.0f);
        this.rwing1 = new ModelRenderer(this, 0, 47);
        this.rwing1.addBox(0.0f, -9.0f, 0.0f, 26, 16, 0);
        this.rwing1.setPos(-2.0f, 0.0f, 2.0f);
        this.rwing1.mirror = true;
        this.setRotation(this.rwing1, 0.0f, -2.548181f, 0.0f);
    }
    @Override
    public void setupAnim(Fairy entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Fairy fly = (Fairy)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float onoff = 0.0f;
        this.lwing1.yRot = -0.6f + MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.35f;
        this.rwing1.yRot = -2.55f - MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.35f;
        this.lwing2.yRot = -0.6f + MathHelper.cos((float)(f2 * this.wingspeed * 0.85f)) * 3.1415927f * 0.25f;
        this.rwing2.yRot = -2.55f - MathHelper.cos((float)(f2 * this.wingspeed * 0.85f)) * 3.1415927f * 0.25f;
        this.head.yRot = (float)Math.toRadians(f3) * 0.45f;
        if (this.head.yRot > 0.45f) {
            this.head.yRot = 0.45f;
        }
        if (this.head.yRot < -0.45f) {
            this.head.yRot = -0.45f;
        }
        this.head.xRot = (float)Math.toRadians(f4);
        this.larm.xRot = -0.2f + MathHelper.cos((float)(f2 * this.wingspeed * 0.15f)) * 3.1415927f * 0.05f;
        this.rarm.xRot = -0.2f + MathHelper.cos((float)(f2 * this.wingspeed * 0.12f)) * 3.1415927f * 0.05f;
        this.larm.zRot = -0.15f + MathHelper.cos((float)(f2 * this.wingspeed * 0.1f)) * 3.1415927f * 0.03f;
        this.rarm.zRot = 0.15f + MathHelper.cos((float)(f2 * this.wingspeed * 0.11f)) * 3.1415927f * 0.03f;
        
        
        
        
        onoff = fly.getBlink();
        this.fairyPackedLight = onoff > 0.0f ? 15728880 : 0;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        int light = this.fairyPackedLight != 0 ? this.fairyPackedLight : packedLight;
        this.lwing2.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.lwing1.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.rwing2.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.rwing1.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.chest.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.waist.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.hips.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.rleg.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.b1.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.b2.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.larm.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.rarm.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

