/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelTerribleTerror
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.TerribleTerror;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTerribleTerror extends EntityModel<TerribleTerror> {
    private float wingspeed = 1.0f;
    ModelRenderer Horn1;
    ModelRenderer Horn2;
    ModelRenderer Snout;
    ModelRenderer Head;
    ModelRenderer Jaw;
    ModelRenderer Neck;
    ModelRenderer Body;
    ModelRenderer Wing1;
    ModelRenderer Wing2;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer FL11;
    ModelRenderer FL12;
    ModelRenderer FL21;
    ModelRenderer FL22;
    ModelRenderer BL21;
    ModelRenderer BL22;
    ModelRenderer BL11;
    ModelRenderer BL12;

    public ModelTerribleTerror() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 119;
        // textureHeight = 72;
        this.Horn1 = new ModelRenderer(this, 90, 0);
        this.Horn1.addBox(1.0f, -4.0f, 0.0f, 0, 2, 2);
        this.Horn1.setPos(0.0f, 17.0f, -6.0f);
        this.Horn1.mirror = true;
        this.setRotation(this.Horn1, 0.0f, 0.0f, 0.0f);
        this.Horn2 = new ModelRenderer(this, 102, 0);
        this.Horn2.addBox(-1.0f, -4.0f, 0.0f, 0, 2, 2);
        this.Horn2.setPos(0.0f, 17.0f, -6.0f);
        this.Horn2.mirror = true;
        this.setRotation(this.Horn2, 0.0f, 0.0f, 0.0f);
        this.Snout = new ModelRenderer(this, 64, 0);
        this.Snout.addBox(-2.0f, -1.0f, -4.0f, 4, 1, 4);
        this.Snout.setPos(0.0f, 17.0f, -6.0f);
        this.Snout.mirror = true;
        this.setRotation(this.Snout, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 41, 0);
        this.Head.addBox(-2.0f, -2.0f, -2.0f, 4, 1, 2);
        this.Head.setPos(0.0f, 17.0f, -6.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Jaw = new ModelRenderer(this, 42, 5);
        this.Jaw.addBox(-2.0f, 0.0f, -4.0f, 4, 1, 4);
        this.Jaw.setPos(0.0f, 17.0f, -6.0f);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, 0.0f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer(this, 30, 0);
        this.Neck.addBox(0.0f, 0.0f, 0.0f, 2, 5, 2);
        this.Neck.setPos(-1.0f, 18.0f, -2.0f);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, -2.082002f, 0.0f, 0.0f);
        this.Body = new ModelRenderer(this, 38, 16);
        this.Body.addBox(0.0f, 0.0f, 0.0f, 2, 3, 10);
        this.Body.setPos(-1.0f, 17.0f, -4.0f);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Wing1 = new ModelRenderer(this, 36, 37);
        this.Wing1.addBox(0.0f, 0.0f, 0.0f, 0, 11, 15);
        this.Wing1.setPos(0.0f, 18.0f, -1.0f);
        this.Wing1.mirror = true;
        this.setRotation(this.Wing1, -0.3490659f, 0.0f, -2.356194f);
        this.Wing2 = new ModelRenderer(this, 0, 37);
        this.Wing2.addBox(0.0f, 0.0f, 0.0f, 0, 11, 15);
        this.Wing2.setPos(0.0f, 18.0f, -1.0f);
        this.Wing2.mirror = true;
        this.setRotation(this.Wing2, -0.3490659f, 0.0f, 2.356194f);
        this.Tail1 = new ModelRenderer(this, 14, 0);
        this.Tail1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Tail1.setPos(-0.5f, 17.0f, 6.0f);
        this.Tail1.mirror = true;
        this.setRotation(this.Tail1, -0.5235988f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 14, 8);
        this.Tail2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Tail2.setPos(-0.5f, 20.0f, 11.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, 0.0f, 0.0f, 0.0f);
        this.Tail3 = new ModelRenderer(this, 17, 16);
        this.Tail3.addBox(0.0f, 0.0f, 0.0f, 1, 1, 4);
        this.Tail3.setPos(-0.5f, 20.0f, 17.0f);
        this.Tail3.mirror = true;
        this.setRotation(this.Tail3, 0.0f, -0.6320364f, 0.0f);
        this.Tail4 = new ModelRenderer(this, 16, 23);
        this.Tail4.addBox(-1.0f, 0.5f, 4.0f, 3, 0, 2);
        this.Tail4.setPos(-0.5f, 20.0f, 17.0f);
        this.Tail4.mirror = true;
        this.setRotation(this.Tail4, 0.0f, -0.6320364f, 0.0f);
        this.FL11 = new ModelRenderer(this, 0, 9);
        this.FL11.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.FL11.setPos(-2.0f, 19.0f, -4.0f);
        this.FL11.mirror = true;
        this.setRotation(this.FL11, 0.3490659f, 0.0f, 0.1745329f);
        this.FL12 = new ModelRenderer(this, 0, 13);
        this.FL12.addBox(-0.5f, 1.0f, 1.0f, 1, 2, 1);
        this.FL12.setPos(-2.0f, 19.0f, -4.0f);
        this.FL12.mirror = true;
        this.setRotation(this.FL12, -0.2617994f, 0.0f, 0.0f);
        this.FL21 = new ModelRenderer(this, 5, 9);
        this.FL21.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.FL21.setPos(1.0f, 19.0f, -4.0f);
        this.FL21.mirror = true;
        this.setRotation(this.FL21, 0.3490659f, 0.0f, -0.1745329f);
        this.FL22 = new ModelRenderer(this, 5, 13);
        this.FL22.addBox(0.5f, 1.0f, 1.0f, 1, 2, 1);
        this.FL22.setPos(1.0f, 19.0f, -4.0f);
        this.FL22.mirror = true;
        this.setRotation(this.FL22, -0.2617994f, 0.0f, 0.0f);
        this.BL21 = new ModelRenderer(this, 0, 18);
        this.BL21.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.BL21.setPos(1.0f, 18.0f, 4.0f);
        this.BL21.mirror = true;
        this.setRotation(this.BL21, -0.3490659f, 0.0f, -0.1745329f);
        this.BL22 = new ModelRenderer(this, 0, 22);
        this.BL22.addBox(0.5f, 2.0f, -1.0f, 1, 2, 1);
        this.BL22.setPos(1.0f, 18.0f, 4.0f);
        this.BL22.mirror = true;
        this.setRotation(this.BL22, 0.1745329f, 0.0f, 0.0f);
        this.BL11 = new ModelRenderer(this, 5, 18);
        this.BL11.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.BL11.setPos(-2.0f, 18.0f, 4.0f);
        this.BL11.mirror = true;
        this.setRotation(this.BL11, -0.3490659f, 0.0f, 0.1745329f);
        this.BL12 = new ModelRenderer(this, 5, 22);
        this.BL12.addBox(-0.5f, 2.0f, -1.0f, 1, 2, 1);
        this.BL12.setPos(-2.0f, 18.0f, 4.0f);
        this.BL12.mirror = true;
        this.setRotation(this.BL12, 0.1745329f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(TerribleTerror entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.Wing1.zRot = -2.0f + newangle;
        this.Wing2.zRot = 2.0f - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.Jaw.xRot = Math.abs(newangle);
        newangle = MathHelper.cos((float)(f2 * 1.25f)) * 3.1415927f * 0.35f;
        this.FL21.xRot = 0.349f + newangle;
        this.FL22.xRot = -0.296f + newangle;
        this.BL21.xRot = -0.349f - newangle;
        this.BL22.xRot = 0.174f - newangle;
        this.FL11.xRot = 0.349f - newangle;
        this.FL12.xRot = -0.296f - newangle;
        this.BL11.xRot = -0.349f + newangle;
        this.BL12.xRot = 0.174f + newangle;
        this.Tail1.xRot = newangle = MathHelper.cos((float)(f2 * 0.71f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.Tail1.yRot = newangle = MathHelper.cos((float)(f2 * 0.77f * this.wingspeed)) * 3.1415927f * 0.1f;
        float dist = 6.0f;
        dist = (float)((double)dist * Math.cos(this.Tail1.xRot));
        this.Tail2.y = (float)((double)this.Tail1.y - Math.sin(this.Tail1.xRot) * (double)dist);
        this.Tail2.x = (float)((double)this.Tail1.x + Math.sin(this.Tail1.yRot) * (double)dist);
        this.Tail2.xRot = newangle = MathHelper.cos((float)(f2 * 0.81f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Tail2.yRot = newangle = MathHelper.cos((float)(f2 * 0.87f * this.wingspeed)) * 3.1415927f * 0.15f;
        dist = 6.0f;
        dist = (float)((double)dist * Math.cos(this.Tail2.xRot));
        this.Tail3.y = this.Tail4.y = (float)((double)this.Tail2.y - Math.sin(this.Tail2.xRot) * (double)dist);
        this.Tail3.x = this.Tail4.x = (float)((double)this.Tail2.x + Math.sin(this.Tail2.yRot) * (double)dist);
        this.Tail3.xRot = this.Tail4.xRot = (newangle = MathHelper.cos((float)(f2 * 0.91f * this.wingspeed)) * 3.1415927f * 0.2f);
        this.Tail3.yRot = this.Tail4.yRot = (newangle = MathHelper.cos((float)(f2 * 0.97f * this.wingspeed)) * 3.1415927f * 0.2f);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Horn1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Horn2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Snout.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Wing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Wing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL22.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL22.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

