package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Stinky;
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

public class ModelStinky extends EntityModel<Stinky> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart neck1;
    private final ModelPart neck;
    private final ModelPart neckbase;
    private final ModelPart head;
    private final ModelPart Rleg1;
    private final ModelPart Lleg1;
    private final ModelPart Lhorn1;
    private final ModelPart Rhorn1;
    private final ModelPart snout;
    private final ModelPart Lhorn2;
    private final ModelPart Rhorn2;
    private final ModelPart tail1;
    private final ModelPart Rleg2;
    private final ModelPart Lleg2;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart Lwing;
    private final ModelPart Rwing;

    public ModelStinky(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 128, 64).bakeRoot(), wingspeed);
    }

    public ModelStinky(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.neck1 = root.getChild("neck1");
        this.neck = root.getChild("neck");
        this.neckbase = root.getChild("neckbase");
        this.head = root.getChild("head");
        this.Rleg1 = root.getChild("Rleg1");
        this.Lleg1 = root.getChild("Lleg1");
        this.Lhorn1 = root.getChild("Lhorn1");
        this.Rhorn1 = root.getChild("Rhorn1");
        this.snout = root.getChild("snout");
        this.Lhorn2 = root.getChild("Lhorn2");
        this.Rhorn2 = root.getChild("Rhorn2");
        this.tail1 = root.getChild("tail1");
        this.Rleg2 = root.getChild("Rleg2");
        this.Lleg2 = root.getChild("Lleg2");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.Lwing = root.getChild("Lwing");
        this.Rwing = root.getChild("Rwing");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 12).addBox(-4.5f, -3.0f, -5.0f, 8, 8, 10),
                PartPose.offset(0.5f, 15.0f, 1.0f));
        root.addOrReplaceChild(
                "neck1",
                CubeListBuilder.create().texOffs(0, 31).addBox(-2.0f, -3.0f, -2.0f, 4, 5, 5),
                PartPose.offsetAndRotation(0.0f, 16.0f, -5.0f, 0.715585f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "neck",
                CubeListBuilder.create().texOffs(0, 42).addBox(-2.0f, -8.0f, -3.0f, 4, 8, 4),
                PartPose.offset(0.0f, 15.0f, -5.5f));
        root.addOrReplaceChild(
                "neckbase",
                CubeListBuilder.create().texOffs(0, 55).addBox(-3.0f, -4.0f, 0.0f, 6, 6, 3),
                PartPose.offset(0.0f, 17.0f, 5.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, -10.0f, -3.5f, 5, 5, 5),
                PartPose.offset(0.0f, 15.0f, -5.5f));
        root.addOrReplaceChild(
                "Rleg1",
                CubeListBuilder.create().texOffs(19, 53).addBox(-1.5f, 0.0f, -1.0f, 3, 8, 3),
                PartPose.offset(2.0f, 16.0f, 5.5f));
        root.addOrReplaceChild(
                "Lleg1",
                CubeListBuilder.create().texOffs(19, 53).addBox(-1.5f, 0.0f, -0.5f, 3, 8, 3),
                PartPose.offset(-2.0f, 16.0f, 5.0f));
        root.addOrReplaceChild(
                "Lhorn1",
                CubeListBuilder.create().texOffs(19, 47).addBox(-3.0f, -10.5f, -1.0f, 2, 2, 3),
                PartPose.offset(0.0f, 15.0f, -5.5f));
        root.addOrReplaceChild(
                "Rhorn1",
                CubeListBuilder.create().texOffs(19, 47).addBox(1.0f, -10.5f, -1.0f, 2, 2, 3),
                PartPose.offset(0.0f, 15.0f, -5.5f));
        root.addOrReplaceChild(
                "snout",
                CubeListBuilder.create().texOffs(32, 57).addBox(-1.5f, -8.0f, -6.5f, 3, 3, 4),
                PartPose.offset(0.0f, 15.0f, -5.5f));
        root.addOrReplaceChild(
                "Lhorn2",
                CubeListBuilder.create().texOffs(19, 42).addBox(-2.5f, -10.0f, 1.0f, 1, 1, 3),
                PartPose.offset(0.0f, 15.0f, -5.5f));
        root.addOrReplaceChild(
                "Rhorn2",
                CubeListBuilder.create().texOffs(19, 42).addBox(1.5f, -10.0f, 1.0f, 1, 1, 3),
                PartPose.offset(0.0f, 15.0f, -5.5f));
        root.addOrReplaceChild(
                "tail1",
                CubeListBuilder.create().texOffs(47, 55).addBox(-3.0f, -3.0f, -3.0f, 6, 6, 3),
                PartPose.offset(0.0f, 16.5f, -2.0f));
        root.addOrReplaceChild(
                "Rleg2",
                CubeListBuilder.create().texOffs(19, 53).addBox(-1.5f, 0.0f, -1.5f, 3, 8, 3),
                PartPose.offset(2.0f, 16.0f, -3.0f));
        root.addOrReplaceChild(
                "Lleg2",
                CubeListBuilder.create().texOffs(19, 53).addBox(-1.5f, 0.0f, -1.5f, 3, 8, 3),
                PartPose.offset(-2.0f, 16.0f, -3.0f));
        root.addOrReplaceChild(
                "tail2",
                CubeListBuilder.create().texOffs(19, 31).addBox(-2.5f, -2.5f, 0.0f, 5, 5, 5),
                PartPose.offsetAndRotation(0.0f, 16.0f, 7.0f, -0.3839724f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "tail3",
                CubeListBuilder.create().texOffs(32, 46).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 4),
                PartPose.offsetAndRotation(0.0f, 17.2f, 11.0f, -0.2094395f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "tail4",
                CubeListBuilder.create().texOffs(37, 13).addBox(-1.5f, -1.5f, 0.0f, 3, 3, 5),
                PartPose.offsetAndRotation(0.0f, 17.5f, 14.0f, -0.0698132f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "Lwing",
                CubeListBuilder.create().texOffs(59, 0).addBox(-18.0f, 0.0f, -5.0f, 18, 0, 10),
                PartPose.offsetAndRotation(-2.0f, 12.6f, 0.0f, 0.0f, 0.0f, 0.4014257f));
        root.addOrReplaceChild(
                "Rwing",
                CubeListBuilder.create().texOffs(59, 11).addBox(0.0f, 0.0f, -5.0f, 18, 0, 10),
                PartPose.offsetAndRotation(2.0f, 12.6f, 0.0f, 0.0f, 0.0f, -0.4014257f));
        return mesh;
    }

    @Override
    public void setupAnim(
            Stinky entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        float newangle;
        int current_activity = entity.getActivity();
        // Activity 2 = flying. Full flap amplitude (OreSpawn f1~1 while moving).
        // Chaos flight skips travel(), so limbSwingAmount stays near 0 and looked frozen.
        if (current_activity == 2) {
            newangle = Mth.cos(ageInTicks * 2.3f * this.wingspeed) * 3.1415927f * 0.4f;
        } else {
            newangle =
                    (double) limbSwingAmount > 0.1
                            ? Mth.cos(ageInTicks * 2.3f * this.wingspeed)
                                    * 3.1415927f
                                    * 0.4f
                                    * limbSwingAmount
                            : 0.0f;
        }
        this.Rwing.zRot = newangle - 0.4f;
        this.Lwing.zRot = -newangle + 0.4f;
        newangle =
                (double) limbSwingAmount > 0.1
                        ? Mth.cos((float) (ageInTicks * 2.0f * this.wingspeed)) * 3.1415927f * 0.25f * limbSwingAmount
                        : 0.0f;
        if (current_activity != 2) {
            this.Rleg1.xRot = newangle;
            this.Lleg1.xRot = -newangle;
            this.Rleg2.xRot = -newangle;
            this.Lleg2.xRot = newangle;
        } else {
            this.Rleg2.xRot = newangle = -1.0f;
            this.Lleg2.xRot = newangle;
            this.Rleg1.xRot = newangle = 1.0f;
            this.Lleg1.xRot = newangle;
        }
        newangle = Mth.cos((float) (ageInTicks * 1.0f * this.wingspeed)) * 3.1415927f * 0.2f;
        if (entity.isInSittingPose()) {
            newangle = 0.0f;
        }
        this.tail2.yRot = newangle;
        this.tail3.z = this.tail2.z + (float) Math.cos(this.tail2.yRot) * 4.0f;
        this.tail3.x = this.tail2.x + (float) Math.sin(this.tail2.yRot) * 4.0f - 0.5f;
        this.tail3.yRot = newangle * 1.6f;
        this.tail4.z = this.tail3.z + (float) Math.cos(this.tail3.yRot) * 3.0f;
        this.tail4.x = this.tail3.x + (float) Math.sin(this.tail3.yRot) * 3.0f - 0.5f;
        this.tail4.yRot = newangle * 2.6f;
        this.head.yRot = (float) Math.toRadians(netHeadYaw);
        this.snout.yRot = (float) Math.toRadians(netHeadYaw);
        this.neck.yRot = (float) Math.toRadians(netHeadYaw) / 2.0f;
        this.Rhorn1.yRot = (float) Math.toRadians(netHeadYaw);
        this.Rhorn2.yRot = (float) Math.toRadians(netHeadYaw);
        this.Lhorn1.yRot = (float) Math.toRadians(netHeadYaw);
        this.Lhorn2.yRot = (float) Math.toRadians(netHeadYaw);
        this.head.xRot = (float) Math.toRadians(headPitch) / 3.0f;
        this.snout.xRot = (float) Math.toRadians(headPitch) / 3.0f;
        this.neck.xRot = (float) Math.toRadians(headPitch) / 3.0f;
        this.Rhorn1.xRot = (float) Math.toRadians(headPitch) / 3.0f;
        this.Rhorn2.xRot = (float) Math.toRadians(headPitch) / 3.0f;
        this.Lhorn1.xRot = (float) Math.toRadians(headPitch) / 3.0f;
        this.Lhorn2.xRot = (float) Math.toRadians(headPitch) / 3.0f;
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
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neckbase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lhorn1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rhorn1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.snout.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lhorn2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rhorn2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
