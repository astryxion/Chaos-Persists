package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.RockBase;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelRockBase extends EntityModel<RockBase> {
    private final ModelPart rockShape1;
    private final ModelPart rockShape2;
    private final ModelPart rockShape3;
    private final ModelPart rockSmallShape2;
    private final ModelPart rockSmallShape1;
    private final ModelPart rockTntShape1;
    private final ModelPart rockTntShape2;
    private final ModelPart rockTntShape3;
    private final ModelPart rockTntShape4;
    private final ModelPart rockSpikeyShape1;
    private final ModelPart rockSpikeyShape2;
    private final ModelPart rockSpikeyShape3;
    private final ModelPart crystalShape1;
    private final ModelPart crystalShape2;
    private final ModelPart crystalShape3a;
    private final ModelPart crystalShape3b;
    private final ModelPart crystalShape3c;
    private final ModelPart crystalShape3d;
    private final ModelPart crystalShape4a;
    private final ModelPart crystalShape4b;
    private final ModelPart crystalShape4c;
    private final ModelPart crystalShape4d;
    private RockBase animEntity;

    public ModelRockBase(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelRockBase(ModelPart root) {
        this.rockShape1 = root.getChild("RockShape1");
        this.rockShape2 = root.getChild("RockShape2");
        this.rockShape3 = root.getChild("RockShape3");
        this.rockSmallShape2 = root.getChild("RockSmallShape2");
        this.rockSmallShape1 = root.getChild("RockSmallShape1");
        this.rockTntShape1 = root.getChild("RockTNTShape1");
        this.rockTntShape2 = root.getChild("RockTNTShape2");
        this.rockTntShape3 = root.getChild("RockTNTShape3");
        this.rockTntShape4 = root.getChild("RockTNTShape4");
        this.rockSpikeyShape1 = root.getChild("RockSpikeyShape1");
        this.rockSpikeyShape2 = root.getChild("RockSpikeyShape2");
        this.rockSpikeyShape3 = root.getChild("RockSpikeyShape3");
        this.crystalShape1 = root.getChild("CrystalShape1");
        this.crystalShape2 = root.getChild("CrystalShape2");
        this.crystalShape3a = root.getChild("CrystalShape3a");
        this.crystalShape3b = root.getChild("CrystalShape3b");
        this.crystalShape3c = root.getChild("CrystalShape3c");
        this.crystalShape3d = root.getChild("CrystalShape3d");
        this.crystalShape4a = root.getChild("CrystalShape4a");
        this.crystalShape4b = root.getChild("CrystalShape4b");
        this.crystalShape4c = root.getChild("CrystalShape4c");
        this.crystalShape4d = root.getChild("CrystalShape4d");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "RockShape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, 0.0f, -1.0f, 6.0f, 1.0f, 2.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockShape2",
                CubeListBuilder.create().texOffs(0, 4).addBox(-3.0f, 0.0f, 1.0f, 3.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockShape3",
                CubeListBuilder.create().texOffs(0, 7).addBox(0.0f, 0.0f, -2.0f, 2.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockSmallShape2",
                CubeListBuilder.create().texOffs(0, 4).addBox(-2.0f, 0.0f, 0.0f, 3.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockSmallShape1",
                CubeListBuilder.create().texOffs(0, 7).addBox(0.0f, 0.0f, -1.0f, 2.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockTNTShape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, 0.0f, -1.0f, 6.0f, 1.0f, 2.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockTNTShape2",
                CubeListBuilder.create().texOffs(0, 4).addBox(-3.0f, 0.0f, 1.0f, 3.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockTNTShape3",
                CubeListBuilder.create().texOffs(0, 7).addBox(0.0f, 0.0f, -2.0f, 2.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockTNTShape4",
                CubeListBuilder.create().texOffs(0, 10).addBox(-4.0f, 0.0f, -2.0f, 3.0f, 1.0f, 3.0f),
                PartPose.offset(0.0f, 22.0f, 0.0f));
        root.addOrReplaceChild(
                "RockSpikeyShape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, 0.0f, -1.0f, 6.0f, 1.0f, 2.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "RockSpikeyShape2",
                CubeListBuilder.create().texOffs(0, 4).addBox(-4.0f, 0.0f, -1.0f, 3.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 0.0f, 1.570796f, 0.0f));
        root.addOrReplaceChild(
                "RockSpikeyShape3",
                CubeListBuilder.create().texOffs(0, 7).addBox(1.0f, 0.0f, 1.0f, 2.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 0.0f, 1.570796f, 0.0f));
        root.addOrReplaceChild(
                "CrystalShape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -4.0f, -1.0f, 2.0f, 5.0f, 2.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "CrystalShape2",
                CubeListBuilder.create().texOffs(10, 0).addBox(-0.5f, -7.0f, -0.5f, 1.0f, 3.0f, 1.0f),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "CrystalShape3a",
                CubeListBuilder.create().texOffs(0, 8).addBox(-1.0f, -5.0f, -1.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 0.5410521f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "CrystalShape3b",
                CubeListBuilder.create().texOffs(0, 8).addBox(0.0f, -5.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, -0.5410521f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "CrystalShape3c",
                CubeListBuilder.create().texOffs(0, 8).addBox(0.0f, -5.0f, -1.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 0.0f, 0.0f, 0.5410521f));
        root.addOrReplaceChild(
                "CrystalShape3d",
                CubeListBuilder.create().texOffs(0, 8).addBox(-1.0f, -5.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 0.0f, 0.0f, -0.5410521f));
        root.addOrReplaceChild(
                "CrystalShape4a",
                CubeListBuilder.create().texOffs(0, 16).addBox(0.0f, -3.0f, -1.0f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 1.308997f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "CrystalShape4b",
                CubeListBuilder.create().texOffs(0, 16).addBox(-1.0f, -3.0f, 0.0f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, -1.308997f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "CrystalShape4c",
                CubeListBuilder.create().texOffs(0, 16).addBox(0.0f, -3.0f, 0.0f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 0.0f, 0.0f, 1.308997f));
        root.addOrReplaceChild(
                "CrystalShape4d",
                CubeListBuilder.create().texOffs(0, 16).addBox(-1.0f, -3.0f, -1.0f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 23.0f, 0.0f, 0.0f, 0.0f, -1.308997f));
        return mesh;
    }

    @Override
    public void setupAnim(RockBase entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
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
        RockBase entity = this.animEntity;
        if (entity == null) {
            return;
        }
        int rt = entity.rock_type > 0 ? entity.rock_type : entity.getRockType();
        if (rt < 1 || rt > 12) {
            return;
        }
        if (rt == 1) {
            this.rockSmallShape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockSmallShape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        } else if (rt == 7) {
            this.rockSpikeyShape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockSpikeyShape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockSpikeyShape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        } else if (rt == 8) {
            this.rockTntShape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockTntShape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockTntShape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockTntShape4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        } else if (rt >= 9 && rt <= 12) {
            float crystalRed = 0.75f;
            float crystalGreen = 0.75f;
            float crystalBlue = 0.75f;
            float crystalAlpha = 0.55f;
            this.crystalShape1.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape2.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape3a.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape3b.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape3c.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape3d.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape4a.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape4b.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape4c.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
            this.crystalShape4d.render(poseStack, buffer, packedLight, packedOverlay, crystalRed, crystalGreen, crystalBlue, crystalAlpha);
        } else {
            this.rockShape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockShape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            this.rockShape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }
    }
}
