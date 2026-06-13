/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelCockateil
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.Cockateil;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCockateil extends EntityModel<Cockateil> {
    private float wingspeed = 1.0f;
    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer Beak;
    ModelRenderer LowerBeak;
    ModelRenderer feather2;
    ModelRenderer feather1;
    ModelRenderer feather3;
    ModelRenderer tailfeather1;
    ModelRenderer rwing1;
    ModelRenderer lwing1;
    ModelRenderer leg;
    ModelRenderer otherleg;
    ModelRenderer lwing2;
    ModelRenderer rwing2;
    ModelRenderer tailfeather2;
    ModelRenderer tailfeather3;

    public ModelCockateil(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 32;
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.addBox(0.0f, 0.0f, 0.0f, 5, 3, 6);
        this.Body.setPos(-1.0f, 18.0f, 0.0f);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer(this, 22, 0);
        this.Head.addBox(0.0f, 0.0f, 0.0f, 3, 3, 4);
        this.Head.setPos(0.0f, 16.0f, -3.0f);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Beak = new ModelRenderer(this, 0, 21);
        this.Beak.addBox(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.Beak.setPos(1.0f, 17.0f, -6.0f);
        this.Beak.mirror = true;
        this.setRotation(this.Beak, 0.0f, 0.0f, 0.0f);
        this.LowerBeak = new ModelRenderer(this, 1, 17);
        this.LowerBeak.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.LowerBeak.setPos(1.0f, 18.0f, -4.0f);
        this.LowerBeak.mirror = true;
        this.setRotation(this.LowerBeak, 0.0f, 0.0f, 0.0f);
        this.feather2 = new ModelRenderer(this, 15, 9);
        this.feather2.addBox(0.0f, -2.5f, -0.75f, 1, 3, 1);
        this.feather2.setPos(1.0f, 16.0f, 0.0f);
        this.feather2.mirror = true;
        this.setRotation(this.feather2, -0.6426736f, 0.0f, 0.0f);
        this.feather1 = new ModelRenderer(this, 11, 9);
        this.feather1.addBox(0.0f, -2.5f, -0.5f, 1, 3, 1);
        this.feather1.setPos(1.0f, 16.0f, -2.0f);
        this.feather1.mirror = true;
        this.setRotation(this.feather1, -0.2230717f, 0.0f, 0.0f);
        this.feather3 = new ModelRenderer(this, 19, 9);
        this.feather3.addBox(0.0f, -3.0f, 0.5f, 1, 4, 1);
        this.feather3.setPos(1.0f, 16.0f, 1.0f);
        this.feather3.mirror = true;
        this.setRotation(this.feather3, -1.276259f, 0.0f, 0.0f);
        this.tailfeather1 = new ModelRenderer(this, 46, 15);
        this.tailfeather1.addBox(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.tailfeather1.setPos(0.0f, 18.0f, 6.0f);
        this.tailfeather1.mirror = true;
        this.setRotation(this.tailfeather1, 0.0f, 0.0f, 0.0f);
        this.rwing1 = new ModelRenderer(this, 23, 9);
        this.rwing1.addBox(0.0f, 0.0f, 0.0f, 1, 4, 4);
        this.rwing1.setPos(-1.0f, 18.0f, 1.0f);
        this.rwing1.mirror = true;
        this.setRotation(this.rwing1, 0.0f, 0.0f, 1.595066f);
        this.lwing1 = new ModelRenderer(this, 33, 9);
        this.lwing1.addBox(-1.0f, 0.0f, 0.0f, 1, 4, 4);
        this.lwing1.setPos(4.0f, 18.0f, 1.0f);
        this.lwing1.mirror = true;
        this.setRotation(this.lwing1, 0.0f, 0.0f, -1.561488f);
        this.leg = new ModelRenderer(this, 4, 12);
        this.leg.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.leg.setPos(2.0f, 21.0f, 3.0f);
        this.leg.mirror = true;
        this.setRotation(this.leg, 0.8726646f, 0.0f, 0.0f);
        this.otherleg = new ModelRenderer(this, 0, 12);
        this.otherleg.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.otherleg.setPos(0.0f, 21.0f, 3.0f);
        this.otherleg.mirror = true;
        this.setRotation(this.otherleg, 0.6108652f, 0.0f, 0.0f);
        this.lwing2 = new ModelRenderer(this, 10, 14);
        this.lwing2.addBox(4.0f, 0.0f, 0.0f, 3, 1, 3);
        this.lwing2.setPos(4.0f, 18.0f, 1.0f);
        this.lwing2.mirror = true;
        this.setRotation(this.lwing2, 0.0f, 0.0f, 0.0f);
        this.rwing2 = new ModelRenderer(this, 10, 19);
        this.rwing2.addBox(-7.0f, 0.0f, 0.0f, 3, 1, 3);
        this.rwing2.setPos(-1.0f, 18.0f, 1.0f);
        this.rwing2.mirror = true;
        this.setRotation(this.rwing2, 0.0f, 0.0f, 0.0f);
        this.tailfeather2 = new ModelRenderer(this, 44, 20);
        this.tailfeather2.addBox(-0.5f, 0.0f, 3.0f, 4, 1, 4);
        this.tailfeather2.setPos(0.0f, 18.0f, 6.0f);
        this.tailfeather2.mirror = true;
        this.setRotation(this.tailfeather2, 0.0f, 0.0f, 0.0f);
        this.tailfeather3 = new ModelRenderer(this, 36, 26);
        this.tailfeather3.addBox(-1.0f, 0.0f, 7.0f, 5, 1, 4);
        this.tailfeather3.setPos(0.0f, 18.0f, 6.0f);
        this.tailfeather3.mirror = true;
        this.setRotation(this.tailfeather3, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Cockateil entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed)) * 3.1415927f * 0.35f;
        this.lwing1.zRot = -1.5f + newangle;
        this.lwing2.zRot = newangle;
        this.rwing1.zRot = 1.5f - newangle;
        this.rwing2.zRot = - newangle;
        this.tailfeather1.xRot = newangle = MathHelper.cos((float)(f2 * 0.3f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.tailfeather2.xRot = newangle;
        this.tailfeather3.xRot = newangle;
        this.feather1.zRot = newangle = MathHelper.cos((float)(f2 * 1.1f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.feather2.zRot = newangle = MathHelper.cos((float)(f2 * 1.2f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.feather3.zRot = newangle = MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.08f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Beak.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LowerBeak.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfeather1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.otherleg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfeather2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfeather3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

