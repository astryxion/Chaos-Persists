package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.RenderInfo;
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

public class ModelEmperorScorpion extends EntityModel<EmperorScorpion> {
    private final float wingspeed;
    private final ModelPart Head;
    private final ModelPart Seg1;
    private final ModelPart Seg2;
    private final ModelPart Seg3;
    private final ModelPart Seg4;
    private final ModelPart Seg5;
    private final ModelPart Seg6;
    private final ModelPart Seg7;
    private final ModelPart Seg8;
    private final ModelPart Tailseg1;
    private final ModelPart Tailseg2;
    private final ModelPart Tailseg3;
    private final ModelPart Tailseg4;
    private final ModelPart Tailseg5;
    private final ModelPart Tailseg6;
    private final ModelPart Tailseg7;
    private final ModelPart Tailseg8;
    private final ModelPart Stinger1;
    private final ModelPart Stinger2;
    private final ModelPart Stinger3;
    private final ModelPart LeftShoulder;
    private final ModelPart LeftArmSeg1;
    private final ModelPart LeftArmSeg2;
    private final ModelPart LeftArmSeg3;
    private final ModelPart LeftArmSeg4;
    private final ModelPart RightShoulder;
    private final ModelPart RightArmSeg1;
    private final ModelPart RightArmSeg2;
    private final ModelPart RightArmSeg3;
    private final ModelPart RightArmSeg4;
    private final ModelPart RightPincer;
    private final ModelPart LeftPincer;
    private final ModelPart Lefteye;
    private final ModelPart Righteye;
    private final ModelPart RightMandible;
    private final ModelPart LeftMandible;
    private final ModelPart RightManPart2;
    private final ModelPart LeftManPart2;
    private final ModelPart Leg1Seg1;
    private final ModelPart Leg1Seg2;
    private final ModelPart Leg1Seg3;
    private final ModelPart Leg1Seg4;
    private final ModelPart Leg1Seg5;
    private final ModelPart Leg2Seg1;
    private final ModelPart Leg2Seg2;
    private final ModelPart Leg2Seg3;
    private final ModelPart Leg2Seg4;
    private final ModelPart Leg2Seg5;
    private final ModelPart Leg3Seg1;
    private final ModelPart Leg3Seg2;
    private final ModelPart Leg3Seg3;
    private final ModelPart Leg3Seg4;
    private final ModelPart Leg3Seg5;
    private final ModelPart Leg4Seg1;
    private final ModelPart Leg4Seg2;
    private final ModelPart Leg4Seg3;
    private final ModelPart Leg4Seg4;
    private final ModelPart Leg4Seg5;
    private final ModelPart Leg5Seg1;
    private final ModelPart Leg5Seg2;
    private final ModelPart Leg5Seg3;
    private final ModelPart Leg5Seg4;
    private final ModelPart Leg5Seg5;
    private final ModelPart Leg6Seg1;
    private final ModelPart Leg6Seg2;
    private final ModelPart Leg6Seg3;
    private final ModelPart Leg6Seg4;
    private final ModelPart Leg6Seg5;
    private final ModelPart Leg7Seg1;
    private final ModelPart Leg7Seg2;
    private final ModelPart Leg7Seg3;
    private final ModelPart Leg7Seg4;
    private final ModelPart Leg7Seg5;
    private final ModelPart Leg8Seg1;
    private final ModelPart Leg8Seg2;
    private final ModelPart Leg8Seg3;
    private final ModelPart Leg8Seg4;
    private final ModelPart Leg8Seg5;

    public ModelEmperorScorpion(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 256, 128).bakeRoot(), wingspeed);
    }

    public ModelEmperorScorpion(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Head = root.getChild("Head");
        this.Seg1 = root.getChild("Seg1");
        this.Seg2 = root.getChild("Seg2");
        this.Seg3 = root.getChild("Seg3");
        this.Seg4 = root.getChild("Seg4");
        this.Seg5 = root.getChild("Seg5");
        this.Seg6 = root.getChild("Seg6");
        this.Seg7 = root.getChild("Seg7");
        this.Seg8 = root.getChild("Seg8");
        this.Tailseg1 = root.getChild("Tailseg1");
        this.Tailseg2 = root.getChild("Tailseg2");
        this.Tailseg3 = root.getChild("Tailseg3");
        this.Tailseg4 = root.getChild("Tailseg4");
        this.Tailseg5 = root.getChild("Tailseg5");
        this.Tailseg6 = root.getChild("Tailseg6");
        this.Tailseg7 = root.getChild("Tailseg7");
        this.Tailseg8 = root.getChild("Tailseg8");
        this.Stinger1 = root.getChild("Stinger1");
        this.Stinger2 = root.getChild("Stinger2");
        this.Stinger3 = root.getChild("Stinger3");
        this.LeftShoulder = root.getChild("LeftShoulder");
        this.LeftArmSeg1 = root.getChild("LeftArmSeg1");
        this.LeftArmSeg2 = root.getChild("LeftArmSeg2");
        this.LeftArmSeg3 = root.getChild("LeftArmSeg3");
        this.LeftArmSeg4 = root.getChild("LeftArmSeg4");
        this.RightShoulder = root.getChild("RightShoulder");
        this.RightArmSeg1 = root.getChild("RightArmSeg1");
        this.RightArmSeg2 = root.getChild("RightArmSeg2");
        this.RightArmSeg3 = root.getChild("RightArmSeg3");
        this.RightArmSeg4 = root.getChild("RightArmSeg4");
        this.RightPincer = root.getChild("RightPincer");
        this.LeftPincer = root.getChild("LeftPincer");
        this.Lefteye = root.getChild("Lefteye");
        this.Righteye = root.getChild("Righteye");
        this.RightMandible = root.getChild("RightMandible");
        this.LeftMandible = root.getChild("LeftMandible");
        this.RightManPart2 = root.getChild("RightManPart2");
        this.LeftManPart2 = root.getChild("LeftManPart2");
        this.Leg1Seg1 = root.getChild("Leg1Seg1");
        this.Leg1Seg2 = root.getChild("Leg1Seg2");
        this.Leg1Seg3 = root.getChild("Leg1Seg3");
        this.Leg1Seg4 = root.getChild("Leg1Seg4");
        this.Leg1Seg5 = root.getChild("Leg1Seg5");
        this.Leg2Seg1 = root.getChild("Leg2Seg1");
        this.Leg2Seg2 = root.getChild("Leg2Seg2");
        this.Leg2Seg3 = root.getChild("Leg2Seg3");
        this.Leg2Seg4 = root.getChild("Leg2Seg4");
        this.Leg2Seg5 = root.getChild("Leg2Seg5");
        this.Leg3Seg1 = root.getChild("Leg3Seg1");
        this.Leg3Seg2 = root.getChild("Leg3Seg2");
        this.Leg3Seg3 = root.getChild("Leg3Seg3");
        this.Leg3Seg4 = root.getChild("Leg3Seg4");
        this.Leg3Seg5 = root.getChild("Leg3Seg5");
        this.Leg4Seg1 = root.getChild("Leg4Seg1");
        this.Leg4Seg2 = root.getChild("Leg4Seg2");
        this.Leg4Seg3 = root.getChild("Leg4Seg3");
        this.Leg4Seg4 = root.getChild("Leg4Seg4");
        this.Leg4Seg5 = root.getChild("Leg4Seg5");
        this.Leg5Seg1 = root.getChild("Leg5Seg1");
        this.Leg5Seg2 = root.getChild("Leg5Seg2");
        this.Leg5Seg3 = root.getChild("Leg5Seg3");
        this.Leg5Seg4 = root.getChild("Leg5Seg4");
        this.Leg5Seg5 = root.getChild("Leg5Seg5");
        this.Leg6Seg1 = root.getChild("Leg6Seg1");
        this.Leg6Seg2 = root.getChild("Leg6Seg2");
        this.Leg6Seg3 = root.getChild("Leg6Seg3");
        this.Leg6Seg4 = root.getChild("Leg6Seg4");
        this.Leg6Seg5 = root.getChild("Leg6Seg5");
        this.Leg7Seg1 = root.getChild("Leg7Seg1");
        this.Leg7Seg2 = root.getChild("Leg7Seg2");
        this.Leg7Seg3 = root.getChild("Leg7Seg3");
        this.Leg7Seg4 = root.getChild("Leg7Seg4");
        this.Leg7Seg5 = root.getChild("Leg7Seg5");
        this.Leg8Seg1 = root.getChild("Leg8Seg1");
        this.Leg8Seg2 = root.getChild("Leg8Seg2");
        this.Leg8Seg3 = root.getChild("Leg8Seg3");
        this.Leg8Seg4 = root.getChild("Leg8Seg4");
        this.Leg8Seg5 = root.getChild("Leg8Seg5");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 104).addBox(-9.0f, -4.0f, -16.0f, 18, 8, 16), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg1", CubeListBuilder.create().texOffs(0, 78).addBox(-9.0f, -4.0f, 0.0f, 18, 8, 4), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg2", CubeListBuilder.create().texOffs(0, 65).addBox(-8.5f, -4.1f, 4.0f, 17, 8, 4), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg3", CubeListBuilder.create().texOffs(0, 50).addBox(-9.5f, -4.0f, 8.0f, 19, 8, 5), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg4", CubeListBuilder.create().texOffs(0, 35).addBox(-9.0f, -4.1f, 13.0f, 18, 8, 6), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg5", CubeListBuilder.create().texOffs(45, 91).addBox(-8.5f, -4.0f, 19.0f, 17, 8, 3), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg6", CubeListBuilder.create().texOffs(45, 79).addBox(-8.0f, -4.1f, 22.0f, 16, 8, 3), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg7", CubeListBuilder.create().texOffs(43, 66).addBox(-7.0f, -4.0f, 25.0f, 14, 8, 3), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Seg8", CubeListBuilder.create().texOffs(49, 53).addBox(-5.5f, -4.1f, 28.0f, 11, 8, 2), PartPose.offset(0.0f, 13.0f, -8.0f));
        root.addOrReplaceChild("Tailseg1", CubeListBuilder.create().texOffs(92, 0).addBox(-4.0f, -1.0f, 0.0f, 8, 4, 10), PartPose.offsetAndRotation(0.0f, 13.0f, 20.0f, 0.5948578f, 0.0f, 0.0f));
        root.addOrReplaceChild("Tailseg2", CubeListBuilder.create().texOffs(90, 15).addBox(-3.5f, -2.0f, 0.0f, 7, 4, 12), PartPose.offsetAndRotation(0.0f, 10.0f, 27.0f, 1.07818f, 0.0f, 0.0f));
        root.addOrReplaceChild("Tailseg3", CubeListBuilder.create().texOffs(96, 32).addBox(-3.0f, -2.0f, 1.0f, 6, 4, 10), PartPose.offsetAndRotation(0.0f, 2.0f, 32.0f, 1.710216f, 0.0f, 0.0f));
        root.addOrReplaceChild("Tailseg4", CubeListBuilder.create().texOffs(96, 47).addBox(-2.5f, -2.0f, 0.0f, 5, 4, 11), PartPose.offsetAndRotation(0.0f, -7.0f, 31.0f, 2.267895f, 0.0f, 0.0f));
        root.addOrReplaceChild("Tailseg5", CubeListBuilder.create().texOffs(98, 63).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 11), PartPose.offsetAndRotation(0.0f, -14.0f, 25.0f, 2.899932f, 0.0f, 0.0f));
        root.addOrReplaceChild("Tailseg6", CubeListBuilder.create().texOffs(98, 79).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 11), PartPose.offsetAndRotation(0.0f, -17.0f, 16.0f, -2.602503f, 0.0f, 0.0f));
        root.addOrReplaceChild("Tailseg7", CubeListBuilder.create().texOffs(94, 95).addBox(-3.0f, -2.0f, 0.0f, 6, 4, 11), PartPose.offsetAndRotation(0.0f, -12.0f, 8.0f, -0.2230717f, 0.0f, 0.0f));
        root.addOrReplaceChild("Tailseg8", CubeListBuilder.create().texOffs(102, 111).addBox(-4.0f, -2.0f, 4.0f, 8, 4, 5), PartPose.offsetAndRotation(0.0f, -12.0f, 8.0f, -0.2230717f, 0.0f, 0.0f));
        root.addOrReplaceChild("Stinger1", CubeListBuilder.create().texOffs(83, 0).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 3), PartPose.offsetAndRotation(0.0f, -10.0f, 18.0f, 0.2230717f, 0.0f, 0.0f));
        root.addOrReplaceChild("Stinger2", CubeListBuilder.create().texOffs(83, 0).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 3), PartPose.offsetAndRotation(0.0f, -10.5f, 20.5f, -0.2602503f, 0.0f, 0.0f));
        root.addOrReplaceChild("Stinger3", CubeListBuilder.create().texOffs(79, 5).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 5), PartPose.offsetAndRotation(0.0f, -10.0f, 23.0f, -0.8551081f, 0.0f, 0.0f));
        root.addOrReplaceChild("LeftShoulder", CubeListBuilder.create().texOffs(69, 103).addBox(-3.0f, -3.0f, -4.0f, 6, 6, 4), PartPose.offsetAndRotation(7.0f, 13.0f, -22.0f, 0.0f, -0.8551081f, 0.0f));
        root.addOrReplaceChild("LeftArmSeg1", CubeListBuilder.create().texOffs(55, 0).addBox(-3.0f, -3.0f, -10.0f, 4, 6, 13), PartPose.offsetAndRotation(10.0f, 13.0f, -24.0f, 0.0f, -2.044824f, 0.0f));
        root.addOrReplaceChild("LeftArmSeg2", CubeListBuilder.create().texOffs(130, 0).addBox(-7.0f, -3.0f, -17.0f, 8, 6, 17), PartPose.offsetAndRotation(19.0f, 13.0f, -22.0f, 0.0f, -0.7435722f, 0.0f));
        root.addOrReplaceChild("LeftArmSeg3", CubeListBuilder.create().texOffs(130, 50).addBox(-3.0f, -3.0f, -24.0f, 4, 6, 24), PartPose.offsetAndRotation(29.0f, 13.0f, -33.0f, 0.0f, 0.3717861f, 0.0f));
        root.addOrReplaceChild("LeftArmSeg4", CubeListBuilder.create().texOffs(181, 0).addBox(-3.0f, -3.0f, -14.0f, 8, 6, 12), PartPose.offsetAndRotation(29.0f, 13.0f, -33.0f, 0.0f, 1.487144f, 0.0f));
        root.addOrReplaceChild("RightShoulder", CubeListBuilder.create().texOffs(69, 103).addBox(-3.0f, -3.0f, -4.0f, 6, 6, 4), PartPose.offsetAndRotation(-7.0f, 13.0f, -22.0f, 0.0f, 0.8551066f, 0.0f));
        root.addOrReplaceChild("RightArmSeg1", CubeListBuilder.create().texOffs(55, 0).addBox(-1.0f, -3.0f, -10.0f, 4, 6, 13), PartPose.offsetAndRotation(-10.0f, 13.0f, -24.0f, 0.0f, 2.044828f, 0.0f));
        root.addOrReplaceChild("RightArmSeg2", CubeListBuilder.create().texOffs(130, 0).addBox(-1.0f, -3.0f, -17.0f, 8, 6, 17), PartPose.offsetAndRotation(-19.0f, 13.0f, -22.0f, 0.0f, 0.7435801f, 0.0f));
        root.addOrReplaceChild("RightArmSeg3", CubeListBuilder.create().texOffs(130, 50).addBox(-1.0f, -3.0f, -24.0f, 4, 6, 24), PartPose.offsetAndRotation(-29.0f, 13.0f, -33.0f, 0.0f, -0.37179f, 0.0f));
        root.addOrReplaceChild("RightArmSeg4", CubeListBuilder.create().texOffs(181, 0).addBox(-5.0f, -3.0f, -14.0f, 8, 6, 12), PartPose.offsetAndRotation(-29.0f, 13.0f, -33.0f, 0.0f, -1.487143f, 0.0f));
        root.addOrReplaceChild("RightPincer", CubeListBuilder.create().texOffs(130, 24).addBox(-1.0f, -3.0f, -19.0f, 2, 6, 19), PartPose.offsetAndRotation(-17.0f, 13.0f, -33.0f, 0.0f, -0.0743611f, 0.0f));
        root.addOrReplaceChild("LeftPincer", CubeListBuilder.create().texOffs(130, 24).addBox(-1.0f, -3.0f, -19.0f, 2, 6, 19), PartPose.offsetAndRotation(17.0f, 13.0f, -33.0f, 0.0f, 0.0743685f, 0.0f));
        root.addOrReplaceChild("Lefteye", CubeListBuilder.create().texOffs(0, 113).addBox(-0.5f, -5.0f, -7.5f, 3, 2, 3), PartPose.offsetAndRotation(0.0f, 13.0f, -8.0f, 0.0f, 0.0f, 0.2974289f));
        root.addOrReplaceChild("Righteye", CubeListBuilder.create().texOffs(0, 113).addBox(-2.5f, -5.0f, -7.5f, 3, 2, 3), PartPose.offsetAndRotation(0.0f, 13.0f, -8.0f, 0.0f, 0.0f, -0.2974216f));
        root.addOrReplaceChild("RightMandible", CubeListBuilder.create().texOffs(76, 55).addBox(-2.0f, -3.0f, -4.0f, 4, 4, 4), PartPose.offsetAndRotation(-2.0f, 13.0f, -23.0f, 0.1115358f, 0.3346075f, 0.0f));
        root.addOrReplaceChild("LeftMandible", CubeListBuilder.create().texOffs(76, 55).addBox(-2.0f, -3.0f, -4.0f, 4, 4, 4), PartPose.offsetAndRotation(2.0f, 13.0f, -23.0f, 0.111544f, -0.3346145f, 0.0f));
        root.addOrReplaceChild("RightManPart2", CubeListBuilder.create().texOffs(82, 64).addBox(-0.5f, -0.5f, -6.0f, 1, 1, 6), PartPose.offsetAndRotation(-3.0f, 11.0f, -26.0f, 1.189716f, 0.0f, 0.0f));
        root.addOrReplaceChild("LeftManPart2", CubeListBuilder.create().texOffs(82, 64).addBox(-0.5f, -0.5f, -6.0f, 1, 1, 6), PartPose.offsetAndRotation(3.0f, 11.0f, -26.0f, 1.188848f, 0.0f, 0.0f));
        root.addOrReplaceChild("Leg1Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(0.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(9.0f, 13.0f, -10.0f));
        root.addOrReplaceChild("Leg1Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(12.0f, 13.0f, -10.0f, 0.0f, 0.0f, -0.9294576f));
        root.addOrReplaceChild("Leg1Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(18.0f, 3.0f, -10.0f, 0.0f, 0.0f, 0.6320361f));
        root.addOrReplaceChild("Leg1Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(0.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(26.0f, 12.0f, -10.0f));
        root.addOrReplaceChild("Leg1Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(27.0f, 19.0f, -10.0f, 0.0f, 0.0f, 0.669215f));
        root.addOrReplaceChild("Leg2Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(0.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(8.5f, 13.0f, -4.0f));
        root.addOrReplaceChild("Leg2Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(12.0f, 13.0f, -4.0f, 0.0f, 0.0f, -0.9294576f));
        root.addOrReplaceChild("Leg2Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(18.0f, 3.0f, -4.0f, 0.0f, 0.0f, 0.6320361f));
        root.addOrReplaceChild("Leg2Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(0.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(26.0f, 12.0f, -4.0f));
        root.addOrReplaceChild("Leg2Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(27.0f, 19.0f, -4.0f, 0.0f, 0.0f, 0.669215f));
        root.addOrReplaceChild("Leg3Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(0.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(9.5f, 13.0f, 2.0f));
        root.addOrReplaceChild("Leg3Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(12.0f, 13.0f, 2.0f, 0.0f, 0.0f, -0.9294576f));
        root.addOrReplaceChild("Leg3Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(18.0f, 3.0f, 2.0f, 0.0f, 0.0f, 0.6320361f));
        root.addOrReplaceChild("Leg3Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(0.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(26.0f, 12.0f, 2.0f));
        root.addOrReplaceChild("Leg3Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(27.0f, 19.0f, 2.0f, 0.0f, 0.0f, 0.669215f));
        root.addOrReplaceChild("Leg4Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(0.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(9.0f, 13.0f, 8.0f));
        root.addOrReplaceChild("Leg4Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(12.0f, 13.0f, 8.0f, 0.0f, 0.0f, -0.9294576f));
        root.addOrReplaceChild("Leg4Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(0.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(18.0f, 3.0f, 8.0f, 0.0f, 0.0f, 0.6320361f));
        root.addOrReplaceChild("Leg4Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(0.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(26.0f, 12.0f, 8.0f));
        root.addOrReplaceChild("Leg4Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(27.0f, 19.0f, 8.0f, 0.0f, 0.0f, 0.669215f));
        root.addOrReplaceChild("Leg5Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(-9.0f, 13.0f, -10.0f));
        root.addOrReplaceChild("Leg5Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-12.0f, 14.0f, -10.0f, 0.0f, 0.0f, 0.9294653f));
        root.addOrReplaceChild("Leg5Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-18.0f, 4.0f, -10.0f, 0.0f, 0.0f, -0.6320364f));
        root.addOrReplaceChild("Leg5Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(-26.0f, 12.0f, -10.0f));
        root.addOrReplaceChild("Leg5Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(-27.0f, 19.0f, -10.0f, 0.0f, 0.0f, 2.240008f));
        root.addOrReplaceChild("Leg6Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(-8.5f, 13.0f, -4.0f));
        root.addOrReplaceChild("Leg6Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-12.0f, 14.0f, -4.0f, 0.0f, 0.0f, 0.9294576f));
        root.addOrReplaceChild("Leg6Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-18.0f, 4.0f, -4.0f, 0.0f, 0.0f, -0.6320361f));
        root.addOrReplaceChild("Leg6Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(-26.0f, 12.0f, -4.0f));
        root.addOrReplaceChild("Leg6Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(-27.0f, 19.0f, -4.0f, 0.0f, 0.0f, 2.240008f));
        root.addOrReplaceChild("Leg7Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(-9.5f, 13.0f, 2.0f));
        root.addOrReplaceChild("Leg7Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-12.0f, 14.0f, 2.0f, 0.0f, 0.0f, 0.9294576f));
        root.addOrReplaceChild("Leg7Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-18.0f, 4.0f, 2.0f, 0.0f, 0.0f, -0.6320361f));
        root.addOrReplaceChild("Leg7Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(-26.0f, 12.0f, 2.0f));
        root.addOrReplaceChild("Leg7Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(-27.0f, 19.0f, 2.0f, 0.0f, 0.0f, 2.240008f));
        root.addOrReplaceChild("Leg8Seg1", CubeListBuilder.create().texOffs(20, 20).addBox(-4.0f, -1.5f, -2.0f, 4, 3, 4), PartPose.offset(-9.0f, 13.0f, 8.0f));
        root.addOrReplaceChild("Leg8Seg2", CubeListBuilder.create().texOffs(21, 0).addBox(-12.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-12.0f, 14.0f, 8.0f, 0.0f, 0.0f, 0.9294576f));
        root.addOrReplaceChild("Leg8Seg3", CubeListBuilder.create().texOffs(15, 8).addBox(-13.0f, -1.5f, -1.5f, 13, 3, 3), PartPose.offsetAndRotation(-18.0f, 4.0f, 8.0f, 0.0f, 0.0f, -0.6320361f));
        root.addOrReplaceChild("Leg8Seg4", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offset(-26.0f, 12.0f, 8.0f));
        root.addOrReplaceChild("Leg8Seg5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -1.5f, 7, 3, 3), PartPose.offsetAndRotation(-27.0f, 19.0f, 8.0f, 0.0f, 0.0f, 2.240008f));
        return mesh;
    }

    @Override
    public void setupAnim(EmperorScorpion entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float pi4 = 1.570795f;
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * limbSwingAmount;
        nextangle = Mth.cos((float)((ageInTicks + 0.1f) * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * limbSwingAmount;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * limbSwingAmount - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg1Seg1, this.Leg1Seg2, this.Leg1Seg3, this.Leg1Seg4, this.Leg1Seg5, newangle, upangle);
        this.doRightLeg(this.Leg5Seg1, this.Leg5Seg2, this.Leg5Seg3, this.Leg5Seg4, this.Leg5Seg5, - newangle, upangle);
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed - 1.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        nextangle = Mth.cos((float)((ageInTicks + 0.1f) * 2.0f * this.wingspeed - 1.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * limbSwingAmount - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg2Seg1, this.Leg2Seg2, this.Leg2Seg3, this.Leg2Seg4, this.Leg2Seg5, newangle, upangle);
        this.doRightLeg(this.Leg6Seg1, this.Leg6Seg2, this.Leg6Seg3, this.Leg6Seg4, this.Leg6Seg5, - newangle, upangle);
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        nextangle = Mth.cos((float)((ageInTicks + 0.1f) * 2.0f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * limbSwingAmount - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg3Seg1, this.Leg3Seg2, this.Leg3Seg3, this.Leg3Seg4, this.Leg3Seg5, newangle, upangle);
        this.doRightLeg(this.Leg7Seg1, this.Leg7Seg2, this.Leg7Seg3, this.Leg7Seg4, this.Leg7Seg5, - newangle, upangle);
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        nextangle = Mth.cos((float)((ageInTicks + 0.1f) * 2.0f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * limbSwingAmount - Math.abs(newangle);
        }
        this.doLeftLeg(this.Leg4Seg1, this.Leg4Seg2, this.Leg4Seg3, this.Leg4Seg4, this.Leg4Seg5, newangle, upangle);
        this.doRightLeg(this.Leg8Seg1, this.Leg8Seg2, this.Leg8Seg3, this.Leg8Seg4, this.Leg8Seg5, - newangle, upangle);
        newangle = entity.getAttacking() == 0 ? Mth.cos((float)(ageInTicks * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f : Mth.cos((float)(ageInTicks * 2.5f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.LeftManPart2.zRot = newangle;
        this.RightManPart2.zRot = -newangle;
        RenderInfo r = entity.getRenderInfo();
        newangle = Mth.cos((float)(ageInTicks * 3.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = Mth.cos((float)((ageInTicks + 0.1f) * 3.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (entity.getAttacking() == 0) {
                r.ri1 = entity.getRandom().nextInt(20);
                r.ri2 = entity.getRandom().nextInt(25);
            } else {
                r.ri1 = entity.getRandom().nextInt(4);
                r.ri2 = entity.getRandom().nextInt(3);
            }
        }
        if (r.ri1 == 1 || r.ri1 == 3) {
            this.doLeftClaw(newangle);
        } else {
            this.doLeftClaw(0.0f);
        }
        if (r.ri1 == 2 || r.ri1 == 3) {
            this.doRightClaw(newangle);
        } else {
            this.doRightClaw(0.0f);
        }
        if (r.ri2 == 1) {
            this.doTail(newangle);
        } else {
            this.doTail(0.0f);
        }
        entity.setRenderInfo(r);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Seg8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tailseg8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Stinger1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Stinger2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Stinger3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftShoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftArmSeg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightShoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightArmSeg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPincer.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPincer.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lefteye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Righteye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightMandible.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftMandible.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightManPart2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftManPart2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg3Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg4Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg5Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg6Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8Seg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void doLeftLeg(
            ModelPart seg1,
            ModelPart seg2,
            ModelPart seg3,
            ModelPart seg4,
            ModelPart seg5,
            float angle,
            float upangle) {
        seg2.yRot = angle;
        seg3.yRot = angle;
        seg4.yRot = angle;
        seg5.yRot = angle;
        seg3.z = (float) ((double) seg2.z - Math.sin(angle) * 6.0);
        seg3.x = (float) ((double) seg2.x - Math.abs(Math.sin(angle) * 6.0) + 6.0);
        seg4.z = (float) ((double) seg3.z - Math.sin(angle) * 9.0);
        seg4.x = (float) ((double) seg3.x - Math.abs(Math.sin(angle) * 9.0) + 9.0);
        seg5.z = (float) ((double) seg4.z - Math.sin(angle) * 1.0);
        seg5.x = (float) ((double) seg4.x - Math.abs(Math.sin(angle) * 1.0) + 1.0);
        seg2.zRot = -upangle - 0.929f;
        seg3.zRot = -upangle + 0.632f;
        seg3.y = seg2.y + (float) (11.5 * Math.sin(seg2.zRot));
        seg4.y = seg3.y + (float) (11.5 * Math.sin(seg3.zRot));
        seg5.y = seg4.y + 6.5f;
    }

    private void doRightLeg(
            ModelPart seg1,
            ModelPart seg2,
            ModelPart seg3,
            ModelPart seg4,
            ModelPart seg5,
            float angle,
            float upangle) {
        seg2.yRot = angle;
        seg3.yRot = angle;
        seg4.yRot = angle;
        seg5.yRot = -angle;
        seg3.z = (float) ((double) seg2.z + Math.sin(angle) * 6.0);
        seg3.x = (float) ((double) seg2.x + Math.abs(Math.sin(angle) * 6.0) - 6.0);
        seg4.z = (float) ((double) seg3.z + Math.sin(angle) * 9.0);
        seg4.x = (float) ((double) seg3.x + Math.abs(Math.sin(angle) * 9.0) - 9.0);
        seg5.z = (float) ((double) seg4.z + Math.sin(angle) * 1.0);
        seg5.x = (float) ((double) seg4.x + Math.abs(Math.sin(angle) * 1.0) - 1.0);
        seg2.zRot = upangle + 0.929f;
        seg3.zRot = upangle - 0.632f;
        seg3.y = seg2.y - (float) (11.5 * Math.sin(seg2.zRot));
        seg4.y = seg3.y - (float) (11.5 * Math.sin(seg3.zRot));
        seg5.y = seg4.y + 6.5f;
    }

    private void doLeftClaw(float angle) {
        this.LeftArmSeg1.yRot = -1.57f + angle;
        this.LeftArmSeg2.z = (float) (-22.0 - Math.cos(this.LeftArmSeg1.yRot) * 12.0);
        this.LeftArmSeg3.z = this.LeftArmSeg2.z - 11.0f;
        this.LeftArmSeg4.z = this.LeftArmSeg2.z - 11.0f;
        this.LeftPincer.z = this.LeftArmSeg2.z - 11.0f;
        this.LeftArmSeg3.yRot = 0.074f + angle;
        this.LeftPincer.yRot = 0.371f - angle;
    }

    private void doRightClaw(float angle) {
        this.RightArmSeg1.yRot = 1.57f - angle;
        this.RightArmSeg2.z = (float) (-22.0 - Math.cos(this.RightArmSeg1.yRot) * 12.0);
        this.RightArmSeg3.z = this.RightArmSeg2.z - 11.0f;
        this.RightArmSeg4.z = this.RightArmSeg2.z - 11.0f;
        this.RightPincer.z = this.RightArmSeg2.z - 11.0f;
        this.RightArmSeg3.yRot = -0.074f - angle;
        this.RightPincer.yRot = -0.371f + angle;
    }

    private void doTail(float angle) {
        this.Tailseg1.xRot = 0.594f + angle;
        this.Tailseg2.xRot = this.Tailseg1.xRot + 0.48399997f + angle;
        this.Tailseg2.y = (float) ((double) this.Tailseg1.y - Math.sin(this.Tailseg1.xRot) * 9.0);
        this.Tailseg2.z = (float) ((double) this.Tailseg1.z + Math.cos(this.Tailseg1.xRot) * 9.0);
        this.Tailseg3.xRot = this.Tailseg2.xRot + 0.6320001f + angle;
        this.Tailseg3.y = (float) ((double) this.Tailseg2.y - Math.sin(this.Tailseg2.xRot) * 10.0);
        this.Tailseg3.z = (float) ((double) this.Tailseg2.z + Math.cos(this.Tailseg2.xRot) * 10.0);
        this.Tailseg4.xRot = this.Tailseg3.xRot + 0.5569999f - angle;
        this.Tailseg4.y = (float) ((double) this.Tailseg3.y - Math.sin(this.Tailseg3.xRot) * 10.0);
        this.Tailseg4.z = (float) ((double) this.Tailseg3.z + Math.cos(this.Tailseg3.xRot) * 10.0);
        this.Tailseg5.xRot = this.Tailseg4.xRot + 0.63199997f - angle;
        this.Tailseg5.y = (float) ((double) this.Tailseg4.y - Math.sin(this.Tailseg4.xRot) * 10.0);
        this.Tailseg5.z = (float) ((double) this.Tailseg4.z + Math.cos(this.Tailseg4.xRot) * 10.0);
        this.Tailseg6.xRot = this.Tailseg5.xRot + -5.501f - angle * 3.0f / 2.0f - 0.4f;
        this.Tailseg6.y = (float) ((double) this.Tailseg5.y - Math.sin(this.Tailseg5.xRot) * 10.0);
        this.Tailseg6.z = (float) ((double) this.Tailseg5.z + Math.cos(this.Tailseg5.xRot) * 10.0);
        this.Tailseg7.xRot = this.Tailseg6.xRot + -2.822f - angle * 2.5f - 2.2f;
        this.Tailseg7.y = (float) ((double) this.Tailseg6.y - Math.sin(this.Tailseg6.xRot) * 10.0);
        this.Tailseg7.z = (float) ((double) this.Tailseg6.z + Math.cos(this.Tailseg6.xRot) * 10.0);
        this.Tailseg8.xRot = this.Tailseg7.xRot;
        this.Tailseg8.y = this.Tailseg7.y;
        this.Tailseg8.z = this.Tailseg7.z;
        this.Stinger1.xRot = this.Tailseg7.xRot + angle * 0.66f;
        this.Stinger1.y = (float) ((double) this.Tailseg7.y - Math.sin(this.Tailseg7.xRot) * 10.0);
        this.Stinger1.z = (float) ((double) this.Tailseg7.z + Math.cos(this.Tailseg7.xRot) * 10.0);
        this.Stinger2.xRot = this.Stinger1.xRot + -0.48f + angle;
        this.Stinger2.y = (float) ((double) this.Stinger1.y - Math.sin(this.Stinger1.xRot) * 3.0);
        this.Stinger2.z = (float) ((double) this.Stinger1.z + Math.cos(this.Stinger1.xRot) * 3.0);
        this.Stinger3.xRot = this.Stinger2.xRot + -1.01f + angle * 1.7f;
        this.Stinger3.y = (float) ((double) this.Stinger2.y - Math.sin(this.Stinger2.xRot) * 3.0);
        this.Stinger3.z = (float) ((double) this.Stinger2.z + Math.cos(this.Stinger2.xRot) * 3.0);
    }
}
