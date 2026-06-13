/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRubberDucky
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.RubberDucky
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
import com.astryxion.chaospersists.entity.RubberDucky;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelRubberDucky extends EntityModel<RubberDucky> {
    private float wingspeed = 1.0f;
    ModelRenderer bottom;
    ModelRenderer body;
    ModelRenderer back;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer beak;
    ModelRenderer Lwing;
    ModelRenderer Rwing;

    public ModelRubberDucky(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.bottom = new ModelRenderer(this, 0, 56);
        this.bottom.addBox(-2.0f, 0.0f, -2.0f, 4, 1, 4);
        this.bottom.setPos(0.0f, 23.0f, 0.0f);
        this.bottom.mirror = true;
        this.setRotation(this.bottom, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 0, 45);
        this.body.addBox(-3.0f, 0.0f, -3.0f, 6, 2, 8);
        this.body.setPos(0.0f, 21.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.back = new ModelRenderer(this, 0, 33);
        this.back.addBox(-3.0f, 0.0f, -3.0f, 6, 1, 10);
        this.back.setPos(0.0f, 20.0f, 0.0f);
        this.back.mirror = true;
        this.setRotation(this.back, 0.0f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 17, 27);
        this.neck.addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2);
        this.neck.setPos(0.0f, 19.0f, -1.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 13, 18);
        this.head.addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4);
        this.head.setPos(0.0f, 19.0f, -1.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.beak = new ModelRenderer(this, 0, 21);
        this.beak.addBox(-1.5f, -1.0f, -5.0f, 3, 1, 3);
        this.beak.setPos(0.0f, 19.0f, -1.0f);
        this.beak.mirror = true;
        this.setRotation(this.beak, 0.0f, 0.0f, 0.0f);
        this.Lwing = new ModelRenderer(this, 0, 0);
        this.Lwing.addBox(0.0f, -0.5f, 0.0f, 2, 1, 5);
        this.Lwing.setPos(3.0f, 21.0f, -2.0f);
        this.Lwing.mirror = true;
        this.setRotation(this.Lwing, 0.0f, 0.0f, 0.0f);
        this.Rwing = new ModelRenderer(this, 17, 0);
        this.Rwing.addBox(-2.0f, -0.5f, 0.0f, 2, 1, 5);
        this.Rwing.setPos(-3.0f, 21.0f, -2.0f);
        this.Rwing.mirror = true;
        this.setRotation(this.Rwing, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(RubberDucky entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        RubberDucky c = (RubberDucky)entity;
        RenderInfo r = null;
        float hf = 0.0f;
        float newangle = 0.0f;
        float nextangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.beak.yRot = this.head.yRot = (float)Math.toRadians(f3) * 0.45f;
        this.beak.xRot = this.head.xRot = (float)Math.toRadians(f4) * 0.65f;
        r = c.getRenderInfo();
        newangle = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = MathHelper.cos((float)((f2 + 0.3f) * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (c.level.random.nextInt(3) == 1) {
                r.ri1 = 1;
            }
            if (c.getKillCount() >= 5) {
                if (c.level.random.nextInt(2) == 1) {
                    r.ri1 = 1;
                }
                newangle *= 4.0f;
            }
        }
        if (r.ri1 == 0) {
            newangle = 0.0f;
        }
        if (c.isOrderedToSit()) {
            newangle = 0.0f;
        }
        newangle = Math.abs(newangle);
        this.Lwing.zRot = - newangle;
        this.Lwing.yRot = newangle / 2.0f;
        this.Rwing.zRot = newangle;
        this.Rwing.yRot = (- newangle) / 2.0f;
        c.setRenderInfo(r);
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bottom.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.beak.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

