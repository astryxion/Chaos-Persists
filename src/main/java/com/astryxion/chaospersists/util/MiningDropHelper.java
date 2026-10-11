package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.loot.DatapackLoot;
import java.util.Collections;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public final class MiningDropHelper {

  private MiningDropHelper() {}

  public static List<ItemStack> selfDrops(Block block, LootParams.Builder builder) {
    BlockState state = builder.getOptionalParameter(LootContextParams.BLOCK_STATE);
    if (state == null) {
      state = block.defaultBlockState();
    }
    List<ItemStack> datapack = DatapackLoot.tryBlockTable(block, state, builder);
    if (datapack != null) {
      return datapack;
    }
    ItemStack tool = builder.getOptionalParameter(LootContextParams.TOOL);
    if (tool != null && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0) {
      return Collections.singletonList(new ItemStack(block));
    }
    return Collections.singletonList(new ItemStack(block.asItem()));
  }
}
