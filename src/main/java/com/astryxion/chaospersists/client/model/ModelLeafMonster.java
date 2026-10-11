package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.LeafMonster;
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

public class ModelLeafMonster extends EntityModel<LeafMonster> {
    private final ModelPart body;
    private final ModelPart larm;
    private final ModelPart rarm;
    private final ModelPart lleg;
    private final ModelPart rleg;

    public ModelLeafMonster(float f1) {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelLeafMonster(ModelPart root) {
        this.body = root.getChild("body");
        this.larm = root.getChild("larm");
        this.rarm = root.getChild("rarm");
        this.lleg = root.getChild("lleg");
        this.rleg = root.getChild("rleg");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 32).addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16), PartPose.offset(0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("larm", CubeListBuilder.create().texOffs(64, 0).addBox(0.0f, -16.0f, -8.0f, 16, 16, 16), PartPose.offset(8.0f, -8.0f, 0.0f));
        partdefinition.addOrReplaceChild("rarm", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0f, -16.0f, -8.0f, 16, 16, 16), PartPose.offset(-8.0f, -8.0f, 0.0f));
        partdefinition.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(64, 64).addBox(0.0f, 0.0f, -8.0f, 16, 16, 16), PartPose.offset(8.0f, 8.0f, 0.0f));
        partdefinition.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 64).addBox(-16.0f, 0.0f, -8.0f, 16, 16, 16), PartPose.offset(-8.0f, 8.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(LeafMonster entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        if (entity.getAttacking() == 0) {
            this.body.y = 16.0f;
            this.rarm.y = 8.0f;
            this.larm.y = 8.0f;
            this.rarm.yRot = 0.0f;
            this.larm.yRot = 0.0f;
            this.rarm.xRot = 0.0f;
            this.larm.xRot = 0.0f;
            this.lleg.xRot = 0.0f;
            this.rleg.xRot = 0.0f;
        } else {
            this.body.y = 0.0f;
            this.rarm.y = -8.0f;
            this.larm.y = -8.0f;
            float newangle =
                    (double) f1 > 0.1 ? Mth.cos(f2 * 0.95f) * (float) Math.PI * 0.25f * f1 : 0.0f;
            this.lleg.xRot = newangle;
            this.rleg.xRot = -newangle;
            newangle = Mth.cos(f2 * 0.7f) * (float) Math.PI * 0.55f;
            this.rarm.yRot = -Mth.abs(newangle);
            this.larm.yRot = Mth.abs(newangle);
            this.rarm.xRot = -Mth.abs(newangle);
            this.larm.xRot = -Mth.abs(newangle);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
