/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelScorpion
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Scorpion
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
import com.astryxion.chaospersists.entity.Scorpion;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelScorpion extends EntityModel<Scorpion> {
    private float wingspeed = 1.0f;
    ModelRenderer body;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tail5;
    ModelRenderer tail6;
    ModelRenderer lleg1;
    ModelRenderer rleg1;
    ModelRenderer rleg2;
    ModelRenderer lleg3;
    ModelRenderer rleg4;
    ModelRenderer rleg3;
    ModelRenderer lleg4;
    ModelRenderer lleg2;
    ModelRenderer head;
    ModelRenderer larm2;
    ModelRenderer rarm2;
    ModelRenderer larm1;
    ModelRenderer rarm1;
    ModelRenderer lclaw;
    ModelRenderer rclaw;

    public ModelScorpion(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 88;
        // textureHeight = 24;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 6, 4, 8);
        this.body.setPos(-3.0f, 17.0f, -4.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 28, 0);
        this.tail1.addBox(0.0f, 0.0f, 0.0f, 4, 4, 5);
        this.tail1.setPos(-2.0f, 17.0f, 3.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.2617994f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 46, 0);
        this.tail2.addBox(0.0f, 0.0f, 0.0f, 3, 3, 5);
        this.tail2.setPos(-1.5f, 16.8f, 6.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 1.029744f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 62, 0);
        this.tail3.addBox(0.0f, 0.0f, 0.0f, 3, 3, 4);
        this.tail3.setPos(-1.5f, 14.5f, 8.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 1.727876f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 0, 17);
        this.tail4.addBox(0.0f, 0.0f, 0.0f, 2, 2, 5);
        this.tail4.setPos(-1.0f, 12.0f, 9.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, 2.513274f, 0.0f, 0.0f);
        this.tail5 = new ModelRenderer(this, 70, 7);
        this.tail5.addBox(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.tail5.setPos(-1.0f, 9.0f, 6.0f);
        this.tail5.mirror = true;
        this.setRotation(this.tail5, 3.141593f, 0.0f, 0.0f);
        this.tail6 = new ModelRenderer(this, 62, 7);
        this.tail6.addBox(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.tail6.setPos(-0.5f, 8.0f, 2.0f);
        this.tail6.mirror = true;
        this.setRotation(this.tail6, 3.141593f, 0.0f, 0.0f);
        this.lleg1 = new ModelRenderer(this, 0, 12);
        this.lleg1.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.lleg1.setPos(2.0f, 18.0f, -3.0f);
        this.lleg1.mirror = true;
        this.setRotation(this.lleg1, 0.0f, 0.4886922f, 0.3665191f);
        this.rleg1 = new ModelRenderer(this, 0, 12);
        this.rleg1.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.rleg1.setPos(-2.0f, 18.0f, -1.0f);
        this.rleg1.mirror = true;
        this.setRotation(this.rleg1, 0.0f, 2.6529f, -0.3665191f);
        this.rleg2 = new ModelRenderer(this, 0, 12);
        this.rleg2.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.rleg2.setPos(-2.0f, 18.0f, 1.0f);
        this.rleg2.mirror = true;
        this.setRotation(this.rleg2, 0.0f, 2.897247f, -0.3665191f);
        this.lleg3 = new ModelRenderer(this, 0, 12);
        this.lleg3.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.lleg3.setPos(2.0f, 18.0f, 1.0f);
        this.lleg3.mirror = true;
        this.setRotation(this.lleg3, 0.0f, -0.2443461f, 0.3665191f);
        this.rleg4 = new ModelRenderer(this, 0, 12);
        this.rleg4.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.rleg4.setPos(-2.0f, 18.0f, 5.0f);
        this.rleg4.mirror = true;
        this.setRotation(this.rleg4, 0.0f, -2.6529f, -0.3665191f);
        this.rleg3 = new ModelRenderer(this, 0, 12);
        this.rleg3.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.rleg3.setPos(-2.0f, 18.0f, 3.0f);
        this.rleg3.mirror = true;
        this.setRotation(this.rleg3, 0.0f, -2.897247f, -0.3665191f);
        this.lleg4 = new ModelRenderer(this, 0, 12);
        this.lleg4.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.lleg4.setPos(2.0f, 18.0f, 3.0f);
        this.lleg4.mirror = true;
        this.setRotation(this.lleg4, 0.0f, -0.4886922f, 0.3665191f);
        this.lleg2 = new ModelRenderer(this, 0, 12);
        this.lleg2.addBox(0.0f, 0.0f, 0.0f, 11, 2, 2);
        this.lleg2.setPos(2.0f, 18.0f, -1.0f);
        this.lleg2.mirror = true;
        this.setRotation(this.lleg2, 0.0f, 0.2443461f, 0.3665191f);
        this.head = new ModelRenderer(this, 28, 9);
        this.head.addBox(0.0f, 0.0f, 0.0f, 5, 3, 4);
        this.head.setPos(-2.5f, 17.5f, -8.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.larm2 = new ModelRenderer(this, 46, 8);
        this.larm2.addBox(0.0f, 0.0f, 0.0f, 6, 2, 2);
        this.larm2.setPos(1.0f, 18.0f, -6.0f);
        this.larm2.mirror = true;
        this.setRotation(this.larm2, 0.0f, 0.5235988f, 0.1745329f);
        this.rarm2 = new ModelRenderer(this, 46, 8);
        this.rarm2.addBox(0.0f, 0.0f, -2.0f, 6, 2, 2);
        this.rarm2.setPos(-1.0f, 18.0f, -6.0f);
        this.rarm2.mirror = true;
        this.setRotation(this.rarm2, 0.0f, 2.617994f, -0.1745329f);
        this.larm1 = new ModelRenderer(this, 70, 13);
        this.larm1.addBox(-2.0f, 0.0f, -3.0f, 2, 2, 3);
        this.larm1.setPos(7.0f, 19.0f, -7.2f);
        this.larm1.mirror = true;
        this.setRotation(this.larm1, 0.1745329f, 0.1745329f, 0.0f);
        this.rarm1 = new ModelRenderer(this, 70, 13);
        this.rarm1.addBox(0.0f, 0.0f, -3.0f, 2, 2, 3);
        this.rarm1.setPos(-7.0f, 19.0f, -7.2f);
        this.rarm1.mirror = true;
        this.setRotation(this.rarm1, 0.1745329f, -0.1745329f, 0.0f);
        this.lclaw = new ModelRenderer(this, 46, 12);
        this.lclaw.addBox(-3.0f, 0.0f, -4.0f, 3, 2, 4);
        this.lclaw.setPos(7.0f, 19.0f, -10.0f);
        this.lclaw.mirror = true;
        this.setRotation(this.lclaw, 0.0174533f, 0.3839724f, 0.1396263f);
        this.rclaw = new ModelRenderer(this, 46, 12);
        this.rclaw.addBox(0.0f, 0.0f, -4.0f, 3, 2, 4);
        this.rclaw.setPos(-7.0f, 19.0f, -10.0f);
        this.rclaw.mirror = true;
        this.setRotation(this.rclaw, 0.0174533f, -0.3839724f, 0.1396263f);
    }
    @Override
    public void setupAnim(Scorpion entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Scorpion e = (Scorpion)entity;
        RenderInfo r = null;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float pi4 = 1.570795f;
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * f1;
        this.lleg1.yRot = newangle + 0.49f;
        this.rleg1.yRot = - newangle + 2.65f;
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 1.0f * pi4)) * 3.1415927f * 0.12f * f1;
        this.lleg2.yRot = newangle + 0.24f;
        this.rleg2.yRot = - newangle + 2.9f;
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.12f * f1;
        this.lleg3.yRot = newangle - 0.24f;
        this.rleg3.yRot = - newangle - 2.9f;
        newangle = MathHelper.cos((float)(f2 * 2.0f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.12f * f1;
        this.lleg4.yRot = newangle - 0.49f;
        this.rleg4.yRot = - newangle - 2.65f;
        r = e.getRenderInfo();
        newangle = MathHelper.cos((float)(f2 * 3.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = MathHelper.cos((float)((f2 + 0.1f) * 3.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (e.getAttacking() == 0) {
                r.ri1 = e.level.random.nextInt(20);
                r.ri2 = e.level.random.nextInt(25);
            } else {
                r.ri1 = e.level.random.nextInt(4);
                r.ri2 = e.level.random.nextInt(3);
            }
        }
        if (r.ri1 == 1 || r.ri1 == 3) {
            this.doLeftClaw(newangle);
        } else {
            this.doLeftClaw(0.0f);
        }
        if (r.ri1 == 2 || r.ri1 == 3) {
            this.doRightClaw(newangle);
        } else {
            this.doRightClaw(0.0f);
        }
        if (r.ri2 == 1) {
            this.doTail(newangle);
        } else {
            this.doTail(0.0f);
        }
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }

    private void doLeftClaw(float angle) {
        this.larm2.yRot = 0.52f + angle;
        this.larm1.z = (float)((double)this.larm2.z - Math.sin(this.larm2.yRot) * 4.5);
        this.lclaw.z = this.larm1.z - 3.0f;
        this.lclaw.yRot = 0.381f - angle;
    }

    private void doRightClaw(float angle) {
        this.rarm2.yRot = 2.61f - angle;
        this.rarm1.z = (float)((double)this.rarm2.z - Math.sin(this.rarm2.yRot) * 4.5);
        this.rclaw.z = this.rarm1.z - 3.0f;
        this.rclaw.yRot = -0.381f + angle;
    }

    private void doTail(float angle) {
        this.tail1.xRot = 0.26f + angle;
        this.tail2.xRot = this.tail1.xRot + 0.76900005f + angle;
        this.tail2.y = (float)((double)this.tail1.y - Math.sin(this.tail1.xRot) * 4.0);
        this.tail2.z = (float)((double)this.tail1.z + Math.cos(this.tail1.xRot) * 4.0);
        this.tail3.xRot = this.tail2.xRot + 0.701f + angle;
        this.tail3.y = (float)((double)this.tail2.y - Math.sin(this.tail2.xRot) * 4.0);
        this.tail3.z = (float)((double)this.tail2.z + Math.cos(this.tail2.xRot) * 4.0);
        this.tail4.xRot = this.tail3.xRot + -5.501f - angle * 3.0f / 2.0f - 0.4f;
        this.tail4.y = (float)((double)this.tail3.y - Math.sin(this.tail3.xRot) * 3.0);
        this.tail4.z = (float)((double)this.tail3.z + Math.cos(this.tail3.xRot) * 3.0);
        this.tail5.y = (float)((double)this.tail4.y - Math.sin(this.tail4.xRot) * 4.0);
        this.tail5.z = (float)((double)this.tail4.z + Math.cos(this.tail4.xRot) * 4.0);
        this.tail6.y = (float)((double)this.tail5.y - Math.sin(this.tail5.xRot) * 4.0);
        this.tail6.z = (float)((double)this.tail5.z + Math.cos(this.tail5.xRot) * 4.0);
    }
}

