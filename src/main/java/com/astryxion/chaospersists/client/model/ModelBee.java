package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Bee;
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

public class ModelBee extends EntityModel<Bee> {
    private final float wingspeed;
    private final ModelPart Sting;
    private final ModelPart Abdomnem1;
    private final ModelPart Abdomnem2;
    private final ModelPart Abdomnem3;
    private final ModelPart Abdomnem4;
    private final ModelPart Abdomnem5;
    private final ModelPart MainBody;
    private final ModelPart Neck;
    private final ModelPart Head;
    private final ModelPart WingRight;
    private final ModelPart WingLeft;
    private final ModelPart RA1;
    private final ModelPart LA1;
    private final ModelPart LA2;
    private final ModelPart RA2;
    private final ModelPart RA3;
    private final ModelPart LA3;
    private final ModelPart LeftPom;
    private final ModelPart RightPom;
    private final ModelPart LeftPincerExtra;
    private final ModelPart LeftPincerMain;
    private final ModelPart RightPincerMain;
    private final ModelPart RightPincerExtra;

    public ModelBee(float f1) {
        this(LayerDefinition.create(createMesh(), 256, 256).bakeRoot(), f1);
    }

    public ModelBee(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Sting = root.getChild("Sting");
        this.Abdomnem1 = root.getChild("Abdomnem1");
        this.Abdomnem2 = root.getChild("Abdomnem2");
        this.Abdomnem3 = root.getChild("Abdomnem3");
        this.Abdomnem4 = root.getChild("Abdomnem4");
        this.Abdomnem5 = root.getChild("Abdomnem5");
        this.MainBody = root.getChild("MainBody");
        this.Neck = root.getChild("Neck");
        this.Head = root.getChild("Head");
        this.WingRight = root.getChild("WingRight");
        this.WingLeft = root.getChild("WingLeft");
        this.RA1 = root.getChild("RA1");
        this.LA1 = root.getChild("LA1");
        this.LA2 = root.getChild("LA2");
        this.RA2 = root.getChild("RA2");
        this.RA3 = root.getChild("RA3");
        this.LA3 = root.getChild("LA3");
        this.LeftPom = root.getChild("LeftPom");
        this.RightPom = root.getChild("RightPom");
        this.LeftPincerExtra = root.getChild("LeftPincerExtra");
        this.LeftPincerMain = root.getChild("LeftPincerMain");
        this.RightPincerMain = root.getChild("RightPincerMain");
        this.RightPincerExtra = root.getChild("RightPincerExtra");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Sting", CubeListBuilder.create().texOffs(68, 0).addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2), PartPose.offsetAndRotation(0.0f, 16.0f, 1.0f, -0.7853982f, 0.0f, 0.0f));
        root.addOrReplaceChild("Abdomnem1", CubeListBuilder.create().texOffs(64, 12).addBox(-2.0f, 0.0f, 0.0f, 4, 8, 4), PartPose.offsetAndRotation(0.0f, 9.0f, 2.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("Abdomnem2", CubeListBuilder.create().texOffs(60, 24).addBox(-3.0f, 0.0f, 0.0f, 6, 6, 6), PartPose.offset(0.0f, 5.0f, 0.0f));
        root.addOrReplaceChild("Abdomnem3", CubeListBuilder.create().texOffs(56, 36).addBox(-4.0f, 0.0f, 0.0f, 8, 7, 8), PartPose.offsetAndRotation(0.0f, 1.0f, -2.0f, 0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("Abdomnem4", CubeListBuilder.create().texOffs(53, 51).addBox(-5.0f, 0.0f, 0.0f, 10, 12, 10), PartPose.offsetAndRotation(0.0f, -6.0f, -8.0f, 0.5934119f, 0.0f, 0.0f));
        root.addOrReplaceChild("Abdomnem5", CubeListBuilder.create().texOffs(48, 73).addBox(-6.0f, 0.0f, 0.0f, 12, 12, 12), PartPose.offsetAndRotation(0.0f, -6.0f, -15.0f, 1.099557f, 0.0f, 0.0f));
        root.addOrReplaceChild("MainBody", CubeListBuilder.create().texOffs(48, 97).addBox(-6.0f, 0.0f, -6.0f, 12, 14, 12), PartPose.offsetAndRotation(0.0f, -12.0f, -24.0f, 1.48353f, 0.0f, 0.0f));
        root.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(55, 123).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8), PartPose.offset(0.0f, -12.0f, -23.0f));
        root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(51, 139).addBox(-5.0f, -5.0f, -10.0f, 10, 10, 10), PartPose.offsetAndRotation(0.0f, -13.0f, -28.0f, 0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(0, 91).addBox(0.0f, 0.0f, 0.0f, 0, 8, 24), PartPose.offsetAndRotation(-4.0f, -14.0f, -15.0f, -0.7853982f, -0.5235988f, 2.617994f));
        root.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(96, 91).addBox(0.0f, 0.0f, 0.0f, 0, 8, 24), PartPose.offsetAndRotation(3.0f, -14.0f, -15.0f, -0.7853982f, 0.5235988f, -2.617994f));
        root.addOrReplaceChild("RA1", CubeListBuilder.create().texOffs(47, 152).addBox(0.0f, -6.0f, -1.0f, 1, 6, 1), PartPose.offsetAndRotation(-3.0f, -17.0f, -31.0f, 0.2617994f, 0.5235988f, 0.0f));
        root.addOrReplaceChild("LA1", CubeListBuilder.create().texOffs(91, 152).addBox(0.0f, -6.0f, -1.0f, 1, 6, 1), PartPose.offsetAndRotation(2.0f, -17.0f, -32.0f, 0.2617994f, -0.5235988f, 0.0f));
        root.addOrReplaceChild("LA2", CubeListBuilder.create().texOffs(91, 145).addBox(0.0f, -11.0f, 0.0f, 1, 6, 1), PartPose.offsetAndRotation(2.0f, -17.0f, -32.0f, 0.4363323f, -0.6108652f, 0.0f));
        root.addOrReplaceChild("RA2", CubeListBuilder.create().texOffs(47, 145).addBox(0.0f, -11.0f, 0.0f, 1, 6, 1), PartPose.offsetAndRotation(-3.0f, -17.0f, -31.0f, 0.4363323f, 0.6108652f, 0.0f));
        root.addOrReplaceChild("RA3", CubeListBuilder.create().texOffs(47, 138).addBox(0.0f, -16.0f, 2.0f, 1, 6, 1), PartPose.offsetAndRotation(-3.0f, -17.0f, -31.0f, 0.6108652f, 0.6981317f, 0.0f));
        root.addOrReplaceChild("LA3", CubeListBuilder.create().texOffs(91, 138).addBox(0.0f, -16.0f, 2.0f, 1, 6, 1), PartPose.offsetAndRotation(2.0f, -17.0f, -32.0f, 0.6108652f, -0.6981317f, 0.0f));
        root.addOrReplaceChild("LeftPom", CubeListBuilder.create().texOffs(89, 134).addBox(4.0f, -16.0f, -6.0f, 2, 2, 2), PartPose.offset(2.0f, -17.0f, -32.0f));
        root.addOrReplaceChild("RightPom", CubeListBuilder.create().texOffs(45, 134).addBox(-5.0f, -16.0f, -7.0f, 2, 2, 2), PartPose.offset(-3.0f, -17.0f, -31.0f));
        root.addOrReplaceChild("LeftPincerExtra", CubeListBuilder.create().texOffs(71, 166).addBox(-2.0f, 0.0f, -6.0f, 2, 1, 2), PartPose.offsetAndRotation(2.0f, -8.0f, -36.0f, 0.1745329f, -0.1745329f, 0.0f));
        root.addOrReplaceChild("LeftPincerMain", CubeListBuilder.create().texOffs(71, 159).addBox(0.0f, 0.0f, -6.0f, 2, 1, 6), PartPose.offsetAndRotation(2.0f, -8.0f, -36.0f, 0.1745329f, -0.1745329f, 0.0f));
        root.addOrReplaceChild("RightPincerMain", CubeListBuilder.create().texOffs(55, 159).addBox(0.0f, 0.0f, -6.0f, 2, 1, 6), PartPose.offsetAndRotation(-4.0f, -8.0f, -36.0f, 0.1745329f, 0.1745329f, 0.0f));
        root.addOrReplaceChild("RightPincerExtra", CubeListBuilder.create().texOffs(63, 166).addBox(2.0f, 0.0f, -6.0f, 2, 1, 2), PartPose.offsetAndRotation(-4.0f, -8.0f, -36.0f, 0.1745329f, 0.1745329f, 0.0f));
        return mesh;
    }

    @Override
    public void setupAnim(Bee entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = 0.0f;
        Bee b = (Bee)entity;
        newangle = Mth.cos((float)(ageInTicks * 1.1f * this.wingspeed)) * 3.1415927f * 0.3f;
        this.WingLeft.zRot = -1.745f - newangle;
        this.WingRight.zRot = 1.754f + newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.3f * this.wingspeed)) * 3.1415927f * 0.1f;
        this.LeftPincerMain.yRot = -0.274f + newangle;
        this.LeftPincerExtra.yRot = -0.274f + newangle;
        this.RightPincerMain.yRot = 0.274f - newangle;
        this.RightPincerExtra.yRot = 0.274f - newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.21f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.LA1.xRot = 0.261f + newangle;
        this.LA2.xRot = 0.436f + newangle;
        this.LA3.xRot = 0.611f + newangle;
        this.LeftPom.xRot = newangle;
        newangle = Mth.cos((float)(ageInTicks * 0.27f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.RA1.xRot = 0.261f + newangle;
        this.RA2.xRot = 0.436f + newangle;
        this.RA3.xRot = 0.611f + newangle;
        this.RightPom.xRot = newangle;
        this.LA1.zRot = newangle = Mth.cos((float)(ageInTicks * 0.31f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.LA2.zRot = newangle;
        this.LA3.zRot = newangle;
        this.LeftPom.zRot = newangle;
        this.RA1.zRot = newangle = Mth.cos((float)(ageInTicks * 0.37f * this.wingspeed)) * 3.1415927f * 0.06f;
        this.RA2.zRot = newangle;
        this.RA3.zRot = newangle;
        this.RightPom.zRot = newangle;
        newangle = b.getAttacking() == 0 ? Mth.cos((float)(ageInTicks * 0.021f * this.wingspeed)) * 3.1415927f * 0.023f : Mth.cos((float)(ageInTicks * 0.11f * this.wingspeed)) * 3.1415927f * 0.055f;
        this.Abdomnem5.xRot = 1.099f + newangle;
        this.Abdomnem4.xRot = this.Abdomnem5.xRot + newangle - 0.35f;
        this.Abdomnem4.y = (float)((double)this.Abdomnem5.y + Math.cos(this.Abdomnem5.xRot) * 10.0);
        this.Abdomnem4.z = (float)((double)this.Abdomnem5.z + Math.sin(this.Abdomnem5.xRot) * 10.0);
        this.Abdomnem3.xRot = this.Abdomnem4.xRot + newangle - 0.35f;
        this.Abdomnem3.y = (float)((double)this.Abdomnem4.y + Math.cos(this.Abdomnem4.xRot) * 10.0);
        this.Abdomnem3.z = (float)((double)this.Abdomnem4.z + Math.sin(this.Abdomnem4.xRot) * 10.0);
        this.Abdomnem2.xRot = this.Abdomnem3.xRot + newangle - 0.35f;
        this.Abdomnem2.y = (float)((double)this.Abdomnem3.y + Math.cos(this.Abdomnem3.xRot) * 6.0);
        this.Abdomnem2.z = (float)((double)this.Abdomnem3.z + Math.sin(this.Abdomnem3.xRot) * 6.0);
        this.Abdomnem1.xRot = this.Abdomnem2.xRot + newangle - 0.35f;
        this.Abdomnem1.y = (float)((double)this.Abdomnem2.y + Math.cos(this.Abdomnem2.xRot) * 5.0);
        this.Abdomnem1.z = (float)((double)this.Abdomnem2.z + Math.sin(this.Abdomnem2.xRot) * 5.0);
        this.Sting.xRot = this.Abdomnem1.xRot + newangle - 0.35f;
        this.Sting.y = (float)((double)this.Abdomnem1.y + Math.cos(this.Abdomnem1.xRot) * 7.0);
        this.Sting.z = 1.0f + (float)((double)this.Abdomnem1.z + Math.sin(this.Abdomnem1.xRot) * 7.0);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Sting.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Abdomnem5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.MainBody.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingRight.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.WingLeft.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RA1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LA1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LA2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RA2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RA3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LA3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPincerExtra.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LeftPincerMain.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPincerMain.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RightPincerExtra.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
