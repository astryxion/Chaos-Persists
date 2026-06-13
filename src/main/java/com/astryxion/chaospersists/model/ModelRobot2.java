/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRobot2
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Robot2
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Robot2;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelRobot2 extends EntityModel<Robot2> {
    private float wingspeed = 1.0f;
    ModelRenderer rleg1;
    ModelRenderer rleg2;
    ModelRenderer Shape3;
    ModelRenderer lleg2;
    ModelRenderer lleg1;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer rarm3;
    ModelRenderer rarm2;
    ModelRenderer rarm1;
    ModelRenderer larm3;
    ModelRenderer larm2;
    ModelRenderer larm1;
    ModelRenderer head;

    public ModelRobot2(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 512;
        this.rleg1 = new ModelRenderer(this, 10, 250);
        this.rleg1.addBox(-14.0f, 24.0f, -7.0f, 16, 24, 16);
        this.rleg1.setPos(-10.0f, -24.0f, 0.0f);
        this.rleg1.mirror = true;
        this.setRotation(this.rleg1, 0.0f, 0.0f, 0.0f);
        this.rleg2 = new ModelRenderer(this, 10, 150);
        this.rleg2.addBox(-12.0f, 0.0f, -6.0f, 12, 24, 12);
        this.rleg2.setPos(-10.0f, -24.0f, 1.0f);
        this.rleg2.mirror = true;
        this.setRotation(this.rleg2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 10, 50);
        this.Shape3.addBox(-4.0f, 0.0f, -2.0f, 26, 8, 12);
        this.Shape3.setPos(-9.0f, -32.0f, -3.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.lleg2 = new ModelRenderer(this, 10, 200);
        this.lleg2.addBox(0.0f, 0.0f, -6.0f, 12, 24, 12);
        this.lleg2.setPos(10.0f, -24.0f, 1.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, 0.0f, 0.0f, 0.0f);
        this.lleg1 = new ModelRenderer(this, 10, 300);
        this.lleg1.addBox(-2.0f, 24.0f, -7.0f, 16, 24, 16);
        this.lleg1.setPos(10.0f, -24.0f, 0.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer(this, 10, 100);
        this.Shape6.addBox(-4.0f, -8.0f, -3.0f, 8, 8, 8);
        this.Shape6.setPos(0.0f, -32.0f, 0.0f);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer(this, 10, 350);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 26, 8, 12);
        this.Shape7.setPos(-13.0f, -48.0f, -5.0f);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer(this, 16, 400);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 44, 18, 14);
        this.Shape8.setPos(-22.0f, -66.0f, -6.0f);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.rarm3 = new ModelRenderer(this, 100, 100);
        this.rarm3.addBox(-16.0f, -16.0f, -7.0f, 16, 24, 17);
        this.rarm3.setPos(-22.0f, -58.0f, 0.0f);
        this.rarm3.mirror = true;
        this.setRotation(this.rarm3, 0.0f, 0.0f, 0.0f);
        this.rarm2 = new ModelRenderer(this, 100, 200);
        this.rarm2.addBox(-14.0f, 8.0f, -5.0f, 12, 24, 12);
        this.rarm2.setPos(-22.0f, -58.0f, 0.0f);
        this.rarm2.mirror = true;
        this.setRotation(this.rarm2, 0.0f, 0.0f, 0.0f);
        this.rarm1 = new ModelRenderer(this, 100, 300);
        this.rarm1.addBox(-14.0f, 32.0f, -5.0f, 12, 24, 12);
        this.rarm1.setPos(-22.0f, -58.0f, 0.0f);
        this.rarm1.mirror = true;
        this.setRotation(this.rarm1, 0.0f, 0.0f, 0.0f);
        this.larm3 = new ModelRenderer(this, 100, 50);
        this.larm3.addBox(0.0f, -16.0f, -7.0f, 16, 24, 17);
        this.larm3.setPos(22.0f, -58.0f, 0.0f);
        this.larm3.mirror = true;
        this.setRotation(this.larm3, 0.0f, 0.0f, 0.0f);
        this.larm2 = new ModelRenderer(this, 100, 150);
        this.larm2.addBox(2.0f, 8.0f, -5.0f, 12, 24, 12);
        this.larm2.setPos(21.0f, -58.0f, 0.0f);
        this.larm2.mirror = true;
        this.setRotation(this.larm2, 0.0f, 0.0f, 0.0f);
        this.larm1 = new ModelRenderer(this, 100, 250);
        this.larm1.addBox(2.0f, 32.0f, -5.0f, 12, 24, 12);
        this.larm1.setPos(21.0f, -58.0f, 0.0f);
        this.larm1.mirror = true;
        this.setRotation(this.larm1, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 50, 10);
        this.head.addBox(-7.0f, -12.0f, -5.0f, 15, 12, 10);
        this.head.setPos(0.0f, -66.0f, 1.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
    }
    public void setupAnim(Robot2 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Robot2 e = (Robot2)entity;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.12f * f1 : 0.0f;
        this.lleg1.xRot = newangle;
        this.lleg2.xRot = newangle;
        this.rleg1.xRot = - newangle;
        this.rleg2.xRot = - newangle;
        this.head.yRot = (float)Math.toRadians(f3);
        newangle = MathHelper.sin((float)((float)Math.toRadians(f2 * 20.0f * this.wingspeed)));
        float nextangle = MathHelper.sin((float)((float)Math.toRadians(f2 * 20.0f * this.wingspeed + 1.5f)));
        r = e.getRenderInfo();
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (e.getAttacking() == 0) {
                r.ri1 = 0;
            } else {
                while (r.ri1 == 0) {
                    r.ri1 = e.level.random.nextInt(4);
                }
            }
        }
        newangle = (float)Math.toRadians(f2 * 20.0f * this.wingspeed);
        if (r.ri1 == 1 || r.ri1 == 3) {
            this.rarm1.xRot = newangle;
            this.rarm2.xRot = newangle;
            this.rarm3.xRot = newangle;
        } else {
            this.rarm1.xRot = 0.0f;
            this.rarm2.xRot = 0.0f;
            this.rarm3.xRot = 0.0f;
        }
        if (r.ri1 == 2 || r.ri1 == 3) {
            this.larm1.xRot = newangle;
            this.larm2.xRot = newangle;
            this.larm3.xRot = newangle;
        } else {
            this.larm1.xRot = 0.0f;
            this.larm2.xRot = 0.0f;
            this.larm3.xRot = 0.0f;
        }
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Robot2 par7Entity) {
        
    }
}

