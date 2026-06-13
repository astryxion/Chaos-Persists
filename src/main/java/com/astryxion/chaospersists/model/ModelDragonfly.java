/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelDragonfly
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import com.astryxion.chaospersists.entity.Dragonfly;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDragonfly extends EntityModel<Dragonfly> {
    private float wingspeed = 1.0f;
    ModelRenderer Shape1;
    ModelRenderer lfwing;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer rjaw;
    ModelRenderer ljaw;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer lrwing;
    ModelRenderer rfwing;
    ModelRenderer rrwing;

    public ModelDragonfly(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 5, 4, 7);
        this.Shape1.setPos(0.0f, 16.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.lfwing = new ModelRenderer(this, 0, 33);
        this.lfwing.addBox(0.0f, 0.0f, 0.0f, 10, 1, 3);
        this.lfwing.setPos(5.0f, 16.0f, 1.0f);
        this.lfwing.mirror = true;
        this.setRotation(this.lfwing, 0.0f, 0.4886922f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 0, 13);
        this.Shape3.addBox(-2.0f, 0.0f, -4.0f, 4, 3, 4);
        this.Shape3.setPos(2.5f, 16.0f, -1.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.4886922f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer(this, 9, 21);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 1, 2, 3);
        this.Shape4.setPos(1.0f, 18.0f, -6.0f);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.4886922f, 0.1745329f, 0.0f);
        this.Shape5 = new ModelRenderer(this, 0, 21);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 1, 2, 3);
        this.Shape5.setPos(3.0f, 18.0f, -6.0f);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.4886922f, -0.1745329f, 0.0f);
        this.rjaw = new ModelRenderer(this, 0, 27);
        this.rjaw.addBox(-1.0f, 0.0f, 0.0f, 1, 3, 1);
        this.rjaw.setPos(2.0f, 19.0f, -5.0f);
        this.rjaw.mirror = true;
        this.setRotation(this.rjaw, 0.4363323f, 0.1745329f, 0.0f);
        this.ljaw = new ModelRenderer(this, 5, 27);
        this.ljaw.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.ljaw.setPos(3.0f, 19.0f, -5.0f);
        this.ljaw.mirror = true;
        this.setRotation(this.ljaw, 0.4363323f, -0.1745329f, 0.0f);
        this.tail1 = new ModelRenderer(this, 25, 0);
        this.tail1.addBox(-1.0f, 0.0f, 0.0f, 3, 3, 7);
        this.tail1.setPos(2.0f, 16.0f, 7.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 25, 11);
        this.tail2.addBox(0.0f, 0.0f, 0.0f, 1, 2, 9);
        this.tail2.setPos(2.0f, 16.0f, 14.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer(this, 23, 0);
        this.Shape10.addBox(-1.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Shape10.setPos(1.0f, 18.0f, 0.0f);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, -0.2792527f, 0.0f, 0.3490659f);
        this.Shape11 = new ModelRenderer(this, 40, 0);
        this.Shape11.addBox(0.0f, 0.0f, -4.0f, 1, 1, 4);
        this.Shape11.setPos(-1.0f, 21.0f, 0.0f);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer(this, 18, 12);
        this.Shape12.addBox(-1.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape12.setPos(0.0f, 21.0f, -4.0f);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, -0.1919862f);
        this.Shape13 = new ModelRenderer(this, 18, 0);
        this.Shape13.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Shape13.setPos(4.0f, 18.0f, 0.0f);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, -0.2792527f, 0.0f, -0.3490659f);
        this.Shape14 = new ModelRenderer(this, 51, 0);
        this.Shape14.addBox(0.0f, 0.0f, -4.0f, 1, 1, 4);
        this.Shape14.setPos(5.0f, 21.0f, 0.0f);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        this.Shape15 = new ModelRenderer(this, 13, 12);
        this.Shape15.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape15.setPos(5.0f, 21.0f, -4.0f);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.1919862f);
        this.Shape16 = new ModelRenderer(this, 9, 53);
        this.Shape16.addBox(0.0f, 0.0f, 0.0f, 3, 1, 1);
        this.Shape16.setPos(5.0f, 19.5f, 3.0f);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.6457718f);
        this.Shape17 = new ModelRenderer(this, 0, 56);
        this.Shape17.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape17.setPos(6.0f, 21.0f, 3.0f);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
        this.Shape18 = new ModelRenderer(this, 0, 53);
        this.Shape18.addBox(-3.0f, 0.0f, 0.0f, 3, 1, 1);
        this.Shape18.setPos(0.0f, 19.5f, 3.0f);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0f, 0.0f, -0.6457718f);
        this.Shape19 = new ModelRenderer(this, 5, 56);
        this.Shape19.addBox(-1.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape19.setPos(-1.0f, 21.0f, 3.0f);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0f, 0.0f, 0.0f);
        this.Shape20 = new ModelRenderer(this, 9, 61);
        this.Shape20.addBox(0.0f, 0.0f, 0.0f, 3, 1, 1);
        this.Shape20.setPos(4.0f, 19.5f, 6.0f);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0f, -0.6457718f, 0.5061455f);
        this.Shape21 = new ModelRenderer(this, 0, 61);
        this.Shape21.addBox(0.0f, 0.0f, 0.0f, 3, 1, 1);
        this.Shape21.setPos(1.5f, 19.5f, 7.0f);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0f, -2.391101f, 0.5061455f);
        this.Shape22 = new ModelRenderer(this, 0, 0);
        this.Shape22.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape22.setPos(-1.0f, 21.0f, 7.5f);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0f, 0.0f, 0.0f);
        this.Shape23 = new ModelRenderer(this, 0, 13);
        this.Shape23.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape23.setPos(5.0f, 21.0f, 7.5f);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0f, 0.0f, 0.0f);
        this.lrwing = new ModelRenderer(this, 0, 38);
        this.lrwing.addBox(0.0f, 0.0f, -3.0f, 10, 1, 3);
        this.lrwing.setPos(5.0f, 16.0f, 6.0f);
        this.lrwing.mirror = true;
        this.setRotation(this.lrwing, 0.0f, -0.3839724f, 0.0f);
        this.rfwing = new ModelRenderer(this, 0, 48);
        this.rfwing.addBox(-10.0f, 0.0f, 0.0f, 10, 1, 3);
        this.rfwing.setPos(0.0f, 16.0f, 1.0f);
        this.rfwing.mirror = true;
        this.setRotation(this.rfwing, 0.0f, -0.4886922f, 0.0f);
        this.rrwing = new ModelRenderer(this, 0, 43);
        this.rrwing.addBox(-10.0f, 0.0f, -3.0f, 10, 1, 3);
        this.rrwing.setPos(0.0f, 16.0f, 6.0f);
        this.rrwing.mirror = true;
        this.setRotation(this.rrwing, 0.0f, 0.3839724f, 0.0f);
    }
    @Override
    public void setupAnim(Dragonfly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.lfwing.zRot = newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.rfwing.zRot = - newangle;
        this.lrwing.zRot = newangle + 3.14f;
        this.rrwing.zRot = - newangle + 3.14f;
        this.ljaw.xRot = newangle = MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.rjaw.xRot = - newangle;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape10.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape12.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape13.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape14.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape18.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape19.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape20.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape21.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape22.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape23.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrwing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

