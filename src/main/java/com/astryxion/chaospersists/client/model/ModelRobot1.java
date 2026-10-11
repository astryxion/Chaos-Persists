package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Robot1;
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

public class ModelRobot1 extends EntityModel<Robot1> {
    private final float wingspeed;
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape2a;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;
    private final ModelPart Shape9;
    private final ModelPart Shape10;
    private final ModelPart Shape11;
    private final ModelPart Shape12;
    private final ModelPart Shape13;
    private final ModelPart Shape14;
    private final ModelPart Shape15;
    private final ModelPart Shape15a;
    private final ModelPart Shape16;
    private final ModelPart Shape17;
    private final ModelPart Shape18;
    private final ModelPart rfoot;
    private final ModelPart lfoot;
    private final ModelPart key2;
    private final ModelPart key1;
    private final ModelPart key3;
    private final ModelPart key4;
    private final ModelPart key5;
    private float animLimbSwingAmount;
    private float animAgeInTicks;
    private float animNetHeadYaw;

    public ModelRobot1(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot(), wingspeed);
    }

    public ModelRobot1(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape2a = root.getChild("Shape2a");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
        this.Shape9 = root.getChild("Shape9");
        this.Shape10 = root.getChild("Shape10");
        this.Shape11 = root.getChild("Shape11");
        this.Shape12 = root.getChild("Shape12");
        this.Shape13 = root.getChild("Shape13");
        this.Shape14 = root.getChild("Shape14");
        this.Shape15 = root.getChild("Shape15");
        this.Shape15a = root.getChild("Shape15a");
        this.Shape16 = root.getChild("Shape16");
        this.Shape17 = root.getChild("Shape17");
        this.Shape18 = root.getChild("Shape18");
        this.rfoot = root.getChild("rfoot");
        this.lfoot = root.getChild("lfoot");
        this.key2 = root.getChild("key2");
        this.key1 = root.getChild("key1");
        this.key3 = root.getChild("key3");
        this.key4 = root.getChild("key4");
        this.key5 = root.getChild("key5");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 3, 9, 3), PartPose.offsetAndRotation(-1.0f, 13.0f, -1.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1, 9, 5), PartPose.offsetAndRotation(0.0f, 13.0f, -2.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape2a", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 5, 9, 1), PartPose.offsetAndRotation(-2.0f, 13.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 7, 7, 3), PartPose.offsetAndRotation(-3.0f, 14.0f, -1.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 3, 7, 7), PartPose.offsetAndRotation(-1.0f, 14.0f, -3.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 5, 7, 5), PartPose.offsetAndRotation(-2.0f, 14.0f, -2.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 5, 5, 7), PartPose.offsetAndRotation(-2.0f, 15.0f, -3.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, 15.0f, 4.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 7, 5, 5), PartPose.offsetAndRotation(-3.0f, 15.0f, -2.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 9, 5, 1), PartPose.offsetAndRotation(-4.0f, 15.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 1.0f, 3, 3, 8), PartPose.offsetAndRotation(-1.0f, 16.0f, -4.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 9, 3, 3), PartPose.offsetAndRotation(-4.0f, 16.0f, -1.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape12", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 7, 3, 7), PartPose.offsetAndRotation(-3.0f, 16.0f, -3.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape13", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 9, 1, 5), PartPose.offsetAndRotation(-4.0f, 17.0f, -2.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape14", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 5, 1, 1), PartPose.offsetAndRotation(-2.0f, 17.0f, 4.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape15", CubeListBuilder.create().texOffs(32, 0).addBox(0.0f, 0.0f, 0.0f, 2, 3, 1), PartPose.offsetAndRotation(-2.0f, 15.0f, -4.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape15a", CubeListBuilder.create().texOffs(32, 0).addBox(0.0f, 0.0f, 0.0f, 2, 3, 1), PartPose.offsetAndRotation(1.0f, 15.0f, -4.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape16", CubeListBuilder.create().texOffs(45, 0).addBox(0.0f, 0.0f, 0.0f, 3, 1, 3), PartPose.offsetAndRotation(-1.0f, 12.0f, -1.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape17", CubeListBuilder.create().texOffs(33, 7).addBox(0.0f, 0.0f, 0.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape18", CubeListBuilder.create().texOffs(33, 7).addBox(0.0f, 0.0f, 0.0f, 1, 2, 1), PartPose.offsetAndRotation(1.7f, 8.733334f, 0.0f, 0.0f, 0.0f, 0.9667472f));
        root.addOrReplaceChild("rfoot", CubeListBuilder.create().texOffs(46, 8).addBox(0.0f, 3.0f, -2.0f, 2, 2, 4), PartPose.offsetAndRotation(-3.0f, 19.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("lfoot", CubeListBuilder.create().texOffs(46, 8).addBox(0.0f, 3.0f, -2.0f, 2, 2, 4), PartPose.offsetAndRotation(2.0f, 19.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("key2", CubeListBuilder.create().texOffs(46, 8).addBox(-0.5f, -1.5f, 1.0f, 1, 3, 1), PartPose.offsetAndRotation(0.5f, 17.5f, 5.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("key1", CubeListBuilder.create().texOffs(46, 8).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 3), PartPose.offsetAndRotation(0.5f, 17.5f, 5.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("key3", CubeListBuilder.create().texOffs(46, 8).addBox(-0.5f, -2.5f, 1.0f, 1, 1, 2), PartPose.offsetAndRotation(0.5f, 17.5f, 5.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("key4", CubeListBuilder.create().texOffs(46, 8).addBox(-0.5f, 1.5f, 1.0f, 1, 1, 2), PartPose.offsetAndRotation(0.5f, 17.5f, 5.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("key5", CubeListBuilder.create().texOffs(46, 8).addBox(-0.5f, -1.5f, 3.0f, 1, 3, 1), PartPose.offsetAndRotation(0.5f, 17.5f, 5.0f, 0.0f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Robot1 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animLimbSwingAmount = limbSwingAmount;
        this.animAgeInTicks = ageInTicks;
        this.animNetHeadYaw = netHeadYaw;
        float newangle = (double) this.animLimbSwingAmount > 0.1
                ? Mth.cos(this.animAgeInTicks * 1.5f * this.wingspeed) * 3.1415927f * 0.75f * this.animLimbSwingAmount
                : 0.0f;
        this.lfoot.xRot = newangle;
        this.rfoot.xRot = -newangle;
        newangle = (float) Math.toRadians(this.animAgeInTicks * 0.75f * this.wingspeed);
        this.key1.zRot = newangle;
        this.key2.zRot = newangle;
        this.key3.zRot = newangle;
        this.key4.zRot = newangle;
        this.key5.zRot = newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2a.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape13.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape14.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15a.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape18.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.key5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
