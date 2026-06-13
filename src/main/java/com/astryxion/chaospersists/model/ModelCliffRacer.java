/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelCliffRacer
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.CliffRacer;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCliffRacer extends EntityModel<CliffRacer> {
    private float wingspeed = 1.0f;
    ModelRenderer Body;
    ModelRenderer Fins;
    ModelRenderer LWing;
    ModelRenderer RWing;
    ModelRenderer Tail;
    ModelRenderer TailEnd;
    ModelRenderer Head;
    ModelRenderer Beak;

    public ModelCliffRacer(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.Body = new ModelRenderer(this, 0, 52);
        this.Body.addBox(0.0f, 0.0f, 0.0f, 3, 1, 10);
        this.Body.setPos(-1.0f, 15.0f, -4.0f);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Fins = new ModelRenderer(this, 0, 40);
        this.Fins.addBox(0.0f, -4.0f, 0.0f, 1, 6, 3);
        this.Fins.setPos(0.0f, 15.0f, -1.0f);
        this.Fins.mirror = true;
        this.setRotation(this.Fins, 0.0f, 0.0f, 0.0f);
        this.LWing = new ModelRenderer(this, 0, 31);
        this.LWing.addBox(0.0f, 0.0f, 0.0f, 7, 1, 6);
        this.LWing.setPos(2.0f, 15.0f, -2.0f);
        this.LWing.mirror = true;
        this.setRotation(this.LWing, 0.0f, 0.0f, 0.0f);
        this.RWing = new ModelRenderer(this, 39, 0);
        this.RWing.addBox(-7.0f, 0.0f, 0.0f, 7, 1, 6);
        this.RWing.setPos(-1.0f, 15.0f, -2.0f);
        this.RWing.mirror = true;
        this.setRotation(this.RWing, 0.0f, 0.0f, 0.0f);
        this.Tail = new ModelRenderer(this, 0, 16);
        this.Tail.addBox(0.0f, 0.0f, 0.0f, 1, 1, 9);
        this.Tail.setPos(0.0f, 15.0f, 6.0f);
        this.Tail.mirror = true;
        this.setRotation(this.Tail, 0.0f, 0.0f, 0.0f);
        this.TailEnd = new ModelRenderer(this, 0, 10);
        this.TailEnd.addBox(0.0f, -1.0f, 9.0f, 2, 2, 2);
        this.TailEnd.setPos(-0.5f, 15.0f, 6.0f);
        this.TailEnd.mirror = true;
        this.setRotation(this.TailEnd, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 28, 21);
        this.Head.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Head.setPos(-0.5f, 14.0f, -6.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Beak = new ModelRenderer(this, 0, 0);
        this.Beak.addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.Beak.setPos(0.0f, 14.5f, -8.0f);
        this.Beak.mirror = true;
        this.setRotation(this.Beak, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(CliffRacer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.LWing.zRot = newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.RWing.zRot = - newangle;
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fins.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LWing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RWing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailEnd.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Beak.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

