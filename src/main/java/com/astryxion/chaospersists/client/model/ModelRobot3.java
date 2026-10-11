package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Robot3;
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

public class ModelRobot3 extends EntityModel<Robot3> {
    private final float wingspeed;
    private final ModelPart rleg1;
    private final ModelPart lleg1;
    private final ModelPart rleg2;
    private final ModelPart lleg2;
    private final ModelPart hips;
    private final ModelPart waist1;
    private final ModelPart waist2;
    private final ModelPart body3;
    private final ModelPart lazer;
    private final ModelPart body2;
    private final ModelPart body1;
    private final ModelPart body4;
    private final ModelPart waist3;
    private final ModelPart larm3;
    private final ModelPart rarm3;
    private final ModelPart larm2;
    private final ModelPart rarm2;
    private final ModelPart larm1;
    private final ModelPart rarm1;

    public ModelRobot3(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 512, 512).bakeRoot(), wingspeed);
    }

    public ModelRobot3(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.rleg1 = root.getChild("rleg1");
        this.lleg1 = root.getChild("lleg1");
        this.rleg2 = root.getChild("rleg2");
        this.lleg2 = root.getChild("lleg2");
        this.hips = root.getChild("hips");
        this.waist1 = root.getChild("waist1");
        this.waist2 = root.getChild("waist2");
        this.body3 = root.getChild("body3");
        this.lazer = root.getChild("lazer");
        this.body2 = root.getChild("body2");
        this.body1 = root.getChild("body1");
        this.body4 = root.getChild("body4");
        this.waist3 = root.getChild("waist3");
        this.larm3 = root.getChild("larm3");
        this.rarm3 = root.getChild("rarm3");
        this.larm2 = root.getChild("larm2");
        this.rarm2 = root.getChild("rarm2");
        this.larm1 = root.getChild("larm1");
        this.rarm1 = root.getChild("rarm1");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(20, 100).addBox(-23.0f, 26.0f, -8.0f, 16, 29, 16), PartPose.offset(-9.0f, -31.0f, 0.0f));
        root.addOrReplaceChild("lleg1", CubeListBuilder.create().texOffs(20, 159).addBox(7.0f, 25.0f, -8.0f, 16, 29, 16), PartPose.offset(9.0f, -30.0f, 0.0f));
        root.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(20, 212).addBox(-14.0f, 0.0f, -7.0f, 14, 29, 14), PartPose.offsetAndRotation(-9.0f, -31.0f, 0.0f, 0.0f, 0.0f, 0.2792527f));
        root.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(20, 265).addBox(0.0f, 0.0f, -7.0f, 13, 29, 14), PartPose.offsetAndRotation(9.0f, -31.0f, 0.0f, 0.0f, 0.0f, -0.2792527f));
        root.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(20, 316).addBox(0.0f, 0.0f, 0.0f, 18, 16, 16), PartPose.offset(-9.0f, -43.0f, -8.0f));
        root.addOrReplaceChild("waist1", CubeListBuilder.create().texOffs(20, 359).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12), PartPose.offsetAndRotation(-6.0f, -55.0f, -4.0f, -0.1f, 0.0f, 0.0f));
        root.addOrReplaceChild("waist2", CubeListBuilder.create().texOffs(20, 391).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12), PartPose.offset(-6.0f, -67.0f, -4.0f));
        root.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(20, 426).addBox(-23.0f, -25.0f, 10.0f, 47, 47, 25), PartPose.offsetAndRotation(0.0f, -88.0f, -10.0f, 0.2f, 0.0f, 0.0f));
        root.addOrReplaceChild("lazer", CubeListBuilder.create().texOffs(20, 50).addBox(-8.0f, -8.0f, -22.0f, 17, 16, 22), PartPose.offsetAndRotation(0.0f, -88.0f, -11.0f, 0.4f, 0.0f, 0.0f));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(101, 103).addBox(9.0f, -24.0f, -12.0f, 15, 47, 47), PartPose.offsetAndRotation(0.0f, -88.0f, -11.0f, 0.2f, 0.0f, 0.0f));
        root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(101, 210).addBox(-23.0f, -24.0f, -12.0f, 15, 47, 47), PartPose.offsetAndRotation(0.0f, -88.0f, -11.0f, 0.2f, 0.0f, 0.0f));
        root.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(101, 321).addBox(-8.0f, -24.0f, -12.0f, 18, 16, 22), PartPose.offsetAndRotation(0.0f, -88.0f, -11.0f, 0.2f, 0.0f, 0.0f));
        root.addOrReplaceChild("waist3", CubeListBuilder.create().texOffs(99, 375).addBox(0.0f, 0.0f, -1.0f, 12, 17, 12), PartPose.offsetAndRotation(-6.0f, -83.0f, -6.0f, 0.2f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm3", CubeListBuilder.create().texOffs(121, 54).addBox(0.0f, -10.0f, -9.0f, 20, 18, 18), PartPose.offsetAndRotation(24.0f, -92.0f, 2.0f, 1.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm3", CubeListBuilder.create().texOffs(26, 8).addBox(-20.0f, -9.0f, -9.0f, 20, 18, 18), PartPose.offsetAndRotation(-23.0f, -92.0f, 2.0f, 1.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(207, 47).addBox(3.0f, 8.0f, -7.0f, 14, 29, 14), PartPose.offsetAndRotation(24.0f, -92.0f, 2.0f, 1.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(161, 372).addBox(-17.0f, 9.0f, -7.0f, 14, 29, 14), PartPose.offsetAndRotation(-23.0f, -92.0f, 2.0f, 1.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("larm1", CubeListBuilder.create().texOffs(185, 433).addBox(0.0f, -12.0f, 30.0f, 14, 37, 14), PartPose.offsetAndRotation(27.0f, -92.0f, 2.0f, -1.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rarm1", CubeListBuilder.create().texOffs(239, 105).addBox(-17.0f, -12.0f, 30.0f, 14, 37, 14), PartPose.offsetAndRotation(-23.0f, -92.0f, 2.0f, -1.0f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Robot3 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderInfo r;
        float newangle = 0.0f;
        float nextangle = 0.0f;
        newangle = (double)limbSwingAmount > 0.1
                ? Mth.cos((float)(ageInTicks * 0.55f * this.wingspeed)) * 3.1415927f * 0.12f * limbSwingAmount
                : 0.0f;
        this.lleg1.xRot = newangle;
        this.lleg2.xRot = newangle;
        this.rleg1.xRot = -newangle;
        this.rleg2.xRot = -newangle;
        this.lazer.yRot = (float)Math.toRadians((double)netHeadYaw / 2.0);
        r = entity.getRenderInfo();
        newangle = Mth.cos((float)(ageInTicks * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = Mth.cos((float)((ageInTicks + 0.3f) * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (entity.getAttacking() != 0) {
                r.ri1 = 1;
            }
        }
        if (r.ri1 == 0) {
            newangle = 0.0f;
        }
        this.rarm1.xRot = newangle - 1.0f;
        this.rarm2.xRot = newangle + 1.0f;
        this.rarm3.xRot = newangle + 1.0f;
        this.larm1.xRot = newangle - 1.0f;
        this.larm2.xRot = newangle + 1.0f;
        this.larm3.xRot = newangle + 1.0f;
        entity.setRenderInfo(r);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hips.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.waist1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.waist2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lazer.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.waist3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
