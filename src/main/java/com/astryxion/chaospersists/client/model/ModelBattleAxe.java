package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelBattleAxe {
    private final ModelPart Handle1;
    private final ModelPart Head1;
    private final ModelPart Grip;
    private final ModelPart Pin;
    private final ModelPart Top;
    private final ModelPart Blade1;
    private final ModelPart Blade2;
    private final ModelPart Blade3;
    private final ModelPart Blade4;
    private final ModelPart Blade5;
    private final ModelPart Blade6;
    private final ModelPart Blade7;
    private final ModelPart Blade8;
    private final ModelPart Blade9;
    private final ModelPart Blade10;

    public ModelBattleAxe() {
        this(LayerDefinition.create(createMesh(), 128, 64).bakeRoot());
    }

    public ModelBattleAxe(ModelPart root) {
        this.Handle1 = root.getChild("Handle1");
        this.Head1 = root.getChild("Head1");
        this.Grip = root.getChild("Grip");
        this.Pin = root.getChild("Pin");
        this.Top = root.getChild("Top");
        this.Blade1 = root.getChild("Blade1");
        this.Blade2 = root.getChild("Blade2");
        this.Blade3 = root.getChild("Blade3");
        this.Blade4 = root.getChild("Blade4");
        this.Blade5 = root.getChild("Blade5");
        this.Blade6 = root.getChild("Blade6");
        this.Blade7 = root.getChild("Blade7");
        this.Blade8 = root.getChild("Blade8");
        this.Blade9 = root.getChild("Blade9");
        this.Blade10 = root.getChild("Blade10");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Handle1", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0f, -0.5f, 0.0f, 31, 2, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 1.570796f));
        partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(29, 18).addBox(-2.0f, -4.5f, -0.5f, 3, 4, 2), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Grip", CubeListBuilder.create().texOffs(0, 7).addBox(-1.92f, 13.0f, -0.5f, 3, 11, 2), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Pin", CubeListBuilder.create().texOffs(38, 11).addBox(-1.0f, -3.0f, -1.0f, 1, 1, 3), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(24, 11).addBox(-2.0f, -8.0f, -0.5f, 3, 2, 2), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Blade1", CubeListBuilder.create().texOffs(70, 0).addBox(6.0f, -8.0f, 0.0f, 3, 10, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.5061455f));
        partdefinition.addOrReplaceChild("Blade2", CubeListBuilder.create().texOffs(70, 0).addBox(8.5f, -6.9f, 0.0f, 3, 10, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, -0.5061455f));
        partdefinition.addOrReplaceChild("Blade3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -3.0f, 0.0f, 10, 1, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Blade4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -2.0f, 0.0f, 7, 1, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.5061455f));
        partdefinition.addOrReplaceChild("Blade5", CubeListBuilder.create().texOffs(0, 0).addBox(0.5f, -3.5f, 0.0f, 8, 1, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, -0.5061455f));
        partdefinition.addOrReplaceChild("Blade6", CubeListBuilder.create().texOffs(70, 0).addBox(-12.2f, -5.2f, 0.0f, 3, 10, 1), PartPose.offsetAndRotation(0.0f, -13.0f, 0.0f, 0.0f, 0.0f, 0.5061455f));
        partdefinition.addOrReplaceChild("Blade7", CubeListBuilder.create().texOffs(0, 0).addBox(-9.9f, -3.0f, 0.0f, 8, 1, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.5061455f));
        partdefinition.addOrReplaceChild("Blade8", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0f, -3.0f, 0.0f, 10, 1, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Blade9", CubeListBuilder.create().texOffs(70, 0).addBox(-10.0f, -8.5f, 0.0f, 3, 10, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, -0.5061455f));
        partdefinition.addOrReplaceChild("Blade10", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0f, -2.5f, 0.0f, 7, 1, 1), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, -0.5061455f));
        return meshdefinition;
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        this.Handle1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Head1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Grip.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Pin.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Top.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade5.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade6.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade7.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade8.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade9.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade10.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
