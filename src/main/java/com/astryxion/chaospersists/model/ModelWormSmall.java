/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelWormSmall
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.WormSmall;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWormSmall extends EntityModel<WormSmall> {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer tail;

    public ModelWormSmall() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 32;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-0.5f, -5.0f, -0.5f, 1, 5, 1);
        this.head.setPos(0.0f, 14.0f, 0.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 6, 0);
        this.body.addBox(-0.5f, -5.0f, -0.5f, 1, 5, 1);
        this.body.setPos(0.0f, 19.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer(this, 12, 0);
        this.tail.addBox(-0.5f, -5.0f, -0.5f, 1, 5, 1);
        this.tail.setPos(0.0f, 24.0f, 0.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(WormSmall entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.tail.xRot = newangle = MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        float d1 = (float)(Math.sin(newangle) * 5.0);
        float d2 = (float)(Math.cos(newangle) * 5.0);
        this.body.z = this.tail.z - d1;
        this.tail.zRot = newangle = MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.1f;
        float d3 = (float)(Math.cos(newangle) * (double)d2);
        float d4 = (float)(Math.sin(newangle) * (double)d2);
        this.body.x = this.tail.x + d4;
        this.body.y = (float)((double)this.tail.y - 5.0 + (5.0 - (double)d3));
        this.body.xRot = newangle = MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 5.0);
        d2 = (float)(Math.cos(newangle) * 5.0);
        this.head.z = this.body.z - d1;
        this.body.zRot = newangle = MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.head.x = this.body.x + d4;
        this.head.y = (float)((double)this.body.y - 5.0 + (5.0 - (double)d3));
        this.head.xRot = 0.62f + MathHelper.cos((float)(f2 * 0.65f)) * 3.1415927f * 0.15f;
        this.head.zRot = MathHelper.cos((float)(f2 * 0.3f)) * 3.1415927f * 0.05f;
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, WormSmall par7Entity) {
        
    }
}

