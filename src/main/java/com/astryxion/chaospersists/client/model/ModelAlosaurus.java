package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Alosaurus;
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

public class ModelAlosaurus extends EntityModel<Alosaurus> {
    private final float wingspeed;
    private final ModelPart Shape18;
    private final ModelPart Shape19;
    private final ModelPart Shape20;
    private final ModelPart Shape21;
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart jaw;
    private final ModelPart leftleg;
    private final ModelPart leftleg2;
    private final ModelPart leftleg3;
    private final ModelPart Shape11;
    private final ModelPart rightleg;
    private final ModelPart rightleg2;
    private final ModelPart rightleg3;
    private final ModelPart leftleg4;
    private final ModelPart rightleg4;
    private final ModelPart Shape17;

    public ModelAlosaurus(float f1) {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot(), f1);
    }

    public ModelAlosaurus(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Shape18 = root.getChild("Shape18");
        this.Shape19 = root.getChild("Shape19");
        this.Shape20 = root.getChild("Shape20");
        this.Shape21 = root.getChild("Shape21");
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.jaw = root.getChild("jaw");
        this.leftleg = root.getChild("leftleg");
        this.leftleg2 = root.getChild("leftleg2");
        this.leftleg3 = root.getChild("leftleg3");
        this.Shape11 = root.getChild("Shape11");
        this.rightleg = root.getChild("rightleg");
        this.rightleg2 = root.getChild("rightleg2");
        this.rightleg3 = root.getChild("rightleg3");
        this.leftleg4 = root.getChild("leftleg4");
        this.rightleg4 = root.getChild("rightleg4");
        this.Shape17 = root.getChild("Shape17");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Shape18", CubeListBuilder.create().texOffs(91, 114).addBox(0.0f, 0.0f, 0.0f, 2, 4, 5), PartPose.offsetAndRotation(3.3f, -25.0f, -27.0f, 0.5759587f, 0.0f, 0.5585054f));
        partdefinition.addOrReplaceChild("Shape19", CubeListBuilder.create().texOffs(71, 114).addBox(0.0f, 0.0f, 0.0f, 2, 4, 5), PartPose.offsetAndRotation(-4.0f, -24.0f, -28.0f, 0.5759587f, 0.0f, -0.5585054f));
        partdefinition.addOrReplaceChild("Shape20", CubeListBuilder.create().texOffs(91, 30).addBox(0.0f, 0.0f, 0.0f, 2, 7, 5), PartPose.offsetAndRotation(5.0f, -8.0f, -6.0f, 0.3839724f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape21", CubeListBuilder.create().texOffs(93, 46).addBox(-2.0f, 0.0f, 0.0f, 2, 7, 5), PartPose.offsetAndRotation(-4.0f, -8.0f, -6.0f, 0.3839724f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0f, 0.0f, 0.0f, 10, 18, 31), PartPose.offsetAndRotation(2.5f, -19.0f, -8.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(62, 0).addBox(-5.0f, 0.0f, 0.0f, 10, 11, 11), PartPose.offsetAndRotation(0.5f, -19.0f, 23.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(10, 54).addBox(-3.0f, 0.0f, 0.0f, 7, 7, 25), PartPose.offsetAndRotation(0.0f, -19.0f, 34.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(68, 88).addBox(-5.0f, 0.0f, 0.0f, 8, 9, 16), PartPose.offsetAndRotation(1.5f, -25.0f, -16.0f, -0.4014257f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(75, 65).addBox(0.0f, 0.0f, 0.0f, 9, 9, 12), PartPose.offsetAndRotation(-4.0f, -25.0f, -27.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 50).addBox(0.0f, 0.0f, 0.0f, 7, 9, 9), PartPose.offsetAndRotation(-3.0f, -25.0f, -36.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 86).addBox(-5.0f, 0.0f, -10.0f, 7, 1, 13), PartPose.offsetAndRotation(2.0f, -15.0f, -24.0f, 0.5201081f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0f, 0.0f, 0.0f, 3, 16, 10), PartPose.offsetAndRotation(6.0f, -10.0f, 11.0f, -0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(0, 106).addBox(-1.0f, 12.0f, -8.0f, 3, 15, 5), PartPose.offsetAndRotation(6.0f, -10.0f, 11.0f, 0.5061455f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg3", CubeListBuilder.create().texOffs(112, 89).addBox(-1.0f, 19.0f, 16.0f, 3, 9, 3), PartPose.offsetAndRotation(6.0f, -10.0f, 11.0f, -0.4014257f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(0, 72).addBox(0.0f, 0.0f, 0.0f, 2, 10, 2), PartPose.offsetAndRotation(5.0f, -5.0f, -3.0f, -0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(54, 51).addBox(0.0f, 0.0f, 0.0f, 3, 16, 10), PartPose.offsetAndRotation(-7.0f, -10.0f, 11.0f, -0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(23, 106).addBox(0.0f, 12.0f, -8.0f, 3, 15, 5), PartPose.offsetAndRotation(-7.0f, -10.0f, 11.0f, 0.5061455f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg3", CubeListBuilder.create().texOffs(70, 90).addBox(0.0f, 19.0f, 16.0f, 3, 9, 3), PartPose.offsetAndRotation(-7.0f, -10.0f, 11.0f, -0.4014257f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg4", CubeListBuilder.create().texOffs(42, 113).addBox(-1.0f, 31.0f, -1.0f, 3, 3, 8), PartPose.offsetAndRotation(6.0f, -10.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg4", CubeListBuilder.create().texOffs(44, 93).addBox(0.0f, 31.0f, -1.0f, 3, 3, 8), PartPose.offsetAndRotation(-7.0f, -10.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape17", CubeListBuilder.create().texOffs(112, 60).addBox(-2.0f, 0.0f, 0.0f, 2, 10, 2), PartPose.offsetAndRotation(-4.0f, -3.533333f, -3.0f, -0.5235988f, 0.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Alosaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = limbSwingAmount > 0.1f ? Mth.cos(ageInTicks * 1.3f * this.wingspeed) * ((float) Math.PI * 0.25f) * limbSwingAmount : 0.0f;
        this.rightleg.xRot = -0.174f + newangle;
        this.rightleg2.xRot = 0.506f + newangle;
        this.rightleg3.xRot = -0.401f + newangle;
        this.rightleg4.xRot = newangle;
        this.leftleg.xRot = -0.174f - newangle;
        this.leftleg2.xRot = 0.506f - newangle;
        this.leftleg3.xRot = -0.401f - newangle;
        this.leftleg4.xRot = -newangle;
        this.jaw.xRot = entity.getAttacking() != 0 ? 0.52f + Mth.cos(ageInTicks * 0.45f) * ((float) Math.PI * 0.18f) : 0.1f;
        this.Shape17.xRot = -0.523f + Mth.cos(ageInTicks * 0.1f) * ((float) Math.PI * 0.05f);
        this.Shape11.xRot = -0.523f + Mth.cos(ageInTicks * 0.1f) * ((float) Math.PI * 0.05f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape18.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape19.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape20.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
