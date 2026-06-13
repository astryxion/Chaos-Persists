/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EnderKnight
 *  com.astryxion.chaospersists.ModelEnderKnight
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.EnderKnight;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEnderKnight extends EntityModel<EnderKnight> {
    ModelRenderer rleg1;
    ModelRenderer rleg3;
    ModelRenderer pelvis;
    ModelRenderer spine1;
    ModelRenderer spine2;
    ModelRenderer spine3;
    ModelRenderer neck;
    ModelRenderer rleg2;
    ModelRenderer rhip;
    ModelRenderer rib4;
    ModelRenderer rib3;
    ModelRenderer rib2;
    ModelRenderer rib1;
    ModelRenderer rfoot1;
    ModelRenderer rfoot3;
    ModelRenderer rcollar;
    ModelRenderer lcollar;
    ModelRenderer lleg3;
    ModelRenderer lleg2;
    ModelRenderer lhip;
    ModelRenderer lleg1;
    ModelRenderer rfoot4;
    ModelRenderer rfoot2;
    ModelRenderer cape2;
    ModelRenderer cape1;
    ModelRenderer lfoot1;
    ModelRenderer lfoot3;
    ModelRenderer lfoot2;
    ModelRenderer lfoot4;
    ModelRenderer head;
    ModelRenderer lshoulder;
    ModelRenderer rshoulder;
    ModelRenderer rarm3;
    ModelRenderer rarm2;
    ModelRenderer rarm1;
    ModelRenderer larm3;
    ModelRenderer larm2;
    ModelRenderer larm1;
    ModelRenderer blade;
    ModelRenderer handle;
    private float wingspeed = 1.0f;

    public ModelEnderKnight(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 512;
        this.rleg1 = new ModelRenderer(this, 20, 50);
        this.rleg1.addBox(0.0f, 12.0f, -1.0f, 1, 15, 1);
        this.rleg1.setPos(-7.0f, -5.0f, -2.0f);
        this.rleg1.mirror = true;
        this.setRotation(this.rleg1, 0.0f, 0.0f, 0.0f);
        this.rleg3 = new ModelRenderer(this, 20, 100);
        this.rleg3.addBox(0.0f, 0.0f, 0.0f, 1, 14, 2);
        this.rleg3.setPos(-6.0f, -5.0f, -2.0f);
        this.rleg3.mirror = true;
        this.setRotation(this.rleg3, -0.1f, 0.0f, 0.0f);
        this.pelvis = new ModelRenderer(this, 20, 150);
        this.pelvis.addBox(0.0f, 0.0f, 0.0f, 3, 3, 3);
        this.pelvis.setPos(-5.0f, -6.0f, -2.0f);
        this.pelvis.mirror = true;
        this.setRotation(this.pelvis, 0.0f, 0.0f, 0.0f);
        this.spine1 = new ModelRenderer(this, 20, 200);
        this.spine1.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.spine1.setPos(-4.0f, -9.0f, 1.0f);
        this.spine1.mirror = true;
        this.setRotation(this.spine1, -0.3f, 0.0f, 0.0f);
        this.spine2 = new ModelRenderer(this, 20, 250);
        this.spine2.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.spine2.setPos(-4.0f, -13.0f, 1.0f);
        this.spine2.mirror = true;
        this.setRotation(this.spine2, 0.0f, 0.0f, 0.0f);
        this.spine3 = new ModelRenderer(this, 20, 300);
        this.spine3.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.spine3.setPos(-4.0f, -17.0f, 0.0f);
        this.spine3.mirror = true;
        this.setRotation(this.spine3, 0.2f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 20, 11);
        this.neck.addBox(0.0f, 0.0f, 0.0f, 5, 3, 3);
        this.neck.setPos(-6.0f, -20.0f, 0.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        this.rleg2 = new ModelRenderer(this, 20, 400);
        this.rleg2.addBox(0.0f, 0.0f, 0.0f, 1, 14, 2);
        this.rleg2.setPos(-8.0f, -5.0f, -2.0f);
        this.rleg2.mirror = true;
        this.setRotation(this.rleg2, -0.1f, 0.0f, 0.0f);
        this.rhip = new ModelRenderer(this, 20, 450);
        this.rhip.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.rhip.setPos(-7.0f, -4.0f, -2.0f);
        this.rhip.mirror = true;
        this.setRotation(this.rhip, 0.0f, 0.0f, 0.0f);
        this.rib4 = new ModelRenderer(this, 20, 79);
        this.rib4.addBox(0.0f, 0.0f, 0.0f, 3, 1, 1);
        this.rib4.setPos(-5.0f, -9.0f, 1.0f);
        this.rib4.mirror = true;
        this.setRotation(this.rib4, 0.0f, 0.0f, 0.0f);
        this.rib3 = new ModelRenderer(this, 20, 86);
        this.rib3.addBox(0.0f, 0.0f, 0.0f, 3, 1, 1);
        this.rib3.setPos(-5.0f, -11.0f, 1.0f);
        this.rib3.mirror = true;
        this.setRotation(this.rib3, 0.0f, 0.0f, 0.0f);
        this.rib2 = new ModelRenderer(this, 20, 94);
        this.rib2.addBox(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.rib2.setPos(-6.0f, -13.0f, 1.0f);
        this.rib2.mirror = true;
        this.setRotation(this.rib2, 0.0f, 0.0f, 0.0f);
        this.rib1 = new ModelRenderer(this, 20, 122);
        this.rib1.addBox(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.rib1.setPos(-6.0f, -16.0f, 0.0f);
        this.rib1.mirror = true;
        this.setRotation(this.rib1, 0.0f, 0.0f, 0.0f);
        this.rfoot1 = new ModelRenderer(this, 20, 131);
        this.rfoot1.addBox(0.0f, 21.0f, -2.0f, 3, 8, 3);
        this.rfoot1.setPos(-8.0f, -5.0f, -2.0f);
        this.rfoot1.mirror = true;
        this.setRotation(this.rfoot1, 0.0f, 0.0f, 0.0f);
        this.rfoot3 = new ModelRenderer(this, 20, 162);
        this.rfoot3.addBox(0.0f, 27.0f, -5.0f, 3, 2, 6);
        this.rfoot3.setPos(-8.0f, -5.0f, -2.0f);
        this.rfoot3.mirror = true;
        this.setRotation(this.rfoot3, 0.0f, 0.0f, 0.0f);
        this.rcollar = new ModelRenderer(this, 20, 243);
        this.rcollar.addBox(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.rcollar.setPos(-11.0f, -19.0f, 1.0f);
        this.rcollar.mirror = true;
        this.setRotation(this.rcollar, 0.0f, 0.0f, 0.0f);
        this.lcollar = new ModelRenderer(this, 20, 286);
        this.lcollar.addBox(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.lcollar.setPos(-1.0f, -19.0f, 1.0f);
        this.lcollar.mirror = true;
        this.setRotation(this.lcollar, 0.0f, 0.0f, 0.0f);
        this.lleg3 = new ModelRenderer(this, 48, 159);
        this.lleg3.addBox(0.0f, 0.0f, 0.0f, 1, 14, 2);
        this.lleg3.setPos(-2.0f, -5.0f, -2.0f);
        this.lleg3.mirror = true;
        this.setRotation(this.lleg3, -0.1f, 0.0f, 0.0f);
        this.lleg2 = new ModelRenderer(this, 28, 187);
        this.lleg2.addBox(0.0f, 0.0f, 0.0f, 1, 14, 2);
        this.lleg2.setPos(0.0f, -5.0f, -2.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, -0.1f, 0.0f, 0.0f);
        this.lhip = new ModelRenderer(this, 32, 219);
        this.lhip.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.lhip.setPos(-1.0f, -4.0f, -2.0f);
        this.lhip.mirror = true;
        this.setRotation(this.lhip, 0.0f, 0.0f, 0.0f);
        this.lleg1 = new ModelRenderer(this, 36, 224);
        this.lleg1.addBox(0.0f, 12.0f, -1.0f, 1, 15, 1);
        this.lleg1.setPos(-1.0f, -5.0f, -2.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, 0.0f, 0.0f, 0.0f);
        this.rfoot4 = new ModelRenderer(this, 33, 254);
        this.rfoot4.addBox(0.0f, 26.0f, -3.0f, 3, 1, 1);
        this.rfoot4.setPos(-8.0f, -5.0f, -2.0f);
        this.rfoot4.mirror = true;
        this.setRotation(this.rfoot4, 0.0f, 0.0f, 0.0f);
        this.rfoot2 = new ModelRenderer(this, 32, 36);
        this.rfoot2.addBox(0.0f, 19.5f, -19.0f, 3, 1, 5);
        this.rfoot2.setPos(-8.0f, -5.0f, -2.0f);
        this.rfoot2.mirror = true;
        this.setRotation(this.rfoot2, 0.6f, 0.0f, 0.0f);
        this.cape2 = new ModelRenderer(this, 51, 276);
        this.cape2.addBox(-4.0f, 0.0f, 0.0f, 9, 24, 0);
        this.cape2.setPos(-4.0f, -20.0f, 4.0f);
        this.cape2.mirror = true;
        this.setRotation(this.cape2, 0.0f, 0.0f, 0.0f);
        this.cape1 = new ModelRenderer(this, 51, 264);
        this.cape1.addBox(0.0f, 0.0f, 0.0f, 9, 1, 1);
        this.cape1.setPos(-8.0f, -20.0f, 3.0f);
        this.cape1.mirror = true;
        this.setRotation(this.cape1, 0.0f, 0.0f, 0.0f);
        this.lfoot1 = new ModelRenderer(this, 44, 182);
        this.lfoot1.addBox(0.0f, 21.0f, -2.0f, 3, 8, 3);
        this.lfoot1.setPos(-2.0f, -5.0f, -2.0f);
        this.lfoot1.mirror = true;
        this.setRotation(this.lfoot1, 0.0f, 0.0f, 0.0f);
        this.lfoot3 = new ModelRenderer(this, 52, 200);
        this.lfoot3.addBox(0.0f, 27.0f, -5.0f, 3, 2, 6);
        this.lfoot3.setPos(-2.0f, -5.0f, -2.0f);
        this.lfoot3.mirror = true;
        this.setRotation(this.lfoot3, 0.0f, 0.0f, 0.0f);
        this.lfoot2 = new ModelRenderer(this, 52, 218);
        this.lfoot2.addBox(0.0f, 19.5f, -19.0f, 3, 1, 5);
        this.lfoot2.setPos(-2.0f, -5.0f, -2.0f);
        this.lfoot2.mirror = true;
        this.setRotation(this.lfoot2, 0.6f, 0.0f, 0.0f);
        this.lfoot4 = new ModelRenderer(this, 48, 235);
        this.lfoot4.addBox(0.0f, 26.0f, -3.0f, 3, 1, 1);
        this.lfoot4.setPos(-2.0f, -5.0f, -2.0f);
        this.lfoot4.mirror = true;
        this.setRotation(this.lfoot4, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 34, 106);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 7, 6, 6);
        this.head.setPos(-3.0f, -18.0f, 3.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.lshoulder = new ModelRenderer(this, 48, 16);
        this.lshoulder.addBox(0.0f, 0.0f, 0.0f, 5, 4, 4);
        this.lshoulder.setPos(2.0f, -21.0f, 0.0f);
        this.lshoulder.mirror = true;
        this.setRotation(this.lshoulder, 0.0f, 0.0f, 0.0f);
        this.rshoulder = new ModelRenderer(this, 48, 16);
        this.rshoulder.addBox(0.0f, 0.0f, 0.0f, 5, 4, 4);
        this.rshoulder.setPos(-14.0f, -21.0f, 0.0f);
        this.rshoulder.mirror = true;
        this.setRotation(this.rshoulder, 0.0f, 0.0f, 0.0f);
        this.rarm3 = new ModelRenderer(this, 39, 64);
        this.rarm3.addBox(0.0f, 0.0f, 0.0f, 1, 12, 1);
        this.rarm3.setPos(-11.0f, -18.0f, 1.0f);
        this.rarm3.mirror = true;
        this.setRotation(this.rarm3, -0.5f, 0.0f, 0.0f);
        this.rarm2 = new ModelRenderer(this, 57, 62);
        this.rarm2.addBox(0.0f, 0.0f, 0.0f, 1, 12, 1);
        this.rarm2.setPos(-13.0f, -18.0f, 1.0f);
        this.rarm2.mirror = true;
        this.setRotation(this.rarm2, -0.5f, 0.0f, 0.0f);
        this.rarm1 = new ModelRenderer(this, 49, 81);
        this.rarm1.addBox(0.0f, 0.0f, 0.0f, 1, 11, 1);
        this.rarm1.setPos(-12.0f, -18.0f, 2.0f);
        this.rarm1.mirror = true;
        this.setRotation(this.rarm1, -1.0f, -1.0f, 0.0f);
        this.larm3 = new ModelRenderer(this, 49, 129);
        this.larm3.addBox(0.0f, 0.0f, 0.0f, 1, 12, 1);
        this.larm3.setPos(3.0f, -18.0f, 1.0f);
        this.larm3.mirror = true;
        this.setRotation(this.larm3, -0.5f, 0.0f, 0.0f);
        this.larm2 = new ModelRenderer(this, 64, 133);
        this.larm2.addBox(0.0f, 0.0f, 0.0f, 1, 12, 1);
        this.larm2.setPos(5.0f, -18.0f, 1.0f);
        this.larm2.mirror = true;
        this.setRotation(this.larm2, -0.5f, 0.0f, 0.0f);
        this.larm1 = new ModelRenderer(this, 22, 316);
        this.larm1.addBox(0.0f, 0.0f, 0.0f, 1, 11, 1);
        this.larm1.setPos(4.0f, -18.0f, 1.0f);
        this.larm1.mirror = true;
        this.setRotation(this.larm1, -1.0f, 1.0f, 0.0f);
        this.blade = new ModelRenderer(this, 36, 304);
        this.blade.addBox(0.0f, -34.0f, -2.0f, 1, 32, 6);
        this.blade.setPos(-4.0f, -2.0f, -8.0f);
        this.blade.mirror = true;
        this.setRotation(this.blade, 0.35f, 0.0f, 0.0f);
        this.handle = new ModelRenderer(this, 18, 26);
        this.handle.addBox(0.0f, -2.0f, 0.0f, 1, 4, 1);
        this.handle.setPos(-4.0f, -2.0f, -8.0f);
        this.handle.mirror = true;
        this.setRotation(this.handle, 0.35f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(EnderKnight entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        EnderKnight e = (EnderKnight)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.lfoot1.xRot = newangle;
        this.lfoot2.xRot = 0.6f + newangle;
        this.lfoot3.xRot = newangle;
        this.lfoot4.xRot = newangle;
        this.lleg1.xRot = newangle;
        this.lleg2.xRot = -0.1f + newangle;
        this.lleg3.xRot = -0.1f + newangle;
        this.rfoot1.xRot = - newangle;
        this.rfoot2.xRot = 0.6f - newangle;
        this.rfoot3.xRot = - newangle;
        this.rfoot4.xRot = - newangle;
        this.rleg1.xRot = - newangle;
        this.rleg2.xRot = -0.1f - newangle;
        this.rleg3.xRot = -0.1f - newangle;
        this.cape2.zRot = newangle / 4.0f;
        this.cape2.xRot = newangle = MathHelper.cos((float)(f2 * 0.7f * this.wingspeed)) * 3.1415927f * 0.02f;
        this.head.yRot = (float)Math.toRadians(f3) * 0.45f;
        if (this.head.yRot > 0.45f) {
            this.head.yRot = 0.45f;
        }
        if (this.head.yRot < -0.45f) {
            this.head.yRot = -0.45f;
        }
        newangle = MathHelper.cos((float)(f2 * 2.7f * this.wingspeed)) * 3.1415927f * 0.3f;
        if (e.isScreaming()) {
            this.larm2.xRot = -1.2f + newangle;
            this.larm3.xRot = -1.2f + newangle;
            this.rarm2.xRot = -1.2f + newangle;
            this.rarm3.xRot = -1.2f + newangle;
            this.larm1.xRot = -1.8f + newangle;
            this.rarm1.xRot = -1.8f + newangle;
            this.blade.xRot = this.handle.xRot = 0.5f + newangle * 3.0f / 2.0f;
        } else {
            this.larm2.xRot = -0.5f;
            this.larm3.xRot = -0.5f;
            this.larm1.zRot = 0.0f;
            this.larm1.yRot = 1.0f;
            this.larm1.xRot = -1.0f;
            this.rarm2.xRot = -0.5f;
            this.rarm3.xRot = -0.5f;
            this.rarm1.zRot = 0.0f;
            this.rarm1.yRot = -1.0f;
            this.rarm1.xRot = -1.0f;
            this.handle.xRot = 0.35f;
            this.blade.xRot = 0.35f;
        }
        this.larm1.y = (float)((double)this.larm2.y + Math.cos(this.larm2.xRot) * 10.0);
        this.larm1.z = (float)((double)this.larm2.z + Math.sin(this.larm2.xRot) * 10.0);
        this.rarm1.y = (float)((double)this.rarm2.y + Math.cos(this.rarm2.xRot) * 10.0);
        this.rarm1.z = (float)((double)this.rarm2.z + Math.sin(this.rarm2.xRot) * 10.0);
        this.blade.y = this.handle.y = (float)((double)this.rarm1.y + Math.cos(this.rarm1.xRot) * 7.0) + 1.0f;
        this.blade.z = this.handle.z = (float)((double)this.rarm1.z + Math.sin(this.rarm1.xRot) * 7.0);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pelvis.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rhip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rcollar.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lcollar.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lhip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.blade.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.handle.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

