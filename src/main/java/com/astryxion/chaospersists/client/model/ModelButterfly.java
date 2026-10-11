package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.EntityButterfly;
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

public class ModelButterfly extends EntityModel<EntityButterfly> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart leftwing;
    private final ModelPart rightwing;
    private final ModelPart leftwing2;
    private final ModelPart rightwing2;
    private final ModelPart leftwing3;
    private final ModelPart rightwing3;
    private final ModelPart head;
    private final ModelPart leftwing4;
    private final ModelPart rightwing4;

    public ModelButterfly(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelButterfly(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.leftwing = root.getChild("leftwing");
        this.rightwing = root.getChild("rightwing");
        this.leftwing2 = root.getChild("leftwing2");
        this.rightwing2 = root.getChild("rightwing2");
        this.leftwing3 = root.getChild("leftwing3");
        this.rightwing3 = root.getChild("rightwing3");
        this.head = root.getChild("head");
        this.leftwing4 = root.getChild("leftwing4");
        this.rightwing4 = root.getChild("rightwing4");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(21, 19).addBox(0.0f, 0.0f, -4.0f, 1.0f, 1.0f, 8.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "leftwing",
                CubeListBuilder.create().texOffs(43, 24).addBox(0.0f, 0.0f, -4.0f, 5.0f, 1.0f, 5.0f),
                PartPose.offset(1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "rightwing",
                CubeListBuilder.create().texOffs(43, 17).addBox(-5.0f, 0.0f, -4.0f, 5.0f, 1.0f, 5.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "leftwing2",
                CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, 0.0f, -6.0f, 6.0f, 1.0f, 7.0f),
                PartPose.offset(1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "rightwing2",
                CubeListBuilder.create().texOffs(29, 0).addBox(-7.0f, 0.0f, -6.0f, 6.0f, 1.0f, 7.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "leftwing3",
                CubeListBuilder.create().texOffs(0, 9).addBox(0.0f, 0.0f, 1.0f, 5.0f, 1.0f, 5.0f),
                PartPose.offset(1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "rightwing3",
                CubeListBuilder.create().texOffs(27, 9).addBox(-5.0f, 0.0f, 1.0f, 5.0f, 1.0f, 5.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(21, 11).addBox(0.0f, 0.0f, -6.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 17.0f, 1.0f));
        root.addOrReplaceChild(
                "leftwing4",
                CubeListBuilder.create().texOffs(2, 24).addBox(0.0f, 0.0f, 6.0f, 1.0f, 1.0f, 7.0f),
                PartPose.offset(1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "rightwing4",
                CubeListBuilder.create().texOffs(2, 16).addBox(-1.0f, 0.0f, 6.0f, 1.0f, 1.0f, 7.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(EntityButterfly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float flap = Mth.cos(ageInTicks * 1.3f * this.wingspeed) * (float) Math.PI * 0.25f;
        this.rightwing2.zRot = flap;
        this.rightwing.zRot = flap;
        this.rightwing3.zRot = flap;
        this.rightwing4.zRot = flap;
        this.leftwing.zRot = -flap;
        this.leftwing2.zRot = -flap;
        this.leftwing3.zRot = -flap;
        this.leftwing4.zRot = -flap;
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
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
