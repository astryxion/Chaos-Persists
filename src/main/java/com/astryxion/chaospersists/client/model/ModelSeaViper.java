package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.SeaViper;
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

public class ModelSeaViper extends EntityModel<SeaViper> {
    private final float wingspeed;
    private SeaViper animEntity;
    private float animAgeInTicks;
    private float animLimbSwingAmount;
    private float animNetHeadYaw;
    private float toungBaseBaseZ;
    private float middleToungeBaseZ;
    private float forkRightBaseZ;
    private float forkLeftBaseZ;
    private final ModelPart TailTip;
    private final ModelPart Neck;
    private final ModelPart tBase;
    private final ModelPart t2;
    private final ModelPart t3;
    private final ModelPart t4;
    private final ModelPart t5;
    private final ModelPart t6;
    private final ModelPart t7;
    private final ModelPart t8;
    private final ModelPart t9;
    private final ModelPart t10;
    private final ModelPart t12;
    private final ModelPart t11;
    private final ModelPart t13;
    private final ModelPart t14;
    private final ModelPart t15;
    private final ModelPart t16;
    private final ModelPart t17;
    private final ModelPart t18;
    private final ModelPart t19;
    private final ModelPart t20;
    private final ModelPart t21;
    private final ModelPart MouthBottom;
    private final ModelPart ToungBase;
    private final ModelPart MiddleTounge;
    private final ModelPart EyeRight;
    private final ModelPart EyeLeft;
    private final ModelPart MouthTop;
    private final ModelPart Head;
    private final ModelPart FangRight;
    private final ModelPart FangLeft;
    private final ModelPart ForkRight;
    private final ModelPart ForkLeft;
    public ModelSeaViper(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelSeaViper(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.TailTip = root.getChild("TailTip");
        this.Neck = root.getChild("Neck");
        this.tBase = root.getChild("tBase");
        this.t2 = root.getChild("t2");
        this.t3 = root.getChild("t3");
        this.t4 = root.getChild("t4");
        this.t5 = root.getChild("t5");
        this.t6 = root.getChild("t6");
        this.t7 = root.getChild("t7");
        this.t8 = root.getChild("t8");
        this.t9 = root.getChild("t9");
        this.t10 = root.getChild("t10");
        this.t12 = root.getChild("t12");
        this.t11 = root.getChild("t11");
        this.t13 = root.getChild("t13");
        this.t14 = root.getChild("t14");
        this.t15 = root.getChild("t15");
        this.t16 = root.getChild("t16");
        this.t17 = root.getChild("t17");
        this.t18 = root.getChild("t18");
        this.t19 = root.getChild("t19");
        this.t20 = root.getChild("t20");
        this.t21 = root.getChild("t21");
        this.MouthBottom = root.getChild("MouthBottom");
        this.ToungBase = root.getChild("ToungBase");
        this.MiddleTounge = root.getChild("MiddleTounge");
        this.EyeRight = root.getChild("EyeRight");
        this.EyeLeft = root.getChild("EyeLeft");
        this.MouthTop = root.getChild("MouthTop");
        this.Head = root.getChild("Head");
        this.FangRight = root.getChild("FangRight");
        this.FangLeft = root.getChild("FangLeft");
        this.ForkRight = root.getChild("ForkRight");
        this.ForkLeft = root.getChild("ForkLeft");
        this.toungBaseBaseZ = this.ToungBase.z;
        this.middleToungeBaseZ = this.MiddleTounge.z;
        this.forkRightBaseZ = this.ForkRight.z;
        this.forkLeftBaseZ = this.ForkLeft.z;
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("TailTip", CubeListBuilder.create().texOffs(0, 90).addBox(-1.0f, -1.0f, 0.0f, 2, 5, 10), PartPose.offsetAndRotation(1.0f, 20.0f, 120.0f + 32.0f, 0.0f, -0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(60, 60).addBox(-4.0f, -4.0f, -10.0f, 8, 8, 10), PartPose.offsetAndRotation(0.0f, 4.5f, -33.0f + 32.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tBase", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(0.0f, 4.0f, -34.0f + 32.0f, -0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t2", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(0.0f, 7.0f, -27.0f + 32.0f, -1.047198f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t3", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(0.0f, 14.0f, -24.0f + 32.0f, -0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t4", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(0.0f, 19.0f, -17.0f + 32.0f, -0.0872665f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t5", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(0.0f, 20.0f, -9.0f + 32.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t6", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(0.0f, 20.0f, -1.0f + 32.0f, 0.0f, 0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("t7", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(2.0f, 20.0f, 6.0f + 32.0f, 0.0f, 0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("t8", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(7.0f, 20.0f, 12.0f + 32.0f, 0.0f, 0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("t9", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(10.0f, 20.0f, 20.0f + 32.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t10", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(10.0f, 20.0f, 28.0f + 32.0f, 0.0f, -0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("t12", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(2.0f, 20.0f, 42.0f + 32.0f, 0.0f, -0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("t11", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(8.0f, 20.0f, 35.0f + 32.0f, 0.0f, -0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("t13", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 10), PartPose.offsetAndRotation(-4.0f, 20.0f, 48.0f + 32.0f, 0.0f, -0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("t14", CubeListBuilder.create().texOffs(0, 51).addBox(-3.0f, -3.0f, 0.0f, 6, 7, 10), PartPose.offsetAndRotation(-8.0f, 20.0f, 56.0f + 32.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t15", CubeListBuilder.create().texOffs(0, 51).addBox(-3.0f, -3.0f, 0.0f, 6, 7, 10), PartPose.offsetAndRotation(-8.0f, 20.0f, 65.0f + 32.0f, 0.0f, 0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("t16", CubeListBuilder.create().texOffs(0, 51).addBox(-3.0f, -3.0f, 0.0f, 6, 7, 10), PartPose.offsetAndRotation(-5.0f, 20.0f, 73.0f + 32.0f, 0.0f, 0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("t17", CubeListBuilder.create().texOffs(0, 70).addBox(-2.0f, -2.0f, 0.0f, 4, 6, 10), PartPose.offsetAndRotation(1.0f, 20.0f, 80.0f + 32.0f, 0.0f, 0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("t18", CubeListBuilder.create().texOffs(0, 70).addBox(-2.0f, -2.0f, 0.0f, 4, 6, 10), PartPose.offsetAndRotation(7.0f, 20.0f, 87.0f + 32.0f, 0.0f, 0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("t19", CubeListBuilder.create().texOffs(0, 70).addBox(-2.0f, -2.0f, 0.0f, 4, 6, 10), PartPose.offsetAndRotation(10.0f, 20.0f, 95.0f + 32.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("t20", CubeListBuilder.create().texOffs(0, 90).addBox(-1.0f, -1.0f, 0.0f, 2, 5, 10), PartPose.offsetAndRotation(10.0f, 20.0f, 104.0f + 32.0f, 0.0f, -0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("t21", CubeListBuilder.create().texOffs(0, 90).addBox(-1.0f, -1.0f, 0.0f, 2, 5, 10), PartPose.offsetAndRotation(7.0f, 20.0f, 113.0f + 32.0f, 0.0f, -0.6981317f, 0.0f));
        partdefinition.addOrReplaceChild("MouthBottom", CubeListBuilder.create().texOffs(58, 78).addBox(-4.0f, 0.0f, -12.0f, 8, 2, 12), PartPose.offsetAndRotation(0.0f, 4.0f, -42.0f + 32.0f, 0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("ToungBase", CubeListBuilder.create().texOffs(70, 17).addBox(-1.0f, -2.0f, -11.0f, 2, 1, 6), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("MiddleTounge", CubeListBuilder.create().texOffs(70, 10).addBox(-1.0f, -1.0f, -17.0f, 2, 1, 6), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("EyeRight", CubeListBuilder.create().texOffs(96, 60).addBox(-7.0f, -7.0f, -3.0f, 1, 3, 4), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.3490659f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("EyeLeft", CubeListBuilder.create().texOffs(50, 60).addBox(6.0f, -7.0f, -3.0f, 1, 3, 4), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.3490659f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("MouthTop", CubeListBuilder.create().texOffs(52, 24).addBox(-5.0f, -6.0f, -16.0f, 10, 6, 16), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(60, 46).addBox(-6.0f, -8.0f, -6.0f, 12, 8, 6), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("FangRight", CubeListBuilder.create().texOffs(92, 18).addBox(-4.0f, -3.0f, -15.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("FangLeft", CubeListBuilder.create().texOffs(60, 18).addBox(3.0f, -3.0f, -15.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("ForkRight", CubeListBuilder.create().texOffs(60, 3).addBox(6.0f, 0.6f, -21.0f, 2, 1, 6), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.0872665f, 0.4363323f, 0.0f));
        partdefinition.addOrReplaceChild("ForkLeft", CubeListBuilder.create().texOffs(80, 3).addBox(-8.0f, 0.6f, -21.0f, 2, 1, 6), PartPose.offsetAndRotation(0.0f, 6.0f, -40.0f + 32.0f, 0.0872665f, -0.4363323f, 0.0f));
        return meshdefinition;
    }
    private void doseg(ModelPart inn, ModelPart notinn, float f, float limbSwingAmount, float ageInTicks) {
        float pi4 = 0.7853982f;
        float newangle = 0.0f;
        notinn.z = (float)((double)inn.z + (double)((float)Math.cos(inn.yRot)) * (9.0 * Math.abs(Math.cos(inn.xRot))));
        notinn.x = (float)((double)inn.x + (double)((float)Math.sin(inn.yRot) * 9.0f) * Math.abs(Math.cos(inn.xRot)));
        newangle = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed - pi4 * f)) * 3.1415927f * 0.2f * limbSwingAmount;
        float a = Mth.cos((float)(- pi4 * f));
        notinn.yRot = newangle + a - a * limbSwingAmount;
    }

    @Override
    public void setupAnim(SeaViper entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.animAgeInTicks = ageInTicks;
        this.animLimbSwingAmount = limbSwingAmount;
        this.animNetHeadYaw = netHeadYaw;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        SeaViper entity = this.animEntity;
        float ageInTicks = this.animAgeInTicks;
        float limbSwingAmount = this.animLimbSwingAmount;
        float netHeadYaw = this.animNetHeadYaw;
float newangle = 0.0f;
        if (limbSwingAmount < 0.0f) {
            limbSwingAmount = 0.0f;
        }
        this.tBase.yRot = newangle = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f * limbSwingAmount;
        this.doseg(this.tBase, this.t2, 2.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t2, this.t3, 2.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t3, this.t4, 3.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t4, this.t5, 4.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t5, this.t6, 5.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t6, this.t7, 6.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t7, this.t8, 7.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t8, this.t9, 8.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t9, this.t10, 9.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t10, this.t11, 10.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t11, this.t12, 11.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t12, this.t13, 12.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t13, this.t14, 13.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t14, this.t15, 14.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t15, this.t16, 15.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t16, this.t17, 16.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t17, this.t18, 17.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t18, this.t19, 18.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t19, this.t20, 19.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t20, this.t21, 20.0f, limbSwingAmount, ageInTicks);
        this.doseg(this.t21, this.TailTip, 21.0f, limbSwingAmount, ageInTicks);
        if (entity.getAttacking() != 0) {
            newangle = Mth.cos((float)(ageInTicks * 1.7f * this.wingspeed)) * 3.1415927f * 0.17f;
            this.MouthBottom.xRot = 0.65f + newangle;
            newangle = Mth.cos((float)(ageInTicks * 4.7f * this.wingspeed)) * 3.1415927f * 0.07f;
            this.ToungBase.xRot = 0.261f + newangle;
            this.MiddleTounge.xRot = 0.174f + newangle;
            this.ForkLeft.xRot = 0.087f + newangle;
            this.ForkRight.xRot = 0.087f + newangle;
            newangle = Mth.cos((float)(ageInTicks * 1.5f * this.wingspeed)) * 3.1415927f * 0.05f;
            this.ForkRight.z = this.forkRightBaseZ + newangle;
            this.ForkLeft.z = this.forkLeftBaseZ + newangle;
            this.MiddleTounge.z = this.middleToungeBaseZ + newangle;
            this.ToungBase.z = this.toungBaseBaseZ + newangle;
        } else {
            newangle = Mth.cos((float)(ageInTicks * 0.2f * this.wingspeed)) * 3.1415927f * 0.02f;
            this.MouthBottom.xRot = 0.45f + newangle;
            newangle = Mth.cos((float)(ageInTicks * 1.7f * this.wingspeed)) * 3.1415927f * 0.03f;
            this.ToungBase.xRot = 0.261f + newangle;
            this.MiddleTounge.xRot = 0.174f + newangle;
            this.ForkLeft.xRot = 0.087f + newangle;
            this.ForkRight.xRot = 0.087f + newangle;
            newangle = Mth.cos((float)(ageInTicks * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f;
            this.ForkRight.z = this.forkRightBaseZ + newangle;
            this.ForkLeft.z = this.forkLeftBaseZ + newangle;
            this.MiddleTounge.z = this.middleToungeBaseZ + newangle;
            this.ToungBase.z = this.toungBaseBaseZ + newangle;
        }
        this.EyeLeft.yRot = this.EyeRight.yRot = (newangle = (float) Math.toRadians(netHeadYaw) * 0.5f);
        this.MouthTop.yRot = this.EyeRight.yRot;
        this.Head.yRot = this.EyeRight.yRot;
        this.FangLeft.yRot = this.FangRight.yRot = newangle;
        this.MouthBottom.yRot = newangle;
        this.MouthBottom.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 2.0f;
        this.MouthBottom.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 2.0f;
        this.ToungBase.yRot = newangle;
        this.MiddleTounge.yRot = newangle;
        this.ForkLeft.yRot = newangle - 0.436f;
        this.ForkRight.yRot = newangle + 0.436f;
        this.TailTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t13.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t14.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t15.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t17.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t18.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t19.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t20.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MouthBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ToungBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MiddleTounge.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.EyeRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.EyeLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MouthTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FangRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FangLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ForkRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ForkLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
