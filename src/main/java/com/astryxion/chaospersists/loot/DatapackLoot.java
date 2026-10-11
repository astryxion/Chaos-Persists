package com.astryxion.chaospersists.loot;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

/**
 * Datapack loot tables win, matching {@code LegacyRecipeInjector}. Missing JSON keeps the original
 * OreSpawn Java drops. A JSON file at the entity/block loot table id (including empty pools)
 * replaces those Java drops.
 */
public final class DatapackLoot {

  private DatapackLoot() {}

  public static boolean useEntityTable(LivingEntity entity) {
    if (!(entity.level() instanceof ServerLevel server) || server.getServer() == null) {
      return false;
    }
    return hasTable(server, entity.getLootTable());
  }

  public static boolean hasTable(ServerLevel level, ResourceLocation id) {
    if (level == null || level.getServer() == null || id == null || BuiltInLootTables.EMPTY.equals(id)) {
      return false;
    }
    LootTable table = level.getServer().getLootData().getLootTable(id);
    return table != null && table != LootTable.EMPTY;
  }

  @Nullable
  public static List<ItemStack> tryBlockTable(Block block, BlockState state, LootParams.Builder builder) {
    ServerLevel level = builder.getLevel();
    if (level == null || block == null) {
      return null;
    }
    ResourceLocation id = block.getLootTable();
    if (!hasTable(level, id)) {
      return null;
    }
    LootParams params =
        builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
    return level.getServer().getLootData().getLootTable(id).getRandomItems(params);
  }
}
