package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Basilisk;
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

public class ModelBasilisk extends EntityModel<Basilisk> {
    private final float wingspeed;
    private final ModelPart body3;
    private final ModelPart body2;
    private final ModelPart body1;
    private final ModelPart body4;
    private final ModelPart body5;
    private final ModelPart body6;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart neck2;
    private final ModelPart neck1;
    private final ModelPart head;
    private final ModelPart rog_1;
    private final ModelPart rog_2;
    private final ModelPart rog_3;
    private final ModelPart rog_4;
    private final ModelPart rog_5;
    private final ModelPart rog_6;
    private final ModelPart snout;
    private final ModelPart jaw;

    public ModelBasilisk(float f1) {
        this(LayerDefinition.create(createMesh(), 256, 64).bakeRoot(), f1);
    }

    public ModelBasilisk(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.body3 = root.getChild("body3");
        this.body2 = root.getChild("body2");
        this.body1 = root.getChild("body1");
        this.body4 = root.getChild("body4");
        this.body5 = root.getChild("body5");
        this.body6 = root.getChild("body6");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.neck2 = root.getChild("neck2");
        this.neck1 = root.getChild("neck1");
        this.head = root.getChild("head");
        this.rog_1 = root.getChild("rog_1");
        this.rog_2 = root.getChild("rog_2");
        this.rog_3 = root.getChild("rog_3");
        this.rog_4 = root.getChild("rog_4");
        this.rog_5 = root.getChild("rog_5");
        this.rog_6 = root.getChild("rog_6");
        this.snout = root.getChild("snout");
        this.jaw = root.getChild("jaw");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16), PartPose.offset(-8.0f, 8.0f, 0.0f));
        partdefinition.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16), PartPose.offsetAndRotation(-8.0f, 4.0f, -10.0f, -0.2974289f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16), PartPose.offsetAndRotation(-8.0f, 2.0f, -25.0f, -0.1487144f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16), PartPose.offsetAndRotation(-8.0f, 8.0f, 13.0f, 0.1487144f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16), PartPose.offset(-8.0f, 5.8f, 28.8f));
        partdefinition.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(148, 4).addBox(0.0f, 0.0f, 0.0f, 15, 15, 17), PartPose.offsetAndRotation(-7.5f, 6.166667f, 44.0f, -0.1115358f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(140, 36).addBox(0.0f, 0.0f, 0.0f, 13, 13, 15), PartPose.offsetAndRotation(-6.5f, 9.0f, 58.0f, 0.1115358f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(64, 41).addBox(0.0f, 0.0f, 0.0f, 10, 10, 13), PartPose.offsetAndRotation(-5.0f, 10.0f, 70.0f, 0.4089647f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(64, 20).addBox(0.0f, 0.0f, 0.0f, 8, 8, 13), PartPose.offsetAndRotation(-4.0f, 6.0f, 82.0f, 0.2230717f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(64, 1).addBox(0.0f, 0.0f, 0.0f, 6, 6, 13), PartPose.offsetAndRotation(-3.0f, 4.0f, 95.0f, -0.0743572f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16), PartPose.offsetAndRotation(-8.0f, -4.9f, -26.0f, -0.8464847f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(0, 32).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16), PartPose.offsetAndRotation(-8.0f, -15.0f, -29.0f, -1.181092f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 16, 18, 10), PartPose.offsetAndRotation(-8.0f, -21.0f, -30.0f, -1.404164f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rog_1", CubeListBuilder.create().texOffs(110, 45).addBox(0.0f, 0.0f, 0.0f, 3, 3, 5), PartPose.offsetAndRotation(3.0f, -21.0f, -32.0f, 0.6320364f, 0.2230717f, 0.0f));
        partdefinition.addOrReplaceChild("rog_2", CubeListBuilder.create().texOffs(110, 45).addBox(0.0f, 0.0f, 0.0f, 3, 3, 5), PartPose.offsetAndRotation(-6.0f, -21.0f, -32.8f, 0.6320364f, -0.2230705f, 0.0f));
        partdefinition.addOrReplaceChild("rog_3", CubeListBuilder.create().texOffs(52, 0).addBox(0.0f, 0.0f, 0.0f, 2, 2, 4), PartPose.offsetAndRotation(0.4666667f, -21.0f, -31.0f, 0.6320364f, 0.2230717f, 0.0f));
        partdefinition.addOrReplaceChild("rog_4", CubeListBuilder.create().texOffs(52, 0).addBox(0.0f, 0.0f, 0.0f, 2, 2, 4), PartPose.offsetAndRotation(-2.466667f, -21.0f, -31.46667f, 0.6320364f, -0.2230705f, 0.0f));
        partdefinition.addOrReplaceChild("rog_5", CubeListBuilder.create().texOffs(52, 0).addBox(0.0f, 0.0f, 0.0f, 2, 2, 4), PartPose.offsetAndRotation(-8.0f, -17.0f, -32.0f, 0.6320364f, -0.6692139f, 0.0f));
        partdefinition.addOrReplaceChild("rog_6", CubeListBuilder.create().texOffs(52, 0).addBox(0.0f, 0.0f, 0.0f, 2, 2, 4), PartPose.offsetAndRotation(6.4f, -17.0f, -32.0f, 0.6320364f, 0.6692116f, 0.0f));
        partdefinition.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(102, 1).addBox(0.0f, 0.0f, 0.0f, 14, 16, 9), PartPose.offsetAndRotation(-7.0f, -17.0f, -43.0f, -1.404164f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(106, 26).addBox(0.0f, 0.0f, 0.0f, 14, 16, 3), PartPose.offsetAndRotation(-7.0f, -11.0f, -39.0f, -0.8836633f, 0.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Basilisk entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float newangle = 0.0f;
                newangle = (double)f1 > 0.1 ? Mth.cos((float)(f2 * 1.3f * this.wingspeed)) * (float)Math.PI * 0.1f * f1 : 0.0f;
                float pi4 = 0.7853975f;
                this.body1.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed)) * (float)Math.PI * 0.1f * f1;
                this.body2.z = this.body1.z + (float)Math.cos(this.body1.yRot) * 12.0f;
                this.body2.x = this.body1.x + (float)Math.sin(this.body1.yRot) * 12.0f;
                this.body2.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - pi4)) * (float)Math.PI * 0.1f * f1;
                this.body3.z = this.body2.z + (float)Math.cos(this.body2.yRot) * 11.0f;
                this.body3.x = this.body2.x + (float)Math.sin(this.body2.yRot) * 11.0f;
                this.body3.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 2.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.body4.z = this.body3.z + (float)Math.cos(this.body3.yRot) * 12.0f;
                this.body4.x = this.body3.x + (float)Math.sin(this.body3.yRot) * 12.0f;
                this.body4.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 3.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.body5.z = this.body4.z + (float)Math.cos(this.body4.yRot) * 12.0f;
                this.body5.x = this.body4.x + (float)Math.sin(this.body4.yRot) * 12.0f;
                this.body5.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 4.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.body6.z = this.body5.z + (float)Math.cos(this.body5.yRot) * 12.0f;
                this.body6.x = this.body5.x + 0.5f + (float)Math.sin(this.body5.yRot) * 12.0f;
                this.body6.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 5.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.tail1.z = this.body6.z + (float)Math.cos(this.body6.yRot) * 12.0f;
                this.tail1.x = this.body6.x + 1.0f + (float)Math.sin(this.body6.yRot) * 12.0f;
                this.tail1.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 6.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 10.0f;
                this.tail2.x = this.tail1.x + 1.5f + (float)Math.sin(this.tail1.yRot) * 10.0f;
                this.tail2.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 7.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 10.0f;
                this.tail3.x = this.tail2.x + 1.0f + (float)Math.sin(this.tail2.yRot) * 10.0f;
                this.tail3.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 8.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 10.0f;
                this.tail4.x = this.tail3.x + 1.0f + (float)Math.sin(this.tail3.yRot) * 10.0f;
                this.tail4.yRot = Mth.cos((float)(f2 * 1.3f * this.wingspeed - 9.0f * pi4)) * (float)Math.PI * 0.1f * f1;
                this.jaw.xRot = entity.getAttacking() != 0 ? -1.0f + Mth.cos((float)(f2 * 0.45f)) * (float)Math.PI * 0.18f : -1.1f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rog_6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.snout.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
