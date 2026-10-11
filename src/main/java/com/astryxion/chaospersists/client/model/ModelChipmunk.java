package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.EntityCannonFodder;
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

public class ModelChipmunk extends EntityModel<Chipmunk> {
    private final float wingspeed;
    private final ModelPart cheek2;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart tail2;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart mouthUnder;
    private final ModelPart cheek1;
    private final ModelPart ear2;
    private final ModelPart nose;
    private final ModelPart ear1;
    private final ModelPart body;
    private final ModelPart bodyTail;
    private final ModelPart tail1;
    private final ModelPart hat1;
    private final ModelPart hat2;

    public ModelChipmunk(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelChipmunk(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.cheek2 = root.getChild("cheek2");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.tail2 = root.getChild("tail2");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.mouthUnder = root.getChild("mouth_under");
        this.cheek1 = root.getChild("cheek1");
        this.ear2 = root.getChild("ear2");
        this.nose = root.getChild("nose");
        this.ear1 = root.getChild("ear1");
        this.body = root.getChild("body");
        this.bodyTail = root.getChild("body_tail");
        this.tail1 = root.getChild("tail1");
        this.hat1 = root.getChild("hat1");
        this.hat2 = root.getChild("hat2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "cheek2",
                CubeListBuilder.create().texOffs(14, 0).addBox(0.5f, -1.5f, -3.5f, 2.0f, 2.0f, 2.0f),
                PartPose.offset(0.0f, 20.0f, -3.0f));
        root.addOrReplaceChild(
                "leg1",
                CubeListBuilder.create().texOffs(22, 7).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(-2.0f, 23.0f, -4.0f));
        root.addOrReplaceChild(
                "leg2",
                CubeListBuilder.create().texOffs(22, 9).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(1.0f, 23.0f, -4.0f));
        root.addOrReplaceChild(
                "leg3",
                CubeListBuilder.create().texOffs(22, 11).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(1.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "leg4",
                CubeListBuilder.create().texOffs(22, 13).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(-2.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "tail2",
                CubeListBuilder.create().texOffs(28, 15).addBox(-0.5f, 1.0f, 2.5f, 3.0f, 3.0f, 4.0f),
                PartPose.offsetAndRotation(-1.0f, 20.0f, 1.0f, 0.7662421f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "neck",
                CubeListBuilder.create().texOffs(26, 9).addBox(0.0f, 0.0f, 0.0f, 3.0f, 2.0f, 4.0f),
                PartPose.offsetAndRotation(-1.5f, 22.0f, -5.0f, 1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -3.0f, 0.0f, 4.0f, 4.0f, 3.0f),
                PartPose.offsetAndRotation(0.0f, 20.0f, -3.0f, 1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "mouth_under",
                CubeListBuilder.create().texOffs(20, 4).addBox(-1.0f, -1.9f, -3.8f, 2.0f, 2.0f, 1.0f),
                PartPose.offset(0.0f, 20.0f, -3.0f));
        root.addOrReplaceChild(
                "cheek1",
                CubeListBuilder.create().texOffs(22, 0).addBox(-2.5f, -1.5f, -3.5f, 2.0f, 2.0f, 2.0f),
                PartPose.offset(0.0f, 20.0f, -3.0f));
        root.addOrReplaceChild(
                "ear2",
                CubeListBuilder.create().texOffs(18, 11).addBox(1.0f, 0.0f, 3.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 20.0f, -3.0f, 1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(18, 7).addBox(-0.5f, -2.0f, -4.2f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 20.0f, -3.0f));
        root.addOrReplaceChild(
                "ear1",
                CubeListBuilder.create().texOffs(18, 9).addBox(-2.0f, 0.0f, 3.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 20.0f, -3.0f, 1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 7).addBox(0.0f, 0.0f, 0.0f, 4.0f, 3.0f, 5.0f),
                PartPose.offset(-2.0f, 20.0f, -4.0f));
        root.addOrReplaceChild(
                "body_tail",
                CubeListBuilder.create().texOffs(0, 15).addBox(0.0f, 0.0f, 0.0f, 5.0f, 4.0f, 3.0f),
                PartPose.offset(-2.5f, 19.0f, -1.0f));
        root.addOrReplaceChild(
                "tail1",
                CubeListBuilder.create().texOffs(16, 15).addBox(0.0f, 0.0f, 0.0f, 2.0f, 2.0f, 4.0f),
                PartPose.offsetAndRotation(-1.0f, 20.0f, 1.0f, 0.3064968f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "hat1",
                CubeListBuilder.create().texOffs(40, 0).addBox(-2.5f, -4.0f, -4.0f, 5.0f, 1.0f, 5.0f),
                PartPose.offset(0.0f, 20.0f, -3.0f));
        root.addOrReplaceChild(
                "hat2",
                CubeListBuilder.create().texOffs(40, 0).addBox(-2.0f, -6.0f, -3.0f, 4.0f, 2.0f, 4.0f),
                PartPose.offset(0.0f, 20.0f, -3.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Chipmunk entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle =
                limbSwingAmount > 0.1f
                        ? Mth.cos(ageInTicks * 2.3f * this.wingspeed) * (float) Math.PI * 0.25f * limbSwingAmount
                        : 0.0f;
        this.leg1.xRot = newangle;
        this.leg3.xRot = newangle;
        this.leg2.xRot = -newangle;
        this.leg4.xRot = -newangle;
        float headYaw = (float) Math.toRadians(netHeadYaw) * 0.45f;
        this.nose.yRot = headYaw;
        this.head.yRot = headYaw;
        this.ear1.yRot = headYaw;
        this.ear2.yRot = headYaw;
        this.mouthUnder.yRot = headYaw;
        this.cheek1.yRot = headYaw;
        this.cheek2.yRot = headYaw;
        this.hat1.yRot = headYaw;
        this.hat2.yRot = headYaw;
        if (!entity.isInSittingPose()) {
            this.tail1.xRot = 0.306f + Mth.cos(ageInTicks * 0.25f) * (float) Math.PI * 0.06f;
            newangle = Mth.cos(ageInTicks * 1.3f * this.wingspeed) * (float) Math.PI * 0.25f * limbSwingAmount;
            this.tail1.xRot += newangle;
            this.tail2.xRot = 0.306f + this.tail1.xRot;
        }
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
        this.cheek2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouthUnder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cheek1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ear2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ear1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodyTail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderHats(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            EntityCannonFodder entity,
            float red,
            float green,
            float blue,
            float alpha) {
        if (entity.get_is_activated() != 0) {
            this.hat1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            if (entity.get_is_activated() > 1) {
                this.hat2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            }
        }
    }
}
