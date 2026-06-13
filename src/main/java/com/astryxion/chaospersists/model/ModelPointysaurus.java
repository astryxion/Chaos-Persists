/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelPointysaurus
 *  com.astryxion.chaospersists.Pointysaurus
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Pointysaurus;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPointysaurus extends EntityModel<Pointysaurus> {
    private float wingspeed = 1.0f;
    ModelRenderer lfleg;
    ModelRenderer rfleg;
    ModelRenderer lrleg;
    ModelRenderer rrleg;
    ModelRenderer body1;
    ModelRenderer head;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer guard;
    ModelRenderer nose;
    ModelRenderer lhorn;
    ModelRenderer rhorn;
    ModelRenderer chorn;
    ModelRenderer tail;
    ModelRenderer bump1;
    ModelRenderer bump2;
    ModelRenderer bump3;
    ModelRenderer bump4;
    ModelRenderer bump5;
    ModelRenderer bump6;
    ModelRenderer bump7;
    ModelRenderer bump8;
    ModelRenderer bump9;
    ModelRenderer bump10;
    ModelRenderer bump11;
    ModelRenderer bump12;
    ModelRenderer bump13;
    ModelRenderer bump14;
    ModelRenderer bump15;
    ModelRenderer bump16;

    public ModelPointysaurus(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.lfleg = new ModelRenderer(this, 102, 66);
        this.lfleg.addBox(-3.0f, 0.0f, -3.0f, 6, 8, 6);
        this.lfleg.setPos(9.0f, 16.0f, -8.0f);
        this.lfleg.mirror = true;
        this.setRotation(this.lfleg, 0.0f, 0.0f, 0.0f);
        this.rfleg = new ModelRenderer(this, 102, 66);
        this.rfleg.addBox(-3.0f, 0.0f, -3.0f, 6, 8, 6);
        this.rfleg.setPos(-9.0f, 16.0f, -8.0f);
        this.rfleg.mirror = true;
        this.setRotation(this.rfleg, 0.0f, 0.0f, 0.0f);
        this.lrleg = new ModelRenderer(this, 0, 0);
        this.lrleg.addBox(-4.0f, 0.0f, -4.0f, 8, 8, 8);
        this.lrleg.setPos(9.0f, 16.0f, 12.0f);
        this.lrleg.mirror = true;
        this.setRotation(this.lrleg, 0.0f, 0.0f, 0.0f);
        this.rrleg = new ModelRenderer(this, 0, 0);
        this.rrleg.addBox(-4.0f, 0.0f, -4.0f, 8, 8, 8);
        this.rrleg.setPos(-9.0f, 16.0f, 12.0f);
        this.rrleg.mirror = true;
        this.setRotation(this.rrleg, 0.0f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer(this, 0, 87);
        this.body1.addBox(-4.0f, 0.0f, 0.0f, 22, 9, 30);
        this.body1.setPos(-7.0f, 9.0f, -12.0f);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 70, 0);
        this.head.addBox(-6.0f, -10.0f, -12.0f, 12, 10, 12);
        this.head.setPos(0.0f, 11.0f, -7.0f);
        this.head.mirror = true;
        this.setRotation(this.head, -0.1919862f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 0, 63);
        this.body2.addBox(-9.0f, 0.0f, 0.0f, 18, 7, 15);
        this.body2.setPos(0.0f, 2.0f, -9.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer(this, 0, 44);
        this.body3.addBox(-8.0f, 0.0f, 0.0f, 16, 6, 11);
        this.body3.setPos(0.0f, 3.0f, 6.0f);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.guard = new ModelRenderer(this, 60, 34);
        this.guard.addBox(-14.0f, -20.0f, -8.0f, 28, 23, 3);
        this.guard.setPos(0.0f, 11.0f, -7.0f);
        this.guard.mirror = true;
        this.setRotation(this.guard, -0.2617994f, 0.0f, 0.0f);
        this.nose = new ModelRenderer(this, 39, 0);
        this.nose.addBox(-5.0f, -9.0f, -15.0f, 10, 6, 5);
        this.nose.setPos(0.0f, 11.0f, -7.0f);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0f, 0.0f, 0.0f);
        this.lhorn = new ModelRenderer(this, 0, 18);
        this.lhorn.addBox(8.0f, -16.0f, -29.0f, 2, 2, 23);
        this.lhorn.setPos(0.0f, 11.0f, -7.0f);
        this.lhorn.mirror = true;
        this.setRotation(this.lhorn, -0.1570796f, -0.1396263f, 0.0f);
        this.rhorn = new ModelRenderer(this, 0, 18);
        this.rhorn.addBox(-9.0f, -16.0f, -29.0f, 2, 2, 23);
        this.rhorn.setPos(0.0f, 11.0f, -7.0f);
        this.rhorn.mirror = true;
        this.setRotation(this.rhorn, -0.1570796f, 0.1396263f, 0.0f);
        this.chorn = new ModelRenderer(this, 52, 13);
        this.chorn.addBox(-1.5f, -9.0f, -20.0f, 3, 3, 5);
        this.chorn.setPos(0.0f, 11.0f, -7.0f);
        this.chorn.mirror = true;
        this.setRotation(this.chorn, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer(this, 68, 70);
        this.tail.addBox(-3.0f, -3.0f, 0.0f, 6, 6, 9);
        this.tail.setPos(0.0f, 7.0f, 15.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.2792527f, 0.0f, 0.0f);
        this.bump1 = new ModelRenderer(this, 57, 17);
        this.bump1.addBox(14.0f, -20.0f, -8.0f, 2, 2, 2);
        this.bump1.setPos(0.0f, 11.0f, -7.0f);
        this.bump1.mirror = true;
        this.setRotation(this.bump1, -0.2617994f, 0.0f, 0.0f);
        this.bump2 = new ModelRenderer(this, 57, 17);
        this.bump2.addBox(14.0f, -15.0f, -8.0f, 2, 2, 2);
        this.bump2.setPos(0.0f, 11.0f, -7.0f);
        this.bump2.mirror = true;
        this.setRotation(this.bump2, -0.2617994f, 0.0f, 0.0f);
        this.bump3 = new ModelRenderer(this, 57, 17);
        this.bump3.addBox(14.0f, -10.0f, -8.0f, 2, 2, 2);
        this.bump3.setPos(0.0f, 11.0f, -7.0f);
        this.bump3.mirror = true;
        this.setRotation(this.bump3, -0.2617994f, 0.0f, 0.0f);
        this.bump4 = new ModelRenderer(this, 57, 17);
        this.bump4.addBox(14.0f, -5.0f, -8.0f, 2, 2, 2);
        this.bump4.setPos(0.0f, 11.0f, -7.0f);
        this.bump4.mirror = true;
        this.setRotation(this.bump4, -0.2617994f, 0.0f, 0.0f);
        this.bump5 = new ModelRenderer(this, 57, 17);
        this.bump5.addBox(14.0f, 0.0f, -8.0f, 2, 2, 2);
        this.bump5.setPos(0.0f, 11.0f, -7.0f);
        this.bump5.mirror = true;
        this.setRotation(this.bump5, -0.2617994f, 0.0f, 0.0f);
        this.bump6 = new ModelRenderer(this, 57, 17);
        this.bump6.addBox(-16.0f, -20.0f, -8.0f, 2, 2, 2);
        this.bump6.setPos(0.0f, 11.0f, -7.0f);
        this.bump6.mirror = true;
        this.setRotation(this.bump6, -0.2617994f, 0.0f, 0.0f);
        this.bump7 = new ModelRenderer(this, 57, 17);
        this.bump7.addBox(-16.0f, -15.0f, -8.0f, 2, 2, 2);
        this.bump7.setPos(0.0f, 11.0f, -7.0f);
        this.bump7.mirror = true;
        this.setRotation(this.bump7, -0.2617994f, 0.0f, 0.0f);
        this.bump8 = new ModelRenderer(this, 57, 17);
        this.bump8.addBox(-16.0f, -10.0f, -8.0f, 2, 2, 2);
        this.bump8.setPos(0.0f, 11.0f, -7.0f);
        this.bump8.mirror = true;
        this.setRotation(this.bump8, -0.2617994f, 0.0f, 0.0f);
        this.bump9 = new ModelRenderer(this, 57, 17);
        this.bump9.addBox(-16.0f, -5.0f, -8.0f, 2, 2, 2);
        this.bump9.setPos(0.0f, 11.0f, -7.0f);
        this.bump9.mirror = true;
        this.setRotation(this.bump9, -0.2617994f, 0.0f, 0.0f);
        this.bump10 = new ModelRenderer(this, 57, 17);
        this.bump10.addBox(-16.0f, 0.0f, -8.0f, 2, 2, 2);
        this.bump10.setPos(0.0f, 11.0f, -7.0f);
        this.bump10.mirror = true;
        this.setRotation(this.bump10, -0.2617994f, 0.0f, 0.0f);
        this.bump11 = new ModelRenderer(this, 57, 17);
        this.bump11.addBox(12.0f, -22.0f, -8.0f, 2, 2, 2);
        this.bump11.setPos(0.0f, 11.0f, -7.0f);
        this.bump11.mirror = true;
        this.setRotation(this.bump11, -0.2617994f, 0.0f, 0.0f);
        this.bump12 = new ModelRenderer(this, 57, 17);
        this.bump12.addBox(7.0f, -22.0f, -8.0f, 2, 2, 2);
        this.bump12.setPos(0.0f, 11.0f, -7.0f);
        this.bump12.mirror = true;
        this.setRotation(this.bump12, -0.2617994f, 0.0f, 0.0f);
        this.bump13 = new ModelRenderer(this, 57, 17);
        this.bump13.addBox(2.0f, -22.0f, -8.0f, 2, 2, 2);
        this.bump13.setPos(0.0f, 11.0f, -7.0f);
        this.bump13.mirror = true;
        this.setRotation(this.bump13, -0.2617994f, 0.0f, 0.0f);
        this.bump14 = new ModelRenderer(this, 57, 17);
        this.bump14.addBox(-4.0f, -22.0f, -8.0f, 2, 2, 2);
        this.bump14.setPos(0.0f, 11.0f, -7.0f);
        this.bump14.mirror = true;
        this.setRotation(this.bump14, -0.2617994f, 0.0f, 0.0f);
        this.bump15 = new ModelRenderer(this, 57, 17);
        this.bump15.addBox(-9.0f, -22.0f, -8.0f, 2, 2, 2);
        this.bump15.setPos(0.0f, 11.0f, -7.0f);
        this.bump15.mirror = true;
        this.setRotation(this.bump15, -0.2617994f, 0.0f, 0.0f);
        this.bump16 = new ModelRenderer(this, 57, 17);
        this.bump16.addBox(-14.0f, -22.0f, -8.0f, 2, 2, 2);
        this.bump16.setPos(0.0f, 11.0f, -7.0f);
        this.bump16.mirror = true;
        this.setRotation(this.bump16, -0.2617994f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Pointysaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Pointysaurus e = (Pointysaurus)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.lfleg.xRot = newangle;
        this.rrleg.xRot = newangle;
        this.rfleg.xRot = - newangle;
        this.lrleg.xRot = - newangle;
        this.nose.yRot = this.head.yRot = (float)Math.toRadians(f3) * 0.45f;
        this.chorn.yRot = this.head.yRot;
        this.lhorn.yRot = this.head.yRot - 0.14f;
        this.rhorn.yRot = this.head.yRot + 0.14f;
        this.guard.yRot = this.head.yRot;
        this.bump1.yRot = this.head.yRot;
        this.bump2.yRot = this.head.yRot;
        this.bump3.yRot = this.head.yRot;
        this.bump4.yRot = this.head.yRot;
        this.bump5.yRot = this.head.yRot;
        this.bump6.yRot = this.head.yRot;
        this.bump7.yRot = this.head.yRot;
        this.bump8.yRot = this.head.yRot;
        this.bump9.yRot = this.head.yRot;
        this.bump10.yRot = this.head.yRot;
        this.bump11.yRot = this.head.yRot;
        this.bump12.yRot = this.head.yRot;
        this.bump13.yRot = this.head.yRot;
        this.bump14.yRot = this.head.yRot;
        this.bump15.yRot = this.head.yRot;
        this.bump16.yRot = this.head.yRot;
        this.nose.xRot = this.head.xRot = (float)Math.toRadians(f4) * 0.45f;
        this.chorn.xRot = this.head.xRot;
        this.lhorn.xRot = this.head.xRot - 0.16f;
        this.rhorn.xRot = this.head.xRot - 0.16f;
        this.bump1.xRot = this.guard.xRot = this.head.xRot - 0.262f;
        this.bump2.xRot = this.guard.xRot;
        this.bump3.xRot = this.guard.xRot;
        this.bump4.xRot = this.guard.xRot;
        this.bump5.xRot = this.guard.xRot;
        this.bump6.xRot = this.guard.xRot;
        this.bump7.xRot = this.guard.xRot;
        this.bump8.xRot = this.guard.xRot;
        this.bump9.xRot = this.guard.xRot;
        this.bump10.xRot = this.guard.xRot;
        this.bump11.xRot = this.guard.xRot;
        this.bump12.xRot = this.guard.xRot;
        this.bump13.xRot = this.guard.xRot;
        this.bump14.xRot = this.guard.xRot;
        this.bump15.xRot = this.guard.xRot;
        this.bump16.xRot = this.guard.xRot;
        newangle = e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f : MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.05f;
        this.tail.yRot = newangle;
        newangle = MathHelper.cos((float)(f2 * 0.02f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.tail.xRot = newangle + 0.28f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.guard.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lhorn.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rhorn.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chorn.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

