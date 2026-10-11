package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.RubberDucky;
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

public class ModelRubberDucky extends EntityModel<RubberDucky> {
    private final float wingspeed;
    private final ModelPart bottom;
    private final ModelPart body;
    private final ModelPart back;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart beak;
    private final ModelPart Lwing;
    private final ModelPart Rwing;

    public ModelRubberDucky(float f1) {
        this(LayerDefinition.create(createMesh(), 64, 64).bakeRoot(), f1);
    }

    public ModelRubberDucky(ModelPart root, float wingspeed) {
        this.wingspeed = wingspeed;
        this.bottom = root.getChild("bottom");
        this.body = root.getChild("body");
        this.back = root.getChild("back");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.beak = root.getChild("beak");
        this.Lwing = root.getChild("Lwing");
        this.Rwing = root.getChild("Rwing");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "bottom",
                CubeListBuilder.create().texOffs(0, 56).addBox(-2.0f, 0.0f, -2.0f, 4, 1, 4),
                PartPose.offset(0.0f, 23.0f, 0.0f));
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 45).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 8),
                PartPose.offset(0.0f, 21.0f, 0.0f));
        root.addOrReplaceChild(
                "back",
                CubeListBuilder.create().texOffs(0, 33).addBox(-3.0f, 0.0f, -3.0f, 6, 1, 10),
                PartPose.offset(0.0f, 20.0f, 0.0f));
        root.addOrReplaceChild(
                "neck",
                CubeListBuilder.create().texOffs(17, 27).addBox(-1.0f, 0.0f, -1.0f, 2, 1, 2),
                PartPose.offset(0.0f, 19.0f, -1.0f));
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(13, 18).addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4),
                PartPose.offset(0.0f, 19.0f, -1.0f));
        root.addOrReplaceChild(
                "beak",
                CubeListBuilder.create().texOffs(0, 21).addBox(-1.5f, -1.0f, -5.0f, 3, 1, 3),
                PartPose.offset(0.0f, 19.0f, -1.0f));
        root.addOrReplaceChild(
                "Lwing",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0f, -0.5f, 0.0f, 2, 1, 5),
                PartPose.offset(3.0f, 21.0f, -2.0f));
        root.addOrReplaceChild(
                "Rwing",
                CubeListBuilder.create().texOffs(17, 0).addBox(-2.0f, -0.5f, 0.0f, 2, 1, 5),
                PartPose.offset(-3.0f, 21.0f, -2.0f));
        return mesh;
    }

    @Override
    public void setupAnim(
            RubberDucky c,
            float f,
            float f1,
            float f2,
            float f3,
            float f4) {
        RenderInfo r;
        float newangle;
        float nextangle;
        newangle =
                (double) f1 > 0.1
                        ? Mth.cos((float) (f2 * 2.3f * this.wingspeed)) * 3.1415927f * 0.25f * f1
                        : 0.0f;
        this.beak.yRot = this.head.yRot = (float) Math.toRadians(f3) * 0.45f;
        this.beak.xRot = this.head.xRot = (float) Math.toRadians(f4) * 0.65f;
        r = c.getRenderInfo();
        newangle = Mth.cos((float) (f2 * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        nextangle = Mth.cos((float) ((f2 + 0.3f) * 1.0f * this.wingspeed)) * 3.1415927f * 0.15f;
        if (nextangle > 0.0f && newangle < 0.0f) {
            r.ri1 = 0;
            if (c.getRandom().nextInt(3) == 1) {
                r.ri1 = 1;
            }
            if (c.getKillCount() >= 5) {
                if (c.getRandom().nextInt(2) == 1) {
                    r.ri1 = 1;
                }
                newangle *= 4.0f;
            }
        }
        if (r.ri1 == 0) {
            newangle = 0.0f;
        }
        if (c.isInSittingPose()) {
            newangle = 0.0f;
        }
        newangle = Math.abs(newangle);
        this.Lwing.zRot = -newangle;
        this.Lwing.yRot = newangle / 2.0f;
        this.Rwing.zRot = newangle;
        this.Rwing.yRot = (-newangle) / 2.0f;
        c.setRenderInfo(r);
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
        this.bottom.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.back.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.beak.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Lwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Rwing.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
