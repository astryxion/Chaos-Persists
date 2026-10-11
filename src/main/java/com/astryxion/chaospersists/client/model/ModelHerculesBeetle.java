package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.HerculesBeetle;
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

public class ModelHerculesBeetle extends EntityModel<HerculesBeetle> {
    private final float wingspeed;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart head1;
    private final ModelPart head2;
    private final ModelPart head3;
    private final ModelPart head4;
    private final ModelPart head5;
    private final ModelPart head6;
    private final ModelPart head8;
    private final ModelPart jaw1;
    private final ModelPart jaw2;
    private final ModelPart jaw3;
    private final ModelPart jaw4;
    private final ModelPart head7;
    private final ModelPart lfleg1;
    private final ModelPart lfleg2;
    private final ModelPart lfleg3;
    private final ModelPart lmleg1;
    private final ModelPart lmleg2;
    private final ModelPart lmleg3;
    private final ModelPart lrleg1;
    private final ModelPart lrleg2;
    private final ModelPart lrleg3;
    private final ModelPart jaw5;
    private final ModelPart jaw6;
    private final ModelPart jaw7;
    private final ModelPart jaw8;
    private final ModelPart rfleg1;
    private final ModelPart rfleg2;
    private final ModelPart rfleg3;
    private final ModelPart rmleg1;
    private final ModelPart rmleg2;
    private final ModelPart rmleg3;
    private final ModelPart rrleg1;
    private final ModelPart rrleg2;
    private final ModelPart rrleg3;
    private final ModelPart jaw9;

    public ModelHerculesBeetle(float f1) {
        this(LayerDefinition.create(createMesh(), 256, 256).bakeRoot(), f1);
    }

    public ModelHerculesBeetle(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.body1 = root.getChild("body1");
        this.body2 = root.getChild("body2");
        this.head1 = root.getChild("head1");
        this.head2 = root.getChild("head2");
        this.head3 = root.getChild("head3");
        this.head4 = root.getChild("head4");
        this.head5 = root.getChild("head5");
        this.head6 = root.getChild("head6");
        this.head8 = root.getChild("head8");
        this.jaw1 = root.getChild("jaw1");
        this.jaw2 = root.getChild("jaw2");
        this.jaw3 = root.getChild("jaw3");
        this.jaw4 = root.getChild("jaw4");
        this.head7 = root.getChild("head7");
        this.lfleg1 = root.getChild("lfleg1");
        this.lfleg2 = root.getChild("lfleg2");
        this.lfleg3 = root.getChild("lfleg3");
        this.lmleg1 = root.getChild("lmleg1");
        this.lmleg2 = root.getChild("lmleg2");
        this.lmleg3 = root.getChild("lmleg3");
        this.lrleg1 = root.getChild("lrleg1");
        this.lrleg2 = root.getChild("lrleg2");
        this.lrleg3 = root.getChild("lrleg3");
        this.jaw5 = root.getChild("jaw5");
        this.jaw6 = root.getChild("jaw6");
        this.jaw7 = root.getChild("jaw7");
        this.jaw8 = root.getChild("jaw8");
        this.rfleg1 = root.getChild("rfleg1");
        this.rfleg2 = root.getChild("rfleg2");
        this.rfleg3 = root.getChild("rfleg3");
        this.rmleg1 = root.getChild("rmleg1");
        this.rmleg2 = root.getChild("rmleg2");
        this.rmleg3 = root.getChild("rmleg3");
        this.rrleg1 = root.getChild("rrleg1");
        this.rrleg2 = root.getChild("rrleg2");
        this.rrleg3 = root.getChild("rrleg3");
        this.jaw9 = root.getChild("jaw9");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 30).addBox(-8.0f, 0.0f, 0.0f, 16, 16, 23), PartPose.offset(0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(80, 41).addBox(-6.0f, 0.0f, 0.0f, 12, 12, 4), PartPose.offset(0.0f, 3.0f, 23.0f));
        root.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 71).addBox(-9.0f, 0.0f, 0.0f, 18, 16, 12), PartPose.offsetAndRotation(0.0f, -1.0f, -10.0f, -0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 100).addBox(-7.0f, 0.0f, 0.0f, 14, 10, 6), PartPose.offsetAndRotation(0.0f, -2.0f, -16.0f, -0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(0, 117).addBox(-5.0f, 0.0f, 0.0f, 10, 6, 9), PartPose.offsetAndRotation(0.0f, -3.0f, -25.0f, -0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("head4", CubeListBuilder.create().texOffs(0, 133).addBox(-4.0f, 0.0f, 0.0f, 8, 4, 12), PartPose.offsetAndRotation(0.0f, -4.0f, -37.0f, -0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("head5", CubeListBuilder.create().texOffs(0, 150).addBox(-3.0f, 0.0f, 0.0f, 6, 3, 21), PartPose.offset(0.0f, -4.0f, -58.0f));
        root.addOrReplaceChild("head6", CubeListBuilder.create().texOffs(0, 175).addBox(-2.0f, 0.0f, 0.0f, 4, 2, 14), PartPose.offsetAndRotation(0.0f, -2.0f, -72.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("head8", CubeListBuilder.create().texOffs(6, 193).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, -2.0f, -46.0f, -0.2094395f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw1", CubeListBuilder.create().texOffs(114, 0).addBox(-3.0f, -3.0f, -4.0f, 6, 7, 5), PartPose.offsetAndRotation(0.0f, 12.0f, -12.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(115, 14).addBox(-2.5f, -3.0f, -27.0f, 5, 5, 23), PartPose.offsetAndRotation(0.0f, 12.0f, -12.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3", CubeListBuilder.create().texOffs(115, 43).addBox(-1.5f, 0.0f, -44.0f, 3, 5, 18), PartPose.offset(0.0f, 12.0f, -12.0f));
        root.addOrReplaceChild("jaw4", CubeListBuilder.create().texOffs(115, 70).addBox(-0.5f, -2.0f, -45.0f, 1, 5, 1), PartPose.offset(0.0f, 12.0f, -12.0f));
        root.addOrReplaceChild("head7", CubeListBuilder.create().texOffs(0, 193).addBox(-0.5f, 0.0f, 0.0f, 1, 4, 1), PartPose.offsetAndRotation(0.0f, -2.0f, -73.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("lfleg1", CubeListBuilder.create().texOffs(60, 0).addBox(0.0f, 0.0f, -0.5f, 10, 3, 3), PartPose.offsetAndRotation(6.0f, 15.0f, -5.0f, 0.0f, 0.3490659f, 0.0872665f));
        root.addOrReplaceChild("lfleg2", CubeListBuilder.create().texOffs(60, 8).addBox(10.0f, -1.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(6.0f, 15.0f, -5.0f, 0.0f, 0.3490659f, 0.2617994f));
        root.addOrReplaceChild("lfleg3", CubeListBuilder.create().texOffs(60, 14).addBox(21.0f, -2.0f, 0.5f, 10, 1, 1), PartPose.offsetAndRotation(6.0f, 15.0f, -5.0f, 0.0f, 0.3490659f, 0.3490659f));
        root.addOrReplaceChild("lmleg1", CubeListBuilder.create().texOffs(60, 0).addBox(0.0f, 0.0f, -0.5f, 10, 3, 3), PartPose.offsetAndRotation(6.0f, 15.0f, 0.0f, 0.0f, 0.0f, 0.0872665f));
        root.addOrReplaceChild("lmleg2", CubeListBuilder.create().texOffs(60, 8).addBox(10.0f, -1.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(6.0f, 15.0f, 0.0f, 0.0f, 0.0f, 0.2617994f));
        root.addOrReplaceChild("lmleg3", CubeListBuilder.create().texOffs(60, 14).addBox(21.0f, -2.0f, 0.5f, 10, 1, 1), PartPose.offsetAndRotation(6.0f, 15.0f, 0.0f, 0.0f, 0.0f, 0.3490659f));
        root.addOrReplaceChild("lrleg1", CubeListBuilder.create().texOffs(60, 0).addBox(0.0f, 0.0f, -0.5f, 10, 3, 3), PartPose.offsetAndRotation(6.0f, 15.0f, 5.0f, 0.0f, -0.3490659f, 0.0872665f));
        root.addOrReplaceChild("lrleg2", CubeListBuilder.create().texOffs(60, 8).addBox(10.0f, -1.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(6.0f, 15.0f, 5.0f, 0.0f, -0.3490659f, 0.2617994f));
        root.addOrReplaceChild("lrleg3", CubeListBuilder.create().texOffs(60, 14).addBox(21.0f, -2.0f, 0.5f, 10, 1, 1), PartPose.offsetAndRotation(6.0f, 15.0f, 5.0f, 0.0f, -0.3490659f, 0.3490659f));
        root.addOrReplaceChild("jaw5", CubeListBuilder.create().texOffs(115, 78).addBox(2.0f, -2.0f, -9.0f, 2, 3, 3), PartPose.offsetAndRotation(0.0f, 12.0f, -12.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw6", CubeListBuilder.create().texOffs(127, 78).addBox(-4.0f, -2.0f, -9.0f, 2, 3, 3), PartPose.offsetAndRotation(0.0f, 12.0f, -12.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw7", CubeListBuilder.create().texOffs(115, 86).addBox(5.0f, 1.0f, -6.0f, 9, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -12.0f, 0.0f, 0.5585054f, 0.2268928f));
        root.addOrReplaceChild("jaw8", CubeListBuilder.create().texOffs(115, 89).addBox(-14.0f, 1.0f, -6.0f, 9, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -12.0f, 0.0f, -0.5585054f, -0.2268928f));
        root.addOrReplaceChild("rfleg1", CubeListBuilder.create().texOffs(30, 0).addBox(-10.0f, 0.0f, -0.5f, 10, 3, 3), PartPose.offsetAndRotation(-6.0f, 15.0f, -5.0f, 0.0f, -0.3490659f, -0.0872665f));
        root.addOrReplaceChild("rfleg2", CubeListBuilder.create().texOffs(30, 8).addBox(-21.0f, -1.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(-6.0f, 15.0f, -5.0f, 0.0f, -0.3490659f, -0.2617994f));
        root.addOrReplaceChild("rfleg3", CubeListBuilder.create().texOffs(30, 14).addBox(-31.0f, -2.0f, 0.5f, 10, 1, 1), PartPose.offsetAndRotation(-6.0f, 15.0f, -5.0f, 0.0f, -0.3490659f, -0.3490659f));
        root.addOrReplaceChild("rmleg1", CubeListBuilder.create().texOffs(30, 0).addBox(-10.0f, 0.0f, -0.5f, 10, 3, 3), PartPose.offsetAndRotation(-6.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.0872665f));
        root.addOrReplaceChild("rmleg2", CubeListBuilder.create().texOffs(30, 8).addBox(-21.0f, -1.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(-6.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.2617994f));
        root.addOrReplaceChild("rmleg3", CubeListBuilder.create().texOffs(30, 14).addBox(-31.0f, -2.0f, 0.5f, 10, 1, 1), PartPose.offsetAndRotation(-6.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.3490659f));
        root.addOrReplaceChild("rrleg1", CubeListBuilder.create().texOffs(30, 0).addBox(-10.0f, 0.0f, -0.5f, 10, 3, 3), PartPose.offsetAndRotation(-6.0f, 15.0f, 5.0f, 0.0f, 0.3490659f, -0.0872665f));
        root.addOrReplaceChild("rrleg2", CubeListBuilder.create().texOffs(30, 8).addBox(-21.0f, -1.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(-6.0f, 15.0f, 5.0f, 0.0f, 0.3490659f, -0.2617994f));
        root.addOrReplaceChild("rrleg3", CubeListBuilder.create().texOffs(30, 14).addBox(-31.0f, -2.0f, 0.5f, 10, 1, 1), PartPose.offsetAndRotation(-6.0f, 15.0f, 5.0f, 0.0f, 0.3490659f, -0.3490659f));
        root.addOrReplaceChild("jaw9", CubeListBuilder.create().texOffs(121, 70).addBox(-0.5f, -12.0f, -25.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -12.0f, 0.3141593f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(HerculesBeetle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        HerculesBeetle b = (HerculesBeetle)entity;
        newangle = Mth.cos((float)(ageInTicks * this.wingspeed * 0.45f)) * 3.1415927f * 0.12f * limbSwingAmount;
        this.lfleg3.yRot = this.lfleg2.yRot = (this.lfleg1.yRot = 0.349f + newangle);
        this.lmleg3.yRot = this.lmleg2.yRot = (this.lmleg1.yRot = - newangle);
        this.lrleg3.yRot = this.lrleg2.yRot = (this.lrleg1.yRot = -0.349f + newangle);
        this.rfleg3.yRot = this.rfleg2.yRot = (this.rfleg1.yRot = -0.349f + newangle);
        this.rmleg3.yRot = this.rmleg2.yRot = (this.rmleg1.yRot = - newangle);
        this.rrleg3.yRot = this.rrleg2.yRot = (this.rrleg1.yRot = 0.349f + newangle);
        newangle = b.getAttacking() == 0 ? Mth.cos((float)(ageInTicks * 0.051f * this.wingspeed)) * 3.1415927f * 0.01f : Mth.cos((float)(ageInTicks * 0.51f * this.wingspeed)) * 3.1415927f * 0.07f;
        this.jaw1.xRot = 0.122f + newangle;
        this.jaw2.xRot = 0.122f + newangle;
        this.jaw3.xRot = 0.0f + newangle;
        this.jaw4.xRot = 0.0f + newangle;
        this.jaw5.xRot = 0.122f + newangle;
        this.jaw6.xRot = 0.122f + newangle;
        this.jaw7.xRot = 0.0f + newangle;
        this.jaw8.xRot = 0.0f + newangle;
        this.jaw9.xRot = 0.314f + newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lmleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lmleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lmleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rmleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
