package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.item.Tshirt;
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

public class ModelTshirt extends EntityModel<Tshirt> {
    private final float wingspeed;
    private final ModelPart shape1;
    private final ModelPart shape2;
    private float animAgeInTicks;

    public ModelTshirt(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 512, 256).bakeRoot());
    }

    public ModelTshirt(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.shape1 = root.getChild("Shape1");
        this.shape2 = root.getChild("Shape2");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild(
                "Shape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-128.0f, -64.0f, 0.0f, 256, 64, 1),
                PartPose.offsetAndRotation(0.0f, -128.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild(
                "Shape2",
                CubeListBuilder.create().texOffs(0, 64).addBox(-64.0f, 0.0f, 0.0f, 128, 128, 1),
                PartPose.offsetAndRotation(0.0f, -128.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Tshirt entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animAgeInTicks = ageInTicks;
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
        float newangle = Mth.cos(this.animAgeInTicks * 0.05f * this.wingspeed) * (float) Math.PI;
        this.shape1.yRot = newangle;
        this.shape2.yRot = newangle;
        this.shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
