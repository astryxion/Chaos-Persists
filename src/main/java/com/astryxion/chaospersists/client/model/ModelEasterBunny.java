package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.EasterBunny;
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

public class ModelEasterBunny extends EntityModel<EasterBunny> {
    private final float wingspeed;
    private EasterBunny animEntity;
    private float animAgeInTicks;
    private float animLimbSwingAmount;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart lfoot;
    private final ModelPart lleg;
    private final ModelPart upperbody;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart lear;
    private final ModelPart lpaw;
    private final ModelPart rleg;
    private final ModelPart rfoot;
    private final ModelPart rear;
    private final ModelPart rpaw;
    public ModelEasterBunny(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 128).bakeRoot());
    }

    public ModelEasterBunny(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.lfoot = root.getChild("lfoot");
        this.lleg = root.getChild("lleg");
        this.upperbody = root.getChild("upperbody");
        this.head = root.getChild("head");
        this.nose = root.getChild("nose");
        this.lear = root.getChild("lear");
        this.lpaw = root.getChild("lpaw");
        this.rleg = root.getChild("rleg");
        this.rfoot = root.getChild("rfoot");
        this.rear = root.getChild("rear");
        this.rpaw = root.getChild("rpaw");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 44).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 7), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 58).addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4), PartPose.offsetAndRotation(0.0f, 19.0f, 6.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lfoot", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0f, 2.0f, -5.0f, 3, 1, 7), PartPose.offsetAndRotation(3.0f, 21.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(0, 20).addBox(0.0f, -2.0f, -2.0f, 1, 4, 5), PartPose.offsetAndRotation(3.0f, 21.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(42, 27).addBox(-2.0f, 0.0f, -2.0f, 4, 1, 5), PartPose.offsetAndRotation(0.0f, 16.0f, -1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 17).addBox(-2.5f, 0.0f, -2.0f, 5, 4, 5), PartPose.offsetAndRotation(0.0f, 12.0f, -2.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(44, 9).addBox(-1.0f, -1.0f, 0.0f, 2, 2, 1), PartPose.offsetAndRotation(0.0f, 15.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lear", CubeListBuilder.create().texOffs(54, 0).addBox(0.0f, -10.0f, -1.0f, 1, 10, 3), PartPose.offsetAndRotation(2.0f, 13.0f, -1.0f, -0.2268928f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lpaw", CubeListBuilder.create().texOffs(6, 7).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(0.5f, 19.0f, -4.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(21, 20).addBox(0.0f, -2.0f, -2.0f, 1, 4, 5), PartPose.offsetAndRotation(-4.0f, 21.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rfoot", CubeListBuilder.create().texOffs(21, 30).addBox(-1.0f, 2.0f, -5.0f, 3, 1, 7), PartPose.offsetAndRotation(-4.0f, 21.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rear", CubeListBuilder.create().texOffs(32, 0).addBox(0.0f, -10.0f, -1.0f, 1, 10, 3), PartPose.offsetAndRotation(-3.0f, 13.0f, -1.0f, -0.418879f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rpaw", CubeListBuilder.create().texOffs(0, 7).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(-1.5f, 19.0f, -4.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(EasterBunny entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.animAgeInTicks = ageInTicks;
        this.animLimbSwingAmount = limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        EasterBunny entity = this.animEntity;
        float ageInTicks = this.animAgeInTicks;
        float limbSwingAmount = this.animLimbSwingAmount;
float newangle = 0.0f;
        float newangle2 = 0.0f;
        if ((double)limbSwingAmount > 0.1) {
            newangle = Mth.cos((float)(ageInTicks * 2.6f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount;
            newangle2 = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.1f * limbSwingAmount;
        } else {
            newangle = 0.0f;
            newangle2 = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.01f;
        }
        this.lleg.xRot = this.lfoot.xRot = newangle;
        this.rleg.xRot = this.rfoot.xRot = - newangle;
        this.lear.xRot = -0.226f + newangle2;
        this.rear.xRot = -0.418f - newangle2;
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.upperbody.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lpaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rpaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
