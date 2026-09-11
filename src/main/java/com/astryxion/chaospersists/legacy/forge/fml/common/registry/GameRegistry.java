package com.astryxion.chaospersists.legacy.forge.fml.common.registry;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.RegisterEvent;

/**
 * Legacy 1.12 {@code GameRegistry} — defers recipes/blocks until registry/reload (1:1 behavior).
 */
@Mod.EventBusSubscriber(modid = "chaospersists", bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GameRegistry {

  static final List<RecipeEntry> REGISTERED_RECIPES = new ArrayList<>();
  static final List<SmeltEntry> REGISTERED_SMELTING = new ArrayList<>();
  static final List<SmeltEntry> REGISTERED_SMOKING = new ArrayList<>();
  static final List<RecipeEntry> PENDING_RECIPES = REGISTERED_RECIPES;
  static final List<SmeltEntry> PENDING_SMELTING = REGISTERED_SMELTING;
  private static final List<Block> PENDING_BLOCKS = new ArrayList<>();
  private static int smeltRecipeCounter = 0;
  private static int smokeRecipeCounter = 0;

  private GameRegistry() {}

  /** Legacy 1.12 {@code GameRegistry.findRegistry} — defers block registration only. */
  public interface LegacyRegistrySink<T> {
    void register(T value);
  }

  @SuppressWarnings("unchecked")
  public static <T> LegacyRegistrySink<T> findRegistry(Class<T> clazz) {
    if (clazz != null && Block.class.isAssignableFrom(clazz)) {
      return (LegacyRegistrySink<T>) findBlockRegistry();
    }
    if (clazz != null && Item.class.isAssignableFrom(clazz)) {
      return (LegacyRegistrySink<T>) findItemRegistry();
    }
    return value -> {};
  }

  private static LegacyRegistrySink<Block> findBlockRegistry() {
    return value -> {
      if (value == null) {
        return;
      }
      ResourceLocation key = BuiltInRegistries.BLOCK.getKey(value);
      if (key != null && !BuiltInRegistries.BLOCK.getDefaultKey().equals(key)) {
        return;
      }
      if (LegacyBlockRegistry.getPendingKey(value) != null) {
        PENDING_BLOCKS.add(value);
      }
    };
  }

  private static LegacyRegistrySink<Item> findItemRegistry() {
    return value -> {
      // Items are registered via DeferredRegister; 1.12 GameRegistry.register is a no-op.
    };
  }

  public static void addShapedRecipe(
      ResourceLocation name,
      ResourceLocation group,
      ItemStack output,
      Object... recipe) {
    PENDING_RECIPES.add(new RecipeEntry(name, group, output, recipe, true));
  }

  public static void addShapelessRecipe(
      ResourceLocation name,
      ResourceLocation group,
      ItemStack output,
      Ingredient... ingredients) {
    PENDING_RECIPES.add(new RecipeEntry(name, group, output, ingredients, false));
  }

  public static void addSmelting(Object input, ItemStack output, float experience) {
    PENDING_SMELTING.add(new SmeltEntry(input, output, experience));
  }

  /** 1.20 smoker recipe (100 ticks). Furnace {@link #addSmelting} does not apply to smokers. */
  public static void addSmoking(Object input, ItemStack output, float experience) {
    REGISTERED_SMOKING.add(new SmeltEntry(input, output, experience));
  }

  public static void registerWorldGenerator(Object generator, int weight) {
    // Registered from ChaosPersists.preInit (ChaosWorld / Forge hooks).
  }

  @SubscribeEvent
  public static void onRegister(RegisterEvent event) {
    if (event.getRegistryKey().equals(Registries.BLOCK)) {
      event.register(
          Registries.BLOCK,
          helper -> {
            for (Block block : PENDING_BLOCKS) {
              ResourceLocation key = LegacyBlockRegistry.getPendingKey(block);
              if (key != null) {
                helper.register(key, block);
                LegacyBlockRegistry.clear(block);
              }
            }
          });
      PENDING_BLOCKS.clear();
    }
    if (event.getRegistryKey().equals(Registries.ITEM)) {
      event.register(
          Registries.ITEM,
          helper -> {
            for (Block block : BuiltInRegistries.BLOCK) {
              ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(block);
              if (rl == null || !"chaospersists".equals(rl.getNamespace())) {
                continue;
              }
              String path = rl.getPath();
              if ("pizza".equals(path)
                  || "ducttape".equals(path)
                  || "island".equals(path)
                  || path.startsWith("potted_")) {
                continue;
              }
              if (BuiltInRegistries.ITEM.containsKey(rl)) {
                continue;
              }
              helper.register(rl, new BlockItem(block, new Properties()));
            }
          });
    }
  }

  static void injectLegacyRecipes(RecipeManager manager) {
    if (manager == null
        || (REGISTERED_RECIPES.isEmpty()
            && REGISTERED_SMELTING.isEmpty()
            && REGISTERED_SMOKING.isEmpty())) {
      return;
    }
    try {
      smeltRecipeCounter = 0;
      smokeRecipeCounter = 0;
      List<Recipe<?>> built =
          LegacyRecipeInjector.drainPending(
              new ArrayList<>(REGISTERED_RECIPES),
              new ArrayList<>(REGISTERED_SMELTING),
              new ArrayList<>(REGISTERED_SMOKING));
      LegacyRecipeInjector.inject(manager, built);
    } catch (Throwable t) {
      org.apache.logging.log4j.LogManager.getLogger(GameRegistry.class)
          .warn("ChaosPersists: failed to re-inject legacy recipes after datapack reload", t);
    }
  }

  @Mod.EventBusSubscriber(modid = "chaospersists", bus = Mod.EventBusSubscriber.Bus.FORGE)
  public static final class ForgeHooks {
    private ForgeHooks() {}

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
      RecipeManager manager = event.getServerResources().getRecipeManager();
      event.addListener(
          new SimplePreparableReloadListener<Void>() {
            @Override
            protected Void prepare(ResourceManager resourceManager, ProfilerFiller profiler) {
              return null;
            }

            @Override
            protected void apply(
                Void unused, ResourceManager resourceManager, ProfilerFiller profiler) {
              injectLegacyRecipes(manager);
            }
          });
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
      if (event.getPlayerList() == null || event.getPlayerList().getServer() == null) {
        return;
      }
      injectLegacyRecipes(event.getPlayerList().getServer().getRecipeManager());
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
      injectLegacyRecipes(event.getServer().getRecipeManager());
    }
  }

  static final class RecipeEntry {
    private final ResourceLocation name;
    private final ResourceLocation group;
    private final ItemStack output;
    private final Object[] data;
    private final boolean shaped;

    RecipeEntry(
        ResourceLocation name,
        ResourceLocation group,
        ItemStack output,
        Object[] data,
        boolean shaped) {
      this.name = name;
      this.group = group;
      this.output = output;
      this.data = data;
      this.shaped = shaped;
    }

    Recipe<?> build() {
      if (shaped) {
        return LegacyRecipeBuilder.buildShaped(name, group, output, data);
      }
      return LegacyRecipeBuilder.buildShapeless(
          name, group, output, (Ingredient[]) data);
    }
  }

  static final class SmeltEntry {
    private final Object input;
    private final ItemStack output;
    private final float experience;

    SmeltEntry(Object input, ItemStack output, float experience) {
      this.input = input;
      this.output = output;
      this.experience = experience;
    }

    Recipe<?> build() {
      Ingredient ingredient = LegacyRecipeBuilder.ingredientFrom(input);
      ResourceLocation id =
          new ResourceLocation(
              "chaospersists",
              "smelt_"
                  + BuiltInRegistries.ITEM.getKey(output.getItem()).getPath()
                  + "_"
                  + (smeltRecipeCounter++));
      return new SmeltingRecipe(
          id,
          "",
          CookingBookCategory.MISC,
          ingredient,
          output,
          experience,
          200);
    }

    Recipe<?> buildSmoking() {
      Ingredient ingredient = LegacyRecipeBuilder.ingredientFrom(input);
      ResourceLocation id =
          new ResourceLocation(
              "chaospersists",
              "smoke_"
                  + BuiltInRegistries.ITEM.getKey(output.getItem()).getPath()
                  + "_"
                  + (smokeRecipeCounter++));
      return new SmokingRecipe(
          id,
          "",
          CookingBookCategory.FOOD,
          ingredient,
          output,
          experience,
          100);
    }
  }
}
