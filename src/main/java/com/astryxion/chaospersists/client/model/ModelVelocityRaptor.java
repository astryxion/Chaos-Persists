package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.VelocityRaptor;
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

public class ModelVelocityRaptor extends EntityModel<VelocityRaptor> {
    private final float wingspeed;
    private final ModelPart hf3;
    private final ModelPart hf4;
    private final ModelPart hf2;
    private final ModelPart hf1;
    private final ModelPart lff2;
    private final ModelPart lff1;
    private final ModelPart lff3;
    private final ModelPart rff2;
    private final ModelPart rff3;
    private final ModelPart rff1;
    private final ModelPart tf4;
    private final ModelPart tf1;
    private final ModelPart shape1;
    private final ModelPart neck;
    private final ModelPart head1;
    private final ModelPart lf1;
    private final ModelPart lf2;
    private final ModelPart head2;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart bl1;
    private final ModelPart br1;
    private final ModelPart bl2;
    private final ModelPart br2;
    private final ModelPart bl3;
    private final ModelPart br3;
    private final ModelPart rf1;
    private final ModelPart rf2;
    private final ModelPart tf2;
    private final ModelPart tf3;
    private final ModelPart bl4;
    private final ModelPart br4;
    private final ModelPart hat1;
    private final ModelPart hat2;

    public ModelVelocityRaptor(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelVelocityRaptor(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.hf3 = root.getChild("hf3");
        this.hf4 = root.getChild("hf4");
        this.hf2 = root.getChild("hf2");
        this.hf1 = root.getChild("hf1");
        this.lff2 = root.getChild("lff2");
        this.lff1 = root.getChild("lff1");
        this.lff3 = root.getChild("lff3");
        this.rff2 = root.getChild("rff2");
        this.rff3 = root.getChild("rff3");
        this.rff1 = root.getChild("rff1");
        this.tf4 = root.getChild("tf4");
        this.tf1 = root.getChild("tf1");
        this.shape1 = root.getChild("shape1");
        this.neck = root.getChild("neck");
        this.head1 = root.getChild("head1");
        this.lf1 = root.getChild("lf1");
        this.lf2 = root.getChild("lf2");
        this.head2 = root.getChild("head2");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.bl1 = root.getChild("bl1");
        this.br1 = root.getChild("br1");
        this.bl2 = root.getChild("bl2");
        this.br2 = root.getChild("br2");
        this.bl3 = root.getChild("bl3");
        this.br3 = root.getChild("br3");
        this.rf1 = root.getChild("rf1");
        this.rf2 = root.getChild("rf2");
        this.tf2 = root.getChild("tf2");
        this.tf3 = root.getChild("tf3");
        this.bl4 = root.getChild("bl4");
        this.br4 = root.getChild("br4");
        this.hat1 = root.getChild("hat1");
        this.hat2 = root.getChild("hat2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("hf3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 0, 1, 3), PartPose.offsetAndRotation(0.0f, 7.0f, -2.0f, 0.4537856f, 0.0f, 0.0f));
        root.addOrReplaceChild("hf4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -0.2f, 0.0f, 0, 1, 3), PartPose.offsetAndRotation(0.0f, 8.0f, -1.5f, 0.2443461f, 0.0f, 0.0f));
        root.addOrReplaceChild("hf2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 0, 1, 3), PartPose.offsetAndRotation(0.0f, 7.0f, -3.5f, 0.6632251f, 0.0f, 0.0f));
        root.addOrReplaceChild("hf1", CubeListBuilder.create().texOffs(0, 1).addBox(0.0f, 0.0f, 0.0f, 0, 1, 2), PartPose.offsetAndRotation(0.0f, 7.0f, -4.5f, 0.9424778f, 0.0f, 0.0f));
        root.addOrReplaceChild("lff2", CubeListBuilder.create().texOffs(0, 6).addBox(0.5f, 2.5f, 3.0f, 0, 1, 3), PartPose.offsetAndRotation(2.0f, 14.0f, 1.0f, -0.4537856f, 0.0f, 0.0f));
        root.addOrReplaceChild("lff1", CubeListBuilder.create().texOffs(0, 6).addBox(0.5f, 2.0f, 2.0f, 0, 1, 3), PartPose.offsetAndRotation(2.0f, 14.0f, 1.0f, -0.2792527f, 0.0f, 0.0f));
        root.addOrReplaceChild("lff3", CubeListBuilder.create().texOffs(0, 6).addBox(0.5f, 1.0f, 4.0f, 0, 1, 3), PartPose.offsetAndRotation(2.0f, 14.0f, 1.0f, -1.047198f, 0.0f, 0.0f));
        root.addOrReplaceChild("rff2", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5f, 2.5f, 3.0f, 0, 1, 3), PartPose.offsetAndRotation(-2.0f, 14.0f, 1.0f, -0.4537856f, 0.0f, 0.0f));
        root.addOrReplaceChild("rff3", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5f, 1.0f, 4.0f, 0, 1, 3), PartPose.offsetAndRotation(-2.0f, 14.0f, 1.0f, -1.047198f, 0.0f, 0.0f));
        root.addOrReplaceChild("rff1", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5f, 2.0f, 2.0f, 0, 1, 3), PartPose.offsetAndRotation(-2.0f, 14.0f, 1.0f, -0.2792527f, 0.0f, 0.0f));
        root.addOrReplaceChild("tf4", CubeListBuilder.create().texOffs(0, 3).addBox(0.0f, 0.0f, 0.0f, 0, 1, 3), PartPose.offsetAndRotation(0.0f, 11.0f, 25.0f, -0.5410521f, 0.0f, 0.0f));
        root.addOrReplaceChild("tf1", CubeListBuilder.create().texOffs(0, 3).addBox(0.0f, 0.0f, 0.0f, 0, 1, 3), PartPose.offsetAndRotation(0.0f, 11.0f, 19.0f, -0.5410521f, 0.0f, 0.0f));
        root.addOrReplaceChild("shape1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, 0.0f, 0.0f, 4, 7, 11), PartPose.offset(0.0f, 10.0f, 0.0f));
        root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0f, -7.0f, -2.0f, 2, 8, 3), PartPose.offsetAndRotation(0.0f, 12.0f, 2.0f, 1.082104f, 0.0f, 0.0f));
        root.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 49).addBox(-2.0f, 0.0f, -7.0f, 3, 4, 7), PartPose.offset(0.5f, 7.0f, -1.0f));
        root.addOrReplaceChild("lf1", CubeListBuilder.create().texOffs(0, 31).addBox(0.0f, 0.0f, 0.0f, 1, 3, 2), PartPose.offsetAndRotation(2.0f, 14.0f, 1.0f, 0.2792527f, 0.0f, 0.0f));
        root.addOrReplaceChild("lf2", CubeListBuilder.create().texOffs(16, 19).addBox(0.0f, 1.0f, 2.0f, 1, 4, 1), PartPose.offsetAndRotation(2.0f, 14.0f, 1.0f, -0.4363323f, 0.0f, 0.0f));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0f, 0.0f, -10.0f, 2, 4, 4), PartPose.offset(0.0f, 7.0f, -1.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 38).addBox(-1.0f, 0.0f, 0.0f, 2, 5, 4), PartPose.offset(0.0f, 10.0f, 11.0f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(26, 11).addBox(0.0f, 0.0f, 0.0f, 1, 2, 10), PartPose.offset(-0.5f, 10.0f, 15.0f));
        root.addOrReplaceChild("bl1", CubeListBuilder.create().texOffs(22, 24).addBox(-1.0f, 0.0f, 0.0f, 2, 6, 4), PartPose.offset(2.0f, 13.0f, 6.0f));
        root.addOrReplaceChild("br1", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0f, 0.0f, 0.0f, 2, 6, 4), PartPose.offset(-2.0f, 13.0f, 6.0f));
        root.addOrReplaceChild("bl2", CubeListBuilder.create().texOffs(12, 26).addBox(-1.0f, 5.0f, -3.0f, 2, 5, 2), PartPose.offsetAndRotation(2.0f, 13.0f, 6.0f, 0.4886922f, 0.0f, 0.0f));
        root.addOrReplaceChild("br2", CubeListBuilder.create().texOffs(13, 36).addBox(-1.0f, 5.0f, -3.0f, 2, 5, 2), PartPose.offsetAndRotation(-2.0f, 13.0f, 6.0f, 0.4886922f, 0.0f, 0.0f));
        root.addOrReplaceChild("bl3", CubeListBuilder.create().texOffs(28, 39).addBox(-1.0f, 9.0f, -1.0f, 2, 2, 4), PartPose.offset(2.0f, 13.0f, 6.0f));
        root.addOrReplaceChild("br3", CubeListBuilder.create().texOffs(18, 45).addBox(-1.0f, 9.0f, -1.0f, 2, 2, 4), PartPose.offset(-2.0f, 13.0f, 6.0f));
        root.addOrReplaceChild("rf1", CubeListBuilder.create().texOffs(35, 31).addBox(-1.0f, 0.0f, 0.0f, 1, 3, 2), PartPose.offsetAndRotation(-2.0f, 14.0f, 1.0f, 0.2792527f, 0.0f, 0.0f));
        root.addOrReplaceChild("rf2", CubeListBuilder.create().texOffs(11, 19).addBox(-1.0f, 1.0f, 2.0f, 1, 4, 1), PartPose.offsetAndRotation(-2.0f, 14.0f, 1.0f, -0.4363323f, 0.0f, 0.0f));
        root.addOrReplaceChild("tf2", CubeListBuilder.create().texOffs(0, 3).addBox(0.0f, 0.0f, 0.0f, 0, 1, 3), PartPose.offsetAndRotation(0.0f, 11.0f, 21.0f, -0.5410521f, 0.0f, 0.0f));
        root.addOrReplaceChild("tf3", CubeListBuilder.create().texOffs(0, 3).addBox(0.0f, 0.0f, 0.0f, 0, 1, 3), PartPose.offsetAndRotation(0.0f, 11.0f, 23.0f, -0.5410521f, 0.0f, 0.0f));
        root.addOrReplaceChild("bl4", CubeListBuilder.create().texOffs(31, 10).addBox(-1.0f, 6.0f, -5.0f, 1, 3, 1), PartPose.offsetAndRotation(2.0f, 13.0f, 6.0f, 0.6283185f, 0.0f, 0.0f));
        root.addOrReplaceChild("br4", CubeListBuilder.create().texOffs(31, 15).addBox(0.0f, 6.0f, -5.0f, 1, 3, 1), PartPose.offsetAndRotation(-2.0f, 13.0f, 6.0f, 0.6283185f, 0.0f, 0.0f));
        root.addOrReplaceChild("hat1", CubeListBuilder.create().texOffs(50, 0).addBox(0.0f, 0.0f, 0.0f, 4, 1, 5), PartPose.offset(-2.0f, 6.0f, -6.0f));
        root.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(50, 0).addBox(0.0f, 0.0f, 0.0f, 3, 2, 3), PartPose.offset(-1.5f, 4.0f, -4.0f));
        return mesh;
    }

    @Override
    public void setupAnim(VelocityRaptor e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float hf = 0.0f;
        float newangle = 0.0f;
        newangle = limbSwingAmount > 0.1f ? Mth.cos(ageInTicks * 1.3f * this.wingspeed) * (float) Math.PI * 0.25f * limbSwingAmount : 0.0f;
        this.bl1.xRot = newangle;
        this.bl2.xRot = newangle + 0.488f;
        this.bl3.xRot = newangle;
        this.bl4.xRot = newangle + 0.628f;
        this.br1.xRot = -newangle;
        this.br2.xRot = -newangle + 0.488f;
        this.br3.xRot = -newangle;
        this.br4.xRot = -newangle + 0.628f;
        hf = (float) e.getVHealth() / e.getMaxHealth();
        this.hf1.yRot = newangle = Mth.cos(ageInTicks * 1.25f * this.wingspeed * hf) * (float) Math.PI * 0.1f * hf;
        this.hf2.yRot = -newangle;
        this.hf3.yRot = newangle;
        this.hf4.yRot = -newangle;
        newangle = Mth.cos(ageInTicks * 0.3f) * (float) Math.PI * 0.05f;
        this.lf1.xRot = newangle + 0.279f;
        this.lf2.xRot = newangle - 0.436f;
        this.lff1.xRot = newangle - 0.279f;
        this.lff2.xRot = newangle - 0.453f;
        this.lff3.xRot = newangle - 1.047f;
        this.rf1.xRot = -newangle + 0.279f;
        this.rf2.xRot = -newangle - 0.436f;
        this.rff1.xRot = -newangle - 0.279f;
        this.rff2.xRot = -newangle - 0.453f;
        this.rff3.xRot = -newangle - 1.047f;
        this.lff1.yRot = newangle = Mth.cos(ageInTicks * 1.3f * this.wingspeed) * (float) Math.PI * 0.1f;
        this.lff2.yRot = -newangle;
        this.lff3.yRot = newangle;
        this.rff1.yRot = -newangle;
        this.rff2.yRot = newangle;
        this.rff3.yRot = -newangle;
        newangle = e.isInSittingPose() ? 0.0f : Mth.cos(ageInTicks * 1.4f * this.wingspeed * hf) * (float) Math.PI * 0.25f * hf;
        this.tf1.zRot = newangle;
        this.tf2.zRot = -newangle;
        this.tf3.zRot = newangle;
        this.tf4.zRot = -newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.hf3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hf1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tf4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lf2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lff3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rf2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rff1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.bl4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.br4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.shape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderHats(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            VelocityRaptor e,
            float red,
            float green,
            float blue,
            float alpha) {
        if (e.get_is_activated() != 0) {
            this.hat1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            if (e.get_is_activated() > 1) {
                this.hat2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            }
        }
    }
}
