package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.entity.RenderInfo;
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

public class ModelGhostSkelly extends EntityModel<GhostSkelly> {
    private final ModelPart body;
    private final ModelPart shirt;
    private final ModelPart head;
    private final ModelPart stem;
    private final ModelPart rarm;
    private final ModelPart larm;
    private final ModelPart rsleeve;
    private final ModelPart lsleeve;
    private final ModelPart lchains;
    private final ModelPart rchains;

    public ModelGhostSkelly() {
        this(LayerDefinition.create(createMesh(), 128, 64).bakeRoot());
    }

    public ModelGhostSkelly(ModelPart root) {
        this.body = root.getChild("body");
        this.shirt = root.getChild("shirt");
        this.head = root.getChild("head");
        this.stem = root.getChild("stem");
        this.rarm = root.getChild("rarm");
        this.larm = root.getChild("larm");
        this.rsleeve = root.getChild("rsleeve");
        this.lsleeve = root.getChild("lsleeve");
        this.lchains = root.getChild("lchains");
        this.rchains = root.getChild("rchains");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1.0f, 21.0f, 1.0f),
                PartPose.offset(0.0f, -1.0f, 0.0f));
        root.addOrReplaceChild(
                "shirt",
                CubeListBuilder.create().texOffs(42, 43).addBox(-2.0f, 0.0f, -2.0f, 5.0f, 12.0f, 5.0f),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(40, 29).addBox(-3.0f, 0.0f, -3.0f, 7.0f, 5.0f, 7.0f),
                PartPose.offset(0.0f, -6.0f, 0.0f));
        root.addOrReplaceChild(
                "stem",
                CubeListBuilder.create().texOffs(49, 23).addBox(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f),
                PartPose.offsetAndRotation(0.0f, -8.0f, 0.0f, 0.1745329f, 0.0f, 0.1745329f));
        root.addOrReplaceChild(
                "rarm",
                CubeListBuilder.create().texOffs(26, 0).addBox(-14.0f, 0.0f, 0.0f, 15.0f, 1.0f, 1.0f),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "larm",
                CubeListBuilder.create().texOffs(63, 0).addBox(0.0f, 0.0f, 0.0f, 15.0f, 1.0f, 1.0f),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "rsleeve",
                CubeListBuilder.create().texOffs(31, 7).addBox(-11.0f, 0.0f, -1.0f, 9.0f, 8.0f, 3.0f),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "lsleeve",
                CubeListBuilder.create().texOffs(71, 7).addBox(3.0f, 0.0f, -1.0f, 9.0f, 8.0f, 3.0f),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "lchains",
                CubeListBuilder.create().texOffs(98, 0).addBox(11.0f, -1.0f, 0.0f, 3.0f, 16.0f, 1.0f),
                PartPose.ZERO);
        root.addOrReplaceChild(
                "rchains",
                CubeListBuilder.create().texOffs(12, 0).addBox(-13.0f, -1.0f, 0.0f, 3.0f, 10.0f, 1.0f),
                PartPose.ZERO);
        return mesh;
    }

    @Override
    public void setupAnim(GhostSkelly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderInfo r = entity.getRenderInfo();
        float chainZ = Mth.cos(ageInTicks * 0.2f) * (float) Math.PI * 0.05f;
        this.lsleeve.zRot = chainZ;
        this.lchains.zRot = chainZ;
        this.larm.zRot = chainZ;
        float chainZ2 = Mth.cos(ageInTicks * 0.22f) * (float) Math.PI * 0.05f;
        this.rsleeve.zRot = chainZ2;
        this.rchains.zRot = chainZ2;
        this.rarm.zRot = chainZ2;
        float chainY = Mth.cos(ageInTicks * 0.24f) * (float) Math.PI * 0.05f;
        this.lsleeve.yRot = chainY;
        this.lchains.yRot = chainY;
        this.larm.yRot = chainY;
        float chainY2 = Mth.cos(ageInTicks * 0.26f) * (float) Math.PI * 0.05f;
        this.rsleeve.yRot = chainY2;
        this.rchains.yRot = chainY2;
        this.rarm.yRot = chainY2;
        float newangle = Mth.cos(ageInTicks * 0.05f) * (float) Math.PI * 2.0f;
        float newrf1 = ageInTicks * 0.05f % 6.2831855f;
        newrf1 = Math.abs(newrf1);
        if (newrf1 < r.rf2) {
            r.ri2 = 0;
            if (entity.getRandom().nextInt(3) == 1) {
                r.ri2 |= 1;
            }
        }
        r.rf2 = newrf1;
        if ((r.ri2 & 1) == 0) {
            newangle = 0.0f;
        }
        this.head.yRot = newangle;
        entity.setRenderInfo(r);
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
        float ghostAlpha = 0.25f;
        this.body.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.shirt.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.stem.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.rarm.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.larm.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.rsleeve.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.lsleeve.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.lchains.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
        this.rchains.render(poseStack, buffer, packedLight, packedOverlay, 0.75f, 0.75f, 0.75f, ghostAlpha);
    }
}
