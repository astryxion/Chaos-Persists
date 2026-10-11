package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Dragonfly;
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

public class ModelDragonfly extends EntityModel<Dragonfly> {
    private final float wingspeed;
    private final ModelPart Shape1;
    private final ModelPart lfwing;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart rjaw;
    private final ModelPart ljaw;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart Shape10;
    private final ModelPart Shape11;
    private final ModelPart Shape12;
    private final ModelPart Shape13;
    private final ModelPart Shape14;
    private final ModelPart Shape15;
    private final ModelPart Shape16;
    private final ModelPart Shape17;
    private final ModelPart Shape18;
    private final ModelPart Shape19;
    private final ModelPart Shape20;
    private final ModelPart Shape21;
    private final ModelPart Shape22;
    private final ModelPart Shape23;
    private final ModelPart lrwing;
    private final ModelPart rfwing;
    private final ModelPart rrwing;

    public ModelDragonfly(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot(), f1);
    }

    public ModelDragonfly(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Shape1 = root.getChild("Shape1");
        this.lfwing = root.getChild("lfwing");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.rjaw = root.getChild("rjaw");
        this.ljaw = root.getChild("ljaw");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.Shape10 = root.getChild("Shape10");
        this.Shape11 = root.getChild("Shape11");
        this.Shape12 = root.getChild("Shape12");
        this.Shape13 = root.getChild("Shape13");
        this.Shape14 = root.getChild("Shape14");
        this.Shape15 = root.getChild("Shape15");
        this.Shape16 = root.getChild("Shape16");
        this.Shape17 = root.getChild("Shape17");
        this.Shape18 = root.getChild("Shape18");
        this.Shape19 = root.getChild("Shape19");
        this.Shape20 = root.getChild("Shape20");
        this.Shape21 = root.getChild("Shape21");
        this.Shape22 = root.getChild("Shape22");
        this.Shape23 = root.getChild("Shape23");
        this.lrwing = root.getChild("lrwing");
        this.rfwing = root.getChild("rfwing");
        this.rrwing = root.getChild("rrwing");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 5, 4, 7), PartPose.offset(0.0f, 16.0f, 0.0f));
        partdefinition.addOrReplaceChild("lfwing", CubeListBuilder.create().texOffs(0, 33).addBox(0.0f, 0.0f, 0.0f, 10, 1, 3), PartPose.offsetAndRotation(5.0f, 16.0f, 1.0f, 0.0f, 0.4886922f, 0.0f));
        partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0f, 0.0f, -4.0f, 4, 3, 4), PartPose.offsetAndRotation(2.5f, 16.0f, -1.0f, 0.4886922f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(9, 21).addBox(0.0f, 0.0f, 0.0f, 1, 2, 3), PartPose.offsetAndRotation(1.0f, 18.0f, -6.0f, 0.4886922f, 0.1745329f, 0.0f));
        partdefinition.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 21).addBox(0.0f, 0.0f, 0.0f, 1, 2, 3), PartPose.offsetAndRotation(3.0f, 18.0f, -6.0f, 0.4886922f, -0.1745329f, 0.0f));
        partdefinition.addOrReplaceChild("rjaw", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(2.0f, 19.0f, -5.0f, 0.4363323f, 0.1745329f, 0.0f));
        partdefinition.addOrReplaceChild("ljaw", CubeListBuilder.create().texOffs(5, 27).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(3.0f, 19.0f, -5.0f, 0.4363323f, -0.1745329f, 0.0f));
        partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0f, 0.0f, 0.0f, 3, 3, 7), PartPose.offset(2.0f, 16.0f, 7.0f));
        partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(25, 11).addBox(0.0f, 0.0f, 0.0f, 1, 2, 9), PartPose.offset(2.0f, 16.0f, 14.0f));
        partdefinition.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(23, 0).addBox(-1.0f, 0.0f, 0.0f, 1, 4, 1), PartPose.offsetAndRotation(1.0f, 18.0f, 0.0f, -0.2792527f, 0.0f, 0.3490659f));
        partdefinition.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(40, 0).addBox(0.0f, 0.0f, -4.0f, 1, 1, 4), PartPose.offset(-1.0f, 21.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape12", CubeListBuilder.create().texOffs(18, 12).addBox(-1.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, 21.0f, -4.0f, 0.0f, 0.0f, -0.1919862f));
        partdefinition.addOrReplaceChild("Shape13", CubeListBuilder.create().texOffs(18, 0).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1), PartPose.offsetAndRotation(4.0f, 18.0f, 0.0f, -0.2792527f, 0.0f, -0.3490659f));
        partdefinition.addOrReplaceChild("Shape14", CubeListBuilder.create().texOffs(51, 0).addBox(0.0f, 0.0f, -4.0f, 1, 1, 4), PartPose.offset(5.0f, 21.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape15", CubeListBuilder.create().texOffs(13, 12).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offsetAndRotation(5.0f, 21.0f, -4.0f, 0.0f, 0.0f, 0.1919862f));
        partdefinition.addOrReplaceChild("Shape16", CubeListBuilder.create().texOffs(9, 53).addBox(0.0f, 0.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(5.0f, 19.5f, 3.0f, 0.0f, 0.0f, 0.6457718f));
        partdefinition.addOrReplaceChild("Shape17", CubeListBuilder.create().texOffs(0, 56).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offset(6.0f, 21.0f, 3.0f));
        partdefinition.addOrReplaceChild("Shape18", CubeListBuilder.create().texOffs(0, 53).addBox(-3.0f, 0.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(0.0f, 19.5f, 3.0f, 0.0f, 0.0f, -0.6457718f));
        partdefinition.addOrReplaceChild("Shape19", CubeListBuilder.create().texOffs(5, 56).addBox(-1.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offset(-1.0f, 21.0f, 3.0f));
        partdefinition.addOrReplaceChild("Shape20", CubeListBuilder.create().texOffs(9, 61).addBox(0.0f, 0.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(4.0f, 19.5f, 6.0f, 0.0f, -0.6457718f, 0.5061455f));
        partdefinition.addOrReplaceChild("Shape21", CubeListBuilder.create().texOffs(0, 61).addBox(0.0f, 0.0f, 0.0f, 3, 1, 1), PartPose.offsetAndRotation(1.5f, 19.5f, 7.0f, 0.0f, -2.391101f, 0.5061455f));
        partdefinition.addOrReplaceChild("Shape22", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offset(-1.0f, 21.0f, 7.5f));
        partdefinition.addOrReplaceChild("Shape23", CubeListBuilder.create().texOffs(0, 13).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1), PartPose.offset(5.0f, 21.0f, 7.5f));
        partdefinition.addOrReplaceChild("lrwing", CubeListBuilder.create().texOffs(0, 38).addBox(0.0f, 0.0f, -3.0f, 10, 1, 3), PartPose.offsetAndRotation(5.0f, 16.0f, 6.0f, 0.0f, -0.3839724f, 0.0f));
        partdefinition.addOrReplaceChild("rfwing", CubeListBuilder.create().texOffs(0, 48).addBox(-10.0f, 0.0f, 0.0f, 10, 1, 3), PartPose.offsetAndRotation(0.0f, 16.0f, 1.0f, 0.0f, -0.4886922f, 0.0f));
        partdefinition.addOrReplaceChild("rrwing", CubeListBuilder.create().texOffs(0, 43).addBox(-10.0f, 0.0f, -3.0f, 10, 1, 3), PartPose.offsetAndRotation(0.0f, 16.0f, 6.0f, 0.0f, 0.3839724f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Dragonfly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float newangle = 0.0f;
                this.lfwing.zRot = newangle = Mth.cos((float)(f2 * 1.3f * this.wingspeed)) * (float)Math.PI * 0.25f;
                this.rfwing.zRot = - newangle;
                this.lrwing.zRot = newangle + 3.14f;
                this.rrwing.zRot = - newangle + 3.14f;
                this.ljaw.xRot = newangle = Mth.cos((float)(f2 * 0.3f * this.wingspeed)) * (float)Math.PI * 0.1f;
                this.rjaw.xRot = - newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rjaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ljaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape13.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape14.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape15.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape17.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape18.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape19.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape20.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape22.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape23.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
