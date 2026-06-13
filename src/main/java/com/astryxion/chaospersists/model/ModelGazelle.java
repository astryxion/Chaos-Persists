/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Gazelle
 *  com.astryxion.chaospersists.ModelGazelle
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Gazelle;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelGazelle extends EntityModel<Gazelle> {
    private float wingspeed = 1.0f;
    ModelRenderer Chest;
    ModelRenderer lfleg1;
    ModelRenderer lrleg2;
    ModelRenderer lrleg1;
    ModelRenderer rfleg3;
    ModelRenderer rrleg2;
    ModelRenderer rrleg3;
    ModelRenderer rfleg2;
    ModelRenderer lrleg4;
    ModelRenderer tail;
    ModelRenderer lear;
    ModelRenderer rrleg1;
    ModelRenderer rfleg1;
    ModelRenderer lrleg3;
    ModelRenderer lfleg2;
    ModelRenderer rrleg5;
    ModelRenderer rrleg4;
    ModelRenderer lfleg3;
    ModelRenderer rfleg4;
    ModelRenderer lfleg4;
    ModelRenderer lrleg5;
    ModelRenderer Body;
    ModelRenderer neck;
    ModelRenderer la3;
    ModelRenderer throatfluff;
    ModelRenderer rear;
    ModelRenderer head;
    ModelRenderer ra1;
    ModelRenderer la1;
    ModelRenderer la2;
    ModelRenderer ra2;
    ModelRenderer ra3;
    ModelRenderer nose;
    ModelRenderer mouth;

    public ModelGazelle(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 64;
        this.Chest = new ModelRenderer(this, 12, 57);
        this.Chest.addBox(0.0f, 0.0f, 0.0f, 5, 2, 3);
        this.Chest.setPos(-2.5f, 8.0f, -6.0f);
        this.Chest.mirror = true;
        this.setRotation(this.Chest, 2.342252f, 0.0f, 0.0f);
        this.lfleg1 = new ModelRenderer(this, 0, 31);
        this.lfleg1.addBox(0.0f, 0.0f, 0.0f, 2, 6, 3);
        this.lfleg1.setPos(2.0f, 6.0f, -6.0f);
        this.lfleg1.mirror = true;
        this.setRotation(this.lfleg1, 0.2974289f, 0.0f, 0.0f);
        this.lrleg2 = new ModelRenderer(this, 16, 49);
        this.lrleg2.addBox(0.0f, 5.0f, -1.0f, 2, 2, 6);
        this.lrleg2.setPos(2.0f, 4.0f, 3.0f);
        this.lrleg2.mirror = true;
        this.setRotation(this.lrleg2, 0.1858931f, 0.0f, 0.0f);
        this.lrleg1 = new ModelRenderer(this, 23, 31);
        this.lrleg1.addBox(0.0f, 0.0f, 0.0f, 2, 6, 3);
        this.lrleg1.setPos(2.0f, 4.0f, 3.0f);
        this.lrleg1.mirror = true;
        this.setRotation(this.lrleg1, 0.0f, 0.0f, 0.0f);
        this.rfleg3 = new ModelRenderer(this, 40, 49);
        this.rfleg3.addBox(0.0f, 10.0f, 6.0f, 2, 6, 2);
        this.rfleg3.setPos(-4.0f, 5.966667f, -6.0f);
        this.rfleg3.mirror = true;
        this.setRotation(this.rfleg3, -0.4089647f, 0.0f, 0.0f);
        this.rrleg2 = new ModelRenderer(this, 16, 49);
        this.rrleg2.addBox(0.0f, 5.0f, -1.0f, 2, 2, 6);
        this.rrleg2.setPos(-4.0f, 4.0f, 3.0f);
        this.rrleg2.mirror = true;
        this.setRotation(this.rrleg2, 0.1858931f, 0.0f, 0.0f);
        this.rrleg3 = new ModelRenderer(this, 32, 11);
        this.rrleg3.addBox(0.0f, 4.0f, 5.0f, 2, 12, 2);
        this.rrleg3.setPos(-4.0f, 3.966667f, 3.0f);
        this.rrleg3.mirror = true;
        this.setRotation(this.rrleg3, -0.0743572f, 0.0f, 0.0f);
        this.rfleg2 = new ModelRenderer(this, 24, 11);
        this.rfleg2.addBox(0.0f, 2.0f, 2.0f, 2, 12, 2);
        this.rfleg2.setPos(-4.0f, 5.966667f, -6.0f);
        this.rfleg2.mirror = true;
        this.setRotation(this.rfleg2, -0.0743572f, 0.0f, 0.0f);
        this.lrleg4 = new ModelRenderer(this, 32, 49);
        this.lrleg4.addBox(0.0f, 11.0f, 9.5f, 2, 6, 2);
        this.lrleg4.setPos(2.0f, 4.0f, 3.0f);
        this.lrleg4.mirror = true;
        this.setRotation(this.lrleg4, -0.4089647f, 0.0f, 0.0f);
        this.tail = new ModelRenderer(this, 0, 49);
        this.tail.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.tail.setPos(-2.0f, 0.0f, 4.0f);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.9666439f, 0.0f, 0.0f);
        this.lear = new ModelRenderer(this, 18, 0);
        this.lear.addBox(-5.0f, -3.0f, 2.0f, 3, 2, 1);
        this.lear.setPos(0.0f, -9.0f, -6.0f);
        this.lear.mirror = true;
        this.setRotation(this.lear, -0.1047198f, 1.570796f, 0.0f);
        this.rrleg1 = new ModelRenderer(this, 23, 31);
        this.rrleg1.addBox(0.0f, 0.0f, 0.0f, 2, 6, 3);
        this.rrleg1.setPos(-4.0f, 4.0f, 3.0f);
        this.rrleg1.mirror = true;
        this.setRotation(this.rrleg1, 0.0f, 0.0f, 0.0f);
        this.rfleg1 = new ModelRenderer(this, 0, 31);
        this.rfleg1.addBox(0.0f, 0.0f, 0.0f, 2, 6, 3);
        this.rfleg1.setPos(-4.0f, 6.0f, -6.0f);
        this.rfleg1.mirror = true;
        this.setRotation(this.rfleg1, 0.2974289f, 0.0f, 0.0f);
        this.lrleg3 = new ModelRenderer(this, 32, 11);
        this.lrleg3.addBox(0.0f, 4.0f, 5.0f, 2, 12, 2);
        this.lrleg3.setPos(2.0f, 3.966667f, 3.0f);
        this.lrleg3.mirror = true;
        this.setRotation(this.lrleg3, -0.0743572f, 0.0f, 0.0f);
        this.lfleg2 = new ModelRenderer(this, 24, 11);
        this.lfleg2.addBox(0.0f, 2.0f, 2.0f, 2, 12, 2);
        this.lfleg2.setPos(2.0f, 5.966667f, -6.0f);
        this.lfleg2.mirror = true;
        this.setRotation(this.lfleg2, -0.0743572f, 0.0f, 0.0f);
        this.rrleg5 = new ModelRenderer(this, 0, 58);
        this.rrleg5.addBox(-0.5f, 17.0f, 2.0f, 3, 3, 3);
        this.rrleg5.setPos(-4.0f, 4.0f, 3.0f);
        this.rrleg5.mirror = true;
        this.setRotation(this.rrleg5, 0.0f, 0.0f, 0.0f);
        this.rrleg5.mirror = false;
        this.rrleg4 = new ModelRenderer(this, 32, 49);
        this.rrleg4.addBox(0.0f, 11.0f, 9.5f, 2, 6, 2);
        this.rrleg4.setPos(-4.0f, 3.966667f, 3.0f);
        this.rrleg4.mirror = true;
        this.setRotation(this.rrleg4, -0.4089647f, 0.0f, 0.0f);
        this.lfleg3 = new ModelRenderer(this, 40, 49);
        this.lfleg3.addBox(0.0f, 10.0f, 6.0f, 2, 6, 2);
        this.lfleg3.setPos(2.0f, 5.966667f, -6.0f);
        this.lfleg3.mirror = true;
        this.setRotation(this.lfleg3, -0.4089647f, 0.0f, 0.0f);
        this.rfleg4 = new ModelRenderer(this, 0, 58);
        this.rfleg4.addBox(-0.5f, 15.0f, -1.0f, 3, 3, 3);
        this.rfleg4.setPos(-4.0f, 6.0f, -6.0f);
        this.rfleg4.mirror = true;
        this.setRotation(this.rfleg4, 0.0f, 0.0f, 0.0f);
        this.lfleg4 = new ModelRenderer(this, 0, 58);
        this.lfleg4.addBox(-0.5f, 15.0f, -1.0f, 3, 3, 3);
        this.lfleg4.setPos(2.0f, 6.0f, -6.0f);
        this.lfleg4.mirror = true;
        this.setRotation(this.lfleg4, 0.0f, 0.0f, 0.0f);
        this.lrleg5 = new ModelRenderer(this, 0, 58);
        this.lrleg5.addBox(-0.5f, 17.0f, 2.0f, 3, 3, 3);
        this.lrleg5.setPos(2.0f, 4.0f, 3.0f);
        this.lrleg5.mirror = true;
        this.setRotation(this.lrleg5, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer(this, 0, 12);
        this.Body.addBox(0.0f, 0.0f, 0.0f, 6, 6, 13);
        this.Body.setPos(-3.0f, 2.0f, -7.0f);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.2230717f, 0.0f, 0.0f);
        this.neck = new ModelRenderer(this, 0, 31);
        this.neck.addBox(0.0f, 0.0f, 0.0f, 5, 5, 13);
        this.neck.setPos(-2.5f, 6.0f, -8.0f);
        this.neck.mirror = true;
        this.setRotation(this.neck, 1.524323f, 0.0f, 0.0f);
        this.la3 = new ModelRenderer(this, 4, 12);
        this.la3.addBox(0.5f, -12.5f, 3.0f, 1, 5, 1);
        this.la3.setPos(0.0f, -9.0f, -6.0f);
        this.la3.mirror = true;
        this.setRotation(this.la3, -0.3346075f, 0.0f, 0.0f);
        this.throatfluff = new ModelRenderer(this, 36, 41);
        this.throatfluff.addBox(0.0f, -2.0f, 0.0f, 4, 3, 5);
        this.throatfluff.setPos(-2.0f, 0.0f, -8.0f);
        this.throatfluff.mirror = true;
        this.setRotation(this.throatfluff, 1.07818f, 0.0f, 0.0f);
        this.rear = new ModelRenderer(this, 18, 0);
        this.rear.addBox(-5.0f, -3.0f, -3.0f, 3, 2, 1);
        this.rear.setPos(0.0f, -9.0f, -6.0f);
        this.rear.mirror = true;
        this.setRotation(this.rear, 0.1047198f, 1.570796f, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
        this.head.setPos(0.0f, -9.0f, -6.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.ra1 = new ModelRenderer(this, 0, 12);
        this.ra1.addBox(-1.5f, -5.0f, 0.0f, 1, 4, 1);
        this.ra1.setPos(0.0f, -9.0f, -6.0f);
        this.ra1.mirror = true;
        this.setRotation(this.ra1, -0.3717861f, 0.0f, 0.0f);
        this.la1 = new ModelRenderer(this, 0, 12);
        this.la1.addBox(0.5f, -5.0f, 0.0f, 1, 4, 1);
        this.la1.setPos(0.0f, -9.0f, -6.0f);
        this.la1.mirror = true;
        this.setRotation(this.la1, -0.3717861f, 0.0f, 0.0f);
        this.la2 = new ModelRenderer(this, 0, 17);
        this.la2.addBox(0.5f, -8.5f, -3.0f, 1, 5, 1);
        this.la2.setPos(0.0f, -9.0f, -6.0f);
        this.la2.mirror = true;
        this.setRotation(this.la2, -1.041001f, 0.0f, 0.0f);
        this.ra2 = new ModelRenderer(this, 0, 17);
        this.ra2.addBox(-1.5f, -8.5f, -3.0f, 1, 5, 1);
        this.ra2.setPos(0.0f, -9.0f, -6.0f);
        this.ra2.mirror = true;
        this.setRotation(this.ra2, -1.041001f, 0.0f, 0.0f);
        this.ra3 = new ModelRenderer(this, 4, 12);
        this.ra3.addBox(-1.5f, -12.5f, 3.0f, 1, 5, 1);
        this.ra3.setPos(0.0f, -9.0f, -6.0f);
        this.ra3.mirror = true;
        this.setRotation(this.ra3, -0.3346075f, 0.0f, 0.0f);
        this.nose = new ModelRenderer(this, 24, 0);
        this.nose.addBox(-2.5f, 0.0f, -7.0f, 5, 3, 5);
        this.nose.setPos(0.0f, -9.0f, -6.0f);
        this.nose.mirror = true;
        this.setRotation(this.nose, 0.0f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer(this, 28, 57);
        this.mouth.addBox(-2.0f, 2.0f, -6.0f, 4, 2, 5);
        this.mouth.setPos(0.0f, -9.0f, -6.0f);
        this.mouth.mirror = true;
        this.setRotation(this.mouth, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Gazelle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Gazelle g = (Gazelle)entity;
        float hf = 0.0f;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.1f * this.wingspeed)) * 3.1415927f * 0.12f * f1 : 0.0f;
        this.lfleg1.xRot = 0.297f + newangle;
        this.lfleg2.xRot = -0.074f + newangle;
        this.lfleg3.xRot = -0.409f + newangle;
        this.lfleg4.xRot = newangle;
        this.rfleg1.xRot = 0.297f - newangle;
        this.rfleg2.xRot = -0.074f - newangle;
        this.rfleg3.xRot = -0.409f - newangle;
        this.rfleg4.xRot = - newangle;
        this.lrleg1.xRot = - newangle;
        this.lrleg2.xRot = 0.185f - newangle;
        this.lrleg3.xRot = -0.074f - newangle;
        this.lrleg4.xRot = -0.409f - newangle;
        this.lrleg5.xRot = - newangle;
        this.rrleg1.xRot = newangle;
        this.rrleg2.xRot = 0.185f + newangle;
        this.rrleg3.xRot = -0.074f + newangle;
        this.rrleg4.xRot = -0.409f + newangle;
        this.rrleg5.xRot = newangle;
        newangle = MathHelper.cos((float)(f2 * 0.5f)) * 3.1415927f * 0.02f;
        this.nose.yRot = this.head.yRot = (float)Math.toRadians(f3) * 0.45f;
        this.mouth.yRot = this.head.yRot;
        this.lear.yRot = 1.57f + this.head.yRot + newangle;
        this.rear.yRot = 1.57f + this.head.yRot + newangle;
        this.la1.yRot = this.head.yRot;
        this.la2.yRot = this.head.yRot;
        this.la3.yRot = this.head.yRot;
        this.ra1.yRot = this.head.yRot;
        this.ra2.yRot = this.head.yRot;
        this.ra3.yRot = this.head.yRot;
        if (!g.isOrderedToSit()) {
            this.tail.xRot = 1.0f + MathHelper.cos((float)(f2 * 0.1f)) * 3.1415927f * 0.06f;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.la3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.throatfluff.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rear.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ra1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.la1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.la2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ra2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ra3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity par7Entity) {
        
    }
}

