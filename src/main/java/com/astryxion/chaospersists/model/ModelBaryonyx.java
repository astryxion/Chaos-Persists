/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Baryonyx
 *  com.astryxion.chaospersists.ModelBaryonyx
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Baryonyx;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBaryonyx extends EntityModel<Baryonyx> {
    private float wingspeed = 1.0f;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;
    ModelRenderer Shape32;
    ModelRenderer Shape33;
    ModelRenderer Shape34;
    ModelRenderer Shape35;
    ModelRenderer Shape36;
    ModelRenderer Shape37;
    ModelRenderer Shape38;
    ModelRenderer Shape39;
    ModelRenderer Shape40;
    ModelRenderer Shape41;
    ModelRenderer Shape42;
    ModelRenderer Shape43;
    ModelRenderer Shape44;
    ModelRenderer Shape45;
    ModelRenderer Shape46;
    ModelRenderer Shape47;
    ModelRenderer Shape48;
    ModelRenderer Shape49;
    ModelRenderer Shape50;
    ModelRenderer Shape51;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape52;

    public ModelBaryonyx(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.Shape27 = new ModelRenderer(this, 0, 0);
        this.Shape27.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape27.setPos(0.0f, -17.0f, -10.0f);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, 0.0f, 0.0f, 0.0f);
        this.Shape28 = new ModelRenderer(this, 0, 0);
        this.Shape28.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape28.setPos(0.0f, -17.0f, -7.0f);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, 0.0f, 0.0f, 0.0f);
        this.Shape29 = new ModelRenderer(this, 0, 0);
        this.Shape29.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape29.setPos(0.0f, -17.0f, -4.0f);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.0f, 0.0f, 0.0f);
        this.Shape30 = new ModelRenderer(this, 0, 0);
        this.Shape30.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape30.setPos(0.0f, -17.0f, -1.0f);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, 0.0f, 0.0f, 0.0f);
        this.Shape31 = new ModelRenderer(this, 0, 0);
        this.Shape31.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape31.setPos(0.0f, -17.0f, 2.0f);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, 0.0f, 0.0f, 0.0f);
        this.Shape32 = new ModelRenderer(this, 0, 0);
        this.Shape32.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape32.setPos(0.0f, -17.0f, 5.0f);
        this.Shape32.mirror = true;
        this.setRotation(this.Shape32, 0.0f, 0.0f, 0.0f);
        this.Shape33 = new ModelRenderer(this, 0, 0);
        this.Shape33.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape33.setPos(0.0f, -17.0f, 8.0f);
        this.Shape33.mirror = true;
        this.setRotation(this.Shape33, 0.0f, 0.0f, 0.0f);
        this.Shape34 = new ModelRenderer(this, 0, 0);
        this.Shape34.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape34.setPos(0.0f, -17.0f, 11.0f);
        this.Shape34.mirror = true;
        this.setRotation(this.Shape34, 0.0f, 0.0f, 0.0f);
        this.Shape35 = new ModelRenderer(this, 0, 0);
        this.Shape35.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape35.setPos(0.0f, -17.0f, 14.0f);
        this.Shape35.mirror = true;
        this.setRotation(this.Shape35, 0.0f, 0.0f, 0.0f);
        this.Shape36 = new ModelRenderer(this, 0, 0);
        this.Shape36.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape36.setPos(0.0f, -17.0f, 17.0f);
        this.Shape36.mirror = true;
        this.setRotation(this.Shape36, 0.0f, 0.0f, 0.0f);
        this.Shape37 = new ModelRenderer(this, 0, 0);
        this.Shape37.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape37.setPos(0.0f, -17.0f, 20.0f);
        this.Shape37.mirror = true;
        this.setRotation(this.Shape37, 0.0f, 0.0f, 0.0f);
        this.Shape38 = new ModelRenderer(this, 0, 0);
        this.Shape38.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape38.setPos(0.0f, -17.0f, 23.0f);
        this.Shape38.mirror = true;
        this.setRotation(this.Shape38, 0.0f, 0.0f, 0.0f);
        this.Shape39 = new ModelRenderer(this, 0, 0);
        this.Shape39.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape39.setPos(0.0f, -17.0f, 26.0f);
        this.Shape39.mirror = true;
        this.setRotation(this.Shape39, 0.0f, 0.0f, 0.0f);
        this.Shape40 = new ModelRenderer(this, 0, 0);
        this.Shape40.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape40.setPos(0.0f, -17.0f, 29.0f);
        this.Shape40.mirror = true;
        this.setRotation(this.Shape40, 0.0f, 0.0f, 0.0f);
        this.Shape41 = new ModelRenderer(this, 0, 0);
        this.Shape41.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape41.setPos(0.0f, -17.0f, 32.0f);
        this.Shape41.mirror = true;
        this.setRotation(this.Shape41, 0.0f, 0.0f, 0.0f);
        this.Shape42 = new ModelRenderer(this, 0, 0);
        this.Shape42.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape42.setPos(0.0f, -17.0f, 35.0f);
        this.Shape42.mirror = true;
        this.setRotation(this.Shape42, 0.0f, 0.0f, 0.0f);
        this.Shape43 = new ModelRenderer(this, 0, 0);
        this.Shape43.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape43.setPos(0.0f, -17.0f, 38.0f);
        this.Shape43.mirror = true;
        this.setRotation(this.Shape43, 0.0f, 0.0f, 0.0f);
        this.Shape44 = new ModelRenderer(this, 0, 0);
        this.Shape44.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape44.setPos(0.0f, -17.0f, 41.0f);
        this.Shape44.mirror = true;
        this.setRotation(this.Shape44, 0.0f, 0.0f, 0.0f);
        this.Shape45 = new ModelRenderer(this, 0, 0);
        this.Shape45.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape45.setPos(0.0f, -17.0f, 44.0f);
        this.Shape45.mirror = true;
        this.setRotation(this.Shape45, 0.0f, 0.0f, 0.0f);
        this.Shape46 = new ModelRenderer(this, 0, 0);
        this.Shape46.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape46.setPos(0.0f, -12.0f, -11.0f);
        this.Shape46.mirror = true;
        this.setRotation(this.Shape46, 0.0f, 0.0f, 0.0f);
        this.Shape47 = new ModelRenderer(this, 0, 0);
        this.Shape47.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape47.setPos(0.0f, -13.0f, -13.0f);
        this.Shape47.mirror = true;
        this.setRotation(this.Shape47, 0.0f, 0.0f, 0.0f);
        this.Shape48 = new ModelRenderer(this, 0, 0);
        this.Shape48.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape48.setPos(0.0f, -15.0f, -15.0f);
        this.Shape48.mirror = true;
        this.setRotation(this.Shape48, 0.0f, 0.0f, 0.0f);
        this.Shape49 = new ModelRenderer(this, 0, 0);
        this.Shape49.addBox(0.0f, 0.0f, 0.0f, 0, 2, 1);
        this.Shape49.setPos(0.0f, -16.0f, -16.0f);
        this.Shape49.mirror = true;
        this.setRotation(this.Shape49, 0.0f, 0.0f, 0.0f);
        this.Shape50 = new ModelRenderer(this, 0, 0);
        this.Shape50.addBox(0.0f, 0.0f, 0.0f, 0, 1, 1);
        this.Shape50.setPos(0.0f, -19.0f, -17.0f);
        this.Shape50.mirror = true;
        this.setRotation(this.Shape50, 0.0f, 0.0f, 0.0f);
        this.Shape51 = new ModelRenderer(this, 0, 0);
        this.Shape51.addBox(0.0f, 0.0f, 0.0f, 0, 1, 1);
        this.Shape51.setPos(0.0f, -19.0f, -19.0f);
        this.Shape51.mirror = true;
        this.setRotation(this.Shape51, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 10, 17, 25);
        this.Shape1.setPos(-5.0f, -15.0f, -10.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 0, 93);
        this.Shape2.addBox(-3.0f, 0.0f, -11.0f, 6, 10, 11);
        this.Shape2.setPos(0.0f, -10.0f, -6.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.1919862f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 29, 110);
        this.Shape3.addBox(-2.0f, -9.0f, -8.0f, 4, 9, 8);
        this.Shape3.setPos(0.0f, -10.0f, -11.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.7504916f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer(this, 54, 108);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 6, 7, 12);
        this.Shape4.setPos(-3.0f, -18.0f, -28.0f);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer(this, 54, 86);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 3, 6, 15);
        this.Shape5.setPos(-1.5f, -17.5f, -43.0f);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer(this, 0, 43);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 8, 11, 8);
        this.Shape6.setPos(-4.0f, -15.0f, 15.0f);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer(this, 0, 63);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 6, 6, 23);
        this.Shape7.setPos(-3.0f, -15.0f, 23.0f);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer(this, 47, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 2, 5, 3);
        this.Shape8.setPos(5.0f, 0.0f, -7.0f);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer(this, 49, 10);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape9.setPos(5.1f, 3.0f, -6.0f);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -0.3839724f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer(this, 13, 17);
        this.Shape10.addBox(0.0f, 0.0f, 0.0f, 2, 4, 3);
        this.Shape10.setPos(5.0f, 7.0f, -8.0f);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer(this, 0, 17);
        this.Shape11.addBox(0.0f, 0.0f, -2.0f, 1, 1, 2);
        this.Shape11.setPos(5.0f, 8.0f, -8.0f);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer(this, 0, 21);
        this.Shape12.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Shape12.setPos(5.0f, 9.0f, -11.0f);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        this.Shape13 = new ModelRenderer(this, 95, 36);
        this.Shape13.addBox(0.0f, 0.0f, 0.0f, 3, 21, 13);
        this.Shape13.setPos(5.0f, -15.0f, 2.0f);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        this.Shape14 = new ModelRenderer(this, 36, 94);
        this.Shape14.addBox(0.0f, 0.0f, -3.0f, 3, 5, 3);
        this.Shape14.setPos(-1.5f, -17.0f, -43.0f);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        this.Shape15 = new ModelRenderer(this, 113, 71);
        this.Shape15.addBox(0.0f, 18.0f, 8.0f, 3, 18, 4);
        this.Shape15.setPos(5.0f, -15.0f, 2.0f);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, -0.1745329f, 0.0f, 0.0f);
        this.Shape16 = new ModelRenderer(this, 13, 11);
        this.Shape16.addBox(-2.0f, 0.0f, 0.0f, 2, 1, 3);
        this.Shape16.setPos(5.0f, 10.0f, -8.0f);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer(this, 0, 74);
        this.Shape17.addBox(0.0f, 35.0f, -1.0f, 3, 3, 6);
        this.Shape17.setPos(5.0f, -15.0f, 2.0f);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
        this.Shape18 = new ModelRenderer(this, 58, 0);
        this.Shape18.addBox(-2.0f, 0.0f, 0.0f, 2, 5, 3);
        this.Shape18.setPos(-5.0f, 0.0f, -7.0f);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0f, 0.0f, 0.0f);
        this.Shape19 = new ModelRenderer(this, 59, 10);
        this.Shape19.addBox(-2.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape19.setPos(-5.1f, 3.0f, -6.0f);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, -0.3839724f, 0.0f, 0.0f);
        this.Shape20 = new ModelRenderer(this, 71, 5);
        this.Shape20.addBox(-2.0f, 0.0f, 0.0f, 2, 4, 3);
        this.Shape20.setPos(-5.0f, 7.0f, -8.0f);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0f, 0.0f, 0.0f);
        this.Shape21 = new ModelRenderer(this, 71, 0);
        this.Shape21.addBox(0.0f, 0.0f, 0.0f, 2, 1, 3);
        this.Shape21.setPos(-5.0f, 10.0f, -8.0f);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0f, 0.0f, 0.0f);
        this.Shape22 = new ModelRenderer(this, 0, 10);
        this.Shape22.addBox(-1.0f, 0.0f, -2.0f, 1, 1, 2);
        this.Shape22.setPos(-5.0f, 8.0f, -8.0f);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0f, 0.0f, 0.0f);
        this.Shape23 = new ModelRenderer(this, 0, 14);
        this.Shape23.addBox(-1.0f, 0.0f, 0.0f, 1, 1, 1);
        this.Shape23.setPos(-5.0f, 9.0f, -11.0f);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0f, 0.0f, 0.0f);
        this.Shape24 = new ModelRenderer(this, 95, 0);
        this.Shape24.addBox(-3.0f, 0.0f, 0.0f, 3, 22, 13);
        this.Shape24.setPos(-5.0f, -15.0f, 2.0f);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.0f, 0.0f, 0.0f);
        this.Shape25 = new ModelRenderer(this, 96, 71);
        this.Shape25.addBox(-3.0f, 18.0f, 8.0f, 3, 18, 4);
        this.Shape25.setPos(-5.0f, -15.0f, 2.0f);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, -0.1745329f, 0.0f, 0.0f);
        this.Shape26 = new ModelRenderer(this, 0, 64);
        this.Shape26.addBox(-3.0f, 35.0f, -1.0f, 3, 3, 6);
        this.Shape26.setPos(-5.0f, -15.0f, 2.0f);
        this.Shape26.mirror = true;
        this.setRotation(this.Shape26, 0.0f, 0.0f, 0.0f);
        this.Shape52 = new ModelRenderer(this, 9, 0);
        this.Shape52.addBox(0.0f, 0.0f, 0.0f, 0, 2, 2);
        this.Shape52.setPos(0.0f, -19.0f, -30.0f);
        this.Shape52.mirror = true;
        this.setRotation(this.Shape52, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Baryonyx entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Baryonyx e = (Baryonyx)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.15f * f1 : 0.0f;
        this.Shape24.xRot = newangle;
        this.Shape25.xRot = -0.17f + newangle;
        this.Shape26.xRot = newangle;
        this.Shape13.xRot = - newangle;
        this.Shape15.xRot = -0.17f - newangle;
        this.Shape17.xRot = - newangle;
        this.Shape21.zRot = newangle = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.Shape16.zRot = - newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape27.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape28.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape29.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape30.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape31.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape32.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape33.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape34.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape35.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape36.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape37.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape38.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape39.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape40.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape41.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape42.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape43.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape44.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape45.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape46.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape47.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape48.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape49.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape50.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape51.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape18.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape19.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape20.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape22.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape23.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape24.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape25.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape26.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape52.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

