/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelWormMedium
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.WormMedium;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWormMedium extends EntityModel<WormMedium> {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer tail;
    ModelRenderer tooth1;
    ModelRenderer tooth2;
    ModelRenderer tooth3;
    ModelRenderer tooth4;
    ModelRenderer head2;

    public ModelWormMedium() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 32;
        this.head = new ModelRenderer(this, 24, 0);
        this.head.addBox(-1.5f, -12.0f, -1.5f, 3, 12, 3);
        this.head.setPos(0.0f, 1.0f, 0.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 37, 0);
        this.body.addBox(-1.5f, -12.0f, -1.5f, 3, 12, 3);
        this.body.setPos(0.0f, 13.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer(this, 50, 0);
        this.tail.addBox(-1.5f, -12.0f, -1.5f, 3, 12, 3);
        this.tail.setPos(0.0f, 25.0f, 0.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer(this, 15, 0);
        this.tooth1.addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1);
        this.tooth1.setPos(1.0f, -11.0f, 0.0f);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, 0.0f, 0.0f, 0.0f);
        this.tooth2 = new ModelRenderer(this, 5, 0);
        this.tooth2.addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1);
        this.tooth2.setPos(-1.0f, -11.0f, 0.0f);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, 0.0f, 0.0f, 0.0f);
        this.tooth3 = new ModelRenderer(this, 0, 0);
        this.tooth3.addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1);
        this.tooth3.setPos(0.0f, -11.0f, 1.0f);
        this.tooth3.mirror = true;
        this.setRotation(this.tooth3, 0.0f, 0.0f, 0.0f);
        this.tooth4 = new ModelRenderer(this, 10, 0);
        this.tooth4.addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1);
        this.tooth4.setPos(0.0f, -11.0f, -1.0f);
        this.tooth4.mirror = true;
        this.setRotation(this.tooth4, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 0, 6);
        this.head2.addBox(-2.0f, -8.0f, -2.0f, 4, 8, 4);
        this.head2.setPos(0.0f, 0.0f, 0.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(com.astryxion.chaospersists.entity.WormMedium entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.tail.xRot = newangle = MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.1f;
        float d1 = (float)(Math.sin(newangle) * 12.0);
        float d2 = (float)(Math.cos(newangle) * 12.0);
        this.body.z = this.tail.z - d1;
        this.tail.zRot = newangle = MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.08f;
        float d3 = (float)(Math.cos(newangle) * (double)d2);
        float d4 = (float)(Math.sin(newangle) * (double)d2);
        this.body.x = this.tail.x + d4;
        this.body.y = (float)((double)this.tail.y - 12.0 + (12.0 - (double)d3));
        this.body.xRot = newangle = MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.1f;
        d1 = (float)(Math.sin(newangle) * 12.0);
        d2 = (float)(Math.cos(newangle) * 12.0);
        this.head2.z = this.head.z = this.body.z - d1;
        this.body.zRot = newangle = MathHelper.cos((float)(f2 * 0.15f)) * 3.1415927f * 0.07f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.head2.x = this.head.x = this.body.x + d4;
        this.head2.y = this.head.y = (float)((double)this.body.y - 12.0 + (12.0 - (double)d3));
        this.head2.xRot = this.head.xRot = 0.62f + MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        this.head2.zRot = this.head.zRot = MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.05f;
        this.tooth3.xRot = this.tooth4.xRot = (newangle = this.head.xRot);
        this.tooth2.xRot = this.tooth4.xRot;
        this.tooth1.xRot = this.tooth4.xRot;
        d1 = (float)(Math.sin(newangle) * 12.0);
        d2 = (float)(Math.cos(newangle) * 12.0);
        this.tooth3.z = this.tooth4.z = this.head.z - d1;
        this.tooth2.z = this.tooth4.z;
        this.tooth1.z = this.tooth4.z;
        this.tooth3.zRot = this.tooth4.zRot = (newangle = this.head.zRot);
        this.tooth2.zRot = this.tooth4.zRot;
        this.tooth1.zRot = this.tooth4.zRot;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.tooth3.x = this.tooth4.x = this.head.x + d4;
        this.tooth2.x = this.tooth4.x;
        this.tooth1.x = this.tooth4.x;
        this.tooth3.y = this.tooth4.y = (float)((double)this.head.y - 12.0 + (12.0 - (double)d3));
        this.tooth2.y = this.tooth4.y;
        this.tooth1.y = this.tooth4.y;
        this.tooth1.z += 1.0f;
        this.tooth2.z -= 1.0f;
        this.tooth1.xRot = this.tooth1.xRot - 0.4f - MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        this.tooth2.xRot = this.tooth2.xRot + 0.4f + MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        this.tooth3.x += 1.0f;
        this.tooth4.x -= 1.0f;
        this.tooth3.zRot = this.tooth3.zRot + 0.4f + MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        this.tooth4.zRot = this.tooth4.zRot - 0.4f - MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, WormMedium par7Entity) {
        
    }
}

