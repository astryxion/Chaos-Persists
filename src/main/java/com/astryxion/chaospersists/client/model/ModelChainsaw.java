package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Port of 1.7.10 {@code danger.orespawn.ModelChainsaw} (animation and geometry unchanged).
 */
public class ModelChainsaw extends Model {
    ModelRenderer engine;
    ModelRenderer handle1;
    ModelRenderer handle2;
    ModelRenderer handle3;
    ModelRenderer muffler;
    ModelRenderer blade1;
    ModelRenderer blade2;
    ModelRenderer tooth;
    float toothpos = 0.0f;
    int toothdir = 0;
    float toothpos1 = 7.0f;
    int toothdir1 = 0;
    float toothpos2 = 14.0f;
    int toothdir2 = 0;
    float toothpos3 = 20.0f;
    int toothdir3 = 1;
    float toothpos4 = 13.0f;
    int toothdir4 = 1;
    float toothpos5 = 6.0f;
    int toothdir5 = 1;

    public ModelChainsaw() {
        super(RenderType::entityCutoutNoCull);
        this.engine = new ModelRenderer(this, 0, 19);
        this.engine.addBox(-2.0f, -4.0f, -4.0f, 4, 7, 8);
        this.engine.setPos(0.0f, 0.0f, 0.0f);
        this.engine.mirror = true;
        this.setRotation(this.engine, 0.0f, 0.0f, 0.0f);
        this.handle1 = new ModelRenderer(this, 49, 0);
        this.handle1.addBox(0.0f, -3.0f, 3.0f, 1, 1, 5);
        this.handle1.setPos(0.0f, 0.0f, 0.0f);
        this.handle1.mirror = true;
        this.setRotation(this.handle1, -0.1919862f, 0.0f, 0.0f);
        this.handle2 = new ModelRenderer(this, 50, 13);
        this.handle2.addBox(0.0f, 2.0f, 4.0f, 1, 1, 4);
        this.handle2.setPos(0.0f, 0.0f, 0.0f);
        this.handle2.mirror = true;
        this.setRotation(this.handle2, 0.0f, 0.0f, 0.0f);
        this.handle3 = new ModelRenderer(this, 52, 7);
        this.handle3.addBox(0.0f, -2.0f, 7.0f, 1, 4, 1);
        this.handle3.setPos(0.0f, 0.0f, 0.0f);
        this.handle3.mirror = true;
        this.setRotation(this.handle3, -0.0872665f, 0.0f, 0.0f);
        this.muffler = new ModelRenderer(this, 14, 0);
        this.muffler.addBox(-3.0f, 0.0f, 1.0f, 1, 3, 3);
        this.muffler.setPos(0.0f, 0.0f, 0.0f);
        this.muffler.mirror = true;
        this.setRotation(this.muffler, 0.0f, 0.0f, 0.0f);
        this.blade1 = new ModelRenderer(this, 0, 35);
        this.blade1.addBox(0.0f, -2.0f, -28.0f, 1, 4, 24);
        this.blade1.setPos(0.0f, 0.0f, 0.0f);
        this.blade1.mirror = true;
        this.setRotation(this.blade1, 0.0f, 0.0f, 0.0f);
        this.blade2 = new ModelRenderer(this, 0, 8);
        this.blade2.addBox(0.0f, -2.5f, -2.5f, 1, 5, 5);
        this.blade2.setPos(0.0f, 0.0f, -28.0f);
        this.blade2.mirror = true;
        this.setRotation(this.blade2, 0.0f, 0.0f, 0.0f);
        this.tooth = new ModelRenderer(this, 0, 0);
        this.tooth.addBox(0.0f, -1.0f, -0.5f, 1, 1, 1);
        this.tooth.setPos(0.0f, -2.0f, -5.0f);
        this.tooth.mirror = true;
        this.setRotation(this.tooth, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.renderTooth(matrixStack, buffer, packedLight, packedOverlay);
        this.renderTooth1(matrixStack, buffer, packedLight, packedOverlay);
        this.renderTooth2(matrixStack, buffer, packedLight, packedOverlay);
        this.renderTooth3(matrixStack, buffer, packedLight, packedOverlay);
        this.renderTooth4(matrixStack, buffer, packedLight, packedOverlay);
        this.renderTooth5(matrixStack, buffer, packedLight, packedOverlay);
        this.blade2.xRot = (float) ((double) this.blade2.xRot + 0.10471975511965977);
        if ((double) this.blade2.xRot > 6.283185307179586) {
            this.blade2.xRot = 0.0f;
        }
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.engine);
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.handle1);
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.handle2);
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.handle3);
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.muffler);
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.blade1);
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.blade2);
    }

    private void renderPart(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, ModelRenderer part) {
        part.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    private void renderTooth(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        if (this.toothdir == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos;
            this.toothpos += 0.5f;
            if (this.toothpos > 21.0f) {
                this.toothpos = 21.0f;
                this.toothdir = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos;
            this.toothpos -= 0.5f;
            if (this.toothpos < 0.0f) {
                this.toothpos = 0.0f;
                this.toothdir = 0;
            }
        }
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.tooth);
    }

    private void renderTooth1(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        if (this.toothdir1 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos1;
            this.toothpos1 += 0.5f;
            if (this.toothpos1 > 21.0f) {
                this.toothpos1 = 21.0f;
                this.toothdir1 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos1;
            this.toothpos1 -= 0.5f;
            if (this.toothpos1 < 0.0f) {
                this.toothpos1 = 0.0f;
                this.toothdir1 = 0;
            }
        }
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.tooth);
    }

    private void renderTooth2(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        if (this.toothdir2 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos2;
            this.toothpos2 += 0.5f;
            if (this.toothpos2 > 21.0f) {
                this.toothpos2 = 21.0f;
                this.toothdir2 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos2;
            this.toothpos2 -= 0.5f;
            if (this.toothpos2 < 0.0f) {
                this.toothpos2 = 0.0f;
                this.toothdir2 = 0;
            }
        }
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.tooth);
    }

    private void renderTooth3(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        if (this.toothdir3 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos3;
            this.toothpos3 += 0.5f;
            if (this.toothpos3 > 21.0f) {
                this.toothpos3 = 21.0f;
                this.toothdir3 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos3;
            this.toothpos3 -= 0.5f;
            if (this.toothpos3 < 0.0f) {
                this.toothpos3 = 0.0f;
                this.toothdir3 = 0;
            }
        }
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.tooth);
    }

    private void renderTooth4(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        if (this.toothdir4 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos4;
            this.toothpos4 += 0.5f;
            if (this.toothpos4 > 21.0f) {
                this.toothpos4 = 21.0f;
                this.toothdir4 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos4;
            this.toothpos4 -= 0.5f;
            if (this.toothpos4 < 0.0f) {
                this.toothpos4 = 0.0f;
                this.toothdir4 = 0;
            }
        }
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.tooth);
    }

    private void renderTooth5(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        if (this.toothdir5 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos5;
            this.toothpos5 += 0.5f;
            if (this.toothpos5 > 21.0f) {
                this.toothpos5 = 21.0f;
                this.toothdir5 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos5;
            this.toothpos5 -= 0.5f;
            if (this.toothpos5 < 0.0f) {
                this.toothpos5 = 0.0f;
                this.toothdir5 = 0;
            }
        }
        this.renderPart(matrixStack, buffer, packedLight, packedOverlay, this.tooth);
    }
}
