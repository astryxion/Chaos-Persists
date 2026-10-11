package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.EntityMosquito;
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

public class ModelMosquito extends EntityModel<EntityMosquito> {
    private final ModelPart body;
    private final ModelPart leftwing1;
    private final ModelPart rightwing1;
    private final ModelPart leftwing2;
    private final ModelPart rightwing2;

    public ModelMosquito() {
        this(LayerDefinition.create(createMesh(), 32, 32).bakeRoot());
    }

    public ModelMosquito(ModelPart root) {
        this.body = root.getChild("body");
        this.leftwing1 = root.getChild("leftwing1");
        this.rightwing1 = root.getChild("rightwing1");
        this.leftwing2 = root.getChild("leftwing2");
        this.rightwing2 = root.getChild("rightwing2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(8, 18).addBox(0.0f, 0.0f, -2.0f, 1.0f, 1.0f, 8.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "leftwing1",
                CubeListBuilder.create().texOffs(16, 13).addBox(1.0f, 0.0f, -1.0f, 3.0f, 1.0f, 3.0f),
                PartPose.offset(1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "rightwing1",
                CubeListBuilder.create().texOffs(2, 13).addBox(-4.0f, 0.0f, -1.0f, 3.0f, 1.0f, 3.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "leftwing2",
                CubeListBuilder.create().texOffs(15, 8).addBox(0.0f, 0.0f, 0.0f, 5.0f, 1.0f, 1.0f),
                PartPose.offset(1.0f, 17.0f, 0.0f));
        root.addOrReplaceChild(
                "rightwing2",
                CubeListBuilder.create().texOffs(2, 8).addBox(-5.0f, 0.0f, 0.0f, 5.0f, 1.0f, 1.0f),
                PartPose.offset(0.0f, 17.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(EntityMosquito entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float flap = Mth.cos(ageInTicks * 3.0f) * ((float) Math.PI * 0.25f);
        this.rightwing2.zRot = flap;
        this.rightwing1.zRot = flap;
        this.leftwing1.zRot = -flap;
        this.leftwing2.zRot = -flap;
    }

    @Override
    public void renderToBuffer(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
