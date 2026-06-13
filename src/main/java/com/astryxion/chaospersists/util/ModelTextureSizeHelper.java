package com.astryxion.chaospersists.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Applies legacy 1.12 texture atlas dimensions to {@link ModelRenderer} parts (UV layout).
 */
public final class ModelTextureSizeHelper {

    private ModelTextureSizeHelper() {
    }

    public static void apply(Object modelRoot, int width, int height) {
        if (modelRoot == null) {
            return;
        }
        Set<Object> visited = new HashSet<Object>();
        applyRecursive(modelRoot, width, height, visited);
    }

    private static void applyRecursive(Object obj, int width, int height, Set<Object> visited) {
        if (obj == null || !visited.add(obj)) {
            return;
        }
        setTextureFields(obj, width, height);
        Class<?> type = obj.getClass();
        while (type != null && type != Object.class) {
            for (Field field : type.getDeclaredFields()) {
                if (!ModelRenderer.class.isAssignableFrom(field.getType())) {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    Object part = field.get(obj);
                    if (part instanceof ModelRenderer) {
                        applyRecursive(part, width, height, visited);
                    }
                } catch (ReflectiveOperationException ignored) {
                    // skip inaccessible parts
                }
            }
            type = type.getSuperclass();
        }
    }

    private static void setTextureFields(Object obj, int width, int height) {
        setFloat(obj, "textureWidth", width);
        setFloat(obj, "textureHeight", height);
        setFloat(obj, "texWidth", width);
        setFloat(obj, "texHeight", height);
    }

    private static void setFloat(Object obj, String name, float value) {
        Class<?> type = obj.getClass();
        while (type != null && type != Object.class) {
            try {
                Field field = type.getDeclaredField(name);
                field.setAccessible(true);
                field.setFloat(obj, value);
                return;
            } catch (ReflectiveOperationException ignored) {
                type = type.getSuperclass();
            }
        }
    }
}
