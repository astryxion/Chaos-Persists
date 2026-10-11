package com.astryxion.chaospersists.menu;

import com.astryxion.chaospersists.blockentity.TileEntityCrystalFurnace;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceMenu;

public class ContainerCrystalFurnace extends FurnaceMenu {

    public ContainerCrystalFurnace(int windowId, Inventory playerInventory, TileEntityCrystalFurnace furnace) {
        super(windowId, playerInventory, furnace, furnace.getDataAccess());
    }
}
