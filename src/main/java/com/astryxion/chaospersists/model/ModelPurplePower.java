/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelPurplePower
 *  com.astryxion.chaospersists.PurplePower
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.item.PurplePower;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelPurplePower extends EntityModel<PurplePower> {
    float wingspeed = 1.0f;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    private PurplePower currentEntity;

    public ModelPurplePower(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        this.Shape1 = new ModelRenderer(this, 0, 12);
        this.Shape1.addBox(-2.0f, -0.5f, -0.5f, 4, 1, 1);
        this.Shape1.setPos(0.0f, 0.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 0, 7);
        this.Shape2.addBox(-4.0f, -0.5f, -0.5f, 8, 1, 1);
        this.Shape2.setPos(0.0f, 0.0f, 0.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 0, 0);
        this.Shape3.addBox(-7.0f, -0.5f, -0.5f, 14, 1, 1);
        this.Shape3.setPos(0.0f, 0.0f, 0.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void setupAnim(PurplePower entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.currentEntity = entity;
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0625F;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        PurplePower p = this.currentEntity;
        if (p == null) {
            return;
        }
        int i;
        float rf1 = 1.0f;
        float newangle = 0.0f;
        matrixStack.pushPose();
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)0.75f, (float)0.75f, (float)0.75f, (float)0.55f);
        rf1 = p.level.random.nextFloat() * 360.0f;
        GL11.glRotatef((float)rf1, (float)1.0f, (float)0.0f, (float)0.0f);
        int light = 15728880;
        for (i = 0; i < 6; ++i) {
            this.Shape1.zRot = newangle;
            this.Shape1.render(matrixStack, buffer, light, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            newangle += 1.0471976f;
        }
        GL11.glRotatef((float)rf1, (float)1.0f, (float)0.0f, (float)0.0f);
        newangle = 0.0f;
        rf1 = p.level.random.nextFloat() * 360.0f;
        GL11.glRotatef((float)rf1, (float)0.0f, (float)1.0f, (float)0.0f);
        for (i = 0; i < 6; ++i) {
            this.Shape2.zRot = newangle;
            this.Shape2.render(matrixStack, buffer, light, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            newangle += 1.0471976f;
        }
        GL11.glRotatef((float)rf1, (float)0.0f, (float)1.0f, (float)0.0f);
        newangle = 0.0f;
        rf1 = p.level.random.nextFloat() * 360.0f;
        GL11.glRotatef((float)rf1, (float)0.0f, (float)0.0f, (float)1.0f);
        for (i = 0; i < 6; ++i) {
            this.Shape3.zRot = newangle;
            this.Shape3.render(matrixStack, buffer, light, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            newangle += 1.0471976f;
        }
        GL11.glRotatef((float)rf1, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        matrixStack.popPose();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, PurplePower par7Entity) {
    }
}
