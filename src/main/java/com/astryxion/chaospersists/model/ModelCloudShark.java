/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelCloudShark
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.CloudShark;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCloudShark extends EntityModel<CloudShark> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer jaw;
    ModelRenderer topfin;
    ModelRenderer bbody;
    ModelRenderer fins;
    ModelRenderer leftfin;
    ModelRenderer rightfin;

    public ModelCloudShark(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 6, 8, 15);
        this.body.setPos(-4.0f, 11.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 51);
        this.head.addBox(-2.5f, 0.0f, -8.0f, 5, 5, 8);
        this.head.setPos(-1.0f, 11.0f, 0.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 42, 0);
        this.jaw.addBox(-2.5f, 0.0f, -6.0f, 5, 2, 6);
        this.jaw.setPos(-1.0f, 15.0f, 0.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, 0.5056291f, 0.0f, 0.0f);
        this.topfin = new ModelRenderer(this, 0, 0);
        this.topfin.addBox(0.0f, 0.0f, 0.0f, 1, 3, 6);
        this.topfin.setPos(-1.5f, 11.0f, 5.0f);
        this.topfin.mirror = true;
        this.setRotation(this.topfin, 0.935765f, 0.0f, 0.0f);
        this.bbody = new ModelRenderer(this, 0, 9);
        this.bbody.addBox(-2.0f, 0.0f, 0.0f, 4, 8, 6);
        this.bbody.setPos(-1.0f, 11.0f, 15.0f);
        this.bbody.mirror = true;
        this.setRotation(this.bbody, 0.0f, 0.0f, 0.0f);
        this.fins = new ModelRenderer(this, 0, 24);
        this.fins.addBox(0.0f, 0.0f, 0.0f, 0, 10, 10);
        this.fins.setPos(-1.0f, 16.0f, 16.0f);
        this.fins.mirror = true;
        this.setRotation(this.fins, 0.9220296f, 0.0f, 0.0f);
        this.leftfin = new ModelRenderer(this, 0, 0);
        this.leftfin.addBox(0.0f, 0.0f, 0.0f, 0, 3, 7);
        this.leftfin.setPos(2.0f, 16.0f, 6.0f);
        this.leftfin.mirror = true;
        this.setRotation(this.leftfin, -0.6108652f, 1.134464f, -0.6108652f);
        this.rightfin = new ModelRenderer(this, 0, 0);
        this.rightfin.addBox(0.0f, 0.0f, 0.0f, 0, 3, 7);
        this.rightfin.setPos(-4.0f, 16.0f, 6.0f);
        this.rightfin.mirror = true;
        this.setRotation(this.rightfin, -0.6283185f, -1.134464f, 0.6108652f);
    }
    @Override
    public void setupAnim(CloudShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.leftfin.yRot = 1.15f + newangle;
        newangle = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.rightfin.yRot = -0.9f + newangle;
        this.fins.yRot = newangle = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.25f;
        newangle = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.jaw.xRot = 0.5f + newangle;
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bbody.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fins.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

