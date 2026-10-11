package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.WormSmall;
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

public class ModelWormSmall extends EntityModel<WormSmall> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;

    public ModelWormSmall() {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelWormSmall(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, -5.0f, -0.5f, 1, 5, 1), PartPose.offset(0.0f, 14.0f, 0.0f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(6, 0).addBox(-0.5f, -5.0f, -0.5f, 1, 5, 1), PartPose.offset(0.0f, 19.0f, 0.0f));
        root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 0).addBox(-0.5f, -5.0f, -0.5f, 1, 5, 1), PartPose.offset(0.0f, 24.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(WormSmall entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle;
        this.tail.xRot = newangle = Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        float d1 = (float)(Math.sin(newangle) * 5.0);
        float d2 = (float)(Math.cos(newangle) * 5.0);
        this.body.z = this.tail.z - d1;
        this.tail.zRot = newangle = Mth.cos((float)(ageInTicks * 0.35f)) * 3.1415927f * 0.1f;
        float d3 = (float)(Math.cos(newangle) * (double)d2);
        float d4 = (float)(Math.sin(newangle) * (double)d2);
        this.body.x = this.tail.x + d4;
        this.body.y = (float)((double)this.tail.y - 5.0 + (5.0 - (double)d3));
        this.body.xRot = newangle = Mth.cos((float)(ageInTicks * 0.45f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 5.0);
        d2 = (float)(Math.cos(newangle) * 5.0);
        this.head.z = this.body.z - d1;
        this.body.zRot = newangle = Mth.cos((float)(ageInTicks * 0.25f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.head.x = this.body.x + d4;
        this.head.y = (float)((double)this.body.y - 5.0 + (5.0 - (double)d3));
        this.head.xRot = 0.62f + Mth.cos((float)(ageInTicks * 0.65f)) * 3.1415927f * 0.15f;
        this.head.zRot = Mth.cos((float)(ageInTicks * 0.3f)) * 3.1415927f * 0.05f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
