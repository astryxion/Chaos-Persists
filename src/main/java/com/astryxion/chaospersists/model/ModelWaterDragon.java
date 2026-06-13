/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelWaterDragon
 *  com.astryxion.chaospersists.WaterDragon
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.WaterDragon;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWaterDragon extends EntityModel<WaterDragon> {
    private float wingspeed = 1.0f;
    ModelRenderer Head;
    ModelRenderer neck1;
    ModelRenderer body1;
    ModelRenderer Leg8;
    ModelRenderer Leg2;
    ModelRenderer Leg7;
    ModelRenderer Leg1;
    ModelRenderer neck2;
    ModelRenderer neck3;
    ModelRenderer neck4;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer tail1;
    ModelRenderer tailmiddle;
    ModelRenderer tailtop;
    ModelRenderer tailbottom;
    ModelRenderer nose;
    ModelRenderer headfin;
    ModelRenderer rightear;
    ModelRenderer leftear;
    ModelRenderer neackfin;
    ModelRenderer Bodyfin;
    ModelRenderer jaw;

    public ModelWaterDragon(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.Head = new ModelRenderer(this, 79, 64);
        this.Head.addBox(-4.0f, -4.0f, -8.0f, 7, 8, 8);
        this.Head.setPos(0.0f, 0.0f, -3.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 29, 70);
        this.neck1.addBox(-2.0f, 0.0f, -3.0f, 5, 5, 5);
        this.neck1.setPos(-1.0f, 4.0f, -5.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, -0.1858931f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer(this, 0, 33);
        this.body1.addBox(-5.0f, -4.0f, -6.0f, 9, 9, 9);
        this.body1.setPos(0.0f, 19.0f, 2.0f);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.Leg8 = new ModelRenderer(this, 23, 25);
        this.Leg8.addBox(0.0f, -1.0f, -1.0f, 9, 2, 3);
        this.Leg8.setPos(3.0f, 22.0f, -2.0f);
        this.Leg8.mirror = true;
        this.setRotation(this.Leg8, 0.0f, 0.5759587f, 0.1919862f);
        this.Leg2 = new ModelRenderer(this, 80, 18);
        this.Leg2.addBox(0.0f, -1.0f, -1.0f, 9, 2, 3);
        this.Leg2.setPos(2.0f, 22.0f, 13.0f);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, -0.5759587f, 0.1919862f);
        this.Leg7 = new ModelRenderer(this, 23, 18);
        this.Leg7.addBox(-9.0f, -1.0f, -1.0f, 9, 2, 3);
        this.Leg7.setPos(-4.0f, 22.0f, -1.0f);
        this.Leg7.mirror = true;
        this.setRotation(this.Leg7, 0.0f, -0.5759587f, -0.1919862f);
        this.Leg1 = new ModelRenderer(this, 80, 25);
        this.Leg1.addBox(-9.0f, -1.0f, -2.0f, 9, 2, 3);
        this.Leg1.setPos(-3.0f, 22.0f, 14.0f);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.5759587f, -0.1919862f);
        this.neck2 = new ModelRenderer(this, 0, 11);
        this.neck2.addBox(-2.0f, 0.0f, -2.0f, 5, 5, 5);
        this.neck2.setPos(-1.0f, 9.0f, -7.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, 0.1115358f, 0.0f, 0.0f);
        this.neck3 = new ModelRenderer(this, 0, 22);
        this.neck3.addBox(-2.0f, 0.0f, -2.0f, 5, 5, 5);
        this.neck3.setPos(-1.0f, 14.0f, -6.0f);
        this.neck3.mirror = true;
        this.setRotation(this.neck3, 0.4461433f, 0.0f, 0.0f);
        this.neck4 = new ModelRenderer(this, 26, 12);
        this.neck4.addBox(-3.0f, 0.0f, -2.0f, 5, 3, 3);
        this.neck4.setPos(0.0f, 18.0f, -4.0f);
        this.neck4.mirror = true;
        this.setRotation(this.neck4, 1.226894f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 0, 52);
        this.body2.addBox(-5.0f, -5.0f, 0.0f, 7, 7, 9);
        this.body2.setPos(1.0f, 21.0f, 5.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer(this, 0, 69);
        this.body3.addBox(-3.0f, -3.0f, 0.0f, 5, 5, 7);
        this.body3.setPos(0.0f, 20.0f, 14.0f);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer(this, 0, 89);
        this.body4.addBox(-1.0f, -1.0f, 0.0f, 3, 3, 5);
        this.body4.setPos(-1.0f, 19.0f, 21.0f);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 0, 82);
        this.tail1.addBox(0.0f, 0.0f, 0.0f, 1, 2, 3);
        this.tail1.setPos(-1.0f, 19.0f, 25.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tailmiddle = new ModelRenderer(this, 55, 37);
        this.tailmiddle.addBox(-1.0f, -6.0f, 0.0f, 2, 11, 9);
        this.tailmiddle.setPos(0.0f, 19.0f, 28.0f);
        this.tailmiddle.mirror = true;
        this.setRotation(this.tailmiddle, 0.0f, 0.0f, 0.0f);
        this.tailtop = new ModelRenderer(this, 82, 36);
        this.tailtop.addBox(-1.0f, -11.0f, 0.0f, 2, 11, 9);
        this.tailtop.setPos(0.0f, 14.0f, 28.0f);
        this.tailtop.mirror = true;
        this.setRotation(this.tailtop, -0.6320364f, 0.0f, 0.0f);
        this.tailbottom = new ModelRenderer(this, 56, 60);
        this.tailbottom.addBox(0.0f, 0.0f, 0.0f, 2, 11, 9);
        this.tailbottom.setPos(-1.0f, 23.0f, 28.0f);
        this.tailbottom.mirror = true;
        this.setRotation(this.tailbottom, 0.6320361f, 0.0f, -0.0174533f);
        this.nose = new ModelRenderer(this, 54, 19);
        this.nose.addBox(-3.0f, -2.0f, -5.0f, 5, 5, 5);
        this.nose.setPos(0.0f, -2.0f, -11.0f);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0f, 0.0f, 0.0f);
        this.headfin = new ModelRenderer(this, 0, 99);
        this.headfin.addBox(0.0f, -5.0f, 0.0f, 0, 10, 9);
        this.headfin.setPos(0.0f, -4.0f, -6.0f);
        this.headfin.mirror = true;
        this.setRotation(this.headfin, 0.1396263f, 0.0f, 0.0f);
        this.rightear = new ModelRenderer(this, 38, 32);
        this.rightear.addBox(0.0f, 0.0f, 0.0f, 0, 5, 5);
        this.rightear.setPos(-4.0f, -2.0f, -5.0f);
        this.rightear.mirror = true;
        this.setRotation(this.rightear, 0.0698132f, -0.418879f, 0.0f);
        this.leftear = new ModelRenderer(this, 38, 32);
        this.leftear.addBox(0.0f, 0.0f, 0.0f, 0, 5, 5);
        this.leftear.setPos(3.0f, -2.0f, -5.0f);
        this.leftear.mirror = true;
        this.setRotation(this.leftear, 0.0698132f, 0.418879f, 0.0f);
        this.neackfin = new ModelRenderer(this, 42, 47);
        this.neackfin.addBox(0.0f, -1.0f, 0.0f, 0, 5, 5);
        this.neackfin.setPos(0.0f, 3.0f, -3.0f);
        this.neackfin.mirror = true;
        this.setRotation(this.neackfin, -0.185895f, 0.0f, 0.0f);
        this.Bodyfin = new ModelRenderer(this, 21, 91);
        this.Bodyfin.addBox(0.0f, -6.0f, -3.0f, 0, 10, 9);
        this.Bodyfin.setPos(0.0f, 15.0f, 2.0f);
        this.Bodyfin.mirror = true;
        this.setRotation(this.Bodyfin, -0.0698132f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 76, 8);
        this.jaw.addBox(-2.0f, 0.0f, -5.0f, 5, 1, 5);
        this.jaw.setPos(-1.0f, 3.0f, -10.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(WaterDragon e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        float newangle = 0.0f;
        float pi4 = 0.7853982f;
        float root13 = (float)Math.sqrt(13.0);
        float root20 = (float)Math.sqrt(20.0);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.2f * f1 : 0.0f;
        this.body3.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.4f * f1;
        this.body4.z = this.body3.z + (float)Math.cos(this.body3.yRot) * 7.0f;
        this.body4.x = this.body3.x - 1.0f + (float)Math.sin(this.body3.yRot) * 7.0f;
        this.body4.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - pi4)) * 3.1415927f * 0.4f * f1;
        this.tail1.z = this.body4.z + (float)Math.cos(this.body4.yRot) * 5.0f;
        this.tail1.x = this.body4.x + (float)Math.sin(this.body4.yRot) * 5.0f;
        this.tail1.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.4f * f1;
        this.tailmiddle.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 3.0f;
        this.tailmiddle.x = this.tail1.x + (float)Math.sin(this.tail1.yRot) * 3.0f;
        this.tailtop.yRot = this.tailmiddle.yRot = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.4f * f1;
        this.tailtop.z = this.tailmiddle.z;
        this.tailtop.x = this.tailmiddle.x;
        this.tailbottom.yRot = this.tailmiddle.yRot;
        this.tailbottom.z = this.tailmiddle.z;
        this.tailbottom.x = this.tailmiddle.x;
        this.Leg8.yRot = 0.58f + newangle;
        this.Leg2.yRot = -0.58f + newangle;
        this.Leg7.yRot = -0.58f - newangle;
        this.Leg1.yRot = 0.58f - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.8f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.leftear.yRot = 0.62f + newangle;
        this.rightear.yRot = -0.62f - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.02f;
        if (e.isOrderedToSit()) {
            newangle = 0.0f;
        }
        this.Bodyfin.zRot = newangle;
        newangle = MathHelper.cos((float)(f2 * 0.6f * this.wingspeed)) * 3.1415927f * 0.1f;
        if (e.isOrderedToSit()) {
            newangle = 0.0f;
        }
        this.neackfin.yRot = newangle;
        newangle = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f;
        if (e.isOrderedToSit()) {
            newangle = 0.0f;
        }
        this.headfin.yRot = newangle;
        this.jaw.xRot = e.getAttacking() == 1 ? (newangle = MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.25f) : (e.getAttacking() == 2 ? 0.45f : -0.25f);
        this.Head.yRot = newangle = (float)Math.toRadians(f3) * 0.75f;
        this.nose.yRot = newangle;
        this.nose.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 8.0f;
        this.nose.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 8.0f;
        this.jaw.yRot = newangle;
        this.jaw.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 7.0f;
        this.jaw.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 7.0f - 1.0f;
        this.headfin.yRot = newangle;
        this.headfin.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 3.0f;
        this.headfin.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 3.0f;
        this.leftear.yRot += newangle;
        this.leftear.z = this.Head.z - (float)Math.cos(this.Head.yRot - pi4) * root13;
        this.leftear.x = this.Head.x - (float)Math.sin(this.Head.yRot - pi4) * root13;
        this.rightear.yRot += newangle;
        this.rightear.z = this.Head.z - (float)Math.cos(this.Head.yRot + pi4) * root20;
        this.rightear.x = this.Head.x - (float)Math.sin(this.Head.yRot + pi4) * root20;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmiddle.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailtop.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailbottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neackfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bodyfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

