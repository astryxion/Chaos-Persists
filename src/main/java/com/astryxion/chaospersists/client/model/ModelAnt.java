package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.EntityAnt;
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

public class ModelAnt extends EntityModel<EntityAnt> {
    private final ModelPart thorax;
    private final ModelPart thorax1;
    private final ModelPart thorax3;
    private final ModelPart abdomen;
    private final ModelPart abdomen1;
    private final ModelPart head;
    private final ModelPart jawsr;
    private final ModelPart jawsl;
    private final ModelPart llegtop1;
    private final ModelPart llegbot1;
    private final ModelPart llegtop2;
    private final ModelPart llegbot2;
    private final ModelPart llegtop3;
    private final ModelPart llegbot3;
    private final ModelPart rlegtop1;
    private final ModelPart rlegbot1;
    private final ModelPart rlegtop2;
    private final ModelPart rlegbot2;
    private final ModelPart rlegtop3;
    private final ModelPart rlegbot3;

    public ModelAnt() {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelAnt(ModelPart root) {
        this.thorax = root.getChild("thorax");
        this.thorax1 = root.getChild("thorax1");
        this.thorax3 = root.getChild("thorax3");
        this.abdomen = root.getChild("abdomen");
        this.abdomen1 = root.getChild("abdomen1");
        this.head = root.getChild("head");
        this.jawsr = root.getChild("jawsr");
        this.jawsl = root.getChild("jawsl");
        this.llegtop1 = root.getChild("llegtop1");
        this.llegbot1 = root.getChild("llegbot1");
        this.llegtop2 = root.getChild("llegtop2");
        this.llegbot2 = root.getChild("llegbot2");
        this.llegtop3 = root.getChild("llegtop3");
        this.llegbot3 = root.getChild("llegbot3");
        this.rlegtop1 = root.getChild("rlegtop1");
        this.rlegbot1 = root.getChild("rlegbot1");
        this.rlegtop2 = root.getChild("rlegtop2");
        this.rlegbot2 = root.getChild("rlegbot2");
        this.rlegtop3 = root.getChild("rlegtop3");
        this.rlegbot3 = root.getChild("rlegbot3");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(22, 0).addBox(0.0f, 0.0f, 0.0f, 3, 3, 3), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("thorax1", CubeListBuilder.create().texOffs(18, 0).addBox(1.0f, 1.0f, -1.0f, 1, 1, 1), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("thorax3", CubeListBuilder.create().texOffs(34, 0).addBox(1.0f, 1.0f, 3.0f, 1, 1, 1), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(38, 0).addBox(0.0f, 0.0f, 4.0f, 3, 3, 5), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("abdomen1", CubeListBuilder.create().texOffs(54, 0).addBox(1.0f, 1.0f, 9.0f, 1, 1, 1), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(6, 0).addBox(0.0f, -1.0f, -4.0f, 3, 3, 3), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("jawsr", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0f, 0.0f, -6.0f, 1, 1, 3), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("jawsl", CubeListBuilder.create().texOffs(0, 14).addBox(3.0f, 0.0f, -6.0f, 1, 1, 3), PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("llegtop1", CubeListBuilder.create().texOffs(15, 10).addBox(3.0f, 1.0f, 1.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.3839724f));
        root.addOrReplaceChild("llegbot1", CubeListBuilder.create().texOffs(15, 19).addBox(5.0f, -3.0f, 1.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 1.064651f));
        root.addOrReplaceChild("llegtop2", CubeListBuilder.create().texOffs(15, 13).addBox(3.0f, 1.0f, 2.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, -0.2094395f, 0.3839724f));
        root.addOrReplaceChild("llegbot2", CubeListBuilder.create().texOffs(15, 22).addBox(5.0f, -3.0f, 2.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, -0.2268928f, 1.064651f));
        root.addOrReplaceChild("llegtop3", CubeListBuilder.create().texOffs(15, 16).addBox(3.0f, 1.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.3490659f, 0.3839724f));
        root.addOrReplaceChild("llegbot3", CubeListBuilder.create().texOffs(15, 25).addBox(5.0f, -3.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.3490659f, 1.064651f));
        root.addOrReplaceChild("rlegtop1", CubeListBuilder.create().texOffs(25, 10).addBox(-4.0f, 2.0f, 1.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, -0.4712389f));
        root.addOrReplaceChild("rlegbot1", CubeListBuilder.create().texOffs(25, 19).addBox(-7.0f, 0.0f, 1.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, -0.9773844f));
        root.addOrReplaceChild("rlegtop2", CubeListBuilder.create().texOffs(25, 13).addBox(-4.0f, 2.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, -0.5934119f, -0.4712389f));
        root.addOrReplaceChild("rlegbot2", CubeListBuilder.create().texOffs(25, 22).addBox(-7.0f, 0.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, -0.5934119f, -0.9773844f));
        root.addOrReplaceChild("rlegtop3", CubeListBuilder.create().texOffs(25, 16).addBox(-4.0f, 2.0f, 2.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.418879f, -0.4712389f));
        root.addOrReplaceChild("rlegbot3", CubeListBuilder.create().texOffs(25, 25).addBox(-7.0f, 0.0f, 2.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.418879f, -0.9773844f));
        return mesh;
    }

    @Override
    public void setupAnim(
            EntityAnt entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        this.llegbot1.xRot = this.llegtop1.xRot = Mth.cos(ageInTicks * 2.7f) * (float) Math.PI * 0.45f * limbSwingAmount;
        this.rlegtop2.xRot = this.llegtop1.xRot;
        this.rlegbot2.xRot = this.llegtop1.xRot;
        this.rlegtop3.xRot = this.llegtop1.xRot;
        this.rlegbot3.xRot = this.llegtop1.xRot;
        this.rlegtop1.xRot = -this.llegtop1.xRot;
        this.rlegbot1.xRot = -this.llegtop1.xRot;
        this.llegtop2.xRot = -this.llegtop1.xRot;
        this.llegbot2.xRot = -this.llegtop1.xRot;
        this.llegtop3.xRot = -this.llegtop1.xRot;
        this.llegbot3.xRot = -this.llegtop1.xRot;
        this.jawsl.yRot = Mth.cos(ageInTicks * 0.4f) * (float) Math.PI * 0.05f;
        this.jawsr.yRot = -this.jawsl.yRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.thorax.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawsr.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jawsl.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegtop1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegbot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegtop2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegbot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegtop3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.llegbot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegtop1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegbot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegtop2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegbot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegtop3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlegbot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
