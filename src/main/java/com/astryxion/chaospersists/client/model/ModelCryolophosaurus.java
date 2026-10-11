package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Cryolophosaurus;
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

public class ModelCryolophosaurus extends EntityModel<Cryolophosaurus> {
    private final float wingspeed;
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart jaw;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;
    private final ModelPart Shape9;
    private final ModelPart rightleg;
    private final ModelPart Shape11;
    private final ModelPart rightleg2;
    private final ModelPart rightleg3;
    private final ModelPart rightleg4;
    private final ModelPart leftleg;
    private final ModelPart Shape16;
    private final ModelPart Shape17;
    private final ModelPart leftleg2;
    private final ModelPart leftleg3;
    private final ModelPart leftleg4;

    public ModelCryolophosaurus(float f1) {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot(), f1);
    }

    public ModelCryolophosaurus(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.jaw = root.getChild("jaw");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
        this.Shape9 = root.getChild("Shape9");
        this.rightleg = root.getChild("rightleg");
        this.Shape11 = root.getChild("Shape11");
        this.rightleg2 = root.getChild("rightleg2");
        this.rightleg3 = root.getChild("rightleg3");
        this.rightleg4 = root.getChild("rightleg4");
        this.leftleg = root.getChild("leftleg");
        this.Shape16 = root.getChild("Shape16");
        this.Shape17 = root.getChild("Shape17");
        this.leftleg2 = root.getChild("leftleg2");
        this.leftleg3 = root.getChild("leftleg3");
        this.leftleg4 = root.getChild("leftleg4");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 8, 9, 18), PartPose.offset(0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(53, 0).addBox(0.0f, 0.0f, 0.0f, 6, 4, 11), PartPose.offsetAndRotation(1.0f, -2.0f, -7.0f, -0.2268928f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 41).addBox(0.0f, 0.0f, 0.0f, 6, 4, 10), PartPose.offset(1.0f, -2.0f, -15.0f));
        partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 30).addBox(0.0f, 0.0f, 0.0f, 4, 9, 1), PartPose.offsetAndRotation(2.0f, 1.0f, -8.0f, -1.256637f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(91, 0).addBox(0.0f, 0.0f, 0.0f, 6, 6, 7), PartPose.offset(1.0f, 0.0f, 18.0f));
        partdefinition.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(36, 31).addBox(0.0f, 0.0f, 0.0f, 4, 4, 14), PartPose.offset(2.0f, 0.0f, 25.0f));
        partdefinition.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(43, 8).addBox(0.0f, 0.0f, 0.0f, 1, 4, 2), PartPose.offsetAndRotation(-1.0f, 8.0f, 0.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(9, 0).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(-1.0f, 11.0f, 1.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 2, 4, 1), PartPose.offsetAndRotation(3.0f, -4.0f, -9.0f, -0.9424778f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 58).addBox(0.0f, 0.0f, 0.0f, 2, 10, 6), PartPose.offsetAndRotation(-1.0f, 2.0f, 12.0f, -0.2792527f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(39, 0).addBox(0.0f, 0.0f, 0.0f, 4, 3, 3), PartPose.offset(2.0f, -1.0f, -18.0f));
        partdefinition.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(0, 77).addBox(0.0f, 7.0f, -5.0f, 2, 10, 3), PartPose.offsetAndRotation(-1.0f, 2.0f, 12.0f, 0.3839724f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg3", CubeListBuilder.create().texOffs(35, 31).addBox(0.0f, 10.0f, 12.0f, 2, 7, 2), PartPose.offsetAndRotation(-1.0f, 2.0f, 12.0f, -0.6806784f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg4", CubeListBuilder.create().texOffs(68, 55).addBox(0.0f, 20.0f, -5.0f, 2, 2, 6), PartPose.offset(-1.0f, 2.0f, 12.0f));
        partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(22, 58).addBox(0.0f, 0.0f, 0.0f, 2, 10, 6), PartPose.offsetAndRotation(7.0f, 2.0f, 12.0f, -0.2792527f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape16", CubeListBuilder.create().texOffs(0, 8).addBox(0.0f, 0.0f, 0.0f, 1, 4, 2), PartPose.offsetAndRotation(8.0f, 8.0f, 0.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape17", CubeListBuilder.create().texOffs(9, 9).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(8.0f, 11.0f, 1.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(16, 77).addBox(0.0f, 7.0f, -5.0f, 2, 10, 3), PartPose.offsetAndRotation(7.0f, 2.0f, 12.0f, 0.3839724f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg3", CubeListBuilder.create().texOffs(67, 31).addBox(0.0f, 10.0f, 12.0f, 2, 7, 2), PartPose.offsetAndRotation(7.0f, 2.0f, 12.0f, -0.6806784f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg4", CubeListBuilder.create().texOffs(47, 56).addBox(0.0f, 20.0f, -5.0f, 2, 2, 6), PartPose.offset(7.0f, 2.0f, 12.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Cryolophosaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = limbSwingAmount > 0.1f ? Mth.cos(ageInTicks * 1.3f * this.wingspeed) * ((float) Math.PI * 0.25f) * limbSwingAmount : 0.0f;
        this.rightleg.xRot = -0.2792527f + newangle;
        this.rightleg2.xRot = 0.384f + newangle;
        this.rightleg3.xRot = -0.68f + newangle;
        this.rightleg4.xRot = newangle;
        this.leftleg.xRot = -0.2792527f - newangle;
        this.leftleg2.xRot = 0.384f - newangle;
        this.leftleg3.xRot = -0.68f - newangle;
        this.leftleg4.xRot = -newangle;
        this.jaw.xRot = -1.15f + Mth.cos(ageInTicks * 0.28f) * ((float) Math.PI * 0.1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
