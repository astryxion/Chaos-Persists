package com.astryxion.chaospersists.legacy.minecraft.entity;

import net.minecraft.world.entity.MobCategory;

/** Legacy 1.12 creature type — maps to {@link MobCategory}. */
public enum EnumCreatureType {
  MONSTER(MobCategory.MONSTER),
  CREATURE(MobCategory.CREATURE),
  AMBIENT(MobCategory.AMBIENT),
  WATER_CREATURE(MobCategory.WATER_CREATURE),
  WATER_AMBIENT(MobCategory.WATER_AMBIENT),
  MISC(MobCategory.MISC);

  private final MobCategory category;

  EnumCreatureType(MobCategory category) {
    this.category = category;
  }

  public MobCategory toMobCategory() {
    return category;
  }
}
