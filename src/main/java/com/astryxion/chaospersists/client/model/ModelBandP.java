package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.item.BandP;
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

public class ModelBandP extends EntityModel<BandP> {
    private final float wingspeed;
    private final ModelPart belly;
    private final ModelPart chest;
    private final ModelPart head;
    private final ModelPart lleg;
    private final ModelPart rleg;
    private final ModelPart larm;
    private final ModelPart rarm;

    public ModelBandP(float wingspeed) {
        this(wingspeed, LayerDefinition.create(createMesh(), 64, 128).bakeRoot());
    }

    public ModelBandP(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.belly = root.getChild("belly");
        this.chest = root.getChild("chest");
        this.head = root.getChild("head");
        this.lleg = root.getChild("lleg");
        this.rleg = root.getChild("rleg");
        this.larm = root.getChild("larm");
        this.rarm = root.getChild("rarm");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();
        root.addOrReplaceChild(
                "belly",
                CubeListBuilder.create()
                        .texOffs(0, 61)
                        
                        .addBox(-8.0f, -5.0f, -7.0f, 16, 10, 16),
                PartPose.offsetAndRotation(0.0f, 12.0f, 0.0f, 0.0698132f, 0.0f, 0.0f));
        root.addOrReplaceChild(
                "chest",
                CubeListBuilder.create().texOffs(0, 42).addBox(-5.0f, -3.0f, -5.0f, 10, 6, 10),
                PartPose.offset(0.0f, 5.0f, 2.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 11).addBox(-3.0f, -5.0f, -3.0f, 6, 6, 6),
                PartPose.offset(0.0f, 1.0f, 3.0f));
        root.addOrReplaceChild(
                "lleg",
                CubeListBuilder.create().texOffs(25, 90).addBox(-2.0f, 0.0f, -3.0f, 6, 8, 6),
                PartPose.offset(2.0f, 16.0f, 2.0f));
        root.addOrReplaceChild(
                "rleg",
                CubeListBuilder.create().texOffs(0, 90).addBox(-4.0f, 0.0f, -3.0f, 6, 8, 6),
                PartPose.offset(-2.0f, 16.0f, 2.0f));
        root.addOrReplaceChild(
                "larm",
                CubeListBuilder.create().texOffs(0, 25).addBox(-1.0f, -1.0f, -2.0f, 4, 10, 4),
                PartPose.offsetAndRotation(6.0f, 4.0f, 3.0f, 0.0f, 0.0f, -0.4886922f));
        root.addOrReplaceChild(
                "rarm",
                CubeListBuilder.create().texOffs(18, 25).addBox(-3.0f, -1.0f, -2.0f, 4, 10, 4),
                PartPose.offsetAndRotation(-6.0f, 4.0f, 3.0f, 0.0f, 0.0f, 0.4886922f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(
            BandP entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {
        float newangle;
        float newangle2;
        float newangle3;
        if ((double) limbSwingAmount > 0.1) {
            newangle = Mth.cos(ageInTicks * 1.3f * this.wingspeed) * (float) Math.PI * 0.25f * limbSwingAmount;
            newangle2 = Mth.cos(ageInTicks * 2.6f * this.wingspeed) * (float) Math.PI * 0.025f * limbSwingAmount;
            newangle3 = newangle;
        } else {
            newangle = 0.0f;
            newangle2 = Mth.cos(ageInTicks * 0.6f * this.wingspeed) * (float) Math.PI * 0.005f;
            newangle3 = Mth.cos(ageInTicks * 0.3f * this.wingspeed) * (float) Math.PI * 0.02f;
        }
        this.lleg.xRot = newangle;
        this.rleg.xRot = -newangle;
        this.belly.xRot = 0.07f + newangle2;
        this.larm.xRot = -newangle3;
        this.rarm.xRot = newangle3;
        this.belly.yRot = (-newangle) / 2.0f;
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180.0f);
        this.head.xRot = headPitch * ((float) Math.PI / 180.0f);
    }

    @Override
    public void renderToBuffer(
            PoseStack poseStack,
            VertexConsumer buffer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha) {
        this.belly.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rleg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.larm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rarm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
