package com.astryxion.chaospersists.loot;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class ChaosLootModifiers {

  public static final DeferredRegister<MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier>>
      SERIALIZERS =
          DeferredRegister.create(
              NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ChaosPersists.MODID);

  public static final DeferredHolder<MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier>, MapCodec<InjectLootTableModifier>> INJECT_LOOT_TABLE =
      SERIALIZERS.register("inject_loot_table", InjectLootTableModifier.CODEC);

  public static final DeferredHolder<MapCodec<? extends net.neoforged.neoforge.common.loot.IGlobalLootModifier>, MapCodec<StripDisabledChaosItemsModifier>> STRIP_DISABLED_ITEMS =
      SERIALIZERS.register("strip_disabled_items", StripDisabledChaosItemsModifier.CODEC);

  private ChaosLootModifiers() {}

  public static void register(IEventBus modBus) {
    SERIALIZERS.register(modBus);
  }
}
