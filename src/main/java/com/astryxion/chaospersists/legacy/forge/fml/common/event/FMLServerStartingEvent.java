package com.astryxion.chaospersists.legacy.forge.fml.common.event;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;

/** Legacy 1.12 server-start event — forwards to brigadier command registration. */
public final class FMLServerStartingEvent {

  private final RegisterCommandsEvent delegate;

  public FMLServerStartingEvent(RegisterCommandsEvent delegate) {
    this.delegate = delegate;
  }

  public void registerServerCommand(Object command) {
    if (command == null) {
      return;
    }
    try {
      command
          .getClass()
          .getMethod("register", com.mojang.brigadier.CommandDispatcher.class)
          .invoke(command, delegate.getDispatcher());
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException("Failed to register command: " + command.getClass().getName(), e);
    }
  }
}
