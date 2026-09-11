package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.compat.iceandfire.IceAndFireMultipartCompat;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import javax.annotation.Nullable;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.entity.PartEntity;

/**
 * Ultimate Bow / Royal Guardian (and other Bertha weapons) skip damage on tamed pets. Ice and Fire
 * dragons take hits on body-part entities that are not tamed, so those parts must resolve to the
 * parent dragon.
 */
public final class FriendlyWeaponHits {

    public static final TagKey<EntityType<?>> MULTIPART_HITBOXES =
            TagKey.create(
                    Registries.ENTITY_TYPE,
                    new ResourceLocation(ChaosPersists.MODID, "multipart_hitboxes"));
    public static final TagKey<EntityType<?>> FRIENDLY_WEAPON_IGNORE =
            TagKey.create(
                    Registries.ENTITY_TYPE,
                    new ResourceLocation(ChaosPersists.MODID, "friendly_weapon_ignore"));

    private FriendlyWeaponHits() {}

    public static Entity root(@Nullable Entity hit) {
        if (hit == null) {
            return null;
        }
        if (hit instanceof PartEntity<?> part) {
            Entity parent = part.getParent();
            if (parent != null) {
                return parent;
            }
        }
        Entity iaf = IceAndFireMultipartCompat.parentOf(hit);
        if (iaf != null) {
            return iaf;
        }
        if (hit.getType().is(MULTIPART_HITBOXES)) {
            Entity tagged = IceAndFireMultipartCompat.invokeGetParent(hit);
            if (tagged != null) {
                return tagged;
            }
        }
        return hit;
    }

    public static boolean isListedIgnore(@Nullable Entity hit) {
        if (hit == null) {
            return false;
        }
        Entity root = root(hit);
        return hit.getType().is(FRIENDLY_WEAPON_IGNORE)
                || (root != null && root.getType().is(FRIENDLY_WEAPON_IGNORE));
    }

    public static boolean isCompanion(@Nullable Entity hit) {
        Entity e = root(hit);
        return e instanceof Girlfriend || e instanceof Boyfriend;
    }

    /** When PvP-off flags are set: players, companions, tamed animals, ignore-tag, tamed multipart parents. */
    public static boolean isFriendlyWhenPvpOff(@Nullable Entity hit) {
        if (hit == null) {
            return false;
        }
        if (isListedIgnore(hit) || isCompanion(hit)) {
            return true;
        }
        Entity e = root(hit);
        if (e instanceof Player) {
            return true;
        }
        return e instanceof TamableAnimal tame && tame.isTame();
    }

    @Nullable
    public static LivingEntity healTarget(@Nullable Entity hit) {
        Entity e = root(hit);
        return e instanceof LivingEntity living ? living : null;
    }
}
