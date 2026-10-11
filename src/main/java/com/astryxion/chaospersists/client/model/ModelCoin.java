package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.item.Coin;
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

public class ModelCoin extends EntityModel<Coin> {
    private final float wingspeed;
    private final ModelPart shape1;
    private float animAgeInTicks;

    public ModelCoin(float wingspeed) {
        this(wingspeed, LayerDefinition.create(createMesh(), 512, 512).bakeRoot());
    }

    public ModelCoin(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.shape1 = root.getChild("Shape1");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild(
                "Shape1",
                CubeListBuilder.create().texOffs(0, 0).addBox(-128.0f, -128.0f, 0.0f, 256, 256, 1),
                PartPose.offsetAndRotation(0.0f, -109.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Coin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
        this.shape1.yRot = Mth.cos(this.animAgeInTicks * 0.05f * this.wingspeed) * (float) Math.PI;
        this.shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
