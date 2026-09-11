package com.astryxion.chaospersists.model;

import com.astryxion.chaospersists.entity.TerribleTerror;
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

public class ModelTerribleTerror extends EntityModel<TerribleTerror> {
    private final float wingspeed;
    private final ModelPart Horn1;
    private final ModelPart Horn2;
    private final ModelPart Snout;
    private final ModelPart Head;
    private final ModelPart Jaw;
    private final ModelPart Neck;
    private final ModelPart Body;
    private final ModelPart Wing1;
    private final ModelPart Wing2;
    private final ModelPart Tail1;
    private final ModelPart Tail2;
    private final ModelPart Tail3;
    private final ModelPart Tail4;
    private final ModelPart FL11;
    private final ModelPart FL12;
    private final ModelPart FL21;
    private final ModelPart FL22;
    private final ModelPart BL21;
    private final ModelPart BL22;
    private final ModelPart BL11;
    private final ModelPart BL12;

    public ModelTerribleTerror(float f1) {
        this(LayerDefinition.create(createMesh(), 119, 72).bakeRoot(), f1);
    }

    public ModelTerribleTerror(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Horn1 = root.getChild("Horn1");
        this.Horn2 = root.getChild("Horn2");
        this.Snout = root.getChild("Snout");
        this.Head = root.getChild("Head");
        this.Jaw = root.getChild("Jaw");
        this.Neck = root.getChild("Neck");
        this.Body = root.getChild("Body");
        this.Wing1 = root.getChild("Wing1");
        this.Wing2 = root.getChild("Wing2");
        this.Tail1 = root.getChild("Tail1");
        this.Tail2 = root.getChild("Tail2");
        this.Tail3 = root.getChild("Tail3");
        this.Tail4 = root.getChild("Tail4");
        this.FL11 = root.getChild("FL11");
        this.FL12 = root.getChild("FL12");
        this.FL21 = root.getChild("FL21");
        this.FL22 = root.getChild("FL22");
        this.BL21 = root.getChild("BL21");
        this.BL22 = root.getChild("BL22");
        this.BL11 = root.getChild("BL11");
        this.BL12 = root.getChild("BL12");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Horn1", CubeListBuilder.create().texOffs(90, 0).addBox(1.0f, -4.0f, 0.0f, 0, 2, 2), PartPose.offset(0.0f, 17.0f, -6.0f));
        partdefinition.addOrReplaceChild("Horn2", CubeListBuilder.create().texOffs(102, 0).addBox(-1.0f, -4.0f, 0.0f, 0, 2, 2), PartPose.offset(0.0f, 17.0f, -6.0f));
        partdefinition.addOrReplaceChild("Snout", CubeListBuilder.create().texOffs(64, 0).addBox(-2.0f, -1.0f, -4.0f, 4, 1, 4), PartPose.offset(0.0f, 17.0f, -6.0f));
        partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(41, 0).addBox(-2.0f, -2.0f, -2.0f, 4, 1, 2), PartPose.offset(0.0f, 17.0f, -6.0f));
        partdefinition.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(42, 5).addBox(-2.0f, 0.0f, -4.0f, 4, 1, 4), PartPose.offset(0.0f, 17.0f, -6.0f));
        partdefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(30, 0).addBox(0.0f, 0.0f, 0.0f, 2, 5, 2), PartPose.offsetAndRotation(-1.0f, 18.0f, -2.0f, -2.082002f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(38, 16).addBox(0.0f, 0.0f, 0.0f, 2, 3, 10), PartPose.offset(-1.0f, 17.0f, -4.0f));
        partdefinition.addOrReplaceChild("Wing1", CubeListBuilder.create().texOffs(36, 37).addBox(0.0f, 0.0f, 0.0f, 0, 11, 15), PartPose.offsetAndRotation(0.0f, 18.0f, -1.0f, -0.3490659f, 0.0f, -2.356194f));
        partdefinition.addOrReplaceChild("Wing2", CubeListBuilder.create().texOffs(0, 37).addBox(0.0f, 0.0f, 0.0f, 0, 11, 15), PartPose.offsetAndRotation(0.0f, 18.0f, -1.0f, -0.3490659f, 0.0f, 2.356194f));
        partdefinition.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(14, 0).addBox(0.0f, 0.0f, 0.0f, 1, 1, 6), PartPose.offsetAndRotation(-0.5f, 17.0f, 6.0f, -0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(14, 8).addBox(0.0f, 0.0f, 0.0f, 1, 1, 6), PartPose.offset(-0.5f, 20.0f, 11.0f));
        partdefinition.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(17, 16).addBox(0.0f, 0.0f, 0.0f, 1, 1, 4), PartPose.offsetAndRotation(-0.5f, 20.0f, 17.0f, 0.0f, -0.6320364f, 0.0f));
        partdefinition.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(16, 23).addBox(-1.0f, 0.5f, 4.0f, 3, 0, 2), PartPose.offsetAndRotation(-0.5f, 20.0f, 17.0f, 0.0f, -0.6320364f, 0.0f));
        partdefinition.addOrReplaceChild("FL11", CubeListBuilder.create().texOffs(0, 9).addBox(0.0f, 0.0f, 0.0f, 1, 2, 1), PartPose.offsetAndRotation(-2.0f, 19.0f, -4.0f, 0.3490659f, 0.0f, 0.1745329f));
        partdefinition.addOrReplaceChild("FL12", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5f, 1.0f, 1.0f, 1, 2, 1), PartPose.offsetAndRotation(-2.0f, 19.0f, -4.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("FL21", CubeListBuilder.create().texOffs(5, 9).addBox(0.0f, 0.0f, 0.0f, 1, 2, 1), PartPose.offsetAndRotation(1.0f, 19.0f, -4.0f, 0.3490659f, 0.0f, -0.1745329f));
        partdefinition.addOrReplaceChild("FL22", CubeListBuilder.create().texOffs(5, 13).addBox(0.5f, 1.0f, 1.0f, 1, 2, 1), PartPose.offsetAndRotation(1.0f, 19.0f, -4.0f, -0.2617994f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BL21", CubeListBuilder.create().texOffs(0, 18).addBox(0.0f, 0.0f, 0.0f, 1, 2, 1), PartPose.offsetAndRotation(1.0f, 18.0f, 4.0f, -0.3490659f, 0.0f, -0.1745329f));
        partdefinition.addOrReplaceChild("BL22", CubeListBuilder.create().texOffs(0, 22).addBox(0.5f, 2.0f, -1.0f, 1, 2, 1), PartPose.offsetAndRotation(1.0f, 18.0f, 4.0f, 0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("BL11", CubeListBuilder.create().texOffs(5, 18).addBox(0.0f, 0.0f, 0.0f, 1, 2, 1), PartPose.offsetAndRotation(-2.0f, 18.0f, 4.0f, -0.3490659f, 0.0f, 0.1745329f));
        partdefinition.addOrReplaceChild("BL12", CubeListBuilder.create().texOffs(5, 22).addBox(-0.5f, 2.0f, -1.0f, 1, 2, 1), PartPose.offsetAndRotation(-2.0f, 18.0f, 4.0f, 0.1745329f, 0.0f, 0.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(TerribleTerror entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f2 = ageInTicks;
        boolean sitting = entity.isOrderedToSit() || entity.isInSittingPose();
        float newangle = sitting ? 0.0f : Mth.cos(f2 * 1.3f * this.wingspeed) * (float) Math.PI * 0.25f;
        this.Wing1.zRot = sitting ? -2.356194f : -2.0f + newangle;
        this.Wing2.zRot = sitting ? 2.356194f : 2.0f - newangle;
        newangle = sitting ? 0.0f : Mth.cos(f2 * 0.3f * this.wingspeed) * (float) Math.PI * 0.1f;
        this.Jaw.xRot = Mth.abs(newangle);
        newangle = sitting ? 0.0f : Mth.cos(f2 * 1.25f) * (float) Math.PI * 0.35f;
        this.FL21.xRot = 0.349f + newangle;
        this.FL22.xRot = -0.296f + newangle;
        this.BL21.xRot = -0.349f - newangle;
        this.BL22.xRot = 0.174f - newangle;
        this.FL11.xRot = 0.349f - newangle;
        this.FL12.xRot = -0.296f - newangle;
        this.BL11.xRot = -0.349f + newangle;
        this.BL12.xRot = 0.174f + newangle;
        this.Tail1.xRot = newangle = sitting ? 0.0f : Mth.cos(f2 * 0.71f * this.wingspeed) * (float) Math.PI * 0.1f;
        this.Tail1.yRot = newangle = sitting ? 0.0f : Mth.cos(f2 * 0.77f * this.wingspeed) * (float) Math.PI * 0.1f;
        float dist = 6.0f;
        dist = dist * Mth.cos(this.Tail1.xRot);
        this.Tail2.y = this.Tail1.y - Mth.sin(this.Tail1.xRot) * dist;
        this.Tail2.x = this.Tail1.x + Mth.sin(this.Tail1.yRot) * dist;
        this.Tail2.xRot = newangle = sitting ? 0.0f : Mth.cos(f2 * 0.81f * this.wingspeed) * (float) Math.PI * 0.15f;
        this.Tail2.yRot = newangle = sitting ? 0.0f : Mth.cos(f2 * 0.87f * this.wingspeed) * (float) Math.PI * 0.15f;
        dist = 6.0f;
        dist = dist * Mth.cos(this.Tail2.xRot);
        this.Tail3.y = this.Tail4.y = this.Tail2.y - Mth.sin(this.Tail2.xRot) * dist;
        this.Tail3.x = this.Tail4.x = this.Tail2.x + Mth.sin(this.Tail2.yRot) * dist;
        this.Tail3.xRot = this.Tail4.xRot = (newangle = sitting ? 0.0f : Mth.cos(f2 * 0.91f * this.wingspeed) * (float) Math.PI * 0.2f);
        this.Tail3.yRot = this.Tail4.yRot = (newangle = sitting ? 0.0f : Mth.cos(f2 * 0.97f * this.wingspeed) * (float) Math.PI * 0.2f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Horn1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Horn2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Snout.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Jaw.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Wing1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Wing2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.FL22.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL22.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.BL12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
