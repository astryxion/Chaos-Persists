package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.IslandToo;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelIslandToo extends EntityModel<IslandToo> {
    private final float wingspeed = 1.0f;
    private final ModelRenderer Shape1;
    private final ModelRenderer Shape2;
    private final ModelRenderer Shape3;

    public ModelIslandToo(float f) {
        super();
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.Shape1.setPos(0.0f, 16.0f, 0.0f);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer(this, 32, 0);
        this.Shape2.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.Shape2.setPos(0.0f, 16.0f, 0.0f);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.7853982f, 0.7853982f, 0.7853982f);
        this.Shape3 = new ModelRenderer(this, 32, 16);
        this.Shape3.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.Shape3.setPos(0.0f, 16.0f, 0.0f);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.7853982f, 0.7853982f, 0.7853982f);
    }

    @Override
    public void setupAnim(IslandToo entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f2 = ageInTicks;
        float newangle = 0.0f;
        this.Shape1.xRot = newangle = MathHelper.cos((float)(f2 * 0.05f * this.wingspeed)) * 3.1415927f;
        this.Shape1.yRot = newangle = MathHelper.cos((float)(f2 * 0.051f * this.wingspeed)) * 3.1415927f;
        this.Shape1.zRot = newangle = MathHelper.cos((float)(f2 * 0.052f * this.wingspeed)) * 3.1415927f;
        this.Shape2.xRot = newangle = MathHelper.cos((float)(f2 * 0.053f * this.wingspeed)) * 3.1415927f;
        this.Shape2.yRot = newangle = MathHelper.cos((float)(f2 * 0.054f * this.wingspeed)) * 3.1415927f;
        this.Shape2.zRot = newangle = MathHelper.cos((float)(f2 * 0.055f * this.wingspeed)) * 3.1415927f;
        this.Shape3.xRot = newangle = MathHelper.cos((float)(f2 * 0.056f * this.wingspeed)) * 3.1415927f;
        this.Shape3.yRot = newangle = MathHelper.cos((float)(f2 * 0.057f * this.wingspeed)) * 3.1415927f;
        this.Shape3.zRot = newangle = MathHelper.cos((float)(f2 * 0.058f * this.wingspeed)) * 3.1415927f;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}
