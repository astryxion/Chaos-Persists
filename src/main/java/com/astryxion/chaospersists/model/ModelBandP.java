/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.BandP
 *  com.astryxion.chaospersists.ModelBandP
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.item.BandP;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBandP extends EntityModel<BandP> {
    private float wingspeed = 1.0f;
    ModelRenderer belly;
    ModelRenderer chest;
    ModelRenderer head;
    ModelRenderer lleg;
    ModelRenderer rleg;
    ModelRenderer larm;
    ModelRenderer rarm;

    public ModelBandP(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 128;
        this.belly = new ModelRenderer(this, 0, 61);
        this.belly.addBox(-8.0f, -5.0f, -7.0f, 16, 10, 16);
        this.belly.setPos(0.0f, 12.0f, 0.0f);
        this.belly.mirror = true;
        this.setRotation(this.belly, 0.0698132f, 0.0f, 0.0f);
        this.chest = new ModelRenderer(this, 0, 42);
        this.chest.addBox(-5.0f, -3.0f, -5.0f, 10, 6, 10);
        this.chest.setPos(0.0f, 5.0f, 2.0f);
        this.chest.mirror = true;
        this.setRotation(this.chest, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 11);
        this.head.addBox(-3.0f, -5.0f, -3.0f, 6, 6, 6);
        this.head.setPos(0.0f, 1.0f, 3.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.lleg = new ModelRenderer(this, 25, 90);
        this.lleg.addBox(-2.0f, 0.0f, -3.0f, 6, 8, 6);
        this.lleg.setPos(2.0f, 16.0f, 2.0f);
        this.lleg.mirror = true;
        this.setRotation(this.lleg, 0.0f, 0.0f, 0.0f);
        this.rleg = new ModelRenderer(this, 0, 90);
        this.rleg.addBox(-4.0f, 0.0f, -3.0f, 6, 8, 6);
        this.rleg.setPos(-2.0f, 16.0f, 2.0f);
        this.rleg.mirror = true;
        this.setRotation(this.rleg, 0.0f, 0.0f, 0.0f);
        this.larm = new ModelRenderer(this, 0, 25);
        this.larm.addBox(-1.0f, -1.0f, -2.0f, 4, 10, 4);
        this.larm.setPos(6.0f, 4.0f, 3.0f);
        this.larm.mirror = true;
        this.setRotation(this.larm, 0.0f, 0.0f, -0.4886922f);
        this.rarm = new ModelRenderer(this, 18, 25);
        this.rarm.addBox(-3.0f, -1.0f, -2.0f, 4, 10, 4);
        this.rarm.setPos(-6.0f, 4.0f, 3.0f);
        this.rarm.mirror = true;
        this.setRotation(this.rarm, 0.0f, 0.0f, 0.4886922f);
    }
    @Override
    public void setupAnim(BandP entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        BandP e = (BandP)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float newangle2 = 0.0f;
        float newangle3 = 0.0f;
        if ((double)f1 > 0.1) {
            newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1;
            newangle2 = MathHelper.cos((float)(f2 * 2.6f * this.wingspeed)) * 3.1415927f * 0.025f * f1;
            newangle3 = newangle;
        } else {
            newangle = 0.0f;
            newangle2 = MathHelper.cos((float)(f2 * 0.6f * this.wingspeed)) * 3.1415927f * 0.005f;
            newangle3 = MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.02f;
        }
        this.lleg.xRot = newangle;
        this.rleg.xRot = - newangle;
        this.belly.xRot = 0.07f + newangle2;
        this.larm.xRot = - newangle3;
        this.rarm.xRot = newangle3;
        this.belly.yRot = (- newangle) / 2.0f;
        this.head.yRot = (float)Math.toRadians(f3);
        this.head.xRot = (float)Math.toRadians(f4);
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.belly.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

