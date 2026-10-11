package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Whale;
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

public class ModelWhale extends EntityModel<Whale> {
    private final ModelPart belly;
    private final ModelPart body;
    private final ModelPart back;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tailfin1;
    private final ModelPart tailfin2;
    private final ModelPart backfin;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart lfin1;
    private final ModelPart lfin2;
    private final ModelPart rfin1;
    private final ModelPart rfin2;

    public ModelWhale() {
        this(LayerDefinition.create(createMesh(), 256, 256).bakeRoot());
    }

    public ModelWhale(ModelPart root) {
        this.belly = root.getChild("belly");
        this.body = root.getChild("body");
        this.back = root.getChild("back");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tailfin1 = root.getChild("tailfin1");
        this.tailfin2 = root.getChild("tailfin2");
        this.backfin = root.getChild("backfin");
        this.head = root.getChild("head");
        this.jaw = root.getChild("jaw");
        this.lfin1 = root.getChild("lfin1");
        this.lfin2 = root.getChild("lfin2");
        this.rfin1 = root.getChild("rfin1");
        this.rfin2 = root.getChild("rfin2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 92).addBox(-6.0f, 0.0f, 0.0f, 12, 2, 32), PartPose.offset(0.0f, 22.0f, 6.0f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 188).addBox(-10.0f, 0.0f, 0.0f, 20, 12, 52), PartPose.offset(0.0f, 10.0f, 0.0f));
        root.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0f, 0.0f, 0.0f, 8, 2, 40), PartPose.offset(0.0f, 8.0f, 3.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(186, 0).addBox(-6.0f, 0.0f, 0.0f, 12, 7, 14), PartPose.offset(0.0f, 11.0f, 52.0f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(186, 24).addBox(-4.0f, 0.0f, 0.0f, 8, 5, 10), PartPose.offset(0.0f, 12.0f, 66.0f));
        root.addOrReplaceChild("tailfin1", CubeListBuilder.create().texOffs(186, 43).addBox(0.0f, 0.0f, 0.0f, 17, 2, 11), PartPose.offsetAndRotation(2.0f, 13.0f, 74.0f, 0.0872665f, -0.0872665f, 0.0f));
        root.addOrReplaceChild("tailfin2", CubeListBuilder.create().texOffs(186, 59).addBox(-17.0f, 0.0f, 0.0f, 17, 2, 11), PartPose.offsetAndRotation(-2.0f, 13.0f, 74.0f, 0.0872665f, 0.0872665f, 0.0f));
        root.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5f, 0.0f, 0.0f, 1, 4, 8), PartPose.offsetAndRotation(0.0f, 8.0f, 11.0f, 0.3665191f, 0.0f, 0.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 155).addBox(-8.0f, 0.0f, -16.0f, 16, 8, 22), PartPose.offset(0.0f, 11.0f, -6.0f));
        root.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 130).addBox(-7.0f, -1.0f, -20.0f, 14, 2, 20), PartPose.offsetAndRotation(0.0f, 20.0f, 0.0f, 0.0698132f, 0.0f, 0.0f));
        root.addOrReplaceChild("lfin1", CubeListBuilder.create().texOffs(96, 0).addBox(0.0f, -1.0f, -3.0f, 4, 3, 6), PartPose.offsetAndRotation(10.0f, 18.0f, 8.0f, 0.0f, -0.0872665f, 0.0f));
        root.addOrReplaceChild("lfin2", CubeListBuilder.create().texOffs(120, 0).addBox(2.0f, -0.5f, -3.0f, 22, 2, 8), PartPose.offsetAndRotation(10.0f, 18.0f, 8.0f, 0.0f, -0.0872665f, 0.0f));
        root.addOrReplaceChild("rfin1", CubeListBuilder.create().texOffs(96, 12).addBox(-4.0f, -1.0f, -3.0f, 4, 3, 6), PartPose.offsetAndRotation(-10.0f, 18.0f, 8.0f, 0.0f, 0.0872665f, 0.0f));
        root.addOrReplaceChild("rfin2", CubeListBuilder.create().texOffs(120, 13).addBox(-24.0f, -0.5f, -3.0f, 22, 2, 8), PartPose.offsetAndRotation(-10.0f, 18.0f, 8.0f, 0.0f, 0.0872665f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Whale entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 0.3f)) * 3.1415927f * 0.2f * limbSwingAmount : Mth.cos((float)(ageInTicks * 0.08f)) * 3.1415927f * 0.05f;
        this.lfin2.zRot = 0.436f + newangle;
        this.lfin1.zRot = this.lfin2.zRot / 2.0f;
        this.rfin2.zRot = -0.436f - newangle;
        this.rfin1.zRot = this.rfin2.zRot / 2.0f;
        newangle = Mth.cos((float)(ageInTicks * 0.03f)) * 3.1415927f * 0.02f;
        this.jaw.xRot = 0.087f + newangle;
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 0.4f)) * 3.1415927f * 0.16f * limbSwingAmount : Mth.cos((float)(ageInTicks * 0.05f)) * 3.1415927f * 0.03f;
        this.tail1.xRot = newangle * 0.5f;
        this.tail2.xRot = newangle * 1.25f;
        this.tailfin1.xRot = this.tailfin2.xRot = newangle * 2.25f;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.xRot) * 14.0f;
        this.tail2.y = this.tail1.y - (float)Math.sin(this.tail1.xRot) * 14.0f;
        this.tailfin1.z = this.tailfin2.z = this.tail2.z + (float)Math.cos(this.tail2.xRot) * 8.0f;
        this.tailfin1.y = this.tailfin2.y = this.tail2.y - (float)Math.sin(this.tail2.xRot) * 8.0f;

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.belly.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.backfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
