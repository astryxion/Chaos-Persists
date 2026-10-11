package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.CloudShark;
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

public class ModelCloudShark extends EntityModel<CloudShark> {
    private final float wingspeed;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart topfin;
    private final ModelPart bbody;
    private final ModelPart fins;
    private final ModelPart leftfin;
    private final ModelPart rightfin;

    public ModelCloudShark(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot(), f1);
    }

    public ModelCloudShark(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.jaw = root.getChild("jaw");
        this.topfin = root.getChild("topfin");
        this.bbody = root.getChild("bbody");
        this.fins = root.getChild("fins");
        this.leftfin = root.getChild("leftfin");
        this.rightfin = root.getChild("rightfin");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 6, 8, 15), PartPose.offset(-4.0f, 11.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 51).addBox(-2.5f, 0.0f, -8.0f, 5, 5, 8), PartPose.offset(-1.0f, 11.0f, 0.0f));
        partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(42, 0).addBox(-2.5f, 0.0f, -6.0f, 5, 2, 6), PartPose.offsetAndRotation(-1.0f, 15.0f, 0.0f, 0.5056291f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("topfin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1, 3, 6), PartPose.offsetAndRotation(-1.5f, 11.0f, 5.0f, 0.935765f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("bbody", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0f, 0.0f, 0.0f, 4, 8, 6), PartPose.offset(-1.0f, 11.0f, 15.0f));
        partdefinition.addOrReplaceChild("fins", CubeListBuilder.create().texOffs(0, 24).addBox(0.0f, 0.0f, 0.0f, 0, 10, 10), PartPose.offsetAndRotation(-1.0f, 16.0f, 16.0f, 0.9220296f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftfin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 0, 3, 7), PartPose.offsetAndRotation(2.0f, 16.0f, 6.0f, -0.6108652f, 1.134464f, -0.6108652f));
        partdefinition.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 0, 3, 7), PartPose.offsetAndRotation(-4.0f, 16.0f, 6.0f, -0.6283185f, -1.134464f, 0.6108652f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(CloudShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f2 = ageInTicks;
        float newangle = Mth.cos(f2 * 0.7f * this.wingspeed) * (float) Math.PI * 0.15f;
        this.leftfin.yRot = 1.15f + newangle;
        newangle = Mth.cos(f2 * 1.5f * this.wingspeed) * (float) Math.PI * 0.15f;
        this.rightfin.yRot = -0.9f + newangle;
        this.fins.yRot = newangle = Mth.cos(f2 * 1.5f * this.wingspeed) * (float) Math.PI * 0.25f;
        newangle = Mth.cos(f2 * 0.5f * this.wingspeed) * (float) Math.PI * 0.1f;
        this.jaw.xRot = 0.5f + newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bbody.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.fins.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
