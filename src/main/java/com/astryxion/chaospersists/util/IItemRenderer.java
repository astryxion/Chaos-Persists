package com.astryxion.chaospersists.util;

import net.minecraft.item.ItemStack;

/**
 * 1.12.2 compat: Forge's IItemRenderer was removed. Local interface for custom item renderers.
 */
public interface IItemRenderer {

    enum ItemRenderType {
        EQUIPPED_FIRST_PERSON,
        EQUIPPED,
        INVENTORY,
        ENTITY,
        FIRST_PERSON_MAP
    }

    enum ItemRendererHelper {
        BLOCK_3D,
        ENTITY_BOBBING,
        ENTITY_ROTATION,
        EQUIPPED_BLOCK,
        INVENTORY_BLOCK,
        ROTATE_BLOCK
    }

    boolean handleRenderType(ItemStack item, ItemRenderType type);

    boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper);

    void renderItem(ItemRenderType type, ItemStack item, Object... data);
}
