/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Camarasaurus
 *  com.astryxion.chaospersists.ModelCamarasaurus
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Camarasaurus;
import net.minecraft.client.renderer.entity.model.EntityModel;
import com.astryxion.chaospersists.entity.Camarasaurus;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCamarasaurus extends EntityModel<Camarasaurus> {
    private float wingspeed = 1.0f;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Tail0;
    ModelRenderer Neck1;
    ModelRenderer Neck2;
    ModelRenderer Neck3;
    ModelRenderer Head1;
    ModelRenderer Head2;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer BLegupleft;
    ModelRenderer FLegupleft;
    ModelRenderer BLegupright;
    ModelRenderer FLegupright;
    ModelRenderer BLegdownright;
    ModelRenderer FLegdownleft;
    ModelRenderer FLegdownright;
    ModelRenderer BLegdownleft;

    public ModelCamarasaurus(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 256;
        // textureHeight = 256;
        this.Body1 = new ModelRenderer(this, 0, 135);
        this.Body1.addBox(-6.0f, 0.0f, 0.0f, 12, 12, 12);
        this.Body1.setPos(0.0f, -1.0f, 0.0f);
        this.Body1.mirror = true;
        this.setRotation(this.Body1, 0.0f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer(this, 0, 160);
        this.Body2.addBox(-5.0f, 0.0f, 0.0f, 10, 10, 6);
        this.Body2.setPos(0.0f, -2.0f, -4.0f);
        this.Body2.mirror = true;
        this.setRotation(this.Body2, -0.1858931f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer(this, 0, 177);
        this.Body3.addBox(-4.0f, 0.0f, 0.0f, 8, 8, 4);
        this.Body3.setPos(0.0f, -3.0f, -6.0f);
        this.Body3.mirror = true;
        this.setRotation(this.Body3, -0.3346075f, 0.0f, 0.0f);
        this.Body4 = new ModelRenderer(this, 0, 120);
        this.Body4.addBox(-5.0f, 0.0f, 0.0f, 10, 10, 4);
        this.Body4.setPos(0.0f, 0.0f, 11.0f);
        this.Body4.mirror = true;
        this.setRotation(this.Body4, 0.0f, 0.0f, 0.0f);
        this.Tail0 = new ModelRenderer(this, 0, 107);
        this.Tail0.addBox(-3.0f, -2.0f, 0.0f, 6, 6, 6);
        this.Tail0.setPos(0.0f, 3.0f, 14.0f);
        this.Tail0.mirror = true;
        this.setRotation(this.Tail0, -0.0743572f, 0.0f, 0.0f);
        this.Neck1 = new ModelRenderer(this, 0, 190);
        this.Neck1.addBox(-3.0f, 0.0f, 0.0f, 6, 6, 5);
        this.Neck1.setPos(0.0f, -4.0f, -9.0f);
        this.Neck1.mirror = true;
        this.setRotation(this.Neck1, -0.4089647f, 0.0f, 0.0f);
        this.Neck2 = new ModelRenderer(this, 0, 202);
        this.Neck2.addBox(-2.0f, 0.0f, -6.0f, 4, 4, 7);
        this.Neck2.setPos(0.0f, -3.0f, -9.0f);
        this.Neck2.mirror = true;
        this.setRotation(this.Neck2, -0.5948578f, 0.0f, 0.0f);
        this.Neck3 = new ModelRenderer(this, 0, 214);
        this.Neck3.addBox(-2.0f, -2.0f, -12.0f, 4, 4, 13);
        this.Neck3.setPos(0.0f, -5.0f, -15.0f);
        this.Neck3.mirror = true;
        this.setRotation(this.Neck3, -0.8179294f, 0.0f, 0.0f);
        this.Head1 = new ModelRenderer(this, 0, 232);
        this.Head1.addBox(-4.0f, -3.0f, -6.0f, 8, 6, 6);
        this.Head1.setPos(0.0f, -13.0f, -22.0f);
        this.Head1.mirror = true;
        this.setRotation(this.Head1, -0.1115358f, 0.0f, 0.0f);
        this.Head2 = new ModelRenderer(this, 0, 245);
        this.Head2.addBox(-3.0f, -2.0f, -4.0f, 6, 4, 4);
        this.Head2.setPos(0.0f, -13.0f, -27.0f);
        this.Head2.mirror = true;
        this.setRotation(this.Head2, 0.0f, 0.0f, 0.0f);
        this.Tail1 = new ModelRenderer(this, 0, 93);
        this.Tail1.addBox(-2.0f, -3.0f, 0.0f, 4, 4, 9);
        this.Tail1.setPos(0.0f, 5.0f, 19.0f);
        this.Tail1.mirror = true;
        this.setRotation(this.Tail1, -0.1115358f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer(this, 0, 82);
        this.Tail2.addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8);
        this.Tail2.setPos(0.0f, 4.0f, 26.0f);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, -0.0743572f, 0.0f, 0.0f);
        this.Tail3 = new ModelRenderer(this, 0, 73);
        this.Tail3.addBox(-0.5f, -0.5f, 0.0f, 1, 1, 7);
        this.Tail3.setPos(0.0f, 4.5f, 34.0f);
        this.Tail3.mirror = true;
        this.setRotation(this.Tail3, -0.0371786f, 0.0f, 0.0f);
        this.BLegupleft = new ModelRenderer(this, 49, 157);
        this.BLegupleft.addBox(0.0f, 0.0f, 0.0f, 6, 8, 6);
        this.BLegupleft.setPos(2.0f, 9.0f, 7.0f);
        this.BLegupleft.mirror = true;
        this.setRotation(this.BLegupleft, -0.1487195f, 0.0f, 0.0f);
        this.FLegupleft = new ModelRenderer(this, 49, 141);
        this.FLegupleft.addBox(0.0f, 0.0f, -6.0f, 6, 9, 6);
        this.FLegupleft.setPos(2.0f, 8.0f, 2.0f);
        this.FLegupleft.mirror = true;
        this.setRotation(this.FLegupleft, 0.0f, 0.0f, 0.0f);
        this.BLegupright = new ModelRenderer(this, 49, 126);
        this.BLegupright.addBox(-6.0f, 0.0f, 0.0f, 6, 8, 6);
        this.BLegupright.setPos(-2.0f, 9.0f, 7.0f);
        this.BLegupright.mirror = true;
        this.setRotation(this.BLegupright, -0.1487144f, 0.0f, 0.0f);
        this.FLegupright = new ModelRenderer(this, 49, 110);
        this.FLegupright.addBox(-6.0f, 0.0f, -6.0f, 6, 9, 6);
        this.FLegupright.setPos(-2.0f, 8.0f, 2.0f);
        this.FLegupright.mirror = true;
        this.setRotation(this.FLegupright, 0.0f, 0.0f, 0.0f);
        this.BLegdownright = new ModelRenderer(this, 115, 157);
        this.BLegdownright.addBox(-5.0f, 7.0f, -1.0f, 5, 8, 5);
        this.BLegdownright.setPos(-2.0f, 9.0f, 7.0f);
        this.BLegdownright.mirror = true;
        this.setRotation(this.BLegdownright, 0.0f, 0.0f, 0.0f);
        this.FLegdownleft = new ModelRenderer(this, 94, 143);
        this.FLegdownleft.addBox(0.0f, 8.0f, -6.0f, 5, 8, 5);
        this.FLegdownleft.setPos(2.0f, 8.0f, 2.0f);
        this.FLegdownleft.mirror = true;
        this.setRotation(this.FLegdownleft, 0.0f, 0.0f, 0.0f);
        this.FLegdownright = new ModelRenderer(this, 94, 157);
        this.FLegdownright.addBox(-5.0f, 8.0f, -6.0f, 5, 8, 5);
        this.FLegdownright.setPos(-2.0f, 8.0f, 2.0f);
        this.FLegdownright.mirror = true;
        this.setRotation(this.FLegdownright, 0.0f, 0.0f, 0.0f);
        this.BLegdownleft = new ModelRenderer(this, 115, 143);
        this.BLegdownleft.addBox(0.0f, 7.0f, -1.0f, 5, 8, 5);
        this.BLegdownleft.setPos(2.0f, 9.0f, 7.0f);
        this.BLegdownleft.mirror = true;
        this.setRotation(this.BLegdownleft, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Camarasaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Camarasaurus c = (Camarasaurus)entity;
        float hf = 0.0f;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.FLegupleft.xRot = newangle;
        this.FLegdownleft.xRot = newangle;
        this.FLegupright.xRot = - newangle;
        this.FLegdownright.xRot = - newangle;
        this.BLegupleft.xRot = -0.15f - newangle;
        this.BLegdownleft.xRot = - newangle;
        this.BLegupright.xRot = -0.15f + newangle;
        this.BLegdownright.xRot = newangle;
        hf = (float)c.getCamarasaurusHealth() / c.getMaxHealth();
        newangle = MathHelper.cos((float)(f2 * 1.5f * this.wingspeed * hf)) * 3.1415927f * 0.25f * hf;
        if (c.isOrderedToSit()) {
            newangle = 0.0f;
        }
        this.Tail0.yRot = newangle * 0.25f;
        this.Tail1.z = this.Tail0.z + (float)Math.cos(this.Tail0.yRot) * 5.0f;
        this.Tail1.x = this.Tail0.x + (float)Math.sin(this.Tail0.yRot) * 5.0f;
        this.Tail1.yRot = newangle * 0.5f;
        this.Tail2.z = this.Tail1.z + (float)Math.cos(this.Tail1.yRot) * 8.0f;
        this.Tail2.x = this.Tail1.x + (float)Math.sin(this.Tail1.yRot) * 8.0f;
        this.Tail2.yRot = newangle * 0.75f;
        this.Tail3.z = this.Tail2.z + (float)Math.cos(this.Tail2.yRot) * 7.0f;
        this.Tail3.x = this.Tail2.x + (float)Math.sin(this.Tail2.yRot) * 7.0f;
        this.Tail3.yRot = newangle * 1.0f;
        this.Neck1.yRot = (float)Math.toRadians(f3) * 0.125f;
        this.Neck2.z = this.Neck1.z;
        this.Neck2.x = this.Neck1.x;
        this.Neck2.yRot = (float)Math.toRadians(f3) * 0.25f;
        this.Neck3.z = this.Neck2.z - (float)Math.cos(this.Neck2.yRot) * 6.0f;
        this.Neck3.x = this.Neck2.x - (float)Math.sin(this.Neck2.yRot) * 6.0f;
        this.Neck3.yRot = (float)Math.toRadians(f3) * 0.38f;
        this.Head1.z = this.Neck3.z - (float)Math.cos(this.Neck3.yRot) * 7.0f;
        this.Head1.x = this.Neck3.x - (float)Math.sin(this.Neck3.yRot) * 7.0f;
        this.Head1.yRot = (float)Math.toRadians(f3);
        this.Head2.z = this.Head1.z - (float)Math.cos(this.Head1.yRot) * 5.0f;
        this.Head2.x = this.Head1.x - (float)Math.sin(this.Head1.yRot) * 5.0f;
        this.Head2.yRot = (float)Math.toRadians(f3);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegupleft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegdownleft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegupright.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegdownright.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegupleft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegdownright.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegupright.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegdownleft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

