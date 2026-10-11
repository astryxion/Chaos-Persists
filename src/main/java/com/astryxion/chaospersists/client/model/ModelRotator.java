package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.RenderInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelRotator extends EntityModel<Rotator> {
    private final float wingspeed;
    private final ModelPart shape1;
    private final ModelPart shape2;
    private final ModelPart shape3;
    private Rotator animEntity;

    public ModelRotator(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot(), f1);
    }

    public ModelRotator(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.shape1 = root.getChild("shape1");
        this.shape2 = root.getChild("shape2");
        this.shape3 = root.getChild("shape3");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "shape1",
                CubeListBuilder.create().texOffs(0, 12).addBox(-2.0f, 3.9f, 0.0f, 4, 1, 1),
                PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "shape2",
                CubeListBuilder.create().texOffs(0, 7).addBox(-4.0f, 7.6f, 0.0f, 8, 2, 2),
                PartPose.offset(0.0f, 0.0f, -0.5f));
        root.addOrReplaceChild(
                "shape3",
                CubeListBuilder.create().texOffs(0, 0).addBox(-7.0f, 13.7f, 0.0f, 14, 3, 3),
                PartPose.offset(0.0f, 0.0f, -1.0f));
        return mesh;
    }

    @Override
    public void setupAnim(
            Rotator entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        this.animEntity = entity;
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
        if (this.animEntity == null) {
            return;
        }
        RenderInfo ri = this.animEntity.getRenderInfo();
        float newangle = 0.0f;

        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(ri.rf1));
        for (int i = 0; i < 8; ++i) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.ZP.rotation(newangle));
            this.shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
            newangle += 0.7853982f;
        }
        poseStack.popPose();

        newangle = 0.0f;
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(ri.rf1));
        for (int i = 0; i < 8; ++i) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.ZP.rotation(newangle));
            this.shape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
            newangle += 0.7853982f;
        }
        poseStack.popPose();

        newangle = 0.0f;
        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(ri.rf1));
        for (int i = 0; i < 8; ++i) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.ZP.rotation(newangle));
            this.shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            poseStack.popPose();
            newangle += 0.7853982f;
        }
        poseStack.popPose();

        ri.rf1 += 2.0f;
        if ((double) ri.rf1 > 359.0) {
            ri.rf1 = 0.0f;
        }
        this.animEntity.setRenderInfo(ri);
    }
}
