package com.astryxion.chaospersists.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelChainsaw {
    float toothpos = 0.0f;
    int toothdir = 0;
    float toothpos1 = 7.0f;
    int toothdir1 = 0;
    float toothpos2 = 14.0f;
    int toothdir2 = 0;
    float toothpos3 = 20.0f;
    int toothdir3 = 1;
    float toothpos4 = 13.0f;
    int toothdir4 = 1;
    float toothpos5 = 6.0f;
    int toothdir5 = 1;
    private final ModelPart engine;
    private final ModelPart handle1;
    private final ModelPart handle2;
    private final ModelPart handle3;
    private final ModelPart muffler;
    private final ModelPart blade1;
    private final ModelPart blade2;
    private final ModelPart tooth;

    public ModelChainsaw() {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot());
    }

    public ModelChainsaw(ModelPart root) {
        this.engine = root.getChild("engine");
        this.handle1 = root.getChild("handle1");
        this.handle2 = root.getChild("handle2");
        this.handle3 = root.getChild("handle3");
        this.muffler = root.getChild("muffler");
        this.blade1 = root.getChild("blade1");
        this.blade2 = root.getChild("blade2");
        this.tooth = root.getChild("tooth");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("engine", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-2.0f, -4.0f, -4.0f, 4, 7, 8), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("handle1", CubeListBuilder.create().texOffs(49, 0).mirror().addBox(0.0f, -3.0f, 3.0f, 1, 1, 5), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(50, 13).mirror().addBox(0.0f, 2.0f, 4.0f, 1, 1, 4), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(52, 7).mirror().addBox(0.0f, -2.0f, 7.0f, 1, 4, 1), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.0872665f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("muffler", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(-3.0f, 0.0f, 1.0f, 1, 3, 3), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("blade1", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(0.0f, -2.0f, -28.0f, 1, 4, 24), PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("blade2", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(0.0f, -2.5f, -2.5f, 1, 5, 5), PartPose.offsetAndRotation(0.0f, 0.0f, -28.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0f, -1.0f, -0.5f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, -2.0f, -5.0f, 0.0f, 0.0f, 0.0f));
        return meshdefinition;
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        this.renderTooth(poseStack, buffer, packedLight, packedOverlay);
        this.renderTooth1(poseStack, buffer, packedLight, packedOverlay);
        this.renderTooth2(poseStack, buffer, packedLight, packedOverlay);
        this.renderTooth3(poseStack, buffer, packedLight, packedOverlay);
        this.renderTooth4(poseStack, buffer, packedLight, packedOverlay);
        this.renderTooth5(poseStack, buffer, packedLight, packedOverlay);
        this.blade2.xRot = (float) ((double) this.blade2.xRot + 0.10471975511965977);
        if ((double) this.blade2.xRot > 6.283185307179586) {
            this.blade2.xRot = 0.0f;
        }
        this.engine.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
        this.handle1.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
        this.handle2.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
        this.handle3.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
        this.muffler.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
        this.blade1.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
        this.blade2.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
    }

    private void renderTooth(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        if (this.toothdir == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos;
            this.toothpos += 0.5f;
            if (this.toothpos > 21.0f) {
                this.toothpos = 21.0f;
                this.toothdir = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos;
            this.toothpos -= 0.5f;
            if (this.toothpos < 0.0f) {
                this.toothpos = 0.0f;
                this.toothdir = 0;
            }
        }
                this.tooth.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
    }

    private void renderTooth1(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        if (this.toothdir1 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos1;
            this.toothpos1 += 0.5f;
            if (this.toothpos1 > 21.0f) {
                this.toothpos1 = 21.0f;
                this.toothdir1 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos1;
            this.toothpos1 -= 0.5f;
            if (this.toothpos1 < 0.0f) {
                this.toothpos1 = 0.0f;
                this.toothdir1 = 0;
            }
        }
                this.tooth.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
    }

    private void renderTooth2(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        if (this.toothdir2 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos2;
            this.toothpos2 += 0.5f;
            if (this.toothpos2 > 21.0f) {
                this.toothpos2 = 21.0f;
                this.toothdir2 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos2;
            this.toothpos2 -= 0.5f;
            if (this.toothpos2 < 0.0f) {
                this.toothpos2 = 0.0f;
                this.toothdir2 = 0;
            }
        }
                this.tooth.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
    }

    private void renderTooth3(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        if (this.toothdir3 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos3;
            this.toothpos3 += 0.5f;
            if (this.toothpos3 > 21.0f) {
                this.toothpos3 = 21.0f;
                this.toothdir3 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos3;
            this.toothpos3 -= 0.5f;
            if (this.toothpos3 < 0.0f) {
                this.toothpos3 = 0.0f;
                this.toothdir3 = 0;
            }
        }
                this.tooth.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
    }

    private void renderTooth4(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        if (this.toothdir4 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos4;
            this.toothpos4 += 0.5f;
            if (this.toothpos4 > 21.0f) {
                this.toothpos4 = 21.0f;
                this.toothdir4 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos4;
            this.toothpos4 -= 0.5f;
            if (this.toothpos4 < 0.0f) {
                this.toothpos4 = 0.0f;
                this.toothdir4 = 0;
            }
        }
                this.tooth.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
    }

    private void renderTooth5(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        if (this.toothdir5 == 0) {
            this.tooth.y = -2.0f;
            this.tooth.z = -5.0f - this.toothpos5;
            this.toothpos5 += 0.5f;
            if (this.toothpos5 > 21.0f) {
                this.toothpos5 = 21.0f;
                this.toothdir5 = 1;
            }
        } else {
            this.tooth.y = 3.0f;
            this.tooth.z = -5.0f - this.toothpos5;
            this.toothpos5 -= 0.5f;
            if (this.toothpos5 < 0.0f) {
                this.toothpos5 = 0.0f;
                this.toothdir5 = 0;
            }
        }
        this.tooth.render(poseStack, buffer, packedLight, packedOverlay, net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 1.0f, 1.0f, 1.0f));
    }
}
