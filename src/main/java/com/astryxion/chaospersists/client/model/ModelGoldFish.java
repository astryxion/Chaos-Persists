package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.GoldFish;
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

public class ModelGoldFish extends EntityModel<GoldFish> {
    private final float wingspeed;
    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart Dorsalfin;
    private final ModelPart Mouth;
    private final ModelPart Jaw;
    private final ModelPart Pectoralfin1;
    private final ModelPart Pectoralfin2;
    private final ModelPart Pectoralfin3;
    private final ModelPart Pectoralfin4;
    private final ModelPart Bottomfin;
    private final ModelPart Tail1;
    private final ModelPart Tail2;
    private final ModelPart Caudalfin1;
    private final ModelPart Caudalfin2;
    private final ModelPart Bottomfin1;
    private final ModelPart Bottomfin2;

    public ModelGoldFish(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelGoldFish(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.Body = root.getChild("Body");
        this.Head = root.getChild("Head");
        this.Dorsalfin = root.getChild("Dorsalfin");
        this.Mouth = root.getChild("Mouth");
        this.Jaw = root.getChild("Jaw");
        this.Pectoralfin1 = root.getChild("Pectoralfin1");
        this.Pectoralfin2 = root.getChild("Pectoralfin2");
        this.Pectoralfin3 = root.getChild("Pectoralfin3");
        this.Pectoralfin4 = root.getChild("Pectoralfin4");
        this.Bottomfin = root.getChild("Bottomfin");
        this.Tail1 = root.getChild("Tail1");
        this.Tail2 = root.getChild("Tail2");
        this.Caudalfin1 = root.getChild("Caudalfin1");
        this.Caudalfin2 = root.getChild("Caudalfin2");
        this.Bottomfin1 = root.getChild("Bottomfin1");
        this.Bottomfin2 = root.getChild("Bottomfin2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 10), PartPose.offset(0.0f, 14.0f, -5.0f));
        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5f, -2.0f, -3.0f, 3, 4, 3), PartPose.offset(0.0f, 14.0f, -5.0f));
        root.addOrReplaceChild("Dorsalfin", CubeListBuilder.create().texOffs(29, 0).addBox(0.0f, -6.0f, 0.0f, 0, 4, 10), PartPose.offset(0.0f, 14.0f, -5.0f));
        root.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(0, 38).addBox(-1.5f, 0.6f, -3.5f, 3, 3, 3), PartPose.offsetAndRotation(0.0f, 14.0f, -5.0f, -0.7853982f, 0.0f, 0.0f));
        root.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(13, 30).addBox(-1.0f, 0.0f, -3.0f, 3, 1, 3), PartPose.offsetAndRotation(-0.5f, 15.6f, -7.4f, -0.2284419f, 0.0f, 0.0f));
        root.addOrReplaceChild("Pectoralfin1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, 0.0f, 0, 3, 5), PartPose.offsetAndRotation(-2.0f, 14.0f, -3.0f, -0.2974289f, -0.3346075f, 0.0f));
        root.addOrReplaceChild("Pectoralfin2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, 0.0f, 0, 3, 5), PartPose.offsetAndRotation(2.0f, 14.0f, -3.0f, -0.2974216f, 0.3346145f, 0.0f));
        root.addOrReplaceChild("Pectoralfin3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, 0.0f, 0, 3, 5), PartPose.offsetAndRotation(-2.0f, 14.0f, 1.0f, -0.2974289f, -0.3346075f, 0.0f));
        root.addOrReplaceChild("Pectoralfin4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -1.5f, 0.0f, 0, 3, 5), PartPose.offsetAndRotation(2.0f, 14.0f, 1.0f, -0.2974289f, 0.3346145f, 0.0f));
        root.addOrReplaceChild("Bottomfin", CubeListBuilder.create().texOffs(20, 8).addBox(0.0f, 2.0f, 6.0f, 0, 3, 4), PartPose.offset(0.0f, 14.0f, -5.0f));
        root.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(29, 15).addBox(-1.5f, -2.0f, 0.0f, 3, 4, 6), PartPose.offset(0.0f, 14.0f, 5.0f));
        root.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0f, -1.5f, 6.0f, 2, 3, 4), PartPose.offset(0.0f, 14.0f, 5.0f));
        root.addOrReplaceChild("Caudalfin1", CubeListBuilder.create().texOffs(13, 35).addBox(-0.5f, 5.5f, 6.0f, 1, 3, 4), PartPose.offsetAndRotation(0.0f, 14.0f, 5.0f, 0.8179294f, 0.0f, 0.0f));
        root.addOrReplaceChild("Caudalfin2", CubeListBuilder.create().texOffs(15, 35).addBox(-0.5f, 5.5f, 6.0f, 1, 4, 3), PartPose.offsetAndRotation(0.0f, 14.0f, 5.0f, 0.8179294f, 0.0f, 0.0f));
        root.addOrReplaceChild("Bottomfin1", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0f, 2.0f, 1.0f, 0, 5, 2), PartPose.offsetAndRotation(0.0f, 14.0f, -5.0f, 0.2974289f, 0.0f, 0.3346145f));
        root.addOrReplaceChild("Bottomfin2", CubeListBuilder.create().texOffs(20, 0).addBox(1.0f, 2.0f, 1.0f, 0, 5, 2), PartPose.offsetAndRotation(0.0f, 14.0f, -5.0f, 0.2974289f, 0.0f, -0.3346075f));
        return mesh;
    }

    @Override
    public void setupAnim(GoldFish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        newangle = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin1.yRot = 0.4f + newangle;
        newangle = Mth.cos((float)(ageInTicks * 1.2f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin2.yRot = -0.4f + newangle;
        newangle = Mth.cos((float)(ageInTicks * 1.1f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin3.yRot = 0.4f + newangle;
        newangle = Mth.cos((float)(ageInTicks * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        this.Pectoralfin4.yRot = -0.4f + newangle;
        this.Bottomfin1.yRot = newangle = Mth.cos((float)(ageInTicks * 1.7f * this.wingspeed)) * 3.1415927f * 0.25f;
        this.Bottomfin2.yRot = - newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.7f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.Jaw.xRot = -0.25f + newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Dorsalfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Mouth.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Pectoralfin4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bottomfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Caudalfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Caudalfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bottomfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bottomfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
