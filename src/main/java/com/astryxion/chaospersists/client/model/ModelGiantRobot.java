package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.RenderGiantRobotInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelGiantRobot extends EntityModel<GiantRobot> {
    private final float wingspeed;
    private final float hipy;
    private final ModelPart Hip;
    private final ModelPart Thigh;
    private final ModelPart Shin;
    private final ModelPart Foot1;
    private final ModelPart Foot2;
    private final ModelPart Foot3;
    private final ModelPart Thigh2;
    private final ModelPart Thigh3;
    private final ModelPart Back1;
    private final ModelPart Back2;
    private final ModelPart Back3;
    private final ModelPart Shoulders;
    private final ModelPart Neck;
    private final ModelPart Head;
    private final ModelPart Arm1;
    private final ModelPart Arm2;
    private final ModelPart Arm3;
    private final ModelPart Knuckles;

    public ModelGiantRobot(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 256, 512).bakeRoot(), wingspeed);
    }

    public ModelGiantRobot(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Hip = root.getChild("Hip");
        this.Thigh = root.getChild("Thigh");
        this.Shin = root.getChild("Shin");
        this.Foot1 = root.getChild("Foot1");
        this.Foot2 = root.getChild("Foot2");
        this.Foot3 = root.getChild("Foot3");
        this.Thigh2 = root.getChild("Thigh2");
        this.Thigh3 = root.getChild("Thigh3");
        this.Back1 = root.getChild("Back1");
        this.Back2 = root.getChild("Back2");
        this.Back3 = root.getChild("Back3");
        this.Shoulders = root.getChild("Shoulders");
        this.Neck = root.getChild("Neck");
        this.Head = root.getChild("Head");
        this.Arm1 = root.getChild("Arm1");
        this.Arm2 = root.getChild("Arm2");
        this.Arm3 = root.getChild("Arm3");
        this.Knuckles = root.getChild("Knuckles");
        this.hipy = this.Hip.y;
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Hip", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -4.0f, -15.0f, 8, 8, 30), PartPose.offsetAndRotation(0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Thigh", CubeListBuilder.create().texOffs(0, 115).addBox(-3.0f, -3.0f, -3.0f, 6, 43, 6), PartPose.offsetAndRotation(0.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shin", CubeListBuilder.create().texOffs(0, 167).addBox(-3.0f, -3.0f, -3.0f, 6, 43, 6), PartPose.offsetAndRotation(0.0f, -18.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Foot1", CubeListBuilder.create().texOffs(0, 282).addBox(-7.0f, 38.0f, -11.0f, 14, 4, 17), PartPose.offsetAndRotation(0.0f, -18.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Foot2", CubeListBuilder.create().texOffs(0, 246).addBox(-6.0f, 19.0f, -8.0f, 12, 19, 13), PartPose.offsetAndRotation(0.0f, -18.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Foot3", CubeListBuilder.create().texOffs(0, 219).addBox(-5.0f, 5.0f, -5.0f, 10, 14, 9), PartPose.offsetAndRotation(0.0f, -18.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Thigh2", CubeListBuilder.create().texOffs(0, 43).addBox(-7.0f, -8.0f, -7.0f, 14, 24, 14), PartPose.offsetAndRotation(0.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Thigh3", CubeListBuilder.create().texOffs(0, 84).addBox(-5.0f, 16.0f, -5.0f, 10, 17, 10), PartPose.offsetAndRotation(0.0f, -58.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Back1", CubeListBuilder.create().texOffs(125, 138).addBox(-4.0f, -20.0f, -4.0f, 8, 24, 8), PartPose.offsetAndRotation(0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Back2", CubeListBuilder.create().texOffs(125, 95).addBox(-13.0f, -42.0f, -10.0f, 26, 24, 16), PartPose.offsetAndRotation(0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Back3", CubeListBuilder.create().texOffs(125, 43).addBox(-17.0f, -68.0f, -13.0f, 34, 26, 20), PartPose.offsetAndRotation(0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Shoulders", CubeListBuilder.create().texOffs(60, 200).addBox(-22.0f, -64.0f, -4.0f, 44, 8, 8), PartPose.offsetAndRotation(0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(125, 29).addBox(-4.0f, -70.0f, -4.0f, 8, 2, 8), PartPose.offsetAndRotation(0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(127, 0).addBox(-7.0f, -82.0f, -7.0f, 14, 12, 14), PartPose.offsetAndRotation(0.0f, -60.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Arm1", CubeListBuilder.create().texOffs(77, 250).addBox(-6.0f, -6.0f, -6.0f, 12, 21, 12), PartPose.offsetAndRotation(28.0f, -120.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Arm2", CubeListBuilder.create().texOffs(73, 300).addBox(-4.0f, 15.0f, -4.0f, 8, 24, 8), PartPose.offsetAndRotation(28.0f, -120.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Arm3", CubeListBuilder.create().texOffs(61, 350).addBox(-3.0f, -3.0f, -3.0f, 6, 33, 6), PartPose.offsetAndRotation(28.0f, -81.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("Knuckles", CubeListBuilder.create().texOffs(56, 400).addBox(-7.0f, 30.0f, -5.0f, 14, 12, 10), PartPose.offsetAndRotation(28.0f, -81.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return mesh;
    }
    private GiantRobot animEntity;
    private float animLimbSwingAmount;
    private float animAgeInTicks;
    private float animNetHeadYaw;
    private float animHeadPitch;

    @Override
    public void setupAnim(GiantRobot entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.animLimbSwingAmount = limbSwingAmount;
        this.animAgeInTicks = ageInTicks;
        this.animNetHeadYaw = netHeadYaw;
        this.animHeadPitch = headPitch;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        GiantRobot e = this.animEntity;
        if (e == null) {
            return;
        }

        float b2angle;
        float a2angle;
        
        RenderGiantRobotInfo r = e.getRenderGiantRobotInfo();
        float movescale = this.animLimbSwingAmount * 0.65f;
        if (movescale > 1.0f) {
            movescale = 1.0f;
        }
        r.hipxdisplayangle = (float)(Math.cos((- this.animAgeInTicks) * this.wingspeed) * 3.141592653589793 * 0.10000000149011612 * (double)movescale);
        r.hipydisplayangle = (float)(Math.sin((- this.animAgeInTicks) * this.wingspeed) * 3.141592653589793 * 0.10000000149011612 * (double)movescale);
        r.thighdisplayangle[0] = (float)(Math.cos((double)((- this.animAgeInTicks) * this.wingspeed) + 1.5707963267948966) * 3.141592653589793 * 0.15000000596046448 * (double)movescale) - (float)(0.19634954084936207 * (double)movescale);
        r.thighdisplayangle[1] = (float)(Math.cos((double)((- this.animAgeInTicks) * this.wingspeed) + 3.141592653589793 + 1.5707963267948966) * 3.141592653589793 * 0.15000000596046448 * (double)movescale) - (float)(0.19634954084936207 * (double)movescale);
        r.shindisplayangle[0] = (float)((double)((float)(Math.cos((double)((- this.animAgeInTicks) * this.wingspeed) + 3.141592653589793) * 3.141592653589793 * 0.20000000298023224 * (double)movescale)) + 0.6283185400806344 * (double)movescale);
        r.shindisplayangle[1] = (float)((double)((float)(Math.cos((- this.animAgeInTicks) * this.wingspeed) * 3.141592653589793 * 0.20000000298023224 * (double)movescale)) + 0.6283185400806344 * (double)movescale);
        float newangle = (float)(Math.cos((- this.animAgeInTicks) * this.wingspeed * 2.0f) * (double)movescale);
        this.Hip.y = this.hipy + newangle * 4.0f;
        this.Hip.xRot = r.hipxdisplayangle;
        this.Hip.yRot = (float)((double)r.hipydisplayangle + 1.5707963267948966);
        this.Hip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh2.xRot = this.Thigh3.xRot = r.thighdisplayangle[0];
        this.Thigh.xRot = this.Thigh3.xRot;
        this.Thigh2.y = this.Thigh3.y = this.Hip.y - (float)Math.sin(this.Hip.xRot) * 13.0f;
        this.Thigh.y = this.Thigh3.y;
        this.Thigh2.z = this.Thigh3.z = this.Hip.z + (float)Math.cos(this.Hip.xRot) * (float)Math.cos(this.Hip.yRot) * 13.0f;
        this.Thigh.z = this.Thigh3.z;
        this.Thigh2.x = this.Thigh3.x = this.Hip.x + (float)Math.cos(this.Hip.xRot) * (float)Math.sin(this.Hip.yRot) * 13.0f;
        this.Thigh.x = this.Thigh3.x;
        this.Thigh.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shin.xRot = r.shindisplayangle[0];
        this.Shin.y = this.Thigh.y + (float)Math.cos(this.Thigh.xRot) * 40.0f;
        this.Shin.z = this.Thigh.z + (float)Math.sin(this.Thigh.xRot) * 40.0f;
        this.Shin.x = this.Thigh.x;
        this.Shin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot2.xRot = this.Foot3.xRot = r.shindisplayangle[0];
        this.Foot1.xRot = this.Foot3.xRot;
        this.Foot2.y = this.Foot3.y = this.Shin.y;
        this.Foot1.y = this.Foot3.y;
        this.Foot2.z = this.Foot3.z = this.Shin.z;
        this.Foot1.z = this.Foot3.z;
        this.Foot2.x = this.Foot3.x = this.Shin.x;
        this.Foot1.x = this.Foot3.x;
        this.Foot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh2.xRot = this.Thigh3.xRot = r.thighdisplayangle[1];
        this.Thigh.xRot = this.Thigh3.xRot;
        this.Thigh2.y = this.Thigh3.y = this.Hip.y + (float)Math.sin(this.Hip.xRot) * 13.0f;
        this.Thigh.y = this.Thigh3.y;
        this.Thigh2.z = this.Thigh3.z = this.Hip.z - (float)Math.cos(this.Hip.xRot) * (float)Math.cos(this.Hip.yRot) * 13.0f;
        this.Thigh.z = this.Thigh3.z;
        this.Thigh2.x = this.Thigh3.x = this.Hip.x - (float)Math.cos(this.Hip.xRot) * (float)Math.sin(this.Hip.yRot) * 13.0f;
        this.Thigh.x = this.Thigh3.x;
        this.Thigh.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Thigh3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shin.xRot = r.shindisplayangle[1];
        this.Shin.y = this.Thigh.y + (float)Math.cos(this.Thigh.xRot) * 40.0f;
        this.Shin.z = this.Thigh.z + (float)Math.sin(this.Thigh.xRot) * 40.0f;
        this.Shin.x = this.Thigh.x;
        this.Shin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot2.xRot = this.Foot3.xRot = r.shindisplayangle[1];
        this.Foot1.xRot = this.Foot3.xRot;
        this.Foot2.y = this.Foot3.y = this.Shin.y;
        this.Foot1.y = this.Foot3.y;
        this.Foot2.z = this.Foot3.z = this.Shin.z;
        this.Foot1.z = this.Foot3.z;
        this.Foot2.x = this.Foot3.x = this.Shin.x;
        this.Foot1.x = this.Foot3.x;
        this.Foot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Foot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        float shoulderangle = - r.hipydisplayangle;
        float a1angle = a2angle = r.thighdisplayangle[1];
        float b1angle = b2angle = r.thighdisplayangle[0];
        if (e.getAttacking() != 0) {
            shoulderangle = (float)(- Math.sin(this.animAgeInTicks * this.wingspeed * 2.0f) * 3.141592653589793 * 0.20000000298023224);
            a1angle = (float)((double)((float)(Math.sin(this.animAgeInTicks * this.wingspeed * 2.0f) * 3.141592653589793 / 5.0)) - 0.7853981633974483);
            a2angle = (float)((double)(- a1angle) + 3.141592653589793);
            a1angle = (float)((double)a1angle + 0.6283185307179586);
            a2angle = (float)((double)a2angle + 0.6283185307179586);
            b1angle = (float)((double)((float)(- Math.sin(this.animAgeInTicks * this.wingspeed * 2.0f) * 3.141592653589793 / 5.0)) - 0.7853981633974483);
            b2angle = (float)((double)(- b1angle) + 3.141592653589793);
            b1angle = (float)((double)b1angle + 0.6283185307179586);
            b2angle = (float)((double)b2angle + 0.6283185307179586);
        }
        this.Back3.yRot = shoulderangle / 2.0f;
        this.Shoulders.yRot = shoulderangle;
        this.Arm1.y = this.Arm2.y = this.Hip.y - 60.0f;
        this.Arm1.x = this.Arm2.x = this.Hip.x + 26.0f;
        this.Arm1.z = this.Arm2.z = this.Shoulders.z - (float)Math.sin(this.Shoulders.yRot) * 26.0f;
        this.Arm1.xRot = this.Arm2.xRot = a1angle;
        this.Arm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm3.xRot = this.Knuckles.xRot = (float)((double)a2angle - 0.19634954084936207);
        this.Arm3.y = this.Knuckles.y = this.Arm1.y + (float)Math.cos(this.Arm1.xRot) * 41.0f;
        this.Arm3.z = this.Knuckles.z = this.Arm1.z + (float)Math.sin(this.Arm1.xRot) * 41.0f;
        this.Arm3.x = this.Knuckles.x = this.Arm1.x;
        this.Arm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Knuckles.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm1.y = this.Arm2.y = this.Hip.y - 60.0f;
        this.Arm1.x = this.Arm2.x = this.Hip.x - 26.0f;
        this.Arm1.z = this.Arm2.z = this.Shoulders.z + (float)Math.sin(this.Shoulders.yRot) * 26.0f;
        this.Arm1.xRot = this.Arm2.xRot = b1angle;
        this.Arm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Arm3.xRot = this.Knuckles.xRot = (float)((double)b2angle - 0.19634954084936207);
        this.Arm3.y = this.Knuckles.y = this.Arm1.y + (float)Math.cos(this.Arm1.xRot) * 41.0f;
        this.Arm3.z = this.Knuckles.z = this.Arm1.z + (float)Math.sin(this.Arm1.xRot) * 41.0f;
        this.Arm3.x = this.Knuckles.x = this.Arm1.x;
        this.Arm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Knuckles.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Back2.y = this.Back3.y = this.Hip.y;
        this.Back1.y = this.Back3.y;
        this.Neck.y = this.Head.y = this.Hip.y;
        this.Shoulders.y = this.Head.y;
        this.Head.yRot = (float)Math.toRadians(this.animNetHeadYaw);
        this.Head.xRot = (float)Math.toRadians(this.animHeadPitch) / 3.0f;
        this.Back1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Back2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Back3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shoulders.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
