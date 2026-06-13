/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Hammerhead
 *  com.astryxion.chaospersists.ModelHammerhead
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Hammerhead;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHammerhead extends EntityModel<Hammerhead> {
    private float wingspeed = 1.0f;
    ModelRenderer chest;
    ModelRenderer abdomen;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer snout;
    ModelRenderer neck_armour;
    ModelRenderer horn_base;
    ModelRenderer horn_1;
    ModelRenderer horn_2;
    ModelRenderer horn_R;
    ModelRenderer horn_L;
    ModelRenderer back_armour1;
    ModelRenderer back_armour_2;
    ModelRenderer back_armour_3;
    ModelRenderer back_armour_3R;
    ModelRenderer back_armour_4;
    ModelRenderer back_armour_4R;
    ModelRenderer tail;
    ModelRenderer leg_1R;
    ModelRenderer leg_1;
    ModelRenderer leg_2;
    ModelRenderer leg_2R;
    ModelRenderer leg_3R;
    ModelRenderer leg_3;
    ModelRenderer leg_1Rb;
    ModelRenderer leg_1b;
    ModelRenderer leg_2b;
    ModelRenderer leg_2Rb;
    ModelRenderer leg_3Rb;
    ModelRenderer leg_3b;
    ModelRenderer fan1;
    ModelRenderer Lfan2;
    ModelRenderer Rfan2;
    ModelRenderer Lfan3;
    ModelRenderer Rfan3;
    ModelRenderer Lear;
    ModelRenderer Rear;

    public ModelHammerhead(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 222;
        // textureHeight = 256;
        this.chest = new ModelRenderer(this, 0, 0);
        this.chest.addBox(-9.0f, -1.0f, 0.0f, 19, 16, 17);
        this.chest.setPos(0.0f, -1.0f, -12.0f);
        this.chest.mirror = true;
        this.setRotation(this.chest, 0.0349066f, 0.0f, 0.0f);
        this.abdomen = new ModelRenderer(this, 0, 34);
        this.abdomen.addBox(-7.5f, 0.0f, 0.0f, 16, 14, 16);
        this.abdomen.setPos(0.0f, -2.0f, 4.0f);
        this.abdomen.mirror = true;
        this.setRotation(this.abdomen, -0.0349066f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 146, 59);
        this.neck.addBox(-6.5f, -0.5f, -12.0f, 14, 13, 13);
        this.neck.setPos(0.0f, -1.0f, -12.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.1570796f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 101, 59);
        this.head.addBox(-6.0f, -0.5f, -21.0f, 13, 11, 9);
        this.head.setPos(0.0f, -1.0f, -12.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.2094395f, 0.0f, 0.0f);
        this.snout = new ModelRenderer(this, 166, 86);
        this.snout.addBox(-4.0f, -6.0f, -27.0f, 9, 8, 8);
        this.snout.setPos(0.0f, -1.0f, -12.0f);
        this.snout.mirror = true;
        this.setRotation(this.snout, 0.6108652f, 0.0f, 0.0f);
        this.neck_armour = new ModelRenderer(this, 73, 0);
        this.neck_armour.addBox(-7.0f, -1.5f, -18.0f, 15, 4, 18);
        this.neck_armour.setPos(0.0f, -1.0f, -12.0f);
        this.neck_armour.mirror = true;
        this.setRotation(this.neck_armour, 0.1570796f, 0.0f, 0.0f);
        this.horn_base = new ModelRenderer(this, 49, 35);
        this.horn_base.addBox(-7.0f, -1.5f, -27.0f, 15, 5, 9);
        this.horn_base.setPos(0.0f, -1.0f, -12.0f);
        this.horn_base.mirror = true;
        this.setRotation(this.horn_base, 0.0872665f, 0.0f, 0.0f);
        this.horn_1 = new ModelRenderer(this, 122, 23);
        this.horn_1.addBox(-12.0f, -4.5f, -40.0f, 25, 6, 14);
        this.horn_1.setPos(0.0f, -1.0f, -12.0f);
        this.horn_1.mirror = true;
        this.setRotation(this.horn_1, 0.1919862f, 0.0f, 0.0f);
        this.horn_2 = new ModelRenderer(this, 106, 44);
        this.horn_2.addBox(-18.0f, -3.5f, -37.0f, 37, 4, 10);
        this.horn_2.setPos(0.0f, -1.0f, -12.0f);
        this.horn_2.mirror = true;
        this.setRotation(this.horn_2, 0.1919862f, 0.0f, 0.0f);
        this.horn_R = new ModelRenderer(this, 158, 0);
        this.horn_R.addBox(-26.0f, -5.5f, -38.5f, 8, 7, 13);
        this.horn_R.setPos(0.0f, -1.0f, -12.0f);
        this.horn_R.mirror = true;
        this.setRotation(this.horn_R, 0.1919862f, 0.0f, -0.0174533f);
        this.horn_L = new ModelRenderer(this, 158, 0);
        this.horn_L.addBox(19.0f, -5.5f, -38.5f, 8, 7, 13);
        this.horn_L.setPos(0.0f, -1.0f, -12.0f);
        this.horn_L.mirror = true;
        this.setRotation(this.horn_L, 0.1919862f, 0.0f, -0.0174533f);
        this.back_armour1 = new ModelRenderer(this, 0, 98);
        this.back_armour1.addBox(-5.0f, -2.5f, -6.0f, 9, 3, 7);
        this.back_armour1.setPos(1.0f, -4.0f, -15.0f);
        this.back_armour1.mirror = true;
        this.setRotation(this.back_armour1, -0.0872665f, 0.0f, 0.0f);
        this.back_armour_2 = new ModelRenderer(this, 0, 65);
        this.back_armour_2.addBox(-8.0f, -4.5f, -13.0f, 17, 4, 28);
        this.back_armour_2.setPos(0.0f, -1.0f, -3.0f);
        this.back_armour_2.mirror = true;
        this.setRotation(this.back_armour_2, -0.122173f, 0.0f, 0.0f);
        this.back_armour_3 = new ModelRenderer(this, 15, 104);
        this.back_armour_3.addBox(0.5f, -3.5f, -13.0f, 4, 4, 20);
        this.back_armour_3.setPos(8.0f, 1.0f, -2.0f);
        this.back_armour_3.mirror = true;
        this.setRotation(this.back_armour_3, 0.0174533f, 0.1570796f, 0.0f);
        this.back_armour_3R = new ModelRenderer(this, 15, 104);
        this.back_armour_3R.addBox(-3.5f, -3.5f, -13.0f, 4, 4, 20);
        this.back_armour_3R.setPos(-8.0f, 1.0f, -2.0f);
        this.back_armour_3R.mirror = true;
        this.setRotation(this.back_armour_3R, 0.0174533f, -0.1570796f, 0.0f);
        this.back_armour_4 = new ModelRenderer(this, 0, 65);
        this.back_armour_4.addBox(1.5f, -1.5f, -3.0f, 3, 4, 10);
        this.back_armour_4.setPos(6.0f, 5.0f, -10.0f);
        this.back_armour_4.mirror = true;
        this.setRotation(this.back_armour_4, -0.1396263f, 0.3490659f, 0.0f);
        this.back_armour_4R = new ModelRenderer(this, 0, 65);
        this.back_armour_4R.addBox(-1.5f, -1.5f, -3.0f, 3, 4, 10);
        this.back_armour_4R.setPos(-8.0f, 5.0f, -11.0f);
        this.back_armour_4R.mirror = true;
        this.setRotation(this.back_armour_4R, -0.1396263f, -0.3490659f, 0.0f);
        this.tail = new ModelRenderer(this, 66, 52);
        this.tail.addBox(-2.0f, 0.0f, -3.0f, 5, 5, 3);
        this.tail.setPos(0.0f, 0.0f, 20.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.5061455f, 0.0f, 0.0f);
        this.leg_1R = new ModelRenderer(this, 71, 102);
        this.leg_1R.addBox(-2.5f, -2.5f, -3.0f, 5, 10, 6);
        this.leg_1R.setPos(-9.0f, 11.0f, -10.0f);
        this.leg_1R.mirror = true;
        this.setRotation(this.leg_1R, -0.0872665f, 0.0f, 0.0f);
        this.leg_1 = new ModelRenderer(this, 64, 76);
        this.leg_1.addBox(-1.5f, -2.5f, -3.0f, 5, 10, 6);
        this.leg_1.setPos(9.0f, 11.0f, -10.0f);
        this.leg_1.mirror = true;
        this.setRotation(this.leg_1, -0.0872665f, 0.0f, 0.0f);
        this.leg_2 = new ModelRenderer(this, 98, 28);
        this.leg_2.addBox(-1.5f, -2.5f, -3.0f, 5, 9, 6);
        this.leg_2.setPos(9.0f, 12.0f, -2.0f);
        this.leg_2.mirror = true;
        this.setRotation(this.leg_2, -0.0523599f, 0.0f, 0.0f);
        this.leg_2R = new ModelRenderer(this, 98, 80);
        this.leg_2R.addBox(-1.5f, -2.5f, -3.0f, 5, 9, 6);
        this.leg_2R.setPos(-10.0f, 12.0f, -2.0f);
        this.leg_2R.mirror = true;
        this.setRotation(this.leg_2R, -0.0523599f, 0.0f, 0.0f);
        this.leg_3R = new ModelRenderer(this, 44, 129);
        this.leg_3R.addBox(-3.5f, -2.5f, -3.0f, 5, 11, 8);
        this.leg_3R.setPos(-7.0f, 9.0f, 14.0f);
        this.leg_3R.mirror = true;
        this.setRotation(this.leg_3R, -0.3490659f, 0.0f, 0.0f);
        this.leg_3 = new ModelRenderer(this, 44, 99);
        this.leg_3.addBox(-3.5f, -2.5f, -3.0f, 5, 11, 8);
        this.leg_3.setPos(10.0f, 9.0f, 14.0f);
        this.leg_3.mirror = true;
        this.setRotation(this.leg_3, -0.3490659f, 0.0f, 0.0f);
        this.leg_1Rb = new ModelRenderer(this, 15, 129);
        this.leg_1Rb.addBox(-2.0f, 5.5f, -3.0f, 4, 8, 5);
        this.leg_1Rb.setPos(-9.0f, 11.0f, -10.0f);
        this.leg_1Rb.mirror = true;
        this.setRotation(this.leg_1Rb, 0.0f, 0.0f, 0.0f);
        this.leg_1b = new ModelRenderer(this, 15, 110);
        this.leg_1b.addBox(-1.0f, 5.5f, -3.0f, 4, 8, 5);
        this.leg_1b.setPos(9.0f, 11.0f, -10.0f);
        this.leg_1b.mirror = true;
        this.setRotation(this.leg_1b, 0.0f, 0.0f, 0.0f);
        this.leg_2b = new ModelRenderer(this, 57, 1);
        this.leg_2b.addBox(-1.0f, 5.5f, -3.0f, 4, 7, 5);
        this.leg_2b.setPos(9.0f, 12.0f, -2.0f);
        this.leg_2b.mirror = true;
        this.setRotation(this.leg_2b, 0.0523599f, 0.0f, 0.0f);
        this.leg_2Rb = new ModelRenderer(this, 94, 106);
        this.leg_2Rb.addBox(-2.0f, 5.5f, -3.0f, 4, 7, 5);
        this.leg_2Rb.setPos(-9.0f, 12.0f, -2.0f);
        this.leg_2Rb.mirror = true;
        this.setRotation(this.leg_2Rb, 0.0523599f, 0.0f, 0.0f);
        this.leg_3Rb = new ModelRenderer(this, 122, 81);
        this.leg_3Rb.addBox(-2.0f, 6.5f, -5.0f, 4, 9, 5);
        this.leg_3Rb.setPos(-8.0f, 9.0f, 14.0f);
        this.leg_3Rb.mirror = true;
        this.setRotation(this.leg_3Rb, 0.122173f, 0.0f, 0.0f);
        this.leg_3b = new ModelRenderer(this, 122, 0);
        this.leg_3b.addBox(-3.0f, 6.5f, -5.0f, 4, 9, 5);
        this.leg_3b.setPos(10.0f, 9.0f, 14.0f);
        this.leg_3b.mirror = true;
        this.setRotation(this.leg_3b, 0.122173f, 0.0f, 0.0f);
        this.fan1 = new ModelRenderer(this, 0, 109);
        this.fan1.addBox(-1.0f, -7.0f, -34.0f, 4, 15, 1);
        this.fan1.setPos(0.0f, -1.0f, -12.0f);
        this.fan1.mirror = true;
        this.setRotation(this.fan1, -0.1396263f, 0.0f, 0.0f);
        this.Lfan2 = new ModelRenderer(this, 0, 109);
        this.Lfan2.addBox(-1.0f, -3.0f, -31.5f, 4, 12, 1);
        this.Lfan2.setPos(0.0f, -1.0f, -14.0f);
        this.Lfan2.mirror = true;
        this.setRotation(this.Lfan2, -0.2094395f, -0.122173f, 0.0f);
        this.Rfan2 = new ModelRenderer(this, 0, 109);
        this.Rfan2.addBox(-1.0f, -3.0f, -33.5f, 4, 12, 1);
        this.Rfan2.setPos(0.0f, -1.0f, -12.0f);
        this.Rfan2.mirror = true;
        this.setRotation(this.Rfan2, -0.2094395f, 0.122173f, 0.0f);
        this.Lfan3 = new ModelRenderer(this, 0, 109);
        this.Lfan3.addBox(-1.0f, 4.0f, -32.0f, 4, 9, 1);
        this.Lfan3.setPos(0.0f, -1.0f, -12.0f);
        this.Lfan3.mirror = true;
        this.setRotation(this.Lfan3, -0.3316126f, -0.2268928f, 0.0f);
        this.Rfan3 = new ModelRenderer(this, 0, 109);
        this.Rfan3.addBox(-1.0f, 4.0f, -32.0f, 4, 9, 1);
        this.Rfan3.setPos(0.0f, -1.0f, -12.0f);
        this.Rfan3.mirror = true;
        this.setRotation(this.Rfan3, -0.3316126f, 0.2443461f, 0.0f);
        this.Lear = new ModelRenderer(this, 0, 80);
        this.Lear.addBox(8.5f, 2.5f, -10.0f, 1, 1, 10);
        this.Lear.setPos(0.0f, -1.0f, -12.0f);
        this.Lear.mirror = true;
        this.setRotation(this.Lear, 0.3665191f, 0.2268928f, 0.0f);
        this.Rear = new ModelRenderer(this, 0, 80);
        this.Rear.addBox(-8.5f, 2.5f, -11.0f, 1, 1, 10);
        this.Rear.setPos(0.0f, -1.0f, -12.0f);
        this.Rear.mirror = true;
        this.setRotation(this.Rear, 0.3665191f, -0.2268928f, 0.0f);
    }
    @Override
    public void setupAnim(Hammerhead entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Hammerhead e = (Hammerhead)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float newangle2 = 0.0f;
        if ((double)f1 > 0.1) {
            newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f * f1;
            newangle2 = MathHelper.cos((float)((float)((double)(f2 * 1.3f * this.wingspeed) + 0.7853981633974483))) * 3.1415927f * 0.1f * f1;
        } else {
            newangle = 0.0f;
        }
        this.leg_1.xRot = -0.087f + newangle;
        this.leg_1b.xRot = newangle;
        this.leg_1R.xRot = -0.087f - newangle;
        this.leg_1Rb.xRot = - newangle;
        this.leg_2.xRot = -0.052f + newangle2;
        this.leg_2b.xRot = newangle2;
        this.leg_2R.xRot = -0.052f - newangle2;
        this.leg_2Rb.xRot = - newangle2;
        this.leg_3.xRot = -0.349f - newangle;
        this.leg_3b.xRot = - newangle;
        this.leg_3R.xRot = -0.349f + newangle;
        this.leg_3Rb.xRot = newangle;
        this.neck_armour.yRot = this.neck.yRot = (float)Math.toRadians(f3) * 0.25f;
        this.horn_base.yRot = this.neck.yRot;
        this.horn_1.yRot = this.neck.yRot;
        this.horn_2.yRot = this.neck.yRot;
        this.horn_L.yRot = this.neck.yRot;
        this.horn_R.yRot = this.neck.yRot;
        this.head.yRot = this.neck.yRot;
        this.snout.yRot = this.neck.yRot;
        this.fan1.yRot = this.neck.yRot;
        this.Lfan2.yRot = this.neck.yRot - 0.122f;
        this.Lfan3.yRot = this.neck.yRot - 0.226f;
        this.Rfan2.yRot = this.neck.yRot + 0.122f;
        this.Rfan3.yRot = this.neck.yRot + 0.226f;
        this.Lear.yRot = this.neck.yRot + 0.227f;
        this.Rear.yRot = this.neck.yRot - 0.227f;
        newangle = MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.03f;
        this.back_armour_4.yRot = 0.349f + newangle;
        this.back_armour_4R.yRot = -0.349f - newangle;
        newangle = e.getAttacking() != 0 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.13f : 0.0f;
        this.neck.xRot = newangle + 0.157f;
        this.neck_armour.xRot = newangle + 0.157f;
        this.horn_base.xRot = newangle + 0.087f;
        this.horn_1.xRot = newangle + 0.192f;
        this.horn_2.xRot = newangle + 0.192f;
        this.horn_L.xRot = newangle + 0.192f;
        this.horn_R.xRot = newangle + 0.192f;
        this.head.xRot = newangle + 0.209f;
        this.snout.xRot = newangle + 0.611f;
        this.fan1.xRot = newangle - 0.139f;
        this.Lfan2.xRot = newangle - 0.209f;
        this.Lfan3.xRot = newangle - 0.331f;
        this.Rfan2.xRot = newangle - 0.209f;
        this.Rfan3.xRot = newangle - 0.331f;
        this.Lear.xRot = newangle + 0.366f;
        this.Rear.xRot = newangle + 0.366f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.snout.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck_armour.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn_base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn_R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn_L.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_armour1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_armour_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_armour_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_armour_3R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_armour_4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_armour_4R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_1R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_2R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_3R.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_1Rb.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_1b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_2b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_2Rb.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_3Rb.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg_3b.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fan1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lfan2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rfan2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lfan3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rfan3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

