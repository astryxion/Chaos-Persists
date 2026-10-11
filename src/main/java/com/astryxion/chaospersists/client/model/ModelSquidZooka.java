package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelSquidZooka {
    private final ModelPart Barrel;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart tail5;
    private final ModelPart tail6;
    private final ModelPart tail7;
    private final ModelPart sight3;
    private final ModelPart sight2;
    private final ModelPart sight1;
    private final ModelPart handle1;

    public ModelSquidZooka() {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelSquidZooka(ModelPart root) {
        this.Barrel = root.getChild("Barrel");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.tail5 = root.getChild("tail5");
        this.tail6 = root.getChild("tail6");
        this.tail7 = root.getChild("tail7");
        this.sight3 = root.getChild("sight3");
        this.sight2 = root.getChild("sight2");
        this.sight1 = root.getChild("sight1");
        this.handle1 = root.getChild("handle1");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Barrel", CubeListBuilder.create().texOffs(29, 19).addBox(-1.0f, -1.0f, -19.0f, 2, 2, 34), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 53).addBox(-1.5f, -1.5f, 15.0f, 3, 3, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 58).addBox(-2.0f, -2.0f, 16.0f, 4, 4, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 64).addBox(-2.5f, -2.5f, 17.0f, 5, 5, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 71).addBox(-3.0f, -3.0f, 18.0f, 6, 6, 6), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(0, 84).addBox(-2.5f, -2.5f, 24.0f, 5, 5, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(0, 91).addBox(-2.0f, -2.0f, 25.0f, 4, 4, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail7", CubeListBuilder.create().texOffs(0, 97).addBox(-1.5f, -1.5f, 26.0f, 3, 3, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("sight3", CubeListBuilder.create().texOffs(25, 0).addBox(1.0f, -2.0f, -10.0f, 1, 1, 2), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("sight2", CubeListBuilder.create().texOffs(32, 0).addBox(0.5f, -4.0f, -12.0f, 2, 2, 6), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("sight1", CubeListBuilder.create().texOffs(18, 0).addBox(1.0f, -1.0f, -10.0f, 1, 1, 2), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("handle1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 1.0f, 0.0f, 1, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(180.0f));
        this.Barrel.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.tail5.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.tail6.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.tail7.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.sight3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.sight2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.sight1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.handle1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        poseStack.popPose();
    }
}
