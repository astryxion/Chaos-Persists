/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Firefly
 *  com.astryxion.chaospersists.ModelFirefly
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Firefly;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFirefly extends EntityModel<Firefly> {
    private float wingspeed = 1.0f;
    private int fireflyPackedLight = 0;
    ModelRenderer body;
    ModelRenderer wing_left;
    ModelRenderer wing_right;
    ModelRenderer head;
    ModelRenderer mouth;
    ModelRenderer eye_left;
    ModelRenderer eye_right;
    ModelRenderer front_leg_left_;
    ModelRenderer front_leg_right;
    ModelRenderer back_leg_left;
    ModelRenderer back_leg_right;
    ModelRenderer TailLight;

    public ModelFirefly(float f1) {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 64;
        // textureHeight = 128;
        this.wingspeed = f1;
        this.body = new ModelRenderer(this, 38, 12);
        this.body.addBox(-3.0f, -3.0f, -3.0f, 5, 5, 5);
        this.body.setPos(-1.0f, 9.0f, -1.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.wing_left = new ModelRenderer(this, 46, 0);
        this.wing_left.addBox(0.0f, -6.0f, 0.0f, 0, 6, 2);
        this.wing_left.setPos(1.0f, 6.0f, -2.0f);
        this.wing_left.mirror = true;
        this.setRotation(this.wing_left, 0.0f, 0.0174533f, 0.6981317f);
        this.wing_right = new ModelRenderer(this, 53, 0);
        this.wing_right.addBox(0.0f, -6.0f, 0.0f, 0, 6, 2);
        this.wing_right.setPos(-4.0f, 6.0f, -2.0f);
        this.wing_right.mirror = true;
        this.setRotation(this.wing_right, 0.0f, 0.0f, -0.6981317f);
        this.head = new ModelRenderer(this, 3, 14);
        this.head.addBox(0.0f, 0.0f, 0.0f, 3, 3, 3);
        this.head.setPos(-3.0f, 7.0f, -7.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.2230717f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer(this, 26, 15);
        this.mouth.addBox(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.mouth.setPos(-2.0f, 9.0f, -8.0f);
        this.mouth.mirror = true;
        this.setRotation(this.mouth, 0.2117115f, 0.0f, 0.0f);
        this.eye_left = new ModelRenderer(this, 18, 12);
        this.eye_left.addBox(0.0f, 0.0f, 0.0f, 1, 2, 2);
        this.eye_left.setPos(-1.0f, 6.5f, -6.0f);
        this.eye_left.mirror = true;
        this.setRotation(this.eye_left, 0.0174533f, 0.2602503f, -0.2230717f);
        this.eye_right = new ModelRenderer(this, 18, 18);
        this.eye_right.addBox(1.0f, -0.6f, -0.6f, 1, 2, 2);
        this.eye_right.setPos(-4.0f, 6.5f, -6.0f);
        this.eye_right.mirror = true;
        this.setRotation(this.eye_right, 0.0f, -0.2602503f, 0.2230717f);
        this.front_leg_left_ = new ModelRenderer(this, 32, 0);
        this.front_leg_left_.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.front_leg_left_.setPos(-1.0f, 10.0f, -3.0f);
        this.front_leg_left_.mirror = true;
        this.setRotation(this.front_leg_left_, -0.2792527f, 0.0f, -0.2792527f);
        this.front_leg_right = new ModelRenderer(this, 22, 0);
        this.front_leg_right.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.front_leg_right.setPos(-3.0f, 10.0f, -3.0f);
        this.front_leg_right.mirror = true;
        this.setRotation(this.front_leg_right, -0.2792527f, 0.0f, 0.2792527f);
        this.back_leg_left = new ModelRenderer(this, 11, 0);
        this.back_leg_left.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.back_leg_left.setPos(-1.0f, 10.0f, -1.0f);
        this.back_leg_left.mirror = true;
        this.setRotation(this.back_leg_left, 0.2792527f, 0.0f, -0.2792527f);
        this.back_leg_right = new ModelRenderer(this, 2, 0);
        this.back_leg_right.addBox(0.0f, 0.0f, 0.0f, 1, 5, 1);
        this.back_leg_right.setPos(-3.0f, 10.0f, -1.0f);
        this.back_leg_right.mirror = true;
        this.setRotation(this.back_leg_right, 0.2792527f, 0.0f, 0.2792527f);
        this.TailLight = new ModelRenderer(this, 10, 27);
        this.TailLight.addBox(0.0f, 0.0f, 0.0f, 3, 3, 4);
        this.TailLight.setPos(-3.0f, 6.0f, 1.0f);
        this.TailLight.mirror = true;
        this.setRotation(this.TailLight, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Firefly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Firefly fly = (Firefly)entity;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float onoff = 0.0f;
        this.wing_left.zRot = 1.11f + MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.35f;
        this.wing_right.zRot = -1.11f - MathHelper.cos((float)(f2 * this.wingspeed)) * 3.1415927f * 0.35f;
        
        
        
        
        
        
        
        
        
        
        
        onoff = fly.getBlink();
        this.fireflyPackedLight = onoff > 0.0f ? 15728880 : 0;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        int light = this.fireflyPackedLight != 0 ? this.fireflyPackedLight : packedLight;
        this.body.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.wing_left.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.wing_right.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.mouth.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.eye_left.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.eye_right.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.front_leg_left_.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.front_leg_right.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.back_leg_left.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.back_leg_right.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
        this.TailLight.render(matrixStack, buffer, light, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

