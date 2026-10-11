package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelQueenBattleAxe {
    private final ModelPart Handle1;
    private final ModelPart Head1;
    private final ModelPart Grip;
    private final ModelPart Pin;
    private final ModelPart Blade1;
    private final ModelPart Blade2;
    private final ModelPart Blade3;
    private final ModelPart Blade4;
    private final ModelPart Top;

    public ModelQueenBattleAxe() {
        this(LayerDefinition.create(createMesh(), 128, 64).bakeRoot());
    }

    public ModelQueenBattleAxe(ModelPart root) {
        this.Handle1 = root.getChild("Handle1");
        this.Head1 = root.getChild("Head1");
        this.Grip = root.getChild("Grip");
        this.Pin = root.getChild("Pin");
        this.Blade1 = root.getChild("Blade1");
        this.Blade2 = root.getChild("Blade2");
        this.Blade3 = root.getChild("Blade3");
        this.Blade4 = root.getChild("Blade4");
        this.Top = root.getChild("Top");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Handle1", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0f, -0.5f, 0.0f, 31, 1, 1), PartPose.offsetAndRotation(-0.5f, -12.0f, 0.0f, 0.0f, 0.0f, 1.570796f));
        partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(29, 18).addBox(-2.0f, -4.5f, -0.5f, 3, 4, 2), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Grip", CubeListBuilder.create().texOffs(0, 7).addBox(-1.92f, 13.0f, -0.5f, 2, 11, 2), PartPose.offsetAndRotation(0.5f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Pin", CubeListBuilder.create().texOffs(38, 11).addBox(-1.0f, -3.0f, -1.0f, 1, 1, 3), PartPose.offsetAndRotation(0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Blade1", CubeListBuilder.create().texOffs(70, 0).addBox(-10.0f, -2.0f, 0.0f, 20, 4, 1), PartPose.offsetAndRotation(-0.5f, -14.5f, 0.0f, 0.0f, 0.0f, -0.5934119f));
        partdefinition.addOrReplaceChild("Blade2", CubeListBuilder.create().texOffs(70, 0).addBox(-10.0f, -2.0f, 0.0f, 20, 4, 1), PartPose.offsetAndRotation(-0.5f, -14.5f, 0.0f, 0.0f, 0.0f, -0.1919862f));
        partdefinition.addOrReplaceChild("Blade3", CubeListBuilder.create().texOffs(70, 0).addBox(-10.0f, -2.0f, 0.0f, 20, 4, 1), PartPose.offsetAndRotation(-0.5f, -14.5f, 0.0f, 0.0f, 0.0f, 0.2094395f));
        partdefinition.addOrReplaceChild("Blade4", CubeListBuilder.create().texOffs(70, 0).addBox(-10.0f, -2.0f, 0.0f, 20, 4, 1), PartPose.offsetAndRotation(-0.5f, -14.5f, 0.0f, 0.0f, 0.0f, 0.5934119f));
        partdefinition.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(13, 4).addBox(0.0f, 0.0f, 0.0f, 2, 2, 2), PartPose.offsetAndRotation(-1.5f, -21.0f, -0.5f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        this.Handle1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Head1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Grip.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Pin.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Blade4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Top.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
