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

  static void inject(RecipeManager manager, List<Recipe<?>> recipes) {
    if (manager == null || recipes == null || recipes.isEmpty()) {
      return;
    }
    Map<ResourceLocation, Recipe<?>> byId = new LinkedHashMap<>();
    for (Recipe<?> existing : manager.getRecipes()) {
      if (existing != null && existing.getId() != null) {
        byId.put(existing.getId(), existing);
      }
    }
    for (Recipe<?> recipe : recipes) {
      if (recipe != null && recipe.getId() != null) {
        byId.put(recipe.getId(), recipe);
      }
    }
    manager.replaceRecipes(new ArrayList<>(byId.values()));
  }

  static List<Recipe<?>> drainPending(
      List<GameRegistry.RecipeEntry> entries,
      List<GameRegistry.SmeltEntry> smelts,
      List<GameRegistry.SmeltEntry> smoking) {
    List<Recipe<?>> out = new ArrayList<>();
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
