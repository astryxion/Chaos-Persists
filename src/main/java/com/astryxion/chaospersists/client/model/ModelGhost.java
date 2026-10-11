package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Ghost;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ModelGhost extends EntityModel<Ghost> {
    private final ModelPart headAndBody;
    private final ModelPart lArm;
    private final ModelPart rArm;

    public ModelGhost() {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelGhost(ModelPart root) {
        this.headAndBody = root.getChild("head_and_body");
        this.lArm = root.getChild("l_arm");
        this.rArm = root.getChild("r_arm");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "head_and_body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 21.0f, 6.0f),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "l_arm",
                CubeListBuilder.create()
                        .texOffs(34, 0)
                        
                        .addBox(-1.0f, -1.0f, -1.0f, 2.0f, 11.0f, 2.0f),
                PartPose.offsetAndRotation(3.0f, 6.0f, 0.0f, 0.0f, 0.0f, -0.3316126f));
        root.addOrReplaceChild(
                "r_arm",
                CubeListBuilder.create()
                        .texOffs(25, 0)
                        
                        .addBox(-1.0f, -1.0f, -1.0f, 2.0f, 11.0f, 2.0f),
                PartPose.offsetAndRotation(-3.0f, 6.0f, 0.0f, 0.0f, 0.0f, 0.3316126f));
        return mesh;
    }

    @Override
    public void setupAnim(Ghost entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.lArm.zRot = -0.33f + Mth.cos(ageInTicks * 0.3f) * (float) Math.PI * 0.05f;
        this.rArm.zRot = 0.33f + Mth.cos(ageInTicks * 0.32f) * (float) Math.PI * 0.05f;
        this.lArm.xRot = -0.33f + Mth.cos(ageInTicks * 0.34f) * (float) Math.PI * 0.05f;
        this.rArm.xRot = 0.33f + Mth.cos(ageInTicks * 0.36f) * (float) Math.PI * 0.05f;
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
        float ghostAlpha = 0.25f;
        this.headAndBody.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.lArm.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.rArm.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
    }
}
