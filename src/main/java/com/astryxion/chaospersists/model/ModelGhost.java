/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelGhost
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.Ghost;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelGhost extends EntityModel<Ghost> {
    ModelRenderer HeadAndBody;
    ModelRenderer LArm;
    ModelRenderer RArm;

    public ModelGhost() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 64;
        this.HeadAndBody = new ModelRenderer(this, 0, 0);
        this.HeadAndBody.addBox(-3.0f, 0.0f, -3.0f, 6, 21, 6);
        this.HeadAndBody.setPos(0.0f, 0.0f, 0.0f);
        this.HeadAndBody.mirror = true;
        this.setRotation(this.HeadAndBody, 0.0f, 0.0f, 0.0f);
        this.LArm = new ModelRenderer(this, 34, 0);
        this.LArm.addBox(-1.0f, -1.0f, -1.0f, 2, 11, 2);
        this.LArm.setPos(3.0f, 6.0f, 0.0f);
        this.LArm.mirror = true;
        this.setRotation(this.LArm, 0.0f, 0.0f, -0.3316126f);
        this.RArm = new ModelRenderer(this, 25, 0);
        this.RArm.addBox(-1.0f, -1.0f, -1.0f, 2, 11, 2);
        this.RArm.setPos(-3.0f, 6.0f, 0.0f);
        this.RArm.mirror = true;
        this.setRotation(this.RArm, 0.0f, 0.0f, 0.3316126f);
    }

    @Override
    public void setupAnim(Ghost entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0625F;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.LArm.zRot = -0.33f + MathHelper.cos((float)(f2 * 0.3f)) * 3.1415927f * 0.05f;
        this.RArm.zRot = 0.33f + MathHelper.cos((float)(f2 * 0.32f)) * 3.1415927f * 0.05f;
        this.LArm.xRot = -0.33f + MathHelper.cos((float)(f2 * 0.34f)) * 3.1415927f * 0.05f;
        this.RArm.xRot = 0.33f + MathHelper.cos((float)(f2 * 0.36f)) * 3.1415927f * 0.05f;    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        matrixStack.pushPose();
                GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)0.75f, (float)0.75f, (float)0.75f, (float)0.25f);
        this.HeadAndBody.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.LArm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.RArm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable((int)3042);
                matrixStack.popPose();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

