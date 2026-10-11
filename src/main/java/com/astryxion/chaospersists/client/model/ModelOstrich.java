package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Ostrich;
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

public class ModelOstrich extends EntityModel<Ostrich> {
    private final float wingspeed;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart l_leg1;
    private final ModelPart rleg1;
    private final ModelPart l_leg2;
    private final ModelPart lfoot1;
    private final ModelPart r_leg2;
    private final ModelPart lfoot2;
    private final ModelPart lfoot3;
    private final ModelPart l_claw1;
    private final ModelPart l_claw2;
    private final ModelPart l_claw3;
    private final ModelPart lfoot4;
    private final ModelPart l_claw4;
    private final ModelPart rfoot1;
    private final ModelPart rfoot2;
    private final ModelPart rclaw1;
    private final ModelPart rfoot3;
    private final ModelPart rclaw3;
    private final ModelPart rfoot4;
    private final ModelPart rclaw2;
    private final ModelPart rclaw4;
    private final ModelPart body3;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart body4;
    private final ModelPart head;
    private final ModelPart leftleg;
    private final ModelPart neck1;
    private final ModelPart head1;
    private final ModelPart mouth1;
    private final ModelPart neck2;
    private final ModelPart rightleg;
    private final ModelPart lwing;
    private final ModelPart rwing;
    private final ModelPart hat1;
    private final ModelPart hat2;

    public ModelOstrich(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 256, 128).bakeRoot());
    }

    public ModelOstrich(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body1 = root.getChild("body1");
        this.body2 = root.getChild("body2");
        this.l_leg1 = root.getChild("l_leg1");
        this.rleg1 = root.getChild("rleg1");
        this.l_leg2 = root.getChild("l_leg2");
        this.lfoot1 = root.getChild("lfoot1");
        this.r_leg2 = root.getChild("r_leg2");
        this.lfoot2 = root.getChild("lfoot2");
        this.lfoot3 = root.getChild("lfoot3");
        this.l_claw1 = root.getChild("l_claw1");
        this.l_claw2 = root.getChild("l_claw2");
        this.l_claw3 = root.getChild("l_claw3");
        this.lfoot4 = root.getChild("lfoot4");
        this.l_claw4 = root.getChild("l_claw4");
        this.rfoot1 = root.getChild("rfoot1");
        this.rfoot2 = root.getChild("rfoot2");
        this.rclaw1 = root.getChild("rclaw1");
        this.rfoot3 = root.getChild("rfoot3");
        this.rclaw3 = root.getChild("rclaw3");
        this.rfoot4 = root.getChild("rfoot4");
        this.rclaw2 = root.getChild("rclaw2");
        this.rclaw4 = root.getChild("rclaw4");
        this.body3 = root.getChild("body3");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.body4 = root.getChild("body4");
        this.head = root.getChild("head");
        this.leftleg = root.getChild("leftleg");
        this.neck1 = root.getChild("neck1");
        this.head1 = root.getChild("head1");
        this.mouth1 = root.getChild("mouth1");
        this.neck2 = root.getChild("neck2");
        this.rightleg = root.getChild("rightleg");
        this.lwing = root.getChild("lwing");
        this.rwing = root.getChild("rwing");
        this.hat1 = root.getChild("hat1");
        this.hat2 = root.getChild("hat2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0f, 0.0f, 0.0f, 8, 9, 8), PartPose.offsetAndRotation(0.0f, 0.0f, -6.0f, -0.2230717f, 0.0f, 0.0f));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(25, 111).addBox(-4.0f, 0.0f, 0.0f, 8, 8, 8), PartPose.offset(0.0f, 2.0f, -1.0f));
        root.addOrReplaceChild("l_leg1", CubeListBuilder.create().texOffs(25, 70).addBox(-1.0f, 3.0f, -5.0f, 2, 7, 3), PartPose.offsetAndRotation(3.0f, 8.0f, 1.0f, 0.4833219f, 0.0f, 0.0f));
        root.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(25, 70).addBox(-2.0f, 3.0f, -5.0f, 2, 7, 3), PartPose.offsetAndRotation(-2.0f, 8.0f, 1.0f, 0.4833219f, 0.0f, 0.0f));
        root.addOrReplaceChild("l_leg2", CubeListBuilder.create().texOffs(29, 59).addBox(-1.0f, 7.0f, 4.0f, 2, 7, 3), PartPose.offsetAndRotation(3.0f, 8.0f, 1.0f, -0.4370552f, 0.0f, 0.0f));
        root.addOrReplaceChild("lfoot1", CubeListBuilder.create().texOffs(29, 50).addBox(-1.0f, 14.0f, -5.0f, 2, 2, 6), PartPose.offset(3.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("r_leg2", CubeListBuilder.create().texOffs(29, 59).addBox(-2.0f, 7.0f, 4.0f, 2, 7, 3), PartPose.offsetAndRotation(-2.0f, 8.0f, 1.0f, -0.4370552f, 0.0f, 0.0f));
        root.addOrReplaceChild("lfoot2", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0f, 15.0f, -4.0f, 2, 1, 5), PartPose.offsetAndRotation(3.0f, 8.0f, 1.0f, 0.0f, 0.2602503f, 0.0f));
        root.addOrReplaceChild("lfoot3", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0f, 15.0f, -4.0f, 2, 1, 5), PartPose.offsetAndRotation(3.0f, 8.0f, 1.0f, 0.0f, -0.260246f, 0.0f));
        root.addOrReplaceChild("l_claw1", CubeListBuilder.create().texOffs(16, 10).addBox(0.0f, 14.0f, -7.0f, 0, 2, 3), PartPose.offset(3.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("l_claw2", CubeListBuilder.create().texOffs(19, 16).addBox(-0.5f, 15.0f, -5.0f, 0, 1, 3), PartPose.offsetAndRotation(3.0f, 8.0f, 1.0f, 0.0f, 0.260246f, 0.0f));
        root.addOrReplaceChild("l_claw3", CubeListBuilder.create().texOffs(19, 16).addBox(0.5f, 15.0f, -5.0f, 0, 1, 3), PartPose.offsetAndRotation(3.0f, 8.0f, 1.0f, 0.0f, -0.260246f, 0.0f));
        root.addOrReplaceChild("lfoot4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, 14.0f, -1.0f, 2, 2, 4), PartPose.offset(3.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("l_claw4", CubeListBuilder.create().texOffs(16, 10).addBox(0.0f, 14.0f, 2.0f, 0, 2, 3), PartPose.offset(3.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("rfoot1", CubeListBuilder.create().texOffs(29, 50).addBox(-2.0f, 14.0f, -5.0f, 2, 2, 6), PartPose.offset(-2.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("rfoot2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, 14.0f, -1.0f, 2, 2, 4), PartPose.offset(-2.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("rclaw1", CubeListBuilder.create().texOffs(16, 10).addBox(-1.0f, 14.0f, -7.0f, 0, 2, 3), PartPose.offset(-2.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("rfoot3", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0f, 15.0f, -4.0f, 2, 1, 5), PartPose.offsetAndRotation(-2.0f, 8.0f, 1.0f, 0.0f, -0.260246f, 0.0f));
        root.addOrReplaceChild("rclaw3", CubeListBuilder.create().texOffs(19, 16).addBox(-0.5f, 15.0f, -5.0f, 0, 1, 3), PartPose.offsetAndRotation(-2.0f, 8.0f, 1.0f, 0.0f, -0.260246f, 0.0f));
        root.addOrReplaceChild("rfoot4", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0f, 15.0f, -4.0f, 2, 1, 5), PartPose.offsetAndRotation(-2.0f, 8.0f, 1.0f, 0.0f, 0.2602503f, 0.0f));
        root.addOrReplaceChild("rclaw2", CubeListBuilder.create().texOffs(19, 16).addBox(-1.5f, 15.0f, -5.0f, 0, 1, 3), PartPose.offsetAndRotation(-2.0f, 8.0f, 1.0f, 0.0f, 0.260246f, 0.0f));
        root.addOrReplaceChild("rclaw4", CubeListBuilder.create().texOffs(16, 10).addBox(-1.0f, 14.0f, 2.0f, 0, 2, 3), PartPose.offset(-2.0f, 8.0f, 1.0f));
        root.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(17, 96).addBox(-3.0f, 0.0f, 0.0f, 6, 7, 3), PartPose.offset(0.0f, 2.0f, 6.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(33, 81).addBox(-2.0f, 0.0f, 0.0f, 4, 0, 14), PartPose.offsetAndRotation(0.0f, 3.0f, 9.0f, -0.5948578f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(36, 97).addBox(-1.0f, 0.0f, 0.0f, 3, 0, 13), PartPose.offsetAndRotation(0.0f, 3.0f, 8.0f, -0.5948578f, 0.3346075f, 0.0f));
        root.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(36, 97).addBox(-2.0f, 0.0f, 0.0f, 3, 0, 13), PartPose.offsetAndRotation(0.0f, 3.0f, 8.0f, -0.5948578f, -0.3346145f, 0.0f));
        root.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(17, 89).addBox(-2.0f, 0.0f, 0.0f, 4, 3, 3), PartPose.offsetAndRotation(0.0f, 6.0f, 7.0f, 1.003822f, 0.0f, 0.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(74, 48).addBox(-1.0f, -24.0f, -7.0f, 2, 2, 4), PartPose.offset(0.0f, 5.0f, -7.0f));
        root.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 5), PartPose.offsetAndRotation(3.0f, 8.0f, 1.0f, -0.2974289f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(79, 84).addBox(-1.5f, -21.0f, -2.0f, 3, 21, 3), PartPose.offsetAndRotation(0.0f, 5.0f, -7.0f, 0.0f, -0.0349066f, 0.0f));
        root.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 70).addBox(-2.0f, -25.0f, -3.0f, 4, 4, 4), PartPose.offset(0.0f, 5.0f, -7.0f));
        root.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(74, 64).addBox(-1.0f, -22.0f, -6.0f, 2, 1, 3), PartPose.offset(0.0f, 5.0f, -7.0f));
        root.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(0, 99).addBox(-1.0f, -2.0f, -2.0f, 2, 4, 3), PartPose.offset(0.0f, 5.0f, -6.9f));
        root.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0f, 0.0f, -2.0f, 4, 6, 5), PartPose.offsetAndRotation(-2.0f, 8.0f, 1.0f, -0.2974216f, 0.0f, 0.0f));
        root.addOrReplaceChild("lwing", CubeListBuilder.create().texOffs(0, 107).addBox(0.0f, 0.0f, 0.0f, 1, 7, 11), PartPose.offset(4.0f, 1.0f, -5.0f));
        root.addOrReplaceChild("rwing", CubeListBuilder.create().texOffs(0, 107).addBox(0.0f, 0.0f, 0.0f, 1, 7, 11), PartPose.offset(-5.0f, 1.0f, -5.0f));
        root.addOrReplaceChild("hat1", CubeListBuilder.create().texOffs(40, 0).addBox(-2.5f, -26.0f, -4.0f, 5, 1, 5), PartPose.offset(0.0f, 5.0f, -7.0f));
        root.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0f, -28.0f, -3.0f, 4, 2, 4), PartPose.offset(0.0f, 5.0f, -7.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Ostrich e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        
        RenderInfo r = e.getRenderInfo();
        float newangle = 0.0f;
        float nextangle = 0.0f;
        float lspeed =
                (float)
                        Math.sqrt(
                                (e.xo - e.getX()) * (e.xo - e.getX())
                                        + (e.zo - e.getZ()) * (e.zo - e.getZ()));
        newangle = Mth.cos((float)(ageInTicks * 1.25f * this.wingspeed)) * 3.1415927f * lspeed * 0.4f;
        if ((double)newangle > 0.5) {
            newangle = 0.75f;
        }
        if ((double)newangle < -0.5) {
            newangle = -0.75f;
        }
        this.leftleg.xRot = -0.297f + newangle;
        this.l_leg1.xRot = 0.483f + newangle;
        this.l_leg2.xRot = -0.437f + newangle;
        this.lfoot1.xRot = newangle;
        this.lfoot2.xRot = newangle;
        this.lfoot3.xRot = newangle;
        this.lfoot4.xRot = newangle;
        this.l_claw1.xRot = newangle;
        this.l_claw2.xRot = newangle;
        this.l_claw3.xRot = newangle;
        this.l_claw4.xRot = newangle;
        this.rightleg.xRot = -0.297f - newangle;
        this.rleg1.xRot = 0.483f - newangle;
        this.r_leg2.xRot = -0.437f - newangle;
        this.rfoot1.xRot = - newangle;
        this.rfoot2.xRot = - newangle;
        this.rfoot3.xRot = - newangle;
        this.rfoot4.xRot = - newangle;
        this.rclaw1.xRot = - newangle;
        this.rclaw2.xRot = - newangle;
        this.rclaw3.xRot = - newangle;
        this.rclaw4.xRot = - newangle;
        this.tail2.xRot = this.tail1.xRot = -0.594f + Mth.cos((float)(ageInTicks * 0.05f)) * 3.1415927f * 0.06f;
        this.tail3.xRot = this.tail1.xRot;
        this.tail3.yRot = -0.334f + Mth.cos((float)(ageInTicks * 0.061f)) * 3.1415927f * 0.08f;
        this.tail2.yRot = 0.334f - Mth.cos((float)(ageInTicks * 0.072f)) * 3.1415927f * 0.08f;
        r = e.getRenderInfo();
        if (!e.getPassengers().isEmpty()) {
            netHeadYaw = (e.yRotO - e.getYRot()) * 20.0f;
            netHeadYaw = - netHeadYaw;
            r.rf1 += (netHeadYaw - r.rf1) / 60.0f;
            if (r.rf1 > 50.0f) {
                r.rf1 = 50.0f;
            }
            if (r.rf1 < -50.0f) {
                r.rf1 = -50.0f;
            }
            netHeadYaw = r.rf1;
        } else {
            netHeadYaw /= 2.0f;
        }
        if (e.isInSittingPose() && e.get_is_activated() == 0) {
            netHeadYaw = 0.0f;
            this.head.xRot = this.head1.xRot = 3.1415f;
            this.mouth1.xRot = this.head1.xRot;
            this.neck1.xRot = this.head1.xRot;
            this.hat1.xRot = this.head1.xRot;
            this.hat2.xRot = this.head1.xRot;
        } else {
            this.head.xRot = this.head1.xRot = 0.0f;
            this.mouth1.xRot = this.head1.xRot;
            this.neck1.xRot = this.head1.xRot;
            this.hat1.xRot = this.head1.xRot;
            this.hat2.xRot = this.head1.xRot;
        }
        this.head.yRot = this.head1.yRot = (float) Math.toRadians(netHeadYaw) * 0.65f;
        this.mouth1.yRot = this.head1.yRot;
        this.hat1.yRot = this.head1.yRot;
        this.hat2.yRot = this.head1.yRot;
        newangle = Mth.cos((float)(ageInTicks * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = Mth.cos((float)((ageInTicks + 0.3f) * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (e.getRandom().nextInt(3) == 1) {
                r.ri1 = 1;
            }
        }
        if (r.ri1 == 0) {
            newangle = 0.0f;
        }
        newangle = Math.abs(newangle);
        this.lwing.zRot = - newangle;
        this.lwing.yRot = newangle / 2.0f;
        this.rwing.zRot = newangle;
        this.rwing.yRot = (- newangle) / 2.0f;
        e.setRenderInfo(r);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l_leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l_leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.r_leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l_claw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l_claw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l_claw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l_claw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderHats(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            Ostrich e,
            float red,
            float green,
            float blue,
            float alpha) {
        if (e.get_is_activated() != 0) {
            this.hat1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            if (e.get_is_activated() > 1) {
                this.hat2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            }
        }
    }
}
