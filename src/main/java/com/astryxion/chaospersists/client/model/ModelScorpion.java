package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Scorpion;
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

public class ModelScorpion extends EntityModel<Scorpion> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart tail5;
    private final ModelPart tail6;
    private final ModelPart lleg1;
    private final ModelPart rleg1;
    private final ModelPart rleg2;
    private final ModelPart lleg3;
    private final ModelPart rleg4;
    private final ModelPart rleg3;
    private final ModelPart lleg4;
    private final ModelPart lleg2;
    private final ModelPart head;
    private final ModelPart larm2;
    private final ModelPart rarm2;
    private final ModelPart larm1;
    private final ModelPart rarm1;
    private final ModelPart lclaw;
    private final ModelPart rclaw;

    public ModelScorpion() {
        this(1.0f);
    }

    public ModelScorpion(float wingspeed) {
        this(wingspeed, LayerDefinition.create(createMesh(), 88, 24).bakeRoot());
    }

    public ModelScorpion(ModelPart root) {
        this(1.0f, root);
    }

    public ModelScorpion(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.tail5 = root.getChild("tail5");
        this.tail6 = root.getChild("tail6");
        this.lleg1 = root.getChild("lleg1");
        this.rleg1 = root.getChild("rleg1");
        this.rleg2 = root.getChild("rleg2");
        this.lleg3 = root.getChild("lleg3");
        this.rleg4 = root.getChild("rleg4");
        this.rleg3 = root.getChild("rleg3");
        this.lleg4 = root.getChild("lleg4");
        this.lleg2 = root.getChild("lleg2");
        this.head = root.getChild("head");
        this.larm2 = root.getChild("larm2");
        this.rarm2 = root.getChild("rarm2");
        this.larm1 = root.getChild("larm1");
        this.rarm1 = root.getChild("rarm1");
        this.lclaw = root.getChild("lclaw");
        this.rclaw = root.getChild("rclaw");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 6, 4, 8), PartPose.offset(-3.0f, 17.0f, -4.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(28, 0).addBox(0.0f, 0.0f, 0.0f, 4, 4, 5), PartPose.offsetAndRotation(-2.0f, 17.0f, 3.0f, 0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(46, 0).addBox(0.0f, 0.0f, 0.0f, 3, 3, 5), PartPose.offsetAndRotation(-1.5f, 16.8f, 6.0f, 1.029744f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(62, 0).addBox(0.0f, 0.0f, 0.0f, 3, 3, 4), PartPose.offsetAndRotation(-1.5f, 14.5f, 8.0f, 1.727876f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 17).addBox(0.0f, 0.0f, 0.0f, 2, 2, 5), PartPose.offsetAndRotation(-1.0f, 12.0f, 9.0f, 2.513274f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(70, 7).addBox(0.0f, 0.0f, 0.0f, 2, 2, 4), PartPose.offsetAndRotation(-1.0f, 9.0f, 6.0f, 3.141593f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(62, 7).addBox(0.0f, 0.0f, 0.0f, 1, 1, 3), PartPose.offsetAndRotation(-0.5f, 8.0f, 2.0f, 3.141593f, 0.0f, 0.0f));
        root.addOrReplaceChild("lleg1", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(2.0f, 18.0f, -3.0f, 0.0f, 0.4886922f, 0.3665191f));
        root.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(-2.0f, 18.0f, -1.0f, 0.0f, 2.6529f, -0.3665191f));
        root.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(-2.0f, 18.0f, 1.0f, 0.0f, 2.897247f, -0.3665191f));
        root.addOrReplaceChild("lleg3", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(2.0f, 18.0f, 1.0f, 0.0f, -0.2443461f, 0.3665191f));
        root.addOrReplaceChild("rleg4", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(-2.0f, 18.0f, 5.0f, 0.0f, -2.6529f, -0.3665191f));
        root.addOrReplaceChild("rleg3", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(-2.0f, 18.0f, 3.0f, 0.0f, -2.897247f, -0.3665191f));
        root.addOrReplaceChild("lleg4", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(2.0f, 18.0f, 3.0f, 0.0f, -0.4886922f, 0.3665191f));
        root.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 11, 2, 2), PartPose.offsetAndRotation(2.0f, 18.0f, -1.0f, 0.0f, 0.2443461f, 0.3665191f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 9).addBox(0.0f, 0.0f, 0.0f, 5, 3, 4), PartPose.offset(-2.5f, 17.5f, -8.0f));
        root.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(46, 8).addBox(0.0f, 0.0f, 0.0f, 6, 2, 2), PartPose.offsetAndRotation(1.0f, 18.0f, -6.0f, 0.0f, 0.5235988f, 0.1745329f));
        root.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(46, 8).addBox(0.0f, 0.0f, -2.0f, 6, 2, 2), PartPose.offsetAndRotation(-1.0f, 18.0f, -6.0f, 0.0f, 2.617994f, -0.1745329f));
        root.addOrReplaceChild("larm1", CubeListBuilder.create().texOffs(70, 13).addBox(-2.0f, 0.0f, -3.0f, 2, 2, 3), PartPose.offsetAndRotation(7.0f, 19.0f, -7.2f, 0.1745329f, 0.1745329f, 0.0f));
        root.addOrReplaceChild("rarm1", CubeListBuilder.create().texOffs(70, 13).addBox(0.0f, 0.0f, -3.0f, 2, 2, 3), PartPose.offsetAndRotation(-7.0f, 19.0f, -7.2f, 0.1745329f, -0.1745329f, 0.0f));
        root.addOrReplaceChild("lclaw", CubeListBuilder.create().texOffs(46, 12).addBox(-3.0f, 0.0f, -4.0f, 3, 2, 4), PartPose.offsetAndRotation(7.0f, 19.0f, -10.0f, 0.0174533f, 0.3839724f, 0.1396263f));
        root.addOrReplaceChild("rclaw", CubeListBuilder.create().texOffs(46, 12).addBox(0.0f, 0.0f, -4.0f, 3, 2, 4), PartPose.offsetAndRotation(-7.0f, 19.0f, -10.0f, 0.0174533f, -0.3839724f, 0.1396263f));
        return mesh;
    }

    @Override
    public void setupAnim(Scorpion entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        float upangle = 0.0f;
        float nextangle = 0.0f;
        float pi4 = 1.570795f;
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed)) * 3.1415927f * 0.12f * limbSwingAmount;
        this.lleg1.yRot = newangle + 0.49f;
        this.rleg1.yRot = - newangle + 2.65f;
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed - 1.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        this.lleg2.yRot = newangle + 0.24f;
        this.rleg2.yRot = - newangle + 2.9f;
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        this.lleg3.yRot = newangle - 0.24f;
        this.rleg3.yRot = - newangle - 2.9f;
        newangle = Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.12f * limbSwingAmount;
        this.lleg4.yRot = newangle - 0.49f;
        this.rleg4.yRot = - newangle - 2.65f;
        RenderInfo r = entity.getRenderInfo();
        newangle = Mth.cos(ageInTicks * 3.0f * this.wingspeed) * (float) Math.PI * 0.15f;
        nextangle = Mth.cos((ageInTicks + 0.1f) * 3.0f * this.wingspeed) * (float) Math.PI * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (entity.getAttacking() == 0) {
                r.ri1 = entity.getRandom().nextInt(20);
                r.ri2 = entity.getRandom().nextInt(25);
            } else {
                r.ri1 = entity.getRandom().nextInt(4);
                r.ri2 = entity.getRandom().nextInt(3);
            }
        }
        if (r.ri1 == 1 || r.ri1 == 3) {
            this.doLeftClaw(newangle);
        } else {
            this.doLeftClaw(0.0f);
        }
        if (r.ri1 == 2 || r.ri1 == 3) {
            this.doRightClaw(newangle);
        } else {
            this.doRightClaw(0.0f);
        }
        if (r.ri2 == 1) {
            this.doTail(newangle);
        } else {
            this.doTail(0.0f);
        }
        entity.setRenderInfo(r);
    }

    private void doLeftClaw(float angle) {
        this.larm2.yRot = 0.52f + angle;
        this.larm1.z = (float) ((double) this.larm2.z - Math.sin(this.larm2.yRot) * 4.5);
        this.lclaw.z = this.larm1.z - 3.0f;
        this.lclaw.yRot = 0.381f - angle;
    }

    private void doRightClaw(float angle) {
        this.rarm2.yRot = 2.61f - angle;
        this.rarm1.z = (float) ((double) this.rarm2.z - Math.sin(this.rarm2.yRot) * 4.5);
        this.rclaw.z = this.rarm1.z - 3.0f;
        this.rclaw.yRot = -0.381f + angle;
    }

    private void doTail(float angle) {
        this.tail1.xRot = 0.26f + angle;
        this.tail2.xRot = this.tail1.xRot + 0.76900005f + angle;
        this.tail2.y = (float) ((double) this.tail1.y - Math.sin(this.tail1.xRot) * 4.0);
        this.tail2.z = (float) ((double) this.tail1.z + Math.cos(this.tail1.xRot) * 4.0);
        this.tail3.xRot = this.tail2.xRot + 0.701f + angle;
        this.tail3.y = (float) ((double) this.tail2.y - Math.sin(this.tail2.xRot) * 4.0);
        this.tail3.z = (float) ((double) this.tail2.z + Math.cos(this.tail2.xRot) * 4.0);
        this.tail4.xRot = this.tail3.xRot + -5.501f - angle * 3.0f / 2.0f - 0.4f;
        this.tail4.y = (float) ((double) this.tail3.y - Math.sin(this.tail3.xRot) * 3.0);
        this.tail4.z = (float) ((double) this.tail3.z + Math.cos(this.tail3.xRot) * 3.0);
        this.tail5.y = (float) ((double) this.tail4.y - Math.sin(this.tail4.xRot) * 4.0);
        this.tail5.z = (float) ((double) this.tail4.z + Math.cos(this.tail4.xRot) * 4.0);
        this.tail6.y = (float) ((double) this.tail5.y - Math.sin(this.tail5.xRot) * 4.0);
        this.tail6.z = (float) ((double) this.tail5.z + Math.cos(this.tail5.xRot) * 4.0);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
