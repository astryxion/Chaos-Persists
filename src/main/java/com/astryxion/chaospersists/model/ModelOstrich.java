/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.ModelOstrich
 *  com.astryxion.chaospersists.Ostrich
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.EntityCannonFodder;
import com.astryxion.chaospersists.entity.Ostrich;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelOstrich extends EntityModel<Ostrich> {
    private float wingspeed = 1.0f;
    ModelRenderer Body1;
    ModelRenderer body2;
    ModelRenderer LLeg1;
    ModelRenderer Rleg1;
    ModelRenderer LLeg2;
    ModelRenderer Lfoot1;
    ModelRenderer RLeg2;
    ModelRenderer Lfoot2;
    ModelRenderer Lfoot3;
    ModelRenderer LClaw1;
    ModelRenderer LClaw2;
    ModelRenderer LClaw3;
    ModelRenderer Lfoot4;
    ModelRenderer LClaw4;
    ModelRenderer Rfoot1;
    ModelRenderer Rfoot2;
    ModelRenderer Rclaw1;
    ModelRenderer Rfoot3;
    ModelRenderer Rclaw3;
    ModelRenderer Rfoot4;
    ModelRenderer Rclaw2;
    ModelRenderer Rclaw4;
    ModelRenderer Body3;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Body4;
    ModelRenderer head;
    ModelRenderer leftleg;
    ModelRenderer Neck1;
    ModelRenderer Head1;
    ModelRenderer mouth1;
    ModelRenderer neck2;
    ModelRenderer rightleg;
    ModelRenderer Lwing;
    ModelRenderer Rwing;
    ModelRenderer Hat1;
    ModelRenderer Hat2;

    public ModelOstrich(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 128;
        this.Body1 = new ModelRenderer(this, 0, 28);
        this.Body1.addBox(-4.0f, 0.0f, 0.0f, 8, 9, 8);
        this.Body1.setPos(0.0f, 0.0f, -6.0f);
        this.Body1.mirror = true;
        this.setRotation(this.Body1, -0.2230717f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 25, 111);
        this.body2.addBox(-4.0f, 0.0f, 0.0f, 8, 8, 8);
        this.body2.setPos(0.0f, 2.0f, -1.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.LLeg1 = new ModelRenderer(this, 25, 70);
        this.LLeg1.addBox(-1.0f, 3.0f, -5.0f, 2, 7, 3);
        this.LLeg1.setPos(3.0f, 8.0f, 1.0f);
        this.LLeg1.mirror = true;
        this.setRotation(this.LLeg1, 0.4833219f, 0.0f, 0.0f);
        this.Rleg1 = new ModelRenderer(this, 25, 70);
        this.Rleg1.addBox(-2.0f, 3.0f, -5.0f, 2, 7, 3);
        this.Rleg1.setPos(-2.0f, 8.0f, 1.0f);
        this.Rleg1.mirror = true;
        this.setRotation(this.Rleg1, 0.4833219f, 0.0f, 0.0f);
        this.LLeg2 = new ModelRenderer(this, 29, 59);
        this.LLeg2.addBox(-1.0f, 7.0f, 4.0f, 2, 7, 3);
        this.LLeg2.setPos(3.0f, 8.0f, 1.0f);
        this.LLeg2.mirror = true;
        this.setRotation(this.LLeg2, -0.4370552f, 0.0f, 0.0f);
        this.Lfoot1 = new ModelRenderer(this, 29, 50);
        this.Lfoot1.addBox(-1.0f, 14.0f, -5.0f, 2, 2, 6);
        this.Lfoot1.setPos(3.0f, 8.0f, 1.0f);
        this.Lfoot1.mirror = true;
        this.setRotation(this.Lfoot1, 0.0f, 0.0f, 0.0f);
        this.RLeg2 = new ModelRenderer(this, 29, 59);
        this.RLeg2.addBox(-2.0f, 7.0f, 4.0f, 2, 7, 3);
        this.RLeg2.setPos(-2.0f, 8.0f, 1.0f);
        this.RLeg2.mirror = true;
        this.setRotation(this.RLeg2, -0.4370552f, 0.0f, 0.0f);
        this.Lfoot2 = new ModelRenderer(this, 0, 9);
        this.Lfoot2.addBox(-1.0f, 15.0f, -4.0f, 2, 1, 5);
        this.Lfoot2.setPos(3.0f, 8.0f, 1.0f);
        this.Lfoot2.mirror = true;
        this.setRotation(this.Lfoot2, 0.0f, 0.2602503f, 0.0f);
        this.Lfoot3 = new ModelRenderer(this, 0, 9);
        this.Lfoot3.addBox(-1.0f, 15.0f, -4.0f, 2, 1, 5);
        this.Lfoot3.setPos(3.0f, 8.0f, 1.0f);
        this.Lfoot3.mirror = true;
        this.setRotation(this.Lfoot3, 0.0f, -0.260246f, 0.0f);
        this.LClaw1 = new ModelRenderer(this, 16, 10);
        this.LClaw1.addBox(0.0f, 14.0f, -7.0f, 0, 2, 3);
        this.LClaw1.setPos(3.0f, 8.0f, 1.0f);
        this.LClaw1.mirror = true;
        this.setRotation(this.LClaw1, 0.0f, 0.0f, 0.0f);
        this.LClaw2 = new ModelRenderer(this, 19, 16);
        this.LClaw2.addBox(-0.5f, 15.0f, -5.0f, 0, 1, 3);
        this.LClaw2.setPos(3.0f, 8.0f, 1.0f);
        this.LClaw2.mirror = true;
        this.setRotation(this.LClaw2, 0.0f, 0.260246f, 0.0f);
        this.LClaw3 = new ModelRenderer(this, 19, 16);
        this.LClaw3.addBox(0.5f, 15.0f, -5.0f, 0, 1, 3);
        this.LClaw3.setPos(3.0f, 8.0f, 1.0f);
        this.LClaw3.mirror = true;
        this.setRotation(this.LClaw3, 0.0f, -0.260246f, 0.0f);
        this.Lfoot4 = new ModelRenderer(this, 0, 0);
        this.Lfoot4.addBox(-1.0f, 14.0f, -1.0f, 2, 2, 4);
        this.Lfoot4.setPos(3.0f, 8.0f, 1.0f);
        this.Lfoot4.mirror = true;
        this.setRotation(this.Lfoot4, 0.0f, 0.0f, 0.0f);
        this.LClaw4 = new ModelRenderer(this, 16, 10);
        this.LClaw4.addBox(0.0f, 14.0f, 2.0f, 0, 2, 3);
        this.LClaw4.setPos(3.0f, 8.0f, 1.0f);
        this.LClaw4.mirror = true;
        this.setRotation(this.LClaw4, 0.0f, 0.0f, 0.0f);
        this.Rfoot1 = new ModelRenderer(this, 29, 50);
        this.Rfoot1.addBox(-2.0f, 14.0f, -5.0f, 2, 2, 6);
        this.Rfoot1.setPos(-2.0f, 8.0f, 1.0f);
        this.Rfoot1.mirror = true;
        this.setRotation(this.Rfoot1, 0.0f, 0.0f, 0.0f);
        this.Rfoot2 = new ModelRenderer(this, 0, 0);
        this.Rfoot2.addBox(-2.0f, 14.0f, -1.0f, 2, 2, 4);
        this.Rfoot2.setPos(-2.0f, 8.0f, 1.0f);
        this.Rfoot2.mirror = true;
        this.setRotation(this.Rfoot2, 0.0f, 0.0f, 0.0f);
        this.Rclaw1 = new ModelRenderer(this, 16, 10);
        this.Rclaw1.addBox(-1.0f, 14.0f, -7.0f, 0, 2, 3);
        this.Rclaw1.setPos(-2.0f, 8.0f, 1.0f);
        this.Rclaw1.mirror = true;
        this.setRotation(this.Rclaw1, 0.0f, 0.0f, 0.0f);
        this.Rfoot3 = new ModelRenderer(this, 0, 9);
        this.Rfoot3.addBox(-2.0f, 15.0f, -4.0f, 2, 1, 5);
        this.Rfoot3.setPos(-2.0f, 8.0f, 1.0f);
        this.Rfoot3.mirror = true;
        this.setRotation(this.Rfoot3, 0.0f, -0.260246f, 0.0f);
        this.Rclaw3 = new ModelRenderer(this, 19, 16);
        this.Rclaw3.addBox(-0.5f, 15.0f, -5.0f, 0, 1, 3);
        this.Rclaw3.setPos(-2.0f, 8.0f, 1.0f);
        this.Rclaw3.mirror = true;
        this.setRotation(this.Rclaw3, 0.0f, -0.260246f, 0.0f);
        this.Rfoot4 = new ModelRenderer(this, 0, 9);
        this.Rfoot4.addBox(-2.0f, 15.0f, -4.0f, 2, 1, 5);
        this.Rfoot4.setPos(-2.0f, 8.0f, 1.0f);
        this.Rfoot4.mirror = true;
        this.setRotation(this.Rfoot4, 0.0f, 0.2602503f, 0.0f);
        this.Rclaw2 = new ModelRenderer(this, 19, 16);
        this.Rclaw2.addBox(-1.5f, 15.0f, -5.0f, 0, 1, 3);
        this.Rclaw2.setPos(-2.0f, 8.0f, 1.0f);
        this.Rclaw2.mirror = true;
        this.setRotation(this.Rclaw2, 0.0f, 0.260246f, 0.0f);
        this.Rclaw4 = new ModelRenderer(this, 16, 10);
        this.Rclaw4.addBox(-1.0f, 14.0f, 2.0f, 0, 2, 3);
        this.Rclaw4.setPos(-2.0f, 8.0f, 1.0f);
        this.Rclaw4.mirror = true;
        this.setRotation(this.Rclaw4, 0.0f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer(this, 17, 96);
        this.Body3.addBox(-3.0f, 0.0f, 0.0f, 6, 7, 3);
        this.Body3.setPos(0.0f, 2.0f, 6.0f);
        this.Body3.mirror = true;
        this.setRotation(this.Body3, 0.0f, 0.0f, 0.0f);
        this.Tail1 = new ModelRenderer(this, 33, 81);
        this.Tail1.addBox(-2.0f, 0.0f, 0.0f, 4, 0, 14);
        this.Tail1.setPos(0.0f, 3.0f, 9.0f);
        this.Tail1.mirror = true;
        this.setRotation(this.Tail1, -0.5948578f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 36, 97);
        this.Tail2.addBox(-1.0f, 0.0f, 0.0f, 3, 0, 13);
        this.Tail2.setPos(0.0f, 3.0f, 8.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, -0.5948578f, 0.3346075f, 0.0f);
        this.Tail3 = new ModelRenderer(this, 36, 97);
        this.Tail3.addBox(-2.0f, 0.0f, 0.0f, 3, 0, 13);
        this.Tail3.setPos(0.0f, 3.0f, 8.0f);
        this.Tail3.mirror = true;
        this.setRotation(this.Tail3, -0.5948578f, -0.3346145f, 0.0f);
        this.Body4 = new ModelRenderer(this, 17, 89);
        this.Body4.addBox(-2.0f, 0.0f, 0.0f, 4, 3, 3);
        this.Body4.setPos(0.0f, 6.0f, 7.0f);
        this.Body4.mirror = true;
        this.setRotation(this.Body4, 1.003822f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 74, 48);
        this.head.addBox(-1.0f, -24.0f, -7.0f, 2, 2, 4);
        this.head.setPos(0.0f, 5.0f, -7.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 5);
        this.leftleg.setPos(3.0f, 8.0f, 1.0f);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, -0.2974289f, 0.0f, 0.0f);
        this.Neck1 = new ModelRenderer(this, 79, 84);
        this.Neck1.addBox(-1.5f, -21.0f, -2.0f, 3, 21, 3);
        this.Neck1.setPos(0.0f, 5.0f, -7.0f);
        this.Neck1.mirror = true;
        this.setRotation(this.Neck1, 0.0f, -0.0349066f, 0.0f);
        this.Head1 = new ModelRenderer(this, 0, 70);
        this.Head1.addBox(-2.0f, -25.0f, -3.0f, 4, 4, 4);
        this.Head1.setPos(0.0f, 5.0f, -7.0f);
        this.Head1.mirror = true;
        this.setRotation(this.Head1, 0.0f, 0.0f, 0.0f);
        this.mouth1 = new ModelRenderer(this, 74, 64);
        this.mouth1.addBox(-1.0f, -22.0f, -6.0f, 2, 1, 3);
        this.mouth1.setPos(0.0f, 5.0f, -7.0f);
        this.mouth1.mirror = true;
        this.setRotation(this.mouth1, 0.0f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 0, 99);
        this.neck2.addBox(-1.0f, -2.0f, -2.0f, 2, 4, 3);
        this.neck2.setPos(0.0f, 5.0f, -6.9f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.addBox(-3.0f, 0.0f, -2.0f, 4, 6, 5);
        this.rightleg.setPos(-2.0f, 8.0f, 1.0f);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, -0.2974216f, 0.0f, 0.0f);
        this.Lwing = new ModelRenderer(this, 0, 107);
        this.Lwing.addBox(0.0f, 0.0f, 0.0f, 1, 7, 11);
        this.Lwing.setPos(4.0f, 1.0f, -5.0f);
        this.Lwing.mirror = true;
        this.setRotation(this.Lwing, 0.0f, 0.0f, 0.0f);
        this.Rwing = new ModelRenderer(this, 0, 107);
        this.Rwing.addBox(0.0f, 0.0f, 0.0f, 1, 7, 11);
        this.Rwing.setPos(-5.0f, 1.0f, -5.0f);
        this.Rwing.mirror = true;
        this.setRotation(this.Rwing, 0.0f, 0.0f, 0.0f);
        this.Hat1 = new ModelRenderer(this, 40, 0);
        this.Hat1.addBox(-2.5f, -26.0f, -4.0f, 5, 1, 5);
        this.Hat1.setPos(0.0f, 5.0f, -7.0f);
        this.Hat1.mirror = true;
        this.setRotation(this.Hat1, 0.0f, 0.0f, 0.0f);
        this.Hat2 = new ModelRenderer(this, 40, 0);
        this.Hat2.addBox(-2.0f, -28.0f, -3.0f, 4, 2, 4);
        this.Hat2.setPos(0.0f, 5.0f, -7.0f);
        this.Hat2.mirror = true;
        this.setRotation(this.Hat2, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Ostrich entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Ostrich o = (Ostrich)entity;
        RenderInfo r = null;
        float hf = 0.0f;
        float newangle = 0.0f;
        float nextangle = 0.0f;
        float lspeed = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        lspeed = (float)((o.xo - o.getX()) * (o.xo - o.getX()) + (o.zo - o.getZ()) * (o.zo - o.getZ()));
        lspeed = (float)Math.sqrt(lspeed);
        newangle = MathHelper.cos((float)(f2 * 1.25f * this.wingspeed)) * 3.1415927f * lspeed * 0.4f;
        if ((double)newangle > 0.5) {
            newangle = 0.75f;
        }
        if ((double)newangle < -0.5) {
            newangle = -0.75f;
        }
        this.leftleg.xRot = -0.297f + newangle;
        this.LLeg1.xRot = 0.483f + newangle;
        this.LLeg2.xRot = -0.437f + newangle;
        this.Lfoot1.xRot = newangle;
        this.Lfoot2.xRot = newangle;
        this.Lfoot3.xRot = newangle;
        this.Lfoot4.xRot = newangle;
        this.LClaw1.xRot = newangle;
        this.LClaw2.xRot = newangle;
        this.LClaw3.xRot = newangle;
        this.LClaw4.xRot = newangle;
        this.rightleg.xRot = -0.297f - newangle;
        this.Rleg1.xRot = 0.483f - newangle;
        this.RLeg2.xRot = -0.437f - newangle;
        this.Rfoot1.xRot = - newangle;
        this.Rfoot2.xRot = - newangle;
        this.Rfoot3.xRot = - newangle;
        this.Rfoot4.xRot = - newangle;
        this.Rclaw1.xRot = - newangle;
        this.Rclaw2.xRot = - newangle;
        this.Rclaw3.xRot = - newangle;
        this.Rclaw4.xRot = - newangle;
        this.Tail2.xRot = this.Tail1.xRot = -0.594f + MathHelper.cos((float)(f2 * 0.05f)) * 3.1415927f * 0.06f;
        this.Tail3.xRot = this.Tail1.xRot;
        this.Tail3.yRot = -0.334f + MathHelper.cos((float)(f2 * 0.061f)) * 3.1415927f * 0.08f;
        this.Tail2.yRot = 0.334f - MathHelper.cos((float)(f2 * 0.072f)) * 3.1415927f * 0.08f;
        r = o.getRenderInfo();
        if (!o.getPassengers().isEmpty()) {
            f3 = (o.yRotO - o.yRot) * 20.0f;
            f3 = - f3;
            r.rf1 += (f3 - r.rf1) / 60.0f;
            if (r.rf1 > 50.0f) {
                r.rf1 = 50.0f;
            }
            if (r.rf1 < -50.0f) {
                r.rf1 = -50.0f;
            }
            f3 = r.rf1;
        } else {
            f3 /= 2.0f;
        }
        if (o.isOrderedToSit() && o.get_is_activated() == 0) {
            f3 = 0.0f;
            this.head.xRot = this.Head1.xRot = 3.1415f;
            this.mouth1.xRot = this.Head1.xRot;
            this.Neck1.xRot = this.Head1.xRot;
            this.Hat1.xRot = this.Head1.xRot;
            this.Hat2.xRot = this.Head1.xRot;
        } else {
            this.head.xRot = this.Head1.xRot = 0.0f;
            this.mouth1.xRot = this.Head1.xRot;
            this.Neck1.xRot = this.Head1.xRot;
            this.Hat1.xRot = this.Head1.xRot;
            this.Hat2.xRot = this.Head1.xRot;
        }
        this.head.yRot = this.Head1.yRot = (float)Math.toRadians(f3) * 0.65f;
        this.mouth1.yRot = this.Head1.yRot;
        this.Hat1.yRot = this.Head1.yRot;
        this.Hat2.yRot = this.Head1.yRot;
        newangle = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = MathHelper.cos((float)((f2 + 0.3f) * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (o.level.random.nextInt(3) == 1) {
                r.ri1 = 1;
            }
        }
        if (r.ri1 == 0) {
            newangle = 0.0f;
        }
        newangle = Math.abs(newangle);
        this.Lwing.zRot = - newangle;
        this.Lwing.yRot = newangle / 2.0f;
        this.Rwing.zRot = newangle;
        this.Rwing.yRot = (- newangle) / 2.0f;
        o.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (o instanceof EntityCannonFodder && o.get_is_activated() != 0) {
            
            if (o.get_is_activated() > 1) {
                
            }
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RLeg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lfoot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lfoot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LClaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LClaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LClaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lfoot4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LClaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rfoot2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rfoot3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rfoot4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Hat2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity) {
        
    }
}

