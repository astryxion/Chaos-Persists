package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.RenderInfo;
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

public class ModelRobot2 extends EntityModel<Robot2> {
    private final float wingspeed;
    private final ModelPart rleg1;
    private final ModelPart rleg2;
    private final ModelPart Shape3;
    private final ModelPart lleg2;
    private final ModelPart lleg1;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;
    private final ModelPart rarm3;
    private final ModelPart rarm2;
    private final ModelPart rarm1;
    private final ModelPart larm3;
    private final ModelPart larm2;
    private final ModelPart larm1;
    private final ModelPart head;
    private float animLimbSwingAmount;
    private float animAgeInTicks;
    private float animNetHeadYaw;

    public ModelRobot2(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 256, 512).bakeRoot(), wingspeed);
    }

    public ModelRobot2(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.rleg1 = root.getChild("rleg1");
        this.rleg2 = root.getChild("rleg2");
        this.Shape3 = root.getChild("Shape3");
        this.lleg2 = root.getChild("lleg2");
        this.lleg1 = root.getChild("lleg1");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
        this.rarm3 = root.getChild("rarm3");
        this.rarm2 = root.getChild("rarm2");
        this.rarm1 = root.getChild("rarm1");
        this.larm3 = root.getChild("larm3");
        this.larm2 = root.getChild("larm2");
        this.larm1 = root.getChild("larm1");
        this.head = root.getChild("head");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(10, 250).addBox(-14.0f, 24.0f, -7.0f, 16, 24, 16), PartPose.offsetAndRotation(-10.0f, -24.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(10, 150).addBox(-12.0f, 0.0f, -6.0f, 12, 24, 12), PartPose.offsetAndRotation(-10.0f, -24.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(10, 50).addBox(-4.0f, 0.0f, -2.0f, 26, 8, 12), PartPose.offsetAndRotation(-9.0f, -32.0f, -3.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(10, 200).addBox(0.0f, 0.0f, -6.0f, 12, 24, 12), PartPose.offsetAndRotation(10.0f, -24.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("lleg1", CubeListBuilder.create().texOffs(10, 300).addBox(-2.0f, 24.0f, -7.0f, 16, 24, 16), PartPose.offsetAndRotation(10.0f, -24.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(10, 100).addBox(-4.0f, -8.0f, -3.0f, 8, 8, 8), PartPose.offsetAndRotation(0.0f, -32.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(10, 350).addBox(0.0f, 0.0f, 0.0f, 26, 8, 12), PartPose.offsetAndRotation(-13.0f, -48.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(16, 400).addBox(0.0f, 0.0f, 0.0f, 44, 18, 14), PartPose.offsetAndRotation(-22.0f, -66.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm3", CubeListBuilder.create().texOffs(100, 100).addBox(-16.0f, -16.0f, -7.0f, 16, 24, 17), PartPose.offsetAndRotation(-22.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(100, 200).addBox(-14.0f, 8.0f, -5.0f, 12, 24, 12), PartPose.offsetAndRotation(-22.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm1", CubeListBuilder.create().texOffs(100, 300).addBox(-14.0f, 32.0f, -5.0f, 12, 24, 12), PartPose.offsetAndRotation(-22.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm3", CubeListBuilder.create().texOffs(100, 50).addBox(0.0f, -16.0f, -7.0f, 16, 24, 17), PartPose.offsetAndRotation(22.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(100, 150).addBox(2.0f, 8.0f, -5.0f, 12, 24, 12), PartPose.offsetAndRotation(21.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm1", CubeListBuilder.create().texOffs(100, 250).addBox(2.0f, 32.0f, -5.0f, 12, 24, 12), PartPose.offsetAndRotation(21.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 10).addBox(-7.0f, -12.0f, -5.0f, 15, 12, 10), PartPose.offsetAndRotation(0.0f, -66.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Robot2 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animLimbSwingAmount = limbSwingAmount;
        this.animAgeInTicks = ageInTicks;
        this.animNetHeadYaw = netHeadYaw;
        float newangle = (double) this.animLimbSwingAmount > 0.1
                ? Mth.cos(this.animAgeInTicks * 0.3f * this.wingspeed) * 3.1415927f * 0.12f * this.animLimbSwingAmount
                : 0.0f;
        this.lleg1.xRot = newangle;
        this.lleg2.xRot = newangle;
        this.rleg1.xRot = -newangle;
        this.rleg2.xRot = -newangle;
        this.head.yRot = (float) Math.toRadians(this.animNetHeadYaw);
        newangle = Mth.sin((float) Math.toRadians(this.animAgeInTicks * 20.0f * this.wingspeed));
        float nextangle = Mth.sin((float) Math.toRadians(this.animAgeInTicks * 20.0f * this.wingspeed + 1.5f));
        RenderInfo r = entity.getRenderInfo();
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (entity.getAttacking() == 0) {
                r.ri1 = 0;
            } else {
                while (r.ri1 == 0) {
                    r.ri1 = entity.getRandom().nextInt(4);
                }
            }
        }
        newangle = (float) Math.toRadians(this.animAgeInTicks * 20.0f * this.wingspeed);
        if (r.ri1 == 1 || r.ri1 == 3) {
            this.rarm1.xRot = newangle;
            this.rarm2.xRot = newangle;
            this.rarm3.xRot = newangle;
        } else {
            this.rarm1.xRot = 0.0f;
            this.rarm2.xRot = 0.0f;
            this.rarm3.xRot = 0.0f;
        }
        if (r.ri1 == 2 || r.ri1 == 3) {
            this.larm1.xRot = newangle;
            this.larm2.xRot = newangle;
            this.larm3.xRot = newangle;
        } else {
            this.larm1.xRot = 0.0f;
            this.larm2.xRot = 0.0f;
            this.larm3.xRot = 0.0f;
        }
        entity.setRenderInfo(r);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
