/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ModelWormLarge
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.WormLarge;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWormLarge extends EntityModel<WormLarge> {
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer neck1;
    ModelRenderer neck4;
    ModelRenderer neck5;
    ModelRenderer neck2;
    ModelRenderer neck3;
    ModelRenderer tail1;
    ModelRenderer tailtip;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tooth1;
    ModelRenderer tooth2;
    ModelRenderer tooth3;
    ModelRenderer tooth4;
    ModelRenderer tooth5;
    ModelRenderer tooth6;
    ModelRenderer tooth7;
    ModelRenderer tooth8;

    public ModelWormLarge() {
        super(RenderType::entityCutoutNoCull);
        // textureWidth = 256;
        // textureHeight = 256;
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.addBox(-8.0f, -8.0f, -20.0f, 16, 16, 20);
        this.head1.setPos(0.0f, 0.0f, 10.0f);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer(this, 83, 27);
        this.head2.addBox(8.0f, -3.0f, -20.0f, 3, 6, 19);
        this.head2.setPos(0.0f, 0.0f, 10.0f);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer(this, 9, 65);
        this.head3.addBox(-11.0f, -3.0f, -20.0f, 3, 6, 19);
        this.head3.setPos(0.0f, 0.0f, 10.0f);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.head4 = new ModelRenderer(this, 77, 0);
        this.head4.addBox(-3.0f, -11.0f, -20.0f, 6, 3, 20);
        this.head4.setPos(0.0f, 0.0f, 10.0f);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.0f, 0.0f, 0.0f);
        this.head5 = new ModelRenderer(this, 10, 39);
        this.head5.addBox(-3.0f, 8.0f, -20.0f, 6, 3, 20);
        this.head5.setPos(0.0f, 0.0f, 10.0f);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 25, 94);
        this.neck1.addBox(-6.0f, -6.0f, -36.0f, 12, 12, 36);
        this.neck1.setPos(0.0f, 20.0f, 33.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, -0.6981317f, 0.0f, 0.0f);
        this.neck4 = new ModelRenderer(this, 25, 146);
        this.neck4.addBox(-2.0f, -8.0f, -38.0f, 4, 2, 38);
        this.neck4.setPos(0.0f, 20.0f, 33.0f);
        this.neck4.mirror = true;
        this.setRotation(this.neck4, -0.6981317f, 0.0f, 0.0f);
        this.neck5 = new ModelRenderer(this, 125, 189);
        this.neck5.addBox(-2.0f, 6.0f, -31.0f, 4, 2, 31);
        this.neck5.setPos(0.0f, 20.0f, 33.0f);
        this.neck5.mirror = true;
        this.setRotation(this.neck5, -0.6981317f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 25, 189);
        this.neck2.addBox(6.0f, -2.0f, -34.0f, 2, 4, 34);
        this.neck2.setPos(0.0f, 20.0f, 33.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, -0.6981317f, 0.0f, 0.0f);
        this.neck3 = new ModelRenderer(this, 125, 147);
        this.neck3.addBox(-8.0f, -2.0f, -34.0f, 2, 4, 34);
        this.neck3.setPos(0.0f, 20.0f, 33.0f);
        this.neck3.mirror = true;
        this.setRotation(this.neck3, -0.6981317f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 145, 21);
        this.tail1.addBox(-4.0f, -4.0f, 0.0f, 8, 8, 24);
        this.tail1.setPos(0.0f, 20.0f, 29.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.tailtip = new ModelRenderer(this, 180, 0);
        this.tailtip.addBox(-1.5f, -1.5f, 0.0f, 3, 3, 12);
        this.tailtip.setPos(0.0f, 19.5f, 52.0f);
        this.tailtip.mirror = true;
        this.setRotation(this.tailtip, 0.3490659f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 145, 56);
        this.tail2.addBox(4.0f, -1.0f, 2.0f, 1, 2, 14);
        this.tail2.setPos(0.0f, 20.0f, 29.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 145, 90);
        this.tail3.addBox(-5.0f, -1.0f, 2.0f, 1, 2, 14);
        this.tail3.setPos(0.0f, 20.0f, 29.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.0f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer(this, 145, 76);
        this.tail4.addBox(-1.0f, -5.0f, 7.0f, 2, 1, 9);
        this.tail4.setPos(0.0f, 20.0f, 29.0f);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, 0.0f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer(this, 0, 220);
        this.tooth1.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth1.setPos(0.0f, 9.0f, -10.0f);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, 0.0f, 0.0f, 0.0f);
        this.tooth2 = new ModelRenderer(this, 0, 210);
        this.tooth2.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth2.setPos(0.0f, -9.0f, -10.0f);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, 0.0f, 0.0f, 0.0f);
        this.tooth3 = new ModelRenderer(this, 0, 200);
        this.tooth3.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth3.setPos(9.0f, 0.0f, -10.0f);
        this.tooth3.mirror = true;
        this.setRotation(this.tooth3, 0.0f, 0.0f, 0.0f);
        this.tooth4 = new ModelRenderer(this, 0, 190);
        this.tooth4.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth4.setPos(-9.0f, 0.0f, -10.0f);
        this.tooth4.mirror = true;
        this.setRotation(this.tooth4, 0.0f, 0.0f, 0.0f);
        this.tooth5 = new ModelRenderer(this, 0, 180);
        this.tooth5.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth5.setPos(-6.0f, -6.0f, -10.0f);
        this.tooth5.mirror = true;
        this.setRotation(this.tooth5, 0.0f, 0.0f, 0.0f);
        this.tooth6 = new ModelRenderer(this, 0, 170);
        this.tooth6.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth6.setPos(6.0f, 6.0f, -10.0f);
        this.tooth6.mirror = true;
        this.setRotation(this.tooth6, 0.0f, 0.0f, 0.0f);
        this.tooth7 = new ModelRenderer(this, 0, 160);
        this.tooth7.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth7.setPos(6.0f, -6.0f, -10.0f);
        this.tooth7.mirror = true;
        this.setRotation(this.tooth7, 0.0f, 0.0f, 0.0f);
        this.tooth8 = new ModelRenderer(this, 0, 150);
        this.tooth8.addBox(-0.5f, -0.5f, -7.0f, 1, 1, 7);
        this.tooth8.setPos(-6.0f, 6.0f, -10.0f);
        this.tooth8.mirror = true;
        this.setRotation(this.tooth8, 0.0f, 0.0f, 0.0f);
    }
    @Override
    public void setupAnim(com.astryxion.chaospersists.entity.WormLarge entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;

        float newangle2;
        double dist = 32.0;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float newangle = MathHelper.cos((float)(f2 * 0.25f)) * 3.1415927f * 0.08f;
        this.neck1.xRot = newangle -= 0.698f;
        this.neck1.yRot = newangle2 = MathHelper.cos((float)(f2 * 0.15f)) * 3.1415927f * 0.07f;
        this.neck4.xRot = this.neck5.xRot = this.neck1.xRot;
        this.neck3.xRot = this.neck5.xRot;
        this.neck2.xRot = this.neck5.xRot;
        this.neck4.yRot = this.neck5.yRot = this.neck1.yRot;
        this.neck3.yRot = this.neck5.yRot;
        this.neck2.yRot = this.neck5.yRot;
        double d1 = (float)(Math.cos(newangle) * dist);
        double d2 = (float)(Math.sin(newangle) * dist);
        this.head1.z = (float)((double)this.neck1.z - d1);
        double d3 = (float)(Math.sin(newangle2) * d1);
        double d4 = (float)(Math.cos(newangle2) * d1);
        this.head1.x = (float)((double)this.neck1.x - d3);
        this.head1.y = (float)((double)this.neck1.y + d2);
        this.head1.xRot = newangle = MathHelper.cos((float)(f2 * 0.35f)) * 3.1415927f * 0.15f;
        this.head1.yRot = newangle2 = MathHelper.cos((float)(f2 * 0.45f)) * 3.1415927f * 0.05f;
        this.head4.x = this.head5.x = this.head1.x;
        this.head3.x = this.head5.x;
        this.head2.x = this.head5.x;
        this.head4.y = this.head5.y = this.head1.y;
        this.head3.y = this.head5.y;
        this.head2.y = this.head5.y;
        this.head4.z = this.head5.z = this.head1.z;
        this.head3.z = this.head5.z;
        this.head2.z = this.head5.z;
        this.head4.xRot = this.head5.xRot = this.head1.xRot;
        this.head3.xRot = this.head5.xRot;
        this.head2.xRot = this.head5.xRot;
        this.head4.yRot = this.head5.yRot = this.head1.yRot;
        this.head3.yRot = this.head5.yRot;
        this.head2.yRot = this.head5.yRot;
        dist = 19.0;
        d1 = (float)(Math.cos(newangle) * dist);
        d2 = (float)(Math.sin(newangle) * dist);
        this.tooth1.z = (float)((double)this.head1.z - d1);
        d3 = (float)(Math.sin(newangle2) * d1);
        d4 = (float)(Math.cos(newangle2) * d1);
        this.tooth1.x = (float)((double)this.head1.x - d3);
        this.tooth1.y = (float)((double)this.head1.y + d2 - 9.0);
        this.tooth2.z = this.tooth1.z;
        this.tooth2.x = this.tooth1.x;
        this.tooth2.y = this.tooth1.y + 18.0f;
        this.tooth3.z = this.tooth1.z;
        this.tooth3.x = this.tooth1.x + 9.0f;
        this.tooth3.y = this.tooth1.y + 9.0f;
        this.tooth4.z = this.tooth1.z;
        this.tooth4.x = this.tooth1.x - 9.0f;
        this.tooth4.y = this.tooth1.y + 9.0f;
        this.tooth5.z = this.tooth1.z;
        this.tooth5.x = this.tooth1.x - 6.0f;
        this.tooth5.y = this.tooth1.y + 9.0f - 6.0f;
        this.tooth6.z = this.tooth1.z;
        this.tooth6.x = this.tooth1.x + 6.0f;
        this.tooth6.y = this.tooth1.y + 9.0f + 6.0f;
        this.tooth7.z = this.tooth1.z;
        this.tooth7.x = this.tooth1.x + 6.0f;
        this.tooth7.y = this.tooth1.y + 9.0f - 6.0f;
        this.tooth8.z = this.tooth1.z;
        this.tooth8.x = this.tooth1.x - 6.0f;
        this.tooth8.y = this.tooth1.y + 9.0f + 6.0f;
        this.tooth1.z = (float)((double)this.tooth1.z - Math.sin(this.head1.xRot) * 9.0);
        this.tooth2.z = (float)((double)this.tooth2.z + Math.sin(this.head1.xRot) * 9.0);
        this.tooth3.z = (float)((double)this.tooth3.z - Math.sin(this.head1.yRot) * 9.0);
        this.tooth4.z = (float)((double)this.tooth4.z + Math.sin(this.head1.yRot) * 9.0);
        this.tooth7.z = (float)((double)this.tooth7.z - Math.sin(this.head1.xRot) * 6.0);
        this.tooth7.z = (float)((double)this.tooth7.z - Math.sin(this.head1.yRot) * 6.0);
        this.tooth6.z = (float)((double)this.tooth6.z + Math.sin(this.head1.xRot) * 6.0);
        this.tooth6.z = (float)((double)this.tooth6.z - Math.sin(this.head1.yRot) * 6.0);
        this.tooth5.z = (float)((double)this.tooth5.z - Math.sin(this.head1.xRot) * 6.0);
        this.tooth5.z = (float)((double)this.tooth5.z + Math.sin(this.head1.yRot) * 6.0);
        this.tooth8.z = (float)((double)this.tooth8.z + Math.sin(this.head1.xRot) * 6.0);
        this.tooth8.z = (float)((double)this.tooth8.z + Math.sin(this.head1.yRot) * 6.0);
        newangle = MathHelper.cos((float)(f2 * 0.57f)) * 3.1415927f * 0.35f;
        this.tooth1.xRot = this.head1.xRot + newangle;
        this.tooth2.xRot = this.head1.xRot - newangle;
        this.tooth3.yRot = this.head1.yRot + newangle;
        this.tooth4.yRot = this.head1.yRot - newangle;
        this.tooth5.xRot = this.head1.xRot + newangle;
        this.tooth7.xRot = this.head1.xRot + newangle;
        this.tooth6.xRot = this.head1.xRot - newangle;
        this.tooth8.xRot = this.head1.xRot - newangle;
        this.tooth6.yRot = this.head1.yRot + newangle;
        this.tooth7.yRot = this.head1.yRot + newangle;
        this.tooth5.yRot = this.head1.yRot - newangle;
        this.tooth8.yRot = this.head1.yRot - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.63f)) * 3.1415927f * 0.15f;
        this.tailtip.xRot = newangle + 0.35f;
        this.tailtip.yRot = newangle = MathHelper.cos((float)((float)((double)(f2 * 0.63f) + 1.57075))) * 3.1415927f * 0.15f;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailtip.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, WormLarge par7Entity) {
        
    }
}

