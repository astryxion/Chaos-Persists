/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Hydrolisc
 *  com.astryxion.chaospersists.ModelHydrolisc
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Hydrolisc;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHydrolisc extends EntityModel<Hydrolisc> {
    private float wingspeed = 1.0f;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer body2;
    ModelRenderer lb2;
    ModelRenderer lb1;
    ModelRenderer spine3;
    ModelRenderer spine4;
    ModelRenderer rb1;
    ModelRenderer rb2;
    ModelRenderer spine1;
    ModelRenderer spine2;
    ModelRenderer lb3;
    ModelRenderer rb3;
    ModelRenderer body1;
    ModelRenderer body0;
    ModelRenderer lf1;
    ModelRenderer rf1;
    ModelRenderer rb6;
    ModelRenderer rb4;
    ModelRenderer rb5;
    ModelRenderer lb6;
    ModelRenderer lb5;
    ModelRenderer lb4;
    ModelRenderer head3;
    ModelRenderer feather3;
    ModelRenderer feather1;
    ModelRenderer feather2;
    ModelRenderer head1;
    ModelRenderer rf2;
    ModelRenderer rf3;
    ModelRenderer rf4;
    ModelRenderer rf5;
    ModelRenderer rf6;
    ModelRenderer lf2;
    ModelRenderer lf3;
    ModelRenderer lf4;
    ModelRenderer lf5;
    ModelRenderer lf6;
    ModelRenderer head2;
    ModelRenderer tail1;

    public ModelHydrolisc(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 64;
        // textureHeight = 128;
        this.tail2 = new ModelRenderer(this, 29, 3);
        this.tail2.addBox(-1.0f, 0.0f, -0.8f, 2, 8, 2);
        this.tail2.setPos(1.0f, 20.0f, 13.53333f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 1.392442f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 39, 0);
        this.tail3.addBox(-1.0f, -1.0f, -2.0f, 2, 8, 2);
        this.tail3.setPos(1.0f, 20.0f, 21.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 1.72705f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 0, 99);
        this.body2.addBox(-2.0f, 14.0f, 0.0f, 6, 4, 10);
        this.body2.setPos(0.0f, 0.0f, 0.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, -0.0523599f, 0.0f, 0.0f);
        this.lb2 = new ModelRenderer(this, 45, 13);
        this.lb2.addBox(0.0f, 0.0f, 3.0f, 3, 2, 5);
        this.lb2.setPos(5.0f, 15.0f, 0.0f);
        this.lb2.mirror = true;
        this.setRotation(this.lb2, -0.4886922f, 0.0f, 0.0f);
        this.lb1 = new ModelRenderer(this, 46, 22);
        this.lb1.addBox(-1.0f, 0.0f, 0.0f, 4, 3, 3);
        this.lb1.setPos(5.0f, 15.0f, 0.0f);
        this.lb1.mirror = true;
        this.setRotation(this.lb1, 0.0f, 0.0f, 0.0f);
        this.spine3 = new ModelRenderer(this, 11, 31);
        this.spine3.addBox(-1.0f, -5.0f, 0.0f, 2, 6, 2);
        this.spine3.setPos(1.0f, 14.0f, 6.0f);
        this.spine3.mirror = true;
        this.setRotation(this.spine3, -1.117011f, 0.0f, 0.0f);
        this.spine4 = new ModelRenderer(this, 0, 30);
        this.spine4.addBox(-1.0f, -10.5f, -1.0f, 2, 6, 2);
        this.spine4.setPos(1.0f, 14.0f, 6.0f);
        this.spine4.mirror = true;
        this.setRotation(this.spine4, -1.343904f, 0.0f, 0.0f);
        this.rb1 = new ModelRenderer(this, 46, 22);
        this.rb1.addBox(-4.0f, 0.0f, 0.0f, 4, 3, 3);
        this.rb1.setPos(-2.0f, 15.0f, 0.0f);
        this.rb1.mirror = true;
        this.setRotation(this.rb1, 0.0f, 0.0f, 0.0f);
        this.rb2 = new ModelRenderer(this, 45, 13);
        this.rb2.addBox(-4.0f, 0.0f, 2.0f, 3, 2, 5);
        this.rb2.setPos(-2.0f, 15.0f, 0.0f);
        this.rb2.mirror = true;
        this.setRotation(this.rb2, -0.4886922f, 0.0f, 0.0f);
        this.spine1 = new ModelRenderer(this, 33, 19);
        this.spine1.addBox(-1.0f, -5.0f, 0.0f, 2, 6, 2);
        this.spine1.setPos(1.0f, 14.0f, 0.0f);
        this.spine1.mirror = true;
        this.setRotation(this.spine1, -0.8552113f, 0.0f, 0.0f);
        this.spine2 = new ModelRenderer(this, 21, 19);
        this.spine2.addBox(-1.0f, -10.5f, -1.5f, 2, 6, 2);
        this.spine2.setPos(1.0f, 14.0f, 0.0f);
        this.spine2.mirror = true;
        this.setRotation(this.spine2, -1.169371f, 0.0f, 0.0f);
        this.lb3 = new ModelRenderer(this, 0, 58);
        this.lb3.addBox(0.0f, -8.0f, -2.0f, 3, 2, 6);
        this.lb3.setPos(5.0f, 15.0f, 0.0f);
        this.lb3.mirror = true;
        this.setRotation(this.lb3, -2.347623f, 0.0f, 0.0f);
        this.rb3 = new ModelRenderer(this, 0, 58);
        this.rb3.addBox(-4.0f, -8.0f, -2.0f, 3, 2, 6);
        this.rb3.setPos(-2.0f, 15.0f, 0.0f);
        this.rb3.mirror = true;
        this.setRotation(this.rb3, -2.347623f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer(this, 0, 79);
        this.body1.addBox(-2.0f, 16.0f, -7.0f, 4, 2, 5);
        this.body1.setPos(1.0f, -1.0f, 2.0f);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.body0 = new ModelRenderer(this, 0, 0);
        this.body0.addBox(-1.0f, 14.0f, -13.0f, 4, 3, 10);
        this.body0.setPos(0.0f, 0.0f, 0.0f);
        this.body0.mirror = true;
        this.setRotation(this.body0, 0.0523599f, 0.0f, 0.0f);
        this.lf1 = new ModelRenderer(this, 45, 32);
        this.lf1.addBox(-1.0f, 0.0f, -2.0f, 4, 3, 3);
        this.lf1.setPos(4.0f, 14.0f, -7.0f);
        this.lf1.mirror = true;
        this.setRotation(this.lf1, 0.0f, 0.0f, 0.0f);
        this.rf1 = new ModelRenderer(this, 45, 32);
        this.rf1.addBox(-3.0f, 0.0f, -2.0f, 4, 3, 3);
        this.rf1.setPos(-2.0f, 14.0f, -7.0f);
        this.rf1.mirror = true;
        this.setRotation(this.rf1, 0.0f, 0.0f, 0.0f);
        this.rb6 = new ModelRenderer(this, 30, 39);
        this.rb6.addBox(-3.5f, 7.0f, 2.0f, 2, 3, 1);
        this.rb6.setPos(-2.0f, 15.0f, 0.0f);
        this.rb6.mirror = true;
        this.setRotation(this.rb6, 0.1745329f, 0.0f, 0.0f);
        this.rb4 = new ModelRenderer(this, 20, 39);
        this.rb4.addBox(-2.0f, 3.0f, 6.0f, 1, 4, 1);
        this.rb4.setPos(-2.0f, 15.0f, 0.0f);
        this.rb4.mirror = true;
        this.setRotation(this.rb4, -0.6283185f, 0.0f, 0.0f);
        this.rb5 = new ModelRenderer(this, 20, 39);
        this.rb5.addBox(-4.0f, 3.0f, 6.0f, 1, 4, 1);
        this.rb5.setPos(-2.0f, 15.0f, 0.0f);
        this.rb5.mirror = true;
        this.setRotation(this.rb5, -0.6283185f, 0.0f, 0.0f);
        this.lb6 = new ModelRenderer(this, 30, 39);
        this.lb6.addBox(0.5f, 7.0f, 2.0f, 2, 3, 1);
        this.lb6.setPos(5.0f, 15.0f, 0.0f);
        this.lb6.mirror = true;
        this.setRotation(this.lb6, 0.1745329f, 0.0f, 0.0f);
        this.lb5 = new ModelRenderer(this, 20, 39);
        this.lb5.addBox(2.0f, 3.0f, 6.0f, 1, 4, 1);
        this.lb5.setPos(5.0f, 15.0f, 0.0f);
        this.lb5.mirror = true;
        this.setRotation(this.lb5, -0.6283185f, 0.0f, 0.0f);
        this.lb4 = new ModelRenderer(this, 20, 39);
        this.lb4.addBox(0.0f, 3.0f, 6.0f, 1, 4, 1);
        this.lb4.setPos(5.0f, 15.0f, 0.0f);
        this.lb4.mirror = true;
        this.setRotation(this.lb4, -0.6283185f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer(this, 38, 50);
        this.head3.addBox(0.0f, 0.0f, 0.0f, 4, 2, 8);
        this.head3.setPos(-1.0f, 15.0f, -13.0f);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.5235988f, 0.0f, 0.0f);
        this.feather3 = new ModelRenderer(this, 25, 117);
        this.feather3.addBox(0.0f, 0.0f, 1.0f, 1, 2, 9);
        this.feather3.setPos(1.0f, 12.0f, -8.0f);
        this.feather3.mirror = true;
        this.setRotation(this.feather3, 0.3490659f, 0.2617994f, 0.0f);
        this.feather1 = new ModelRenderer(this, 34, 100);
        this.feather1.addBox(0.0f, 0.0f, 1.0f, 1, 2, 9);
        this.feather1.setPos(0.0f, 12.0f, -8.0f);
        this.feather1.mirror = true;
        this.setRotation(this.feather1, 0.3490659f, -0.2617994f, 0.0f);
        this.feather2 = new ModelRenderer(this, 0, 116);
        this.feather2.addBox(0.0f, 0.0f, 0.0f, 1, 2, 10);
        this.feather2.setPos(0.5f, 11.0f, -6.0f);
        this.feather2.mirror = true;
        this.setRotation(this.feather2, 0.3490659f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer(this, 38, 41);
        this.head1.addBox(0.0f, 0.0f, 0.0f, 4, 3, 4);
        this.head1.setPos(-1.0f, 15.0f, -15.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.1396263f, 0.0f, 0.0f);
        this.rf2 = new ModelRenderer(this, 19, 58);
        this.rf2.addBox(-3.0f, 0.0f, 0.0f, 3, 3, 6);
        this.rf2.setPos(-2.0f, 14.0f, -7.0f);
        this.rf2.mirror = true;
        this.setRotation(this.rf2, -0.4886922f, 0.0f, 0.0f);
        this.rf3 = new ModelRenderer(this, 19, 47);
        this.rf3.addBox(-3.0f, -7.0f, 0.0f, 3, 3, 6);
        this.rf3.setPos(-2.0f, 14.0f, -7.0f);
        this.rf3.mirror = true;
        this.setRotation(this.rf3, -2.347623f, 0.0f, 0.0f);
        this.rf4 = new ModelRenderer(this, 20, 39);
        this.rf4.addBox(0.0f, 6.0f, 4.0f, 1, 4, 1);
        this.rf4.setPos(-3.0f, 14.0f, -7.0f);
        this.rf4.mirror = true;
        this.setRotation(this.rf4, -0.6283185f, 0.0f, 0.0f);
        this.rf5 = new ModelRenderer(this, 20, 39);
        this.rf5.addBox(-2.0f, 6.0f, 4.0f, 1, 4, 1);
        this.rf5.setPos(-3.0f, 14.0f, -7.0f);
        this.rf5.mirror = true;
        this.setRotation(this.rf5, -0.6283185f, 0.0f, 0.0f);
        this.rf6 = new ModelRenderer(this, 30, 39);
        this.rf6.addBox(-2.5f, 6.0f, 0.0f, 2, 5, 1);
        this.rf6.setPos(-2.0f, 14.0f, -7.0f);
        this.rf6.mirror = true;
        this.setRotation(this.rf6, 0.1745329f, 0.0f, 0.0f);
        this.lf2 = new ModelRenderer(this, 19, 58);
        this.lf2.addBox(0.0f, 0.0f, 0.0f, 3, 3, 6);
        this.lf2.setPos(4.0f, 14.0f, -7.0f);
        this.lf2.mirror = true;
        this.setRotation(this.lf2, -0.4886922f, 0.0f, 0.0f);
        this.lf3 = new ModelRenderer(this, 19, 47);
        this.lf3.addBox(0.0f, -7.0f, 0.0f, 3, 3, 6);
        this.lf3.setPos(4.0f, 14.0f, -7.0f);
        this.lf3.mirror = true;
        this.setRotation(this.lf3, -2.347623f, 0.0f, 0.0f);
        this.lf4 = new ModelRenderer(this, 20, 39);
        this.lf4.addBox(0.0f, 6.0f, 4.0f, 1, 4, 1);
        this.lf4.setPos(4.0f, 14.0f, -7.0f);
        this.lf4.mirror = true;
        this.setRotation(this.lf4, -0.6283185f, 0.0f, 0.0f);
        this.lf5 = new ModelRenderer(this, 20, 39);
        this.lf5.addBox(2.0f, 6.0f, 4.0f, 1, 4, 1);
        this.lf5.setPos(4.0f, 14.0f, -7.0f);
        this.lf5.mirror = true;
        this.setRotation(this.lf5, -0.6283185f, 0.0f, 0.0f);
        this.lf6 = new ModelRenderer(this, 30, 39);
        this.lf6.addBox(0.5f, 6.0f, -2.0f, 2, 5, 1);
        this.lf6.setPos(4.0f, 14.0f, -5.0f);
        this.lf6.mirror = true;
        this.setRotation(this.lf6, 0.1745329f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 19, 80);
        this.head2.addBox(-1.0f, 16.0f, -16.0f, 4, 1, 5);
        this.head2.setPos(0.0f, 0.0f, 0.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.1047198f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 9, 18);
        this.tail1.addBox(-1.0f, -1.0f, -3.0f, 2, 8, 2);
        this.tail1.setPos(1.0f, 15.0f, 9.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 1.095163f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(Hydrolisc entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        Hydrolisc c = (Hydrolisc)entity;
        float hf = 0.0f;
        float newangle = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        newangle = (double)f1 > 0.1 ? MathHelper.cos((float)(f2 * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1 : 0.0f;
        this.lf1.xRot = newangle;
        this.lf2.xRot = newangle - 0.488f;
        this.lf3.xRot = newangle - 2.347f;
        this.lf4.xRot = newangle - 0.628f;
        this.lf5.xRot = newangle - 0.628f;
        this.lf6.xRot = newangle + 0.174f;
        this.rf1.xRot = - newangle;
        this.rf2.xRot = - newangle - 0.488f;
        this.rf3.xRot = - newangle - 2.347f;
        this.rf4.xRot = - newangle - 0.628f;
        this.rf5.xRot = - newangle - 0.628f;
        this.rf6.xRot = - newangle + 0.174f;
        this.lb1.xRot = - newangle;
        this.lb2.xRot = - newangle - 0.488f;
        this.lb3.xRot = - newangle - 2.347f;
        this.lb4.xRot = - newangle - 0.628f;
        this.lb5.xRot = - newangle - 0.628f;
        this.lb6.xRot = - newangle + 0.174f;
        this.rb1.xRot = newangle;
        this.rb2.xRot = newangle - 0.488f;
        this.rb3.xRot = newangle - 2.347f;
        this.rb4.xRot = newangle - 0.628f;
        this.rb5.xRot = newangle - 0.628f;
        this.rb6.xRot = newangle + 0.174f;
        newangle = MathHelper.cos((float)(f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (c.isOrderedToSit()) {
            newangle = 0.0f;
        }
        this.tail1.yRot = newangle * 0.25f;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 5.0f;
        this.tail2.x = this.tail1.x + (float)Math.sin(this.tail1.yRot) * 5.0f;
        this.tail2.yRot = newangle * 0.5f;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 8.0f;
        this.tail3.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 8.0f;
        this.tail3.yRot = newangle * 0.75f;
        hf = (float)c.getHydroHealth() / c.getMaxHealth();
        this.feather2.yRot = newangle = MathHelper.cos((float)(f2 * 1.25f * this.wingspeed * hf)) * 3.1415927f * 0.2f * hf;
        newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed * hf)) * 3.1415927f * 0.2f * hf;
        this.feather1.yRot = newangle - 0.9f;
        this.feather3.yRot = - newangle + 0.9f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lb2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lb1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rb1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rb2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lb3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rb3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rb6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rb4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rb5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lb6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lb5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lb4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

