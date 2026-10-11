package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Irukandji;
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

public class ModelIrukandji extends EntityModel<Irukandji> {
    private final ModelPart body;
    private final ModelPart t11;
    private final ModelPart t12;
    private final ModelPart t21;
    private final ModelPart t22;
    private final ModelPart t31;
    private final ModelPart t32;
    private final ModelPart t41;
    private final ModelPart t42;

    public ModelIrukandji() {
        this(LayerDefinition.create(createMesh(), 64, 32).bakeRoot());
    }

    public ModelIrukandji(ModelPart root) {
        this.body = root.getChild("body");
        this.t11 = root.getChild("t11");
        this.t12 = root.getChild("t12");
        this.t21 = root.getChild("t21");
        this.t22 = root.getChild("t22");
        this.t31 = root.getChild("t31");
        this.t32 = root.getChild("t32");
        this.t41 = root.getChild("t41");
        this.t42 = root.getChild("t42");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4), PartPose.offset(0.0f, 6.0f, 0.0f));
        root.addOrReplaceChild("t11", CubeListBuilder.create().texOffs(25, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(1.0f, 10.0f, -2.0f));
        root.addOrReplaceChild("t12", CubeListBuilder.create().texOffs(5, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(1.0f, 17.0f, -2.0f));
        root.addOrReplaceChild("t21", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(-2.0f, 10.0f, -2.0f));
        root.addOrReplaceChild("t22", CubeListBuilder.create().texOffs(20, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(-2.0f, 17.0f, -2.0f));
        root.addOrReplaceChild("t31", CubeListBuilder.create().texOffs(30, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(1.0f, 10.0f, 1.0f));
        root.addOrReplaceChild("t32", CubeListBuilder.create().texOffs(10, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(1.0f, 17.0f, 1.0f));
        root.addOrReplaceChild("t41", CubeListBuilder.create().texOffs(35, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(-2.0f, 10.0f, 1.0f));
        root.addOrReplaceChild("t42", CubeListBuilder.create().texOffs(15, 0).addBox(0.0f, 0.0f, 0.0f, 1, 7, 1), PartPose.offset(-2.0f, 17.0f, 1.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Irukandji entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        this.t11.xRot = newangle = Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        float d1 = (float)(Math.sin(newangle) * 7.0);
        float d2 = (float)(Math.cos(newangle) * 7.0);
        this.t12.z = this.t11.z + d1;
        this.t11.zRot = newangle = Mth.cos((float)(ageInTicks * 0.35f)) * 3.1415927f * 0.1f;
        float d3 = (float)(Math.cos(newangle) * (double)d2);
        float d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t12.x = this.t11.x - d4;
        this.t12.y = this.t11.y + d3;
        this.t12.xRot = newangle = Mth.cos((float)(ageInTicks * 0.45f)) * 3.1415927f * 0.15f;
        this.t12.zRot = newangle = Mth.cos((float)(ageInTicks * 0.25f)) * 3.1415927f * 0.1f;
        this.t21.xRot = newangle = Mth.cos((float)(ageInTicks * 0.65f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 7.0);
        d2 = (float)(Math.cos(newangle) * 7.0);
        this.t22.z = this.t21.z + d1;
        this.t21.zRot = newangle = Mth.cos((float)(ageInTicks * 0.45f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t22.x = this.t21.x - d4;
        this.t22.y = this.t21.y + d3;
        this.t22.xRot = newangle = Mth.cos((float)(ageInTicks * 0.55f)) * 3.1415927f * 0.15f;
        this.t22.zRot = newangle = Mth.cos((float)(ageInTicks * 0.35f)) * 3.1415927f * 0.1f;
        this.t31.xRot = newangle = Mth.cos((float)(ageInTicks * 0.5f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 7.0);
        d2 = (float)(Math.cos(newangle) * 7.0);
        this.t32.z = this.t31.z + d1;
        this.t31.zRot = newangle = Mth.cos((float)(ageInTicks * 0.3f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t32.x = this.t31.x - d4;
        this.t32.y = this.t31.y + d3;
        this.t32.xRot = newangle = Mth.cos((float)(ageInTicks * 0.4f)) * 3.1415927f * 0.15f;
        this.t32.zRot = newangle = Mth.cos((float)(ageInTicks * 0.2f)) * 3.1415927f * 0.1f;
        this.t41.xRot = newangle = Mth.cos((float)(ageInTicks * 0.57f)) * 3.1415927f * 0.15f;
        d1 = (float)(Math.sin(newangle) * 7.0);
        d2 = (float)(Math.cos(newangle) * 7.0);
        this.t42.z = this.t41.z + d1;
        this.t41.zRot = newangle = Mth.cos((float)(ageInTicks * 0.37f)) * 3.1415927f * 0.1f;
        d3 = (float)(Math.cos(newangle) * (double)d2);
        d4 = (float)(Math.sin(newangle) * (double)d2);
        this.t42.x = this.t41.x - d4;
        this.t42.y = this.t41.y + d3;
        this.t42.xRot = newangle = Mth.cos((float)(ageInTicks * 0.48f)) * 3.1415927f * 0.15f;
        this.t42.zRot = newangle = Mth.cos((float)(ageInTicks * 0.29f)) * 3.1415927f * 0.1f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t22.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t31.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t32.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t41.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.t42.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
