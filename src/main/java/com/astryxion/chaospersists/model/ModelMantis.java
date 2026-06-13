/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Mantis
 *  com.astryxion.chaospersists.ModelMantis
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Mantis;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMantis extends EntityModel<Mantis> {
    private float wingspeed = 1.0f;
    ModelRenderer lfleg1;
    ModelRenderer lfleg2;
    ModelRenderer lfleg3;
    ModelRenderer lfleg4;
    ModelRenderer lrleg1;
    ModelRenderer lrleg2;
    ModelRenderer lrleg3;
    ModelRenderer lrleg4;
    ModelRenderer abdomen;
    ModelRenderer thorax;
    ModelRenderer neck1;
    ModelRenderer neck2;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer leye;
    ModelRenderer reye;
    ModelRenderer lantenna;
    ModelRenderer rantenna;
    ModelRenderer larm1;
    ModelRenderer larm2;
    ModelRenderer larm3;
    ModelRenderer lfwing;
    ModelRenderer rfwing;
    ModelRenderer lrwing;
    ModelRenderer rrwing;
    ModelRenderer rarm1;
    ModelRenderer rarm2;
    ModelRenderer rarm3;
    ModelRenderer rlfleg3;
    ModelRenderer rfleg4;
    ModelRenderer rfleg2;
    ModelRenderer rfleg1;
    ModelRenderer rrleg3;
    ModelRenderer rrleg4;
    ModelRenderer rrleg2;
    ModelRenderer rrleg1;

    public ModelMantis(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 256;
        this.lfleg1 = new ModelRenderer(this, 28, 35);
        this.lfleg1.addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
        this.lfleg1.setPos(27.0f, 16.0f, -3.0f);
        this.lfleg1.mirror = true;
        this.setRotation(this.lfleg1, 0.0f, 0.0f, -0.6283185f);
        this.lfleg2 = new ModelRenderer(this, 0, 32);
        this.lfleg2.addBox(0.0f, 0.0f, 0.0f, 1, 22, 1);
        this.lfleg2.setPos(21.0f, -5.0f, -3.0f);
        this.lfleg2.mirror = true;
        this.setRotation(this.lfleg2, 0.0f, 0.0f, -0.2792527f);
        this.lfleg3 = new ModelRenderer(this, 64, 2);
        this.lfleg3.addBox(0.0f, 0.0f, 0.0f, 20, 1, 1);
        this.lfleg3.setPos(2.0f, -5.0f, 0.0f);
        this.lfleg3.mirror = true;
        this.setRotation(this.lfleg3, 0.0f, 0.1570796f, 0.0f);
        this.lfleg4 = new ModelRenderer(this, 64, 20);
        this.lfleg4.addBox(15.0f, 0.0f, -2.0f, 4, 1, 5);
        this.lfleg4.setPos(2.0f, -5.0f, 0.0f);
        this.lfleg4.mirror = true;
        this.setRotation(this.lfleg4, 0.0f, 0.1570796f, 0.0f);
        this.lrleg1 = new ModelRenderer(this, 35, 35);
        this.lrleg1.addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
        this.lrleg1.setPos(32.0f, 18.0f, 11.0f);
        this.lrleg1.mirror = true;
        this.setRotation(this.lrleg1, 0.0f, 0.0f, -0.8726646f);
        this.lrleg2 = new ModelRenderer(this, 14, 32);
        this.lrleg2.addBox(0.0f, 0.0f, 0.0f, 1, 22, 1);
        this.lrleg2.setPos(21.0f, 0.0f, 11.0f);
        this.lrleg2.mirror = true;
        this.setRotation(this.lrleg2, 0.0f, 0.0f, -0.5410521f);
        this.lrleg3 = new ModelRenderer(this, 64, 11);
        this.lrleg3.addBox(0.0f, 0.0f, 0.0f, 20, 1, 1);
        this.lrleg3.setPos(2.0f, 0.0f, 8.0f);
        this.lrleg3.mirror = true;
        this.setRotation(this.lrleg3, 0.0f, -0.1570796f, 0.0f);
        this.lrleg4 = new ModelRenderer(this, 64, 36);
        this.lrleg4.addBox(15.0f, 0.0f, -2.0f, 4, 1, 5);
        this.lrleg4.setPos(2.0f, 0.0f, 8.0f);
        this.lrleg4.mirror = true;
        this.setRotation(this.lrleg4, 0.0f, -0.1570796f, 0.0f);
        this.abdomen = new ModelRenderer(this, 118, 0);
        this.abdomen.addBox(0.0f, 0.0f, 0.0f, 9, 5, 53);
        this.abdomen.setPos(-4.0f, -11.0f, 0.0f);
        this.abdomen.mirror = true;
        this.setRotation(this.abdomen, -0.5061455f, 0.0f, 0.0f);
        this.thorax = new ModelRenderer(this, 145, 62);
        this.thorax.addBox(0.0f, 0.0f, 0.0f, 15, 3, 13);
        this.thorax.setPos(-7.0f, -14.0f, -12.0f);
        this.thorax.mirror = true;
        this.setRotation(this.thorax, -0.2443461f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 145, 82);
        this.neck1.addBox(0.0f, 0.0f, 0.0f, 9, 1, 15);
        this.neck1.setPos(-4.0f, -15.0f, -27.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, -0.0698132f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 40, 150);
        this.neck2.addBox(0.0f, 0.0f, 0.0f, 3, 1, 2);
        this.neck2.setPos(-1.0f, -15.0f, -29.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, 0.0f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 0, 150);
        this.head1.addBox(0.0f, 0.0f, 0.0f, 2, 6, 1);
        this.head1.setPos(0.0f, -16.0f, -30.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.1396263f);
        this.head2 = new ModelRenderer(this, 10, 150);
        this.head2.addBox(-2.0f, 0.0f, 0.0f, 2, 6, 1);
        this.head2.setPos(0.0f, -16.0f, -30.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, -0.1745329f);
        this.leye = new ModelRenderer(this, 20, 150);
        this.leye.addBox(1.0f, 0.0f, -0.5f, 2, 2, 1);
        this.leye.setPos(0.0f, -16.0f, -30.0f);
        this.leye.mirror = true;
        this.setRotation(this.leye, 0.0f, 0.0f, 0.1396263f);
        this.reye = new ModelRenderer(this, 30, 150);
        this.reye.addBox(-3.0f, 0.0f, -0.5f, 2, 2, 1);
        this.reye.setPos(0.0f, -16.0f, -30.0f);
        this.reye.mirror = true;
        this.setRotation(this.reye, 0.0f, 0.0f, -0.1745329f);
        this.lantenna = new ModelRenderer(this, 53, 150);
        this.lantenna.addBox(0.0f, -20.0f, 0.0f, 1, 20, 1);
        this.lantenna.setPos(0.0f, -16.0f, -30.0f);
        this.lantenna.mirror = true;
        this.setRotation(this.lantenna, 0.0f, 0.0f, 0.2792527f);
        this.rantenna = new ModelRenderer(this, 60, 150);
        this.rantenna.addBox(-1.0f, -20.0f, 0.0f, 1, 20, 1);
        this.rantenna.setPos(0.0f, -16.0f, -30.0f);
        this.rantenna.mirror = true;
        this.setRotation(this.rantenna, 0.0f, 0.0f, -0.2792527f);
        this.larm1 = new ModelRenderer(this, 51, 0);
        this.larm1.addBox(0.0f, 0.0f, -1.0f, 1, 23, 4);
        this.larm1.setPos(2.0f, -14.0f, -23.0f);
        this.larm1.mirror = true;
        this.setRotation(this.larm1, 0.0349066f, 0.0f, 0.0f);
        this.larm2 = new ModelRenderer(this, 30, 0);
        this.larm2.addBox(0.0f, -18.0f, -2.0f, 1, 18, 2);
        this.larm2.setPos(2.0f, 8.0f, -22.0f);
        this.larm2.mirror = true;
        this.setRotation(this.larm2, 0.5585054f, 0.0f, 0.0f);
        this.larm3 = new ModelRenderer(this, 16, 0);
        this.larm3.addBox(0.0f, 0.0f, 0.0f, 1, 21, 1);
        this.larm3.setPos(2.0f, -7.0f, -33.0f);
        this.larm3.mirror = true;
        this.setRotation(this.larm3, 0.0f, 0.0f, 0.0f);
        this.lfwing = new ModelRenderer(this, 0, 67);
        this.lfwing.addBox(0.0f, 0.0f, 0.0f, 48, 1, 12);
        this.lfwing.setPos(2.0f, -11.0f, 0.0f);
        this.lfwing.mirror = true;
        this.setRotation(this.lfwing, -0.2268928f, 0.0f, -0.6981317f);
        this.rfwing = new ModelRenderer(this, 0, 83);
        this.rfwing.addBox(-48.0f, 0.0f, 0.0f, 48, 1, 12);
        this.rfwing.setPos(-1.0f, -11.0f, 0.0f);
        this.rfwing.mirror = true;
        this.setRotation(this.rfwing, -0.2268928f, 0.0f, 0.6981317f);
        this.lrwing = new ModelRenderer(this, 0, 100);
        this.lrwing.addBox(0.0f, 0.0f, 0.0f, 42, 1, 17);
        this.lrwing.setPos(2.0f, -6.0f, 10.0f);
        this.lrwing.mirror = true;
        this.setRotation(this.lrwing, -0.2268928f, 0.0f, -0.3490659f);
        this.rrwing = new ModelRenderer(this, 0, 122);
        this.rrwing.addBox(-42.0f, 0.0f, 0.0f, 42, 1, 17);
        this.rrwing.setPos(-1.0f, -6.0f, 10.0f);
        this.rrwing.mirror = true;
        this.setRotation(this.rrwing, -0.2268928f, 0.0f, 0.3490659f);
        this.rarm1 = new ModelRenderer(this, 38, 0);
        this.rarm1.addBox(0.0f, 0.0f, -1.0f, 1, 23, 4);
        this.rarm1.setPos(-1.0f, -14.0f, -23.0f);
        this.rarm1.mirror = true;
        this.setRotation(this.rarm1, 0.0349066f, 0.0f, 0.0f);
        this.rarm2 = new ModelRenderer(this, 22, 0);
        this.rarm2.addBox(0.0f, -18.0f, -2.0f, 1, 18, 2);
        this.rarm2.setPos(-1.0f, 8.0f, -22.0f);
        this.rarm2.mirror = true;
        this.setRotation(this.rarm2, 0.5585054f, 0.0f, 0.0f);
        this.rarm3 = new ModelRenderer(this, 10, 0);
        this.rarm3.addBox(0.0f, 0.0f, 0.0f, 1, 21, 1);
        this.rarm3.setPos(-1.0f, -7.0f, -33.0f);
        this.rarm3.mirror = true;
        this.setRotation(this.rarm3, 0.0f, 0.0f, 0.0f);
        this.rlfleg3 = new ModelRenderer(this, 64, 6);
        this.rlfleg3.addBox(-20.0f, 0.0f, 0.0f, 20, 1, 1);
        this.rlfleg3.setPos(-1.0f, -5.0f, 0.0f);
        this.rlfleg3.mirror = true;
        this.setRotation(this.rlfleg3, 0.0f, -0.1570796f, 0.0f);
        this.rfleg4 = new ModelRenderer(this, 64, 28);
        this.rfleg4.addBox(-19.0f, 0.0f, -2.0f, 4, 1, 5);
        this.rfleg4.setPos(-1.0f, -5.0f, 0.0f);
        this.rfleg4.mirror = true;
        this.setRotation(this.rfleg4, 0.0f, -0.1570796f, 0.0f);
        this.rfleg2 = new ModelRenderer(this, 7, 32);
        this.rfleg2.addBox(0.0f, 0.0f, 0.0f, 1, 22, 1);
        this.rfleg2.setPos(-21.0f, -5.0f, -3.0f);
        this.rfleg2.mirror = true;
        this.setRotation(this.rfleg2, 0.0f, 0.0f, 0.2792527f);
        this.rfleg1 = new ModelRenderer(this, 42, 35);
        this.rfleg1.addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
        this.rfleg1.setPos(-27.0f, 16.0f, -3.0f);
        this.rfleg1.mirror = true;
        this.setRotation(this.rfleg1, 0.0f, 0.0f, 0.6283185f);
        this.rrleg3 = new ModelRenderer(this, 64, 16);
        this.rrleg3.addBox(-20.0f, 0.0f, 0.0f, 20, 1, 1);
        this.rrleg3.setPos(-1.0f, 0.0f, 8.0f);
        this.rrleg3.mirror = true;
        this.setRotation(this.rrleg3, 0.0f, 0.1570796f, 0.0f);
        this.rrleg4 = new ModelRenderer(this, 64, 44);
        this.rrleg4.addBox(-19.0f, 0.0f, -2.0f, 4, 1, 5);
        this.rrleg4.setPos(-1.0f, 0.0f, 8.0f);
        this.rrleg4.mirror = true;
        this.setRotation(this.rrleg4, 0.0f, 0.1570796f, 0.0f);
        this.rrleg2 = new ModelRenderer(this, 21, 32);
        this.rrleg2.addBox(0.0f, 0.0f, 0.0f, 1, 22, 1);
        this.rrleg2.setPos(-21.0f, 0.0f, 11.0f);
        this.rrleg2.mirror = true;
        this.setRotation(this.rrleg2, 0.0f, 0.0f, 0.5410521f);
        this.rrleg1 = new ModelRenderer(this, 49, 35);
        this.rrleg1.addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
        this.rrleg1.setPos(-32.0f, 18.0f, 11.0f);
        this.rrleg1.mirror = true;
        this.setRotation(this.rrleg1, 0.0f, 0.0f, 0.8726646f);
    }
    @Override
    public void setupAnim(Mantis entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float a1;
        float newangle = 0.0f;
        Mantis b = (Mantis)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = MathHelper.cos((float)(f2 * 0.9f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.lfwing.zRot = -0.698f - newangle;
        this.rfwing.zRot = 0.698f + newangle;
        newangle = MathHelper.cos((float)(f2 * 0.9f * this.wingspeed)) * 3.1415927f * 0.35f;
        this.lrwing.zRot = -0.349f + newangle;
        this.rrwing.zRot = 0.349f - newangle;
        if (b.getAttacking() == 0) {
            newangle = MathHelper.cos((float)(f2 * 0.051f * this.wingspeed)) * 3.1415927f * 0.013f;
            a1 = -0.2f;
        } else {
            newangle = MathHelper.cos((float)(f2 * 0.51f * this.wingspeed)) * 3.1415927f * 0.25f;
            a1 = -0.698f;
        }
        this.larm1.xRot = a1 + newangle;
        this.larm2.z = (float)((double)(this.larm1.z + 1.0f) + Math.sin(this.larm1.xRot) * 22.0);
        this.larm2.y = (float)((double)this.larm1.y + Math.cos(this.larm1.xRot) * 22.0);
        this.larm2.xRot = - a1 - newangle;
        this.larm3.z = (float)((double)(this.larm2.z + 1.0f) - Math.sin(this.larm2.xRot) * 17.0);
        this.larm3.y = (float)((double)this.larm2.y - Math.cos(this.larm2.xRot) * 17.0);
        this.larm3.xRot = a1 + newangle;
        this.rarm1.xRot = a1 - newangle;
        this.rarm2.z = (float)((double)(this.rarm1.z + 1.0f) + Math.sin(this.rarm1.xRot) * 22.0);
        this.rarm2.y = (float)((double)this.rarm1.y + Math.cos(this.rarm1.xRot) * 22.0);
        this.rarm2.xRot = - a1 + newangle;
        this.rarm3.z = (float)((double)(this.rarm2.z + 1.0f) - Math.sin(this.rarm2.xRot) * 17.0);
        this.rarm3.y = (float)((double)this.rarm2.y - Math.cos(this.rarm2.xRot) * 17.0);
        this.rarm3.xRot = a1 - newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lfleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.reye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lantenna.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rantenna.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlfleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

