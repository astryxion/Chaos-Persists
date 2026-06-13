/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.HerculesBeetle
 *  com.astryxion.chaospersists.ModelHerculesBeetle
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.HerculesBeetle;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHerculesBeetle extends EntityModel<HerculesBeetle> {
    private float wingspeed = 1.0f;
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;
    ModelRenderer head8;
    ModelRenderer jaw1;
    ModelRenderer jaw2;
    ModelRenderer jaw3;
    ModelRenderer jaw4;
    ModelRenderer head7;
    ModelRenderer lfleg1;
    ModelRenderer lfleg2;
    ModelRenderer lfleg3;
    ModelRenderer lmleg1;
    ModelRenderer lmleg2;
    ModelRenderer lmleg3;
    ModelRenderer lrleg1;
    ModelRenderer lrleg2;
    ModelRenderer lrleg3;
    ModelRenderer jaw5;
    ModelRenderer jaw6;
    ModelRenderer jaw7;
    ModelRenderer jaw8;
    ModelRenderer rfleg1;
    ModelRenderer rfleg2;
    ModelRenderer rfleg3;
    ModelRenderer rmleg1;
    ModelRenderer rmleg2;
    ModelRenderer rmleg3;
    ModelRenderer rrleg1;
    ModelRenderer rrleg2;
    ModelRenderer rrleg3;
    ModelRenderer jaw9;

    public ModelHerculesBeetle(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 256;
        this.body1 = new ModelRenderer(this, 0, 30);
        this.body1.addBox(-8.0f, 0.0f, 0.0f, 16, 16, 23);
        this.body1.setPos(0.0f, 0.0f, 0.0f);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 80, 41);
        this.body2.addBox(-6.0f, 0.0f, 0.0f, 12, 12, 4);
        this.body2.setPos(0.0f, 3.0f, 23.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 0, 71);
        this.head1.addBox(-9.0f, 0.0f, 0.0f, 18, 16, 12);
        this.head1.setPos(0.0f, -1.0f, -10.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, -0.122173f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 0, 100);
        this.head2.addBox(-7.0f, 0.0f, 0.0f, 14, 10, 6);
        this.head2.setPos(0.0f, -2.0f, -16.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, -0.122173f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer(this, 0, 117);
        this.head3.addBox(-5.0f, 0.0f, 0.0f, 10, 6, 9);
        this.head3.setPos(0.0f, -3.0f, -25.0f);
        this.head3.mirror = true;
        this.setRotation(this.head3, -0.122173f, 0.0f, 0.0f);
        this.head4 = new ModelRenderer(this, 0, 133);
        this.head4.addBox(-4.0f, 0.0f, 0.0f, 8, 4, 12);
        this.head4.setPos(0.0f, -4.0f, -37.0f);
        this.head4.mirror = true;
        this.setRotation(this.head4, -0.122173f, 0.0f, 0.0f);
        this.head5 = new ModelRenderer(this, 0, 150);
        this.head5.addBox(-3.0f, 0.0f, 0.0f, 6, 3, 21);
        this.head5.setPos(0.0f, -4.0f, -58.0f);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0f, 0.0f, 0.0f);
        this.head6 = new ModelRenderer(this, 0, 175);
        this.head6.addBox(-2.0f, 0.0f, 0.0f, 4, 2, 14);
        this.head6.setPos(0.0f, -2.0f, -72.0f);
        this.head6.mirror = true;
        this.setRotation(this.head6, 0.122173f, 0.0f, 0.0f);
        this.head8 = new ModelRenderer(this, 6, 193);
        this.head8.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.head8.setPos(0.0f, -2.0f, -46.0f);
        this.head8.mirror = true;
        this.setRotation(this.head8, -0.2094395f, 0.0f, 0.0f);
        this.jaw1 = new ModelRenderer(this, 114, 0);
        this.jaw1.addBox(-3.0f, -3.0f, -4.0f, 6, 7, 5);
        this.jaw1.setPos(0.0f, 12.0f, -12.0f);
        this.jaw1.mirror = true;
        this.setRotation(this.jaw1, 0.122173f, 0.0f, 0.0f);
        this.jaw2 = new ModelRenderer(this, 115, 14);
        this.jaw2.addBox(-2.5f, -3.0f, -27.0f, 5, 5, 23);
        this.jaw2.setPos(0.0f, 12.0f, -12.0f);
        this.jaw2.mirror = true;
        this.setRotation(this.jaw2, 0.122173f, 0.0f, 0.0f);
        this.jaw3 = new ModelRenderer(this, 115, 43);
        this.jaw3.addBox(-1.5f, 0.0f, -44.0f, 3, 5, 18);
        this.jaw3.setPos(0.0f, 12.0f, -12.0f);
        this.jaw3.mirror = true;
        this.setRotation(this.jaw3, 0.0f, 0.0f, 0.0f);
        this.jaw4 = new ModelRenderer(this, 115, 70);
        this.jaw4.addBox(-0.5f, -2.0f, -45.0f, 1, 5, 1);
        this.jaw4.setPos(0.0f, 12.0f, -12.0f);
        this.jaw4.mirror = true;
        this.setRotation(this.jaw4, 0.0f, 0.0f, 0.0f);
        this.head7 = new ModelRenderer(this, 0, 193);
        this.head7.addBox(-0.5f, 0.0f, 0.0f, 1, 4, 1);
        this.head7.setPos(0.0f, -2.0f, -73.0f);
        this.head7.mirror = true;
        this.setRotation(this.head7, 0.122173f, 0.0f, 0.0f);
        this.lfleg1 = new ModelRenderer(this, 60, 0);
        this.lfleg1.addBox(0.0f, 0.0f, -0.5f, 10, 3, 3);
        this.lfleg1.setPos(6.0f, 15.0f, -5.0f);
        this.lfleg1.mirror = true;
        this.setRotation(this.lfleg1, 0.0f, 0.3490659f, 0.0872665f);
        this.lfleg2 = new ModelRenderer(this, 60, 8);
        this.lfleg2.addBox(10.0f, -1.0f, 0.0f, 11, 2, 2);
        this.lfleg2.setPos(6.0f, 15.0f, -5.0f);
        this.lfleg2.mirror = true;
        this.setRotation(this.lfleg2, 0.0f, 0.3490659f, 0.2617994f);
        this.lfleg3 = new ModelRenderer(this, 60, 14);
        this.lfleg3.addBox(21.0f, -2.0f, 0.5f, 10, 1, 1);
        this.lfleg3.setPos(6.0f, 15.0f, -5.0f);
        this.lfleg3.mirror = true;
        this.setRotation(this.lfleg3, 0.0f, 0.3490659f, 0.3490659f);
        this.lmleg1 = new ModelRenderer(this, 60, 0);
        this.lmleg1.addBox(0.0f, 0.0f, -0.5f, 10, 3, 3);
        this.lmleg1.setPos(6.0f, 15.0f, 0.0f);
        this.lmleg1.mirror = true;
        this.setRotation(this.lmleg1, 0.0f, 0.0f, 0.0872665f);
        this.lmleg2 = new ModelRenderer(this, 60, 8);
        this.lmleg2.addBox(10.0f, -1.0f, 0.0f, 11, 2, 2);
        this.lmleg2.setPos(6.0f, 15.0f, 0.0f);
        this.lmleg2.mirror = true;
        this.setRotation(this.lmleg2, 0.0f, 0.0f, 0.2617994f);
        this.lmleg3 = new ModelRenderer(this, 60, 14);
        this.lmleg3.addBox(21.0f, -2.0f, 0.5f, 10, 1, 1);
        this.lmleg3.setPos(6.0f, 15.0f, 0.0f);
        this.lmleg3.mirror = true;
        this.setRotation(this.lmleg3, 0.0f, 0.0f, 0.3490659f);
        this.lrleg1 = new ModelRenderer(this, 60, 0);
        this.lrleg1.addBox(0.0f, 0.0f, -0.5f, 10, 3, 3);
        this.lrleg1.setPos(6.0f, 15.0f, 5.0f);
        this.lrleg1.mirror = true;
        this.setRotation(this.lrleg1, 0.0f, -0.3490659f, 0.0872665f);
        this.lrleg2 = new ModelRenderer(this, 60, 8);
        this.lrleg2.addBox(10.0f, -1.0f, 0.0f, 11, 2, 2);
        this.lrleg2.setPos(6.0f, 15.0f, 5.0f);
        this.lrleg2.mirror = true;
        this.setRotation(this.lrleg2, 0.0f, -0.3490659f, 0.2617994f);
        this.lrleg3 = new ModelRenderer(this, 60, 14);
        this.lrleg3.addBox(21.0f, -2.0f, 0.5f, 10, 1, 1);
        this.lrleg3.setPos(6.0f, 15.0f, 5.0f);
        this.lrleg3.mirror = true;
        this.setRotation(this.lrleg3, 0.0f, -0.3490659f, 0.3490659f);
        this.jaw5 = new ModelRenderer(this, 115, 78);
        this.jaw5.addBox(2.0f, -2.0f, -9.0f, 2, 3, 3);
        this.jaw5.setPos(0.0f, 12.0f, -12.0f);
        this.jaw5.mirror = true;
        this.setRotation(this.jaw5, 0.122173f, 0.0f, 0.0f);
        this.jaw6 = new ModelRenderer(this, 127, 78);
        this.jaw6.addBox(-4.0f, -2.0f, -9.0f, 2, 3, 3);
        this.jaw6.setPos(0.0f, 12.0f, -12.0f);
        this.jaw6.mirror = true;
        this.setRotation(this.jaw6, 0.122173f, 0.0f, 0.0f);
        this.jaw7 = new ModelRenderer(this, 115, 86);
        this.jaw7.addBox(5.0f, 1.0f, -6.0f, 9, 1, 1);
        this.jaw7.setPos(0.0f, 12.0f, -12.0f);
        this.jaw7.mirror = true;
        this.setRotation(this.jaw7, 0.0f, 0.5585054f, 0.2268928f);
        this.jaw8 = new ModelRenderer(this, 115, 89);
        this.jaw8.addBox(-14.0f, 1.0f, -6.0f, 9, 1, 1);
        this.jaw8.setPos(0.0f, 12.0f, -12.0f);
        this.jaw8.mirror = true;
        this.setRotation(this.jaw8, 0.0f, -0.5585054f, -0.2268928f);
        this.rfleg1 = new ModelRenderer(this, 30, 0);
        this.rfleg1.addBox(-10.0f, 0.0f, -0.5f, 10, 3, 3);
        this.rfleg1.setPos(-6.0f, 15.0f, -5.0f);
        this.rfleg1.mirror = true;
        this.setRotation(this.rfleg1, 0.0f, -0.3490659f, -0.0872665f);
        this.rfleg2 = new ModelRenderer(this, 30, 8);
        this.rfleg2.addBox(-21.0f, -1.0f, 0.0f, 11, 2, 2);
        this.rfleg2.setPos(-6.0f, 15.0f, -5.0f);
        this.rfleg2.mirror = true;
        this.setRotation(this.rfleg2, 0.0f, -0.3490659f, -0.2617994f);
        this.rfleg3 = new ModelRenderer(this, 30, 14);
        this.rfleg3.addBox(-31.0f, -2.0f, 0.5f, 10, 1, 1);
        this.rfleg3.setPos(-6.0f, 15.0f, -5.0f);
        this.rfleg3.mirror = true;
        this.setRotation(this.rfleg3, 0.0f, -0.3490659f, -0.3490659f);
        this.rmleg1 = new ModelRenderer(this, 30, 0);
        this.rmleg1.addBox(-10.0f, 0.0f, -0.5f, 10, 3, 3);
        this.rmleg1.setPos(-6.0f, 15.0f, 0.0f);
        this.rmleg1.mirror = true;
        this.setRotation(this.rmleg1, 0.0f, 0.0f, -0.0872665f);
        this.rmleg2 = new ModelRenderer(this, 30, 8);
        this.rmleg2.addBox(-21.0f, -1.0f, 0.0f, 11, 2, 2);
        this.rmleg2.setPos(-6.0f, 15.0f, 0.0f);
        this.rmleg2.mirror = true;
        this.setRotation(this.rmleg2, 0.0f, 0.0f, -0.2617994f);
        this.rmleg3 = new ModelRenderer(this, 30, 14);
        this.rmleg3.addBox(-31.0f, -2.0f, 0.5f, 10, 1, 1);
        this.rmleg3.setPos(-6.0f, 15.0f, 0.0f);
        this.rmleg3.mirror = true;
        this.setRotation(this.rmleg3, 0.0f, 0.0f, -0.3490659f);
        this.rrleg1 = new ModelRenderer(this, 30, 0);
        this.rrleg1.addBox(-10.0f, 0.0f, -0.5f, 10, 3, 3);
        this.rrleg1.setPos(-6.0f, 15.0f, 5.0f);
        this.rrleg1.mirror = true;
        this.setRotation(this.rrleg1, 0.0f, 0.3490659f, -0.0872665f);
        this.rrleg2 = new ModelRenderer(this, 30, 8);
        this.rrleg2.addBox(-21.0f, -1.0f, 0.0f, 11, 2, 2);
        this.rrleg2.setPos(-6.0f, 15.0f, 5.0f);
        this.rrleg2.mirror = true;
        this.setRotation(this.rrleg2, 0.0f, 0.3490659f, -0.2617994f);
        this.rrleg3 = new ModelRenderer(this, 30, 14);
        this.rrleg3.addBox(-31.0f, -2.0f, 0.5f, 10, 1, 1);
        this.rrleg3.setPos(-6.0f, 15.0f, 5.0f);
        this.rrleg3.mirror = true;
        this.setRotation(this.rrleg3, 0.0f, 0.3490659f, -0.3490659f);
        this.jaw9 = new ModelRenderer(this, 121, 70);
        this.jaw9.addBox(-0.5f, -12.0f, -25.0f, 1, 5, 1);
        this.jaw9.setPos(0.0f, 12.0f, -12.0f);
        this.jaw9.mirror = true;
        this.setRotation(this.jaw9, 0.3141593f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(HerculesBeetle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        HerculesBeetle b = (HerculesBeetle)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = MathHelper.cos((float)(f2 * this.wingspeed * 0.45f)) * 3.1415927f * 0.12f * f1;
        this.lfleg3.yRot = this.lfleg2.yRot = (this.lfleg1.yRot = 0.349f + newangle);
        this.lmleg3.yRot = this.lmleg2.yRot = (this.lmleg1.yRot = - newangle);
        this.lrleg3.yRot = this.lrleg2.yRot = (this.lrleg1.yRot = -0.349f + newangle);
        this.rfleg3.yRot = this.rfleg2.yRot = (this.rfleg1.yRot = -0.349f + newangle);
        this.rmleg3.yRot = this.rmleg2.yRot = (this.rmleg1.yRot = - newangle);
        this.rrleg3.yRot = this.rrleg2.yRot = (this.rrleg1.yRot = 0.349f + newangle);
        newangle = b.getAttacking() == 0 ? MathHelper.cos((float)(f2 * 0.051f * this.wingspeed)) * 3.1415927f * 0.01f : MathHelper.cos((float)(f2 * 0.51f * this.wingspeed)) * 3.1415927f * 0.07f;
        this.jaw1.xRot = 0.122f + newangle;
        this.jaw2.xRot = 0.122f + newangle;
        this.jaw3.xRot = 0.0f + newangle;
        this.jaw4.xRot = 0.0f + newangle;
        this.jaw5.xRot = 0.122f + newangle;
        this.jaw6.xRot = 0.122f + newangle;
        this.jaw7.xRot = 0.0f + newangle;
        this.jaw8.xRot = 0.0f + newangle;
        this.jaw9.xRot = 0.314f + newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lmleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lmleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lmleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

