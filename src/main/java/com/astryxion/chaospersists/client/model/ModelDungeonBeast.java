package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.entity.RenderInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ModelDungeonBeast extends EntityModel<DungeonBeast> {
    private final float wingspeed;
    private final ModelPart tail7;
    private final ModelPart head3;
    private final ModelPart neck;
    private final ModelPart lhornbase;
    private final ModelPart leye;
    private final ModelPart ljaw3;
    private final ModelPart ljaw1;
    private final ModelPart ljaw2;
    private final ModelPart rjaw1;
    private final ModelPart rjaw2;
    private final ModelPart rjaw3;
    private final ModelPart t1s3;
    private final ModelPart rshoulder;
    private final ModelPart rheel;
    private final ModelPart lshoulder;
    private final ModelPart rleg1;
    private final ModelPart rleg2;
    private final ModelPart lleg1;
    private final ModelPart lleg2;
    private final ModelPart rfoot;
    private final ModelPart ltoe3;
    private final ModelPart ltoe2;
    private final ModelPart ltoe1;
    private final ModelPart head1;
    private final ModelPart horn2;
    private final ModelPart rhornbase;
    private final ModelPart rh1;
    private final ModelPart lh1;
    private final ModelPart lh2;
    private final ModelPart rh2;
    private final ModelPart rh3;
    private final ModelPart lh3;
    private final ModelPart lh4;
    private final ModelPart rh4;
    private final ModelPart horn1;
    private final ModelPart t2s3;
    private final ModelPart tail3;
    private final ModelPart t4s1;
    private final ModelPart t6s1;
    private final ModelPart tail6;
    private final ModelPart body;
    private final ModelPart bodys1;
    private final ModelPart bodys2;
    private final ModelPart tail1;
    private final ModelPart bodys3;
    private final ModelPart t1s1;
    private final ModelPart t1s2;
    private final ModelPart tail2;
    private final ModelPart t3s2;
    private final ModelPart t2s2;
    private final ModelPart t2s1;
    private final ModelPart t3s1;
    private final ModelPart tail4;
    private final ModelPart tail5;
    private final ModelPart t5s1;
    private final ModelPart head2;
    private final ModelPart reye;
    private final ModelPart lfoot;
    private final ModelPart rfoot2;
    private final ModelPart lfoot2;
    private final ModelPart lheel;
    private final ModelPart rtoe3;
    private final ModelPart rtoe2;
    private final ModelPart rtoe1;

    public ModelDungeonBeast(float f1) {
        this(LayerDefinition.create(createMesh(), 128, 64).bakeRoot(), f1);
    }

    public ModelDungeonBeast(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.tail7 = root.getChild("tail7");
        this.head3 = root.getChild("head3");
        this.neck = root.getChild("neck");
        this.lhornbase = root.getChild("lhornbase");
        this.leye = root.getChild("leye");
        this.ljaw3 = root.getChild("ljaw3");
        this.ljaw1 = root.getChild("ljaw1");
        this.ljaw2 = root.getChild("ljaw2");
        this.rjaw1 = root.getChild("rjaw1");
        this.rjaw2 = root.getChild("rjaw2");
        this.rjaw3 = root.getChild("rjaw3");
        this.t1s3 = root.getChild("t1s3");
        this.rshoulder = root.getChild("rshoulder");
        this.rheel = root.getChild("rheel");
        this.lshoulder = root.getChild("lshoulder");
        this.rleg1 = root.getChild("rleg1");
        this.rleg2 = root.getChild("rleg2");
        this.lleg1 = root.getChild("lleg1");
        this.lleg2 = root.getChild("lleg2");
        this.rfoot = root.getChild("rfoot");
        this.ltoe3 = root.getChild("ltoe3");
        this.ltoe2 = root.getChild("ltoe2");
        this.ltoe1 = root.getChild("ltoe1");
        this.head1 = root.getChild("head1");
        this.horn2 = root.getChild("horn2");
        this.rhornbase = root.getChild("rhornbase");
        this.rh1 = root.getChild("rh1");
        this.lh1 = root.getChild("lh1");
        this.lh2 = root.getChild("lh2");
        this.rh2 = root.getChild("rh2");
        this.rh3 = root.getChild("rh3");
        this.lh3 = root.getChild("lh3");
        this.lh4 = root.getChild("lh4");
        this.rh4 = root.getChild("rh4");
        this.horn1 = root.getChild("horn1");
        this.t2s3 = root.getChild("t2s3");
        this.tail3 = root.getChild("tail3");
        this.t4s1 = root.getChild("t4s1");
        this.t6s1 = root.getChild("t6s1");
        this.tail6 = root.getChild("tail6");
        this.body = root.getChild("body");
        this.bodys1 = root.getChild("bodys1");
        this.bodys2 = root.getChild("bodys2");
        this.tail1 = root.getChild("tail1");
        this.bodys3 = root.getChild("bodys3");
        this.t1s1 = root.getChild("t1s1");
        this.t1s2 = root.getChild("t1s2");
        this.tail2 = root.getChild("tail2");
        this.t3s2 = root.getChild("t3s2");
        this.t2s2 = root.getChild("t2s2");
        this.t2s1 = root.getChild("t2s1");
        this.t3s1 = root.getChild("t3s1");
        this.tail4 = root.getChild("tail4");
        this.tail5 = root.getChild("tail5");
        this.t5s1 = root.getChild("t5s1");
        this.head2 = root.getChild("head2");
        this.reye = root.getChild("reye");
        this.lfoot = root.getChild("lfoot");
        this.rfoot2 = root.getChild("rfoot2");
        this.lfoot2 = root.getChild("lfoot2");
        this.lheel = root.getChild("lheel");
        this.rtoe3 = root.getChild("rtoe3");
        this.rtoe2 = root.getChild("rtoe2");
        this.rtoe1 = root.getChild("rtoe1");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("tail7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -0.5f, -0.5333334f, 3, 1, 1), PartPose.offsetAndRotation(-24.0f, 23.5f, 0.0f, 0.0f, 0.0f, 3.141593f));
        root.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, -2.466667f, 4.3f, 4, 4, 2), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.8028515f, 0.0f));
        root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, -2.533333f, 3, 3, 5), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.1745329f));
        root.addOrReplaceChild("lhornbase", CubeListBuilder.create().texOffs(0, 0).addBox(2.5f, -3.0f, 0.5f, 3, 1, 2), PartPose.offset(5.0f, 15.0f, 0.0f));
        root.addOrReplaceChild("leye", CubeListBuilder.create().texOffs(14, 15).addBox(3.0f, -1.466667f, 3.3f, 2, 1, 2), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.4363323f, 0.0f));
        root.addOrReplaceChild("ljaw3", CubeListBuilder.create().texOffs(10, 28).addBox(3.5f, 0.0f, 1.5f, 1, 1, 1), PartPose.offsetAndRotation(10.0f, 16.0f, 2.0f, 0.0f, 0.5235988f, 0.0f));
        root.addOrReplaceChild("ljaw1", CubeListBuilder.create().texOffs(10, 20).addBox(0.0f, 0.0f, -1.466667f, 3, 1, 2), PartPose.offsetAndRotation(10.0f, 16.0f, 2.0f, 0.0f, -0.3490659f, 0.0f));
        root.addOrReplaceChild("ljaw2", CubeListBuilder.create().texOffs(10, 24).addBox(2.0f, 0.0f, 0.3f, 2, 1, 2), PartPose.offsetAndRotation(10.0f, 16.0f, 2.0f, 0.0f, 0.3490659f, 0.0f));
        root.addOrReplaceChild("rjaw1", CubeListBuilder.create().texOffs(10, 20).addBox(0.0f, 0.0f, -0.4666667f, 3, 1, 2), PartPose.offsetAndRotation(10.0f, 16.0f, -2.0f, 0.0f, 0.3490659f, 0.0f));
        root.addOrReplaceChild("rjaw2", CubeListBuilder.create().texOffs(10, 24).addBox(2.0f, 0.0f, -2.3f, 2, 1, 2), PartPose.offsetAndRotation(10.0f, 16.0f, -2.0f, 0.0f, -0.3490659f, 0.0f));
        root.addOrReplaceChild("rjaw3", CubeListBuilder.create().texOffs(10, 28).addBox(3.5f, 0.0f, -2.5f, 1, 1, 1), PartPose.offsetAndRotation(10.0f, 16.0f, -2.0f, 0.0f, -0.5235988f, 0.0f));
        root.addOrReplaceChild("t1s3", CubeListBuilder.create().texOffs(75, 0).addBox(-3.0f, -7.0f, -0.5f, 1, 4, 1), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.8726646f));
        root.addOrReplaceChild("rshoulder", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, -2.2f, -5.0f, 4, 4, 2), PartPose.offset(-1.0f, 15.0f, 0.0f));
        root.addOrReplaceChild("rheel", CubeListBuilder.create().texOffs(0, 0).addBox(-2.3f, 0.3f, 6.0f, 1, 1, 1), PartPose.offsetAndRotation(3.0f, 17.0f, -7.0f, -1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("lshoulder", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, -2.2f, 3.0f, 4, 4, 2), PartPose.offset(-1.0f, 15.0f, 0.0f));
        root.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.466667f, -2.0f, -5.0f, 3, 3, 6), PartPose.offsetAndRotation(3.0f, 15.0f, -4.0f, 0.6981317f, 0.0f, 0.0f));
        root.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -0.2f, 0.0f, 2, 2, 6), PartPose.offsetAndRotation(3.0f, 17.0f, -7.0f, -1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("lleg1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.466667f, -2.0f, -1.0f, 3, 3, 6), PartPose.offsetAndRotation(3.0f, 15.0f, 4.0f, -0.6981317f, 0.0f, 0.0f));
        root.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -1.8f, 0.0f, 2, 2, 6), PartPose.offsetAndRotation(3.0f, 17.0f, 7.0f, -1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("rfoot", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -0.7f, 5.0f, 3, 3, 2), PartPose.offsetAndRotation(3.0f, 17.0f, -7.0f, -1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("ltoe3", CubeListBuilder.create().texOffs(32, 0).addBox(-3.7f, -1.5f, 4.5f, 1, 1, 2), PartPose.offsetAndRotation(3.0f, 17.0f, 7.0f, -1.570796f, 0.7853982f, -0.7853982f));
        root.addOrReplaceChild("ltoe2", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0f, -1.3f, 5.2f, 1, 1, 2), PartPose.offsetAndRotation(3.0f, 17.0f, 7.0f, -1.570796f, 0.0f, -0.7853982f));
        root.addOrReplaceChild("ltoe1", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0f, -0.6f, 5.2f, 1, 1, 2), PartPose.offsetAndRotation(3.0f, 17.0f, 7.0f, -1.570796f, -0.7853982f, -0.7853982f));
        root.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, -2.466667f, -3.0f, 4, 4, 6), PartPose.offset(5.0f, 15.0f, 0.0f));
        root.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(75, 6).addBox(-7.0f, -4.0f, -0.5f, 1, 2, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, 2.181662f));
        root.addOrReplaceChild("rhornbase", CubeListBuilder.create().texOffs(0, 0).addBox(2.5f, -3.0f, -2.5f, 3, 1, 2), PartPose.offset(5.0f, 15.0f, 0.0f));
        root.addOrReplaceChild("rh1", CubeListBuilder.create().texOffs(0, 28).addBox(4.0f, -3.0f, -2.5f, 2, 3, 2), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.5235988f));
        root.addOrReplaceChild("lh1", CubeListBuilder.create().texOffs(0, 28).addBox(4.0f, -3.0f, 0.5f, 2, 3, 2), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.5235988f));
        root.addOrReplaceChild("lh2", CubeListBuilder.create().texOffs(0, 23).addBox(5.0f, -4.0f, 1.0f, 1, 3, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.8726646f));
        root.addOrReplaceChild("rh2", CubeListBuilder.create().texOffs(0, 23).addBox(5.0f, -4.0f, -2.0f, 1, 3, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.8726646f));
        root.addOrReplaceChild("rh3", CubeListBuilder.create().texOffs(0, 19).addBox(6.1f, -2.4f, -2.0f, 1, 2, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -1.396263f));
        root.addOrReplaceChild("lh3", CubeListBuilder.create().texOffs(0, 19).addBox(6.1f, -2.4f, 1.0f, 1, 2, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -1.396263f));
        root.addOrReplaceChild("lh4", CubeListBuilder.create().texOffs(0, 15).addBox(6.5f, -1.8f, 1.0f, 1, 2, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -1.745329f));
        root.addOrReplaceChild("rh4", CubeListBuilder.create().texOffs(0, 15).addBox(6.5f, -1.8f, -2.0f, 1, 2, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, -1.745329f));
        root.addOrReplaceChild("horn1", CubeListBuilder.create().texOffs(75, 6).addBox(-8.0f, -2.5f, -0.5f, 1, 2, 1), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, 0.0f, 2.617994f));
        root.addOrReplaceChild("t2s3", CubeListBuilder.create().texOffs(75, 0).addBox(3.0f, 3.466667f, -0.5333334f, 1, 3, 1), PartPose.offsetAndRotation(-6.0f, 17.0f, 0.0f, 0.0f, 0.0f, 2.007129f));
        root.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -1.5f, -2.5f, 6, 3, 5), PartPose.offsetAndRotation(-10.0f, 20.0f, 0.0f, 0.0f, 0.0f, 2.530727f));
        root.addOrReplaceChild("t4s1", CubeListBuilder.create().texOffs(75, 0).addBox(0.5333334f, 1.533333f, -0.4666667f, 1, 2, 1), PartPose.offsetAndRotation(-14.0f, 22.8f, 0.0f, 0.0f, 0.0f, 2.356194f));
        root.addOrReplaceChild("t6s1", CubeListBuilder.create().texOffs(75, 0).addBox(0.0f, 0.5f, -0.5f, 1, 1, 1), PartPose.offsetAndRotation(-21.0f, 23.5f, 0.0f, 0.0f, 0.0f, 2.356194f));
        root.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -0.5f, -1.0f, 4, 1, 2), PartPose.offsetAndRotation(-21.0f, 23.5f, 0.0f, 0.0f, 0.0f, 3.141593f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -3.0f, -4.0f, 7, 6, 8), PartPose.offset(-1.0f, 15.0f, 0.0f));
        root.addOrReplaceChild("bodys1", CubeListBuilder.create().texOffs(75, 0).addBox(6.0f, -3.0f, -0.5f, 1, 4, 1), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.5235988f));
        root.addOrReplaceChild("bodys2", CubeListBuilder.create().texOffs(75, 0).addBox(4.0f, -4.0f, -0.5f, 1, 4, 1), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.5235988f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -2.533333f, -3.5f, 7, 5, 7), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.0f, 0.0f, 2.792527f));
        root.addOrReplaceChild("bodys3", CubeListBuilder.create().texOffs(75, 0).addBox(2.0f, -5.0f, -0.5f, 1, 4, 1), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.5235988f));
        root.addOrReplaceChild("t1s1", CubeListBuilder.create().texOffs(75, 0).addBox(1.0f, -5.0f, -0.5f, 1, 4, 1), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.8726646f));
        root.addOrReplaceChild("t1s2", CubeListBuilder.create().texOffs(75, 0).addBox(-1.0f, -6.0f, -0.5f, 1, 4, 1), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.0f, 0.0f, -0.8726646f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -2.0f, -3.0f, 7, 4, 6), PartPose.offsetAndRotation(-6.0f, 17.0f, 0.0f, 0.0f, 0.0f, 2.530727f));
        root.addOrReplaceChild("t3s2", CubeListBuilder.create().texOffs(75, 0).addBox(2.5f, 2.466667f, -0.5333334f, 1, 3, 1), PartPose.offsetAndRotation(-10.0f, 20.0f, 0.0f, 0.0f, 0.0f, 2.007129f));
        root.addOrReplaceChild("t2s2", CubeListBuilder.create().texOffs(75, 0).addBox(1.0f, 2.466667f, -0.5333334f, 1, 3, 1), PartPose.offsetAndRotation(-6.0f, 17.0f, 0.0f, 0.0f, 0.0f, 2.007129f));
        root.addOrReplaceChild("t2s1", CubeListBuilder.create().texOffs(75, 0).addBox(-1.0f, 1.466667f, -0.5333334f, 1, 3, 1), PartPose.offsetAndRotation(-6.0f, 17.0f, 0.0f, 0.0f, 0.0f, 2.007129f));
        root.addOrReplaceChild("t3s1", CubeListBuilder.create().texOffs(75, 0).addBox(0.5f, 1.466667f, -0.5333334f, 1, 3, 1), PartPose.offsetAndRotation(-10.0f, 20.0f, 0.0f, 0.0f, 0.0f, 2.007129f));
        root.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -1.0f, -2.0f, 5, 2, 4), PartPose.offsetAndRotation(-14.0f, 22.8f, 0.0f, 0.0f, 0.0f, 3.054326f));
        root.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, -0.5f, -1.5f, 4, 1, 3), PartPose.offsetAndRotation(-18.0f, 23.2f, 0.0f, 0.0f, 0.0f, 3.054326f));
        root.addOrReplaceChild("t5s1", CubeListBuilder.create().texOffs(75, 0).addBox(0.0f, 0.5f, -0.5f, 1, 2, 1), PartPose.offsetAndRotation(-18.0f, 23.2f, 0.0f, 0.0f, 0.0f, 2.356194f));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, -2.466667f, -6.3f, 4, 4, 2), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, -0.8028515f, 0.0f));
        root.addOrReplaceChild("reye", CubeListBuilder.create().texOffs(5, 15).addBox(3.0f, -1.466667f, -5.3f, 2, 1, 2), PartPose.offsetAndRotation(5.0f, 15.0f, 0.0f, 0.0f, -0.4363323f, 0.0f));
        root.addOrReplaceChild("lfoot", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -2.3f, 5.0f, 3, 3, 2), PartPose.offsetAndRotation(3.0f, 17.0f, 7.0f, -1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("rfoot2", CubeListBuilder.create().texOffs(0, 0).addBox(0.6f, -1.5f, 5.0f, 2, 2, 2), PartPose.offsetAndRotation(3.0f, 17.0f, -7.0f, -1.570796f, 0.7853982f, 0.0f));
        root.addOrReplaceChild("lfoot2", CubeListBuilder.create().texOffs(0, 0).addBox(0.7f, -0.5f, 5.0f, 2, 2, 2), PartPose.offsetAndRotation(3.0f, 17.0f, 7.0f, -1.570796f, -0.7853982f, 0.0f));
        root.addOrReplaceChild("lheel", CubeListBuilder.create().texOffs(0, 0).addBox(-2.3f, -1.3f, 6.0f, 1, 1, 1), PartPose.offsetAndRotation(3.0f, 17.0f, 7.0f, -1.570796f, 0.0f, 0.0f));
        root.addOrReplaceChild("rtoe3", CubeListBuilder.create().texOffs(32, 0).addBox(-3.7f, 0.6f, 4.5f, 1, 1, 2), PartPose.offsetAndRotation(3.0f, 17.0f, -7.0f, -1.570796f, -0.7853982f, -0.7853982f));
        root.addOrReplaceChild("rtoe2", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0f, 0.3f, 5.2f, 1, 1, 2), PartPose.offsetAndRotation(3.0f, 17.0f, -7.0f, -1.570796f, 0.0f, -0.7853982f));
        root.addOrReplaceChild("rtoe1", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0f, -0.6f, 5.2f, 1, 1, 2), PartPose.offsetAndRotation(3.0f, 17.0f, -7.0f, -1.570796f, 0.7853982f, -0.7853982f));
        return mesh;
    }

    @Override
    public void setupAnim(
            DungeonBeast entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        float newangle;
        float pi4 = 0.39269876f;
        float tailamp = entity.getAttacking() == 0 ? limbSwingAmount : 1.25f;
        newangle = Mth.cos(ageInTicks * 1.4f * this.wingspeed) * (float) Math.PI * 0.22f * limbSwingAmount;
        this.rfoot2.zRot = newangle;
        this.rheel.zRot = newangle;
        this.rfoot.zRot = newangle;
        this.rleg2.zRot = newangle;
        this.rleg1.zRot = newangle;
        this.rtoe2.zRot = -0.785f + newangle;
        newangle = Mth.cos(ageInTicks * 1.4f * this.wingspeed) * (float) Math.PI * 0.22f * limbSwingAmount;
        this.lfoot2.zRot = -newangle;
        this.lheel.zRot = -newangle;
        this.lfoot.zRot = -newangle;
        this.lleg2.zRot = -newangle;
        this.lleg1.zRot = -newangle;
        this.ltoe2.zRot = -0.785f - newangle;
        this.bodys1.xRot = Mth.cos(ageInTicks * 0.5f * this.wingspeed) * (float) Math.PI * 0.07f;
        this.bodys2.xRot = Mth.cos(ageInTicks * 0.5f * this.wingspeed + pi4) * (float) Math.PI * 0.07f;
        this.bodys3.xRot = Mth.cos(ageInTicks * 0.5f * this.wingspeed + 2.0f * pi4) * (float) Math.PI * 0.07f;
        this.t1s1.xRot = Mth.cos(ageInTicks * 0.5f * this.wingspeed + 3.0f * pi4) * (float) Math.PI * 0.07f;
        this.t1s2.xRot = Mth.cos(ageInTicks * 0.5f * this.wingspeed + 4.0f * pi4) * (float) Math.PI * 0.07f;
        this.t1s3.xRot = Mth.cos(ageInTicks * 0.5f * this.wingspeed + 5.0f * pi4) * (float) Math.PI * 0.07f;
        this.t2s1.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 6.0f * pi4) * (float) Math.PI * 0.07f;
        this.t2s2.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 7.0f * pi4) * (float) Math.PI * 0.07f;
        this.t2s3.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 8.0f * pi4) * (float) Math.PI * 0.07f;
        this.t3s1.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 9.0f * pi4) * (float) Math.PI * 0.07f;
        this.t3s2.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 10.0f * pi4) * (float) Math.PI * 0.07f;
        this.t4s1.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 11.0f * pi4) * (float) Math.PI * 0.07f;
        this.t5s1.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 12.0f * pi4) * (float) Math.PI * 0.07f;
        this.t6s1.xRot = -Mth.cos(ageInTicks * 0.5f * this.wingspeed + 13.0f * pi4) * (float) Math.PI * 0.07f;
        newangle = Mth.cos(ageInTicks * 0.75f * this.wingspeed) * (float) Math.PI * 0.25f * tailamp;
        this.tail1.yRot = newangle * 0.25f;
        this.t1s2.yRot = this.tail1.yRot;
        this.t1s3.yRot = this.tail1.yRot;
        this.t1s1.yRot = this.t1s3.yRot;
        this.tail2.yRot = newangle * 0.5f;
        this.tail2.x = this.tail1.x - Mth.cos(this.tail1.yRot) * 6.0f;
        this.tail2.z = this.tail1.z - Mth.sin(this.tail1.yRot) * 6.0f;
        this.t2s2.yRot = this.tail2.yRot;
        this.t2s3.yRot = this.tail2.yRot;
        this.t2s1.yRot = this.t2s3.yRot;
        this.t2s2.z = this.t2s3.z = this.tail2.z;
        this.t2s1.z = this.t2s3.z;
        this.t2s2.x = this.t2s3.x = this.tail2.x;
        this.t2s1.x = this.t2s3.x;
        this.tail3.yRot = newangle * 0.75f;
        this.tail3.x = this.tail2.x - Mth.cos(this.tail2.yRot) * 5.0f;
        this.tail3.z = this.tail2.z - Mth.sin(this.tail2.yRot) * 5.0f;
        this.t3s1.yRot = this.tail3.yRot;
        this.t3s2.yRot = this.tail3.yRot;
        this.t3s1.z = this.t3s2.z = this.tail3.z;
        this.t3s1.x = this.t3s2.x = this.tail3.x;
        this.tail4.yRot = newangle;
        this.tail4.x = this.tail3.x - Mth.cos(this.tail3.yRot) * 4.5f;
        this.tail4.z = this.tail3.z - Mth.sin(this.tail3.yRot) * 4.5f;
        this.t4s1.yRot = this.tail4.yRot;
        this.t4s1.z = this.tail4.z;
        this.t4s1.x = this.tail4.x;
        this.tail5.yRot = newangle * 1.25f;
        this.tail5.x = this.tail4.x - Mth.cos(this.tail4.yRot) * 4.0f;
        this.tail5.z = this.tail4.z - Mth.sin(this.tail4.yRot) * 4.0f;
        this.t5s1.yRot = this.tail5.yRot;
        this.t5s1.z = this.tail5.z;
        this.t5s1.x = this.tail5.x;
        this.tail6.yRot = newangle * 1.5f;
        this.tail6.x = this.tail5.x - Mth.cos(this.tail5.yRot) * 3.0f;
        this.tail6.z = this.tail5.z - Mth.sin(this.tail5.yRot) * 3.0f;
        this.t6s1.yRot = this.tail6.yRot;
        this.t6s1.z = this.tail6.z;
        this.t6s1.x = this.tail6.x;
        this.tail7.yRot = newangle * 1.75f;
        this.tail7.x = this.tail6.x - Mth.cos(this.tail6.yRot) * 3.0f;
        this.tail7.z = this.tail6.z - Mth.sin(this.tail6.yRot) * 3.0f;
        RenderInfo r = entity.getRenderInfo();
        newangle = Mth.cos(ageInTicks * 2.0f * this.wingspeed) * (float) Math.PI * 0.15f;
        float nextangle = Mth.cos((ageInTicks + 0.1f) * 2.0f * this.wingspeed) * (float) Math.PI * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            if (entity.getAttacking() == 0) {
                r.ri1 = entity.getRandom().nextInt(15);
                r.ri2 = entity.getRandom().nextInt(15);
            } else {
                r.ri1 = 0;
                r.ri2 = 0;
            }
        }
        if (r.ri1 == 0) {
            this.ljaw1.yRot = -0.349f + newangle;
            this.ljaw2.yRot = 0.349f + newangle;
            this.ljaw3.yRot = 0.523f + newangle;
            this.rjaw1.yRot = 0.349f - newangle;
            this.rjaw2.yRot = -0.349f - newangle;
            this.rjaw3.yRot = -0.523f - newangle;
        } else {
            this.ljaw1.yRot = -0.349f;
            this.ljaw2.yRot = 0.349f;
            this.ljaw3.yRot = 0.523f;
            this.rjaw1.yRot = 0.349f;
            this.rjaw2.yRot = -0.349f;
            this.rjaw3.yRot = -0.523f;
        }
        entity.setRenderInfo(r);
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
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
        this.tail7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lhornbase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t1s3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rshoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rheel.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lshoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ltoe2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rhornbase.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lh4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rh4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.horn1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2s3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t4s1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t6s1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodys1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodys2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bodys3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t1s1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t1s2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t3s2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2s2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2s1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t3s1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t5s1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.reye.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lheel.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rtoe2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.popPose();
    }
}
