package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Cassowary;
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

public class ModelCassowary extends EntityModel<Cassowary> {
    private final float wingspeed;
    private Cassowary animEntity;
    private float animAgeInTicks;
    private float animLimbSwingAmount;
    private final ModelPart tail;
    private final ModelPart body;
    private final ModelPart neck1;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart beak;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart crest;
    private final ModelPart foot1;
    private final ModelPart foot2;
    private final ModelPart gobbler;
    public ModelCassowary(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelCassowary(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.tail = root.getChild("tail");
        this.body = root.getChild("body");
        this.neck1 = root.getChild("neck1");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.beak = root.getChild("beak");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.crest = root.getChild("crest");
        this.foot1 = root.getChild("foot1");
        this.foot2 = root.getChild("foot2");
        this.gobbler = root.getChild("gobbler");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(38, 16).addBox(-3.0f, 0.0f, 0.0f, 6, 9, 7), PartPose.offsetAndRotation(0.0f, 8.0f, 1.0f, 0.8922867f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-4.0f, 0.0f, 0.0f, 8, 10, 9), PartPose.offsetAndRotation(0.0f, 5.0f, -3.0f, 0.3346075f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0f, 0.0f, 0.0f, 4, 5, 4), PartPose.offsetAndRotation(0.0f, 4.0f, -1.0f, -1.189716f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0f, 0.0f, 0.0f, 2, 7, 2), PartPose.offsetAndRotation(0.0f, 8.0f, -3.0f, -2.806985f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0f, -2.0f, -3.0f, 2, 2, 4), PartPose.offsetAndRotation(0.0f, 2.0f, -6.0f, 0.0371786f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(28, 7).addBox(-0.5f, 0.0f, 3.0f, 1, 1, 3), PartPose.offsetAndRotation(0.0f, 2.0f, -6.0f, -3.104414f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, 0.0f, -1.0f, 1, 11, 2), PartPose.offsetAndRotation(3.0f, 12.0f, 3.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, 0.0f, -1.0f, 1, 11, 2), PartPose.offsetAndRotation(-3.0f, 12.0f, 3.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("crest", CubeListBuilder.create().texOffs(10, 0).addBox(-0.5f, -4.0f, 1.0f, 1, 4, 5), PartPose.offsetAndRotation(0.0f, 2.0f, -6.0f, 1.710216f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("foot1", CubeListBuilder.create().texOffs(47, 10).addBox(-1.033333f, 11.0f, -2.0f, 2, 1, 3), PartPose.offsetAndRotation(-3.0f, 12.0f, 3.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("foot2", CubeListBuilder.create().texOffs(47, 10).addBox(-1.0f, 11.0f, -2.0f, 2, 1, 3), PartPose.offsetAndRotation(3.0f, 12.0f, 3.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("gobbler", CubeListBuilder.create().texOffs(38, 10).addBox(-0.5f, -1.0f, -2.5f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, 8.0f, -3.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(Cassowary entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animEntity = entity;
        this.animAgeInTicks = ageInTicks;
        this.animLimbSwingAmount = limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Cassowary entity = this.animEntity;
        float ageInTicks = this.animAgeInTicks;
        float limbSwingAmount = this.animLimbSwingAmount;
float newangle = 0.0f;
        float newangle2 = 0.0f;
        if ((double)limbSwingAmount > 0.1) {
            newangle = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.15f * limbSwingAmount;
            newangle2 = Mth.cos((float)(ageInTicks * 2.6f * this.wingspeed)) * 3.1415927f * 0.1f * limbSwingAmount;
        } else {
            newangle2 = 0.0f;
            newangle = 0.0f;
        }
        this.leg1.xRot = this.foot2.xRot = newangle;
        this.leg2.xRot = this.foot1.xRot = - newangle;
        this.neck.xRot = -2.827f + newangle2;
        this.gobbler.xRot = newangle2;
        this.crest.z = this.beak.z = this.neck.z + Mth.sin((float)this.neck.xRot) * 7.0f;
        this.head.z = this.beak.z;
        this.crest.y = this.beak.y = this.neck.y + Mth.cos((float)this.neck.xRot) * 7.0f;
        this.head.y = this.beak.y;
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.beak.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.crest.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.foot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.gobbler.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
