/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.ModelVelocityRaptor
 *  com.astryxion.chaospersists.VelocityRaptor
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.EntityCannonFodder;
import com.astryxion.chaospersists.entity.VelocityRaptor;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelVelocityRaptor extends EntityModel<VelocityRaptor> {
    private float wingspeed = 1.0f;
    ModelRenderer hf3;
    ModelRenderer hf4;
    ModelRenderer hf2;
    ModelRenderer hf1;
    ModelRenderer lff2;
    ModelRenderer lff1;
    ModelRenderer lff3;
    ModelRenderer rff2;
    ModelRenderer rff3;
    ModelRenderer rff1;
    ModelRenderer tf4;
    ModelRenderer tf1;
    ModelRenderer Shape1;
    ModelRenderer neck;
    ModelRenderer head1;
    ModelRenderer lf1;
    ModelRenderer lf2;
    ModelRenderer head2;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer bl1;
    ModelRenderer br1;
    ModelRenderer bl2;
    ModelRenderer br2;
    ModelRenderer bl3;
    ModelRenderer bl4;
    ModelRenderer br3;
    ModelRenderer rf1;
    ModelRenderer rf2;
    ModelRenderer tf2;
    ModelRenderer tf3;
    ModelRenderer br4;
    ModelRenderer Hat1;
    ModelRenderer Hat2;

    public ModelVelocityRaptor(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.hf3 = new ModelRenderer(this, 0, 0);
        this.hf3.addBox(0.0f, 0.0f, 0.0f, 0, 1, 3);
        this.hf3.setPos(0.0f, 7.0f, -2.0f);
        this.hf3.mirror = true;
        this.setRotation(this.hf3, 0.4537856f, 0.0f, 0.0f);
        this.hf4 = new ModelRenderer(this, 0, 0);
        this.hf4.addBox(0.0f, -0.2f, 0.0f, 0, 1, 3);
        this.hf4.setPos(0.0f, 8.0f, -1.5f);
        this.hf4.mirror = true;
        this.setRotation(this.hf4, 0.2443461f, 0.0f, 0.0f);
        this.hf2 = new ModelRenderer(this, 0, 0);
        this.hf2.addBox(0.0f, 0.0f, 0.0f, 0, 1, 3);
        this.hf2.setPos(0.0f, 7.0f, -3.5f);
        this.hf2.mirror = true;
        this.setRotation(this.hf2, 0.6632251f, 0.0f, 0.0f);
        this.hf1 = new ModelRenderer(this, 0, 1);
        this.hf1.addBox(0.0f, 0.0f, 0.0f, 0, 1, 2);
        this.hf1.setPos(0.0f, 7.0f, -4.5f);
        this.hf1.mirror = true;
        this.setRotation(this.hf1, 0.9424778f, 0.0f, 0.0f);
        this.lff2 = new ModelRenderer(this, 0, 6);
        this.lff2.addBox(0.5f, 2.5f, 3.0f, 0, 1, 3);
        this.lff2.setPos(2.0f, 14.0f, 1.0f);
        this.lff2.mirror = true;
        this.setRotation(this.lff2, -0.4537856f, 0.0f, 0.0f);
        this.lff1 = new ModelRenderer(this, 0, 6);
        this.lff1.addBox(0.5f, 2.0f, 2.0f, 0, 1, 3);
        this.lff1.setPos(2.0f, 14.0f, 1.0f);
        this.lff1.mirror = true;
        this.setRotation(this.lff1, -0.2792527f, 0.0f, 0.0f);
        this.lff3 = new ModelRenderer(this, 0, 6);
        this.lff3.addBox(0.5f, 1.0f, 4.0f, 0, 1, 3);
        this.lff3.setPos(2.0f, 14.0f, 1.0f);
        this.lff3.mirror = true;
        this.setRotation(this.lff3, -1.047198f, 0.0f, 0.0f);
        this.rff2 = new ModelRenderer(this, 0, 6);
        this.rff2.addBox(-0.5f, 2.5f, 3.0f, 0, 1, 3);
        this.rff2.setPos(-2.0f, 14.0f, 1.0f);
        this.rff2.mirror = true;
        this.setRotation(this.rff2, -0.4537856f, 0.0f, 0.0f);
        this.rff3 = new ModelRenderer(this, 0, 6);
        this.rff3.addBox(-0.5f, 1.0f, 4.0f, 0, 1, 3);
        this.rff3.setPos(-2.0f, 14.0f, 1.0f);
        this.rff3.mirror = true;
        this.setRotation(this.rff3, -1.047198f, 0.0f, 0.0f);
        this.rff1 = new ModelRenderer(this, 0, 6);
        this.rff1.addBox(-0.5f, 2.0f, 2.0f, 0, 1, 3);
        this.rff1.setPos(-2.0f, 14.0f, 1.0f);
        this.rff1.mirror = true;
        this.setRotation(this.rff1, -0.2792527f, 0.0f, 0.0f);
        this.tf4 = new ModelRenderer(this, 0, 3);
        this.tf4.addBox(0.0f, 0.0f, 0.0f, 0, 1, 3);
        this.tf4.setPos(0.0f, 11.0f, 25.0f);
        this.tf4.mirror = true;
        this.setRotation(this.tf4, -0.5410521f, 0.0f, 0.0f);
        this.tf1 = new ModelRenderer(this, 0, 3);
        this.tf1.addBox(0.0f, 0.0f, 0.0f, 0, 1, 3);
        this.tf1.setPos(0.0f, 11.0f, 19.0f);
        this.tf1.mirror = true;
        this.setRotation(this.tf1, -0.5410521f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-2.0f, 0.0f, 0.0f, 4, 7, 11);
        this.Shape1.setPos(0.0f, 10.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 0, 19);
        this.neck.addBox(-1.0f, -7.0f, -2.0f, 2, 8, 3);
        this.neck.setPos(0.0f, 12.0f, 2.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 1.082104f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 0, 49);
        this.head1.addBox(-2.0f, 0.0f, -7.0f, 3, 4, 7);
        this.head1.setPos(0.5f, 7.0f, -1.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.lf1 = new ModelRenderer(this, 0, 31);
        this.lf1.addBox(0.0f, 0.0f, 0.0f, 1, 3, 2);
        this.lf1.setPos(2.0f, 14.0f, 1.0f);
        this.lf1.mirror = true;
        this.setRotation(this.lf1, 0.2792527f, 0.0f, 0.0f);
        this.lf2 = new ModelRenderer(this, 16, 19);
        this.lf2.addBox(0.0f, 1.0f, 2.0f, 1, 4, 1);
        this.lf2.setPos(2.0f, 14.0f, 1.0f);
        this.lf2.mirror = true;
        this.setRotation(this.lf2, -0.4363323f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 20, 0);
        this.head2.addBox(-1.0f, 0.0f, -10.0f, 2, 4, 4);
        this.head2.setPos(0.0f, 7.0f, -1.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 0, 38);
        this.tail1.addBox(-1.0f, 0.0f, 0.0f, 2, 5, 4);
        this.tail1.setPos(0.0f, 10.0f, 11.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 26, 11);
        this.tail2.addBox(0.0f, 0.0f, 0.0f, 1, 2, 10);
        this.tail2.setPos(-0.5f, 10.0f, 15.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.bl1 = new ModelRenderer(this, 22, 24);
        this.bl1.addBox(-1.0f, 0.0f, 0.0f, 2, 6, 4);
        this.bl1.setPos(2.0f, 13.0f, 6.0f);
        this.bl1.mirror = true;
        this.setRotation(this.bl1, 0.0f, 0.0f, 0.0f);
        this.br1 = new ModelRenderer(this, 36, 0);
        this.br1.addBox(-1.0f, 0.0f, 0.0f, 2, 6, 4);
        this.br1.setPos(-2.0f, 13.0f, 6.0f);
        this.br1.mirror = true;
        this.setRotation(this.br1, 0.0f, 0.0f, 0.0f);
        this.bl2 = new ModelRenderer(this, 12, 26);
        this.bl2.addBox(-1.0f, 5.0f, -3.0f, 2, 5, 2);
        this.bl2.setPos(2.0f, 13.0f, 6.0f);
        this.bl2.mirror = true;
        this.setRotation(this.bl2, 0.4886922f, 0.0f, 0.0f);
        this.br2 = new ModelRenderer(this, 13, 36);
        this.br2.addBox(-1.0f, 5.0f, -3.0f, 2, 5, 2);
        this.br2.setPos(-2.0f, 13.0f, 6.0f);
        this.br2.mirror = true;
        this.setRotation(this.br2, 0.4886922f, 0.0f, 0.0f);
        this.bl3 = new ModelRenderer(this, 28, 39);
        this.bl3.addBox(-1.0f, 9.0f, -1.0f, 2, 2, 4);
        this.bl3.setPos(2.0f, 13.0f, 6.0f);
        this.bl3.mirror = true;
        this.setRotation(this.bl3, 0.0f, 0.0f, 0.0f);
        this.br3 = new ModelRenderer(this, 18, 45);
        this.br3.addBox(-1.0f, 9.0f, -1.0f, 2, 2, 4);
        this.br3.setPos(-2.0f, 13.0f, 6.0f);
        this.br3.mirror = true;
        this.setRotation(this.br3, 0.0f, 0.0f, 0.0f);
        this.rf1 = new ModelRenderer(this, 35, 31);
        this.rf1.addBox(-1.0f, 0.0f, 0.0f, 1, 3, 2);
        this.rf1.setPos(-2.0f, 14.0f, 1.0f);
        this.rf1.mirror = true;
        this.setRotation(this.rf1, 0.2792527f, 0.0f, 0.0f);
        this.rf2 = new ModelRenderer(this, 11, 19);
        this.rf2.addBox(-1.0f, 1.0f, 2.0f, 1, 4, 1);
        this.rf2.setPos(-2.0f, 14.0f, 1.0f);
        this.rf2.mirror = true;
        this.setRotation(this.rf2, -0.4363323f, 0.0f, 0.0f);
        this.tf2 = new ModelRenderer(this, 0, 3);
        this.tf2.addBox(0.0f, 0.0f, 0.0f, 0, 1, 3);
        this.tf2.setPos(0.0f, 11.0f, 21.0f);
        this.tf2.mirror = true;
        this.setRotation(this.tf2, -0.5410521f, 0.0f, 0.0f);
        this.tf3 = new ModelRenderer(this, 0, 3);
        this.tf3.addBox(0.0f, 0.0f, 0.0f, 0, 1, 3);
        this.tf3.setPos(0.0f, 11.0f, 23.0f);
        this.tf3.mirror = true;
        this.setRotation(this.tf3, -0.5410521f, 0.0f, 0.0f);
        this.bl4 = new ModelRenderer(this, 31, 10);
        this.bl4.addBox(-1.0f, 6.0f, -5.0f, 1, 3, 1);
        this.bl4.setPos(2.0f, 13.0f, 6.0f);
        this.bl4.mirror = true;
        this.setRotation(this.bl4, 0.6283185f, 0.0f, 0.0f);
        this.br4 = new ModelRenderer(this, 31, 15);
        this.br4.addBox(0.0f, 6.0f, -5.0f, 1, 3, 1);
        this.br4.setPos(-2.0f, 13.0f, 6.0f);
        this.br4.mirror = true;
        this.setRotation(this.br4, 0.6283185f, 0.0f, 0.0f);
        this.Hat1 = new ModelRenderer(this, 50, 0);
        this.Hat1.addBox(0.0f, 0.0f, 0.0f, 4, 1, 5);
        this.Hat1.setPos(-2.0f, 6.0f, -6.0f);
        this.Hat1.mirror = true;
        this.setRotation(this.Hat1, 0.0f, 0.0f, 0.0f);
        this.Hat2 = new ModelRenderer(this, 50, 0);
        this.Hat2.addBox(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.Hat2.setPos(-1.5f, 4.0f, -4.0f);
        this.Hat2.mirror = true;
        this.setRotation(this.Hat2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(VelocityRaptor entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        VelocityRaptor c = (VelocityRaptor)entity;
        float hf = 0.0f;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.bl1.xRot = newangle;
        this.bl2.xRot = newangle + 0.488f;
        this.bl3.xRot = newangle;
        this.bl4.xRot = newangle + 0.628f;
        this.br1.xRot = - newangle;
        this.br2.xRot = - newangle + 0.488f;
        this.br3.xRot = - newangle;
        this.br4.xRot = - newangle + 0.628f;
        hf = (float)c.getVHealth() / c.getMaxHealth();
        this.hf1.yRot = newangle = MathHelper.cos((float)(f2 * 1.25f * this.wingspeed * hf)) * 3.1415927f * 0.1f * hf;
        this.hf2.yRot = - newangle;
        this.hf3.yRot = newangle;
        this.hf4.yRot = - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.3f)) * 3.1415927f * 0.05f;
        this.lf1.xRot = newangle + 0.279f;
        this.lf2.xRot = newangle - 0.436f;
        this.lff1.xRot = newangle - 0.279f;
        this.lff2.xRot = newangle - 0.453f;
        this.lff3.xRot = newangle - 1.047f;
        this.rf1.xRot = - newangle + 0.279f;
        this.rf2.xRot = - newangle - 0.436f;
        this.rff1.xRot = - newangle - 0.279f;
        this.rff2.xRot = - newangle - 0.453f;
        this.rff3.xRot = - newangle - 1.047f;
        this.lff1.yRot = newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.lff2.yRot = - newangle;
        this.lff3.yRot = newangle;
        this.rff1.yRot = - newangle;
        this.rff2.yRot = newangle;
        this.rff3.yRot = - newangle;
        newangle = c.isOrderedToSit() ? 0.0f : MathHelper.cos((float)(f2 * 1.4f * this.wingspeed * hf)) * 3.1415927f * 0.25f * hf;
        this.tf1.zRot = newangle;
        this.tf2.zRot = - newangle;
        this.tf3.zRot = newangle;
        this.tf4.zRot = - newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (c instanceof EntityCannonFodder && c.get_is_activated() != 0) {
            
            if (c.get_is_activated() > 1) {
                
            }
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.hf3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

