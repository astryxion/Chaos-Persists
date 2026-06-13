/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelGoldFish
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.GoldFish;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGoldFish extends EntityModel<GoldFish> {
    private float wingspeed = 1.0f;
    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer Dorsalfin;
    ModelRenderer Mouth;
    ModelRenderer Jaw;
    ModelRenderer Pectoralfin1;
    ModelRenderer Pectoralfin2;
    ModelRenderer Pectoralfin3;
    ModelRenderer Pectoralfin4;
    ModelRenderer Bottomfin;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Caudalfin1;
    ModelRenderer Caudalfin2;
    ModelRenderer Bottomfin1;
    ModelRenderer Bottomfin2;

    public ModelGoldFish(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.Body = new ModelRenderer(this, 0, 15);
        this.Body.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 10);
        this.Body.setPos(0.0f, 14.0f, -5.0f);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 0, 30);
        this.Head.addBox(-1.5f, -2.0f, -3.0f, 3, 4, 3);
        this.Head.setPos(0.0f, 14.0f, -5.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Dorsalfin = new ModelRenderer(this, 29, 0);
        this.Dorsalfin.addBox(0.0f, -6.0f, 0.0f, 0, 4, 10);
        this.Dorsalfin.setPos(0.0f, 14.0f, -5.0f);
        this.Dorsalfin.mirror = true;
        this.setRotation(this.Dorsalfin, 0.0f, 0.0f, 0.0f);
        this.Mouth = new ModelRenderer(this, 0, 38);
        this.Mouth.addBox(-1.5f, 0.6f, -3.5f, 3, 3, 3);
        this.Mouth.setPos(0.0f, 14.0f, -5.0f);
        this.Mouth.mirror = true;
        this.setRotation(this.Mouth, -0.7853982f, 0.0f, 0.0f);
        this.Jaw = new ModelRenderer(this, 13, 30);
        this.Jaw.addBox(-1.0f, 0.0f, -3.0f, 3, 1, 3);
        this.Jaw.setPos(-0.5f, 15.6f, -7.4f);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, -0.2284419f, 0.0f, 0.0f);
        this.Pectoralfin1 = new ModelRenderer(this, 0, 0);
        this.Pectoralfin1.addBox(0.0f, -1.5f, 0.0f, 0, 3, 5);
        this.Pectoralfin1.setPos(-2.0f, 14.0f, -3.0f);
        this.Pectoralfin1.mirror = true;
        this.setRotation(this.Pectoralfin1, -0.2974289f, -0.3346075f, 0.0f);
        this.Pectoralfin2 = new ModelRenderer(this, 0, 0);
        this.Pectoralfin2.addBox(0.0f, -1.5f, 0.0f, 0, 3, 5);
        this.Pectoralfin2.setPos(2.0f, 14.0f, -3.0f);
        this.Pectoralfin2.mirror = true;
        this.setRotation(this.Pectoralfin2, -0.2974216f, 0.3346145f, 0.0f);
        this.Pectoralfin3 = new ModelRenderer(this, 0, 0);
        this.Pectoralfin3.addBox(0.0f, -1.5f, 0.0f, 0, 3, 5);
        this.Pectoralfin3.setPos(-2.0f, 14.0f, 1.0f);
        this.Pectoralfin3.mirror = true;
        this.setRotation(this.Pectoralfin3, -0.2974289f, -0.3346075f, 0.0f);
        this.Pectoralfin4 = new ModelRenderer(this, 0, 0);
        this.Pectoralfin4.addBox(0.0f, -1.5f, 0.0f, 0, 3, 5);
        this.Pectoralfin4.setPos(2.0f, 14.0f, 1.0f);
        this.Pectoralfin4.mirror = true;
        this.setRotation(this.Pectoralfin4, -0.2974289f, 0.3346145f, 0.0f);
        this.Bottomfin = new ModelRenderer(this, 20, 8);
        this.Bottomfin.addBox(0.0f, 2.0f, 6.0f, 0, 3, 4);
        this.Bottomfin.setPos(0.0f, 14.0f, -5.0f);
        this.Bottomfin.mirror = true;
        this.setRotation(this.Bottomfin, 0.0f, 0.0f, 0.0f);
        this.Tail1 = new ModelRenderer(this, 29, 15);
        this.Tail1.addBox(-1.5f, -2.0f, 0.0f, 3, 4, 6);
        this.Tail1.setPos(0.0f, 14.0f, 5.0f);
        this.Tail1.mirror = true;
        this.setRotation(this.Tail1, 0.0f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 0, 8);
        this.Tail2.addBox(-1.0f, -1.5f, 6.0f, 2, 3, 4);
        this.Tail2.setPos(0.0f, 14.0f, 5.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, 0.0f, 0.0f, 0.0f);
        this.Caudalfin1 = new ModelRenderer(this, 13, 35);
        this.Caudalfin1.addBox(-0.5f, 5.5f, 6.0f, 1, 3, 4);
        this.Caudalfin1.setPos(0.0f, 14.0f, 5.0f);
        this.Caudalfin1.mirror = true;
        this.setRotation(this.Caudalfin1, 0.8179294f, 0.0f, 0.0f);
        this.Caudalfin2 = new ModelRenderer(this, 15, 35);
        this.Caudalfin2.addBox(-0.5f, 5.5f, 6.0f, 1, 4, 3);
        this.Caudalfin2.setPos(0.0f, 14.0f, 5.0f);
        this.Caudalfin2.mirror = true;
        this.setRotation(this.Caudalfin2, 0.8179294f, 0.0f, 0.0f);
        this.Bottomfin1 = new ModelRenderer(this, 20, 0);
        this.Bottomfin1.addBox(-1.0f, 2.0f, 1.0f, 0, 5, 2);
        this.Bottomfin1.setPos(0.0f, 14.0f, -5.0f);
        this.Bottomfin1.mirror = true;
        this.setRotation(this.Bottomfin1, 0.2974289f, 0.0f, 0.3346145f);
        this.Bottomfin2 = new ModelRenderer(this, 20, 0);
        this.Bottomfin2.addBox(1.0f, 2.0f, 1.0f, 0, 5, 2);
        this.Bottomfin2.setPos(0.0f, 14.0f, -5.0f);
        this.Bottomfin2.mirror = true;
        this.setRotation(this.Bottomfin2, 0.2974289f, 0.0f, -0.3346075f);
    }
    @Override
    public void setupAnim(GoldFish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin1.yRot = 0.4f + newangle;
        newangle = MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin2.yRot = -0.4f + newangle;
        newangle = MathHelper.cos((float)(f2 * 1.1f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin3.yRot = 0.4f + newangle;
        newangle = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin4.yRot = -0.4f + newangle;
        this.Bottomfin1.yRot = newangle = MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.Bottomfin2.yRot = - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.Jaw.xRot = -0.25f + newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Dorsalfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Mouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bottomfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Caudalfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Caudalfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bottomfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bottomfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

