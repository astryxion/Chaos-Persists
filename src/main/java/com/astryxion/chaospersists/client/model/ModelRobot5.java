package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Robot5;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelRobot5 extends EntityModel<Robot5> {
    private final float wingspeed;
    private final ModelPart lwheel1;
    private final ModelPart lwheel2;
    private final ModelPart rwheel1;
    private final ModelPart rwheel2;
    private final ModelPart axle;
    private final ModelPart drivebox;
    private final ModelPart stand;
    private final ModelPart swivel;
    private final ModelPart barrel1;
    private final ModelPart barrel2;
    private final ModelPart ammobox;

    public ModelRobot5(float wingspeed) {
        this(LayerDefinition.create(createMesh(), 128, 128).bakeRoot(), wingspeed);
    }

    public ModelRobot5(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.lwheel1 = root.getChild("lwheel1");
        this.lwheel2 = root.getChild("lwheel2");
        this.rwheel1 = root.getChild("rwheel1");
        this.rwheel2 = root.getChild("rwheel2");
        this.axle = root.getChild("axle");
        this.drivebox = root.getChild("drivebox");
        this.stand = root.getChild("stand");
        this.swivel = root.getChild("swivel");
        this.barrel1 = root.getChild("barrel1");
        this.barrel2 = root.getChild("barrel2");
        this.ammobox = root.getChild("ammobox");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("lwheel1", CubeListBuilder.create().texOffs(0, 23).addBox(0.0f, -4.0f, -4.0f, 2, 8, 8), PartPose.offsetAndRotation(6.0f, 19.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("lwheel2", CubeListBuilder.create().texOffs(0, 43).addBox(0.0f, -4.0f, -4.0f, 2, 8, 8), PartPose.offsetAndRotation(6.0f, 19.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        root.addOrReplaceChild("rwheel1", CubeListBuilder.create().texOffs(0, 23).addBox(0.0f, -4.0f, -4.0f, 2, 8, 8), PartPose.offsetAndRotation(-8.0f, 19.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("rwheel2", CubeListBuilder.create().texOffs(0, 43).addBox(0.0f, -4.0f, -4.0f, 2, 8, 8), PartPose.offsetAndRotation(-8.0f, 19.0f, 0.0f, 0.7853982f, 0.0f, 0.0f));
        root.addOrReplaceChild("axle", CubeListBuilder.create().texOffs(42, 0).addBox(-6.0f, -0.5f, -0.5f, 12, 1, 1), PartPose.offsetAndRotation(0.0f, 19.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("drivebox", CubeListBuilder.create().texOffs(47, 4).addBox(-2.0f, -1.5f, -1.5f, 4, 3, 3), PartPose.offsetAndRotation(0.0f, 19.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("stand", CubeListBuilder.create().texOffs(35, 0).addBox(-0.5f, 0.0f, -0.5f, 1, 18, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("swivel", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("barrel1", CubeListBuilder.create().texOffs(24, 25).addBox(-1.0f, -2.0f, -10.0f, 2, 2, 13), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("barrel2", CubeListBuilder.create().texOffs(27, 43).addBox(-0.5f, -1.5f, -19.0f, 1, 1, 9), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        root.addOrReplaceChild("ammobox", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -2.0f, 3.0f, 4, 3, 5), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Robot5 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        if ((double) limbSwingAmount > 0.1) {
            newangle = ageInTicks * 0.15f % 6.2831855f;
            newangle = Math.abs(newangle);
        } else {
            newangle = 0.0f;
        }
        this.lwheel1.xRot = newangle;
        this.lwheel2.xRot = (float) ((double) newangle + 0.7853981633974483);
        this.rwheel1.xRot = newangle;
        this.rwheel2.xRot = (float) ((double) newangle + 0.7853981633974483);
        float barrelYaw = (float) Math.toRadians((double) netHeadYaw / 2.0);
        this.barrel2.yRot = barrelYaw;
        this.ammobox.yRot = barrelYaw;
        this.barrel1.yRot = barrelYaw;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lwheel1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lwheel2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwheel1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rwheel2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.axle.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.drivebox.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.stand.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.swivel.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.barrel1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.barrel2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ammobox.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
