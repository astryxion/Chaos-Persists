/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRockBase
 *  com.astryxion.chaospersists.RockBase
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.RockBase;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelRockBase extends EntityModel<RockBase> {
    private float wingspeed = 1.0f;
    private RockBase lastRock;
    ModelRenderer RockShape1;
    ModelRenderer RockShape2;
    ModelRenderer RockShape3;
    ModelRenderer RockSmallShape2;
    ModelRenderer RockSmallShape1;
    ModelRenderer RockTNTShape1;
    ModelRenderer RockTNTShape2;
    ModelRenderer RockTNTShape3;
    ModelRenderer RockTNTShape4;
    ModelRenderer RockSpikeyShape1;
    ModelRenderer RockSpikeyShape2;
    ModelRenderer RockSpikeyShape3;
    ModelRenderer CrystalShape1;
    ModelRenderer CrystalShape2;
    ModelRenderer CrystalShape3a;
    ModelRenderer CrystalShape3b;
    ModelRenderer CrystalShape3c;
    ModelRenderer CrystalShape3d;
    ModelRenderer CrystalShape4a;
    ModelRenderer CrystalShape4b;
    ModelRenderer CrystalShape4c;
    ModelRenderer CrystalShape4d;

    public ModelRockBase(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.RockShape1 = new ModelRenderer(this, 0, 0);
        this.RockShape1.addBox(-3.0f, 0.0f, -1.0f, 6, 1, 2);
        this.RockShape1.setPos(0.0f, 23.0f, 0.0f);
        this.RockShape1.mirror = true;
        this.setRotation(this.RockShape1, 0.0f, 0.0f, 0.0f);
        this.RockShape2 = new ModelRenderer(this, 0, 4);
        this.RockShape2.addBox(-3.0f, 0.0f, 1.0f, 3, 1, 1);
        this.RockShape2.setPos(0.0f, 23.0f, 0.0f);
        this.RockShape2.mirror = true;
        this.setRotation(this.RockShape2, 0.0f, 0.0f, 0.0f);
        this.RockShape3 = new ModelRenderer(this, 0, 7);
        this.RockShape3.addBox(0.0f, 0.0f, -2.0f, 2, 1, 1);
        this.RockShape3.setPos(0.0f, 23.0f, 0.0f);
        this.RockShape3.mirror = true;
        this.setRotation(this.RockShape3, 0.0f, 0.0f, 0.0f);
        this.RockSmallShape2 = new ModelRenderer(this, 0, 4);
        this.RockSmallShape2.addBox(-2.0f, 0.0f, 0.0f, 3, 1, 1);
        this.RockSmallShape2.setPos(0.0f, 23.0f, 0.0f);
        this.RockSmallShape2.mirror = true;
        this.setRotation(this.RockSmallShape2, 0.0f, 0.0f, 0.0f);
        this.RockSmallShape1 = new ModelRenderer(this, 0, 7);
        this.RockSmallShape1.addBox(0.0f, 0.0f, -1.0f, 2, 1, 1);
        this.RockSmallShape1.setPos(0.0f, 23.0f, 0.0f);
        this.RockSmallShape1.mirror = true;
        this.setRotation(this.RockSmallShape1, 0.0f, 0.0f, 0.0f);
        this.RockTNTShape1 = new ModelRenderer(this, 0, 0);
        this.RockTNTShape1.addBox(-3.0f, 0.0f, -1.0f, 6, 1, 2);
        this.RockTNTShape1.setPos(0.0f, 23.0f, 0.0f);
        this.RockTNTShape1.mirror = true;
        this.setRotation(this.RockTNTShape1, 0.0f, 0.0f, 0.0f);
        this.RockTNTShape2 = new ModelRenderer(this, 0, 4);
        this.RockTNTShape2.addBox(-3.0f, 0.0f, 1.0f, 3, 1, 1);
        this.RockTNTShape2.setPos(0.0f, 23.0f, 0.0f);
        this.RockTNTShape2.mirror = true;
        this.setRotation(this.RockTNTShape2, 0.0f, 0.0f, 0.0f);
        this.RockTNTShape3 = new ModelRenderer(this, 0, 7);
        this.RockTNTShape3.addBox(0.0f, 0.0f, -2.0f, 2, 1, 1);
        this.RockTNTShape3.setPos(0.0f, 23.0f, 0.0f);
        this.RockTNTShape3.mirror = true;
        this.setRotation(this.RockTNTShape3, 0.0f, 0.0f, 0.0f);
        this.RockTNTShape4 = new ModelRenderer(this, 0, 10);
        this.RockTNTShape4.addBox(-4.0f, 0.0f, -2.0f, 3, 1, 3);
        this.RockTNTShape4.setPos(0.0f, 22.0f, 0.0f);
        this.RockTNTShape4.mirror = true;
        this.setRotation(this.RockTNTShape4, 0.0f, 0.0f, 0.0f);
        this.RockSpikeyShape1 = new ModelRenderer(this, 0, 0);
        this.RockSpikeyShape1.addBox(-3.0f, 0.0f, -1.0f, 6, 1, 2);
        this.RockSpikeyShape1.setPos(0.0f, 23.0f, 0.0f);
        this.RockSpikeyShape1.mirror = true;
        this.setRotation(this.RockSpikeyShape1, 0.0f, 0.0f, 0.0f);
        this.RockSpikeyShape2 = new ModelRenderer(this, 0, 4);
        this.RockSpikeyShape2.addBox(-4.0f, 0.0f, -1.0f, 3, 1, 1);
        this.RockSpikeyShape2.setPos(0.0f, 23.0f, 0.0f);
        this.RockSpikeyShape2.mirror = true;
        this.setRotation(this.RockSpikeyShape2, 0.0f, 1.570796f, 0.0f);
        this.RockSpikeyShape3 = new ModelRenderer(this, 0, 7);
        this.RockSpikeyShape3.addBox(1.0f, 0.0f, 1.0f, 2, 1, 1);
        this.RockSpikeyShape3.setPos(0.0f, 23.0f, 0.0f);
        this.RockSpikeyShape3.mirror = true;
        this.setRotation(this.RockSpikeyShape3, 0.0f, 1.570796f, 0.0f);
        this.CrystalShape1 = new ModelRenderer(this, 0, 0);
        this.CrystalShape1.addBox(-1.0f, -4.0f, -1.0f, 2, 5, 2);
        this.CrystalShape1.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape1.mirror = true;
        this.setRotation(this.CrystalShape1, 0.0f, 0.0f, 0.0f);
        this.CrystalShape2 = new ModelRenderer(this, 10, 0);
        this.CrystalShape2.addBox(-0.5f, -7.0f, -0.5f, 1, 3, 1);
        this.CrystalShape2.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape2.mirror = true;
        this.setRotation(this.CrystalShape2, 0.0f, 0.0f, 0.0f);
        this.CrystalShape3a = new ModelRenderer(this, 0, 8);
        this.CrystalShape3a.addBox(-1.0f, -5.0f, -1.0f, 1, 5, 1);
        this.CrystalShape3a.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape3a.mirror = true;
        this.setRotation(this.CrystalShape3a, 0.5410521f, 0.0f, 0.0f);
        this.CrystalShape3b = new ModelRenderer(this, 0, 8);
        this.CrystalShape3b.addBox(0.0f, -5.0f, 0.0f, 1, 5, 1);
        this.CrystalShape3b.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape3b.mirror = true;
        this.setRotation(this.CrystalShape3b, -0.5410521f, 0.0f, 0.0f);
        this.CrystalShape3c = new ModelRenderer(this, 0, 8);
        this.CrystalShape3c.addBox(0.0f, -5.0f, -1.0f, 1, 5, 1);
        this.CrystalShape3c.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape3c.mirror = true;
        this.setRotation(this.CrystalShape3c, 0.0f, 0.0f, 0.5410521f);
        this.CrystalShape3d = new ModelRenderer(this, 0, 8);
        this.CrystalShape3d.addBox(-1.0f, -5.0f, 0.0f, 1, 5, 1);
        this.CrystalShape3d.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape3d.mirror = true;
        this.setRotation(this.CrystalShape3d, 0.0f, 0.0f, -0.5410521f);
        this.CrystalShape4a = new ModelRenderer(this, 0, 16);
        this.CrystalShape4a.addBox(0.0f, -3.0f, -1.0f, 1, 3, 1);
        this.CrystalShape4a.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape4a.mirror = true;
        this.setRotation(this.CrystalShape4a, 1.308997f, 0.0f, 0.0f);
        this.CrystalShape4b = new ModelRenderer(this, 0, 16);
        this.CrystalShape4b.addBox(-1.0f, -3.0f, 0.0f, 1, 3, 1);
        this.CrystalShape4b.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape4b.mirror = true;
        this.setRotation(this.CrystalShape4b, -1.308997f, 0.0f, 0.0f);
        this.CrystalShape4c = new ModelRenderer(this, 0, 16);
        this.CrystalShape4c.addBox(0.0f, -3.0f, 0.0f, 1, 3, 1);
        this.CrystalShape4c.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape4c.mirror = true;
        this.setRotation(this.CrystalShape4c, 0.0f, 0.0f, 1.308997f);
        this.CrystalShape4d = new ModelRenderer(this, 0, 16);
        this.CrystalShape4d.addBox(-1.0f, -3.0f, -1.0f, 1, 3, 1);
        this.CrystalShape4d.setPos(0.0f, 23.0f, 0.0f);
        this.CrystalShape4d.mirror = true;
        this.setRotation(this.CrystalShape4d, 0.0f, 0.0f, -1.308997f);
    }

    @Override
    public void setupAnim(RockBase entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0625F;
        this.lastRock = (RockBase) entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.lastRock == null) {
            return;
        }
        int rt = this.lastRock.getRockType();
        if (rt < 1 || rt > 12) {
            return;
        }
        matrixStack.pushPose();
        if (rt == 1) {
            this.RockSmallShape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockSmallShape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        } else if (rt == 7) {
            this.RockSpikeyShape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockSpikeyShape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockSpikeyShape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        } else if (rt == 8) {
            this.RockTNTShape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockTNTShape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockTNTShape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockTNTShape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        } else if (rt >= 9 && rt <= 12) {
            float cr = 0.75F;
            float ca = 0.55F;
            this.CrystalShape1.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape2.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape3a.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape3b.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape3c.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape3d.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape4a.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape4b.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape4c.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
            this.CrystalShape4d.render(matrixStack, buffer, packedLight, packedOverlay, cr, cr, cr, ca);
        } else {
            this.RockShape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockShape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.RockShape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }
        matrixStack.popPose();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

