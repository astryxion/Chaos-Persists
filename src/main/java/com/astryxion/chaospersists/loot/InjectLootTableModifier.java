package com.astryxion.chaospersists.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

/** Rolls an extra datapack loot table into another table (vanilla chests). */
public class InjectLootTableModifier extends LootModifier {

  public static final Supplier<MapCodec<InjectLootTableModifier>> CODEC =
      Suppliers.memoize(
          () ->
              RecordCodecBuilder.mapCodec(
                  inst ->
                      codecStart(inst)
                          .and(
                              ResourceLocation.CODEC
                                  .fieldOf("loot_table")
                                  .forGetter(m -> m.lootTable))
                          .apply(inst, InjectLootTableModifier::new)));

  private final ResourceLocation lootTable;

  public InjectLootTableModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
    super(conditionsIn);
    this.lootTable = lootTable;
  }

  @Override
  protected ObjectArrayList<ItemStack> doApply(
      ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (context.getLevel() == null || context.getLevel().getServer() == null) {
      return generatedLoot;
    }
    LootTable table = context.getLevel().getServer().reloadableRegistries().getLootTable(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, this.lootTable));
    if (table != null && table != LootTable.EMPTY) {
      // Raw roll skips global loot modifiers so this cannot recurse into itself.
      table.getRandomItemsRaw(context, generatedLoot::add);
    }
    return generatedLoot;
  }

  @Override
  public MapCodec<? extends IGlobalLootModifier> codec() {
    return CODEC.get();
  }
}
