package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Crab;
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

public class ModelCrab extends EntityModel<Crab> {
    private float limbSwingAmountAnim;
    private float ageInTicksAnim;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart leg1;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart body5;
    private final ModelPart body6;
    private final ModelPart Leye1;
    private final ModelPart Reye1;
    private final ModelPart Leye2;
    private final ModelPart Reye2;
    private final ModelPart Lclaw1;
    private final ModelPart Lclaw2;
    private final ModelPart Lclaw3;
    private final ModelPart Lclaw4;
    private final ModelPart Lclaw5;
    private final ModelPart Rclaw1;
    private final ModelPart Rclaw2;
    private final ModelPart Rclaw3;
    private final ModelPart Rclaw4;
    private final ModelPart Rclaw5;
    private final ModelPart Rmouth;
    private final ModelPart Lmouth;

    public ModelCrab() {
        this(LayerDefinition.create(createMesh(), 256, 512).bakeRoot());
    }

    public ModelCrab(ModelPart root) {
        this.body1 = root.getChild("body1");
        this.body2 = root.getChild("body2");
        this.leg1 = root.getChild("leg1");
        this.body3 = root.getChild("body3");
        this.body4 = root.getChild("body4");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.body5 = root.getChild("body5");
        this.body6 = root.getChild("body6");
        this.Leye1 = root.getChild("Leye1");
        this.Reye1 = root.getChild("Reye1");
        this.Leye2 = root.getChild("Leye2");
        this.Reye2 = root.getChild("Reye2");
        this.Lclaw1 = root.getChild("Lclaw1");
        this.Lclaw2 = root.getChild("Lclaw2");
        this.Lclaw3 = root.getChild("Lclaw3");
        this.Lclaw4 = root.getChild("Lclaw4");
        this.Lclaw5 = root.getChild("Lclaw5");
        this.Rclaw1 = root.getChild("Rclaw1");
        this.Rclaw2 = root.getChild("Rclaw2");
        this.Rclaw3 = root.getChild("Rclaw3");
        this.Rclaw4 = root.getChild("Rclaw4");
        this.Rclaw5 = root.getChild("Rclaw5");
        this.Rmouth = root.getChild("Rmouth");
        this.Lmouth = root.getChild("Lmouth");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 450).addBox(-38.0f, -5.0f, -8.0f, 76, 10, 48), PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 406).addBox(-32.0f, -10.0f, -10.0f, 64, 5, 34), PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(128, 0).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4), PartPose.offsetAndRotation(36.0f, 3.0f, 0.0f, -1.343904f, -1.500983f, 0.0f));
        root.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 357).addBox(0.0f, 0.0f, 0.0f, 8, 4, 40), PartPose.offset(38.0f, -5.0f, -6.0f));
        root.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(100, 357).addBox(0.0f, 0.0f, 0.0f, 8, 4, 40), PartPose.offset(-46.0f, -5.0f, -6.0f));
        root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(128, 20).addBox(-1.0f, 10.0f, -6.0f, 3, 16, 3), PartPose.offsetAndRotation(36.0f, 3.0f, 0.0f, -0.9599311f, -1.500983f, 0.0f));
        root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(128, 43).addBox(0.0f, 21.0f, -15.0f, 2, 16, 2), PartPose.offsetAndRotation(36.0f, 3.0f, 0.0f, -0.5759587f, -1.500983f, 0.0f));
        root.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(0, 339).addBox(-25.0f, 0.0f, 0.0f, 50, 4, 10), PartPose.offset(0.0f, -4.0f, 40.0f));
        root.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(124, 342).addBox(-14.0f, 0.0f, 0.0f, 28, 3, 4), PartPose.offset(0.0f, -10.0f, -14.0f));
        root.addOrReplaceChild("Leye1", CubeListBuilder.create().texOffs(62, 0).addBox(-0.5f, -12.0f, -0.5f, 1, 12, 1), PartPose.offsetAndRotation(9.0f, -9.0f, -11.0f, 0.0f, 0.0f, 0.4886922f));
        root.addOrReplaceChild("Reye1", CubeListBuilder.create().texOffs(40, 0).addBox(-0.5f, -12.0f, -0.5f, 1, 12, 1), PartPose.offsetAndRotation(-9.0f, -9.0f, -11.0f, 0.0f, 0.0f, -0.4886922f));
        root.addOrReplaceChild("Leye2", CubeListBuilder.create().texOffs(50, 0).addBox(-1.0f, -14.0f, -1.0f, 2, 2, 2), PartPose.offsetAndRotation(9.0f, -9.0f, -11.0f, 0.0f, 0.0f, 0.4886922f));
        root.addOrReplaceChild("Reye2", CubeListBuilder.create().texOffs(26, 0).addBox(-1.0f, -14.0f, -1.0f, 2, 2, 2), PartPose.offsetAndRotation(-9.0f, -9.0f, -11.0f, 0.0f, 0.0f, -0.4886922f));
        root.addOrReplaceChild("Lclaw1", CubeListBuilder.create().texOffs(0, 80).addBox(-4.0f, 0.0f, -14.0f, 8, 4, 18), PartPose.offsetAndRotation(31.0f, -2.0f, -8.0f, 0.0f, -0.4886922f, 0.0f));
        root.addOrReplaceChild("Lclaw2", CubeListBuilder.create().texOffs(0, 105).addBox(-7.0f, -3.0f, -12.0f, 17, 6, 16), PartPose.offsetAndRotation(37.0f, 0.0f, -20.0f, 0.0f, -0.1745329f, 0.0f));
        root.addOrReplaceChild("Lclaw3", CubeListBuilder.create().texOffs(0, 131).addBox(0.0f, -5.0f, -25.0f, 17, 10, 30), PartPose.offsetAndRotation(37.0f, 0.0f, -31.0f, 0.0f, -0.4537856f, 0.0f));
        root.addOrReplaceChild("Lclaw4", CubeListBuilder.create().texOffs(0, 175).addBox(2.0f, -3.0f, -32.0f, 11, 5, 12), PartPose.offsetAndRotation(37.0f, 0.0f, -31.0f, 0.0f, -0.3490659f, 0.0f));
        root.addOrReplaceChild("Lclaw5", CubeListBuilder.create().texOffs(0, 197).addBox(-4.0f, -3.0f, -27.0f, 7, 5, 32), PartPose.offsetAndRotation(36.0f, 0.0f, -31.0f, 0.0f, 0.3839724f, 0.0f));
        root.addOrReplaceChild("Rclaw1", CubeListBuilder.create().texOffs(102, 78).addBox(-4.0f, 0.0f, -14.0f, 8, 4, 18), PartPose.offsetAndRotation(-31.0f, -2.0f, -8.0f, 0.0f, 0.4886922f, 0.0f));
        root.addOrReplaceChild("Rclaw2", CubeListBuilder.create().texOffs(103, 106).addBox(-10.0f, -3.0f, -12.0f, 17, 6, 16), PartPose.offsetAndRotation(-37.0f, 0.0f, -20.0f, 0.0f, 0.1745329f, 0.0f));
        root.addOrReplaceChild("Rclaw3", CubeListBuilder.create().texOffs(100, 131).addBox(-17.0f, -5.0f, -25.0f, 17, 10, 30), PartPose.offsetAndRotation(-37.0f, 0.0f, -31.0f, 0.0f, 0.4537856f, 0.0f));
        root.addOrReplaceChild("Rclaw4", CubeListBuilder.create().texOffs(101, 175).addBox(-13.0f, -3.0f, -32.0f, 11, 5, 12), PartPose.offsetAndRotation(-37.0f, 0.0f, -31.0f, 0.0f, 0.3490659f, 0.0f));
        root.addOrReplaceChild("Rclaw5", CubeListBuilder.create().texOffs(100, 197).addBox(-4.0f, -3.0f, -27.0f, 7, 5, 32), PartPose.offsetAndRotation(-36.0f, 0.0f, -31.0f, 0.0f, -0.3839724f, 0.0f));
        root.addOrReplaceChild("Rmouth", CubeListBuilder.create().texOffs(0, 28).addBox(0.0f, 0.0f, -0.5f, 6, 3, 1), PartPose.offsetAndRotation(-7.0f, 0.0f, -7.5f, 0.0f, 0.3665191f, 0.0f));
        root.addOrReplaceChild("Lmouth", CubeListBuilder.create().texOffs(0, 19).addBox(-6.0f, 0.0f, -0.5f, 6, 3, 1), PartPose.offsetAndRotation(7.0f, 0.0f, -7.5f, 0.0f, -0.3665191f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Crab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.limbSwingAmountAnim = limbSwingAmount;
        this.ageInTicksAnim = ageInTicks;
        if (entity.getAttacking() == 0) {
            this.Leye1.xRot = this.Leye2.xRot = Mth.cos(ageInTicks * 0.35f) * 3.1415927f * 0.05f;
            this.Leye1.zRot = this.Leye2.zRot = 0.54f + Mth.cos(ageInTicks * 0.25f) * 3.1415927f * 0.05f;
            this.Reye1.xRot = this.Reye2.xRot = Mth.cos(ageInTicks * 0.3f) * 3.1415927f * 0.05f;
            this.Reye1.zRot = this.Reye2.zRot = -0.54f + Mth.cos(ageInTicks * 0.45f) * 3.1415927f * 0.05f;
            this.Lmouth.yRot = -0.72f + Mth.cos(ageInTicks * 0.25f) * 3.1415927f * 0.05f;
            this.Rmouth.yRot = 0.72f - Mth.cos(ageInTicks * 0.25f) * 3.1415927f * 0.05f;
            float newangle = Mth.cos(ageInTicks * 0.15f) * 3.1415927f * 0.03f;
            this.Lclaw3.yRot = -0.453f + newangle;
            this.Lclaw4.yRot = -0.349f + newangle;
            this.Lclaw5.yRot = 0.384f - newangle;
            newangle = Mth.cos(ageInTicks * 0.13f) * 3.1415927f * 0.02f;
            this.Rclaw3.yRot = 0.453f + newangle;
            this.Rclaw4.yRot = 0.349f + newangle;
            this.Rclaw5.yRot = -0.384f - newangle;
        } else {
            this.Leye1.xRot = this.Leye2.xRot = Mth.cos(ageInTicks * 0.45f) * 3.1415927f * 0.1f;
            this.Leye1.zRot = this.Leye2.zRot = 0.54f + Mth.cos(ageInTicks * 0.35f) * 3.1415927f * 0.1f;
            this.Reye1.xRot = this.Reye2.xRot = Mth.cos(ageInTicks * 0.4f) * 3.1415927f * 0.1f;
            this.Reye1.zRot = this.Reye2.zRot = -0.54f + Mth.cos(ageInTicks * 0.55f) * 3.1415927f * 0.1f;
            this.Lmouth.yRot = -0.72f + Mth.cos(ageInTicks * 0.45f) * 3.1415927f * 0.15f;
            this.Rmouth.yRot = 0.72f - Mth.cos(ageInTicks * 0.45f) * 3.1415927f * 0.15f;
            float newangle = Mth.cos(ageInTicks * 0.35f) * 3.1415927f * 0.13f;
            this.Lclaw3.yRot = -0.453f + newangle;
            this.Lclaw4.yRot = -0.349f + newangle;
            this.Lclaw5.yRot = 0.384f - newangle;
            newangle = Mth.cos(ageInTicks * 0.43f) * 3.1415927f * 0.12f;
            this.Rclaw3.yRot = 0.453f + newangle;
            this.Rclaw4.yRot = 0.349f + newangle;
            this.Rclaw5.yRot = -0.384f - newangle;
        }
    }

    private void renderLegPass(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha,
            float legX,
            float legZ,
            float f1,
            float f2,
            boolean leftSide,
            boolean plusOsc) {
        this.leg3.x = legX;
        this.leg2.x = legX;
        this.leg1.x = legX;
        this.leg3.y = 3.0f;
        this.leg2.y = 3.0f;
        this.leg1.y = 3.0f;
        this.leg3.z = legZ;
        this.leg2.z = legZ;
        this.leg1.z = legZ;
        float osc = Mth.cos(f2 * 1.7f) * 3.1415927f * 0.15f * f1;
        float base = leftSide ? 1.5707963267948966f : -1.5707963267948966f;
        float legYRot = base + (plusOsc ? osc : -osc);
        this.leg2.yRot = legYRot;
        this.leg3.yRot = legYRot;
        this.leg1.yRot = this.leg3.yRot;
        this.leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void renderWalkingLegs(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha,
            float f1,
            float f2) {
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, 36.0f, 0.0f, f1, f2, false, true);
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, 36.0f, 10.0f, f1, f2, false, false);
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, 36.0f, 20.0f, f1, f2, false, true);
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, 36.0f, 30.0f, f1, f2, false, false);
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, -36.0f, 0.0f, f1, f2, true, true);
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, -36.0f, 10.0f, f1, f2, true, false);
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, -36.0f, 20.0f, f1, f2, true, true);
        this.renderLegPass(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, -36.0f, 30.0f, f1, f2, true, false);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float f1 = this.limbSwingAmountAnim;
        float f2 = this.ageInTicksAnim;
        this.renderWalkingLegs(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha, f1, f2);
        this.body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leye1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Reye1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leye2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Reye2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lclaw5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rclaw5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rmouth.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lmouth.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
