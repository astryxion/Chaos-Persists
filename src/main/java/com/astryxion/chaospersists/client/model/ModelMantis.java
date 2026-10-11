package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Mantis;
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

public class ModelMantis extends EntityModel<Mantis> {
    private final float wingspeed;
    private final ModelPart lfleg1;
    private final ModelPart lfleg2;
    private final ModelPart lfleg3;
    private final ModelPart lfleg4;
    private final ModelPart lrleg1;
    private final ModelPart lrleg2;
    private final ModelPart lrleg3;
    private final ModelPart lrleg4;
    private final ModelPart abdomen;
    private final ModelPart thorax;
    private final ModelPart neck1;
    private final ModelPart neck2;
    private final ModelPart head1;
    private final ModelPart head2;
    private final ModelPart leye;
    private final ModelPart reye;
    private final ModelPart lantenna;
    private final ModelPart rantenna;
    private final ModelPart larm1;
    private final ModelPart larm2;
    private final ModelPart larm3;
    private final ModelPart lfwing;
    private final ModelPart rfwing;
    private final ModelPart lrwing;
    private final ModelPart rrwing;
    private final ModelPart rarm1;
    private final ModelPart rarm2;
    private final ModelPart rarm3;
    private final ModelPart rlfleg3;
    private final ModelPart rfleg4;
    private final ModelPart rfleg2;
    private final ModelPart rfleg1;
    private final ModelPart rrleg3;
    private final ModelPart rrleg4;
    private final ModelPart rrleg2;
    private final ModelPart rrleg1;

    public ModelMantis(float f1) {
        this(LayerDefinition.create(createMesh(), 256, 256).bakeRoot(), f1);
    }

    public ModelMantis(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.lfleg1 = root.getChild("lfleg1");
        this.lfleg2 = root.getChild("lfleg2");
        this.lfleg3 = root.getChild("lfleg3");
        this.lfleg4 = root.getChild("lfleg4");
        this.lrleg1 = root.getChild("lrleg1");
        this.lrleg2 = root.getChild("lrleg2");
        this.lrleg3 = root.getChild("lrleg3");
        this.lrleg4 = root.getChild("lrleg4");
        this.abdomen = root.getChild("abdomen");
        this.thorax = root.getChild("thorax");
        this.neck1 = root.getChild("neck1");
        this.neck2 = root.getChild("neck2");
        this.head1 = root.getChild("head1");
        this.head2 = root.getChild("head2");
        this.leye = root.getChild("leye");
        this.reye = root.getChild("reye");
        this.lantenna = root.getChild("lantenna");
        this.rantenna = root.getChild("rantenna");
        this.larm1 = root.getChild("larm1");
        this.larm2 = root.getChild("larm2");
        this.larm3 = root.getChild("larm3");
        this.lfwing = root.getChild("lfwing");
        this.rfwing = root.getChild("rfwing");
        this.lrwing = root.getChild("lrwing");
        this.rrwing = root.getChild("rrwing");
        this.rarm1 = root.getChild("rarm1");
        this.rarm2 = root.getChild("rarm2");
        this.rarm3 = root.getChild("rarm3");
        this.rlfleg3 = root.getChild("rlfleg3");
        this.rfleg4 = root.getChild("rfleg4");
        this.rfleg2 = root.getChild("rfleg2");
        this.rfleg1 = root.getChild("rfleg1");
        this.rrleg3 = root.getChild("rrleg3");
        this.rrleg4 = root.getChild("rrleg4");
        this.rrleg2 = root.getChild("rrleg2");
        this.rrleg1 = root.getChild("rrleg1");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("lfleg1", CubeListBuilder.create().texOffs(28, 35).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1), PartPose.offsetAndRotation(27.0f, 16.0f, -3.0f, 0.0f, 0.0f, -0.6283185f));
        root.addOrReplaceChild("lfleg2", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 1, 22, 1), PartPose.offsetAndRotation(21.0f, -5.0f, -3.0f, 0.0f, 0.0f, -0.2792527f));
        root.addOrReplaceChild("lfleg3", CubeListBuilder.create().texOffs(64, 2).addBox(0.0f, 0.0f, 0.0f, 20, 1, 1), PartPose.offsetAndRotation(2.0f, -5.0f, 0.0f, 0.0f, 0.1570796f, 0.0f));
        root.addOrReplaceChild("lfleg4", CubeListBuilder.create().texOffs(64, 20).addBox(15.0f, 0.0f, -2.0f, 4, 1, 5), PartPose.offsetAndRotation(2.0f, -5.0f, 0.0f, 0.0f, 0.1570796f, 0.0f));
        root.addOrReplaceChild("lrleg1", CubeListBuilder.create().texOffs(35, 35).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1), PartPose.offsetAndRotation(32.0f, 18.0f, 11.0f, 0.0f, 0.0f, -0.8726646f));
        root.addOrReplaceChild("lrleg2", CubeListBuilder.create().texOffs(14, 32).addBox(0.0f, 0.0f, 0.0f, 1, 22, 1), PartPose.offsetAndRotation(21.0f, 0.0f, 11.0f, 0.0f, 0.0f, -0.5410521f));
        root.addOrReplaceChild("lrleg3", CubeListBuilder.create().texOffs(64, 11).addBox(0.0f, 0.0f, 0.0f, 20, 1, 1), PartPose.offsetAndRotation(2.0f, 0.0f, 8.0f, 0.0f, -0.1570796f, 0.0f));
        root.addOrReplaceChild("lrleg4", CubeListBuilder.create().texOffs(64, 36).addBox(15.0f, 0.0f, -2.0f, 4, 1, 5), PartPose.offsetAndRotation(2.0f, 0.0f, 8.0f, 0.0f, -0.1570796f, 0.0f));
        root.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(118, 0).addBox(0.0f, 0.0f, 0.0f, 9, 5, 53), PartPose.offsetAndRotation(-4.0f, -11.0f, 0.0f, -0.5061455f, 0.0f, 0.0f));
        root.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(145, 62).addBox(0.0f, 0.0f, 0.0f, 15, 3, 13), PartPose.offsetAndRotation(-7.0f, -14.0f, -12.0f, -0.2443461f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(145, 82).addBox(0.0f, 0.0f, 0.0f, 9, 1, 15), PartPose.offsetAndRotation(-4.0f, -15.0f, -27.0f, -0.0698132f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(40, 150).addBox(0.0f, 0.0f, 0.0f, 3, 1, 2), PartPose.offset(-1.0f, -15.0f, -29.0f));
        root.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 150).addBox(0.0f, 0.0f, 0.0f, 2, 6, 1), PartPose.offsetAndRotation(0.0f, -16.0f, -30.0f, 0.0f, 0.0f, 0.1396263f));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(10, 150).addBox(-2.0f, 0.0f, 0.0f, 2, 6, 1), PartPose.offsetAndRotation(0.0f, -16.0f, -30.0f, 0.0f, 0.0f, -0.1745329f));
        root.addOrReplaceChild("leye", CubeListBuilder.create().texOffs(20, 150).addBox(1.0f, 0.0f, -0.5f, 2, 2, 1), PartPose.offsetAndRotation(0.0f, -16.0f, -30.0f, 0.0f, 0.0f, 0.1396263f));
        root.addOrReplaceChild("reye", CubeListBuilder.create().texOffs(30, 150).addBox(-3.0f, 0.0f, -0.5f, 2, 2, 1), PartPose.offsetAndRotation(0.0f, -16.0f, -30.0f, 0.0f, 0.0f, -0.1745329f));
        root.addOrReplaceChild("lantenna", CubeListBuilder.create().texOffs(53, 150).addBox(0.0f, -20.0f, 0.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, -16.0f, -30.0f, 0.0f, 0.0f, 0.2792527f));
        root.addOrReplaceChild("rantenna", CubeListBuilder.create().texOffs(60, 150).addBox(-1.0f, -20.0f, 0.0f, 1, 20, 1), PartPose.offsetAndRotation(0.0f, -16.0f, -30.0f, 0.0f, 0.0f, -0.2792527f));
        root.addOrReplaceChild("larm1", CubeListBuilder.create().texOffs(51, 0).addBox(0.0f, 0.0f, -1.0f, 1, 23, 4), PartPose.offsetAndRotation(2.0f, -14.0f, -23.0f, 0.0349066f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(30, 0).addBox(0.0f, -18.0f, -2.0f, 1, 18, 2), PartPose.offsetAndRotation(2.0f, 8.0f, -22.0f, 0.5585054f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm3", CubeListBuilder.create().texOffs(16, 0).addBox(0.0f, 0.0f, 0.0f, 1, 21, 1), PartPose.offset(2.0f, -7.0f, -33.0f));
        root.addOrReplaceChild("lfwing", CubeListBuilder.create().texOffs(0, 67).addBox(0.0f, 0.0f, 0.0f, 48, 1, 12), PartPose.offsetAndRotation(2.0f, -11.0f, 0.0f, -0.2268928f, 0.0f, -0.6981317f));
        root.addOrReplaceChild("rfwing", CubeListBuilder.create().texOffs(0, 83).addBox(-48.0f, 0.0f, 0.0f, 48, 1, 12), PartPose.offsetAndRotation(-1.0f, -11.0f, 0.0f, -0.2268928f, 0.0f, 0.6981317f));
        root.addOrReplaceChild("lrwing", CubeListBuilder.create().texOffs(0, 100).addBox(0.0f, 0.0f, 0.0f, 42, 1, 17), PartPose.offsetAndRotation(2.0f, -6.0f, 10.0f, -0.2268928f, 0.0f, -0.3490659f));
        root.addOrReplaceChild("rrwing", CubeListBuilder.create().texOffs(0, 122).addBox(-42.0f, 0.0f, 0.0f, 42, 1, 17), PartPose.offsetAndRotation(-1.0f, -6.0f, 10.0f, -0.2268928f, 0.0f, 0.3490659f));
        root.addOrReplaceChild("rarm1", CubeListBuilder.create().texOffs(38, 0).addBox(0.0f, 0.0f, -1.0f, 1, 23, 4), PartPose.offsetAndRotation(-1.0f, -14.0f, -23.0f, 0.0349066f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(22, 0).addBox(0.0f, -18.0f, -2.0f, 1, 18, 2), PartPose.offsetAndRotation(-1.0f, 8.0f, -22.0f, 0.5585054f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm3", CubeListBuilder.create().texOffs(10, 0).addBox(0.0f, 0.0f, 0.0f, 1, 21, 1), PartPose.offset(-1.0f, -7.0f, -33.0f));
        root.addOrReplaceChild("rlfleg3", CubeListBuilder.create().texOffs(64, 6).addBox(-20.0f, 0.0f, 0.0f, 20, 1, 1), PartPose.offsetAndRotation(-1.0f, -5.0f, 0.0f, 0.0f, -0.1570796f, 0.0f));
        root.addOrReplaceChild("rfleg4", CubeListBuilder.create().texOffs(64, 28).addBox(-19.0f, 0.0f, -2.0f, 4, 1, 5), PartPose.offsetAndRotation(-1.0f, -5.0f, 0.0f, 0.0f, -0.1570796f, 0.0f));
        root.addOrReplaceChild("rfleg2", CubeListBuilder.create().texOffs(7, 32).addBox(0.0f, 0.0f, 0.0f, 1, 22, 1), PartPose.offsetAndRotation(-21.0f, -5.0f, -3.0f, 0.0f, 0.0f, 0.2792527f));
        root.addOrReplaceChild("rfleg1", CubeListBuilder.create().texOffs(42, 35).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1), PartPose.offsetAndRotation(-27.0f, 16.0f, -3.0f, 0.0f, 0.0f, 0.6283185f));
        root.addOrReplaceChild("rrleg3", CubeListBuilder.create().texOffs(64, 16).addBox(-20.0f, 0.0f, 0.0f, 20, 1, 1), PartPose.offsetAndRotation(-1.0f, 0.0f, 8.0f, 0.0f, 0.1570796f, 0.0f));
        root.addOrReplaceChild("rrleg4", CubeListBuilder.create().texOffs(64, 44).addBox(-19.0f, 0.0f, -2.0f, 4, 1, 5), PartPose.offsetAndRotation(-1.0f, 0.0f, 8.0f, 0.0f, 0.1570796f, 0.0f));
        root.addOrReplaceChild("rrleg2", CubeListBuilder.create().texOffs(21, 32).addBox(0.0f, 0.0f, 0.0f, 1, 22, 1), PartPose.offsetAndRotation(-21.0f, 0.0f, 11.0f, 0.0f, 0.0f, 0.5410521f));
        root.addOrReplaceChild("rrleg1", CubeListBuilder.create().texOffs(49, 35).addBox(0.0f, 0.0f, 0.0f, 1, 10, 1), PartPose.offsetAndRotation(-32.0f, 18.0f, 11.0f, 0.0f, 0.0f, 0.8726646f));
        return mesh;
    }

    @Override
    public void setupAnim(Mantis entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        float a1;
        newangle = Mth.cos(ageInTicks * 0.9f * this.wingspeed) * (float) Math.PI * 0.25f;
        this.lfwing.zRot = -0.698f - newangle;
        this.rfwing.zRot = 0.698f + newangle;
        newangle = Mth.cos(ageInTicks * 0.9f * this.wingspeed) * (float) Math.PI * 0.35f;
        this.lrwing.zRot = -0.349f + newangle;
        this.rrwing.zRot = 0.349f - newangle;
        if (entity.getAttacking() == 0) {
            newangle = Mth.cos(ageInTicks * 0.051f * this.wingspeed) * (float) Math.PI * 0.013f;
            a1 = -0.2f;
        } else {
            newangle = Mth.cos(ageInTicks * 0.51f * this.wingspeed) * (float) Math.PI * 0.25f;
            a1 = -0.698f;
        }
        this.larm1.xRot = a1 + newangle;
        this.larm2.z = (float)((double)(this.larm1.z + 1.0f) + Math.sin(this.larm1.xRot) * 22.0);
        this.larm2.y = (float)((double)this.larm1.y + Math.cos(this.larm1.xRot) * 22.0);
        this.larm2.xRot = - a1 - newangle;
        this.larm3.z = (float)((double)(this.larm2.z + 1.0f) - Math.sin(this.larm2.xRot) * 17.0);
        this.larm3.y = (float)((double)this.larm2.y - Math.cos(this.larm2.xRot) * 17.0);
        this.larm3.xRot = a1 + newangle;
        this.rarm1.xRot = a1 - newangle;
        this.rarm2.z = (float)((double)(this.rarm1.z + 1.0f) + Math.sin(this.rarm1.xRot) * 22.0);
        this.rarm2.y = (float)((double)this.rarm1.y + Math.cos(this.rarm1.xRot) * 22.0);
        this.rarm2.xRot = - a1 + newangle;
        this.rarm3.z = (float)((double)(this.rarm2.z + 1.0f) - Math.sin(this.rarm2.xRot) * 17.0);
        this.rarm3.y = (float)((double)this.rarm2.y - Math.cos(this.rarm2.xRot) * 17.0);
        this.rarm3.xRot = a1 - newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lfleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.reye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lantenna.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rantenna.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rlfleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
