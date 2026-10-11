package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import com.astryxion.chaospersists.entity.Vortex;

public class ModelVortex extends EntityModel<Vortex> {
    private final float wingspeed;
    private final ModelPart shape1;

    public ModelVortex(float f1) {
        this(LayerDefinition.create(createMesh(), 256, 128).bakeRoot(), f1);
    }

    public ModelVortex(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.shape1 = root.getChild("shape1");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "shape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-64.0f, -64.0f, 0.0f, 128, 64, 0),
                PartPose.offset(0.0f, 22.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Vortex entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
        this.shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
