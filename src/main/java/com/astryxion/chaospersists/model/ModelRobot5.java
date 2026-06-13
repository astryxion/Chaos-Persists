/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelRobot5
 *  com.astryxion.chaospersists.Robot5
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Robot5;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;


public class ModelRobot5 extends EntityModel<Robot5> {
    private float wingspeed = 1.0f;
    ModelRenderer lwheel1;
    ModelRenderer lwheel2;
    ModelRenderer rwheel1;
    ModelRenderer rwheel2;
    ModelRenderer axle;
    ModelRenderer drivebox;
    ModelRenderer stand;
    ModelRenderer swivel;
    ModelRenderer barrel1;
    ModelRenderer barrel2;
    ModelRenderer ammobox;

    public ModelRobot5(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 128;
        // textureHeight = 128;
        this.lwheel1 = new ModelRenderer(this, 0, 23);
        this.lwheel1.addBox(0.0f, -4.0f, -4.0f, 2, 8, 8);
        this.lwheel1.setPos(6.0f, 19.0f, 0.0f);
        this.lwheel1.mirror = true;
        this.setRotation(this.lwheel1, 0.0f, 0.0f, 0.0f);
        this.lwheel2 = new ModelRenderer(this, 0, 43);
        this.lwheel2.addBox(0.0f, -4.0f, -4.0f, 2, 8, 8);
        this.lwheel2.setPos(6.0f, 19.0f, 0.0f);
        this.lwheel2.mirror = true;
        this.setRotation(this.lwheel2, 0.7853982f, 0.0f, 0.0f);
        this.rwheel1 = new ModelRenderer(this, 0, 23);
        this.rwheel1.addBox(0.0f, -4.0f, -4.0f, 2, 8, 8);
        this.rwheel1.setPos(-8.0f, 19.0f, 0.0f);
        this.rwheel1.mirror = true;
        this.setRotation(this.rwheel1, 0.0f, 0.0f, 0.0f);
        this.rwheel2 = new ModelRenderer(this, 0, 43);
        this.rwheel2.addBox(0.0f, -4.0f, -4.0f, 2, 8, 8);
        this.rwheel2.setPos(-8.0f, 19.0f, 0.0f);
        this.rwheel2.mirror = true;
        this.setRotation(this.rwheel2, 0.7853982f, 0.0f, 0.0f);
        this.axle = new ModelRenderer(this, 42, 0);
        this.axle.addBox(-6.0f, -0.5f, -0.5f, 12, 1, 1);
        this.axle.setPos(0.0f, 19.0f, 0.0f);
        this.axle.mirror = true;
        this.setRotation(this.axle, 0.0f, 0.0f, 0.0f);
        this.drivebox = new ModelRenderer(this, 47, 4);
        this.drivebox.addBox(-2.0f, -1.5f, -1.5f, 4, 3, 3);
        this.drivebox.setPos(0.0f, 19.0f, 0.0f);
        this.drivebox.mirror = true;
        this.setRotation(this.drivebox, 0.0f, 0.0f, 0.0f);
        this.stand = new ModelRenderer(this, 35, 0);
        this.stand.addBox(-0.5f, 0.0f, -0.5f, 1, 18, 1);
        this.stand.setPos(0.0f, 0.0f, 0.0f);
        this.stand.mirror = true;
        this.setRotation(this.stand, 0.0f, 0.0f, 0.0f);
        this.swivel = new ModelRenderer(this, 22, 0);
        this.swivel.addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2);
        this.swivel.setPos(0.0f, 0.0f, 0.0f);
        this.swivel.mirror = true;
        this.setRotation(this.swivel, 0.0f, 0.0f, 0.0f);
        this.barrel1 = new ModelRenderer(this, 24, 25);
        this.barrel1.addBox(-1.0f, -2.0f, -10.0f, 2, 2, 13);
        this.barrel1.setPos(0.0f, 0.0f, 0.0f);
        this.barrel1.mirror = true;
        this.setRotation(this.barrel1, 0.0f, 0.0f, 0.0f);
        this.barrel2 = new ModelRenderer(this, 27, 43);
        this.barrel2.addBox(-0.5f, -1.5f, -19.0f, 1, 1, 9);
        this.barrel2.setPos(0.0f, 0.0f, 0.0f);
        this.barrel2.mirror = true;
        this.setRotation(this.barrel2, 0.0f, 0.0f, 0.0f);
        this.ammobox = new ModelRenderer(this, 0, 0);
        this.ammobox.addBox(-2.0f, -2.0f, 3.0f, 4, 3, 5);
        this.ammobox.setPos(0.0f, 0.0f, 0.0f);
        this.ammobox.mirror = true;
        this.setRotation(this.ammobox, 0.0f, 0.0f, 0.0f);
    }
    public void setupAnim(Robot5 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Robot5 e = (Robot5)entity;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        if ((double)f1 > 0.1) {
            newangle = f2 * 0.15f % 6.2831855f;
            newangle = Math.abs(newangle);
        } else {
            newangle = 0.0f;
        }
        this.lwheel1.xRot = newangle;
        this.lwheel2.xRot = (float)((double)newangle + 0.7853981633974483);
        this.rwheel1.xRot = newangle;
        this.rwheel2.xRot = (float)((double)newangle + 0.7853981633974483);
        this.barrel2.yRot = this.ammobox.yRot = (float)Math.toRadians((double)f3 / 2.0);
        this.barrel1.yRot = this.ammobox.yRot;
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lwheel1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwheel2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwheel1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwheel2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.axle.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.drivebox.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.stand.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.swivel.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.barrel1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.barrel2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ammobox.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Robot5 par7Entity) {
        
    }
}

