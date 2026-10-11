package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.CliffRacer;
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

public class ModelCliffRacer extends EntityModel<CliffRacer> {
    private final float wingspeed;
    private final ModelPart Body;
    private final ModelPart Fins;
    private final ModelPart LWing;
    private final ModelPart RWing;
    private final ModelPart Tail;
    private final ModelPart TailEnd;
    private final ModelPart Head;
    private final ModelPart Beak;

    public ModelCliffRacer(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot(), f1);
    }

    public ModelCliffRacer(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.Body = root.getChild("Body");
        this.Fins = root.getChild("Fins");
        this.LWing = root.getChild("LWing");
        this.RWing = root.getChild("RWing");
        this.Tail = root.getChild("Tail");
        this.TailEnd = root.getChild("TailEnd");
        this.Head = root.getChild("Head");
        this.Beak = root.getChild("Beak");
    }

    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 52).addBox(0.0f, 0.0f, 0.0f, 3, 1, 10), PartPose.offset(-1.0f, 15.0f, -4.0f));
        partdefinition.addOrReplaceChild("Fins", CubeListBuilder.create().texOffs(0, 40).addBox(0.0f, -4.0f, 0.0f, 1, 6, 3), PartPose.offset(0.0f, 15.0f, -1.0f));
        partdefinition.addOrReplaceChild("LWing", CubeListBuilder.create().texOffs(0, 31).addBox(0.0f, 0.0f, 0.0f, 7, 1, 6), PartPose.offset(2.0f, 15.0f, -2.0f));
        partdefinition.addOrReplaceChild("RWing", CubeListBuilder.create().texOffs(39, 0).addBox(-7.0f, 0.0f, 0.0f, 7, 1, 6), PartPose.offset(-1.0f, 15.0f, -2.0f));
        partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 16).addBox(0.0f, 0.0f, 0.0f, 1, 1, 9), PartPose.offset(0.0f, 15.0f, 6.0f));
        partdefinition.addOrReplaceChild("TailEnd", CubeListBuilder.create().texOffs(0, 10).addBox(0.0f, -1.0f, 9.0f, 2, 2, 2), PartPose.offset(-0.5f, 15.0f, 6.0f));
        partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(28, 21).addBox(0.0f, 0.0f, 0.0f, 2, 2, 2), PartPose.offset(-0.5f, 14.0f, -6.0f));
        partdefinition.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, 0.0f, 0.0f, 1, 1, 2), PartPose.offset(0.0f, 14.5f, -8.0f));
        return meshdefinition;
    }

    @Override
    public void setupAnim(CliffRacer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float newangle = 0.0f;
                this.LWing.zRot = newangle = Mth.cos((float)(f2 * 1.3f * this.wingspeed)) * (float)Math.PI * 0.25f;
                this.RWing.zRot = - newangle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Fins.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LWing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.RWing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Tail.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.TailEnd.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Beak.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
