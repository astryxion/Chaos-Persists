package com.astryxion.chaospersists.compat.iceandfire;

import java.lang.reflect.Method;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.ModList;

/**
 * Ice and Fire dragon / hydra / cyclops hitboxes are standalone entities with {@code getParent()}.
 * They are not {@link net.minecraft.world.entity.TamableAnimal}, so Ultimate Bow and Royal hits
 * would damage the parent instead of treating a tamed dragon as a pet.
 */
public final class IceAndFireMultipartCompat {

    public static final String ICEANDFIRE_MODID = "iceandfire";

    private static boolean checked;
    private static boolean present;

    private IceAndFireMultipartCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(ICEANDFIRE_MODID);
            checked = true;
        }
        return present;
    }

    @Nullable
    public static Entity parentOf(@Nullable Entity hit) {
        if (hit == null) {
            return null;
        }
        ResourceLocation id = EntityType.getKey(hit.getType());
        if (!isPresent() || id == null || !ICEANDFIRE_MODID.equals(id.getNamespace())) {
            return null;
        }
        if (!id.getPath().contains("multipart")) {
            return null;
        }
        return invokeGetParent(hit);
    }

    @Nullable
    public static Entity invokeGetParent(@Nullable Entity hit) {
        if (hit == null) {
            return null;
        }
        try {
            Method method = hit.getClass().getMethod("getParent");
            Object parent = method.invoke(hit);
            return parent instanceof Entity entity ? entity : null;
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }
}
