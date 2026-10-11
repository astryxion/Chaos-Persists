package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelHammy {
    private final ModelPart Handle1;
    private final ModelPart Handle2;
    private final ModelPart Handle3;
    private final ModelPart Head1;
    private final ModelPart Head2;
    private final ModelPart Head3;
    private final ModelPart Head4;
    private final ModelPart Band1;
    private final ModelPart Band2;
    private final ModelPart Band3;
    private final ModelPart Band4;
    private final ModelPart Band5;
    private final ModelPart Band6;
    private final ModelPart Band7;
    private final ModelPart Band8;
    private final ModelPart Point1;
    private final ModelPart Spike1;
    private final ModelPart Spike2;
    private final ModelPart Spike3;
    private final ModelPart Spike4;
    private final ModelPart Band1b;
    private final ModelPart Band2b;
    private final ModelPart Band3b;
    private final ModelPart Band4b;
    private final ModelPart Band5b;
    private final ModelPart Band6b;
    private final ModelPart Band7b;
    private final ModelPart Band8b;
    private final ModelPart Point1b;
    private final ModelPart Spike2b;
    private final ModelPart Spike1b;
    private final ModelPart Spike3b;
    private final ModelPart Spike4b;

    public ModelHammy() {
        this(LayerDefinition.create(createMesh(), 128, 256).bakeRoot());
    }

    public ModelHammy(ModelPart root) {
        this.Handle1 = root.getChild("Handle1");
        this.Handle2 = root.getChild("Handle2");
        this.Handle3 = root.getChild("Handle3");
        this.Head1 = root.getChild("Head1");
        this.Head2 = root.getChild("Head2");
        this.Head3 = root.getChild("Head3");
        this.Head4 = root.getChild("Head4");
        this.Band1 = root.getChild("Band1");
        this.Band2 = root.getChild("Band2");
        this.Band3 = root.getChild("Band3");
        this.Band4 = root.getChild("Band4");
        this.Band5 = root.getChild("Band5");
        this.Band6 = root.getChild("Band6");
        this.Band7 = root.getChild("Band7");
        this.Band8 = root.getChild("Band8");
        this.Point1 = root.getChild("Point1");
        this.Spike1 = root.getChild("Spike1");
        this.Spike2 = root.getChild("Spike2");
        this.Spike3 = root.getChild("Spike3");
        this.Spike4 = root.getChild("Spike4");
        this.Band1b = root.getChild("Band1b");
        this.Band2b = root.getChild("Band2b");
        this.Band3b = root.getChild("Band3b");
        this.Band4b = root.getChild("Band4b");
        this.Band5b = root.getChild("Band5b");
        this.Band6b = root.getChild("Band6b");
        this.Band7b = root.getChild("Band7b");
        this.Band8b = root.getChild("Band8b");
        this.Point1b = root.getChild("Point1b");
        this.Spike2b = root.getChild("Spike2b");
        this.Spike1b = root.getChild("Spike1b");
        this.Spike3b = root.getChild("Spike3b");
        this.Spike4b = root.getChild("Spike4b");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Handle1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, -12.0f, -1.0f, 1, 36, 2), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Handle2", CubeListBuilder.create().texOffs(7, 0).addBox(-0.5f, -12.0f, -1.0f, 1, 36, 2), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 1.047198f, 0.0f));
        partdefinition.addOrReplaceChild("Handle3", CubeListBuilder.create().texOffs(14, 0).addBox(-0.5f, -12.0f, -1.0f, 1, 36, 2), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, -1.047198f, 0.0f));
        partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(0, 230).addBox(-20.0f, -22.0f, -7.0f, 40, 6, 14), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 184).addBox(-20.0f, -26.0f, -3.0f, 40, 14, 6), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Head3", CubeListBuilder.create().texOffs(0, 161).addBox(-20.0f, -16.5f, 6.4f, 40, 6, 14), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Head4", CubeListBuilder.create().texOffs(0, 207).addBox(-20.0f, -16.5f, -20.4f, 40, 6, 14), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band1", CubeListBuilder.create().texOffs(0, 88).addBox(12.0f, -22.5f, -8.0f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band2", CubeListBuilder.create().texOffs(0, 128).addBox(12.0f, -22.5f, 7.0f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band3", CubeListBuilder.create().texOffs(0, 98).addBox(12.0f, -17.0f, 5.4f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band4", CubeListBuilder.create().texOffs(0, 118).addBox(12.0f, -16.9f, -6.4f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band5", CubeListBuilder.create().texOffs(0, 108).addBox(12.0f, -12.0f, -3.5f, 5, 1, 7), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band6", CubeListBuilder.create().texOffs(0, 79).addBox(12.0f, -16.5f, -21.4f, 5, 6, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band7", CubeListBuilder.create().texOffs(0, 138).addBox(12.0f, -17.0f, 20.4f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band8", CubeListBuilder.create().texOffs(0, 148).addBox(12.0f, -27.0f, -3.5f, 5, 1, 7), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Point1", CubeListBuilder.create().texOffs(28, 130).addBox(-2.5f, -29.5f, -0.5f, 5, 5, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7853982f));
        partdefinition.addOrReplaceChild("Spike1", CubeListBuilder.create().texOffs(67, 0).addBox(14.0f, -20.0f, -10.0f, 1, 1, 20), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike2", CubeListBuilder.create().texOffs(49, 0).addBox(14.0f, -29.0f, 0.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike3", CubeListBuilder.create().texOffs(55, 0).addBox(14.0f, -23.5f, 13.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike4", CubeListBuilder.create().texOffs(61, 0).addBox(-15.0f, -23.5f, -14.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band1b", CubeListBuilder.create().texOffs(0, 88).addBox(-17.0f, -22.5f, -8.0f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band2b", CubeListBuilder.create().texOffs(0, 128).addBox(-17.0f, -22.5f, 7.0f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band3b", CubeListBuilder.create().texOffs(0, 98).addBox(-17.0f, -17.0f, 5.4f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band4b", CubeListBuilder.create().texOffs(0, 118).addBox(-17.0f, -16.9f, -6.4f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band5b", CubeListBuilder.create().texOffs(0, 108).addBox(-17.0f, -12.0f, -3.5f, 5, 1, 7), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band6b", CubeListBuilder.create().texOffs(0, 79).addBox(-17.0f, -16.5f, -21.4f, 5, 6, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band7b", CubeListBuilder.create().texOffs(0, 138).addBox(-17.0f, -17.0f, 20.4f, 5, 7, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Band8b", CubeListBuilder.create().texOffs(0, 148).addBox(-17.0f, -27.0f, -3.5f, 5, 1, 7), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Point1b", CubeListBuilder.create().texOffs(28, 130).addBox(-29.5f, -2.5f, -0.5f, 5, 5, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7853982f));
        partdefinition.addOrReplaceChild("Spike2b", CubeListBuilder.create().texOffs(49, 0).addBox(-15.0f, -29.0f, 0.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike1b", CubeListBuilder.create().texOffs(67, 0).addBox(-15.0f, -20.0f, -10.0f, 1, 1, 20), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike3b", CubeListBuilder.create().texOffs(55, 0).addBox(-15.0f, -23.5f, 13.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike4b", CubeListBuilder.create().texOffs(61, 0).addBox(14.0f, -23.5f, -14.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.7853982f, 0.0f, 0.0f));
        return meshdefinition;
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        this.Handle1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Handle2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Handle3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Head1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Head2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Head3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Head4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band5.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band6.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band7.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band8.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Point1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike1.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike2.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike3.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike4.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band1b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band2b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band3b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band4b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band5b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band6b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band7b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Band8b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Point1b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike2b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike1b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike3b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.Spike4b.render(poseStack, buffer, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
