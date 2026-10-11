package com.astryxion.chaospersists.loot;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.serialization.Codec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ChaosLootModifiers {

  public static final DeferredRegister<Codec<? extends net.minecraftforge.common.loot.IGlobalLootModifier>>
      SERIALIZERS =
          DeferredRegister.create(
              ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ChaosPersists.MODID);

  public static final RegistryObject<Codec<InjectLootTableModifier>> INJECT_LOOT_TABLE =
      SERIALIZERS.register("inject_loot_table", InjectLootTableModifier.CODEC);

  public static final RegistryObject<Codec<StripDisabledChaosItemsModifier>> STRIP_DISABLED_ITEMS =
      SERIALIZERS.register("strip_disabled_items", StripDisabledChaosItemsModifier.CODEC);

  private ChaosLootModifiers() {}

  public static void register(IEventBus modBus) {
    SERIALIZERS.register(modBus);
  }
}
