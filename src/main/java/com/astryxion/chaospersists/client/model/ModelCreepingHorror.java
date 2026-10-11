package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.CreepingHorror;
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

public class ModelCreepingHorror extends EntityModel<CreepingHorror> {
    private final ModelPart body;
    private final ModelPart leg1;
    private final ModelPart leg1part2;
    private final ModelPart leg2;
    private final ModelPart leg2part2;
    private final ModelPart leg3;
    private final ModelPart leg3part2;
    private final ModelPart leg4;
    private final ModelPart leg4part2;
    private final ModelPart tailseg1;
    private final ModelPart tailseg2;
    private final ModelPart tailseg3;
    private final ModelPart pincer1;
    private final ModelPart pincer1part2;
    private final ModelPart pincer2;
    private final ModelPart pincer2part2;
    private final ModelPart spike1;
    private final ModelPart spike2;
    private final ModelPart spike3;
    private final ModelPart spike4;
    private final ModelPart spike5;
    private final ModelPart insides1;
    private final ModelPart insides2;
    private final ModelPart insides3;
    private final ModelPart insides4;
    private final ModelPart insides5;

    public ModelCreepingHorror(float f1) {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelCreepingHorror(ModelPart root) {
        this.body = root.getChild("body");
        this.leg1 = root.getChild("leg1");
        this.leg1part2 = root.getChild("leg1part2");
        this.leg2 = root.getChild("leg2");
        this.leg2part2 = root.getChild("leg2part2");
        this.leg3 = root.getChild("leg3");
        this.leg3part2 = root.getChild("leg3part2");
        this.leg4 = root.getChild("leg4");
        this.leg4part2 = root.getChild("leg4part2");
        this.tailseg1 = root.getChild("tailseg1");
        this.tailseg2 = root.getChild("tailseg2");
        this.tailseg3 = root.getChild("tailseg3");
        this.pincer1 = root.getChild("pincer1");
        this.pincer1part2 = root.getChild("pincer1part2");
        this.pincer2 = root.getChild("pincer2");
        this.pincer2part2 = root.getChild("pincer2part2");
        this.spike1 = root.getChild("spike1");
        this.spike2 = root.getChild("spike2");
        this.spike3 = root.getChild("spike3");
        this.spike4 = root.getChild("spike4");
        this.spike5 = root.getChild("spike5");
        this.insides1 = root.getChild("insides1");
        this.insides2 = root.getChild("insides2");
        this.insides3 = root.getChild("insides3");
        this.insides4 = root.getChild("insides4");
        this.insides5 = root.getChild("insides5");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 30).addBox(-4.0f, -5.0f, -4.0f, 8, 8, 8), PartPose.offset(0.0f, 20.0f, 0.0f));
        partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(65, 0).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2), PartPose.offsetAndRotation(4.0f, 18.0f, -2.0f, 0.0f, 0.5759587f, 0.1919862f));
        partdefinition.addOrReplaceChild("leg1part2", CubeListBuilder.create().texOffs(37, 5).addBox(13.01f, -1.01f, -1.0f, 2, 5, 2), PartPose.offsetAndRotation(4.0f, 18.0f, -2.0f, 0.0f, 0.5759587f, 0.1919862f));
        partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(65, 0).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2), PartPose.offsetAndRotation(4.0f, 18.0f, 2.0f, 0.0f, -0.5759587f, 0.1919862f));
        partdefinition.addOrReplaceChild("leg2part2", CubeListBuilder.create().texOffs(37, 5).addBox(13.01f, -1.01f, -1.0f, 2, 5, 2), PartPose.offsetAndRotation(4.0f, 18.0f, 2.0f, 0.0f, -0.5759587f, 0.1919862f));
        partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(28, 0).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2), PartPose.offsetAndRotation(-4.0f, 18.0f, -2.0f, 0.0f, -0.5759587f, -0.1919862f));
        partdefinition.addOrReplaceChild("leg3part2", CubeListBuilder.create().texOffs(28, 5).addBox(-15.01f, -1.01f, -1.0f, 2, 5, 2), PartPose.offsetAndRotation(-4.0f, 18.0f, -2.0f, 0.0f, -0.5759587f, -0.1919862f));
        partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(28, 0).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2), PartPose.offsetAndRotation(-4.0f, 18.0f, 2.0f, 0.0f, 0.5759587f, -0.1919862f));
        partdefinition.addOrReplaceChild("leg4part2", CubeListBuilder.create().texOffs(28, 5).addBox(-15.01f, -1.01f, -1.0f, 2, 5, 2), PartPose.offsetAndRotation(-4.0f, 18.0f, 2.0f, 0.0f, 0.5759587f, -0.1919862f));
        partdefinition.addOrReplaceChild("tailseg1", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0f, -1.0f, 0.0f, 4, 2, 7), PartPose.offsetAndRotation(0.0f, 17.0f, 3.0f, -0.5576792f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tailseg2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, 3.0f, 7.0f, 2, 1, 11), PartPose.offsetAndRotation(0.0f, 17.0f, 3.0f, -0.0349066f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tailseg3", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5f, 1.0f, 6.0f, 3, 2, 2), PartPose.offsetAndRotation(0.0f, 17.0f, 3.0f, -0.2230717f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("pincer1", CubeListBuilder.create().texOffs(26, 30).addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5), PartPose.offsetAndRotation(-3.0f, 19.0f, -3.0f, 0.0f, -0.2230717f, 0.0f));
        partdefinition.addOrReplaceChild("pincer1part2", CubeListBuilder.create().texOffs(26, 30).addBox(-0.5f, -0.5f, -5.01f, 2, 1, 0), PartPose.offsetAndRotation(-3.0f, 19.0f, -3.0f, 0.0f, -0.2230717f, 0.0f));
        partdefinition.addOrReplaceChild("pincer2", CubeListBuilder.create().texOffs(26, 30).addBox(-0.5f, -0.5f, -5.0f, 1, 1, 5), PartPose.offsetAndRotation(3.0f, 19.0f, -3.0f, 0.0f, 0.2230705f, 0.0f));
        partdefinition.addOrReplaceChild("pincer2part2", CubeListBuilder.create().texOffs(26, 28).addBox(-1.5f, -0.5f, -5.01f, 2, 1, 0), PartPose.offsetAndRotation(3.0f, 19.0f, -3.0f, 0.0f, 0.2230705f, 0.0f));
        partdefinition.addOrReplaceChild("spike1", CubeListBuilder.create().texOffs(26, 13).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6), PartPose.offsetAndRotation(-3.0f, 16.0f, -2.0f, 0.7063936f, -0.2602503f, 0.0f));
        partdefinition.addOrReplaceChild("spike2", CubeListBuilder.create().texOffs(26, 13).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6), PartPose.offsetAndRotation(-1.0f, 16.0f, 1.0f, 0.7063936f, -0.111544f, 0.0f));
        partdefinition.addOrReplaceChild("spike3", CubeListBuilder.create().texOffs(26, 13).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6), PartPose.offsetAndRotation(1.0f, 16.0f, 1.0f, 0.7063936f, 0.1115358f, 0.0f));
        partdefinition.addOrReplaceChild("spike4", CubeListBuilder.create().texOffs(26, 13).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6), PartPose.offsetAndRotation(3.0f, 16.0f, -2.0f, 0.7063936f, 0.260246f, 0.0f));
        partdefinition.addOrReplaceChild("spike5", CubeListBuilder.create().texOffs(26, 13).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 6), PartPose.offsetAndRotation(0.0f, 16.0f, -3.0f, 0.7063936f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("insides1", CubeListBuilder.create().texOffs(0, 30).addBox(-2.0f, -3.0f, -3.0f, 4, 4, 0), PartPose.offset(0.0f, 20.0f, 0.0f));
        partdefinition.addOrReplaceChild("insides2", CubeListBuilder.create().texOffs(-1, 29).addBox(-2.0f, -3.0f, -4.0f, 4, 0, 1), PartPose.offset(0.0f, 20.0f, 0.0f));
        partdefinition.addOrReplaceChild("insides3", CubeListBuilder.create().texOffs(-1, 29).addBox(-2.0f, 1.0f, -4.0f, 4, 0, 1), PartPose.offset(0.0f, 20.0f, 0.0f));
        partdefinition.addOrReplaceChild("insides4", CubeListBuilder.create().texOffs(0, 29).addBox(-2.0f, -3.0f, -4.0f, 0, 4, 1), PartPose.offset(0.0f, 20.0f, 0.0f));
        partdefinition.addOrReplaceChild("insides5", CubeListBuilder.create().texOffs(0, 29).addBox(2.0f, -3.0f, -4.0f, 0, 4, 1), PartPose.offset(0.0f, 20.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(CreepingHorror entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float newangle = Mth.cos(f2 * 1.25f) * (float) Math.PI * 0.35f * f1;
        this.leg1part2.yRot = this.leg1.yRot = 0.576f + newangle;
        this.leg2part2.yRot = this.leg2.yRot = -0.576f - newangle;
        this.leg3part2.yRot = this.leg3.yRot = -0.576f - newangle;
        this.leg4part2.yRot = this.leg4.yRot = 0.576f + newangle;
        this.pincer1.yRot = newangle = Mth.cos(f2 * 0.48f) * (float) Math.PI * 0.15f;
        this.pincer1part2.yRot = newangle;
        this.pincer2.yRot = -newangle;
        this.pincer2part2.yRot = -newangle;
        newangle = Mth.cos(f2 * 0.11f) * (float) Math.PI * 0.25f;
        newangle = Mth.abs(newangle);
        this.tailseg1.xRot = -0.55f + newangle;
        this.tailseg3.xRot = -0.22f + newangle;
        this.tailseg2.xRot = newangle;
        newangle = Mth.cos(f2 * 0.81f) * (float) Math.PI * 0.08f;
        this.spike1.xRot = 0.7f + newangle;
        newangle = Mth.cos(f2 * 0.87f) * (float) Math.PI * 0.08f;
        this.spike2.xRot = 0.7f + newangle;
        newangle = Mth.cos(f2 * 0.99f) * (float) Math.PI * 0.08f;
        this.spike3.xRot = 0.7f + newangle;
        newangle = Mth.cos(f2 * 0.103f) * (float) Math.PI * 0.08f;
        this.spike4.xRot = 0.7f + newangle;
        newangle = Mth.cos(f2 * 0.107f) * (float) Math.PI * 0.08f;
        this.spike5.xRot = 0.7f + newangle;
        this.spike1.yRot = newangle = Mth.cos(f2 * 1.11f) * (float) Math.PI * 0.08f;
        this.spike2.yRot = newangle = Mth.cos(f2 * 1.17f) * (float) Math.PI * 0.08f;
        this.spike3.yRot = newangle = Mth.cos(f2 * 1.25f) * (float) Math.PI * 0.08f;
        this.spike4.yRot = newangle = Mth.cos(f2 * 1.28f) * (float) Math.PI * 0.08f;
        this.spike5.yRot = newangle = Mth.cos(f2 * 1.31f) * (float) Math.PI * 0.08f;
        this.spike1.zRot = newangle = Mth.cos(f2 * 1.41f) * (float) Math.PI * 0.08f;
        this.spike2.zRot = newangle = Mth.cos(f2 * 1.47f) * (float) Math.PI * 0.08f;
        this.spike3.zRot = newangle = Mth.cos(f2 * 1.55f) * (float) Math.PI * 0.08f;
        this.spike4.zRot = newangle = Mth.cos(f2 * 1.58f) * (float) Math.PI * 0.08f;
        this.spike5.zRot = newangle = Mth.cos(f2 * 1.61f) * (float) Math.PI * 0.08f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg3part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg4part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailseg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailseg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailseg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer1part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pincer2part2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spike5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.insides5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
