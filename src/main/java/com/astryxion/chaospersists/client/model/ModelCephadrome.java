package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Cephadrome;
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
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModelCephadrome extends EntityModel<Cephadrome> {
    private final float wingspeed;
    private final ModelPart leftfoot;
    private final ModelPart butt;
    private final ModelPart rightfoot;
    private final ModelPart topfin1;
    private final ModelPart topfin2;
    private final ModelPart topfin3;
    private final ModelPart topfin4;
    private final ModelPart leftshoulder;
    private final ModelPart lefwingfin1;
    private final ModelPart tailfin1;
    private final ModelPart tailmembrane2;
    private final ModelPart tailfin2;
    private final ModelPart tailfin4;
    private final ModelPart tailfin3;
    private final ModelPart tailmembrane1;
    private final ModelPart topmem1;
    private final ModelPart topmem2;
    private final ModelPart topmem3;
    private final ModelPart topmem4;
    private final ModelPart neck1;
    private final ModelPart body;
    private final ModelPart chest1;
    private final ModelPart leftleg1;
    private final ModelPart mouth;
    private final ModelPart neck2;
    private final ModelPart head;
    private final ModelPart hammerhead;
    private final ModelPart chest;
    private final ModelPart neck3;
    private final ModelPart tail1;
    private final ModelPart rightleg1;
    private final ModelPart leftleg2;
    private final ModelPart rightleg2;
    private final ModelPart body2;
    private final ModelPart leftleg3;
    private final ModelPart rightleg3;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tailmembrane3;
    private final ModelPart leftwingfin2;
    private final ModelPart leftwingfin3;
    private final ModelPart leftwingfin4;
    private final ModelPart leftwingmembrane;
    private final ModelPart rightshoulder;
    private final ModelPart rightwingfin1;
    private final ModelPart rightwingfin2;
    private final ModelPart rightwingfin3;
    private final ModelPart rightwingfin4;
    private final ModelPart rightwingmembrane;
    private final ModelPart hammerhead2;

    public ModelCephadrome(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 512, 256).bakeRoot());
    }

    public ModelCephadrome(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.leftfoot = root.getChild("leftfoot");
        this.butt = root.getChild("butt");
        this.rightfoot = root.getChild("rightfoot");
        this.topfin1 = root.getChild("topfin1");
        this.topfin2 = root.getChild("topfin2");
        this.topfin3 = root.getChild("topfin3");
        this.topfin4 = root.getChild("topfin4");
        this.leftshoulder = root.getChild("leftshoulder");
        this.lefwingfin1 = root.getChild("lefwingfin1");
        this.tailfin1 = root.getChild("tailfin1");
        this.tailmembrane2 = root.getChild("tailmembrane2");
        this.tailfin2 = root.getChild("tailfin2");
        this.tailfin4 = root.getChild("tailfin4");
        this.tailfin3 = root.getChild("tailfin3");
        this.tailmembrane1 = root.getChild("tailmembrane1");
        this.topmem1 = root.getChild("topmem1");
        this.topmem2 = root.getChild("topmem2");
        this.topmem3 = root.getChild("topmem3");
        this.topmem4 = root.getChild("topmem4");
        this.neck1 = root.getChild("neck1");
        this.body = root.getChild("body");
        this.chest1 = root.getChild("chest1");
        this.leftleg1 = root.getChild("leftleg1");
        this.mouth = root.getChild("mouth");
        this.neck2 = root.getChild("neck2");
        this.head = root.getChild("head");
        this.hammerhead = root.getChild("hammerhead");
        this.chest = root.getChild("chest");
        this.neck3 = root.getChild("neck3");
        this.tail1 = root.getChild("tail1");
        this.rightleg1 = root.getChild("rightleg1");
        this.leftleg2 = root.getChild("leftleg2");
        this.rightleg2 = root.getChild("rightleg2");
        this.body2 = root.getChild("body2");
        this.leftleg3 = root.getChild("leftleg3");
        this.rightleg3 = root.getChild("rightleg3");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tailmembrane3 = root.getChild("tailmembrane3");
        this.leftwingfin2 = root.getChild("leftwingfin2");
        this.leftwingfin3 = root.getChild("leftwingfin3");
        this.leftwingfin4 = root.getChild("leftwingfin4");
        this.leftwingmembrane = root.getChild("leftwingmembrane");
        this.rightshoulder = root.getChild("rightshoulder");
        this.rightwingfin1 = root.getChild("rightwingfin1");
        this.rightwingfin2 = root.getChild("rightwingfin2");
        this.rightwingfin3 = root.getChild("rightwingfin3");
        this.rightwingfin4 = root.getChild("rightwingfin4");
        this.rightwingmembrane = root.getChild("rightwingmembrane");
        this.hammerhead2 = root.getChild("hammerhead2");
    }

    private static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("leftfoot", CubeListBuilder.create().texOffs(41, 194).addBox(-2.0f, 34.0f, -12.0f, 9, 4, 10), PartPose.offset(7.0f, -14.0f, 17.0f));
        root.addOrReplaceChild("butt", CubeListBuilder.create().texOffs(367, 235).addBox(0.0f, 0.0f, -2.0f, 9, 14, 6), PartPose.offsetAndRotation(-4.5f, -8.0f, 29.0f, -0.8726646f, 0.0f, 0.0f));
        root.addOrReplaceChild("rightfoot", CubeListBuilder.create().texOffs(41, 170).addBox(-7.0f, 34.0f, -12.0f, 9, 4, 10), PartPose.offset(-7.0f, -14.0f, 17.0f));
        root.addOrReplaceChild("topfin1", CubeListBuilder.create().texOffs(64, 112).addBox(-3.0f, -2.0f, -30.0f, 6, 3, 30), PartPose.offsetAndRotation(0.0f, -15.0f, -7.0f, -1.850049f, 0.0f, 0.0f));
        root.addOrReplaceChild("topfin2", CubeListBuilder.create().texOffs(69, 81).addBox(-3.0f, -2.0f, -25.0f, 6, 3, 25), PartPose.offsetAndRotation(0.0f, -15.0f, -2.0f, -2.076942f, 0.0f, 0.0f));
        root.addOrReplaceChild("topfin3", CubeListBuilder.create().texOffs(-1, 140).addBox(-3.0f, -2.0f, -20.0f, 6, 3, 20), PartPose.offsetAndRotation(0.0f, -16.0f, 3.0f, -2.426008f, 0.0f, 0.0f));
        root.addOrReplaceChild("topfin4", CubeListBuilder.create().texOffs(148, 148).addBox(-3.0f, -2.0f, -10.0f, 6, 3, 10), PartPose.offsetAndRotation(0.0f, -17.0f, 13.0f, -2.635447f, 0.0f, 0.0f));
        root.addOrReplaceChild("leftshoulder", CubeListBuilder.create().texOffs(144, 236).addBox(0.0f, 0.0f, 1.0f, 6, 8, 11), PartPose.offsetAndRotation(6.0f, -16.0f, -14.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("lefwingfin1", CubeListBuilder.create().texOffs(147, 96).addBox(0.0f, -2.0f, -2.0f, 70, 5, 3), PartPose.offsetAndRotation(9.0f, -12.0f, -11.0f, -0.2617994f, -0.1745329f, 0.0f));
        root.addOrReplaceChild("tailfin1", CubeListBuilder.create().texOffs(168, 0).addBox(-6.0f, -1.0f, 0.0f, 12, 3, 30), PartPose.offsetAndRotation(0.0f, -9.0f, 56.0f, 0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailmembrane2", CubeListBuilder.create().texOffs(201, 38).addBox(0.0f, -8.0f, 3.0f, 0, 10, 19), PartPose.offsetAndRotation(0.0f, 0.0f, 56.0f, -0.296706f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailfin2", CubeListBuilder.create().texOffs(186, 184).addBox(-4.0f, 0.0f, 0.0f, 8, 2, 27), PartPose.offsetAndRotation(0.0f, -7.0f, 56.0f, -0.1919862f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailfin4", CubeListBuilder.create().texOffs(186, 137).addBox(-4.0f, 1.0f, 1.0f, 8, 3, 22), PartPose.offsetAndRotation(0.0f, -3.0f, 56.0f, -0.837758f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailfin3", CubeListBuilder.create().texOffs(185, 216).addBox(-4.0f, 0.0f, 1.0f, 8, 2, 23), PartPose.offsetAndRotation(0.0f, -5.0f, 57.0f, -0.5759587f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailmembrane1", CubeListBuilder.create().texOffs(245, 38).addBox(0.0f, 0.0f, 4.0f, 0, 11, 21), PartPose.offsetAndRotation(0.0f, -9.0f, 56.0f, 0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("topmem1", CubeListBuilder.create().texOffs(25, 0).addBox(0.0f, -25.0f, 0.0f, 0, 24, 10), PartPose.offsetAndRotation(0.0f, -15.0f, -6.0f, -0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("topmem2", CubeListBuilder.create().texOffs(135, 0).addBox(1.0f, -22.0f, 0.0f, 0, 20, 10), PartPose.offsetAndRotation(-1.0f, -15.0f, -2.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("topmem3", CubeListBuilder.create().texOffs(258, 0).addBox(0.0f, -18.0f, 0.0f, 0, 18, 8), PartPose.offsetAndRotation(0.0f, -16.0f, 3.0f, -0.8901179f, 0.0f, 0.0f));
        root.addOrReplaceChild("topmem4", CubeListBuilder.create().texOffs(282, 0).addBox(0.0f, -9.0f, 0.0f, 0, 9, 10), PartPose.offsetAndRotation(0.0f, -17.0f, 13.0f, -1.117011f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(404, 235).addBox(-6.0f, -5.0f, -10.0f, 10, 9, 10), PartPose.offsetAndRotation(1.0f, -6.0f, -33.0f, 0.3665191f, 0.0f, 0.0f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 220).addBox(-6.0f, -11.0f, -10.0f, 12, 15, 19), PartPose.offsetAndRotation(0.0f, -7.0f, 3.0f, 0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("chest1", CubeListBuilder.create().texOffs(98, 210).addBox(-3.0f, -4.0f, -2.0f, 10, 11, 5), PartPose.offsetAndRotation(-2.0f, -6.0f, -13.0f, 1.029744f, 0.0f, 0.0f));
        root.addOrReplaceChild("leftleg1", CubeListBuilder.create().texOffs(135, 183).addBox(-1.0f, 0.0f, -4.0f, 7, 18, 10), PartPose.offsetAndRotation(7.0f, -14.0f, 17.0f, -0.5759587f, 0.0f, 0.0f));
        root.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(92, 150).addBox(-7.0f, 1.0f, 3.0f, 14, 15, 4), PartPose.offsetAndRotation(0.0f, -6.0f, -43.0f, -0.8726646f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(152, 110).addBox(-6.0f, -5.0f, -17.0f, 11, 10, 17), PartPose.offsetAndRotation(0.5f, -10.0f, -19.0f, 0.2617994f, 0.0f, 0.0f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(275, 219).addBox(-10.0f, -3.0f, -16.0f, 20, 7, 16), PartPose.offsetAndRotation(0.0f, -6.0f, -43.0f, 0.5061455f, 0.0f, 0.0f));
        root.addOrReplaceChild("hammerhead", CubeListBuilder.create().texOffs(258, 134).addBox(-18.0f, -2.0f, -15.0f, 36, 6, 14), PartPose.offsetAndRotation(0.0f, -6.0f, -43.0f, 0.4537856f, 0.0f, 0.0f));
        root.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(100, 15).addBox(-3.0f, -3.0f, 0.0f, 9, 29, 7), PartPose.offsetAndRotation(-1.5f, 0.0f, -5.0f, 1.413717f, 0.0f, 0.0f));
        root.addOrReplaceChild("neck3", CubeListBuilder.create().texOffs(264, 173).addBox(-6.0f, -5.0f, -16.0f, 12, 11, 16), PartPose.offsetAndRotation(0.0f, -11.0f, -6.0f, 0.0872665f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(51, 5).addBox(-5.0f, -6.0f, 0.0f, 10, 13, 14), PartPose.offsetAndRotation(0.0f, -10.0f, 22.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("rightleg1", CubeListBuilder.create().texOffs(94, 175).addBox(-6.0f, 0.0f, -4.0f, 7, 18, 10), PartPose.offsetAndRotation(-7.0f, -14.0f, 17.0f, -0.5759587f, 0.0f, 0.0f));
        root.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(28, 112).addBox(-1.0f, 6.0f, -17.0f, 7, 12, 7), PartPose.offsetAndRotation(7.0f, -14.0f, 17.0f, 0.9773844f, 0.0f, 0.0f));
        root.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(32, 90).addBox(-6.0f, 6.0f, -17.0f, 7, 12, 7), PartPose.offsetAndRotation(-7.0f, -14.0f, 17.0f, 0.9773844f, 0.0f, 0.0f));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(400, 179).addBox(0.0f, 3.0f, 3.0f, 12, 16, 16), PartPose.offsetAndRotation(-6.0f, -23.0f, 6.0f, -0.1919862f, 0.0f, 0.0f));
        root.addOrReplaceChild("leftleg3", CubeListBuilder.create().texOffs(351, 192).addBox(-1.0f, 17.0f, 10.0f, 7, 17, 6), PartPose.offsetAndRotation(7.0f, -14.0f, 17.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("rightleg3", CubeListBuilder.create().texOffs(323, 192).addBox(-6.0f, 17.0f, 10.0f, 7, 17, 6), PartPose.offsetAndRotation(-7.0f, -14.0f, 17.0f, -0.5235988f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(51, 55).addBox(-6.0f, -6.0f, 0.0f, 9, 12, 14), PartPose.offsetAndRotation(1.5f, -7.0f, 35.0f, -0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(105, 52).addBox(-5.0f, -6.0f, 0.0f, 8, 11, 14), PartPose.offsetAndRotation(1.0f, -5.0f, 48.0f, -0.1396263f, 0.0f, 0.0f));
        root.addOrReplaceChild("tailmembrane3", CubeListBuilder.create().texOffs(155, 38).addBox(0.0f, -10.0f, 0.0f, 0, 10, 18), PartPose.offsetAndRotation(0.0f, 2.0f, 56.0f, -0.837758f, 0.0f, 0.0f));
        root.addOrReplaceChild("leftwingfin2", CubeListBuilder.create().texOffs(160, 83).addBox(0.0f, -2.0f, 0.0f, 64, 4, 2), PartPose.offsetAndRotation(9.0f, -12.0f, -11.0f, -0.2617994f, -0.4363323f, 0.0f));
        root.addOrReplaceChild("leftwingfin3", CubeListBuilder.create().texOffs(209, 106).addBox(0.0f, -2.0f, 0.0f, 48, 4, 2), PartPose.offsetAndRotation(9.0f, -11.0f, -10.0f, -0.2617994f, -0.7853982f, 0.0f));
        root.addOrReplaceChild("leftwingfin4", CubeListBuilder.create().texOffs(233, 120).addBox(0.0f, 0.0f, 0.0f, 37, 4, 2), PartPose.offsetAndRotation(9.0f, -13.0f, -6.0f, -0.2617994f, -1.186824f, 0.0f));
        root.addOrReplaceChild("leftwingmembrane", CubeListBuilder.create().texOffs(300, 27).addBox(3.0f, 0.0f, 0.0f, 64, 0, 34), PartPose.offsetAndRotation(9.0f, -13.0f, -10.0f, -0.0872665f, -0.1745329f, 0.0f));
        root.addOrReplaceChild("rightshoulder", CubeListBuilder.create().texOffs(0, 193).addBox(0.0f, 0.0f, 0.0f, 6, 8, 11), PartPose.offsetAndRotation(-12.0f, -16.0f, -13.0f, -0.1745329f, 0.0f, 0.0f));
        root.addOrReplaceChild("rightwingfin1", CubeListBuilder.create().texOffs(344, 109).addBox(-69.0f, -2.0f, 0.0f, 69, 5, 3), PartPose.offsetAndRotation(-10.0f, -12.0f, -13.0f, -0.2617994f, 0.1745329f, 0.0f));
        root.addOrReplaceChild("rightwingfin2", CubeListBuilder.create().texOffs(349, 119).addBox(-63.0f, -2.0f, 0.0f, 64, 4, 2), PartPose.offsetAndRotation(-9.0f, -12.0f, -11.0f, -0.2617994f, 0.4363323f, 0.0f));
        root.addOrReplaceChild("rightwingfin3", CubeListBuilder.create().texOffs(368, 128).addBox(-49.0f, 0.0f, 0.0f, 48, 4, 2), PartPose.offsetAndRotation(-9.0f, -13.0f, -9.0f, -0.2617994f, 0.7679449f, 0.0f));
        root.addOrReplaceChild("rightwingfin4", CubeListBuilder.create().texOffs(379, 137).addBox(-35.0f, 0.0f, 0.0f, 37, 4, 2), PartPose.offsetAndRotation(-9.0f, -13.0f, -6.0f, -0.2617994f, 1.186824f, 0.0f));
        root.addOrReplaceChild("rightwingmembrane", CubeListBuilder.create().texOffs(300, 67).addBox(-67.0f, -1.0f, 0.0f, 64, 0, 34), PartPose.offsetAndRotation(-9.0f, -12.0f, -12.0f, -0.0872665f, 0.1745329f, 0.0f));
        root.addOrReplaceChild("hammerhead2", CubeListBuilder.create().texOffs(258, 157).addBox(-25.0f, 0.0f, -14.0f, 50, 4, 7), PartPose.offsetAndRotation(0.0f, -7.0f, -43.0f, 0.4537856f, 0.0f, 0.0f));
        return mesh;
    }


    private static boolean cephGroundedForPose(Cephadrome e) {
        if (e.onGround()) {
            return true;
        }
        if (!e.getPassengers().isEmpty()) {
            return e.onGround();
        }
        Level level = e.level();
        if (level == null) {
            return false;
        }
        try {
            double px = e.getX();
            double py = e.getBoundingBox().minY;
            double pz = e.getZ();
            BlockPos.MutableBlockPos mp = new BlockPos.MutableBlockPos();
            for (int i = 0; i < 18; i++) {
                mp.set(px, py - 0.12 - (double) i * 0.35, pz);
                BlockState st = level.getBlockState(mp);
                if (st.isSolid()) {
                    return true;
                }
            }
        } catch (Throwable ignored) {
            return false;
        }
        return false;
    }

    @Override
    public void setupAnim(Cephadrome e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderInfo r = e.getRenderInfo();
        float newangle = 0.0f;
        float lspeed = 0.0f;
        float pi4 = 0.7853982f;
        float tailspeed = 0.76f;
        float tailamp = 0.1f;
        float neckYaw = netHeadYaw;
        boolean useFlightPose =
                !e.getPassengers().isEmpty()
                        ? e.isNoGravity()
                        : e.getActivity() != 0 && !cephGroundedForPose(e);
        if ((double) limbSwingAmount > 0.001) {
            lspeed =
                    (float)
                            ((e.xo - e.getX()) * (e.xo - e.getX())
                                    + (e.zo - e.getZ()) * (e.zo - e.getZ()));
            lspeed = (float) Math.sqrt(lspeed);
            newangle = Mth.cos(ageInTicks * 0.75f * this.wingspeed) * 3.1415927f * lspeed * 0.4f;
            if ((double) newangle > 0.5) {
                newangle = 0.75f;
            }
            if ((double) newangle < -0.5) {
                newangle = -0.75f;
            }
        } else {
            newangle = 0.0f;
        }
        if (useFlightPose) {
            newangle = 1.0f;
            this.rightleg1.xRot = -0.58f + newangle;
            this.rightleg2.xRot = 0.98f + newangle;
            this.rightleg3.xRot = -0.52f + newangle;
            this.rightfoot.xRot = newangle;
            this.leftleg1.xRot = -0.58f + newangle;
            this.leftleg2.xRot = 0.98f + newangle;
            this.leftleg3.xRot = -0.52f + newangle;
            this.leftfoot.xRot = newangle;
        } else {
            this.rightleg1.xRot = -0.58f + newangle;
            this.rightleg2.xRot = 0.98f + newangle;
            this.rightleg3.xRot = -0.52f + newangle;
            this.rightfoot.xRot = newangle;
            this.leftleg1.xRot = -0.58f - newangle;
            this.leftleg2.xRot = 0.98f - newangle;
            this.leftleg3.xRot = -0.52f - newangle;
            this.leftfoot.xRot = -newangle;
        }
        newangle =
                useFlightPose
                        ? Mth.cos(ageInTicks * 0.55f * this.wingspeed) * 3.1415927f * 0.28f
                        : (e.getAttacking() == 0
                                ? -0.85f + Mth.cos(ageInTicks * 0.2f * this.wingspeed) * 3.1415927f * 0.028f
                                : -0.65f + Mth.cos(ageInTicks * 0.9f * this.wingspeed) * 3.1415927f * 0.068f);
        this.lefwingfin1.zRot = newangle;
        this.leftwingfin2.zRot = newangle;
        this.leftwingfin3.zRot = newangle;
        this.leftwingfin4.zRot = newangle;
        this.leftwingmembrane.zRot = newangle;
        this.rightwingfin1.zRot = -newangle;
        this.rightwingfin2.zRot = -newangle;
        this.rightwingfin3.zRot = -newangle;
        this.rightwingfin4.zRot = -newangle;
        this.rightwingmembrane.zRot = -newangle;
        newangle = Mth.cos(ageInTicks * 0.15f * this.wingspeed) * 3.1415927f * 0.05f;
        this.topfin1.xRot = -1.85f - Math.abs(newangle);
        this.topmem1.xRot = -0.26f - Math.abs(newangle);
        this.topfin2.xRot = -2.07f - Math.abs(newangle / 2.0f);
        this.topmem2.xRot = -0.52f - Math.abs(newangle / 2.0f);
        this.topfin3.xRot = -2.42f - Math.abs(newangle / 4.0f);
        this.topmem3.xRot = -0.89f - Math.abs(newangle / 4.0f);
        this.topfin4.xRot = -2.63f - Math.abs(newangle / 8.0f);
        this.topmem4.xRot = -1.11f - Math.abs(newangle / 8.0f);
        if (!useFlightPose && e.getAttacking() == 0) {
            tailspeed = 0.22f;
            tailamp = 0.03f;
        }
        this.tail1.yRot = Mth.cos(ageInTicks * tailspeed * this.wingspeed) * 3.1415927f * 0.04f;
        this.tail2.z = this.tail1.z + (float) Math.cos(this.tail1.yRot) * 13.0f;
        this.tail2.x = this.tail1.x + 1.5f + (float) Math.sin(this.tail1.yRot) * 13.0f;
        this.tail2.yRot = Mth.cos(ageInTicks * tailspeed * this.wingspeed - pi4) * 3.1415927f * tailamp;
        this.tail3.z = this.tail2.z + (float) Math.cos(this.tail2.yRot) * 13.0f;
        this.tail3.x = this.tail2.x - 0.5f + (float) Math.sin(this.tail2.yRot) * 13.0f;
        this.tail3.yRot = Mth.cos(ageInTicks * tailspeed * this.wingspeed - 2.0f * pi4) * 3.1415927f * tailamp;
        this.tailfin1.z = this.tail3.z + (float) Math.cos(this.tail3.yRot) * 10.0f;
        this.tailfin1.x = this.tail3.x - 1.0f + (float) Math.sin(this.tail3.yRot) * 10.0f;
        this.tailfin1.yRot = Mth.cos(ageInTicks * tailspeed * this.wingspeed - 3.0f * pi4) * 3.1415927f * tailamp;
        this.tailfin2.z = this.tailfin1.z;
        this.tailfin2.x = this.tailfin1.x;
        this.tailfin2.yRot = this.tailfin1.yRot;
        this.tailfin3.z = this.tailfin1.z;
        this.tailfin3.x = this.tailfin1.x;
        this.tailfin3.yRot = this.tailfin1.yRot;
        this.tailfin4.z = this.tailfin1.z;
        this.tailfin4.x = this.tailfin1.x;
        this.tailfin4.yRot = this.tailfin1.yRot;
        this.tailmembrane1.z = this.tailfin1.z;
        this.tailmembrane1.x = this.tailfin1.x;
        this.tailmembrane1.yRot = this.tailfin1.yRot;
        this.tailmembrane2.z = this.tailfin1.z;
        this.tailmembrane2.x = this.tailfin1.x;
        this.tailmembrane2.yRot = this.tailfin1.yRot;
        this.tailmembrane3.z = this.tailfin1.z;
        this.tailmembrane3.x = this.tailfin1.x;
        this.tailmembrane3.yRot = this.tailfin1.yRot;
        if (useFlightPose) {
            neckYaw = (e.yRotO - e.getYRot()) * 10.0f;
            neckYaw = -neckYaw;
            r.rf1 += (neckYaw - r.rf1) / 50.0f;
            if (r.rf1 > 50.0f) {
                r.rf1 = 50.0f;
            }
            if (r.rf1 < -50.0f) {
                r.rf1 = -50.0f;
            }
            neckYaw = r.rf1;
        } else {
            neckYaw /= 2.0f;
        }
        this.neck3.yRot = (float) Math.toRadians(neckYaw) * 0.125f;
        this.neck2.z = this.neck3.z - (float) Math.cos(this.neck3.yRot) * 14.0f;
        this.neck2.x = this.neck3.x + 0.5f - (float) Math.sin(this.neck3.yRot) * 14.0f;
        this.neck2.yRot = (float) Math.toRadians(neckYaw) * 0.25f;
        this.neck1.z = this.neck2.z - (float) Math.cos(this.neck2.yRot) * 14.0f;
        this.neck1.x = this.neck2.x + 0.5f - (float) Math.sin(this.neck2.yRot) * 14.0f;
        this.neck1.yRot = (float) Math.toRadians(neckYaw) * 0.5f;
        this.head.z = this.neck1.z - (float) Math.cos(this.neck1.yRot) * 8.0f;
        this.head.x = this.neck1.x - (float) Math.sin(this.neck1.yRot) * 8.0f;
        this.head.yRot = (float) Math.toRadians(neckYaw) * 0.75f;
        this.hammerhead.z = this.head.z;
        this.hammerhead.x = this.head.x;
        this.hammerhead.yRot = this.head.yRot;
        this.hammerhead2.z = this.head.z;
        this.hammerhead2.x = this.head.x;
        this.hammerhead2.yRot = this.head.yRot;
        this.mouth.z = this.head.z;
        this.mouth.x = this.head.x;
        this.mouth.yRot = this.head.yRot;
        newangle = Mth.cos(ageInTicks * 0.5f * this.wingspeed) * 3.1415927f * 0.14f;
        this.mouth.xRot = e.getAttacking() != 0 ? -0.61f + newangle : -0.87f;
        e.setRenderInfo(r);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.leftfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.butt.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfoot.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftshoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lefwingfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmembrane2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmembrane1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hammerhead.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmembrane3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingfin3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingfin4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingmembrane.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightshoulder.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingmembrane.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hammerhead2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
