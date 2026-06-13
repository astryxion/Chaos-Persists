/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Cricket
 *  com.astryxion.chaospersists.ModelCricket
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Cricket;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCricket extends EntityModel<Cricket> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer abdomen;
    ModelRenderer lfleg;
    ModelRenderer lrleg;
    ModelRenderer rfleg;
    ModelRenderer rrleg;
    ModelRenderer lleg1;
    ModelRenderer rleg1;
    ModelRenderer lleg2;
    ModelRenderer rleg2;

    public ModelCricket(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 25);
        this.body.addBox(-1.0f, -1.0f, -3.0f, 3, 3, 6);
        this.body.setPos(0.0f, 21.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 17);
        this.head.addBox(-1.0f, -2.0f, -1.0f, 3, 4, 3);
        this.head.setPos(0.0f, 21.0f, -5.0f);
        this.head.mirror = true;
        this.setRotation(this.head, -0.1745329f, 0.0f, 0.0f);
        this.abdomen = new ModelRenderer(this, 0, 36);
        this.abdomen.addBox(-0.5f, -1.0f, 3.0f, 2, 2, 3);
        this.abdomen.setPos(0.0f, 21.0f, 0.0f);
        this.abdomen.mirror = true;
        this.setRotation(this.abdomen, 0.0f, 0.0f, 0.0f);
        this.lfleg = new ModelRenderer(this, 25, 0);
        this.lfleg.addBox(2.0f, 0.0f, 0.0f, 5, 1, 1);
        this.lfleg.setPos(0.0f, 21.0f, -2.0f);
        this.lfleg.mirror = true;
        this.setRotation(this.lfleg, 0.0f, 0.4712389f, 0.418879f);
        this.lrleg = new ModelRenderer(this, 23, 4);
        this.lrleg.addBox(1.0f, 0.0f, -2.0f, 6, 1, 1);
        this.lrleg.setPos(0.0f, 21.0f, 0.0f);
        this.lrleg.mirror = true;
        this.setRotation(this.lrleg, 0.0f, -0.296706f, 0.418879f);
        this.rfleg = new ModelRenderer(this, 25, 8);
        this.rfleg.addBox(-7.0f, 0.0f, 0.0f, 5, 1, 1);
        this.rfleg.setPos(1.0f, 21.0f, -2.0f);
        this.rfleg.mirror = true;
        this.setRotation(this.rfleg, 0.0f, -0.5410521f, -0.4363323f);
        this.rrleg = new ModelRenderer(this, 25, 12);
        this.rrleg.addBox(-7.0f, -1.0f, 0.0f, 5, 1, 1);
        this.rrleg.setPos(1.0f, 22.0f, -2.0f);
        this.rrleg.mirror = true;
        this.setRotation(this.rrleg, 0.0f, 0.3839724f, -0.418879f);
        this.lleg1 = new ModelRenderer(this, 40, 0);
        this.lleg1.addBox(-1.0f, -1.0f, 0.0f, 1, 2, 8);
        this.lleg1.setPos(2.0f, 22.0f, 0.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, 0.5585054f, 0.4363323f, 0.0f);
        this.rleg1 = new ModelRenderer(this, 40, 11);
        this.rleg1.addBox(0.0f, -1.0f, 0.0f, 1, 2, 8);
        this.rleg1.setPos(-1.0f, 22.0f, 0.0f);
        this.rleg1.mirror = true;
        this.setRotation(this.rleg1, 0.5585054f, -0.4363323f, 0.0f);
        this.lleg2 = new ModelRenderer(this, 21, 23);
        this.lleg2.addBox(-0.5f, -6.5f, 4.5f, 1, 1, 8);
        this.lleg2.setPos(2.0f, 22.0f, 0.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, -0.3665191f, 0.3490659f, 0.0f);
        this.rleg2 = new ModelRenderer(this, 21, 34);
        this.rleg2.addBox(-0.5f, -6.5f, 4.0f, 1, 1, 8);
        this.rleg2.setPos(-1.0f, 22.0f, 0.0f);
        this.rleg2.mirror = true;
        this.setRotation(this.rleg2, -0.3665191f, -0.3490659f, 0.0f);
    }
    @Override
    public void setupAnim(Cricket entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Cricket c = (Cricket)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.lfleg.yRot = 0.47f + newangle;
        this.rfleg.yRot = -0.54f + newangle;
        this.lrleg.yRot = -0.296f - newangle;
        this.rrleg.yRot = 0.384f - newangle;
        if (c.getSinging() != 0) {
            newangle = MathHelper.cos((float)(f2 * 3.0f * this.wingspeed)) * 3.1415927f * 0.25f;
            this.lleg1.yRot = -0.035f;
            this.lleg2.yRot = -0.105f;
            this.rleg1.yRot = 0.035f;
            this.rleg2.yRot = 0.105f;
        } else {
            newangle = 0.0f;
            this.lleg1.yRot = 0.436f;
            this.lleg2.yRot = 0.349f;
            this.rleg1.yRot = -0.436f;
            this.rleg2.yRot = -0.349f;
        }
        this.lleg1.xRot = newangle + 0.558f;
        this.lleg2.xRot = newangle - 0.366f;
        this.rleg1.xRot = - newangle + 0.558f;
        this.rleg2.xRot = - newangle - 0.366f;
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

