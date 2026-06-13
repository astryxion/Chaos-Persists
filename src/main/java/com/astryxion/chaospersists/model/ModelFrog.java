/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Frog
 *  com.astryxion.chaospersists.ModelFrog
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Frog;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFrog extends EntityModel<Frog> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer jaw;
    ModelRenderer lfleg;
    ModelRenderer rfleg;
    ModelRenderer lleg1;
    ModelRenderer rleg1;
    ModelRenderer lleg2;
    ModelRenderer rleg2;
    ModelRenderer leye;
    ModelRenderer reye;

    public ModelFrog(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.body = new ModelRenderer(this, 41, 0);
        this.body.addBox(-4.0f, -10.0f, 0.0f, 8, 11, 2);
        this.body.setPos(0.0f, 24.0f, 2.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.7330383f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 42, 15);
        this.jaw.addBox(-4.0f, -8.0f, 0.0f, 8, 8, 1);
        this.jaw.setPos(0.0f, 24.0f, 2.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, 1.22173f, 0.0f, 0.0f);
        this.lfleg = new ModelRenderer(this, 14, 0);
        this.lfleg.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.lfleg.setPos(3.0f, 20.0f, 0.0f);
        this.lfleg.mirror = true;
        this.setRotation(this.lfleg, -0.5235988f, 0.0f, -0.4712389f);
        this.rfleg = new ModelRenderer(this, 20, 0);
        this.rfleg.addBox(-1.0f, 0.0f, 0.0f, 1, 5, 1);
        this.rfleg.setPos(-3.0f, 20.0f, 0.0f);
        this.rfleg.mirror = true;
        this.setRotation(this.rfleg, -0.5235988f, 0.0f, 0.4712389f);
        this.lleg1 = new ModelRenderer(this, 10, 8);
        this.lleg1.addBox(0.0f, -9.0f, -1.0f, 1, 9, 2);
        this.lleg1.setPos(3.0f, 24.0f, 3.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, 0.0f, 0.0f, 0.2268928f);
        this.rleg1 = new ModelRenderer(this, 18, 8);
        this.rleg1.addBox(-1.0f, -9.0f, -1.0f, 1, 9, 2);
        this.rleg1.setPos(-3.0f, 24.0f, 3.0f);
        this.rleg1.mirror = true;
        this.setRotation(this.rleg1, 0.0f, 0.0f, -0.2268928f);
        this.lleg2 = new ModelRenderer(this, 11, 20);
        this.lleg2.addBox(0.0f, 0.0f, 0.0f, 1, 10, 1);
        this.lleg2.setPos(5.0f, 15.0f, 3.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, 0.0f, 0.0f, -0.3839724f);
        this.rleg2 = new ModelRenderer(this, 19, 20);
        this.rleg2.addBox(-1.0f, 0.0f, 0.0f, 1, 10, 1);
        this.rleg2.setPos(-5.0f, 15.0f, 3.0f);
        this.rleg2.mirror = true;
        this.setRotation(this.rleg2, 0.0f, 0.0f, 0.3839724f);
        this.leye = new ModelRenderer(this, 0, 8);
        this.leye.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.leye.setPos(2.0f, 17.0f, -2.0f);
        this.leye.mirror = true;
        this.setRotation(this.leye, 0.7330383f, 0.0f, 0.0f);
        this.reye = new ModelRenderer(this, 0, 4);
        this.reye.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.reye.setPos(-3.0f, 17.0f, -2.0f);
        this.reye.mirror = true;
        this.setRotation(this.reye, 0.7330383f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Frog entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Frog c = (Frog)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * this.wingspeed * 1.4f)) * 3.1415927f * 0.55f * f1 : 0.0f;
        this.lfleg.yRot = newangle;
        this.rfleg.yRot = - newangle;
        this.lleg2.yRot = (- newangle) / 2.0f;
        this.rleg2.yRot = newangle / 2.0f;
        newangle = c.getSinging() != 0 ? MathHelper.cos((float)(f2 * 0.85f * this.wingspeed)) * 3.1415927f * 0.15f : 0.0f;
        this.jaw.xRot = newangle + 1.22f;
        if (c.getDeltaMovement().y > 0.10000000149011612 || c.getDeltaMovement().y < -0.10000000149011612) {
            this.lleg1.zRot = 2.44f;
            this.rleg1.zRot = -2.44f;
        } else {
            this.lleg1.zRot = 0.227f;
            this.rleg1.zRot = -0.227f;
        }
        this.lleg2.y = this.lleg1.y - (float)Math.cos(this.lleg1.zRot) * 9.0f;
        this.lleg2.x = this.lleg1.x + (float)Math.sin(this.lleg1.zRot) * 9.0f;
        this.rleg2.y = this.rleg1.y - (float)Math.cos(this.rleg1.zRot) * 9.0f;
        this.rleg2.x = this.rleg1.x + (float)Math.sin(this.rleg1.zRot) * 9.0f;
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.reye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

