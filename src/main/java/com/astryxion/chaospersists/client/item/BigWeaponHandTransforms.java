package com.astryxion.chaospersists.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

/**
 * CA rotation/translation for hand pose on legacy OreSpawn {@link net.minecraft.client.model.geom.ModelPart} weapons.
 * CA display scale targets Blockbench JSON; {@link TeisrHandScales} keeps the correct in-hand size.
 */
public final class BigWeaponHandTransforms {

    private static final float TEXEL_TO_LEGACY_UNIT = 16.0f;

    public enum Style {
        BERTHA,
        HAMMY,
        SLICE,
        ROYAL,
        BATTLE_AXE,
        QUEEN_BATTLE_AXE,
        CHAINSAW,
        SQUID_ZOOKA
    }

    private BigWeaponHandTransforms() {}

    public static void apply(PoseStack poseStack, Style style, boolean firstPerson, boolean leftHand) {
        poseStack.translate(0.5f, 0.5f, 0.5f);
        CaWeaponDisplayTransforms.applyPositioning(poseStack, style, firstPerson, leftHand);
        float teisrScale = TeisrHandScales.get(style, firstPerson);
        if (isSword(style) && firstPerson) {
            applyFirstPersonSwordScreenDrop(poseStack, teisrScale);
            applyFirstPersonSwordLean(poseStack, style, leftHand);
        }
        if (isSword(style) && !firstPerson) {
            applyThirdPersonSwordEdgeRoll(poseStack);
            applyThirdPersonSwordGripShift(poseStack, teisrScale);
        }
        if (isBattleAxe(style) && !firstPerson) {
            applyThirdPersonSwordEdgeRoll(poseStack);
        }
        if (style == Style.HAMMY && !firstPerson) {
            applyThirdPersonSwordEdgeRoll(poseStack);
            applyThirdPersonHammyGripShift(poseStack, teisrScale);
        }
        if (style == Style.SQUID_ZOOKA && !firstPerson) {
            applyThirdPersonSquidZookaLift(poseStack, teisrScale);
        }
        if (style == Style.CHAINSAW && firstPerson) {
            applyFirstPersonChainsawPose(poseStack, teisrScale);
        }
        if (style == Style.SQUID_ZOOKA && firstPerson) {
            applyFirstPersonSquidZookaPose(poseStack, teisrScale, leftHand);
        }
        if (style == Style.CHAINSAW && !firstPerson) {
            applyThirdPersonSwordEdgeRoll(poseStack);
            applyThirdPersonSwordGripShift(poseStack, teisrScale);
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0f));
            poseStack.translate(-0.5f * teisrScale, 0.0f, -2.5f * teisrScale);
        }
        float modelScale = TEXEL_TO_LEGACY_UNIT * teisrScale;
        poseStack.scale(modelScale, modelScale, modelScale);
    }

    public static boolean isSword(Style style) {
        return style == Style.BERTHA || style == Style.SLICE || style == Style.ROYAL;
    }

    private static boolean isBattleAxe(Style style) {
        return style == Style.BATTLE_AXE || style == Style.QUEEN_BATTLE_AXE;
    }

    /**
     * Pull FP swords down after CA rotation so the blade fills the view instead of the hilt.
     * Orientation stays on the +180 X texel-model fix; this is translation only.
     */
    private static void applyFirstPersonSwordScreenDrop(PoseStack poseStack, float teisrScale) {
        // +180 X flips local Y, so positive Y here moves the model down on screen.
        poseStack.translate(0.0f, 6.7f * teisrScale, 0.0f);
    }

    /** Roll FP swords outward so the blade leans off the screen edge instead of blocking center view. */
    private static void applyFirstPersonSwordLean(PoseStack poseStack, Style style, boolean leftHand) {
        float lean = leftHand ? -20.0f : 20.0f;
        // Bertha's CA yaw is +90 vs -90 for Slice/Royal, so its lean axis is mirrored.
        if (style == Style.BERTHA) {
            lean = -lean;
        }
        poseStack.mulPose(Axis.ZP.rotationDegrees(lean));
    }

    /** 1.12.2 {@code ChainsawItemStackRenderer#applyHandFirstPersonTransforms}. */
    private static void applyFirstPersonChainsawPose(PoseStack poseStack, float teisrScale) {
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
        poseStack.mulPose(Axis.XP.rotationDegrees(110.0f));
        poseStack.translate(0.8f * teisrScale, -0.2f * teisrScale, 0.2f * teisrScale);
    }

    /**
     * OreSpawn 1.7.10 {@code RenderSquidZooka#renderSword}: Y-30 with translate (4,2,2) at scale 0.35.
     * 1.20.1 hand matrix needs tail flip and pitch, but Y-X-Y stacking rolls the barrel — collapse to one yaw + one pitch.
     */
    private static void applyFirstPersonSquidZookaPose(PoseStack poseStack, float teisrScale, boolean leftHand) {
        float oreSpawnScale = 0.35f;
        float modelScale = TEXEL_TO_LEGACY_UNIT * teisrScale;
        float unit = oreSpawnScale / modelScale;

        // OreSpawn uses one FP transform; 1.20.1 left-hand context mirrors the hand matrix.
        poseStack.mulPose(Axis.YP.rotationDegrees(118.0f));
        poseStack.mulPose(Axis.XP.rotationDegrees(20.0f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-6.0f));
        poseStack.translate(6.5f * unit, 4.5f * unit, 5.5f * unit);
    }

    /** Grip-only offset after battle-axe CA rotation + edge roll; keeps palm alignment on the long handle. */
    private static void applyThirdPersonHammyGripShift(PoseStack poseStack, float teisrScale) {
        poseStack.translate(0.5f * teisrScale, -10.4f * teisrScale, 0.0f);
    }

    /** CA third-person Squid Zooka sits low on the texel model; nudge up into the hand. */
    private static void applyThirdPersonSquidZookaLift(PoseStack poseStack, float teisrScale) {
        poseStack.translate(0.0f, 4.5f * teisrScale, 0.0f);
    }

    /**
     * Roll around the blade long axis (model Y) so the texel blade sits edge-on.
     * X/Z tilts pitch the sword forward or down; only Y rolls it in place.
     */
    private static void applyThirdPersonSwordEdgeRoll(PoseStack poseStack) {
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
    }

    /**
     * Slide TP swords along the handle axis so more grip sits behind the hand.
     * Uses local Y after the edge roll — no extra rotation or sideways offset.
     */
    private static void applyThirdPersonSwordGripShift(PoseStack poseStack, float teisrScale) {
        // Left-hand CA mirror already flips axes; same sign as right hand for matching grip height.
        poseStack.translate(0.0f, 2.0f * teisrScale, 0.0f);
    }

    public static Style fromStaticStyle(StaticBigWeaponItemStackRenderer.Style style) {
        return switch (style) {
            case BERTHA -> Style.BERTHA;
            case HAMMY -> Style.HAMMY;
            case SLICE -> Style.SLICE;
            case ROYAL -> Style.ROYAL;
            case BATTLE_AXE -> Style.BATTLE_AXE;
            case QUEEN_BATTLE_AXE -> Style.QUEEN_BATTLE_AXE;
            case SQUID_ZOOKA -> Style.SQUID_ZOOKA;
        };
    }
}
