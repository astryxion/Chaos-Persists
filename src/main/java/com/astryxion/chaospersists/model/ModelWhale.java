/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelWhale
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Whale;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWhale extends EntityModel<Whale> {
    ModelRenderer belly;
    ModelRenderer body;
    ModelRenderer back;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tailfin1;
    ModelRenderer tailfin2;
    ModelRenderer backfin;
    ModelRenderer head;
    ModelRenderer jaw;
    ModelRenderer lfin1;
    ModelRenderer lfin2;
    ModelRenderer rfin1;
    ModelRenderer rfin2;

    public ModelWhale() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 256;
        // textureHeight = 256;
        this.belly = new ModelRenderer(this, 0, 92);
        this.belly.addBox(-6.0f, 0.0f, 0.0f, 12, 2, 32);
        this.belly.setPos(0.0f, 22.0f, 6.0f);
        this.belly.mirror = true;
        this.setRotation(this.belly, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 0, 188);
        this.body.addBox(-10.0f, 0.0f, 0.0f, 20, 12, 52);
        this.body.setPos(0.0f, 10.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.back = new ModelRenderer(this, 0, 45);
        this.back.addBox(-4.0f, 0.0f, 0.0f, 8, 2, 40);
        this.back.setPos(0.0f, 8.0f, 3.0f);
        this.back.mirror = true;
        this.setRotation(this.back, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 186, 0);
        this.tail1.addBox(-6.0f, 0.0f, 0.0f, 12, 7, 14);
        this.tail1.setPos(0.0f, 11.0f, 52.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 186, 24);
        this.tail2.addBox(-4.0f, 0.0f, 0.0f, 8, 5, 10);
        this.tail2.setPos(0.0f, 12.0f, 66.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.tailfin1 = new ModelRenderer(this, 186, 43);
        this.tailfin1.addBox(0.0f, 0.0f, 0.0f, 17, 2, 11);
        this.tailfin1.setPos(2.0f, 13.0f, 74.0f);
        this.tailfin1.mirror = true;
        this.setRotation(this.tailfin1, 0.0872665f, -0.0872665f, 0.0f);
        this.tailfin2 = new ModelRenderer(this, 186, 59);
        this.tailfin2.addBox(-17.0f, 0.0f, 0.0f, 17, 2, 11);
        this.tailfin2.setPos(-2.0f, 13.0f, 74.0f);
        this.tailfin2.mirror = true;
        this.setRotation(this.tailfin2, 0.0872665f, 0.0872665f, 0.0f);
        this.backfin = new ModelRenderer(this, 0, 15);
        this.backfin.addBox(-0.5f, 0.0f, 0.0f, 1, 4, 8);
        this.backfin.setPos(0.0f, 8.0f, 11.0f);
        this.backfin.mirror = true;
        this.setRotation(this.backfin, 0.3665191f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 155);
        this.head.addBox(-8.0f, 0.0f, -16.0f, 16, 8, 22);
        this.head.setPos(0.0f, 11.0f, -6.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer(this, 0, 130);
        this.jaw.addBox(-7.0f, -1.0f, -20.0f, 14, 2, 20);
        this.jaw.setPos(0.0f, 20.0f, 0.0f);
        this.jaw.mirror = true;
        this.setRotation(this.jaw, 0.0698132f, 0.0f, 0.0f);
        this.lfin1 = new ModelRenderer(this, 96, 0);
        this.lfin1.addBox(0.0f, -1.0f, -3.0f, 4, 3, 6);
        this.lfin1.setPos(10.0f, 18.0f, 8.0f);
        this.lfin1.mirror = true;
        this.setRotation(this.lfin1, 0.0f, -0.0872665f, 0.0f);
        this.lfin2 = new ModelRenderer(this, 120, 0);
        this.lfin2.addBox(2.0f, -0.5f, -3.0f, 22, 2, 8);
        this.lfin2.setPos(10.0f, 18.0f, 8.0f);
        this.lfin2.mirror = true;
        this.setRotation(this.lfin2, 0.0f, -0.0872665f, 0.0f);
        this.rfin1 = new ModelRenderer(this, 96, 12);
        this.rfin1.addBox(-4.0f, -1.0f, -3.0f, 4, 3, 6);
        this.rfin1.setPos(-10.0f, 18.0f, 8.0f);
        this.rfin1.mirror = true;
        this.setRotation(this.rfin1, 0.0f, 0.0872665f, 0.0f);
        this.rfin2 = new ModelRenderer(this, 120, 13);
        this.rfin2.addBox(-24.0f, -0.5f, -3.0f, 22, 2, 8);
        this.rfin2.setPos(-10.0f, 18.0f, 8.0f);
        this.rfin2.mirror = true;
        this.setRotation(this.rfin2, 0.0f, 0.0872665f, 0.0f);
    }
    @Override
    public void setupAnim(Whale entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = MathHelper.cos((float)(f2 * 0.55f)) * 3.1415927f * 0.15f;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 0.3f)) * 3.1415927f * 0.2f * f1 : MathHelper.cos((float)(f2 * 0.08f)) * 3.1415927f * 0.05f;
        this.lfin2.zRot = 0.436f + newangle;
        this.lfin1.zRot = this.lfin2.zRot / 2.0f;
        this.rfin2.zRot = -0.436f - newangle;
        this.rfin1.zRot = this.rfin2.zRot / 2.0f;
        newangle = MathHelper.cos((float)(f2 * 0.03f)) * 3.1415927f * 0.02f;
        this.jaw.xRot = 0.087f + newangle;
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 0.4f)) * 3.1415927f * 0.16f * f1 : MathHelper.cos((float)(f2 * 0.05f)) * 3.1415927f * 0.03f;
        this.tail1.xRot = newangle * 0.5f;
        this.tail2.xRot = newangle * 1.25f;
        this.tailfin1.xRot = this.tailfin2.xRot = newangle * 2.25f;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.xRot) * 14.0f;
        this.tail2.y = this.tail1.y - (float)Math.sin(this.tail1.xRot) * 14.0f;
        this.tailfin1.z = this.tailfin2.z = this.tail2.z + (float)Math.cos(this.tail2.xRot) * 8.0f;
        this.tailfin1.y = this.tailfin2.y = this.tail2.y - (float)Math.sin(this.tail2.xRot) * 8.0f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.belly.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.backfin.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Whale par7Entity) {
        
    }
}

