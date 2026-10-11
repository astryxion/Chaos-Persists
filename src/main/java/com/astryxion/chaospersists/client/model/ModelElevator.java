package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.item.Elevator;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelElevator extends EntityModel<Elevator> {
    private final ModelPart shape1;
    private final ModelPart shape2;
    private final ModelPart shape3;
    private final ModelPart shape4;
    private final ModelPart shape5;

    public ModelElevator() {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelElevator(ModelPart root) {
        this.shape1 = root.getChild("shape1");
        this.shape2 = root.getChild("shape2");
        this.shape3 = root.getChild("shape3");
        this.shape4 = root.getChild("shape4");
        this.shape5 = root.getChild("shape5");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "shape2",
                CubeListBuilder.create().texOffs(0, 18).addBox(-3.0f, 0.0f, -9.0f, 6, 1, 1),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "shape3",
                CubeListBuilder.create().texOffs(0, 21).addBox(-1.0f, 0.0f, -10.0f, 2, 1, 1),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "shape4",
                CubeListBuilder.create().texOffs(17, 18).addBox(-3.0f, 0.0f, 8.0f, 6, 1, 1),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "shape5",
                CubeListBuilder.create().texOffs(17, 21).addBox(-1.0f, 0.0f, 9.0f, 2, 1, 1),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "shape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, 0.0f, -8.0f, 8, 1, 16),
                PartPose.ZERO);
        return mesh;
    }

    @Override
    public void setupAnim(Elevator entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

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
        this.shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
