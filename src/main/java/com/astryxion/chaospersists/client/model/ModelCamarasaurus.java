package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Camarasaurus;
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

public class ModelCamarasaurus extends EntityModel<Camarasaurus> {
    private final float wingspeed;
    private final ModelPart Body1;
    private final ModelPart Body2;
    private final ModelPart Body3;
    private final ModelPart Body4;
    private final ModelPart Tail0;
    private final ModelPart Neck1;
    private final ModelPart Neck2;
    private final ModelPart Neck3;
    private final ModelPart Head1;
    private final ModelPart Head2;
    private final ModelPart Tail1;
    private final ModelPart Tail2;
    private final ModelPart Tail3;
    private final ModelPart BLegupleft;
    private final ModelPart FLegupleft;
    private final ModelPart BLegupright;
    private final ModelPart FLegupright;
    private final ModelPart BLegdownright;
    private final ModelPart FLegdownleft;
    private final ModelPart FLegdownright;
    private final ModelPart BLegdownleft;

    public ModelCamarasaurus(float f1) {
        this(LayerDefinition.create(createMesh(), 256, 256).bakeRoot(), f1);
    }

    public ModelCamarasaurus(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Body1 = root.getChild("Body1");
        this.Body2 = root.getChild("Body2");
        this.Body3 = root.getChild("Body3");
        this.Body4 = root.getChild("Body4");
        this.Tail0 = root.getChild("Tail0");
        this.Neck1 = root.getChild("Neck1");
        this.Neck2 = root.getChild("Neck2");
        this.Neck3 = root.getChild("Neck3");
        this.Head1 = root.getChild("Head1");
        this.Head2 = root.getChild("Head2");
        this.Tail1 = root.getChild("Tail1");
        this.Tail2 = root.getChild("Tail2");
        this.Tail3 = root.getChild("Tail3");
        this.BLegupleft = root.getChild("BLegupleft");
        this.FLegupleft = root.getChild("FLegupleft");
        this.BLegupright = root.getChild("BLegupright");
        this.FLegupright = root.getChild("FLegupright");
        this.BLegdownright = root.getChild("BLegdownright");
        this.FLegdownleft = root.getChild("FLegdownleft");
        this.FLegdownright = root.getChild("FLegdownright");
        this.BLegdownleft = root.getChild("BLegdownleft");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Body1", CubeListBuilder.create().texOffs(0, 135).addBox(-6.0f, 0.0f, 0.0f, 12, 12, 12), PartPose.offset(0.0f, -1.0f, 0.0f));
        partdefinition.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(0, 160).addBox(-5.0f, 0.0f, 0.0f, 10, 10, 6), PartPose.offsetAndRotation(0.0f, -2.0f, -4.0f, -0.1858931f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Body3", CubeListBuilder.create().texOffs(0, 177).addBox(-4.0f, 0.0f, 0.0f, 8, 8, 4), PartPose.offsetAndRotation(0.0f, -3.0f, -6.0f, -0.3346075f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Body4", CubeListBuilder.create().texOffs(0, 120).addBox(-5.0f, 0.0f, 0.0f, 10, 10, 4), PartPose.offset(0.0f, 0.0f, 11.0f));
        partdefinition.addOrReplaceChild("Tail0", CubeListBuilder.create().texOffs(0, 107).addBox(-3.0f, -2.0f, 0.0f, 6, 6, 6), PartPose.offsetAndRotation(0.0f, 3.0f, 14.0f, -0.0743572f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck1", CubeListBuilder.create().texOffs(0, 190).addBox(-3.0f, 0.0f, 0.0f, 6, 6, 5), PartPose.offsetAndRotation(0.0f, -4.0f, -9.0f, -0.4089647f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck2", CubeListBuilder.create().texOffs(0, 202).addBox(-2.0f, 0.0f, -6.0f, 4, 4, 7), PartPose.offsetAndRotation(0.0f, -3.0f, -9.0f, -0.5948578f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Neck3", CubeListBuilder.create().texOffs(0, 214).addBox(-2.0f, -2.0f, -12.0f, 4, 4, 13), PartPose.offsetAndRotation(0.0f, -5.0f, -15.0f, -0.8179294f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Head1", CubeListBuilder.create().texOffs(0, 232).addBox(-4.0f, -3.0f, -6.0f, 8, 6, 6), PartPose.offsetAndRotation(0.0f, -13.0f, -22.0f, -0.1115358f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Head2", CubeListBuilder.create().texOffs(0, 245).addBox(-3.0f, -2.0f, -4.0f, 6, 4, 4), PartPose.offset(0.0f, -13.0f, -27.0f));
        partdefinition.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(0, 93).addBox(-2.0f, -3.0f, 0.0f, 4, 4, 9), PartPose.offsetAndRotation(0.0f, 5.0f, 19.0f, -0.1115358f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(0, 82).addBox(-1.0f, -1.0f, 0.0f, 2, 2, 8), PartPose.offsetAndRotation(0.0f, 4.0f, 26.0f, -0.0743572f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(0, 73).addBox(-0.5f, -0.5f, 0.0f, 1, 1, 7), PartPose.offsetAndRotation(0.0f, 4.5f, 34.0f, -0.0371786f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BLegupleft", CubeListBuilder.create().texOffs(49, 157).addBox(0.0f, 0.0f, 0.0f, 6, 8, 6), PartPose.offsetAndRotation(2.0f, 9.0f, 7.0f, -0.1487195f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("FLegupleft", CubeListBuilder.create().texOffs(49, 141).addBox(0.0f, 0.0f, -6.0f, 6, 9, 6), PartPose.offset(2.0f, 8.0f, 2.0f));
        partdefinition.addOrReplaceChild("BLegupright", CubeListBuilder.create().texOffs(49, 126).addBox(-6.0f, 0.0f, 0.0f, 6, 8, 6), PartPose.offsetAndRotation(-2.0f, 9.0f, 7.0f, -0.1487144f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("FLegupright", CubeListBuilder.create().texOffs(49, 110).addBox(-6.0f, 0.0f, -6.0f, 6, 9, 6), PartPose.offset(-2.0f, 8.0f, 2.0f));
        partdefinition.addOrReplaceChild("BLegdownright", CubeListBuilder.create().texOffs(115, 157).addBox(-5.0f, 7.0f, -1.0f, 5, 8, 5), PartPose.offset(-2.0f, 9.0f, 7.0f));
        partdefinition.addOrReplaceChild("FLegdownleft", CubeListBuilder.create().texOffs(94, 143).addBox(0.0f, 8.0f, -6.0f, 5, 8, 5), PartPose.offset(2.0f, 8.0f, 2.0f));
        partdefinition.addOrReplaceChild("FLegdownright", CubeListBuilder.create().texOffs(94, 157).addBox(-5.0f, 8.0f, -6.0f, 5, 8, 5), PartPose.offset(-2.0f, 8.0f, 2.0f));
        partdefinition.addOrReplaceChild("BLegdownleft", CubeListBuilder.create().texOffs(115, 143).addBox(0.0f, 7.0f, -1.0f, 5, 8, 5), PartPose.offset(2.0f, 9.0f, 7.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Camarasaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float hf = entity.getHealth() / entity.getMaxHealth();
        float newangle = limbSwingAmount > 0.1f ? Mth.cos(ageInTicks * 1.3f * this.wingspeed) * ((float) Math.PI * 0.25f) * limbSwingAmount : 0.0f;
        this.FLegupleft.xRot = newangle;
        this.FLegdownleft.xRot = newangle;
        this.FLegupright.xRot = -newangle;
        this.FLegdownright.xRot = -newangle;
        this.BLegupleft.xRot = -0.15f - newangle;
        this.BLegdownleft.xRot = -newangle;
        this.BLegupright.xRot = -0.15f + newangle;
        this.BLegdownright.xRot = newangle;
        newangle = Mth.cos(ageInTicks * 1.5f * this.wingspeed * hf) * ((float) Math.PI * 0.25f) * hf;
        if (entity.isInSittingPose()) {
            newangle = 0.0f;
        }
        this.Tail0.yRot = newangle * 0.25f;
        this.Tail1.z = this.Tail0.z + (float) Math.cos(this.Tail0.yRot) * 5.0f;
        this.Tail1.x = this.Tail0.x + (float) Math.sin(this.Tail0.yRot) * 5.0f;
        this.Tail1.yRot = newangle * 0.5f;
        this.Tail2.z = this.Tail1.z + (float) Math.cos(this.Tail1.yRot) * 8.0f;
        this.Tail2.x = this.Tail1.x + (float) Math.sin(this.Tail1.yRot) * 8.0f;
        this.Tail2.yRot = newangle * 0.75f;
        this.Tail3.z = this.Tail2.z + (float) Math.cos(this.Tail2.yRot) * 7.0f;
        this.Tail3.x = this.Tail2.x + (float) Math.sin(this.Tail2.yRot) * 7.0f;
        this.Tail3.yRot = newangle;
        this.Neck1.yRot = (float) Math.toRadians(netHeadYaw) * 0.125f;
        this.Neck2.z = this.Neck1.z;
        this.Neck2.x = this.Neck1.x;
        this.Neck2.yRot = (float) Math.toRadians(netHeadYaw) * 0.25f;
        this.Neck3.z = this.Neck2.z - (float) Math.cos(this.Neck2.yRot) * 6.0f;
        this.Neck3.x = this.Neck2.x - (float) Math.sin(this.Neck2.yRot) * 6.0f;
        this.Neck3.yRot = (float) Math.toRadians(netHeadYaw) * 0.38f;
        this.Head1.z = this.Neck3.z - (float) Math.cos(this.Neck3.yRot) * 7.0f;
        this.Head1.x = this.Neck3.x - (float) Math.sin(this.Neck3.yRot) * 7.0f;
        this.Head1.yRot = (float) Math.toRadians(netHeadYaw);
        this.Head2.z = this.Head1.z - (float) Math.cos(this.Head1.yRot) * 5.0f;
        this.Head2.x = this.Head1.x - (float) Math.sin(this.Head1.yRot) * 5.0f;
        this.Head2.yRot = (float) Math.toRadians(netHeadYaw);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail0.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegupleft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegupleft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegupright.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegupright.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegdownright.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegdownleft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FLegdownright.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BLegdownleft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
