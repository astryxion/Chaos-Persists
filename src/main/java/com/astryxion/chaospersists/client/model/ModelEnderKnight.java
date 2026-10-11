package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.EnderKnight;
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

public class ModelEnderKnight extends EntityModel<EnderKnight> {
    private final float wingspeed;
    private final ModelPart rleg1;
    private final ModelPart rleg3;
    private final ModelPart pelvis;
    private final ModelPart spine1;
    private final ModelPart spine2;
    private final ModelPart spine3;
    private final ModelPart neck;
    private final ModelPart rleg2;
    private final ModelPart rhip;
    private final ModelPart rib4;
    private final ModelPart rib3;
    private final ModelPart rib2;
    private final ModelPart rib1;
    private final ModelPart rfoot1;
    private final ModelPart rfoot3;
    private final ModelPart rcollar;
    private final ModelPart lcollar;
    private final ModelPart lleg3;
    private final ModelPart lleg2;
    private final ModelPart lhip;
    private final ModelPart lleg1;
    private final ModelPart rfoot4;
    private final ModelPart rfoot2;
    private final ModelPart cape2;
    private final ModelPart cape1;
    private final ModelPart lfoot1;
    private final ModelPart lfoot3;
    private final ModelPart lfoot2;
    private final ModelPart lfoot4;
    private final ModelPart head;
    private final ModelPart lshoulder;
    private final ModelPart rshoulder;
    private final ModelPart rarm3;
    private final ModelPart rarm2;
    private final ModelPart rarm1;
    private final ModelPart larm3;
    private final ModelPart larm2;
    private final ModelPart larm1;
    private final ModelPart blade;
    private final ModelPart handle;

    public ModelEnderKnight(float f1) {
        this(LayerDefinition.create(createMesh(), 512, 512).bakeRoot(), f1);
    }

    public ModelEnderKnight(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.rleg1 = root.getChild("rleg1");
        this.rleg3 = root.getChild("rleg3");
        this.pelvis = root.getChild("pelvis");
        this.spine1 = root.getChild("spine1");
        this.spine2 = root.getChild("spine2");
        this.spine3 = root.getChild("spine3");
        this.neck = root.getChild("neck");
        this.rleg2 = root.getChild("rleg2");
        this.rhip = root.getChild("rhip");
        this.rib4 = root.getChild("rib4");
        this.rib3 = root.getChild("rib3");
        this.rib2 = root.getChild("rib2");
        this.rib1 = root.getChild("rib1");
        this.rfoot1 = root.getChild("rfoot1");
        this.rfoot3 = root.getChild("rfoot3");
        this.rcollar = root.getChild("rcollar");
        this.lcollar = root.getChild("lcollar");
        this.lleg3 = root.getChild("lleg3");
        this.lleg2 = root.getChild("lleg2");
        this.lhip = root.getChild("lhip");
        this.lleg1 = root.getChild("lleg1");
        this.rfoot4 = root.getChild("rfoot4");
        this.rfoot2 = root.getChild("rfoot2");
        this.cape2 = root.getChild("cape2");
        this.cape1 = root.getChild("cape1");
        this.lfoot1 = root.getChild("lfoot1");
        this.lfoot3 = root.getChild("lfoot3");
        this.lfoot2 = root.getChild("lfoot2");
        this.lfoot4 = root.getChild("lfoot4");
        this.head = root.getChild("head");
        this.lshoulder = root.getChild("lshoulder");
        this.rshoulder = root.getChild("rshoulder");
        this.rarm3 = root.getChild("rarm3");
        this.rarm2 = root.getChild("rarm2");
        this.rarm1 = root.getChild("rarm1");
        this.larm3 = root.getChild("larm3");
        this.larm2 = root.getChild("larm2");
        this.larm1 = root.getChild("larm1");
        this.blade = root.getChild("blade");
        this.handle = root.getChild("handle");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(20, 50).addBox(0.0f, 12.0f, -1.0f, 1, 15, 1), PartPose.offset(-7.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("rleg3", CubeListBuilder.create().texOffs(20, 100).addBox(0.0f, 0.0f, 0.0f, 1, 14, 2), PartPose.offsetAndRotation(-6.0f, -5.0f, -2.0f, -0.1f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("pelvis", CubeListBuilder.create().texOffs(20, 150).addBox(0.0f, 0.0f, 0.0f, 3, 3, 3), PartPose.offset(-5.0f, -6.0f, -2.0f));
        partdefinition.addOrReplaceChild("spine1", CubeListBuilder.create().texOffs(20, 200).addBox(0.0f, 0.0f, 0.0f, 1, 5, 1), PartPose.offsetAndRotation(-4.0f, -9.0f, 1.0f, -0.3f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(20, 250).addBox(0.0f, 0.0f, 0.0f, 1, 5, 1), PartPose.offset(-4.0f, -13.0f, 1.0f));
        partdefinition.addOrReplaceChild("spine3", CubeListBuilder.create().texOffs(20, 300).addBox(0.0f, 0.0f, 0.0f, 1, 5, 1), PartPose.offsetAndRotation(-4.0f, -17.0f, 0.0f, 0.2f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(20, 11).addBox(0.0f, 0.0f, 0.0f, 5, 3, 3), PartPose.offset(-6.0f, -20.0f, 0.0f));
        partdefinition.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(20, 400).addBox(0.0f, 0.0f, 0.0f, 1, 14, 2), PartPose.offsetAndRotation(-8.0f, -5.0f, -2.0f, -0.1f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rhip", CubeListBuilder.create().texOffs(20, 450).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1), PartPose.offset(-7.0f, -4.0f, -2.0f));
        partdefinition.addOrReplaceChild("rib4", CubeListBuilder.create().texOffs(20, 79).addBox(0.0f, 0.0f, 0.0f, 3, 1, 1), PartPose.offset(-5.0f, -9.0f, 1.0f));
        partdefinition.addOrReplaceChild("rib3", CubeListBuilder.create().texOffs(20, 86).addBox(0.0f, 0.0f, 0.0f, 3, 1, 1), PartPose.offset(-5.0f, -11.0f, 1.0f));
        partdefinition.addOrReplaceChild("rib2", CubeListBuilder.create().texOffs(20, 94).addBox(0.0f, 0.0f, 0.0f, 5, 1, 1), PartPose.offset(-6.0f, -13.0f, 1.0f));
        partdefinition.addOrReplaceChild("rib1", CubeListBuilder.create().texOffs(20, 122).addBox(0.0f, 0.0f, 0.0f, 5, 1, 1), PartPose.offset(-6.0f, -16.0f, 0.0f));
        partdefinition.addOrReplaceChild("rfoot1", CubeListBuilder.create().texOffs(20, 131).addBox(0.0f, 21.0f, -2.0f, 3, 8, 3), PartPose.offset(-8.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("rfoot3", CubeListBuilder.create().texOffs(20, 162).addBox(0.0f, 27.0f, -5.0f, 3, 2, 6), PartPose.offset(-8.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("rcollar", CubeListBuilder.create().texOffs(20, 243).addBox(0.0f, 0.0f, 0.0f, 5, 1, 1), PartPose.offset(-11.0f, -19.0f, 1.0f));
        partdefinition.addOrReplaceChild("lcollar", CubeListBuilder.create().texOffs(20, 286).addBox(0.0f, 0.0f, 0.0f, 5, 1, 1), PartPose.offset(-1.0f, -19.0f, 1.0f));
        partdefinition.addOrReplaceChild("lleg3", CubeListBuilder.create().texOffs(48, 159).addBox(0.0f, 0.0f, 0.0f, 1, 14, 2), PartPose.offsetAndRotation(-2.0f, -5.0f, -2.0f, -0.1f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(28, 187).addBox(0.0f, 0.0f, 0.0f, 1, 14, 2), PartPose.offsetAndRotation(0.0f, -5.0f, -2.0f, -0.1f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lhip", CubeListBuilder.create().texOffs(32, 219).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1), PartPose.offset(-1.0f, -4.0f, -2.0f));
        partdefinition.addOrReplaceChild("lleg1", CubeListBuilder.create().texOffs(36, 224).addBox(0.0f, 12.0f, -1.0f, 1, 15, 1), PartPose.offset(-1.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("rfoot4", CubeListBuilder.create().texOffs(33, 254).addBox(0.0f, 26.0f, -3.0f, 3, 1, 1), PartPose.offset(-8.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("rfoot2", CubeListBuilder.create().texOffs(32, 36).addBox(0.0f, 19.5f, -19.0f, 3, 1, 5), PartPose.offsetAndRotation(-8.0f, -5.0f, -2.0f, 0.6f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("cape2", CubeListBuilder.create().texOffs(51, 276).addBox(-4.0f, 0.0f, 0.0f, 9, 24, 0), PartPose.offset(-4.0f, -20.0f, 4.0f));
        partdefinition.addOrReplaceChild("cape1", CubeListBuilder.create().texOffs(51, 264).addBox(0.0f, 0.0f, 0.0f, 9, 1, 1), PartPose.offset(-8.0f, -20.0f, 3.0f));
        partdefinition.addOrReplaceChild("lfoot1", CubeListBuilder.create().texOffs(44, 182).addBox(0.0f, 21.0f, -2.0f, 3, 8, 3), PartPose.offset(-2.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("lfoot3", CubeListBuilder.create().texOffs(52, 200).addBox(0.0f, 27.0f, -5.0f, 3, 2, 6), PartPose.offset(-2.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("lfoot2", CubeListBuilder.create().texOffs(52, 218).addBox(0.0f, 19.5f, -19.0f, 3, 1, 5), PartPose.offsetAndRotation(-2.0f, -5.0f, -2.0f, 0.6f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lfoot4", CubeListBuilder.create().texOffs(48, 235).addBox(0.0f, 26.0f, -3.0f, 3, 1, 1), PartPose.offset(-2.0f, -5.0f, -2.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 106).addBox(-4.0f, -8.0f, -4.0f, 7, 6, 6), PartPose.offset(-3.0f, -18.0f, 3.0f));
        partdefinition.addOrReplaceChild("lshoulder", CubeListBuilder.create().texOffs(48, 16).addBox(0.0f, 0.0f, 0.0f, 5, 4, 4), PartPose.offset(2.0f, -21.0f, 0.0f));
        partdefinition.addOrReplaceChild("rshoulder", CubeListBuilder.create().texOffs(48, 16).addBox(0.0f, 0.0f, 0.0f, 5, 4, 4), PartPose.offset(-14.0f, -21.0f, 0.0f));
        partdefinition.addOrReplaceChild("rarm3", CubeListBuilder.create().texOffs(39, 64).addBox(0.0f, 0.0f, 0.0f, 1, 12, 1), PartPose.offsetAndRotation(-11.0f, -18.0f, 1.0f, -0.5f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(57, 62).addBox(0.0f, 0.0f, 0.0f, 1, 12, 1), PartPose.offsetAndRotation(-13.0f, -18.0f, 1.0f, -0.5f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rarm1", CubeListBuilder.create().texOffs(49, 81).addBox(0.0f, 0.0f, 0.0f, 1, 11, 1), PartPose.offsetAndRotation(-12.0f, -18.0f, 2.0f, -1.0f, -1.0f, 0.0f));
        partdefinition.addOrReplaceChild("larm3", CubeListBuilder.create().texOffs(49, 129).addBox(0.0f, 0.0f, 0.0f, 1, 12, 1), PartPose.offsetAndRotation(3.0f, -18.0f, 1.0f, -0.5f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(64, 133).addBox(0.0f, 0.0f, 0.0f, 1, 12, 1), PartPose.offsetAndRotation(5.0f, -18.0f, 1.0f, -0.5f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("larm1", CubeListBuilder.create().texOffs(22, 316).addBox(0.0f, 0.0f, 0.0f, 1, 11, 1), PartPose.offsetAndRotation(4.0f, -18.0f, 1.0f, -1.0f, 1.0f, 0.0f));
        partdefinition.addOrReplaceChild("blade", CubeListBuilder.create().texOffs(36, 304).addBox(0.0f, -34.0f, -2.0f, 1, 32, 6), PartPose.offsetAndRotation(-4.0f, -2.0f, -8.0f, 0.35f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(18, 26).addBox(0.0f, -2.0f, 0.0f, 1, 4, 1), PartPose.offsetAndRotation(-4.0f, -2.0f, -8.0f, 0.35f, 0.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(EnderKnight entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float newangle = 0.0f;
        newangle = (double)f1 > 0.1 ? Mth.cos((float)(f2 * 1.3f * this.wingspeed)) * (float)Math.PI * 0.25f * f1 : 0.0f;
        this.lfoot1.xRot = newangle;
        this.lfoot2.xRot = 0.6f + newangle;
        this.lfoot3.xRot = newangle;
        this.lfoot4.xRot = newangle;
        this.lleg1.xRot = newangle;
        this.lleg2.xRot = -0.1f + newangle;
        this.lleg3.xRot = -0.1f + newangle;
        this.rfoot1.xRot = -newangle;
        this.rfoot2.xRot = 0.6f - newangle;
        this.rfoot3.xRot = -newangle;
        this.rfoot4.xRot = -newangle;
        this.rleg1.xRot = -newangle;
        this.rleg2.xRot = -0.1f - newangle;
        this.rleg3.xRot = -0.1f - newangle;
        this.cape2.zRot = newangle / 4.0f;
        this.cape2.xRot = newangle = Mth.cos((float)(f2 * 0.7f * this.wingspeed)) * (float)Math.PI * 0.02f;
        this.head.yRot = (float)Math.toRadians(f3) * 0.45f;
        if (this.head.yRot > 0.45f) {
            this.head.yRot = 0.45f;
        }
        if (this.head.yRot < -0.45f) {
            this.head.yRot = -0.45f;
        }
        newangle = Mth.cos((float)(f2 * 2.7f * this.wingspeed)) * (float)Math.PI * 0.3f;
        if (entity.isScreaming()) {
            this.larm2.xRot = -1.2f + newangle;
            this.larm3.xRot = -1.2f + newangle;
            this.rarm2.xRot = -1.2f + newangle;
            this.rarm3.xRot = -1.2f + newangle;
            this.larm1.xRot = -1.8f + newangle;
            this.rarm1.xRot = -1.8f + newangle;
            this.blade.xRot = this.handle.xRot = 0.5f + newangle * 3.0f / 2.0f;
        } else {
            this.larm2.xRot = -0.5f;
            this.larm3.xRot = -0.5f;
            this.larm1.zRot = 0.0f;
            this.larm1.yRot = 1.0f;
            this.larm1.xRot = -1.0f;
            this.rarm2.xRot = -0.5f;
            this.rarm3.xRot = -0.5f;
            this.rarm1.zRot = 0.0f;
            this.rarm1.yRot = -1.0f;
            this.rarm1.xRot = -1.0f;
            this.handle.xRot = 0.35f;
            this.blade.xRot = 0.35f;
        }
        this.larm1.y = (float)((double)this.larm2.y + Math.cos(this.larm2.xRot) * 10.0);
        this.larm1.z = (float)((double)this.larm2.z + Math.sin(this.larm2.xRot) * 10.0);
        this.rarm1.y = (float)((double)this.rarm2.y + Math.cos(this.rarm2.xRot) * 10.0);
        this.rarm1.z = (float)((double)this.rarm2.z + Math.sin(this.rarm2.xRot) * 10.0);
        this.blade.y = this.handle.y = (float)((double)this.rarm1.y + Math.cos(this.rarm1.xRot) * 7.0) + 1.0f;
        this.blade.z = this.handle.z = (float)((double)this.rarm1.z + Math.sin(this.rarm1.xRot) * 7.0);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.rleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.pelvis.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.spine3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rhip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rib1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rcollar.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lcollar.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lhip.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfoot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cape2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cape1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfoot4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lshoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rshoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.blade.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.handle.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
