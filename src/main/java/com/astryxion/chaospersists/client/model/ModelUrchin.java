package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Urchin;
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

public class ModelUrchin extends EntityModel<Urchin> {
    private final float wingspeed;
    private final ModelPart if1;
    private final ModelPart if2;
    private final ModelPart if3;
    private final ModelPart if4;
    private final ModelPart of1;
    private final ModelPart of2;
    private final ModelPart of3;
    private final ModelPart of4;
    private final ModelPart center;
    private final ModelPart tis1;
    private final ModelPart tis2;
    private final ModelPart tis3;
    private final ModelPart tis4;
    private final ModelPart tos1;
    private final ModelPart tos2;
    private final ModelPart tos3;
    private final ModelPart tos4;

    public ModelUrchin(float f1) {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot(), f1);
    }

    public ModelUrchin(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.if1 = root.getChild("if1");
        this.if2 = root.getChild("if2");
        this.if3 = root.getChild("if3");
        this.if4 = root.getChild("if4");
        this.of1 = root.getChild("of1");
        this.of2 = root.getChild("of2");
        this.of3 = root.getChild("of3");
        this.of4 = root.getChild("of4");
        this.center = root.getChild("center");
        this.tis1 = root.getChild("tis1");
        this.tis2 = root.getChild("tis2");
        this.tis3 = root.getChild("tis3");
        this.tis4 = root.getChild("tis4");
        this.tos1 = root.getChild("tos1");
        this.tos2 = root.getChild("tos2");
        this.tos3 = root.getChild("tos3");
        this.tos4 = root.getChild("tos4");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "if1",
                CubeListBuilder.create().texOffs(0, 35).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "if2",
                CubeListBuilder.create().texOffs(5, 35).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, -0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "if3",
                CubeListBuilder.create().texOffs(10, 35).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.0f, 0.0f, 0.2617994f));
        root.addOrReplaceChild(
                "if4",
                CubeListBuilder.create().texOffs(15, 35).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.0f, 0.0f, -0.2617994f));
        root.addOrReplaceChild(
                "of1",
                CubeListBuilder.create().texOffs(0, 45).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(2.0f, 16.0f, 0.0f, 0.0f, 0.0f, -0.5235988f));
        root.addOrReplaceChild(
                "of2",
                CubeListBuilder.create().texOffs(5, 45).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(-2.0f, 16.0f, 0.0f, 0.0f, 0.0f, 0.5235988f));
        root.addOrReplaceChild(
                "of3",
                CubeListBuilder.create().texOffs(10, 45).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, -2.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "of4",
                CubeListBuilder.create().texOffs(15, 45).addBox(0.0f, 0.0f, 0.0f, 1, 8, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 2.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "center",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -30.0f, 0.0f, 1, 30, 1),
                PartPose.offset(0.0f, 16.0f, 0.0f));
        root.addOrReplaceChild(
                "tis1",
                CubeListBuilder.create().texOffs(25, 0).addBox(0.0f, -25.0f, 0.0f, 1, 25, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "tis2",
                CubeListBuilder.create().texOffs(30, 0).addBox(0.0f, -25.0f, 0.0f, 1, 25, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, -0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "tis3",
                CubeListBuilder.create().texOffs(35, 0).addBox(0.0f, -25.0f, 0.0f, 1, 25, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.0f, 0.0f, 0.2617994f));
        root.addOrReplaceChild(
                "tis4",
                CubeListBuilder.create().texOffs(40, 0).addBox(0.0f, -25.0f, 0.0f, 1, 25, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.0f, 0.0f, -0.2617994f));
        root.addOrReplaceChild(
                "tos1",
                CubeListBuilder.create().texOffs(5, 0).addBox(0.0f, -20.0f, 0.0f, 1, 20, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 2.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "tos2",
                CubeListBuilder.create().texOffs(10, 0).addBox(-2.0f, -20.0f, 0.0f, 1, 20, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.0f, 0.0f, -0.5235988f));
        root.addOrReplaceChild(
                "tos3",
                CubeListBuilder.create().texOffs(15, 0).addBox(0.0f, -20.0f, 0.0f, 1, 20, 1),
                PartPose.offsetAndRotation(2.0f, 16.0f, 0.0f, 0.0f, 0.0f, 0.5235988f));
        root.addOrReplaceChild(
                "tos4",
                CubeListBuilder.create().texOffs(20, 0).addBox(0.0f, -20.0f, 0.0f, 1, 20, 1),
                PartPose.offsetAndRotation(0.0f, 16.0f, -2.0f, 0.5235988f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(
            Urchin entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        float newangle1;
        float newangle5;
        float newangle2;
        float newangle;
        float newangle3;
        float newangle8;
        float newangle4;
        float newangle7;
        float newangle6;
        if ((double) limbSwingAmount > 0.1) {
            newangle = Mth.cos((float) (ageInTicks * 0.7f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount;
            newangle1 = Mth.cos((float) (ageInTicks * 1.7f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount;
            newangle2 = Mth.cos((float) (ageInTicks * 1.65f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount;
            newangle3 = Mth.cos((float) (ageInTicks * 1.75f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount;
            newangle4 = Mth.cos((float) (ageInTicks * 1.8f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount;
        } else {
            newangle = 0.0f;
            newangle1 = 0.0f;
            newangle2 = 0.0f;
            newangle3 = 0.0f;
            newangle4 = 0.0f;
        }
        this.if1.xRot = 0.261f + newangle1;
        this.if2.xRot = -0.261f - newangle2;
        this.if3.zRot = newangle3;
        this.if4.zRot = -newangle4;
        this.of1.zRot = -0.523f + newangle;
        this.of2.zRot = 0.523f - newangle;
        this.of3.xRot = -0.523f + newangle;
        this.of4.xRot = 0.523f - newangle;
        if (entity.getAttacking() != 0) {
            newangle = (float) ((double) (ageInTicks * 0.2f) % 6.283185307179586);
            newangle1 = Mth.cos((float) (ageInTicks * 0.7f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle2 = Mth.cos((float) (ageInTicks * 0.65f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle3 = Mth.cos((float) (ageInTicks * 0.75f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle4 = Mth.cos((float) (ageInTicks * 0.8f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle5 = Mth.cos((float) (ageInTicks * 0.55f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle6 = Mth.cos((float) (ageInTicks * 0.45f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle7 = Mth.cos((float) (ageInTicks * 0.35f * this.wingspeed)) * 3.1415927f * 0.06f;
            newangle8 = Mth.cos((float) (ageInTicks * 0.4f * this.wingspeed)) * 3.1415927f * 0.06f;
        } else {
            newangle = (float) ((double) (ageInTicks * 0.02f) % 6.283185307179586);
            newangle1 = Mth.cos((float) (ageInTicks * 0.07f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle2 = Mth.cos((float) (ageInTicks * 0.065f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle3 = Mth.cos((float) (ageInTicks * 0.075f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle4 = Mth.cos((float) (ageInTicks * 0.08f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle5 = Mth.cos((float) (ageInTicks * 0.055f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle6 = Mth.cos((float) (ageInTicks * 0.045f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle7 = Mth.cos((float) (ageInTicks * 0.035f * this.wingspeed)) * 3.1415927f * 0.02f;
            newangle8 = Mth.cos((float) (ageInTicks * 0.04f * this.wingspeed)) * 3.1415927f * 0.02f;
        }
        this.center.yRot = newangle;
        this.tis1.xRot = 0.261f + newangle1;
        this.tis2.xRot = -0.261f + newangle2;
        this.tis3.xRot = newangle3;
        this.tis4.xRot = newangle4;
        this.tis1.zRot = newangle5;
        this.tis2.zRot = newangle6;
        this.tis3.zRot = 0.261f + newangle7;
        this.tis4.zRot = -0.261f + newangle8;
        this.tos1.xRot = -0.532f + newangle1;
        this.tos2.xRot = newangle7;
        this.tos3.xRot = newangle3;
        this.tos4.xRot = 0.532f + newangle5;
        this.tos1.zRot = newangle4;
        this.tos2.zRot = -0.523f + newangle6;
        this.tos3.zRot = 0.523f + newangle2;
        this.tos4.zRot = newangle8;
    }

    @Override
    public void renderToBuffer(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha) {
        this.if1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.if2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.if3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.if4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.of4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.center.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tis4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tos4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
