package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelSlice {
    private final ModelPart Grip;
    private final ModelPart Blade1;
    private final ModelPart Handguard2;
    private final ModelPart Handguard1;
    private final ModelPart hg2;
    private final ModelPart hg4;
    private final ModelPart hg3;
    private final ModelPart hg1;
    private final ModelPart BaseGrip;
    private final ModelPart Bottom;
    private final ModelPart Blade2;
    private final ModelPart Blade3;
    private final ModelPart Blade4;
    private final ModelPart Shape1;

    public ModelSlice() {
        this(LayerDefinition.create(createMesh(), 64, 128).bakeRoot());
    }

    public ModelSlice(ModelPart root) {
        this.Grip = root.getChild("Grip");
        this.Blade1 = root.getChild("Blade1");
        this.Handguard2 = root.getChild("Handguard2");
        this.Handguard1 = root.getChild("Handguard1");
        this.hg2 = root.getChild("hg2");
        this.hg4 = root.getChild("hg4");
        this.hg3 = root.getChild("hg3");
        this.hg1 = root.getChild("hg1");
        this.BaseGrip = root.getChild("BaseGrip");
        this.Bottom = root.getChild("Bottom");
        this.Blade2 = root.getChild("Blade2");
        this.Blade3 = root.getChild("Blade3");
        this.Blade4 = root.getChild("Blade4");
        this.Shape1 = root.getChild("Shape1");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Grip", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -6.0f, 0.0f, 1, 12, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Blade1", CubeListBuilder.create().texOffs(6, 49).addBox(0.0f, -41.0f, 0.0f, 1, 34, 3), PartPose.offsetAndRotation(0.5f, 0.0f, -2.3f, 0.0f, 0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("Handguard2", CubeListBuilder.create().texOffs(16, 0).addBox(0.0f, -7.0f, -4.0f, 1, 1, 9), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Handguard1", CubeListBuilder.create().texOffs(18, 12).addBox(-3.0f, -7.0f, 0.0f, 7, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg2", CubeListBuilder.create().texOffs(0, 15).addBox(0.0f, -9.0f, -7.0f, 1, 3, 3), PartPose.offsetAndRotation(0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg4", CubeListBuilder.create().texOffs(0, 22).addBox(0.0f, -9.0f, 5.0f, 1, 3, 3), PartPose.offsetAndRotation(0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg3", CubeListBuilder.create().texOffs(0, 29).addBox(-4.0f, -9.0f, 0.0f, 3, 3, 1), PartPose.offsetAndRotation(-2.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("hg1", CubeListBuilder.create().texOffs(0, 34).addBox(4.0f, -9.0f, 0.0f, 3, 3, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BaseGrip", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0f, 5.0f, -1.0f, 3, 1, 3), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(0, 45).addBox(0.0f, 6.0f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Blade2", CubeListBuilder.create().texOffs(24, 49).addBox(-1.0f, -41.0f, 0.0f, 1, 34, 3), PartPose.offsetAndRotation(0.5f, 0.0f, -2.3f, 0.0f, -0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("Blade3", CubeListBuilder.create().texOffs(15, 49).addBox(0.0f, -41.0f, 0.0f, 1, 34, 3), PartPose.offsetAndRotation(1.5f, 0.0f, 0.4f, 0.0f, -0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("Blade4", CubeListBuilder.create().texOffs(33, 49).addBox(0.0f, -41.0f, 0.0f, 1, 34, 3), PartPose.offsetAndRotation(-1.5f, 0.0f, 0.7f, 0.0f, 0.3490659f, 0.0f));
        partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(6, 0).addBox(0.0f, -6.0f, 0.0f, 1, 6, 3), PartPose.offsetAndRotation(0.5f, -40.0f, -1.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        this.Grip.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Handguard2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Handguard1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.hg1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.BaseGrip.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Bottom.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Shape1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
