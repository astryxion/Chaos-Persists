package com.astryxion.chaospersists.legacy.minecraft.util.math;

import net.minecraft.util.Mth;

/** Legacy 1.12 {@code MathHelper} — delegates to {@link Mth}. */
public final class MathHelper {
  private MathHelper() {}

  public static int floor(double value) {
    return Mth.floor(value);
  }

  public static int floor(float value) {
    return Mth.floor(value);
  }

  public static float sqrt(float value) {
    return Mth.sqrt(value);
  }

  public static float sin(float value) {
    return Mth.sin(value);
  }

  public static float cos(float value) {
    return Mth.cos(value);
  }

  public static double clamp(double value, double min, double max) {
    return Mth.clamp(value, min, max);
  }

  public static double clampedLerp(double from, double to, double delta) {
    return Mth.lerp(Mth.clamp(delta, 0.0, 1.0), from, to);
  }
}
