package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.WormMedium;
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

public class ModelWormMedium extends EntityModel<WormMedium> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart tooth1;
    private final ModelPart tooth2;
    private final ModelPart tooth3;
    private final ModelPart tooth4;
    private final ModelPart head2;

    public ModelWormMedium() {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelWormMedium(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.tooth1 = root.getChild("tooth1");
        this.tooth2 = root.getChild("tooth2");
        this.tooth3 = root.getChild("tooth3");
        this.tooth4 = root.getChild("tooth4");
        this.head2 = root.getChild("head2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5f, -12.0f, -1.5f, 3, 12, 3), PartPose.offset(0.0f, 1.0f, 0.0f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(37, 0).addBox(-1.5f, -12.0f, -1.5f, 3, 12, 3), PartPose.offset(0.0f, 13.0f, 0.0f));
        root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5f, -12.0f, -1.5f, 3, 12, 3), PartPose.offset(0.0f, 25.0f, 0.0f));
        root.addOrReplaceChild("tooth1", CubeListBuilder.create().texOffs(15, 0).addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1), PartPose.offset(1.0f, -11.0f, 0.0f));
        root.addOrReplaceChild("tooth2", CubeListBuilder.create().texOffs(5, 0).addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1), PartPose.offset(-1.0f, -11.0f, 0.0f));
        root.addOrReplaceChild("tooth3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1), PartPose.offset(0.0f, -11.0f, 1.0f));
        root.addOrReplaceChild("tooth4", CubeListBuilder.create().texOffs(10, 0).addBox(-0.5f, -3.0f, -0.5f, 1, 3, 1), PartPose.offset(0.0f, -11.0f, -1.0f));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 6).addBox(-2.0f, -8.0f, -2.0f, 4, 8, 4), PartPose.offset(0.0f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(WormMedium entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle;
        this.tail.xRot = newangle = Mth.cos((float)(ageInTicks * 0.45f)) * 3.1415927f * 0.1f;
        float d1 = (float)(Math.sin(newangle) * 12.0);
        float d2 = (float)(Math.cos(newangle) * 12.0);
        this.body.z = this.tail.z - d1;
        this.tail.zRot = newangle = Mth.cos((float)(ageInTicks * 0.25f)) * 3.1415927f * 0.08f;
        float d3 = (float)(Math.cos(newangle) * (double)d2);
        float d4 = (float)(Math.sin(newangle) * (double)d2);
        this.body.x = this.tail.x + d4;
        this.body.y = (float)((double)this.tail.y - 12.0 + (12.0 - (double)d3));
        this.body.xRot = newangle = Mth.cos((float)(ageInTicks * 0.35f)) * 3.1415927f * 0.1f;
        d1 = (float)(Math.sin(newangle) * 12.0);
        d2 = (float)(Math.cos(newangle) * 12.0);
        this.head2.z = this.head.z = this.body.z - d1;
        this.body.zRot = newangle = Mth.cos((float)(ageInTicks * 0.15f)) * 3.1415927f * 0.07f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.head2.x = this.head.x = this.body.x + d4;
        this.head2.y = this.head.y = (float)((double)this.body.y - 12.0 + (12.0 - (double)d3));
        this.head2.xRot = this.head.xRot = 0.62f + Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        this.head2.zRot = this.head.zRot = Mth.cos((float)(ageInTicks * 0.25f)) * 3.1415927f * 0.05f;
        this.tooth3.xRot = this.tooth4.xRot = (newangle = this.head.xRot);
        this.tooth2.xRot = this.tooth4.xRot;
        this.tooth1.xRot = this.tooth4.xRot;
        d1 = (float)(Math.sin(newangle) * 12.0);
        d2 = (float)(Math.cos(newangle) * 12.0);
        this.tooth3.z = this.tooth4.z = this.head.z - d1;
        this.tooth2.z = this.tooth4.z;
        this.tooth1.z = this.tooth4.z;
        this.tooth3.zRot = this.tooth4.zRot = (newangle = this.head.zRot);
        this.tooth2.zRot = this.tooth4.zRot;
        this.tooth1.zRot = this.tooth4.zRot;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.tooth3.x = this.tooth4.x = this.head.x + d4;
        this.tooth2.x = this.tooth4.x;
        this.tooth1.x = this.tooth4.x;
        this.tooth3.y = this.tooth4.y = (float)((double)this.head.y - 12.0 + (12.0 - (double)d3));
        this.tooth2.y = this.tooth4.y;
        this.tooth1.y = this.tooth4.y;
        this.tooth1.z += 1.0f;
        this.tooth2.z -= 1.0f;
        this.tooth1.xRot = this.tooth1.xRot - 0.4f - Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        this.tooth2.xRot = this.tooth2.xRot + 0.4f + Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        this.tooth3.x += 1.0f;
        this.tooth4.x -= 1.0f;
        this.tooth3.zRot = this.tooth3.zRot + 0.4f + Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        this.tooth4.zRot = this.tooth4.zRot - 0.4f - Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
