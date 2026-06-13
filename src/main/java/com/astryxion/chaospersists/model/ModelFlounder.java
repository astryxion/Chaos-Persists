/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelFlounder
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.Flounder;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFlounder extends EntityModel<Flounder> {
    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer rfin;
    ModelRenderer lfin;

    public ModelFlounder() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 16);
        this.body.addBox(-4.0f, 0.0f, -5.0f, 8, 1, 12);
        this.body.setPos(0.0f, 22.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 5);
        this.head.addBox(-2.0f, 0.0f, 0.0f, 4, 1, 2);
        this.head.setPos(0.0f, 22.0f, -7.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 30, 0);
        this.tail1.addBox(-2.0f, 0.0f, 0.0f, 4, 1, 2);
        this.tail1.setPos(0.0f, 22.0f, 7.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 30, 4);
        this.tail2.addBox(-3.0f, 0.0f, 2.0f, 6, 1, 3);
        this.tail2.setPos(0.0f, 22.0f, 7.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.rfin = new ModelRenderer(this, 12, 0);
        this.rfin.addBox(-3.0f, 0.0f, 0.0f, 3, 1, 2);
        this.rfin.setPos(-4.0f, 22.0f, -2.0f);
        this.rfin.mirror = true;
        this.setRotation(this.rfin, 0.0f, 0.0f, 0.0f);
        this.lfin = new ModelRenderer(this, 0, 0);
        this.lfin.addBox(0.0f, 0.0f, 0.0f, 3, 1, 2);
        this.lfin.setPos(4.0f, 22.0f, -2.0f);
        this.lfin.mirror = true;
        this.setRotation(this.lfin, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Flounder entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle;
        float newangle2;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if ((double)f1 > 0.1) {
            newangle = MathHelper.cos((float)(f2 * 1.3f)) * 3.1415927f * 0.25f * f1;
            newangle2 = MathHelper.cos((float)(f2 * 1.7f)) * 3.1415927f * 0.25f * f1;
        } else {
            newangle = 0.0f;
            newangle2 = 0.0f;
        }
        this.lfin.zRot = newangle;
        this.rfin.zRot = newangle2;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.2f)) * 3.1415927f * 0.25f * f1 : MathHelper.cos((float)(f2 * 0.7f)) * 3.1415927f * 0.05f;
        this.tail1.xRot = this.tail2.xRot = newangle;
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

