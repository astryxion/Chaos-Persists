package com.astryxion.chaospersists.legacy.minecraft.entity.passive;

import net.minecraft.world.entity.animal.horse.Horse;

/** Legacy 1.12 horse entity type reference. */
public class EntityHorse extends Horse {
  public EntityHorse(net.minecraft.world.entity.EntityType<? extends Horse> type, net.minecraft.world.level.Level level) {
    super(type, level);
  }
}
