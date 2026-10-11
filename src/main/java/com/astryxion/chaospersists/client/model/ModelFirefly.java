package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Firefly;
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

public class ModelFirefly extends EntityModel<Firefly> {
    private Firefly animEntity;
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart wingLeft;
    private final ModelPart wingRight;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart eyeLeft;
    private final ModelPart eyeRight;
    private final ModelPart frontLegLeft;
    private final ModelPart frontLegRight;
    private final ModelPart backLegLeft;
    private final ModelPart backLegRight;
    private final ModelPart tailLight;

    public ModelFirefly(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 128).bakeRoot());
    }

    public ModelFirefly(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.wingLeft = root.getChild("wing_left");
        this.wingRight = root.getChild("wing_right");
        this.head = root.getChild("head");
        this.mouth = root.getChild("mouth");
        this.eyeLeft = root.getChild("eye_left");
        this.eyeRight = root.getChild("eye_right");
        this.frontLegLeft = root.getChild("front_leg_left");
        this.frontLegRight = root.getChild("front_leg_right");
        this.backLegLeft = root.getChild("back_leg_left");
        this.backLegRight = root.getChild("back_leg_right");
        this.tailLight = root.getChild("tail_light");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(38, 12).addBox(-3.0f, -3.0f, -3.0f, 5.0f, 5.0f, 5.0f),
                PartPose.offset(-1.0f, 9.0f, -1.0f));
        root.addOrReplaceChild(
                "wing_left",
                CubeListBuilder.create().texOffs(46, 0).addBox(0.0f, -6.0f, 0.0f, 0.0f, 6.0f, 2.0f),
                PartPose.offsetAndRotation(1.0f, 6.0f, -2.0f, 0.0f, 0.0174533f, 0.6981317f));
        root.addOrReplaceChild(
                "wing_right",
                CubeListBuilder.create().texOffs(53, 0).addBox(0.0f, -6.0f, 0.0f, 0.0f, 6.0f, 2.0f),
                PartPose.offsetAndRotation(-4.0f, 6.0f, -2.0f, 0.0f, 0.0f, -0.6981317f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(3, 14).addBox(0.0f, 0.0f, 0.0f, 3.0f, 3.0f, 3.0f),
                PartPose.offsetAndRotation(-3.0f, 7.0f, -7.0f, 0.2230717f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "mouth",
                CubeListBuilder.create().texOffs(26, 15).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 3.0f),
                PartPose.offsetAndRotation(-2.0f, 9.0f, -8.0f, 0.2117115f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "eye_left",
                CubeListBuilder.create().texOffs(18, 12).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 2.0f),
                PartPose.offsetAndRotation(-1.0f, 6.5f, -6.0f, 0.0174533f, 0.2602503f, -0.2230717f));
        root.addOrReplaceChild(
                "eye_right",
                CubeListBuilder.create().texOffs(18, 18).addBox(1.0f, -0.6f, -0.6f, 1.0f, 2.0f, 2.0f),
                PartPose.offsetAndRotation(-4.0f, 6.5f, -6.0f, 0.0f, -0.2602503f, 0.2230717f));
        root.addOrReplaceChild(
                "front_leg_left",
                CubeListBuilder.create().texOffs(32, 0).addBox(0.0f, 0.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(-1.0f, 10.0f, -3.0f, -0.2792527f, 0.0f, -0.2792527f));
        root.addOrReplaceChild(
                "front_leg_right",
                CubeListBuilder.create().texOffs(22, 0).addBox(0.0f, 0.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(-3.0f, 10.0f, -3.0f, -0.2792527f, 0.0f, 0.2792527f));
        root.addOrReplaceChild(
                "back_leg_left",
                CubeListBuilder.create().texOffs(11, 0).addBox(0.0f, 0.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(-1.0f, 10.0f, -1.0f, 0.2792527f, 0.0f, -0.2792527f));
        root.addOrReplaceChild(
                "back_leg_right",
                CubeListBuilder.create().texOffs(2, 0).addBox(0.0f, 0.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(-3.0f, 10.0f, -1.0f, 0.2792527f, 0.0f, 0.2792527f));
        root.addOrReplaceChild(
                "tail_light",
                CubeListBuilder.create().texOffs(10, 27).addBox(0.0f, 0.0f, 0.0f, 3.0f, 3.0f, 4.0f),
                PartPose.offset(-3.0f, 6.0f, 1.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Firefly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.wingLeft.zRot = 1.11f + Mth.cos(ageInTicks * this.wingspeed) * (float) Math.PI * 0.35f;
        this.wingRight.zRot = -1.11f - Mth.cos(ageInTicks * this.wingspeed) * (float) Math.PI * 0.35f;
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
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wingLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wingRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eyeLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.eyeRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.frontLegLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.frontLegRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.backLegLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.backLegRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        int tailLight =
                this.animEntity != null && this.animEntity.getBlink() > 0.0f ? 15728880 : packedLight;
        this.tailLight.render(poseStack, buffer, tailLight, packedOverlay, red, green, blue, alpha);
    }
}
