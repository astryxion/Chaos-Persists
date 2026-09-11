package com.astryxion.chaospersists.compat.jei;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import com.astryxion.chaospersists.legacy.forge.common.CreativeTabCompat;

/**
 * Hides {@code chaospersists} stacks in JEI that do not appear on the mod's creative tabs.
 * Relies on {@code ChaosPersists.load} having already run {@code applyChaosCreativeTabs()}.
 *
 * <p>JEI discovers this via {@link JeiPlugin}; it is never loaded when JEI is absent.
 */
@JeiPlugin
public class JeiChaosPlugin implements IModPlugin {

  @Override
  public ResourceLocation getPluginUid() {
    return new ResourceLocation(ChaosPersists.MODID, "jei");
  }

  @Override
  public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    IIngredientManager ingredientManager = jeiRuntime.getIngredientManager();
    List<ItemStack> allowed = new ArrayList<>();
    for (Item item : BuiltInRegistries.ITEM) {
      ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item);
      if (rl == null || !ChaosPersists.MODID.equals(rl.getNamespace())) {
        continue;
      }
      CreativeModeTab tab = CreativeTabCompat.getCreativeTab(item);
      if (tab == null) {
        continue;
      }
      allowed.add(new ItemStack(item));
    }

    Collection<ItemStack> allItemIngredients = ingredientManager.getAllIngredients(VanillaTypes.ITEM_STACK);
    List<ItemStack> toRemove = new ArrayList<>();
    for (ItemStack stack : allItemIngredients) {
      if (stack.isEmpty()) {
        continue;
      }
      Item item = stack.getItem();
      ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item);
      if (rl == null || !ChaosPersists.MODID.equals(rl.getNamespace())) {
        continue;
      }
      if (CreativeTabCompat.getCreativeTab(item) == null) {
        toRemove.add(stack.copy());
        continue;
      }
      if (!stackListContains(allowed, stack)) {
        toRemove.add(stack.copy());
      }
    }
    if (!toRemove.isEmpty()) {
      ingredientManager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, toRemove);
    }
  }

  private static boolean stackListContains(List<ItemStack> list, ItemStack candidate) {
    if (candidate.isEmpty()) {
      return false;
    }
    for (ItemStack s : list) {
      if (s.isEmpty()) {
        continue;
      }
      if (ItemStack.isSameItem(s, candidate) && ItemStack.matches(s, candidate)) {
        return true;
      }
    }
    return false;
  }
}
