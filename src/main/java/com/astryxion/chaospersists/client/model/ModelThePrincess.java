package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.ThePrincess;
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

public class ModelThePrincess extends EntityModel<ThePrincess> implements RoyalWingMembranes {
    private final float wingspeed;
    private ThePrincess animEntity;
    private float animAgeInTicks;
    private float animLimbSwingAmount;
    private float animNetHeadYaw;
    private float animHeadPitch;
    private final ModelPart body;
    private final ModelPart neck1;
    private final ModelPart neck;
    private final ModelPart neckbase;
    private final ModelPart head;
    private final ModelPart Rleg1;
    private final ModelPart Lleg1;
    private final ModelPart snout;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart Lwing;
    private final ModelPart Rwing;
    private final ModelPart Tail5;
    private final ModelPart Tail6;
    private final ModelPart Lneck1;
    private final ModelPart Lneck;
    private final ModelPart Lhead;
    private final ModelPart Lsnout;
    private final ModelPart Rneck1;
    private final ModelPart Rneck;
    private final ModelPart Rhead;
    private final ModelPart Rsnout;
    private final ModelPart headfin;
    private final ModelPart Lheadfin;
    private final ModelPart Rheadfin;
    private final ModelPart Backfin;
    private final ModelPart Rwing2;
    private final ModelPart Rwing3;
    private final ModelPart Lwing2;
    private final ModelPart Lwing3;
    private final ModelPart Ljaw;
    private final ModelPart jaw;
    private final ModelPart Rjaw;
    private final ModelPart Lpower;
    private final ModelPart Cpower;
    private final ModelPart Rpower;
    public ModelThePrincess(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelThePrincess(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.neck1 = root.getChild("neck1");
        this.neck = root.getChild("neck");
        this.neckbase = root.getChild("neckbase");
        this.head = root.getChild("head");
        this.Rleg1 = root.getChild("Rleg1");
        this.Lleg1 = root.getChild("Lleg1");
        this.snout = root.getChild("snout");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.Lwing = root.getChild("Lwing");
        this.Rwing = root.getChild("Rwing");
        this.Tail5 = root.getChild("Tail5");
        this.Tail6 = root.getChild("Tail6");
        this.Lneck1 = root.getChild("Lneck1");
        this.Lneck = root.getChild("Lneck");
        this.Lhead = root.getChild("Lhead");
        this.Lsnout = root.getChild("Lsnout");
        this.Rneck1 = root.getChild("Rneck1");
        this.Rneck = root.getChild("Rneck");
        this.Rhead = root.getChild("Rhead");
        this.Rsnout = root.getChild("Rsnout");
        this.headfin = root.getChild("headfin");
        this.Lheadfin = root.getChild("Lheadfin");
        this.Rheadfin = root.getChild("Rheadfin");
        this.Backfin = root.getChild("Backfin");
        this.Rwing2 = root.getChild("Rwing2");
        this.Rwing3 = root.getChild("Rwing3");
        this.Lwing2 = root.getChild("Lwing2");
        this.Lwing3 = root.getChild("Lwing3");
        this.Ljaw = root.getChild("Ljaw");
        this.jaw = root.getChild("jaw");
        this.Rjaw = root.getChild("Rjaw");
        this.Lpower = root.getChild("Lpower");
        this.Cpower = root.getChild("Cpower");
        this.Rpower = root.getChild("Rpower");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(59, 34).addBox(-7.0f, -3.0f, -3.0f, 13, 8, 8), PartPose.offsetAndRotation(0.5f, 15.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(20, 45).addBox(-1.5f, -2.0f, -1.0f, 3, 1, 4), PartPose.offsetAndRotation(0.0f, 15.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(20, 31).addBox(-1.5f, -8.0f, -1.0f, 3, 8, 3), PartPose.offsetAndRotation(0.0f, 14.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neckbase", CubeListBuilder.create().texOffs(0, 76).addBox(-4.5f, -4.0f, 0.0f, 9, 6, 3), PartPose.offsetAndRotation(0.0f, 17.0f, 5.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 20).addBox(-2.0f, -3.0f, -3.5f, 4, 4, 5), PartPose.offsetAndRotation(0.0f, 7.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rleg1", CubeListBuilder.create().texOffs(0, 58).addBox(-1.5f, 0.0f, -2.0f, 3, 8, 4), PartPose.offsetAndRotation(6.0f, 16.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lleg1", CubeListBuilder.create().texOffs(15, 58).addBox(-1.5f, 0.0f, -2.0f, 3, 8, 4), PartPose.offsetAndRotation(-6.0f, 16.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(20, 11).addBox(-1.5f, -2.0f, -8.5f, 3, 3, 5), PartPose.offsetAndRotation(0.0f, 7.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 86).addBox(-3.0f, -2.5f, 0.0f, 6, 4, 7), PartPose.offsetAndRotation(0.0f, 16.0f, 7.0f, -0.3839724f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 98).addBox(-2.0f, -2.0f, 0.0f, 4, 3, 6), PartPose.offsetAndRotation(0.0f, 18.2f, 13.0f, -0.2094395f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 108).addBox(-1.5f, -1.5f, 0.0f, 3, 2, 5), PartPose.offsetAndRotation(0.0f, 19.5f, 18.0f, -0.0698132f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lwing", CubeListBuilder.create().texOffs(59, 0).addBox(-22.0f, 0.0f, -3.0f, 22, 0, 10), PartPose.offsetAndRotation(-6.0f, 12.6f, 0.0f, 0.0f, 0.0f, 0.4014257f));
        partdefinition.addOrReplaceChild("Rwing", CubeListBuilder.create().texOffs(59, 66).addBox(0.0f, 0.0f, -3.0f, 22, 0, 10), PartPose.offsetAndRotation(6.0f, 12.6f, 0.0f, 0.0f, 0.0f, -0.4014257f));
        partdefinition.addOrReplaceChild("Tail5", CubeListBuilder.create().texOffs(0, 116).addBox(-3.0f, 0.0f, 0.0f, 6, 2, 4), PartPose.offsetAndRotation(0.0f, 18.0f, 22.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail6", CubeListBuilder.create().texOffs(0, 123).addBox(-1.0f, 0.0f, 0.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 18.0f, 26.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lneck1", CubeListBuilder.create().texOffs(0, 45).addBox(-1.5f, -2.0f, -1.0f, 3, 1, 4), PartPose.offsetAndRotation(4.5f, 15.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lneck", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5f, -8.0f, -1.0f, 3, 8, 3), PartPose.offsetAndRotation(4.5f, 14.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lhead", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0f, -3.0f, -3.5f, 4, 4, 5), PartPose.offsetAndRotation(4.5f, 7.0f, -6.0f, -0.0174533f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lsnout", CubeListBuilder.create().texOffs(0, 11).addBox(-1.5f, -2.0f, -8.5f, 3, 3, 5), PartPose.offsetAndRotation(4.5f, 7.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rneck1", CubeListBuilder.create().texOffs(40, 45).addBox(-1.5f, -2.0f, -1.0f, 3, 1, 4), PartPose.offsetAndRotation(-4.5f, 15.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rneck", CubeListBuilder.create().texOffs(40, 31).addBox(-1.5f, -8.0f, -1.0f, 3, 8, 3), PartPose.offsetAndRotation(-4.5f, 14.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rhead", CubeListBuilder.create().texOffs(40, 20).addBox(-2.0f, -3.0f, -3.5f, 4, 4, 5), PartPose.offsetAndRotation(-4.5f, 7.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rsnout", CubeListBuilder.create().texOffs(40, 11).addBox(-1.5f, -2.0f, -8.5f, 3, 3, 5), PartPose.offsetAndRotation(-4.5f, 7.0f, -6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("headfin", CubeListBuilder.create().texOffs(20, 0).addBox(-0.5f, -3.0f, 1.0f, 1, 4, 3), PartPose.offsetAndRotation(0.0f, 7.0f, -6.0f, -0.122173f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lheadfin", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, -3.0f, 1.0f, 1, 4, 3), PartPose.offsetAndRotation(4.5f, 7.0f, -6.0f, -0.122173f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rheadfin", CubeListBuilder.create().texOffs(40, 0).addBox(-0.5f, -3.0f, 1.0f, 1, 4, 3), PartPose.offsetAndRotation(-4.5f, 7.0f, -6.0f, -0.122173f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Backfin", CubeListBuilder.create().texOffs(35, 57).addBox(-0.5f, 0.0f, 0.0f, 1, 3, 5), PartPose.offsetAndRotation(0.0f, 12.0f, -1.0f, 0.5061455f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rwing2", CubeListBuilder.create().texOffs(59, 77).addBox(0.0f, 0.0f, -3.0f, 12, 0, 10), PartPose.offsetAndRotation(6.0f, 12.6f, 0.0f, 0.0f, 0.0f, -0.6981317f));
        partdefinition.addOrReplaceChild("Rwing3", CubeListBuilder.create().texOffs(59, 88).addBox(0.0f, 0.0f, -3.0f, 10, 0, 10), PartPose.offsetAndRotation(6.0f, 12.6f, 0.0f, 0.0f, 0.0f, -0.0698132f));
        partdefinition.addOrReplaceChild("Lwing2", CubeListBuilder.create().texOffs(59, 11).addBox(-12.0f, 0.0f, -3.0f, 12, 0, 10), PartPose.offsetAndRotation(-6.0f, 12.6f, 0.0f, 0.0f, 0.0f, 0.6981317f));
        partdefinition.addOrReplaceChild("Lwing3", CubeListBuilder.create().texOffs(59, 22).addBox(-10.0f, 0.0f, -3.0f, 10, 0, 10), PartPose.offsetAndRotation(-6.0f, 12.6f, 0.0f, 0.0f, 0.0f, 0.0698132f));
        partdefinition.addOrReplaceChild("Ljaw", CubeListBuilder.create().texOffs(30, 70).addBox(-1.5f, 1.0f, -5.0f, 3, 1, 5), PartPose.offsetAndRotation(4.5f, 7.0f, -7.0f, 0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(30, 80).addBox(-1.5f, 1.0f, -5.0f, 3, 1, 5), PartPose.offsetAndRotation(0.0f, 7.0f, -7.0f, 0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rjaw", CubeListBuilder.create().texOffs(30, 90).addBox(-1.5f, 1.0f, -5.0f, 3, 1, 5), PartPose.offsetAndRotation(-4.5f, 7.0f, -7.0f, 0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Lpower", CubeListBuilder.create().texOffs(30, 100).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4), PartPose.offsetAndRotation(4.5f, 17.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Cpower", CubeListBuilder.create().texOffs(50, 100).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4), PartPose.offsetAndRotation(0.0f, 17.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Rpower", CubeListBuilder.create().texOffs(70, 100).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4), PartPose.offsetAndRotation(-4.5f, 17.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(ThePrincess entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.animAgeInTicks = ageInTicks;
        this.animLimbSwingAmount = limbSwingAmount;
        this.animNetHeadYaw = netHeadYaw;
        this.animHeadPitch = headPitch;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ThePrincess entity = this.animEntity;
        float ageInTicks = this.animAgeInTicks;
        float limbSwingAmount = this.animLimbSwingAmount;
        float netHeadYaw = this.animNetHeadYaw;
        float headPitch = this.animHeadPitch;
float h3;
        float d3;
                float hf = 0.0f;
        float newangle = 0.0f;
        int current_activity = entity.getActivity();
        // Activity 2 = flying. Hard flap at full amplitude (1.7 teen/adult pattern).
        // Do not scale by limbSwingAmount — hovering flight often has tiny walk-swing and looked like gliding.
        // Relaxed soft flutter only when on the ground (walking/idle).
        if (current_activity == 2 || entity.getAttacking() != 0) {
            newangle = Mth.cos(ageInTicks * 2.3f * this.wingspeed) * 3.1415927f * 0.45f;
        } else {
            newangle = Mth.cos(ageInTicks * 0.3f * this.wingspeed) * 3.1415927f * 0.04f;
        }
        this.Rwing.zRot = newangle - 0.4f;
        this.Rwing2.zRot = newangle - 0.6f;
        this.Rwing3.zRot = newangle - 0.2f;
        this.Lwing.zRot = - newangle + 0.4f;
        this.Lwing2.zRot = - newangle + 0.6f;
        this.Lwing3.zRot = - newangle + 0.2f;
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 2.0f * this.wingspeed)) * 3.1415927f * 0.25f * limbSwingAmount : 0.0f;
        if (current_activity != 2 || entity.getAttacking() != 0) {
            this.Rleg1.xRot = newangle;
            this.Lleg1.xRot = - newangle;
        } else {
            this.Rleg1.xRot = newangle = -1.0f;
            this.Lleg1.xRot = newangle;
        }
        newangle = Mth.cos((float)(ageInTicks * 0.9f * this.wingspeed)) * 3.1415927f * 0.06f;
        if (entity.isInSittingPose()) {
            newangle = 0.0f;
        }
        if (entity.getAttacking() != 0) {
            newangle = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.12f;
        }
        this.tail2.yRot = newangle;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 6.0f;
        this.tail3.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 6.0f;
        this.tail3.yRot = newangle * 1.6f;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 5.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 5.0f;
        this.tail4.yRot = newangle * 2.6f;
        this.Tail5.z = this.tail4.z + (float)Math.cos(this.tail4.yRot) * 4.0f;
        this.Tail5.x = this.tail4.x + (float)Math.sin(this.tail4.yRot) * 4.0f;
        this.Tail5.yRot = newangle * 3.6f;
        this.Tail6.z = this.Tail5.z + (float)Math.cos(this.Tail5.yRot) * 4.0f;
        this.Tail6.x = this.Tail5.x + (float)Math.sin(this.Tail5.yRot) * 4.0f;
        this.Tail6.yRot = newangle * 4.6f;
        float h2 = h3 = netHeadYaw * 2.0f / 3.0f;
        float h1 = h3;
        float d2 = d3 = headPitch * 2.0f / 3.0f;
        float d1 = d3;
        if (h1 < 0.0f) {
            h2 = h3 = h1 / 2.0f;
            d2 = d3 = d1 / 2.0f;
        } else {
            h2 = h1 = h3 / 2.0f;
            d2 = d1 = d3 / 2.0f;
        }
        this.head.yRot = (float)Math.toRadians(h2);
        this.snout.yRot = (float)Math.toRadians(h2);
        this.headfin.yRot = (float)Math.toRadians(h2);
        this.jaw.yRot = (float)Math.toRadians(h2);
        this.jaw.z = this.snout.z - (float)Math.cos(this.snout.yRot);
        this.jaw.x = this.snout.x - (float)Math.sin(this.snout.yRot);
        this.neck.yRot = (float)Math.toRadians(h2) / 2.0f;
        this.Lhead.yRot = (float)Math.toRadians(h1);
        this.Lsnout.yRot = (float)Math.toRadians(h1);
        this.Lheadfin.yRot = (float)Math.toRadians(h1);
        this.Ljaw.yRot = (float)Math.toRadians(h1);
        this.Ljaw.z = this.Lsnout.z - (float)Math.cos(this.Lsnout.yRot);
        this.Ljaw.x = this.Lsnout.x - (float)Math.sin(this.Lsnout.yRot);
        this.Lneck.yRot = (float)Math.toRadians(h1) / 2.0f;
        this.Rhead.yRot = (float)Math.toRadians(h3);
        this.Rsnout.yRot = (float)Math.toRadians(h3);
        this.Rheadfin.yRot = (float)Math.toRadians(h3);
        this.Rjaw.yRot = (float)Math.toRadians(h3);
        this.Rjaw.z = this.Rsnout.z - (float)Math.cos(this.Rsnout.yRot);
        this.Rjaw.x = this.Rsnout.x - (float)Math.sin(this.Rsnout.yRot);
        this.Rneck.yRot = (float)Math.toRadians(h3) / 2.0f;
        float Rjx = 0.0f;
        float jx = 0.0f;
        float Ljx = 0.0f;
        if (entity.getAttacking() != 0) {
            newangle = Mth.cos((float)(ageInTicks * 1.9f * this.wingspeed)) * 3.1415927f * 0.2f;
            Ljx = 0.2f + newangle;
            newangle = Mth.cos((float)(ageInTicks * 2.1f * this.wingspeed)) * 3.1415927f * 0.2f;
            Rjx = 0.2f + newangle;
            newangle = Mth.cos((float)(ageInTicks * 2.3f * this.wingspeed)) * 3.1415927f * 0.2f;
            jx = 0.2f + newangle;
        }
        this.head.xRot = (float)Math.toRadians(d2);
        this.snout.xRot = (float)Math.toRadians(d2);
        this.headfin.xRot = (float)Math.toRadians(d2);
        this.jaw.xRot = (float)Math.toRadians(d2) + jx;
        this.Lhead.xRot = (float)Math.toRadians(d1);
        this.Lsnout.xRot = (float)Math.toRadians(d1);
        this.Lheadfin.xRot = (float)Math.toRadians(d1);
        this.Ljaw.xRot = (float)Math.toRadians(d1) + Ljx;
        this.Rhead.xRot = (float)Math.toRadians(d3);
        this.Rsnout.xRot = (float)Math.toRadians(d3);
        this.Rheadfin.xRot = (float)Math.toRadians(d3);
        this.Rjaw.xRot = (float)Math.toRadians(d3) + Rjx;
        d1 = entity.getHead1Ext();
        d2 = entity.getHead2Ext();
        d3 = entity.getHead3Ext();
        this.Lneck.xRot = (float)Math.toRadians(d1);
        this.neck.xRot = (float)Math.toRadians(d2);
        this.Rneck.xRot = (float)Math.toRadians(d3);
        this.Lsnout.y = this.Ljaw.y = (this.Lhead.y = this.Lneck.y - (float)Math.cos(this.Lneck.xRot) * 7.0f);
        this.Lheadfin.y = this.Ljaw.y;
        this.Lsnout.z = this.Ljaw.z = (this.Lhead.z = this.Lneck.z - (float)Math.sin(this.Lneck.xRot) * 7.0f);
        this.Lheadfin.z = this.Ljaw.z;
        this.Lsnout.x = this.Ljaw.x = (this.Lhead.x = this.Lneck.x - (float)Math.sin(this.Lneck.yRot) * 7.0f * (float)Math.sin(this.Lneck.xRot));
        this.Lheadfin.x = this.Ljaw.x;
        this.Rsnout.y = this.Rjaw.y = (this.Rhead.y = this.Rneck.y - (float)Math.cos(this.Rneck.xRot) * 7.0f);
        this.Rheadfin.y = this.Rjaw.y;
        this.Rsnout.z = this.Rjaw.z = (this.Rhead.z = this.Rneck.z - (float)Math.sin(this.Rneck.xRot) * 7.0f);
        this.Rheadfin.z = this.Rjaw.z;
        this.Rsnout.x = this.Rjaw.x = (this.Rhead.x = this.Rneck.x - (float)Math.sin(this.Rneck.yRot) * 7.0f * (float)Math.sin(this.Rneck.xRot));
        this.Rheadfin.x = this.Rjaw.x;
        this.snout.y = this.jaw.y = (this.head.y = this.neck.y - (float)Math.cos(this.neck.xRot) * 7.0f);
        this.headfin.y = this.jaw.y;
        this.snout.z = this.jaw.z = (this.head.z = this.neck.z - (float)Math.sin(this.neck.xRot) * 7.0f);
        this.headfin.z = this.jaw.z;
        this.snout.x = this.jaw.x = (this.head.x = this.neck.x - (float)Math.sin(this.neck.yRot) * 7.0f * (float)Math.sin(this.neck.xRot));
        this.headfin.x = this.jaw.x;
        this.Lpower.xRot += 0.03f;
        if ((double)this.Lpower.xRot > 3.141592653589793) {
            this.Lpower.xRot = (float)((double)this.Lpower.xRot - 6.283185307179586);
        }
        this.Cpower.xRot += 0.04f;
        if ((double)this.Cpower.xRot > 3.141592653589793) {
            this.Cpower.xRot = (float)((double)this.Cpower.xRot - 6.283185307179586);
        }
        this.Rpower.xRot += 0.05f;
        if ((double)this.Rpower.xRot > 3.141592653589793) {
            this.Rpower.xRot = (float)((double)this.Rpower.xRot - 6.283185307179586);
        }
        this.Lpower.yRot += 0.035f;
        if ((double)this.Lpower.yRot > 3.141592653589793) {
            this.Lpower.yRot = (float)((double)this.Lpower.yRot - 6.283185307179586);
        }
        this.Cpower.yRot += 0.046f;
        if ((double)this.Cpower.yRot > 3.141592653589793) {
            this.Cpower.yRot = (float)((double)this.Cpower.yRot - 6.283185307179586);
        }
        this.Rpower.yRot += 0.065f;
        if ((double)this.Rpower.yRot > 3.141592653589793) {
            this.Rpower.yRot = (float)((double)this.Rpower.yRot - 6.283185307179586);
        }
        this.Lpower.zRot += 0.05f;
        if ((double)this.Lpower.zRot > 3.141592653589793) {
            this.Lpower.zRot = (float)((double)this.Lpower.zRot - 6.283185307179586);
        }
        this.Cpower.zRot += 0.13f;
        if ((double)this.Cpower.zRot > 3.141592653589793) {
            this.Cpower.zRot = (float)((double)this.Cpower.zRot - 6.283185307179586);
        }
        this.Rpower.zRot += 0.03f;
        if ((double)this.Rpower.zRot > 3.141592653589793) {
            this.Rpower.zRot = (float)((double)this.Rpower.zRot - 6.283185307179586);
        }
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neckbase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.snout.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lneck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lneck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lhead.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lsnout.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rneck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rneck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rhead.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rsnout.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lheadfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rheadfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Backfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Ljaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rjaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderWingMembranes(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        this.Rwing2.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Rwing3.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Lwing2.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Lwing3.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Lwing.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Rwing.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Lpower.render(poseStack, buffer, 15728880, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Cpower.render(poseStack, buffer, 15728880, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
        this.Rpower.render(poseStack, buffer, 15728880, packedOverlay, 0.75f, 0.75f, 0.75f, 0.55f);
    }
}
