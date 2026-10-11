package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.GammaMetroid;
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

public class ModelGammaMetroid extends EntityModel<GammaMetroid> {
    private final float wingspeed;
    private final ModelPart Shell3;
    private final ModelPart Shell4;
    private final ModelPart Head;
    private final ModelPart BeakUpper;
    private final ModelPart BeakLower;
    private final ModelPart LeftTusk;
    private final ModelPart MiddleTusk;
    private final ModelPart RightTusk;
    private final ModelPart LeftFrontUpperLeg;
    private final ModelPart LeftFrontLowerLeg;
    private final ModelPart LeftRearUpperLeg;
    private final ModelPart LeftRearLowerLeg;
    private final ModelPart RightFrontUpperLeg;
    private final ModelPart RightFrontLowerLeg;
    private final ModelPart RightRearUpperLeg;
    private final ModelPart RightRearLowerLeg;
    private final ModelPart Core;
    private final ModelPart Bellyinside;
    private final ModelPart Bellyoutside;
    private final ModelPart Shell1;
    private final ModelPart Shell2;

    public ModelGammaMetroid(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 256, 64).bakeRoot(), wingspeed);
    }

    public ModelGammaMetroid(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Shell3 = root.getChild("Shell3");
        this.Shell4 = root.getChild("Shell4");
        this.Head = root.getChild("Head");
        this.BeakUpper = root.getChild("BeakUpper");
        this.BeakLower = root.getChild("BeakLower");
        this.LeftTusk = root.getChild("LeftTusk");
        this.MiddleTusk = root.getChild("MiddleTusk");
        this.RightTusk = root.getChild("RightTusk");
        this.LeftFrontUpperLeg = root.getChild("LeftFrontUpperLeg");
        this.LeftFrontLowerLeg = root.getChild("LeftFrontLowerLeg");
        this.LeftRearUpperLeg = root.getChild("LeftRearUpperLeg");
        this.LeftRearLowerLeg = root.getChild("LeftRearLowerLeg");
        this.RightFrontUpperLeg = root.getChild("RightFrontUpperLeg");
        this.RightFrontLowerLeg = root.getChild("RightFrontLowerLeg");
        this.RightRearUpperLeg = root.getChild("RightRearUpperLeg");
        this.RightRearLowerLeg = root.getChild("RightRearLowerLeg");
        this.Core = root.getChild("Core");
        this.Bellyinside = root.getChild("Bellyinside");
        this.Bellyoutside = root.getChild("Bellyoutside");
        this.Shell1 = root.getChild("Shell1");
        this.Shell2 = root.getChild("Shell2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        // 1.12 mirror=true only flips UVs in 1.20; keep original box coords so geometry stays unchanged.
        root.addOrReplaceChild("Shell3", CubeListBuilder.create().texOffs(128, 0).addBox(-6.0f, -6.0f, 0.0f, 12, 12, 7), PartPose.offsetAndRotation(0.0f, 7.0f, 10.0f, -0.9599311f, 0.6283185f, 0.5235988f));
        root.addOrReplaceChild("Shell4", CubeListBuilder.create().texOffs(48, 34).addBox(0.0f, 0.0f, 0.0f, 6, 6, 8), PartPose.offsetAndRotation(-3.0f, 9.0f, 13.0f, -0.2792527f, 0.0f, 0.0f));
        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(48, 48).addBox(0.0f, 0.0f, 0.0f, 16, 8, 6), PartPose.offset(-8.0f, -1.0f, -11.0f));
        root.addOrReplaceChild("BeakUpper", CubeListBuilder.create().texOffs(114, 44).addBox(-3.0f, 0.0f, -3.0f, 6, 4, 6), PartPose.offsetAndRotation(0.0f, 5.0f, -11.0f, 0.1047198f, 0.7853982f, 0.1047198f));
        root.addOrReplaceChild("BeakLower", CubeListBuilder.create().texOffs(120, 54).addBox(-1.5f, 0.0f, -1.5f, 3, 6, 3), PartPose.offsetAndRotation(0.0f, 9.0f, -12.0f, 0.1396263f, 0.7853982f, 0.1396263f));
        root.addOrReplaceChild("LeftTusk", CubeListBuilder.create().texOffs(76, 50).addBox(0.0f, 0.0f, -12.0f, 2, 2, 12), PartPose.offsetAndRotation(5.0f, 6.0f, -10.0f, 0.1047198f, 0.0872665f, 0.0f));
        root.addOrReplaceChild("MiddleTusk", CubeListBuilder.create().texOffs(76, 50).addBox(-1.0f, 0.0f, -12.0f, 2, 2, 12), PartPose.offsetAndRotation(0.0f, -2.0f, -10.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("RightTusk", CubeListBuilder.create().texOffs(76, 50).addBox(-2.0f, 0.0f, -12.0f, 2, 2, 12), PartPose.offsetAndRotation(-5.0f, 6.0f, -10.0f, 0.1047198f, -0.0872665f, 0.0f));
        root.addOrReplaceChild("LeftFrontUpperLeg", CubeListBuilder.create().texOffs(64, 0).addBox(0.0f, 0.0f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(8.0f, 8.0f, -2.0f, -0.1745329f, 0.0f, -0.6632251f));
        root.addOrReplaceChild("LeftFrontLowerLeg", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2), PartPose.offsetAndRotation(14.0f, 13.0f, -3.5f, -0.2617994f, 0.1396263f, 0.0f));
        root.addOrReplaceChild("LeftRearUpperLeg", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0f, 0.0f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(8.0f, 9.0f, 7.0f, 0.1745329f, 0.0f, -0.8203047f));
        root.addOrReplaceChild("LeftRearLowerLeg", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2), PartPose.offsetAndRotation(14.0f, 14.0f, 8.5f, 0.3141593f, -0.1570796f, -0.2792527f));
        root.addOrReplaceChild("RightFrontUpperLeg", CubeListBuilder.create().texOffs(64, 0).addBox(-3.0f, 0.0f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(-8.0f, 8.0f, -2.0f, -0.1745329f, 0.0f, 0.6632251f));
        root.addOrReplaceChild("RightFrontLowerLeg", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2), PartPose.offsetAndRotation(-14.0f, 13.0f, -3.5f, -0.2617994f, -0.1396263f, 0.0f));
        root.addOrReplaceChild("RightRearUpperLeg", CubeListBuilder.create().texOffs(64, 0).addBox(-2.0f, 0.0f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(-8.0f, 9.0f, 7.0f, 0.1745329f, 0.0f, 0.8203047f));
        root.addOrReplaceChild("RightRearLowerLeg", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0f, 0.0f, -1.0f, 2, 11, 2), PartPose.offsetAndRotation(-14.0f, 14.0f, 8.5f, 0.3141593f, 0.1570796f, 0.2792527f));
        root.addOrReplaceChild("Core", CubeListBuilder.create().texOffs(82, 33).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6), PartPose.offsetAndRotation(0.0f, 8.0f, 3.0f, -0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("Bellyinside", CubeListBuilder.create().texOffs(150, 3).addBox(-8.0f, -1.0f, -8.0f, 16, 1, 16), PartPose.offsetAndRotation(0.0f, 8.0f, 2.0f, -0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("Bellyoutside", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0f, -6.0f, -8.0f, 16, 14, 16), PartPose.offsetAndRotation(0.0f, 8.0f, 2.0f, -0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shell1", CubeListBuilder.create().texOffs(64, 0).addBox(-10.0f, -10.0f, 2.0f, 19, 19, 12), PartPose.offsetAndRotation(0.0f, 4.0f, -7.0f, 0.0f, 0.0f, 0.7853982f));
        root.addOrReplaceChild("Shell2", CubeListBuilder.create().texOffs(0, 30).addBox(-9.0f, -9.0f, 0.0f, 16, 16, 8), PartPose.offsetAndRotation(0.0f, 4.5f, 5.0f, -0.5235988f, 0.3665191f, 0.715585f));
        return mesh;
    }

    @Override
    public void setupAnim(GammaMetroid entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        this.LeftTusk.xRot = newangle = Mth.cos((float)(ageInTicks * 0.81f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.RightTusk.xRot = newangle = Mth.cos((float)(ageInTicks * 0.87f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.MiddleTusk.xRot = newangle = Mth.cos((float)(ageInTicks * 0.99f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.LeftTusk.yRot = newangle = Mth.cos((float)(ageInTicks * 1.11f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.RightTusk.yRot = newangle = Mth.cos((float)(ageInTicks * 1.17f * this.wingspeed)) * 3.1415927f * 0.08f;
        this.MiddleTusk.yRot = newangle = Mth.cos((float)(ageInTicks * 1.25f * this.wingspeed)) * 3.1415927f * 0.08f;
        float nextangle = 0.0f;
        float upangle = 0.0f;
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * limbSwingAmount;
        nextangle = Mth.cos((float)((ageInTicks + 0.1f) * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * limbSwingAmount;
        upangle = 0.0f;
        if (nextangle > newangle) {
            upangle = 0.47f * limbSwingAmount - Math.abs(newangle);
        }
        this.doLeftFLeg(this.LeftFrontUpperLeg, this.LeftFrontLowerLeg, newangle, upangle);
        this.doRightFLeg(this.RightFrontUpperLeg, this.RightFrontLowerLeg, - newangle, upangle);
        this.doLeftRLeg(this.LeftRearUpperLeg, this.LeftRearLowerLeg, - newangle, upangle);
        this.doRightRLeg(this.RightRearUpperLeg, this.RightRearLowerLeg, newangle, upangle);
        newangle = Mth.cos((float)(ageInTicks * 0.4f * this.wingspeed)) * 3.1415927f * 0.05f;
        if (entity.isInSittingPose()) {
            newangle = 0.0f;
        }
        this.Shell1.xRot = newangle / 4.0f;
        this.Shell1.yRot = - newangle / 4.0f;
        this.Shell2.xRot = newangle - 0.49f;
        this.Shell2.yRot = - newangle + 0.33f;
        this.Shell3.xRot = newangle - 0.96f;
        this.Shell3.yRot = - newangle + 0.63f;
        this.Shell4.xRot = newangle - 0.28f;
        newangle = Mth.cos((float)(ageInTicks * 0.75f * this.wingspeed)) * 3.1415927f * 0.1f;
        newangle = Math.abs(newangle);
        this.BeakLower.xRot = newangle + 0.14f;
        this.BeakLower.zRot = newangle + 0.14f;
    }

    private void doLeftFLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
        seg2.xRot = angle - 0.17f;
        seg3.xRot = angle - 0.26f;
        seg3.z = (float) ((double) seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = -upangle - 0.66f;
        seg3.zRot = -upangle;
        seg3.y = seg2.y + (float) (5.0 * Math.cos(seg2.xRot));
        seg3.x = (float) ((double) seg2.x + Math.abs(Math.sin(seg2.zRot) * 7.0) + 1.0);
    }

    private void doLeftRLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
        seg2.xRot = angle + 0.17f;
        seg3.xRot = angle + 0.31f;
        seg3.z = (float) ((double) seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = -upangle - 0.82f;
        seg3.zRot = -upangle;
        seg3.y = seg2.y + (float) (5.0 * Math.cos(seg2.xRot));
        seg3.x = (float) ((double) seg2.x + Math.abs(Math.sin(seg2.zRot) * 7.0) + 1.5);
    }

    private void doRightFLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
        seg2.xRot = angle - 0.17f;
        seg3.xRot = angle - 0.26f;
        seg3.z = (float) ((double) seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = -upangle + 0.34f;
        seg3.zRot = -upangle;
        seg3.y = seg2.y + (float) (5.0 * Math.cos(seg2.xRot));
        seg3.x = (float) ((double) seg2.x - Math.abs(Math.sin(seg2.zRot) * 7.0) - 1.0);
    }

    private void doRightRLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
        seg2.xRot = angle + 0.17f;
        seg3.xRot = angle + 0.31f;
        seg3.z = (float) ((double) seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5f;
        seg2.zRot = -upangle + 0.82f;
        seg3.zRot = -upangle;
        seg3.y = seg2.y + (float) (5.0 * Math.cos(seg2.xRot));
        seg3.x = (float) ((double) seg2.x - Math.abs(Math.sin(seg2.zRot) * 7.0) - 1.5);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Core.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BeakUpper.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BeakLower.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftTusk.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MiddleTusk.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightTusk.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftFrontUpperLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftFrontLowerLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftRearUpperLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftRearLowerLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightFrontUpperLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightFrontLowerLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightRearUpperLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightRearLowerLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bellyinside.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shell2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bellyoutside.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
