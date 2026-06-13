/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelBeaver
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.Beaver;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBeaver extends EntityModel<Beaver> {
    private float wingspeed = 1.0f;
    ModelRenderer head;
    ModelRenderer nose;
    ModelRenderer teeth;
    ModelRenderer body;
    ModelRenderer tail;
    ModelRenderer rff;
    ModelRenderer lff;
    ModelRenderer rrf;
    ModelRenderer lrf;

    public ModelBeaver(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.head = new ModelRenderer(this, 0, 3);
        this.head.addBox(0.0f, 0.0f, 0.0f, 6, 5, 5);
        this.head.setPos(0.0f, 15.0f, -8.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.nose = new ModelRenderer(this, 6, 0);
        this.nose.addBox(0.0f, 0.0f, 0.0f, 2, 1, 1);
        this.nose.setPos(2.0f, 18.0f, -8.5f);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0f, 0.0f, 0.0f);
        this.teeth = new ModelRenderer(this, 0, 0);
        this.teeth.addBox(0.0f, 0.0f, 0.0f, 2, 2, 1);
        this.teeth.setPos(2.0f, 19.0f, -8.2f);
        this.teeth.mirror = true;
        this.setRotation(this.teeth, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 0, 13);
        this.body.addBox(0.0f, 0.0f, 0.0f, 8, 8, 10);
        this.body.setPos(-1.0f, 14.0f, -3.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer(this, 22, 0);
        this.tail.addBox(0.0f, -1.0f, 0.0f, 5, 1, 8);
        this.tail.setPos(0.5f, 21.0f, 7.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
        this.rff = new ModelRenderer(this, 22, 9);
        this.rff.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.rff.setPos(-0.5f, 22.0f, -2.5f);
        this.rff.mirror = true;
        this.setRotation(this.rff, 0.0f, 0.0f, 0.0f);
        this.lff = new ModelRenderer(this, 22, 9);
        this.lff.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.lff.setPos(4.5f, 22.0f, -2.5f);
        this.lff.mirror = true;
        this.setRotation(this.lff, 0.0f, 0.0f, 0.0f);
        this.rrf = new ModelRenderer(this, 22, 9);
        this.rrf.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.rrf.setPos(-0.5f, 22.0f, 4.5f);
        this.rrf.mirror = true;
        this.setRotation(this.rrf, 0.0f, 0.0f, 0.0f);
        this.lrf = new ModelRenderer(this, 22, 9);
        this.lrf.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.lrf.setPos(4.5f, 22.0f, 4.5f);
        this.lrf.mirror = true;
        this.setRotation(this.lrf, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Beaver entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.rff.xRot = this.lrf.xRot = (newangle = MathHelper.cos((float)(f2 * 3.7f * this.wingspeed)) * 3.1415927f * 0.45f * f1);
        this.lff.xRot = this.rrf.xRot = - newangle;
        this.teeth.xRot = newangle = MathHelper.cos((float)(f2 * 2.7f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.tail.xRot = newangle = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f;
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.teeth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrf.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrf.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

