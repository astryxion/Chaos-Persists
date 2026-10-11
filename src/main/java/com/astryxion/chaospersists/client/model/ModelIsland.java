package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Island;
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
public class ModelIsland<T extends net.minecraft.world.entity.Entity> extends EntityModel<T> {
    private final float wingspeed;
    private final ModelPart shape1;
    private final ModelPart shape2;
    private final ModelPart shape3;

    public ModelIsland(float f) {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot(), f);
    }

    public ModelIsland(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.shape1 = root.getChild("Shape1");
        this.shape2 = root.getChild("Shape2");
        this.shape3 = root.getChild("Shape3");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8), PartPose.offset(0.0f, 16.0f, 0.0f));
        partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8), PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.7853982f, 0.7853982f, 0.7853982f));
        partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8), PartPose.offsetAndRotation(0.0f, 16.0f, 0.0f, 0.7853982f, 0.7853982f, 0.7853982f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = Mth.cos(ageInTicks * 0.05f * this.wingspeed) * (float) Math.PI;
        this.shape1.xRot = newangle;
        this.shape1.yRot = newangle = Mth.cos(ageInTicks * 0.051f * this.wingspeed) * (float) Math.PI;
        this.shape1.zRot = newangle = Mth.cos(ageInTicks * 0.052f * this.wingspeed) * (float) Math.PI;
        this.shape2.xRot = newangle = Mth.cos(ageInTicks * 0.053f * this.wingspeed) * (float) Math.PI;
        this.shape2.yRot = newangle = Mth.cos(ageInTicks * 0.054f * this.wingspeed) * (float) Math.PI;
        this.shape2.zRot = newangle = Mth.cos(ageInTicks * 0.055f * this.wingspeed) * (float) Math.PI;
        this.shape3.xRot = newangle = Mth.cos(ageInTicks * 0.056f * this.wingspeed) * (float) Math.PI;
        this.shape3.yRot = newangle = Mth.cos(ageInTicks * 0.057f * this.wingspeed) * (float) Math.PI;
        this.shape3.zRot = newangle = Mth.cos(ageInTicks * 0.058f * this.wingspeed) * (float) Math.PI;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
