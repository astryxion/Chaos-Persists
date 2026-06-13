/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Cassowary
 *  com.astryxion.chaospersists.ModelCassowary
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Cassowary;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelCassowary extends EntityModel<Cassowary> {
    private float wingspeed = 1.0f;
    ModelRenderer tail;
    ModelRenderer body;
    ModelRenderer neck1;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer beak;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer crest;
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer gobbler;

    public ModelCassowary(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.tail = new ModelRenderer(this, 38, 16);
        this.tail.addBox(-3.0f, 0.0f, 0.0f, 6, 9, 7);
        this.tail.setPos(0.0f, 8.0f, 1.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.8922867f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 0, 13);
        this.body.addBox(-4.0f, 0.0f, 0.0f, 8, 10, 9);
        this.body.setPos(0.0f, 5.0f, -3.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.3346075f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 48, 0);
        this.neck1.addBox(-2.0f, 0.0f, 0.0f, 4, 5, 4);
        this.neck1.setPos(0.0f, 4.0f, -1.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, -1.189716f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 38, 0);
        this.neck.addBox(-1.0f, 0.0f, 0.0f, 2, 7, 2);
        this.neck.setPos(0.0f, 8.0f, -3.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, -2.806985f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 24, 0);
        this.head.addBox(-1.0f, -2.0f, -3.0f, 2, 2, 4);
        this.head.setPos(0.0f, 2.0f, -6.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0371786f, 0.0f, 0.0f);
        this.beak = new ModelRenderer(this, 28, 7);
        this.beak.addBox(-0.5f, 0.0f, 3.0f, 1, 1, 3);
        this.beak.setPos(0.0f, 2.0f, -6.0f);
        this.beak.mirror = true;
        this.setRotation(this.beak, -3.104414f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.addBox(-0.5f, 0.0f, -1.0f, 1, 11, 2);
        this.leg1.setPos(3.0f, 12.0f, 3.0f);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.addBox(-0.5f, 0.0f, -1.0f, 1, 11, 2);
        this.leg2.setPos(-3.0f, 12.0f, 3.0f);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.crest = new ModelRenderer(this, 10, 0);
        this.crest.addBox(-0.5f, -4.0f, 1.0f, 1, 4, 5);
        this.crest.setPos(0.0f, 2.0f, -6.0f);
        this.crest.mirror = true;
        this.setRotation(this.crest, 1.710216f, 0.0f, 0.0f);
        this.foot1 = new ModelRenderer(this, 47, 10);
        this.foot1.addBox(-1.033333f, 11.0f, -2.0f, 2, 1, 3);
        this.foot1.setPos(-3.0f, 12.0f, 3.0f);
        this.foot1.mirror = true;
        this.setRotation(this.foot1, 0.0f, 0.0f, 0.0f);
        this.foot2 = new ModelRenderer(this, 47, 10);
        this.foot2.addBox(-1.0f, 11.0f, -2.0f, 2, 1, 3);
        this.foot2.setPos(3.0f, 12.0f, 3.0f);
        this.foot2.mirror = true;
        this.setRotation(this.foot2, 0.0f, 0.0f, 0.0f);
        this.gobbler = new ModelRenderer(this, 38, 10);
        this.gobbler.addBox(-0.5f, -1.0f, -2.5f, 1, 5, 1);
        this.gobbler.setPos(0.0f, 8.0f, -3.0f);
        this.gobbler.mirror = true;
        this.setRotation(this.gobbler, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Cassowary entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Cassowary e = (Cassowary)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float newangle2 = 0.0f;
        if ((double)f1 > 0.1) {
            newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.15f * f1;
            newangle2 = MathHelper.cos((float)(f2 * 2.6f * this.wingspeed)) * 3.1415927f * 0.1f * f1;
        } else {
            newangle2 = 0.0f;
            newangle = 0.0f;
        }
        this.leg1.xRot = this.foot2.xRot = newangle;
        this.leg2.xRot = this.foot1.xRot = - newangle;
        this.neck.xRot = -2.827f + newangle2;
        this.gobbler.xRot = newangle2;
        this.crest.z = this.beak.z = this.neck.z + MathHelper.sin((float)this.neck.xRot) * 7.0f;
        this.head.z = this.beak.z;
        this.crest.y = this.beak.y = this.neck.y + MathHelper.cos((float)this.neck.xRot) * 7.0f;
        this.head.y = this.beak.y;
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.beak.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.crest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.gobbler.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity) {
        
    }
}

