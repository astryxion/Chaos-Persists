/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRat
 *  com.astryxion.chaospersists.Rat
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Rat;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRat extends EntityModel<Rat> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer lfleg;
    ModelRenderer rfleg;
    ModelRenderer lrleg;
    ModelRenderer rrleg;
    ModelRenderer body2;
    ModelRenderer head;
    ModelRenderer nose;
    ModelRenderer lear;
    ModelRenderer rear;

    public ModelRat(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.body = new ModelRenderer(this, 27, 0);
        this.body.addBox(-2.0f, -1.0f, 0.0f, 5, 3, 10);
        this.body.setPos(0.0f, 20.0f, -3.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 0, 30);
        this.tail1.addBox(-0.5f, -1.0f, 0.0f, 2, 2, 9);
        this.tail1.setPos(0.0f, 21.0f, 7.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 0, 43);
        this.tail2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 12);
        this.tail2.setPos(0.0f, 21.0f, 16.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.lfleg = new ModelRenderer(this, 0, 14);
        this.lfleg.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.lfleg.setPos(2.0f, 22.0f, -2.0f);
        this.lfleg.mirror = true;
        this.setRotation(this.lfleg, 0.0f, 0.0f, 0.0f);
        this.rfleg = new ModelRenderer(this, 10, 14);
        this.rfleg.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.rfleg.setPos(-2.0f, 22.0f, -2.0f);
        this.rfleg.mirror = true;
        this.setRotation(this.rfleg, 0.0f, 0.0f, 0.0f);
        this.lrleg = new ModelRenderer(this, 0, 18);
        this.lrleg.addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.lrleg.setPos(2.0f, 20.0f, 4.0f);
        this.lrleg.mirror = true;
        this.setRotation(this.lrleg, 0.0f, 0.0f, 0.0f);
        this.rrleg = new ModelRenderer(this, 9, 18);
        this.rrleg.addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.rrleg.setPos(-3.0f, 20.0f, 4.0f);
        this.rrleg.mirror = true;
        this.setRotation(this.rrleg, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 0, 0);
        this.body2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.body2.setPos(0.0f, 18.0f, 0.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 27, 17);
        this.head.addBox(-1.0f, -2.0f, -3.0f, 3, 2, 4);
        this.head.setPos(0.0f, 22.0f, -4.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.nose = new ModelRenderer(this, 27, 25);
        this.nose.addBox(0.0f, -1.0f, -5.0f, 1, 1, 2);
        this.nose.setPos(0.0f, 22.0f, -4.0f);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0f, 0.0f, 0.0f);
        this.lear = new ModelRenderer(this, 0, 9);
        this.lear.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.lear.setPos(1.5f, 19.5f, -4.0f);
        this.lear.mirror = true;
        this.setRotation(this.lear, 0.0f, 0.0f, 0.0f);
        this.rear = new ModelRenderer(this, 5, 9);
        this.rear.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.rear.setPos(-1.5f, 19.5f, -4.0f);
        this.rear.mirror = true;
        this.setRotation(this.rear, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Rat entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Rat r = (Rat)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.7f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.rfleg.xRot = newangle;
        this.lfleg.xRot = - newangle;
        this.rrleg.xRot = - newangle;
        this.lrleg.xRot = newangle;
        newangle = r.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.25f : MathHelper.cos((float)(f2 * 0.4f * this.wingspeed)) * 3.1415927f * 0.05f;
        this.tail1.yRot = newangle * 0.5f;
        this.tail2.yRot = newangle * 1.25f;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 9.0f;
        this.tail2.x = this.tail1.x + (float)Math.sin(this.tail1.yRot) * 9.0f;
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

