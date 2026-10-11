package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.SeaMonster;
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

public class ModelSeaMonster extends EntityModel<SeaMonster> {
    private final float wingspeed;
    private SeaMonster animEntity;
    private float animAgeInTicks;
    private float animLimbSwingAmount;
    private float animNetHeadYaw;
    private final ModelPart TailTip;
    private final ModelPart TailBase;
    private final ModelPart Tail2;
    private final ModelPart Tail3;
    private final ModelPart BodyBack;
    private final ModelPart Neck6;
    private final ModelPart BodyFront;
    private final ModelPart NeckBase;
    private final ModelPart Neck2;
    private final ModelPart Neck3;
    private final ModelPart Neck4;
    private final ModelPart Neck5;
    private final ModelPart BottomJaw;
    private final ModelPart FinBackRight;
    private final ModelPart FinBackLeft;
    private final ModelPart FinFrontLeft;
    private final ModelPart FinFrontRight;
    private final ModelPart Tail4;
    private final ModelPart Tail5;
    private final ModelPart Tail6;
    private final ModelPart TopJaw;
    private final ModelPart RightEye;
    private final ModelPart LeftEye;
    public ModelSeaMonster(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 256, 128).bakeRoot());
    }

    public ModelSeaMonster(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.TailTip = root.getChild("TailTip");
        this.TailBase = root.getChild("TailBase");
        this.Tail2 = root.getChild("Tail2");
        this.Tail3 = root.getChild("Tail3");
        this.BodyBack = root.getChild("BodyBack");
        this.Neck6 = root.getChild("Neck6");
        this.BodyFront = root.getChild("BodyFront");
        this.NeckBase = root.getChild("NeckBase");
        this.Neck2 = root.getChild("Neck2");
        this.Neck3 = root.getChild("Neck3");
        this.Neck4 = root.getChild("Neck4");
        this.Neck5 = root.getChild("Neck5");
        this.BottomJaw = root.getChild("BottomJaw");
        this.FinBackRight = root.getChild("FinBackRight");
        this.FinBackLeft = root.getChild("FinBackLeft");
        this.FinFrontLeft = root.getChild("FinFrontLeft");
        this.FinFrontRight = root.getChild("FinFrontRight");
        this.Tail4 = root.getChild("Tail4");
        this.Tail5 = root.getChild("Tail5");
        this.Tail6 = root.getChild("Tail6");
        this.TopJaw = root.getChild("TopJaw");
        this.RightEye = root.getChild("RightEye");
        this.LeftEye = root.getChild("LeftEye");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("TailTip", CubeListBuilder.create().texOffs(158, 36).addBox(-1.0f, -1.0f, 0.0f, 2, 2, 6), PartPose.offsetAndRotation(0.0f, 16.0f, 70.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("TailBase", CubeListBuilder.create().texOffs(68, 64).addBox(-7.0f, -7.0f, 0.0f, 14, 14, 12), PartPose.offsetAndRotation(0.0f, 16.0f, 26.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(74, 90).addBox(-6.0f, -6.0f, 0.0f, 12, 12, 8), PartPose.offsetAndRotation(0.0f, 16.0f, 38.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(78, 110).addBox(-5.0f, -5.0f, 0.0f, 10, 10, 6), PartPose.offsetAndRotation(0.0f, 16.0f, 46.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BodyBack", CubeListBuilder.create().texOffs(62, 32).addBox(-8.0f, -8.0f, 0.0f, 16, 16, 16), PartPose.offsetAndRotation(0.0f, 16.0f, 10.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck6", CubeListBuilder.create().texOffs(20, 28).addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4), PartPose.offsetAndRotation(0.0f, -21.0f, -25.0f, 1.22173f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BodyFront", CubeListBuilder.create().texOffs(62, 0).addBox(-8.0f, -8.0f, -16.0f, 16, 16, 16), PartPose.offsetAndRotation(0.0f, 16.0f, 10.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("NeckBase", CubeListBuilder.create().texOffs(8, 96).addBox(-5.0f, -10.0f, -5.0f, 10, 10, 10), PartPose.offsetAndRotation(0.0f, 12.0f, -2.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck2", CubeListBuilder.create().texOffs(14, 78).addBox(-3.0f, -10.0f, -4.0f, 6, 10, 8), PartPose.offsetAndRotation(0.0f, 6.0f, -9.0f, 0.6981317f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck3", CubeListBuilder.create().texOffs(16, 62).addBox(-3.0f, -10.0f, -3.0f, 6, 10, 6), PartPose.offsetAndRotation(0.0f, -1.0f, -15.0f, 0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck4", CubeListBuilder.create().texOffs(20, 48).addBox(-2.0f, -10.0f, -2.0f, 4, 10, 4), PartPose.offsetAndRotation(0.0f, -9.0f, -20.0f, 0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck5", CubeListBuilder.create().texOffs(20, 38).addBox(-2.0f, -6.0f, -2.0f, 4, 6, 4), PartPose.offsetAndRotation(0.0f, -17.6f, -22.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BottomJaw", CubeListBuilder.create().texOffs(10, 0).addBox(-4.0f, 0.0f, -10.0f, 8, 3, 10), PartPose.offsetAndRotation(0.0f, -23.0f, -29.0f, 0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("FinBackRight", CubeListBuilder.create().texOffs(132, 95).addBox(-8.0f, 0.0f, 0.0f, 8, 1, 16), PartPose.offsetAndRotation(-7.0f, 16.0f, 16.0f, -0.5235988f, -0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("FinBackLeft", CubeListBuilder.create().texOffs(132, 61).addBox(0.0f, 0.0f, 0.0f, 8, 1, 16), PartPose.offsetAndRotation(7.0f, 16.0f, 16.0f, -0.5235988f, 0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("FinFrontLeft", CubeListBuilder.create().texOffs(132, 44).addBox(0.0f, 0.0f, 0.0f, 8, 1, 16), PartPose.offsetAndRotation(7.0f, 16.0f, -1.0f, -0.5235988f, 0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("FinFrontRight", CubeListBuilder.create().texOffs(132, 78).addBox(-8.0f, 0.0f, 0.0f, 8, 1, 16), PartPose.offsetAndRotation(-7.0f, 16.0f, -1.0f, -0.5235988f, -0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(152, 0).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 6), PartPose.offsetAndRotation(0.0f, 16.0f, 52.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail5", CubeListBuilder.create().texOffs(154, 14).addBox(-3.0f, -3.0f, 0.0f, 6, 6, 6), PartPose.offsetAndRotation(0.0f, 16.0f, 58.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail6", CubeListBuilder.create().texOffs(156, 26).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 6), PartPose.offsetAndRotation(0.0f, 16.0f, 64.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("TopJaw", CubeListBuilder.create().texOffs(10, 13).addBox(-4.0f, -4.0f, -10.0f, 8, 4, 10), PartPose.offsetAndRotation(0.0f, -23.0f, -29.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RightEye", CubeListBuilder.create().texOffs(46, 16).addBox(-3.0f, -6.0f, -5.0f, 2, 2, 1), PartPose.offsetAndRotation(0.0f, -23.0f, -29.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("LeftEye", CubeListBuilder.create().texOffs(4, 16).addBox(1.0f, -6.0f, -5.0f, 2, 2, 1), PartPose.offsetAndRotation(0.0f, -23.0f, -29.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(SeaMonster entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.animAgeInTicks = ageInTicks;
        this.animLimbSwingAmount = limbSwingAmount;
        this.animNetHeadYaw = netHeadYaw;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        SeaMonster entity = this.animEntity;
        float ageInTicks = this.animAgeInTicks;
        float limbSwingAmount = this.animLimbSwingAmount;
        float netHeadYaw = this.animNetHeadYaw;
float newangle = 0.0f;
        float pi4 = 0.7853982f;
        newangle = (double)limbSwingAmount > 0.1 || entity.getAttacking() != 0 ? Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.2f * limbSwingAmount : 0.0f;
        this.TailBase.yRot = newangle / 7.0f;
        this.Tail2.z = this.TailBase.z + (float)Math.cos(this.TailBase.yRot) * 10.0f;
        this.Tail2.x = this.TailBase.x + (float)Math.sin(this.TailBase.yRot) * 10.0f;
        this.Tail2.yRot = newangle / 6.0f;
        this.Tail3.z = this.Tail2.z + (float)Math.cos(this.Tail2.yRot) * 7.0f;
        this.Tail3.x = this.Tail2.x + (float)Math.sin(this.Tail2.yRot) * 7.0f;
        this.Tail3.yRot = newangle / 5.0f;
        this.Tail4.z = this.Tail3.z + (float)Math.cos(this.Tail3.yRot) * 5.0f;
        this.Tail4.x = this.Tail3.x + (float)Math.sin(this.Tail3.yRot) * 5.0f;
        this.Tail4.yRot = newangle / 4.0f;
        this.Tail5.z = this.Tail4.z + (float)Math.cos(this.Tail4.yRot) * 5.0f;
        this.Tail5.x = this.Tail4.x + (float)Math.sin(this.Tail4.yRot) * 5.0f;
        this.Tail5.yRot = newangle / 3.0f;
        this.Tail6.z = this.Tail5.z + (float)Math.cos(this.Tail5.yRot) * 5.0f;
        this.Tail6.x = this.Tail5.x + (float)Math.sin(this.Tail5.yRot) * 5.0f;
        this.Tail6.yRot = newangle / 2.0f;
        this.TailTip.z = this.Tail6.z + (float)Math.cos(this.Tail6.yRot) * 5.0f;
        this.TailTip.x = this.Tail6.x + (float)Math.sin(this.Tail6.yRot) * 5.0f;
        this.TailTip.yRot = newangle;
        newangle = (double)limbSwingAmount > 0.1 || entity.getAttacking() != 0 ? Mth.cos((float)(ageInTicks * 1.2f * this.wingspeed)) * 3.1415927f * 0.2f * limbSwingAmount : Mth.cos((float)(ageInTicks * 1.2f * this.wingspeed)) * 3.1415927f * 0.02f;
        this.FinFrontLeft.xRot = newangle - 0.523f;
        this.FinFrontLeft.yRot = newangle + 0.698f;
        this.FinBackLeft.xRot = - newangle - 0.523f;
        this.FinBackLeft.yRot = - newangle + 0.698f;
        this.FinFrontRight.xRot = newangle - 0.523f;
        this.FinFrontRight.yRot = newangle - 0.698f;
        this.FinBackRight.xRot = - newangle - 0.523f;
        this.FinBackRight.yRot = - newangle - 0.698f;
        newangle = (double)limbSwingAmount > 0.1 || entity.getAttacking() != 0 ? 0.455f * limbSwingAmount + Mth.cos((float)(ageInTicks * 0.9f * this.wingspeed)) * 3.1415927f * 0.25f * limbSwingAmount : Mth.cos((float)(ageInTicks * 0.3f * this.wingspeed)) * 3.1415927f * 0.02f;
        this.NeckBase.xRot = 0.455f + newangle / 5.0f;
        this.Neck2.z = this.NeckBase.z - (float)Math.sin(this.NeckBase.xRot) * 9.0f;
        this.Neck2.y = this.NeckBase.y - (float)Math.cos(this.NeckBase.xRot) * 9.0f;
        this.Neck2.xRot = this.NeckBase.xRot + newangle / 4.0f;
        this.Neck3.z = this.Neck2.z - (float)Math.sin(this.Neck2.xRot) * 9.0f;
        this.Neck3.y = this.Neck2.y - (float)Math.cos(this.Neck2.xRot) * 9.0f;
        this.Neck3.xRot = this.Neck2.xRot + newangle / 3.0f;
        this.Neck4.z = this.Neck3.z - (float)Math.sin(this.Neck3.xRot) * 9.0f;
        this.Neck4.y = this.Neck3.y - (float)Math.cos(this.Neck3.xRot) * 9.0f;
        this.Neck4.xRot = this.Neck3.xRot + newangle / 2.0f;
        this.Neck5.z = this.Neck4.z - (float)Math.sin(this.Neck4.xRot) * 9.0f;
        this.Neck5.y = this.Neck4.y - (float)Math.cos(this.Neck4.xRot) * 9.0f;
        this.Neck5.xRot = this.Neck4.xRot - newangle / 2.0f;
        this.Neck6.z = this.Neck5.z - (float)Math.sin(this.Neck5.xRot) * 5.0f;
        this.Neck6.y = this.Neck5.y - (float)Math.cos(this.Neck5.xRot) * 5.0f;
        this.Neck6.xRot = this.Neck5.xRot - newangle / 3.0f;
        this.RightEye.z = this.TopJaw.z = this.Neck6.z - (float)Math.sin(this.Neck6.xRot) * 5.0f;
        this.LeftEye.z = this.TopJaw.z;
        this.BottomJaw.z = this.TopJaw.z;
        this.RightEye.y = this.TopJaw.y = this.Neck6.y - (float)Math.cos(this.Neck6.xRot) * 5.0f;
        this.LeftEye.y = this.TopJaw.y;
        this.BottomJaw.y = this.TopJaw.y;
        this.RightEye.yRot = this.TopJaw.yRot = (newangle = (float)Math.toRadians(netHeadYaw) * 0.5f);
        this.LeftEye.yRot = this.TopJaw.yRot;
        this.BottomJaw.yRot = this.TopJaw.yRot;
        if (entity.getAttacking() != 0) {
            newangle = Mth.cos((float)(ageInTicks * 1.7f * this.wingspeed)) * 3.1415927f * 0.17f;
            this.BottomJaw.xRot = 0.45f + newangle;
        } else {
            newangle = Mth.cos((float)(ageInTicks * 0.2f * this.wingspeed)) * 3.1415927f * 0.05f;
            this.BottomJaw.xRot = 0.17f + newangle;
        }
        this.TailTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyBack.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyFront.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.NeckBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BottomJaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinBackRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinBackLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinFrontLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FinFrontRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopJaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightEye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftEye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
