/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelMosquito
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.EntityMosquito;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMosquito extends EntityModel<EntityMosquito> {
    ModelRenderer body;
    ModelRenderer leftwing1;
    ModelRenderer rightwing1;
    ModelRenderer leftwing2;
    ModelRenderer rightwing2;

    public ModelMosquito() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 32;
        // textureHeight = 32;
        this.body = new ModelRenderer(this, 8, 18);
        this.body.addBox(0.0f, 0.0f, -2.0f, 1, 1, 8);
        this.body.setPos(0.0f, 17.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.leftwing1 = new ModelRenderer(this, 16, 13);
        this.leftwing1.addBox(1.0f, 0.0f, -1.0f, 3, 1, 3);
        this.leftwing1.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing1.mirror = true;
        this.setRotation(this.leftwing1, 0.0f, 0.0f, 0.0f);
        this.rightwing1 = new ModelRenderer(this, 2, 13);
        this.rightwing1.addBox(-4.0f, 0.0f, -1.0f, 3, 1, 3);
        this.rightwing1.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing1.mirror = true;
        this.setRotation(this.rightwing1, 0.0f, 0.0f, 0.0f);
        this.leftwing2 = new ModelRenderer(this, 15, 8);
        this.leftwing2.addBox(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.leftwing2.setPos(1.0f, 17.0f, 0.0f);
        this.leftwing2.mirror = true;
        this.setRotation(this.leftwing2, 0.0f, 0.0f, 0.0f);
        this.rightwing2 = new ModelRenderer(this, 2, 8);
        this.rightwing2.addBox(-5.0f, 0.0f, 0.0f, 5, 1, 1);
        this.rightwing2.setPos(0.0f, 17.0f, 0.0f);
        this.rightwing2.mirror = true;
        this.setRotation(this.rightwing2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(EntityMosquito entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        this.rightwing2.zRot = this.rightwing1.zRot = MathHelper.cos((float)(f2 * 3.0f)) * 3.1415927f * 0.25f;
        this.leftwing1.zRot = - this.rightwing1.zRot;
        this.leftwing2.zRot = - this.rightwing1.zRot;
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

