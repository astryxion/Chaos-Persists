/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.LeafMonster
 *  com.astryxion.chaospersists.ModelLeafMonster
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.LeafMonster;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLeafMonster extends EntityModel<LeafMonster> {
    ModelRenderer body;
    ModelRenderer larm;
    ModelRenderer rarm;
    ModelRenderer lleg;
    ModelRenderer rleg;

    public ModelLeafMonster() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 128;
        // textureHeight = 128;
        this.body = new ModelRenderer(this, 32, 32);
        this.body.addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16);
        this.body.setPos(0.0f, 0.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.larm = new ModelRenderer(this, 64, 0);
        this.larm.addBox(0.0f, -16.0f, -8.0f, 16, 16, 16);
        this.larm.setPos(8.0f, -8.0f, 0.0f);
        this.larm.mirror = true;
        this.setRotation(this.larm, 0.0f, 0.0f, 0.0f);
        this.rarm = new ModelRenderer(this, 0, 0);
        this.rarm.addBox(-16.0f, -16.0f, -8.0f, 16, 16, 16);
        this.rarm.setPos(-8.0f, -8.0f, 0.0f);
        this.rarm.mirror = true;
        this.setRotation(this.rarm, 0.0f, 0.0f, 0.0f);
        this.lleg = new ModelRenderer(this, 64, 64);
        this.lleg.addBox(0.0f, 0.0f, -8.0f, 16, 16, 16);
        this.lleg.setPos(8.0f, 8.0f, 0.0f);
        this.lleg.mirror = true;
        this.setRotation(this.lleg, 0.0f, 0.0f, 0.0f);
        this.rleg = new ModelRenderer(this, 0, 64);
        this.rleg.addBox(-16.0f, 0.0f, -8.0f, 16, 16, 16);
        this.rleg.setPos(-8.0f, 8.0f, 0.0f);
        this.rleg.mirror = true;
        this.setRotation(this.rleg, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(LeafMonster entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        LeafMonster lm = (LeafMonster)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if (lm.getAttacking() == 0) {
            this.body.y = 16.0f;
            this.rarm.y = 8.0f;
            this.larm.y = 8.0f;
            this.rarm.yRot = 0.0f;
            this.larm.yRot = 0.0f;
            this.rarm.xRot = 0.0f;
            this.larm.xRot = 0.0f;
            this.lleg.xRot = 0.0f;
            this.rleg.xRot = 0.0f;
        } else {
            this.body.y = 0.0f;
            this.rarm.y = -8.0f;
            this.larm.y = -8.0f;
            float newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 0.95f)) * 3.1415927f * 0.25f * f1 : 0.0f;
            this.lleg.xRot = newangle;
            this.rleg.xRot = - newangle;
            newangle = MathHelper.cos((float)(f2 * 0.7f)) * 3.1415927f * 0.55f;
            this.rarm.yRot = - Math.abs(newangle);
            this.larm.yRot = Math.abs(newangle);
            this.rarm.xRot = - Math.abs(newangle);
            this.larm.xRot = - Math.abs(newangle);
        }
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

