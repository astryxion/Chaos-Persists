package com.astryxion.chaospersists.legacy.minecraft.block;

import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;

/** Legacy 1.12 {@code BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY}. */
public final class BlockDispenser {

  public static final DispenseBehaviorRegistry DISPENSE_BEHAVIOR_REGISTRY =
      new DispenseBehaviorRegistry();

  private BlockDispenser() {}

  public static final class DispenseBehaviorRegistry {
    public void putObject(Item item, DispenseItemBehavior behavior) {
      DispenserBlock.registerBehavior(item, behavior);
    }
  }
}
