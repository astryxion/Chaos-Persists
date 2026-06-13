package com.astryxion.chaospersists.integration.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.astryxion.chaospersists.core.ChaosPersists;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Hides {@code chaospersists} stacks in JEI that do not appear on the mod's creative tabs.
 * Relies on {@code ChaosPersists.load} having already run {@code applyChaosCreativeTabs()}.
 */
@JeiPlugin
public class JeiChaosPlugin implements IModPlugin {

  @Override
  public ResourceLocation getPluginUid() {
    return new ResourceLocation("chaospersists", "jei");
  }

  @Override
  public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    IIngredientManager ingredientManager = jeiRuntime.getIngredientManager();
    IIngredientType<ItemStack> itemType = VanillaTypes.ITEM;

    NonNullList<ItemStack> allowed = NonNullList.create();
    for (Item item : ForgeRegistries.ITEMS) {
      if (item == null) {
        continue;
      }
      ResourceLocation rl = item.getRegistryName();
      if (rl == null || !"chaospersists".equals(rl.getNamespace())) {
        continue;
      }
      ItemGroup tab = ChaosPersists.resolveChaosCreativeTab(item);
      if (tab == null) {
        continue;
      }
      item.fillItemCategory(tab, allowed);
    }

    List<ItemStack> toRemove = new ArrayList<>();
    Collection<ItemStack> allItemIngredients = ingredientManager.getAllIngredients(itemType);
    for (ItemStack stack : allItemIngredients) {
      if (stack == null || stack.isEmpty()) {
        continue;
      }
      Item item = stack.getItem();
      ResourceLocation rl = item.getRegistryName();
      if (rl == null || !"chaospersists".equals(rl.getNamespace())) {
        continue;
      }
      if (ChaosPersists.resolveChaosCreativeTab(item) == null) {
        toRemove.add(stack.copy());
        continue;
      }
      if (!stackListContains(allowed, stack)) {
        toRemove.add(stack.copy());
      }
    }
    if (!toRemove.isEmpty()) {
      ingredientManager.removeIngredientsAtRuntime(itemType, toRemove);
    }
  }

  private static boolean stackListContains(NonNullList<ItemStack> list, ItemStack candidate) {
    if (candidate == null || candidate.isEmpty()) {
      return false;
    }
    for (ItemStack s : list) {
      if (s.isEmpty()) {
        continue;
      }
      if (ItemStack.isSame(s, candidate) && ItemStack.tagMatches(s, candidate)) {
        return true;
      }
    }
    return false;
  }
}
