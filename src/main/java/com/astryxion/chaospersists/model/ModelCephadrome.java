/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Cephadrome
 *  com.astryxion.chaospersists.ModelCephadrome
 *  com.astryxion.chaospersists.RenderInfo
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package com.astryxion.chaospersists.model;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.render.RenderInfo;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModelCephadrome extends EntityModel<Cephadrome> {
    private float wingspeed = 1.0f;
    ModelRenderer leftfoot;
    ModelRenderer butt;
    ModelRenderer rightfoot;
    ModelRenderer topfin1;
    ModelRenderer topfin2;
    ModelRenderer topfin3;
    ModelRenderer topfin4;
    ModelRenderer leftshoulder;
    ModelRenderer lefwingfin1;
    ModelRenderer tailfin1;
    ModelRenderer tailmembrane2;
    ModelRenderer tailfin2;
    ModelRenderer tailfin4;
    ModelRenderer tailfin3;
    ModelRenderer tailmembrane1;
    ModelRenderer topmem1;
    ModelRenderer topmem2;
    ModelRenderer topmem3;
    ModelRenderer topmem4;
    ModelRenderer neck1;
    ModelRenderer body;
    ModelRenderer chest1;
    ModelRenderer leftleg1;
    ModelRenderer mouth;
    ModelRenderer neck2;
    ModelRenderer head;
    ModelRenderer hammerhead;
    ModelRenderer chest;
    ModelRenderer neck3;
    ModelRenderer tail1;
    ModelRenderer rightleg1;
    ModelRenderer leftleg2;
    ModelRenderer rightleg2;
    ModelRenderer body2;
    ModelRenderer leftleg3;
    ModelRenderer rightleg3;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tailmembrane3;
    ModelRenderer leftwingfin2;
    ModelRenderer leftwingfin3;
    ModelRenderer leftwingfin4;
    ModelRenderer leftwingmembrane;
    ModelRenderer rightshoulder;
    ModelRenderer rightwingfin1;
    ModelRenderer rightwingfin2;
    ModelRenderer rightwingfin3;
    ModelRenderer rightwingfin4;
    ModelRenderer rightwingmembrane;
    ModelRenderer hammerhead2;

    public ModelCephadrome(float f1) {
        super(RenderType::entityCutoutNoCull);
        this.wingspeed = f1;
        // textureWidth = 512;
        // textureHeight = 256;
        this.leftfoot = new ModelRenderer(this, 41, 194);
        this.leftfoot.addBox(-2.0f, 34.0f, -12.0f, 9, 4, 10);
        this.leftfoot.setPos(7.0f, -14.0f, 17.0f);
        this.leftfoot.mirror = true;
        this.setRotation(this.leftfoot, 0.0f, 0.0f, 0.0f);
        this.butt = new ModelRenderer(this, 367, 235);
        this.butt.addBox(0.0f, 0.0f, -2.0f, 9, 14, 6);
        this.butt.setPos(-4.5f, -8.0f, 29.0f);
        this.butt.mirror = true;
        this.setRotation(this.butt, -0.8726646f, 0.0f, 0.0f);
        this.rightfoot = new ModelRenderer(this, 41, 170);
        this.rightfoot.addBox(-7.0f, 34.0f, -12.0f, 9, 4, 10);
        this.rightfoot.setPos(-7.0f, -14.0f, 17.0f);
        this.rightfoot.mirror = true;
        this.setRotation(this.rightfoot, 0.0f, 0.0f, 0.0f);
        this.topfin1 = new ModelRenderer(this, 64, 112);
        this.topfin1.addBox(-3.0f, -2.0f, -30.0f, 6, 3, 30);
        this.topfin1.setPos(0.0f, -15.0f, -7.0f);
        this.topfin1.mirror = true;
        this.setRotation(this.topfin1, -1.850049f, 0.0f, 0.0f);
        this.topfin2 = new ModelRenderer(this, 69, 81);
        this.topfin2.addBox(-3.0f, -2.0f, -25.0f, 6, 3, 25);
        this.topfin2.setPos(0.0f, -15.0f, -2.0f);
        this.topfin2.mirror = true;
        this.setRotation(this.topfin2, -2.076942f, 0.0f, 0.0f);
        this.topfin3 = new ModelRenderer(this, -1, 140);
        this.topfin3.addBox(-3.0f, -2.0f, -20.0f, 6, 3, 20);
        this.topfin3.setPos(0.0f, -16.0f, 3.0f);
        this.topfin3.mirror = true;
        this.setRotation(this.topfin3, -2.426008f, 0.0f, 0.0f);
        this.topfin4 = new ModelRenderer(this, 148, 148);
        this.topfin4.addBox(-3.0f, -2.0f, -10.0f, 6, 3, 10);
        this.topfin4.setPos(0.0f, -17.0f, 13.0f);
        this.topfin4.mirror = true;
        this.setRotation(this.topfin4, -2.635447f, 0.0f, 0.0f);
        this.leftshoulder = new ModelRenderer(this, 144, 236);
        this.leftshoulder.addBox(0.0f, 0.0f, 1.0f, 6, 8, 11);
        this.leftshoulder.setPos(6.0f, -16.0f, -14.0f);
        this.leftshoulder.mirror = true;
        this.setRotation(this.leftshoulder, -0.1745329f, 0.0f, 0.0f);
        this.lefwingfin1 = new ModelRenderer(this, 147, 96);
        this.lefwingfin1.addBox(0.0f, -2.0f, -2.0f, 70, 5, 3);
        this.lefwingfin1.setPos(9.0f, -12.0f, -11.0f);
        this.lefwingfin1.mirror = true;
        this.setRotation(this.lefwingfin1, -0.2617994f, -0.1745329f, 0.0f);
        this.tailfin1 = new ModelRenderer(this, 168, 0);
        this.tailfin1.addBox(-6.0f, -1.0f, 0.0f, 12, 3, 30);
        this.tailfin1.setPos(0.0f, -9.0f, 56.0f);
        this.tailfin1.mirror = true;
        this.setRotation(this.tailfin1, 0.1396263f, 0.0f, 0.0f);
        this.tailmembrane2 = new ModelRenderer(this, 201, 38);
        this.tailmembrane2.addBox(0.0f, -8.0f, 3.0f, 0, 10, 19);
        this.tailmembrane2.setPos(0.0f, 0.0f, 56.0f);
        this.tailmembrane2.mirror = true;
        this.setRotation(this.tailmembrane2, -0.296706f, 0.0f, 0.0f);
        this.tailfin2 = new ModelRenderer(this, 186, 184);
        this.tailfin2.addBox(-4.0f, 0.0f, 0.0f, 8, 2, 27);
        this.tailfin2.setPos(0.0f, -7.0f, 56.0f);
        this.tailfin2.mirror = true;
        this.setRotation(this.tailfin2, -0.1919862f, 0.0f, 0.0f);
        this.tailfin4 = new ModelRenderer(this, 186, 137);
        this.tailfin4.addBox(-4.0f, 1.0f, 1.0f, 8, 3, 22);
        this.tailfin4.setPos(0.0f, -3.0f, 56.0f);
        this.tailfin4.mirror = true;
        this.setRotation(this.tailfin4, -0.837758f, 0.0f, 0.0f);
        this.tailfin3 = new ModelRenderer(this, 185, 216);
        this.tailfin3.addBox(-4.0f, 0.0f, 1.0f, 8, 2, 23);
        this.tailfin3.setPos(0.0f, -5.0f, 57.0f);
        this.tailfin3.mirror = true;
        this.setRotation(this.tailfin3, -0.5759587f, 0.0f, 0.0f);
        this.tailmembrane1 = new ModelRenderer(this, 245, 38);
        this.tailmembrane1.addBox(0.0f, 0.0f, 4.0f, 0, 11, 21);
        this.tailmembrane1.setPos(0.0f, -9.0f, 56.0f);
        this.tailmembrane1.mirror = true;
        this.setRotation(this.tailmembrane1, 0.1396263f, 0.0f, 0.0f);
        this.topmem1 = new ModelRenderer(this, 25, 0);
        this.topmem1.addBox(0.0f, -25.0f, 0.0f, 0, 24, 10);
        this.topmem1.setPos(0.0f, -15.0f, -6.0f);
        this.topmem1.mirror = true;
        this.setRotation(this.topmem1, -0.2617994f, 0.0f, 0.0f);
        this.topmem2 = new ModelRenderer(this, 135, 0);
        this.topmem2.addBox(1.0f, -22.0f, 0.0f, 0, 20, 10);
        this.topmem2.setPos(-1.0f, -15.0f, -2.0f);
        this.topmem2.mirror = true;
        this.setRotation(this.topmem2, -0.5235988f, 0.0f, 0.0f);
        this.topmem3 = new ModelRenderer(this, 258, 0);
        this.topmem3.addBox(0.0f, -18.0f, 0.0f, 0, 18, 8);
        this.topmem3.setPos(0.0f, -16.0f, 3.0f);
        this.topmem3.mirror = true;
        this.setRotation(this.topmem3, -0.8901179f, 0.0f, 0.0f);
        this.topmem4 = new ModelRenderer(this, 282, 0);
        this.topmem4.addBox(0.0f, -9.0f, 0.0f, 0, 9, 10);
        this.topmem4.setPos(0.0f, -17.0f, 13.0f);
        this.topmem4.mirror = true;
        this.setRotation(this.topmem4, -1.117011f, 0.0f, 0.0f);
        this.neck1 = new ModelRenderer(this, 404, 235);
        this.neck1.addBox(-6.0f, -5.0f, -10.0f, 10, 9, 10);
        this.neck1.setPos(1.0f, -6.0f, -33.0f);
        this.neck1.mirror = true;
        this.setRotation(this.neck1, 0.3665191f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 28, 220);
        this.body.addBox(-6.0f, -11.0f, -10.0f, 12, 15, 19);
        this.body.setPos(0.0f, -7.0f, 3.0f);
        this.body.mirror = true;
        this.setRotation(this.body, 0.1745329f, 0.0f, 0.0f);
        this.chest1 = new ModelRenderer(this, 98, 210);
        this.chest1.addBox(-3.0f, -4.0f, -2.0f, 10, 11, 5);
        this.chest1.setPos(-2.0f, -6.0f, -13.0f);
        this.chest1.mirror = true;
        this.setRotation(this.chest1, 1.029744f, 0.0f, 0.0f);
        this.leftleg1 = new ModelRenderer(this, 135, 183);
        this.leftleg1.addBox(-1.0f, 0.0f, -4.0f, 7, 18, 10);
        this.leftleg1.setPos(7.0f, -14.0f, 17.0f);
        this.leftleg1.mirror = true;
        this.setRotation(this.leftleg1, -0.5759587f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer(this, 92, 150);
        this.mouth.addBox(-7.0f, 1.0f, 3.0f, 14, 15, 4);
        this.mouth.setPos(0.0f, -6.0f, -43.0f);
        this.mouth.mirror = true;
        this.setRotation(this.mouth, -0.8726646f, 0.0f, 0.0f);
        this.neck2 = new ModelRenderer(this, 152, 110);
        this.neck2.addBox(-6.0f, -5.0f, -17.0f, 11, 10, 17);
        this.neck2.setPos(0.5f, -10.0f, -19.0f);
        this.neck2.mirror = true;
        this.setRotation(this.neck2, 0.2617994f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 275, 219);
        this.head.addBox(-10.0f, -3.0f, -16.0f, 20, 7, 16);
        this.head.setPos(0.0f, -6.0f, -43.0f);
        this.head.mirror = true;
        this.setRotation(this.head, 0.5061455f, 0.0f, 0.0f);
        this.hammerhead = new ModelRenderer(this, 258, 134);
        this.hammerhead.addBox(-18.0f, -2.0f, -15.0f, 36, 6, 14);
        this.hammerhead.setPos(0.0f, -6.0f, -43.0f);
        this.hammerhead.mirror = true;
        this.setRotation(this.hammerhead, 0.4537856f, 0.0f, 0.0f);
        this.chest = new ModelRenderer(this, 100, 15);
        this.chest.addBox(-3.0f, -3.0f, 0.0f, 9, 29, 7);
        this.chest.setPos(-1.5f, 0.0f, -5.0f);
        this.chest.mirror = true;
        this.setRotation(this.chest, 1.413717f, 0.0f, 0.0f);
        this.neck3 = new ModelRenderer(this, 264, 173);
        this.neck3.addBox(-6.0f, -5.0f, -16.0f, 12, 11, 16);
        this.neck3.setPos(0.0f, -11.0f, -6.0f);
        this.neck3.mirror = true;
        this.setRotation(this.neck3, 0.0872665f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer(this, 51, 5);
        this.tail1.addBox(-5.0f, -6.0f, 0.0f, 10, 13, 14);
        this.tail1.setPos(0.0f, -10.0f, 22.0f);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, -0.1745329f, 0.0f, 0.0f);
        this.rightleg1 = new ModelRenderer(this, 94, 175);
        this.rightleg1.addBox(-6.0f, 0.0f, -4.0f, 7, 18, 10);
        this.rightleg1.setPos(-7.0f, -14.0f, 17.0f);
        this.rightleg1.mirror = true;
        this.setRotation(this.rightleg1, -0.5759587f, 0.0f, 0.0f);
        this.leftleg2 = new ModelRenderer(this, 28, 112);
        this.leftleg2.addBox(-1.0f, 6.0f, -17.0f, 7, 12, 7);
        this.leftleg2.setPos(7.0f, -14.0f, 17.0f);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.9773844f, 0.0f, 0.0f);
        this.rightleg2 = new ModelRenderer(this, 32, 90);
        this.rightleg2.addBox(-6.0f, 6.0f, -17.0f, 7, 12, 7);
        this.rightleg2.setPos(-7.0f, -14.0f, 17.0f);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.9773844f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 400, 179);
        this.body2.addBox(0.0f, 3.0f, 3.0f, 12, 16, 16);
        this.body2.setPos(-6.0f, -23.0f, 6.0f);
        this.body2.mirror = true;
        this.setRotation(this.body2, -0.1919862f, 0.0f, 0.0f);
        this.leftleg3 = new ModelRenderer(this, 351, 192);
        this.leftleg3.addBox(-1.0f, 17.0f, 10.0f, 7, 17, 6);
        this.leftleg3.setPos(7.0f, -14.0f, 17.0f);
        this.leftleg3.mirror = true;
        this.setRotation(this.leftleg3, -0.5235988f, 0.0f, 0.0f);
        this.rightleg3 = new ModelRenderer(this, 323, 192);
        this.rightleg3.addBox(-6.0f, 17.0f, 10.0f, 7, 17, 6);
        this.rightleg3.setPos(-7.0f, -14.0f, 17.0f);
        this.rightleg3.mirror = true;
        this.setRotation(this.rightleg3, -0.5235988f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer(this, 51, 55);
        this.tail2.addBox(-6.0f, -6.0f, 0.0f, 9, 12, 14);
        this.tail2.setPos(1.5f, -7.0f, 35.0f);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, -0.1396263f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer(this, 105, 52);
        this.tail3.addBox(-5.0f, -6.0f, 0.0f, 8, 11, 14);
        this.tail3.setPos(1.0f, -5.0f, 48.0f);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, -0.1396263f, 0.0f, 0.0f);
        this.tailmembrane3 = new ModelRenderer(this, 155, 38);
        this.tailmembrane3.addBox(0.0f, -10.0f, 0.0f, 0, 10, 18);
        this.tailmembrane3.setPos(0.0f, 2.0f, 56.0f);
        this.tailmembrane3.mirror = true;
        this.setRotation(this.tailmembrane3, -0.837758f, 0.0f, 0.0f);
        this.leftwingfin2 = new ModelRenderer(this, 160, 83);
        this.leftwingfin2.addBox(0.0f, -2.0f, 0.0f, 64, 4, 2);
        this.leftwingfin2.setPos(9.0f, -12.0f, -11.0f);
        this.leftwingfin2.mirror = true;
        this.setRotation(this.leftwingfin2, -0.2617994f, -0.4363323f, 0.0f);
        this.leftwingfin3 = new ModelRenderer(this, 209, 106);
        this.leftwingfin3.addBox(0.0f, -2.0f, 0.0f, 48, 4, 2);
        this.leftwingfin3.setPos(9.0f, -11.0f, -10.0f);
        this.leftwingfin3.mirror = true;
        this.setRotation(this.leftwingfin3, -0.2617994f, -0.7853982f, 0.0f);
        this.leftwingfin4 = new ModelRenderer(this, 233, 120);
        this.leftwingfin4.addBox(0.0f, 0.0f, 0.0f, 37, 4, 2);
        this.leftwingfin4.setPos(9.0f, -13.0f, -6.0f);
        this.leftwingfin4.mirror = true;
        this.setRotation(this.leftwingfin4, -0.2617994f, -1.186824f, 0.0f);
        this.leftwingmembrane = new ModelRenderer(this, 300, 27);
        this.leftwingmembrane.addBox(3.0f, 0.0f, 0.0f, 64, 0, 34);
        this.leftwingmembrane.setPos(9.0f, -13.0f, -10.0f);
        this.leftwingmembrane.mirror = true;
        this.setRotation(this.leftwingmembrane, -0.0872665f, -0.1745329f, 0.0f);
        this.rightshoulder = new ModelRenderer(this, 0, 193);
        this.rightshoulder.addBox(0.0f, 0.0f, 0.0f, 6, 8, 11);
        this.rightshoulder.setPos(-12.0f, -16.0f, -13.0f);
        this.rightshoulder.mirror = true;
        this.setRotation(this.rightshoulder, -0.1745329f, 0.0f, 0.0f);
        this.rightwingfin1 = new ModelRenderer(this, 344, 109);
        this.rightwingfin1.addBox(-69.0f, -2.0f, 0.0f, 69, 5, 3);
        this.rightwingfin1.setPos(-10.0f, -12.0f, -13.0f);
        this.rightwingfin1.mirror = true;
        this.setRotation(this.rightwingfin1, -0.2617994f, 0.1745329f, 0.0f);
        this.rightwingfin2 = new ModelRenderer(this, 349, 119);
        this.rightwingfin2.addBox(-63.0f, -2.0f, 0.0f, 64, 4, 2);
        this.rightwingfin2.setPos(-9.0f, -12.0f, -11.0f);
        this.rightwingfin2.mirror = true;
        this.setRotation(this.rightwingfin2, -0.2617994f, 0.4363323f, 0.0f);
        this.rightwingfin3 = new ModelRenderer(this, 368, 128);
        this.rightwingfin3.addBox(-49.0f, 0.0f, 0.0f, 48, 4, 2);
        this.rightwingfin3.setPos(-9.0f, -13.0f, -9.0f);
        this.rightwingfin3.mirror = true;
        this.setRotation(this.rightwingfin3, -0.2617994f, 0.7679449f, 0.0f);
        this.rightwingfin4 = new ModelRenderer(this, 379, 137);
        this.rightwingfin4.addBox(-35.0f, 0.0f, 0.0f, 37, 4, 2);
        this.rightwingfin4.setPos(-9.0f, -13.0f, -6.0f);
        this.rightwingfin4.mirror = true;
        this.setRotation(this.rightwingfin4, -0.2617994f, 1.186824f, 0.0f);
        this.rightwingmembrane = new ModelRenderer(this, 300, 67);
        this.rightwingmembrane.addBox(-67.0f, -1.0f, 0.0f, 64, 0, 34);
        this.rightwingmembrane.setPos(-9.0f, -12.0f, -12.0f);
        this.rightwingmembrane.mirror = true;
        this.setRotation(this.rightwingmembrane, -0.0872665f, 0.1745329f, 0.0f);
        this.hammerhead2 = new ModelRenderer(this, 258, 157);
        this.hammerhead2.addBox(-25.0f, 0.0f, -14.0f, 50, 4, 7);
        this.hammerhead2.setPos(0.0f, -7.0f, -43.0f);
        this.hammerhead2.mirror = true;
        this.setRotation(this.hammerhead2, 0.4537856f, 0.0f, 0.0f);
    }

    /**
     * Flight AI keeps the hitbox slightly above blocks so {@link Entity#onGround} stays false.
     * When unmounted, treat solid collision within a short column under the feet as "landed" for poses.
     */
    private static boolean cephGroundedForPose(Cephadrome e) {
        if (e.isOnGround()) {
            return true;
        }
        if (!e.getPassengers().isEmpty()) {
            return e.isOnGround();
        }
        World world = e.level;
        if (world == null) {
            return false;
        }
        try {
            double px = e.getX();
            double py = e.getBoundingBox().minY;
            double pz = e.getZ();
            BlockPos.Mutable mp = new BlockPos.Mutable();
            for (int i = 0; i < 18; i++) {
                mp.set(px, py - 0.12 - (double)i * 0.35, pz);
                BlockState st = world.getBlockState(mp);
                if (!st.getCollisionShape(world, mp).isEmpty()) {
                    return true;
                }
            }
        } catch (Throwable t) {
            return false;
        }
        return false;
    }

    @Override
    public void setupAnim(Cephadrome e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing;
        float f1 = limbSwingAmount;
        float f2 = ageInTicks;
        float f3 = netHeadYaw;
        float f4 = headPitch;
        float f5 = 0.0F;
        RenderInfo r = null;
        float newangle = 0.0f;
        float lspeed = 0.0f;
        float pi4 = 0.7853982f;
        float tailspeed = 0.76f;
        float tailamp = 0.1f;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        r = e.getRenderInfo();
        boolean useFlightPose = e.getActivity() != 0 && !cephGroundedForPose(e);
        if ((double)f1 > 0.001) {
            lspeed = (float)((e.xo - e.getX()) * (e.xo - e.getX()) + (e.zo - e.getZ()) * (e.zo - e.getZ()));
            lspeed = (float)Math.sqrt(lspeed);
            newangle = MathHelper.cos((float)(f2 * 0.75f * this.wingspeed)) * 3.1415927f * lspeed * 0.4f;
            if ((double)newangle > 0.5) {
                newangle = 0.75f;
            }
            if ((double)newangle < -0.5) {
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
            this.leftfoot.xRot = - newangle;
        }
        newangle = useFlightPose ? MathHelper.cos((float)(f2 * 0.55f * this.wingspeed)) * 3.1415927f * 0.28f : (e.getAttacking() == 0 ? -0.85f + MathHelper.cos((float)(f2 * 0.2f * this.wingspeed)) * 3.1415927f * 0.028f : -0.65f + MathHelper.cos((float)(f2 * 0.9f * this.wingspeed)) * 3.1415927f * 0.068f);
        this.lefwingfin1.zRot = newangle;
        this.leftwingfin2.zRot = newangle;
        this.leftwingfin3.zRot = newangle;
        this.leftwingfin4.zRot = newangle;
        this.leftwingmembrane.zRot = newangle;
        this.rightwingfin1.zRot = - newangle;
        this.rightwingfin2.zRot = - newangle;
        this.rightwingfin3.zRot = - newangle;
        this.rightwingfin4.zRot = - newangle;
        this.rightwingmembrane.zRot = - newangle;
        newangle = MathHelper.cos((float)(f2 * 0.15f * this.wingspeed)) * 3.1415927f * 0.05f;
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
        this.tail1.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed)) * 3.1415927f * 0.04f;
        this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 13.0f;
        this.tail2.x = this.tail1.x + 1.5f + (float)Math.sin(this.tail1.yRot) * 13.0f;
        this.tail2.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed - pi4)) * 3.1415927f * tailamp;
        this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 13.0f;
        this.tail3.x = this.tail2.x - 0.5f + (float)Math.sin(this.tail2.yRot) * 13.0f;
        this.tail3.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed - 2.0f * pi4)) * 3.1415927f * tailamp;
        this.tailfin1.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 10.0f;
        this.tailfin1.x = this.tail3.x - 1.0f + (float)Math.sin(this.tail3.yRot) * 10.0f;
        this.tailfin1.yRot = MathHelper.cos((float)(f2 * tailspeed * this.wingspeed - 3.0f * pi4)) * 3.1415927f * tailamp;
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
            f3 = (e.yRotO - e.yRot) * 10.0f;
            f3 = - f3;
            r.rf1 += (f3 - r.rf1) / 50.0f;
            if (r.rf1 > 50.0f) {
                r.rf1 = 50.0f;
            }
            if (r.rf1 < -50.0f) {
                r.rf1 = -50.0f;
            }
            f3 = r.rf1;
        } else {
            f3 /= 2.0f;
        }
        this.neck3.yRot = (float)Math.toRadians(f3) * 0.125f;
        this.neck2.z = this.neck3.z - (float)Math.cos(this.neck3.yRot) * 14.0f;
        this.neck2.x = this.neck3.x + 0.5f - (float)Math.sin(this.neck3.yRot) * 14.0f;
        this.neck2.yRot = (float)Math.toRadians(f3) * 0.25f;
        this.neck1.z = this.neck2.z - (float)Math.cos(this.neck2.yRot) * 14.0f;
        this.neck1.x = this.neck2.x + 0.5f - (float)Math.sin(this.neck2.yRot) * 14.0f;
        this.neck1.yRot = (float)Math.toRadians(f3) * 0.5f;
        this.head.z = this.neck1.z - (float)Math.cos(this.neck1.yRot) * 8.0f;
        this.head.x = this.neck1.x - (float)Math.sin(this.neck1.yRot) * 8.0f;
        this.head.yRot = (float)Math.toRadians(f3) * 0.75f;
        this.hammerhead.z = this.head.z;
        this.hammerhead.x = this.head.x;
        this.hammerhead.yRot = this.head.yRot;
        this.hammerhead2.z = this.head.z;
        this.hammerhead2.x = this.head.x;
        this.hammerhead2.yRot = this.head.yRot;
        this.mouth.z = this.head.z;
        this.mouth.x = this.head.x;
        this.mouth.yRot = this.head.yRot;
        newangle = MathHelper.cos((float)(f2 * 0.5f * this.wingspeed)) * 3.1415927f * 0.14f;
        this.mouth.xRot = e.getAttacking() != 0 ? -0.61f + newangle : -0.87f;
        e.setRenderInfo(r);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.leftfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.butt.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightfoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topfin4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.lefwingfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmembrane2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailfin3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmembrane1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.topmem4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hammerhead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.chest.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.neck3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightleg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.tailmembrane3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingfin3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingfin4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftwingmembrane.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightshoulder.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingfin4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightwingmembrane.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.hammerhead2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        
    }
}

