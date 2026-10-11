package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Cricket;
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

public class ModelCricket extends EntityModel<Cricket> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart abdomen;
    private final ModelPart lfleg;
    private final ModelPart lrleg;
    private final ModelPart rfleg;
    private final ModelPart rrleg;
    private final ModelPart lleg1;
    private final ModelPart rleg1;
    private final ModelPart lleg2;
    private final ModelPart rleg2;

    public ModelCricket(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelCricket(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.abdomen = root.getChild("abdomen");
        this.lfleg = root.getChild("lfleg");
        this.lrleg = root.getChild("lrleg");
        this.rfleg = root.getChild("rfleg");
        this.rrleg = root.getChild("rrleg");
        this.lleg1 = root.getChild("lleg1");
        this.rleg1 = root.getChild("rleg1");
        this.lleg2 = root.getChild("lleg2");
        this.rleg2 = root.getChild("rleg2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 25).addBox(-1.0f, -1.0f, -3.0f, 3.0f, 3.0f, 6.0f),
                PartPose.offset(0.0f, 21.0f, 0.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 17).addBox(-1.0f, -2.0f, -1.0f, 3.0f, 4.0f, 3.0f),
                PartPose.offsetAndRotation(0.0f, 21.0f, -5.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "abdomen",
                CubeListBuilder.create().texOffs(0, 36).addBox(-0.5f, -1.0f, 3.0f, 2.0f, 2.0f, 3.0f),
                PartPose.offset(0.0f, 21.0f, 0.0f));
        root.addOrReplaceChild(
                "lfleg",
                CubeListBuilder.create().texOffs(25, 0).addBox(2.0f, 0.0f, 0.0f, 5.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 21.0f, -2.0f, 0.0f, 0.4712389f, 0.418879f));
        root.addOrReplaceChild(
                "lrleg",
                CubeListBuilder.create().texOffs(23, 4).addBox(1.0f, 0.0f, -2.0f, 6.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 21.0f, 0.0f, 0.0f, -0.296706f, 0.418879f));
        root.addOrReplaceChild(
                "rfleg",
                CubeListBuilder.create().texOffs(25, 8).addBox(-7.0f, 0.0f, 0.0f, 5.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(1.0f, 21.0f, -2.0f, 0.0f, -0.5410521f, -0.4363323f));
        root.addOrReplaceChild(
                "rrleg",
                CubeListBuilder.create().texOffs(25, 12).addBox(-7.0f, -1.0f, 0.0f, 5.0f, 1.0f, 1.0f),
                PartPose.offsetAndRotation(1.0f, 22.0f, -2.0f, 0.0f, 0.3839724f, -0.418879f));
        root.addOrReplaceChild(
                "lleg1",
                CubeListBuilder.create().texOffs(40, 0).addBox(-1.0f, -1.0f, 0.0f, 1.0f, 2.0f, 8.0f),
                PartPose.offsetAndRotation(2.0f, 22.0f, 0.0f, 0.5585054f, 0.4363323f, 0.0f));
        root.addOrReplaceChild(
                "rleg1",
                CubeListBuilder.create().texOffs(40, 11).addBox(0.0f, -1.0f, 0.0f, 1.0f, 2.0f, 8.0f),
                PartPose.offsetAndRotation(-1.0f, 22.0f, 0.0f, 0.5585054f, -0.4363323f, 0.0f));
        root.addOrReplaceChild(
                "lleg2",
                CubeListBuilder.create().texOffs(21, 23).addBox(-0.5f, -6.5f, 4.5f, 1.0f, 1.0f, 8.0f),
                PartPose.offsetAndRotation(2.0f, 22.0f, 0.0f, -0.3665191f, 0.3490659f, 0.0f));
        root.addOrReplaceChild(
                "rleg2",
                CubeListBuilder.create().texOffs(21, 34).addBox(-0.5f, -6.5f, 4.0f, 1.0f, 1.0f, 8.0f),
                PartPose.offsetAndRotation(-1.0f, 22.0f, 0.0f, -0.3665191f, -0.3490659f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Cricket entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = limbSwingAmount > 0.1f ? Mth.cos(ageInTicks * this.wingspeed) * ((float) Math.PI * 0.25f) * limbSwingAmount : 0.0f;
        this.lfleg.yRot = 0.47f + newangle;
        this.rfleg.yRot = -0.54f + newangle;
        this.lrleg.yRot = -0.296f - newangle;
        this.rrleg.yRot = 0.384f - newangle;
        if (entity.getSinging() != 0) {
            newangle = Mth.cos(ageInTicks * 3.0f * this.wingspeed) * ((float) Math.PI * 0.25f);
            this.lleg1.yRot = -0.035f;
            this.lleg2.yRot = -0.105f;
            this.rleg1.yRot = 0.035f;
            this.rleg2.yRot = 0.105f;
        } else {
            newangle = 0.0f;
            this.lleg1.yRot = 0.436f;
            this.lleg2.yRot = 0.349f;
            this.rleg1.yRot = -0.436f;
            this.rleg2.yRot = -0.349f;
        }
        this.lleg1.xRot = newangle + 0.558f;
        this.lleg2.xRot = newangle - 0.366f;
        this.rleg1.xRot = -newangle + 0.558f;
        this.rleg2.xRot = -newangle - 0.366f;
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
        this.abdomen.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
