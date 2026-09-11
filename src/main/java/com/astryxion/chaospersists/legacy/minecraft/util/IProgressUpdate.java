package com.astryxion.chaospersists.legacy.minecraft.util;

/** Legacy 1.12 progress callback for chunk save. */
public interface IProgressUpdate {
  void displaySavingString(String text);

  void resetProgressAndMessage(String message);

  void setLoadingProgress(int progress);

  void displayLoadingString(String text);
}
