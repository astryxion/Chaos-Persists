package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.StinkBug;
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

public class ModelStinkBug extends EntityModel<StinkBug> {
    private final float wingspeed;
    private final ModelPart f6;
    private final ModelPart b10;
    private final ModelPart l6;
    private final ModelPart l4;
    private final ModelPart f4;
    private final ModelPart l5;
    private final ModelPart f5;
    private final ModelPart l3;
    private final ModelPart l2;
    private final ModelPart l1;
    private final ModelPart f3;
    private final ModelPart f2;
    private final ModelPart f1;
    private final ModelPart jaw;
    private final ModelPart b9;
    private final ModelPart head;
    private final ModelPart b4;
    private final ModelPart h1;
    private final ModelPart h2;
    private final ModelPart body;
    private final ModelPart t21;
    private final ModelPart tail;
    private final ModelPart t22;
    private final ModelPart t20;
    private final ModelPart t19;
    private final ModelPart t6;
    private final ModelPart t11;
    private final ModelPart t9;
    private final ModelPart t4;
    private final ModelPart t2;
    private final ModelPart t7;
    private final ModelPart t12;
    private final ModelPart t10;
    private final ModelPart t8;
    private final ModelPart t5;
    private final ModelPart t3;
    private final ModelPart t1;
    private final ModelPart t18;
    private final ModelPart t16;
    private final ModelPart t14;
    private final ModelPart t13;
    private final ModelPart t15;
    private final ModelPart t17;
    private final ModelPart b1;
    private final ModelPart b2;
    private final ModelPart b3;
    private final ModelPart b8;
    private final ModelPart b7;
    private final ModelPart b6;
    private final ModelPart b5;

    public ModelStinkBug(float ff1) {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot(), ff1);
    }

    public ModelStinkBug(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.f6 = root.getChild("f6");
        this.b10 = root.getChild("b10");
        this.l6 = root.getChild("l6");
        this.l4 = root.getChild("l4");
        this.f4 = root.getChild("f4");
        this.l5 = root.getChild("l5");
        this.f5 = root.getChild("f5");
        this.l3 = root.getChild("l3");
        this.l2 = root.getChild("l2");
        this.l1 = root.getChild("l1");
        this.f3 = root.getChild("f3");
        this.f2 = root.getChild("f2");
        this.f1 = root.getChild("f1");
        this.jaw = root.getChild("jaw");
        this.b9 = root.getChild("b9");
        this.head = root.getChild("head");
        this.b4 = root.getChild("b4");
        this.h1 = root.getChild("h1");
        this.h2 = root.getChild("h2");
        this.body = root.getChild("body");
        this.t21 = root.getChild("t21");
        this.tail = root.getChild("tail");
        this.t22 = root.getChild("t22");
        this.t20 = root.getChild("t20");
        this.t19 = root.getChild("t19");
        this.t6 = root.getChild("t6");
        this.t11 = root.getChild("t11");
        this.t9 = root.getChild("t9");
        this.t4 = root.getChild("t4");
        this.t2 = root.getChild("t2");
        this.t7 = root.getChild("t7");
        this.t12 = root.getChild("t12");
        this.t10 = root.getChild("t10");
        this.t8 = root.getChild("t8");
        this.t5 = root.getChild("t5");
        this.t3 = root.getChild("t3");
        this.t1 = root.getChild("t1");
        this.t18 = root.getChild("t18");
        this.t16 = root.getChild("t16");
        this.t14 = root.getChild("t14");
        this.t13 = root.getChild("t13");
        this.t15 = root.getChild("t15");
        this.t17 = root.getChild("t17");
        this.b1 = root.getChild("b1");
        this.b2 = root.getChild("b2");
        this.b3 = root.getChild("b3");
        this.b8 = root.getChild("b8");
        this.b7 = root.getChild("b7");
        this.b6 = root.getChild("b6");
        this.b5 = root.getChild("b5");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("f6", CubeListBuilder.create().texOffs(20, 16).addBox(-2.0f, 0.0f, -1.0f, 2, 2, 2), PartPose.offset(-3.5f, 22f, 3.0f));
        root.addOrReplaceChild("b10", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5f, -1.5f, -0.5f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, 17f, 1.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("l6", CubeListBuilder.create().texOffs(20, 13).addBox(-2.0f, 0.0f, -1.0f, 2, 1, 2), PartPose.offset(-3.0f, 21f, 3.0f));
        root.addOrReplaceChild("l4", CubeListBuilder.create().texOffs(20, 13).addBox(-2.0f, 0.0f, -1.0f, 2, 1, 2), PartPose.offset(-3.0f, 21f, -3.0f));
        root.addOrReplaceChild("f4", CubeListBuilder.create().texOffs(20, 16).addBox(-2.0f, 0.0f, -1.0f, 2, 2, 2), PartPose.offset(-3.5f, 22f, -3.0f));
        root.addOrReplaceChild("l5", CubeListBuilder.create().texOffs(20, 13).addBox(-2.0f, 0.0f, -1.0f, 2, 1, 2), PartPose.offset(-3.0f, 21f, 0.0f));
        root.addOrReplaceChild("f5", CubeListBuilder.create().texOffs(20, 16).addBox(-2.0f, 0.0f, -1.0f, 2, 2, 2), PartPose.offset(-3.5f, 22f, 0.0f));
        root.addOrReplaceChild("l3", CubeListBuilder.create().texOffs(20, 13).addBox(0.0f, 0.0f, -1.0f, 2, 1, 2), PartPose.offset(3.0f, 21f, 3.0f));
        root.addOrReplaceChild("l2", CubeListBuilder.create().texOffs(20, 13).addBox(0.0f, 0.0f, -1.0f, 2, 1, 2), PartPose.offset(3.0f, 21f, 0.0f));
        root.addOrReplaceChild("l1", CubeListBuilder.create().texOffs(20, 13).addBox(0.0f, 0.0f, -1.0f, 2, 1, 2), PartPose.offset(3.0f, 21f, -3.0f));
        root.addOrReplaceChild("f3", CubeListBuilder.create().texOffs(20, 16).addBox(0.0f, 0.0f, -1.0f, 2, 2, 2), PartPose.offset(3.5f, 22f, 3.0f));
        root.addOrReplaceChild("f2", CubeListBuilder.create().texOffs(20, 16).addBox(0.0f, 0.0f, -1.0f, 2, 2, 2), PartPose.offset(3.5f, 22f, 0.0f));
        root.addOrReplaceChild("f1", CubeListBuilder.create().texOffs(20, 16).addBox(0.0f, 0.0f, -1.0f, 2, 2, 2), PartPose.offset(3.5f, 22f, -3.0f));
        root.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(28, 8).addBox(-3.5f, 0.0f, -8.0f, 5, 1, 4), PartPose.offsetAndRotation(1.0f, 21f, 0.0f, 0.122173f, 0.0f, 0.0f));
        root.addOrReplaceChild("b9", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5f, -1.5f, -0.5f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, 17f, -1.0f, 0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 0).addBox(-3.5f, -3.5f, -8.0f, 5, 4, 4), PartPose.offset(1.0f, 21f, 0.0f));
        root.addOrReplaceChild("b4", CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, -0.5f, 2.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        root.addOrReplaceChild("h1", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5f, -2.0f, -0.5f, 1, 2, 1), PartPose.offsetAndRotation(-1.5f, 18f, -7.0f, 0.5235988f, 0.3490659f, 0.0f));
        root.addOrReplaceChild("h2", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5f, -2.0f, -0.5f, 1, 2, 1), PartPose.offsetAndRotation(1.5f, 18f, -7.0f, 0.5235988f, -0.3490659f, 0.0f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -4.0f, -4.0f, 6, 5, 8), PartPose.offset(1.0f, 21f, 0.0f));
        root.addOrReplaceChild("t21", CubeListBuilder.create().texOffs(0, 0).addBox(0.5f, 3.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0f, 0.0f, 0.0f, 4, 4, 6), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t22", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, 3.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t20", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, 3.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t19", CubeListBuilder.create().texOffs(0, 0).addBox(0.5f, 3.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t6", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, 2.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t11", CubeListBuilder.create().texOffs(0, 0).addBox(0.5f, -0.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t9", CubeListBuilder.create().texOffs(0, 0).addBox(0.5f, -0.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t4", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, 2.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t2", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, 2.5f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t7", CubeListBuilder.create().texOffs(0, 0).addBox(0.5f, -0.5f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t12", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -0.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t10", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -0.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -0.5f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t5", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, 0.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t3", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, 0.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t1", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, 0.5f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t18", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, 2.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t16", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, 2.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t14", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, 2.5f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t13", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, 0.5f, 0.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t15", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, 0.5f, 2.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("t17", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, 0.5f, 4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.5f, 4.0f, -0.3316126f, 0.0f, 0.0f));
        root.addOrReplaceChild("b1", CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, -0.5f, -3.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        root.addOrReplaceChild("b2", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, -0.5f, -1.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        root.addOrReplaceChild("b3", CubeListBuilder.create().texOffs(0, 0).addBox(1.5f, -0.5f, 0.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        root.addOrReplaceChild("b8", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -0.5f, 2.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        root.addOrReplaceChild("b7", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, -0.5f, 0.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        root.addOrReplaceChild("b6", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5f, -0.5f, -1.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        root.addOrReplaceChild("b5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.966667f, -0.5f, -3.5f, 1, 1, 1), PartPose.offset(0.0f, 17f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(
            StinkBug entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        float newangle;
        this.f1.xRot = this.f3.xRot = (newangle = Mth.sin(ageInTicks * 3.1f * this.wingspeed) * (float) Math.PI * 0.3f * limbSwingAmount);
        this.f4.xRot = this.f6.xRot = -newangle;
        this.f2.xRot = this.f6.xRot;
        this.b9.zRot = newangle = Mth.sin(ageInTicks * 0.4f * this.wingspeed) * (float) Math.PI * 0.2f;
        this.b10.zRot = -newangle;
        newangle = Mth.sin(ageInTicks * 0.2f * this.wingspeed) * (float) Math.PI * 0.04f;
        this.jaw.xRot = 0.18f + newangle;
        this.h1.xRot = 0.52f + Mth.sin(ageInTicks * 0.4f * this.wingspeed) * (float) Math.PI * 0.15f;
        this.h1.yRot = -0.3f + Mth.sin(ageInTicks * 0.43f * this.wingspeed) * (float) Math.PI * 0.15f;
        this.h2.xRot = 0.52f + Mth.sin(ageInTicks * 0.46f * this.wingspeed) * (float) Math.PI * 0.15f;
        this.h2.yRot = 0.3f + Mth.sin(ageInTicks * 0.49f * this.wingspeed) * (float) Math.PI * 0.15f;
        this.t4.xRot = this.t5.xRot = (this.tail.xRot = -0.2f + Mth.sin(ageInTicks * 0.1f * this.wingspeed) * (float) Math.PI * 0.1f);
        this.t3.xRot = this.t5.xRot;
        this.t2.xRot = this.t5.xRot;
        this.t1.xRot = this.t5.xRot;
        this.t9.xRot = this.t10.xRot = this.tail.xRot;
        this.t8.xRot = this.t10.xRot;
        this.t7.xRot = this.t10.xRot;
        this.t6.xRot = this.t10.xRot;
        this.t14.xRot = this.t15.xRot = this.tail.xRot;
        this.t13.xRot = this.t15.xRot;
        this.t12.xRot = this.t15.xRot;
        this.t11.xRot = this.t15.xRot;
        this.t19.xRot = this.t20.xRot = this.tail.xRot;
        this.t18.xRot = this.t20.xRot;
        this.t17.xRot = this.t20.xRot;
        this.t16.xRot = this.t20.xRot;
        this.t21.xRot = this.t22.xRot = this.tail.xRot;
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
        this.f6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.f4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.f5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.l1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.f3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.f2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.f1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.h1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.h2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t22.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t20.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t19.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t18.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t14.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t13.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t15.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t17.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.b5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
