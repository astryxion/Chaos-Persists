package com.astryxion.chaospersists.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.block.model.ItemTransform;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * CA rotation and translation from {@code models/custom/*.json}.
 * Display {@code scale} is omitted here; {@link TeisrHandScales} sizes legacy {@link net.minecraft.client.model.geom.ModelPart} weapons.
 */
public final class CaWeaponDisplayTransforms {

    private static final float UNIT = 0.0625f;

    private CaWeaponDisplayTransforms() {}

    /**
     * Exact CA angles and translation ratios. Offsets are scaled by {@code teisrScale / caScale}
     * so hand pose matches CA while the rendered model stays texel-sized.
     */
    public static void applyPositioning(
            PoseStack poseStack, BigWeaponHandTransforms.Style style, boolean firstPerson, boolean leftHand) {
        ItemTransform transform = get(style, firstPerson, leftHand);
        float teisrScale = TeisrHandScales.get(style, firstPerson);
        applyPositioning(poseStack, transform, style, firstPerson, leftHand, teisrScale);
    }

    public static ItemTransform get(BigWeaponHandTransforms.Style style, boolean firstPerson, boolean leftHand) {
        if (firstPerson) {
            return leftHand ? firstPersonLeft(style) : firstPersonRight(style);
        }
        return leftHand ? thirdPersonLeft(style) : thirdPersonRight(style);
    }

    private static boolean usesCustomFirstPersonPose(BigWeaponHandTransforms.Style style, boolean firstPerson) {
        return firstPerson && (style == BigWeaponHandTransforms.Style.CHAINSAW
                || style == BigWeaponHandTransforms.Style.SQUID_ZOOKA);
    }

    /** Squid Zooka left hand uses custom Java poses; generic CA left mirror breaks both FP and TP. */
    private static boolean skipLeftHandMirror(
            BigWeaponHandTransforms.Style style, boolean firstPerson, boolean leftHand) {
        return leftHand && style == BigWeaponHandTransforms.Style.SQUID_ZOOKA;
    }

    private static void applyPositioning(
            PoseStack poseStack,
            ItemTransform transform,
            BigWeaponHandTransforms.Style style,
            boolean firstPerson,
            boolean leftHand,
            float teisrScale) {
        if (transform == ItemTransform.NO_TRANSFORM) {
            return;
        }
        if (leftHand && !usesCustomFirstPersonPose(style, firstPerson) && !skipLeftHandMirror(style, firstPerson, leftHand)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
            poseStack.scale(-1.0f, 1.0f, 1.0f);
        }
        float tx = transform.translation.x() * teisrScale / transform.scale.x();
        float ty = transform.translation.y() * teisrScale / transform.scale.y();
        float tz = transform.translation.z() * teisrScale / transform.scale.z();
        poseStack.translate(tx, ty, tz);
        poseStack.mulPose(
                new Quaternionf().rotationXYZ(
                        (float) Math.toRadians(transform.rotation.x()),
                        (float) Math.toRadians(transform.rotation.y()),
                        (float) Math.toRadians(transform.rotation.z())));
    }

    private static ItemTransform t(float[] rot, float[] trans, float[] scale) {
        return new ItemTransform(
                new Vector3f(rot[0], rot[1], rot[2]),
                new Vector3f(trans[0] * UNIT, trans[1] * UNIT, trans[2] * UNIT),
                new Vector3f(scale[0], scale[1], scale[2]));
    }

    private static ItemTransform t(float[] rot, float[] trans) {
        return t(rot, trans, new float[] {1.0f, 1.0f, 1.0f});
    }

    private static boolean isSword(BigWeaponHandTransforms.Style style) {
        return style == BigWeaponHandTransforms.Style.BERTHA
                || style == BigWeaponHandTransforms.Style.SLICE
                || style == BigWeaponHandTransforms.Style.ROYAL;
    }

    private static boolean isBattleAxe(BigWeaponHandTransforms.Style style) {
        return style == BigWeaponHandTransforms.Style.BATTLE_AXE
                || style == BigWeaponHandTransforms.Style.QUEEN_BATTLE_AXE;
    }

    /** OreSpawn texel hammer faces opposite CA Blockbench model; flip X in FP, Z in TP. */
    private static float[] hammyRotation(BigWeaponHandTransforms.Style style, boolean firstPerson, float[] rot) {
        if (style != BigWeaponHandTransforms.Style.HAMMY) {
            return rot;
        }
        if (firstPerson) {
            return new float[] {rot[0] + 180.0f, rot[1], rot[2]};
        }
        return new float[] {rot[0], rot[1], rot[2] + 180.0f};
    }

    /** OreSpawn texel axes face opposite CA Blockbench models; flip X in FP and Z in TP. */
    private static float[] battleAxeRotation(BigWeaponHandTransforms.Style style, boolean firstPerson, float[] rot) {
        if (!isBattleAxe(style)) {
            return rot;
        }
        if (firstPerson) {
            return new float[] {rot[0] + 180.0f, rot[1], rot[2]};
        }
        return new float[] {rot[0], rot[1], rot[2] + 180.0f};
    }

    /** OreSpawn texel swords face opposite CA Blockbench blades; flip X in FP and Z in TP. */
    private static float[] swordRotation(BigWeaponHandTransforms.Style style, boolean firstPerson, float[] rot) {
        if (!isSword(style)) {
            return rot;
        }
        if (firstPerson) {
            return new float[] {rot[0] + 180.0f, rot[1], rot[2]};
        }
        return new float[] {rot[0], rot[1], rot[2] + 180.0f};
    }

    /** OreSpawn texel chainsaw faces opposite CA Blockbench blade; flip X in FP and Z in TP. */
    private static float[] chainsawRotation(boolean firstPerson, float[] rot) {
        if (firstPerson) {
            return new float[] {rot[0] + 180.0f, rot[1], rot[2]};
        }
        return new float[] {rot[0], rot[1], rot[2] + 180.0f};
    }

    private static ItemTransform thirdPersonRight(BigWeaponHandTransforms.Style style) {
        return switch (style) {
            case BERTHA -> t(swordRotation(style, false, new float[] {-6, -90, 0}), new float[] {0, 21.25f, -7.25f}, new float[] {1.5f, 1.5f, 1.5f});
            case ROYAL -> t(swordRotation(style, false, new float[] {80, -90, 90}), new float[] {0, 29, -12.25f}, new float[] {2, 1.8f, 2});
            case SLICE -> t(swordRotation(style, false, new float[] {80, -90, 81}), new float[] {0, 20.75f, -4.75f}, new float[] {1.5f, 1.4f, 1.5f});
            case HAMMY -> t(hammyRotation(style, false, new float[] {20, 0, 0}), new float[] {-0.5f, 24, 1.25f}, new float[] {1, 1.3f, 1});
            case BATTLE_AXE -> t(battleAxeRotation(style, false, new float[] {20, 0, 0}), new float[] {-0.5f, 24, 1.25f}, new float[] {1, 1.3f, 1});
            case QUEEN_BATTLE_AXE -> t(battleAxeRotation(style, false, new float[] {20, 0, 0}), new float[] {-0.5f, 39, 0.75f}, new float[] {1.5f, 2, 1.5f});
            case CHAINSAW -> t(chainsawRotation(false, new float[] {-6, -90, 0}), new float[] {0, 21.25f, -7.25f}, new float[] {1.5f, 1.5f, 1.5f});
            case SQUID_ZOOKA -> t(new float[] {68.25f, 0, 0}, new float[] {0, 2.5f, 6.25f}, new float[] {1, 1, 1.35938f});
        };
    }

    private static ItemTransform thirdPersonLeft(BigWeaponHandTransforms.Style style) {
        return switch (style) {
            case BERTHA -> t(swordRotation(style, false, new float[] {-6, -90, 0}), new float[] {0, 21.25f, 7.25f}, new float[] {1.5f, 1.5f, 1.5f});
            case ROYAL -> t(swordRotation(style, false, new float[] {80, -90, 90}), new float[] {0, 31.25f, 6}, new float[] {2, 2, 2});
            case SLICE -> t(swordRotation(style, false, new float[] {80, -90, 81}), new float[] {0, 18.75f, 9}, new float[] {1.5f, 1.3f, 1.5f});
            case HAMMY -> t(hammyRotation(style, false, new float[] {20, 0, 0}), new float[] {0.5f, 24.25f, 1.25f}, new float[] {1, 1.3f, 1});
            case BATTLE_AXE -> t(battleAxeRotation(style, false, new float[] {20, 0, 0}), new float[] {0.5f, 24.25f, 1.25f}, new float[] {1, 1.3f, 1});
            case QUEEN_BATTLE_AXE -> t(battleAxeRotation(style, false, new float[] {20, 0, 0}), new float[] {0.5f, 39.25f, 0.75f}, new float[] {1.5f, 2, 1.5f});
            case CHAINSAW -> t(chainsawRotation(false, new float[] {-6, -90, 0}), new float[] {0, 21.25f, 7.25f}, new float[] {1.5f, 1.5f, 1.5f});
            case SQUID_ZOOKA -> t(new float[] {68.25f, 0, 0}, new float[] {0, 2.5f, -6.25f}, new float[] {1, 1, 1.35938f});
        };
    }

    private static ItemTransform firstPersonRight(BigWeaponHandTransforms.Style style) {
        return switch (style) {
            case BERTHA -> t(swordRotation(style, true, new float[] {0, 90, 0}), new float[] {7.5f, 5.5f, -1.5f});
            case ROYAL -> t(swordRotation(style, true, new float[] {0, -90, 0}), new float[] {4.25f, 10.75f, -9.5f});
            case SLICE -> t(swordRotation(style, true, new float[] {0, -88, 0}), new float[] {8, 7.75f, -13.25f}, new float[] {1.3f, 1.2f, 1.3f});
            case HAMMY -> t(hammyRotation(style, true, new float[] {-8, 0, -7}), new float[] {0, 0, 0});
            case BATTLE_AXE -> t(battleAxeRotation(style, true, new float[] {-7, 0, 0}), new float[] {1.5f, 9.75f, -9.75f});
            case QUEEN_BATTLE_AXE -> t(battleAxeRotation(style, true, new float[] {-7, 0, 0}), new float[] {1.5f, 9.75f, -9.75f});
            case CHAINSAW -> t(new float[] {0, 0, 0}, new float[] {0, 0, 0});
            case SQUID_ZOOKA -> t(new float[] {0, 0, 0}, new float[] {0, 0, 0});
        };
    }

    private static ItemTransform firstPersonLeft(BigWeaponHandTransforms.Style style) {
        return switch (style) {
            case BERTHA -> t(swordRotation(style, true, new float[] {0, 90, 0}), new float[] {8.5f, 5.5f, -11.75f});
            case ROYAL -> t(swordRotation(style, true, new float[] {0, -90, 0}), new float[] {5.5f, 9, -1.25f});
            case SLICE -> t(swordRotation(style, true, new float[] {172.91f, -88, 172.83f}), new float[] {5.75f, 11, -0.75f}, new float[] {1.3f, 1.2f, 1.3f});
            case HAMMY -> t(hammyRotation(style, true, new float[] {-5, 0, -5}), new float[] {0, 0, 0});
            case BATTLE_AXE -> t(battleAxeRotation(style, true, new float[] {-7, 0, 0}), new float[] {6, 10, -10.25f});
            case QUEEN_BATTLE_AXE -> t(battleAxeRotation(style, true, new float[] {-7, 0, 0}), new float[] {6, 10, -10.25f});
            case CHAINSAW -> t(new float[] {0, 0, 0}, new float[] {0, 0, 0});
            case SQUID_ZOOKA -> t(new float[] {0, 0, 0}, new float[] {0, 0, 0});
        };
    }
}
