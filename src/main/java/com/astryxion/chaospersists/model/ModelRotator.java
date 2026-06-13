/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRotator
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Rotator
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Rotator;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelRotator extends EntityModel<Rotator> {
    float wingspeed = 1.0f;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;

    public ModelRotator(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 0, 12);
        this.Shape1.addBox(-2.0f, 3.9f, 0.0f, 4, 1, 1);
        this.Shape1.setPos(0.0f, 0.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 0, 7);
        this.Shape2.addBox(-4.0f, 7.6f, 0.0f, 8, 2, 2);
        this.Shape2.setPos(0.0f, 0.0f, -0.5f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 0, 0);
        this.Shape3.addBox(-7.0f, 13.7f, 0.0f, 14, 3, 3);
        this.Shape3.setPos(0.0f, 0.0f, -1.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Rotator entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        int i;
        Rotator r = (Rotator)entity;
        RenderInfo ri = null;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        ri = r.getRenderInfo();
        GL11.glRotatef((float)ri.rf1, (float)1.0f, (float)0.0f, (float)0.0f);
        for (i = 0; i < 8; ++i) {
            this.Shape1.zRot = newangle;
            
            newangle += 0.7853982f;
        }
        GL11.glRotatef((float)(- ri.rf1), (float)1.0f, (float)0.0f, (float)0.0f);
        newangle = 0.0f;
        GL11.glRotatef((float)ri.rf1, (float)0.0f, (float)1.0f, (float)0.0f);
        for (i = 0; i < 8; ++i) {
            this.Shape2.zRot = newangle;
            
            newangle += 0.7853982f;
        }
        GL11.glRotatef((float)(- ri.rf1), (float)0.0f, (float)1.0f, (float)0.0f);
        newangle = 0.0f;
        GL11.glRotatef((float)ri.rf1, (float)0.0f, (float)0.0f, (float)1.0f);
        for (i = 0; i < 8; ++i) {
            this.Shape3.zRot = newangle;
            
            newangle += 0.7853982f;
        }
        GL11.glRotatef((float)(- ri.rf1), (float)0.0f, (float)0.0f, (float)1.0f);
        ri.rf1 += 2.0f;
        if ((double)ri.rf1 > 359.0) {
            ri.rf1 = 0.0f;
        }
        r.setRenderInfo(ri);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

