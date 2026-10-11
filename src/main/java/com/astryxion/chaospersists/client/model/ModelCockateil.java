package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Cockateil;
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

public class ModelCockateil extends EntityModel<Cockateil> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart beak;
    private final ModelPart lowerBeak;
    private final ModelPart feather2;
    private final ModelPart feather1;
    private final ModelPart feather3;
    private final ModelPart tailfeather1;
    private final ModelPart rwing1;
    private final ModelPart lwing1;
    private final ModelPart leg;
    private final ModelPart otherleg;
    private final ModelPart lwing2;
    private final ModelPart rwing2;
    private final ModelPart tailfeather2;
    private final ModelPart tailfeather3;

    public ModelCockateil(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelCockateil(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.beak = root.getChild("beak");
        this.lowerBeak = root.getChild("lower_beak");
        this.feather2 = root.getChild("feather2");
        this.feather1 = root.getChild("feather1");
        this.feather3 = root.getChild("feather3");
        this.tailfeather1 = root.getChild("tailfeather1");
        this.rwing1 = root.getChild("rwing1");
        this.lwing1 = root.getChild("lwing1");
        this.leg = root.getChild("leg");
        this.otherleg = root.getChild("otherleg");
        this.lwing2 = root.getChild("lwing2");
        this.rwing2 = root.getChild("rwing2");
        this.tailfeather2 = root.getChild("tailfeather2");
        this.tailfeather3 = root.getChild("tailfeather3");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 5.0f, 3.0f, 6.0f),
                PartPose.offset(-1.0f, 18.0f, 0.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(22, 0).addBox(0.0f, 0.0f, 0.0f, 3.0f, 3.0f, 4.0f),
                PartPose.offset(0.0f, 16.0f, -3.0f));
        root.addOrReplaceChild(
                "beak",
                CubeListBuilder.create().texOffs(0, 21).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 3.0f),
                PartPose.offset(1.0f, 17.0f, -6.0f));
        root.addOrReplaceChild(
                "lower_beak",
                CubeListBuilder.create().texOffs(1, 17).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(1.0f, 18.0f, -4.0f));
        root.addOrReplaceChild(
                "feather2",
                CubeListBuilder.create().texOffs(15, 9).addBox(0.0f, -2.5f, -0.75f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(1.0f, 16.0f, 0.0f, -0.6426736f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "feather1",
                CubeListBuilder.create().texOffs(11, 9).addBox(0.0f, -2.5f, -0.5f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(1.0f, 16.0f, -2.0f, -0.2230717f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "feather3",
                CubeListBuilder.create().texOffs(19, 9).addBox(0.0f, -3.0f, 0.5f, 1.0f, 4.0f, 1.0f),
                PartPose.offsetAndRotation(1.0f, 16.0f, 1.0f, -1.276259f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "tailfeather1",
                CubeListBuilder.create().texOffs(46, 15).addBox(0.0f, 0.0f, 0.0f, 3.0f, 2.0f, 3.0f),
                PartPose.offset(0.0f, 18.0f, 6.0f));
        root.addOrReplaceChild(
                "rwing1",
                CubeListBuilder.create().texOffs(23, 9).addBox(0.0f, 0.0f, 0.0f, 1.0f, 4.0f, 4.0f),
                PartPose.offsetAndRotation(-1.0f, 18.0f, 1.0f, 0.0f, 0.0f, 1.595066f));
        root.addOrReplaceChild(
                "lwing1",
                CubeListBuilder.create().texOffs(33, 9).addBox(-1.0f, 0.0f, 0.0f, 1.0f, 4.0f, 4.0f),
                PartPose.offsetAndRotation(4.0f, 18.0f, 1.0f, 0.0f, 0.0f, -1.561488f));
        root.addOrReplaceChild(
                "leg",
                CubeListBuilder.create().texOffs(4, 12).addBox(0.0f, 0.0f, 0.0f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(2.0f, 21.0f, 3.0f, 0.8726646f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "otherleg",
                CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 1.0f, 3.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 21.0f, 3.0f, 0.6108652f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "lwing2",
                CubeListBuilder.create().texOffs(10, 14).addBox(4.0f, 0.0f, 0.0f, 3.0f, 1.0f, 3.0f),
                PartPose.offset(4.0f, 18.0f, 1.0f));
        root.addOrReplaceChild(
                "rwing2",
                CubeListBuilder.create().texOffs(10, 19).addBox(-7.0f, 0.0f, 0.0f, 3.0f, 1.0f, 3.0f),
                PartPose.offset(-1.0f, 18.0f, 1.0f));
        root.addOrReplaceChild(
                "tailfeather2",
                CubeListBuilder.create().texOffs(44, 20).addBox(-0.5f, 0.0f, 3.0f, 4.0f, 1.0f, 4.0f),
                PartPose.offset(0.0f, 18.0f, 6.0f));
        root.addOrReplaceChild(
                "tailfeather3",
                CubeListBuilder.create().texOffs(36, 26).addBox(-1.0f, 0.0f, 7.0f, 5.0f, 1.0f, 4.0f),
                PartPose.offset(0.0f, 18.0f, 6.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Cockateil entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = Mth.cos(ageInTicks * 1.5f * this.wingspeed) * (float) Math.PI * 0.35f;
        this.lwing1.zRot = -1.5f + newangle;
        this.lwing2.zRot = newangle;
        this.rwing1.zRot = 1.5f - newangle;
        this.rwing2.zRot = -newangle;
        newangle = Mth.cos(ageInTicks * 0.3f * this.wingspeed) * (float) Math.PI * 0.1f;
        this.tailfeather1.xRot = newangle;
        this.tailfeather2.xRot = newangle;
        this.tailfeather3.xRot = newangle;
        this.feather1.zRot = Mth.cos(ageInTicks * 1.1f * this.wingspeed) * (float) Math.PI * 0.08f;
        this.feather2.zRot = Mth.cos(ageInTicks * 1.2f * this.wingspeed) * (float) Math.PI * 0.08f;
        this.feather3.zRot = Mth.cos(ageInTicks * 1.3f * this.wingspeed) * (float) Math.PI * 0.08f;
    }

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
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.beak.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lowerBeak.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.feather3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfeather1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.otherleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfeather2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfeather3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
