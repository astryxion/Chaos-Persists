package com.astryxion.chaospersists.legacy.forge.fml.common.registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

/** Merges legacy queued recipes into a loaded {@link RecipeManager} (1.12 GameRegistry behavior). */
final class LegacyRecipeInjector {

  private LegacyRecipeInjector() {}

  static void inject(RecipeManager manager, List<net.minecraft.world.item.crafting.RecipeHolder<?>> recipes) {
    if (manager == null || recipes == null || recipes.isEmpty()) {
      return;
    }
    Map<ResourceLocation, net.minecraft.world.item.crafting.RecipeHolder<?>> byId = new LinkedHashMap<>();
    for (net.minecraft.world.item.crafting.RecipeHolder<?> existing : manager.getRecipes()) {
      if (existing != null && existing.id() != null) {
        byId.put(existing.id(), existing);
      }
    }
    for (net.minecraft.world.item.crafting.RecipeHolder<?> recipe : recipes) {
      if (recipe != null && recipe.id() != null) {
        // Datapack/JSON recipes win. Java GameRegistry entries are defaults only.
        byId.putIfAbsent(recipe.id(), recipe);
      }
    }
    manager.replaceRecipes(new ArrayList<>(byId.values()));
  }

  static List<net.minecraft.world.item.crafting.RecipeHolder<?>> drainPending(
      List<GameRegistry.RecipeEntry> entries,
      List<GameRegistry.SmeltEntry> smelts,
      List<GameRegistry.SmeltEntry> smoking) {
    List<net.minecraft.world.item.crafting.RecipeHolder<?>> out = new ArrayList<>();
    for (GameRegistry.RecipeEntry entry : entries) {
      out.add(entry.build());
    }
    for (GameRegistry.SmeltEntry entry : smelts) {
      out.add(entry.build());
    }
    for (GameRegistry.SmeltEntry entry : smoking) {
      out.add(entry.buildSmoking());
    }
    return out;
  }
}
