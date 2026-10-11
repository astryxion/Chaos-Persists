package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.LurkingTerror;
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

public class ModelLurkingTerror extends EntityModel<LurkingTerror> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart leg1;
    private final ModelPart leg1part2;
    private final ModelPart leg1part3;
    private final ModelPart leg2;
    private final ModelPart leg2part2;
    private final ModelPart leg2part3;
    private final ModelPart leg3;
    private final ModelPart leg3part2;
    private final ModelPart leg3part3;
    private final ModelPart leg4;
    private final ModelPart leg4part2;
    private final ModelPart leg4part3;
    private final ModelPart leg5;
    private final ModelPart leg5part2;
    private final ModelPart leg6;
    private final ModelPart leg6part2;
    private final ModelPart thorax;
    private final ModelPart abdomen;
    private final ModelPart head;
    private final ModelPart jaw1;
    private final ModelPart jaw1part2;
    private final ModelPart jaw1tooth1;
    private final ModelPart jaw1tooth2;
    private final ModelPart jaw1tooth3;
    private final ModelPart jaw1tooth4;
    private final ModelPart jaw1tooth5;
    private final ModelPart jaw1tooth6;
    private final ModelPart jaw2;
    private final ModelPart jaw2part2;
    private final ModelPart jaw2tooth1;
    private final ModelPart jaw2tooth2;
    private final ModelPart jaw2tooth3;
    private final ModelPart jaw2tooth4;
    private final ModelPart jaw2tooth5;
    private final ModelPart jaw2tooth6;
    private final ModelPart jaw3;
    private final ModelPart jaw3part2;
    private final ModelPart jaw3tooth1;
    private final ModelPart jaw3tooth2;
    private final ModelPart jaw3tooth3;
    private final ModelPart jaw3tooth4;
    private final ModelPart jaw3tooth5;
    private final ModelPart jaw3tooth6;
    private final ModelPart jaw4;
    private final ModelPart jaw4part2;
    private final ModelPart jaw4tooth1;
    private final ModelPart jaw4tooth2;
    private final ModelPart jaw4tooth3;
    private final ModelPart jaw4tooth4;
    private final ModelPart jaw4tooth5;
    private final ModelPart jaw4tooth6;
    private final ModelPart tonguepart1;
    private final ModelPart tonguepart2;
    private final ModelPart tonguepart3;
    private final ModelPart wing_1;
    private final ModelPart wing_2;
    private final ModelPart wing_3;
    private final ModelPart wing_4;

    public ModelLurkingTerror(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 256, 64).bakeRoot());
    }

    public ModelLurkingTerror(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.leg1 = root.getChild("leg1");
        this.leg1part2 = root.getChild("leg1part2");
        this.leg1part3 = root.getChild("leg1part3");
        this.leg2 = root.getChild("leg2");
        this.leg2part2 = root.getChild("leg2part2");
        this.leg2part3 = root.getChild("leg2part3");
        this.leg3 = root.getChild("leg3");
        this.leg3part2 = root.getChild("leg3part2");
        this.leg3part3 = root.getChild("leg3part3");
        this.leg4 = root.getChild("leg4");
        this.leg4part2 = root.getChild("leg4part2");
        this.leg4part3 = root.getChild("leg4part3");
        this.leg5 = root.getChild("leg5");
        this.leg5part2 = root.getChild("leg5part2");
        this.leg6 = root.getChild("leg6");
        this.leg6part2 = root.getChild("leg6part2");
        this.thorax = root.getChild("thorax");
        this.abdomen = root.getChild("abdomen");
        this.head = root.getChild("head");
        this.jaw1 = root.getChild("jaw1");
        this.jaw1part2 = root.getChild("jaw1part2");
        this.jaw1tooth1 = root.getChild("jaw1tooth1");
        this.jaw1tooth2 = root.getChild("jaw1tooth2");
        this.jaw1tooth3 = root.getChild("jaw1tooth3");
        this.jaw1tooth4 = root.getChild("jaw1tooth4");
        this.jaw1tooth5 = root.getChild("jaw1tooth5");
        this.jaw1tooth6 = root.getChild("jaw1tooth6");
        this.jaw2 = root.getChild("jaw2");
        this.jaw2part2 = root.getChild("jaw2part2");
        this.jaw2tooth1 = root.getChild("jaw2tooth1");
        this.jaw2tooth2 = root.getChild("jaw2tooth2");
        this.jaw2tooth3 = root.getChild("jaw2tooth3");
        this.jaw2tooth4 = root.getChild("jaw2tooth4");
        this.jaw2tooth5 = root.getChild("jaw2tooth5");
        this.jaw2tooth6 = root.getChild("jaw2tooth6");
        this.jaw3 = root.getChild("jaw3");
        this.jaw3part2 = root.getChild("jaw3part2");
        this.jaw3tooth1 = root.getChild("jaw3tooth1");
        this.jaw3tooth2 = root.getChild("jaw3tooth2");
        this.jaw3tooth3 = root.getChild("jaw3tooth3");
        this.jaw3tooth4 = root.getChild("jaw3tooth4");
        this.jaw3tooth5 = root.getChild("jaw3tooth5");
        this.jaw3tooth6 = root.getChild("jaw3tooth6");
        this.jaw4 = root.getChild("jaw4");
        this.jaw4part2 = root.getChild("jaw4part2");
        this.jaw4tooth1 = root.getChild("jaw4tooth1");
        this.jaw4tooth2 = root.getChild("jaw4tooth2");
        this.jaw4tooth3 = root.getChild("jaw4tooth3");
        this.jaw4tooth4 = root.getChild("jaw4tooth4");
        this.jaw4tooth5 = root.getChild("jaw4tooth5");
        this.jaw4tooth6 = root.getChild("jaw4tooth6");
        this.tonguepart1 = root.getChild("tonguepart1");
        this.tonguepart2 = root.getChild("tonguepart2");
        this.tonguepart3 = root.getChild("tonguepart3");
        this.wing_1 = root.getChild("wing_1");
        this.wing_2 = root.getChild("wing_2");
        this.wing_3 = root.getChild("wing_3");
        this.wing_4 = root.getChild("wing_4");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(39, 27).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 12), PartPose.offset(0.0f, 10.0f, 0.0f));
        root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0f, -1.5f, -1.5f, 16, 3, 3), PartPose.offsetAndRotation(-4.0f, 10.0f, -1.0f, 0.0f, -0.5759587f, -0.1919862f));
        root.addOrReplaceChild("leg1part2", CubeListBuilder.create().texOffs(58, 0).addBox(-15.0f, -1.5f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(-4.0f, 10.0f, -1.0f, 0.0f, -0.5759587f, -0.1919862f));
        root.addOrReplaceChild("leg1part3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0f, -1.0f, -1.0f, 2, 8, 2), PartPose.offsetAndRotation(-4.0f, 10.0f, -1.0f, 0.0f, -0.5759587f, -0.6753082f));
        root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0f, -1.5f, -1.5f, 16, 3, 3), PartPose.offsetAndRotation(4.0f, 10.0f, -1.0f, 0.0f, 0.5759587f, 0.1919862f));
        root.addOrReplaceChild("leg2part2", CubeListBuilder.create().texOffs(58, 0).addBox(12.0f, -1.5f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(4.0f, 10.0f, -1.0f, 0.0f, 0.5759587f, 0.1919862f));
        root.addOrReplaceChild("leg2part3", CubeListBuilder.create().texOffs(0, 0).addBox(13.0f, -1.0f, -1.0f, 2, 8, 2), PartPose.offsetAndRotation(4.0f, 10.0f, -1.0f, 0.0f, 0.5759587f, 0.6753028f));
        root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(18, 0).addBox(-15.0f, -1.5f, -1.5f, 16, 3, 3), PartPose.offsetAndRotation(-4.0f, 10.0f, 1.0f, 0.0f, 0.2792527f, -0.1919862f));
        root.addOrReplaceChild("leg3part2", CubeListBuilder.create().texOffs(58, 0).addBox(-15.0f, -1.5f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(-4.0f, 10.0f, 1.0f, 0.0f, 0.2792527f, -0.1919862f));
        root.addOrReplaceChild("leg3part3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0f, -1.0f, -1.0f, 2, 8, 2), PartPose.offsetAndRotation(-4.0f, 10.0f, 1.0f, 0.0f, 0.2792527f, -0.6753028f));
        root.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0f, -1.5f, -1.5f, 16, 3, 3), PartPose.offsetAndRotation(4.0f, 10.0f, 1.0f, 0.0f, -0.2792527f, 0.1919862f));
        root.addOrReplaceChild("leg4part2", CubeListBuilder.create().texOffs(58, 0).addBox(12.0f, -1.5f, -1.5f, 3, 8, 3), PartPose.offsetAndRotation(4.0f, 10.0f, 1.0f, 0.0f, -0.2792527f, 0.1919862f));
        root.addOrReplaceChild("leg4part3", CubeListBuilder.create().texOffs(0, 0).addBox(13.0f, -1.0f, -1.0f, 2, 8, 2), PartPose.offsetAndRotation(4.0f, 10.0f, 1.0f, 0.0f, -0.2792527f, 0.6753028f));
        root.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(119, 0).addBox(-4.0f, -1.5f, -1.5f, 25, 3, 3), PartPose.offsetAndRotation(4.0f, 10.0f, 4.0f, 0.0f, -1.134359f, 0.3407057f));
        root.addOrReplaceChild("leg5part2", CubeListBuilder.create().texOffs(18, 9).addBox(18.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offsetAndRotation(4.0f, 10.0f, 4.0f, 0.0f, -1.134359f, 0.3407057f));
        root.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(119, 0).addBox(-21.0f, -1.5f, -1.5f, 25, 3, 3), PartPose.offsetAndRotation(-4.0f, 10.0f, 4.0f, 0.0f, 1.134359f, -0.3407057f));
        root.addOrReplaceChild("leg6part2", CubeListBuilder.create().texOffs(18, 9).addBox(-21.0f, -1.5f, -1.5f, 3, 10, 3), PartPose.offsetAndRotation(-4.0f, 10.0f, 4.0f, 0.0f, 1.134359f, -0.3407057f));
        root.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(0, 42).addBox(-2.0f, -2.0f, -6.0f, 4, 4, 18), PartPose.offsetAndRotation(0.0f, 10.0f, 9.0f, -0.2602503f, 0.0f, 0.0f));
        root.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(118, 18).addBox(-3.0f, -3.0f, 0.0f, 6, 6, 16), PartPose.offset(0.0f, 13.0f, 20.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(27, 48).addBox(-3.0f, -3.0f, -3.0f, 6, 6, 5), PartPose.offset(0.0f, 10.0f, -8.0f));
        root.addOrReplaceChild("jaw1", CubeListBuilder.create().texOffs(96, 31).addBox(-1.0f, -1.0f, -13.0f, 1, 2, 14), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw1part2", CubeListBuilder.create().texOffs(39, 17).addBox(-1.1f, -2.0f, -5.0f, 1, 4, 5), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw1tooth1", CubeListBuilder.create().texOffs(39, 27).addBox(0.0f, -0.5f, -13.0f, 1, 1, 1), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw1tooth2", CubeListBuilder.create().texOffs(39, 27).addBox(0.0f, -0.5f, -11.0f, 1, 1, 1), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw1tooth3", CubeListBuilder.create().texOffs(39, 27).addBox(0.0f, -0.5f, -9.0f, 1, 1, 1), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw1tooth4", CubeListBuilder.create().texOffs(39, 27).addBox(0.0f, -0.5f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw1tooth5", CubeListBuilder.create().texOffs(39, 27).addBox(0.0f, -1.5f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw1tooth6", CubeListBuilder.create().texOffs(39, 27).addBox(0.0f, 0.5f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(-2.0f, 10.0f, -8.0f, 0.0f, 0.4089647f, 0.0f));
        root.addOrReplaceChild("jaw2", CubeListBuilder.create().texOffs(96, 48).addBox(0.0f, -1.0f, -13.0f, 1, 2, 14), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw2part2", CubeListBuilder.create().texOffs(39, 7).addBox(0.1f, -2.0f, -5.0f, 1, 4, 5), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw2tooth1", CubeListBuilder.create().texOffs(96, 48).addBox(-1.0f, -0.5f, -13.0f, 1, 1, 1), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw2tooth2", CubeListBuilder.create().texOffs(96, 48).addBox(-1.0f, -0.5f, -11.0f, 1, 1, 1), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw2tooth3", CubeListBuilder.create().texOffs(96, 48).addBox(-1.0f, -0.5f, -9.0f, 1, 1, 1), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw2tooth4", CubeListBuilder.create().texOffs(96, 48).addBox(-1.0f, -0.5f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw2tooth5", CubeListBuilder.create().texOffs(96, 48).addBox(-1.0f, -1.5f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw2tooth6", CubeListBuilder.create().texOffs(96, 48).addBox(-1.0f, 0.5f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(2.0f, 10.0f, -8.0f, 0.0f, -0.4089656f, 0.0f));
        root.addOrReplaceChild("jaw3", CubeListBuilder.create().texOffs(95, 16).addBox(-1.0f, -1.0f, -13.0f, 2, 1, 14), PartPose.offsetAndRotation(0.0f, 8.0f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3part2", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0f, -1.0f, -5.0f, 4, 1, 5), PartPose.offsetAndRotation(0.0f, 7.9f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3tooth1", CubeListBuilder.create().texOffs(95, 16).addBox(-0.5f, 0.0f, -13.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 8.0f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3tooth2", CubeListBuilder.create().texOffs(95, 16).addBox(-0.5f, 0.0f, -11.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 8.0f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3tooth3", CubeListBuilder.create().texOffs(95, 16).addBox(-0.5f, 0.0f, -9.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 8.0f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3tooth4", CubeListBuilder.create().texOffs(95, 16).addBox(-0.5f, 0.0f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 8.0f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3tooth5", CubeListBuilder.create().texOffs(95, 16).addBox(-1.5f, 0.0f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 8.0f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw3tooth6", CubeListBuilder.create().texOffs(95, 16).addBox(0.5f, 0.0f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 8.0f, -8.0f, -0.4089647f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4", CubeListBuilder.create().texOffs(95, 0).addBox(-1.0f, 0.0f, -13.0f, 2, 1, 14), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4part2", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0f, 0.0f, -5.0f, 4, 1, 5), PartPose.offsetAndRotation(0.0f, 12.1f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4tooth1", CubeListBuilder.create().texOffs(95, 0).addBox(-0.5f, -1.0f, -13.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4tooth2", CubeListBuilder.create().texOffs(95, 0).addBox(-0.5f, -1.0f, -11.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4tooth3", CubeListBuilder.create().texOffs(95, 0).addBox(-0.5f, -1.0f, -9.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4tooth4", CubeListBuilder.create().texOffs(95, 0).addBox(-0.5f, -1.0f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4tooth5", CubeListBuilder.create().texOffs(95, 0).addBox(-1.5f, -1.0f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw4tooth6", CubeListBuilder.create().texOffs(95, 0).addBox(0.5f, -1.0f, -4.5f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 12.0f, -8.0f, 0.4089656f, 0.0f, 0.0f));
        root.addOrReplaceChild("tonguepart1", CubeListBuilder.create().texOffs(24, 34).addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5), PartPose.offsetAndRotation(1.6f, 9.3f, -15.0f, 1.041001f, 1.264073f, -1.07818f));
        root.addOrReplaceChild("tonguepart2", CubeListBuilder.create().texOffs(0, 46).addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5), PartPose.offsetAndRotation(0.0f, 10.0f, -11.0f, -0.1858931f, -0.2230717f, 0.669215f));
        root.addOrReplaceChild("tonguepart3", CubeListBuilder.create().texOffs(24, 27).addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5), PartPose.offsetAndRotation(0.2f, 11.3f, -19.0f, -0.2602503f, 0.3717861f, -1.07818f));
        root.addOrReplaceChild("wing_1", CubeListBuilder.create().texOffs(108, 42).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 22), PartPose.offsetAndRotation(-2.0f, 6.0f, -5.0f, 0.5948578f, -0.9294653f, 0.0f));
        root.addOrReplaceChild("wing_2", CubeListBuilder.create().texOffs(141, 42).addBox(-4.0f, 0.0f, 0.0f, 8, 0, 22), PartPose.offsetAndRotation(2.0f, 6.0f, -5.0f, 0.5948606f, 0.9294576f, 0.0f));
        root.addOrReplaceChild("wing_3", CubeListBuilder.create().texOffs(64, 27).addBox(-2.0f, 0.0f, 0.0f, 4, 0, 18), PartPose.offsetAndRotation(-2.0f, 6.0f, -1.0f, 0.3346075f, -0.4089647f, 0.0f));
        root.addOrReplaceChild("wing_4", CubeListBuilder.create().texOffs(153, 17).addBox(-2.0f, 0.0f, 0.0f, 4, 0, 18), PartPose.offsetAndRotation(2.0f, 6.0f, -1.0f, 0.3346075f, 0.4089656f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(
            LurkingTerror entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        float newangle = 0.0f;
        float legspeed = 0.7f;
        float mouthspeed = 0.9f;
        RenderInfo r = entity.getRenderInfo();
        newangle = ageInTicks * legspeed * this.wingspeed % 6.2831855f;
        newangle = Math.abs(newangle);
        if (newangle < r.rf1) {
            r.ri1 = 0;
            if (entity.level().isClientSide) {
                if (entity.getRandom().nextInt(3) == 1) {
                    r.ri1 |= 1;
                }
                if (entity.getRandom().nextInt(3) == 1) {
                    r.ri1 |= 2;
                }
                if (entity.getRandom().nextInt(4) == 1) {
                    r.ri1 |= 4;
                }
                if (entity.getRandom().nextInt(4) == 1) {
                    r.ri1 |= 8;
                }
                if (entity.getRandom().nextInt(6) == 1) {
                    r.ri1 |= 16;
                }
                if (entity.getRandom().nextInt(6) == 1) {
                    r.ri1 |= 32;
                }
            }
        }
        r.rf1 = newangle;
        newangle = ageInTicks * mouthspeed * this.wingspeed % 6.2831855f;
        if ((newangle = Math.abs(newangle)) < r.rf2) {
            r.ri2 = 0;
            if (entity.level().isClientSide) {
                if (entity.getRandom().nextInt(20) == 1) {
                    r.ri2 |= 1;
                }
            }
            if (entity.getAttacking() != 0) {
                r.ri2 = 1;
            }
        }
        r.rf2 = newangle;
        newangle = 0.0f;
        if ((r.ri1 & 1) != 0) {
            newangle = Mth.sin((float)(ageInTicks * legspeed * this.wingspeed)) * 3.1415927f * 0.25f;
        }
        this.leg2.zRot = this.leg2part2.zRot = 0.191f + newangle;
        this.leg2part3.zRot = 0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 2) != 0) {
            newangle = Mth.sin((float)(ageInTicks * legspeed * this.wingspeed)) * 3.1415927f * 0.25f;
        }
        this.leg1.zRot = this.leg1part2.zRot = -0.191f + newangle;
        this.leg1part3.zRot = -0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 4) != 0) {
            newangle = Mth.sin((float)(ageInTicks * legspeed * this.wingspeed)) * 3.1415927f * 0.15f;
        }
        this.leg4.zRot = this.leg4part2.zRot = 0.191f + newangle;
        this.leg4part3.zRot = 0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 8) != 0) {
            newangle = Mth.sin((float)(ageInTicks * legspeed * this.wingspeed)) * 3.1415927f * 0.15f;
        }
        this.leg3.zRot = this.leg3part2.zRot = -0.191f + newangle;
        this.leg3part3.zRot = -0.675f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 16) != 0) {
            newangle = Mth.sin((float)(ageInTicks * legspeed * this.wingspeed)) * 3.1415927f * 0.1f;
        }
        this.leg6.zRot = this.leg6part2.zRot = -0.34f + newangle;
        newangle = 0.0f;
        if ((r.ri1 & 32) != 0) {
            newangle = Mth.sin((float)(ageInTicks * legspeed * this.wingspeed)) * 3.1415927f * 0.1f;
        }
        this.leg5.zRot = this.leg5part2.zRot = 0.34f + newangle;
        newangle = 0.0f;
        if ((r.ri2 & 1) != 0) {
            newangle = Mth.sin((float)(ageInTicks * mouthspeed * this.wingspeed)) * 3.1415927f * 0.35f;
            newangle = Math.abs(newangle);
        }
        this.jaw1.yRot = newangle;
        this.jaw1part2.yRot = newangle;
        this.jaw1tooth3.yRot = this.jaw1tooth5.yRot = newangle;
        this.jaw1tooth1.yRot = this.jaw1tooth5.yRot;
        this.jaw1tooth4.yRot = this.jaw1tooth6.yRot = newangle;
        this.jaw1tooth2.yRot = this.jaw1tooth6.yRot;
        this.jaw2.yRot = - newangle;
        this.jaw2part2.yRot = - newangle;
        this.jaw2tooth3.yRot = this.jaw2tooth5.yRot = - newangle;
        this.jaw2tooth1.yRot = this.jaw2tooth5.yRot;
        this.jaw2tooth4.yRot = this.jaw2tooth6.yRot = - newangle;
        this.jaw2tooth2.yRot = this.jaw2tooth6.yRot;
        this.jaw3.xRot = - newangle;
        this.jaw3part2.xRot = - newangle;
        this.jaw3tooth3.xRot = this.jaw3tooth5.xRot = - newangle;
        this.jaw3tooth1.xRot = this.jaw3tooth5.xRot;
        this.jaw3tooth4.xRot = this.jaw3tooth6.xRot = - newangle;
        this.jaw3tooth2.xRot = this.jaw3tooth6.xRot;
        this.jaw4.xRot = newangle;
        this.jaw4part2.xRot = newangle;
        this.jaw4tooth3.xRot = this.jaw4tooth5.xRot = newangle;
        this.jaw4tooth1.xRot = this.jaw4tooth5.xRot;
        this.jaw4tooth4.xRot = this.jaw4tooth6.xRot = newangle;
        this.jaw4tooth2.xRot = this.jaw4tooth6.xRot;
        this.tonguepart3.xRot = 0.0f;
        this.tonguepart2.xRot = 0.0f;
        this.tonguepart1.xRot = 0.0f;
        this.tonguepart3.yRot = 0.0f;
        this.tonguepart2.yRot = 0.0f;
        this.tonguepart1.yRot = 0.0f;
        this.tonguepart3.zRot = 0.0f;
        this.tonguepart2.zRot = 0.0f;
        this.tonguepart1.zRot = 0.0f;
        this.tonguepart1.x = this.tonguepart3.x = this.tonguepart2.x;
        this.tonguepart1.y = this.tonguepart3.y = this.tonguepart2.y;
        this.tonguepart1.z = this.tonguepart2.z - newangle * 5.0f;
        this.tonguepart3.z = this.tonguepart2.z - newangle * 10.0f;
        this.thorax.xRot = newangle = Mth.sin((float)(ageInTicks * 0.1f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.abdomen.y = (float)((double)this.thorax.y - Math.sin(newangle) * 14.0);
        newangle = Mth.cos((float)(ageInTicks * 1.4f * this.wingspeed)) * 3.1415927f * 0.2f;
        this.wing_1.xRot = 0.455f + newangle;
        this.wing_2.xRot = 0.455f + newangle;
        this.wing_3.xRot = 0.455f - newangle;
        this.wing_4.xRot = 0.455f - newangle;
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
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg5part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg6part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.thorax.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.abdomen.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1tooth6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw2tooth6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw3tooth6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw4tooth6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tonguepart1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tonguepart2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tonguepart3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.wing_4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
