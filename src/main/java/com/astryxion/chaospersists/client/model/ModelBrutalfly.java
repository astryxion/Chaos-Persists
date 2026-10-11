package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Brutalfly;
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

public class ModelBrutalfly extends EntityModel<Brutalfly> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart leftwing;
    private final ModelPart rightwing;
    private final ModelPart leftwing2;
    private final ModelPart rightwing2;
    private final ModelPart leftwing3;
    private final ModelPart rightwing3;
    private final ModelPart head;
    private final ModelPart leftwing4;
    private final ModelPart rightwing4;
    private final ModelPart leftwing5;
    private final ModelPart leftwing6;
    private final ModelPart rightwing5;
    private final ModelPart rightwing6;
    public ModelBrutalfly(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelBrutalfly(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.leftwing = root.getChild("leftwing");
        this.rightwing = root.getChild("rightwing");
        this.leftwing2 = root.getChild("leftwing2");
        this.rightwing2 = root.getChild("rightwing2");
        this.leftwing3 = root.getChild("leftwing3");
        this.rightwing3 = root.getChild("rightwing3");
        this.head = root.getChild("head");
        this.leftwing4 = root.getChild("leftwing4");
        this.rightwing4 = root.getChild("rightwing4");
        this.leftwing5 = root.getChild("leftwing5");
        this.leftwing6 = root.getChild("leftwing6");
        this.rightwing5 = root.getChild("rightwing5");
        this.rightwing6 = root.getChild("rightwing6");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(21, 19).addBox(0.0f, 0.0f, -4.0f, 1, 1, 8), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(43, 24).addBox(0.0f, 0.0f, -4.0f, 1, 1, 5), PartPose.offsetAndRotation(1.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(43, 17).addBox(-1.0f, 0.0f, -4.0f, 1, 1, 5), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftwing2", CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, 0.0f, -6.0f, 6, 1, 7), PartPose.offsetAndRotation(1.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightwing2", CubeListBuilder.create().texOffs(29, 0).addBox(-7.0f, 0.0f, -6.0f, 6, 1, 7), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftwing3", CubeListBuilder.create().texOffs(0, 9).addBox(0.0f, 0.0f, 1.0f, 5, 1, 5), PartPose.offsetAndRotation(1.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightwing3", CubeListBuilder.create().texOffs(27, 9).addBox(-5.0f, 0.0f, 1.0f, 5, 1, 5), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 11).addBox(0.0f, 0.0f, -6.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 1.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftwing4", CubeListBuilder.create().texOffs(2, 24).addBox(0.0f, 0.0f, 6.0f, 2, 1, 7), PartPose.offsetAndRotation(1.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightwing4", CubeListBuilder.create().texOffs(2, 16).addBox(-2.0f, 0.0f, 6.0f, 2, 1, 7), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftwing5", CubeListBuilder.create().texOffs(21, 16).addBox(1.0f, 0.0f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(1.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftwing6", CubeListBuilder.create().texOffs(50, 10).addBox(7.0f, 0.0f, -6.0f, 2, 1, 1), PartPose.offsetAndRotation(1.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightwing5", CubeListBuilder.create().texOffs(27, 16).addBox(-2.0f, 0.0f, -7.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightwing6", CubeListBuilder.create().texOffs(50, 13).addBox(-9.0f, 0.0f, -6.0f, 2, 1, 1), PartPose.offsetAndRotation(0.0f, 17.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(Brutalfly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightwing2.zRot = this.rightwing.zRot = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.rightwing3.zRot = this.rightwing.zRot;
        this.rightwing4.zRot = this.rightwing.zRot;
        this.rightwing5.zRot = this.rightwing.zRot;
        this.rightwing6.zRot = this.rightwing.zRot;
        this.leftwing.zRot = - this.rightwing.zRot;
        this.leftwing2.zRot = - this.rightwing.zRot;
        this.leftwing3.zRot = - this.rightwing.zRot;
        this.leftwing4.zRot = - this.rightwing.zRot;
        this.leftwing5.zRot = - this.rightwing.zRot;
        this.leftwing6.zRot = - this.rightwing.zRot;
    }




    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
