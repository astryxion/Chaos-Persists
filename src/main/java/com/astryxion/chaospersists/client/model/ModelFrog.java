package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Frog;
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

public class ModelFrog extends EntityModel<Frog> {
    private static final float LLEG1_X = 3.0f;
    private static final float LLEG1_Y = 24.0f;
    private static final float LLEG2_BASE_X = 5.0f;
    private static final float LLEG2_BASE_Y = 15.0f;
    private static final float RLEG1_X = -3.0f;
    private static final float RLEG1_Y = 24.0f;
    private static final float RLEG2_BASE_X = -5.0f;
    private static final float RLEG2_BASE_Y = 15.0f;
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart jaw;
    private final ModelPart lfleg;
    private final ModelPart rfleg;
    private final ModelPart lleg1;
    private final ModelPart rleg1;
    private final ModelPart lleg2;
    private final ModelPart rleg2;
    private final ModelPart leye;
    private final ModelPart reye;

    public ModelFrog(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelFrog(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.jaw = root.getChild("jaw");
        this.lfleg = root.getChild("lfleg");
        this.rfleg = root.getChild("rfleg");
        this.lleg1 = root.getChild("lleg1");
        this.rleg1 = root.getChild("rleg1");
        this.lleg2 = root.getChild("lleg2");
        this.rleg2 = root.getChild("rleg2");
        this.leye = root.getChild("leye");
        this.reye = root.getChild("reye");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(41, 0).addBox(-4.0f, -10.0f, 0.0f, 8.0f, 11.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, 24.0f, 2.0f, 0.7330383f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "jaw",
                CubeListBuilder.create().texOffs(42, 15).addBox(-4.0f, -8.0f, 0.0f, 8.0f, 8.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, 24.0f, 2.0f, 1.22173f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "lfleg",
                CubeListBuilder.create().texOffs(14, 0).addBox(0.0f, 0.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(3.0f, 20.0f, 0.0f, -0.5235988f, 0.0f, -0.4712389f));
        root.addOrReplaceChild(
                "rfleg",
                CubeListBuilder.create().texOffs(20, 0).addBox(-1.0f, 0.0f, 0.0f, 1.0f, 5.0f, 1.0f),
                PartPose.offsetAndRotation(-3.0f, 20.0f, 0.0f, -0.5235988f, 0.0f, 0.4712389f));
        root.addOrReplaceChild(
                "lleg1",
                CubeListBuilder.create().texOffs(10, 8).addBox(0.0f, -9.0f, -1.0f, 1.0f, 9.0f, 2.0f),
                PartPose.offsetAndRotation(3.0f, 24.0f, 3.0f, 0.0f, 0.0f, 0.2268928f));
        root.addOrReplaceChild(
                "rleg1",
                CubeListBuilder.create().texOffs(18, 8).addBox(-1.0f, -9.0f, -1.0f, 1.0f, 9.0f, 2.0f),
                PartPose.offsetAndRotation(-3.0f, 24.0f, 3.0f, 0.0f, 0.0f, -0.2268928f));
        root.addOrReplaceChild(
                "lleg2",
                CubeListBuilder.create().texOffs(11, 20).addBox(0.0f, 0.0f, 0.0f, 1.0f, 10.0f, 1.0f),
                PartPose.offsetAndRotation(5.0f, 15.0f, 3.0f, 0.0f, 0.0f, -0.3839724f));
        root.addOrReplaceChild(
                "rleg2",
                CubeListBuilder.create().texOffs(19, 20).addBox(-1.0f, 0.0f, 0.0f, 1.0f, 10.0f, 1.0f),
                PartPose.offsetAndRotation(-5.0f, 15.0f, 3.0f, 0.0f, 0.0f, 0.3839724f));
        root.addOrReplaceChild(
                "leye",
                CubeListBuilder.create().texOffs(0, 8).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f),
                PartPose.offsetAndRotation(2.0f, 17.0f, -2.0f, 0.7330383f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "reye",
                CubeListBuilder.create().texOffs(0, 4).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f),
                PartPose.offsetAndRotation(-3.0f, 17.0f, -2.0f, 0.7330383f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Frog entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle =
                (double) limbSwingAmount > 0.1
                        ? Mth.cos(ageInTicks * this.wingspeed * 1.4f) * ((float) Math.PI * 0.55f) * limbSwingAmount
                        : 0.0f;
        this.lfleg.yRot = newangle;
        this.rfleg.yRot = -newangle;
        this.lleg2.yRot = -newangle / 2.0f;
        this.rleg2.yRot = newangle / 2.0f;
        newangle =
                entity.getSinging() != 0
                        ? Mth.cos(ageInTicks * 0.85f * this.wingspeed) * ((float) Math.PI * 0.15f)
                        : 0.0f;
        this.jaw.xRot = newangle + 1.22f;
        boolean leaping = entity.getJumping() > 0;
        if (leaping) {
            this.lleg1.zRot = 2.44f;
            this.rleg1.zRot = -2.44f;
        } else {
            this.lleg1.zRot = 0.227f;
            this.rleg1.zRot = -0.227f;
        }
        this.lleg2.y = LLEG1_Y - (float) Math.cos(this.lleg1.zRot) * 9.0f;
        this.lleg2.x = LLEG1_X + (float) Math.sin(this.lleg1.zRot) * 9.0f;
        this.rleg2.y = RLEG1_Y - (float) Math.cos(this.rleg1.zRot) * 9.0f;
        this.rleg2.x = RLEG1_X + (float) Math.sin(this.rleg1.zRot) * 9.0f;
        if (!leaping) {
            this.lleg2.x = LLEG2_BASE_X;
            this.lleg2.y = LLEG2_BASE_Y;
            this.rleg2.x = RLEG2_BASE_X;
            this.rleg2.y = RLEG2_BASE_Y;
        }
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
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.reye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
