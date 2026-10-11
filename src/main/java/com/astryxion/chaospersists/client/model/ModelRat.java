package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Rat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelRat extends EntityModel<Rat> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart lfleg;
    private final ModelPart rfleg;
    private final ModelPart lrleg;
    private final ModelPart rrleg;
    private final ModelPart body2;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart lear;
    private final ModelPart rear;

    public ModelRat(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelRat(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.lfleg = root.getChild("lfleg");
        this.rfleg = root.getChild("rfleg");
        this.lrleg = root.getChild("lrleg");
        this.rrleg = root.getChild("rrleg");
        this.body2 = root.getChild("body2");
        this.head = root.getChild("head");
        this.nose = root.getChild("nose");
        this.lear = root.getChild("lear");
        this.rear = root.getChild("rear");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(27, 0).addBox(-2.0f, -1.0f, 0.0f, 5.0f, 3.0f, 10.0f),
                PartPose.offset(0.0f, 20.0f, -3.0f));
        root.addOrReplaceChild(
                "tail1",
                CubeListBuilder.create().texOffs(0, 30).addBox(-0.5f, -1.0f, 0.0f, 2.0f, 2.0f, 9.0f),
                PartPose.offset(0.0f, 21.0f, 7.0f));
        root.addOrReplaceChild(
                "tail2",
                CubeListBuilder.create().texOffs(0, 43).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 12.0f),
                PartPose.offset(0.0f, 21.0f, 16.0f));
        root.addOrReplaceChild(
                "lfleg",
                CubeListBuilder.create().texOffs(0, 14).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f),
                PartPose.offset(2.0f, 22.0f, -2.0f));
        root.addOrReplaceChild(
                "rfleg",
                CubeListBuilder.create().texOffs(10, 14).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f),
                PartPose.offset(-2.0f, 22.0f, -2.0f));
        root.addOrReplaceChild(
                "lrleg",
                CubeListBuilder.create().texOffs(0, 18).addBox(0.0f, 0.0f, 0.0f, 2.0f, 4.0f, 2.0f),
                PartPose.offset(2.0f, 20.0f, 4.0f));
        root.addOrReplaceChild(
                "rrleg",
                CubeListBuilder.create().texOffs(9, 18).addBox(0.0f, 0.0f, 0.0f, 2.0f, 4.0f, 2.0f),
                PartPose.offset(-3.0f, 20.0f, 4.0f));
        root.addOrReplaceChild(
                "body2",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 6.0f),
                PartPose.offset(0.0f, 18.0f, 0.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(27, 17).addBox(-1.0f, -2.0f, -3.0f, 3.0f, 2.0f, 4.0f),
                PartPose.offset(0.0f, 22.0f, -4.0f));
        root.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(27, 25).addBox(0.0f, -1.0f, -5.0f, 1.0f, 1.0f, 2.0f),
                PartPose.offset(0.0f, 22.0f, -4.0f));
        root.addOrReplaceChild(
                "lear",
                CubeListBuilder.create().texOffs(0, 9).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(1.5f, 19.5f, -4.0f));
        root.addOrReplaceChild(
                "rear",
                CubeListBuilder.create().texOffs(5, 9).addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f),
                PartPose.offset(-1.5f, 19.5f, -4.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Rat entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = limbSwingAmount > 0.1f ? Mth.cos(ageInTicks * 1.7f * this.wingspeed) * (float) Math.PI * 0.25f * limbSwingAmount : 0.0f;
        this.rfleg.xRot = newangle;
        this.lfleg.xRot = -newangle;
        this.rrleg.xRot = -newangle;
        this.lrleg.xRot = newangle;
        newangle = entity.getAttacking() != 0
                ? Mth.cos(ageInTicks * 1.5f * this.wingspeed) * (float) Math.PI * 0.25f
                : Mth.cos(ageInTicks * 0.4f * this.wingspeed) * (float) Math.PI * 0.05f;
        this.tail1.yRot = newangle * 0.5f;
        this.tail2.yRot = newangle * 1.25f;
        this.tail2.z = this.tail1.z + Mth.cos(this.tail1.yRot) * 9.0f;
        this.tail2.x = this.tail1.x + Mth.sin(this.tail1.yRot) * 9.0f;
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
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
