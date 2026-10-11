package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Flounder;
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

public class ModelFlounder extends EntityModel<Flounder> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart rfin;
    private final ModelPart lfin;

    public ModelFlounder() {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelFlounder(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.rfin = root.getChild("rfin");
        this.lfin = root.getChild("lfin");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0f, 0.0f, -5.0f, 8, 1, 12), PartPose.offset(0.0f, 22.0f, 0.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 5).addBox(-2.0f, 0.0f, 0.0f, 4, 1, 2), PartPose.offset(0.0f, 22.0f, -7.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0f, 0.0f, 0.0f, 4, 1, 2), PartPose.offset(0.0f, 22.0f, 7.0f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(30, 4).addBox(-3.0f, 0.0f, 2.0f, 6, 1, 3), PartPose.offset(0.0f, 22.0f, 7.0f));
        root.addOrReplaceChild("rfin", CubeListBuilder.create().texOffs(12, 0).addBox(-3.0f, 0.0f, 0.0f, 3, 1, 2), PartPose.offset(-4.0f, 22.0f, -2.0f));
        root.addOrReplaceChild("lfin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 3, 1, 2), PartPose.offset(4.0f, 22.0f, -2.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Flounder entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle;
        float newangle2;
        if ((double)limbSwingAmount > 0.1) {
            newangle = Mth.cos((float)(ageInTicks * 1.3f)) * 3.1415927f * 0.25f * limbSwingAmount;
            newangle2 = Mth.cos((float)(ageInTicks * 1.7f)) * 3.1415927f * 0.25f * limbSwingAmount;
        } else {
            newangle = 0.0f;
            newangle2 = 0.0f;
        }
        this.lfin.zRot = newangle;
        this.rfin.zRot = newangle2;
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 1.2f)) * 3.1415927f * 0.25f * limbSwingAmount : Mth.cos((float)(ageInTicks * 0.7f)) * 3.1415927f * 0.05f;
        this.tail1.xRot = this.tail2.xRot = newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
