package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Lizard;
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

public class ModelLizard extends EntityModel<Lizard> {
    private final float wingspeed;
    private final ModelPart body_back;
    private final ModelPart top_back_left_leg;
    private final ModelPart tail_tip;
    private final ModelPart body_front;
    private final ModelPart tail_base1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart neck;
    private final ModelPart top_front_left_leg;
    private final ModelPart top_back_right_leg;
    private final ModelPart bottom_back_right_leg;
    private final ModelPart top_front_right_leg;
    private final ModelPart bottom_back_left_leg;
    private final ModelPart bottom_front_right_leg;
    private final ModelPart bottom_front_left_leg;
    private final ModelPart body_center;
    private final ModelPart toe7;
    private final ModelPart toe6;
    private final ModelPart back_left_foot;
    private final ModelPart toe4;
    private final ModelPart toe5;
    private final ModelPart back_right_foot;
    private final ModelPart toe8;
    private final ModelPart toe1;
    private final ModelPart front_left_foot;
    private final ModelPart toe3;
    private final ModelPart toe2;
    private final ModelPart front_right_foot;
    private final ModelPart fin_ridge7;
    private final ModelPart fin_ridge6;
    private final ModelPart fin_ridge5;
    private final ModelPart fin_ridge4;
    private final ModelPart fin_ridge3;
    private final ModelPart fin_ridge2;
    private final ModelPart fin_ridge1;
    private final ModelPart fin10;
    private final ModelPart fin9;
    private final ModelPart fin8;
    private final ModelPart fin7;
    private final ModelPart fin6;
    private final ModelPart fin5;
    private final ModelPart fin3;
    private final ModelPart fin2;
    private final ModelPart tooth11;
    private final ModelPart tooth10;
    private final ModelPart tooth8;
    private final ModelPart tooth7;
    private final ModelPart tooth6;
    private final ModelPart tooth5;
    private final ModelPart tooth4;
    private final ModelPart tooth3;
    private final ModelPart tooth2;
    private final ModelPart center_right_nose;
    private final ModelPart center_left_nose;
    private final ModelPart tooth1;
    private final ModelPart bottom_nose;
    private final ModelPart top_nose;
    private final ModelPart jaw_top;
    private final ModelPart center_middle_nose;
    private final ModelPart right_eye;
    private final ModelPart left_eye;
    private final ModelPart tooth16;
    private final ModelPart tooth15;
    private final ModelPart tooth14;
    private final ModelPart tooth13;
    private final ModelPart tooth12;
    private final ModelPart tooth9;
    private final ModelPart bottom_jaw;
    private final ModelPart hat1;
    private final ModelPart hat2;

    public ModelLizard(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelLizard(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body_back = root.getChild("body_back");
        this.top_back_left_leg = root.getChild("top_back_left_leg");
        this.tail_tip = root.getChild("tail_tip");
        this.body_front = root.getChild("body_front");
        this.tail_base1 = root.getChild("tail_base1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.neck = root.getChild("neck");
        this.top_front_left_leg = root.getChild("top_front_left_leg");
        this.top_back_right_leg = root.getChild("top_back_right_leg");
        this.bottom_back_right_leg = root.getChild("bottom_back_right_leg");
        this.top_front_right_leg = root.getChild("top_front_right_leg");
        this.bottom_back_left_leg = root.getChild("bottom_back_left_leg");
        this.bottom_front_right_leg = root.getChild("bottom_front_right_leg");
        this.bottom_front_left_leg = root.getChild("bottom_front_left_leg");
        this.body_center = root.getChild("body_center");
        this.toe7 = root.getChild("toe7");
        this.toe6 = root.getChild("toe6");
        this.back_left_foot = root.getChild("back_left_foot");
        this.toe4 = root.getChild("toe4");
        this.toe5 = root.getChild("toe5");
        this.back_right_foot = root.getChild("back_right_foot");
        this.toe8 = root.getChild("toe8");
        this.toe1 = root.getChild("toe1");
        this.front_left_foot = root.getChild("front_left_foot");
        this.toe3 = root.getChild("toe3");
        this.toe2 = root.getChild("toe2");
        this.front_right_foot = root.getChild("front_right_foot");
        this.fin_ridge7 = root.getChild("fin_ridge7");
        this.fin_ridge6 = root.getChild("fin_ridge6");
        this.fin_ridge5 = root.getChild("fin_ridge5");
        this.fin_ridge4 = root.getChild("fin_ridge4");
        this.fin_ridge3 = root.getChild("fin_ridge3");
        this.fin_ridge2 = root.getChild("fin_ridge2");
        this.fin_ridge1 = root.getChild("fin_ridge1");
        this.fin10 = root.getChild("fin10");
        this.fin9 = root.getChild("fin9");
        this.fin8 = root.getChild("fin8");
        this.fin7 = root.getChild("fin7");
        this.fin6 = root.getChild("fin6");
        this.fin5 = root.getChild("fin5");
        this.fin3 = root.getChild("fin3");
        this.fin2 = root.getChild("fin2");
        this.tooth11 = root.getChild("tooth11");
        this.tooth10 = root.getChild("tooth10");
        this.tooth8 = root.getChild("tooth8");
        this.tooth7 = root.getChild("tooth7");
        this.tooth6 = root.getChild("tooth6");
        this.tooth5 = root.getChild("tooth5");
        this.tooth4 = root.getChild("tooth4");
        this.tooth3 = root.getChild("tooth3");
        this.tooth2 = root.getChild("tooth2");
        this.center_right_nose = root.getChild("center_right_nose");
        this.center_left_nose = root.getChild("center_left_nose");
        this.tooth1 = root.getChild("tooth1");
        this.bottom_nose = root.getChild("bottom_nose");
        this.top_nose = root.getChild("top_nose");
        this.jaw_top = root.getChild("jaw_top");
        this.center_middle_nose = root.getChild("center_middle_nose");
        this.right_eye = root.getChild("right_eye");
        this.left_eye = root.getChild("left_eye");
        this.tooth16 = root.getChild("tooth16");
        this.tooth15 = root.getChild("tooth15");
        this.tooth14 = root.getChild("tooth14");
        this.tooth13 = root.getChild("tooth13");
        this.tooth12 = root.getChild("tooth12");
        this.tooth9 = root.getChild("tooth9");
        this.bottom_jaw = root.getChild("bottom_jaw");
        this.hat1 = root.getChild("hat1");
        this.hat2 = root.getChild("hat2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(92, 48).addBox(-4.0f, -4.0f, 0.0f, 8, 8, 8), PartPose.offset(0.0f, 14.0f, 0.0f));
        root.addOrReplaceChild("top_back_left_leg", CubeListBuilder.create().texOffs(54, 32).addBox(0.0f, -2.0f, -2.0f, 8, 3, 3), PartPose.offsetAndRotation(3.0f, 13.0f, 2.0f, 0.0f, 0.0f, 0.2617994f));
        root.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(100, 118).addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8), PartPose.offset(0.0f, 23.0f, 41.0f));
        root.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(92, 16).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8), PartPose.offset(0.0f, 14.0f, -8.0f));
        root.addOrReplaceChild("tail_base1", CubeListBuilder.create().texOffs(88, 64).addBox(-3.0f, -3.0f, 0.0f, 6, 6, 14), PartPose.offsetAndRotation(0.0f, 14.0f, 7.0f, -0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(95, 84).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 10), PartPose.offsetAndRotation(0.0f, 17.0f, 19.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(100, 98).addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8), PartPose.offsetAndRotation(0.0f, 21.0f, 26.0f, -0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(100, 108).addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8), PartPose.offset(0.0f, 23.0f, 33.0f));
        root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(100, 9).addBox(-3.0f, -2.0f, -2.0f, 6, 5, 2), PartPose.offset(0.0f, 12.0f, -16.0f));
        root.addOrReplaceChild("top_front_left_leg", CubeListBuilder.create().texOffs(26, 12).addBox(0.0f, -2.0f, -2.0f, 8, 3, 3), PartPose.offsetAndRotation(3.0f, 13.0f, -12.0f, 0.0f, 0.0f, 0.2617994f));
        root.addOrReplaceChild("top_back_right_leg", CubeListBuilder.create().texOffs(26, 32).addBox(-8.0f, -2.0f, -2.0f, 8, 3, 3), PartPose.offsetAndRotation(-3.0f, 13.0f, 2.0f, 0.0f, 0.0f, -0.2617994f));
        root.addOrReplaceChild("bottom_back_right_leg", CubeListBuilder.create().texOffs(25, 26).addBox(-12.0f, -8.0f, -2.0f, 9, 3, 3), PartPose.offsetAndRotation(-3.0f, 13.0f, 2.0f, 0.0f, 0.0f, -1.308997f));
        root.addOrReplaceChild("top_front_right_leg", CubeListBuilder.create().texOffs(54, 12).addBox(-8.0f, -2.0f, -2.0f, 8, 3, 3), PartPose.offsetAndRotation(-3.0f, 13.0f, -12.0f, 0.0f, 0.0f, -0.2617994f));
        root.addOrReplaceChild("bottom_back_left_leg", CubeListBuilder.create().texOffs(53, 26).addBox(3.0f, -8.0f, -2.0f, 9, 3, 3), PartPose.offsetAndRotation(3.0f, 13.0f, 2.0f, 0.0f, 0.0f, 1.308997f));
        root.addOrReplaceChild("bottom_front_right_leg", CubeListBuilder.create().texOffs(53, 18).addBox(-12.0f, -8.0f, -2.0f, 9, 3, 3), PartPose.offsetAndRotation(-3.0f, 13.0f, -12.0f, 0.0f, 0.0f, -1.308997f));
        root.addOrReplaceChild("bottom_front_left_leg", CubeListBuilder.create().texOffs(25, 18).addBox(3.0f, -8.0f, -2.0f, 9, 3, 3), PartPose.offsetAndRotation(3.0f, 13.0f, -12.0f, 0.0f, 0.0f, 1.308997f));
        root.addOrReplaceChild("body_center", CubeListBuilder.create().texOffs(92, 32).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8), PartPose.offset(0.0f, 14.0f, -4.0f));
        root.addOrReplaceChild("toe7", CubeListBuilder.create().texOffs(104, 0).addBox(10.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(3.0f, 13.0f, 2.0f));
        root.addOrReplaceChild("toe6", CubeListBuilder.create().texOffs(108, 0).addBox(8.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(3.0f, 13.0f, 2.0f));
        root.addOrReplaceChild("back_left_foot", CubeListBuilder.create().texOffs(20, 0).addBox(7.0f, 9.0f, -4.0f, 4, 2, 6), PartPose.offset(3.0f, 13.0f, 2.0f));
        root.addOrReplaceChild("toe4", CubeListBuilder.create().texOffs(80, 0).addBox(-11.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(-3.0f, 13.0f, 2.0f));
        root.addOrReplaceChild("toe5", CubeListBuilder.create().texOffs(84, 0).addBox(-9.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(-3.0f, 13.0f, 2.0f));
        root.addOrReplaceChild("back_right_foot", CubeListBuilder.create().texOffs(60, 0).addBox(-11.0f, 9.0f, -4.0f, 4, 2, 6), PartPose.offset(-3.0f, 13.0f, 2.0f));
        root.addOrReplaceChild("toe8", CubeListBuilder.create().texOffs(100, 0).addBox(10.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(3.0f, 13.0f, -12.0f));
        root.addOrReplaceChild("toe1", CubeListBuilder.create().texOffs(96, 0).addBox(8.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(3.0f, 13.0f, -12.0f));
        root.addOrReplaceChild("front_left_foot", CubeListBuilder.create().texOffs(40, 0).addBox(7.0f, 9.0f, -4.0f, 4, 2, 6), PartPose.offset(3.0f, 13.0f, -12.0f));
        root.addOrReplaceChild("toe3", CubeListBuilder.create().texOffs(88, 0).addBox(-11.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(-3.0f, 13.0f, -12.0f));
        root.addOrReplaceChild("toe2", CubeListBuilder.create().texOffs(92, 0).addBox(-9.0f, 10.0f, -5.0f, 1, 1, 1), PartPose.offset(-3.0f, 13.0f, -12.0f));
        root.addOrReplaceChild("front_right_foot", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0f, 9.0f, -4.0f, 4, 2, 6), PartPose.offset(-3.0f, 13.0f, -12.0f));
        root.addOrReplaceChild("fin_ridge7", CubeListBuilder.create().texOffs(0, 99).addBox(0.0f, -13.0f, 0.0f, 2, 13, 1), PartPose.offsetAndRotation(-1.0f, 10.0f, -4.5f, -0.9666439f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin_ridge6", CubeListBuilder.create().texOffs(6, 98).addBox(0.0f, -13.0f, 0.0f, 2, 13, 1), PartPose.offsetAndRotation(-1.0f, 10.0f, -4.0f, -0.5205006f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin_ridge5", CubeListBuilder.create().texOffs(12, 99).addBox(0.0f, -13.0f, 0.0f, 2, 13, 1), PartPose.offset(-1.0f, 10.0f, -4.0f));
        root.addOrReplaceChild("fin_ridge4", CubeListBuilder.create().texOffs(6, 114).addBox(0.0f, -13.0f, 0.0f, 2, 13, 1), PartPose.offsetAndRotation(-1.0f, 10.0f, -3.5f, 0.9666439f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin_ridge3", CubeListBuilder.create().texOffs(12, 115).addBox(0.0f, -13.0f, 0.0f, 2, 13, 1), PartPose.offsetAndRotation(-1.0f, 10.0f, -4.0f, 0.5205006f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin_ridge2", CubeListBuilder.create().texOffs(0, 84).addBox(0.0f, -13.0f, 0.0f, 2, 13, 1), PartPose.offsetAndRotation(-1.0f, 10.0f, -4.5f, -1.375609f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin_ridge1", CubeListBuilder.create().texOffs(0, 114).addBox(0.0f, -13.0f, 0.0f, 2, 13, 1), PartPose.offsetAndRotation(-1.0f, 10.0f, -3.5f, 1.412787f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin10", CubeListBuilder.create().texOffs(0, 58).addBox(0.0f, -13.0f, -2.0f, 0, 11, 6), PartPose.offsetAndRotation(0.0f, 10.5f, -5.0f, 0.2094395f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin9", CubeListBuilder.create().texOffs(7, 84).addBox(0.0f, -11.0f, 0.0f, 0, 11, 3), PartPose.offsetAndRotation(0.0f, 10.0f, -5.0f, 1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin8", CubeListBuilder.create().texOffs(12, 34).addBox(0.0f, -7.0f, -4.0f, 0, 7, 4), PartPose.offsetAndRotation(0.0f, 10.0f, 1.0f, -1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin7", CubeListBuilder.create().texOffs(12, 46).addBox(0.0f, -8.0f, -4.0f, 0, 8, 4), PartPose.offsetAndRotation(0.0f, 10.0f, 1.0f, -1.033256f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin6", CubeListBuilder.create().texOffs(0, 31).addBox(0.0f, -10.0f, -4.0f, 0, 10, 4), PartPose.offsetAndRotation(0.0f, 10.0f, -1.0f, -0.7267386f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin5", CubeListBuilder.create().texOffs(30, 59).addBox(0.0f, -12.0f, -5.0f, 0, 11, 6), PartPose.offsetAndRotation(0.0f, 10.0f, -2.0f, -0.3003206f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin3", CubeListBuilder.create().texOffs(14, 60).addBox(0.0f, -12.0f, -3.0f, 0, 12, 6), PartPose.offsetAndRotation(0.0f, 10.0f, -4.0f, 0.7073231f, 0.0f, 0.0f));
        root.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(14, 79).addBox(0.0f, -12.0f, -4.0f, 0, 11, 6), PartPose.offsetAndRotation(0.0f, 10.0f, -4.0f, 1.048747f, 0.0f, 0.0f));
        root.addOrReplaceChild("tooth11", CubeListBuilder.create().texOffs(24, 110).addBox(3.0f, 3.0f, -8.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth10", CubeListBuilder.create().texOffs(24, 106).addBox(3.0f, 3.0f, -10.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth8", CubeListBuilder.create().texOffs(28, 95).addBox(3.0f, 3.0f, -14.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth7", CubeListBuilder.create().texOffs(70, 106).addBox(-4.0f, 3.0f, -10.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth6", CubeListBuilder.create().texOffs(70, 102).addBox(-4.0f, 3.0f, -12.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth5", CubeListBuilder.create().texOffs(66, 95).addBox(-4.0f, 3.0f, -14.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth4", CubeListBuilder.create().texOffs(60, 95).addBox(1.0f, 3.0f, -14.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth3", CubeListBuilder.create().texOffs(34, 95).addBox(-2.0f, 3.0f, -14.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth2", CubeListBuilder.create().texOffs(70, 110).addBox(-4.0f, 3.0f, -8.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("center_right_nose", CubeListBuilder.create().texOffs(40, 88).addBox(-4.0f, 0.0f, -14.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("center_left_nose", CubeListBuilder.create().texOffs(54, 88).addBox(3.0f, 0.0f, -14.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("tooth1", CubeListBuilder.create().texOffs(24, 102).addBox(3.0f, 3.0f, -12.0f, 1, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("bottom_nose", CubeListBuilder.create().texOffs(40, 90).addBox(-4.0f, 1.0f, -14.0f, 8, 2, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("top_nose", CubeListBuilder.create().texOffs(40, 84).addBox(-4.0f, -3.0f, -14.0f, 8, 3, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("jaw_top", CubeListBuilder.create().texOffs(28, 97).addBox(-4.0f, -3.0f, -13.0f, 8, 6, 13), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("center_middle_nose", CubeListBuilder.create().texOffs(46, 88).addBox(-1.0f, 0.0f, -14.0f, 2, 1, 1), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(116, 10).addBox(-2.0f, -4.0f, -4.0f, 2, 2, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -18.0f, 0.0f, 0.7853982f, 0.3490659f));
        root.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(94, 10).addBox(0.0f, -4.0f, -4.0f, 2, 2, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -18.0f, 0.0f, -0.7853982f, -0.3490659f));
        root.addOrReplaceChild("tooth16", CubeListBuilder.create().texOffs(24, 97).addBox(3.0f, -1.0f, -10.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 14.0f, -19.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("tooth15", CubeListBuilder.create().texOffs(70, 97).addBox(-4.0f, -1.0f, -10.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 14.0f, -19.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("tooth14", CubeListBuilder.create().texOffs(42, 95).addBox(-2.0f, -1.0f, -10.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 14.0f, -19.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("tooth13", CubeListBuilder.create().texOffs(52, 95).addBox(1.0f, -1.0f, -10.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 14.0f, -19.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("tooth12", CubeListBuilder.create().texOffs(24, 114).addBox(3.0f, -1.0f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 14.0f, -19.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("tooth9", CubeListBuilder.create().texOffs(70, 114).addBox(-4.0f, -1.0f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 14.0f, -19.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("bottom_jaw", CubeListBuilder.create().texOffs(31, 116).addBox(-4.0f, 0.0f, -10.0f, 8, 2, 10), PartPose.offsetAndRotation(0.0f, 14.0f, -19.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("hat1", CubeListBuilder.create().texOffs(30, 40).addBox(-2.0f, -4.0f, -6.0f, 4, 1, 6), PartPose.offset(0.0f, 12.0f, -18.0f));
        root.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(30, 40).addBox(-1.5f, -6.0f, -4.0f, 3, 2, 4), PartPose.offset(0.0f, 12.0f, -18.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Lizard e, float f, float f1, float f2, float f3, float f4) {
        float newangle = f1 > 0.1f ? Mth.cos(f2 * 1.0f * this.wingspeed) * (float) Math.PI * 0.25f * f1 : 0.0f;
        this.top_front_left_leg.yRot = newangle;
        this.bottom_front_left_leg.xRot = newangle;
        this.front_left_foot.yRot = newangle;
        this.toe8.yRot = newangle;
        this.toe1.yRot = newangle;
        this.top_front_right_leg.yRot = newangle;
        this.bottom_front_right_leg.xRot = -newangle;
        this.front_right_foot.yRot = newangle;
        this.toe3.yRot = newangle;
        this.toe2.yRot = newangle;
        this.top_back_left_leg.yRot = -newangle;
        this.bottom_back_left_leg.xRot = -newangle;
        this.back_left_foot.yRot = -newangle;
        this.toe7.yRot = -newangle;
        this.toe6.yRot = -newangle;
        this.top_back_right_leg.yRot = -newangle;
        this.bottom_back_right_leg.xRot = newangle;
        this.back_right_foot.yRot = -newangle;
        this.toe4.yRot = -newangle;
        this.toe5.yRot = -newangle;
        this.bottom_jaw.xRot = e.getAttacking() != 0 ? 0.52f + Mth.cos(f2 * 0.45f) * 0.35f : 0.25f;
        this.tooth9.xRot = this.bottom_jaw.xRot;
        this.tooth15.xRot = this.bottom_jaw.xRot;
        this.tooth14.xRot = this.bottom_jaw.xRot;
        this.tooth13.xRot = this.bottom_jaw.xRot;
        this.tooth16.xRot = this.bottom_jaw.xRot;
        this.tooth12.xRot = this.bottom_jaw.xRot;
        newangle = Mth.cos(f2 * 0.25f * this.wingspeed) * (float) Math.PI * 0.05f;
        if (e.getAttacking() != 0) {
            newangle = Mth.cos(f2 * 1.25f * this.wingspeed) * (float) Math.PI * 0.35f;
        }
        this.tail_base1.yRot = newangle * 0.25f;
        this.tail2.z = this.tail_base1.z + Mth.cos(this.tail_base1.yRot) * 12.0f;
        this.tail2.x = this.tail_base1.x + Mth.sin(this.tail_base1.yRot) * 12.0f;
        this.tail2.yRot = newangle * 0.5f;
        this.tail3.z = this.tail2.z + Mth.cos(this.tail2.yRot) * 9.0f;
        this.tail3.x = this.tail2.x + Mth.sin(this.tail2.yRot) * 9.0f;
        this.tail3.yRot = newangle * 0.75f;
        this.tail4.z = this.tail3.z + Mth.cos(this.tail3.yRot) * 7.0f;
        this.tail4.x = this.tail3.x + Mth.sin(this.tail3.yRot) * 7.0f;
        this.tail4.yRot = newangle * 1.0f;
        this.tail_tip.z = this.tail4.z + Mth.cos(this.tail4.yRot) * 7.0f;
        this.tail_tip.x = this.tail4.x + Mth.sin(this.tail4.yRot) * 7.0f;
        this.tail_tip.yRot = newangle * 1.25f;
        this.neck.yRot = (float) Math.toRadians(f3) * 0.25f;
        this.jaw_top.z = this.neck.z - Mth.cos(this.neck.yRot) * 2.0f;
        this.jaw_top.x = this.neck.x - Mth.sin(this.neck.yRot) * 2.0f;
        this.jaw_top.yRot = (float) Math.toRadians(f3) * 0.5f;
        syncHead(this.top_nose);
        syncHead(this.bottom_nose);
        syncHead(this.center_right_nose);
        syncHead(this.center_middle_nose);
        syncHead(this.center_left_nose);
        syncHead(this.tooth11);
        syncHead(this.tooth10);
        syncHead(this.tooth1);
        syncHead(this.tooth8);
        syncHead(this.tooth4);
        syncHead(this.tooth3);
        syncHead(this.tooth5);
        syncHead(this.tooth6);
        syncHead(this.tooth7);
        syncHead(this.tooth2);
        syncHead(this.hat1);
        syncHead(this.hat2);
        this.right_eye.yRot = this.jaw_top.yRot + 0.78f;
        this.left_eye.yRot = this.jaw_top.yRot - 0.78f;
        this.bottom_jaw.z = this.neck.z - Mth.cos(this.neck.yRot) * 3.0f;
        this.bottom_jaw.x = this.neck.x - Mth.sin(this.neck.yRot) * 3.0f;
        this.bottom_jaw.yRot = (float) Math.toRadians(f3) * 0.5f;
        syncJaw(this.tooth9);
        syncJaw(this.tooth16);
        syncJaw(this.tooth15);
        syncJaw(this.tooth14);
        syncJaw(this.tooth13);
        syncJaw(this.tooth12);
    }

    private void syncHead(ModelPart p) {
        p.z = this.jaw_top.z;
        p.x = this.jaw_top.x;
        p.yRot = this.jaw_top.yRot;
    }

    private void syncJaw(ModelPart p) {
        p.z = this.bottom_jaw.z;
        p.x = this.bottom_jaw.x;
        p.yRot = this.bottom_jaw.yRot;
        p.xRot = this.bottom_jaw.xRot;
    }

    @Override
    public void renderToBuffer(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha) {
        this.body_back.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.top_back_left_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail_tip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body_front.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail_base1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.top_front_left_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.top_back_right_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottom_back_right_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.top_front_right_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottom_back_left_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottom_front_right_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottom_front_left_leg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body_center.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_left_foot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back_right_foot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.front_left_foot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.toe2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.front_right_foot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin_ridge7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin_ridge6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin_ridge5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin_ridge4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin_ridge3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin_ridge2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin_ridge1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.center_right_nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.center_left_nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottom_nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.top_nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw_top.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.center_middle_nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.right_eye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.left_eye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth15.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth14.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth13.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bottom_jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderHats(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            Lizard e,
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
