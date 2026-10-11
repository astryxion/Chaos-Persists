package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Skate;
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

public class ModelSkate extends EntityModel<Skate> {
    private final ModelPart body;
    private final ModelPart tail1;
    private final ModelPart Shape1;

    public ModelSkate() {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelSkate(ModelPart root) {
        this.body = root.getChild("body");
        this.tail1 = root.getChild("tail1");
        this.Shape1 = root.getChild("Shape1");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0f, 0.0f, -3.0f, 6, 1, 6), PartPose.offsetAndRotation(0.0f, 22.0f, 0.0f, 0.0f, 0.7853982f, 0.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, 0.0f, 0.0f, 1, 1, 11), PartPose.offset(0.0f, 22.0f, 3.0f));
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 21).addBox(-0.5f, 0.0f, 0.0f, 1, 1, 4), PartPose.offsetAndRotation(0.0f, 22.0f, 5.0f, 0.7853982f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Skate entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 1.2f)) * 3.1415927f * 0.15f * limbSwingAmount : Mth.cos((float)(ageInTicks * 0.4f)) * 3.1415927f * 0.05f;
        this.Shape1.xRot = 0.785f + newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
