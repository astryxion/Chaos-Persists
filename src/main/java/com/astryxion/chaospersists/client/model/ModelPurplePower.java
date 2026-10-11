package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.item.PurplePower;
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
public class ModelPurplePower extends EntityModel<PurplePower> {
    @SuppressWarnings("unused")
    private final float wingspeed;
    private final ModelPart shape1;
    private final ModelPart shape2;
    private final ModelPart shape3;
    private PurplePower animEntity;

    public ModelPurplePower(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelPurplePower(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.shape1 = root.getChild("Shape1");
        this.shape2 = root.getChild("Shape2");
        this.shape3 = root.getChild("Shape3");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild(
                "Shape1",
                CubeListBuilder.create().texOffs(0, 12).addBox(-2.0f, -0.5f, -0.5f, 4, 1, 1),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild(
                "Shape2",
                CubeListBuilder.create().texOffs(0, 7).addBox(-4.0f, -0.5f, -0.5f, 8, 1, 1),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild(
                "Shape3",
                CubeListBuilder.create().texOffs(0, 0).addBox(-7.0f, -0.5f, -0.5f, 14, 1, 1),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(
            PurplePower entity,
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
        PurplePower p = this.animEntity;
        float rf1 = 1.0f;
        float newangle = 0.0f;
        float a = 0.75f;
        float b = 0.75f;
        float c = 0.75f;
        float d = 0.55f;

        poseStack.pushPose();
        rf1 = p.level().getRandom().nextFloat() * 360.0f;
        poseStack.mulPose(Axis.XP.rotationDegrees(rf1));
        for (int i = 0; i < 6; ++i) {
            this.shape1.zRot = newangle;
            this.shape1.render(poseStack, buffer, packedLight, packedOverlay, a, b, c, d);
            newangle += 1.0471976f;
        }
        poseStack.mulPose(Axis.XP.rotationDegrees(-rf1));

        newangle = 0.0f;
        rf1 = p.level().getRandom().nextFloat() * 360.0f;
        poseStack.mulPose(Axis.YP.rotationDegrees(rf1));
        for (int i = 0; i < 6; ++i) {
            this.shape2.zRot = newangle;
            this.shape2.render(poseStack, buffer, packedLight, packedOverlay, a, b, c, d);
            newangle += 1.0471976f;
        }
        poseStack.mulPose(Axis.YP.rotationDegrees(-rf1));

        newangle = 0.0f;
        rf1 = p.level().getRandom().nextFloat() * 360.0f;
        poseStack.mulPose(Axis.ZP.rotationDegrees(rf1));
        for (int i = 0; i < 6; ++i) {
            this.shape3.zRot = newangle;
            this.shape3.render(poseStack, buffer, packedLight, packedOverlay, a, b, c, d);
            newangle += 1.0471976f;
        }
        poseStack.mulPose(Axis.ZP.rotationDegrees(-rf1));
        poseStack.popPose();
    }
}
