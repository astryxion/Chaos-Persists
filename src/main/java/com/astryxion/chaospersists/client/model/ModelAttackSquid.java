package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.AttackSquid;
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

public class ModelAttackSquid extends EntityModel<AttackSquid> {
    private final float wingspeed;
    private final ModelPart tent1;
    private final ModelPart tent2;
    private final ModelPart tent3;
    private final ModelPart tent4;
    private final ModelPart tent5;
    private final ModelPart tent6;
    private final ModelPart tent7;
    private final ModelPart body;
    private final ModelPart tent8;

    public ModelAttackSquid(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelAttackSquid(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.tent1 = root.getChild("tent1");
        this.tent2 = root.getChild("tent2");
        this.tent3 = root.getChild("tent3");
        this.tent4 = root.getChild("tent4");
        this.tent5 = root.getChild("tent5");
        this.tent6 = root.getChild("tent6");
        this.tent7 = root.getChild("tent7");
        this.body = root.getChild("body");
        this.tent8 = root.getChild("tent8");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("tent1", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2, 9, 2), PartPose.offsetAndRotation(5.0f, 15.0f, -1.0f, -0.9250245f, -1.745329f, 0.0f));
        root.addOrReplaceChild("tent2", CubeListBuilder.create().texOffs(0, 18).addBox(-8.0f, -1.0f, -1.0f, 8, 2, 2), PartPose.offsetAndRotation(-2.0f, 15.0f, -3.0f, -0.1745329f, -0.6632251f, -0.2443461f));
        root.addOrReplaceChild("tent3", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2), PartPose.offsetAndRotation(1.0f, 15.0f, -4.0f, -1.134464f, 0.3316126f, 0.0f));
        root.addOrReplaceChild("tent4", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2), PartPose.offsetAndRotation(-3.0f, 15.0f, -1.0f, 0.5585054f, -1.692969f, 0.0f));
        root.addOrReplaceChild("tent5", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2), PartPose.offsetAndRotation(1.0f, 15.0f, 3.0f, 0.5410521f, 0.2268928f, 0.0f));
        root.addOrReplaceChild("tent6", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8), PartPose.offsetAndRotation(-2.0f, 15.0f, 2.0f, -0.418879f, -0.6806784f, 0.0f));
        root.addOrReplaceChild("tent7", CubeListBuilder.create().texOffs(0, 18).addBox(0.0f, -1.0f, -1.0f, 8, 2, 2), PartPose.offsetAndRotation(3.0f, 15.0f, 1.0f, -0.1919862f, -0.6632251f, 0.418879f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8), PartPose.offsetAndRotation(1.0f, 16.0f, -1.0f, -0.1919862f, -0.6806784f, 0.0f));
        root.addOrReplaceChild("tent8", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0f, -1.0f, -8.0f, 2, 2, 8), PartPose.offsetAndRotation(3.0f, 15.0f, -4.0f, 0.1919862f, -0.6806784f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(AttackSquid e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangleA = 0.0f;
        float newangleB = 0.0f;
        float newangle8 = 0.0f;
        float newangle1 = 0.0f;
        float newangle2 = 0.0f;
        float newangle3 = 0.0f;
        float newangle4 = 0.0f;
        float newangle5 = 0.0f;
        float newangle6 = 0.0f;
        float newangle7 = 0.0f;
        if (limbSwingAmount > 0.1f) {
            newangleA = Mth.cos(ageInTicks * 0.25f * this.wingspeed) * (float) Math.PI * 0.04f * limbSwingAmount;
            newangleB = Mth.cos(ageInTicks * 0.39f * this.wingspeed) * (float) Math.PI * 0.04f * limbSwingAmount;
            newangle1 = Mth.cos(ageInTicks * 1.2f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
            newangle2 = Mth.cos(ageInTicks * 1.1f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
            newangle3 = Mth.cos(ageInTicks * 1.0f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
            newangle4 = Mth.cos(ageInTicks * 1.9f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
            newangle5 = Mth.cos(ageInTicks * 1.8f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
            newangle6 = Mth.cos(ageInTicks * 1.7f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
            newangle7 = Mth.cos(ageInTicks * 1.6f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
            newangle8 = Mth.cos(ageInTicks * 1.5f * this.wingspeed) * (float) Math.PI * 0.4f * limbSwingAmount;
        } else {
            newangleA = Mth.cos(ageInTicks * 0.25f * this.wingspeed) * (float) Math.PI * 0.01f;
            newangleB = Mth.cos(ageInTicks * 0.39f * this.wingspeed) * (float) Math.PI * 0.01f;
            newangle1 = Mth.cos(ageInTicks * 1.2f * this.wingspeed) * (float) Math.PI * 0.1f;
            newangle2 = Mth.cos(ageInTicks * 1.1f * this.wingspeed) * (float) Math.PI * 0.1f;
            newangle3 = Mth.cos(ageInTicks * 1.0f * this.wingspeed) * (float) Math.PI * 0.1f;
            newangle4 = Mth.cos(ageInTicks * 1.9f * this.wingspeed) * (float) Math.PI * 0.1f;
            newangle5 = Mth.cos(ageInTicks * 1.8f * this.wingspeed) * (float) Math.PI * 0.1f;
            newangle6 = Mth.cos(ageInTicks * 1.7f * this.wingspeed) * (float) Math.PI * 0.1f;
            newangle7 = Mth.cos(ageInTicks * 1.6f * this.wingspeed) * (float) Math.PI * 0.1f;
            newangle8 = Mth.cos(ageInTicks * 1.5f * this.wingspeed) * (float) Math.PI * 0.1f;
        }
        this.tent1.xRot = newangle1 - 1.03f;
        this.tent7.zRot = newangle2 + 0.37f;
        this.tent5.xRot = newangle3 + 0.6f;
        this.tent6.xRot = newangle4 - 0.48f;
        this.tent4.xRot = newangle5 + 0.63f;
        this.tent2.zRot = newangle6 - 0.26f;
        this.tent3.xRot = newangle7 - 1.03f;
        this.tent8.xRot = newangle8 + 0.43f;
        this.body.xRot = newangleA;
        this.body.zRot = newangleB;
        this.body.yRot = (float) Math.toRadians(netHeadYaw) * 0.75f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.tent1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tent8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
