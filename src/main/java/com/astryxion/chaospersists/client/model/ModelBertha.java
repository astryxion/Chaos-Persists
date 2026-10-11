package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelBertha {
    private final ModelPart Grip;
    private final ModelPart Blade;
    private final ModelPart Handguard2;
    private final ModelPart Handguard1;
    private final ModelPart hg2;
    private final ModelPart hg4;
    private final ModelPart hg3;
    private final ModelPart hg1;
    private final ModelPart BaseGrip;
    private final ModelPart Tip1;
    private final ModelPart Tip2;
    private final ModelPart Bottom;

    public ModelBertha() {
        this(LayerDefinition.create(createMesh(), 64, 128).bakeRoot());
    }

    public ModelBertha(ModelPart root) {
        this.Grip = root.getChild("Grip");
        this.Blade = root.getChild("Blade");
        this.Handguard2 = root.getChild("Handguard2");
        this.Handguard1 = root.getChild("Handguard1");
        this.hg2 = root.getChild("hg2");
        this.hg4 = root.getChild("hg4");
        this.hg3 = root.getChild("hg3");
        this.hg1 = root.getChild("hg1");
        this.BaseGrip = root.getChild("BaseGrip");
        this.Tip1 = root.getChild("Tip1");
        this.Tip2 = root.getChild("Tip2");
        this.Bottom = root.getChild("Bottom");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Grip", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -6.0f, 0.0f, 1, 12, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Blade", CubeListBuilder.create().texOffs(6, 0).addBox(0.0f, -41.0f, -1.0f, 1, 34, 3), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Handguard2", CubeListBuilder.create().texOffs(16, 0).addBox(0.0f, -7.0f, -4.0f, 1, 1, 9), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Handguard1", CubeListBuilder.create().texOffs(18, 12).addBox(-3.0f, -7.0f, 0.0f, 7, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg2", CubeListBuilder.create().texOffs(0, 15).addBox(0.0f, -8.0f, -5.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg4", CubeListBuilder.create().texOffs(0, 18).addBox(0.0f, -8.0f, 5.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg3", CubeListBuilder.create().texOffs(0, 21).addBox(-4.0f, -8.0f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg1", CubeListBuilder.create().texOffs(0, 24).addBox(4.0f, -8.0f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BaseGrip", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0f, 5.0f, -1.0f, 3, 1, 3), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tip1", CubeListBuilder.create().texOffs(21, 16).addBox(0.0f, -42.0f, -0.5f, 1, 1, 2), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tip2", CubeListBuilder.create().texOffs(22, 20).addBox(0.0f, -43.0f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(0, 45).addBox(0.0f, 6.0f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        this.Grip.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Handguard2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Handguard1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.BaseGrip.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Tip1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Tip2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Bottom.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
