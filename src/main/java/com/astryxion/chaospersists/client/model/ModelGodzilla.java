package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Godzilla;
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

public class ModelGodzilla extends EntityModel<Godzilla> {
    private final float wingspeed;
    private Godzilla animEntity;
    private float animAgeInTicks;
    private float animLimbSwingAmount;
    private float animNetHeadYaw;
    private float animHeadPitch;
    private final ModelPart LToe1;
    private final ModelPart LToe3;
    private final ModelPart LToe2;
    private final ModelPart LToe9;
    private final ModelPart LToe8;
    private final ModelPart LToe7;
    private final ModelPart LToe6;
    private final ModelPart LToe5;
    private final ModelPart LToe4;
    private final ModelPart RToe9;
    private final ModelPart RToe6;
    private final ModelPart RToe5;
    private final ModelPart RToe2;
    private final ModelPart RToe1;
    private final ModelPart RToe4;
    private final ModelPart RToe7;
    private final ModelPart RToe8;
    private final ModelPart RToe3;
    private final ModelPart LThigh;
    private final ModelPart LLowerLeg;
    private final ModelPart LUpperLeg;
    private final ModelPart TailTip;
    private final ModelPart RLegLower;
    private final ModelPart RLegUpper;
    private final ModelPart RThigh;
    private final ModelPart LowerJaw;
    private final ModelPart TailBase;
    private final ModelPart Tail2;
    private final ModelPart Tail3;
    private final ModelPart Tail4;
    private final ModelPart Tail5;
    private final ModelPart Tail6;
    private final ModelPart Tail7;
    private final ModelPart BodyBottom;
    private final ModelPart RLowerArm;
    private final ModelPart BodyCenter;
    private final ModelPart Neck;
    private final ModelPart TopJaw;
    private final ModelPart Head;
    private final ModelPart BodyTop;
    private final ModelPart RShoulder;
    private final ModelPart RThumbTip;
    private final ModelPart RUpperArm;
    private final ModelPart RHand;
    private final ModelPart RThumbBase;
    private final ModelPart R3rdFingerTip;
    private final ModelPart R3rdFingerBase;
    private final ModelPart RIndexTip;
    private final ModelPart RIndexBase;
    private final ModelPart LShoulder;
    private final ModelPart LUpperArm;
    private final ModelPart LLowerArm;
    private final ModelPart LIndexBase;
    private final ModelPart LIndexTip;
    private final ModelPart LHand;
    private final ModelPart LThumbBase;
    private final ModelPart LThumbTip;
    private final ModelPart L3rdFingerTip;
    private final ModelPart L3rdFingerBase;
    private final ModelPart Lspikes1;
    private final ModelPart Rspikes1;
    private final ModelPart Lspike2;
    private final ModelPart Rspike2;
    private final ModelPart Lspike3;
    private final ModelPart Rspike3;
    private final ModelPart Lspike4;
    private final ModelPart Rspike4;
    private final ModelPart Lspike5;
    private final ModelPart Rspike5;
    private final ModelPart Spike6;
    private final ModelPart Spikes7;
    public ModelGodzilla(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 1024, 1024).bakeRoot());
    }

    public ModelGodzilla(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.LToe1 = root.getChild("LToe1");
        this.LToe3 = root.getChild("LToe3");
        this.LToe2 = root.getChild("LToe2");
        this.LToe9 = root.getChild("LToe9");
        this.LToe8 = root.getChild("LToe8");
        this.LToe7 = root.getChild("LToe7");
        this.LToe6 = root.getChild("LToe6");
        this.LToe5 = root.getChild("LToe5");
        this.LToe4 = root.getChild("LToe4");
        this.RToe9 = root.getChild("RToe9");
        this.RToe6 = root.getChild("RToe6");
        this.RToe5 = root.getChild("RToe5");
        this.RToe2 = root.getChild("RToe2");
        this.RToe1 = root.getChild("RToe1");
        this.RToe4 = root.getChild("RToe4");
        this.RToe7 = root.getChild("RToe7");
        this.RToe8 = root.getChild("RToe8");
        this.RToe3 = root.getChild("RToe3");
        this.LThigh = root.getChild("LThigh");
        this.LLowerLeg = root.getChild("LLowerLeg");
        this.LUpperLeg = root.getChild("LUpperLeg");
        this.TailTip = root.getChild("TailTip");
        this.RLegLower = root.getChild("RLegLower");
        this.RLegUpper = root.getChild("RLegUpper");
        this.RThigh = root.getChild("RThigh");
        this.LowerJaw = root.getChild("LowerJaw");
        this.TailBase = root.getChild("TailBase");
        this.Tail2 = root.getChild("Tail2");
        this.Tail3 = root.getChild("Tail3");
        this.Tail4 = root.getChild("Tail4");
        this.Tail5 = root.getChild("Tail5");
        this.Tail6 = root.getChild("Tail6");
        this.Tail7 = root.getChild("Tail7");
        this.BodyBottom = root.getChild("BodyBottom");
        this.RLowerArm = root.getChild("RLowerArm");
        this.BodyCenter = root.getChild("BodyCenter");
        this.Neck = root.getChild("Neck");
        this.TopJaw = root.getChild("TopJaw");
        this.Head = root.getChild("Head");
        this.BodyTop = root.getChild("BodyTop");
        this.RShoulder = root.getChild("RShoulder");
        this.RThumbTip = root.getChild("RThumbTip");
        this.RUpperArm = root.getChild("RUpperArm");
        this.RHand = root.getChild("RHand");
        this.RThumbBase = root.getChild("RThumbBase");
        this.R3rdFingerTip = root.getChild("R3rdFingerTip");
        this.R3rdFingerBase = root.getChild("R3rdFingerBase");
        this.RIndexTip = root.getChild("RIndexTip");
        this.RIndexBase = root.getChild("RIndexBase");
        this.LShoulder = root.getChild("LShoulder");
        this.LUpperArm = root.getChild("LUpperArm");
        this.LLowerArm = root.getChild("LLowerArm");
        this.LIndexBase = root.getChild("LIndexBase");
        this.LIndexTip = root.getChild("LIndexTip");
        this.LHand = root.getChild("LHand");
        this.LThumbBase = root.getChild("LThumbBase");
        this.LThumbTip = root.getChild("LThumbTip");
        this.L3rdFingerTip = root.getChild("L3rdFingerTip");
        this.L3rdFingerBase = root.getChild("L3rdFingerBase");
        this.Lspikes1 = root.getChild("Lspikes1");
        this.Rspikes1 = root.getChild("Rspikes1");
        this.Lspike2 = root.getChild("Lspike2");
        this.Rspike2 = root.getChild("Rspike2");
        this.Lspike3 = root.getChild("Lspike3");
        this.Rspike3 = root.getChild("Rspike3");
        this.Lspike4 = root.getChild("Lspike4");
        this.Rspike4 = root.getChild("Rspike4");
        this.Lspike5 = root.getChild("Lspike5");
        this.Rspike5 = root.getChild("Rspike5");
        this.Spike6 = root.getChild("Spike6");
        this.Spikes7 = root.getChild("Spikes7");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("LToe1", CubeListBuilder.create().texOffs(45, 1002).addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, 0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("LToe3", CubeListBuilder.create().texOffs(0, 955).addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, 0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("LToe2", CubeListBuilder.create().texOffs(0, 1002).addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, 0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("LToe9", CubeListBuilder.create().texOffs(0, 955).addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, -0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("LToe8", CubeListBuilder.create().texOffs(0, 1002).addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, -0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("LToe7", CubeListBuilder.create().texOffs(45, 1002).addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, -0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("LToe6", CubeListBuilder.create().texOffs(92, 955).addBox(-8.0f, -8.0f, -26.0f, 16, 16, 36), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("LToe5", CubeListBuilder.create().texOffs(0, 1002).addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("LToe4", CubeListBuilder.create().texOffs(45, 1002).addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6), PartPose.offsetAndRotation(54.0f, 16.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RToe9", CubeListBuilder.create().texOffs(0, 955).addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, 0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("RToe6", CubeListBuilder.create().texOffs(92, 955).addBox(-8.0f, -8.0f, -26.0f, 16, 16, 36), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RToe5", CubeListBuilder.create().texOffs(0, 1002).addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RToe2", CubeListBuilder.create().texOffs(0, 1002).addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, -0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("RToe1", CubeListBuilder.create().texOffs(45, 1002).addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, -0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("RToe4", CubeListBuilder.create().texOffs(45, 1002).addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RToe7", CubeListBuilder.create().texOffs(45, 1002).addBox(-5.0f, -2.0f, -40.0f, 10, 10, 6), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, 0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("RToe8", CubeListBuilder.create().texOffs(0, 1002).addBox(-7.0f, -6.0f, -34.0f, 14, 14, 8), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, 0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("RToe3", CubeListBuilder.create().texOffs(0, 955).addBox(-8.0f, -8.0f, -26.0f, 16, 16, 30), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.0f, -0.7853982f, 0.0f));
        partdefinition.addOrReplaceChild("LThigh", CubeListBuilder.create().texOffs(192, 350).addBox(0.0f, -14.0f, -21.0f, 28, 28, 42), PartPose.offsetAndRotation(40.0f, -91.0f, 2.0f, -0.5585054f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("LLowerLeg", CubeListBuilder.create().texOffs(202, 556).addBox(-15.0f, -62.0f, -15.0f, 30, 62, 30), PartPose.offsetAndRotation(54.0f, 14.0f, 6.0f, 0.1745329f, -0.1308997f, 0.0f));
        partdefinition.addOrReplaceChild("LUpperLeg", CubeListBuilder.create().texOffs(152, 420).addBox(-16.0f, -52.0f, -16.0f, 32, 52, 32), PartPose.offsetAndRotation(56.0f, -36.0f, -5.0f, -0.1745329f, -0.3926991f, -0.0872665f));
        partdefinition.addOrReplaceChild("TailTip", CubeListBuilder.create().texOffs(0, 694).addBox(-6.0f, 0.0f, -5.0f, 12, 21, 10), PartPose.offsetAndRotation(0.0f, 18.0f, 203.0f, 1.53589f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RLegLower", CubeListBuilder.create().texOffs(200, 646).addBox(-15.0f, -62.0f, -15.0f, 30, 62, 30), PartPose.offsetAndRotation(-54.0f, 16.0f, 6.0f, 0.1745329f, 0.1308997f, 0.0f));
        partdefinition.addOrReplaceChild("RLegUpper", CubeListBuilder.create().texOffs(152, 420).addBox(-16.0f, -52.0f, -16.0f, 32, 52, 32), PartPose.offsetAndRotation(-56.0f, -36.0f, -5.0f, -0.1745329f, 0.3926991f, 0.0872665f));
        partdefinition.addOrReplaceChild("RThigh", CubeListBuilder.create().texOffs(192, 350).addBox(-28.0f, -14.0f, -21.0f, 28, 28, 42), PartPose.offsetAndRotation(-40.0f, -91.0f, 2.0f, -0.5585054f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("LowerJaw", CubeListBuilder.create().texOffs(272, 0).addBox(-13.0f, -5.0f, -50.0f, 26, 11, 50), PartPose.offsetAndRotation(0.0f, -142.0f, -109.0f, 0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("TailBase", CubeListBuilder.create().texOffs(0, 240).addBox(-32.0f, 0.0f, -29.0f, 64, 40, 58), PartPose.offsetAndRotation(0.0f, -73.0f, 26.0f, 0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(0, 338).addBox(-25.0f, 0.0f, -23.0f, 50, 36, 46), PartPose.offsetAndRotation(0.0f, -48.0f, 48.0f, 0.6981317f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(0, 420).addBox(-20.0f, 0.0f, -18.0f, 40, 36, 36), PartPose.offsetAndRotation(0.0f, -24.0f, 66.0f, 0.8726646f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(0, 492).addBox(-16.0f, 0.0f, -14.0f, 32, 42, 28), PartPose.offsetAndRotation(0.0f, -3.0f, 87.0f, 1.134464f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail5", CubeListBuilder.create().texOffs(0, 556).addBox(-13.0f, 0.0f, -11.0f, 26, 42, 22), PartPose.offsetAndRotation(0.0f, 12.0f, 116.0f, 1.53589f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail6", CubeListBuilder.create().texOffs(0, 614).addBox(-10.0f, 0.0f, -9.0f, 20, 32, 18), PartPose.offsetAndRotation(0.0f, 14.0f, 154.0f, 1.53589f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail7", CubeListBuilder.create().texOffs(0, 658).addBox(-8.0f, 0.0f, -7.0f, 16, 22, 14), PartPose.offsetAndRotation(0.0f, 16.0f, 185.0f, 1.53589f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BodyBottom", CubeListBuilder.create().texOffs(0, 104).addBox(-40.0f, 0.0f, -36.0f, 80, 64, 72), PartPose.offsetAndRotation(0.0f, -112.0f, -20.0f, 0.8726646f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RLowerArm", CubeListBuilder.create().texOffs(245, 240).addBox(-48.0f, -11.0f, -11.0f, 48, 22, 22), PartPose.offsetAndRotation(-80.0f, -115.0f, -61.0f, 0.0f, -0.7853982f, -0.2617994f));
        partdefinition.addOrReplaceChild("BodyCenter", CubeListBuilder.create().texOffs(0, 0).addBox(-36.0f, -32.0f, -32.0f, 72, 40, 64), PartPose.offsetAndRotation(0.0f, -112.0f, -20.0f, 1.134464f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 720).addBox(-23.0f, -23.0f, -32.0f, 46, 46, 32), PartPose.offsetAndRotation(0.0f, -144.0f, -71.0f, -0.0698132f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("TopJaw", CubeListBuilder.create().texOffs(0, 892).addBox(-14.0f, -8.0f, -73.0f, 28, 26, 33), PartPose.offsetAndRotation(0.0f, -156.0f, -98.0f, 0.0872665f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 808).addBox(-17.0f, -18.0f, -40.0f, 34, 36, 40), PartPose.offsetAndRotation(0.0f, -156.0f, -98.0f, 0.0872665f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BodyTop", CubeListBuilder.create().texOffs(0, 0).addBox(-36.0f, -32.0f, -32.0f, 72, 40, 64), PartPose.offsetAndRotation(0.0f, -126.0f, -50.0f, 1.308997f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RShoulder", CubeListBuilder.create().texOffs(304, 96).addBox(-16.0f, -32.0f, -32.0f, 16, 42, 46), PartPose.offsetAndRotation(-36.0f, -130.0f, -42.0f, 1.308997f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RThumbTip", CubeListBuilder.create().texOffs(422, 18).addBox(5.0f, 1.0f, -43.0f, 8, 8, 12), PartPose.offsetAndRotation(-115.0f, -100.0f, -99.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("RUpperArm", CubeListBuilder.create().texOffs(304, 184).addBox(-54.0f, -13.0f, -13.0f, 54, 26, 26), PartPose.offsetAndRotation(-38.0f, -130.0f, -52.0f, 0.0f, -0.2617994f, -0.3490659f));
        partdefinition.addOrReplaceChild("RHand", CubeListBuilder.create().texOffs(245, 292).addBox(-13.0f, -13.0f, -13.0f, 26, 26, 26), PartPose.offsetAndRotation(-115.0f, -100.0f, -99.0f, -1.071467f, 2.007129f, 0.1745329f));
        partdefinition.addOrReplaceChild("RThumbBase", CubeListBuilder.create().texOffs(424, 57).addBox(2.0f, 1.0f, -32.0f, 8, 8, 20), PartPose.offsetAndRotation(-115.0f, -100.0f, -99.0f, 0.0f, -0.1047198f, 0.0f));
        partdefinition.addOrReplaceChild("R3rdFingerTip", CubeListBuilder.create().texOffs(422, 18).addBox(-10.0f, 0.0f, -41.0f, 8, 8, 12), PartPose.offsetAndRotation(-115.0f, -100.0f, -99.0f, 0.0f, 0.6806784f, 0.0f));
        partdefinition.addOrReplaceChild("R3rdFingerBase", CubeListBuilder.create().texOffs(424, 57).addBox(-11.0f, -3.0f, -30.0f, 8, 8, 20), PartPose.offsetAndRotation(-115.0f, -100.0f, -99.0f, 0.122173f, 0.6457718f, 0.0f));
        partdefinition.addOrReplaceChild("RIndexTip", CubeListBuilder.create().texOffs(422, 18).addBox(-4.0f, -12.0f, -43.0f, 8, 8, 12), PartPose.offsetAndRotation(-115.0f, -100.0f, -99.0f, -0.2094395f, 0.1745329f, 0.0f));
        partdefinition.addOrReplaceChild("RIndexBase", CubeListBuilder.create().texOffs(424, 57).addBox(-4.0f, -9.0f, -34.0f, 8, 8, 20), PartPose.offsetAndRotation(-115.0f, -100.0f, -99.0f, -0.2792527f, 0.1570796f, 0.0f));
        partdefinition.addOrReplaceChild("LShoulder", CubeListBuilder.create().texOffs(304, 96).addBox(0.0f, -32.0f, -32.0f, 16, 42, 46), PartPose.offsetAndRotation(36.0f, -130.0f, -42.0f, 1.308997f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("LUpperArm", CubeListBuilder.create().texOffs(304, 184).addBox(0.0f, -13.0f, -13.0f, 54, 26, 26), PartPose.offsetAndRotation(38.0f, -130.0f, -52.0f, 0.0f, 0.296706f, 0.3490659f));
        partdefinition.addOrReplaceChild("LLowerArm", CubeListBuilder.create().texOffs(245, 240).addBox(0.0f, -11.0f, -11.0f, 48, 22, 22), PartPose.offsetAndRotation(80.0f, -115.0f, -61.0f, 0.0f, 0.7853982f, 0.2617994f));
        partdefinition.addOrReplaceChild("LIndexBase", CubeListBuilder.create().texOffs(424, 57).addBox(-4.0f, -13.0f, -32.0f, 8, 8, 20), PartPose.offsetAndRotation(115.0f, -100.0f, -99.0f, -0.1570796f, -0.1396263f, 0.0f));
        partdefinition.addOrReplaceChild("LIndexTip", CubeListBuilder.create().texOffs(422, 18).addBox(-1.0f, -18.0f, -41.0f, 8, 8, 12), PartPose.offsetAndRotation(115.0f, -100.0f, -99.0f, 0.0f, -0.0349066f, 0.0f));
        partdefinition.addOrReplaceChild("LHand", CubeListBuilder.create().texOffs(245, 292).addBox(-13.0f, -13.0f, -13.0f, 26, 26, 26), PartPose.offsetAndRotation(115.0f, -100.0f, -99.0f, 0.9599311f, 1.308997f, 0.1745329f));
        partdefinition.addOrReplaceChild("LThumbBase", CubeListBuilder.create().texOffs(424, 57).addBox(-8.0f, -2.0f, -32.0f, 8, 8, 20), PartPose.offsetAndRotation(115.0f, -100.0f, -98.0f, 0.1396263f, 0.2617994f, 0.0f));
        partdefinition.addOrReplaceChild("LThumbTip", CubeListBuilder.create().texOffs(422, 18).addBox(-12.0f, 2.0f, -40.0f, 8, 8, 12), PartPose.offsetAndRotation(115.0f, -100.0f, -99.0f, 0.0f, 0.1396263f, 0.0f));
        partdefinition.addOrReplaceChild("L3rdFingerTip", CubeListBuilder.create().texOffs(422, 18).addBox(9.0f, 2.0f, -42.0f, 8, 8, 12), PartPose.offsetAndRotation(115.0f, -100.0f, -99.0f, 0.0349066f, -0.3316126f, 0.0f));
        partdefinition.addOrReplaceChild("L3rdFingerBase", CubeListBuilder.create().texOffs(424, 57).addBox(4.0f, -5.0f, -33.0f, 8, 8, 20), PartPose.offsetAndRotation(115.0f, -100.0f, -99.0f, 0.2617994f, -0.4712389f, 0.0f));
        partdefinition.addOrReplaceChild("Lspikes1", CubeListBuilder.create().texOffs(500, 0).addBox(0.0f, -10.0f, 0.0f, 0, 10, 11), PartPose.offsetAndRotation(5.0f, -168.0f, -86.0f, -0.0872665f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Rspikes1", CubeListBuilder.create().texOffs(500, 0).addBox(0.0f, -10.0f, 0.0f, 0, 10, 11), PartPose.offsetAndRotation(-5.0f, -168.0f, -86.0f, -0.0872665f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Lspike2", CubeListBuilder.create().texOffs(500, 30).addBox(0.0f, -25.0f, 0.0f, 0, 25, 21), PartPose.offsetAndRotation(10.0f, -162.0f, -63.0f, -0.2617994f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Rspike2", CubeListBuilder.create().texOffs(500, 30).addBox(0.0f, -25.0f, 0.0f, 0, 25, 21), PartPose.offsetAndRotation(-10.0f, -162.0f, -63.0f, -0.2617994f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Lspike3", CubeListBuilder.create().texOffs(500, 80).addBox(0.0f, -45.0f, 0.0f, 0, 45, 34), PartPose.offsetAndRotation(14.0f, -153.0f, -32.0f, -0.4363323f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Rspike3", CubeListBuilder.create().texOffs(500, 80).addBox(0.0f, -45.0f, 0.0f, 0, 45, 34), PartPose.offsetAndRotation(-14.0f, -153.0f, -32.0f, -0.4363323f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Lspike4", CubeListBuilder.create().texOffs(500, 165).addBox(0.0f, -50.0f, 0.0f, 0, 50, 36), PartPose.offsetAndRotation(18.0f, -131.0f, 13.0f, -0.715585f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Rspike4", CubeListBuilder.create().texOffs(500, 165).addBox(0.0f, -50.0f, 0.0f, 0, 50, 36), PartPose.offsetAndRotation(-18.0f, -131.0f, 13.0f, -0.715585f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Lspike5", CubeListBuilder.create().texOffs(500, 255).addBox(12.0f, -67.0f, 5.0f, 0, 39, 27), PartPose.offsetAndRotation(0.0f, -73.0f, 26.0f, -0.7853982f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Rspike5", CubeListBuilder.create().texOffs(500, 255).addBox(-12.0f, -67.0f, 5.0f, 0, 39, 27), PartPose.offsetAndRotation(0.0f, -73.0f, 26.0f, -0.7853982f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Spike6", CubeListBuilder.create().texOffs(500, 325).addBox(0.0f, -48.0f, 11.0f, 0, 25, 21), PartPose.offsetAndRotation(0.0f, -48.0f, 48.0f, -0.8901179f, 0.0f, -0.0174533f));
        partdefinition.addOrReplaceChild("Spikes7", CubeListBuilder.create().texOffs(500, 376).addBox(0.0f, -29.0f, 20.0f, 0, 10, 11), PartPose.offsetAndRotation(0.0f, -24.0f, 66.0f, -0.7504916f, 0.0f, -0.0174533f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(Godzilla entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.animAgeInTicks = ageInTicks;
        this.animLimbSwingAmount = limbSwingAmount;
        this.animNetHeadYaw = netHeadYaw;
        this.animHeadPitch = headPitch;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Godzilla entity = this.animEntity;
        float ageInTicks = this.animAgeInTicks;
        float limbSwingAmount = this.animLimbSwingAmount;
        float netHeadYaw = this.animNetHeadYaw;
        float headPitch = this.animHeadPitch;
        float newangle = 0.0f;
        float newangle2 = 0.0f;
        float pscale = 1.0f;
        float pi4 = 0.7853982f;
        float clawZ = 6.0f;
        float clawY = 16.0f;
        float clawZamp = 35.0f * pscale;
        float clawYamp = 18.0f * pscale;
        float spikeamp = 1.0f;
        float spikefreq = 1.0f;
        RenderInfo r = entity.getRenderInfo();
        float t1 = 0.0f;
        float t2 = 0.0f;
        if ((double)limbSwingAmount > 0.001) {
            newangle = Mth.cos((float)(ageInTicks * 0.75f * this.wingspeed / pscale));
            newangle2 = Mth.cos((float)(ageInTicks * 0.75f * this.wingspeed / pscale + pi4));
            t1 = Mth.sin((float)(ageInTicks * 0.75f * this.wingspeed / pscale));
        } else {
            newangle2 = 0.0f;
            newangle = 0.0f;
            t1 = 0.0f;
            t2 = 0.0f;
        }
        if (t1 > 0.0f) {
            t2 = t1 * clawYamp * limbSwingAmount;
            this.LToe1.y = clawY - t2;
        } else {
            this.LToe1.y = clawY;
        }
        this.LToe8.z = this.LToe9.z = (this.LToe1.z = clawZ + clawZamp * newangle * limbSwingAmount);
        this.LToe7.z = this.LToe9.z;
        this.LToe6.z = this.LToe9.z;
        this.LToe5.z = this.LToe9.z;
        this.LToe4.z = this.LToe9.z;
        this.LToe3.z = this.LToe9.z;
        this.LToe2.z = this.LToe9.z;
        this.LToe8.y = this.LToe9.y = this.LToe1.y;
        this.LToe7.y = this.LToe9.y;
        this.LToe6.y = this.LToe9.y;
        this.LToe5.y = this.LToe9.y;
        this.LToe4.y = this.LToe9.y;
        this.LToe3.y = this.LToe9.y;
        this.LToe2.y = this.LToe9.y;
        this.LLowerLeg.z = this.LToe1.z;
        this.LLowerLeg.y = this.LToe1.y;
        this.LLowerLeg.xRot = 0.22f + newangle * 3.1415927f * 0.09f * limbSwingAmount;
        this.LUpperLeg.xRot = -0.17f + newangle2 * 3.1415927f * 0.15f * limbSwingAmount;
        this.LUpperLeg.y = this.LLowerLeg.y - (float)Math.cos(this.LLowerLeg.xRot) * 55.0f;
        this.LUpperLeg.z = this.LLowerLeg.z - (float)Math.sin(this.LLowerLeg.xRot) * 55.0f;
        this.LThigh.xRot = -0.558f + newangle2 * 3.1415927f * 0.1f * limbSwingAmount;
        this.LThigh.z = 2.0f + clawZamp * newangle * limbSwingAmount / 4.0f;
        t1 = 0.0f;
        t2 = 0.0f;
        if ((double)limbSwingAmount > 0.001) {
            newangle = Mth.cos((float)(ageInTicks * 0.75f * this.wingspeed / pscale + pi4 * 4.0f));
            newangle2 = Mth.cos((float)(ageInTicks * 0.75f * this.wingspeed / pscale + pi4 * 5.0f));
            t1 = Mth.sin((float)(ageInTicks * 0.75f * this.wingspeed / pscale + pi4 * 4.0f));
        } else {
            newangle = 0.0f;
            t1 = 0.0f;
            t2 = 0.0f;
        }
        if (t1 > 0.0f) {
            t2 = t1 * clawYamp * limbSwingAmount;
            this.RToe1.y = clawY - t2;
        } else {
            this.RToe1.y = clawY;
        }
        this.RToe8.z = this.RToe9.z = (this.RToe1.z = clawZ + clawZamp * newangle * limbSwingAmount);
        this.RToe7.z = this.RToe9.z;
        this.RToe6.z = this.RToe9.z;
        this.RToe5.z = this.RToe9.z;
        this.RToe4.z = this.RToe9.z;
        this.RToe3.z = this.RToe9.z;
        this.RToe2.z = this.RToe9.z;
        this.RToe8.y = this.RToe9.y = this.RToe1.y;
        this.RToe7.y = this.RToe9.y;
        this.RToe6.y = this.RToe9.y;
        this.RToe5.y = this.RToe9.y;
        this.RToe4.y = this.RToe9.y;
        this.RToe3.y = this.RToe9.y;
        this.RToe2.y = this.RToe9.y;
        this.RLegLower.z = this.RToe1.z;
        this.RLegLower.y = this.RToe1.y;
        this.RLegLower.xRot = 0.22f + newangle * 3.1415927f * 0.09f * limbSwingAmount;
        this.RLegUpper.xRot = -0.17f + newangle2 * 3.1415927f * 0.15f * limbSwingAmount;
        this.RLegUpper.y = this.RLegLower.y - (float)Math.cos(this.RLegLower.xRot) * 55.0f;
        this.RLegUpper.z = this.RLegLower.z - (float)Math.sin(this.RLegLower.xRot) * 55.0f;
        this.RThigh.xRot = -0.558f + newangle2 * 3.1415927f * 0.1f * limbSwingAmount;
        this.RThigh.z = 2.0f + clawZamp * newangle * limbSwingAmount / 4.0f;
        this.LToe1.xRot = 0.0f;
        this.LToe9.xRot = 0.0f;
        this.LToe8.xRot = 0.0f;
        this.LToe7.xRot = 0.0f;
        this.LToe6.xRot = 0.0f;
        this.LToe5.xRot = 0.0f;
        this.LToe4.xRot = 0.0f;
        this.LToe3.xRot = 0.0f;
        this.LToe2.xRot = 0.0f;
        this.RToe1.xRot = 0.0f;
        this.RToe9.xRot = 0.0f;
        this.RToe8.xRot = 0.0f;
        this.RToe7.xRot = 0.0f;
        this.RToe6.xRot = 0.0f;
        this.RToe5.xRot = 0.0f;
        this.RToe4.xRot = 0.0f;
        this.RToe3.xRot = 0.0f;
        this.RToe2.xRot = 0.0f;
        newangle = entity.getAttacking() != 0 ? Mth.cos((float)(ageInTicks * this.wingspeed * 1.75f)) * 3.1415927f * 0.2f : Mth.cos((float)(ageInTicks * this.wingspeed * 0.75f)) * 3.1415927f * 0.05f;
        this.doTail(newangle);
        this.Head.yRot = newangle = (float)Math.toRadians(netHeadYaw) * 0.55f;
        this.TopJaw.yRot = newangle;
        this.LowerJaw.yRot = newangle;
        this.LowerJaw.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 11.0f;
        this.LowerJaw.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 11.0f;
        this.TopJaw.xRot = this.Head.xRot = (float)Math.toRadians(headPitch);
        newangle = Mth.cos((float)(ageInTicks * this.wingspeed * 1.5f)) * 3.1415927f * 0.12f;
        float newrf1 = ageInTicks * 1.5f * this.wingspeed % 6.2831855f;
        newrf1 = Math.abs(newrf1);
        if (newrf1 < r.rf2) {
            r.ri2 = 0;
            if (entity.getAttacking() == 0) {
                if (entity.getRandom().nextInt(20) == 1) {
                    r.ri2 |= 1;
                }
            } else if (entity.getRandom().nextInt(2) == 1) {
                r.ri2 |= 1;
            }
        }
        r.rf2 = newrf1;
        if ((r.ri2 & 1) == 0) {
            newangle = 0.0f;
        }
        this.LowerJaw.xRot = 0.52f + newangle + this.TopJaw.xRot;
        newangle = newangle2 = Mth.sin((float)(ageInTicks * this.wingspeed * 1.75f)) * 3.1415927f * 0.16f;
        newrf1 = ageInTicks * 1.75f * this.wingspeed % 6.2831855f;
        if ((newrf1 = Math.abs(newrf1)) < r.rf1) {
            r.ri1 = 0;
            if (entity.getAttacking() == 0) {
                if (entity.getRandom().nextInt(20) == 1) {
                    r.ri1 |= 1;
                }
                if (entity.getRandom().nextInt(20) == 1) {
                    r.ri1 |= 2;
                }
            } else {
                if (entity.getRandom().nextInt(2) == 1) {
                    r.ri1 |= 1;
                }
                if (entity.getRandom().nextInt(2) == 1) {
                    r.ri1 |= 2;
                }
            }
        }
        r.rf1 = newrf1;
        if ((r.ri1 & 1) == 0) {
            newangle = 0.0f;
        }
        if ((r.ri1 & 2) == 0) {
            newangle2 = 0.0f;
        }
        this.LUpperArm.yRot = 0.65f + newangle;
        this.LLowerArm.yRot = 0.78f + newangle * 3.0f / 2.0f;
        this.LLowerArm.z = this.LUpperArm.z - (float)Math.sin(this.LUpperArm.yRot) * 50.0f;
        this.LLowerArm.x = this.LUpperArm.x + (float)Math.cos(this.LUpperArm.yRot) * 50.0f;
        this.LLowerArm.y = this.LUpperArm.y - (float)Math.sin(this.LUpperArm.yRot) * 10.0f + 18.0f;
        this.LHand.z = this.LLowerArm.z - (float)Math.sin(this.LLowerArm.yRot) * 45.0f;
        this.LHand.x = this.LLowerArm.x + (float)Math.cos(this.LLowerArm.yRot) * 45.0f;
        this.LHand.y = this.LLowerArm.y - (float)Math.sin(this.LLowerArm.yRot) * 10.0f + 15.0f;
        this.LThumbBase.z = this.L3rdFingerBase.z = this.LHand.z;
        this.LIndexBase.z = this.L3rdFingerBase.z;
        this.LThumbTip.z = this.L3rdFingerTip.z = this.LHand.z;
        this.LIndexTip.z = this.L3rdFingerTip.z;
        this.LThumbBase.y = this.L3rdFingerBase.y = this.LHand.y;
        this.LIndexBase.y = this.L3rdFingerBase.y;
        this.LThumbTip.y = this.L3rdFingerTip.y = this.LHand.y;
        this.LIndexTip.y = this.L3rdFingerTip.y;
        this.LThumbBase.x = this.L3rdFingerBase.x = this.LHand.x;
        this.LIndexBase.x = this.L3rdFingerBase.x;
        this.LThumbTip.x = this.L3rdFingerTip.x = this.LHand.x;
        this.LIndexTip.x = this.L3rdFingerTip.x;
        this.LHand.yRot = 1.308f + newangle * 2.0f;
        this.LIndexBase.yRot = -0.139f + newangle * 2.0f;
        this.LIndexTip.yRot = -0.034f + newangle * 2.0f;
        this.LThumbBase.yRot = 0.261f + newangle;
        this.LThumbTip.yRot = 0.139f + newangle;
        this.L3rdFingerBase.yRot = -0.471f + newangle * 3.0f;
        this.L3rdFingerTip.yRot = -0.331f + newangle * 3.0f;
        this.RUpperArm.yRot = -0.65f - newangle2;
        this.RLowerArm.yRot = -0.78f - newangle2 * 3.0f / 2.0f;
        this.RLowerArm.z = this.RUpperArm.z + (float)Math.sin(this.RUpperArm.yRot) * 50.0f;
        this.RLowerArm.x = this.RUpperArm.x - (float)Math.cos(this.RUpperArm.yRot) * 50.0f;
        this.RLowerArm.y = this.RUpperArm.y + (float)Math.sin(this.RUpperArm.yRot) * 10.0f + 18.0f;
        this.RHand.z = this.RLowerArm.z + (float)Math.sin(this.RLowerArm.yRot) * 45.0f;
        this.RHand.x = this.RLowerArm.x - (float)Math.cos(this.RLowerArm.yRot) * 45.0f;
        this.RHand.y = this.RLowerArm.y + (float)Math.sin(this.RLowerArm.yRot) * 10.0f + 15.0f;
        this.RThumbBase.z = this.R3rdFingerBase.z = this.RHand.z;
        this.RIndexBase.z = this.R3rdFingerBase.z;
        this.RThumbTip.z = this.R3rdFingerTip.z = this.RHand.z;
        this.RIndexTip.z = this.R3rdFingerTip.z;
        this.RThumbBase.y = this.R3rdFingerBase.y = this.RHand.y;
        this.RIndexBase.y = this.R3rdFingerBase.y;
        this.RThumbTip.y = this.R3rdFingerTip.y = this.RHand.y;
        this.RIndexTip.y = this.R3rdFingerTip.y;
        this.RThumbBase.x = this.R3rdFingerBase.x = this.RHand.x;
        this.RIndexBase.x = this.R3rdFingerBase.x;
        this.RThumbTip.x = this.R3rdFingerTip.x = this.RHand.x;
        this.RIndexTip.x = this.R3rdFingerTip.x;
        this.RHand.yRot = -2.0f - newangle2 * 2.0f;
        this.RIndexBase.yRot = 0.157f - newangle2 * 2.0f;
        this.RIndexTip.yRot = 0.174f - newangle2 * 2.0f;
        this.RThumbBase.yRot = -0.104f - newangle2;
        this.RThumbTip.yRot = 0.001f - newangle2;
        this.R3rdFingerTip.yRot = 0.68f - newangle2 * 3.0f;
        this.R3rdFingerBase.yRot = 0.645f - newangle2 * 3.0f;
        entity.setRenderInfo(r);
        this.LToe1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LToe4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RToe3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LThigh.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LLowerLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LUpperLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RLegLower.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RLegUpper.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RThigh.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LowerJaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyBottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RLowerArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyCenter.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TopJaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BodyTop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RShoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RThumbTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RUpperArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RHand.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RThumbBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.R3rdFingerTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.R3rdFingerBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RIndexTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RIndexBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LShoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LUpperArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LLowerArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LIndexBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LIndexTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LHand.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LThumbBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LThumbTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.L3rdFingerTip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.L3rdFingerBase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspikes1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspikes1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lspike5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rspike5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spikes7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

private void doTail(float angle) {
        this.Lspike5.yRot = this.Rspike5.yRot = (this.TailBase.yRot = angle * 0.25f);
        this.Tail2.yRot = angle * 0.5f;
        this.Tail2.z = this.TailBase.z + (float)Math.cos(this.TailBase.yRot) * 25.0f;
        this.Tail2.x = this.TailBase.x + (float)Math.sin(this.TailBase.yRot) * 25.0f;
        this.Spike6.yRot = this.Tail2.yRot;
        this.Spike6.z = this.Tail2.z;
        this.Spike6.x = this.Tail2.x;
        this.Tail3.yRot = angle * 0.75f;
        this.Tail3.z = this.Tail2.z + (float)Math.cos(this.Tail2.yRot) * 20.0f;
        this.Tail3.x = this.Tail2.x + (float)Math.sin(this.Tail2.yRot) * 20.0f;
        this.Spikes7.yRot = this.Tail3.yRot;
        this.Spikes7.z = this.Tail3.z;
        this.Spikes7.x = this.Tail3.x;
        this.Tail4.yRot = angle * 1.25f;
        this.Tail4.z = this.Tail3.z + (float)Math.cos(this.Tail3.yRot) * 20.0f;
        this.Tail4.x = this.Tail3.x + (float)Math.sin(this.Tail3.yRot) * 20.0f;
        this.Tail5.yRot = angle * 1.5f;
        this.Tail5.z = this.Tail4.z + (float)Math.cos(this.Tail4.yRot) * 25.0f;
        this.Tail5.x = this.Tail4.x + (float)Math.sin(this.Tail4.yRot) * 25.0f;
        this.Tail6.yRot = angle * 1.75f;
        this.Tail6.z = this.Tail5.z + (float)Math.cos(this.Tail5.yRot) * 27.0f;
        this.Tail6.x = this.Tail5.x + (float)Math.sin(this.Tail5.yRot) * 27.0f;
        this.Tail7.yRot = angle * 2.0f;
        this.Tail7.z = this.Tail6.z + (float)Math.cos(this.Tail6.yRot) * 28.0f;
        this.Tail7.x = this.Tail6.x + (float)Math.sin(this.Tail6.yRot) * 28.0f;
        this.TailTip.yRot = angle * 2.25f;
        this.TailTip.z = this.Tail7.z + (float)Math.cos(this.Tail7.yRot) * 18.0f;
        this.TailTip.x = this.Tail7.x + (float)Math.sin(this.Tail7.yRot) * 18.0f;
    }
}
