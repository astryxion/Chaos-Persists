package com.astryxion.chaospersists.client.item;

/**
 * 1.12.2 TEISR hand scales that produced the correct giant-weapon size with {@link ModelPart} rendering.
 * CA display {@code scale} values target Blockbench JSON models and must not be applied on top of texel models.
 */
public final class TeisrHandScales {

    private TeisrHandScales() {}

    public static float get(BigWeaponHandTransforms.Style style, boolean firstPerson) {
        if (firstPerson) {
            return switch (style) {
                case BERTHA, SLICE, ROYAL -> 0.19f;
                case HAMMY -> 0.09f;
                case BATTLE_AXE, QUEEN_BATTLE_AXE, CHAINSAW, SQUID_ZOOKA -> 0.18f;
            };
        }
        return switch (style) {
            case BERTHA, SLICE, ROYAL -> 0.24f;
            case HAMMY, BATTLE_AXE, QUEEN_BATTLE_AXE, CHAINSAW, SQUID_ZOOKA -> 0.18f;
        };
    }
}
