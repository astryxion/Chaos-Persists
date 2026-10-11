package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Gazelle;
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

public class ModelGazelle extends EntityModel<Gazelle> {
    private final float wingspeed;
    private final ModelPart Chest;
    private final ModelPart lfleg1;
    private final ModelPart lrleg2;
    private final ModelPart lrleg1;
    private final ModelPart rfleg3;
    private final ModelPart rrleg2;
    private final ModelPart rrleg3;
    private final ModelPart rfleg2;
    private final ModelPart lrleg4;
    private final ModelPart tail;
    private final ModelPart lear;
    private final ModelPart rrleg1;
    private final ModelPart rfleg1;
    private final ModelPart lrleg3;
    private final ModelPart lfleg2;
    private final ModelPart rrleg5;
    private final ModelPart rrleg4;
    private final ModelPart lfleg3;
    private final ModelPart rfleg4;
    private final ModelPart lfleg4;
    private final ModelPart lrleg5;
    private final ModelPart Body;
    private final ModelPart neck;
    private final ModelPart la3;
    private final ModelPart throatfluff;
    private final ModelPart rear;
    private final ModelPart head;
    private final ModelPart ra1;
    private final ModelPart la1;
    private final ModelPart la2;
    private final ModelPart ra2;
    private final ModelPart ra3;
    private final ModelPart nose;
    private final ModelPart mouth;

    public ModelGazelle(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot(), f1);
    }

    public ModelGazelle(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Chest = root.getChild("Chest");
        this.lfleg1 = root.getChild("lfleg1");
        this.lrleg2 = root.getChild("lrleg2");
        this.lrleg1 = root.getChild("lrleg1");
        this.rfleg3 = root.getChild("rfleg3");
        this.rrleg2 = root.getChild("rrleg2");
        this.rrleg3 = root.getChild("rrleg3");
        this.rfleg2 = root.getChild("rfleg2");
        this.lrleg4 = root.getChild("lrleg4");
        this.tail = root.getChild("tail");
        this.lear = root.getChild("lear");
        this.rrleg1 = root.getChild("rrleg1");
        this.rfleg1 = root.getChild("rfleg1");
        this.lrleg3 = root.getChild("lrleg3");
        this.lfleg2 = root.getChild("lfleg2");
        this.rrleg5 = root.getChild("rrleg5");
        this.rrleg4 = root.getChild("rrleg4");
        this.lfleg3 = root.getChild("lfleg3");
        this.rfleg4 = root.getChild("rfleg4");
        this.lfleg4 = root.getChild("lfleg4");
        this.lrleg5 = root.getChild("lrleg5");
        this.Body = root.getChild("Body");
        this.neck = root.getChild("neck");
        this.la3 = root.getChild("la3");
        this.throatfluff = root.getChild("throatfluff");
        this.rear = root.getChild("rear");
        this.head = root.getChild("head");
        this.ra1 = root.getChild("ra1");
        this.la1 = root.getChild("la1");
        this.la2 = root.getChild("la2");
        this.ra2 = root.getChild("ra2");
        this.ra3 = root.getChild("ra3");
        this.nose = root.getChild("nose");
        this.mouth = root.getChild("mouth");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(12, 57).addBox(0.0f, 0.0f, 0.0f, 5, 2, 3), PartPose.offsetAndRotation(-2.5f, 8.0f, -6.0f, 2.342252f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lfleg1", CubeListBuilder.create().texOffs(0, 31).addBox(0.0f, 0.0f, 0.0f, 2, 6, 3), PartPose.offsetAndRotation(2.0f, 6.0f, -6.0f, 0.2974289f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lrleg2", CubeListBuilder.create().texOffs(16, 49).addBox(0.0f, 5.0f, -1.0f, 2, 2, 6), PartPose.offsetAndRotation(2.0f, 4.0f, 3.0f, 0.1858931f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lrleg1", CubeListBuilder.create().texOffs(23, 31).addBox(0.0f, 0.0f, 0.0f, 2, 6, 3), PartPose.offset(2.0f, 4.0f, 3.0f));
        partdefinition.addOrReplaceChild("rfleg3", CubeListBuilder.create().texOffs(40, 49).addBox(0.0f, 10.0f, 6.0f, 2, 6, 2), PartPose.offsetAndRotation(-4.0f, 5.966667f, -6.0f, -0.4089647f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rrleg2", CubeListBuilder.create().texOffs(16, 49).addBox(0.0f, 5.0f, -1.0f, 2, 2, 6), PartPose.offsetAndRotation(-4.0f, 4.0f, 3.0f, 0.1858931f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rrleg3", CubeListBuilder.create().texOffs(32, 11).addBox(0.0f, 4.0f, 5.0f, 2, 12, 2), PartPose.offsetAndRotation(-4.0f, 3.966667f, 3.0f, -0.0743572f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rfleg2", CubeListBuilder.create().texOffs(24, 11).addBox(0.0f, 2.0f, 2.0f, 2, 12, 2), PartPose.offsetAndRotation(-4.0f, 5.966667f, -6.0f, -0.0743572f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lrleg4", CubeListBuilder.create().texOffs(32, 49).addBox(0.0f, 11.0f, 9.5f, 2, 6, 2), PartPose.offsetAndRotation(2.0f, 4.0f, 3.0f, -0.4089647f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 49).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4), PartPose.offsetAndRotation(-2.0f, 0.0f, 4.0f, 0.9666439f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lear", CubeListBuilder.create().texOffs(18, 0).addBox(-5.0f, -3.0f, 2.0f, 3, 2, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, -0.1047198f, 1.570796f, 0.0f));
        partdefinition.addOrReplaceChild("rrleg1", CubeListBuilder.create().texOffs(23, 31).addBox(0.0f, 0.0f, 0.0f, 2, 6, 3), PartPose.offset(-4.0f, 4.0f, 3.0f));
        partdefinition.addOrReplaceChild("rfleg1", CubeListBuilder.create().texOffs(0, 31).addBox(0.0f, 0.0f, 0.0f, 2, 6, 3), PartPose.offsetAndRotation(-4.0f, 6.0f, -6.0f, 0.2974289f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lrleg3", CubeListBuilder.create().texOffs(32, 11).addBox(0.0f, 4.0f, 5.0f, 2, 12, 2), PartPose.offsetAndRotation(2.0f, 3.966667f, 3.0f, -0.0743572f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lfleg2", CubeListBuilder.create().texOffs(24, 11).addBox(0.0f, 2.0f, 2.0f, 2, 12, 2), PartPose.offsetAndRotation(2.0f, 5.966667f, -6.0f, -0.0743572f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rrleg5", CubeListBuilder.create().texOffs(0, 58).addBox(-0.5f, 17.0f, 2.0f, 3, 3, 3), PartPose.offset(-4.0f, 4.0f, 3.0f));
        partdefinition.addOrReplaceChild("rrleg4", CubeListBuilder.create().texOffs(32, 49).addBox(0.0f, 11.0f, 9.5f, 2, 6, 2), PartPose.offsetAndRotation(-4.0f, 3.966667f, 3.0f, -0.4089647f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lfleg3", CubeListBuilder.create().texOffs(40, 49).addBox(0.0f, 10.0f, 6.0f, 2, 6, 2), PartPose.offsetAndRotation(2.0f, 5.966667f, -6.0f, -0.4089647f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rfleg4", CubeListBuilder.create().texOffs(0, 58).addBox(-0.5f, 15.0f, -1.0f, 3, 3, 3), PartPose.offset(-4.0f, 6.0f, -6.0f));
        partdefinition.addOrReplaceChild("lfleg4", CubeListBuilder.create().texOffs(0, 58).addBox(-0.5f, 15.0f, -1.0f, 3, 3, 3), PartPose.offset(2.0f, 6.0f, -6.0f));
        partdefinition.addOrReplaceChild("lrleg5", CubeListBuilder.create().texOffs(0, 58).addBox(-0.5f, 17.0f, 2.0f, 3, 3, 3), PartPose.offset(2.0f, 4.0f, 3.0f));
        partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 12).addBox(0.0f, 0.0f, 0.0f, 6, 6, 13), PartPose.offsetAndRotation(-3.0f, 2.0f, -7.0f, 0.2230717f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 31).addBox(0.0f, 0.0f, 0.0f, 5, 5, 13), PartPose.offsetAndRotation(-2.5f, 6.0f, -8.0f, 1.524323f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("la3", CubeListBuilder.create().texOffs(4, 12).addBox(0.5f, -12.5f, 3.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, -0.3346075f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("throatfluff", CubeListBuilder.create().texOffs(36, 41).addBox(0.0f, -2.0f, 0.0f, 4, 3, 5), PartPose.offsetAndRotation(-2.0f, 0.0f, -8.0f, 1.07818f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rear", CubeListBuilder.create().texOffs(18, 0).addBox(-5.0f, -3.0f, -3.0f, 3, 2, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, 0.1047198f, 1.570796f, 0.0f));
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6), PartPose.offset(0.0f, -9.0f, -6.0f));
        partdefinition.addOrReplaceChild("ra1", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5f, -5.0f, 0.0f, 1, 4, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, -0.3717861f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("la1", CubeListBuilder.create().texOffs(0, 12).addBox(0.5f, -5.0f, 0.0f, 1, 4, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, -0.3717861f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("la2", CubeListBuilder.create().texOffs(0, 17).addBox(0.5f, -8.5f, -3.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, -1.041001f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("ra2", CubeListBuilder.create().texOffs(0, 17).addBox(-1.5f, -8.5f, -3.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, -1.041001f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("ra3", CubeListBuilder.create().texOffs(4, 12).addBox(-1.5f, -12.5f, 3.0f, 1, 5, 1), PartPose.offsetAndRotation(0.0f, -9.0f, -6.0f, -0.3346075f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-2.5f, 0.0f, -7.0f, 5, 3, 5), PartPose.offset(0.0f, -9.0f, -6.0f));
        partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(28, 57).addBox(-2.0f, 2.0f, -6.0f, 4, 2, 5), PartPose.offset(0.0f, -9.0f, -6.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(Gazelle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float newangle = limbSwingAmount > 0.1f ? Mth.cos(ageInTicks * 1.1f * this.wingspeed) * ((float) Math.PI * 0.12f) * limbSwingAmount : 0.0f;
        this.lfleg1.xRot = 0.297f + newangle;
        this.lfleg2.xRot = -0.074f + newangle;
        this.lfleg3.xRot = -0.409f + newangle;
        this.lfleg4.xRot = newangle;
        this.rfleg1.xRot = 0.297f - newangle;
        this.rfleg2.xRot = -0.074f - newangle;
        this.rfleg3.xRot = -0.409f - newangle;
        this.rfleg4.xRot = -newangle;
        this.lrleg1.xRot = -newangle;
        this.lrleg2.xRot = 0.185f - newangle;
        this.lrleg3.xRot = -0.074f - newangle;
        this.lrleg4.xRot = -0.409f - newangle;
        this.lrleg5.xRot = -newangle;
        this.rrleg1.xRot = newangle;
        this.rrleg2.xRot = 0.185f + newangle;
        this.rrleg3.xRot = -0.074f + newangle;
        this.rrleg4.xRot = -0.409f + newangle;
        this.rrleg5.xRot = newangle;
        newangle = Mth.cos(ageInTicks * 0.5f) * ((float) Math.PI * 0.02f);
        this.nose.yRot = this.head.yRot = (float) Math.toRadians(netHeadYaw) * 0.45f;
        this.mouth.yRot = this.head.yRot;
        this.lear.yRot = 1.57f + this.head.yRot + newangle;
        this.rear.yRot = 1.57f + this.head.yRot + newangle;
        this.la1.yRot = this.head.yRot;
        this.la2.yRot = this.head.yRot;
        this.la3.yRot = this.head.yRot;
        this.ra1.yRot = this.head.yRot;
        this.ra2.yRot = this.head.yRot;
        this.ra3.yRot = this.head.yRot;
        if (!entity.isInSittingPose()) {
            this.tail.xRot = 1.0f + Mth.cos(ageInTicks * 0.1f) * ((float) Math.PI * 0.06f);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Chest.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rrleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rfleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lfleg4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lrleg5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.la3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.throatfluff.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rear.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ra1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.la1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.la2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ra2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.ra3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.nose.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
