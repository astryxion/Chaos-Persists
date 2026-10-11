package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Fairy;
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
import javax.annotation.Nullable;

public class ModelFairy extends EntityModel<Fairy> {
    @Nullable
    private Fairy animatingEntity;
    private final float wingspeed;
    private final ModelPart head;
    private final ModelPart chest;
    private final ModelPart waist;
    private final ModelPart hips;
    private final ModelPart lleg1;
    private final ModelPart lleg2;
    private final ModelPart rleg;
    private final ModelPart b1;
    private final ModelPart b2;
    private final ModelPart larm;
    private final ModelPart rarm;
    private final ModelPart lwing2;
    private final ModelPart lwing1;
    private final ModelPart rwing2;
    private final ModelPart rwing1;

    public ModelFairy(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot(), f1);
    }

    public ModelFairy(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.head = root.getChild("head");
        this.chest = root.getChild("chest");
        this.waist = root.getChild("waist");
        this.hips = root.getChild("hips");
        this.lleg1 = root.getChild("lleg1");
        this.lleg2 = root.getChild("lleg2");
        this.rleg = root.getChild("rleg");
        this.b1 = root.getChild("b1");
        this.b2 = root.getChild("b2");
        this.larm = root.getChild("larm");
        this.rarm = root.getChild("rarm");
        this.lwing2 = root.getChild("lwing2");
        this.lwing1 = root.getChild("lwing1");
        this.rwing2 = root.getChild("rwing2");
        this.rwing1 = root.getChild("rwing1");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, -5.0f, -2.5f, 5.0f, 5.0f, 5.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "chest",
                CubeListBuilder.create().texOffs(31, 5).addBox(-3.5f, 0.0f, -1.0f, 7.0f, 4.0f, 3.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "waist",
                CubeListBuilder.create().texOffs(33, 13).addBox(-2.5f, 4.0f, -1.0f, 5.0f, 3.0f, 3.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "hips",
                CubeListBuilder.create().texOffs(31, 20).addBox(-3.0f, 7.0f, -1.0f, 6.0f, 4.0f, 4.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "lleg1",
                CubeListBuilder.create().texOffs(53, 8).addBox(0.0f, 0.0f, 0.0f, 2.0f, 7.0f, 2.0f),
                PartPose.offsetAndRotation(1.0f, 10.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "lleg2",
                CubeListBuilder.create().texOffs(53, 18).addBox(0.0f, 0.0f, 0.0f, 2.0f, 8.0f, 2.0f),
                PartPose.offsetAndRotation(1.0f, 15.0f, -5.0f, 0.7679449f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "rleg",
                CubeListBuilder.create().texOffs(51, 30).addBox(-3.0f, 0.0f, 0.0f, 2.0f, 13.0f, 2.0f),
                PartPose.offset(0.0f, 11.0f, 0.0f));
        root.addOrReplaceChild(
                "b1",
                CubeListBuilder.create().texOffs(42, 1).addBox(1.0f, 1.0f, -2.0f, 2.0f, 2.0f, 1.0f),
                PartPose.offset(0.0f, 1.0f, 0.0f));
        root.addOrReplaceChild(
                "b2",
                CubeListBuilder.create().texOffs(32, 1).addBox(-3.0f, 2.0f, -2.0f, 2.0f, 2.0f, 1.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "larm",
                CubeListBuilder.create().texOffs(7, 14).addBox(0.0f, 0.0f, 0.0f, 1.0f, 10.0f, 1.0f),
                PartPose.offsetAndRotation(3.0f, 0.0f, 0.0f, -0.0174533f, 0.0f, -0.122173f));
        root.addOrReplaceChild(
                "rarm",
                CubeListBuilder.create().texOffs(2, 14).addBox(-1.0f, 0.0f, 0.0f, 1.0f, 10.0f, 1.0f),
                PartPose.offsetAndRotation(-3.0f, 0.0f, 0.0f, -0.0174533f, 0.0f, 0.122173f));
        root.addOrReplaceChild(
                "lwing2",
                CubeListBuilder.create().texOffs(0, 47).addBox(0.0f, -9.0f, 0.0f, 26.0f, 16.0f, 0.0f),
                PartPose.offsetAndRotation(2.0f, 0.0f, 2.0f, 0.0f, -0.5934119f, 0.0f));
        root.addOrReplaceChild(
                "lwing1",
                CubeListBuilder.create().texOffs(0, 30).addBox(0.0f, -7.0f, 0.0f, 24.0f, 16.0f, 0.0f),
                PartPose.offsetAndRotation(2.0f, 3.0f, 2.0f, 0.0f, -0.8203047f, 0.0f));
        root.addOrReplaceChild(
                "rwing2",
                CubeListBuilder.create().texOffs(0, 30).addBox(0.0f, -7.0f, 0.0f, 24.0f, 16.0f, 0.0f),
                PartPose.offsetAndRotation(-2.0f, 3.0f, 2.0f, 0.0f, -2.356194f, 0.0f));
        root.addOrReplaceChild(
                "rwing1",
                CubeListBuilder.create().texOffs(0, 47).addBox(0.0f, -9.0f, 0.0f, 26.0f, 16.0f, 0.0f),
                PartPose.offsetAndRotation(-2.0f, 0.0f, 2.0f, 0.0f, -2.548181f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(
            Fairy entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        this.animatingEntity = entity;
        this.lwing1.yRot = -0.6f + Mth.cos(ageInTicks * this.wingspeed) * (float) Math.PI * 0.35f;
        this.rwing1.yRot = -2.55f - Mth.cos(ageInTicks * this.wingspeed) * (float) Math.PI * 0.35f;
        this.lwing2.yRot = -0.6f + Mth.cos(ageInTicks * this.wingspeed * 0.85f) * (float) Math.PI * 0.25f;
        this.rwing2.yRot = -2.55f - Mth.cos(ageInTicks * this.wingspeed * 0.85f) * (float) Math.PI * 0.25f;
        this.head.yRot = Mth.clamp(netHeadYaw * ((float) Math.PI / 180.0f) * 0.45f, -0.45f, 0.45f);
        this.head.xRot = headPitch * ((float) Math.PI / 180.0f);
        this.larm.xRot = -0.2f + Mth.cos(ageInTicks * this.wingspeed * 0.15f) * (float) Math.PI * 0.05f;
        this.rarm.xRot = -0.2f + Mth.cos(ageInTicks * this.wingspeed * 0.12f) * (float) Math.PI * 0.05f;
        this.larm.zRot = -0.15f + Mth.cos(ageInTicks * this.wingspeed * 0.1f) * (float) Math.PI * 0.03f;
        this.rarm.zRot = 0.15f + Mth.cos(ageInTicks * this.wingspeed * 0.11f) * (float) Math.PI * 0.03f;
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
        this.lwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        int bodyLight =
                this.animatingEntity != null && this.animatingEntity.getBlink() > 1.0f ? 15728880 : packedLight;
        this.head.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.chest.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.waist.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.hips.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.b1.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.b2.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.larm.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
        this.rarm.render(poseStack, buffer, bodyLight, packedOverlay, red, green, blue, alpha);
    }
}
