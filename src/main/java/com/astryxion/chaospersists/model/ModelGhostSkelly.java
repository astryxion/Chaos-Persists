/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.GhostSkelly
 *  com.astryxion.chaospersists.ModelGhostSkelly
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.render.RenderInfo;
import java.util.Random;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ModelGhostSkelly extends EntityModel<GhostSkelly> {
    ModelRenderer body;
    ModelRenderer shirt;
    ModelRenderer head;
    ModelRenderer stem;
    ModelRenderer rarm;
    ModelRenderer larm;
    ModelRenderer rsleeve;
    ModelRenderer lsleeve;
    ModelRenderer lchains;
    ModelRenderer rchains;

    public ModelGhostSkelly() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 128;
        // textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 1, 21, 1);
        this.body.setPos(0.0f, -1.0f, 0.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.shirt = new ModelRenderer(this, 42, 43);
        this.shirt.addBox(-2.0f, 0.0f, -2.0f, 5, 12, 5);
        this.shirt.setPos(0.0f, 0.0f, 0.0f);
        this.shirt.mirror = true;
        this.setRotation(this.shirt, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 40, 29);
        this.head.addBox(-3.0f, 0.0f, -3.0f, 7, 5, 7);
        this.head.setPos(0.0f, -6.0f, 0.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.stem = new ModelRenderer(this, 49, 23);
        this.stem.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.stem.setPos(0.0f, -8.0f, 0.0f);
        this.stem.mirror = true;
        this.setRotation(this.stem, 0.1745329f, 0.0f, 0.1745329f);
        this.rarm = new ModelRenderer(this, 26, 0);
        this.rarm.addBox(-14.0f, 0.0f, 0.0f, 15, 1, 1);
        this.rarm.setPos(0.0f, 0.0f, 0.0f);
        this.rarm.mirror = true;
        this.setRotation(this.rarm, 0.0f, 0.0f, 0.0f);
        this.larm = new ModelRenderer(this, 63, 0);
        this.larm.addBox(0.0f, 0.0f, 0.0f, 15, 1, 1);
        this.larm.setPos(0.0f, 0.0f, 0.0f);
        this.larm.mirror = true;
        this.setRotation(this.larm, 0.0f, 0.0f, 0.0f);
        this.rsleeve = new ModelRenderer(this, 31, 7);
        this.rsleeve.addBox(-11.0f, 0.0f, -1.0f, 9, 8, 3);
        this.rsleeve.setPos(0.0f, 0.0f, 0.0f);
        this.rsleeve.mirror = true;
        this.setRotation(this.rsleeve, 0.0f, 0.0f, 0.0f);
        this.lsleeve = new ModelRenderer(this, 71, 7);
        this.lsleeve.addBox(3.0f, 0.0f, -1.0f, 9, 8, 3);
        this.lsleeve.setPos(0.0f, 0.0f, 0.0f);
        this.lsleeve.mirror = true;
        this.setRotation(this.lsleeve, 0.0f, 0.0f, 0.0f);
        this.lchains = new ModelRenderer(this, 98, 0);
        this.lchains.addBox(11.0f, -1.0f, 0.0f, 3, 16, 1);
        this.lchains.setPos(0.0f, 0.0f, 0.0f);
        this.lchains.mirror = true;
        this.setRotation(this.lchains, 0.0f, 0.0f, 0.0f);
        this.rchains = new ModelRenderer(this, 12, 0);
        this.rchains.addBox(-13.0f, -1.0f, 0.0f, 3, 10, 1);
        this.rchains.setPos(0.0f, 0.0f, 0.0f);
        this.rchains.mirror = true;
        this.setRotation(this.rchains, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void setupAnim(GhostSkelly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        GhostSkelly e = entity;
        RenderInfo r = null;
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0625F;
        float newangle = 0.0f;
        float newrf1 = 0.0f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        r = e.getRenderInfo();
        this.lsleeve.zRot = this.lchains.zRot = MathHelper.cos((float)(f2 * 0.2f)) * 3.1415927f * 0.05f;
        this.larm.zRot = this.lchains.zRot;
        this.rsleeve.zRot = this.rchains.zRot = MathHelper.cos((float)(f2 * 0.22f)) * 3.1415927f * 0.05f;
        this.rarm.zRot = this.rchains.zRot;
        this.lsleeve.yRot = this.lchains.yRot = MathHelper.cos((float)(f2 * 0.24f)) * 3.1415927f * 0.05f;
        this.larm.yRot = this.lchains.yRot;
        this.rsleeve.yRot = this.rchains.yRot = MathHelper.cos((float)(f2 * 0.26f)) * 3.1415927f * 0.05f;
        this.rarm.yRot = this.rchains.yRot;
        newangle = MathHelper.cos((float)(f2 * 0.05f)) * 3.1415927f * 2.0f;
        newrf1 = f2 * 0.05f % 6.2831855f;
        newrf1 = Math.abs(newrf1);
        if (newrf1 < r.rf2) {
            r.ri2 = 0;
            if (e.level.random.nextInt(3) == 1) {
                r.ri2 |= 1;
            }
        }
        r.rf2 = newrf1;
        if ((r.ri2 & 1) == 0) {
            newangle = 0.0f;
        }
        this.head.yRot = newangle;
        e.setRenderInfo(r);    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        matrixStack.pushPose();
                GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)0.75f, (float)0.75f, (float)0.75f, (float)0.25f);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.shirt.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.stem.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.rarm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.larm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.rsleeve.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.lsleeve.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.lchains.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        this.rchains.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
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

