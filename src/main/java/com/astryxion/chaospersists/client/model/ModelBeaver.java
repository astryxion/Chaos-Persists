package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Beaver;
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

public class ModelBeaver extends EntityModel<Beaver> {
    private final float wingspeed;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart teeth;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart rff;
    private final ModelPart lff;
    private final ModelPart rrf;
    private final ModelPart lrf;

    public ModelBeaver(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelBeaver(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.head = root.getChild("head");
        this.nose = root.getChild("nose");
        this.teeth = root.getChild("teeth");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.rff = root.getChild("rff");
        this.lff = root.getChild("lff");
        this.rrf = root.getChild("rrf");
        this.lrf = root.getChild("lrf");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 3).addBox(0.0f, 0.0f, 0.0f, 6, 5, 5), PartPose.offset(0.0f, 15.0f, -8.0f));
        partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(6, 0).addBox(0.0f, 0.0f, 0.0f, 2, 1, 1), PartPose.offset(2.0f, 18.0f, -8.5f));
        partdefinition.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 2, 2, 1), PartPose.offset(2.0f, 19.0f, -8.2f));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(0.0f, 0.0f, 0.0f, 8, 8, 10), PartPose.offset(-1.0f, 14.0f, -3.0f));
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(22, 0).addBox(0.0f, -1.0f, 0.0f, 5, 1, 8), PartPose.offset(0.5f, 21.0f, 7.0f));
        partdefinition.addOrReplaceChild("rff", CubeListBuilder.create().texOffs(22, 9).addBox(0.0f, 0.0f, 0.0f, 2, 2, 2), PartPose.offset(-0.5f, 22.0f, -2.5f));
        partdefinition.addOrReplaceChild("lff", CubeListBuilder.create().texOffs(22, 9).addBox(0.0f, 0.0f, 0.0f, 2, 2, 2), PartPose.offset(4.5f, 22.0f, -2.5f));
        partdefinition.addOrReplaceChild("rrf", CubeListBuilder.create().texOffs(22, 9).addBox(0.0f, 0.0f, 0.0f, 2, 2, 2), PartPose.offset(-0.5f, 22.0f, 4.5f));
        partdefinition.addOrReplaceChild("lrf", CubeListBuilder.create().texOffs(22, 9).addBox(0.0f, 0.0f, 0.0f, 2, 2, 2), PartPose.offset(4.5f, 22.0f, 4.5f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Beaver entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = Mth.cos(ageInTicks * 3.7f * this.wingspeed) * 3.1415927f * 0.45f * limbSwingAmount;
        this.rff.xRot = newangle;
        this.lrf.xRot = newangle;
        this.lff.xRot = -newangle;
        this.rrf.xRot = -newangle;
        newangle = Mth.cos(ageInTicks * 2.7f * this.wingspeed) * 3.1415927f * 0.25f;
        this.teeth.xRot = newangle;
        newangle = Mth.cos(ageInTicks * 0.5f * this.wingspeed) * 3.1415927f * 0.05f;
        this.tail.xRot = newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.teeth.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrf.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrf.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
