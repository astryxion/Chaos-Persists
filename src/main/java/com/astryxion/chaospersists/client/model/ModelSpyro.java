package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Spyro;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ModelSpyro extends EntityModel<Spyro> {
    private final float wingspeed;
    private final ModelPart RightFrontPaw;
    private final ModelPart WingLeft;
    private final ModelPart LegRightFrontTop;
    private final ModelPart LegRightFrontBottom;
    private final ModelPart LegRightBackTop;
    private final ModelPart LegRightBackBottom;
    private final ModelPart RightBackPaw;
    private final ModelPart LegLeftFrontTop;
    private final ModelPart SnoutRight;
    private final ModelPart LeftFrontPaw;
    private final ModelPart LegLeftBackTop;
    private final ModelPart LegLeftBackBottom;
    private final ModelPart LeftBackPaw;
    private final ModelPart LegLeftFrontBottom;
    private final ModelPart TailPieceSmall;
    private final ModelPart JawPiece;
    private final ModelPart HeadPieceBottom;
    private final ModelPart HeadPieceTop;
    private final ModelPart HornRightBottom;
    private final ModelPart HornLeftBottom;
    private final ModelPart HornRightTop;
    private final ModelPart HornLeftTop;
    private final ModelPart Torso;
    private final ModelPart SnoutLeft;
    private final ModelPart WingPieceLeft;
    private final ModelPart WingRight;
    private final ModelPart WingPieceRight;
    private final ModelPart Neck;
    private final ModelPart TailBack;
    private final ModelPart TailFront;
    private final ModelPart ScaleBackHead;
    private final ModelPart TailPieceLarge;
    private final ModelPart ScaleTailPiece;
    private final ModelPart ScaleHead;
    private final ModelPart ScaleTop1;
    private final ModelPart ScaleBackPiece1;
    private final ModelPart ScaleBackPiece2;

    public ModelSpyro(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot(), wingspeed);
    }

    public ModelSpyro(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.RightFrontPaw = root.getChild("RightFrontPaw");
        this.WingLeft = root.getChild("WingLeft");
        this.LegRightFrontTop = root.getChild("LegRightFrontTop");
        this.LegRightFrontBottom = root.getChild("LegRightFrontBottom");
        this.LegRightBackTop = root.getChild("LegRightBackTop");
        this.LegRightBackBottom = root.getChild("LegRightBackBottom");
        this.RightBackPaw = root.getChild("RightBackPaw");
        this.LegLeftFrontTop = root.getChild("LegLeftFrontTop");
        this.SnoutRight = root.getChild("SnoutRight");
        this.LeftFrontPaw = root.getChild("LeftFrontPaw");
        this.LegLeftBackTop = root.getChild("LegLeftBackTop");
        this.LegLeftBackBottom = root.getChild("LegLeftBackBottom");
        this.LeftBackPaw = root.getChild("LeftBackPaw");
        this.LegLeftFrontBottom = root.getChild("LegLeftFrontBottom");
        this.TailPieceSmall = root.getChild("TailPieceSmall");
        this.JawPiece = root.getChild("JawPiece");
        this.HeadPieceBottom = root.getChild("HeadPieceBottom");
        this.HeadPieceTop = root.getChild("HeadPieceTop");
        this.HornRightBottom = root.getChild("HornRightBottom");
        this.HornLeftBottom = root.getChild("HornLeftBottom");
        this.HornRightTop = root.getChild("HornRightTop");
        this.HornLeftTop = root.getChild("HornLeftTop");
        this.Torso = root.getChild("Torso");
        this.SnoutLeft = root.getChild("SnoutLeft");
        this.WingPieceLeft = root.getChild("WingPieceLeft");
        this.WingRight = root.getChild("WingRight");
        this.WingPieceRight = root.getChild("WingPieceRight");
        this.Neck = root.getChild("Neck");
        this.TailBack = root.getChild("TailBack");
        this.TailFront = root.getChild("TailFront");
        this.ScaleBackHead = root.getChild("ScaleBackHead");
        this.TailPieceLarge = root.getChild("TailPieceLarge");
        this.ScaleTailPiece = root.getChild("ScaleTailPiece");
        this.ScaleHead = root.getChild("ScaleHead");
        this.ScaleTop1 = root.getChild("ScaleTop1");
        this.ScaleBackPiece1 = root.getChild("ScaleBackPiece1");
        this.ScaleBackPiece2 = root.getChild("ScaleBackPiece2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("RightFrontPaw", CubeListBuilder.create().texOffs(12, 31).addBox(0.0f, 5.0f, -4.0f, 2, 1, 4), PartPose.offset(3.0f, 18.0f, -2.0f));
        root.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(2, 51).addBox(-10.0f, -1.0f, -2.0f, 10, 0, 4), PartPose.offsetAndRotation(-1.0f, 16.0f, 0.0f, 0.1745329f, 0.0f, -0.1745329f));
        root.addOrReplaceChild("LegRightFrontTop", CubeListBuilder.create().texOffs(20, 19).addBox(0.0f, 0.0f, -2.0f, 2, 3, 3), PartPose.offsetAndRotation(3.0f, 18.0f, -2.0f, -0.0872665f, 0.0f, 0.0f));
        root.addOrReplaceChild("LegRightFrontBottom", CubeListBuilder.create().texOffs(0, 25).addBox(0.0f, 2.0f, -1.5f, 2, 4, 2), PartPose.offsetAndRotation(3.0f, 18.0f, -2.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("LegRightBackTop", CubeListBuilder.create().texOffs(30, 19).addBox(0.0f, 0.0f, -2.0f, 2, 3, 3), PartPose.offsetAndRotation(3.0f, 18.0f, 3.0f, 0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("LegRightBackBottom", CubeListBuilder.create().texOffs(16, 25).addBox(0.0f, 2.0f, -1.0f, 2, 4, 2), PartPose.offsetAndRotation(3.0f, 18.0f, 3.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("RightBackPaw", CubeListBuilder.create().texOffs(36, 31).addBox(0.0f, 5.0f, -3.0f, 2, 1, 4), PartPose.offset(3.0f, 18.0f, 3.0f));
        root.addOrReplaceChild("LegLeftFrontTop", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0f, 0.0f, -1.0f, 2, 3, 3), PartPose.offsetAndRotation(-2.0f, 18.0f, -3.0f, -0.0872665f, 0.0f, 0.0f));
        root.addOrReplaceChild("SnoutRight", CubeListBuilder.create().texOffs(48, 2).addBox(1.0f, -3.0f, -5.0f, 1, 1, 1), PartPose.offset(1.0f, 16.0f, -3.0f));
        root.addOrReplaceChild("LeftFrontPaw", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0f, 5.0f, -3.0f, 2, 1, 4), PartPose.offset(-2.0f, 18.0f, -3.0f));
        root.addOrReplaceChild("LegLeftBackTop", CubeListBuilder.create().texOffs(10, 19).addBox(-2.0f, 0.0f, -2.0f, 2, 3, 3), PartPose.offsetAndRotation(-2.0f, 18.0f, 3.0f, 0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("LegLeftBackBottom", CubeListBuilder.create().texOffs(24, 25).addBox(-2.0f, 2.0f, -1.0f, 2, 4, 2), PartPose.offsetAndRotation(-2.0f, 18.0f, 3.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("LeftBackPaw", CubeListBuilder.create().texOffs(24, 31).addBox(-2.0f, 5.0f, -3.0f, 2, 1, 4), PartPose.offset(-2.0f, 18.0f, 3.0f));
        root.addOrReplaceChild("LegLeftFrontBottom", CubeListBuilder.create().texOffs(8, 25).addBox(-2.0f, 2.0f, -0.5f, 2, 4, 2), PartPose.offsetAndRotation(-2.0f, 18.0f, -3.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("TailPieceSmall", CubeListBuilder.create().texOffs(28, 36).addBox(0.0f, -0.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 16.0f, 7.0f, 0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("JawPiece", CubeListBuilder.create().texOffs(52, 0).addBox(-2.0f, -1.0f, -4.0f, 3, 1, 3), PartPose.offsetAndRotation(1.0f, 16.0f, -3.0f, 0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("HeadPieceBottom", CubeListBuilder.create().texOffs(30, 7).addBox(-3.0f, -2.0f, -5.0f, 5, 2, 6), PartPose.offset(1.0f, 16.0f, -3.0f));
        root.addOrReplaceChild("HeadPieceTop", CubeListBuilder.create().texOffs(30, 0).addBox(-3.0f, -5.0f, -3.0f, 5, 3, 4), PartPose.offset(1.0f, 16.0f, -3.0f));
        root.addOrReplaceChild("HornRightBottom", CubeListBuilder.create().texOffs(8, 14).addBox(0.0f, -6.0f, -3.5f, 2, 3, 2), PartPose.offsetAndRotation(1.0f, 16.0f, -3.0f, -0.7853982f, 0.7853982f, 0.0f));
        root.addOrReplaceChild("HornLeftBottom", CubeListBuilder.create().texOffs(0, 14).addBox(-2.75f, -6.5f, -3.0f, 2, 3, 2), PartPose.offsetAndRotation(1.0f, 16.0f, -3.0f, -0.7853982f, -0.7853982f, 0.0f));
        root.addOrReplaceChild("HornRightTop", CubeListBuilder.create().texOffs(20, 14).addBox(0.5f, -9.0f, -3.0f, 1, 3, 1), PartPose.offsetAndRotation(1.0f, 16.0f, -3.0f, -0.7853982f, 0.7853982f, 0.0f));
        root.addOrReplaceChild("HornLeftTop", CubeListBuilder.create().texOffs(16, 14).addBox(-2.2f, -9.5f, -2.5f, 1, 3, 1), PartPose.offsetAndRotation(1.0f, 16.0f, -3.0f, -0.7853982f, -0.7853982f, 0.0f));
        root.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -2.0f, -5.0f, 5, 4, 10), PartPose.offset(0.0f, 19.0f, 0.0f));
        root.addOrReplaceChild("SnoutLeft", CubeListBuilder.create().texOffs(48, 0).addBox(-3.0f, -3.0f, -5.0f, 1, 1, 1), PartPose.offset(1.0f, 16.0f, -3.0f));
        root.addOrReplaceChild("WingPieceLeft", CubeListBuilder.create().texOffs(4, 42).addBox(-1.0f, -2.0f, -1.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, 17.2f, 0.0f, 0.1745329f, 0.0f, -0.1745329f));
        root.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(2, 45).addBox(0.0f, -1.0f, -2.0f, 10, 0, 4), PartPose.offsetAndRotation(2.0f, 16.0f, 0.0f, 0.1745329f, 0.0f, 0.1745329f));
        root.addOrReplaceChild("WingPieceRight", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0f, -2.0f, 0.0f, 1, 2, 1), PartPose.offsetAndRotation(2.0f, 17.5f, -1.0f, 0.1745329f, 0.0f, 0.1745329f));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(52, 7).addBox(-1.0f, -2.0f, -1.0f, 3, 3, 3), PartPose.offsetAndRotation(0.0f, 17.0f, -4.0f, 0.4537856f, 0.0f, 0.0f));
        root.addOrReplaceChild("TailBack", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0f, -1.0f, -1.0f, 2, 2, 4), PartPose.offsetAndRotation(0.5f, 17.5f, 5.0f, 0.4537856f, 0.0f, 0.0f));
        root.addOrReplaceChild("TailFront", CubeListBuilder.create().texOffs(12, 36).addBox(0.0f, 0.0f, -1.0f, 1, 1, 4), PartPose.offsetAndRotation(0.0f, 16.0f, 7.0f, 0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("ScaleBackHead", CubeListBuilder.create().texOffs(38, 36).addBox(-1.0f, -3.0f, 2.0f, 1, 2, 1), PartPose.offset(1.0f, 16.0f, -4.0f));
        root.addOrReplaceChild("TailPieceLarge", CubeListBuilder.create().texOffs(22, 36).addBox(0.0f, -1.0f, 2.0f, 1, 2, 2), PartPose.offsetAndRotation(0.0f, 16.0f, 7.0f, 0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("ScaleTailPiece", CubeListBuilder.create().texOffs(48, 36).addBox(-0.5f, -2.0f, 0.2f, 1, 1, 2), PartPose.offsetAndRotation(0.5f, 17.5f, 5.0f, 0.4537856f, 0.0f, 0.0f));
        root.addOrReplaceChild("ScaleHead", CubeListBuilder.create().texOffs(42, 36).addBox(-1.0f, -6.0f, 0.0f, 1, 2, 2), PartPose.offset(1.0f, 16.0f, -3.0f));
        root.addOrReplaceChild("ScaleTop1", CubeListBuilder.create().texOffs(48, 36).addBox(-1.0f, -6.0f, -4.0f, 1, 1, 2), PartPose.offset(1.0f, 16.0f, -2.0f));
        root.addOrReplaceChild("ScaleBackPiece1", CubeListBuilder.create().texOffs(48, 36).addBox(0.0f, -1.0f, -1.0f, 1, 1, 2), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("ScaleBackPiece2", CubeListBuilder.create().texOffs(48, 36).addBox(0.0f, -1.0f, -1.0f, 1, 1, 2), PartPose.offset(0.0f, 17.0f, 3.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Spyro entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        int current_activity = entity.getActivity();
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 2.3f * this.wingspeed)) * 3.1415927f * 0.4f * limbSwingAmount : 0.0f;
        if (current_activity == 3) {
            newangle *= 0.5f;
        }
        this.WingLeft.zRot = newangle;
        this.WingRight.zRot = - newangle;
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed)) * 3.1415927f * 0.25f * limbSwingAmount : 0.0f;
        if (current_activity == 3) {
            newangle = 0.0f;
        }
        if (current_activity != 2) {
            this.LegRightFrontTop.xRot = newangle - 0.087f;
            this.LegRightFrontBottom.xRot = newangle - 0.17f;
            this.RightFrontPaw.xRot = newangle;
            this.LegLeftFrontTop.xRot = - newangle - 0.087f;
            this.LegLeftFrontBottom.xRot = - newangle - 0.17f;
            this.LeftFrontPaw.xRot = - newangle;
            this.LegRightBackBottom.xRot = - newangle + 0.139f;
            this.LegRightBackTop.xRot = - newangle - 0.174f;
            this.RightBackPaw.xRot = - newangle;
            this.LegLeftBackBottom.xRot = newangle + 0.139f;
            this.LegLeftBackTop.xRot = newangle - 0.174f;
            this.LeftBackPaw.xRot = newangle;
        } else {
            newangle = -1.0f;
            this.LegRightFrontTop.xRot = newangle - 0.087f;
            this.LegRightFrontBottom.xRot = newangle - 0.17f;
            this.RightFrontPaw.xRot = newangle;
            this.LegLeftFrontTop.xRot = newangle - 0.087f;
            this.LegLeftFrontBottom.xRot = newangle - 0.17f;
            this.LeftFrontPaw.xRot = newangle;
            newangle = 1.0f;
            this.LegRightBackBottom.xRot = newangle + 0.139f;
            this.LegRightBackTop.xRot = newangle - 0.174f;
            this.RightBackPaw.xRot = newangle;
            this.LegLeftBackBottom.xRot = newangle + 0.139f;
            this.LegLeftBackTop.xRot = newangle - 0.174f;
            this.LeftBackPaw.xRot = newangle;
        }
        newangle = Mth.cos((float)(ageInTicks * 1.2f * this.wingspeed)) * 3.1415927f * 0.25f;
        if (entity.isInSittingPose() || current_activity == 3) {
            newangle = 0.0f;
        }
        this.TailBack.yRot = newangle;
        this.ScaleTailPiece.yRot = newangle;
        this.TailFront.z = this.TailBack.z + (float)Math.cos(this.TailBack.yRot) * 3.0f;
        this.TailFront.x = this.TailBack.x + (float)Math.sin(this.TailBack.yRot) * 3.0f - 0.5f;
        this.TailFront.yRot = newangle * 1.6f;
        this.TailPieceLarge.z = this.TailFront.z;
        this.TailPieceLarge.x = this.TailFront.x;
        this.TailPieceLarge.yRot = this.TailFront.yRot;
        this.TailPieceSmall.z = this.TailFront.z;
        this.TailPieceSmall.x = this.TailFront.x;
        this.TailPieceSmall.yRot = this.TailFront.yRot;
        this.HeadPieceTop.yRot = (float)Math.toRadians(netHeadYaw);
        this.HeadPieceBottom.yRot = (float)Math.toRadians(netHeadYaw);
        this.JawPiece.yRot = (float)Math.toRadians(netHeadYaw);
        this.SnoutRight.yRot = (float)Math.toRadians(netHeadYaw);
        this.SnoutLeft.yRot = (float)Math.toRadians(netHeadYaw);
        this.ScaleTop1.yRot = (float)Math.toRadians(netHeadYaw);
        this.ScaleHead.yRot = (float)Math.toRadians(netHeadYaw);
        this.ScaleBackHead.yRot = (float)Math.toRadians(netHeadYaw);
        this.HornRightBottom.yRot = (float)Math.toRadians(netHeadYaw) + 0.785f;
        this.HornRightTop.yRot = (float)Math.toRadians(netHeadYaw) + 0.785f;
        this.HornLeftBottom.yRot = (float)Math.toRadians(netHeadYaw) - 0.785f;
        this.HornLeftTop.yRot = (float)Math.toRadians(netHeadYaw) - 0.785f;
        this.HeadPieceTop.xRot = (float)Math.toRadians(headPitch);
        this.HeadPieceBottom.xRot = (float)Math.toRadians(headPitch);
        this.JawPiece.xRot = (float)Math.toRadians(headPitch);
        this.SnoutRight.xRot = (float)Math.toRadians(headPitch);
        this.SnoutLeft.xRot = (float)Math.toRadians(headPitch);
        this.ScaleTop1.xRot = (float)Math.toRadians(headPitch);
        this.ScaleHead.xRot = (float)Math.toRadians(headPitch);
        this.ScaleBackHead.xRot = (float)Math.toRadians(headPitch);
        this.HornRightBottom.xRot = (float)Math.toRadians(headPitch) - 0.785f;
        this.HornRightTop.xRot = (float)Math.toRadians(headPitch) - 0.785f;
        this.HornLeftBottom.xRot = (float)Math.toRadians(headPitch) - 0.785f;
        this.HornLeftTop.xRot = (float)Math.toRadians(headPitch) - 0.785f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.RightFrontPaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightFrontTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightFrontBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightBackTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegRightBackBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightBackPaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftFrontTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.SnoutRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftFrontPaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftBackTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftBackBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftBackPaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegLeftFrontBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailPieceSmall.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.JawPiece.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadPieceBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HeadPieceTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornRightBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornLeftBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornRightTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.HornLeftTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.SnoutLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingPieceLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingPieceRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBack.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailFront.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleBackHead.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailPieceLarge.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleTailPiece.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleHead.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleTop1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleBackPiece1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ScaleBackPiece2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
