package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.WaterDragon;
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

public class ModelWaterDragon extends EntityModel<WaterDragon> {
    private final float wingspeed;
    private final ModelPart Head;
    private final ModelPart neck1;
    private final ModelPart body1;
    private final ModelPart Leg8;
    private final ModelPart Leg2;
    private final ModelPart Leg7;
    private final ModelPart Leg1;
    private final ModelPart neck2;
    private final ModelPart neck3;
    private final ModelPart neck4;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart tail1;
    private final ModelPart tailmiddle;
    private final ModelPart tailtop;
    private final ModelPart tailbottom;
    private final ModelPart nose;
    private final ModelPart headfin;
    private final ModelPart rightear;
    private final ModelPart leftear;
    private final ModelPart neackfin;
    private final ModelPart Bodyfin;
    private final ModelPart jaw;

    public ModelWaterDragon(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 128, 128).bakeRoot());
    }

    public ModelWaterDragon(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.Head = root.getChild("Head");
        this.neck1 = root.getChild("neck1");
        this.body1 = root.getChild("body1");
        this.Leg8 = root.getChild("Leg8");
        this.Leg2 = root.getChild("Leg2");
        this.Leg7 = root.getChild("Leg7");
        this.Leg1 = root.getChild("Leg1");
        this.neck2 = root.getChild("neck2");
        this.neck3 = root.getChild("neck3");
        this.neck4 = root.getChild("neck4");
        this.body2 = root.getChild("body2");
        this.body3 = root.getChild("body3");
        this.body4 = root.getChild("body4");
        this.tail1 = root.getChild("tail1");
        this.tailmiddle = root.getChild("tailmiddle");
        this.tailtop = root.getChild("tailtop");
        this.tailbottom = root.getChild("tailbottom");
        this.nose = root.getChild("nose");
        this.headfin = root.getChild("headfin");
        this.rightear = root.getChild("rightear");
        this.leftear = root.getChild("leftear");
        this.neackfin = root.getChild("neackfin");
        this.Bodyfin = root.getChild("Bodyfin");
        this.jaw = root.getChild("jaw");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(79, 64).addBox(-4.0f, -4.0f, -8.0f, 7, 8, 8), PartPose.offset(0.0f, 0.0f, -3.0f));
        root.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(29, 70).addBox(-2.0f, 0.0f, -3.0f, 5, 5, 5), PartPose.offsetAndRotation(-1.0f, 4.0f, -5.0f, -0.1858931f, 0.0f, 0.0f));
        root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 33).addBox(-5.0f, -4.0f, -6.0f, 9, 9, 9), PartPose.offset(0.0f, 19.0f, 2.0f));
        root.addOrReplaceChild("Leg8", CubeListBuilder.create().texOffs(23, 25).addBox(0.0f, -1.0f, -1.0f, 9, 2, 3), PartPose.offsetAndRotation(3.0f, 22.0f, -2.0f, 0.0f, 0.5759587f, 0.1919862f));
        root.addOrReplaceChild("Leg2", CubeListBuilder.create().texOffs(80, 18).addBox(0.0f, -1.0f, -1.0f, 9, 2, 3), PartPose.offsetAndRotation(2.0f, 22.0f, 13.0f, 0.0f, -0.5759587f, 0.1919862f));
        root.addOrReplaceChild("Leg7", CubeListBuilder.create().texOffs(23, 18).addBox(-9.0f, -1.0f, -1.0f, 9, 2, 3), PartPose.offsetAndRotation(-4.0f, 22.0f, -1.0f, 0.0f, -0.5759587f, -0.1919862f));
        root.addOrReplaceChild("Leg1", CubeListBuilder.create().texOffs(80, 25).addBox(-9.0f, -1.0f, -2.0f, 9, 2, 3), PartPose.offsetAndRotation(-3.0f, 22.0f, 14.0f, 0.0f, 0.5759587f, -0.1919862f));
        root.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(0, 11).addBox(-2.0f, 0.0f, -2.0f, 5, 5, 5), PartPose.offsetAndRotation(-1.0f, 9.0f, -7.0f, 0.1115358f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck3", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0f, 0.0f, -2.0f, 5, 5, 5), PartPose.offsetAndRotation(-1.0f, 14.0f, -6.0f, 0.4461433f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck4", CubeListBuilder.create().texOffs(26, 12).addBox(-3.0f, 0.0f, -2.0f, 5, 3, 3), PartPose.offsetAndRotation(0.0f, 18.0f, -4.0f, 1.226894f, 0.0f, 0.0f));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 52).addBox(-5.0f, -5.0f, 0.0f, 7, 7, 9), PartPose.offset(1.0f, 21.0f, 5.0f));
        root.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 69).addBox(-3.0f, -3.0f, 0.0f, 5, 5, 7), PartPose.offset(0.0f, 20.0f, 14.0f));
        root.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(0, 89).addBox(-1.0f, -1.0f, 0.0f, 3, 3, 5), PartPose.offset(-1.0f, 19.0f, 21.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 82).addBox(0.0f, 0.0f, 0.0f, 1, 2, 3), PartPose.offset(-1.0f, 19.0f, 25.0f));
        root.addOrReplaceChild("tailmiddle", CubeListBuilder.create().texOffs(55, 37).addBox(-1.0f, -6.0f, 0.0f, 2, 11, 9), PartPose.offset(0.0f, 19.0f, 28.0f));
        root.addOrReplaceChild("tailtop", CubeListBuilder.create().texOffs(82, 36).addBox(-1.0f, -11.0f, 0.0f, 2, 11, 9), PartPose.offsetAndRotation(0.0f, 14.0f, 28.0f, -0.6320364f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailbottom", CubeListBuilder.create().texOffs(56, 60).addBox(0.0f, 0.0f, 0.0f, 2, 11, 9), PartPose.offsetAndRotation(-1.0f, 23.0f, 28.0f, 0.6320361f, 0.0f, -0.0174533f));
        root.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(54, 19).addBox(-3.0f, -2.0f, -5.0f, 5, 5, 5), PartPose.offset(0.0f, -2.0f, -11.0f));
        root.addOrReplaceChild("headfin", CubeListBuilder.create().texOffs(0, 99).addBox(0.0f, -5.0f, 0.0f, 0, 10, 9), PartPose.offsetAndRotation(0.0f, -4.0f, -6.0f, 0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("rightear", CubeListBuilder.create().texOffs(38, 32).addBox(0.0f, 0.0f, 0.0f, 0, 5, 5), PartPose.offsetAndRotation(-4.0f, -2.0f, -5.0f, 0.0698132f, -0.418879f, 0.0f));
        root.addOrReplaceChild("leftear", CubeListBuilder.create().texOffs(38, 32).addBox(0.0f, 0.0f, 0.0f, 0, 5, 5), PartPose.offsetAndRotation(3.0f, -2.0f, -5.0f, 0.0698132f, 0.418879f, 0.0f));
        root.addOrReplaceChild("neackfin", CubeListBuilder.create().texOffs(42, 47).addBox(0.0f, -1.0f, 0.0f, 0, 5, 5), PartPose.offsetAndRotation(0.0f, 3.0f, -3.0f, -0.185895f, 0.0f, 0.0f));
        root.addOrReplaceChild("Bodyfin", CubeListBuilder.create().texOffs(21, 91).addBox(0.0f, -6.0f, -3.0f, 0, 10, 9), PartPose.offsetAndRotation(0.0f, 15.0f, 2.0f, -0.0698132f, 0.0f, 0.0f));
        root.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(76, 8).addBox(-2.0f, 0.0f, -5.0f, 5, 1, 5), PartPose.offset(-1.0f, 3.0f, -10.0f));
        return mesh;
    }

    @Override
    public void setupAnim(WaterDragon e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        float pi4 = 0.7853982f;
        float root13 = (float)Math.sqrt(13.0);
        float root20 = (float)Math.sqrt(20.0);
        newangle = (double)limbSwingAmount > 0.1 ? Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.2f * limbSwingAmount : 0.0f;
        this.body3.yRot = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed)) * 3.1415927f * 0.4f * limbSwingAmount;
        this.body4.z = this.body3.z + (float)Math.cos(this.body3.yRot) * 7.0f;
        this.body4.x = this.body3.x - 1.0f + (float)Math.sin(this.body3.yRot) * 7.0f;
        this.body4.yRot = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed - pi4)) * 3.1415927f * 0.4f * limbSwingAmount;
        this.tail1.z = this.body4.z + (float)Math.cos(this.body4.yRot) * 5.0f;
        this.tail1.x = this.body4.x + (float)Math.sin(this.body4.yRot) * 5.0f;
        this.tail1.yRot = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed - 2.0f * pi4)) * 3.1415927f * 0.4f * limbSwingAmount;
        this.tailmiddle.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 3.0f;
        this.tailmiddle.x = this.tail1.x + (float)Math.sin(this.tail1.yRot) * 3.0f;
        this.tailtop.yRot = this.tailmiddle.yRot = Mth.cos((float)(ageInTicks * 1.3f * this.wingspeed - 3.0f * pi4)) * 3.1415927f * 0.4f * limbSwingAmount;
        this.tailtop.z = this.tailmiddle.z;
        this.tailtop.x = this.tailmiddle.x;
        this.tailbottom.yRot = this.tailmiddle.yRot;
        this.tailbottom.z = this.tailmiddle.z;
        this.tailbottom.x = this.tailmiddle.x;
        this.Leg8.yRot = 0.58f + newangle;
        this.Leg2.yRot = -0.58f + newangle;
        this.Leg7.yRot = -0.58f - newangle;
        this.Leg1.yRot = 0.58f - newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.8f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.leftear.yRot = 0.62f + newangle;
        this.rightear.yRot = -0.62f - newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.7f * this.wingspeed)) * 3.1415927f * 0.02f;
        if (e.isInSittingPose()) {
            newangle = 0.0f;
        }
        this.Bodyfin.zRot = newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.6f * this.wingspeed)) * 3.1415927f * 0.1f;
        if (e.isInSittingPose()) {
            newangle = 0.0f;
        }
        this.neackfin.yRot = newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.5f * this.wingspeed)) * 3.1415927f * 0.05f;
        if (e.isInSittingPose()) {
            newangle = 0.0f;
        }
        this.headfin.yRot = newangle;
        this.jaw.xRot = e.getAttacking() == 1 ? (newangle = Mth.cos((float)(ageInTicks * 1.2f * this.wingspeed)) * 3.1415927f * 0.25f) : (e.getAttacking() == 2 ? 0.45f : -0.25f);
        this.Head.yRot = newangle = (float)Math.toRadians(netHeadYaw) * 0.75f;
        this.nose.yRot = newangle;
        this.nose.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 8.0f;
        this.nose.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 8.0f;
        this.jaw.yRot = newangle;
        this.jaw.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 7.0f;
        this.jaw.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 7.0f - 1.0f;
        this.headfin.yRot = newangle;
        this.headfin.z = this.Head.z - (float)Math.cos(this.Head.yRot) * 3.0f;
        this.headfin.x = this.Head.x - (float)Math.sin(this.Head.yRot) * 3.0f;
        this.leftear.yRot += newangle;
        this.leftear.z = this.Head.z - (float)Math.cos(this.Head.yRot - pi4) * root13;
        this.leftear.x = this.Head.x - (float)Math.sin(this.Head.yRot - pi4) * root13;
        this.rightear.yRot += newangle;
        this.rightear.z = this.Head.z - (float)Math.cos(this.Head.yRot + pi4) * root20;
        this.rightear.x = this.Head.x - (float)Math.sin(this.Head.yRot + pi4) * root20;

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Leg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmiddle.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailtop.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailbottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.headfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neackfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Bodyfin.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
