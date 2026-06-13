/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelCryolophosaurus
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.Cryolophosaurus;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCryolophosaurus extends EntityModel<Cryolophosaurus> {
    private float wingspeed = 1.0f;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer jaw;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer rightleg;
    ModelRenderer Shape11;
    ModelRenderer rightleg2;
    ModelRenderer rightleg3;
    ModelRenderer rightleg4;
    ModelRenderer leftleg;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer leftleg2;
    ModelRenderer leftleg3;
    ModelRenderer leftleg4;

    public ModelCryolophosaurus(float f1) {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 128;
        // textureHeight = 128;
        this.wingspeed = f1;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 8, 9, 18);
        this.Shape1.setPos(0.0f, 0.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 53, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 6, 4, 11);
        this.Shape2.setPos(1.0f, -2.0f, -7.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.2268928f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer(this, 0, 41);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 6, 4, 10);
        this.Shape3.setPos(1.0f, -2.0f, -15.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 0, 30);
        this.jaw.addBox(0.0f, 0.0f, 0.0f, 4, 9, 1);
        this.jaw.setPos(2.0f, 1.0f, -8.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, -1.256637f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer(this, 91, 0);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 6, 6, 7);
        this.Shape5.setPos(1.0f, 0.0f, 18.0f);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer(this, 36, 31);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 4, 4, 14);
        this.Shape6.setPos(2.0f, 0.0f, 25.0f);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer(this, 43, 8);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 1, 4, 2);
        this.Shape7.setPos(-1.0f, 8.0f, 0.0f);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.1919862f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer(this, 9, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape8.setPos(-1.0f, 11.0f, 1.0f);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, -0.2617994f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer(this, 0, 0);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 2, 4, 1);
        this.Shape9.setPos(3.0f, -4.0f, -9.0f);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -0.9424778f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer(this, 0, 58);
        this.rightleg.addBox(0.0f, 0.0f, 0.0f, 2, 10, 6);
        this.rightleg.setPos(-1.0f, 2.0f, 12.0f);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, -0.2792527f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer(this, 39, 0);
        this.Shape11.addBox(0.0f, 0.0f, 0.0f, 4, 3, 3);
        this.Shape11.setPos(2.0f, -1.0f, -18.0f);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.rightleg2 = new ModelRenderer(this, 0, 77);
        this.rightleg2.addBox(0.0f, 7.0f, -5.0f, 2, 10, 3);
        this.rightleg2.setPos(-1.0f, 2.0f, 12.0f);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.3839724f, 0.0f, 0.0f);
        this.rightleg3 = new ModelRenderer(this, 35, 31);
        this.rightleg3.addBox(0.0f, 10.0f, 12.0f, 2, 7, 2);
        this.rightleg3.setPos(-1.0f, 2.0f, 12.0f);
        this.rightleg3.mirror = true;
        this.setRotation(this.rightleg3, -0.6806784f, 0.0f, 0.0f);
        this.rightleg4 = new ModelRenderer(this, 68, 55);
        this.rightleg4.addBox(0.0f, 20.0f, -5.0f, 2, 2, 6);
        this.rightleg4.setPos(-1.0f, 2.0f, 12.0f);
        this.rightleg4.mirror = true;
        this.setRotation(this.rightleg4, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer(this, 22, 58);
        this.leftleg.addBox(0.0f, 0.0f, 0.0f, 2, 10, 6);
        this.leftleg.setPos(7.0f, 2.0f, 12.0f);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, -0.2792527f, 0.0f, 0.0f);
        this.Shape16 = new ModelRenderer(this, 0, 8);
        this.Shape16.addBox(0.0f, 0.0f, 0.0f, 1, 4, 2);
        this.Shape16.setPos(8.0f, 8.0f, 0.0f);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.1919862f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer(this, 9, 9);
        this.Shape17.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Shape17.setPos(8.0f, 11.0f, 1.0f);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, -0.2617994f, 0.0f, 0.0f);
        this.leftleg2 = new ModelRenderer(this, 16, 77);
        this.leftleg2.addBox(0.0f, 7.0f, -5.0f, 2, 10, 3);
        this.leftleg2.setPos(7.0f, 2.0f, 12.0f);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.3839724f, 0.0f, 0.0f);
        this.leftleg3 = new ModelRenderer(this, 67, 31);
        this.leftleg3.addBox(0.0f, 10.0f, 12.0f, 2, 7, 2);
        this.leftleg3.setPos(7.0f, 2.0f, 12.0f);
        this.leftleg3.mirror = true;
        this.setRotation(this.leftleg3, -0.6806784f, 0.0f, 0.0f);
        this.leftleg4 = new ModelRenderer(this, 47, 56);
        this.leftleg4.addBox(0.0f, 20.0f, -5.0f, 2, 2, 6);
        this.leftleg4.setPos(7.0f, 2.0f, 12.0f);
        this.leftleg4.mirror = true;
        this.setRotation(this.leftleg4, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Cryolophosaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.rightleg.xRot = -0.2792527f + newangle;
        this.rightleg2.xRot = 0.384f + newangle;
        this.rightleg3.xRot = -0.68f + newangle;
        this.rightleg4.xRot = newangle;
        this.leftleg.xRot = -0.2792527f - newangle;
        this.leftleg2.xRot = 0.384f - newangle;
        this.leftleg3.xRot = -0.68f - newangle;
        this.leftleg4.xRot = - newangle;
        this.jaw.xRot = -1.15f + MathHelper.cos((float)(f2 * 0.28f)) * 3.1415927f * 0.1f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape9.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

