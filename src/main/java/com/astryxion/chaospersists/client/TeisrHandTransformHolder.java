package com.astryxion.chaospersists.client;

import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;

/**
 * Holds the current {@link TransformType} between {@link TeisrHandBakedModelWrapper#handlePerspective}
 * and {@link TileEntityItemStackRenderer#renderByItem}.
 */
public final class TeisrHandTransformHolder {

    private static final ThreadLocal<TransformType> CURRENT = new ThreadLocal<>();

    private TeisrHandTransformHolder() {}

    public static void set(TransformType type) {
        CURRENT.set(type);
    }

    public static TransformType get() {
        return CURRENT.get();
    }

    public static void clear() {
        CURRENT.remove();
    }
}
