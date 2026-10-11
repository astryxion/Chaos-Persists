package com.astryxion.chaospersists.client.model;

import com.astryxion.chaospersists.entity.Nastysaurus;
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

public class ModelNastysaurus extends EntityModel<Nastysaurus> {
    private final float wingspeed;
    private final ModelPart lclaw1;
    private final ModelPart body;
    private final ModelPart leftleg1;
    private final ModelPart tail1;
    private final ModelPart leftleg2;
    private final ModelPart body2;
    private final ModelPart leftleg3;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart lclaw2;
    private final ModelPart lclaw3;
    private final ModelPart lclaw4;
    private final ModelPart lclaw5;
    private final ModelPart lclaw6;
    private final ModelPart lclaw7;
    private final ModelPart neck3;
    private final ModelPart head3;
    private final ModelPart jaw1;
    private final ModelPart tooth1;
    private final ModelPart tooth2;
    private final ModelPart tooth3;
    private final ModelPart tooth4;
    private final ModelPart tooth5;
    private final ModelPart jaw5;
    private final ModelPart head7;
    private final ModelPart tooth6;
    private final ModelPart tooth7;
    private final ModelPart tooth8;
    private final ModelPart tooth9;
    private final ModelPart tooth10;
    private final ModelPart tooth11;
    private final ModelPart tooth12;
    private final ModelPart tooth13;
    private final ModelPart rightleg1;
    private final ModelPart rightleg2;
    private final ModelPart tooth14;
    private final ModelPart tooth15;
    private final ModelPart tooth16;
    private final ModelPart tooth17;
    private final ModelPart tooth18;
    private final ModelPart tooth19;
    private final ModelPart tooth20;
    private final ModelPart tooth21;
    private final ModelPart tooth22;
    private final ModelPart tooth23;
    private final ModelPart rightleg3;
    private final ModelPart rclaw2;
    private final ModelPart rclaw4;
    private final ModelPart rclaw1;
    private final ModelPart rclaw5;
    private final ModelPart rclaw7;
    private final ModelPart rclaw3;
    private final ModelPart rclaw6;
    private final ModelPart neck1;
    private final ModelPart neck2;
    private final ModelPart tail4;
    private final ModelPart Spike1;
    private final ModelPart Spike2;
    private final ModelPart Spike3;
    public ModelNastysaurus(float f1) {
        this(f1, LayerDefinition.create(createMesh(), 512, 256).bakeRoot());
    }

    public ModelNastysaurus(float wingspeed, ModelPart root) {
        this.wingspeed = wingspeed;
        this.lclaw1 = root.getChild("lclaw1");
        this.body = root.getChild("body");
        this.leftleg1 = root.getChild("leftleg1");
        this.tail1 = root.getChild("tail1");
        this.leftleg2 = root.getChild("leftleg2");
        this.body2 = root.getChild("body2");
        this.leftleg3 = root.getChild("leftleg3");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.lclaw2 = root.getChild("lclaw2");
        this.lclaw3 = root.getChild("lclaw3");
        this.lclaw4 = root.getChild("lclaw4");
        this.lclaw5 = root.getChild("lclaw5");
        this.lclaw6 = root.getChild("lclaw6");
        this.lclaw7 = root.getChild("lclaw7");
        this.neck3 = root.getChild("neck3");
        this.head3 = root.getChild("head3");
        this.jaw1 = root.getChild("jaw1");
        this.tooth1 = root.getChild("tooth1");
        this.tooth2 = root.getChild("tooth2");
        this.tooth3 = root.getChild("tooth3");
        this.tooth4 = root.getChild("tooth4");
        this.tooth5 = root.getChild("tooth5");
        this.jaw5 = root.getChild("jaw5");
        this.head7 = root.getChild("head7");
        this.tooth6 = root.getChild("tooth6");
        this.tooth7 = root.getChild("tooth7");
        this.tooth8 = root.getChild("tooth8");
        this.tooth9 = root.getChild("tooth9");
        this.tooth10 = root.getChild("tooth10");
        this.tooth11 = root.getChild("tooth11");
        this.tooth12 = root.getChild("tooth12");
        this.tooth13 = root.getChild("tooth13");
        this.rightleg1 = root.getChild("rightleg1");
        this.rightleg2 = root.getChild("rightleg2");
        this.tooth14 = root.getChild("tooth14");
        this.tooth15 = root.getChild("tooth15");
        this.tooth16 = root.getChild("tooth16");
        this.tooth17 = root.getChild("tooth17");
        this.tooth18 = root.getChild("tooth18");
        this.tooth19 = root.getChild("tooth19");
        this.tooth20 = root.getChild("tooth20");
        this.tooth21 = root.getChild("tooth21");
        this.tooth22 = root.getChild("tooth22");
        this.tooth23 = root.getChild("tooth23");
        this.rightleg3 = root.getChild("rightleg3");
        this.rclaw2 = root.getChild("rclaw2");
        this.rclaw4 = root.getChild("rclaw4");
        this.rclaw1 = root.getChild("rclaw1");
        this.rclaw5 = root.getChild("rclaw5");
        this.rclaw7 = root.getChild("rclaw7");
        this.rclaw3 = root.getChild("rclaw3");
        this.rclaw6 = root.getChild("rclaw6");
        this.neck1 = root.getChild("neck1");
        this.neck2 = root.getChild("neck2");
        this.tail4 = root.getChild("tail4");
        this.Spike1 = root.getChild("Spike1");
        this.Spike2 = root.getChild("Spike2");
        this.Spike3 = root.getChild("Spike3");
    }
    public static MeshDefinition createMesh() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("lclaw1", CubeListBuilder.create().texOffs(300, 111).addBox(-3.0f, 0.0f, -3.0f, 2, 3, 6), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, 0.0f, 0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(407, 3).addBox(-6.0f, -12.0f, -9.0f, 12, 17, 9), PartPose.offsetAndRotation(0.0f, -2.0f, 9.0f, -0.3141593f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg1", CubeListBuilder.create().texOffs(300, 0).addBox(-3.0f, -4.0f, -21.0f, 6, 11, 11), PartPose.offsetAndRotation(9.0f, 2.0f, 26.0f, -0.5759587f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(400, 75).addBox(-6.0f, -6.0f, 0.0f, 10, 12, 14), PartPose.offsetAndRotation(1.0f, -5.0f, 22.0f, -0.1745329f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(300, 23).addBox(-3.0f, -10.0f, -5.0f, 5, 13, 7), PartPose.offsetAndRotation(9.0f, 2.0f, 26.0f, 0.9773844f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(400, 39).addBox(0.0f, -3.0f, -3.0f, 12, 18, 16), PartPose.offsetAndRotation(-6.0f, -11.0f, 10.0f, -0.1047198f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("leftleg3", CubeListBuilder.create().texOffs(300, 51).addBox(-1.0f, -19.0f, 0.0f, 4, 18, 6), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, -0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(400, 103).addBox(-4.0f, -4.0f, 0.0f, 8, 10, 12), PartPose.offsetAndRotation(0.0f, -4.0f, 35.0f, -0.1396263f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(400, 127).addBox(-3.0f, -3.0f, 0.0f, 6, 8, 12), PartPose.offsetAndRotation(0.0f, -3.0f, 46.0f, -0.1396263f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lclaw2", CubeListBuilder.create().texOffs(300, 76).addBox(-1.0f, -1.0f, -6.0f, 4, 4, 13), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lclaw3", CubeListBuilder.create().texOffs(300, 95).addBox(2.0f, 0.0f, -6.0f, 2, 3, 10), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, 0.0f, -0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("lclaw4", CubeListBuilder.create().texOffs(308, 123).addBox(0.0f, 0.0f, -10.0f, 2, 3, 4), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("lclaw5", CubeListBuilder.create().texOffs(300, 123).addBox(-2.5f, 1.0f, -5.0f, 1, 2, 2), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, 0.0f, 0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("lclaw6", CubeListBuilder.create().texOffs(322, 123).addBox(2.5f, 1.0f, -9.0f, 1, 2, 3), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, 0.0f, -0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("lclaw7", CubeListBuilder.create().texOffs(333, 123).addBox(0.0f, 1.0f, 7.0f, 1, 2, 3), PartPose.offsetAndRotation(7.0f, 21.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck3", CubeListBuilder.create().texOffs(375, 23).addBox(-3.0f, -3.0f, -6.0f, 6, 6, 8), PartPose.offsetAndRotation(0.0f, -24.0f, -9.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(130, 32).addBox(-3.0f, -6.0f, -15.0f, 6, 6, 17), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("jaw1", CubeListBuilder.create().texOffs(143, 114).addBox(-3.0f, 1.0f, -14.0f, 6, 3, 15), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, 0.0f, -14.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5f, 0.0f, -14.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth3", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, 0.0f, -14.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth4", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, 0.0f, -12.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth5", CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, 0.0f, -12.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("jaw5", CubeListBuilder.create().texOffs(151, 135).addBox(-4.0f, 1.0f, -4.0f, 8, 4, 7), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("head7", CubeListBuilder.create().texOffs(185, 34).addBox(-4.0f, -7.0f, -3.0f, 8, 7, 10), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth6", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, 0.0f, -10.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth7", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, 0.0f, -10.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth8", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, 0.0f, -8.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth9", CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, 0.0f, -8.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth10", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, 0.0f, -6.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth11", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, 0.0f, -6.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth12", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, 0.0f, -4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth13", CubeListBuilder.create().texOffs(-1, 0).addBox(1.0f, 0.0f, -4.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, -0.2443461f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg1", CubeListBuilder.create().texOffs(246, 0).addBox(-2.0f, -4.0f, -21.0f, 6, 11, 11), PartPose.offsetAndRotation(-10.0f, 2.0f, 26.0f, -0.5934119f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(250, 24).addBox(-1.0f, -10.0f, -5.0f, 5, 13, 7), PartPose.offsetAndRotation(-10.0f, 2.0f, 26.0f, 0.9773844f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth14", CubeListBuilder.create().texOffs(0, 0).addBox(0.5f, -2.0f, -14.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth15", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -2.0f, -14.0f, 1, 3, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth16", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, -1.0f, -12.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth17", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, -1.0f, -12.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth18", CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, -1.0f, -10.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth19", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -1.0f, -10.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth20", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0f, -1.0f, -8.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth21", CubeListBuilder.create().texOffs(0, 0).addBox(2.0f, -1.0f, -8.0f, 1, 2, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth22", CubeListBuilder.create().texOffs(0, 0).addBox(1.0f, 0.0f, -6.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tooth23", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, 0.0f, -6.0f, 1, 1, 1), PartPose.offsetAndRotation(0.0f, -26.0f, -14.0f, 0.1919862f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rightleg3", CubeListBuilder.create().texOffs(250, 47).addBox(-2.0f, -19.0f, 0.0f, 4, 18, 6), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, -0.5235988f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rclaw2", CubeListBuilder.create().texOffs(250, 76).addBox(-2.0f, -1.0f, -6.0f, 4, 4, 13), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rclaw4", CubeListBuilder.create().texOffs(247, 123).addBox(-1.0f, 0.0f, -10.0f, 2, 3, 4), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rclaw1", CubeListBuilder.create().texOffs(250, 111).addBox(2.0f, 0.0f, -3.0f, 2, 3, 6), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, 0.0f, -0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("rclaw5", CubeListBuilder.create().texOffs(261, 123).addBox(2.5f, 1.0f, -5.0f, 1, 2, 2), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, 0.0f, -0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("rclaw7", CubeListBuilder.create().texOffs(283, 123).addBox(0.0f, 1.0f, 7.0f, 1, 2, 3), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, 0.0f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("rclaw3", CubeListBuilder.create().texOffs(250, 95).addBox(-3.0f, 0.0f, -6.0f, 2, 3, 10), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, 0.0f, 0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("rclaw6", CubeListBuilder.create().texOffs(270, 123).addBox(-2.5f, 1.0f, -9.0f, 1, 2, 3), PartPose.offsetAndRotation(-8.0f, 21.0f, 11.0f, 0.0f, 0.6632251f, 0.0f));
        partdefinition.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(45, 0).addBox(-5.0f, -6.0f, -14.0f, 10, 12, 15), PartPose.offsetAndRotation(0.0f, -9.0f, 5.0f, -0.837758f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(48, 29).addBox(-4.5f, -4.0f, -10.0f, 9, 9, 10), PartPose.offsetAndRotation(0.0f, -19.0f, -2.0f, -0.7853982f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(400, 150).addBox(-2.0f, -3.0f, 0.0f, 4, 6, 16), PartPose.offsetAndRotation(0.0f, -1.0f, 56.0f, -0.1396263f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike1", CubeListBuilder.create().texOffs(0, 100).addBox(-2.0f, -16.0f, -1.0f, 4, 16, 18), PartPose.offsetAndRotation(0.0f, -4.0f, 7.0f, 0.5061455f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike2", CubeListBuilder.create().texOffs(0, 72).addBox(-1.5f, -12.0f, 0.0f, 3, 12, 10), PartPose.offsetAndRotation(0.0f, 0.0f, 29.0f, 0.4886922f, 0.0f, 0.0f));
        partdefinition.addOrReplaceChild("Spike3", CubeListBuilder.create().texOffs(0, 44).addBox(-1.0f, -7.0f, 0.0f, 2, 8, 7), PartPose.offsetAndRotation(0.0f, -2.0f, 41.0f, 0.5934119f, 0.0f, 0.0f));
        return meshdefinition;
    }
    @Override
    public void setupAnim(Nastysaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderInfo r = entity.getRenderInfo();
float newangle = 0.0f;
        float pscale = 2.0f;
        float tailspeed = 0.0f;
        float tailamp = 0.0f;
        float clawZ = 15.0f;
        float clawY = 21.0f;
        float clawZamp = 5.0f * pscale;
        float clawYamp = 2.0f * pscale;
        float pi4 = 0.7853982f;
        netHeadYaw %= 360.0f;
        netHeadYaw *= 0.35f;
        this.neck2.yRot = (float) Math.toRadians(netHeadYaw) * 0.5f;
        this.neck3.yRot = this.head7.yRot = (this.head3.yRot = (float) Math.toRadians(netHeadYaw));
        this.jaw1.yRot = this.jaw5.yRot = this.head3.yRot;
        this.tooth4.yRot = this.tooth5.yRot = this.head3.yRot;
        this.tooth3.yRot = this.tooth5.yRot;
        this.tooth2.yRot = this.tooth5.yRot;
        this.tooth1.yRot = this.tooth5.yRot;
        this.tooth9.yRot = this.tooth10.yRot = this.head3.yRot;
        this.tooth8.yRot = this.tooth10.yRot;
        this.tooth7.yRot = this.tooth10.yRot;
        this.tooth6.yRot = this.tooth10.yRot;
        this.tooth14.yRot = this.tooth15.yRot = this.head3.yRot;
        this.tooth13.yRot = this.tooth15.yRot;
        this.tooth12.yRot = this.tooth15.yRot;
        this.tooth11.yRot = this.tooth15.yRot;
        this.tooth19.yRot = this.tooth20.yRot = this.head3.yRot;
        this.tooth18.yRot = this.tooth20.yRot;
        this.tooth17.yRot = this.tooth20.yRot;
        this.tooth16.yRot = this.tooth20.yRot;
        this.tooth22.yRot = this.tooth23.yRot = this.head3.yRot;
        this.tooth21.yRot = this.tooth23.yRot;
        if (entity.getAttacking() != 0) {
            newangle = Mth.cos((float)(ageInTicks * 0.85f * this.wingspeed)) * 3.1415927f * 0.16f;
            newangle += 0.5f;
        } else {
            newangle = ageInTicks * 0.7f * this.wingspeed % 6.2831855f;
            if ((newangle = Math.abs(newangle)) < r.rf1) {
                r.ri1 = 0;
                if (entity.getRandom().nextInt(20) == 1) {
                    r.ri1 |= 1;
                }
            }
            r.rf1 = newangle;
            if (r.ri1 != 0) {
                newangle = Mth.sin((float)(ageInTicks * 0.85f * this.wingspeed)) * 3.1415927f * 0.16f;
                newangle += 0.5f;
            } else {
                newangle = pi4 / 4.0f;
            }
        }
        this.jaw1.xRot = this.jaw5.xRot = newangle;
        this.tooth14.xRot = this.tooth15.xRot = newangle;
        this.tooth19.xRot = this.tooth20.xRot = newangle;
        this.tooth18.xRot = this.tooth20.xRot;
        this.tooth17.xRot = this.tooth20.xRot;
        this.tooth16.xRot = this.tooth20.xRot;
        this.tooth22.xRot = this.tooth23.xRot = newangle;
        this.tooth21.xRot = this.tooth23.xRot;
        float t1 = 0.0f;
        float t2 = 0.0f;
        if (limbSwingAmount > 0.001f) {
            newangle = Mth.cos((float)(ageInTicks * this.wingspeed / pscale));
            t1 = Mth.sin((float)(ageInTicks * this.wingspeed / pscale));
        } else {
            newangle = 0.0f;
            t1 = 0.0f;
            t2 = 0.0f;
        }
        if (t1 > 0.0f) {
            t2 = t1 * clawYamp * limbSwingAmount;
            this.lclaw1.y = clawY - t2;
        } else {
            this.lclaw1.y = clawY;
        }
        this.lclaw6.z = this.lclaw7.z = (this.lclaw1.z = clawZ + clawZamp * newangle * limbSwingAmount);
        this.lclaw5.z = this.lclaw7.z;
        this.lclaw4.z = this.lclaw7.z;
        this.lclaw3.z = this.lclaw7.z;
        this.lclaw2.z = this.lclaw7.z;
        this.lclaw6.y = this.lclaw7.y = this.lclaw1.y;
        this.lclaw5.y = this.lclaw7.y;
        this.lclaw4.y = this.lclaw7.y;
        this.lclaw3.y = this.lclaw7.y;
        this.lclaw2.y = this.lclaw7.y;
        this.leftleg3.z = this.lclaw1.z;
        this.leftleg3.y = this.lclaw1.y;
        this.leftleg3.xRot = -0.523f + newangle * 3.1415927f * 0.15f * limbSwingAmount;
        this.leftleg1.xRot = -0.576f + newangle * 3.1415927f * 0.06f * limbSwingAmount;
        this.leftleg2.xRot = 0.977f + newangle * 3.1415927f * 0.06f * limbSwingAmount;
        this.leftleg1.y = this.leftleg2.y = this.leftleg3.y - (float)Math.cos(this.leftleg3.xRot) * 17.0f;
        this.leftleg1.z = this.leftleg2.z = this.leftleg3.z - (float)Math.sin(this.leftleg3.xRot) * 17.0f;
        t1 = 0.0f;
        t2 = 0.0f;
        if (limbSwingAmount > 0.001f) {
            newangle = Mth.cos((float)(ageInTicks * this.wingspeed / pscale + pi4 * 4.0f));
            t1 = Mth.sin((float)(ageInTicks * this.wingspeed / pscale + pi4 * 4.0f));
        } else {
            newangle = 0.0f;
            t1 = 0.0f;
            t2 = 0.0f;
        }
        if (t1 > 0.0f) {
            t2 = t1 * clawYamp * limbSwingAmount;
            this.rclaw1.y = clawY - t2;
        } else {
            this.rclaw1.y = clawY;
        }
        this.rclaw6.z = this.rclaw7.z = (this.rclaw1.z = clawZ + clawZamp * newangle * limbSwingAmount);
        this.rclaw5.z = this.rclaw7.z;
        this.rclaw4.z = this.rclaw7.z;
        this.rclaw3.z = this.rclaw7.z;
        this.rclaw2.z = this.rclaw7.z;
        this.rclaw6.y = this.rclaw7.y = this.rclaw1.y;
        this.rclaw5.y = this.rclaw7.y;
        this.rclaw4.y = this.rclaw7.y;
        this.rclaw3.y = this.rclaw7.y;
        this.rclaw2.y = this.rclaw7.y;
        this.rightleg3.z = this.rclaw1.z;
        this.rightleg3.y = this.rclaw1.y;
        this.rightleg3.xRot = -0.523f + newangle * 3.1415927f * 0.15f * limbSwingAmount;
        this.rightleg1.xRot = -0.576f + newangle * 3.1415927f * 0.06f * limbSwingAmount;
        this.rightleg2.xRot = 0.977f + newangle * 3.1415927f * 0.06f * limbSwingAmount;
        this.rightleg1.y = this.rightleg2.y = this.rightleg3.y - (float)Math.cos(this.rightleg3.xRot) * 17.0f;
        this.rightleg1.z = this.rightleg2.z = this.rightleg3.z - (float)Math.sin(this.rightleg3.xRot) * 17.0f;
        this.lclaw1.xRot = 0.0f;
        this.lclaw7.xRot = 0.0f;
        this.lclaw6.xRot = 0.0f;
        this.lclaw5.xRot = 0.0f;
        this.lclaw4.xRot = 0.0f;
        this.lclaw3.xRot = 0.0f;
        this.lclaw2.xRot = 0.0f;
        this.rclaw1.xRot = 0.0f;
        this.rclaw7.xRot = 0.0f;
        this.rclaw6.xRot = 0.0f;
        this.rclaw5.xRot = 0.0f;
        this.rclaw4.xRot = 0.0f;
        this.rclaw3.xRot = 0.0f;
        this.rclaw2.xRot = 0.0f;
        if (entity.getAttacking() != 0) {
            tailspeed = 0.76f;
            tailamp = 0.25f;
        } else {
            tailspeed = 0.26f;
            tailamp = 0.08f;
        }
        this.tail3.yRot = Mth.cos((float)(ageInTicks * tailspeed * this.wingspeed)) * 3.1415927f * tailamp / 2.0f;
        this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 11.0f;
        this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 11.0f;
        this.tail4.yRot = Mth.cos((float)(ageInTicks * tailspeed * this.wingspeed)) * 3.1415927f * tailamp;
        entity.setRenderInfo(r);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.lclaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lclaw7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.jaw5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth8.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth9.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth10.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth11.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth12.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth13.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth14.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth15.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth16.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth17.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth18.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth19.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth20.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth21.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth22.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tooth23.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw5.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw7.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rclaw6.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail4.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike1.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike2.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.Spike3.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
