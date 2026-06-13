/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelAnt
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.EntityAnt;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAnt extends EntityModel<EntityAnt> {
    ModelRenderer thorax;
    ModelRenderer thorax1;
    ModelRenderer thorax3;
    ModelRenderer abdomen;
    ModelRenderer abdomen1;
    ModelRenderer head;
    ModelRenderer jawsr;
    ModelRenderer jawsl;
    ModelRenderer llegtop1;
    ModelRenderer llegbot1;
    ModelRenderer llegtop2;
    ModelRenderer llegbot2;
    ModelRenderer llegtop3;
    ModelRenderer llegbot3;
    ModelRenderer rlegtop1;
    ModelRenderer rlegbot1;
    ModelRenderer rlegtop2;
    ModelRenderer rlegbot2;
    ModelRenderer rlegtop3;
    ModelRenderer rlegbot3;

    public ModelAnt() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 32;
        this.thorax = new ModelRenderer(this, 22, 0);
        this.thorax.addBox(0.0f, 0.0f, 0.0f, 3, 3, 3);
        this.thorax.setPos(0.0f, 17.0f, 0.0f);
        this.thorax.mirror = true;
        this.setRotation(this.thorax, 0.0f, 0.0f, 0.0f);
        this.thorax1 = new ModelRenderer(this, 18, 0);
        this.thorax1.addBox(1.0f, 1.0f, -1.0f, 1, 1, 1);
        this.thorax1.setPos(0.0f, 17.0f, 0.0f);
        this.thorax1.mirror = true;
        this.setRotation(this.thorax1, 0.0f, 0.0f, 0.0f);
        this.thorax3 = new ModelRenderer(this, 34, 0);
        this.thorax3.addBox(1.0f, 1.0f, 3.0f, 1, 1, 1);
        this.thorax3.setPos(0.0f, 17.0f, 0.0f);
        this.thorax3.mirror = true;
        this.setRotation(this.thorax3, 0.0f, 0.0f, 0.0f);
        this.abdomen = new ModelRenderer(this, 38, 0);
        this.abdomen.addBox(0.0f, 0.0f, 4.0f, 3, 3, 5);
        this.abdomen.setPos(0.0f, 17.0f, 0.0f);
        this.abdomen.mirror = true;
        this.setRotation(this.abdomen, 0.0f, 0.0f, 0.0f);
        this.abdomen1 = new ModelRenderer(this, 54, 0);
        this.abdomen1.addBox(1.0f, 1.0f, 9.0f, 1, 1, 1);
        this.abdomen1.setPos(0.0f, 17.0f, 0.0f);
        this.abdomen1.mirror = true;
        this.setRotation(this.abdomen1, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 6, 0);
        this.head.addBox(0.0f, -1.0f, -4.0f, 3, 3, 3);
        this.head.setPos(0.0f, 17.0f, 0.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.jawsr = new ModelRenderer(this, 0, 9);
        this.jawsr.addBox(-1.0f, 0.0f, -6.0f, 1, 1, 3);
        this.jawsr.setPos(0.0f, 17.0f, 0.0f);
        this.jawsr.mirror = true;
        this.setRotation(this.jawsr, 0.0f, 0.0f, 0.0f);
        this.jawsl = new ModelRenderer(this, 0, 14);
        this.jawsl.addBox(3.0f, 0.0f, -6.0f, 1, 1, 3);
        this.jawsl.setPos(0.0f, 17.0f, 0.0f);
        this.jawsl.mirror = true;
        this.setRotation(this.jawsl, 0.0f, 0.0f, 0.0f);
        this.llegtop1 = new ModelRenderer(this, 15, 10);
        this.llegtop1.addBox(3.0f, 1.0f, 1.0f, 3, 1, 1);
        this.llegtop1.setPos(0.0f, 17.0f, 0.0f);
        this.llegtop1.mirror = true;
        this.setRotation(this.llegtop1, 0.0f, 0.0f, 0.3839724f);
        this.llegbot1 = new ModelRenderer(this, 15, 19);
        this.llegbot1.addBox(5.0f, -3.0f, 1.0f, 3, 1, 1);
        this.llegbot1.setPos(0.0f, 17.0f, 0.0f);
        this.llegbot1.mirror = true;
        this.setRotation(this.llegbot1, 0.0f, 0.0f, 1.064651f);
        this.llegtop2 = new ModelRenderer(this, 15, 13);
        this.llegtop2.addBox(3.0f, 1.0f, 2.0f, 3, 1, 1);
        this.llegtop2.setPos(0.0f, 17.0f, 0.0f);
        this.llegtop2.mirror = true;
        this.setRotation(this.llegtop2, 0.0f, -0.2094395f, 0.3839724f);
        this.llegbot2 = new ModelRenderer(this, 15, 22);
        this.llegbot2.addBox(5.0f, -3.0f, 2.0f, 3, 1, 1);
        this.llegbot2.setPos(0.0f, 17.0f, 0.0f);
        this.llegbot2.mirror = true;
        this.setRotation(this.llegbot2, 0.0f, -0.2268928f, 1.064651f);
        this.llegtop3 = new ModelRenderer(this, 15, 16);
        this.llegtop3.addBox(3.0f, 1.0f, 0.0f, 3, 1, 1);
        this.llegtop3.setPos(0.0f, 17.0f, 0.0f);
        this.llegtop3.mirror = true;
        this.setRotation(this.llegtop3, 0.0f, 0.3490659f, 0.3839724f);
        this.llegbot3 = new ModelRenderer(this, 15, 25);
        this.llegbot3.addBox(5.0f, -3.0f, 0.0f, 3, 1, 1);
        this.llegbot3.setPos(0.0f, 17.0f, 0.0f);
        this.llegbot3.mirror = true;
        this.setRotation(this.llegbot3, 0.0f, 0.3490659f, 1.064651f);
        this.rlegtop1 = new ModelRenderer(this, 25, 10);
        this.rlegtop1.addBox(-4.0f, 2.0f, 1.0f, 3, 1, 1);
        this.rlegtop1.setPos(0.0f, 17.0f, 0.0f);
        this.rlegtop1.mirror = true;
        this.setRotation(this.rlegtop1, 0.0f, 0.0f, -0.4712389f);
        this.rlegbot1 = new ModelRenderer(this, 25, 19);
        this.rlegbot1.addBox(-7.0f, 0.0f, 1.0f, 3, 1, 1);
        this.rlegbot1.setPos(0.0f, 17.0f, 0.0f);
        this.rlegbot1.mirror = true;
        this.setRotation(this.rlegbot1, 0.0f, 0.0f, -0.9773844f);
        this.rlegtop2 = new ModelRenderer(this, 25, 13);
        this.rlegtop2.addBox(-4.0f, 2.0f, 0.0f, 3, 1, 1);
        this.rlegtop2.setPos(0.0f, 17.0f, 0.0f);
        this.rlegtop2.mirror = true;
        this.setRotation(this.rlegtop2, 0.0f, -0.5934119f, -0.4712389f);
        this.rlegbot2 = new ModelRenderer(this, 25, 22);
        this.rlegbot2.addBox(-7.0f, 0.0f, 0.0f, 3, 1, 1);
        this.rlegbot2.setPos(0.0f, 17.0f, 0.0f);
        this.rlegbot2.mirror = true;
        this.setRotation(this.rlegbot2, 0.0f, -0.5934119f, -0.9773844f);
        this.rlegtop3 = new ModelRenderer(this, 25, 16);
        this.rlegtop3.addBox(-4.0f, 2.0f, 2.0f, 3, 1, 1);
        this.rlegtop3.setPos(0.0f, 17.0f, 0.0f);
        this.rlegtop3.mirror = true;
        this.setRotation(this.rlegtop3, 0.0f, 0.418879f, -0.4712389f);
        this.rlegbot3 = new ModelRenderer(this, 25, 25);
        this.rlegbot3.addBox(-7.0f, 0.0f, 2.0f, 3, 1, 1);
        this.rlegbot3.setPos(0.0f, 17.0f, 0.0f);
        this.rlegbot3.mirror = true;
        this.setRotation(this.rlegbot3, 0.0f, 0.418879f, -0.9773844f);
    }
    @Override
    public void setupAnim(EntityAnt entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.llegbot1.xRot = this.llegtop1.xRot = MathHelper.cos((float)(f2 * 2.7f)) * 3.1415927f * 0.45f * f1;
        this.rlegtop2.xRot = this.llegtop1.xRot;
        this.rlegbot2.xRot = this.llegtop1.xRot;
        this.rlegtop3.xRot = this.llegtop1.xRot;
        this.rlegbot3.xRot = this.llegtop1.xRot;
        this.rlegtop1.xRot = - this.llegtop1.xRot;
        this.rlegbot1.xRot = - this.llegtop1.xRot;
        this.llegtop2.xRot = - this.llegtop1.xRot;
        this.llegbot2.xRot = - this.llegtop1.xRot;
        this.llegtop3.xRot = - this.llegtop1.xRot;
        this.llegbot3.xRot = - this.llegtop1.xRot;
        this.jawsl.yRot = MathHelper.cos((float)(f2 * 0.4f)) * 3.1415927f * 0.05f;
        this.jawsr.yRot = - this.jawsl.yRot;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.thorax.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawsr.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawsl.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegtop1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegbot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegtop2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegbot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegtop3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegbot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegtop1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegbot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegtop2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegbot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegtop3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegbot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

