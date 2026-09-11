package com.astryxion.chaospersists.legacy.forge.fml.common.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

/** Builds 1.20 recipes from legacy 1.12 {@link GameRegistry} recipe arrays. */
final class LegacyRecipeBuilder {

  private LegacyRecipeBuilder() {}

  static Recipe<?> buildShaped(
      ResourceLocation id, ResourceLocation group, ItemStack output, Object... recipe) {
    List<String> rows = new ArrayList<>();
    Map<Character, Ingredient> keys = new HashMap<>();
    for (int i = 0; i < recipe.length; ) {
      Object o = recipe[i];
      if (o instanceof String row) {
        rows.add(row);
        i++;
      } else if (o instanceof Character ch) {
        if (i + 1 >= recipe.length) {
          break;
        }
        keys.put(ch, ingredientFrom(recipe[i + 1]));
        i += 2;
      } else {
        i++;
      }
    }
    int height = rows.size();
    int width = 0;
    for (String row : rows) {
      width = Math.max(width, row.length());
    }
    NonNullList<Ingredient> ingredients =
        NonNullList.withSize(width * height, Ingredient.EMPTY);
    for (int y = 0; y < height; y++) {
      String row = rows.get(y);
      for (int x = 0; x < width; x++) {
        char ch = x < row.length() ? row.charAt(x) : ' ';
        if (ch != ' ') {
          Ingredient ing = keys.get(ch);
          if (ing != null) {
            ingredients.set(y * width + x, ing);
          }
        }
      }
    }
    return new ShapedRecipe(
        id, group.getPath(), CraftingBookCategory.MISC, width, height, ingredients, output, false);
  }

  static Recipe<?> buildShapeless(
      ResourceLocation id, ResourceLocation group, ItemStack output, Ingredient... ingredients) {
    NonNullList<Ingredient> list = NonNullList.create();
    for (Ingredient ingredient : ingredients) {
      list.add(ingredient);
    }
    return new ShapelessRecipe(
        id, group.getPath(), CraftingBookCategory.MISC, output, list);
  }

  static Ingredient ingredientFrom(Object in) {
    if (in instanceof Ingredient ingredient) {
      return ingredient;
    }
    if (in instanceof ItemStack stack) {
      return Ingredient.of(stack);
    }
    if (in instanceof ItemLike like) {
      return Ingredient.of(like);
    }
    if (in instanceof Item item) {
      return Ingredient.of(item);
    }
    if (in instanceof Block block) {
      return Ingredient.of(block);
    }
    return Ingredient.EMPTY;
  }
}
