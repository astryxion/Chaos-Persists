package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Pointysaurus;
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

public class ModelPointysaurus extends EntityModel<Pointysaurus> {
    private final float wingspeed;
    private final ModelPart lfleg;
    private final ModelPart rfleg;
    private final ModelPart lrleg;
    private final ModelPart rrleg;
    private final ModelPart body1;
    private final ModelPart head;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart guard;
    private final ModelPart nose;
    private final ModelPart lhorn;
    private final ModelPart rhorn;
    private final ModelPart chorn;
    private final ModelPart tail;
    private final ModelPart bump1;
    private final ModelPart bump2;
    private final ModelPart bump3;
    private final ModelPart bump4;
    private final ModelPart bump5;
    private final ModelPart bump6;
    private final ModelPart bump7;
    private final ModelPart bump8;
    private final ModelPart bump9;
    private final ModelPart bump10;
    private final ModelPart bump11;
    private final ModelPart bump12;
    private final ModelPart bump13;
    private final ModelPart bump14;
    private final ModelPart bump15;
    private final ModelPart bump16;
    public ModelPointysaurus(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelPointysaurus(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.lfleg = root.getChild("lfleg");
        this.rfleg = root.getChild("rfleg");
        this.lrleg = root.getChild("lrleg");
        this.rrleg = root.getChild("rrleg");
        this.body1 = root.getChild("body1");
        this.head = root.getChild("head");
        this.body2 = root.getChild("body2");
        this.body3 = root.getChild("body3");
        this.guard = root.getChild("guard");
        this.nose = root.getChild("nose");
        this.lhorn = root.getChild("lhorn");
        this.rhorn = root.getChild("rhorn");
        this.chorn = root.getChild("chorn");
        this.tail = root.getChild("tail");
        this.bump1 = root.getChild("bump1");
        this.bump2 = root.getChild("bump2");
        this.bump3 = root.getChild("bump3");
        this.bump4 = root.getChild("bump4");
        this.bump5 = root.getChild("bump5");
        this.bump6 = root.getChild("bump6");
        this.bump7 = root.getChild("bump7");
        this.bump8 = root.getChild("bump8");
        this.bump9 = root.getChild("bump9");
        this.bump10 = root.getChild("bump10");
        this.bump11 = root.getChild("bump11");
        this.bump12 = root.getChild("bump12");
        this.bump13 = root.getChild("bump13");
        this.bump14 = root.getChild("bump14");
        this.bump15 = root.getChild("bump15");
        this.bump16 = root.getChild("bump16");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("lfleg", CubeListBuilder.create().texOffs(102, 66).addBox(-3.0f, 0.0f, -3.0f, 6, 8, 6), PartPose.offsetAndRotation(9.0f, 16.0f, -8.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rfleg", CubeListBuilder.create().texOffs(102, 66).addBox(-3.0f, 0.0f, -3.0f, 6, 8, 6), PartPose.offsetAndRotation(-9.0f, 16.0f, -8.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lrleg", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, 0.0f, -4.0f, 8, 8, 8), PartPose.offsetAndRotation(9.0f, 16.0f, 12.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rrleg", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, 0.0f, -4.0f, 8, 8, 8), PartPose.offsetAndRotation(-9.0f, 16.0f, 12.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 87).addBox(-4.0f, 0.0f, 0.0f, 22, 9, 30), PartPose.offsetAndRotation(-7.0f, 9.0f, -12.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(70, 0).addBox(-6.0f, -10.0f, -12.0f, 12, 10, 12), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 63).addBox(-9.0f, 0.0f, 0.0f, 18, 7, 15), PartPose.offsetAndRotation(0.0f, 2.0f, -9.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 44).addBox(-8.0f, 0.0f, 0.0f, 16, 6, 11), PartPose.offsetAndRotation(0.0f, 3.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("guard", CubeListBuilder.create().texOffs(60, 34).addBox(-14.0f, -20.0f, -8.0f, 28, 23, 3), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(39, 0).addBox(-5.0f, -9.0f, -15.0f, 10, 6, 5), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lhorn", CubeListBuilder.create().texOffs(0, 18).addBox(8.0f, -16.0f, -29.0f, 2, 2, 23), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.1570796f, -0.1396263f, 0.0f));
        partdefinition.addOrReplaceChild("rhorn", CubeListBuilder.create().texOffs(0, 18).addBox(-9.0f, -16.0f, -29.0f, 2, 2, 23), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.1570796f, 0.1396263f, 0.0f));
        partdefinition.addOrReplaceChild("chorn", CubeListBuilder.create().texOffs(52, 13).addBox(-1.5f, -9.0f, -20.0f, 3, 3, 5), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(68, 70).addBox(-3.0f, -3.0f, 0.0f, 6, 6, 9), PartPose.offsetAndRotation(0.0f, 7.0f, 15.0f, 0.2792527f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump1", CubeListBuilder.create().texOffs(57, 17).addBox(14.0f, -20.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump2", CubeListBuilder.create().texOffs(57, 17).addBox(14.0f, -15.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump3", CubeListBuilder.create().texOffs(57, 17).addBox(14.0f, -10.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump4", CubeListBuilder.create().texOffs(57, 17).addBox(14.0f, -5.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump5", CubeListBuilder.create().texOffs(57, 17).addBox(14.0f, 0.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump6", CubeListBuilder.create().texOffs(57, 17).addBox(-16.0f, -20.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump7", CubeListBuilder.create().texOffs(57, 17).addBox(-16.0f, -15.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump8", CubeListBuilder.create().texOffs(57, 17).addBox(-16.0f, -10.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump9", CubeListBuilder.create().texOffs(57, 17).addBox(-16.0f, -5.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump10", CubeListBuilder.create().texOffs(57, 17).addBox(-16.0f, 0.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump11", CubeListBuilder.create().texOffs(57, 17).addBox(12.0f, -22.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump12", CubeListBuilder.create().texOffs(57, 17).addBox(7.0f, -22.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump13", CubeListBuilder.create().texOffs(57, 17).addBox(2.0f, -22.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump14", CubeListBuilder.create().texOffs(57, 17).addBox(-4.0f, -22.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump15", CubeListBuilder.create().texOffs(57, 17).addBox(-9.0f, -22.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bump16", CubeListBuilder.create().texOffs(57, 17).addBox(-14.0f, -22.0f, -8.0f, 2, 2, 2), PartPose.offsetAndRotation(0.0f, 11.0f, -7.0f, -0.2617994f, 0.0f, 0.0f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(Pointysaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
float newangle = 0.0f;
        newangle = limbSwingAmount > 0.1f ? Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f * limbSwingAmount : 0.0f;
        this.lfleg.xRot = newangle;
        this.rrleg.xRot = newangle;
        this.rfleg.xRot = - newangle;
        this.lrleg.xRot = - newangle;
        this.nose.yRot = this.head.yRot = (float) Math.toRadians(netHeadYaw) * 0.45f;
        this.chorn.yRot = this.head.yRot;
        this.lhorn.yRot = this.head.yRot - 0.14f;
        this.rhorn.yRot = this.head.yRot + 0.14f;
        this.guard.yRot = this.head.yRot;
        this.bump1.yRot = this.head.yRot;
        this.bump2.yRot = this.head.yRot;
        this.bump3.yRot = this.head.yRot;
        this.bump4.yRot = this.head.yRot;
        this.bump5.yRot = this.head.yRot;
        this.bump6.yRot = this.head.yRot;
        this.bump7.yRot = this.head.yRot;
        this.bump8.yRot = this.head.yRot;
        this.bump9.yRot = this.head.yRot;
        this.bump10.yRot = this.head.yRot;
        this.bump11.yRot = this.head.yRot;
        this.bump12.yRot = this.head.yRot;
        this.bump13.yRot = this.head.yRot;
        this.bump14.yRot = this.head.yRot;
        this.bump15.yRot = this.head.yRot;
        this.bump16.yRot = this.head.yRot;
        this.nose.xRot = this.head.xRot = (float) Math.toRadians(headPitch) * 0.45f;
        this.chorn.xRot = this.head.xRot;
        this.lhorn.xRot = this.head.xRot - 0.16f;
        this.rhorn.xRot = this.head.xRot - 0.16f;
        this.bump1.xRot = this.guard.xRot = this.head.xRot - 0.262f;
        this.bump2.xRot = this.guard.xRot;
        this.bump3.xRot = this.guard.xRot;
        this.bump4.xRot = this.guard.xRot;
        this.bump5.xRot = this.guard.xRot;
        this.bump6.xRot = this.guard.xRot;
        this.bump7.xRot = this.guard.xRot;
        this.bump8.xRot = this.guard.xRot;
        this.bump9.xRot = this.guard.xRot;
        this.bump10.xRot = this.guard.xRot;
        this.bump11.xRot = this.guard.xRot;
        this.bump12.xRot = this.guard.xRot;
        this.bump13.xRot = this.guard.xRot;
        this.bump14.xRot = this.guard.xRot;
        this.bump15.xRot = this.guard.xRot;
        this.bump16.xRot = this.guard.xRot;
        newangle = entity.getAttacking() != 0 ? Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f : Mth.cos((float)(ageInTicks * 0.3f * this.wingspeed)) * 3.1415927f * 0.05f;
        this.tail.yRot = newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.02f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.tail.xRot = newangle + 0.28f;
    }






















    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.guard.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lhorn.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rhorn.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chorn.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump13.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump14.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump15.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bump16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
