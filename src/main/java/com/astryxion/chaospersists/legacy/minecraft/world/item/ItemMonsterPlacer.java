package com.astryxion.chaospersists.legacy.minecraft.world.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * Legacy spawn-egg helper ({@code ItemMonsterPlacer.applyEntityIdToItemStack}) for recipe code.
 */
public final class ItemMonsterPlacer {

  private ItemMonsterPlacer() {}

  public static void applyEntityIdToItemStack(ItemStack stack, ResourceLocation entityId) {
    CompoundTag entityTag = new CompoundTag();
    entityTag.putString("id", entityId.toString());
    CompoundTag stackTag = stack.getOrCreateTag();
    stackTag.put("EntityTag", entityTag);
  }
}
