package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Peacock;
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

public class ModelPeacock extends EntityModel<Peacock> {
    private final float wingspeed;
    private final ModelPart lleg;
    private final ModelPart rleg;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head1;
    private final ModelPart head2;
    private final ModelPart hf1;
    private final ModelPart hf2;
    private final ModelPart hf3;
    private final ModelPart tailf1;
    private final ModelPart tailf2;
    private final ModelPart tailf3;
    private final ModelPart tailf4;
    private final ModelPart tailf5;
    private final ModelPart tailf6;
    private final ModelPart tailf7;

    public ModelPeacock(float f1) {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot(), f1);
    }

    public ModelPeacock(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.lleg = root.getChild("lleg");
        this.rleg = root.getChild("rleg");
        this.body = root.getChild("body");
        this.neck = root.getChild("neck");
        this.head1 = root.getChild("head1");
        this.head2 = root.getChild("head2");
        this.hf1 = root.getChild("hf1");
        this.hf2 = root.getChild("hf2");
        this.hf3 = root.getChild("hf3");
        this.tailf1 = root.getChild("tailf1");
        this.tailf2 = root.getChild("tailf2");
        this.tailf3 = root.getChild("tailf3");
        this.tailf4 = root.getChild("tailf4");
        this.tailf5 = root.getChild("tailf5");
        this.tailf6 = root.getChild("tailf6");
        this.tailf7 = root.getChild("tailf7");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 20).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(5, 20).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(-1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(88, 0).addBox(-2.0f, -2.0f, -5.0f, 5, 4, 11), PartPose.offsetAndRotation(0.0f, 15.0f, 1.0f, -0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(70, 0).addBox(-0.5f, -1.0f, -6.0f, 2, 2, 6), PartPose.offsetAndRotation(0.0f, 14.0f, -3.0f, -0.5585054f, 0.0f, 0.0f));
        root.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(56, 0).addBox(-0.5f, -2.0f, -2.0f, 2, 2, 4), PartPose.offset(0.0f, 12.0f, -8.0f));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(48, 0).addBox(0.0f, -1.0f, -4.0f, 1, 1, 2), PartPose.offset(0.0f, 12.0f, -8.0f));
        root.addOrReplaceChild("hf1", CubeListBuilder.create().texOffs(8, 0).addBox(0.5f, -9.0f, -1.5f, 0, 7, 3), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4014257f, 0.0f, 0.0f));
        root.addOrReplaceChild("hf2", CubeListBuilder.create().texOffs(8, 0).addBox(0.5f, -9.0f, -1.5f, 0, 7, 3), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("hf3", CubeListBuilder.create().texOffs(8, 0).addBox(0.5f, -9.0f, -1.5f, 0, 7, 3), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, -0.6981317f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailf1", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30), PartPose.offset(0.5f, 14.0f, 7.0f));
        root.addOrReplaceChild("tailf2", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30), PartPose.offset(0.5f, 14.0f, 7.0f));
        root.addOrReplaceChild("tailf3", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30), PartPose.offset(0.5f, 14.0f, 7.0f));
        root.addOrReplaceChild("tailf4", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30), PartPose.offset(0.5f, 14.0f, 7.0f));
        root.addOrReplaceChild("tailf5", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30), PartPose.offset(0.5f, 14.0f, 7.0f));
        root.addOrReplaceChild("tailf6", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30), PartPose.offset(0.5f, 14.0f, 7.0f));
        root.addOrReplaceChild("tailf7", CubeListBuilder.create().texOffs(0, 50).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 30), PartPose.offset(0.514f, 14.0f, 7.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Peacock entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount : 0.0f;
        this.lleg.xRot = newangle;
        this.rleg.xRot = - newangle;
        if (entity.getBlink() > 0) {
            this.hf1.xRot = 0.401f;
            this.hf2.xRot = -0.174f;
            this.hf3.xRot = -0.698f;
            this.tailf1.xRot = 1.047f;
            this.tailf2.xRot = 1.047f;
            this.tailf3.xRot = 1.047f;
            this.tailf4.xRot = 1.047f;
            this.tailf5.xRot = 1.047f;
            this.tailf6.xRot = 1.047f;
            this.tailf7.xRot = 1.047f;
            this.tailf1.zRot = -0.4f;
            this.tailf2.zRot = -0.8f;
            this.tailf3.zRot = -1.2f;
            this.tailf4.zRot = 0.4f;
            this.tailf5.zRot = 0.8f;
            this.tailf6.zRot = 1.2f;
        } else {
            this.hf1.xRot = -1.06f;
            this.hf2.xRot = -1.06f;
            this.hf3.xRot = -1.06f;
            this.tailf1.xRot = 0.0f;
            this.tailf2.xRot = 0.0f;
            this.tailf3.xRot = 0.0f;
            this.tailf4.xRot = 0.0f;
            this.tailf5.xRot = 0.0f;
            this.tailf6.xRot = 0.0f;
            this.tailf7.xRot = 0.0f;
            this.tailf1.zRot = 0.0f;
            this.tailf2.zRot = 0.0f;
            this.tailf3.zRot = 0.0f;
            this.tailf4.zRot = 0.0f;
            this.tailf5.zRot = 0.0f;
            this.tailf6.zRot = 0.0f;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailf7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
